package com.cn.ssm.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.ssm.common.LoginRequired;
import com.cn.ssm.entity.User;
import com.cn.ssm.service.UserServiceImpl;


@Controller

@RequestMapping("/activity")
public class ActivityController {
	
	@Resource
	private UserServiceImpl userService;
	
	@LoginRequired
	@RequestMapping("/getRank.json")
	public void getRank(HttpServletRequest request,HttpServletResponse response, Model model){
		String id = request.getParameter("id");
		
		response.setContentType("application/json");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		
		List<User> list = userService.getPowerTop20(); 
		
		
		
		try{
			out = response.getWriter();
			json.put("PowerTop20", list);
			out.write(json.toString());
			
		}catch(Exception e){
			e.toString();
		}finally{
			out.flush();
			out.close();
		}
		
	}
}
