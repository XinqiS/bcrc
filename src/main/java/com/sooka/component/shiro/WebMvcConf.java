package com.sooka.component.shiro;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConf extends WebMvcConfigurerAdapter{
	
        /**
	 * 注入第一步定义好的拦截器
	 */
    @Autowired
    private ConfigInterceptor configInterceptor;
 
    /**
     * 定义拦截规则, 根据自己需要进行配置拦截和排除的接口
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(configInterceptor)
        // .addPathPatterns() 是配置需要拦截的路径 
        .addPathPatterns("/cms/resume/**")
        .addPathPatterns("/qyzx/**")
        .excludePathPatterns("/qyzx/gwXq")
        .excludePathPatterns("/qyzx/qyXq")
        //.excludePathPatterns("/cms/resume/wdscsqzw")
        //.excludePathPatterns("/cms/resume/zwsc")
        .excludePathPatterns("/cms/resume/findTalentList")
        .excludePathPatterns("/cms/resume/findUserResumeList")
        .excludePathPatterns("/cms/resume/previewResumeByAdmin")
        .excludePathPatterns("/static/**");
        // .excludePathPatterns() 用于排除拦截
        /*.excludePathPatterns("/") // 排除127.0.0.1进入登录页
        .excludePathPatterns("/system/reception/**") // 排除登录页获取验证码接口
        .excludePathPatterns("/verify") // 排除验证账号密码接口
        .excludePathPatterns("/static/**"); // 排除静态文件
       .excludePathPatterns("/js/**")
        .excludePathPatterns("/img/**")
        .excludePathPatterns("/css/**");*/
    }
}