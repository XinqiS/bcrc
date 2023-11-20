package com.sooka.mybatis.mapper;



import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.sooka.mybatis.model.Jlinfo;

import tk.mybatis.mapper.common.Mapper;

public interface JlinfoMapper extends Mapper<Jlinfo>{
	
	@Select("select j.* from tb_jl_info j where j.ZH_ID=#{ZH_ID} ")
	@ResultMap("BaseResultMap")
	Jlinfo findByUserCodeId(@Param("ZH_ID") String ZH_ID);

	@Delete("delete from tb_jl_info  where ZH_ID=#{ZH_ID} ")
	void deleteZhiId(@Param("ZH_ID") String ZH_ID);
    
}