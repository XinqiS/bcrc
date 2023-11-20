package com.sooka.mybatis.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_jl_xnzw")
public class Jlxnzw {
	
	@Id
    private String xnzwId;

    private String jlBbId;

    private String xnzwKssj;

    private String xnzwJssj;

    private String xnzwZw;

    private String xnzwZwms;

    public String getXnzwId() {
        return xnzwId;
    }

    public void setXnzwId(String xnzwId) {
        this.xnzwId = xnzwId == null ? null : xnzwId.trim();
    }

    public String getJlBbId() {
        return jlBbId;
    }

    public void setJlBbId(String jlBbId) {
        this.jlBbId = jlBbId == null ? null : jlBbId.trim();
    }

    public String getXnzwKssj() {
        return xnzwKssj;
    }

    public void setXnzwKssj(String xnzwKssj) {
        this.xnzwKssj = xnzwKssj == null ? null : xnzwKssj.trim();
    }

    public String getXnzwJssj() {
        return xnzwJssj;
    }

    public void setXnzwJssj(String xnzwJssj) {
        this.xnzwJssj = xnzwJssj == null ? null : xnzwJssj.trim();
    }

    public String getXnzwZw() {
        return xnzwZw;
    }

    public void setXnzwZw(String xnzwZw) {
        this.xnzwZw = xnzwZw == null ? null : xnzwZw.trim();
    }

    public String getXnzwZwms() {
        return xnzwZwms;
    }

    public void setXnzwZwms(String xnzwZwms) {
        this.xnzwZwms = xnzwZwms == null ? null : xnzwZwms.trim();
    }
}