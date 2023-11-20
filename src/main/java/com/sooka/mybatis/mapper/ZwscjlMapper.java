package com.sooka.mybatis.mapper;


import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sooka.mybatis.model.Zwscjl;

import tk.mybatis.mapper.common.Mapper;

public interface ZwscjlMapper extends Mapper<Zwscjl>{
	
	List<Map<String,Object>> findZwscjlByZhId(Map<String,Object> map);
	
	@Select("select count(*) from tb_zwscjl where ZH_ID=#{ZH_ID}")
	int findWdscCountByZhId(String zh_id);

	@Delete("delete from tb_zwscjl  where ZH_ID=#{ZH_ID} ")
	void deleteZhiId(@Param("ZH_ID") String ZH_ID);
}