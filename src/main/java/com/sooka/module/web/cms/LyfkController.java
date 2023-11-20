package com.sooka.module.web.cms;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sooka.common.utils.ControllerUtil;
import com.sooka.common.utils.DateUtil;
import com.sooka.common.utils.JsonUtil;
import com.sooka.common.utils.StrUtil;
import com.sooka.module.web.cms.service.LyfkService;
import com.sooka.mybatis.model.Lyfk;

@Controller
@RequestMapping("/cms/lyfk")
public class LyfkController {

    @Autowired
    private LyfkService lyfkService;

    /* 列表 */
    @RequestMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "pageNumber",defaultValue = "1",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue ="50",required = false) Integer pageSize, Lyfk lyfk){
    	ModelAndView view=null;
    	try{
    		view = new ModelAndView("cms/lyfk_list");
	        view.addObject("model",lyfkService.page(pageNumber,pageSize,lyfk));
	        view.addObject("lyfk", lyfk);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return view;
    }

    /*修改 */
    @RequestMapping("/input")
    public String input(@RequestParam(value = "id",required = false) String id, Model model,Lyfk lyfk,HttpServletRequest request){
        if(id!=null){
        	model.addAttribute("lyfk", lyfkService.findById(id));
        }
        if(ControllerUtil.mobileOrNot(request)){
   		 	return "www/syrc-mobile/lyfk";
		}else{
			 return "www/syrc/lyfk";
		}
    }
    /*修改 */
    @RequestMapping("/detail")
    public String detail(@RequestParam(value = "id",required = true) String id, Model model,Lyfk lyfk){
        if(id!=null){
        	model.addAttribute("lyfk", lyfkService.findById(id));
        }
        return "cms/lyfk_detail";
    }
    /*修改 */
    @RequestMapping("/findDetail")
    public String findDetail(@RequestParam(value = "id",required = true) String id, Model model,Lyfk lyfk){
        if(id!=null){
        	model.addAttribute("lyfk", lyfkService.findById(id));
        }
        return "cms/lyfk_info";
    }

    /* 更新 */
    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestParam(value = "verifyCode",required = false) String verifyCode,Lyfk lyfk,HttpServletRequest request){
    	 /* 临时验证码验证 */
        if(StrUtil.isBlank(verifyCode)|| !ControllerUtil.validate(verifyCode,request)) {
            return JsonUtil.toERROR("验证码输入错误");
        }
    	if(lyfk.getLyfkId()!=null) {
            return lyfkService.update(lyfk);
        }
        return lyfkService.save(lyfk);
    }
    /* 更新 */
    @RequestMapping("/updateFknr")
    @ResponseBody
    public String updateFknr(Lyfk lyfk,HttpServletRequest request){
    	Lyfk l=lyfkService.findById(lyfk.getLyfkId());
    	l.setLyfkFknr(lyfk.getLyfkFknr());
    	l.setLyfkFksj(DateUtil.formatDate(new Date()));
        return lyfkService.update(l);
    }


    /*删除 */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("ids") String[] id){
        return lyfkService.delete(id);
    }

}
