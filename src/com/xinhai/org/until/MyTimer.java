package com.xinhai.org.until;
/**
* @author Tony
* @version 创建时间：2018年7月28日 上午11:09:00
* @ClassName 类名称
* @Description 类描述
*/


import java.util.Timer;

public class MyTimer {
  public static void main(String[] args) {
    //1.创建一个timer实例
      Timer timer = new Timer();
    //2.创建一个MyTimerTask实例
      MyTimerTask myTimerTask = new MyTimerTask();
     
    //3.通过timer定时定频率调用myTimerTask的业务逻辑
      //即第一次执行是在当前时间的两秒之后，之后每隔一秒钟执行一次
      timer.schedule(myTimerTask, 2000L,1000*60*60*24);
  		}
}