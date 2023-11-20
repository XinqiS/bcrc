package com.sooka.module.web.cms.service;


import com.sooka.common.base.BaseService;
import com.sooka.mybatis.model.Jlbanben;


public interface JlbanbenService extends BaseService<Jlbanben,String> {

	
	void updateJlbanbenMRtdzt(String jianliId);

	String delete(String zh_id, Integer zh_sf);
	void deletejlBBid(String bbid);

	
}
