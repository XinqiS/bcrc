package com.sooka.mybatis.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.sooka.mybatis.model.Jlgzjy;

import tk.mybatis.mapper.common.Mapper;

public interface JlgzjyMapper extends Mapper<Jlgzjy>{
	@Select("select j.* from tb_jl_gzjy j where j.JL_BB_ID=#{JL_BB_ID} order by j.GZJY_JSSJ desc ")
	@ResultMap("BaseResultMap")
	List<Jlgzjy> findGzjyByJibanbenId(@Param("JL_BB_ID") String JL_BB_ID);
	
	@Delete("delete from tb_jl_gzjy where JL_BB_ID = #{JL_BB_ID} ")
	int deleteGzjyByJibanbenId(@Param("JL_BB_ID") String string);

	@Select("select g.GZJY_ZN,g.JL_BB_ID,DATE_FORMAT(g.GZJY_KSSJ, '%Y年%m月')GZJY_KSSJ,DATE_FORMAT(g.GZJY_JSSJ, '%Y年%m月')GZJY_JSSJ,g.GZJY_GS,g.GZJY_ZW from TB_JL_GZJY g where g.JL_BB_ID=#{JL_BB_ID} ")
	@ResultMap("BaseResultMap")
	List<Jlgzjy> findByJibanbenId(@Param("JL_BB_ID") String JL_BB_ID);
}