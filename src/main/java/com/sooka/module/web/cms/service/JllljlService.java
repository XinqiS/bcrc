package com.sooka.module.web.cms.service;




import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.sooka.common.base.BaseService;
import com.sooka.mybatis.model.Jllljl;


public interface JllljlService extends BaseService<Jllljl,String> {
	
	List<Map<String,Object>> findJllljlByZhId(Map<String,Object> map);

	PageInfo<Map<String, Object>> page(Integer pageNumber, Integer pageSize, Map<String, Object> map);

	int findSkgwCountByZhId(String zh_id);

	void deleteJllljlByJibanbenId(String string);
}
