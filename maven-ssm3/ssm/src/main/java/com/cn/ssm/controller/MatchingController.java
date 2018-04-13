package com.cn.ssm.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.ssm.entity.BelongTeam;
import com.cn.ssm.entity.Lineup;
import com.cn.ssm.entity.TeamMembers;
import com.cn.ssm.entity.User;
import com.cn.ssm.entity.User_Info;
import com.cn.ssm.entity.matching;
import com.cn.ssm.service.IBelongTeamService;
import com.cn.ssm.service.ILineupService;
import com.cn.ssm.service.IMatchingService;
import com.cn.ssm.service.IPlayersInfoService;
import com.cn.ssm.service.IPlayersService;
import com.cn.ssm.service.IPlayers_statsService;
import com.cn.ssm.service.ITeamMembersService;
import com.cn.ssm.service.IUserService;
import com.cn.ssm.service.IUser_InfoService;
import com.cn.ssm.vo.TeamVO;

@Controller
@RequestMapping("/matching")
public class MatchingController {

	
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
    @Resource
    private IMatchingService iMatchingService;
    
    @RequestMapping("matchingIn")
    public String matchingIn(HttpServletRequest request,Model model) throws InterruptedException{
        int user_id = Integer.parseInt(request.getParameter("user_id"));

        User user = userService.getById(user_id);

        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        matching m=new matching();
//        int id=iMatchingService.getMaxId()+1;
        m.setId(user_id);
        m.setLevel(user_Info.getLevel());
        m.setUserId(user_id);
        iMatchingService.insert(m);
        while(true){
        List<matching> list=iMatchingService.getMatching(user_Info.getLevel());
        for(int i=0;i<list.size();i++){
        	if(list.get(i).getUserId()==user_id){
        		continue;
        	}else{
        		Thread.sleep(1000);
        		iMatchingService.delete(list.get(i).getId());
        		iMatchingService.delete(user_id);
        		User user2 = userService.getById(list.get(i).getUserId());
        		//战斗力算法分析输赢：
        		
        			List<User> list1=new ArrayList<User>();
            		list1.add(user);
            		list1.add(userService.getById(list.get(i).getUserId()));
            		model.addAttribute("user_id", user_id);
            		model.addAttribute("user2_id", list.get(i).getUserId());
            		model.addAttribute("list", list1);
            		model.addAttribute("info", "匹配成功！");
        		
        			
            		
        		
        		
        		
        		return "matchingIn";
        	}
        }
        }
   
//        JSONObject json = JSONObject.fromObject(teamVo);
        
        
    }
    @RequestMapping("MatchingInfo")
    public String MatchingInfo(HttpServletRequest request,Model model){ 
    	int user_id = Integer.parseInt(request.getParameter("user_id"));
    	int user2_id = Integer.parseInt(request.getParameter("user2_id"));
    	User user = userService.getById(user_id);
    	User user2 = userService.getById(user2_id);
        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        User_Info user2_Info = user_InfoService.getById(user2.getUserinfo());
        List<User> list=new ArrayList<User>();
		list.add(user);
		list.add(user2);
		model.addAttribute("user_id", user_id);
		model.addAttribute("user2_id", user2_id);
		model.addAttribute("list", list);
		model.addAttribute("info", "对阵信息");
		return "playerIn";
    }
    
    @RequestMapping("Finghting")
    public String Finghting(HttpServletRequest request,Model model){ 
    	int user_id = Integer.parseInt(request.getParameter("user_id"));
    	int user2_id = Integer.parseInt(request.getParameter("user2_id"));
    	User user = userService.getById(user_id);
    	User user2 = userService.getById(user2_id);
        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        User_Info user2_Info = user_InfoService.getById(user2.getUserinfo());
        List<User> list=new ArrayList<User>();
		list.add(user);
		list.add(user2);
		model.addAttribute("user_id", user_id);
		model.addAttribute("user2_id", user2_id);
		model.addAttribute("list", list);
		model.addAttribute("info", "比赛进行中");
		return "Finghting";
    }
    @RequestMapping("FinghtingEnd")
    public String FinghtingEnd(HttpServletRequest request,Model model){ 
    	int user_id = Integer.parseInt(request.getParameter("user_id"));
    	int user2_id = Integer.parseInt(request.getParameter("user2_id"));
    	User user = userService.getById(user_id);
    	User user2 = userService.getById(user2_id);
        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        User_Info user2_Info = user_InfoService.getById(user2.getUserinfo());
        List<User> list=new ArrayList<User>();
		list.add(user);
		list.add(user2);
		model.addAttribute("user_id", user_id);
		model.addAttribute("user2_id", user2_id);
		model.addAttribute("list", list);
		model.addAttribute("info", "比赛结束");
		return "FinghtingEnd";
    }
    
    @RequestMapping("FinghtingResult")
    public String FinghtingResult(HttpServletRequest request,Model model){ 
    	int user_id = Integer.parseInt(request.getParameter("user_id"));
    	int user2_id = Integer.parseInt(request.getParameter("user2_id"));
    	User user = userService.getById(user_id);
    	User user2 = userService.getById(user2_id);
        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        User_Info user2_Info = user_InfoService.getById(user2.getUserinfo());
        List<User> list=new ArrayList<User>();
		list.add(user);
		list.add(user2);
		if(user.getPower()>user2.getPower()){
			model.addAttribute("user_id", user_id);
			model.addAttribute("user2_id", user2_id);
			model.addAttribute("list", list);
			model.addAttribute("info", "you win!");
		}else{
			model.addAttribute("user_id", user_id);
			model.addAttribute("user2_id", user2_id);
			model.addAttribute("list", list);
			model.addAttribute("info", "you lose!");
		}
		
		return "FinghtingResult";
    }
}
