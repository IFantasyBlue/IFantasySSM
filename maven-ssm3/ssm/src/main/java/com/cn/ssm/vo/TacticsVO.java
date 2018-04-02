package com.cn.ssm.vo;

import com.cn.ssm.entity.DTactics;
import com.cn.ssm.entity.OTactics;

public class TacticsVO {
	public OTactics oTactics;
	public OTactics getoTactics() {
		return oTactics;
	}
	public void setoTactics(OTactics oTactics) {
		this.oTactics = oTactics;
	}
	public DTactics getdTactics() {
		return dTactics;
	}
	public void setdTactics(DTactics dTactics) {
		this.dTactics = dTactics;
	}
	public DTactics dTactics;
}
