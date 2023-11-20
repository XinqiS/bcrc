package com.sooka.module.web.cms.service;


import java.util.List;


import com.sooka.common.base.BaseService;
import com.sooka.mybatis.model.Jlgzjy;

public interface JlgzjyService extends BaseService<Jlgzjy,String> {
	
	List<Jlgzjy> findGzjyByJibanbenId(String JL_BB_ID);

	List<Jlgzjy> findByJibanbenId(String JL_BB_ID);

	void deleteGzjyByJibanbenId(String string);
	
}
