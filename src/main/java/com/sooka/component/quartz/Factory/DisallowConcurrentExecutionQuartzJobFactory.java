package com.sooka.component.quartz.Factory;

import com.sooka.component.quartz.job.ScheduleJob;
import com.sooka.component.quartz.util.TaskUtils;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * 定时任务运行工厂类
 * 
*/
@DisallowConcurrentExecution
public class DisallowConcurrentExecutionQuartzJobFactory implements Job{

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
         Object scheduleJob =context.getMergedJobDataMap().get("scheduleJob");
         System.out.println(scheduleJob);
//         System.out.println("任务名称 = [" + scheduleJob.getJobName() + "]");
         TaskUtils.invokeMethod( (ScheduleJob)scheduleJob);
    }

}