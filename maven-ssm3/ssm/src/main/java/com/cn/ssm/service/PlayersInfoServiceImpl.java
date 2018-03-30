package com.cn.ssm.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.dao.PlayersInfoMapper;

import com.cn.ssm.entity.PlayersInfo;

@Service
@Transactional
public class PlayersInfoServiceImpl implements IPlayersInfoService {

	@Resource
    public PlayersInfoMapper playersInfoMapper;
	public PlayersInfo getById(int id) {
		// TODO Auto-generated method stub
		return playersInfoMapper.selectByPrimaryKey(id);
	}

}
