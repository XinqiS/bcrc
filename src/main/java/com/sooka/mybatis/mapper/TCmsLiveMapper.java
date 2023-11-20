package com.sooka.mybatis.mapper;


import java.util.List;

import com.sooka.mybatis.model.TCmsContent;
import com.sooka.mybatis.model.TCmsLive;

import tk.mybatis.mapper.common.Mapper;

public interface TCmsLiveMapper extends Mapper<TCmsLive> {
	
	List<TCmsLive> selectByCondition(TCmsLive live);
	
	Integer selectMaxSort(TCmsLive live);
	
	List<TCmsLive> selectForTag(TCmsLive live);
}
