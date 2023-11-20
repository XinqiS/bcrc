package com.sooka.mybatis.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_jl_banben")
public class Jlbanben {
	
	@Id
    private String jlBbId;

    private String jianliId;

    private String jlBbName;

    private String jlBbTime;
    
    private Integer jlBbMrtdzt;
    
    

    public Integer getJlBbMrtdzt() {
		return jlBbMrtdzt;
	}

	public void setJlBbMrtdzt(Integer jlBbMrtdzt) {
		this.jlBbMrtdzt = jlBbMrtdzt;
	}

	public String getJlBbId() {
        return jlBbId;
    }

    public void setJlBbId(String jlBbId) {
        this.jlBbId = jlBbId == null ? null : jlBbId.trim();
    }

    public String getJianliId() {
        return jianliId;
    }

    public void setJianliId(String jianliId) {
        this.jianliId = jianliId == null ? null : jianliId.trim();
    }

    public String getJlBbName() {
        return jlBbName;
    }

    public void setJlBbName(String jlBbName) {
        this.jlBbName = jlBbName == null ? null : jlBbName.trim();
    }

    public String getJlBbTime() {
        return jlBbTime;
    }

    public void setJlBbTime(String jlBbTime) {
        this.jlBbTime = jlBbTime == null ? null : jlBbTime.trim();
    }
}