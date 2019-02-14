package com.xinhai.org.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.xinhai.org.until.AppMethods;
import com.xinhai.org.until.SMS;






/**
 * Servlet implementation class VerificationCode
 */
@WebServlet("/VerificationCode")
public class VerificationCode extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VerificationCode() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		synchronized (this) {
			try{
				String method=request.getParameter("method");
				String EatTelNumber=request.getParameter("EatTelNumber");
			//	String employeeNumber=request.getParameter("employeeNumber");
				String res=null;
				String msg=null;
				String content=null;
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				System.out.println("EatTelNumber:"+EatTelNumber);
				System.out.println("method:"+method);
				
				if(EatTelNumber!=null){
				    Pattern p = Pattern.compile("^(13[0-9]|14[579]|15[0-3,5-9]|16[6]|17[0135678]|18[0-9]|19[89])\\d{8}$");
					if(p.matcher(EatTelNumber).matches()){
					String name="尊敬的"+EatTelNumber+"机主";
					String codeGen=AppMethods.codeGen();
					System.out.println(codeGen);
					switch(method){
					case "resetTelphone":
					 content ="正在跟换绑定手机号,当前的验证码是："+codeGen+",如果不是本人操作请立即修改密码";
					break;
					case "resetpwd":
						 content ="正在修改密码,当前的验证码是："+codeGen+",如果不是本人操作请立即修改密码";
						break;
					case "EatStatisticsVerificationCode":
						content ="正在申请吃饭统计系统的验证码,当前的验证码是："+codeGen+",如果不是本人操作请立即修改密码";
						break;
					default:
					 break;
					}
					String templId = "32518";
					String operation= "onesms";
					System.out.println("机主"+EatTelNumber+"名字"+name+"内容"+content+"短信类型编号"+templId+"单/群"+operation);
					//发送验证码
					try {
						
						res = SMS.SetSMS(EatTelNumber, name, content, templId, operation);
						System.out.println(res);
						} catch (Exception e) {
						e.printStackTrace();
						}
					 msg="验证码发送成功";
					 map.put("msg", msg);
					 map.put("success", true);
					 map.put("codeGen", codeGen);
					 String json=JSON.toJSONString(map);  
					 response.setContentType("text/javascript;charset=utf-8");
					 response.setCharacterEncoding("utf-8");
					 response.getWriter().print(json);			
					}
					
					
					else{
						//手机号类型不对 非法访问
						msg="请填写正确的手机号";
						map.put("msg", msg);
						map.put("false", true);
						String json=JSON.toJSONString(map);  
						response.setContentType("text/javascript;charset=utf-8");
						response.setCharacterEncoding("utf-8");
						response.getWriter().print(json);	
					}	
				}
				
				else{
					//employeetelNumber为空  无法发送不执行任何操作
				}
				
			}catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
		}

	}
		
		
		
	}
	
