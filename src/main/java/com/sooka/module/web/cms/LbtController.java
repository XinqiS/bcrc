package com.sooka.module.web.cms;

import com.github.pagehelper.PageInfo;
import com.sooka.common.utils.StrUtil;
import com.sooka.module.web.cms.service.LbtService;
import com.sooka.mybatis.model.TCmsLbt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.SQLException;

@Controller
@RequestMapping("/cms/lbt")
public class LbtController {

    @Autowired
    private LbtService lbtService;

    @RequestMapping
    public String index(@RequestParam(value = "pageCurrent",defaultValue = "1") Integer pageNumber,
                        @RequestParam(value = "pageSize",defaultValue = "50") Integer pageSize,
                        TCmsLbt pojo, Model model) {
        PageInfo page = lbtService.page(pageNumber,pageSize,pojo);
        model.addAttribute("model",page);
        model.addAttribute("pojo",pojo);
        return "cms/lbt_list";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(TCmsLbt pojo){
        if(pojo.getLbt_id()!=null){
            lbtService.update(pojo);
        }
        if(StrUtil.isBlank(pojo.getImg())){
            pojo.setImg(null);
        }
        if(StrUtil.isBlank(pojo.getVideo())){
            pojo.setVideo(null);
        }
        return lbtService.save(pojo);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam(value = "ids",required = false) Integer[] ids) throws SQLException {
        return lbtService.delete(ids);
    }
}
