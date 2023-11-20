package com.sooka.module.web.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.sooka.common.base.BaseService;
import com.sooka.module.web.cms.vo.TCmsLiveVo;
import com.sooka.mybatis.model.TCmsLive;


public interface LiveService extends BaseService<TCmsLive,Integer> {

	PageInfo<TCmsLive> page(Integer pageNumber, Integer pageSize, TCmsLiveVo pojo);

	Integer selectMaxSort(TCmsLive live);
	
	List<TCmsLive> selectByCondition(TCmsLive live);
	
	List<TCmsLive> selectForTag(TCmsLive live);
}
