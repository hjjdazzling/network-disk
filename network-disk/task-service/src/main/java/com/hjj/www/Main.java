package com.hjj.www;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author haojunjie
 * @create 2020-04-21 19:52
 */
public class Main {
	public static void main(String[] args) throws SchedulerException {
		//1.从工厂中获取任务调度的实例
		Scheduler scheduler  = StdSchedulerFactory.getDefaultScheduler();
		//2.定义任务调度实例，将该实例与HelloJob绑定，任务类需要实现Job接口
		JobDetail jobDetail = JobBuilder.newJob(HelloJob.class)
										// 定义该实例唯一标识，并指定一个组。
										.withIdentity("helloJob", "group1")
										.usingJobData("message", "jobDetail data")
										.build();

		Date startTime = new Date();

		Date endTime = new Date();

		// 启动结束，任务在当前时间10秒后停止
		endTime.setTime(endTime.getTime()+1000000);

		// 3.定义触发器，马上执行，每5秒执行一次
		Trigger trigger = TriggerBuilder.newTrigger()
										.withIdentity("trigger1", "group1")
										// 指定开始时间
										.startAt(startTime)
										// 指定结束时间
										.endAt(endTime)
										// 0/5 * * ? * * cron表达式 每5秒一次
										.withSchedule(CronScheduleBuilder.cronSchedule("0/5 * * ? * *"))
										.usingJobData("message", "simple trigger")
										.build();

		//4.使用触发器调度任务的执行
		scheduler.scheduleJob(jobDetail, trigger);

		//5.开启
		scheduler.start();
		//6.关闭
		//scheduler.shutdown();

	}
}
