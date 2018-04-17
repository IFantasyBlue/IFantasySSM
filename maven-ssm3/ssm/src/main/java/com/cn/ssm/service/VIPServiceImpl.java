package com.cn.ssm.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.dao.LineupMapper;
import com.cn.ssm.dao.VIPInfoMapper;
import com.cn.ssm.entity.VIPInfo;

@Service
@Transactional
public class VIPServiceImpl implements IVIPService {

	@Resource
    public VIPInfoMapper vipInfoMapper;
	public VIPInfo selectByPrimaryKey(int vipLevel) {
		// TODO Auto-generated method stub
		return vipInfoMapper.selectByPrimaryKey(vipLevel);
	}

}
