package com.sooka.module.web.cms;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sooka.common.constant.CmsConst;
import com.sooka.common.exception.CmsException;
import com.sooka.common.utils.*;
import com.sooka.component.beetl.thread.HtmlThread;
import com.sooka.module.web.cms.service.*;
import com.sooka.module.web.system.service.AttachmentService;
import com.sooka.mybatis.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description:前台首页
 *
 *
 * @create 2017-04-13
 **/
@Controller
@RequestMapping("/")
public class IndexController {

    private final static Logger log = LoggerFactory.getLogger(IndexController.class);

    @Autowired
    private SiteService siteService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ContentService contentService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private ModelFiledService modelFiledService;

    @Autowired
    private LuceneService luceneService;

    @Autowired
    private TopicService topicService;

    @Autowired
    private AdService adService;

    @Autowired
    private MedicCountService medicCountService;

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private EhCacheCacheManager springEhCacheManager;

    @Value("${system.http.protocol}")
    private String httpProtocol;

    @Value("${system.site.prefix}")
    private String sitePrefix;

    @Value("${system.site.subfix}")
    private String siteSubfix;

    @Value("${system.site.static}")
    private String siteStatic;

    @Value("${system.site.page.size}")
    private String pageSize;

    private String[] caches={"cms-site-cache","cms-category-cache","shiro-kickout-cache","cms-content-cache","cms-model-cache","cms-topic-cache"};
    /*网站首页*/
    @GetMapping
    public ModelAndView index(@RequestParam(value = "keyword",required = false) String keyword){
        String domain = ControllerUtil.getDomain();
        log.debug("通过域名访问网站首页[{}]",domain);
        TCmsSite site = siteService.findByDomain(domain);
        if(CmsUtil.isNullOrEmpty(site)) {
            throw new CmsException(CmsConst.SITE_NOT_FOUND);
        }
        if(!StrUtil.isBlank(keyword)) {
            return new ModelAndView( "forward:/"+sitePrefix+"/search");
        }
        return new ModelAndView( "forward:/"+sitePrefix+"/"+site.getSiteId());
    }


    /**
     * 站点首页
     * @param siteId
     * @param model
     * @return
     */
    @GetMapping("/${system.site.prefix}/{siteId}")
    public String index(@PathVariable("siteId") Integer siteId,
                        Model model,HttpServletRequest request ){
        log.debug("通过站点Id访问网站首页[{}]",siteId);
        SimpleDateFormat formatYear = new SimpleDateFormat("yyyy");
        SimpleDateFormat format = new SimpleDateFormat("yyyy-M-d");
        SimpleDateFormat formatMonth = new SimpleDateFormat("M");
        String   callYear =formatYear.format(new Date());
        String   callMonth =formatMonth.format(new Date());

        for(String cacheNames :caches) {
            Cache cache = springEhCacheManager.getCache(cacheNames);
            cache.clear();
        }
        List<TCmsMediaCount>  list=  medicCountService.selectByYear(callYear,callMonth);
        TCmsMediaCount pojo= new TCmsMediaCount();
      if(list.size()==0){
          medicCountService.save(pojo);
      }else {
         for(TCmsMediaCount tCmsMediaCount:list){

             pojo.setId(tCmsMediaCount.getId());
             pojo.setCallConunt(tCmsMediaCount.getCallConunt());
             pojo.setCallDate(tCmsMediaCount.getCallDate());
          }
          medicCountService.updateByYear(pojo);
      }
        /*判断是否开启静态*/
        File file = new File(PathUtil.getWebRootPath() +File.separator+ "html" + File.separator+ siteId + File.separator+"index.html");
        if (file.exists() && Boolean.parseBoolean(siteStatic)&& HtmlThread.size()<=0&&!ControllerUtil.isMobile()) {
            return "redirect:/html/"+ siteId + "/index.html";
        }
        TCmsSite site = siteService.findById(siteId);
        if(CmsUtil.isNullOrEmpty(site)) {
            throw new CmsException(CmsConst.SITE_NOT_FOUND);
        }
        if(!site.getStatus()) {
            throw new CmsException(CmsConst.SITE_LOCKED);
        }
        model.addAttribute("title",site.getTitle());
        model.addAttribute("keyword",site.getKeyword());
        model.addAttribute("description",site.getDescription());
        model.addAttribute("site",site);
        if(StrUtil.isBlank(site.getTemplate())) {
            return view(CmsConst.INDEX_TPL);
        }
        if(ControllerUtil.isMobile()&&site.getIsMobile()){
        	request.getSession().setAttribute("mobile", true);
        }
        else {
        	request.getSession().setAttribute("mobile", false);
        }
        return view((ControllerUtil.isMobile()&&site.getIsMobile())?site.getMobileTpl():site.getTemplate(),CmsConst.INDEX_TPL);
    }


