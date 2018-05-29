package com.cn.ssm.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.entity.Goods;
import com.cn.ssm.entity.Players;
import com.cn.ssm.dao.GoodsMapper;
import com.cn.ssm.dao.PlayersMapper;
import com.cn.ssm.dao.UserMapper;

@Service
@Transactional
public class IRecruitDirectlyServiceImpl implements IRecruitDirectlyService {
	
	@Resource
	public PlayersMapper playerMapper;
	@Resource
	public UserMapper userMapper;

	@Override
	public List<Players> recruitPos(int id, String pos) {
	
			List<Players> playerList =playerMapper.selectByPosition(pos);
			
			userMapper.updateMoney(3000000, id);

			return playerList;
		
	}

	@Override
	public List<Players> recruitScore(int id) {
		
		List<Players> playerList =playerMapper.selectByScore();
		
		userMapper.updateMoney(3000000, id);

		return playerList;
	}

	@Override
	public List<Players> recruitSalary(int id) {
		
		List<Players> playerList =playerMapper.selectBySalary();
		
		userMapper.updateMoney(3000000, id);

		return playerList;
	}


}
