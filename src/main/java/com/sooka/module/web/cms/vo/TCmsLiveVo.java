package com.sooka.module.web.cms.vo;

import com.sooka.mybatis.model.TCmsContent;
import com.sooka.mybatis.model.TCmsLive;

/**
 * Description:live
 *
 *
 **/
public class TCmsLiveVo extends TCmsLive {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2465810050062031742L;
	private String orderField;
    private String orderDirection;
    private String categoryId;

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}
    
}
