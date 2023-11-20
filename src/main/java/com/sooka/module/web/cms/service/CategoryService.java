package com.sooka.module.web.cms.service;

import java.util.List;
import java.util.Map;

import com.sooka.common.base.BaseService;
import com.sooka.mybatis.model.TCmsCategory;

/**
 * Created by binary on 2017/4/18.
 */
public interface CategoryService extends BaseService<TCmsCategory,Long> {

    List<TCmsCategory> findCategoryListByPid(Long pid);

    List<TCmsCategory> findCategoryListBySiteId(Integer siteId);

    List<TCmsCategory> findCategoryListByPid(Long pid,Integer siteId);

    List<TCmsCategory> findCategoryListByPidAndIsNav(Long pid,Integer siteId,Boolean isNav);

    TCmsCategory findByAlias(String alias);

    Integer findPageSize(Long categoryId);

    TCmsCategory findfindByAliasAndSiteId(String alias,Integer siteId);

    Integer AllCount();
    
    List<Map<String,Object>> findByCategoryIds(Long[] categoryIds);
}
