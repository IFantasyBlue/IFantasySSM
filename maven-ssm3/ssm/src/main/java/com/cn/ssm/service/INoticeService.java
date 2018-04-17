package com.cn.ssm.service;

import com.cn.ssm.entity.Notice;

public interface INoticeService {

	public Notice getByUser2Id(int user2_id);
	public int insert(Notice notice);
	public Notice getByUser1Id(int user1_id,int user2_id);
	public int update(Notice notice);
}
