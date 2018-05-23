package com.cn.ssm.controller;

import java.io.PrintWriter;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.cn.ssm.entity.User;
import com.cn.ssm.entity.Chat;
import com.cn.ssm.entity.Friends;
import com.cn.ssm.service.IUserService;

import net.sf.json.JSONObject;

import com.cn.ssm.dao.ChatMapper;
import com.cn.ssm.dao.UserMapper;
import com.cn.ssm.dao.FriendsMapper;

@Controller// 注册为spring容器bean
@RequestMapping("/chat")//请求映射
public class ChatController {
    	@Resource
    private UserMapper userMapper;
    	@Resource
    private ChatMapper chatMapper;
    @Resource
    private FriendsMapper friendsMapper;
    private User receiver=null;
    private ArrayList<Integer> friends_ID= new ArrayList<>();
    private ArrayList<User> world_senders=new ArrayList<>();
    private ArrayList<User> friends= new ArrayList<>();
    private ArrayList<Chat> world_records=new ArrayList<>();
    private ArrayList<Chat> friend_records=new ArrayList<>();
    
    private void init(HttpServletRequest request, HttpServletResponse response) {
		//从HttpRequest解析用户信息，从中得到用户ID
    		//if(sender==null) sender=userService.getById(Integer.parseInt(request.getParameter("id")));
    		world_records=chatMapper.selectByReceive_ID(-1);//广播信息的recive_ID为-1
    		friend_records=chatMapper.selectByReceive_ID(0);
    		friends_ID=friendsMapper.selectByUser_ID(0);
    		friends.clear();
    		//列出好友并以最近聊天时间降序排序
        for(int i=0;i<friends_ID.size();i++)
        	{
        		friends.add(userMapper.selectByPrimaryKey(friends_ID.get(i)));
        	}
	}
    
    private void init_index(int user_id,HttpServletRequest request, HttpServletResponse response) {
    		world_records=chatMapper.selectByReceive_ID(-1);//广播信息的recive_ID为-1
    		friend_records=chatMapper.selectByReceive_ID(user_id);
    		friends_ID=friendsMapper.selectByUser_ID(user_id);
  
    	for(int i=0;i<world_records.size();i++) {
        		world_senders.add(userMapper.selectByPrimaryKey(world_records.get(i).getSendId()));
        	}
    		
    		//列出好友并以最近聊天时间降序排序
        for(int i=0;i<friends_ID.size();i++)
        	{
        		friends.add(userMapper.selectByPrimaryKey(friends_ID.get(i)));
        	}
        
        if(friends!=null) {
        	receiver=userMapper.selectByPrimaryKey(friends_ID.get(0));
        }
        	
	}
    private void init_world(int user_id,HttpServletRequest request, HttpServletResponse response) {
    		world_records=chatMapper.selectByReceive_ID(-1);//广播信息的recive_ID为-1
    		
    		world_senders.clear();
    	for(int i=0;i<world_records.size();i++) {
    		world_senders.add(userMapper.selectByPrimaryKey(world_records.get(i).getSendId()));
    	}
    		friend_records.clear();
    		friends_ID.clear();
    		friends.clear();
	}
    
    private void init_friend(int user_id,HttpServletRequest request, HttpServletResponse response) {
    		world_records.clear();
    		world_senders.clear();
    	if(receiver==null) {
    			return;
    		}
    		friend_records=chatMapper.selectByID(receiver.getId());
    		friends_ID=friendsMapper.selectByUser_ID(user_id);
    		friends.clear();
    		//列出好友并以最近聊天时间降序排序
        for(int i=0;i<friends_ID.size();i++)
        	{
        		friends.add(userMapper.selectByPrimaryKey(friends_ID.get(i)));
        	}
	}
  //把数据封装进JSON格式传输
    private void flush(HttpServletRequest request, HttpServletResponse response)  throws Exception {
       	response.setContentType("application/json");
   		PrintWriter out=null;
   		JSONObject json = new JSONObject();
   	
   		try {
   			out=response.getWriter();
   			
   			json.put("receiver",receiver);
   			json.put("friends", friends);
   			json.put("friend_records", friend_records);
   			json.put("world_senders", world_senders);
   			json.put("world_records", world_records);

   			out.write(json.toString());
   		}finally{
   		out.flush();
   		out.close();
   		}
       }
    
    
    //
    @RequestMapping(value="getChat.json" ,produces = "text/html;charset=UTF-8")
   	private void chat_index(@RequestParam("user_id") int user_id,HttpServletRequest request, HttpServletResponse response)  throws Exception {
       	init_index(user_id,request,response);
           	flush(request,response);
       }
    //查询世界发言记录
    @RequestMapping(value="getWorld.json" ,produces = "text/html;charset=UTF-8")
   	private void chat_getworld(@RequestParam("user_id") int user_id,HttpServletRequest request, HttpServletResponse response)  throws Exception {
       	init_world(user_id,request,response);
           	flush(request,response);
       }
    
