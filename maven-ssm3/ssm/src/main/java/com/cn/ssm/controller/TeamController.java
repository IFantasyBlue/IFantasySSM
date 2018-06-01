package com.cn.ssm.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.json.JsonArray;
import javax.json.JsonNumber;
import javax.json.JsonObject;
import javax.json.JsonString;
import javax.json.JsonValue;
import javax.json.JsonValue.ValueType;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.processors.JsonValueProcessor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.ssm.entity.*;
import com.cn.ssm.service.IBelongTeamService;
import com.cn.ssm.service.ILineupService;
import com.cn.ssm.service.IPlayersInfoService;
import com.cn.ssm.service.IPlayersService;
import com.cn.ssm.service.IPlayers_statsService;
import com.cn.ssm.service.ITeamMembersService;
import com.cn.ssm.service.IUserService;
import com.cn.ssm.service.IUser_InfoService;
import com.cn.ssm.utils.JsonDateValueProcessor;
import com.cn.ssm.vo.MembersVO;
import com.cn.ssm.vo.MessageVO;
import com.cn.ssm.vo.PlayerVO;
import com.cn.ssm.vo.TeamVO;

@Controller
@RequestMapping("/team")
public class TeamController {

    @Resource
    private IBelongTeamService belongTeamService;
    @Resource
    private ILineupService lineupService;
    @Resource
    private ITeamMembersService teamMembersService;
    @Resource
    private IUserService userService;
    @Resource
    private IUser_InfoService user_InfoService;
    @Resource
    private IPlayersService playersService;
    @Resource
    private IPlayersInfoService playersInfoService;
    @Resource
    private IPlayers_statsService iPlayers_statsService;
    
    /*显示用户球队的信息
     * 包括首发球员与替补球员，球队战斗力等信息
     */
    @RequestMapping("/teamShow.json")
    public void toIndex(HttpServletRequest request,HttpServletResponse response, Model model){
        int userId = Integer.parseInt(request.getParameter("user_id"));

        User user = userService.getById(userId);

        
        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        BelongTeam belongTeam=belongTeamService.getById(user_Info.getBelongteam());
        Lineup lineup=lineupService.getById(belongTeam.getLineup());
        List<TeamMembers> list=teamMembersService.getById(user_Info.getUserId());
        List<MembersVO> list2 = new ArrayList<MembersVO>();
        for(int i=0;i<list.size();i++){
        	Players players = new Players();
        	players = playersService.getById(list.get(i).getPlayerId());
        	MembersVO membersVO = new MembersVO();
        	membersVO.setTeamMembers(list.get(i));
        	membersVO.setName(players.getName());
        	list2.add(membersVO);
        }
        TeamVO teamVo=new TeamVO();
        teamVo.setMoney(user.getMoney());
        teamVo.setPower(user.getPower());
        teamVo.setList(list2);
        JSONObject json = JSONObject.fromObject(teamVo);
        json.put("username", user.getName());
        response.setContentType("application/json");
        PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.flush();
            out.close();
		}
//     model.addAttribute("user", user);
//    model.addAttribute("user_info", user_Info);
//  model.addAttribute("belongteam", belongTeam);       model.addAttribute("lineup", lineup);
//   model.addAttribute("list", list);
//   
//        JSONObject json = JSONObject.fromObject(teamVo);
        
        
    }
    /*按照位置显示球队成员
     * 按照位置是C（中锋）
     */
    @RequestMapping("/teamShowC.json")
    public void toTeamC(HttpServletRequest request,Model model,HttpServletResponse response){
        int user_id = Integer.parseInt(request.getParameter("user_id"));

        User user = userService.getById(user_id);

        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        BelongTeam belongTeam=belongTeamService.getById(user_Info.getBelongteam());
        Lineup lineup=lineupService.getById(belongTeam.getLineup());
        List<TeamMembers> list=teamMembersService.selectByKey3(user_id, "C");
        List<MembersVO> list2 = new ArrayList<MembersVO>();
        for(int i=0;i<list.size();i++){
        	Players players = new Players();
        	players = playersService.getById(list.get(i).getPlayerId());
        	MembersVO membersVO = new MembersVO();
        	membersVO.setTeamMembers(list.get(i));
        	membersVO.setName(players.getName());
        	list2.add(membersVO);
        }
        TeamVO teamVo=new TeamVO();
        teamVo.setMoney(user.getMoney());
        teamVo.setPower(user.getPower());
        teamVo.setList(list2);
        JSONObject json = JSONObject.fromObject(teamVo);
        response.setContentType("application/json");
        PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.flush();
            out.close();
		}
