package com.cn.ssm.service;

import java.util.List;

import com.cn.ssm.entity.matching;

public interface IMatchingService {

	public List<matching> getMatching(int level);
	public int insert(matching m);
	public int getMaxId();
	public int delete(int id);
}
