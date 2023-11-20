package com.sooka.module.web.cms.service;


import java.util.List;


import com.sooka.common.base.BaseService;
import com.sooka.mybatis.model.Jlxnzw;


public interface JlxnzwService extends BaseService<Jlxnzw,String> {

	List<Jlxnzw> findXnzwByJibanbenId(String JL_BB_ID);

	void deleteXnzwByJibanbenId(String string);
}
