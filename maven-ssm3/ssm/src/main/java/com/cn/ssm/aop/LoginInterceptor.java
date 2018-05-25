package com.cn.ssm.aop;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;  
import javax.servlet.http.HttpServletResponse;  

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.cn.ssm.common.LoginRequired;
import com.cn.ssm.service.APPTokenService;
import com.cn.ssm.entity.APPToken;
 

@Configuration
public class LoginInterceptor extends HandlerInterceptorAdapter {
	@Autowired
    public APPTokenService tokenService; 
     /** 
     * Handler执行之前调用这个方法 
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {  
        final HttpServletRequest httpRequest = (HttpServletRequest) request;
        final HttpServletResponse httpResponse = (HttpServletResponse) response;
        final String authHeaderVal = httpRequest.getHeader("token");
 
        /*if (!(handler instanceof HandlerMethod)) {
        	System.out.println("===========>LoginInterceptor preHandle weizhicuowu"); 
            return true;
        }*/
        
        System.out.println(handler.toString());
        System.out.println(handler.getClass());
        String name = request.getServletPath().toString();
 
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
 
        JSONObject json = new JSONObject();
        
        LoginRequired methodAnnotation = method.getAnnotation(LoginRequired.class);
        if (methodAnnotation != null) {
            if (StringUtils.isNotEmpty(authHeaderVal)) {
                try {
                    APPToken appToken = tokenService.getById(authHeaderVal);
                    if((System.currentTimeMillis()-appToken.getExtime())<300000){
                    	System.out.println("========"+name+"===>LoginInterceptor preHandle 验证成功放行"); 
                        if((System.currentTimeMillis()-appToken.getExtime())>1500000)
                        	tokenService.updateTime(appToken);
                        return true;
                    }
                    else{
                    	response.setCharacterEncoding("UTF-8");
                        //response.getWriter().write(JSON.toJSONString(new JsonResult(ResultCode.INVALID_AUTHCODE, "登录已过期，请重新登录！")));
                        json.put("status", -1);
                        response.getWriter().write(json.toString());
                        System.out.println("========"+name+"===>LoginInterceptor preHandle 拦截，登录已过期，请重新登录！");
                        tokenService.deleteToken(authHeaderVal);
                        return false;
                    }
                } catch (Exception e) {
                    response.setCharacterEncoding("UTF-8");
                    //response.getWriter().write(JSON.toJSONString(new JsonResult(ResultCode.INVALID_AUTHCODE, "登录已过期，请重新登录！")));
                    json.put("status", -1);
                    response.getWriter().write(json.toString());
                    System.out.println("========"+name+"===>LoginInterceptor preHandle 拦截，登录已过期，请重新登录！"); 
                    return false;
                }
            } else {
                response.setCharacterEncoding("UTF-8");
                //response.getWriter().write(JSON.toJSONString(new JsonResult(ResultCode.NOT_LOGIN,"尚未登录")));
                json.put("status", 0);
                response.getWriter().write(json.toString());
                System.out.println("========"+name+"===>LoginInterceptor preHandle 拦截，尚未登录！");
                return false;
            }
        }   
        System.out.println("========"+name+"===>LoginInterceptor preHandle 没加验证注解放行");
        return true;   
    } 
 
    /** 
     * Handler执行之后，ModelAndView返回之前调用这个方法 
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,  
            Object handler, ModelAndView modelAndView) throws Exception { 
        String name = request.getServletPath().toString();
        System.out.println("========"+name+"===>LoginInterceptor postHandle"); 
    }  
 
    /** 
     * Handler执行完成之后调用这个方法 
     */
    @Override
    public void afterCompletion(HttpServletRequest request,  
            HttpServletResponse response, Object handler, Exception exc)  
            throws Exception { 
        String name = request.getServletPath().toString();
        System.out.println("========"+name+"===>LoginInterceptor afterCompletion"); 
    }  
}
