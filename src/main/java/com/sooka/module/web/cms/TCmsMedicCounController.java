package com.sooka.module.web.cms;

import com.sooka.module.web.cms.service.MedicCountService;
import com.sooka.mybatis.model.TCmsMediaCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TCmsMedicCounController {
    @Autowired
    private MedicCountService medicCountService;

    @RequestMapping("cms/AllMonthCharts")
    @ResponseBody
    public String charts(){
        return medicCountService.findAllMonthCount();
    }
    @RequestMapping("cms/AllCharts")
    @ResponseBody
    public String allCharts(){
        TCmsMediaCount pojo=medicCountService.findByMaxId();
        pojo.setCallConunt(pojo.getCallConunt()+1);
        medicCountService.update(pojo);
        return medicCountService.findAllCount();
    }
}