//     model.addAttribute("user", user);
//    model.addAttribute("user_info", user_Info);
//  model.addAttribute("belongteam", belongTeam);       model.addAttribute("lineup", lineup);
//   model.addAttribute("list", list);
   
//        JSONObject json = JSONObject.fromObject(teamVo);
        
        
    }
    /*按照位置显示球队成员
     * 按照位置是PF（大前锋）
     */
    @RequestMapping("/teamShowPF.json")
    public void toTeamPF(HttpServletRequest request,Model model,HttpServletResponse response){
        int user_id = Integer.parseInt(request.getParameter("user_id"));

        User user = userService.getById(user_id);

        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        BelongTeam belongTeam=belongTeamService.getById(user_Info.getBelongteam());
        Lineup lineup=lineupService.getById(belongTeam.getLineup());
        List<TeamMembers> list=teamMembersService.selectByKey3(user_id, "PF");
        List<MembersVO> list2 = new ArrayList<MembersVO>();
        for(int i=0;i<list.size();i++){
        	Players players = new Players();
        	players = playersService.getById(list.get(i).getPlayerId());
        	MembersVO membersVO = new MembersVO();
        	membersVO.setTeamMembers(list.get(i));
        	membersVO.setName(players.getName());
        	list2.add(membersVO);
        }
        TeamVO teamVo=new TeamVO();
        teamVo.setMoney(user.getMoney());
        teamVo.setPower(user.getPower());
        teamVo.setList(list2);
        JSONObject json = JSONObject.fromObject(teamVo);
        response.setContentType("application/json");
        PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.flush();
            out.close();
		}
//     model.addAttribute("user", user);
//    model.addAttribute("user_info", user_Info);
//  model.addAttribute("belongteam", belongTeam);       model.addAttribute("lineup", lineup);
//   model.addAttribute("list", list);
   
//        JSONObject json = JSONObject.fromObject(teamVo);
        
        
    }
    /*按照位置显示球队成员
     * 按照位置是PG（控球后卫）
     */
    @RequestMapping("/teamShowPG.json")
    public void toTeamPG(HttpServletRequest request,Model model,HttpServletResponse response){
        int user_id = Integer.parseInt(request.getParameter("user_id"));

        User user = userService.getById(user_id);

        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        BelongTeam belongTeam=belongTeamService.getById(user_Info.getBelongteam());
        Lineup lineup=lineupService.getById(belongTeam.getLineup());
        List<TeamMembers> list=teamMembersService.selectByKey3(user_id, "PG");
        List<MembersVO> list2 = new ArrayList<MembersVO>();
        for(int i=0;i<list.size();i++){
        	Players players = new Players();
        	players = playersService.getById(list.get(i).getPlayerId());
        	MembersVO membersVO = new MembersVO();
        	membersVO.setTeamMembers(list.get(i));
        	membersVO.setName(players.getName());
        	list2.add(membersVO);
        }
        TeamVO teamVo=new TeamVO();
        teamVo.setMoney(user.getMoney());
        teamVo.setPower(user.getPower());
        teamVo.setList(list2);
        JSONObject json = JSONObject.fromObject(teamVo);
        response.setContentType("application/json");
        PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.flush();
            out.close();
		}
//     model.addAttribute("user", user);
//    model.addAttribute("user_info", user_Info);
//  model.addAttribute("belongteam", belongTeam);       model.addAttribute("lineup", lineup);
//   model.addAttribute("list", list);
   
//        JSONObject json = JSONObject.fromObject(teamVo);
        
        
    }
    /*按照位置显示球队成员
     * 按照位置是SG（得分后卫）
     */
    @RequestMapping("/teamShowSG.json")
    public void toTeamSG(HttpServletRequest request,Model model,HttpServletResponse response){
        int user_id = Integer.parseInt(request.getParameter("user_id"));

        User user = userService.getById(user_id);

        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        BelongTeam belongTeam=belongTeamService.getById(user_Info.getBelongteam());
        Lineup lineup=lineupService.getById(belongTeam.getLineup());
        List<TeamMembers> list=teamMembersService.selectByKey3(user_id, "SG");
        List<MembersVO> list2 = new ArrayList<MembersVO>();
        for(int i=0;i<list.size();i++){
        	Players players = new Players();
        	players = playersService.getById(list.get(i).getPlayerId());
        	MembersVO membersVO = new MembersVO();
        	membersVO.setTeamMembers(list.get(i));
        	membersVO.setName(players.getName());
        	list2.add(membersVO);
        }
        TeamVO teamVo=new TeamVO();
        teamVo.setMoney(user.getMoney());
        teamVo.setPower(user.getPower());
        teamVo.setList(list2);
        JSONObject json = JSONObject.fromObject(teamVo);
        response.setContentType("application/json");
        PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.flush();
            out.close();
		}
