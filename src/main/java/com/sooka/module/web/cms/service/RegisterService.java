package com.sooka.module.web.cms.service;

import com.sooka.common.base.BaseService;
import com.sooka.mybatis.model.TCmsRegister;


public interface RegisterService extends BaseService<TCmsRegister,Integer> {

    String delete(Integer r_id);

}
