package com.sooka.module.web.cms;

import com.github.pagehelper.PageInfo;
import com.sooka.common.base.BaseController;
import com.sooka.common.constant.CmsConst;
import com.sooka.common.utils.CmsUtil;
import com.sooka.common.utils.ControllerUtil;
import com.sooka.module.web.cms.service.SiteService;
import com.sooka.module.web.cms.service.TopicService;
import com.sooka.module.web.system.vo.UserVo;
import com.sooka.mybatis.model.TCmsContent;
import com.sooka.mybatis.model.TCmsSite;
import com.sooka.mybatis.model.TCmsTopic;

import java.util.List;

import org.apache.shiro.authz.UnauthenticatedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Description:专题控制器
 *
 *
 * @create 2017-07-19
 **/
@Controller
@RequestMapping("/system/cms/topic")
public class TopicController extends BaseController<TCmsTopic> {

    @Autowired
    private SiteService siteService;
	
    @Autowired
    private TopicService topicService;

    @Override
    @RequestMapping
    public String index(@RequestParam(value = "pageCurrent",defaultValue = "1") Integer pageNumber,
                        @RequestParam(value = "pageSize",defaultValue = "50")Integer pageSize,
                        TCmsTopic pojo, Model model) {
        UserVo userVo = ((UserVo) ControllerUtil.getHttpSession().getAttribute(CmsConst.SITE_USER_SESSION_KEY));
        if(CmsUtil.isNullOrEmpty(userVo)) {
            throw  new UnauthenticatedException();
        }
        pojo.setSiteId(userVo.getSiteId());
        model.addAttribute("pojo",pojo);
        model.addAttribute("model",topicService.page(pageNumber,pageSize,pojo));
        return "cms/topic_list";
    }

    @Override
    @RequestMapping("/input")
    public String input(@RequestParam(value = "id",required = false) Integer Id,
                        Model model) {
        if(Id!=null) {
            model.addAttribute("topic",topicService.findById(Id));
        }
        return "cms/topic_input";
    }

    @Override
    @RequestMapping("/save")
    @ResponseBody
    public String save(TCmsTopic pojo) {
        if(pojo.getTopicId()!=null) {
            return topicService.update(pojo);
        }
        return topicService.save(pojo);
    }

    @RequestMapping("/delete")
    @ResponseBody
    @Override
    public String delete(@RequestParam(value = "ids",required = false) Integer[] ids)  {
        return topicService.delete(ids);
    }
    
    @RequestMapping("/allList")
    public String selectInfo(@RequestParam("siteId") Integer siteId,Model model){
    	TCmsSite site = siteService.findById(siteId);
    	model.addAttribute("site",site);
        return "www/jlrd/jlrd_topic";
    }
    
    
}