    //发送世界发言
    @Transactional
    @RequestMapping(value="sendWorld.json")
   	private void chat_sendworld(@RequestParam("user_id") int user_id,@RequestParam("message") String message,HttpServletRequest request, HttpServletResponse response)  throws Exception {
    		Chat record=new Chat();
		record.setSendId(user_id);
		record.setReceiveId(-1);//广播对象默认-1
		record.setTime(new Timestamp(System.currentTimeMillis()));//获取系统当前时间戳
		record.setContent((String)message);
	
		chatMapper.insert_AI(record);
    	
		chat_getworld(user_id,request,response);
       }
    
    //查询特定好友聊天记录
    @RequestMapping(value="getFriend.json")
   	private void chat_getfriend(@RequestParam("user_id") int user_id,
   			HttpServletRequest request, HttpServletResponse response)  throws Exception {
       	init_friend(user_id,request,response);
           	flush(request,response);
       }
    
    //选择好友及查询聊天记录
    @RequestMapping(value="indexFriend.json")
   	private void chat_indexfriend(@RequestParam("user_id") int user_id,@RequestParam("receiver_id") int record_id,
   			HttpServletRequest request, HttpServletResponse response)  throws Exception {
    		receiver=userMapper.selectByPrimaryKey(friends_ID.get(record_id));
    		init_friend(user_id,request,response);
    		flush(request,response);
       }
    //向好友发送消息
    @Transactional
    @RequestMapping(value="sendFriend.json")
   	private void chat_sendfriend(@RequestParam("user_id") int user_id,@RequestParam("message") String message,
   			HttpServletRequest request, HttpServletResponse response)  throws Exception {
		if(receiver!=null)
		{
			Chat record=new Chat();
			record.setSendId(user_id);
			record.setReceiveId(receiver.getId());
			record.setTime(new Timestamp(System.currentTimeMillis()));//获取系统当前时间戳
			record.setContent((String)message);
		
			chatMapper.insert_AI(record);
		}
		chat_getfriend(user_id,request,response);
       }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    private ModelAndView show(HttpServletRequest request, HttpServletResponse response)  throws Exception {
    	ModelAndView mav = new ModelAndView("chat");
    	mav.addObject("world_records",world_records.toString());
    	mav.addObject("friend_records",friend_records.toString());
    	mav.addObject("friends",friends_ID.toString());
    	mav.addObject("list",world_records);
    return mav;
}
    @RequestMapping(value="index")
   	private ModelAndView chat_index(HttpServletRequest request, HttpServletResponse response)  throws Exception {
       		init(request,response);
           	return show(request,response);	
       }
    
    //发送公共聊天
    @RequestMapping(value="airing", method=RequestMethod.GET)
    public ModelAndView Airing(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
    		Chat record=new Chat();
    		
    		record.setSendId(1);//sender.getID()
    		record.setReceiveId(-1);//广播对象默认-1
    		record.setTime(new Timestamp(System.currentTimeMillis()));//获取系统当前时间戳
    		record.setContent("worldwide"+request.getParameter("content"));//request.getParameter("content")
    		
    		chatMapper.insert_AI(record);
    	return new ModelAndView("redirect:/chat/index");
    }
   
    //选择聊天好友
    @RequestMapping("init_receiver")
    private ModelAndView init_receiver(HttpServletRequest request, HttpServletResponse response) throws Exception
    {
        	int id=1;//前端获取id
        	System.out.println(id);
        	//receiver=userMapper.selectByPrimaryKey(id);
        	
        return new ModelAndView("redirect:/chat/index");
    }
    
  //发送私有聊天
    @RequestMapping(value="whisper")
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
    		
    	return new ModelAndView("redirect:/chat/index");
    }
}
