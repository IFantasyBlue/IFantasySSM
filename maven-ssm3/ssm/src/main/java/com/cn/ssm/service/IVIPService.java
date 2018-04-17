package com.cn.ssm.service;

import com.cn.ssm.entity.VIPInfo;

public interface IVIPService {

	public VIPInfo selectByPrimaryKey(int vipLevel);
}
