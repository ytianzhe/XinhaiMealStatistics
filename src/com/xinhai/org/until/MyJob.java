package com.xinhai.org.until;
/**
* @author Tony
* @version 创建时间：2018年7月28日 下午2:44:38
* @ClassName 类名称
* @Description 类描述
*/

import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;

import com.xinhai.org.sql.SqlMethods;
/**
 * 创建要被定执行的任务类
 * @author long
 *
 */
public class MyJob implements Job{

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		 JobKey jobKey = context.getJobDetail().getKey();
		SimpleDateFormat sft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("我执行了定时任务在:"+ jobKey+"|"+sft.format(new Date()));
		try {
			int eatNumber=0;
			double eatRiceNumber=0;
			Timestamp now = new Timestamp(System.currentTimeMillis());
			eatNumber=SqlMethods.SearchEatNumberTodayWrite();
			eatRiceNumber=SqlMethods.SearchEatRiceNumberrTodayWrite();
			//	判断是否插入
			
			int count= SqlMethods.SearchCountEatRiceNumberrTodayWrite(); 
			
			try {
				
				if(count==0){
					SqlMethods.insertEat_result_today_before_midnight(now, eatNumber, eatRiceNumber);	
				}
				else{
					SqlMethods.upDateEat_result_today_before_midnight(eatNumber, eatRiceNumber, now);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		System.out.println("开始执行统计任务");
		
	}


}
