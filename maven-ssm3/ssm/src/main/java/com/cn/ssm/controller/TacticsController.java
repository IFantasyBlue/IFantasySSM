package com.cn.ssm.controller;

import java.io.PrintWriter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.ssm.entity.Lineup;
import com.cn.ssm.entity.Players_Stats;
import com.cn.ssm.entity.User;
import com.cn.ssm.service.IDTacticsService;
import com.cn.ssm.service.ILineupService;
import com.cn.ssm.service.IOTacticsService;
import com.cn.ssm.service.IPlayers_statsService;
import com.cn.ssm.service.IUserService;
import com.cn.ssm.vo.TacticsVO;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/tactics")
public class TacticsController {
	
	@Resource
    private IOTacticsService oTacticsService;
	@Resource
    private IDTacticsService dTacticsService;
	@Resource
    private IUserService userService;
	@Resource
    private ILineupService lineupService;
	@Resource
    private IPlayers_statsService playerStatService;
	/*
	 * 处理最开始的战术按钮响应
	 * 向数据库查询当前装备的进攻和防守战术以及所有战术
	 */
	@RequestMapping("/initsTactics.json")
    public void toEquipped(HttpServletRequest request,HttpServletResponse response, Model model){
		
		int userId = Integer.parseInt(request.getParameter("id"));
		
		response.setContentType("application/json");
        User user = userService.getById(userId);
        TacticsVO tacticsVO = new TacticsVO();
        /*tacticsVO.setoTactics(oTacticsService.getAll());
        tacticsVO.setdTactics(dTacticsService.getAll());*/
        //tacticsVO.setEquippedOTactics(oTacticsService.getByID(user.getoTactics()));
        //tacticsVO.setEquippeddTactics(dTacticsService.getByID(user.getdTactics()));
        //System.out.println(tacticsVO.getEquippedOTactics().getId());
        //System.out.println(tacticsVO.getEquippeddTactics().getId());
        
        PrintWriter out = null;
        JSONObject json = new JSONObject();
        try {
        	 out = response.getWriter();
        	 
        	 json.put("equippedOTactics", user.getoTactics());
        	 json.put("equippedDTactics", user.getdTactics());
        	 json.put("power", user.getPower());
        	 
        	 out.write(json.toString());
        	 
        }catch(Exception error) {
        	error.toString();
        }finally {
        	out.flush();
        	out.close();
        }
    }
    
    /*
     * 处理防守战术布置按钮相应
     * 对于不同的战术进行不同的战力偏正计算
     */
    @RequestMapping("/dtacticsEquipped.json")
    public void toDTacticsEquipped(HttpServletRequest request, HttpServletResponse response, Model model){

    	int user_Id = Integer.parseInt(request.getParameter("user_id"));
    	int dtactics_Id = Integer.parseInt(request.getParameter("dtactics_id"));
    	
    	User user = userService.getById(user_Id);
    	user.setdTactics(dtactics_Id);
        
    	/*PrintWriter out = null;
        JSONObject json = new JSONObject();
        try {
        	 out = response.getWriter();
        	 
        	 json.put("changepower", user.getPower());
        	 
        	 out.write(json.toString());
        }catch(Exception error) {
        	error.toString();
        }finally {
        	out.flush();
        	out.close();
        }*/
    }
    
    /*
     * 处理进攻战术布置按钮相应
     * 对于不同的战术进行不同的战力偏正计算
     */
    @RequestMapping("/otacticsEquipped.json")
    public void toOTacticsEquipped(HttpServletRequest request,HttpServletResponse response, Model model){

    	int user_Id = Integer.parseInt(request.getParameter("user_id"));
    	int otactics_Id = Integer.parseInt(request.getParameter("otactics_id"));
    	
    	int sword = 0;
    	User user = userService.getById(user_Id);
    	Lineup lineup = lineupService.getById(user_Id);
    	
    	System.out.println(lineup.getPf());
    	
 
    	switch(otactics_Id){
    		case 1:
    			sword = (int)OTacticsNo1(lineup.getSf(), lineup.getPg(), lineup.getSg());
    			break;
    		case 2:
    			sword = (int)OTacticsNo2(lineup.getPf(), lineup.getSf(), lineup.getSg());
    			break;
    		case 3:
    			sword = (int)OTacticsNo3(lineup.getSf(), lineup.getPf(), lineup.getPg());
    			break;
    		case 4:
    			sword = (int)OTacticsNo4(lineup.getPf(), lineup.getC());
    			break;
    		case 5:
    			sword = (int)OTacticsNo5(lineup.getPf(), lineup.getC(), lineup.getPg());
    			break;
    		case 6:
    			sword = (int)OTacticsNo6(lineup.getC(), lineup.getPg(), lineup.getSg());
    			break;
    	}
        
    	user.setoTactics(otactics_Id);
    	user.setPower(user.getPower() + (int)sword);
    	
    	PrintWriter out = null;
        JSONObject json = new JSONObject();
        try {
        	 out = response.getWriter();
        	 
        	 json.put("changepower", user.getPower());
        	 
        	 out.write(json.toString());
        }catch(Exception error) {
        	error.toString();
        }finally {
        	out.flush();
        	out.close();
        }
    }
    
