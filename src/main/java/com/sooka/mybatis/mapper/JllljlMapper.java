package com.sooka.mybatis.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sooka.mybatis.model.Jllljl;

import tk.mybatis.mapper.common.Mapper;

public interface JllljlMapper extends Mapper<Jllljl>{
	
	
	List<Map<String,Object>> findJllljlByZhId(Map<String,Object> map);
	
	@Select("select count(*) from tb_jllljl ll  where ll.JL_BB_ID in (select JL_BB_ID from tb_jl_banben where JIANLI_ID = (select JIANLI_ID from tb_jl_info where ZH_ID= #{ZH_ID} ))")
	int findSkgwCountByZhId(@Param("ZH_ID") String zh_id);
	
	@Delete("delete from tb_jllljl where JL_BB_ID=#{JL_BB_ID}")
	void deleteJllljlByJibanbenId(@Param("JL_BB_ID") String string);
}