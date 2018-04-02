package com.cn.ssm.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.ssm.entity.Package;


@Controller// 注册为spring容器bean
@RequestMapping("/package")//请求映射
public class PackageController {
	//@Resource //注入对象
	@RequestMapping("/package")
	public ModelAndView show(HttpServletRequest request, HttpServletResponse response)  throws Exception {
        ModelAndView mav = new ModelAndView("package");
        return mav;
    }
}
