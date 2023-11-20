package com.sooka.module.web.system.vo;

import java.io.Serializable;

@SuppressWarnings({ ("serial") })
public class tb_zh_info implements Serializable {
	private String zh_id;
	private String zh_user;
	private String zh_pwd;
	private Integer zh_sf;

	public String getZh_id() {
		return zh_id;
	}

	public void setZh_id(String zh_id) {
		this.zh_id = zh_id;
	}

	public String getZh_user() {
		return zh_user;
	}

	public void setZh_user(String zh_user) {
		this.zh_user = zh_user;
	}

	public String getZh_pwd() {
		return zh_pwd;
	}

	public void setZh_pwd(String zh_pwd) {
		this.zh_pwd = zh_pwd;
	}

	public Integer getZh_sf() {
		return zh_sf;
	}

	public void setZh_sf(Integer zh_sf) {
		this.zh_sf = zh_sf;
	}


}