    /*
	 * 进攻战术之外线投射
	 * 战力偏正：(sf（assist）+pg&&sg（shot）*0.5+pg&&sg(threeshot)*1.5)*100
	 */
	double OTacticsNo1(int sf, int pg, int sg) {
		
		double sword_shot = 0;
		double sword_threeshot = 0;
		double sword = 0;
		
		Players_Stats sf_Stat = playerStatService.getById(sf);
		Players_Stats pg_Stat = playerStatService.getById(pg);
		Players_Stats sg_Stat = playerStatService.getById(sg);
		
		sword_shot = (pg_Stat.getShot() + sg_Stat.getShot()) * 0.5;
		sword_threeshot = (pg_Stat.getThreeshot() + sg_Stat.getThreeshot()) * 1.5;
		sword = (sword_shot + sword_threeshot) * 100;
		
		return sword;
	}
	
	/*
	 * 进攻战术之突破分球
	 * 战力偏正：(pf（assist）+sf&&sg（shot))*100
	 */
	double OTacticsNo2(int pf, int sf, int sg) {
		
		double sword = 0;
		
		Players_Stats pf_Stat = playerStatService.getById(pf);
		Players_Stats sf_Stat = playerStatService.getById(sf);
		Players_Stats sg_Stat = playerStatService.getById(sg);
		
		sword = (pf_Stat.getAssist() + sf_Stat.getShot() + sg_Stat.getShot()) * 100;
		
		return sword;
	}
	
	/*
	 * 进攻战术之内线强攻
	 * 战力偏正：(sf（assist）+pf&&g（shot))*100
	 */
	double OTacticsNo3(int sf, int pf, int pg) {
		
		double sword = 0;
		
		Players_Stats sf_Stat = playerStatService.getById(sf);
		Players_Stats pf_Stat = playerStatService.getById(pf);
		Players_Stats pg_Stat = playerStatService.getById(pg);
		
		sword = (sf_Stat.getAssist() + pf_Stat.getShot() + pg_Stat.getShot()) * 100;
		
		return sword;
	}
	
	/*
	 * 进攻战术之双塔战术
	 * 战力偏正：(pf&&C(rebound))*100
	 */
	double OTacticsNo4(int pf, int c) {
		
		double sword = 0;
		
		Players_Stats pf_Stat = playerStatService.getById(pf);
		Players_Stats c_Stat = playerStatService.getById(c);
		
		sword = (pf_Stat.getRebound() + c_Stat.getRebound()) * 100;
		
		return sword;
	}
	
	/*
	 * 进攻战术之普林斯顿
	 * 战力偏正：(pf&&C(assist) + pg(shot) + pg(threeshot) * 1.5)*100
	 */
	double OTacticsNo5(int pf, int c, int pg) {
		
		double sword = 0;
		
		Players_Stats pf_Stat = playerStatService.getById(pf);
		Players_Stats c_Stat = playerStatService.getById(c);
		Players_Stats pg_Stat = playerStatService.getById(pg);
		
		sword = (pf_Stat.getAssist() + c_Stat.getAssist() + pg_Stat.getShot() + pg_Stat.getThreeshot() * 1.5) * 100;
		
		return sword;
	}
	
	/*
	 * 进攻战术之三角进攻
	 * 战力偏正：(pf&&C(rebound))*100
	 */
	double OTacticsNo6(int c, int pg, int sg) {
		
		double sword_assist = 0;
		double sword_point = 0;
		double sword = 0;
		
		Players_Stats c_Stat = playerStatService.getById(c);
		Players_Stats pg_Stat = playerStatService.getById(pg);
		Players_Stats sg_Stat = playerStatService.getById(sg);
		
		sword_assist = c_Stat.getAssist() + pg_Stat.getAssist() + sg_Stat.getAssist();
		sword_point = c_Stat.getPoint() + pg_Stat.getPoint() + sg_Stat.getPoint();
		sword = (sword_assist + sword_point) * 0.6 * 100;
		
		return sword;
	}
	
