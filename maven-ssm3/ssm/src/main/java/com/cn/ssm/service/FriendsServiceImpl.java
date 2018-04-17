package com.cn.ssm.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.dao.FriendsMapper;
import com.cn.ssm.dao.LineupMapper;
import com.cn.ssm.entity.Friends;


@Service
@Transactional
public class FriendsServiceImpl implements IFriendsService {

	@Resource
    public FriendsMapper friendsMapper;
	
	public List<Friends> getById(int user_id) {
		// TODO Auto-generated method stub
		return friendsMapper.getById(user_id);
	}

}
