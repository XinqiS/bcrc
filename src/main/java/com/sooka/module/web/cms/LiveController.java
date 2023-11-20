package com.sooka.module.web.cms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sooka.module.web.cms.service.LiveService;
import com.sooka.module.web.cms.vo.TCmsLiveVo;
import com.sooka.mybatis.model.TCmsLive;

@Controller
@RequestMapping("/cms/live")
public class LiveController {

    @Autowired
    private LiveService liveService;

    /* 发言文字列表 */
    @RequestMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "pageNumber",defaultValue = "1",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue ="50",required = false) Integer pageSize, TCmsLiveVo live){
    	ModelAndView view=null;
    	try{
    		view = new ModelAndView("cms/live_list");
	        view.addObject("model",liveService.page(pageNumber,pageSize,live));
	        view.addObject("live", live);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return view;
    }

    /*修改 */
    @RequestMapping("/input")
    public String input(@RequestParam(value = "id",required = false) Integer id, Model model,TCmsLive live){
        if(id!=null) {
            model.addAttribute("live", liveService.findById(id));
        }else{
        	if(liveService.selectMaxSort(live)==null){
        		live.setSort(1);
        	}else{
        		live.setSort(liveService.selectMaxSort(live));
        	}
        	model.addAttribute("live", live);
        }
        return "cms/live_input";
    }

    /* 更新 */
    @RequestMapping("/update")
    @ResponseBody
    public String update(TCmsLive live){
        if(live.getId()!=null) {
            return liveService.update(live);
        }
        return liveService.save(live);
    }


    /*删除 */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("ids") Integer[] id){
        return liveService.delete(id);
    }

}
