package com.sooka.mybatis.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_jl_gzjy")
public class Jlgzjy {
	
	@Id
    private String gzjyId;

    private String jlBbId;

    private String gzjyKssj;

    private String gzjyJssj;

    private String gzjyGs;

    private String gzjyZn;

    private String gzjyHy;

    private String gzjyZw;

    private Integer gzjyGsgm;

    private String gzjyBm;

    private Integer gzjyGsxz;

    private Integer gzjyGzlx;

    private String gzjyXsrs;

    private String gzjyHbdx;

    private String gzjyLzyy;

    private Integer gzjyHwjl;

    private String gzjyGzms;

    private String gzjyZyyj;

    public String getGzjyGzms() {
        return gzjyGzms;
    }

    public void setGzjyGzms(String gzjyGzms) {
        this.gzjyGzms = gzjyGzms == null ? null : gzjyGzms.trim();
    }


    public String getGzjyZyyj() {
		return gzjyZyyj;
	}

	public void setGzjyZyyj(String gzjyZyyj) {
		this.gzjyZyyj = gzjyZyyj;
	}

	public String getGzjyId() {
        return gzjyId;
    }

    public void setGzjyId(String gzjyId) {
        this.gzjyId = gzjyId == null ? null : gzjyId.trim();
    }

    public String getJlBbId() {
        return jlBbId;
    }

    public void setJlBbId(String jlBbId) {
        this.jlBbId = jlBbId == null ? null : jlBbId.trim();
    }

    public String getGzjyKssj() {
        return gzjyKssj;
    }

    public void setGzjyKssj(String gzjyKssj) {
        this.gzjyKssj = gzjyKssj == null ? null : gzjyKssj.trim();
    }

    public String getGzjyJssj() {
        return gzjyJssj;
    }

    public void setGzjyJssj(String gzjyJssj) {
        this.gzjyJssj = gzjyJssj == null ? null : gzjyJssj.trim();
    }

    public String getGzjyGs() {
        return gzjyGs;
    }

    public void setGzjyGs(String gzjyGs) {
        this.gzjyGs = gzjyGs == null ? null : gzjyGs.trim();
    }

    public String getGzjyZn() {
        return gzjyZn;
    }

    public void setGzjyZn(String gzjyZn) {
        this.gzjyZn = gzjyZn == null ? null : gzjyZn.trim();
    }

    public String getGzjyHy() {
        return gzjyHy;
    }

    public void setGzjyHy(String gzjyHy) {
        this.gzjyHy = gzjyHy == null ? null : gzjyHy.trim();
    }

    public String getGzjyZw() {
        return gzjyZw;
    }

    public void setGzjyZw(String gzjyZw) {
        this.gzjyZw = gzjyZw == null ? null : gzjyZw.trim();
    }

    public Integer getGzjyGsgm() {
        return gzjyGsgm;
    }

    public void setGzjyGsgm(Integer gzjyGsgm) {
        this.gzjyGsgm = gzjyGsgm;
    }

    public String getGzjyBm() {
        return gzjyBm;
    }

    public void setGzjyBm(String gzjyBm) {
        this.gzjyBm = gzjyBm == null ? null : gzjyBm.trim();
    }

    public Integer getGzjyGsxz() {
        return gzjyGsxz;
    }

    public void setGzjyGsxz(Integer gzjyGsxz) {
        this.gzjyGsxz = gzjyGsxz;
    }

    public Integer getGzjyGzlx() {
        return gzjyGzlx;
    }

    public void setGzjyGzlx(Integer gzjyGzlx) {
        this.gzjyGzlx = gzjyGzlx;
    }

    public String getGzjyXsrs() {
        return gzjyXsrs;
    }

    public void setGzjyXsrs(String gzjyXsrs) {
        this.gzjyXsrs = gzjyXsrs == null ? null : gzjyXsrs.trim();
    }

    public String getGzjyHbdx() {
        return gzjyHbdx;
    }

    public void setGzjyHbdx(String gzjyHbdx) {
        this.gzjyHbdx = gzjyHbdx == null ? null : gzjyHbdx.trim();
    }

    public String getGzjyLzyy() {
        return gzjyLzyy;
    }

    public void setGzjyLzyy(String gzjyLzyy) {
        this.gzjyLzyy = gzjyLzyy == null ? null : gzjyLzyy.trim();
    }

    public Integer getGzjyHwjl() {
        return gzjyHwjl;
    }

    public void setGzjyHwjl(Integer gzjyHwjl) {
        this.gzjyHwjl = gzjyHwjl;
    }
}