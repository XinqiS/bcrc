package com.sooka.mybatis.mapper;

import com.sooka.mybatis.model.TCmsMediaCount;
import org.apache.ibatis.annotations.*;
import tk.mybatis.mapper.common.Mapper;
import java.util.List;
import java.util.Map;


public interface TCmsMedicCountMapper extends Mapper<TCmsMediaCount> {

    List<TCmsMediaCount> selectByYear(@Param("callYear") String  callYear, @Param("callMonth") String callMonth);
    int insert(TCmsMediaCount tCmsMediaCount);
    @Update("update t_cms_mediacount set callConunt=#{callConunt}+1 where id = #{id}")
    int updateByYear(TCmsMediaCount pojo);

    @Select("select *  from  t_cms_mediacount where year(callDate)=year(now()) ORDER BY  callDate")
    @ResultType(Map.class)
    List<TCmsMediaCount> selectAllMonthCount();

    @Select("select sum(callConunt) as zl from t_cms_mediacount   where year(callDate)=year(now())")
    @ResultMap("BaseResultMap")
    TCmsMediaCount selectByCount();
    @Select(" select  * from  t_cms_mediacount ORDER BY id desc LIMIT 0,1")
    @ResultMap("BaseResultMap")
    TCmsMediaCount findByMaxId();

}

