package com.cn.ssm.service;

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
		return user;
	}

	public int updateUserByMoney(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKey(user);
	}

	public int updateByKey(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKey(user);
	}

}
