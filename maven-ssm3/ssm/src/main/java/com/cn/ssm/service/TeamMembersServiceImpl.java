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
		return null;
	}

}
