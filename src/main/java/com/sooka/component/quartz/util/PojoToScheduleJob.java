package com.sooka.component.quartz.util;

import com.sooka.component.quartz.job.ScheduleJob;
import com.sooka.mybatis.model.TSysScheduleJob;

/**
 * Description:
 *
 *
 * @create 2017-07-10
 **/
public class PojoToScheduleJob {

    public static ScheduleJob convert(TSysScheduleJob pojo){
            ScheduleJob scheduleJob=new ScheduleJob();
            scheduleJob.setBeanClass(pojo.getBeanClass());
            scheduleJob.setCronExpression(pojo.getCronExpression());
            scheduleJob.setDescription(pojo.getDescription());
            scheduleJob.setIsConcurrent(pojo.getIsConcurrent());
            scheduleJob.setJobName(pojo.getJobName());
            scheduleJob.setJobGroup(pojo.getJobGroup());
            scheduleJob.setJobStatus(pojo.getJobStatus());
            scheduleJob.setMethodName(pojo.getMethodName());
            scheduleJob.setSpringBean(pojo.getSpringBean());
            return scheduleJob;


    }

}
