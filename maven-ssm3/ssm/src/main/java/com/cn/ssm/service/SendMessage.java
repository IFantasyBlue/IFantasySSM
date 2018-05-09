package com.cn.ssm.service;

import java.sql.Timestamp;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.dao.*;
import com.cn.ssm.entity.BelongTeam;
import com.cn.ssm.entity.Chat;

@Service
@Transactional
public class SendMessage {

	@Resource
    public ChatMapper chatMapper;
	public void send_friend(int user_id,int receiver_id,String message) {
		Chat record=new Chat();
		record.setSendId(user_id);
		record.setReceiveId(receiver_id);
		record.setTime(new Timestamp(System.currentTimeMillis()));//获取系统当前时间戳
		record.setContent("worldwide"+message);
		chatMapper.insert_AI(record);
	}
	public void send_world(int user_id,String message) {
		Chat record=new Chat();
		record.setSendId(user_id);
		record.setReceiveId(-1);
		record.setTime(new Timestamp(System.currentTimeMillis()));//获取系统当前时间戳
		record.setContent("worldwide"+message);
		chatMapper.insert_AI(record);
	}
}
