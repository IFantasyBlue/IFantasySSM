package com.cn.ssm.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.dao.*;
import com.cn.ssm.entity.TeamMembers;


@Service
@Transactional
public class TeamMembersServiceImpl implements ITeamMembersService {

	
	@Resource
    public TeamMembersMapper teamMembersMapper;
	public List<TeamMembers> getById(int id) {
		// TODO Auto-generated method stub
		return (List<TeamMembers>) teamMembersMapper.selectByKey(id);
	}
	public int updateTeamMembers(int user_id, int player_id, int status) {
		// TODO Auto-generated method stub
		return teamMembersMapper.updateTeamMembers(user_id, player_id, status);
	}
	public int delTeamMembers(int user_id, int player_id) {
		// TODO Auto-generated method stub
		return teamMembersMapper.delTeamMembers(user_id, player_id);
	}

}
