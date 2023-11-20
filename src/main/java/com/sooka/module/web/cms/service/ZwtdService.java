package com.sooka.module.web.cms.service;




import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.sooka.common.base.BaseService;
import com.sooka.mybatis.model.Zwscjl;
import com.sooka.mybatis.model.Zwtdjl;


public interface ZwtdService extends BaseService<Zwtdjl,String> {
	
	PageInfo<Map<String, Object>> page(Integer pageNumber, Integer pageSize, Map<String, Object> map);
	PageInfo<Map<String, Object>> pageAll(Integer pageNumber, Integer pageSize, Map<String, Object> map);


	int findWdsqCountByZhId(String zh_id);

	void deleteZwtdjlByJibanbenId(String string);

	void deleteZhiId(String zhiId);

	List<Map<String,Object>> findZwtdjlByZhIdquery(Map<String,Object> map);
}
