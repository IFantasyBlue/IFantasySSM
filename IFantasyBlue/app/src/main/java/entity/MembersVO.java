package entity;


import java.io.Serializable;

public class MembersVO implements Serializable{




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
