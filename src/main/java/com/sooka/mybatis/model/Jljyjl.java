package com.sooka.mybatis.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_jl_jyjl")
public class Jljyjl {
	
	@Id
    private String jyjlId;

    private String jlBbId;

    private String jyjlKssj;

    private String jyjlJssj;

    private String jyjlXx;

    private Integer jyjlXl;

    private String Xl;

    private String jyjlZy;

    private Integer jyjlQrz;

    private Integer jyjlHwjl;

    private String jyjlZyms;

    public String getJyjlId() {
        return jyjlId;
    }

    public void setJyjlId(String jyjlId) {
        this.jyjlId = jyjlId == null ? null : jyjlId.trim();
    }

    public String getJlBbId() {
        return jlBbId;
    }

    public void setJlBbId(String jlBbId) {
        this.jlBbId = jlBbId == null ? null : jlBbId.trim();
    }

    public String getJyjlKssj() {
        return jyjlKssj;
    }

    public void setJyjlKssj(String jyjlKssj) {
        this.jyjlKssj = jyjlKssj == null ? null : jyjlKssj.trim();
    }

    public String getJyjlJssj() {
        return jyjlJssj;
    }

    public void setJyjlJssj(String jyjlJssj) {
        this.jyjlJssj = jyjlJssj == null ? null : jyjlJssj.trim();
    }

    public String getJyjlXx() {
        return jyjlXx;
    }

    public void setJyjlXx(String jyjlXx) {
        this.jyjlXx = jyjlXx == null ? null : jyjlXx.trim();
    }

    public Integer getJyjlXl() {
        return jyjlXl;
    }

    public void setJyjlXl(Integer jyjlXl) {
        this.jyjlXl = jyjlXl;
    }

    public String getJyjlZy() {
        return jyjlZy;
    }

    public void setJyjlZy(String jyjlZy) {
        this.jyjlZy = jyjlZy == null ? null : jyjlZy.trim();
    }

    public Integer getJyjlQrz() {
        return jyjlQrz;
    }

    public void setJyjlQrz(Integer jyjlQrz) {
        this.jyjlQrz = jyjlQrz;
    }

    public Integer getJyjlHwjl() {
        return jyjlHwjl;
    }

    public void setJyjlHwjl(Integer jyjlHwjl) {
        this.jyjlHwjl = jyjlHwjl;
    }

    public String getJyjlZyms() {
        return jyjlZyms;
    }

    public void setJyjlZyms(String jyjlZyms) {
        this.jyjlZyms = jyjlZyms == null ? null : jyjlZyms.trim();
    }

    public String getXl() {
        return Xl;
    }

    public void setXl(String xl) {
        Xl = xl;
    }
}