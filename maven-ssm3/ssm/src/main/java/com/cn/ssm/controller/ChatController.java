package com.cn.ssm.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cn.ssm.entity.User;
import com.cn.ssm.entity.Chat;
import com.cn.ssm.entity.Friends;
import com.cn.ssm.service.IUserService;
import com.cn.ssm.dao.ChatMapper;
import com.cn.ssm.dao.UserMapper;
import com.cn.ssm.dao.FriendsMapper;

@Controller// 注册为spring容器bean
@RequestMapping("/")//请求映射
public class ChatController {
    	@Resource //注入对象
    private IUserService userService;
    	@Resource
    private UserMapper userMapper;
    	@Resource
       private ChatMapper chatMapper;
    @Resource
    private FriendsMapper friendsMapper;
    private User sender=null;
    private User receiver=null;
    private List<Integer> friends_ID= new ArrayList<>();
    private List<User> friends= new ArrayList<>();
    private List<Chat> world_records=new ArrayList<>();
    private List<Chat> friend_records=new ArrayList<>();
    
    private void init(HttpServletRequest request, HttpServletResponse response) {
		//从HttpRequest解析用户信息，从中得到用户ID
    		//if(sender==null) sender=userService.getById(Integer.parseInt(request.getParameter("id")));
    		world_records=chatMapper.selectByReceive_ID(-1);//广播信息的recive_ID为-1
    		friend_records=chatMapper.selectByReceive_ID(1);//user.getID()
    		friends_ID=friendsMapper.selectByUser_ID(1);//user.getID()
    		friends.clear();
    		//列出好友并以最近聊天时间降序排序
        for(int i=0;i<friends_ID.size();i++)
        	{
        		friends.add(userMapper.selectByPrimaryKey(friends_ID.get(i)));
        	}
	}
    
    private ModelAndView show(HttpServletRequest request, HttpServletResponse response)  throws Exception {
    	ModelAndView mav = new ModelAndView("chat");
    	mav.addObject("world_records",world_records.toString());
    	mav.addObject("friend_records",friend_records.toString());
    	mav.addObject("friends",friends_ID.toString());
    	mav.addObject("list",world_records);
    return mav;
}
    @RequestMapping(value="chat")
   	private ModelAndView chat_index(HttpServletRequest request, HttpServletResponse response)  throws Exception {
       		init(request,response);
           	return show(request,response);	
       }
    
    //发送公共聊天
    @RequestMapping(value="chat/airing", method=RequestMethod.GET)
    public ModelAndView Airing(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    		Chat record=new Chat();
    		
    		record.setSendId(1);//sender.getID()
    		record.setReceiveId(-1);//广播对象默认-1
    		record.setTime(new Timestamp(System.currentTimeMillis()));//获取系统当前时间戳
    		record.setContent("worldwide"+request.getParameter("content"));//request.getParameter("content")
    		
    		chatMapper.insert_AI(record);
    	return new ModelAndView("redirect:/chat");
    }
   
    //选择聊天好友
    @RequestMapping("chat/init_receiver")
    private ModelAndView init_receiver(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        	int id=1;//前端获取id
        	System.out.println(id);
        	//receiver=userMapper.selectByPrimaryKey(id);
        	
        return new ModelAndView("redirect:/chat");
    }
    
  //发送私有聊天
    @RequestMapping(value="chat/whisper")
    public ModelAndView Whisper(HttpServletRequest request, HttpServletResponse response) throws Exception
    { 		
    		Chat record=new Chat();
    		record.setSendId(2);//sender.getID()
    		record.setReceiveId(1);//receiver.getID()
    		record.setTime(new Timestamp(System.currentTimeMillis()));//获取系统当前时间戳
    		record.setContent("whisper");//request.getParameter("content")
    		chatMapper.insert_AI(record);
    		
    		Friends friend=friendsMapper.selectByUser_IDs(1,2);//user.getID(),receiver.getID();
    		friend.setLastChat(new Timestamp(System.currentTimeMillis()));
    		friendsMapper.update(friend);
    		
    		friend=friendsMapper.selectByUser_IDs(2,1);//receiver.getID(),user.getID();
    		friend.setLastChat(new Timestamp(System.currentTimeMillis()));
    		friendsMapper.update(friend);
    		
    	return new ModelAndView("redirect:/chat");
    }
}
