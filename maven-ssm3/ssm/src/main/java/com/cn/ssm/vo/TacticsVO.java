package com.cn.ssm.vo;

import java.util.List;

import com.cn.ssm.entity.DTactics;
import com.cn.ssm.entity.OTactics;

public class TacticsVO {
	public OTactics equippedOTactics;
	public OTactics getEquippedOTactics() {
		return equippedOTactics;
	}
	public void setEquippedOTactics(OTactics equippedOTactics) {
		this.equippedOTactics = equippedOTactics;
	}
	public DTactics getEquippeddTactics() {
		return equippeddTactics;
	}
	public void setEquippeddTactics(DTactics equippeddTactics) {
		this.equippeddTactics = equippeddTactics;
	}
	public List<OTactics> getoTactics() {
		return oTactics;
	}
	public void setoTactics(List<OTactics> oTactics) {
		this.oTactics = oTactics;
	}
	public List<DTactics> getdTactics() {
		return dTactics;
	}
	public void setdTactics(List<DTactics> dTactics) {
		this.dTactics = dTactics;
	}
	public DTactics equippeddTactics;
	List<OTactics> oTactics;
	List<DTactics> dTactics;
	
}
