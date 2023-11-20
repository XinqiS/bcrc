package com.sooka.mybatis.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_cms_content_live")
public class TCmsLive implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Integer id;
    
    private Integer content_id;

    @Column(name = "speakName")
    private String speakName;

    @Column(name = "speakContent")
    private String speakContent;

    @Column(name = "speakDate")
    private Date speakDate;

    @Column(name = "sort")
    private Integer sort;

    @Column(name = "userType")
    private Integer userType;
    
    private static final long serialVersionUID = 1L;


	public Integer getContent_id() {
		return content_id;
	}


	public void setContent_id(Integer content_id) {
		this.content_id = content_id;
	}


	public String getSpeakName() {
		return speakName;
	}


	public void setSpeakName(String speakName) {
		this.speakName = speakName;
	}


	public String getSpeakContent() {
		return speakContent;
	}


	public void setSpeakContent(String speakContent) {
		this.speakContent = speakContent;
	}


	public Date getSpeakDate() {
		return speakDate;
	}


	public void setSpeakDate(Date speakDate) {
		this.speakDate = speakDate;
	}


	public Integer getSort() {
		return sort;
	}


	public void setSort(Integer sort) {
		this.sort = sort;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getUserType() {
		return userType;
	}


	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	
    
}
