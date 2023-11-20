package com.sooka.module.web.cms;

import com.github.pagehelper.PageInfo;
import com.sooka.common.upload.UploadComponent;
import com.sooka.common.upload.bean.UploadBean;
import com.sooka.common.utils.ControllerUtil;
import com.sooka.common.utils.DataDicUtil;
import com.sooka.common.utils.DateUtil;
import com.sooka.common.utils.ExcelUtil;
import com.sooka.module.web.cms.service.*;
import com.sooka.module.web.system.service.GwInfoService;
import com.sooka.module.web.system.vo.gjss;
import com.sooka.module.web.system.vo.tb_zh_info;
import com.sooka.module.web.system.vo.tb_zwscjl;
import com.sooka.mybatis.model.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping("/cms/resume")
public class ResumeController {

    @Autowired
    private JlbanbenService jlbanbenService;
    
    @Autowired
    private JlinfoService jlinfoService;
	@Autowired
	private ZwtdService zwtdService;
	@Autowired
	private GwInfoService gwInfoService;
    @Autowired
    private JlqzyxService  qzyxService;
    
    @Autowired
    private JlxnryService  xnryService;
    
    @Autowired
    private JlxnzwService  xnzwService;
    
    @Autowired
    private JlgzjyService  gzjyService;
    
    @Autowired
    private JljyjlService  jyjlService;
    
    @Autowired
    private UploadComponent uploadComponent;
    
    @Autowired
    private JllljlService  jllljlService;
    
    @Autowired
    private ZwtdService  zwtdjlService;
    
    @Autowired
    private ZwscjlService zwscjlService;
    
    @Autowired
	private TbZwscjlService zwscService;

	@Autowired
	private EightCategoryZxbmService eightCategoryZxbmService;
    
