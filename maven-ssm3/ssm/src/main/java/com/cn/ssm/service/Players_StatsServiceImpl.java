package com.cn.ssm.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.dao.Players_StatsMapper;
import com.cn.ssm.entity.Players_Stats;

@Service
@Transactional
public class Players_StatsServiceImpl implements IPlayers_statsService {

	@Resource
    public Players_StatsMapper players_StatsMapper;
	public Players_Stats getById(int id) {
		
		// TODO Auto-generated method stub
		return players_StatsMapper.selectByPrimaryKey(id);
	}

}
