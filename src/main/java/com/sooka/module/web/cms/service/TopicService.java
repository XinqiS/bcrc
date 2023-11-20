package com.sooka.module.web.cms.service;

import com.sooka.common.base.BaseService;
import com.sooka.mybatis.model.TCmsTopic;

import java.util.List;

public interface TopicService extends BaseService<TCmsTopic,Integer> {
    Integer AllCount();

    List<TCmsTopic> findByRecommendList(Integer siteId,boolean isRecommend, Integer pageSize);
}
