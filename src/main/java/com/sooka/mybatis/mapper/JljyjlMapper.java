package com.sooka.mybatis.mapper;



import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

import com.sooka.mybatis.model.Jljyjl;

import tk.mybatis.mapper.common.Mapper;

public interface JljyjlMapper extends Mapper<Jljyjl>{
	@Select("select j.* from tb_jl_jyjl j where j.JL_BB_ID=#{JL_BB_ID} order by j.jyjl_xl desc ")
	@ResultMap("BaseResultMap")
	List<Jljyjl> findJyjlByJibanbenId(@Param("JL_BB_ID") String JL_BB_ID);
	
	@Delete("delete from tb_jl_jyjl where JL_BB_ID=#{JL_BB_ID} ")
	int deleteJyjlByJibanbenId(@Param("JL_BB_ID") String string);


	@Select("select DATE_FORMAT(j.JYJL_KSSJ, '%Y年%m月')JYJL_KSSJ,DATE_FORMAT(j.JYJL_JSSJ, '%Y年%m月')JYJL_JSSJ,j.JYJL_XX,j.JYJL_ID,j.JYJL_ZY,\n" +
			"(CASE j.JYJL_XL WHEN 0 THEN '初中及以下'  \n" +
			"WHEN 1 THEN '高中' \n" +
			"WHEN 2 THEN '中专' \n" +
			"WHEN 3 THEN '大专' \n" +
			"WHEN 4 THEN '本科' \n" +
			"WHEN 5 THEN '硕士' \n" +
			"WHEN 6 THEN '博士' \n" +
			"WHEN 7 THEN '硕士' \n" +
			"ELSE 'null' END) Xl\n" +
			"from TB_JL_JYJL j where j.JL_BB_ID=#{JL_BB_ID} ")
	@ResultMap("BaseResultMap")
	List<Jljyjl> findByJibanbenId(@Param("JL_BB_ID") String JL_BB_ID);
}