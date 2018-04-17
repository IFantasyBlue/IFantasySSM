package com.cn.ssm.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.dao.OTacticsMapper;
import com.cn.ssm.entity.OTactics;

@Service
@Transactional
public class OTacticsServiceImpl implements IOTacticsService {

	@Resource
    public OTacticsMapper otacticsMapper;
	
	public List<OTactics> getAll() {
		// TODO Auto-generated method stub
		List <OTactics> oTactics = new ArrayList<OTactics>();
		
		oTactics = otacticsMapper.getAll();
		return oTactics;
	}

	public OTactics getByID(int id) {
		// TODO Auto-generated method stub
		return otacticsMapper.selectByPrimaryKey(id);
	}
	
	
}
