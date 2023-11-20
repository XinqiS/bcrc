package com.sooka.module.web.cms.service;

import com.github.pagehelper.PageInfo;
import com.sooka.common.base.BaseService;
import com.sooka.mybatis.model.TCmsPc;

import java.util.List;


public interface PcService extends BaseService<TCmsPc,Integer> {

    PageInfo<TCmsPc> page(Integer pageNumber, Integer pageSize, TCmsPc pojo);
    PageInfo<TCmsPc> pagequery(Integer pageNumber, Integer pageSize, TCmsPc pojo);

    List<TCmsPc> selectPc();
    TCmsPc findById(Integer id);


}
