package com.sooka.mybatis.mapper;

import com.sooka.mybatis.model.TCmsAd;
import com.sooka.module.web.cms.vo.TCmsAdVo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TCmsAdMapper extends Mapper<TCmsAd> {

    @Select("select a.*,g.group_name from t_cms_ad a join t_cms_ad_group g on a.group_id=g.id where g.status=#{status}")
    @ResultMap("VoResultMap")
    List<TCmsAdVo> selectByGroupStatus(@Param("status") Integer status);

    @Select("select * from t_cms_ad where start_date<now() and end_date>now() and id = #{id}")
    @ResultMap("BaseResultMap")
    TCmsAd selectByIdAndEffective(@Param("id") Integer id);

}