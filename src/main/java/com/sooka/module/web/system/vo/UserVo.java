package com.sooka.module.web.system.vo;

import com.sooka.mybatis.model.TSysUser;

/**
 * Description:系统用户user
 *
 *
 * @create 2017-05-17
 **/
public class UserVo extends TSysUser{

    private Integer siteId;
    private String siteName;

    private Integer orgId;

    public Integer getOrgId() {
        return orgId;
    }

    public void setOrgId(Integer orgId) {
        this.orgId = orgId;
    }

    public String getSiteName() {
        return siteName;
    }

    public void setSiteName(String siteName) {
        this.siteName = siteName;
    }

    public Integer getSiteId() {
        return siteId;
    }

    public void setSiteId(Integer siteId) {
        this.siteId = siteId;
    }
}
