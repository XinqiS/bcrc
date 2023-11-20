package com.sooka.module.web.cms;


import com.github.pagehelper.PageInfo;
import com.sooka.common.base.BaseController;
import com.sooka.common.upload.UploadComponent;
import com.sooka.common.upload.bean.UploadBean;
import com.sooka.module.web.cms.service.PcService;
import com.sooka.module.web.cms.service.SiteService;
import com.sooka.mybatis.model.TCmsSite;
import com.sooka.mybatis.model.TCmsPc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

@Controller
@RequestMapping("/cms/pc")
public class PcController  {

    @Autowired
    private PcService pcService;
    @Autowired
    private UploadComponent uploadComponent;
    
    @Autowired
    private SiteService siteService;

    @RequestMapping("/input")
    public String input(@RequestParam(value = "id",required = false) Integer Id, Model model) {
        if(Id!=null) {
            model.addAttribute("pc",pcService.findById(Id));
        }
        return "cms/pc_input";
    }


    @RequestMapping
    public String index(@RequestParam(value = "pageCurrent",defaultValue = "1") Integer pageNumber,
                        @RequestParam(value = "pageSize",defaultValue = "100") Integer pageSize,
                        TCmsPc pojo, Model model){
        try{
            PageInfo<TCmsPc> list=pcService.page(pageNumber,pageSize,pojo);
            model.addAttribute("model",list);
            model.addAttribute("pojo",pojo);
        }catch(Exception e){
            e.printStackTrace();
        }
        return "cms/pc_list";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(HttpServletRequest request,TCmsPc pojo){
        if(pojo.getId()!=null){
            return pcService.update(pojo);
        }
        return pcService.save(pojo);

    }


    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam(value = "ids",required = false) Integer[] ids) throws SQLException {
        return pcService.delete(ids);
    }


    @RequestMapping("/selectPc")
    @ResponseBody
    public Object selectPc(){
        List<TCmsPc> list= pcService.selectPc();
        return list;

    }


}
