package com.sooka.module.web.cms;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.ControllerUtil;
import com.sooka.common.utils.DateUtil;
import com.sooka.module.web.cms.service.GjrcService;
import com.sooka.module.web.cms.vo.GjrcVo;
import com.sooka.module.web.system.service.GwInfoService;

@Controller
@RequestMapping("/gjrc")
public class GjrcControl {

	@Autowired
	private GjrcService gjrcService;
	@Autowired
	private GwInfoService gwinfoService;

	@RequestMapping("/gjrcb")
	public ModelAndView gjrcb(
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,HttpServletRequest request)
			throws ParseException {
		ModelAndView mv =null;
		if(ControllerUtil.mobileOrNot(request))
		mv= new ModelAndView("www/syrc-mobile/gjrc-gjrcb");
		else
		mv = new ModelAndView("www/syrc/gjrc-gjrcb");
		PageInfo<GjrcVo> page = gjrcService.page(pageNum, 5);
		List<GjrcVo> list = page.getList();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy年MM月dd日");
		Date date = null;
		for (GjrcVo gjrcVo : list) {
			try {
				date = sdf.parse(gjrcVo.getJianli_csrq());
			} catch (Exception e) {
				date = sdf2.parse(gjrcVo.getJianli_csrq());
			}
			gjrcVo.setJianli_csrq("" + DateUtil.getAgeByBirth(date));
		}
		page.setList(list);
		mv.addObject("model", page);
		return mv;

	}

	@RequestMapping("zgjgw")
	public ModelAndView zgjgw(
			@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,HttpServletRequest request) {
		
		ModelAndView mv =null;
		if(ControllerUtil.mobileOrNot(request)){
				mv = new ModelAndView("www/syrc-mobile/gjrc-zgjgw");
				mv.addObject("model", gwinfoService.gjrcZgjgw(pageNum,10000000));
		} 
		else {
				mv= new ModelAndView("www/syrc/gjrc-zgjgw");
				mv.addObject("model", gwinfoService.gjrcZgjgw(pageNum,5));
		}
		return mv;

	}
}
