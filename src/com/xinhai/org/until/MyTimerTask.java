package com.xinhai.org.until;
/**
* @author Tony
* @version 创建时间：2018年7月28日 上午11:07:42
* @ClassName 类名称
* @Description 类描述
*/


import java.util.TimerTask;

import com.xinhai.org.servlet.ChatbotSend;
public class MyTimerTask extends TimerTask{
    private String name;
   
    private int count=0;
    
    @Override
    public void run() {
        // TODO Auto-generated method stub
        //打印当前name的内容
    	//Calendar calendar1=Calendar.getInstance();
       // System.out.println("Current exec name is："+name+"time :"+calendar1.getTime()+" count:"+count);
    	try {
			ChatbotSend.SenderweimaTelTWO("6216903cdcf89aa9d5c4574fcb54d165ec2359ee292c0c9c4a523cf7657699fd");
    		//ChatbotSend.SenderweimaTelTWO("346b1d95a50d5608fdaca8e803a1ae53a76e474be047007099c113a0c463bb5c");
    		
			 count++;
		        if(count==30){
		        this.cancel();
		        System.out.println("the timer is cancel");}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    }
    /**
     * @return the name
     */
   
}