package com.sooka.mybatis.mapper;

import com.sooka.module.web.system.vo.tb_zh_info;
import com.sooka.module.web.system.vo.tb_zhjhjl;

import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface TBZHJHJLMapper extends Mapper<tb_zhjhjl> {
    @Select("select * from tb_zhjhjl  where  zh_id=#{zh_id}")
    tb_zhjhjl selectByZhId(String zh_id);
}