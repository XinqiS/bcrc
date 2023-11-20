package com.sooka.mybatis.model;

import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "tb_jl_xnry")
public class Jlxnry {
	
	@Id
    private String xnryId;

    private String jlBbId;

    private String xnrySj;

    private String xnryJx;

    private String xnryJb;

    public String getXnryId() {
        return xnryId;
    }

    public void setXnryId(String xnryId) {
        this.xnryId = xnryId == null ? null : xnryId.trim();
    }

    public String getJlBbId() {
        return jlBbId;
    }

    public void setJlBbId(String jlBbId) {
        this.jlBbId = jlBbId == null ? null : jlBbId.trim();
    }

    public String getXnrySj() {
        return xnrySj;
    }

    public void setXnrySj(String xnrySj) {
        this.xnrySj = xnrySj == null ? null : xnrySj.trim();
    }

    public String getXnryJx() {
        return xnryJx;
    }

    public void setXnryJx(String xnryJx) {
        this.xnryJx = xnryJx == null ? null : xnryJx.trim();
    }

    public String getXnryJb() {
        return xnryJb;
    }

    public void setXnryJb(String xnryJb) {
        this.xnryJb = xnryJb == null ? null : xnryJb.trim();
    }
}