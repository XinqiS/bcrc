package com.sooka.module.web.cms;

import com.github.pagehelper.PageInfo;
import com.sooka.module.web.cms.service.RegisterService;
import com.sooka.mybatis.model.TCmsRegister;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/cms/reg")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    @RequestMapping
    public String index(@RequestParam(value = "pageCurrent",defaultValue = "1") Integer pageNumber,
                        @RequestParam(value = "pageSize",defaultValue = "50") Integer pageSize,
                        TCmsRegister pojo, Model model) {
        PageInfo page = registerService.page(pageNumber,pageSize);
        model.addAttribute("model",page);
        model.addAttribute("pojo",pojo);
        return "cms/register_list";
    }

    @RequestMapping("/input")
    public String input(){
        return "www/jlrd/register";
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(TCmsRegister pojo){
        return registerService.save(pojo);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Integer id){
        return registerService.delete(id);
    }
}
