package com.sooka.module.web.cms.service;


import com.sooka.common.base.BaseService;
import com.sooka.mybatis.model.Jlinfo;


public interface JlinfoService extends BaseService<Jlinfo,String> {

	Jlinfo findByUserCodeId(String id);

	void deleteByUserCodeId(String zh_id);

	
}
