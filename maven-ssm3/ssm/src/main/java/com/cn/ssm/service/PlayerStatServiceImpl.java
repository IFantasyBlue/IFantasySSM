package com.cn.ssm.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.dao.Players_StatsMapper;
import com.cn.ssm.entity.Players_Stats;

@Service
@Transactional
public class PlayerStatServiceImpl implements IPlayerStatService {

	@Resource
    public Players_StatsMapper playerstatsMapper;
	public Players_Stats getById(int id) {
		// TODO Auto-generated method stub
		return playerstatsMapper.selectByPrimaryKey(id);
	}

}
