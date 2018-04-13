package com.cn.ssm.service;

import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.dao.PlayersInfoMapper;
import com.cn.ssm.dao.matchingMapper;
import com.cn.ssm.entity.matching;

@Service
@Transactional
public class MatchingServiceImpl implements IMatchingService {

	@Resource
    public matchingMapper matchingMapper;
	
	public List<matching> getMatching(int level) {
		// TODO Auto-generated method stub
		return matchingMapper.getMatching(level);
	}

	public int insert(matching m) {
		// TODO Auto-generated method stub
		return matchingMapper.insert(m);
	}

	public int getMaxId() {
		// TODO Auto-generated method stub
		return matchingMapper.getMaxId();
	}

	public int delete(int id) {
		// TODO Auto-generated method stub
		return matchingMapper.deleteByPrimaryKey(id);
	}

}
