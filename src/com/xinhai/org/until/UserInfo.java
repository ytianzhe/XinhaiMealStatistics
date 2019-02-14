package com.xinhai.org.until;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;




public class UserInfo {

	

	//private static String url="10.168.20.183:8080/Demo/requestTest2.do";

	public static String SetToken(String openid) throws Exception {
		//System.out.println("收到的值："+uuid);
		String jsonString = "";
		HttpClient client = new DefaultHttpClient();// 实例化http
		HttpPost request = new HttpPost();
		request.setURI(new URI("http://wcphp172.xinhaimobile.cn/ding_login/ding_api.php"));// 设置url地址
//		request.setHeader("Accept", "application/json");
//		request.setHeader("Content-Type", "application/json");
		String charSet = "UTF-8";
		// 设置参数
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();
		nvps.add(new BasicNameValuePair("openid", openid));
		// 将参数写入request请求体 , 设置编码格式
		request.setEntity(new UrlEncodedFormEntity(nvps, charSet));
		
		HttpResponse response = client.execute(request);// 获得转发

		StatusLine status = response.getStatusLine();
		int state = status.getStatusCode();
		System.err.println("状态码" + state);

		if (state == HttpStatus.SC_OK) {
			HttpEntity entity = response.getEntity();
			jsonString = EntityUtils.toString(entity);
			System.err.println("返回结果" + jsonString);
			return jsonString;

		} else {

			System.err.println("请求失败：-------" + status);
			return "2";
		}
		//return null;

	}
	
	
	
	public static void main(String[] args)throws Exception {
		
		String jsonString=UserInfo.SetToken("PLbqwye51uFtf2z9fU16tgiEiE");
		System.out.println(jsonString);
		JSONObject obj = JSONObject.parseObject(jsonString);
	//	String objdata = obj.getString("data");
	//	System.out.println(objdata);
		
		JSONArray array = obj.getJSONArray("data");
		for (int k = 0; k < array.size(); k++) {
			JSONObject kobject = array.getJSONObject(k);
			// if (kobject.getString("info").equals("DevId=1,Count=3")) {
			// System.err.println("--------"+kobject.getString("id"));

			// }
			String name = kobject.getString("nick");
			System.out.println(name);
		}
//		JSONObject datejson = JSONObject.parseObject(objdata);
//		String name = datejson.getString("nick");
//		String useropenid = datejson.getString("openid");
//		System.out.println("name:"+name+",openid:"+useropenid);
		//JSONArray array = obj.getJSONArray("nick");
	}
	
	
	
}