package com.sooka.mybatis.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name="t_cms_pc")
public class TCmsPc implements Serializable {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;
    @Column(name="pc_name")
    private String pc_name;
    @Column(name="pc_url")
    private String pc_url;
    @Column(name="pc_state")
    private String pc_state;
    @Column(name = "pc_file")
    private String pc_file;
    @Column(name = "createtime")
    private Date createtime;
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPc_name() {
        return pc_name;
    }

    public void setPc_name(String pc_name) {
        this.pc_name = pc_name;
    }

    public String getPc_url() {
        return pc_url;
    }

    public void setPc_url(String pc_url) {
        this.pc_url = pc_url;
    }

    public String getPc_state() {
        return pc_state;
    }

    public void setPc_state(String pc_state) {
        this.pc_state = pc_state;
    }

    public String getPc_file() {
        return pc_file;
    }

    public void setPc_file(String pc_file) {
        this.pc_file = pc_file;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }
}
