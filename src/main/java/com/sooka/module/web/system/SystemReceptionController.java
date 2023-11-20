package com.sooka.module.web.system;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.*;
import com.sooka.module.web.cms.IndexController;
import com.sooka.module.web.cms.service.*;
import com.sooka.module.web.cms.vo.TCmsContentVo;
import com.sooka.module.web.system.service.QyInfoService;
import com.sooka.module.web.system.service.SysUserService;
import com.sooka.module.web.system.service.ZwtdjlService;
import com.sooka.module.web.system.vo.tb_qy_info;
import com.sooka.module.web.system.vo.tb_yxyz;
import com.sooka.module.web.system.vo.tb_zh_info;
import com.sooka.module.web.system.vo.tb_zwtdjl;
import com.sooka.mybatis.model.Jlinfo;
import com.sooka.mybatis.model.TCmsContent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/system/reception")
public class SystemReceptionController {
	private final static Logger log = LoggerFactory
			.getLogger(IndexController.class);

	@Autowired
	private SysUserService userService;

	@Autowired
	private EmailUtil em;

	@Autowired
	private ZwtdjlService zwtdl;

	@Autowired
	QyInfoService qyInfoService;

	@Autowired
	private JlinfoService jlService;

	@Autowired
	private ContentService contentService;

	@Autowired
	private JllljlService jllljlService;

	@Autowired
	private ZwtdService zwtdjlService;

	@Autowired
	private JlinfoService jlinfoService;

	@Value("${system.http.host}")
	private String host;

	@Value("${system.site.prefix}")
	private String sitePrefix;

	@Autowired
	private SiteService siteService;

	/**
	 * 去个人注册
	 * 
	 * @return
	 */
	@RequestMapping("/gotogrzc")
	public ModelAndView gotoregister(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if(ControllerUtil.mobileOrNot(request)){
			mv.setViewName("www/syrc-mobile/grzc");
		}
		else {
			mv.setViewName("www/syrc/grzc");
		}
		return mv;
	}

	/**
	 * 去企业注册
	 * 
	 * @return
	 */
	@RequestMapping("/gotoqyzc")
	public ModelAndView gotoqyregister(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if(ControllerUtil.mobileOrNot(request)){
			mv.setViewName("www/syrc-mobile/qyzc");
		}
		else {
			mv.setViewName("www/syrc/qyzc");
		}
		return mv;
	}

	/**
	 * 个人与企业公用注册
	 * 
	 * @param info
	 * @return
	 */
	@RequestMapping("/register")
	@ResponseBody
	public int register(tb_zh_info info) throws Exception {
		info.setZh_id(StrUtil.getUUID());
		// 个人注册
		if (info.getZh_sf() == 0) {
			userService.Register(info);
			Jlinfo jl = new Jlinfo();
			jl.setJianliId(UuidUtil.get32UUID());
			jl.setZhId(info.getZh_id());
			jl.setJianliYx(info.getZh_user());
			jlService.save(jl);
		}
		// 企业注册
		else if (info.getZh_sf() == 1) {
			userService.InsertAndSendMail(info);
		}
		return info.getZh_sf();
	}

