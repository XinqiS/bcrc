package com.sooka.module.web.cms;

import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.sooka.common.annotation.SysLog;
import com.sooka.common.constant.CmsConst;
import com.sooka.common.exception.CmsException;
import com.sooka.common.utils.*;
import com.sooka.component.lucene.LuceneManager;
import com.sooka.component.lucene.util.IndexObject;
import com.sooka.module.web.cms.service.*;
import com.sooka.module.web.system.vo.UserVo;
import com.sooka.module.web.system.vo.tb_qy_info;
import com.sooka.mybatis.model.*;
import com.sooka.module.web.cms.vo.TCmsContentVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.core.io.FileSystemResource;
import org.springframework.integration.mail.support.MailUtils;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import java.io.File;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Description:内容管理控制器
 *
 *
 * @create 2017-04-17
 **/
@Controller
@RequestMapping("/system/cms/content")
public class ContentController{

    @Autowired
    private ContentService contentService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelService modelService;

    @Autowired
    private ModelFiledService modelFiledService;
    @Autowired
    LuceneManager luceneManager;
    
    @Autowired
    private SendMailUtil mailUtil;

    @Autowired
    private EnterpriseService enterpriseService;
    
   
    @RequestMapping
    public String index() {
        return "cms/content";
    }


    @RequiresPermissions("content:admin")
    @RequestMapping("/page")
    public String page(@RequestParam(value = "pageCurrent",defaultValue = "1") Integer pageNumber,
                        @RequestParam(value = "pageSize",defaultValue = "50") Integer pageSize,
                       TCmsContentVo content,
                        Model model){
        Subject currentUser = SecurityUtils.getSubject();
        TCmsCategory category =categoryService.findById(content.getCategoryId());
        if(!CmsUtil.isNullOrEmpty(content.getCategoryId())
                &&!StrUtil.isBlank(category.getPermissionKey())
                &&!currentUser.isPermitted(category.getPermissionKey())) {
            throw new CmsException("对不起,您没有当前栏目的管理权限！");
        }
        UserVo userVo = ((UserVo) ControllerUtil.getHttpSession().getAttribute(CmsConst.SITE_USER_SESSION_KEY));
        if(CmsUtil.isNullOrEmpty(userVo)) {
            throw  new UnauthenticatedException();
        }
        content.setSiteId(userVo.getSiteId());
        content.setUserId(userVo.getUserId());
        model.addAttribute("model",contentService.page(pageNumber,pageSize,content));
        model.addAttribute("pojo",content);

        if(content.getCategoryId()==220){
            return "cms/dbt_list";
        }else {
            return "cms/content_list";
        }

    }

    @SysLog("内容添加")
    @RequiresPermissions("content:input")
    @RequestMapping("/input")
    public String input(@RequestParam(value = "categoryId",required = false) Long categoryId,
                        @RequestParam(value = "contentId",required = false) Long contentId,
                        @RequestParam(value = "isWindow",defaultValue = "NO") String isWindow,
                        Model model) throws MessagingException {
        TCmsCategory category =categoryService.findById(categoryId);
        if(CmsUtil.isNullOrEmpty(category)) {
            throw new CmsException("当前栏目已被删除！");
        }
        Subject currentUser = SecurityUtils.getSubject();
        if(!StrUtil.isBlank(category.getPermissionKey())&&!currentUser.isPermitted(category.getPermissionKey())) {
            throw new CmsException("对不起,您没有当前栏目的管理权限！");
        }
        TCmsModel cmsModel = modelService.findById(category.getModelId());
        List<TCmsModelFiled> cmsModelFileds = modelFiledService.findModelFiledListByModelId(cmsModel.getModelId());
        if(contentId!=null) {
            model.addAttribute("content",contentService.findContentByContentIdAndTableName(contentId,cmsModel.getTableName()));
        }
        model.addAttribute("modelFiled",cmsModelFileds);
        model.addAttribute("category",category);
        model.addAttribute("isWindow",isWindow);
        mailUtil.sendStringMail("1732036358@qq.com", "测试主体", "你的密码66666，请牢记！！！");
        /**如果是以下栏目ID，说明是企业中心信息，需要查询企业列表
         *1033,1034,1035,1036,1037,1038,1039,1040
         **/
        List<Long> qyzxContentList = Arrays.asList(1033L,1034L,1035L,1036L,1037L,1038L,1039L,1040L);
        List<tb_qy_info> qy_infoList = null;
        if(qyzxContentList.contains(categoryId)){
            qy_infoList = enterpriseService.findAll();
        }
        model.addAttribute("enterpriseList",qy_infoList);
        return "cms/content_input";
    }

    @SysLog("内容复制")
    @RequiresPermissions("content:input")
    @RequestMapping("/copy")
    public String copy(@RequestParam(value = "categoryId",required = false) Long categoryId,
                        @RequestParam(value = "contentId",required = false) Long contentId,
                        @RequestParam(value = "isWindow",defaultValue = "NO") String isWindow,
                        Model model) {
        TCmsCategory category =categoryService.findById(categoryId);
        if(CmsUtil.isNullOrEmpty(category)) {
            throw new CmsException("当前栏目已被删除！");
        }
        Subject currentUser = SecurityUtils.getSubject();
        if(!StrUtil.isBlank(category.getPermissionKey())&&!currentUser.isPermitted(category.getPermissionKey())) {
            throw new CmsException("对不起,您没有当前栏目的管理权限！");
        }
        TCmsModel cmsModel = modelService.findById(category.getModelId());
        List<TCmsModelFiled> cmsModelFileds = modelFiledService.findModelFiledListByModelId(cmsModel.getModelId());
        if(contentId!=null) {
            model.addAttribute("content",contentService.findContentByContentIdAndTableName(contentId,cmsModel.getTableName()));
        }
        model.addAttribute("modelFiled",cmsModelFileds);
        model.addAttribute("category",category);
        model.addAttribute("isWindow",isWindow);
        return "cms/content_copy";
    }
    @RequestMapping("/excel")
    public ModelAndView excel(){
        ExcelUtil.exports2007("123",contentService.page(1,20,new TCmsContentVo()).getList());
        return null;
    }