//     model.addAttribute("user", user);
//    model.addAttribute("user_info", user_Info);
//  model.addAttribute("belongteam", belongTeam);       model.addAttribute("lineup", lineup);
//   model.addAttribute("list", list);
   
//        JSONObject json = JSONObject.fromObject(teamVo);
        
        
    }
    /*按照位置显示球队成员
     * 按照位置是SF（小前锋）
     */
    @RequestMapping("/teamShowSF.json")
    public void toTeamSF(HttpServletRequest request,Model model,HttpServletResponse response){
        int user_id = Integer.parseInt(request.getParameter("user_id"));

        User user = userService.getById(user_id);

        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        BelongTeam belongTeam=belongTeamService.getById(user_Info.getBelongteam());
        Lineup lineup=lineupService.getById(belongTeam.getLineup());
        List<TeamMembers> list=teamMembersService.selectByKey3(user_id, "SF");
        List<MembersVO> list2 = new ArrayList<MembersVO>();
        for(int i=0;i<list.size();i++){
        	Players players = new Players();
        	players = playersService.getById(list.get(i).getPlayerId());
        	MembersVO membersVO = new MembersVO();
        	membersVO.setTeamMembers(list.get(i));
        	membersVO.setName(players.getName());
        	list2.add(membersVO);
        }
        TeamVO teamVo=new TeamVO();
        teamVo.setMoney(user.getMoney());
        teamVo.setPower(user.getPower());
        teamVo.setList(list2);
        JSONObject json = JSONObject.fromObject(teamVo);
        response.setContentType("application/json");
        PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.flush();
            out.close();
		}
//     model.addAttribute("user", user);
//    model.addAttribute("user_info", user_Info);
//  model.addAttribute("belongteam", belongTeam);       model.addAttribute("lineup", lineup);
//   model.addAttribute("list", list);
   
