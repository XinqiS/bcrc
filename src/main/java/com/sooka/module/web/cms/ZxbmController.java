package com.sooka.module.web.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sooka.common.utils.ControllerUtil;
import com.sooka.module.web.cms.service.ZxbmService;
import com.sooka.mybatis.model.Cyds;
import com.sooka.mybatis.model.Zxbm;

@Controller
@RequestMapping("/cms/zxbm")
public class ZxbmController {

    @Autowired
    private ZxbmService zxbmService;

    /* 列表 */
    @RequestMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "pageNumber",defaultValue = "1",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue ="50",required = false) Integer pageSize, Zxbm zxbm){
    	ModelAndView view=null;
    	try{
    		view = new ModelAndView("cms/zxbm_list");
	        view.addObject("model",zxbmService.page(pageNumber,pageSize,zxbm));
	        view.addObject("zxbm", zxbm);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return view;
    }

    /*修改 */
    @RequestMapping("/input")
    public String input(@RequestParam(value = "id",required = false) String id, Model model,Zxbm zxbm,HttpServletRequest request){
    	if(id!=null){
        	model.addAttribute("zxbm", zxbmService.findById(id));
        }
    	 if(ControllerUtil.mobileOrNot(request)){
    		 	return "www/syrc-mobile/zxbm";
 		}else{
 			 return "www/syrc/zxbm";
 		}
    }
    /*修改 */
    @RequestMapping("/detail")
    public String detail(@RequestParam(value = "id",required = true) String id, Model model,Zxbm zxbm){
        if(id!=null){
        	model.addAttribute("zxbm", zxbmService.findById(id));
        }
        return "cms/zxbm_detail";
    }

    /* 更新 */
    @RequestMapping("/update")
    @ResponseBody
    public String update(Zxbm zxbm){
        if(zxbm.getZxbmId()!=null) {
            return zxbmService.update(zxbm);
        }
        return zxbmService.save(zxbm);
    }


    /*删除 */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("ids") String[] id){
        return zxbmService.delete(id);
    }

}
