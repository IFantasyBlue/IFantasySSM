package com.cn.ssm.service;

import java.util.List;

import com.cn.ssm.entity.TeamMembers;

public interface ITeamMembersService {

	public List<TeamMembers> getById(int id);
}
