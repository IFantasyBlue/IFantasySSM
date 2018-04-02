package com.cn.ssm.service;

import java.util.List;

import com.cn.ssm.entity.DTactics;

public interface IDTacticsService {
	
	public List<DTactics> getAll();
	public DTactics getByID(int id);
}
