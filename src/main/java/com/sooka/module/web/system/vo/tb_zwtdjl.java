package com.sooka.module.web.system.vo;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_zwtdjl")
public class tb_zwtdjl {
	@Id
	private String tdjl_id;
	private String zw_id;
	private String jl_bb_id;
	private String tdjl_time;
	private Integer tdjl_tdzt;
	private Integer tdjl_mszt;
	private String tdjl_yqsj;

	public String getTdjl_yqsj() {
		return tdjl_yqsj;
	}

	public void setTdjl_yqsj(String tdjl_yqsj) {
		this.tdjl_yqsj = tdjl_yqsj;
	}

	public String getTdjl_id() {
		return tdjl_id;
	}

	public void setTdjl_id(String tdjl_id) {
		this.tdjl_id = tdjl_id;
	}

	public String getZw_id() {
		return zw_id;
	}

	public void setZw_id(String zw_id) {
		this.zw_id = zw_id;
	}

	public String getJl_bb_id() {
		return jl_bb_id;
	}

	public void setJl_bb_id(String jl_bb_id) {
		this.jl_bb_id = jl_bb_id;
	}

	public String getTdjl_time() {
		return tdjl_time;
	}

	public void setTdjl_time(String tdjl_time) {
		this.tdjl_time = tdjl_time;
	}

	public Integer getTdjl_tdzt() {
		return tdjl_tdzt;
	}

	public void setTdjl_tdzt(Integer tdjl_tdzt) {
		this.tdjl_tdzt = tdjl_tdzt;
	}

	public Integer getTdjl_mszt() {
		return tdjl_mszt;
	}

	public void setTdjl_mszt(Integer tdjl_mszt) {
		this.tdjl_mszt = tdjl_mszt;
	}

}