    /* 列表 */
    @RequestMapping("/findUserResume")
    public ModelAndView findUserResume(
            @RequestParam(value = "pageCurrent",defaultValue = "1",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue ="5",required = false) Integer pageSize, Jlbanben jlbanben,HttpServletRequest request){
    	ModelAndView view=null;
    	if(ControllerUtil.mobileOrNot(request)){
    		view=new ModelAndView("www/syrc-mobile/jlzx");
    		pageSize=100000000;
    	}else{
    		view=new ModelAndView("www/syrc/jlzx");
    	}
    	try{
    		HttpSession session = request.getSession();
    		tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
    		if(user!=null){
    			Jlinfo info = jlinfoService.findByUserCodeId(user.getZh_id());
    			if(info!=null){
    				jlbanben.setJianliId(info.getJianliId());
    				 	PageInfo<Jlbanben> p = jlbanbenService.page(pageNumber,pageSize,jlbanben);
    			        List<Jlbanben> list;
    			        if(p!=null){
    			            list = p.getList();
    			            view.addObject("model",list);
    			            view.addObject("total",p.getTotal());//总数
    			            view.addObject("pagesize",p.getPageSize());//页数
    			            view.addObject("pagenum",p.getPageNum());
    			            view.addObject("flag","jlzx");
    			        }else {
    			            list = new ArrayList<Jlbanben>();
    			            view.addObject("model",list);
    			            view.addObject("total",1);//总数
    			            view.addObject("pagesize",1);//页数
    			            view.addObject("pagenum",1);
							view.addObject("flag","jlzx");
    			        }
        	        view.addObject("jlbanben", jlbanben);
    			}
				Jlinfo jlinfo = jlinfoService.findByUserCodeId(user.getZh_id());
				view.addObject("jlinfo", jlinfo);
    			
    		}
    		
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return view;
    }
    /* 列表 */
    @RequestMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "pageNumber",defaultValue = "1",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue ="50",required = false) Integer pageSize, Jlbanben jlbanben){
    	ModelAndView view=null;
    	try{
    		view = new ModelAndView("cms/lyfk_list");
	        view.addObject("model",jlbanbenService.page(pageNumber,pageSize,jlbanben));
	        view.addObject("jlbanben", jlbanben);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return view;
    }

    /*修改 */
    @RequestMapping("/input")
    public String input(@RequestParam(value = "id",required = false) String id, Model model,Jlbanben jlbanben){
        if(id!=null){
        	model.addAttribute("jlbanben", jlbanbenService.findById(id));
        }
        return "www/syrc/jlbanben";
    }
    /*修改 */
    @RequestMapping("/detail")
    public String detail(@RequestParam(value = "id",required = true) String id, Model model,Jlbanben jlbanben){
        if(id!=null){
        	model.addAttribute("jlbanben", jlbanbenService.findById(id));
        }
        return "cms/jlbanben_detail";
    }

    /* 更新 */
    @RequestMapping("/update")
    @ResponseBody
    public String update(Jlbanben jlbanben,HttpServletRequest request){
    	
    	if(jlbanben.getJlBbId()!=null) {
            return jlbanbenService.update(jlbanben);
        }
        return jlbanbenService.save(jlbanben);
    }


    /*删除 */
    @RequestMapping("/delete")
    @ResponseBody
    public Map<String, Object> delete(@RequestParam(value = "id",required = false) String[] jlbanbenId,HttpServletRequest request){
    	Map<String, Object> map = new HashMap<String, Object>();
    	try{
    		
    		/*HttpSession session = request.getSession();
    		tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
    		if(user!=null){
    			jlinfoService.deleteByUserCodeId(user.getZh_id());
    		}*/
    		qzyxService.deleteJlqzyxByJlBanbenId(jlbanbenId[0]);  
    		xnryService.deleteXnryByJibanbenId(jlbanbenId[0]); 
    		xnzwService.deleteXnzwByJibanbenId(jlbanbenId[0]); 
    		gzjyService.deleteGzjyByJibanbenId(jlbanbenId[0]);  
    		jyjlService.deleteJyjlByJibanbenId(jlbanbenId[0]); 
    		jllljlService.deleteJllljlByJibanbenId(jlbanbenId[0]);
    		zwtdjlService.deleteZwtdjlByJibanbenId(jlbanbenId[0]);
    		jlbanbenService.delete(jlbanbenId); 
    		map.put("message", "删除成功！");
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
    	return map;
    }
    /* 列表 */
    @RequestMapping("/goCreateResume")
    public ModelAndView goCreateResume(HttpServletRequest request){
    	ModelAndView view=null;
    	if(ControllerUtil.mobileOrNot(request)){
    		view=new ModelAndView("www/syrc-mobile/jlzx-cjjl");
    	}else{
    		view=new ModelAndView("www/syrc/jlzx-cjjl");
    	}
    	try{
    		HttpSession session = request.getSession();
    		tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
    		if(user!=null){
    			Jlinfo info = jlinfoService.findByUserCodeId(user.getZh_id());
    			
    			if(info!=null){
    				Calendar c = Calendar.getInstance();
    				int year = c.get(Calendar.YEAR);
    				if(StringUtils.isNotBlank(info.getJianliKsgz())){
    					info.setJianliKsgz(year-Integer.parseInt(info.getJianliKsgz().substring(0, 4))+"年工作经验");
    				}
    				if(StringUtils.isNotBlank(info.getJianliCsrq())){
    					info.setJianliCsrq(year-Integer.parseInt(info.getJianliCsrq().substring(0, 4))+"岁("+DateUtil.formatHzDate(info.getJianliCsrq())+")");
    				}
    				view.addObject("jlinfo",info);
    			}
    			
    		}
    		view.addObject("qzyx",null);  
    		view.addObject("xnryList",null); 
    		view.addObject("xnzwList",null); 
    		view.addObject("gzjyList",null);  
    		view.addObject("jyjlList",null);
    		view.addObject("jlbb",null); 
    		request.getSession().removeAttribute("jlbbIdSession");
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return view;
    }

	/* 列表 */
    @RequestMapping("/goEditResume")
    public ModelAndView goEditResume(@RequestParam(value = "id",required = false) String jlbanbenId,HttpServletRequest request){
    	ModelAndView view=null;
    	if(ControllerUtil.mobileOrNot(request)){
    		view=new ModelAndView("www/syrc-mobile/jlzx-cjjl");
    	}else{
    		view=new ModelAndView("www/syrc/jlzx-cjjl");
    	}
    	try{
    		HttpSession session = request.getSession();
    		tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
    		if(user!=null){
    			Jlinfo info = jlinfoService.findByUserCodeId(user.getZh_id());
    			if(info!=null){
    				Calendar c = Calendar.getInstance();
    				int year = c.get(Calendar.YEAR);
    				if(info.getJianliKsgz()!=null){
    					info.setJianliKsgz(year-Integer.parseInt(info.getJianliKsgz().substring(0, 4))+"年工作经验");
    				}
    				if(info.getJianliCsrq()!=null){
    					info.setJianliCsrq(year-Integer.parseInt(info.getJianliCsrq().substring(0, 4))+"岁("+DateUtil.formatHzDate(info.getJianliCsrq())+")");
    				}
    				
        	        view.addObject("jlinfo",info);
    			}
    			
    		}
    		Jlqzyx qzyx=qzyxService.findJlqzyxByJlBanbenId(jlbanbenId);
    		List<Jlxnry> xnryList=xnryService.findXnryByJibanbenId(jlbanbenId);
    		List<Jlxnzw> xnzwList=xnzwService.findXnzwByJibanbenId(jlbanbenId);
    		List<Jlgzjy> gzjyList=gzjyService.findGzjyByJibanbenId(jlbanbenId);
    		List<Jljyjl> jyjlList=jyjlService.findJyjlByJibanbenId(jlbanbenId);
    		for(Jlgzjy gzjy:gzjyList){
    			gzjy.setGzjyXsrs(DataDicUtil.getGsgm(gzjy.getGzjyGsgm()));
    			gzjy.setGzjyLzyy(DataDicUtil.getGsxz(gzjy.getGzjyGsxz()));
    			gzjy.setGzjyHbdx(DataDicUtil.getGzxz(gzjy.getGzjyGzlx()));
    			gzjy.setGzjyKssj(DateUtil.formatHzDate(gzjy.getGzjyKssj()));
				if(gzjy.getGzjyJssj().equals("至今")){
					gzjy.setGzjyJssj(gzjy.getGzjyJssj());
				}else{
					gzjy.setGzjyJssj(DateUtil.formatHzDate(gzjy.getGzjyJssj()));
				}

    		}
    		for(Jljyjl jyjl:jyjlList){
    			jyjl.setJyjlZyms(DataDicUtil.getXl(jyjl.getJyjlXl()));
    			jyjl.setJyjlKssj(DateUtil.formatHzDate(jyjl.getJyjlKssj()));
    			jyjl.setJyjlJssj(DateUtil.formatHzDate(jyjl.getJyjlJssj()));
    		}
    		for(Jlxnry xnry:xnryList){
    			xnry.setXnrySj(DateUtil.formatHzDate(xnry.getXnrySj()));
    		}
    		for(Jlxnzw xnzw:xnzwList){
    			xnzw.setXnzwKssj(DateUtil.formatHzDate(xnzw.getXnzwKssj()));
    			xnzw.setXnzwJssj(DateUtil.formatHzDate(xnzw.getXnzwJssj()));
    		}
    		Jlbanben jlbb=jlbanbenService.findById(jlbanbenId);
    		view.addObject("qzyx",qzyx);  
    		view.addObject("xnryList",xnryList); 
    		view.addObject("xnzwList",xnzwList); 
    		view.addObject("gzjyList",gzjyList);  
    		view.addObject("jyjlList",jyjlList); 
    		view.addObject("jlbb",jlbb); 
    		if(jlbanbenId!=null&&!"".equals(jlbanbenId)){
    			request.getSession().setAttribute("jlbbIdSession", jlbb.getJlBbId());
    		}else{
    			request.getSession().removeAttribute("jlbbIdSession");
    		}
    		}catch(Exception e){
	    	e.printStackTrace();
	    }
        return view;
    }


	/* 点击姓名预览简历并往浏览记录里插入数据 */
	@RequestMapping("/previewResume")
	public ModelAndView previewResume(@RequestParam(value = "id",required = false) String jlbanbenId,@RequestParam(value = "zh_id",required = false) String zh_id,
									  @RequestParam(value = "qy_id",required = false) String qy_id,
									  HttpServletRequest request){
		
		ModelAndView view=null;
		if(ControllerUtil.mobileOrNot(request)){
    		view=new ModelAndView("www/syrc-mobile/jlzx-yljl");
    	}else{
    		view=new ModelAndView("www/syrc/jlzx-yljl");
    	}
		tb_zh_info user = new tb_zh_info();
		Jllljl jllljl = new Jllljl();
		HttpSession session = request.getSession();
		try{
			if(zh_id == null) {
				user = (tb_zh_info) session.getAttribute("tbZhInfo_loginSession");
			}else {
				user.setZh_id(zh_id);
				if(StringUtils.isNotBlank(qy_id)){
					jllljl.setQyId(qy_id);
				}
				jllljl.setJlBbId(jlbanbenId);
				jllljl.setJlllSj(DateUtil.formatDate(new Date()));
				jllljlService.save(jllljl);
			}
			if(user!=null) {
				Jlinfo info = jlinfoService.findByUserCodeId(user.getZh_id());
				if (info != null) {
					Calendar c = Calendar.getInstance();
					int year = c.get(Calendar.YEAR);
					if (info.getJianliCsrq() != null) {
						if(ControllerUtil.mobileOrNot(request)){
							view.addObject("csrq", DateUtil.formatHzDate(info.getJianliCsrq()));
							info.setJianliCsrq(year - Integer.parseInt(info.getJianliCsrq().substring(0, 4)) + "岁");
						}else{
				    		info.setJianliCsrq(year - Integer.parseInt(info.getJianliCsrq().substring(0, 4)) + "岁(" + DateUtil.formatHzDate(info.getJianliCsrq()) + ")");
				    	}
						
					}
					if (info.getJianliKsgz() != null) {
						info.setJianliKsgz(year - Integer.parseInt(info.getJianliKsgz().substring(0, 4)) + "年工作经验");
					}
					if(info.getJianliSj()!=null&&!"".equals(info.getJianliSj())){
						info.setJianliSj(info.getJianliSj().substring(0, 3)+"****"+info.getJianliSj().substring(7));
					}
					view.addObject("jlinfo", info);
				}
			}
			if(StringUtils.isNotBlank(jlbanbenId)) {
				Jlqzyx qzyx = qzyxService.findJlqzyxByJlBanbenId(jlbanbenId);
				List<Jlgzjy> gzjyList = gzjyService.findGzjyByJibanbenId(jlbanbenId);
				List<Jljyjl> jyjlList = jyjlService.findJyjlByJibanbenId(jlbanbenId);
				List<Jlxnry> xnryList = xnryService.findXnryByJibanbenId(jlbanbenId);
				List<Jlxnzw> xnzwList = xnzwService.findXnzwByJibanbenId(jlbanbenId);

				for (Jlgzjy gzjy : gzjyList) {
					gzjy.setGzjyXsrs(DataDicUtil.getGsgm(gzjy.getGzjyGsgm()));
					gzjy.setGzjyLzyy(DataDicUtil.getGsxz(gzjy.getGzjyGsxz()));
					gzjy.setGzjyHbdx(DataDicUtil.getGzxz(gzjy.getGzjyGzlx()));
					gzjy.setGzjyKssj(DateUtil.formatHzDate(gzjy.getGzjyKssj()));
					gzjy.setGzjyJssj(DateUtil.formatHzDate(gzjy.getGzjyJssj()));
				}
				for (Jljyjl jyjl : jyjlList) {
					if (ControllerUtil.mobileOrNot(request)) {
						jyjl.setXl(DataDicUtil.getXl(jyjl.getJyjlXl()));
					} else {
						jyjl.setJyjlZyms(DataDicUtil.getXl(jyjl.getJyjlXl()));
					}

					jyjl.setJyjlKssj(DateUtil.formatHzDate(jyjl.getJyjlKssj()));
					jyjl.setJyjlJssj(DateUtil.formatHzDate(jyjl.getJyjlJssj()));
				}
				for (Jlxnry xnry : xnryList) {
					xnry.setXnrySj(DateUtil.formatHzDate(xnry.getXnrySj()));
				}
				for (Jlxnzw xnzw : xnzwList) {
					xnzw.setXnzwKssj(DateUtil.formatHzDate(xnzw.getXnzwKssj()));
					xnzw.setXnzwJssj(DateUtil.formatHzDate(xnzw.getXnzwJssj()));
				}
				Jlbanben jlbb = jlbanbenService.findById(jlbanbenId);
				view.addObject("qzyx", qzyx);
				view.addObject("xnryList", xnryList);
				view.addObject("xnzwList", xnzwList);
				if (gzjyList.size() > 0) {
					view.addObject("gzjyList", gzjyList.get(0));
				} else {
					view.addObject("gzjyList", gzjyList);
				}
				view.addObject("gzjyLists", gzjyList);
				if (jyjlList.size() > 0) {
					view.addObject("jyjlList", jyjlList.get(0));
				} else {
					view.addObject("jyjlList", jyjlList);
				}
				view.addObject("jyjlLists", jyjlList);
				view.addObject("jlbb", jlbb);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return view;
	}
	/* 点击姓名预览简历并往浏览记录里插入数据 */
	@RequestMapping("/yljlforqy")
	public ModelAndView yljlforqy(@RequestParam(value = "id",required = false) String jlbanbenId,@RequestParam(value = "zh_id",required = false) String zh_id,
									  @RequestParam(value = "tdjlId",required = false) String tdjlId,
									  HttpServletRequest request){
		ModelAndView view=null;
		if(ControllerUtil.mobileOrNot(request)){
    		view=new ModelAndView("www/syrc-mobile/jlzx-msyq");
    	}else{
    		view=new ModelAndView("www/syrc/jlzx-yljlforqy");
    	}
		tb_zh_info user = new tb_zh_info();
		/*Jllljl jllljl = new Jllljl();*/
		//HttpSession session = request.getSession();
		try{
			/*if(zh_id == null) {
				user = (tb_zh_info) session.getAttribute("tbZhInfo_loginSession");
			}*//*else {
				user.setZh_id(zh_id);
				jllljl.setQyId(qy_id);
				jllljl.setJlBbId(jlbanbenId);
				jllljl.setJlllSj(DateUtil.formatDate(new Date()));
				jllljlService.save(jllljl);
			}*/
				Jlinfo info = jlinfoService.findByUserCodeId(zh_id);
				if (info != null) {
					Calendar c = Calendar.getInstance();
					int year = c.get(Calendar.YEAR);
					if (info.getJianliCsrq() != null) {
						if(ControllerUtil.mobileOrNot(request)){
							view.addObject("csrq", DateUtil.formatHzDate(info.getJianliCsrq()));
							info.setJianliCsrq(year - Integer.parseInt(info.getJianliCsrq().substring(0, 4)) + "岁");
						}else{
				    		info.setJianliCsrq(year - Integer.parseInt(info.getJianliCsrq().substring(0, 4)) + "岁(" + DateUtil.formatHzDate(info.getJianliCsrq()) + ")");
				    	}
						
					}
					if (info.getJianliKsgz() != null) {
						info.setJianliKsgz(year - Integer.parseInt(info.getJianliKsgz().substring(0, 4)) + "年工作经验");
					}
					/*if(info.getJianliSj()!=null&&!"".equals(info.getJianliSj())){
						info.setJianliSj(info.getJianliSj().substring(0, 3)+"****"+info.getJianliSj().substring(7));
					}*/
					view.addObject("jlinfo", info);
				}
			Jlqzyx qzyx=qzyxService.findJlqzyxByJlBanbenId(jlbanbenId);
			List<Jlgzjy> gzjyList=gzjyService.findGzjyByJibanbenId(jlbanbenId);
			List<Jljyjl> jyjlList=jyjlService.findJyjlByJibanbenId(jlbanbenId);
			List<Jlxnry> xnryList=xnryService.findXnryByJibanbenId(jlbanbenId);
			List<Jlxnzw> xnzwList=xnzwService.findXnzwByJibanbenId(jlbanbenId);

			for(Jlgzjy gzjy:gzjyList){
    			gzjy.setGzjyXsrs(DataDicUtil.getGsgm(gzjy.getGzjyGsgm()));
    			gzjy.setGzjyLzyy(DataDicUtil.getGsxz(gzjy.getGzjyGsxz()));
    			gzjy.setGzjyHbdx(DataDicUtil.getGzxz(gzjy.getGzjyGzlx()));
    			gzjy.setGzjyKssj(DateUtil.formatHzDate(gzjy.getGzjyKssj()));
    			gzjy.setGzjyJssj(DateUtil.formatHzDate(gzjy.getGzjyJssj()));
    		}
    		for(Jljyjl jyjl:jyjlList){
    			if(ControllerUtil.mobileOrNot(request)){
    				jyjl.setXl(DataDicUtil.getXl(jyjl.getJyjlXl()));
				}else{
					jyjl.setJyjlZyms(DataDicUtil.getXl(jyjl.getJyjlXl()));
		    	}
    			
    			jyjl.setJyjlKssj(DateUtil.formatHzDate(jyjl.getJyjlKssj()));
    			jyjl.setJyjlJssj(DateUtil.formatHzDate(jyjl.getJyjlJssj()));
    		}
    		for(Jlxnry xnry:xnryList){
    			xnry.setXnrySj(DateUtil.formatHzDate(xnry.getXnrySj()));
    		}
    		for(Jlxnzw xnzw:xnzwList){
    			xnzw.setXnzwKssj(DateUtil.formatHzDate(xnzw.getXnzwKssj()));
    			xnzw.setXnzwJssj(DateUtil.formatHzDate(xnzw.getXnzwJssj()));
    		}
			Jlbanben jlbb=jlbanbenService.findById(jlbanbenId);
			view.addObject("qzyx",qzyx);
			view.addObject("tdjlId",tdjlId);
			view.addObject("xnryList",xnryList);
			view.addObject("xnzwList",xnzwList);
			if(gzjyList.size()>0){
				view.addObject("gzjyList",gzjyList.get(0));
			}else{
				view.addObject("gzjyList",gzjyList);
			}
			view.addObject("gzjyLists",gzjyList);
			if(jyjlList.size()>0){
				view.addObject("jyjlList",jyjlList.get(0));
			}else{
				view.addObject("jyjlList",jyjlList);
			}
			view.addObject("jyjlLists",jyjlList);
			view.addObject("jlbb",jlbb);
		}catch(Exception e){
			e.printStackTrace();
		}
		return view;
	}

	/* 点击姓名预览简历并往浏览记录里插入数据 */
	@RequestMapping("/previewResumeForAdmin")
	public ModelAndView previewResumeForAdmin(@RequestParam(value = "id",required = false) String jlbanbenId,@RequestParam(value = "zh_id",required = false) String zh_id,
									  @RequestParam(value = "qy_id",required = false) String qy_id,
									  HttpServletRequest request){
		ModelAndView view=null;
		if(ControllerUtil.mobileOrNot(request)){
    		view=new ModelAndView("www/syrc-mobile/jlzx-yljlforadmin");
    	}else{
    		view=new ModelAndView("www/syrc/jlzx-yljl");
    	}
		tb_zh_info user = new tb_zh_info();
		//Jllljl jllljl = new Jllljl();
		//HttpSession session = request.getSession();
		try{
			/*if(zh_id == null) {
				user = (tb_zh_info) session.getAttribute("tbZhInfo_loginSession");
			}else {
				user.setZh_id(zh_id);
				jllljl.setQyId(qy_id);
				jllljl.setJlBbId(jlbanbenId);
				jllljl.setJlllSj(DateUtil.formatDate(new Date()));
				jllljlService.save(jllljl);
			}*/
			if(user!=null) {
				Jlinfo info = jlinfoService.findByUserCodeId(zh_id);
				if (info != null) {
					Calendar c = Calendar.getInstance();
					int year = c.get(Calendar.YEAR);
					if (info.getJianliCsrq() != null) {
						if(ControllerUtil.mobileOrNot(request)){
							view.addObject("csrq", DateUtil.formatHzDate(info.getJianliCsrq()));
							info.setJianliCsrq(year - Integer.parseInt(info.getJianliCsrq().substring(0, 4)) + "岁");
						}else{
				    		info.setJianliCsrq(year - Integer.parseInt(info.getJianliCsrq().substring(0, 4)) + "岁(" + DateUtil.formatHzDate(info.getJianliCsrq()) + ")");
				    	}
						
					}
					if (info.getJianliKsgz() != null) {
						info.setJianliKsgz(year - Integer.parseInt(info.getJianliKsgz().substring(0, 4)) + "年工作经验");
					}
					if(info.getJianliSj()!=null&&!"".equals(info.getJianliSj())){
						info.setJianliSj(info.getJianliSj().substring(0, 3)+"****"+info.getJianliSj().substring(7));
					}
					view.addObject("jlinfo", info);
				}
			}
			Jlqzyx qzyx=qzyxService.findJlqzyxByJlBanbenId(jlbanbenId);
			List<Jlgzjy> gzjyList=gzjyService.findGzjyByJibanbenId(jlbanbenId);
			List<Jljyjl> jyjlList=jyjlService.findJyjlByJibanbenId(jlbanbenId);
			List<Jlxnry> xnryList=xnryService.findXnryByJibanbenId(jlbanbenId);
			List<Jlxnzw> xnzwList=xnzwService.findXnzwByJibanbenId(jlbanbenId);

			for(Jlgzjy gzjy:gzjyList){
    			gzjy.setGzjyXsrs(DataDicUtil.getGsgm(gzjy.getGzjyGsgm()));
    			gzjy.setGzjyLzyy(DataDicUtil.getGsxz(gzjy.getGzjyGsxz()));
    			gzjy.setGzjyHbdx(DataDicUtil.getGzxz(gzjy.getGzjyGzlx()));
    			gzjy.setGzjyKssj(DateUtil.formatHzDate(gzjy.getGzjyKssj()));
    			gzjy.setGzjyJssj(DateUtil.formatHzDate(gzjy.getGzjyJssj()));
    		}
    		for(Jljyjl jyjl:jyjlList){
    			if(ControllerUtil.mobileOrNot(request)){
    				jyjl.setXl(DataDicUtil.getXl(jyjl.getJyjlXl()));
				}else{
					jyjl.setJyjlZyms(DataDicUtil.getXl(jyjl.getJyjlXl()));
		    	}
    			
    			jyjl.setJyjlKssj(DateUtil.formatHzDate(jyjl.getJyjlKssj()));
    			jyjl.setJyjlJssj(DateUtil.formatHzDate(jyjl.getJyjlJssj()));
    		}
    		for(Jlxnry xnry:xnryList){
    			xnry.setXnrySj(DateUtil.formatHzDate(xnry.getXnrySj()));
    		}
    		for(Jlxnzw xnzw:xnzwList){
    			xnzw.setXnzwKssj(DateUtil.formatHzDate(xnzw.getXnzwKssj()));
    			xnzw.setXnzwJssj(DateUtil.formatHzDate(xnzw.getXnzwJssj()));
    		}
			Jlbanben jlbb=jlbanbenService.findById(jlbanbenId);
			view.addObject("qzyx",qzyx);
			view.addObject("xnryList",xnryList);
			view.addObject("xnzwList",xnzwList);
			if(gzjyList.size()>0){
				view.addObject("gzjyList",gzjyList.get(0));
			}else{
				view.addObject("gzjyList",gzjyList);
			}
			view.addObject("gzjyLists",gzjyList);
			if(jyjlList.size()>0){
				view.addObject("jyjlList",jyjlList.get(0));
			}else{
				view.addObject("jyjlList",jyjlList);
			}
			view.addObject("jyjlLists",jyjlList);
			view.addObject("jlbb",jlbb);
		}catch(Exception e){
			e.printStackTrace();
		}
		return view;
	}
    /* 列表 */
    @RequestMapping("/getUserConsumeInfo")
    @ResponseBody
    public Jlinfo getUserConsumeInfo(@RequestParam(value = "jianliId",required = true) String jianliId,HttpServletRequest request){
    	Jlinfo jlinfo = null;
    	try{
    		jlinfo=jlinfoService.findById(jianliId);
    		//jlinfo.setJianliCsrq(DateUtil.formatHzDate(jlinfo.getJianliCsrq()));
    		jlinfo.setJianliXm(jlinfo.getJianliXm()==null?"":jlinfo.getJianliXm());
    		jlinfo.setJianliKsgz(jlinfo.getJianliKsgz()==null?"":jlinfo.getJianliKsgz());
    		jlinfo.setJianliSj(jlinfo.getJianliSj()==null?"":jlinfo.getJianliSj());
    		jlinfo.setJianliJzd(jlinfo.getJianliJzd()==null?"":jlinfo.getJianliJzd());
    		jlinfo.setJianliHk(jlinfo.getJianliHk()==null?"":jlinfo.getJianliHk());
    		jlinfo.setJianliQtfshm(jlinfo.getJianliQtfshm()==null?"":jlinfo.getJianliQtfshm());
    		jlinfo.setJianliZjhm(jlinfo.getJianliZjhm()==null?"":jlinfo.getJianliZjhm());
    		jlinfo.setJianliSg(jlinfo.getJianliSg()==null?"":jlinfo.getJianliSg());
    		jlinfo.setJianliJtzz(jlinfo.getJianliJtzz()==null?"":jlinfo.getJianliJtzz());
    		jlinfo.setJianliYb(jlinfo.getJianliYb()==null?"":jlinfo.getJianliYb());
    		jlinfo.setJianliTx(jlinfo.getJianliTx()==null?"../../../static/www/syrc/images/man.png":jlinfo.getJianliTx());
    		
    		//NullConverseEmptyStr.nullConverNullString(jlinfo);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return jlinfo;
    }
    /* 列表 */
    @RequestMapping("/deleteTx")
    @ResponseBody
    public Jlinfo deleteTx(@RequestParam(value = "jianliId",required = true) String jianliId,HttpServletRequest request){
    	Jlinfo jlinfo = null;
    	try{
    		jlinfo=jlinfoService.findById(jianliId);
    		jlinfo.setJianliTx("../../../static/www/syrc/images/man.png");
    		jlinfoService.update(jlinfo);
    		jlinfo.setJianliXm(jlinfo.getJianliXm()==null?"":jlinfo.getJianliXm());
    		jlinfo.setJianliKsgz(jlinfo.getJianliKsgz()==null?"":jlinfo.getJianliKsgz());
    		jlinfo.setJianliSj(jlinfo.getJianliSj()==null?"":jlinfo.getJianliSj());
    		jlinfo.setJianliJzd(jlinfo.getJianliJzd()==null?"":jlinfo.getJianliJzd());
    		jlinfo.setJianliHk(jlinfo.getJianliHk()==null?"":jlinfo.getJianliHk());
    		jlinfo.setJianliQtfshm(jlinfo.getJianliQtfshm()==null?"":jlinfo.getJianliQtfshm());
    		jlinfo.setJianliZjhm(jlinfo.getJianliZjhm()==null?"":jlinfo.getJianliZjhm());
    		jlinfo.setJianliSg(jlinfo.getJianliSg()==null?"":jlinfo.getJianliSg());
    		jlinfo.setJianliJtzz(jlinfo.getJianliJtzz()==null?"":jlinfo.getJianliJtzz());
    		jlinfo.setJianliYb(jlinfo.getJianliYb()==null?"":jlinfo.getJianliYb());
    		jlinfo.setJianliTx(jlinfo.getJianliTx()==null?"../../../static/www/syrc/images/man.png":jlinfo.getJianliTx());
    		
    		//NullConverseEmptyStr.nullConverNullString(jlinfo);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return jlinfo;
    }
    /* 列表 */
    @RequestMapping("/saveUserConsumeInfo")
    @ResponseBody
    public String saveUserConsumeInfo(Jlinfo jlinfo,HttpServletRequest request){
    	String result="";
    	try{
    		result=jlinfoService.update(jlinfo);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return result;
    }
    /* 列表 */
    @RequestMapping("/findUserQzyx")
    @ResponseBody
    public Jlqzyx findUserQzyx(@RequestParam(value = "qzyxId",required = true) String qzyxId,HttpServletRequest request){
    	Jlqzyx qzyx=null;
    	try{
    		if(qzyxId!=null){
    			qzyx=qzyxService.findById(qzyxId);
    		}
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return qzyx;
    }
    /* 列表 */
    @RequestMapping("/saveUserQzyx")
    @ResponseBody
    public Map<String,Object> saveUserQzyx(Jlqzyx qzyx,HttpServletRequest request){
    	Map<String,Object> map=new HashMap<String, Object>();
    	Jlbanben jl = null;
    	try{
    		if(qzyx.getQzyxId()==null||"".equals(qzyx.getQzyxId())){
    			//qzyx.setQzyxId(UuidUtil.get32UUID());
    			qzyxService.save(qzyx);
    		}else{
    			
    			qzyxService.update(qzyx);
    		}
    		if(qzyx.getJlBbId()==null||"".equals(qzyx.getJlBbId())){
    			jl=generateConsumeName(request);
    			qzyx.setJlBbId(jl.getJlBbId());
    			qzyxService.update(qzyx);
    		}
    		map.put("qzyx",qzyx);
    		map.put("message", "保存成功！");
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return map;
    }
    /*修改 */
    @RequestMapping("/findXnryById")
    @ResponseBody
    public Jlxnry findXnryById(@RequestParam(value = "id",required = true) String id, Model model){
    	Jlxnry jlxnry=null;
    	if(id!=null){
    		jlxnry=xnryService.findById(id);
        }
        return jlxnry;
    }
    /*修改 */
    @RequestMapping("/findXnzwById")
    @ResponseBody
    public Jlxnzw findXnzwById(@RequestParam(value = "id",required = true) String id, Model model){
    	Jlxnzw jlxnzw=null;
    	if(id!=null){
    		jlxnzw=xnzwService.findById(id);
        }
        return jlxnzw;
    }
    /*修改 */
    @RequestMapping("/findGzjyById")
    @ResponseBody
    public Jlgzjy findGzjyById(@RequestParam(value = "id",required = true) String id, Model model){
    	Jlgzjy jlgzjy=null;
    	if(id!=null){
    		jlgzjy=gzjyService.findById(id);
        }
        return jlgzjy;
    }
    /*修改 */
    @RequestMapping("/findJyjlById")
    @ResponseBody
    public Jljyjl findJyjlById(@RequestParam(value = "id",required = true) String id, Model model){
    	Jljyjl jljyjl=null;
    	if(id!=null){
    		jljyjl=jyjlService.findById(id);
        }
        return jljyjl;
    }
    /*修改 */
    @RequestMapping("/findXnrylistByJlbbId")
    @ResponseBody
    public List<Jlxnry> findXnrylistByJlbbId(@RequestParam(value = "jlbbId",required = true) String id, Model model){
    	List<Jlxnry> jlxnry=null;
    	if(id!=null){
    		jlxnry=xnryService.findXnryByJibanbenId(id);
        }
		for(Jlxnry xnry:jlxnry){
			xnry.setXnrySj(DateUtil.formatHzDate(xnry.getXnrySj()));
		}
        return jlxnry;
    }
    /*修改 */
    @RequestMapping("/findXnzwlistByJlbbId")
    @ResponseBody
    public List<Jlxnzw> findXnzwlistByJlbbId(@RequestParam(value = "jlbbId",required = true) String id, Model model){
    	List<Jlxnzw> jlxnzw=null;
    	if(id!=null){
    		jlxnzw=xnzwService.findXnzwByJibanbenId(id);
        }
		for(Jlxnzw xnzw:jlxnzw){
			xnzw.setXnzwKssj(DateUtil.formatHzDate(xnzw.getXnzwKssj()));
			xnzw.setXnzwJssj(DateUtil.formatHzDate(xnzw.getXnzwJssj()));
		}
        return jlxnzw;
    }
    /*修改 */
    @RequestMapping("/findGzjylistByJlbbId")
    @ResponseBody
    public List<Jlgzjy> findGzjylistByJlbbId(@RequestParam(value = "jlbbId",required = true) String id, Model model){
    	List<Jlgzjy> jlgzjy=null;
    	if(id!=null){
    		jlgzjy=gzjyService.findGzjyByJibanbenId(id);
        }
    	for(Jlgzjy gzjy:jlgzjy){
			gzjy.setGzjyKssj(DateUtil.formatHzDate(gzjy.getGzjyKssj()));
			gzjy.setGzjyJssj(DateUtil.formatHzDate(gzjy.getGzjyJssj()));
		}
        return jlgzjy;
    }
    /*修改 */
    @RequestMapping("/findJyjllistByJlbbId")
    @ResponseBody
    public List<Jljyjl> findJyjllistByJlbbId(@RequestParam(value = "jlbbId",required = true) String id, Model model){
    	List<Jljyjl> jljyjl=null;
    	if(id!=null){
    		jljyjl=jyjlService.findJyjlByJibanbenId(id);
        }
    	for(Jljyjl jyjl:jljyjl){
			jyjl.setJyjlZyms(DataDicUtil.getXl(jyjl.getJyjlXl()));
			jyjl.setJyjlKssj(DateUtil.formatHzDate(jyjl.getJyjlKssj()));
			jyjl.setJyjlJssj(DateUtil.formatHzDate(jyjl.getJyjlJssj()));
		}
        return jljyjl;
    }
    /*删除 */
    @RequestMapping("/deleteXnryById")
    @ResponseBody
    public String deleteXnryById(@RequestParam("ids") String[] id){
        return xnryService.delete(id);
    }
    /*删除 */
    @RequestMapping("/deleteXnzwById")
    @ResponseBody
    public String deleteXnzwById(@RequestParam("ids") String[] id){
        return xnzwService.delete(id);
    }
    /*删除 */
    @RequestMapping("/deleteJyjlById")
    @ResponseBody
    public String deleteJyjlById(@RequestParam("ids") String[] id){
        return jyjlService.delete(id);
    }
    /*删除 */
    @RequestMapping("/deleteGzjyById")
    @ResponseBody
    public String deleteGzjyById(@RequestParam("ids") String[] id){
        return gzjyService.delete(id);
    }
    /* 更新 */
    @RequestMapping("/saveXnzw")
    @ResponseBody
    public Map<String,Object> saveXnzw(Jlxnzw xnzw,HttpServletRequest request){
    	Map<String,Object> map=new HashMap<String, Object>();
    	Jlbanben jl = null;
    	if(xnzw.getXnzwId()!=null) {
            xnzwService.update(xnzw);
        }else{
        	xnzwService.save(xnzw);
        }
    	if(xnzw.getJlBbId()==null||"".equals(xnzw.getJlBbId())){
			jl=generateConsumeName(request);
			xnzw.setJlBbId(jl.getJlBbId());
			xnzwService.update(xnzw);
		}
    	map.put("xnzw",xnzw);
		map.put("message", "保存成功！");
        return map;
    }
    /* 更新 */
    @RequestMapping("/saveXnry")
    @ResponseBody
    public Map<String,Object> saveXnry(Jlxnry xnry,HttpServletRequest request){
    	Map<String,Object> map=new HashMap<String, Object>();
    	Jlbanben jl = null;
    	if(xnry.getXnryId()!=null) {
            xnryService.update(xnry);
        }else{
        	xnryService.save(xnry);
        }
    	if(xnry.getJlBbId()==null||"".equals(xnry.getJlBbId())){
			jl=generateConsumeName(request);
			xnry.setJlBbId(jl.getJlBbId());
			xnryService.update(xnry);
		}
    	map.put("xnry",xnry);
		map.put("message", "保存成功！");
        return map;
    }
    /* 更新 */
    @RequestMapping("/saveJyjl")
    @ResponseBody
    public Map<String,Object> saveJyjl(Jljyjl jyjl,HttpServletRequest request){
    	Map<String,Object> map=new HashMap<String, Object>();
    	Jlbanben jl = null;
    	if(jyjl.getJyjlId()!=null) {
            jyjlService.update(jyjl);
        }else{
        	jyjlService.save(jyjl);
        }
    	if(jyjl.getJlBbId()==null||"".equals(jyjl.getJlBbId())){
			jl=generateConsumeName(request);
			jyjl.setJlBbId(jl.getJlBbId());
			jyjlService.update(jyjl);
		}
    	map.put("jyjl",jyjl);
		map.put("message", "保存成功！");
        return map;
    }
    /* 更新 */
    @RequestMapping("/saveGzjy")
    @ResponseBody
    public Map<String,Object> saveGzjy(Jlgzjy gzjy,HttpServletRequest request){
    	Map<String,Object> map=new HashMap<String, Object>();
    	Jlbanben jl = null;
    	if(gzjy.getGzjyId()!=null) {
            gzjyService.update(gzjy);
        }else{
        	gzjyService.save(gzjy);
        }
    	if(gzjy.getJlBbId()==null||"".equals(gzjy.getJlBbId())){
    		jl=generateConsumeName(request);
			gzjy.setJlBbId(jl.getJlBbId());
			gzjyService.update(gzjy);
		}
    	map.put("gzjy",gzjy);
		map.put("message", "保存成功！");
        return map;
    }
    /* 更新 */
    @RequestMapping("/updateJianliTx")
    @ResponseBody
    public Map<String,Object> updateJianliTx(@RequestParam("myfile") MultipartFile multipartFile,HttpServletRequest request){
    	Map<String,Object> map=new HashMap<String, Object>();
    	UploadBean result = uploadComponent.uploadFile(multipartFile,request);
    	map.put("fileUrl",result.getFileUrl());
    	return map;
    }
	public Jlbanben generateConsumeName(HttpServletRequest request){
    	Jlbanben bb=new Jlbanben();
    	try{
    		HttpSession session = request.getSession();
    		tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
    		if(user!=null){
    			Jlinfo info = jlinfoService.findByUserCodeId(user.getZh_id());
    			bb.setJianliId(info.getJianliId());
    			//bb.setJlBbId(UuidUtil.get32UUID());
    			bb.setJlBbTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        	    bb.setJlBbName("我的简历"+new Date().getTime());
        	    bb.setJlBbMrtdzt(0);//非默认简历
        	    jlbanbenService.save(bb);
        	    request.getSession().setAttribute("jlbbIdSession", bb.getJlBbId());
    			
    		}
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return bb;
    }
	 /* 谁看过我列表 */
    @RequestMapping("/findSkgwList")
    public ModelAndView findSkgwList(
            @RequestParam(value = "pageCurrent",defaultValue = "1",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue ="5",required = false) Integer pageSize, Jllljl jllljl,HttpServletRequest request){
    	ModelAndView view=null;
    	if(ControllerUtil.mobileOrNot(request)){
    		view=new ModelAndView("www/syrc-mobile/jlzx-skgw");
    		pageSize=100000000;
    	}else{
    		view=new ModelAndView("www/syrc/jlzx-skgw");
    	}
    	try{
    		HttpSession session = request.getSession();
    		tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
    		if(user!=null){
    			Map<String, Object> map=new HashMap<String, Object>();
    			map.put("ZH_ID", user.getZh_id());
    			PageInfo<Map<String, Object>> p = jllljlService.page(pageNumber, pageSize, map);
    			List<Map<String, Object>> list;
    			if(p!=null){
    			      list = p.getList();
    			      view.addObject("model",list);
    			      view.addObject("total",p.getTotal());//总数
    			      view.addObject("pagesize",p.getPageSize());//页数
    			      view.addObject("pagenum",p.getPageNum());
    			      view.addObject("flag","skgw");
    			}else {
    			      list = new ArrayList<Map<String, Object>>();
    			      view.addObject("model",list);
    			      view.addObject("total",1);//总数
    			      view.addObject("pagesize",1);//页数
    			      view.addObject("pagenum",1);
					view.addObject("flag","skgw");
    			}
    	}
    		
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return view;
    }
    /* 我的收藏列表 */
    @RequestMapping("/findWdscList")
    public ModelAndView findWdscList(
            @RequestParam(value = "pageCurrent",defaultValue = "1",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue ="5",required = false) Integer pageSize, Zwscjl zwscjl,HttpServletRequest request){
    	ModelAndView view=null;
    	if(ControllerUtil.mobileOrNot(request)){
    		view=new ModelAndView("www/syrc-mobile/jlzx-wdsc");
    		pageSize=100000000;
    	}else{
    		view=new ModelAndView("www/syrc/jlzx-wdsc");
    	}
    	try{
    		HttpSession session = request.getSession();
    		tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
    		if(user!=null){
    			Map<String, Object> map=new HashMap<String, Object>();
    			map.put("ZH_ID", user.getZh_id());
    			PageInfo<Map<String, Object>> p = zwscjlService.page(pageNumber, pageSize, map);
    			List<Map<String, Object>> list;
    			if(p!=null){
    			      list = p.getList();
    			      view.addObject("model",list);
    			      view.addObject("total",p.getTotal());//总数
    			      view.addObject("pagesize",p.getPageSize());//页数
    			      view.addObject("pagenum",p.getPageNum());
    			      view.addObject("flag","wdsc");
    			}else {
    			      list = new ArrayList<Map<String, Object>>();
    			      view.addObject("model",list);
    			      view.addObject("total",1);//总数
    			      view.addObject("pagesize",1);//页数
    			      view.addObject("pagenum",1);
    			      view.addObject("flag","wdsc");
    			}
				Jlinfo jlinfo = jlinfoService.findByUserCodeId(user.getZh_id());
				view.addObject("jlinfo", jlinfo);
    	}
    		
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return view;
    }
    /* 我的申请列表 */
    @RequestMapping("/findWdsqList")
    public ModelAndView findWdsqList(
            @RequestParam(value = "pageCurrent",defaultValue = "1",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue ="5",required = false) Integer pageSize, Zwtdjl zwtdjl,HttpServletRequest request){
    	ModelAndView view=null;
    	if(ControllerUtil.mobileOrNot(request)){
    		view=new ModelAndView("www/syrc-mobile/jlzx-wdsq");
    		pageSize=100000000;
    	}else{
    		view=new ModelAndView("www/syrc/jlzx-wdsq");
    	}
    	try{
    		HttpSession session = request.getSession();
    		tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
    		if(user!=null){
    			Map<String, Object> map=new HashMap<String, Object>();
    			map.put("ZH_ID", user.getZh_id());
    			PageInfo<Map<String, Object>> p = zwtdjlService.page(pageNumber, pageSize, map);
    			List<Map<String, Object>> list;


    			if(p!=null){
    			      list = p.getList();
    			      view.addObject("model",list);
    			      view.addObject("total",p.getTotal());//总数
    			      view.addObject("pagesize",p.getPageSize());//页数
    			      view.addObject("pagenum",p.getPageNum());
    			      view.addObject("flag","wdsq");
    			}else {
    			      list = new ArrayList<Map<String, Object>>();
    			      view.addObject("model",list);
    			      view.addObject("total",1);//总数
    			      view.addObject("pagesize",1);//页数
    			      view.addObject("pagenum",1);
					view.addObject("flag","wdsq");
    			}
				Jlinfo jlinfo = jlinfoService.findByUserCodeId(user.getZh_id());
				view.addObject("jlinfo", jlinfo);
    	}
    		
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return view;
    }
    /* 谁看过我申请职位*/
    @RequestMapping("/skgwsqzw")
    @ResponseBody
    public Map<String,Object> skgwsqzw(Zwtdjl zwtdjl,HttpServletRequest request){
    	Map<String,Object> map=new HashMap<String, Object>();
    	try{
    		zwtdjl.setTdjlTime(DateUtil.formatDate(new Date()));
    		HttpSession session = request.getSession();
    		tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
    		if(user!=null){
    			zwtdjl.setZhId(user.getZh_id());
    			if(zwtdjlService.findList(zwtdjl).size()>0){
    				map.put("message", "已经申请过该职位！！！");
    				return map;
    			}
    		}
    		//面试邀请状态默认未邀请，邀请时间为空
    		zwtdjl.setTdjlMszt(0);
    		zwtdjl.setTdjlTdzt(0);
    		zwtdjlService.save(zwtdjl);
    		map.put("message", "职位申请成功！");
	    }catch(Exception e){
	    	e.printStackTrace();
	    	map.put("message", e.getMessage());
	    }
        return map;
    }
    /* 我的收藏申请职位*/
    @RequestMapping("/wdscsqzw")
    @ResponseBody
    public Map<String,Object> wdscsqzw(Zwtdjl zwtdjl,HttpServletRequest request){
    	Zwtdjl td=new Zwtdjl();
    	td.setZwId(zwtdjl.getZwId());
    	Map<String,Object> map=new HashMap<String, Object>();
    	Jlbanben jlbanben=new Jlbanben();
    	try{
    		zwtdjl.setTdjlTime(DateUtil.formatDate(new Date()));
    		HttpSession session = request.getSession();
    		tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
    		if(user!=null){
				if(user.getZh_sf()==0){
					zwtdjl.setZhId(user.getZh_id());
					td.setZhId(user.getZh_id());
					if(zwtdjlService.findList(td).size()>0){
						map.put("message", "已经申请过该职位！！！");
						map.put("type", "1");
						return map;
					}
					Jlinfo info = jlinfoService.findByUserCodeId(user.getZh_id());
					if(info!=null){
						jlbanben.setJianliId(info.getJianliId());
						jlbanben.setJlBbMrtdzt(1);
					}
					//面试邀请状态默认未邀请，邀请时间为空
					zwtdjl.setTdjlMszt(0);
					zwtdjl.setTdjlTdzt(0);
					//我的收藏没有简历版本Id，需要查询登录用户默认投递的简历
					List<Jlbanben> list=jlbanbenService.findList(jlbanben);
					//
					if(list.size()==0){
						map.put("message", "未创建简历或未设置默认投递简历！！！");
						map.put("type", "2");
						return map;
					}
					if(list.size()==1){
						zwtdjl.setJlBbId(list.get(0).getJlBbId());
					}
					zwtdjlService.save(zwtdjl);
					map.put("message", "职位申请成功！");
					map.put("type", "3");
				}else{
					map.put("type", "4");
					map.put("message", "企业用户不能申请职位！请换成个人用户登录！");
				}

    		}else{
				map.put("type", "5");
    			map.put("message", "用户未登录！");
    		}
    		
	    }catch(Exception e){
	    	e.printStackTrace();
	    	map.put("message", e.getMessage());
	    }
        return map;
    }
    /*删除谁看过我 */
    @RequestMapping("/deleteSkgw")
    @ResponseBody
    public Map<String,Object>  deleteSkgw(@RequestParam(value = "ids",required = false) String[] skgwId,HttpServletRequest request){
    	Map<String,Object> map=new HashMap<String, Object>();
    	try{
    		jllljlService.delete(skgwId);
    		map.put("message", "删除成功！");
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
    	return map;
    }
    /*删除我的收藏 */
    @RequestMapping("/deleteWdsc")
    @ResponseBody
    public Map<String,Object> deleteWdsc(@RequestParam(value = "ids",required = false) String[] zwscId,HttpServletRequest request){
    	Map<String,Object> map=new HashMap<String, Object>();
    	try{
    		zwscjlService.delete(zwscId);
    		map.put("message", "删除成功！");
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
    	return map;
    }
    /*修改简历默认投递状态 */
    @RequestMapping("/editJlbanbenMRtdzt")
    public String editJlbanbenMRtdzt(@RequestParam(value = "id",required = false) String jlbanbenId,
									 @RequestParam(value = "jianliId",required = false) String jianliId,
									 HttpServletRequest request){
    	try{
    		jlbanbenService.updateJlbanbenMRtdzt(jianliId);
    		Jlbanben jlbanben=jlbanbenService.findById(jlbanbenId);
    		jlbanben.setJlBbMrtdzt(1);
    		jlbanbenService.update(jlbanben);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
    	return "redirect:/cms/resume/findUserResume";
	    
    }
    /* 首页刷新个人右侧小框数据*/
    @RequestMapping("/getGrInfo")
    @ResponseBody
    public Map<String,Object> getGrInfo(Zwtdjl zwtdjl,HttpServletRequest request){
    	Map<String,Object> map=new HashMap<String, Object>();
    	try{
    		HttpSession session = request.getSession();
    		tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
    		if(user!=null){
    			Jlinfo info = jlinfoService.findByUserCodeId(user.getZh_id());
    			if(info.getJianliXm()==null){
    				info.setJianliXm("欢迎登录");
    			}
    			int skgw=jllljlService.findSkgwCountByZhId(user.getZh_id());
    			int wdsq=zwtdjlService.findWdsqCountByZhId(user.getZh_id());
    			map.put("skgw", skgw);
    			map.put("wdsq", wdsq);
    			map.put("jlinfo", info);
    		}
    		
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return map;
    }

    //职位收藏保存
    @RequestMapping("zwsc")
	@ResponseBody
	public Map<String, Object> zwsc(tb_zwscjl scjl, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HttpSession session = request.getSession();
    		tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
			if(user!=null){
				if(user.getZh_sf()==0){
					scjl.setZh_id(user.getZh_id());
					List<tb_zwscjl> list=zwscService.findList(scjl);
					if(list.size()>0){
						map.put("type", "1");
						map.put("message", "岗位已经收藏过，不要重复收藏！！！");
					}else{
						zwscService.save(scjl, request);
						map.put("type", "2");
						map.put("message", "岗位收藏成功！");
					}
				}else{
					map.put("type", "3");
					map.put("message", "企业用户不能收藏！请登录个人用户");
				}
			}else{
				map.put("type", "4");
				map.put("message", "用户未登录！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", e.getMessage());
		}
		return map;
	}

	//职位收藏只检查不保存
	@RequestMapping("zwscor")
	@ResponseBody
	public Map<String, Object> zwscor(tb_zwscjl scjl, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			HttpSession session = request.getSession();
			tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
			if(user!=null){
				if(user.getZh_sf()==0){
					scjl.setZh_id(user.getZh_id());
					List<tb_zwscjl> list=zwscService.findList(scjl);
					if(list.size()>0){
						map.put("type", "1");
						map.put("message", "岗位已经收藏过，不要重复收藏！！！");
					}else{
						map.put("type", "2");
					}
				}else{
					map.put("type", "3");
					map.put("message", "企业用户不能收藏！请登录个人用户");
				}
			}else{
				map.put("type", "4");
				map.put("message", "用户未登录！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", e.getMessage());
		}
		return map;
	}
    //简历浏览保存
    @RequestMapping("saveJllljl")
	@ResponseBody
	public Map<String, Object> zwsc(Jllljl jllljl, HttpServletRequest request) {
		Map<String, Object> map = new HashMap<String, Object>();
		try {
    		jllljl.setJlllSj(DateUtil.formatDate(new Date()));
    		jllljlService.save(jllljl);
    		
		} catch (Exception e) {
			e.printStackTrace();
			map.put("message", e.getMessage());
		}
		return map;
	}
    /* 查询人才数据列表 */
    @RequestMapping("/findTalentList")
    public ModelAndView list(
            @RequestParam(value = "pageCurrent",defaultValue = "1",required = false) Integer pageCurrent,
            @RequestParam(value = "pageSize",defaultValue ="30",required = false) Integer pageSize, Jlinfo jlinfo){
    	ModelAndView view=null;
    	try{
    		view = new ModelAndView("cms/talent_list");
	        view.addObject("model",jlinfoService.page(pageCurrent,pageSize,jlinfo));
	        view.addObject("jlinfo", jlinfo);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return view;
    }
    /* 后台查询简历列表 */
    @RequestMapping("/findUserResumeList")
    public ModelAndView findUserResumeList(
            @RequestParam(value = "pageCurrent",defaultValue = "1",required = false) Integer pageCurrent,
            @RequestParam(value = "zhId",required = true) String zhId,
            @RequestParam(value = "pageSize",defaultValue ="5",required = false) Integer pageSize, Jlbanben jlbanben,HttpServletRequest request){
    	ModelAndView view=new ModelAndView("cms/talent_jlbanben");;
    	try{
    		//HttpSession session = request.getSession();
    		//tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
    		Jlinfo info = jlinfoService.findByUserCodeId(zhId);
    		if(info!=null){
    				jlbanben.setJianliId(info.getJianliId());
    				PageInfo<Jlbanben> p = jlbanbenService.page(pageCurrent,pageSize,jlbanben);
        	        view.addObject("model", p);
    				view.addObject("jlbanben", jlbanben);
    				view.addObject("zhId",zhId);
    			}
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return view;
    }
    /* 后台预览简历 */
	@RequestMapping("/previewResumeByAdmin")
	public ModelAndView previewResumeByAdmin(@RequestParam(value = "id",required = false) String jlbanbenId,@RequestParam(value = "zh_id",required = false) String zh_id,
									  HttpServletRequest request){
		ModelAndView view=new ModelAndView("www/syrc/jlzx-yljl");
		try{
				Jlinfo info = jlinfoService.findByUserCodeId(zh_id);
				if (info != null) {
					Calendar c = Calendar.getInstance();
					int year = c.get(Calendar.YEAR);
					if (info.getJianliCsrq() != null) {
						info.setJianliCsrq(year - Integer.parseInt(info.getJianliCsrq().substring(0, 4)) + "岁(" + DateUtil.formatHzDate(info.getJianliCsrq()) + ")");
					}
					if (info.getJianliKsgz() != null) {
						info.setJianliKsgz(year - Integer.parseInt(info.getJianliKsgz().substring(0, 4)) + "年工作经验");
					}
					view.addObject("jlinfo", info);
				}
			Jlqzyx qzyx=qzyxService.findJlqzyxByJlBanbenId(jlbanbenId);
			List<Jlgzjy> gzjyList=gzjyService.findGzjyByJibanbenId(jlbanbenId);
			List<Jljyjl> jyjlList=jyjlService.findJyjlByJibanbenId(jlbanbenId);
			List<Jlxnry> xnryList=xnryService.findXnryByJibanbenId(jlbanbenId);
			List<Jlxnzw> xnzwList=xnzwService.findXnzwByJibanbenId(jlbanbenId);

			for(Jlgzjy gzjy:gzjyList){
    			gzjy.setGzjyXsrs(DataDicUtil.getGsgm(gzjy.getGzjyGsgm()));
    			gzjy.setGzjyLzyy(DataDicUtil.getGsxz(gzjy.getGzjyGsxz()));
    			gzjy.setGzjyHbdx(DataDicUtil.getGzxz(gzjy.getGzjyGzlx()));
    			gzjy.setGzjyKssj(DateUtil.formatHzDate(gzjy.getGzjyKssj()));
    			gzjy.setGzjyJssj(DateUtil.formatHzDate(gzjy.getGzjyJssj()));
    		}
    		for(Jljyjl jyjl:jyjlList){
    			jyjl.setJyjlZyms(DataDicUtil.getXl(jyjl.getJyjlXl()));
    			jyjl.setJyjlKssj(DateUtil.formatHzDate(jyjl.getJyjlKssj()));
    			jyjl.setJyjlJssj(DateUtil.formatHzDate(jyjl.getJyjlJssj()));
    		}
    		for(Jlxnry xnry:xnryList){
    			xnry.setXnrySj(DateUtil.formatHzDate(xnry.getXnrySj()));
    		}
    		for(Jlxnzw xnzw:xnzwList){
    			xnzw.setXnzwKssj(DateUtil.formatHzDate(xnzw.getXnzwKssj()));
    			xnzw.setXnzwJssj(DateUtil.formatHzDate(xnzw.getXnzwJssj()));
    		}
			Jlbanben jlbb=jlbanbenService.findById(jlbanbenId);
			view.addObject("qzyx",qzyx);
			view.addObject("xnryList",xnryList);
			view.addObject("xnzwList",xnzwList);
			view.addObject("gzjyList",gzjyList.get(0));
			view.addObject("gzjyLists",gzjyList);
			view.addObject("jyjlList",jyjlList.get(0));
			view.addObject("jyjlLists",jyjlList);
			view.addObject("jlbb",jlbb);
		}catch(Exception e){
			e.printStackTrace();
		}
		return view;
	}
	@RequestMapping("/excel")
    public ModelAndView excel(){
        List<Jlinfo> list =jlinfoService.findAll();
        for(int i=0;i<list.size();i++){
            String sex=list.get(i).getJianliXb()==0?"男":"女";
            list.get(i).setJianliYb(sex);
        }
        ExcelUtil.exports2007("人才信息表",list);
        return null;
    }
/*******************************************以下为手机页面调用的方法*****************************/
	 /* 列表 */
    @RequestMapping("/goGrzxForSj")
    public ModelAndView goGrzxForSj( Jlbanben jlbanben,HttpServletRequest request){
    	ModelAndView view=new ModelAndView("www/syrc-mobile/jlzx-grzx");;
    	try{
    		HttpSession session = request.getSession();
    		tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
    		if(user!=null){
    			Jlinfo jlinfo = jlinfoService.findByUserCodeId(user.getZh_id());
    			int skgw = jllljlService.findSkgwCountByZhId(user.getZh_id());
    			int wdsq = zwtdjlService.findWdsqCountByZhId(user.getZh_id());
    			int wdsc = zwscjlService.findWdscCountByZhId(user.getZh_id());
    			view.addObject("skgw", skgw);//谁看过我
    			view.addObject("wdsq", wdsq);//我的收藏
    			view.addObject("wdsc", wdsc);//我的申请
				/**limeng 20211224 add**/
    			view.addObject("lwpq", eightCategoryZxbmService.findByCategoryId("1033",user.getZh_id()).size());//劳务派遣服务
    			view.addObject("rczp", eightCategoryZxbmService.findByCategoryId("1034",user.getZh_id()).size());//人才招聘服务
    			view.addObject("rlzywb", eightCategoryZxbmService.findByCategoryId("1035",user.getZh_id()).size());//人力资源外包服务
    			view.addObject("rccpfw", eightCategoryZxbmService.findByCategoryId("1036",user.getZh_id()).size());//人才测评服务
    			view.addObject("rlzypx", eightCategoryZxbmService.findByCategoryId("1037",user.getZh_id()).size());//人力资源培训服务
    			view.addObject("rlzygl", eightCategoryZxbmService.findByCategoryId("1038",user.getZh_id()).size());//人力资源管理咨询服务
    			view.addObject("shbzswdl", eightCategoryZxbmService.findByCategoryId("1039",user.getZh_id()).size());//人力资源和社会保障事务代理
    			view.addObject("ldryrs", eightCategoryZxbmService.findByCategoryId("1040",user.getZh_id()).size());//流动人员人事档案服务

    			if(jlinfo!=null){
    				Calendar c = Calendar.getInstance();
    				int year = c.get(Calendar.YEAR);
    				if(jlinfo.getJianliKsgz()!=null){
    					jlinfo.setJianliKsgz(year-Integer.parseInt(jlinfo.getJianliKsgz().substring(0, 4))+"年工作经验");
    				}
    				if(jlinfo.getJianliCsrq()!=null){
    					jlinfo.setJianliCsrq(year-Integer.parseInt(jlinfo.getJianliCsrq().substring(0, 4))+"岁");
    				}
        	        view.addObject("jlinfo", jlinfo);
    			}
    		}
    		
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return view;
    }
    /*修改 */
    @RequestMapping("/findXnzwAndXnryByJlbbId")
    public ModelAndView findXnzwAndXnryByJlbbId(@RequestParam(value = "jlBbId",required = false) String id, Model model){
    	ModelAndView view=new ModelAndView("www/syrc-mobile/jlzx-xnryAndxnzw");
    	List<Jlxnry> jlxnry=null;
    	if(id!=null){
    		jlxnry=xnryService.findXnryByJibanbenId(id);
        }
		for(Jlxnry xnry:jlxnry){
			xnry.setXnrySj(DateUtil.formatHzDate(xnry.getXnrySj()));
		}
		view.addObject("xnryList", jlxnry);
		List<Jlxnzw> jlxnzw=null;
    	if(id!=null){
    		jlxnzw=xnzwService.findXnzwByJibanbenId(id);
        }
		for(Jlxnzw xnzw:jlxnzw){
			xnzw.setXnzwKssj(DateUtil.formatHzDate(xnzw.getXnzwKssj()));
			xnzw.setXnzwJssj(DateUtil.formatHzDate(xnzw.getXnzwJssj()));
		}
		view.addObject("xnzwList", jlxnzw);
		if(id!=null&&!"".equals(id)){
    		view.addObject("jlBbId", id);
    	}
        return view;
    }
    /*修改 */
    @RequestMapping("/findGzjyByJlbbId")
    public ModelAndView findGzjyByJlbbId(@RequestParam(value = "jlBbId",required = false) String id, Model model){
    	ModelAndView view=new ModelAndView("www/syrc-mobile/jlzx-gzjyList");
    	List<Jlgzjy> jlgzjy=null;
    	if(id!=null){
    		jlgzjy=gzjyService.findGzjyByJibanbenId(id);
        }
    	for(Jlgzjy gzjy:jlgzjy){
			gzjy.setGzjyKssj(DateUtil.formatHzDate(gzjy.getGzjyKssj()));
			if(gzjy.getGzjyJssj().equals("至今")){
				gzjy.setGzjyJssj(gzjy.getGzjyJssj());
			}else{
				gzjy.setGzjyJssj(DateUtil.formatHzDate(gzjy.getGzjyJssj()));
			}
		}
    	view.addObject("gzjyList",jlgzjy);
    	if(id!=null&&!"".equals(id)){
    		view.addObject("jlBbId", id);
    	}
        return view;
    }
    /*修改 */
    @RequestMapping("/findJyjlByJlbbId")
    public ModelAndView findJyjlByJlbbId(@RequestParam(value = "jlBbId",required = false) String id, Model model){
    	ModelAndView view=new ModelAndView("www/syrc-mobile/jlzx-jyjlList");
    	List<Jljyjl> jljyjl=null;
    	if(id!=null){
    		jljyjl=jyjlService.findJyjlByJibanbenId(id);
			for(Jljyjl jyjl:jljyjl){
				jyjl.setJyjlZyms(DataDicUtil.getXl(jyjl.getJyjlXl()));
				jyjl.setJyjlKssj(DateUtil.formatHzDate(jyjl.getJyjlKssj()));
				jyjl.setJyjlJssj(DateUtil.formatHzDate(jyjl.getJyjlJssj()));
			}
			view.addObject("jyjlList", jljyjl);
			if(id!=null&&!"".equals(id)){
				view.addObject("jlBbId", id);
			}
		}else{
			view.addObject("jyjlList", "");
			view.addObject("jlBbId", "");
		}
        return view;
    }
    /* 列表 */
    @RequestMapping("/findQzyxByJlbbId")
    public ModelAndView findQzyxByJlbbId(@RequestParam(value = "jlBbId",required = false) String jlbbId,@RequestParam(value = "type",required = false) Integer type,HttpServletRequest request){
    	ModelAndView view=null;
    	if(type==0){
    		view=new ModelAndView("www/syrc-mobile/jlzx-nsr");
    	}else if(type==1){
    		view=new ModelAndView("www/syrc-mobile/jlzx-qzyx");
    	}else{
    		view=new ModelAndView("www/syrc-mobile/jlzx-jntc");
    	}
    	Jlqzyx qzyx=null;
    	try{
    		if(jlbbId!=null){
    			qzyx=qzyxService.findJlqzyxByJlBanbenId(jlbbId);
    		}
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
    	view.addObject("qzyx", qzyx);
    	if(jlbbId!=null&&!"".equals(jlbbId)){
    		view.addObject("jlBbId", jlbbId);
    	}
        return view;
    }
    /* 列表 */
    @RequestMapping("/getJlInfoById")
    public ModelAndView getJlInfoById(@RequestParam(value = "jianliId",required = true) String jianliId,@RequestParam(value = "jlBbId",required = false) String jlbbId,HttpServletRequest request){
    	ModelAndView view=new ModelAndView("www/syrc-mobile/jlzx-jbxx");
    	Jlinfo jlinfo = null;
    	try{
    		jlinfo=jlinfoService.findById(jianliId);
    		//jlinfo.setJianliCsrq(DateUtil.formatHzDate(jlinfo.getJianliCsrq()));
    		jlinfo.setJianliXm(jlinfo.getJianliXm()==null?"":jlinfo.getJianliXm());
    		jlinfo.setJianliKsgz(jlinfo.getJianliKsgz()==null?"":jlinfo.getJianliKsgz());
    		jlinfo.setJianliSj(jlinfo.getJianliSj()==null?"":jlinfo.getJianliSj());
    		jlinfo.setJianliJzd(jlinfo.getJianliJzd()==null?"":jlinfo.getJianliJzd());
    		jlinfo.setJianliHk(jlinfo.getJianliHk()==null?"":jlinfo.getJianliHk());
    		jlinfo.setJianliQtfshm(jlinfo.getJianliQtfshm()==null?"":jlinfo.getJianliQtfshm());
    		jlinfo.setJianliZjhm(jlinfo.getJianliZjhm()==null?"":jlinfo.getJianliZjhm());
    		jlinfo.setJianliSg(jlinfo.getJianliSg()==null?"":jlinfo.getJianliSg());
    		jlinfo.setJianliJtzz(jlinfo.getJianliJtzz()==null?"":jlinfo.getJianliJtzz());
    		jlinfo.setJianliYb(jlinfo.getJianliYb()==null?"":jlinfo.getJianliYb());
    		jlinfo.setJianliTx(jlinfo.getJianliTx()==null?"../../../static/www/syrc/images/man.png":jlinfo.getJianliTx());
    		if(jlbbId!=null&&!"".equals(jlbbId)){
        		view.addObject("jlBbId", jlbbId);
        	}
    		//NullConverseEmptyStr.nullConverNullString(jlinfo);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
    	view.addObject("jlinfo", jlinfo);
        return view;
    }
    /*修改 */
    @RequestMapping("/findXnryByIdForSj")
    public ModelAndView findXnryByIdForSj(@RequestParam(value = "id",required = true) String id, Model model){
    	ModelAndView view=new ModelAndView("www/syrc-mobile/jlzx-xnry");
    	Jlxnry jlxnry=null;
    	if(id!=null){
    		jlxnry=xnryService.findById(id);
        }
    	view.addObject("xnry", jlxnry);
        return view;
    }
    /*修改 */
    @RequestMapping("/findXnzwByIdForSj")
    public ModelAndView findXnzwByIdForSj(@RequestParam(value = "id",required = true) String id, Model model){
    	ModelAndView view=new ModelAndView("www/syrc-mobile/jlzx-xnzw");
    	Jlxnzw jlxnzw=null;
    	if(id!=null){
    		jlxnzw=xnzwService.findById(id);
        }
    	view.addObject("xnzw", jlxnzw);
        return view;
    }
    /*修改 */
    @RequestMapping("/findGzjyByIdForSj")
    public ModelAndView findGzjyByIdForSj(@RequestParam(value = "id",required = true) String id, Model model){
    	ModelAndView view=new ModelAndView("www/syrc-mobile/jlzx-gzjy");
    	Jlgzjy jlgzjy=null;
    	if(id!=null){
    		jlgzjy=gzjyService.findById(id);
        }
    	view.addObject("gzjy", jlgzjy);
        return view;
    }
    /*修改 */
    @RequestMapping("/findJyjlByIdForSj")
    public ModelAndView findJyjlByIdForSj(@RequestParam(value = "id",required = true) String id, Model model){
    	ModelAndView view=new ModelAndView("www/syrc-mobile/jlzx-jyjl");
    	Jljyjl jljyjl=null;
    	if(id!=null){
    		jljyjl=jyjlService.findById(id);
        }
    	view.addObject("jyjl", jljyjl);
        return view;
    }
    /*修改 */
    @RequestMapping("/addXnryForSj")
    public ModelAndView addXnryForSj(@RequestParam(value = "jlBbId",required = false) String jlBbId, Model model){
    	ModelAndView view=new ModelAndView("www/syrc-mobile/jlzx-xnry");
    	if(jlBbId!=null&&!"".equals(jlBbId)){
    		view.addObject("jlBbId", jlBbId);
    	}
    	view.addObject("xnry", new Jlxnry());
        return view;
        
    }
    /*修改 */
    @RequestMapping("/addXnzwForSj")
    public ModelAndView addXnzwForSj(@RequestParam(value = "jlBbId",required = false) String jlBbId, Model model){
    	ModelAndView view=new ModelAndView("www/syrc-mobile/jlzx-xnzw");
    	if(jlBbId!=null&&!"".equals(jlBbId)){
    		view.addObject("jlBbId", jlBbId);
    	}
    	view.addObject("xnzw", new Jlxnzw());
        return view;
    }
    /*修改 */
    @RequestMapping("/addGzjyForSj")
    public ModelAndView addGzjyForSj(@RequestParam(value = "jlBbId",required = false) String jlBbId, Model model){
    	ModelAndView view=new ModelAndView("www/syrc-mobile/jlzx-gzjy");
    	if(jlBbId!=null&&!"".equals(jlBbId)){
    		view.addObject("jlBbId", jlBbId);
    	}
    	view.addObject("gzjy", new Jlgzjy());
        return view;
    }
    /*修改 */
    @RequestMapping("/addJyjlForSj")
    public ModelAndView addJyjlForSj(@RequestParam(value = "jlBbId",required = false) String jlBbId, Model model){
    	ModelAndView view=new ModelAndView("www/syrc-mobile/jlzx-jyjl");
    	if(jlBbId!=null&&!"".equals(jlBbId)){
    		view.addObject("jlBbId", jlBbId);
    	}
    	view.addObject("jyjl", new Jljyjl());
        return view;
    }
	/**
	 *个人密码修改
	 * @return
	 */
	@RequestMapping("goToXgmm")
	public ModelAndView goToXgmm(HttpServletRequest request, Model model) {
		ModelAndView view=new ModelAndView();
		HttpSession session = request.getSession();
		tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
		view.addObject("flag","skgw");
		if(ControllerUtil.mobileOrNot(request)) {
			view.setViewName("www/syrc-mobile/grxgmm");
		}else{
			view.setViewName("www/syrc/grxgmm");
		}
		Jlinfo jlinfo = jlinfoService.findByUserCodeId(user.getZh_id());
		view.addObject("jlinfo", jlinfo);
		return view;

	}
	/* 基础信息修改前查询*/
	@RequestMapping("/getGrJlInfo")
	@ResponseBody
	public Map<String,Object> getGrJlInfo(Jlinfo jlinfo,HttpServletRequest request){
		Map<String,Object> map=new HashMap<String, Object>();
		try{
			HttpSession session = request.getSession();
			tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
			if(user!=null){
				Jlinfo info = jlinfoService.findById(jlinfo.getJianliId());
				map.put("jlinfo", info);
			}

		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/*年收入，求职意向修改前查询 */
	@RequestMapping("/findQzyxByJlbbIdThree")
	@ResponseBody
	public Map<String,Object> findQzyxByJlbbIdThree(Jlqzyx pojo,HttpServletRequest request){
		Map<String,Object> map=new HashMap<String, Object>();
		try{
			HttpSession session = request.getSession();
			tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
			if(user!=null){
				String jlbbId=pojo.getJlBbId();
				if(jlbbId!=null){
					Jlqzyx qzyx=qzyxService.findJlqzyxByJlBanbenId(jlbbId);
					map.put("qzyx", qzyx);
				}

			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/*
	工作经验修改前查询
	 */
	@RequestMapping("/findGzjyByIdForJlgzjy")
	@ResponseBody
	public Map<String,Object> findGzjyByIdForJlgzjy(Jlgzjy pojo,HttpServletRequest request){
		Map<String,Object> map=new HashMap<String, Object>();
		try{
			HttpSession session = request.getSession();
			tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
			if(user!=null){
				String jlId=pojo.getGzjyId();
				if(jlId!=null){
					Jlgzjy gzjy=gzjyService.findById(jlId);
					map.put("gzjy", gzjy);
				}

			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/*
	教育经历修改前查询
	 */
	@RequestMapping("/findJyjlByIdForSjJljyjl")
	@ResponseBody
	public Map<String,Object> findJyjlByIdForSjJljyjl(Jljyjl pojo,HttpServletRequest request){
		Map<String,Object> map=new HashMap<String, Object>();
		try{
			HttpSession session = request.getSession();
			tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
			if(user!=null){
				String jlId=pojo.getJyjlId();
				if(jlId!=null){
					Jljyjl	jljyjl=jyjlService.findById(jlId);
					map.put("jyjl", jljyjl);
				}

			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/*
	校内荣誉修改前查询
	 */
	@RequestMapping("/findJyjlByIdForXnry")
	@ResponseBody
	public Map<String,Object> findJyjlByIdForXnry(Jlxnry pojo,HttpServletRequest request){
		Map<String,Object> map=new HashMap<String, Object>();
		try{
			HttpSession session = request.getSession();
			tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
			if(user!=null){
				String jlId=pojo.getXnryId();
				if(jlId!=null){
					Jlxnry jlxnry=xnryService.findById(jlId);
					map.put("xnry", jlxnry);
				}

			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}
	/*
        校内职务修改前查询
         */
	@RequestMapping("/findJyjlByIdForXnzw")
	@ResponseBody
	public Map<String,Object> findJyjlByIdForXnzw(Jlxnzw pojo,HttpServletRequest request){
		Map<String,Object> map=new HashMap<String, Object>();
		try{
			HttpSession session = request.getSession();
			tb_zh_info user=(tb_zh_info)session.getAttribute("tbZhInfo_loginSession");
			if(user!=null){
				String jlId=pojo.getXnzwId();
				if(jlId!=null){
					Jlxnzw jlxnzw=xnzwService.findById(jlId);
					map.put("xnzw", jlxnzw);
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return map;
	}

}
