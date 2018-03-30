package com.cn.ssm.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import com.cn.ssm.dao.OTacticsMapper;
import com.cn.ssm.entity.OTactics;

@Resource
@Transactional
public class OTacticsServiceImpl implements IOTacticsService {

	@Resource
	public OTacticsMapper oTacticsMapper;
	
	public List<OTactics> getAll() {
		// TODO Auto-generated method stub
		return oTacticsMapper.getAll();
	}

}
