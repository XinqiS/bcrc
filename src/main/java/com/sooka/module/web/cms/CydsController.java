package com.sooka.module.web.cms;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.sooka.common.upload.UploadComponent;
import com.sooka.common.upload.bean.UploadBean;
import com.sooka.common.utils.ControllerUtil;
import com.sooka.common.utils.DataDicUtil;
import com.sooka.common.utils.IdealDicUtil;
import com.sooka.module.web.cms.service.CydsService;
import com.sooka.module.web.cms.service.EnterpriseService;
import com.sooka.module.web.cms.service.LiveService;
import com.sooka.module.web.cms.service.SiteService;
import com.sooka.module.web.cms.vo.TCmsLiveVo;
import com.sooka.mybatis.model.Cyds;
import com.sooka.mybatis.model.TCmsEnterprise;
import com.sooka.mybatis.model.TCmsLive;
import com.sooka.mybatis.model.TCmsSite;

@Controller
@RequestMapping("/cms/cyds")
public class CydsController {

    @Autowired
    private CydsService cydsService;
    
    @Autowired
    private UploadComponent uploadComponent;
    
    @Autowired
    private SiteService siteService;

    /* 列表 */
    @RequestMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "pageNumber",defaultValue = "1",required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue ="50",required = false) Integer pageSize, Cyds cyds){
    	ModelAndView view=null;
    	try{
    		view = new ModelAndView("cms/cyds_list");
	        view.addObject("model",cydsService.page(pageNumber,pageSize,cyds));
	        view.addObject("cyds", cyds);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
        return view;
    }

    /*修改 */
    @RequestMapping("/input")
    public String input(@RequestParam(value = "id",required = false) String id, Model model,Cyds cyds,HttpServletRequest request){
    	String domain = ControllerUtil.getDomain();
        TCmsSite site = siteService.findByDomain(domain);
    	if(id!=null){
        	model.addAttribute("cyds", cydsService.findById(id));
        }
    	
    	model.addAttribute("site", site);
    	if(ControllerUtil.mobileOrNot(request)){
    		 return "www/syrc-mobile/cyds";
		}else{
			 return "www/syrc/cyds";
    	}
       
    }
    /*修改 */
    @RequestMapping("/detail")
    public String detail(@RequestParam(value = "id",required = true) String id, Model model,Cyds cyds){
    	if(id!=null){
        	model.addAttribute("cyds", cydsService.findById(id));
    	}
        return "cms/cyds_detail";
    }

    /* 更新 */
    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestParam("rwsfile") MultipartFile multipartFile,@RequestParam("xmsfile") MultipartFile multipartFile2,@RequestParam("pptfile") MultipartFile multipartFile3,HttpServletRequest request,Cyds cyds){
    	UploadBean result = uploadComponent.uploadFile(multipartFile,request);
    	UploadBean result1 = uploadComponent.uploadFile(multipartFile2,request);
    	UploadBean result2 = uploadComponent.uploadFile(multipartFile3,request);
    	cyds.setFile(result.getFileUrl());
    	cyds.setFile1(result1.getFileUrl());
    	cyds.setFile2(result2.getFileUrl());
    	if(cyds.getCydsId()!=null) {
            return cydsService.update(cyds);
        }
        return cydsService.save(cyds);
    }


    /*删除 */
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam("ids") String[] id){
        return cydsService.delete(id);
    }

}
