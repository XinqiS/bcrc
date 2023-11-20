package com.sooka.module.web.system.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class tb_zhjhjl implements Serializable{

	
	private String jhjl_id;
	private String jhjl_zh;
	private String jhjl_key;
	private String jhjl_pwd;
	
	public String getJhjl_pwd() {
		return jhjl_pwd;
	}
	public void setJhjl_pwd(String jhjl_pwd) {
		this.jhjl_pwd = jhjl_pwd;
	}
	public String getJhjl_id() {
		return jhjl_id;
	}
	public void setJhjl_id(String jhjl_id) {
		this.jhjl_id = jhjl_id;
	}
	public String getJhjl_zh() {
		return jhjl_zh;
	}
	public void setJhjl_zh(String jhjl_zh) {
		this.jhjl_zh = jhjl_zh;
	}
	public String getJhjl_key() {
		return jhjl_key;
	}
	public void setJhjl_key(String jhjl_key) {
		this.jhjl_key = jhjl_key;
	}
	
	
	
}