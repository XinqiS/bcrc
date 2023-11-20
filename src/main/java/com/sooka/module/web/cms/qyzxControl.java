package com.sooka.module.web.cms;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.google.common.collect.Maps;
import com.sooka.common.constant.CmsConst;
import com.sooka.common.exception.CmsException;
import com.sooka.common.utils.*;
import com.sooka.module.web.cms.service.*;
import com.sooka.module.web.cms.vo.TCmsContentVo;
import com.sooka.module.web.system.vo.*;
import com.sooka.mybatis.mapper.TCmsContentMapper;
import com.sooka.mybatis.model.*;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.sooka.common.upload.UploadComponent;
import com.sooka.common.upload.bean.UploadBean;
import com.sooka.module.web.system.service.GwInfoService;
import com.sooka.module.web.system.service.QyInfoService;
import com.sooka.module.web.system.service.ZwtdjlService;
import com.sooka.mybatis.mapper.TbZwscjlMapper;

@Controller
@RequestMapping("/qyzx")
public class qyzxControl {
	@Autowired
	private QyInfoService qyInfoService;

	@Autowired
	private UploadComponent uploadComponent;

	@Autowired
	private GwInfoService gwInfoServices;

	@Autowired
	private TbZwscjlMapper scjlMapper;

	@Autowired
	private TbZwscjlService scjlService;

	@Autowired
	private ListsService listsSerivce;

	@Autowired
	private ZwtdjlService zwtdlService;

	@Autowired
	private TbZwscjlService zwscjlService;

	@Autowired
	private JljyjlService jljyjlService;

	@Autowired
	private JlgzjyService jlgzjyService;
	@Autowired
	private JlqzyxService jlqzyxService;

	@Autowired
	private CategoryService categoryService;

	@Autowired
	private ContentService contentService;

	@Autowired
	private TCmsContentMapper contentMapper;

	@Autowired
	private ModelService modelService;

	@Autowired
	private ModelFiledService modelFiledService;

	@Autowired
	private TbZwscjlService zwscService;

