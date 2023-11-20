package com.sooka.module.web.cms.service;


import com.sooka.common.base.BaseService;
import com.sooka.module.web.cms.vo.TCmsSiteVo;
import com.sooka.module.web.system.vo.UserVo;
import com.sooka.mybatis.model.TCmsSite;

/**
 * Created by binary on 2017/5/15.
 */
public interface SiteService extends BaseService<TCmsSite,Integer> {

    @Override
    String save(TCmsSite pojo);

    @Override
    String update(TCmsSite pojo);

    String save(TCmsSiteVo pojo);

    String update(TCmsSiteVo pojo);

    TCmsSiteVo findVoById(Integer id);

    String change(UserVo userVo, Integer siteId);

    TCmsSite findByDomain(String domain);
}
