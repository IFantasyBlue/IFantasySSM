package com.cn.ssm.service;

import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.dao.UserMapper;
import com.cn.ssm.entity.User;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Resource
    public UserMapper userMapper;
	
	public User getById(int id) {
		// TODO Auto-generated method stub
		User user = new User();
		
		user = userMapper.selectByPrimaryKey(id);
		System.out.println(user.toString());
		return user;
	}

	@Override
	public int updateUserByMoney(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKey(user);
	}

	@Override
	public User updateUserRecruit(int id) {
		// TODO Auto-generated method stub
		User user = getById(id);
    	Date date = new Date();
		long time=date.getTime();
		double recruitTime=(double) time;
		userMapper.updateRecruitTime(recruitTime,id);
		return user;
	}

}
