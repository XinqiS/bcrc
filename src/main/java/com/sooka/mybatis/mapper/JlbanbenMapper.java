package com.sooka.mybatis.mapper;



import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.sooka.mybatis.model.Jlbanben;

import tk.mybatis.mapper.common.Mapper;

public interface JlbanbenMapper extends Mapper<Jlbanben>{
	
	@Update(" update tb_jl_banben set JL_BB_MRTDZT = 0  where JIANLI_ID=#{jianliId}")
	void updateJlbanbenMRtdzt(@Param("jianliId") String jianliId);
	
	void del_grrelation(String zh_id);
	
	void del_qyrelation(String zh_id);
    
}