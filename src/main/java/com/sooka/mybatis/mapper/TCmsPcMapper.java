package com.sooka.mybatis.mapper;

import com.sooka.mybatis.model.TCmsPc;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface TCmsPcMapper extends Mapper<TCmsPc> {

    List<TCmsPc> selectPc();



}