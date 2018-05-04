package com.cn.ssm.controller;

import java.io.PrintWriter;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.ssm.entity.DTactics;
import com.cn.ssm.entity.Lineup;
import com.cn.ssm.entity.OTactics;
import com.cn.ssm.entity.Players_Stats;
import com.cn.ssm.entity.User;
import com.cn.ssm.service.IDTacticsService;
import com.cn.ssm.service.ILineupService;
import com.cn.ssm.service.IOTacticsService;
import com.cn.ssm.service.IPlayerStatService;
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
    private IPlayerStatService playerStatService;
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
    
    
    double OTacticsNo4(int pf, int c) {
		
		double sword = 0;
		
		Players_Stats pf_Stat = playerStatService.getById(pf);
		Players_Stats c_Stat = playerStatService.getById(c);
		
		sword = (pf_Stat.getRebound() + c_Stat.getRebound()) * 100;
		
		return sword;
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
    	
    	TacticsMethods tacticsMethods = new TacticsMethods();
 
    	switch(otactics_Id){
    		case 1:
    			sword = (int)tacticsMethods.OTacticsNo1(lineup.getSf(), lineup.getPg(), lineup.getSg());
    			break;
    		case 2:
    			sword = (int)tacticsMethods.OTacticsNo2(lineup.getPf(), lineup.getSf(), lineup.getSg());
    			break;
    		case 3:
    			sword = (int)tacticsMethods.OTacticsNo3(lineup.getSf(), lineup.getPf(), lineup.getPg());
    			break;
    		case 4:
    			sword = (int)OTacticsNo4(lineup.getPf(), lineup.getC());
    			break;
    		case 5:
    			sword = (int)tacticsMethods.OTacticsNo5(lineup.getPf(), lineup.getC(), lineup.getPg());
    			break;
    		case 6:
    			sword = (int)tacticsMethods.OTacticsNo6(lineup.getC(), lineup.getPg(), lineup.getSg());
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
}
