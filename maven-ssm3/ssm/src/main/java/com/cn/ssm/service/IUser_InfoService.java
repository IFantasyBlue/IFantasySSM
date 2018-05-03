package com.cn.ssm.service;

import com.cn.ssm.entity.User_Info;

public interface IUser_InfoService {

	public User_Info getById(int id);
	public int update(User_Info user_Info);
}
