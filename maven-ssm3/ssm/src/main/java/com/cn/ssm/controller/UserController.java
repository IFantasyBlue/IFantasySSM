package com.cn.ssm.controller;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.cn.ssm.common.LoginRequired;
import com.cn.ssm.entity.APPToken;
import com.cn.ssm.entity.BelongTeam;
import com.cn.ssm.entity.Lineup;
import com.cn.ssm.entity.Setting;
import com.cn.ssm.entity.TeamMembers;
import com.cn.ssm.entity.User;
import com.cn.ssm.entity.User_Info;
import com.cn.ssm.service.APPTokenService;
import com.cn.ssm.service.SettingServiceImpl;
import com.cn.ssm.service.TeamMembersServiceImpl;
import com.cn.ssm.service.UserServiceImpl;
import com.cn.ssm.service.User_InfoServiceImpl;
import com.cn.ssm.service.BelongTeamServiceImpl;
import com.cn.ssm.service.LineupServiceImpl;


@Controller
@RequestMapping("/user")
public class UserController {
	@Resource
	private UserServiceImpl userService;
	@Resource
	private SettingServiceImpl settingService;
	@Resource
	private User_InfoServiceImpl userInfoService;
	@Resource
	private BelongTeamServiceImpl btmService;
	@Resource
	private LineupServiceImpl lineupService;
	@Resource
	private DataSourceTransactionManager txManager;
	@Resource
	private TeamMembersServiceImpl tmbService;
	@Resource
	private User_InfoServiceImpl userinfoServce;
	@Resource
	private APPTokenService tokenService; 
	
	
	@RequestMapping("/sendAKey.json")
	public void sendAKey(HttpServletRequest request,HttpServletResponse response, Model model){
		String mobile = request.getParameter("phone");
		
		SendSmsResponse ssr =  userService.AliMessagerSend(mobile);
		response.setContentType("application/json");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = txManager.getTransaction(def);
		
		try{
			out = response.getWriter();
			
			if(ssr.getCode() != null && ssr.getCode().equals("OK")){
				json.put("status",1);
				json.put("ssr", ssr.getCode());
				out.write(json.toString());
			}else{
				json.put("status", 0);
				json.put("ssr", ssr.getCode());
				out.write(json.toString());
			}
		}catch(Exception e){
			e.toString();
			txManager.rollback(status);
			json.put("status", -1);
			json.put("ssr",null);
			out.write(json.toString());
		}finally{
			txManager.commit(status);
			out.flush();
			out.close();
		}
		
	}
	
	@RequestMapping("/userLogin.json")
	public void userlogin(HttpServletRequest request,HttpServletResponse response, Model model){
		String mobile = request.getParameter("phone");
		int akey = Integer.parseInt(request.getParameter("akey"));   
		
		response.setContentType("application/json");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		APPToken token = null;
		User user = null;
		User_Info userinfo = null;
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = txManager.getTransaction(def);
		
		try{
			out = response.getWriter();
			
			if(userService.checkAkey(mobile, akey)){
				
				System.out.println("yanzhengzhengque!");
				
				user = userService.getByPhone(mobile);
				System.out.println("getuser");

				
				
				if(user != null){
					userinfo = userInfoService.getById(user.getId());
					token = new APPToken();
					token.setId(user.getPhone());
					token.setExtime(System.currentTimeMillis());
					if(tokenService.getById(user.getPhone())!=null){
						tokenService.updateTime(token);
					}else{
						tokenService.insertToken(token);
					}
					json.put("status",1);
					json.put("token", token);
					json.put("user", user);
					json.put("userinfo", userinfo);
					json.put("akey", true);
					out.write(json.toString());
				}else{
					json.put("status", 0);
					json.put("token", null);
					json.put("user", null);
					json.put("userinfo", null);
					json.put("akey", true);
					out.write(json.toString());
				}
			}else{
				json.put("status", 0);
				json.put("token", null);
				json.put("user", null);
				json.put("userinfo", null);
				json.put("akey", false);
				out.write(json.toString());
			}
			txManager.commit(status);
		}catch(Exception e){
			System.out.println(e.toString());;
			txManager.rollback(status);
			json.put("status", -1);
			json.put("token", null);
			json.put("user",null);
			json.put("userinfo", null);
			json.put("akey", null);
			out.write(json.toString());
		}finally{
			
			out.flush();
			out.close();
		}
		
	}
	
