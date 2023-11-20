package com.sooka.module.web.cms;


import com.sooka.common.annotation.FormToken;
import com.sooka.common.upload.UploadComponent;
import com.sooka.common.upload.bean.UploadBean;
import com.sooka.common.utils.ControllerUtil;
import com.sooka.common.utils.JsonUtil;
import com.sooka.common.utils.StrUtil;
import com.sooka.module.web.cms.service.SiteService;
import com.sooka.module.web.system.service.SysUserService;
import com.sooka.mybatis.model.TCmsSite;
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
import java.util.Map;

@Controller
@RequestMapping("/cms/wjxz")
public class WjxzController {
    @Autowired
    private SysUserService userService;


    @RequestMapping("/selectWjxz")
    public String selectWyly(){
        return "www/jlrd/jlrd_file_login";
    }


    @RequestMapping("/selectSJWjxz")
    public String selectSJWjxz(){
        return "www/jlrd-mobile/jlrd_file_login";
    }


    @RequestMapping("/doLogin")
    @ResponseBody
    public Map<String, Object> doLogin(
            HttpServletRequest request,
            @RequestParam(value = "verifyCode",required = false) String verifyCode,
            @RequestParam(value = "username",required = false) String username,
            @RequestParam(value = "password",required = false) String password,
            @RequestParam(value = "remberMe",required = false,defaultValue = "") String remberMe){

        /* 临时验证码验证 */
        if(StrUtil.isBlank(verifyCode)|| !ControllerUtil.validate(verifyCode,request)) {
            return JsonUtil.toMAP(false,"验证码输入错误");
        }
        return userService.login(request,username,password,remberMe);

    }

    @RequestMapping("/xgmm")
    public String xgmm(){
        return "www/jlrd/jlrd_change_password";
    }


}