    @GetMapping("/${system.site.prefix}/{siteId}/{categoryId}")
    public String category(@PathVariable("siteId") Integer siteId,
                           @PathVariable("categoryId") Long categoryId,
                           Model model){
        log.debug("栏目");
        for(String cacheNames :caches) {
            Cache cache = springEhCacheManager.getCache(cacheNames);
            cache.clear();
        }
        TCmsSite site = siteService.findById(siteId);
        if(CmsUtil.isNullOrEmpty(site)) {
            throw new CmsException(CmsConst.SITE_NOT_FOUND);
        }
        TCmsCategory category = categoryService.findById(categoryId);
        if(CmsUtil.isNullOrEmpty(category)) {
            throw new CmsException(CmsConst.CATEGORY_NOT_FOUND);
        }
        PageInfo page = contentService.page(1,siteId,category.getCategoryId());
        model.addAttribute("title",category.getCategoryName());
        model.addAttribute("keyword",site.getKeyword());
        model.addAttribute("description",site.getDescription());
        model.addAttribute("site",site);
        model.addAttribute("category",category);
        model.addAttribute("page",page);
        if(StrUtil.isBlank(site.getTemplate())) {
            return view(category.getIndexTpl());
        }
      
        return view((ControllerUtil.isMobile()&&site.getIsMobile())?site.getMobileTpl():site.getTemplate(),category.getIndexTpl());
    }

    /*网站栏目列表页*/
    @GetMapping("/${system.site.prefix}/{siteId}/{categoryId}/index_{pageNumber}")
    public String page(@PathVariable("siteId") Integer siteId,
                             @PathVariable("categoryId") Long categoryId,
                             @PathVariable(value = "pageNumber") Integer pageNumber,
                             Model model){
        log.debug("列表");
        for(String cacheNames :caches) {
            Cache cache = springEhCacheManager.getCache(cacheNames);
            cache.clear();
        }
        TCmsSite site = siteService.findById(siteId);
        if(CmsUtil.isNullOrEmpty(site)) {
            throw new CmsException(CmsConst.SITE_NOT_FOUND);
        }
        TCmsCategory category = categoryService.findById(categoryId);
        if(CmsUtil.isNullOrEmpty(category)) {
            throw new CmsException(CmsConst.CATEGORY_NOT_FOUND);
        }
        if(CmsUtil.isNullOrEmpty(pageNumber)) {
            throw new CmsException(CmsConst.PAGE_NOT_FOUND);
        }
        PageInfo page = contentService.page(pageNumber,siteId,category.getCategoryId());
        model.addAttribute("title",category.getCategoryName());
        model.addAttribute("keyword",site.getKeyword());
        model.addAttribute("description",site.getDescription());
        model.addAttribute("site",site);
        model.addAttribute("category",category);
        model.addAttribute("page",page);
        if(StrUtil.isBlank(site.getTemplate())) {
            return view(category.getListTpl());
        }
        return view((ControllerUtil.isMobile()&&site.getIsMobile())?site.getMobileTpl():site.getTemplate(),category.getListTpl());
    }

    /*网站内容页*/
    @GetMapping("/${system.site.prefix}/{siteId}/{categoryId}/{contentId}")
    public String content(@PathVariable("siteId") Integer siteId,
                                @PathVariable("categoryId") Long categoryId,
                                @PathVariable("contentId") Long contentId,
                                 Model model){
        for(String cacheNames :caches) {
            Cache cache = springEhCacheManager.getCache(cacheNames);
            cache.clear();
        }
        TCmsSite site = siteService.findById(siteId);
        if(CmsUtil.isNullOrEmpty(site)) {
            throw new CmsException(CmsConst.SITE_NOT_FOUND);
        }
        TCmsCategory category = categoryService.findById(categoryId);
        if(CmsUtil.isNullOrEmpty(category)) {
            throw new CmsException(CmsConst.CATEGORY_NOT_FOUND);
        }
        TCmsModel contentModel = modelService.findById(category.getModelId());
        if(CmsUtil.isNullOrEmpty(category)) {
            throw new CmsException(CmsConst.PAGE_NOT_FOUND);
        }
        Map content = contentService.findContentByContentIdAndTableName(contentId,contentModel.getTableName());
        if(CmsUtil.isNullOrEmpty(content)) {
            throw new CmsException(CmsConst.CONTENT_NOT_FOUND);
        }
        contentService.viewUpdate(contentId);
        model.addAttribute("title",content.get("title"));
        model.addAttribute("keyword",content.get("keywords"));
        model.addAttribute("description",content.get("description"));
        model.addAttribute("site",site);
        model.addAttribute("category",category);
        model.addAttribute("content",content);
        if(StrUtil.isBlank(site.getTemplate())) {
            return this.view(category.getContentTpl());
        }
        return this.view((ControllerUtil.isMobile()&&site.getIsMobile())?site.getMobileTpl():site.getTemplate(),category.getContentTpl());
    }

