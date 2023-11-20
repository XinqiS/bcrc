package com.sooka.mybatis.model;

import javax.persistence.Id;
import javax.persistence.Table;

import com.sooka.common.annotation.ExcelField;

@Table(name = "tb_jl_info")
public class Jlinfo {
	
	@Id
    private String jianliId;

    private String zhId;
    
    @ExcelField("姓名")
    private String jianliXm;
    
    private Integer jianliXb;
    
    @ExcelField("出生日期")
    private String jianliCsrq;

    private String jianliKsgz;
    
    @ExcelField("手机")
    private String jianliSj;

    private Integer jianliQzzt;

    private String jianliJzd;

    private String jianliHk;

    private Integer jianliHyzt;

    private Integer jianliZjlx;
    
    @ExcelField("证件号码")
    private String jianliZjhm;

    private Integer jianliZzmm;

    private String jianliQtfalx;

    private String jianliQtfshm;

    private String jianliSg;
    
    @ExcelField("家庭住址")
    private String jianliJtzz;
    
    @ExcelField("性别")
    private String jianliYb;
    
    private String jianliTx;
    
    @ExcelField("邮箱")
    private String jianliYx;

    public String getJianliId() {
        return jianliId;
    }

    public void setJianliId(String jianliId) {
        this.jianliId = jianliId == null ? null : jianliId.trim();
    }

    public String getZhId() {
        return zhId;
    }

    public void setZhId(String zhId) {
        this.zhId = zhId == null ? null : zhId.trim();
    }

    public String getJianliXm() {
        return jianliXm;
    }

    public void setJianliXm(String jianliXm) {
        this.jianliXm = jianliXm == null ? null : jianliXm.trim();
    }

    public Integer getJianliXb() {
        return jianliXb;
    }

    public void setJianliXb(Integer jianliXb) {
        this.jianliXb = jianliXb;
    }

    public String getJianliCsrq() {
        return jianliCsrq;
    }

    public void setJianliCsrq(String jianliCsrq) {
        this.jianliCsrq = jianliCsrq == null ? null : jianliCsrq.trim();
    }

    public String getJianliKsgz() {
        return jianliKsgz;
    }

    public void setJianliKsgz(String jianliKsgz) {
        this.jianliKsgz = jianliKsgz == null ? null : jianliKsgz.trim();
    }

    public String getJianliSj() {
        return jianliSj;
    }

    public void setJianliSj(String jianliSj) {
        this.jianliSj = jianliSj == null ? null : jianliSj.trim();
    }

    public Integer getJianliQzzt() {
        return jianliQzzt;
    }

    public void setJianliQzzt(Integer jianliQzzt) {
        this.jianliQzzt = jianliQzzt;
    }

    public String getJianliJzd() {
        return jianliJzd;
    }

    public void setJianliJzd(String jianliJzd) {
        this.jianliJzd = jianliJzd == null ? null : jianliJzd.trim();
    }

    public String getJianliHk() {
        return jianliHk;
    }

    public void setJianliHk(String jianliHk) {
        this.jianliHk = jianliHk == null ? null : jianliHk.trim();
    }

    public Integer getJianliHyzt() {
        return jianliHyzt;
    }

    public void setJianliHyzt(Integer jianliHyzt) {
        this.jianliHyzt = jianliHyzt;
    }

    public Integer getJianliZjlx() {
        return jianliZjlx;
    }

    public void setJianliZjlx(Integer jianliZjlx) {
        this.jianliZjlx = jianliZjlx;
    }

    public String getJianliZjhm() {
        return jianliZjhm;
    }

    public void setJianliZjhm(String jianliZjhm) {
        this.jianliZjhm = jianliZjhm == null ? null : jianliZjhm.trim();
    }

    public Integer getJianliZzmm() {
        return jianliZzmm;
    }

    public void setJianliZzmm(Integer jianliZzmm) {
        this.jianliZzmm = jianliZzmm;
    }

    public String getJianliQtfalx() {
        return jianliQtfalx;
    }

    public void setJianliQtfalx(String jianliQtfalx) {
        this.jianliQtfalx = jianliQtfalx == null ? null : jianliQtfalx.trim();
    }

    public String getJianliQtfshm() {
        return jianliQtfshm;
    }

    public void setJianliQtfshm(String jianliQtfshm) {
        this.jianliQtfshm = jianliQtfshm == null ? null : jianliQtfshm.trim();
    }

    public String getJianliSg() {
        return jianliSg;
    }

    public void setJianliSg(String jianliSg) {
        this.jianliSg = jianliSg == null ? null : jianliSg.trim();
    }

    public String getJianliJtzz() {
        return jianliJtzz;
    }

    public void setJianliJtzz(String jianliJtzz) {
        this.jianliJtzz = jianliJtzz == null ? null : jianliJtzz.trim();
    }

    public String getJianliYb() {
        return jianliYb;
    }

    public void setJianliYb(String jianliYb) {
        this.jianliYb = jianliYb == null ? null : jianliYb.trim();
    }

	public String getJianliTx() {
		return jianliTx;
	}

	public void setJianliTx(String jianliTx) {
		this.jianliTx = jianliTx;
	}

	public String getJianliYx() {
		return jianliYx;
	}

	public void setJianliYx(String jianliYx) {
		this.jianliYx = jianliYx;
	}
	
}