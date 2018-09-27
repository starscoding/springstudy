package com.azxx.demon.task;

import org.springframework.stereotype.Component;

/**
 * Created by Smile on 2018/9/26.
 */
@Component
public class SpringTaskTest {

//    @Scheduled(cron = "0/1 * *  * * ?")
    public void taskTest(){
        System.out.println("hello,spring task!");
    }
}
