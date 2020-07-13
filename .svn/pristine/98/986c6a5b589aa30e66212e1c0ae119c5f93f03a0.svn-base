package com.invoice.quartz.utils;

import com.invoice.quartz.domain.SysJob;
import org.quartz.JobExecutionContext;

/**
 * @ClassName QuartzJobExecution
 * @Description 定时任务处理（允许并发执行）
 * @Author ZZJ
 * @Date 2020/5/29 21:36
 **/
public class QuartzJobExecution extends AbstractQuartzJob
{
    @Override
    protected void doExecute(JobExecutionContext context, SysJob sysJob) throws Exception
    {
        JobInvokeUtil.invokeMethod(sysJob);
    }
}
