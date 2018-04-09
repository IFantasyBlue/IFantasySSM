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
    
    @RequestMapping("playerStatsShow")
    public String showPlayerStats(HttpServletRequest request,Model model){
        int player_id = Integer.parseInt(request.getParameter("player_id"));

        Players_Stats players_Stats=iPlayers_statsService.getById(player_id);
        
        //JSONObject json = JSONObject.fromObject(players_Stats);
        model.addAttribute("player",players_Stats);
        return "showPlayerStats";
    }
    
    @RequestMapping("playerIn")
    public String playerIn(HttpServletRequest request,Model model){
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int player_id = Integer.parseInt(request.getParameter("player_id"));
        TeamMembers teamMembers=teamMembersService.selectByKey2(player_id);
        Players players=playersService.getById(player_id);
        Lineup lineup=lineupService.getById(user_id);
        if(lineup.getC()==player_id || lineup.getPf()==player_id || lineup.getPg()==player_id || lineup.getSf()==player_id || lineup.getSg()==player_id){
        	String error="{"+"mesg"+":"+"player is already in."+"}";
        	return "error";
        }else{
        	if(players.getPosition().equals("C")){
        		int id = lineup.getC();
        		TeamMembers teamMembers2=teamMembersService.selectByKey2(id);
        		teamMembersService.updateTeamMembers(user_id, player_id, true);
        		teamMembersService.updateTeamMembers(user_id, id, false);
        		lineup.setC(player_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        		
        	}else if(players.getPosition().equals("PF")){
        		int id = lineup.getPf();
        		TeamMembers teamMembers2=teamMembersService.selectByKey2(id);
        		teamMembersService.updateTeamMembers(user_id, player_id, true);
        		teamMembersService.updateTeamMembers(user_id, id, false);
        		lineup.setPf(player_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        	}else if(players.getPosition().equals("PG")){
        		int id = lineup.getPg();
        		TeamMembers teamMembers2=teamMembersService.selectByKey2(id);
        		teamMembersService.updateTeamMembers(user_id, player_id, true);
        		teamMembersService.updateTeamMembers(user_id, id, false);
        		lineup.setPg(player_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        	}else if(players.getPosition().equals("SF")){
        		int id = lineup.getSf();
        		TeamMembers teamMembers2=teamMembersService.selectByKey2(id);
        		teamMembersService.updateTeamMembers(user_id, player_id, true);
        		teamMembersService.updateTeamMembers(user_id, id, false);
        		lineup.setSf(player_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        	}else{
        		int id = lineup.getSg();
        		TeamMembers teamMembers2=teamMembersService.selectByKey2(id);
        		teamMembersService.updateTeamMembers(user_id, player_id, true);
        		teamMembersService.updateTeamMembers(user_id, id, false);
        		lineup.setSg(player_id);
        		lineupService.updateLineup(lineup);
        		return "playerIn";
        	}
        	
        	
        }
        
        //JSONObject json = JSONObject.fromObject(lineup);
        
        
    }
    @RequestMapping("repalcePlayer")
    public String repalcePlayer(HttpServletRequest request,Model model){
        int user_id = Integer.parseInt(request.getParameter("user_id"));
        int player_in_id = Integer.parseInt(request.getParameter("player_in_id"));
        int player_out_id = Integer.parseInt(request.getParameter("player_out_id"));
        String position = request.getParameter("position");
        Lineup lineup=lineupService.getById(user_id);
        String mesg="{"+"mesg"+":"+"replace player successfully."+"}";
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
        }
        
        
        
    }
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
        user.setMoney(user.getMoney()+player.getSalary());
        teamMembersService.delTeamMembers(user_id, player_id);
        belongTeamService.updateBelongTeam(belongTeam);
        userService.updateUserByMoney(user);
        String mesg="{"+"mesg"+":"+"fire player successfully."+"}";
        
        return "playerIn";
    }
}
