package com.cn.ssm.service;

import com.cn.ssm.entity.User;

public interface IUserService {

	public User getById(int id);
	public int updateUserByMoney(User user);
	
}
