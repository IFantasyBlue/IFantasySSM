package com.cn.ssm.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.cn.ssm.dao.PhoneLoginMapper;
import com.cn.ssm.dao.UserMapper;
import com.cn.ssm.entity.PhoneLogin;
import com.cn.ssm.entity.User;

@Service
@Transactional
public class UserServiceImpl implements IUserService {

	@Resource
    public UserMapper userMapper;
	@Resource
	public PhoneLoginMapper phoneLoginMapper;
	
	
	public User getById(int id) {
		// TODO Auto-generated method stub
		User user = new User();
		
		user = userMapper.selectByPrimaryKey(id);
		return user;
	}
	
	public User getByPhone(String mobile){
		User user = new User();
		
		user = userMapper.selectByPhone(mobile);		
		return user;
	}

	public int updateUserByMoney(User user) {
		// TODO Auto-generated method stub
		return userMapper.updateByPrimaryKey(user);
	}
	public int insertNewUser(User user){
		
		return userMapper.insert(user);
	}
	
	public Integer getUserNumber(){
		return userMapper.getUserNumber();
	}
	
	public SendSmsResponse AliMessagerSend(String mobile){
		AliMessageSend aliMessageSend = new AliMessageSend(mobile);
		SendSmsResponse ssr = null;
		try {
			PhoneLogin phoneLogin = new PhoneLogin();
			phoneLogin.setPhone(mobile);
			phoneLogin.setAkey(Integer.parseInt(aliMessageSend.Akey));
			
			if(phoneLoginMapper.selectByPrimaryKey(mobile)!=null){
				phoneLoginMapper.updateByPrimaryKey(phoneLogin);
			}else{
				phoneLoginMapper.insert(phoneLogin);
			}
			ssr = aliMessageSend.sendSms();
            
        } catch (ClientException e) {
            e.printStackTrace();
        }
		return ssr;
	}
	
	public boolean checkAkey(String mobile ,int aKey){
		if(aKey==phoneLoginMapper.selectByPrimaryKey(mobile).getAkey())return true;
		else return false;
	}
	
	public List<User> getPowerTop20(){
		return userMapper.selectPowerTop20();
	}
	
}
