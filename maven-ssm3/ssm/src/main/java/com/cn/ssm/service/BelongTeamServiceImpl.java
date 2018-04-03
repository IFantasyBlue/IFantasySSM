package com.cn.ssm.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.dao.*;
import com.cn.ssm.entity.BelongTeam;

@Service
@Transactional
public class BelongTeamServiceImpl implements IBelongTeamService {

	
	@Resource
    public BelongTeamMapper belongTeamMapper;
	public BelongTeam getById(int id) {
		// TODO Auto-generated method stub
		return belongTeamMapper.selectByPrimaryKey(id);
	}
	public int updateBelongTeam(BelongTeam belongTeam) {
		// TODO Auto-generated method stub
		return belongTeamMapper.updateByPrimaryKey(belongTeam);
	}

}
