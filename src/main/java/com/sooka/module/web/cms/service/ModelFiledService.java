package com.sooka.module.web.cms.service;

import com.sooka.common.base.BaseService;
import com.sooka.mybatis.model.TCmsModelFiled;

import java.util.List;

/**
 * Created by binary on 2017/5/12.
 */
public interface ModelFiledService extends BaseService<TCmsModelFiled,Integer> {
    TCmsModelFiled findModelFiledByFiledName(String filedName);

    List<TCmsModelFiled> findModelFiledListByModelId(Integer modelId);

}
