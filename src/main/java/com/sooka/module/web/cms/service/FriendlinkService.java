package com.sooka.module.web.cms.service;

import com.github.pagehelper.PageInfo;
import com.sooka.common.base.BaseService;
import com.sooka.mybatis.model.TCmsFriendlink;
import com.sooka.mybatis.model.TCmsFriendlinkGroup;

import java.util.List;

/**
 * Description:友情链接
 *
 *
 * @create 2017-06-12
 **/
public interface FriendlinkService extends BaseService<TCmsFriendlink,Integer>{

    PageInfo<TCmsFriendlinkGroup> page(Integer pageNumber, Integer pageSize,TCmsFriendlinkGroup group);

    TCmsFriendlinkGroup findGroupById(Integer id);

    String save(TCmsFriendlinkGroup group);

    String update(TCmsFriendlinkGroup group);

    String deleteGroupById(Integer id);

    List<TCmsFriendlinkGroup> findGroupAll();

}
