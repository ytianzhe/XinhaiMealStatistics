package com.xinhai.org.servlet;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinhai.org.sql.SqlMethods;

/**
 * Servlet implementation class EatUserRegister
 */
@WebServlet("/EatUserRegister")
public class EatUserRegister extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EatUserRegister() {
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
		request.setCharacterEncoding("utf-8");
		try {
		Timestamp now = new Timestamp(System.currentTimeMillis());
		String telNumber=request.getParameter("telNumber");
		String eatUserName=request.getParameter("eatUserName");
		String openid=request.getParameter("openid");
		String msg="";
		System.out.println("telNumber:"+telNumber+"eatUserName:"+eatUserName+"openid:"+openid);
		
		
			int openidcount=SqlMethods.SearchOpenid(openid);
			switch (openidcount){
			case 1:
				//已经有这个openid在正常账号中了，停止注册
				int status=SqlMethods.SearchStatusByOpenid(openid);
				switch(status){
				case 1:
					//openid状态正常 已经有注册记录 停止注册
					msg="该openid（钉钉账号）已经被注册";
					break;
				case 2:
					msg="该openid（钉钉账号）已经被锁定";
				break;
				case 0:
					msg="该openid（钉钉账号）已经被标记删除";
					break;
				default:
					msg="状态码异常 停止注册";
					break;
				}
			//	msg="该钉钉账号已经被注册";
			//返回二维码扫描界面	
				request.getRequestDispatcher("http://wcphp172.xinhaimobile.cn/ding_login/dingScanInit.php?key=test&val=http://localhost:8080/XinhaiMealStatistics/EatLogin?methods=Eatlogin").forward(request, response);	
			break;
			case 0:
				//没有这个账号可以进行注册  校验用户和手机号会在注册界面的点击提交是后校验 这里就不进行校验了
				//直接进行注册
				SqlMethods.insertEatUserInfo(openid, now, telNumber, eatUserName);
				msg="注册成功";
				//注册成功后直接登入到员工界面
			break;
			default:
				msg="注册异常  操作被阻止";
				//异常 退出注册
				//返回二维码扫描界面	
			break;
			}
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

}
