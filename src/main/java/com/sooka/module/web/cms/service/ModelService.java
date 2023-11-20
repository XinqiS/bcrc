package com.sooka.module.web.cms.service;

import com.sooka.common.base.BaseService;
import com.sooka.mybatis.model.TCmsModel;

import java.util.List;

/**
 * Created by binary on 2017/5/12.
 */
public interface ModelService extends BaseService<TCmsModel,Integer>{

    TCmsModel findModelByModelName(String modelName);

    TCmsModel findModelByTableName(String tableName);

    List<TCmsModel> findModelListByStatusAndSiteId(boolean status,Integer siteId);

    List<TCmsModel> findModelListByStatus(boolean status);

}