	@RequestMapping("/userSign.json")
	public void userSign(HttpServletRequest request,HttpServletResponse response,Model model){
		String phone = request.getParameter("phone");
		String name = request.getParameter("name");
		int team = Integer.parseInt(request.getParameter("team_id"));
		
		User user = new User();
		
		int id = userService.getUserNumber()+1;
		System.out.println(id);
		
		user.setId(id);
		user.setName(name);
		user.setPhone(phone);
		user.setUserinfo(id);
		user.setSetting(id);
		user.setPower(0);
		user.setMoney(0);
		user.setFriendsNum(0);
		user.setPackageNum(id);
		user.setoTactics(-1);
		user.setdTactics(-1);
		
		Setting setting = new Setting();
		setting.setId(id);
		setting.setMusic(true);
		setting.setNoyice(true);
		setting.setQuality(1);
		
		User_Info userinfo = new User_Info();
		userinfo.setUserId(id);
		userinfo.setBelongteam(id);
		userinfo.setVip(0);
		userinfo.setLevel(1);
		
		BelongTeam btm = new BelongTeam();
		btm.setId(id);
		btm.setPlayersNum(5);
		btm.setMembers(id);
		btm.setLineup(id);
		
		
		
	     int[][] teams = new int[][]{
	    		 {63,156,198,229,393},
	    		 {13,201,608,312,116},
	    		 {108,330,415,191,70},
	    		 {112,288,183,507,597},
	    		 {227,572,473,714,456},
	    		 {21,236,300,269,152},
	    		 {394,323,548,580,434},
	    		 {74,163,380,404,163},
	    		 {1042,366,319,382,371},
	    		 {88,441,617,614,503},
	    		 {140,521,398,546,510},
	    		 {1310,815,283,1716,1727},
	    		 {218,436,1654,691,743},
	    		 {87,166,837,1057,807},
	    		 {334,247,270,385,195},
	    		 {579,654,1428,1319,1119},
	    		 {289,324,154,570,627},
	    		 {626,768,842,881,1027},
	    		 {1763,1172,1103,1416,1445},
	    		 {500,188,653,812,365},
	    		 {1775,1726,1162,1753,1720},
	    		 {594,513,487,647,498},
	    		 {376,431,352,616,488},
	    		 {688,957,226,461,660},
	    		 {284,3,342,348,290},
	    		 {449,401,964,297,802},
	    		 {607,493,145,636,1298},
	    		 {666,590,1370,1284,1233},
	    		 {179,313,450,675,589},
	    		 {220,336,205,96,255}
	    		 };
		
	    TeamMembers tmb1 = new TeamMembers();
		tmb1.setUserId(id);
		tmb1.setPlayerId(teams[team-1][0]);
		tmb1.setPosition("SG");
		tmb1.setStatus(true);
	    TeamMembers tmb2 = new TeamMembers();
	    tmb2.setUserId(id);
		tmb2.setPlayerId(teams[team-1][1]);
		tmb2.setPosition("SF");
		tmb2.setStatus(true);
		TeamMembers tmb3 = new TeamMembers();
		tmb3.setUserId(id);
		tmb3.setPlayerId(teams[team-1][2]);
		tmb3.setPosition("C");
		tmb3.setStatus(true);
		TeamMembers tmb4 = new TeamMembers();
		tmb4.setUserId(id);
		tmb4.setPlayerId(teams[team-1][3]);
		tmb4.setPosition("PG");
		tmb4.setStatus(true);
		TeamMembers tmb5 = new TeamMembers();
		tmb5.setUserId(id);
		tmb5.setPlayerId(teams[team-1][4]);
		tmb5.setPosition("PF");
		tmb5.setStatus(true);
		
		Lineup lineup = new Lineup();
		lineup.setId(id);
		lineup.setSg(tmb1.getPlayerId());
		lineup.setSf(tmb2.getPlayerId());
		lineup.setC(tmb3.getPlayerId());
		lineup.setPg(tmb4.getPlayerId());
		lineup.setPf(tmb5.getPlayerId());
		
		response.setContentType("application/json");
		PrintWriter out = null;
		JSONObject json = new JSONObject();
		APPToken token = null;
		
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED);
		
		TransactionStatus status = txManager.getTransaction(def);
		
		try{
			out = response.getWriter();
			if(	tmbService.insertTeamMembers(tmb1)>0
				&&tmbService.insertTeamMembers(tmb2)>0
				&&tmbService.insertTeamMembers(tmb3)>0
				&&tmbService.insertTeamMembers(tmb4)>0
				&&tmbService.insertTeamMembers(tmb5)>0
				&&settingService.insertSetting(setting)>0
				&&lineupService.insertLineUp(lineup)>0
				&&btmService.insertNewBelongTeam(btm)>0
				&&userInfoService.insertNewUserInfo(userinfo)>0
				&&userService.insertNewUser(user)>0){
				
				token = new APPToken();
				token.setId(user.getPhone());
				token.setExtime(System.currentTimeMillis());
				if(tokenService.getById(user.getPhone())!=null){
					tokenService.updateTime(token);
				}else{
					tokenService.insertToken(token);
				}
				json.put("status",1);
				json.put("user", user);
				json.put("token", token);
				json.put("userinfo", userinfo);
				json.put("btm", btm);
				out.write(json.toString());
			}else{
				json.put("status",0);
				json.put("user", null);
				json.put("token", token);
				json.put("userinfo", null);
				json.put("btm", null);
				out.write(json.toString());
			}
			
			
		}catch(Exception e){
			System.out.println(e.toString());
			txManager.rollback(status);
			json.put("status", -1);
			json.put("user",null);
			json.put("token", null);
			json.put("userinfo", null);
			json.put("btm", null);
			out.write(json.toString());
		}finally{
			txManager.commit(status);
			out.flush();
			out.close();
		}
		
	}
	
	
}
