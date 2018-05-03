package com.cn.ssm.vo;

import com.cn.ssm.entity.TeamMembers;

public class MembersVO {

	public TeamMembers teamMembers;
	public TeamMembers getTeamMembers() {
		return teamMembers;
	}
	public void setTeamMembers(TeamMembers teamMembers) {
		this.teamMembers = teamMembers;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String name;
}
