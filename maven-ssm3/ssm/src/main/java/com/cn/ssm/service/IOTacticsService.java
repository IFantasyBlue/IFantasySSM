package com.cn.ssm.service;

import java.util.List;

import com.cn.ssm.entity.OTactics;

public interface IOTacticsService {
	
	public List<OTactics> getAll();
	public OTactics getByID(int id);
}
