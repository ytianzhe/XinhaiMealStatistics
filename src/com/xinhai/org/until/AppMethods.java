package com.xinhai.org.until;

import java.sql.Timestamp;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.xinhai.org.sql.SqlMethods;

import java.util.Random;
import java.util.Timer;
import java.util.UUID;
/**
* @author Tony
* @version 创建时间：2018年7月19日 下午6:49:47
* @ClassName 类名称
* @Description 类描述
*/
public class AppMethods {
	  public static String getName(String openid) throws Exception{
		  	String name="";
			//String jsonString=UserInfo.SetToken("PLbqwye51uFtf2z9fU16tgiEiE");
			String jsonString=UserInfo.SetToken(openid);
			//System.out.println(jsonString);
			JSONObject obj = JSONObject.parseObject(jsonString);
		//	String objdata = obj.getString("data");
		//	System.out.println(objdata);
			
			JSONArray array = obj.getJSONArray("data");
			for (int k = 0; k < array.size(); k++) {
				JSONObject kobject = array.getJSONObject(k);
				// if (kobject.getString("info").equals("DevId=1,Count=3")) {
				// System.err.println("--------"+kobject.getString("id"));

				// }
				 name = kobject.getString("nick");
				//System.out.println(name);
			}
//			JSONObject datejson = JSONObject.parseObject(objdata);
//			String name = datejson.getString("nick");
//			String useropenid = datejson.getString("openid");
//			System.out.println("name:"+name+",openid:"+useropenid);
			//JSONArray array = obj.getJSONArray("nick");
			return name;
		}
	  public static void  insertInfo(String openid,String eatUserName) throws Exception{
		  Timestamp now = new Timestamp(System.currentTimeMillis());
		  SqlMethods.insertEatUserInfo(openid, now, "", eatUserName);
		}
	  
	  //增加明日吃饭记录的方法
	  public static void  inserteatDayLog(int userId,int isEat,double riceNumber,String AdditionalInformation) throws Exception{
		  Timestamp now = new Timestamp(System.currentTimeMillis());
		  SqlMethods.inserteat_day_log(now, userId, isEat, riceNumber,AdditionalInformation);
		}  
	  
	  
	 
	  public static String  Createuuid() throws Exception{
//	  for(int i=0;i<10;i++){
//		  String uuid = UUID.randomUUID().toString().replaceAll("-", "");
//		  System.out.println(uuid);
//	  	}
		  String uuid=UUID.randomUUID().toString().replaceAll("-", "");
		  return uuid;
	  }
	  
	  public static void SendRice() throws Exception{
		    //1.创建一个timer实例
		      Timer timer = new Timer();
		    //2.创建一个MyTimerTask实例
		      MyTimerTask myTimerTask = new MyTimerTask();
		     
		    //3.通过timer定时定频率调用myTimerTask的业务逻辑
		      //即第一次执行是在当前时间的两秒之后，之后每隔一秒钟执行一次
		      timer.schedule(myTimerTask, 2000L,1000L);
		  		}
	  
	  
	  //生成一个4位随机码
	  //生成四位不重复的验证码
	   public static String codeGen(){
	        char [] codeSequence={'a','b','c','d','e','f','g','h','i','j',
	    'k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
	    '1','2','3','4','5','6','7','8','9'};
	        Random random =new Random();
	        StringBuilder sb=new StringBuilder();//动态字符串，String创建的字符串不能修改
	        int count=0;//计数器确定产生的是四位验证码
	        while(true){
	            //随机产生一个下标，通过下标取出字符数组对应的字符
	            char c=codeSequence[random.nextInt(codeSequence.length)];
	            //假设取出来的字符在动态字符串中不存在，代表没有重复
	            if (sb.indexOf(c+"")==-1) {
	                sb.append(c);//追加到动态字符串中
	                count++;
	                if (count==4) {
	                    break;
	                }
	            }
	        }

	    return sb.toString();

	        }

	  
public static void main(String[] args) throws Exception{
//	String name=AppMethods.getName("gRr2c3Zuz0sriiB7yQqWW6giEiE");
//	System.out.println(name);
	String uuid=Createuuid();
	System.out.println(uuid);
}
}