	/**
	 * 个人与企业公用登录
	 * 
	 * @param info
	 * @return 0 为个人登录 1为企业登录 2为失败
	 */
	@RequestMapping("signin")
	@ResponseBody
	public Map<String, Object> SignInForIndividualsAndBusinesses(
			tb_zh_info info, HttpServletRequest request, String type, String url)
			throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		int flag = 2;// 0 为个人登录 1为企业登录 2为失败
		tb_zh_info signInForIndividualsAndBusinesses = userService
				.SignInForIndividualsAndBusinesses(info);
		HttpSession session = request.getSession();
		if (!StrUtil.checkObjAllFieldsIsNull(signInForIndividualsAndBusinesses)) {
			flag = signInForIndividualsAndBusinesses.getZh_sf();
			session.setAttribute("tbZhInfo_loginSession",
					signInForIndividualsAndBusinesses);
			if (url != null&&!"".equals(url)) {
				map.put("url", url);
				map.put("flag", 3);
				return map;
			}
			if (flag == 0) {
				// 首页个人中心小框
				int skgw = jllljlService
						.findSkgwCountByZhId(signInForIndividualsAndBusinesses
								.getZh_id());
				int wdsq = zwtdjlService
						.findWdsqCountByZhId(signInForIndividualsAndBusinesses
								.getZh_id());
				Jlinfo jlinfo = jlinfoService
						.findByUserCodeId(signInForIndividualsAndBusinesses
								.getZh_id());
				if (jlinfo.getJianliXm() == null) {
					jlinfo.setJianliXm("欢迎登陆");
				}
				map.put("skgw", skgw);
				map.put("wdsq", wdsq);
				map.put("jlinfo", jlinfo);
			}

			else if (flag == 1) {
				tb_qy_info selectByzhInfoId = qyInfoService
						.selectByzhInfoId(signInForIndividualsAndBusinesses
								.getZh_id());
				if (!StrUtil.checkObjAllFieldsIsNull(selectByzhInfoId)) {
					session.setAttribute("QypztSession",
							selectByzhInfoId.getQy_spzt());
				} else {
					session.setAttribute("QypztSession", "000");
				}

			}
		}
		map.put("flag", flag);
		return map;
	}
