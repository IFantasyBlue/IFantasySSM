package com.cn.ssm.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.dao.*;
import com.cn.ssm.entity.Lineup;


@Service
@Transactional
public class LineupServiceImpl implements ILineupService {

	@Resource
    public LineupMapper lineupMapper;
	
	public Lineup getById(int id) {
		// TODO Auto-generated method stub
		return lineupMapper.selectByPrimaryKey(id);
	}

}
