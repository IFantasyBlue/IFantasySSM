package com.cn.ssm.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.dao.*;
import com.cn.ssm.entity.User_Info;


@Service
@Transactional
public class User_InfoServiceImpl implements IUser_InfoService {

	
	@Resource
    public User_InfoMapper user_InfoMapper;
	public User_Info getById(int id) {
		// TODO Auto-generated method stub
		return user_InfoMapper.selectByPrimaryKey(id);
	}

}
