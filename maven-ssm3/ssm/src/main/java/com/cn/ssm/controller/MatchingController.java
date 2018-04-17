package com.cn.ssm.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cn.ssm.dao.VIPInfoMapper;
import com.cn.ssm.entity.BelongTeam;
import com.cn.ssm.entity.Friends;
import com.cn.ssm.entity.Lineup;
import com.cn.ssm.entity.Notice;
import com.cn.ssm.entity.Records;
import com.cn.ssm.entity.TeamMembers;
import com.cn.ssm.entity.User;
import com.cn.ssm.entity.User_Info;
import com.cn.ssm.entity.VIPInfo;
import com.cn.ssm.entity.matching;
import com.cn.ssm.service.IBelongTeamService;
import com.cn.ssm.service.IFriendsService;
import com.cn.ssm.service.ILineupService;
import com.cn.ssm.service.IMatchingService;
import com.cn.ssm.service.INoticeService;
import com.cn.ssm.service.IPlayersInfoService;
import com.cn.ssm.service.IPlayersService;
import com.cn.ssm.service.IPlayers_statsService;
import com.cn.ssm.service.IRecordsService;
import com.cn.ssm.service.ITeamMembersService;
import com.cn.ssm.service.IUserService;
import com.cn.ssm.service.IUser_InfoService;
import com.cn.ssm.service.IVIPService;
import com.cn.ssm.service.VIPServiceImpl;
import com.cn.ssm.vo.TeamVO;

@Controller
@RequestMapping("/matching")
public class MatchingController {

