package com.sooka.module.web.cms.service;




import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.sooka.common.base.BaseService;
import com.sooka.mybatis.model.Zwscjl;


public interface ZwscjlService extends BaseService<Zwscjl,String> {
	
	PageInfo<Map<String, Object>> page(Integer pageNumber, Integer pageSize, Map<String, Object> map);

	int findWdscCountByZhId(String zh_id);

	List<Map<String,Object>> findZwscjlByZhIdquery(Map<String,Object> map);
	void deleteZhiId(String zhiId);
}
