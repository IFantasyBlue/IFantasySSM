package com.cn.ssm.service;

import com.cn.ssm.entity.BelongTeam;

public interface IBelongTeamService {

	public BelongTeam getById(int id);
	public int updateBelongTeam(BelongTeam belongTeam);
}
