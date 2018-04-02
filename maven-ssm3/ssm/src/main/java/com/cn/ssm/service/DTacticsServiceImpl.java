package com.cn.ssm.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.dao.DTacticsMapper;
import com.cn.ssm.entity.DTactics;


@Service
@Transactional
public class DTacticsServiceImpl implements IDTacticsService {

	@Resource
    public DTacticsMapper dtacticsMapper;
	
	public List<DTactics> getAll() {
		// TODO Auto-generated method stub
		List <DTactics> dTactics = new ArrayList<DTactics>();
		
		dTactics = dtacticsMapper.getAll();
		return dTactics;
	}

	public DTactics getByID(int id) {
		// TODO Auto-generated method stub
		return dtacticsMapper.selectByPrimaryKey(id);
	}

}
