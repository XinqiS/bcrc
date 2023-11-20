package com.sooka.mybatis.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_jl_qzyx")
public class Jlqzyx {
	
	@Id
    private String qzyxId;

    private String jlBbId;

    private String qzyxQwxz;

    private String qzyxDd;

    private String qzyxZn;

    private String qzyxZw;

    private String qzyxHy;

    private String qzyxGrbq;

    private Integer qzyxDgsj;

    private Integer qzyxGzlx;

    private String qzyxNsr;

	private String qzyxZwpj;

    private String qzyxJntc;

    public String getQzyxZwpj() {
        return qzyxZwpj;
    }

    public void setQzyxZwpj(String qzyxZwpj) {
        this.qzyxZwpj = qzyxZwpj == null ? null : qzyxZwpj.trim();
    }

    public String getQzyxJntc() {
        return qzyxJntc == null ? null :qzyxJntc.trim();
    }

    public void setQzyxJntc(String qzyxJntc) {
        this.qzyxJntc = qzyxJntc == null ? null : qzyxJntc.trim();
    }
    public void setQzyxId(String qzyxId) {
        this.qzyxId = qzyxId == null ? null : qzyxId.trim();
    }
	public String getQzyxId() {
        return qzyxId;
    }
    public String getJlBbId() {
        return jlBbId;
    }

    public void setJlBbId(String jlBbId) {
        this.jlBbId = jlBbId == null ? null : jlBbId.trim();
    }

    public String getQzyxQwxz() {
        return qzyxQwxz;
    }

    public void setQzyxQwxz(String qzyxQwxz) {
        this.qzyxQwxz = qzyxQwxz == null ? null : qzyxQwxz.trim();
    }

    public String getQzyxDd() {
        return qzyxDd;
    }

    public void setQzyxDd(String qzyxDd) {
        this.qzyxDd = qzyxDd == null ? null : qzyxDd.trim();
    }

    public String getQzyxZn() {
        return qzyxZn;
    }

    public void setQzyxZn(String qzyxZn) {
        this.qzyxZn = qzyxZn == null ? null : qzyxZn.trim();
    }

    public String getQzyxZw() {
        return qzyxZw;
    }

    public void setQzyxZw(String qzyxZw) {
        this.qzyxZw = qzyxZw == null ? null : qzyxZw.trim();
    }

    public String getQzyxHy() {
        return qzyxHy;
    }

    public void setQzyxHy(String qzyxHy) {
        this.qzyxHy = qzyxHy == null ? null : qzyxHy.trim();
    }

    public String getQzyxGrbq() {
        return qzyxGrbq;
    }

    public void setQzyxGrbq(String qzyxGrbq) {
        this.qzyxGrbq = qzyxGrbq == null ? null : qzyxGrbq.trim();
    }

    public Integer getQzyxDgsj() {
        return qzyxDgsj;
    }

    public void setQzyxDgsj(Integer qzyxDgsj) {
        this.qzyxDgsj = qzyxDgsj;
    }

    public Integer getQzyxGzlx() {
        return qzyxGzlx;
    }

    public void setQzyxGzlx(Integer qzyxGzlx) {
        this.qzyxGzlx = qzyxGzlx;
    }

    public String getQzyxNsr() {
        return qzyxNsr == null?null:qzyxNsr.trim();
    }

    public void setQzyxNsr(String qzyxNsr) {
        this.qzyxNsr = qzyxNsr == null ? null : qzyxNsr.trim();
    }
}