package com.sooka.mybatis.mapper;

import com.sooka.mybatis.model.TCmsLbt;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

public interface TCmsLbtMapper extends Mapper<TCmsLbt> {
    @Select("select * from t_cms_lbt where lbt_id = (select max(lbt_id) from t_cms_lbt where lbt_id < #{contentId})")
    @ResultMap("BaseResultMap")
    TCmsLbt selectPrevById(@Param("contentId") Long contentId);

    @Select("select * from t_cms_lbt where lbt_id = (select min(lbt_id) from t_cms_lbt where lbt_id > #{contentId})")
    @ResultMap("BaseResultMap")
    TCmsLbt selectNextById(@Param("contentId") Long contentId);

}
