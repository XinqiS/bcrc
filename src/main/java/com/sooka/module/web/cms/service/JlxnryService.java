package com.sooka.module.web.cms.service;


import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sooka.common.base.BaseService;
import com.sooka.mybatis.model.Jlxnry;


public interface JlxnryService extends BaseService<Jlxnry,String> {

	List<Jlxnry> findXnryByJibanbenId(String JL_BB_ID);

	void deleteXnryByJibanbenId(String string);
}
