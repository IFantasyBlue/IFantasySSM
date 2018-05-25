package com.cn.ssm.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.dao.SettingMapper;

import com.cn.ssm.entity.Setting;

@Service
@Transactional
public class SettingServiceImpl implements ISettingService {
	
	@Resource
	private SettingMapper settingMapper;
	
	
	public Setting getById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public int insertSetting(Setting setting){
		
		return settingMapper.insert(setting);
	}
	
}
