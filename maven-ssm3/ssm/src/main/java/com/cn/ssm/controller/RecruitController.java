package com.cn.ssm.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cn.ssm.dao.PlayersMapper;
import com.cn.ssm.dao.PlayersInfoMapper;
import com.cn.ssm.dao.UserMapper;
import com.cn.ssm.dao.TeamMembersMapper;
import com.cn.ssm.entity.*;
import com.cn.ssm.service.IRecruitDirectlyService;
import com.cn.ssm.service.IRecruitLuckilyService;
import com.cn.ssm.service.IUserService;

@Controller
@RequestMapping("/recruit")
public class RecruitController {
	@Resource
    private IRecruitLuckilyService IRLuckService;
	@Resource
    private IRecruitDirectlyService IRDirectService;
	@Resource
    private IUserService userService;
	@Resource
	public UserMapper userMapper;
	@Resource
	public PlayersMapper playerMapper;
	@Resource
	public PlayersInfoMapper playerInfoMapper;
	@Resource
	public TeamMembersMapper teamMembersMapper;
	
	@RequestMapping(value="show",produces = "text/html;charset=utf-8")
    public void show(HttpServletRequest request,HttpServletResponse response, Model model)throws Exception{
		int userId = Integer.parseInt(request.getParameter("id"));
		//int userId=1;
		User user = userService.getById(userId); 
		List<Players> playerlist = playerMapper.selectDirPlayers();
		response.setContentType("application/json");
		PrintWriter out = null;
		JSONArray json = new JSONArray();
		try {
			out = response.getWriter();
			json.add(0,user);
			for(int i=0;i<8;i++) {
				json.add(playerlist.get(i));
			}
			out.write(json.toString());
			}
			catch(Exception e) {
				e.toString();
			}finally{
			out.flush();
			out.close();
		}
    }
	@RequestMapping("/playerInfo")
    public void playerInfoPost(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		int userId = Integer.parseInt(request.getParameter("id"));
		//int userId=1;
		String name= request.getParameter("name");
		//String name="kuli";
		Players player =playerMapper.selectByName(name);
		int playerId=player.getId();
		PlayersInfo playerInfo= playerInfoMapper.selectByPrimaryKey(playerId);
		response.setContentType("application/json");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		try {
			out = response.getWriter();
			json.put("playerInfo", playerInfo);
			out.write(json.toString());
		}catch(Exception e) {
			e.toString();	
		}finally{
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/addPlayer")
    public void playerAdd(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		int userId = Integer.parseInt(request.getParameter("id"));
		//int userId=1;
		User user = userService.getById(userId);	
	    String playerName=request.getParameter("playerName");
		//String playerName="yh";
	    Players player =playerMapper.selectByName(playerName);
	    int playerId=player.getId();
		String position=player.getPosition();
		List<String> res=new ArrayList<String>();
		TeamMembers teamMember=new TeamMembers();
		teamMember=teamMembersMapper.selectByUserID_playerid(userId, playerId);
		if(teamMember==null) {
			TeamMembers  newMember =new TeamMembers();
			newMember.setPlayerId(playerId);
			newMember.setPosition(position);
			newMember.setUserId(userId);
			teamMembersMapper.insert(newMember);
			res.add("success!");			
		}else {
		
			res.add("This player has exist!");
		}
		response.setContentType("application/json");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		try {
			out = response.getWriter();
			json.put("res", res.get(0));
			json.put("user", user);
			out.write(json.toString());
		}catch(Exception e) {
			e.toString();	
		}finally{
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/DirectPos")
    public void posShow(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		int userId = Integer.parseInt(request.getParameter("id"));
		//int userId=1;
		String position= request.getParameter("position");
		List<Players> playerlist =new ArrayList<Players>();
		if(position.equals("ALL")) {
			playerlist = playerMapper.selectDirPlayers();
		}else {
			playerlist = IRDirectService.recruitPos(userId, position);
		}
		response.setContentType("application/json");
		PrintWriter out = null;
		JSONArray json = new JSONArray();
		try {
			out = response.getWriter();
			for(int i=0;i<8;i++) {
				json.add(playerlist.get(i));
			}
			out.write(json.toString());
		}catch(Exception e) {
			e.toString();
			
		}finally{
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/DirectScore")
    public void scoreShow(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		int userId = Integer.parseInt(request.getParameter("id"));
		//int userId=1;
		List<Players> playerList = IRDirectService.recruitScore(userId);
		response.setContentType("application/json");
		PrintWriter out = null;
		JSONArray json = new JSONArray();
		try {
			out = response.getWriter();
			for(int i=0;i<8;i++) {
				json.add(playerList.get(i));
			}
			out.write(json.toString());
		}catch(Exception e) {
			e.toString();
			
		}finally{
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/DirectSalary")
    public void salaryShow(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		int userId = Integer.parseInt(request.getParameter("id"));
		//int userId=1;

		List<Players> playerList = IRDirectService.recruitSalary(userId);
		response.setContentType("application/json");
		PrintWriter out = null;
		JSONArray json = new JSONArray();
		try {
			out = response.getWriter();
			for(int i=0;i<8;i++) {
				json.add(playerList.get(i));
			}
			out.write(json.toString());
		}catch(Exception e) {
			e.toString();
			
		}finally{
			out.flush();
			out.close();
		}
	}
	
	@RequestMapping("/luckilyFree")
    public void freeShow(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		//int userId = Integer.parseInt(request.getParameter("id"));
		
		response.setContentType("application/json");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		Goods goods = new Goods();		
		int userId=1;
		User user = userService.getById(userId);
        int flag=user.getRecruited(); //鍒ゆ柇鏄惁鏈夊厤璐规嫑鍕熸満浼�
        System.out.println(flag+"   鏄惁鍏嶈垂鎷涘嫙杩�");
        //鍙互鍏嶈垂鍒欒皟鏂规硶锛屽苟鏇存柊user鐨凴ecruited鍜宼ime瀛楁
        if(flag==0) {
        	goods = IRLuckService.recruitFree(userId);
        	user=userService.updateUserRecruit(userId);
        	userMapper.updateRecruited(1,userId);
        	System.out.println(user.getRecruited()+"宸茬粡鎷涘嫙");
        }
        
        //鏃犲厤璐规満浼氾紝鍒欏垽鏂槸鍚﹁兘鑾峰彇鍏嶈垂鏈轰細
        else {
        	System.out.println("鍏嶈垂鎷涘嫙杩�");
        	Date date = new Date();
    		long time=date.getTime();
    		double currentTime=user.getRecruittime();
    		//int days = (int) ((currentTime - time)/(1000 * 60 * 60 * 24));
    		int days = (int) ((time - currentTime )/(1000 * 60)); //鍒嗛挓宸� 
 
    		//鍙幏鍙栧厤璐规満浼氾紝鍒欒皟鏂规硶锛屽苟鏇存柊user鐨凴ecruited鍜宼ime瀛楁
    		if(days >= 1) {
    				userMapper.updateRecruited(0,userId);
    				System.out.println(user.getRecruited()+"***搴旇杈撳嚭0**");
    				goods = IRLuckService.recruitFree(userId);
    			 	user=userService.updateUserRecruit(userId);
    			 	userMapper.updateRecruited(1,userId);	
    			 	System.out.println("閲嶆柊鏈変簡鍏嶈垂鏈轰細锛屽凡缁忔嫑鍕�");
    		}
    		//涓嶅彲鑾峰彇鍏嶈垂鏈轰細锛屽垯杩斿洖瀛楁缁欏墠鍙�
    		else {
    			String error = "涓嶈兘鍏嶈垂鎷涘嫙";
    			json.put("error", error);
    			System.out.println("涓嶈兘鍏嶈垂鎷涘嫙");
    		}
        }
        
        user = userService.getById(userId);
       
		try {
			out = response.getWriter();
			json.put("goods",goods);
			json.put("user", user);
			out.write(json.toString());
		}catch(Exception e) {
			e.toString();
			
		}finally{
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/luckilyOnce")
    public void onceShow(HttpServletRequest request,HttpServletResponse response, Model model)throws Exception{
		//int userId = Integer.parseInt(request.getParameter("id"));
		int userId=1;
		User user = userService.getById(userId);
		Goods goods=IRLuckService.recruitOnce(userId);
		response.setContentType("application/json");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		try {
			out = response.getWriter();
			json.put("goods",goods);
			json.put("user",user);
			out.write(json.toString());
		}catch(Exception e) {
			e.toString();
			
		}finally{
			out.flush();
			out.close();
		}
	}
	@RequestMapping("/luckilyFive")
    public void fiveShow(HttpServletRequest request,HttpServletResponse response,Model model)throws Exception{
		//int userId = Integer.parseInt(request.getParameter("id"));
		int userId=1;
		User user = userService.getById(userId);
        List<Goods> goodslist = new ArrayList<Goods>();
        for(int i=0;i<4;i++) {
        	Goods goods = IRLuckService.recruitFree(userId);
        	goodslist.add(goods);     
        }
        Goods goods = IRLuckService.recruitFive(userId);
        goodslist.add(goods);
        response.setContentType("application/json");
        PrintWriter out = null;
        JSONObject json = new JSONObject();
        try {
        	out = response.getWriter();
        	json.put("user",user);
        	json.put("goods1", goodslist.get(0));
        	json.put("goods2", goodslist.get(1));
        	json.put("goods3", goodslist.get(2));
        	json.put("goods4", goodslist.get(3));
        	json.put("goods5", goodslist.get(4));
        	out.write(json.toString());
        }
        catch(Exception e) {
			e.toString();
			
		}finally{
			out.flush();
			out.close();
		}    
	}
}




