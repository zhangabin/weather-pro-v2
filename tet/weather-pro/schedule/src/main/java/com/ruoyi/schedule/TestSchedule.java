package com.ruoyi.schedule;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 定时任务测试类
 * @ClassName TestSchedule
 * @Author 1129017402@qq.com
 * @Date 2021/2/4 10:43
 */
@Component
@EnableScheduling
public class TestSchedule {
    //@Scheduled(fixedRate=5000)
    private void configureTasks() {
        System.out.println("执行静态定时任务时间: " + new Date());
    }
}
