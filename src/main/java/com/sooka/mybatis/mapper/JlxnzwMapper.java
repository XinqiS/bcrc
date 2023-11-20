package com.sooka.mybatis.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.sooka.mybatis.model.Jlxnzw;

import tk.mybatis.mapper.common.Mapper;

public interface JlxnzwMapper extends Mapper<Jlxnzw>{
	
	@Select("select j.* from tb_jl_xnzw j where j.JL_BB_ID=#{JL_BB_ID} ")
	@ResultMap("BaseResultMap")
	List<Jlxnzw> findXnzwByJibanbenId(@Param("JL_BB_ID") String JL_BB_ID);
	
	@Delete("delete from tb_jl_xnzw where JL_BB_ID=#{JL_BB_ID} ")
	int deleteXnzwByJibanbenId(@Param("JL_BB_ID") String string);
}