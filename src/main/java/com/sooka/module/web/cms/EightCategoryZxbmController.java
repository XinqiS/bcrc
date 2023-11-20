package com.sooka.module.web.cms;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sooka.common.annotation.SysLog;
import com.sooka.common.constant.CmsConst;
import com.sooka.common.exception.CmsException;
import com.sooka.common.utils.CmsUtil;
import com.sooka.common.utils.ControllerUtil;
import com.sooka.common.utils.DateUtil;
import com.sooka.common.utils.ExcelUtil;
import com.sooka.module.web.cms.service.CategoryService;
import com.sooka.module.web.cms.service.ContentService;
import com.sooka.module.web.cms.service.EightCategoryZxbmService;
import com.sooka.module.web.cms.service.SiteService;
import com.sooka.module.web.system.vo.tb_zh_info;
import com.sooka.mybatis.model.*;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.*;

/**
 * @author limeng
 * @date 2021年12月16日 13:44
 */
@Controller
@RequestMapping("/ecZxbm")
public class EightCategoryZxbmController {

    @Autowired
    private ContentService contentService;

    @Autowired
    private SiteService siteService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private EightCategoryZxbmService eightCategoryZxbmService;

    @GetMapping("/zxbm/{siteId}/{categoryId}/{contentId}")
    public ModelAndView zxbm(@PathVariable(value = "contentId",required = false) Long contentId,
                             @PathVariable(value = "siteId",required = false) Integer siteId,
                             @PathVariable(value = "categoryId",required = false) Long categoryId,
                             HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        HttpSession session = request.getSession();
        tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
        TCmsSite site = siteService.findById(siteId);
        if(CmsUtil.isNullOrEmpty(site)) {
            throw new CmsException(CmsConst.SITE_NOT_FOUND);
        }
        TCmsCategory category = categoryService.findById(categoryId);
        if(CmsUtil.isNullOrEmpty(category)) {
            throw new CmsException(CmsConst.CATEGORY_NOT_FOUND);
        }
        mv.addObject("contentId",contentId);
        mv.addObject("site",site);
        mv.addObject("category",category);
        mv.addObject("zhId",user.getZh_id());
        if(ControllerUtil.mobileOrNot(request))
            mv.setViewName("www/syrc-mobile/ecZxbmInput");
        else
            mv.setViewName("www/syrc/ecZxbmInput");
        return mv;
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(EcZxbm ecZxbm, HttpServletRequest request){
        return eightCategoryZxbmService.save(ecZxbm);
    }

    @GetMapping("/zxbm/list/{contentId}")
    public ModelAndView list(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @PathVariable(value = "contentId",required = false) String contentId,
            HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String viewName = "";
        Integer pageSize;
        if(ControllerUtil.mobileOrNot(request)){
            pageSize = 100000;
            viewName = "www/syrc-mobile/ecZxbmList";
        }else{
            pageSize = 10;
            viewName = "www/syrc/ecZxbmList";
        }
        PageHelper.startPage(pageNum,pageSize);
        mv.addObject("contentId",contentId);
        mv.addObject("model",new PageInfo(eightCategoryZxbmService.findByContentId(contentId)));
        mv.setViewName(viewName);
        return mv;
    }

    @GetMapping("/grzx/list/{categoryId}")
    public ModelAndView grzxList(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @PathVariable(value = "categoryId",required = false) String categoryId,
            HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String viewName = "";
        Integer pageSize;
        if(ControllerUtil.mobileOrNot(request)){
            pageSize = 100000;
            viewName = "www/syrc-mobile/ecGrzxList";
        }else{
            pageSize = 10;
            viewName = "www/syrc/ecGrzxList";
        }
        try{
            HttpSession session = request.getSession();
            tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
            if(user!=null){
                PageHelper.startPage(pageNum,pageSize);
                mv.addObject("categoryId",categoryId);
                mv.addObject("flag",categoryId);
                mv.addObject("model",new PageInfo(eightCategoryZxbmService.findByCategoryId(categoryId,user.getZh_id())));
                mv.setViewName(viewName);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return mv;
    }

    @RequestMapping("/grzx/excel/{categoryId}")
    public ModelAndView grzxExcel(@PathVariable(value = "categoryId",required = false) String categoryId,
                                  HttpServletRequest request){
        HttpSession session = request.getSession();
        tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
        List<EcZxbm> list = eightCategoryZxbmService.findByCategoryId(categoryId,user.getZh_id());
        for(EcZxbm ecZxbm : list) {
            //处理性别
            String sex = ecZxbm.getSex().equals("0") ? "女" : "男";
            ecZxbm.setSex(sex);
            //处理学历
            if (ecZxbm.getEducation().equals("0")) {
                ecZxbm.setEducation("高中");
            } else if (ecZxbm.getEducation().equals("1")) {
                ecZxbm.setEducation("大专");
            } else {
                ecZxbm.setEducation("本科");
            }
        }
        ExcelUtil.exports2007("报名信息表",list);
        return null;
    }

    @PostMapping("/grzx/excelCount/{categoryId}")
    @ResponseBody
    public int excelCount(@PathVariable(value = "categoryId",required = false) String categoryId,
                                  HttpServletRequest request){
        HttpSession session = request.getSession();
        tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
        List<EcZxbm> list = eightCategoryZxbmService.findByCategoryId(categoryId,user.getZh_id());
        return list.size();
    }

    @RequestMapping("/excel/{contentId}")
    public ModelAndView excel(@PathVariable(value = "contentId",required = false) String contentId){
        List<EcZxbm> list = eightCategoryZxbmService.findByContentId(contentId);
        for(EcZxbm ecZxbm : list) {
            //处理性别
            String sex = ecZxbm.getSex().equals("0") ? "女" : "男";
            ecZxbm.setSex(sex);
            //处理学历
            if (ecZxbm.getEducation().equals("0")) {
                ecZxbm.setEducation("高中");
            } else if (ecZxbm.getEducation().equals("1")) {
                ecZxbm.setEducation("大专");
            } else {
                ecZxbm.setEducation("本科");
            }
            //处理公司名称
            TCmsContent cmsContent = contentService.findById(Long.parseLong(ecZxbm.getContentId()));
            ecZxbm.setQy_qymc(cmsContent.getQy_qymc());

        }
        ExcelUtil.exports2007("报名信息表",list);
        return null;
    }

    @PostMapping("/excelCount/{contentId}")
    @ResponseBody
    public int zxbmExcelCount(@PathVariable(value = "contentId",required = false) String contentId){
        return eightCategoryZxbmService.findByContentId(contentId).size();
    }

    @GetMapping("/zxbm/detail/{id}")
    public ModelAndView list(
            @PathVariable(value = "id",required = false) String id,
            HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        String viewName = "";
        if(ControllerUtil.mobileOrNot(request)){
            viewName = "www/syrc-mobile/ecZxbmDetail";
        }else{
            viewName = "www/syrc/ecZxbmDetail";
        }
        EcZxbm ecZxbm = eightCategoryZxbmService.getOne(id);
        mv.addObject("ecZxbm",ecZxbm);
        mv.setViewName(viewName);
        return mv;
    }
}
