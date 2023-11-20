package com.sooka.module.web.cms.vo;

import com.sooka.mybatis.model.TCmsAd;

/**
 * Description:
 *
 *
 * @create 2017-06-21
 **/
public class TCmsAdVo extends TCmsAd {

    private Integer groupId;

    @Override
    public Integer getGroupId() {
        return groupId;
    }

    @Override
    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    private String groupName;

}
