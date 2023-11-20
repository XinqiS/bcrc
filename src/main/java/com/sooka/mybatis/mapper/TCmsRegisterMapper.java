package com.sooka.mybatis.mapper;

import com.sooka.mybatis.model.TCmsRegister;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TCmsRegisterMapper extends Mapper<TCmsRegister> {

    @Update("update t_cms_register set r_state = 0 where r_id = #{r_id}")
    int del(@Param("r_id")Integer r_id);

    @Select("select * from t_cms_register where r_state = 1")
    @ResultMap("BaseResultMap")
    List<TCmsRegister> find();
}
