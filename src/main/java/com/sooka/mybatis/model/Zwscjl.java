package com.sooka.mybatis.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_zwscjl")
public class Zwscjl {
	
	@Id
    private String zwscjlId;

    private String zwId;

    private String jianliId;

    private String zwscjlSj;
    
    
    private String zhId;
    
    

    public String getZhId() {
		return zhId;
	}

	public void setZhId(String zhId) {
		this.zhId = zhId;
	}


    public String getZwscjlId() {
        return zwscjlId;
    }

    public void setZwscjlId(String zwscjlId) {
        this.zwscjlId = zwscjlId == null ? null : zwscjlId.trim();
    }

    public String getZwId() {
        return zwId;
    }

    public void setZwId(String zwId) {
        this.zwId = zwId == null ? null : zwId.trim();
    }

    public String getJianliId() {
        return jianliId;
    }

    public void setJianliId(String jianliId) {
        this.jianliId = jianliId == null ? null : jianliId.trim();
    }

    public String getZwscjlSj() {
        return zwscjlSj;
    }

    public void setZwscjlSj(String zwscjlSj) {
        this.zwscjlSj = zwscjlSj == null ? null : zwscjlSj.trim();
    }
}