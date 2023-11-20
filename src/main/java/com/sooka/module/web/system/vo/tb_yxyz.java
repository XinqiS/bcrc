package com.sooka.module.web.system.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import org.junit.Test;

import com.sooka.common.utils.StrUtil;
@SuppressWarnings({ ("serial") })
public class tb_yxyz implements Serializable{
	private String yz_id;
	private String yz_yzm;
	private Timestamp yz_yzsj;
	public String getYz_yx() {
		return yz_yx;
	}
	public void setYz_yx(String yz_yx) {
		this.yz_yx = yz_yx;
	}
	private String yz_yx;
	public String getYz_id() {
		return yz_id;
	}
	public void setYz_id(String yz_id) {
		this.yz_id = yz_id;
	}
	public String getYz_yzm() {
		return yz_yzm;
	}
	public void setYz_yzm(String yz_yzm) {
		this.yz_yzm = yz_yzm;
	}
	public Timestamp getYz_yzsj() {
		return yz_yzsj;
	}
	public void setYz_yzsj(Timestamp yz_yzsj) {
		this.yz_yzsj = yz_yzsj;
	}
	


}
