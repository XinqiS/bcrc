package com.sooka.module.web.cms;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.ControllerUtil;
import com.sooka.module.web.system.service.GwInfoService;
import com.sooka.module.web.system.vo.gjss;
import com.sooka.mybatis.model.Jlbanben;

@Controller
@RequestMapping("/gjss")
public class gjssControl {

	@Autowired
	private GwInfoService gwinfoService;

	@RequestMapping("/gotoGjss")
	public String index(HttpServletRequest request) {
		if(ControllerUtil.mobileOrNot(request)){
			return "www/syrc-mobile/gjss";
    	}else{
    		return "www/syrc/gjss";
    	}
		
	}

	@RequestMapping("/selectExample")
	public ModelAndView selectExample(
			@RequestParam(value = "pageCurrent", defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer pageSize,
			Jlbanben jlbanben, HttpServletRequest request, gjss gjss) {
		String hy = gjss.getQy_sshy();
		String zn = gjss.getZw_zwlb();
		ModelAndView view = null;
		gjss g=new gjss();
		if(ControllerUtil.mobileOrNot(request)){
			//g=gwinfoService.gjssssforsj(gjss);
    		view=new ModelAndView("www/syrc-mobile/wlzp");
    		//pageSize=100000000;
    	}else{
    		//g=gwinfoService.gjssss(gjss);
    		view=new ModelAndView("www/syrc/wlzp");
    	}
		g=gwinfoService.gjssss(gjss);
		// 查询正在招聘的职位
		g.setZw_zt(0);
		PageInfo<Map<String, Object>> p = gwinfoService.page(pageNumber,
				pageSize, g);
		List<Map<String, Object>> list;
		if (p != null) {
			list = p.getList();
			view.addObject("model", list);
			view.addObject("total", p.getTotal());// 总数
			view.addObject("pagesize", p.getPageSize());// 页数
			view.addObject("pagenum", p.getPageNum());
		} else {
			list = new ArrayList<Map<String, Object>>();
			view.addObject("model", list);
			view.addObject("total", 1);// 总数
			view.addObject("pagesize", 1);// 页数
			view.addObject("pagenum", 1);
		}
		gjss.setQy_sshy(hy);
		gjss.setZw_zwlb(zn);
		view.addObject("gjss", gjss);
		return view;
	}

	@RequestMapping("/bysjy")
	public ModelAndView bysjy(
			@RequestParam(value = "pageCurrent", defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			Jlbanben jlbanben, HttpServletRequest request, gjss gjss) {
		ModelAndView view = null;
		gjss g =new gjss();
		if (ControllerUtil.mobileOrNot(request)) {
			view = new ModelAndView("www/syrc-mobile/bysjy");
			pageSize = 100000000;
		} else{
			view = new ModelAndView("www/syrc/bysjy");
			g = gwinfoService.gjssss(gjss);
		}
		// 查询应届毕业生的职位
		g.setZw_yjbys("0");
		// 查询正在招聘的职位
		g.setZw_zt(0);
		PageInfo<Map<String, Object>> p = gwinfoService.page(pageNumber,
				pageSize, g);
		List<Map<String, Object>> list;
		if (p != null) {
			list = p.getList();
			view.addObject("model", list);
			view.addObject("total", p.getTotal());// 总数
			view.addObject("pagesize", p.getPageSize());// 页数
			view.addObject("pagenum", p.getPageNum());
		} else {
			list = new ArrayList<Map<String, Object>>();
			view.addObject("model", list);
			view.addObject("total", 1);// 总数
			view.addObject("pagesize", 1);// 页数
			view.addObject("pagenum", 1);
		}
		view.addObject("gjss", gjss);
		return view;
	}

	@RequestMapping("/zwss")
	public ModelAndView zwss(
			@RequestParam(value = "pageCurrent", defaultValue = "1", required = false) Integer pageNumber,
			@RequestParam(value = "pageSize", defaultValue = "5", required = false) Integer pageSize,
			Jlbanben jlbanben, HttpServletRequest request, gjss gjss) {
		String hy = gjss.getQy_sshy();
		String zn = gjss.getZw_zwlb();
		ModelAndView view = null;
		gjss g=new gjss();
		if (ControllerUtil.mobileOrNot(request)) {
			g=gwinfoService.gjssssforsj(gjss);
			view = new ModelAndView("www/syrc-mobile/zwssjg");
			pageSize = 100000000;
		} else{
			g=gwinfoService.gjssss(gjss);
			view = new ModelAndView("www/syrc/zwssjg");
		}

		// 查询正在招聘的职位
		g.setZw_zt(0);
		PageInfo<Map<String, Object>> p = gwinfoService.page(pageNumber,
				pageSize, g);
		List<Map<String, Object>> list;
		if (p != null) {
			list = p.getList();
			view.addObject("model", list);
			view.addObject("total", p.getTotal());// 总数
			view.addObject("pagesize", p.getPageSize());// 页数
			view.addObject("pagenum", p.getPageNum());
		} else {
			list = new ArrayList<Map<String, Object>>();
			view.addObject("model", list);
			view.addObject("total", 1);// 总数
			view.addObject("pagesize", 1);// 页数
			view.addObject("pagenum", 1);
		}
		gjss.setQy_sshy(hy);
		gjss.setZw_zwlb(zn);
		view.addObject("gjss", gjss);
		return view;
	}

}
