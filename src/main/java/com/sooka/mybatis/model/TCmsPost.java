package com.sooka.mybatis.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Table(name = "t_cms_post")
public class TCmsPost implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5609566319477711028L;

	@Id
    @Column(name = "post_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "SELECT LAST_INSERT_ID()")
    private Long id;
    
	@Column(name = "enterprise_id")
    private Long enterpriseId;

    @Column(name = "post_name")
    private String postName;
    
    private TCmsEnterprise enterprise;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getEnterpriseId() {
		return enterpriseId;
	}

	public void setEnterpriseId(Long enterpriseId) {
		this.enterpriseId = enterpriseId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public TCmsEnterprise getEnterprise() {
		return enterprise;
	}

	public void setEnterprise(TCmsEnterprise enterprise) {
		this.enterprise = enterprise;
	}
	
    
  
}
