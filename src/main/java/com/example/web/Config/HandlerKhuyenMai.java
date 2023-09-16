package com.example.web.Config;
import com.example.web.repository.IKhuyenMaiRepository;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HandlerKhuyenMai {

//    @Autowired
//    private IKhuyenMaiRepository khuyenMaiRepository;
//
//    @Bean
//    public void timeOut() {
//        System.out.println("run...!");
//        khuyenMaiRepository.findAll().forEach(o ->
//
//        {
//            System.out.println(o.getNgayKetThuc());
//            JobKey jobKeyA = new JobKey(String.valueOf(o.getId()), o.getMa());
//            JobDetail jobA = JobBuilder.newJob(JobKhuyenMai.class)
//                    .withIdentity(jobKeyA).build();
//            Trigger trigger = null;
//
//                trigger = TriggerBuilder.newTrigger()
//                        .withIdentity(String.valueOf(o.getId()), o.getMa())
//                        .startAt(o.getNgayBatDau())// some Date
//                        .forJob(String.valueOf(o.getId()), o.getMa()) // identify job with name, group strings
//                        .endAt(o.getNgayKetThuc())
//                        .build();
//            Scheduler scheduler = null;
//            try {
//                scheduler = new StdSchedulerFactory().getScheduler();
//            } catch (SchedulerException e) {
//                e.printStackTrace();
//            }
//            try {
//                scheduler.start();
//            } catch (SchedulerException e) {
//                e.printStackTrace();
//            }
//            try {
//                scheduler.scheduleJob(jobA, trigger);
//            } catch (SchedulerException e) {
//                e.printStackTrace();
//            }
//        });
//    }
}

