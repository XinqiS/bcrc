package com.sooka.module.web.cms.vo;

import com.sooka.mybatis.model.TCmsSite;

/**
 * Description:site vo
 *
 *
 * @create 2017-05-16
 **/
public class TCmsSiteVo extends TCmsSite {



    private String userIds;

    public String getUserIds() {
        return userIds;
    }

    public void setUserIds(String userIds) {
        this.userIds = userIds;
    }

    public String getUserNames() {
        return userNames;
    }

    public void setUserNames(String userNames) {
        this.userNames = userNames;
    }

    private String userNames;

}
