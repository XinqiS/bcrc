package com.sooka.module.web.cms;

import com.sooka.module.web.system.service.GwInfoService;
import com.sooka.module.web.system.vo.tb_gw_info;
import com.sooka.module.web.system.vo.tb_qy_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sooka.module.web.cms.service.LiveService;
import com.sooka.module.web.cms.service.PostService;
import com.sooka.module.web.cms.vo.TCmsLiveVo;
import com.sooka.mybatis.model.TCmsLive;
import com.sooka.mybatis.model.TCmsPost;

@Controller
@RequestMapping("/cms/post")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private GwInfoService gwInfoService;


    /* 列表 */
    @RequestMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "pageNumber",defaultValue = "1",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue ="50",required = false) Integer pageSize, tb_gw_info post){
    	ModelAndView view=null;
    	try{
    		view = new ModelAndView("cms/post_list");
	        view.addObject("model",postService.page(pageNumber,pageSize,post));
	        view.addObject("post", post);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return view;
    }

    /*修改 */
    @RequestMapping("/input")
    public String input(@RequestParam(value = "zw_id",required = false) String id, Model model,tb_gw_info post){
    	if(id!=null){
    		model.addAttribute("post", gwInfoService.selectOne(id));
    	}else{
    		model.addAttribute("post", post);
    	}
        return "cms/post_input";
    }
    /* 更新 */
    @RequestMapping("/update")
    @ResponseBody
    public String update(tb_gw_info post){
        if("".equals(post.getZw_id()) || post.getZw_id()==null) {
            return postService.save(post);
        }
        return postService.update(post);
    }

    /*推荐为中高级岗位 */
    @RequestMapping("/updateSftj")
    @ResponseBody
    public String updateSftj(tb_gw_info post,Integer type){
        return postService.updateSftj(post,type);
    }



    /*删除 */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("ids") String[] id){
        return postService.deleteByZwId(id);
    }

}
