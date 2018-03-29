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
import com.cn.ssm.service.ITeamMembersService;
import com.cn.ssm.service.IUserService;
import com.cn.ssm.service.IUser_InfoService;
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

    @RequestMapping("teamShow")
    public String toIndex(HttpServletRequest request,Model model){
        int userId = Integer.parseInt(request.getParameter("id"));

        User user = userService.getById(userId);

        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        BelongTeam belongTeam=belongTeamService.getById(user_Info.getBelongteam());
        Lineup lineup=lineupService.getById(belongTeam.getLineup());
        List<TeamMembers> list=teamMembersService.getById(user_Info.getUserId());
        TeamVO teamVo=new TeamVO();
        teamVo.setMoney(user.getMoney());
        teamVo.setPower(user.getPower());
        teamVo.setList(list);
//        model.addAttribute("user", user);
//        model.addAttribute("user_info", user_Info);
//        model.addAttribute("belongteam", belongTeam);
//        model.addAttribute("lineup", lineup);
//        model.addAttribute("list", list);
        JSONObject json = JSONObject.fromObject(teamVo);
        
        return "showUser";
    }

}
