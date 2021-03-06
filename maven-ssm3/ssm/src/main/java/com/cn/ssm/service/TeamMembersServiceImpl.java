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
	public int updateTeamMembers(int user_id, int player_id, Boolean status) {
		// TODO Auto-generated method stub
		return teamMembersMapper.updateTeamMembers(user_id, player_id, status);
	}
	public int delTeamMembers(int user_id, int player_id) {
		// TODO Auto-generated method stub
		return teamMembersMapper.delTeamMembers(user_id, player_id);
	}
	public TeamMembers selectByKey2(int uer_id,int player_id) {
		// TODO Auto-generated method stub
		return teamMembersMapper.selectByKey2(uer_id,player_id);
	}
	public List<TeamMembers> selectByKey3(int user_id, String position) {
		// TODO Auto-generated method stub
		return teamMembersMapper.selectByKey3(user_id, position);
	}

}
