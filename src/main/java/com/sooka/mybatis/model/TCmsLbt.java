package com.sooka.mybatis.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_cms_lbt")
public class TCmsLbt implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer lbt_id;

    @Column(name = "title")
    private String title;

    @Column(name = "lbt_time")
    private Date lbt_time;

    @Column(name = "publisher")
    private String publisher;

    @Column(name = "img")
    private String img;

    @Column(name = "video")
    private String video;

    @Column(name = "flag")
    private Integer flag;

    private static final long serialVersionUID = 1L;

    public Integer getLbt_id() {
        return lbt_id;
    }

    public void setLbt_id(Integer lbt_id) {
        this.lbt_id = lbt_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getLbt_time() {
        return lbt_time;
    }

    public void setLbt_time(Date lpt_time) {
        this.lbt_time = lbt_time;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }
}
