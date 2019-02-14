package com.xinhai.org.servlet;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class ChatbotSend {

	public static String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token=346b1d95a50d5608fdaca8e803a1ae53a76e474be047007099c113a0c463bb5c";
    	public static String WEBHOOK_TOKEN2="https://oapi.dingtalk.com/robot/send?access_token=84dcaa07337d30a473fae36f9bf854af6b1dbf85c959a15e42ef0132fa997677";
	public static void SendSomeOne(String tel, String text) throws Exception {
		HttpClient httpclient = HttpClients.createDefault();
		HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
		httppost.addHeader("Content-Type", "application/json; charset=utf-8");
		String textMsg = "";
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> contentMap = new LinkedHashMap<String, Object>();
		Map<String, Object> mobilesMap = new HashMap<String, Object>();
		mobilesMap.put("atMobiles", new String[] { "@" + tel });
		contentMap.put("content", "开始初启动json版  呼叫@" + tel + "," + text);
		map.put("msgtype", "text");
		map.put("text", contentMap);
		map.put("at", mobilesMap);
		// String textMsg1 = "{ \"msgtype\": \"text\", \"text\": {\"content\":
		// \"测试叫人@18872849673\"},\"at\": {\"atMobiles\": [\"@18872849673\"]}}";
		// System.out.println(textMsg1);
		textMsg = JSON.toJSONString(map);
		// System.out.println(textMsg);
		StringEntity se = new StringEntity(textMsg, "utf-8");
		httppost.setEntity(se);
		HttpResponse response = httpclient.execute(httppost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String result = EntityUtils.toString(response.getEntity(), "utf-8");
			System.out.println(result);
		}

	}


	
	
	public static void Senderweima(String token) throws Exception {
		HttpClient httpclient = HttpClients.createDefault();
		
		String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token="+token;
		
		HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
		httppost.addHeader("Content-Type", "application/json; charset=utf-8");
		String textMsg = "";
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> linkmap = new LinkedHashMap<String, Object>();
		Map<String, Object> mobilesMap = new HashMap<String, Object>();
		// mobilesMap.put("atMobiles", new String[]{"@"+tel});
		// contentMap.put("content", "开始初启动json版 呼叫@"+tel+","+text);
		map.put("msgtype", "link");
		linkmap.put("text", "二维码扫描登入");
		linkmap.put("title", "明日吃饭统计");
		linkmap.put("picUrl", "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1973044591,2732746614&fm=27&gp=0.jpg");
	//	linkmap.put("picUrl", "http://img5.imgtn.bdimg.com/it/u=1176334969,1477728034&fm=27&gp=0.jpg");
	//	linkmap.put("messageUrl", "http://wcphp172.xinhaimobile.cn/ding_login/dingScanInit.php?key=test&val=http://localhost:8080/XinhaiMealStatistics/EatLogin?methods=Eatlogin");
		linkmap.put("messageUrl", "http://wcphp172.xinhaimobile.cn/ding_login/dingScanInit.php?key=test&val=http://10.168.20.188:8080/XinhaiMealStatistics/EatLogin?methods=Eatlogin");
		map.put("link",linkmap );
		//String textMsg = "{ \"msgtype\": \"link\", \"link\": {\"text\": \"尝试发送链接,如果过长就会只显示部分,所以text要足够的长,ABCDEFGHIJKLMNOPQRSTUVWXYZ\", \"title\": \"测试标题xxx\",\"picUrl\": \"\", \"messageUrl\": \"www.baidu.com\" }}";
		// map.put("at",mobilesMap);
		// String textMsg1 = "{ \"msgtype\": \"text\", \"text\": {\"content\":
		// \"测试叫人@18872849673\"},\"at\": {\"atMobiles\": [\"@18872849673\"]}}";
		// System.out.println(textMsg1);
		textMsg = JSON.toJSONString(map);
		// System.out.println(textMsg);
		StringEntity se = new StringEntity(textMsg, "utf-8");
		httppost.setEntity(se);
		HttpResponse response = httpclient.execute(httppost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String result = EntityUtils.toString(response.getEntity(), "utf-8");
			System.out.println(result);
		}
	}


	
	public static void SenderweimaTel(String token) throws Exception {
		HttpClient httpclient = HttpClients.createDefault();
		
		String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token="+token;
		
		HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
		httppost.addHeader("Content-Type", "application/json; charset=utf-8");
		String textMsg = "";
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> linkmap = new LinkedHashMap<String, Object>();
		Map<String, Object> mobilesMap = new HashMap<String, Object>();
		map.put("msgtype", "link");
		linkmap.put("text", "二维码扫描登入");
		linkmap.put("title", "明日吃饭统计手机版登入接口");
		linkmap.put("picUrl", "https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1973044591,2732746614&fm=27&gp=0.jpg");
		//linkmap.put("picUrl", "http://img5.imgtn.bdimg.com/it/u=1176334969,1477728034&fm=27&gp=0.jpg");
		//linkmap.put("messageUrl", "http://wcphp172.xinhaimobile.cn/ding_login/dingScanInit.php?key=test&val=http://localhost:8080/XinhaiMealStatistics/EatLogin?methods=Eatlogin");
		linkmap.put("messageUrl", "http://10.168.20.194:8080/XinhaiMealStatistics/EatLogin?methods=EatloginTel&&openid=XXX");
		map.put("link",linkmap );
		//String textMsg = "{ \"msgtype\": \"link\", \"link\": {\"text\": \"尝试发送链接,如果过长就会只显示部分,所以text要足够的长,ABCDEFGHIJKLMNOPQRSTUVWXYZ\", \"title\": \"测试标题xxx\",\"picUrl\": \"\", \"messageUrl\": \"www.baidu.com\" }}";
		// map.put("at",mobilesMap);
		// String textMsg1 = "{ \"msgtype\": \"text\", \"text\": {\"content\":
		// \"测试叫人@18872849673\"},\"at\": {\"atMobiles\": [\"@18872849673\"]}}";
		// System.out.println(textMsg1);
		textMsg = JSON.toJSONString(map);
		// System.out.println(textMsg);
		StringEntity se = new StringEntity(textMsg, "utf-8");
		httppost.setEntity(se);
		HttpResponse response = httpclient.execute(httppost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String result = EntityUtils.toString(response.getEntity(), "utf-8");
			System.out.println(result);
		}
	}	

	public static void SenderweimaTelTWO(String token) throws Exception {
		HttpClient httpclient = HttpClients.createDefault();
		
		String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token="+token;
		HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
		httppost.addHeader("Content-Type", "application/json; charset=utf-8");
		String textMsg = "";
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> actionCardmap = new LinkedHashMap<String, Object>();
		Map<String, Object> mobilesMap = new HashMap<String, Object>();
		map.put("msgtype", "actionCard");
		actionCardmap.put("title", "二维码扫描登入");
		actionCardmap.put("text", "明日吃饭统计登入接口");
		actionCardmap.put("singleTitle", "阅读全文");
		//actionCardmap.put("singleURL", "http://182.254.138.71:8088/XinhaiMealStatistics/NewLoginServlet");
		actionCardmap.put("singleURL", "http://java71.xinhaimobile.cn:8088/NewLoginServlet");
		map.put("actionCard",actionCardmap );
		textMsg = JSON.toJSONString(map);

		StringEntity se = new StringEntity(textMsg, "utf-8");
		httppost.setEntity(se);
		HttpResponse response = httpclient.execute(httppost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String result = EntityUtils.toString(response.getEntity(), "utf-8");
			System.out.println(result);
		}
	}	
	
	public static void SenderweimaTelthree(String token) throws Exception {
		HttpClient httpclient = HttpClients.createDefault();
		String WEBHOOK_TOKEN = "https://oapi.dingtalk.com/robot/send?access_token="+token;
		HttpPost httppost = new HttpPost(WEBHOOK_TOKEN);
		httppost.addHeader("Content-Type", "application/json; charset=utf-8");
		String textMsg = "";
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		Map<String, Object> actionCardmap = new LinkedHashMap<String, Object>();
		Map<String, Object> mobilesMap = new HashMap<String, Object>();
		// mobilesMap.put("atMobiles", new String[]{"@"+tel});
		// contentMap.put("content", "开始初启动json版 呼叫@"+tel+","+text);
		map.put("msgtype", "actionCard");
		actionCardmap.put("title", "二维码扫描登入");
		actionCardmap.put("text", "明日吃饭统计登入接口");
		actionCardmap.put("singleTitle", "阅读全文");
		actionCardmap.put("singleURL", "http://java71.xinhaimobile.cn:8088/NewLoginServlet");
		//actionCardmap.put("singleURL", "http://182.254.138.71:8088/XinhaiMealStatistics/NewLoginServlet");
		map.put("actionCard",actionCardmap );
		textMsg = JSON.toJSONString(map);
		StringEntity se = new StringEntity(textMsg, "utf-8");
		httppost.setEntity(se);
		HttpResponse response = httpclient.execute(httppost);
		if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
			String result = EntityUtils.toString(response.getEntity(), "utf-8");
			System.out.println(result);
		}
	}	
	public static void main(String args[]) throws Exception {
	
		//String token2="84dcaa07337d30a473fae36f9bf854af6b1dbf85c959a15e42ef0132fa997677";
		String token2="346b1d95a50d5608fdaca8e803a1ae53a76e474be047007099c113a0c463bb5c";
	//	ChatbotSend.SenderweimaTelTWO(token2);
		ChatbotSend.SenderweimaTelthree(token2);
	
	}
}
