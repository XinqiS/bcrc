package com.sooka.common.utils;

import com.sooka.module.web.system.vo.UserVo;
import com.sooka.common.constant.CmsConst;
import org.apache.shiro.authz.UnauthenticatedException;

/**
 * Description:系统用户工具类
 *
 *
 * @create 2017-05-31
 **/
public class UserUtil {

    public static UserVo getSysUserVo() {
        UserVo userVo =   ((UserVo) ControllerUtil.getHttpSession().getAttribute(CmsConst.SITE_USER_SESSION_KEY));
        if(CmsUtil.isNullOrEmpty(userVo)) {
            throw  new UnauthenticatedException();
        }
        return userVo;
    }
}
