package com.sooka.mybatis.mapper;


import java.util.List;
import java.util.Map;

import com.sooka.mybatis.model.JlAll;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sooka.mybatis.model.Zwtdjl;

import tk.mybatis.mapper.common.Mapper;

public interface ZwtdMapper extends Mapper<Zwtdjl>{
	
	List<Map<String,Object>> findZwtdjlByZhId(Map<String,Object> map);
	List<Map<String,Object>> findZwtdjlByZhIdAll();

	@Select("select count(*) from tb_zwtdjl where ZH_ID=#{ZH_ID}")
	int findWdsqCountByZhId(String zh_id);

	List<Map<String,Object>> searchTalents(JlAll pojo);

	List<Map<String,Object>> searchTalentsAll(JlAll pojo);

	@Delete("delete from tb_zwtdjl where JL_BB_ID=#{JL_BB_ID}")
	void deleteZwtdjlByJibanbenId(@Param("JL_BB_ID") String string);

	@Delete("delete from tb_zwtdjl where ZH_ID=#{ZH_ID}")
	void deleteZhiId(@Param("ZH_ID") String ZH_ID);

}