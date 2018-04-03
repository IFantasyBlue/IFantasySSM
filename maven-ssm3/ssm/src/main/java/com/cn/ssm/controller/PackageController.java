package com.cn.ssm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.ssm.entity.Package;
import com.cn.ssm.entity.User;
import com.cn.ssm.service.IUserService;
import com.cn.ssm.dao.PackageMapper;

@Controller// 注册为spring容器bean
@RequestMapping("/")//请求映射
public class PackageController {
    	@Resource
    private IUserService userService;
   
    private User user;
    	@Resource
    private PackageMapper packagemapper;
    	
    private List<Package> records=null;
    
	//@Resource //注入对象
	@RequestMapping(value="package")
	public ModelAndView show(HttpServletRequest request, HttpServletResponse response)  throws Exception {
		//int userID= Integer.parseInt(request.getParameter("id"));
		//user=userService.getById(userID);
		
		records=packagemapper.list(0);//user.getPackageNum());
		
        	ModelAndView mav = new ModelAndView("package");
        	mav.addObject(records);
        return mav;
    }
}
