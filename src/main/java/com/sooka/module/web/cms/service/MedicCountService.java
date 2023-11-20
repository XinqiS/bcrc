package com.sooka.module.web.cms.service;

import com.sooka.common.base.BaseService;
import com.sooka.mybatis.model.TCmsLive;
import com.sooka.mybatis.model.TCmsMediaCount;

import java.util.List;

public interface MedicCountService extends BaseService<TCmsMediaCount,Integer> {

    List<TCmsMediaCount> selectByYear(String callYear,String callMonth);

    String updateByYear(TCmsMediaCount pojo);

    String findAllMonthCount();
    String findAllCount();
    TCmsMediaCount findByMaxId();


}
