package com.cn.ssm.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONObject;
import com.cn.ssm.entity.User;


@Controller
@RequestMapping("/test")
public class controller {

	 @RequestMapping("/addUser.do")
	 public void addUserFromClient(HttpServletRequest request, HttpServletResponse response, Model model, @RequestParam("userName") String userName,@RequestParam("password") String password){
		 /*      第二种获取android客户端传递值的方式：
		         String userName=request.getParameter("userName");
		         String password=request.getParameter("password");*/
		         User user = new User();
		         user.setUserName(userName);
		         user.setPassword(password);

		         response.setContentType("application/json");
		         PrintWriter out = null;
		         JSONObject json = new JSONObject();

		         try{
		             out = response.getWriter();

		             if(user.getUserName().equals("yl")){
		            	 if(user.getPassword().equals("123"))
		                 json.put("status", 1);
		                 out.write(json.toString());
		             }else{
		                 json.put("status", 0);
		                 out.write(json.toString());
		             }
		         } catch(Exception e){
		             e.printStackTrace();
		             json.put("status", -1);
		             out.write(json.toString());
		         }finally{
		             out.flush();
		             out.close();
		         }

		     }
}
