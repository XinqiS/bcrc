package com.sooka.mybatis.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.sooka.module.web.system.vo.Lists;

public interface ListsMapper extends Mapper<Lists> {
	@Select("SELECT	a.*,	JYJL_XL,	gws.ZW_ZWMC,	ZW_GZDZ FROM	(SELECT	tdjl_id,	zw_id,	tdjl_time,	tdjl_yqsj,	ss.zh_id,	jl.jianli_xm,	jianli_xb,	jianli_csrq,	JIANLI_ID,	JL_BB_ID FROM	( SELECT * FROM TB_ZWTDJL WHERE zw_id IN ( SELECT zw_id FROM TB_GW_INFO WHERE QY_ID = #{arg0} ) AND TDJL_TDZT = #{arg1} ) ss	LEFT JOIN TB_JL_INFO jl ON jl.ZH_ID = ss.ZH_ID 	) a	LEFT JOIN (SELECT	tb_jl_banben.JL_BB_ID,	max( JYJL_XL ),	tb_jl_info.JIANLI_XM FROM	tb_jl_jyjl	LEFT JOIN tb_jl_banben ON tb_jl_banben.JL_BB_ID = tb_jl_jyjl.JL_BB_ID	LEFT JOIN tb_jl_info ON tb_jl_banben.JIANLI_ID = tb_jl_info.JIANLI_ID GROUP BY	tb_jl_info.JIANLI_ID 	) b ON 1 = 1	LEFT JOIN TB_JL_JYJL jyjl ON a.JL_BB_ID = jyjl.JL_BB_ID	LEFT JOIN TB_GW_INFO gws ON gws.zw_id = a.zw_id GROUP BY	a.tdjl_id")
	public List<Lists> selectAllByQy_Id(String id, Integer zt);

}