    @RequiresPermissions("content:save")
    @RequestMapping("/save")
    @ResponseBody
    public String save(TCmsContent content, HttpServletRequest request,
                       @RequestParam(value = "tag",required = false) String[] tags
                      ) throws SQLException {
        TCmsCategory category =categoryService.findById(content.getCategoryId());
        TCmsModel cmsModel = modelService.findById(category.getModelId());
        List<TCmsModelFiled> cmsModelFileds = modelFiledService.findModelFiledListByModelId(category.getModelId());
        UserVo userVo = UserUtil.getSysUserVo();
        content.setSiteId(userVo.getSiteId());
        content.setUserId(userVo.getUserId());
        content.setModelId(category.getModelId());
        /*Jin：使用Map接收：遍历获取自定义模型字段*/
        Map<String, Object> formParam =Maps.newHashMap();
        for (TCmsModelFiled filed : cmsModelFileds) {
            if(filed.getFiledClass().equals("checkbox")||filed.getFiledClass().equals("image")) {
                String[] filedValue = request.getParameterValues(filed.getFiledName());
                if (filedValue!=null) {
                    formParam.put(filed.getFiledName(), filedValue);
                }
            }else {
                String filedValue = request.getParameter(filed.getFiledName());
                if (!StrUtil.isBlank(filedValue)) {
                    formParam.put(filed.getFiledName(), filedValue);
                }
            }
        }
        //代表团没有title用name值代替
        if(content.getTitle().equals("")){
            content.setTitle(formParam.get("name").toString());
        }
        if(content.getContentId()!=null) {
            return contentService.update(content,cmsModel.getTableName(),cmsModelFileds,formParam,tags);
        }
        return contentService.save(content,cmsModel.getTableName(),formParam,tags);
    }

    @SysLog("内容删除")
    @RequiresPermissions("content:delete")
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam(value = "ids",required = false) Long[] ids) {
        return contentService.delete(ids);
    }

    @SysLog("批量发布")
    @RequestMapping("/release")
    @ResponseBody
    public String release(@RequestParam(value = "ids",required = false) Long[] ids) {
        return contentService.release(ids);
    }


    @RequestMapping("/recovery")
    @ResponseBody
    public String recovery(@RequestParam(value = "ids",required = false) Long[] ids) {
        return contentService.recovery(ids);
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
    @RequestMapping("/selectKeyWord")
    public String search(@RequestParam(value = "pageCurrent",defaultValue = "1") Integer pageNumber,
                         @RequestParam(value = "pageSize",defaultValue = "10") Integer pageSize,String keyword,Model model){
        PageInfo p = luceneManager.page(pageNumber,pageSize,keyword);
        List<IndexObject> list;
        if(p!=null){
            list = p.getList();
            model.addAttribute("model",list);
            model.addAttribute("total",p.getTotal());//总数
            model.addAttribute("pagesize",p.getPageSize());//页数
            model.addAttribute("pagenum",p.getPageNum());
            model.addAttribute("keyword",keyword);
        }else {
            list = new ArrayList<>();
            model.addAttribute("model",list);
            model.addAttribute("total",1);//总数
            model.addAttribute("pagesize",1);//页数
            model.addAttribute("pagenum",1);
            model.addAttribute("keyword",keyword);
        }


        if(ControllerUtil.isMobile()){
            return "www/jlrd-mobile/ssjg";
        }
        return "www/jlrd/ssjg";
    }


    @RequestMapping("/selectList")
    @ResponseBody
    public Object lchList(@RequestParam(value = "pageCurrent",defaultValue = "1") Integer pageNumber,
                        @RequestParam(value = "pageSize",defaultValue = "20") Integer pageSize){
        PageInfo<TCmsContent> list = contentService.page(pageNumber,pageSize);
        List<TCmsContent> lchList = list.getList();
        return lchList;
    }
    
 

//常委会会议
    @RequestMapping("/selectCwhy")
    @ResponseBody
    public Object selectCwhy(Long zr_id,Long fzr_id,Long msz_id,Long cw_id){
        List list = new ArrayList();
        List<TCmsContent> zrList= contentService.findByCategoryId(zr_id);
        List<TCmsContent> fzrList= contentService.findByCategoryId(fzr_id);
        List<TCmsContent> mszList= contentService.findByCategoryId(msz_id);
        List<TCmsContent> cwList= contentService.findByCategoryId(cw_id);

        list.add(zrList);
        list.add(fzrList);
        list.add(mszList);
        list.add(cwList);
        return list;

    }
//主任会议
    @RequestMapping("/selectZrhy")
    @ResponseBody
    public Object selectZrhy(Long zr_id,Long fzr_id,Long msz_id){
        List list = new ArrayList();
        List<TCmsContent> zrList= contentService.findByCategoryId(zr_id);
        List<TCmsContent> fzrList= contentService.findByCategoryId(fzr_id);
        List<TCmsContent> mszList= contentService.findByCategoryId(msz_id);


        list.add(zrList);
        list.add(fzrList);
        list.add(mszList);
        return list;

    }

}
