package com.sooka.mybatis.model;

import com.sooka.common.annotation.ExcelField;

import java.io.Serializable;
import java.util.Date;

public class TSysLog implements Serializable {
    @ExcelField("日志编号")
    private Integer id;

    private String content;

    private Date createtime;

    @ExcelField("日期")
    private String createTime;

    @ExcelField("用户名")
    private String username;

    @ExcelField("类型")
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}