	@Resource
    private IFriendsService iFriendsService;
	@Resource
    private INoticeService iNoticeService;
	@Resource
    private IRecordsService iRecordsService;
	@Resource
    private IVIPService ivipService;
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
    /*进入匹配状态
     * 用户点击在线匹配，便把用户信息加入匹配表并进入循环每隔一秒查询匹配表有无其他段位合适的用户进来
     */
    @RequestMapping("matchingIn")
    public String matchingIn(HttpServletRequest request,Model model) throws InterruptedException{
        int user_id = Integer.parseInt(request.getParameter("user_id"));

        User user = userService.getById(user_id);

        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        matching m = new matching();
//        int id=iMatchingService.getMaxId()+1;
        m.setId(user_id);
        m.setLevel(user_Info.getLevel());
        m.setUserId(user_id);
        iMatchingService.insert(m);
        while(true){
        List<matching> list = iMatchingService.getMatching(user_Info.getLevel());
        for(int i=0;i<list.size();i++){
        	if(list.get(i).getUserId() == user_id){
        		continue;
        	}else{
        		Thread.sleep(1000);
        		iMatchingService.delete(list.get(i).getId());
        		iMatchingService.delete(user_id);
        		User user2 = userService.getById(list.get(i).getUserId());
        		//将用户状态设置为1(1是对战中，0是离线，2为空闲）
        		user.setLoginstatus(1);
        		user2.setLoginstatus(1);
        		userService.updateByKey(user);
        		userService.updateByKey(user2);
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
    /*匹配成功后的双方信息
     * 两用户匹配成功后得到的两个用户的信息
     */
    @RequestMapping("MatchingInfo")
    public String MatchingInfo(HttpServletRequest request,Model model){ 
    	int user_id = Integer.parseInt(request.getParameter("user_id"));
    	int user2_id = Integer.parseInt(request.getParameter("user2_id"));
    	User user = userService.getById(user_id);
    	User user2 = userService.getById(user2_id);
        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        User_Info user2_Info = user_InfoService.getById(user2.getUserinfo());
        List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user2);
		model.addAttribute("user_id", user_id);
		model.addAttribute("user2_id", user2_id);
		model.addAttribute("list", list);
		model.addAttribute("info", "对阵信息");
		return "playerIn";
    }
    /*对战
     * 两用户对战（过渡）
     */
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
    /*对战结束
     * 对战结束
     */
    @RequestMapping("FinghtingEnd")
    public String FinghtingEnd(HttpServletRequest request,Model model){ 
    	int user_id = Integer.parseInt(request.getParameter("user_id"));
    	int user2_id = Integer.parseInt(request.getParameter("user2_id"));
    	User user = userService.getById(user_id);
    	User user2 = userService.getById(user2_id);
        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        User_Info user2_Info = user_InfoService.getById(user2.getUserinfo());
        List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user2);
		model.addAttribute("user_id", user_id);
		model.addAttribute("user2_id", user2_id);
		model.addAttribute("list", list);
		model.addAttribute("info", "比赛结束");
		return "FinghtingEnd";
    }
    /*对战结果
     * 根据两用户的战斗力与vip等级计算胜负概率并按照概率判定一方获胜。（A：Ap/(Ap+Bp)+AVIP-BVIP,B:Bp/(Ap+Bp)+BVIP-AVIP)
     */
    @RequestMapping("FinghtingResult")
    public String FinghtingResult(HttpServletRequest request,Model model){ 
    	int user_id = Integer.parseInt(request.getParameter("user_id"));
    	int user2_id = Integer.parseInt(request.getParameter("user2_id"));
    	User user = userService.getById(user_id);
    	User user2 = userService.getById(user2_id);
        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        User_Info user2_Info = user_InfoService.getById(user2.getUserinfo());
        List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user2);
		int vip1 = user_Info.getVip();
		int vip2 = user2_Info.getVip();
		VIPInfo vipInfo = ivipService.selectByPrimaryKey(vip1);
		VIPInfo vipInfo2 = ivipService.selectByPrimaryKey(vip2);
		double rate = vipInfo.getWinMultiple() - vipInfo2.getWinMultiple();
		System.out.println(rate+""+user_id);
		int user1_dt_id = user.getdTactics();
		int user2_dt_id = user.getdTactics();
		Lineup lineup1=lineupService.getById(user_id);
		Lineup lineup2=lineupService.getById(user2_id);
		TacticsMethods tacticsMethods = new TacticsMethods();
		double dtPower1 = 0;
		double dtPower2 = 0;
		switch(user1_dt_id){
		case 1:
			dtPower2 = tacticsMethods.DTacticsNo1(lineup2.getSg(), lineup2.getPg());
			break;
		case 2:
			dtPower2 = tacticsMethods.DTacticsNo2(lineup2.getSg(), lineup2.getPg());
			break;
		case 3:
			dtPower2 = tacticsMethods.DTacticsNo3(lineup2.getSf(), lineup2.getPf(), lineup2.getC());
			break;
		case 4:
			dtPower2 = tacticsMethods.DTacticsNo4(lineup2.getSf(), lineup2.getPf(), lineup2.getC());
			break;
		case 5:
			dtPower2 = tacticsMethods.DTacticsNo5(lineup2.getSf(), lineup2.getPf(), lineup2.getC(), lineup2.getSg(), lineup2.getPg());
			break;
		}
		switch(user2_dt_id){
		case 1:
			dtPower1 = tacticsMethods.DTacticsNo1(lineup1.getSg(), lineup1.getPg());
			break;
		case 2:
			dtPower1 = tacticsMethods.DTacticsNo2(lineup1.getSg(), lineup1.getPg());
			break;
		case 3:
			dtPower1 = tacticsMethods.DTacticsNo3(lineup1.getSf(), lineup1.getPf(), lineup1.getC());
			break;
		case 4:
			dtPower1 = tacticsMethods.DTacticsNo4(lineup1.getSf(), lineup1.getPf(), lineup1.getC());
			break;
		case 5:
			dtPower1 = tacticsMethods.DTacticsNo5(lineup1.getSf(), lineup1.getPf(), lineup1.getC(), lineup1.getSg(), lineup1.getPg());
			break;
		}
		int power1=user.getPower();
		int power2=user2.getPower();
		int realPower1=(int) Math.floor(power1-dtPower1);
		int realPower2=(int) Math.floor(power2-dtPower2);
		double w1 = (realPower1 + 0.0)/(realPower2 + realPower1);
		double w2 = (realPower2 + 0.0)/(realPower2 + realPower1);
		double user_win = w1 + rate;
		double user2_win = w2 - rate;
		System.out.println(user_win+""+user_id+"");
		System.out.println(user2_win+""+user_id);
		int user_win_rate = (int) Math.round(user_win * 100);
		int user2_win_rate = (int) Math.round(user2_win * 100);
		System.out.println(user_win_rate+""+user_id);
		System.out.println(user2_win_rate+""+user_id);
		Random a = new Random();
		
		int win = a.nextInt(101);
		System.out.println(win+""+user_id);
		if(win >= 0 && win <= user_win_rate){
			System.out.println(3);
			Records records = new Records();
			Records records2 = new Records();
			records2 = iRecordsService.getRecordsById(user_id);
			if(records2 == null){
				Records records3 = new Records();
				records3.setUser1Id(user_id);
				records3.setUser2Id(user2_id);
				records3.setResult("win");
				iRecordsService.insert(records3);
				records = iRecordsService.getRecordsById2(user_id);
				
				model.addAttribute("user_id", user_id);
				model.addAttribute("user2_id", user2_id);
				model.addAttribute("list", list);
				model.addAttribute("info", "you win!");
			}else{
				if(records2.getResult().equals("win")){
					
					model.addAttribute("user_id", user_id);
					model.addAttribute("user2_id", user2_id);
					model.addAttribute("list", list);
					model.addAttribute("info", "you lose!");
					iRecordsService.delete(records2);
					
				}else{
					
					model.addAttribute("user_id", user_id);
					model.addAttribute("user2_id", user2_id);
					model.addAttribute("list", list);
					model.addAttribute("info", "you win!");
					iRecordsService.delete(records2);
					
				}
			}
			}
		else{
			System.out.println(4);
			Records records = new Records();
			Records records2 = new Records();
			records2 = iRecordsService.getRecordsById(user_id);
			if(records2 == null){
				Records records3 = new Records();
				records3.setUser1Id(user_id);
				records3.setUser2Id(user2_id);
				records3.setResult("lose");
				iRecordsService.insert(records3);
				records = iRecordsService.getRecordsById2(user_id);
				
				model.addAttribute("user_id", user_id);
				model.addAttribute("user2_id", user2_id);
				model.addAttribute("list", list);
				model.addAttribute("info", "you lose!");
			}else{
				if(records2.getResult().equals("win")){
					
					model.addAttribute("user_id", user_id);
					model.addAttribute("user2_id", user2_id);
					model.addAttribute("list", list);
					model.addAttribute("info", "you lose!");
					iRecordsService.delete(records2);
					
				}else{
					
					model.addAttribute("user_id", user_id);
					model.addAttribute("user2_id", user2_id);
					model.addAttribute("list", list);
					model.addAttribute("info", "you win!");
					iRecordsService.delete(records2);
					
				}
			}
		}
		user.setLoginstatus(2);
		user2.setLoginstatus(2);
		userService.updateByKey(user);
		userService.updateByKey(user2);
		return "FinghtingResult";
    }
    /*进入好友邀请界面
     * 列出该用户的所有好友
     */
    @RequestMapping("toInvite")
    public String toInvite(HttpServletRequest request,Model model){ 
    	int user_id = Integer.parseInt(request.getParameter("user_id"));
    	List<Friends> list = iFriendsService.getById(user_id);
    	List<User> userList = new ArrayList<User>();
    	for(int i = 0;i < list.size();i++){
    		userList.add(userService.getById(list.get(i).getUserId2()));
    	}
    	model.addAttribute("list", userList);
		return "toInvite";
    }
    /*邀请好友
     * 邀请好友对战，并持续等待10秒若对方接受马上跳转到对战，拒绝则返回拒绝信息，否则等待超时。
     */
    @RequestMapping("Invite")
    public String Invite(HttpServletRequest request,Model model) throws InterruptedException{ 
    	int user_id = Integer.parseInt(request.getParameter("user_id"));
    	int user2_id = Integer.parseInt(request.getParameter("user2_id"));
    	User user = userService.getById(user_id);
    	User user2 = userService.getById(user2_id);
        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        User_Info user2_Info = user_InfoService.getById(user2.getUserinfo());
        List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user2);
		Notice notice = new Notice();
		Notice notice2 = new Notice();
		notice.setStatus(0);
		notice.setUser1Id(user_id);
		notice.setUser2Id(user2_id);
		iNoticeService.insert(notice);
		for(int i = 0;i < 10;i++){
			Thread.sleep(1000);
			notice2=iNoticeService.getByUser1Id(user_id, user2_id);
			if(notice2.getStatus() == 0){
				continue;
			}else if(notice2.getStatus() == 1){
				model.addAttribute("info", "accept");
				model.addAttribute("list", list);
				return "Invite";
			}else{
				model.addAttribute("info", "refuse");
				model.addAttribute("list", list);
				return "Invite";
			}
		}
		model.addAttribute("info", "overtime");
		model.addAttribute("list", list);
		return "Invite";
    }
    /*等待邀请
     * 用户在登录状态时一直保持等待被邀请的状态。
     */
    @RequestMapping("WaitingInvite")
    public String WaitingInvite(HttpServletRequest request,Model model){ 
    	int user_id = Integer.parseInt(request.getParameter("user_id"));
    	while(true){
    		Notice notice = iNoticeService.getByUser2Id(user_id);
    		if(notice == null){
    			continue;
    		}else{
    			model.addAttribute("notice", notice);
    			return "WaitingInvite";
    		}
    	}
		
    }
    /*接受邀请
     * 
     */
    @RequestMapping("AcceptInvite")
    public String AcceptInvite(HttpServletRequest request,Model model){ 
    	int user_id = Integer.parseInt(request.getParameter("user_id"));
    	int user2_id = Integer.parseInt(request.getParameter("user2_id"));
    	User user = userService.getById(user_id);
    	User user2 = userService.getById(user2_id);
        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        User_Info user2_Info = user_InfoService.getById(user2.getUserinfo());
        List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user2);
		Notice notice = iNoticeService.getByUser1Id(user_id, user2_id);
		notice.setStatus(1);
		iNoticeService.update(notice);
		return "AcceptInvite";
    }
    /*拒绝邀请
     * 
     */
    @RequestMapping("RefuseInvite")
    public String RefuseInvite(HttpServletRequest request,Model model){ 
    	int user_id = Integer.parseInt(request.getParameter("user_id"));
    	int user2_id = Integer.parseInt(request.getParameter("user2_id"));
    	User user = userService.getById(user_id);
    	User user2 = userService.getById(user2_id);
        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        User_Info user2_Info = user_InfoService.getById(user2.getUserinfo());
        List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user2);
		Notice notice = iNoticeService.getByUser1Id(user_id, user2_id);
		notice.setStatus(-1);
		iNoticeService.update(notice);
		return "RefuseInvite";
    }
    /*好友对战开始
     * 
     */
    @RequestMapping("FriendsPK")
    public String FriendsPK(HttpServletRequest request,Model model){ 
    	int user_id = Integer.parseInt(request.getParameter("user_id"));
    	int user2_id = Integer.parseInt(request.getParameter("user2_id"));
    	User user = userService.getById(user_id);
    	User user2 = userService.getById(user2_id);
        User_Info user_Info = user_InfoService.getById(user.getUserinfo());
        User_Info user2_Info = user_InfoService.getById(user2.getUserinfo());
        List<User> list = new ArrayList<User>();
		list.add(user);
		list.add(user2);
		int vip1 = user_Info.getVip();
		int vip2 = user2_Info.getVip();
		VIPInfo vipInfo = ivipService.selectByPrimaryKey(vip1);
		VIPInfo vipInfo2 = ivipService.selectByPrimaryKey(vip2);
		double rate = vipInfo.getWinMultiple() - vipInfo2.getWinMultiple();
		System.out.println(rate+""+user_id);
		int user1_dt_id = user.getdTactics();
		int user2_dt_id = user.getdTactics();
		Lineup lineup1=lineupService.getById(user_id);
		Lineup lineup2=lineupService.getById(user2_id);
		TacticsMethods tacticsMethods = new TacticsMethods();
		double dtPower1 = 0;
		double dtPower2 = 0;
		switch(user1_dt_id){
		case 1:
			dtPower2 = tacticsMethods.DTacticsNo1(lineup2.getSg(), lineup2.getPg());
			break;
		case 2:
			dtPower2 = tacticsMethods.DTacticsNo2(lineup2.getSg(), lineup2.getPg());
			break;
		case 3:
			dtPower2 = tacticsMethods.DTacticsNo3(lineup2.getSf(), lineup2.getPf(), lineup2.getC());
			break;
		case 4:
			dtPower2 = tacticsMethods.DTacticsNo4(lineup2.getSf(), lineup2.getPf(), lineup2.getC());
			break;
		case 5:
			dtPower2 = tacticsMethods.DTacticsNo5(lineup2.getSf(), lineup2.getPf(), lineup2.getC(), lineup2.getSg(), lineup2.getPg());
			break;
		}
		switch(user2_dt_id){
		case 1:
			dtPower1 = tacticsMethods.DTacticsNo1(lineup1.getSg(), lineup1.getPg());
			break;
		case 2:
			dtPower1 = tacticsMethods.DTacticsNo2(lineup1.getSg(), lineup1.getPg());
			break;
		case 3:
			dtPower1 = tacticsMethods.DTacticsNo3(lineup1.getSf(), lineup1.getPf(), lineup1.getC());
			break;
		case 4:
			dtPower1 = tacticsMethods.DTacticsNo4(lineup1.getSf(), lineup1.getPf(), lineup1.getC());
			break;
		case 5:
			dtPower1 = tacticsMethods.DTacticsNo5(lineup1.getSf(), lineup1.getPf(), lineup1.getC(), lineup1.getSg(), lineup1.getPg());
			break;
		}
		int power1=user.getPower();
		int power2=user2.getPower();
		int realPower1=(int) Math.floor(power1-dtPower1);
		int realPower2=(int) Math.floor(power2-dtPower2);
		double w1 = (realPower1 + 0.0)/(realPower2 + realPower1);
		double w2 = (realPower2 + 0.0)/(realPower2 + realPower1);
		double user_win = w1 + rate;
		double user2_win = w2 - rate;
		System.out.println(user_win+""+user_id+"");
		System.out.println(user2_win+""+user_id);
		int user_win_rate = (int) Math.round(user_win * 100);
		int user2_win_rate = (int) Math.round(user2_win * 100);
		System.out.println(user_win_rate+""+user_id);
		System.out.println(user2_win_rate+""+user_id);
		Random a = new Random();
		
		int win = a.nextInt(101);
		System.out.println(win+""+user_id);
		if(win >= 0 && win <= user_win_rate){
			System.out.println(3);
			Records records = new Records();
			Records records2 = new Records();
			records2 = iRecordsService.getRecordsById(user_id);
			if(records2 == null){
				Records records3 = new Records();
				records3.setUser1Id(user_id);
				records3.setUser2Id(user2_id);
				records3.setResult("win");
				iRecordsService.insert(records3);
				records = iRecordsService.getRecordsById2(user_id);
				
				model.addAttribute("user_id", user_id);
				model.addAttribute("user2_id", user2_id);
				model.addAttribute("list", list);
				model.addAttribute("info", "you win!");
			}else{
				if(records2.getResult().equals("win")){
					
					model.addAttribute("user_id", user_id);
					model.addAttribute("user2_id", user2_id);
					model.addAttribute("list", list);
					model.addAttribute("info", "you lose!");
					iRecordsService.delete(records2);
					
				}else{
					
					model.addAttribute("user_id", user_id);
					model.addAttribute("user2_id", user2_id);
					model.addAttribute("list", list);
					model.addAttribute("info", "you win!");
					iRecordsService.delete(records2);
					
				}
			}
			}
		else{
			System.out.println(4);
			Records records = new Records();
			Records records2 = new Records();
			records2 = iRecordsService.getRecordsById(user_id);
			if(records2 == null){
				Records records3 = new Records();
				records3.setUser1Id(user_id);
				records3.setUser2Id(user2_id);
				records3.setResult("lose");
				iRecordsService.insert(records3);
				records = iRecordsService.getRecordsById2(user_id);
				
				model.addAttribute("user_id", user_id);
				model.addAttribute("user2_id", user2_id);
				model.addAttribute("list", list);
				model.addAttribute("info", "you lose!");
			}else{
				if(records2.getResult().equals("win")){
					
					model.addAttribute("user_id", user_id);
					model.addAttribute("user2_id", user2_id);
					model.addAttribute("list", list);
					model.addAttribute("info", "you lose!");
					iRecordsService.delete(records2);
					
				}else{
					
					model.addAttribute("user_id", user_id);
					model.addAttribute("user2_id", user2_id);
					model.addAttribute("list", list);
					model.addAttribute("info", "you win!");
					iRecordsService.delete(records2);
					
				}
			}
		}
		user.setLoginstatus(2);
		user2.setLoginstatus(2);
		userService.updateByKey(user);
		userService.updateByKey(user2);
		return "FriendsPK";
    }
}
