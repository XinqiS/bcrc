package com.sooka.module.web.cms.service;


import java.util.List;


import com.sooka.common.base.BaseService;
import com.sooka.mybatis.model.Jljyjl;


public interface JljyjlService extends BaseService<Jljyjl,String> {
	
	List<Jljyjl> findJyjlByJibanbenId(String JL_BB_ID);

	List<Jljyjl> findByJibanbenId(String JL_BB_ID);

	void deleteJyjlByJibanbenId(String string);
	
}
