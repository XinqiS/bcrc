package com.sooka.mybatis.model;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_lyfk")
public class Lyfk  implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	private String lyfkId;

    private Integer lyfkLb;

    private String lyfkYx;

    private String lyfkDh;

    private String lyfkQq;

    private String lyfkBt;

    private String lyfkNr;
    
    private String lyfkFknr;
    
    private String lyfkFksj;

    public String getLyfkId() {
        return lyfkId;
    }

    public void setLyfkId(String lyfkId) {
        this.lyfkId = lyfkId == null ? null : lyfkId.trim();
    }

    public Integer getLyfkLb() {
        return lyfkLb;
    }

    public void setLyfkLb(Integer lyfkLb) {
        this.lyfkLb = lyfkLb;
    }

    public String getLyfkYx() {
        return lyfkYx;
    }

    public void setLyfkYx(String lyfkYx) {
        this.lyfkYx = lyfkYx == null ? null : lyfkYx.trim();
    }

    public String getLyfkDh() {
        return lyfkDh;
    }

    public void setLyfkDh(String lyfkDh) {
        this.lyfkDh = lyfkDh == null ? null : lyfkDh.trim();
    }

    public String getLyfkQq() {
        return lyfkQq;
    }

    public void setLyfkQq(String lyfkQq) {
        this.lyfkQq = lyfkQq == null ? null : lyfkQq.trim();
    }

    public String getLyfkBt() {
        return lyfkBt;
    }

    public void setLyfkBt(String lyfkBt) {
        this.lyfkBt = lyfkBt == null ? null : lyfkBt.trim();
    }

    public String getLyfkNr() {
        return lyfkNr;
    }

    public void setLyfkNr(String lyfkNr) {
        this.lyfkNr = lyfkNr == null ? null : lyfkNr.trim();
    }

	public String getLyfkFknr() {
		return lyfkFknr;
	}

	public void setLyfkFknr(String lyfkFknr) {
		this.lyfkFknr = lyfkFknr;
	}

	public String getLyfkFksj() {
		return lyfkFksj;
	}

	public void setLyfkFksj(String lyfkFksj) {
		this.lyfkFksj = lyfkFksj;
	}
    
}