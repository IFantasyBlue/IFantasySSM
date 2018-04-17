package com.cn.ssm.controller;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

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
    @RequestMapping("teamShow")
    public String toIndex(HttpServletRequest request,Model model){
        int userId = Integer.parseInt(request.getParameter("user_id"));

        User user = userService.getById(userId);

        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        BelongTeam belongTeam=belongTeamService.getById(user_Info.getBelongteam());
        Lineup lineup=lineupService.getById(belongTeam.getLineup());
        List<TeamMembers> list=teamMembersService.getById(user_Info.getUserId());
        TeamVO teamVo=new TeamVO();
        teamVo.setMoney(user.getMoney());
        teamVo.setPower(user.getPower());
        teamVo.setList(list);
     model.addAttribute("user", user);
    model.addAttribute("user_info", user_Info);
  model.addAttribute("belongteam", belongTeam);       model.addAttribute("lineup", lineup);
   model.addAttribute("list", list);
   
//        JSONObject json = JSONObject.fromObject(teamVo);
        
        return "showUser";
    }
    /*按照位置显示球队成员
     * 按照位置是C（中锋）
     */
    @RequestMapping("teamShowC")
    public String toTeamC(HttpServletRequest request,Model model){
        int user_id = Integer.parseInt(request.getParameter("user_id"));

        User user = userService.getById(user_id);

        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        BelongTeam belongTeam=belongTeamService.getById(user_Info.getBelongteam());
        Lineup lineup=lineupService.getById(belongTeam.getLineup());
        List<TeamMembers> list=teamMembersService.selectByKey3(user_id, "C");
        TeamVO teamVo=new TeamVO();
        teamVo.setMoney(user.getMoney());
        teamVo.setPower(user.getPower());
        teamVo.setList(list);
     model.addAttribute("user", user);
    model.addAttribute("user_info", user_Info);
  model.addAttribute("belongteam", belongTeam);       model.addAttribute("lineup", lineup);
   model.addAttribute("list", list);
   
//        JSONObject json = JSONObject.fromObject(teamVo);
        
        return "showUser";
    }
    /*按照位置显示球队成员
     * 按照位置是PF（大前锋）
     */
    @RequestMapping("teamShowPF")
    public String toTeamPF(HttpServletRequest request,Model model){
        int user_id = Integer.parseInt(request.getParameter("user_id"));

        User user = userService.getById(user_id);

        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        BelongTeam belongTeam=belongTeamService.getById(user_Info.getBelongteam());
        Lineup lineup=lineupService.getById(belongTeam.getLineup());
        List<TeamMembers> list=teamMembersService.selectByKey3(user_id, "PF");
        TeamVO teamVo=new TeamVO();
        teamVo.setMoney(user.getMoney());
        teamVo.setPower(user.getPower());
        teamVo.setList(list);
     model.addAttribute("user", user);
    model.addAttribute("user_info", user_Info);
  model.addAttribute("belongteam", belongTeam);       model.addAttribute("lineup", lineup);
   model.addAttribute("list", list);
   
//        JSONObject json = JSONObject.fromObject(teamVo);
        
        return "showUser";
    }
    /*按照位置显示球队成员
     * 按照位置是PG（控球后卫）
     */
    @RequestMapping("teamShowPG")
    public String toTeamPG(HttpServletRequest request,Model model){
        int user_id = Integer.parseInt(request.getParameter("user_id"));

        User user = userService.getById(user_id);

        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        BelongTeam belongTeam=belongTeamService.getById(user_Info.getBelongteam());
        Lineup lineup=lineupService.getById(belongTeam.getLineup());
        List<TeamMembers> list=teamMembersService.selectByKey3(user_id, "PG");
        TeamVO teamVo=new TeamVO();
        teamVo.setMoney(user.getMoney());
        teamVo.setPower(user.getPower());
        teamVo.setList(list);
     model.addAttribute("user", user);
    model.addAttribute("user_info", user_Info);
  model.addAttribute("belongteam", belongTeam);       model.addAttribute("lineup", lineup);
   model.addAttribute("list", list);
   
//        JSONObject json = JSONObject.fromObject(teamVo);
        
        return "showUser";
    }
    /*按照位置显示球队成员
     * 按照位置是SG（得分后卫）
     */
    @RequestMapping("teamShowSG")
    public String toTeamSG(HttpServletRequest request,Model model){
        int user_id = Integer.parseInt(request.getParameter("user_id"));

        User user = userService.getById(user_id);

        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        BelongTeam belongTeam=belongTeamService.getById(user_Info.getBelongteam());
        Lineup lineup=lineupService.getById(belongTeam.getLineup());
        List<TeamMembers> list=teamMembersService.selectByKey3(user_id, "SG");
        TeamVO teamVo=new TeamVO();
        teamVo.setMoney(user.getMoney());
        teamVo.setPower(user.getPower());
        teamVo.setList(list);
     model.addAttribute("user", user);
    model.addAttribute("user_info", user_Info);
  model.addAttribute("belongteam", belongTeam);       model.addAttribute("lineup", lineup);
   model.addAttribute("list", list);
   
//        JSONObject json = JSONObject.fromObject(teamVo);
        
        return "showUser";
    }
    /*按照位置显示球队成员
     * 按照位置是SF（小前锋）
     */
    @RequestMapping("teamShowSF")
    public String toTeamSF(HttpServletRequest request,Model model){
        int user_id = Integer.parseInt(request.getParameter("user_id"));

        User user = userService.getById(user_id);

        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        BelongTeam belongTeam=belongTeamService.getById(user_Info.getBelongteam());
        Lineup lineup=lineupService.getById(belongTeam.getLineup());
        List<TeamMembers> list=teamMembersService.selectByKey3(user_id, "SF");
        TeamVO teamVo=new TeamVO();
        teamVo.setMoney(user.getMoney());
        teamVo.setPower(user.getPower());
        teamVo.setList(list);
     model.addAttribute("user", user);
    model.addAttribute("user_info", user_Info);
  model.addAttribute("belongteam", belongTeam);       model.addAttribute("lineup", lineup);
   model.addAttribute("list", list);
   
//        JSONObject json = JSONObject.fromObject(teamVo);
        
        return "showUser";
    }
    
    /*给出指定球员ID显示球员的基本信息
     * 根据球员id到数据库查询球员信息
     */
    @RequestMapping("playerShow")
    public String toPlayer(HttpServletRequest request,Model model){
        int player_id = Integer.parseInt(request.getParameter("player_id"));
        
        Players player = playersService.getById(player_id);
        PlayersInfo playersInfo = playersInfoService.getById(player_id);
        PlayerVO playerVO = new PlayerVO();
        playerVO.setName(player.getName());
        playerVO.setNation(playersInfo.getNation());
        playerVO.setNumber(playersInfo.getNumber());
        playerVO.setPosition(player.getPosition());
        playerVO.setSalary(player.getSalary());
        playerVO.setTeam(player.getTeam());
        playerVO.setWeight(playersInfo.getWeight());
        playerVO.setArms(playersInfo.getArms());
        playerVO.setBirth(playersInfo.getBirth());
        playerVO.setContract(player.getContract());
        playerVO.setDraft(playersInfo.getDraft());
        playerVO.setHeight(playersInfo.getHeight());
        playerVO.setStandTall(playersInfo.getStandTall());
        //JSONObject json = JSONObject.fromObject(playerVO);
        model.addAttribute("player",playerVO);
        return "showPlayer";
    }
    
    
    /*给出指定球员ID显示球员的数据信息
     * 根据球员id到数据库球员数据表查询
     */
    @RequestMapping("playerStatsShow")
    public String showPlayerStats(HttpServletRequest request,Model model){
        int player_id = Integer.parseInt(request.getParameter("player_id"));

        Players_Stats players_Stats=iPlayers_statsService.getById(player_id);
        
        //JSONObject json = JSONObject.fromObject(players_Stats);
        model.addAttribute("player",players_Stats);
        return "showPlayerStats";
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
        		double player_ev=player.getPerEv().doubleValue();
        		double player2_ev=player2.getPerEv().doubleValue();
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
        		double player_ev=player.getPerEv().doubleValue();
        		double player2_ev=player2.getPerEv().doubleValue();
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
        		double player_ev=player.getPerEv().doubleValue();
        		double player2_ev=player2.getPerEv().doubleValue();
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
        		double player_ev=player.getPerEv().doubleValue();
        		double player2_ev=player2.getPerEv().doubleValue();
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
        		double player_ev=player.getPerEv().doubleValue();
        		double player2_ev=player2.getPerEv().doubleValue();
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
    @RequestMapping("replacePlayer")
    public String repalcePlayer(HttpServletRequest request,Model model){
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        User user = userService.getById(user_id);
        int player_in_id = Integer.parseInt(request.getParameter("player_in_id"));
        int player_out_id = Integer.parseInt(request.getParameter("player_out_id"));
        Players player_in=playersService.getById(player_in_id);
        Players player_out=playersService.getById(player_out_id);
        Players_Stats player_in_stats=iPlayers_statsService.getById(player_in_id);
        Players_Stats player_out_stats=iPlayers_statsService.getById(player_out_id);
        
        
        Lineup lineup=lineupService.getById(user_id);
        String mesg="{"+"mesg"+":"+"replace player successfully."+"}";
        if(player_in.getPosition().equals(player_out.getPosition())){
        	
        	if(player_in.getPosition().equals("C")){
        		
        		
        		double player_ev=player_in_stats.getPerEv().doubleValue();
        		double player2_ev=player_out_stats.getPerEv().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setC(player_in_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        		
        	}else if(player_in.getPosition().equals("PF")){
        		double player_ev=player_in_stats.getPerEv().doubleValue();
        		double player2_ev=player_out_stats.getPerEv().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setPf(player_in_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        	}else if(player_in.getPosition().equals("PG")){
        		double player_ev=player_in_stats.getPerEv().doubleValue();
        		double player2_ev=player_out_stats.getPerEv().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setPg(player_in_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        	}else if(player_in.getPosition().equals("SF")){
        		double player_ev=player_in_stats.getPerEv().doubleValue();
        		double player2_ev=player_out_stats.getPerEv().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setSf(player_in_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        	}else{
        		double player_ev=player_in_stats.getPerEv().doubleValue();
        		double player2_ev=player_out_stats.getPerEv().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setSg(player_in_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        	}
        	
        }else{
        	
        	if(player_out.getPosition().equals("C")){
        		
        		
        		double player_ev=player_in_stats.getPerEv().doubleValue();
        		double player2_ev=player_out_stats.getPerEv().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9/2));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power-10);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setC(player_in_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        		
        	}else if(player_out.getPosition().equals("PF")){

        		double player_ev=player_in_stats.getPerEv().doubleValue();
        		double player2_ev=player_out_stats.getPerEv().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9/2));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power-10);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setPf(player_in_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        	}else if(player_out.getPosition().equals("PG")){

        		double player_ev=player_in_stats.getPerEv().doubleValue();
        		double player2_ev=player_out_stats.getPerEv().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9/2));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power-10);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setPg(player_in_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        	}else if(player_out.getPosition().equals("SF")){

        		double player_ev=player_in_stats.getPerEv().doubleValue();
        		double player2_ev=player_out_stats.getPerEv().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9/2));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power-10);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setSf(player_in_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        	}else{

        		double player_ev=player_in_stats.getPerEv().doubleValue();
        		double player2_ev=player_out_stats.getPerEv().doubleValue();
        		int player_power=(int) Math.floor((100*player_ev/27.9/2));
        		int player2_power=(int) Math.floor((100*player2_ev/27.9));
        		user.setPower(user.getPower()+player_power-player2_power-10);
        		userService.updateByKey(user);
        		
        		
        		teamMembersService.updateTeamMembers(user_id, player_in_id, true);
        		teamMembersService.updateTeamMembers(user_id, player_out_id, false);
        		lineup.setSg(player_in_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        	}
        	
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
    @RequestMapping("playerFire")
    public String playerFire(HttpServletRequest request,Model model){
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int player_id = Integer.parseInt(request.getParameter("player_id"));
        String error="{"+"mesg"+":"+"can't fire a played player."+"}";
        Lineup lineup=lineupService.getById(user_id);
        if(lineup.getC()==player_id || lineup.getPf()==player_id || lineup.getPg()==player_id || lineup.getSf()==player_id || lineup.getSg()==player_id){
        	return error;
        }
        User user = userService.getById(user_id);

        Players player=playersService.getById(player_id);
        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        BelongTeam belongTeam=belongTeamService.getById(user_Info.getBelongteam());
        belongTeam.setPlayersNum(belongTeam.getPlayersNum()-1);
        user.setMoney(user.getMoney()+(int)Math.floor(player.getSalary()*0.8));
        teamMembersService.delTeamMembers(user_id, player_id);
        belongTeamService.updateBelongTeam(belongTeam);
        userService.updateUserByMoney(user);
        String mesg="{"+"mesg"+":"+"fire player successfully."+"}";
        
        return "playerIn";
    }
}
