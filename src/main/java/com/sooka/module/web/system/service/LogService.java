package com.sooka.module.web.system.service;

import com.github.pagehelper.PageInfo;
import com.sooka.module.web.cms.vo.TCmsContentVo;
import com.sooka.mybatis.model.TCmsContent;
import com.sooka.mybatis.model.TSysLog;

import java.util.Date;

/**
 * Created by binary on 2017/4/10.
 */
public interface LogService {

    void saveLog(String content, Date createTime, String username, String type);

    PageInfo<TSysLog>  page(Integer pageNumer,Integer pageSize,String startTime,String endTime);

    String deleteById(Integer[] logId);

    PageInfo<TSysLog> page(Integer pageNumber, Integer pageSize, TSysLog pojo);

}
