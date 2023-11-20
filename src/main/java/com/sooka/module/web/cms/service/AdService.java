package com.sooka.module.web.cms.service;

import com.github.pagehelper.PageInfo;
import com.sooka.common.base.BaseService;
import com.sooka.module.web.cms.vo.TCmsAdVo;
import com.sooka.mybatis.model.TCmsAd;
import com.sooka.mybatis.model.TCmsAdGroup;

import java.util.List;

public interface AdService extends BaseService<TCmsAd,Integer>{

    String toJavascript(Object id);

    String save(TCmsAdGroup pojo);

    String update(TCmsAdGroup pojo);

    List<TCmsAdVo> findVoListByStatus(Integer status);

    TCmsAdGroup findById(Object id);

    TCmsAd findByIdAndEffective(Integer id);

    List<TCmsAdGroup> findList(TCmsAdGroup pojo);

    PageInfo<TCmsAdGroup> page(Integer pageNumber, Integer pageSize,TCmsAdGroup adGroup);

     PageInfo<TCmsAdVo> page(Integer pageNumber, Integer pageSize,Integer status);

    String deleteGroup(Integer[] ids);
}
