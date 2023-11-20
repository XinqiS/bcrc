package com.sooka.mybatis.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Table(name = "tb_zxbm")
public class Zxbm implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String zxbmId;

    private String zxbmKcmc;

    private String zxbmDwmc;

    private String zxbmDwxz;

    private String zxbmQyrs;

    private String zxbmNyye;

    private String zxbmZzc;

    private String zxbmSzdq;

    private String zxbmSshy;

    private String zxbmBgdh;

    private String zxbmCz;

    private String zxbmSj;

    private String zxbmYx;

    public String getZxbmId() {
        return zxbmId;
    }

    public void setZxbmId(String zxbmId) {
        this.zxbmId = zxbmId == null ? null : zxbmId.trim();
    }

    public String getZxbmKcmc() {
        return zxbmKcmc;
    }

    public void setZxbmKcmc(String zxbmKcmc) {
        this.zxbmKcmc = zxbmKcmc == null ? null : zxbmKcmc.trim();
    }

    public String getZxbmDwmc() {
        return zxbmDwmc;
    }

    public void setZxbmDwmc(String zxbmDwmc) {
        this.zxbmDwmc = zxbmDwmc == null ? null : zxbmDwmc.trim();
    }

    public String getZxbmDwxz() {
        return zxbmDwxz;
    }

    public void setZxbmDwxz(String zxbmDwxz) {
        this.zxbmDwxz = zxbmDwxz == null ? null : zxbmDwxz.trim();
    }

    public String getZxbmQyrs() {
        return zxbmQyrs;
    }

    public void setZxbmQyrs(String zxbmQyrs) {
        this.zxbmQyrs = zxbmQyrs == null ? null : zxbmQyrs.trim();
    }

    public String getZxbmNyye() {
        return zxbmNyye;
    }

    public void setZxbmNyye(String zxbmNyye) {
        this.zxbmNyye = zxbmNyye == null ? null : zxbmNyye.trim();
    }

    public String getZxbmZzc() {
        return zxbmZzc;
    }

    public void setZxbmZzc(String zxbmZzc) {
        this.zxbmZzc = zxbmZzc == null ? null : zxbmZzc.trim();
    }

    public String getZxbmSzdq() {
        return zxbmSzdq;
    }

    public void setZxbmSzdq(String zxbmSzdq) {
        this.zxbmSzdq = zxbmSzdq == null ? null : zxbmSzdq.trim();
    }

    public String getZxbmSshy() {
        return zxbmSshy;
    }

    public void setZxbmSshy(String zxbmSshy) {
        this.zxbmSshy = zxbmSshy == null ? null : zxbmSshy.trim();
    }

    public String getZxbmBgdh() {
        return zxbmBgdh;
    }

    public void setZxbmBgdh(String zxbmBgdh) {
        this.zxbmBgdh = zxbmBgdh == null ? null : zxbmBgdh.trim();
    }

    public String getZxbmCz() {
        return zxbmCz;
    }

    public void setZxbmCz(String zxbmCz) {
        this.zxbmCz = zxbmCz == null ? null : zxbmCz.trim();
    }

    public String getZxbmSj() {
        return zxbmSj;
    }

    public void setZxbmSj(String zxbmSj) {
        this.zxbmSj = zxbmSj == null ? null : zxbmSj.trim();
    }

    public String getZxbmYx() {
        return zxbmYx;
    }

    public void setZxbmYx(String zxbmYx) {
        this.zxbmYx = zxbmYx == null ? null : zxbmYx.trim();
    }
}