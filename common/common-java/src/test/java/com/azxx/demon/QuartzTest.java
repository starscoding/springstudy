package com.azxx.demon;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.impl.StdSchedulerFactory;

/**
 * Created by Smile on 2018/9/26.
 */
public class QuartzTest {

    public static void main(String[] args) {
        try {
            //从stdfactory获取scheduler实例
            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.start();

//            JobDetail job = newJob(HelloQuartz.class);

            scheduler.shutdown();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }
}

