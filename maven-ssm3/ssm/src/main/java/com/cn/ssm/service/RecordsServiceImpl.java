package com.cn.ssm.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.dao.RecordsMapper;
import com.cn.ssm.dao.matchingMapper;
import com.cn.ssm.entity.Records;

@Service
@Transactional
public class RecordsServiceImpl implements IRecordsService {

	@Resource
    public RecordsMapper recordsMapper;
	
	public Records getRecordsById(int user_id) {
		// TODO Auto-generated method stub
		return recordsMapper.selectByUserId(user_id);
	}

	public int insert(Records records) {
		// TODO Auto-generated method stub
		return recordsMapper.insertSelective(records);
	}

	public int delete(Records records) {
		// TODO Auto-generated method stub
		return recordsMapper.deleteByPrimaryKey(records.getId());
	}

	public Records getRecordsById2(int user_id) {
		// TODO Auto-generated method stub
		return recordsMapper.selectByUserId2(user_id);
	}

}
