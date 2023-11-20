package com.sooka.module.web.system.vo;

public class tb_jl_banben {
	private String jl_bb_id;
	private String jianli_id;
	private String jl_bb_name;
	private String jl_bb_time;

	public String getJl_bb_id() {
		return jl_bb_id;
	}

	public void setJl_bb_id(String jl_bb_id) {
		this.jl_bb_id = jl_bb_id;
	}

	public String getJianli_id() {
		return jianli_id;
	}

	public void setJianli_id(String jianli_id) {
		this.jianli_id = jianli_id;
	}

	public String getJl_bb_name() {
		return jl_bb_name;
	}

	public void setJl_bb_name(String jl_bb_name) {
		this.jl_bb_name = jl_bb_name;
	}

	public String getJl_bb_time() {
		return jl_bb_time;
	}

	public void setJl_bb_time(String jl_bb_time) {
		this.jl_bb_time = jl_bb_time;
	}

	@Override
	public String toString() {
		return "tb_jl_banben [jl_bb_id=" + jl_bb_id + ", jianli_id="
				+ jianli_id + ", jl_bb_name=" + jl_bb_name + ", jl_bb_time="
				+ jl_bb_time + "]";
	}
}
