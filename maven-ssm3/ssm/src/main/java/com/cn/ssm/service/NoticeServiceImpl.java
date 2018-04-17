package com.cn.ssm.service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.cn.ssm.dao.NoticeMapper;
import com.cn.ssm.dao.Players_StatsMapper;
import com.cn.ssm.entity.Notice;

@Service
@Transactional
public class NoticeServiceImpl implements INoticeService {

	@Resource
    public NoticeMapper noticeMapper;
	
	public Notice getByUser2Id(int user2_id) {
		// TODO Auto-generated method stub
		return noticeMapper.getByUser2Id(user2_id);
	}

	public int insert(Notice notice) {
		// TODO Auto-generated method stub
		return noticeMapper.insertSelective(notice);
	}

	public Notice getByUser1Id(int user1_id, int user2_id) {
		// TODO Auto-generated method stub
		return noticeMapper.getByUser1Id(user1_id, user2_id);
	}

	public int update(Notice notice) {
		// TODO Auto-generated method stub
		return noticeMapper.updateByPrimaryKeySelective(notice);
	}

}