    /*全文检索和模型字段检索*/
    @RequestMapping("/search")
    public String search(@RequestParam(value = "keyword",required = false) String keyword,
                         @RequestParam(value = "m",defaultValue = "0") Integer modelId,
                         @RequestParam(value = "s",defaultValue = "0") Integer siteId,
                         @RequestParam(value = "c",defaultValue = "0") Long catId,
                         @RequestParam(value = "p",defaultValue = "1") Integer pageNumber,
                         HttpServletRequest request){
        log.debug("搜索");
        for(String cacheNames :caches) {
            Cache cache = springEhCacheManager.getCache(cacheNames);
            cache.clear();
        }
        TCmsSite site = siteService.findById(siteId);
        if(CmsUtil.isNullOrEmpty(site)) {
            throw new CmsException(CmsConst.SITE_NOT_FOUND);
        }
        if (modelId > 0 && catId > 0) {
            String action = httpProtocol + "://" + ControllerUtil.getDomain()+"/"+sitePrefix+"/"+site.getSiteId();
            action += "/search?m=" + modelId + "&c=" + catId;
            TCmsCategory category = categoryService.findById(catId);
            if(CmsUtil.isNullOrEmpty(category)) {
                throw new CmsException(CmsConst.CATEGORY_NOT_FOUND);
            }
            TCmsModel model = modelService.findById(modelId);
            Map<String, Object> param = Maps.newHashMap();
            List<TCmsModelFiled> modelFileds = modelFiledService.findModelFiledListByModelId(modelId);
            /* 循环检索获取filedName和fildValue*/
            for (TCmsModelFiled filed : modelFileds) {
                String filedValue = request.getParameter(filed.getFiledName());
                if (!StrUtil.isBlank(filedValue)) {
                    param.put(HtmlKit.getText(filed.getFiledName()).trim(), HtmlKit.getText(filedValue).trim());
                    action += "&" + filed.getFiledName() + "=" + filedValue;
                }
            }
            PageInfo page = contentService.findContentListByModelFiledValue(pageNumber,catId,model.getTableName(), param);
            request.setAttribute("title",category.getCategoryName());
            request.setAttribute("keyword",site.getKeyword());
            request.setAttribute("description",site.getDescription());
            request.setAttribute("site", site);
            request.setAttribute("category", category);
            request.setAttribute("page", page);
            request.setAttribute("param", param);
            request.setAttribute("action", action);
            return view(site.getTemplate(), category.getListTpl());
        }else{
            String action = httpProtocol + "://" + ControllerUtil.getDomain()+"/"+sitePrefix+"/"+site.getSiteId();
            if(StrUtil.isBlank(keyword)) {
                throw new CmsException(CmsConst.SEARCH_KEYWORD_NOT_FOUND);
            }
            action +="/search?keyword="+keyword;
            PageInfo page =luceneService.page(pageNumber,Integer.parseInt(this.pageSize),keyword);
            request.setAttribute("page",page);
            request.setAttribute("site",site);
            request.setAttribute("action", action);
            request.setAttribute("keyword", keyword);
            return view(site.getTemplate(), CmsConst.SEARCH_TPL);
        }
    }

    /*专题*/
    @GetMapping("/${system.site.prefix}/{siteId}/topic/{topicId}")
    public String topic(@PathVariable("siteId") Integer siteId,
                        @PathVariable("topicId") Integer topicId,
                        Model model){
        for(String cacheNames :caches) {
            Cache cache = springEhCacheManager.getCache(cacheNames);
            cache.clear();
        }
        TCmsSite site = siteService.findById(siteId);
        if(CmsUtil.isNullOrEmpty(site)) {
            throw new CmsException(CmsConst.SITE_NOT_FOUND);
        }
        TCmsTopic topic =topicService.findById(topicId);
        if(CmsUtil.isNullOrEmpty(topic)) {
            throw new CmsException(CmsConst.TOPIC_NOT_FOUND);
        }
        model.addAttribute("title",topic.getTopicName());
        model.addAttribute("keyword",topic.getKeywords());
        model.addAttribute("description",topic.getDescription());
        model.addAttribute("site",site);
        model.addAttribute("topic",topic);
        return view(site.getTemplate(), !StrUtil.isBlank(topic.getTopicTpl())?topic.getTopicTpl():CmsConst.TOPIC_TPL);
    }

    @RequestMapping(value = "/ad/js/{id}",produces = "text/javascript; charset=UTF-8")
    @ResponseBody
    public String AdJs(@PathVariable Integer id){
        for(String cacheNames :caches) {
            Cache cache = springEhCacheManager.getCache(cacheNames);
            cache.clear();
        }
        if(CmsUtil.isNullOrEmpty(id)) {
            throw new CmsException("广告不存在！");
        }
        return adService.toJavascript(id);
    }


    private String view(String viewName){
        return  this.view("default",viewName);
    }

    private String view(String theme,String viewName){
        return "www/"+theme.trim()+"/"+viewName.trim();
    }
    
}
