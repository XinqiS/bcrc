package com.sooka.mybatis.mapper;

import org.apache.ibatis.annotations.Select;

import com.sooka.module.web.system.vo.tb_zwscjl;

import tk.mybatis.mapper.common.Mapper;

public interface TbZwscjlMapper extends  Mapper<tb_zwscjl> {
	@Select("select count(*) count from tb_zwscjl where ZW_ID=#{0}")
	int countByzw_id(String zw_id);


}
