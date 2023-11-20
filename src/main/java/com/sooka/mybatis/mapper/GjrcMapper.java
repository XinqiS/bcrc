package com.sooka.mybatis.mapper;


import java.util.List;

import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.sooka.module.web.cms.vo.GjrcVo;

public interface GjrcMapper extends Mapper<GjrcVo> {
	@Select("SELECT tb_jl_banben.JL_BB_ID,ZH_ID,max( JYJL_XL ) JYJL_XL,	tb_jl_info.JIANLI_XM ,	JIANLI_XB,	JIANLI_CSRQ,	QZYX_JNTC,	tb_jl_jyjl.JYJL_XX,	tb_jl_jyjl.JYJL_ZY FROM	tb_jl_jyjl	LEFT JOIN tb_jl_banben ON tb_jl_banben.JL_BB_ID = tb_jl_jyjl.JL_BB_ID	LEFT JOIN tb_jl_info ON tb_jl_banben.JIANLI_ID = tb_jl_info.JIANLI_ID	LEFT JOIN tb_jl_qzyx AS qz ON qz.JL_BB_ID = tb_jl_banben.JL_BB_ID 	GROUP BY	tb_jl_info.JIANLI_ID	order by JYJL_XL desc")
	public List<GjrcVo> selectGjrc();

}
