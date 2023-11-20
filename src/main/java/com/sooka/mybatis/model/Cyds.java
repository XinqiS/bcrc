package com.sooka.mybatis.model;

import java.io.Serializable;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_cyds")
public class Cyds implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String cydsId;

    private Integer cydsHdlb;

    private String cydsXq;

    private String cydsXmmc;

    private String cydsZczb;

    private String cydsFzr;

    private String cydsByyx;

    private Integer cydsXl;

    private String cydsBynf;

    private String cydsLxdh;

    private String cydsQq;

    private String cydsYx;

    private String cydsXmjs;
    
    private String file;
    
    private String file1;
    
    private String file2;

    public String getCydsId() {
        return cydsId;
    }

    public void setCydsId(String cydsId) {
        this.cydsId = cydsId == null ? null : cydsId.trim();
    }

    public Integer getCydsHdlb() {
        return cydsHdlb;
    }

    public void setCydsHdlb(Integer cydsHdlb) {
        this.cydsHdlb = cydsHdlb;
    }

    public String getCydsXq() {
        return cydsXq;
    }

    public void setCydsXq(String cydsXq) {
        this.cydsXq = cydsXq == null ? null : cydsXq.trim();
    }

    public String getCydsXmmc() {
        return cydsXmmc;
    }

    public void setCydsXmmc(String cydsXmmc) {
        this.cydsXmmc = cydsXmmc == null ? null : cydsXmmc.trim();
    }

    public String getCydsZczb() {
        return cydsZczb;
    }

    public void setCydsZczb(String cydsZczb) {
        this.cydsZczb = cydsZczb == null ? null : cydsZczb.trim();
    }

    public String getCydsFzr() {
        return cydsFzr;
    }

    public void setCydsFzr(String cydsFzr) {
        this.cydsFzr = cydsFzr == null ? null : cydsFzr.trim();
    }

    public String getCydsByyx() {
        return cydsByyx;
    }

    public void setCydsByyx(String cydsByyx) {
        this.cydsByyx = cydsByyx == null ? null : cydsByyx.trim();
    }

    public Integer getCydsXl() {
        return cydsXl;
    }

    public void setCydsXl(Integer cydsXl) {
        this.cydsXl = cydsXl;
    }

    public String getCydsBynf() {
        return cydsBynf;
    }

    public void setCydsBynf(String cydsBynf) {
        this.cydsBynf = cydsBynf == null ? null : cydsBynf.trim();
    }

    public String getCydsLxdh() {
        return cydsLxdh;
    }

    public void setCydsLxdh(String cydsLxdh) {
        this.cydsLxdh = cydsLxdh == null ? null : cydsLxdh.trim();
    }

    public String getCydsQq() {
        return cydsQq;
    }

    public void setCydsQq(String cydsQq) {
        this.cydsQq = cydsQq == null ? null : cydsQq.trim();
    }

    public String getCydsYx() {
        return cydsYx;
    }

    public void setCydsYx(String cydsYx) {
        this.cydsYx = cydsYx == null ? null : cydsYx.trim();
    }

    public String getCydsXmjs() {
        return cydsXmjs;
    }

    public void setCydsXmjs(String cydsXmjs) {
        this.cydsXmjs = cydsXmjs == null ? null : cydsXmjs.trim();
    }

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getFile1() {
		return file1;
	}

	public void setFile1(String file1) {
		this.file1 = file1;
	}

	public String getFile2() {
		return file2;
	}

	public void setFile2(String file2) {
		this.file2 = file2;
	}

	
}