//        JSONObject json = JSONObject.fromObject(teamVo);
        
        
    }
    
    /*给出指定球员ID显示球员的基本信息
     * 根据球员id到数据库查询球员信息
     */
    @RequestMapping("/playerShow.json")
    public void toPlayer(HttpServletRequest request,HttpServletResponse response,Model model){
        int player_id = Integer.parseInt(request.getParameter("player_id"));
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class,  new JsonDateValueProcessor());
        Players player = playersService.getById(player_id);
        PlayersInfo playersInfo = playersInfoService.getById(player_id);
        Players_Stats players_Stats = iPlayers_statsService.getById(player_id);
        PlayerVO playerVO = new PlayerVO();
        playerVO.setName(player.getName());
        playerVO.setNation(playersInfo.getNation());
        playerVO.setNumber(playersInfo.getNumber());
        playerVO.setPosition(player.getPosition());
        playerVO.setSalary(player.getSalary());
        playerVO.setTeam(player.getTeam());
        playerVO.setWeight(playersInfo.getWeight());
        //playerVO.setArms(playersInfo.getArms());
        playerVO.setBirth(playersInfo.getBirth());
        playerVO.setContract(player.getContract());
        playerVO.setDraft(playersInfo.getDraft());
        playerVO.setHeight(playersInfo.getHeight());
        playerVO.setPerEv(players_Stats.getPer());
        //playerVO.setStandTall(playersInfo.getStandTall());
        JSONObject json = JSONObject.fromObject(playerVO,jsonConfig);
        System.out.println("abc"+json.toString());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.flush();
            out.close();
		}
        
        //model.addAttribute("player",playerVO);
        
    }
    /*给出指定球员ID显示球员的基本信息
     * 根据球员名字到数据库查询球员信息
     */
    @RequestMapping("/playerShow2.json")
    public void toPlayer2(HttpServletRequest request,HttpServletResponse response,Model model){
        String name = request.getParameter("name");
        JsonConfig jsonConfig = new JsonConfig();
        jsonConfig.registerJsonValueProcessor(Date.class,  new JsonDateValueProcessor());
        Players player = playersService.getByName(name);
        PlayersInfo playersInfo = playersInfoService.getById(player.getId());
        Players_Stats players_Stats = iPlayers_statsService.getById(player.getId());
        PlayerVO playerVO = new PlayerVO();
        playerVO.setName(player.getName());
        playerVO.setNation(playersInfo.getNation());
        playerVO.setNumber(playersInfo.getNumber());
        playerVO.setPosition(player.getPosition());
        playerVO.setSalary(player.getSalary());
        playerVO.setTeam(player.getTeam());
        playerVO.setWeight(playersInfo.getWeight());
        //playerVO.setArms(playersInfo.getArms());
        playerVO.setBirth(playersInfo.getBirth());
        playerVO.setContract(player.getContract());
        playerVO.setDraft(playersInfo.getDraft());
        playerVO.setHeight(playersInfo.getHeight());
        playerVO.setPerEv(players_Stats.getPer());
       // playerVO.setStandTall(playersInfo.getStandTall());
        JSONObject json = JSONObject.fromObject(playerVO,jsonConfig);
        json.put("player_id", player.getId());
        System.out.println("abc"+json.toString());
        response.setContentType("application/json");
        response.setCharacterEncoding("utf-8");
        PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.flush();
            out.close();
		}
        
        //model.addAttribute("player",playerVO);
        
    }
    
    /*给出指定球员ID显示球员的数据信息
     * 根据球员id到数据库球员数据表查询
     */
    @RequestMapping("playerStatsShow.json")
    public void showPlayerStats(HttpServletRequest request,HttpServletResponse response,Model model){
        int player_id = Integer.parseInt(request.getParameter("player_id"));

        Players player = playersService.getById(player_id);
        PlayersInfo playersInfo = playersInfoService.getById(player.getId());
        Players_Stats players_Stats=iPlayers_statsService.getById(player_id);
        
        JSONObject json = JSONObject.fromObject(players_Stats);
        json.put("number", playersInfo.getNumber());
        json.put("team", player.getTeam());
        json.put("position", player.getPosition());
        json.put("salary", player.getSalary());
        json.put("name", player.getName());
        System.out.println("abc"+json.toString());
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json");
        PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.flush();
            out.close();
		}
    }
    
    /*球员上场
     * 根据要上场的球员id先判断该球员是否已为首发，是就返回错误信息，否则完成替换以及战斗力变化等操作
     */
    @RequestMapping("playerIn")
    public String playerIn(HttpServletRequest request,Model model){
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int player_id = Integer.parseInt(request.getParameter("player_id"));
        User user = userService.getById(user_id);
        TeamMembers teamMembers=teamMembersService.selectByKey2(user_id,player_id);
        Players players=playersService.getById(player_id);
        Players_Stats player = iPlayers_statsService.getById(player_id);
        Lineup lineup=lineupService.getById(user_id);
        if(lineup.getC()==player_id || lineup.getPf()==player_id || lineup.getPg()==player_id || lineup.getSf()==player_id || lineup.getSg()==player_id){
        	String error="{"+"mesg"+":"+"player is already in."+"}";
        	return "error";
        }else{
        	if(players.getPosition().equals("C")){
        		int id = lineup.getC();
        		Players_Stats player2 = iPlayers_statsService.getById(id);
        		double player_ev=player.getPer().doubleValue();
        		double player2_ev=player2.getPer().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power);
        		userService.updateByKey(user);
        		TeamMembers teamMembers2=teamMembersService.selectByKey2(user_id,id);
        		
        		teamMembersService.updateTeamMembers(user_id, player_id, true);
        		teamMembersService.updateTeamMembers(user_id, id, false);
        		lineup.setC(player_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        		
        	}else if(players.getPosition().equals("PF")){
        		int id = lineup.getPf();
        		Players_Stats player2 = iPlayers_statsService.getById(id);
        		double player_ev=player.getPer().doubleValue();
        		double player2_ev=player2.getPer().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player2_power-player_power);
        		TeamMembers teamMembers2=teamMembersService.selectByKey2(user_id,id);
        		teamMembersService.updateTeamMembers(user_id, player_id, true);
        		teamMembersService.updateTeamMembers(user_id, id, false);
        		lineup.setPf(player_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        	}else if(players.getPosition().equals("PG")){
        		int id = lineup.getPg();
        		Players_Stats player2 = iPlayers_statsService.getById(id);
        		double player_ev=player.getPer().doubleValue();
        		double player2_ev=player2.getPer().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player2_power-player_power);
        		TeamMembers teamMembers2=teamMembersService.selectByKey2(user_id,id);
        		teamMembersService.updateTeamMembers(user_id, player_id, true);
        		teamMembersService.updateTeamMembers(user_id, id, false);
        		lineup.setPg(player_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        	}else if(players.getPosition().equals("SF")){
        		int id = lineup.getSf();
        		Players_Stats player2 = iPlayers_statsService.getById(id);
        		double player_ev=player.getPer().doubleValue();
        		double player2_ev=player2.getPer().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player2_power-player_power);
        		TeamMembers teamMembers2=teamMembersService.selectByKey2(user_id,id);
        		teamMembersService.updateTeamMembers(user_id, player_id, true);
        		teamMembersService.updateTeamMembers(user_id, id, false);
        		lineup.setSf(player_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        	}else{
        		int id = lineup.getSg();
        		Players_Stats player2 = iPlayers_statsService.getById(id);
        		double player_ev=player.getPer().doubleValue();
        		double player2_ev=player2.getPer().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player2_power-player_power);
        		TeamMembers teamMembers2=teamMembersService.selectByKey2(user_id,id);
        		teamMembersService.updateTeamMembers(user_id, player_id, true);
        		teamMembersService.updateTeamMembers(user_id, id, false);
        		lineup.setSg(player_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        	}
        	
        	
        }
        
        //JSONObject json = JSONObject.fromObject(lineup);
        }
    /*替换球员
     * 如果球员本位置与替换位置不符则战斗力会有减损（战力减损为原来的50%）
     */
    @RequestMapping("/replacePlayer.json")
    public void repalcePlayer(HttpServletRequest request,Model model,HttpServletResponse response){
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        User user = userService.getById(user_id);
        int player_in_id = Integer.parseInt(request.getParameter("player_in_id"));
        int player_out_id = Integer.parseInt(request.getParameter("player_out_id"));
        Players player_in=playersService.getById(player_in_id);
        Players player_out=playersService.getById(player_out_id);
        Players_Stats player_in_stats=iPlayers_statsService.getById(player_in_id);
        Players_Stats player_out_stats=iPlayers_statsService.getById(player_out_id);
        
        
        Lineup lineup=lineupService.getById(user_id);
        MessageVO messageVO = new MessageVO();
        if(lineup.getC()==player_in_id || lineup.getPf()==player_in_id || lineup.getPg()==player_in_id || lineup.getSf()==player_in_id || lineup.getSg()==player_in_id){
        	messageVO.setStatus(-1);
        	
        }else{
        if(player_in.getPosition().equals(player_out.getPosition())){
        	
        	if(player_in.getPosition().equals("C")){
        		
        		
        		double player_ev=player_in_stats.getPer().doubleValue();
        		double player2_ev=player_out_stats.getPer().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setC(player_in_id);
        		lineupService.updateLineup(lineup);
        		messageVO.setStatus(1);
        		
        	}else if(player_in.getPosition().equals("PF")){
        		double player_ev=player_in_stats.getPer().doubleValue();
        		double player2_ev=player_out_stats.getPer().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setPf(player_in_id);
        		lineupService.updateLineup(lineup);
        		messageVO.setStatus(1);
        	}else if(player_in.getPosition().equals("PG")){
        		double player_ev=player_in_stats.getPer().doubleValue();
        		double player2_ev=player_out_stats.getPer().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setPg(player_in_id);
        		lineupService.updateLineup(lineup);
        		messageVO.setStatus(1);
        	}else if(player_in.getPosition().equals("SF")){
        		double player_ev=player_in_stats.getPer().doubleValue();
        		double player2_ev=player_out_stats.getPer().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setSf(player_in_id);
        		lineupService.updateLineup(lineup);
        		messageVO.setStatus(1);
        	}else{
        		double player_ev=player_in_stats.getPer().doubleValue();
        		double player2_ev=player_out_stats.getPer().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setSg(player_in_id);
        		lineupService.updateLineup(lineup);
        		messageVO.setStatus(1);
        	}
        	
        }else{
        	
        	if(player_out.getPosition().equals("C")){
        		
        		
        		double player_ev=player_in_stats.getPer().doubleValue();
        		double player2_ev=player_out_stats.getPer().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9/2));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power-10);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setC(player_in_id);
        		lineupService.updateLineup(lineup);
        		messageVO.setStatus(1);
        		
        	}else if(player_out.getPosition().equals("PF")){

        		double player_ev=player_in_stats.getPer().doubleValue();
        		double player2_ev=player_out_stats.getPer().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9/2));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power-10);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setPf(player_in_id);
        		lineupService.updateLineup(lineup);
        		messageVO.setStatus(1);
        	}else if(player_out.getPosition().equals("PG")){

        		double player_ev=player_in_stats.getPer().doubleValue();
        		double player2_ev=player_out_stats.getPer().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9/2));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power-10);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setPg(player_in_id);
        		lineupService.updateLineup(lineup);
        		messageVO.setStatus(1);
        	}else if(player_out.getPosition().equals("SF")){

        		double player_ev=player_in_stats.getPer().doubleValue();
        		double player2_ev=player_out_stats.getPer().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9/2));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power-10);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setSf(player_in_id);
        		lineupService.updateLineup(lineup);
        		messageVO.setStatus(1);
        	}else{

        		double player_ev=player_in_stats.getPer().doubleValue();
        		double player2_ev=player_out_stats.getPer().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9/2));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power-10);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setSg(player_in_id);
        		lineupService.updateLineup(lineup);
        		messageVO.setStatus(1);
        	}
        }
        }
        JSONObject json = JSONObject.fromObject(messageVO);
        System.out.println("abc"+json.toString());
        response.setContentType("application/json");
        PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.flush();
            out.close();
		}
        /*
        if(position.equals("C")){
        	lineup.setC(player_in_id);
        	lineupService.updateLineup(lineup);
        	teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        	teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        	return mesg;
        	
        }
        else if(position.equals("PF")){
        	lineup.setPf(player_in_id);
        	lineupService.updateLineup(lineup);
        	teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        	teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        	return mesg;
        }
        else if(position.equals("SF")){
        	lineup.setSf(player_in_id);
        	lineupService.updateLineup(lineup);
        	teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        	teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        	return mesg;
        }
        else if(position.equals("PG")){
        	lineup.setPg(player_in_id);
        	lineupService.updateLineup(lineup);
        	teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        	teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        	return mesg;
        }
        else{
        	lineup.setSg(player_in_id);
        	lineupService.updateLineup(lineup);
        	teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        	teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        	return mesg;
        }*/

    }
    /*解雇球员
     * 若球员在首发位置上则显示错误信息不能解雇，否则从用户球队中删除该球员并返回该球员的80%薪资回用户资金。
     */
    @RequestMapping("/playerFire.json")
    public void playerFire(HttpServletRequest request,Model model,HttpServletResponse response){
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int player_id = Integer.parseInt(request.getParameter("player_id"));
        System.out.println(player_id+""+user_id);
        MessageVO messageVO = new MessageVO();
        Lineup lineup=lineupService.getById(user_id);
        if(lineup.getC()==player_id || lineup.getPf()==player_id || lineup.getPg()==player_id || lineup.getSf()==player_id || lineup.getSg()==player_id){
        	messageVO.setStatus(-1);
        	messageVO.setMesg("can't fire a played player!");
        }else{
        User user = userService.getById(user_id);

        Players player=playersService.getById(player_id);
        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        BelongTeam belongTeam=belongTeamService.getById(user_Info.getBelongteam());
        belongTeam.setPlayersNum(belongTeam.getPlayersNum()-1);
        user.setMoney(user.getMoney()+(int)Math.floor(player.getSalary()*0.8));
        teamMembersService.delTeamMembers(user_id, player_id);
        belongTeamService.updateBelongTeam(belongTeam);
        userService.updateUserByMoney(user);
        messageVO.setStatus(1);
    	messageVO.setMesg("fire successfully!");
        }
    	JSONObject json = JSONObject.fromObject(messageVO);
        System.out.println("abc"+json.toString());
        response.setContentType("application/json");
        PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.flush();
            out.close();
		}
        
    }
    @RequestMapping("/findPlayer.json")
    public void findPlayer(HttpServletRequest request,Model model,HttpServletResponse response){
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        String position = request.getParameter("position");
        
        Players players = new Players();
        Lineup lineup=lineupService.getById(user_id);
        
        User user = userService.getById(user_id);
        if(position.equals("C")){
        	players = playersService.getById(lineup.getC());
        }else if(position.equals("PF")){
        	players = playersService.getById(lineup.getPf());
        }else if(position.equals("SF")){
        	players = playersService.getById(lineup.getSf());
        }else if(position.equals("SG")){
        	players = playersService.getById(lineup.getSg());
        }else{
        	players = playersService.getById(lineup.getPg());
        }
        
        
        
    	JSONObject json = JSONObject.fromObject(players);
        System.out.println("abc"+json.toString());
        response.setContentType("application/json");
        PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(json.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			out.flush();
            out.close();
		}
        
    }
}
