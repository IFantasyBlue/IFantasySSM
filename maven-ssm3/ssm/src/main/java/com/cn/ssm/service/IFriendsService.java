package com.cn.ssm.service;

import java.util.List;

import com.cn.ssm.entity.Friends;

public interface IFriendsService {

	public List<Friends> getById(int user_id);
}
