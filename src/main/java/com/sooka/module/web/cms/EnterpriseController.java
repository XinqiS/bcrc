package com.sooka.module.web.cms;
import com.sooka.module.web.system.service.QyInfoService;
import com.sooka.module.web.system.service.SysUserService;
import com.sooka.module.web.system.vo.tb_qy_info;
import com.sooka.module.web.system.vo.tb_zh_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.sooka.module.web.cms.service.EnterpriseService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cms/enterprise")
public class EnterpriseController {

    @Autowired
    private EnterpriseService enterpriseService;
    @Autowired
    private SysUserService userService;
    @Autowired
    private QyInfoService qyInfoService;

    /* 列表 */
    @RequestMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "pageCurrent",defaultValue = "1",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue ="30",required = false) Integer pageSize, tb_qy_info enterprise){
    	ModelAndView view=null;
    	try{
    		view = new ModelAndView("cms/enterprise_list");
	        view.addObject("model",enterpriseService.page(pageNumber,pageSize,enterprise));
	        view.addObject("enterprise", enterprise);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return view;
    }

    /*修改 */
    @RequestMapping("/input")
    public String input(@RequestParam(value = "qy_id",required = false) String qy_id,
                        Model model,tb_qy_info enterprise){
        List<tb_zh_info> zhList=new ArrayList<tb_zh_info>();
        if(qy_id!=null){
            tb_qy_info info=  enterpriseService.findByQyId(qy_id);
        	model.addAttribute("content", info);
            tb_zh_info zhinfo = userService.selectByZhId(info.getZh_id());
            zhList = userService.selectAll();
            if(zhinfo!=null){
                zhList.add(zhinfo);
            }

           /* if(zhList.size()==1){
                model.addAttribute("typesq", "1");
                model.addAttribute("zhList", zhinfo);
            }else{
                model.addAttribute("typesq", "2");
                model.addAttribute("zhList", zhList);
            }*/
            model.addAttribute("zhList", zhList);
            model.addAttribute("zhinfo", zhinfo);

        }else{
            model.addAttribute("content", enterprise);
            zhList = userService.selectAll();
           /* if(zhList.size()==0){
                model.addAttribute("typesq", "0");
            }else if(zhList.size()==1){
                model.addAttribute("typesq", "1");
                tb_zh_info zhinfo=zhList.get(0);
                model.addAttribute("zhList", zhinfo);
            }else{
                model.addAttribute("typesq", "2");
                model.addAttribute("zhList", zhList);
            }*/
            model.addAttribute("zhList", zhList);
        }
        return "cms/enterprise_input";
    }

    /**
     * 查询账号

     * @return
     */
    @RequestMapping("selectZhId")
    @ResponseBody
    public  Object  selectZhId(String zh_id) throws Exception{
        tb_qy_info zh = qyInfoService.selectByzhId(zh_id);
        return zh;
    }


    /*详情 */
    @RequestMapping("/detail")
    public String detail(@RequestParam(value = "qy_id",required = true) String id, Model model,tb_qy_info enterprise){
        if(id!=null){
        	model.addAttribute("enterprise", enterpriseService.findByQyId(id));
        }
        return "cms/enterprise_detail";
    }

    /* 更新 */
    @RequestMapping("/update")
    @ResponseBody
    public String update(tb_qy_info enterprise){
        if("".equals(enterprise.getQy_id()) || enterprise.getQy_id()==null) {
            return enterpriseService.save(enterprise);
        }
        if( enterprise.getQy_qyfl()==null)
        	enterprise.setQy_qyfl("");
        return enterpriseService.update(enterprise);
    }

    /*审批 */
    @RequestMapping("/approval")
    @ResponseBody
    public String approval(tb_qy_info enterprise){

        return enterpriseService.approval(enterprise);
    }


    /* 名企 */
    @RequestMapping("/updateSftj")
    @ResponseBody
    public String updateSftj(tb_qy_info enterprise,Integer type){
        return enterpriseService.updateSftj(enterprise,type);
    }


    /*删除 */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("ids") String[] id){
        return enterpriseService.deleteByGwId(id);
    }

}
