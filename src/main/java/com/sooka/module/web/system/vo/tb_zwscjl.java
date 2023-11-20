package com.sooka.module.web.system.vo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_zwscjl")
public class tb_zwscjl {

	@Id
	private String zwscjl_id;
	private  String zw_id;
	private String jianli_id;
	private String zwscjl_sj;
	private String zh_id;

	public String getZh_id() {
		return zh_id;
	}

	public void setZh_id(String zh_id) {
		this.zh_id = zh_id;
	}
	public String getZwscjl_id() {
		return zwscjl_id;
	}
	public void setZwscjl_id(String zwscjl_id) {
		this.zwscjl_id = zwscjl_id;
	}
	public String getZw_id() {
		return zw_id;
	}
	public void setZw_id(String zw_id) {
		this.zw_id = zw_id;
	}
	public String getJianli_id() {
		return jianli_id;
	}
	public void setJianli_id(String jianli_id) {
		this.jianli_id = jianli_id;
	}
	public String getZwscjl_sj() {
		return zwscjl_sj;
	}
	public void setZwscjl_sj(String zwscjl_sj) {
		this.zwscjl_sj = zwscjl_sj;
	}
	@Override
	public String toString() {
		return "tb_zwscjl [zwscjl_id=" + zwscjl_id + ", zw_id=" + zw_id
				+ ", jianli_id=" + jianli_id + ", zwscjl_sj=" + zwscjl_sj
				+ ", zh_id=" + zh_id + "]";
	}
}
