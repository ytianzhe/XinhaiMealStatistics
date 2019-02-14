package com.xinhai.org.until;
/**
* @author Tony
* @version 创建时间：2018年7月28日 下午3:33:39
* @ClassName 类名称
* @Description 类描述
*/

import static org.quartz.CronScheduleBuilder.cronSchedule;
import static org.quartz.DateBuilder.evenMinuteDate;
import static org.quartz.JobBuilder.newJob;
import static org.quartz.TriggerBuilder.newTrigger;
 
import java.util.Date;
 
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerFactory;
import org.quartz.SchedulerMetaData;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
 
 
public class DemoTest3 {
	
	
	public void run() throws Exception {
		
	    System.out.println("------- 初始化 --------------------");
 
	    SchedulerFactory sf = new StdSchedulerFactory();
	    
	    Scheduler sched = sf.getScheduler();
 
	    System.out.println("------- 初始化完成 ---1s----------------");
 
	    System.out.println("------- 执行任务 ---------------------");
 
	    /**
	     * 任务的执行策略	
	     */
	    JobDetail job =  newJob(MyJob.class).withIdentity("job8", "group1").build();
	    /**
	     * 任务执行的时间 在6月19日的 11点18分执行任务
	     */
	    CronTrigger trigger = newTrigger().withIdentity("trigger8", "group1").withSchedule(cronSchedule("0 59 11 * * ?"))
	        .build();
 
	    Date ft = sched.scheduleJob(job, trigger);
	    
	    sched.start();
	  }
	
	public static void main(String[] args) throws Exception {
		
		new DemoTest3().run();
	}
 
}