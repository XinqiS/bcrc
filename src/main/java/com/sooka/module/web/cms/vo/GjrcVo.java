package com.sooka.module.web.cms.vo;

public class GjrcVo {
	private String jl_bb_id;
	private String zh_id;
	private String jianli_xm;
	private Integer jianli_xb;
	private String jianli_csrq;
	private String qzyx_jntc;
	private String jyjl_zy;
	private String jyjl_xx;
	private Integer jyjl_xl;

	public String getJl_bb_id() {
		return jl_bb_id;
	}

	public void setJl_bb_id(String jl_bb_id) {
		this.jl_bb_id = jl_bb_id;
	}

	public String getZh_id() {
		return zh_id;
	}

	public void setZh_id(String zh_id) {
		this.zh_id = zh_id;
	}

	public String getJianli_xm() {
		return jianli_xm;
	}

	public void setJianli_xm(String jianli_xm) {
		this.jianli_xm = jianli_xm;
	}

	public Integer getJianli_xb() {
		return jianli_xb;
	}

	public void setJianli_xb(Integer jianli_xb) {
		this.jianli_xb = jianli_xb;
	}

	public String getJianli_csrq() {
		return jianli_csrq;
	}

	public void setJianli_csrq(String jianli_csrq) {
		this.jianli_csrq = jianli_csrq;
	}

	public String getQzyx_jntc() {
		return qzyx_jntc;
	}

	public void setQzyx_jntc(String qzyx_jntc) {
		this.qzyx_jntc = qzyx_jntc;
	}

	public String getJyjl_zy() {
		return jyjl_zy;
	}

	public void setJyjl_zy(String jyjl_zy) {
		this.jyjl_zy = jyjl_zy;
	}

	public String getJyjl_xx() {
		return jyjl_xx;
	}

	public void setJyjl_xx(String jyjl_xx) {
		this.jyjl_xx = jyjl_xx;
	}

	public Integer getJyjl_xl() {
		return jyjl_xl;
	}

	public void setJyjl_xl(Integer jyjl_xl) {
		this.jyjl_xl = jyjl_xl;
	}

	@Override
	public String toString() {
		return "GjrcVo [jl_bb_id=" + jl_bb_id + ", zh_id=" + zh_id
				+ ", jianli_xm=" + jianli_xm + ", jianli_xb=" + jianli_xb
				+ ", jianli_csrq=" + jianli_csrq + ", qzyx_jntc=" + qzyx_jntc
				+ ", jyjl_zy=" + jyjl_zy + ", jyjl_xx=" + jyjl_xx
				+ ", jyjl_xl=" + jyjl_xl + "]";
	}

}
