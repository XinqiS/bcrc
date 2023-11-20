package com.sooka.mybatis.mapper;





import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.sooka.mybatis.model.Jlqzyx;

import tk.mybatis.mapper.common.Mapper;

public interface JlqzyxMapper extends Mapper<Jlqzyx>{
	
	
	void updateByPKSelective(Jlqzyx jlqz);
	
	@Select("select j.* from tb_jl_qzyx j where j.JL_BB_ID=#{jlbanbenId} ")
	@ResultMap("BaseResultMap")
	Jlqzyx findJlqzyxByJlBanbenId(@Param("jlbanbenId") String jlbanbenId);
	
	@Delete("delete from tb_jl_qzyx where JL_BB_ID=#{jlbanbenId} ")
	int deleteJlqzyxByJlBanbenId(@Param("jlbanbenId") String string);
   
}