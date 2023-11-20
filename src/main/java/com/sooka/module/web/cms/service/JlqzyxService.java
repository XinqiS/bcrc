package com.sooka.module.web.cms.service;


import com.sooka.common.base.BaseService;
import com.sooka.mybatis.model.Jlqzyx;


public interface JlqzyxService extends BaseService<Jlqzyx,String> {
	
	Jlqzyx findJlqzyxByJlBanbenId(String id);

	void deleteJlqzyxByJlBanbenId(String string);

	
}
