package com.cn.ssm.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.dao.APPTokenMapper;
import com.cn.ssm.entity.APPToken;



@Service
@Transactional
public class APPTokenService {
	@Resource
	public APPTokenMapper tokenMapper;
	
	public int insertToken(APPToken token) {
		
		return tokenMapper.insert(token);
	}
	
	public APPToken getById(String id) {
		return tokenMapper.selectByPrimaryKey(id);
	}
	
	public void updateTime(APPToken token){
		token.setExtime(System.currentTimeMillis());
		tokenMapper.updateByPrimaryKey(token);
		return;
	}
	
	public void deleteToken(String id){
		tokenMapper.deleteByPrimaryKey(id);
		return;
	}
	
	public void deleteAllToken(){
		
	}
}
