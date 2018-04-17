package com.cn.ssm.service;

import com.cn.ssm.entity.Records;

public interface IRecordsService {

	public int insert(Records records);
	public Records getRecordsById(int user_id);
	public Records getRecordsById2(int user_id);
	public int delete(Records records);
} 
