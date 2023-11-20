package com.sooka.mybatis.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_jllljl")
public class Jllljl {
	
	@Id
    private String jlllId;

    private String qyId;

    private String jlBbId;

    private String jlllSj;
    

	public String getJlllId() {
        return jlllId;
    }

    public void setJlllId(String jlllId) {
        this.jlllId = jlllId == null ? null : jlllId.trim();
    }

    public String getQyId() {
        return qyId;
    }

    public void setQyId(String qyId) {
        this.qyId = qyId;
    }

    public String getJlBbId() {
        return jlBbId;
    }

    public void setJlBbId(String jlBbId) {
        this.jlBbId = jlBbId;
    }

    public String getJlllSj() {
        return jlllSj;
    }

    public void setJlllSj(String jlllSj) {
        this.jlllSj = jlllSj == null ? null : jlllSj.trim();
    }
}