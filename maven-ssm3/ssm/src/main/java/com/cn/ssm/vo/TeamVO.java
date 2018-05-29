package com.cn.ssm.vo;

import java.util.List;

import com.cn.ssm.entity.TeamMembers;

public class TeamVO {

	public int power;
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	public int getMoney() {
		return money;
	}
	public void setMoney(int money) {
		this.money = money;
	}
	public List<TeamMembers> getList() {
		return list;
	}
	public void setList(List<TeamMembers> list) {
		this.list = list;
	}
	public int money;
	List<TeamMembers> list;
	
}
