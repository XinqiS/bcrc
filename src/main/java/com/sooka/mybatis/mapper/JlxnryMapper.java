package com.sooka.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.sooka.mybatis.model.Jlxnry;

import tk.mybatis.mapper.common.Mapper;

public interface JlxnryMapper extends Mapper<Jlxnry>{
	 
	@Select("select j.* from tb_jl_xnry j where j.JL_BB_ID=#{JL_BB_ID} ")
	@ResultMap("BaseResultMap")
	List<Jlxnry> findXnryByJibanbenId(@Param("JL_BB_ID") String JL_BB_ID);
	
	@Delete("delete from tb_jl_xnry  where JL_BB_ID=#{JL_BB_ID} ")
	int deleteXnryByJibanbenId(@Param("JL_BB_ID") String string);
}