	/*
	 * 防守战术之外线联防
	 * 战力偏正：(sg&pg(assist) * 0.5 + sg&&pg(point) * 0.125)*100
	 */
	double DTacticsNo1(int sg, int pg) {
		
		double sword_assist = 0;
		double sword_point = 0;
		double sword = 0;
		
		Players_Stats sg_Stat = playerStatService.getById(sg);
		Players_Stats pg_Stat = playerStatService.getById(pg);
		
		sword_assist = (sg_Stat.getAssist() + sg_Stat.getAssist()) * 0.5 ;
		sword_point = (pg_Stat.getPoint() + pg_Stat.getPoint()) * 0.125;
		sword = (sword_assist + sword_point) * 100;
		
		return sword;
	}
	
	/*
	 * 防守战术之外线紧逼
	 * 战力偏正：(sg&&pg(assist) * 0.25 + sg&&pg(point) * 0.2)*100
	 */
	double DTacticsNo2(int sg, int pg) {
		
		double sword_assist = 0;
		double sword_point = 0;
		double sword = 0;
		
		Players_Stats sg_Stat = playerStatService.getById(sg);
		Players_Stats pg_Stat = playerStatService.getById(pg);
		
		sword_assist = (sg_Stat.getAssist() + pg_Stat.getAssist()) * 0.25 ;
		sword_point = (sg_Stat.getPoint() + pg_Stat.getPoint()) * 0.2;
		sword = (sword_assist + sword_point) * 100;
		
		return sword;
	}
	
	/*
	 * 防守战术之内线联防
	 * 战力偏正：(sf&&pf&&c(assist) * 0.5 + sf&&pf&&c(point) * 0.125) * 100
	 */
	double DTacticsNo3(int sf, int pf, int c) {
		
		double sword_assist = 0;
		double sword_point = 0;
		double sword = 0;
		
		Players_Stats sf_Stat = playerStatService.getById(sf);
		Players_Stats pf_Stat = playerStatService.getById(pf);
		Players_Stats c_Stat = playerStatService.getById(c);
		
		sword_assist = (sf_Stat.getAssist() + pf_Stat.getAssist() + pf_Stat.getAssist()) * 0.5 ;
		sword_point = (sf_Stat.getPoint() + pf_Stat.getPoint() + c_Stat.getPoint()) * 0.125;
		sword = (sword_assist + sword_point) * 100;
		
		return sword;
	}
	
	/*
	 * 防守战术之内线紧逼
	 * 战力偏正：(sf&&pf&&c(assist) * 0.25 + sf&&pf&&c(point) * 0.2) * 100
	 */
	double DTacticsNo4(int sf, int pf, int c) {
		
		double sword_assist = 0;
		double sword_point = 0;
		double sword = 0;
		
		Players_Stats sf_Stat = playerStatService.getById(sf);
		Players_Stats pf_Stat = playerStatService.getById(pf);
		Players_Stats c_Stat = playerStatService.getById(c);
		
		sword_assist = (sf_Stat.getAssist() + pf_Stat.getAssist() + pf_Stat.getAssist()) * 0.25 ;
		sword_point = (sf_Stat.getPoint() + pf_Stat.getPoint() + c_Stat.getPoint()) * 0.2;
		sword = (sword_assist + sword_point) * 100;
		
		return sword;
	}
	
	/*
	 * 防守战术之全场盯人
	 * 战力偏正：(sf&&pf&&c&&sg&&pg(assist) * 0.2 + sf&&pf&&c&&sg&&pg(point) * 0.1) * 100
	 */
	double DTacticsNo5(int sf, int pf, int c, int sg, int pg) {
		
		double sword_assist = 0;
		double sword_point = 0;
		double sword = 0;
		
		Players_Stats sf_Stat = playerStatService.getById(sf);
		Players_Stats pf_Stat = playerStatService.getById(pf);
		Players_Stats c_Stat = playerStatService.getById(c);
		Players_Stats sg_Stat = playerStatService.getById(sg);
		Players_Stats pg_Stat = playerStatService.getById(pg);
		
		sword_assist = (sf_Stat.getAssist() + pf_Stat.getAssist() + pf_Stat.getAssist() + sg_Stat.getAssist() + pg_Stat.getAssist()) * 0.2 ;
		sword_point = (sf_Stat.getPoint() + pf_Stat.getPoint() + c_Stat.getPoint() + sg_Stat.getPoint() + pg_Stat.getPoint()) * 0.1;
		sword = (sword_assist + sword_point) * 100;
		
		return sword;
	}
}
