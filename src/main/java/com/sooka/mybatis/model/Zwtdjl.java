package com.sooka.mybatis.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_zwtdjl")
public class Zwtdjl {
	
	@Id
    private String tdjlId;

    private String zwId;

    private String jlBbId;

    private String tdjlTime;

    private Integer tdjlTdzt;

    private Integer tdjlMszt;

    private String tdjlYqsj;
    
    private String zhId;
    
	public String getZhId() {
		return zhId;
	}

	public void setZhId(String zhId) {
		this.zhId = zhId;
	}


    public String getTdjlId() {
        return tdjlId;
    }

    public void setTdjlId(String tdjlId) {
        this.tdjlId = tdjlId == null ? null : tdjlId.trim();
    }

    public String getZwId() {
        return zwId;
    }

    public void setZwId(String zwId) {
        this.zwId = zwId == null ? null : zwId.trim();
    }

    public String getJlBbId() {
        return jlBbId;
    }

    public void setJlBbId(String jlBbId) {
        this.jlBbId = jlBbId == null ? null : jlBbId.trim();
    }

    public String getTdjlTime() {
        return tdjlTime;
    }

    public void setTdjlTime(String tdjlTime) {
        this.tdjlTime = tdjlTime == null ? null : tdjlTime.trim();
    }

    public Integer getTdjlTdzt() {
        return tdjlTdzt;
    }

    public void setTdjlTdzt(Integer tdjlTdzt) {
        this.tdjlTdzt = tdjlTdzt;
    }

    public Integer getTdjlMszt() {
        return tdjlMszt;
    }

    public void setTdjlMszt(Integer tdjlMszt) {
        this.tdjlMszt = tdjlMszt;
    }

    public String getTdjlYqsj() {
        return tdjlYqsj;
    }

    public void setTdjlYqsj(String tdjlYqsj) {
        this.tdjlYqsj = tdjlYqsj == null ? null : tdjlYqsj.trim();
    }
}