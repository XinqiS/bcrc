package com.sooka.component.shiro;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.sooka.module.web.system.vo.tb_zh_info;

/**
 * 定义拦截器
 */
@Component
public class ConfigInterceptor implements HandlerInterceptor{
	
	
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		HttpSession session = request.getSession();
        // 从session中获取用户信息
		tb_zh_info comUser = (tb_zh_info) session.getAttribute("tbZhInfo_loginSession");
        // session过期
        if(comUser == null){ 
        	//System.out.print("登录session过期, 自动跳至登录页.......");
            response.sendRedirect("/system/reception/nologin"); // 通过接口跳转登录页面, 注:重定向后下边的代码还会执行 ; /outToLogin是跳至登录页的后台接口
            return false;
        }else{
        	return true;
        }
    }

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}
}
