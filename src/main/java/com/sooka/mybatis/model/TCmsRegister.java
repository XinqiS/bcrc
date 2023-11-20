package com.sooka.mybatis.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_cms_register")
public class TCmsRegister implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer r_id;

    @Column(name = "r_name")
    private String r_name;

    @Column(name = "r_tel")
    private String r_tel;

    @Column(name = "r_idcard")
    private String r_idcard;

    @Column(name = "r_major")
    private String r_major;

    @Column(name = "r_time")
    private Date r_time;

    @Column(name = "r_state")
    private Integer r_state;

    private static final long serialVersionUID = 1L;

    public Integer getR_id() {
        return r_id;
    }

    public void setR_id(Integer r_id) {
        this.r_id = r_id;
    }

    public String getR_name() {
        return r_name;
    }

    public void setR_name(String r_name) {
        this.r_name = r_name;
    }

    public String getR_tel() {
        return r_tel;
    }

    public void setR_tel(String r_tel) {
        this.r_tel = r_tel;
    }

    public String getR_idcard() {
        return r_idcard;
    }

    public void setR_idcard(String r_idcard) {
        this.r_idcard = r_idcard;
    }

    public String getR_major() {
        return r_major;
    }

    public void setR_major(String r_major) {
        this.r_major = r_major;
    }

    public Date getR_time() {
        return r_time;
    }

    public void setR_time(Date r_time) {
        this.r_time = r_time;
    }

    public Integer getR_state() {
        return r_state;
    }

    public void setR_state(Integer r_state) {
        this.r_state = r_state;
    }
}
