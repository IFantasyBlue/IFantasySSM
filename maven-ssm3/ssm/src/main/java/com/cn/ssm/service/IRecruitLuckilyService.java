package com.cn.ssm.service;

import com.cn.ssm.entity.Goods;

public interface IRecruitLuckilyService {
	public Goods recruitFree(int id);
	public Goods recruitOnce(int id);
	public Goods recruitFive(int id);
}
