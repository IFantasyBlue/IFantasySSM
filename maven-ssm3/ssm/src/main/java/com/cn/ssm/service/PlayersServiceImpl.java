package com.cn.ssm.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.dao.PlayersMapper;
import com.cn.ssm.entity.Players;

@Service
@Transactional
public class PlayersServiceImpl implements IPlayersService {

	
	@Resource
    public PlayersMapper playersMapper;
	public Players getById(int id) {
		// TODO Auto-generated method stub
		return playersMapper.selectByPrimaryKey(id);
	}
	public Players getByName(String name) {
		// TODO Auto-generated method stub
		return playersMapper.selectByName(name);
	}

}