	/**
	 * 企业信息管理
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("goToyprclg")
	public String goToYprcgl(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info)session.getAttribute(Const.ZhInfoLoginSession);
		String zhId =user.getZh_id();
		tb_qy_info qyInfo = qyInfoService.selectAll(zhId);
		if (StrUtil.checkObjAllFieldsIsNull(qyInfo)) {
			if(ControllerUtil.mobileOrNot(request)){
				return "www/syrc-mobile/qyzx-qyxxgl";
			}
			else {
				return "www/syrc/qyzx-qyxxgl";
			}
			
		}
		String QY_SSHY = qyInfo.getQy_sshy().replace("+"," ");
		qyInfo.setQy_sshy(QY_SSHY);
		String qyfl=qyInfo.getQy_qyfl();
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		if(StringUtils.isNotBlank(qyfl)){
			String[] QY_QYFL = qyfl.split(",");
			for (String s:QY_QYFL) {
				if("1".equals(s)){
					list.add("五险一金");
				}
				if("2".equals(s)){
					list.add("节日福利");
				}
				if("3".equals(s)){
					list.add("带薪年假");
				}
				if("4".equals(s)){
					list.add("现金补贴");
				}
			}
		}
		model.addAttribute("qyInfo",qyInfo);
		model.addAttribute("list",list);
		model.addAttribute("categoryId","qyxxgl");
		if(ControllerUtil.mobileOrNot(request)){
			return "www/syrc-mobile/qyzx-qyxxgl";
		}
		else {
			return "www/syrc/qyzx-qyxxgl";
		}
		
	}
	/**
	 * 企业信息
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping("goToyprclg1")
	public String goToYprcgl1(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info)session.getAttribute(Const.ZhInfoLoginSession);
		String zhId =user.getZh_id();
		tb_qy_info qyInfo = qyInfoService.selectAll(zhId);
		String QY_SSHY = qyInfo.getQy_sshy().replace("+"," ");
		qyInfo.setQy_sshy(QY_SSHY);
		String[] QY_QYFL = qyInfo.getQy_qyfl().split(",");
		@SuppressWarnings("rawtypes")
		List list = new ArrayList();
		for (String s:QY_QYFL) {
			if("1".equals(s)){
				list.add("五险一金");
			}
			if("2".equals(s)){
				list.add("节日福利");
			}
			if("3".equals(s)){
				list.add("带薪年假");
			}
			if("4".equals(s)){
				list.add("现金补贴");
			}
		}
		model.addAttribute("qyInfo",qyInfo);
		model.addAttribute("list",list);
		model.addAttribute("categoryId","qyxxgl");
		return "www/syrc-mobile/qyzx-qyxxgl1";

	}
	/**
	 *企业基本信息修改
	 * @return
	 */
	@RequestMapping("goToQyzxQyxxxgJbxx")
	public String goToQyzxQyxxxgJbxx(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info)session.getAttribute(Const.ZhInfoLoginSession);
		String zhId =user.getZh_id();
		tb_qy_info qyInfo = qyInfoService.selectByzhId(zhId);
		model.addAttribute("qyInfo",qyInfo);
		if(ControllerUtil.mobileOrNot(request))
		return "www/syrc-mobile/qyzx-qyxxxg-jbxx";
		else
		return "www/syrc/qyzx-qyxxxg-jbxx";
	}
	/**
	 *企业logo修改
	 * @return
	 */
	@RequestMapping("goToLogoxg")
	public String goToLogoxg(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info)session.getAttribute(Const.ZhInfoLoginSession);
		String zhId =user.getZh_id();
		tb_qy_info qyInfo = qyInfoService.selectByzhId(zhId);
		model.addAttribute("qyInfo",qyInfo);
		if(ControllerUtil.mobileOrNot(request))
		return "www/syrc-mobile/qyzx-qyxxxg-logoxg";
		else
			return "www/syrc/qyzx-qyxxxg-logoxg";
	}

	/**
	 *企业密码修改
	 * @return
	 */
	@RequestMapping("goToXgmm")
	public String goToXgmm(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info)session.getAttribute(Const.ZhInfoLoginSession);
		String zhId =user.getZh_id();
		tb_qy_info qyInfo = qyInfoService.selectByzhId(zhId);
		model.addAttribute("qyInfo",qyInfo);
		model.addAttribute("categoryId","yprcgl");
		if(ControllerUtil.mobileOrNot(request))
		return "www/syrc-mobile/qyzx-qyxxxg-xgmm";
		else
		return "www/syrc/qyzx-qyxxxg-xgmm";
	}
	/**
	 * 企业信息保存
	 * @param qy
	 * @param request
	 * @return
	 */
	@RequestMapping("UpdateOrInsertQyxx")
	@ResponseBody
	public  boolean  UpdateQyxxOrInsert(@RequestParam(value = "myfile", required = false) MultipartFile multipartFile,tb_qy_info qy, HttpServletRequest request) throws Exception{
		if (null != multipartFile){
			UploadBean result = uploadComponent.uploadFile(multipartFile,request);
			qy.setQy_file_zz(result.getFileUrl());
		}
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info)session.getAttribute(Const.ZhInfoLoginSession);
		String zhId =user.getZh_id();
		qy.setZh_id(zhId);
		session.removeAttribute("QypztSession");
		session.setAttribute("QypztSession", 0);
		return	qyInfoService.UpdateQyxx(qy);
	}


	/**
	 * 企业logo保存
	 * @param qy
	 * @param request
	 * @return
	 */
	@RequestMapping("UpdateLogo")
	@ResponseBody
	public  boolean  UpdateLogo(@RequestParam(value = "myfile", required = false) MultipartFile multipartFile,tb_qy_info qy, HttpServletRequest request) throws Exception{
		if (null != multipartFile){
			UploadBean result = uploadComponent.uploadFile(multipartFile,request);
			qy.setQy_file_logo(result.getFileUrl());
		}
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info)session.getAttribute(Const.ZhInfoLoginSession);
		String zhId =user.getZh_id();
		qy.setZh_id(zhId);
		return	qyInfoService.UpdateQyxx(qy);
	}

