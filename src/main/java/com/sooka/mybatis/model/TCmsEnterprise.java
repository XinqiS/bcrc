package com.sooka.mybatis.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_cms_enterprise")
public class TCmsEnterprise implements Serializable {
    @Id
    @Column(name = "enterprise_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;

    @Column(name = "enterprise_name")
    private String enterpriseName;

    @Column(name = "head_portrait")
    private String headPortrait;
    
    @Column(name = "approve_state")
    private Integer approveState;
    
    @Column(name = "is_famous")
    private Integer isFamous;
    
    private static final long serialVersionUID = 1L;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEnterpriseName() {
		return enterpriseName;
	}

	public void setEnterpriseName(String enterpriseName) {
		this.enterpriseName = enterpriseName;
	}

	public String getHeadPortrait() {
		return headPortrait;
	}

	public void setHeadPortrait(String headPortrait) {
		this.headPortrait = headPortrait;
	}

	public Integer getApproveState() {
		return approveState;
	}

	public void setApproveState(Integer approveState) {
		this.approveState = approveState;
	}

	public Integer getIsFamous() {
		return isFamous;
	}

	public void setIsFamous(Integer isFamous) {
		this.isFamous = isFamous;
	}
    
}
