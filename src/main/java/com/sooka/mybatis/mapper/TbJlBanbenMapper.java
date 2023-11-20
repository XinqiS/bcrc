package com.sooka.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

import tk.mybatis.mapper.common.Mapper;

import com.sooka.module.web.system.vo.tb_jl_banben;

public interface TbJlBanbenMapper extends Mapper<tb_jl_banben> {
	@Select("select bb.* from  tb_jl_banben bb ,TB_JL_INFO info where bb.JL_BB_MRTDZT=1 and bb.JIANLI_ID=info.JIANLI_ID and info.ZH_ID=#{0}")
	tb_jl_banben selectOneByZhId(String zh_id);

}