/*
发布职位管理信息
 */

	@RequestMapping("/goToQyzxFbzwgl")
	public ModelAndView goToQyzxFbzwgl(HttpServletRequest request,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		ModelAndView mv = new ModelAndView();
		PageInfo<tb_gw_info> page =null;
		if(ControllerUtil.mobileOrNot(request)){
			mv.setViewName("www/syrc-mobile/qyzx-fbzwgl");
			page= gwInfoServices.selectByZWZt(0, request,
					pageNum,100000);
		}
		else {
			mv.setViewName("www/syrc/qyzx-fbzwgl");
			 page = gwInfoServices.selectByZWZt(0, request,
					pageNum,5);
		}
		
		for (tb_gw_info tb_gw_info : page.getList()) {
			int countByzw_id = scjlMapper.countByzw_id(tb_gw_info.getZw_id());
			tb_gw_info.setZw_gznx(countByzw_id);
			tb_gw_info.setZw_fbsj(DateUtil.dateOfConversion(tb_gw_info.getZw_fbsj()));
			tb_gw_info.setZw_gxrq(DateUtil.dateOfConversion(tb_gw_info
					.getZw_gxrq()));
		}
		page.setList(page.getList());
		mv.addObject("model", page);
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info)session.getAttribute(Const.ZhInfoLoginSession);
		String zhId =user.getZh_id();
		tb_qy_info qyInfo = qyInfoService.selectByzhId(zhId);
		mv.addObject("qyInfo",qyInfo);
		//标示传递ON
		mv.addObject("categoryId","lwpqfw");
		return mv;
	}

	@RequestMapping("/goToQyzxFbzwglForztfb")
	public ModelAndView goToQyzxFbzwglForztfb(
			HttpServletRequest request,
			@RequestParam(value = "pageNum2", defaultValue = "1") Integer pageNum2) {
		ModelAndView mv = new ModelAndView();
		
		PageInfo<tb_gw_info> page2 = null;
		if(ControllerUtil.mobileOrNot(request)){
			page2 = gwInfoServices.selectByZWZt(1, request,pageNum2,10000000);
			mv.setViewName("www/syrc-mobile/qyzx-fbzwglForztfb");
		}
		else {
			mv.setViewName("www/syrc/qyzx-fbzwglForztfb");
			page2 = gwInfoServices.selectByZWZt(1, request,pageNum2,5);
		}
		for (tb_gw_info tb_gw_info : page2.getList()) {
			int countByzw_id = scjlMapper.countByzw_id(tb_gw_info.getZw_id());
			tb_gw_info.setZw_gznx(countByzw_id);
			tb_gw_info.setZw_fbsj(DateUtil.dateOfConversion(tb_gw_info
					.getZw_fbsj()));
			tb_gw_info.setZw_gxrq(DateUtil.dateOfConversion(tb_gw_info
					.getZw_gxrq()));
		}
		page2.setList(page2.getList());
		mv.addObject("model2", page2);
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info)session.getAttribute(Const.ZhInfoLoginSession);
		String zhId =user.getZh_id();
		tb_qy_info qyInfo = qyInfoService.selectByzhId(zhId);
		mv.addObject("qyInfo",qyInfo);
		return mv;
	}
	/*
	发布招聘信息
	 */
	@RequestMapping("/goToQyzxZjgw")
	public ModelAndView goToQyzxZjgw(HttpServletRequest request) {
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info)session.getAttribute(Const.ZhInfoLoginSession);
		String zhId =user.getZh_id();
		tb_qy_info qyInfo = qyInfoService.selectAll(zhId);
		ModelAndView mv = new ModelAndView();
		if(ControllerUtil.mobileOrNot(request))
			mv.setViewName("www/syrc-mobile/qyzx-zjgw");
		else 
		mv.setViewName("www/syrc/qyzx-zjgw");


		mv.addObject("categoryId","fbzwgl");
		mv.addObject("qyInfo",qyInfo);
		return mv;
	}

	@RequestMapping("insertQyzxZjgw")
	@ResponseBody
	public String insertQyzxZjgw(tb_gw_info gw, HttpServletRequest request) {
		gwInfoServices.insertQyzxZjgw(gw, request);
		return "{\"msg\": \"成功\" }";
	}

	@RequestMapping("/gwDeleteById")
	public String gwDeleteById(tb_gw_info gw) {
		gwInfoServices.deleteByPrimaryKey(gw);
		return "redirect:/qyzx/goToQyzxFbzwgl";
	}

	@RequestMapping("/selectOne")
	public ModelAndView selectOne(String zw_id,String url,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info)session.getAttribute(Const.ZhInfoLoginSession);
		String zhId =user.getZh_id();
		tb_qy_info qyInfo = qyInfoService.selectAll(zhId);
		mv.addObject("gw", gwInfoServices.selectOne(zw_id));
		mv.addObject("url",url);
		mv.addObject("qyInfo",qyInfo);
		if(ControllerUtil.mobileOrNot(request))
		mv.setViewName("www/syrc-mobile/qyzx-zjgw");
		else
		mv.setViewName("www/syrc/qyzx-zjgw");
		return mv;
	}

	@RequestMapping("/update")
	@ResponseBody
	public String update(tb_gw_info gw) {
		gwInfoServices.update1(gw);
		return "{\"msg\": \"成功\" }";
	}

	@RequestMapping("/updateZt")
	public String updateZt(String zw_id) {
		gwInfoServices.updateZt(zw_id);
		return "redirect:/qyzx/goToQyzxFbzwgl";
	}
	@RequestMapping("/updatefb")
	public String updatefb(String zw_id) {
		gwInfoServices.updatefb(zw_id);
		return "redirect:/qyzx/goToQyzxFbzwglForztfb";
	}

	@RequestMapping("/goToQyzxYprcgl")
	public ModelAndView goToQyzxYprcgl(
			HttpServletRequest request,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		ModelAndView mv = new ModelAndView();
		PageInfo<Lists> page =null;
	if(ControllerUtil.mobileOrNot(request)){
		mv.setViewName("www/syrc-mobile/qyzx-yprcgl");
		page= listsSerivce.page(pageNum, 1000000, request, 0);
		
	}else{
		mv.setViewName("www/syrc/qyzx-yprcgl");
		page = listsSerivce.page(pageNum, 5, request, 0);
	}

		for (Lists lists : page.getList()) {
			lists.setJianli_csrq(DateUtil.dateOfConversion(lists
					.getJianli_csrq()));
			lists.setTdjl_time(DateUtil.dateOfConversion(lists.getTdjl_time()));
		}
		page.setList(page.getList());
		mv.addObject("model", page);
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info)session.getAttribute(Const.ZhInfoLoginSession);
		String zhId =user.getZh_id();
		tb_qy_info qyInfo = qyInfoService.selectByzhId(zhId);
		mv.addObject("qyInfo",qyInfo);
		mv.addObject("categoryId","rczpfw");
		return mv;
	}

	@RequestMapping("/goToQyzxYprcglForMsyq")
	public ModelAndView goToQyzxYprcglForMsyq(
			HttpServletRequest request,
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum) {
		ModelAndView mv = new ModelAndView();
		PageInfo<Lists> page=null;
		if(ControllerUtil.mobileOrNot(request)){
			mv.setViewName("www/syrc-mobile/qyzx-yprcglForMsyq");
			page = listsSerivce.page(pageNum, 100000, request, 1);
		}
		else{
		mv.setViewName("www/syrc/qyzx-yprcglForMsyq");
		page = listsSerivce.page(pageNum, 5, request, 1);
		}
		for (Lists lists : page.getList()) {
			lists.setJianli_csrq(DateUtil.dateOfConversion(lists
					.getJianli_csrq()));
			lists.setTdjl_time(DateUtil.dateOfConversion(lists.getTdjl_time()));
			lists.setTdjl_yqsj(DateUtil.dateOfConversion(lists.getTdjl_yqsj()));
		}
		page.setList(page.getList());
		mv.addObject("model", page);
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info)session.getAttribute(Const.ZhInfoLoginSession);
		String zhId =user.getZh_id();
		tb_qy_info qyInfo = qyInfoService.selectByzhId(zhId);
		mv.addObject("qyInfo",qyInfo);
		return mv;
	}

	@RequestMapping("/updateMsyqByPrimiryKey")
	public String updateMsyqByPrimiryKey(String tdjl_id) {
		zwtdlService.updateMsyqByPrimiryKey(tdjl_id);
		return "redirect:/qyzx/goToQyzxYprcgl";
	}

	@RequestMapping("/deleteByPrimiryKey")
	public String deleteByPrimiryKey(String tdjl_id) {
		zwtdlService.deleteByPrimiryKey(tdjl_id);
		return "redirect:/qyzx/goToQyzxYprcgl";
	}
	@RequestMapping("/deleteByPrimiryKeyForMsyq")
	public String deleteByPrimiryKeyForMsyq(String tdjl_id) {
		zwtdlService.deleteByPrimiryKey(tdjl_id);
		return "redirect:/qyzx/goToQyzxYprcglForMsyq";
	}



	/**
	 *搜索人才页面
	 * @return
	 */
	/*@RequestMapping("searchTalents")
	public String searchTalents(HttpServletRequest request, Model model) {
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info)session.getAttribute(Const.ZhInfoLoginSession);
		String zhId =user.getZh_id();
		tb_qy_info qyInfo = qyInfoService.selectByzhId(zhId);
		model.addAttribute("qyInfo",qyInfo);
		return "www/syrc/qyzx-ssrc";
	}*/
	/**
	 *求职信息
	 * @return
	 */
	@RequestMapping("/searchTalents")
	@ResponseBody

	public ModelAndView  screeningResume(HttpServletRequest request,@RequestParam(value = "pageCurrent",defaultValue = "1",required = false) Integer pageNumber,
											  @RequestParam(value = "pageSize",defaultValue ="5",required = false) Integer pageSize,
											  JlAll jl){
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info)session.getAttribute(Const.ZhInfoLoginSession);
		String zhId =user.getZh_id();
		tb_qy_info qyInfo = qyInfoService.selectByzhId(zhId);
		ModelAndView view =null;
		if(ControllerUtil.mobileOrNot(request)){
			view= new ModelAndView("www/syrc-mobile/qyzx-ssrc");
		}
		else{
			view = new ModelAndView("www/syrc/qyzx-ssrc");
		}
		PageInfo<Map<String,Object>> p;
		p = zwtdlService.pageAll(pageNumber,pageSize,jl);
		List<Map<String,Object>> list;
		if(p!=null){
			list = p.getList();

			for(int i=0;i<list.size();i++){
				List<Jljyjl> jylist = jljyjlService.findByJibanbenId(list.get(i).get("JL_BB_ID").toString());
				list.get(i).put("jylist", jylist);
				List<Jlgzjy> gzlist = jlgzjyService.findByJibanbenId(list.get(i).get("JL_BB_ID").toString());
				list.get(i).put("gzlist", gzlist);
				Jlqzyx qzyx = jlqzyxService.findJlqzyxByJlBanbenId(list.get(i).get("JL_BB_ID").toString());
				list.get(i).put("qzyx", qzyx);
			}
			view.addObject("model",list);

			view.addObject("total",p.getTotal());//总数
			view.addObject("pagesize",p.getPageSize());//页数
			view.addObject("pagenum",p.getPageNum());
		}else {
			list = new ArrayList<Map<String,Object>>();
			view.addObject("model",list);
			view.addObject("total",1);//总数
			view.addObject("pagesize",1);//页数
			view.addObject("pagenum",1);
		}
		view.addObject("jl", jl);
		if(qyInfo!=null){
			view.addObject("qyInfo",qyInfo);
		}

		return view;
	}
	@RequestMapping("/qyXq")
	public ModelAndView gotoQyXq(
			@RequestParam(value = "pageCurrent", defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			String qy_id,HttpServletRequest request) {
		ModelAndView mv =null;
		PageInfo<Map<String, Object>> p=null;
		if(ControllerUtil.mobileOrNot(request)) {
			mv = new ModelAndView("www/syrc-mobile/jlzx-gsxq");
			p = gwInfoServices.selectByQyIdquery(1, 1000, qy_id);
		}else {
			mv = new ModelAndView("www/syrc/jlzx-gsxq");
			p = gwInfoServices.selectByQyIdquery(pageNumber, pageSize, qy_id);
		}
		tb_qy_info selectByPrimaryKey = qyInfoService.selectByPrimaryKey(qy_id);
		Integer selectCountByQyId = gwInfoServices.selectCountByQyId(qy_id);

		List<tb_gw_info> selectByQyId = gwInfoServices.selectByQyId(qy_id);
		mv.addObject("model", selectByPrimaryKey);
		mv.addObject("count", selectCountByQyId);
		List<Map<String, Object>> list;
		if (p != null) {
			list = p.getList();
			mv.addObject("lists", p.getList());
			mv.addObject("total", p.getTotal());// 总数
			mv.addObject("pagesize", p.getPageSize());// 页数
			mv.addObject("pagenum", p.getPageNum());
		} else {
			list = new ArrayList<Map<String, Object>>();
			mv.addObject("model", list);
			mv.addObject("total", 1);// 总数
			mv.addObject("pagesize", 1);// 页数
			mv.addObject("pagenum", 1);
		}
		mv.addObject("qiyeid", qy_id);
		return mv;

	}
	@RequestMapping("gwXq")
	public ModelAndView gwXq(
			@RequestParam(value = "pageCurrent", defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			String zw_id,HttpServletRequest request) {
		ModelAndView mv=null;

		tb_gw_info selectByPrimaryKeya = gwInfoServices
				.selectByPrimaryKey(zw_id);
		tb_qy_info selectByPrimaryKey = qyInfoService.selectByPrimaryKey(selectByPrimaryKeya
				.getQy_id());
		PageInfo<Map<String, Object>> p=null;
		if(ControllerUtil.mobileOrNot(request)) {
			mv = new ModelAndView("www/syrc-mobile/jlzx-gwxq");
			p = gwInfoServices.selectByQyIdquery(1, 1000, selectByPrimaryKeya.getQy_id());
		}else{
			mv = new ModelAndView("www/syrc/jlzx-gwxq");
			p = gwInfoServices.selectByQyIdquery(pageNumber, pageSize, selectByPrimaryKeya.getQy_id());
		}

	/*	List<tb_gw_info> selectByQyId = gwInfoServices.selectByQyId(selectByPrimaryKeya
				.getQy_id());*/
		mv.addObject("list", selectByPrimaryKeya);

		mv.addObject("qy", qyInfoService.selectByPrimaryKey(selectByPrimaryKeya
				.getQy_id()));
		mv.addObject("gw",gwInfoServices.selectByQyId(selectByPrimaryKeya.getQy_id()));
		mv.addObject("model", selectByPrimaryKey);
		/*mv.addObject("lists", selectByQyId);*/
		List<Map<String, Object>> list;
		if (p != null) {
			list = p.getList();
			mv.addObject("lists", p.getList());
			mv.addObject("total", p.getTotal());// 总数
			mv.addObject("pagesize", p.getPageSize());// 页数
			mv.addObject("pagenum", p.getPageNum());
		} else {
			list = new ArrayList<Map<String, Object>>();
			mv.addObject("model", list);
			mv.addObject("total", 1);// 总数
			mv.addObject("pagesize", 1);// 页数
			mv.addObject("pagenum", 1);
		}

		//岗位收藏
		HttpSession session = request.getSession();
		tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
		if(user!=null) {
			if (user.getZh_sf() == 0) {
				tb_zwscjl scjl = new tb_zwscjl();
				scjl.setZh_id(user.getZh_id());
				List<tb_zwscjl> zwscjllist = zwscService.findList(scjl);
				mv.addObject("zwscjllistlists", zwscjllist);
			}
		}
		mv.addObject("zhiwuid", zw_id);
		return mv;
	}
	/* 列表 */
    @RequestMapping("/findQyList")
    public ModelAndView findUserResume(
            @RequestParam(value = "pageCurrent",defaultValue = "1",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue ="6",required = false) Integer pageSize, tb_qy_info qy,HttpServletRequest request){
    	ModelAndView view=null;
		if(ControllerUtil.mobileOrNot(request)){
			view = new ModelAndView("www/syrc-mobile/qyzx-qyxx");
			pageSize=100000000;
		}
		else{
			view = new ModelAndView("www/syrc/qyzx-qyxx");
		}

    	try{
    				 	PageInfo<tb_qy_info> p = qyInfoService.page(pageNumber,pageSize,qy);
    			        List<tb_qy_info> list;
    			        view.addObject("qy_sftj", qy.getQy_sftj());
    			        if(p!=null){
    			            list = p.getList();
    			            view.addObject("model",list);
    			            view.addObject("total",p.getTotal());//总数
    			            view.addObject("pagesize",p.getPageSize());//页数
    			            view.addObject("pagenum",p.getPageNum());
    			        }else {
    			            list = new ArrayList<tb_qy_info>();
    			            view.addObject("model",list);
    			            view.addObject("total",1);//总数
    			            view.addObject("pagesize",1);//页数
    			            view.addObject("pagenum",1);
    			        }
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return view;
    }
    /* 网络招聘*/
    @RequestMapping("/wlzp")
    public ModelAndView wlss(
            @RequestParam(value = "pageCurrent",defaultValue = "1",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue ="6",required = false) Integer pageSize, tb_qy_info qy,HttpServletRequest request){
    	ModelAndView view=new ModelAndView("www/syrc/wlzp");
    	try{
    				 	PageInfo<tb_qy_info> p = qyInfoService.page(pageNumber,pageSize,qy);
    			        List<tb_qy_info> list;
    			        if(p!=null){
    			            list = p.getList();
    			            view.addObject("model",list);
    			            view.addObject("total",p.getTotal());//总数
    			            view.addObject("pagesize",p.getPageSize());//页数
    			            view.addObject("pagenum",p.getPageNum());
    			        }else {
    			            list = new ArrayList<tb_qy_info>();
    			            view.addObject("model",list);
    			            view.addObject("total",1);//总数
    			            view.addObject("pagesize",1);//页数
    			            view.addObject("pagenum",1);
    			        }
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return view;
    }

    /********************李猛新增*************************
     * 2021年12月7日，松原人才新增需求 （李鹏孙伟提出）
	 * 新增栏目：
	 * 1、劳务派遣服务
	 * 2、人力资源和社会保障代理
	 * 3、人力资源外包服务
	 * 4、人才测评服务
	 * 5、人力资源培训服务
	 * 6、人力资源管理咨询服务
	 * 7、人力资源和社会保障代理服务
	 * 8、人力资源人事档案服务
	 * 以上8个栏目均需后台支撑，并与注册的企业信息相关联。
	 * ①企业登录后，在企业中心可以看到这8个栏目，可以在栏目中录入相关资讯信息，后台审核通过后，个人用户可以在相应栏目下看到文章信息，在浏览信息的同时，可以点击报名按钮，在表单中填写信息后，企业登录【企业中心】可以查看到每个信息的报名人信息，支持导出为xls格式的文件功能；针对每个用户的报名信息，支持在线打印报名单功能。
	 * ②个人用户登录后，在上述8个栏目下，浏览相应文章信息，可以点击下方的报名按钮，填写相应表单信息后，在【个人中心】中可以查看已经报名过的信息记录，并可以在线打印报名单。
	 * ③管理员登录网站后台，负责对企业在上述8个栏目中发布的文章信息进行审核，审核通过后，才会在前台网站中进行展示。
	 * ④栏目位置放置于公式公告下方，按照顺序依次排版。
	 **/

    /**
     * 新增八个栏目跳转方法，通过栏目ID区分
	 * @author limeng
     * @date 2021-12-8 13:05
     * @param request
     * @param pageNum
     * @return org.springframework.web.servlet.ModelAndView
     */
	@GetMapping("/goToQyzxEightOtherCategorys/{qyId}/{categoryId}")
	public ModelAndView goToQyzxLwpqfw(HttpServletRequest request,
									   @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
									   @PathVariable("qyId") String qyId,
									   @PathVariable("categoryId") Long categoryId) {
		ModelAndView mv = new ModelAndView();
		String viewName = "";
		Integer pageSize;
		if(ControllerUtil.mobileOrNot(request)){
			pageSize = 100000;
			viewName = "www/syrc-mobile/qyzx-eightOtherCategorys";
		}else{
			pageSize = 10;
			viewName = "www/syrc/qyzx-eightOtherCategorys";
		}
		TCmsContentVo content = new TCmsContentVo();
		content.setCategoryId(categoryId);
		content.setQy_id(qyId);
		mv.addObject("model",contentService.page(pageNum,pageSize,content));
		mv.addObject("pojo",content);
		mv.addObject("categoryName",categoryService.findById(categoryId).getCategoryName());
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info)session.getAttribute(Const.ZhInfoLoginSession);
		String zhId =user.getZh_id();
		tb_qy_info qyInfo = qyInfoService.selectByzhId(zhId);
		mv.addObject("qyInfo",qyInfo);
		mv.setViewName(viewName);
		return mv;
	}

	/***
	 * 信息删除方法，假删除只能将文章状态改为-1，真删除需要在后台删除
	 * @author limeng
	 * @date 2021-12-9 14:20
	 * @param contentId
	 * @return String
	 */
	@RequestMapping("recovery/{contentId}")
	@ResponseBody
	public String recovery(@PathVariable("contentId") String contentId){
		boolean flag_=false;
		if(contentId!=null&&!contentId.equals("")) {
			TCmsContent cmsContent = contentMapper.selectByPrimaryKey(contentId);
			cmsContent.setStatus(-1);
			flag_ =  contentMapper.updateByPrimaryKeySelective(cmsContent)>0;
		}
		if(flag_) {
			return JsonUtil.toSUCCESS("操作成功","content-tab",false);
		}
		return JsonUtil.toERROR("操作失败");
	}

	/***
	 * 去往添加信息页面
	 * @author limeng
	 * @date 2021-12-9 14:34
	 * @param request
	 * @return org.springframework.web.servlet.ModelAndView
	 */
	@RequestMapping("/goToQyzxEightOtherCategorysContentInput")
	public ModelAndView goToQyzxEightOtherCategorysContentInput(
			HttpServletRequest request,
			@RequestParam(value = "categoryId",required = false) Long categoryId,
			@RequestParam(value = "contentId",required = false) Long contentId) {
		ModelAndView mv = new ModelAndView();
		TCmsCategory category =categoryService.findById(categoryId);
		TCmsModel cmsModel = modelService.findById(category.getModelId());
		List<TCmsModelFiled> cmsModelFileds = modelFiledService.findModelFiledListByModelId(cmsModel.getModelId());
		if(contentId!=null) {
			Map map = contentService.findContentByContentIdAndTableName(contentId,cmsModel.getTableName());
			String inputdate = map.get("inputdate").toString();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
			try {
				Date date = simpleDateFormat.parse(inputdate);
				map.put("inputdate",DateUtil.formatDate(date));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			mv.addObject("content",map);
		}
		mv.addObject("modelFiled",cmsModelFileds);
		mv.addObject("category",category);
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info)session.getAttribute(Const.ZhInfoLoginSession);
		String zhId =user.getZh_id();
		tb_qy_info qyInfo = qyInfoService.selectByzhId(zhId);
		mv.addObject("qyInfo",qyInfo);
		if(ControllerUtil.mobileOrNot(request))
			mv.setViewName("www/syrc-mobile/qyzx-eightOtherCategorysContentInput");
		else
			mv.setViewName("www/syrc/qyzx-eightOtherCategorysContentInput");

		return mv;
	}

	@RequestMapping("/save")
	@ResponseBody
	public String save(TCmsContent content, HttpServletRequest request,
					   @RequestParam(value = "tag",required = false,defaultValue = "") String[] tags
	) throws SQLException {
		TCmsCategory category =categoryService.findById(content.getCategoryId());
		TCmsModel cmsModel = modelService.findById(category.getModelId());
		List<TCmsModelFiled> cmsModelFileds = modelFiledService.findModelFiledListByModelId(category.getModelId());
		content.setModelId(category.getModelId());
		content.setKeywords("");
		content.setDescription("");
		content.setSorts(999);
		/*Jin：使用Map接收：遍历获取自定义模型字段*/
		Map<String, Object> formParam = Maps.newHashMap();
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
		if(content.getContentId()!=null) {
			return contentService.update(content,cmsModel.getTableName(),cmsModelFileds,formParam,tags);
		}
		return contentService.save(content,cmsModel.getTableName(),formParam,tags);
	}

}
