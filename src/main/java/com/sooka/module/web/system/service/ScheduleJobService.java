package com.sooka.module.web.system.service;

import com.sooka.common.base.BaseService;
import com.sooka.mybatis.model.TSysScheduleJob;

public interface ScheduleJobService extends BaseService<TSysScheduleJob,Integer>{

    /* 初始化任务*/
   void initSchedule();

    /* 更改状态 */
    String changeStatus(int id, String status);


}
