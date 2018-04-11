package com.cn.ssm.service;

import java.util.List;

import com.cn.ssm.entity.TeamMembers;

public interface ITeamMembersService {

	public List<TeamMembers> getById(int id);
	public int updateTeamMembers(int user_id,int player_id,Boolean status);
	public int delTeamMembers(int user_id,int player_id);
	public TeamMembers selectByKey2(int user_id,int player_id);
	public List<TeamMembers> selectByKey3(int user_id,String position);
}
