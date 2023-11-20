package com.sooka.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.sooka.mybatis.model.TCmsCategory;
import tk.mybatis.mapper.common.Mapper;

public interface TCmsCategoryMapper extends Mapper<TCmsCategory> {
	
	List<Map> selectByTCmsContentDbtAll(Integer id);
	
	List<Map<String,Object>> findByCategoryIds(@Param("categoryIds") Long[] categoryIds);
}