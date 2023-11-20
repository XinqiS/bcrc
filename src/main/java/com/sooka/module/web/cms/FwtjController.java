package com.sooka.module.web.cms;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/cms/fwtj")
public class FwtjController {

    @RequestMapping
    public String index(){
        return "cms/fwtj_list";
    }
}
