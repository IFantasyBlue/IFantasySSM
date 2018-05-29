package com.cn.ssm.service;

import java.util.List;

import com.cn.ssm.entity.Players;

public interface IRecruitDirectlyService {
	public List<Players> recruitPos(int id,String pos);
	public List<Players> recruitScore(int id);
	public List<Players> recruitSalary(int id);
}

