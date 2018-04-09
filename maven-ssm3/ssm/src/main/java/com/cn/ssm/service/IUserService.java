package com.cn.ssm.service;

import org.springframework.stereotype.Service;

import com.cn.ssm.entity.User;

@Service("userService")
public interface IUserService {

	public User getById(int id);
}
