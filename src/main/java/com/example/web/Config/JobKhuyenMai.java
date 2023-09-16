package com.example.web.Config;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class JobKhuyenMai implements Job {
    
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        System.out.println("id : " + jobExecutionContext.getJobDetail().getKey());
    }


}
