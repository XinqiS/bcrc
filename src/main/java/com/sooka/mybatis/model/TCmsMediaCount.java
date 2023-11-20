package com.sooka.mybatis.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_cms_mediacount")
public class TCmsMediaCount implements Serializable {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;
    private Integer callConunt;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCallConunt() {
        return callConunt;
    }

    public void setCallConunt(Integer callConunt) {
        this.callConunt = callConunt;
    }

    public Date getCallDate() {
        return callDate;
    }

    public void setCallDate(Date callDate) {
        this.callDate = callDate;
    }

    private Date callDate;


    private  String  zl;

    public String getZl() {
        return zl;
    }

    public void setZl(String zl) {
        this.zl = zl;
    }
}