/**
 * nologin 方法
 * @param info 
 * @param request
 * @param url
 * @return Json 状态
 * @throws Exception
 */
	@SuppressWarnings("null")
	@RequestMapping("methodNotLoggedIn")
	@ResponseBody
	public Map<String, Object> methodNotLoggedIn(tb_zh_info info,
			HttpServletRequest request, String url) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		tb_zh_info signInForIndividualsAndBusinesses = userService
				.SignInForIndividualsAndBusinesses(info);
		HttpSession session = request.getSession();
		if (!StrUtil.checkObjAllFieldsIsNull(signInForIndividualsAndBusinesses)) {
			Integer flag = signInForIndividualsAndBusinesses.getZh_sf();
			session.setAttribute("tbZhInfo_loginSession",
					signInForIndividualsAndBusinesses);
			if (flag == 1) {
				tb_qy_info selectByzhInfoId = qyInfoService
						.selectByzhInfoId(signInForIndividualsAndBusinesses
								.getZh_id());
				if (!StrUtil.checkObjAllFieldsIsNull(selectByzhInfoId)) {
					session.setAttribute("QypztSession",
							selectByzhInfoId.getQy_spzt());
				} else {
					session.setAttribute("QypztSession", "000");
				}

			}
			
			if (url != null&&!url.equals("")) {
				map.put("msg", "success");
				map.put("url", url);
				return map;
			}
			else {
				map.put("msg", "notUrl");
				return map;
			}
		} 
		
		else {
			map.put("msg", "error");
			return map;
		}

	}

	/**
	 * 根据邮箱判断是否注册过
	 * 
	 * @param info
	 * @return 0没有 >0 有注册过
	 */
	@RequestMapping("QueryWhetherTheMailBoxIsUnique")
	@ResponseBody
	public int QueryWhetherTheMailBoxIsUnique(String zh_user) {
		int queryWhetherTheMailBoxIsUnique = userService
				.QueryWhetherTheMailBoxIsUnique(zh_user);
		return queryWhetherTheMailBoxIsUnique;
	}

	/**
	 * 判断验证码是否正确
	 * 
	 * @param yzm
	 * @param request
	 * @return
	 */
	@RequestMapping("yzm")
	@ResponseBody
	public boolean yzm(String yzm, HttpServletRequest request) {
		return ControllerUtil.validate(yzm, request);
	}

	/**
	 * 邮箱验证
	 * 
	 * @param name
	 * @param key
	 * @return
	 */
	@RequestMapping("/MethodName")
	public ModelAndView VerifyMailbox(String name, String key) throws Exception {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", userService.selectTBZHJHJLByNameAndKey(name, key));
		mv.setViewName("www/syrc/msg");
		return mv;
	}

	/**
	 * 去忘记密码一
	 * 
	 * @return
	 */
	@RequestMapping("/gotowjmm1")
	public ModelAndView gotowjmm1(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		if(ControllerUtil.mobileOrNot(request)){
			mv.setViewName("www/syrc-mobile/wjmm1");
		}else {
			mv.setViewName("www/syrc/wjmm1");
		}
		return mv;
	}

	/**
	 * 去忘记密码二
	 * 
	 * @return
	 */
	@RequestMapping("/gotowjmm2")
	public ModelAndView gotowjmm2(HttpServletRequest request, String zh_user) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("zh_user", zh_user);
		if(ControllerUtil.mobileOrNot(request)){
			mv.setViewName("www/syrc-mobile/wjmm2");
		}
		else {
			mv.setViewName("www/syrc/wjmm2");
		}
		return mv;
	}

	/**
	 * 去忘记密码三
	 * 
	 * @return
	 */
	@RequestMapping("/gotowjmm3")
	public ModelAndView gotowjmm3(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		String parameter = request.getParameter("zh_user");
		mv.addObject("zh_user", parameter);
		if(ControllerUtil.mobileOrNot(request)){
		mv.setViewName("www/syrc-mobile/wjmm3");
		}
		else{
			mv.setViewName("www/syrc/wjmm3");
		}
		return mv;
	}

	/**
	 * 发送邮箱验证码
	 * 
	 * @param zh_user
	 */
	@RequestMapping("/yjyzm")
	@ResponseBody
	public void yjyzm(String zh_user, HttpServletRequest request) {
		userService.yjyzm(zh_user, request);

	}

	/**
	 * 倒计时
	 * 
	 * @param zh_user
	 * @return
	 */
	@RequestMapping("/djs")
	@ResponseBody
	public long daojishi(String zh_user) {
		tb_yxyz tb = userService.selectByYHM(zh_user);
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		String timestamp = tb.getYz_yzsj().toString();
		try {
			date = format.parse(timestamp);
		} catch (Exception e) {
			e.printStackTrace();
		}
		Date dateEnd = new Date(System.currentTimeMillis());
		long timeOne = 60;
		long time = timeOne * 1000;
		date.setTime(date.getTime() - time);
		long seconds = (dateEnd.getTime() - date.getTime()) / 1000;
		seconds = seconds - 60;
		return seconds;
	}

	/**
	 * 邮箱验证码
	 */
	@RequestMapping("/yxyzforxgmm")
	@ResponseBody
	public int emailVerificationCode(String zh_user, String yzm) {
		int aa = 0;
		if (userService.emailVerificationCode(zh_user, yzm)) {
			aa = 2;
		}
		return aa;
	}

	/**
	 * 修改密码
	 * 
	 * @param zh_user
	 * @param zh_pwd
	 * @return
	 */
	@RequestMapping("/wjmmEnd")
	@ResponseBody
	public int wjmmEnd(String zh_user, String zh_pwd) throws Exception {
		int a = 0;
		userService.ChangePassword(zh_user, zh_pwd);
		return a;
	}

	@RequestMapping("/loginsuccess")
	@ResponseBody
	public String selectJlAndMsBysessionId(HttpServletRequest request) {
		HttpSession session = request.getSession();
		tb_zh_info attribute = (tb_zh_info) session
				.getAttribute("tbZhInfo_loginSession");
		tb_qy_info selectByzhInfoId = qyInfoService.selectByzhInfoId(attribute
				.getZh_id());
		if (!StrUtil.checkObjAllFieldsIsNull(selectByzhInfoId)) {
			tb_zwtdjl selectJlAndMsBysessionId = zwtdl
					.selectJlAndMsBysessionId(attribute.getZh_id());
			String jsonString = JSON.toJSONString(selectJlAndMsBysessionId);
			return jsonString;
		} else {
			String jsonString = "{\"msg\":\"error\"}";
			return jsonString;
		}

	}

	@RequestMapping("/NoLoginSearchInTheStation")
	public ModelAndView NoLoginSearchInTheStation(String title,HttpServletRequest request,
												  @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer pageNumber,
												  @RequestParam(value = "pageSize", defaultValue = "15", required = false) Integer pageSize,
												  TCmsContentVo content) {
		ModelAndView mv = new ModelAndView();
		/*List<Map<String,Object>> fuzzyQueryTitle = contentService
				.FuzzyQueryTitle(title);
		mv.addObject("list", fuzzyQueryTitle);
		mv.addObject("title", title);*/
		if(ControllerUtil.mobileOrNot(request)){
			mv.setViewName("www/syrc-mobile/ssjg");
		}
		else{
			mv.setViewName("www/syrc/ssjg");
		}
		content.setTitle(title);
		PageInfo<TCmsContent> p = contentService.pageSousuo(pageNumber,pageSize,content);
		List<Map<String, Object>> list;
		if (p != null) {
			mv.addObject("list", p.getList());
			mv.addObject("title", title);
			mv.addObject("total", p.getTotal());// 总数
			mv.addObject("pagesize", p.getPageSize());// 页数
			mv.addObject("pagenum", p.getPageNum());
			/*if(pageNumber-1>1){
				mv.addObject("sks", pageNumber-1);
			}else{
				mv.addObject("sks", 1);
			}

			mv.addObject("xks", pageNumber+1);*/

		}else {
			list = new ArrayList<Map<String, Object>>();
			mv.addObject("list", list);
			mv.addObject("total", 1);// 总数
			mv.addObject("pagesize", 1);// 页数
			mv.addObject("pagenum", 1);
			/*mv.addObject("sks", 1);
			mv.addObject("xks", 1);*/
		}

		return mv;
	}
	@RequestMapping("NoLoginSearchInTheStationNews")
	public ModelAndView NoLoginSearchInTheStationNews(String contentId,HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		TCmsContent selectOne = contentService.selectOne(contentId);
		mv.addObject("user", selectOne);
		if(ControllerUtil.mobileOrNot(request)){
			mv.setViewName("www/syrc-mobile/ssnews");
		}
		else{
			mv.setViewName("www/syrc/ssnews");
		}
		return mv;
	}

	/**
	 * 查询密码
	 * 
	 * @return
	 */
	@RequestMapping("selectPassword")
	@ResponseBody
	public Object selectPassword(String jmm,HttpServletRequest request) throws Exception {
		tb_zh_info zh = userService.selectJmm(jmm,request);
		String msg=null;
		if(zh==null){
			msg="{\"msg\": \"error\"}";
		}
		else {
		    msg="{\"msg\": \"success\"}";
		}
		return msg;
	}

	/**
	 * 修改密码
	 * 
	 * @return
	 */
	@RequestMapping("updatePassword")
	@ResponseBody
	public Object updatePassword(String jmm, String zh_pwd,
			HttpServletRequest request) throws Exception {
		tb_zh_info zh = userService.selectJmm(jmm,request);
		HttpSession session = request.getSession();
		tb_zh_info user = (tb_zh_info) session
				.getAttribute(Const.ZhInfoLoginSession);
		String zh_user = user.getZh_user();
		userService.ChangePassword(zh_user, zh_pwd);
		return zh;
	}

	/**
	 * 前台未登录，跳转登录页
	 * 
	 * @return
	 */
	@RequestMapping("/nologin")
	public ModelAndView nologin(HttpServletRequest request, String zh_user,
			String url) {
		ModelAndView mv = new ModelAndView();
		if(ControllerUtil.mobileOrNot(request)){
			mv.setViewName("www/syrc-mobile/login");
		}
		else{
			mv.setViewName("www/syrc/nologin");
		} 
		mv.addObject("url", url);
		return mv;
	}

	@RequestMapping("/logOut")
	public String logOut(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if (session.getAttribute("tbZhInfo_loginSession") != null)
			session.removeAttribute("tbZhInfo_loginSession");
		if (session.getAttribute("QypztSession") != null)
			session.removeAttribute("QypztSession");
		if(session.getAttribute("mobile") != null){
			session.removeAttribute("mobile");
		}
		return "redirect:/" + sitePrefix + "/" + 4;
	}
	/**
	 * 去新用户注册
	 * 
	 * @return
	 */
	@RequestMapping("/gotoxyhzc")
	public ModelAndView gotoxyhzc(HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("www/syrc-mobile/jlzx-xyhzc");
		return mv;
	}
}
