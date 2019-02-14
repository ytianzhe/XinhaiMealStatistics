package com.xinhai.org.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinhai.org.entity.Eat_user;
import com.xinhai.org.sql.SqlMethods;

/**
 * Servlet implementation class AddTel
 */
@WebServlet("/AddTel")
public class AddTel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddTel() {
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
		try{
			request.setCharacterEncoding("utf-8");
			String methods=request.getParameter("methods");
			System.out.println(methods);
			List<Eat_user> lessonList=new ArrayList<Eat_user>();
			switch(methods){
			case "AddtelByTelNumberandName":
				String msg=null;
				String telNumber =request.getParameter("telnumber");
				String eatName =request.getParameter("eatname");
				int count=SqlMethods.SearchEatUserTel(telNumber);
				System.out.println("count: "+count);
				switch(count){
				case 1:
					msg="该账号已经注册";
					break;
				case 0:
					//执行插入操作
					Timestamp now = new Timestamp(System.currentTimeMillis());
					SqlMethods.insertEat_User(now,eatName,telNumber);
					//回跳到后台控制面板然后刷新数据eatName
					msg="注册成功";
					break;
					default:
						msg="数据异常，请联系管理员";
						break;
				}
				
				lessonList=SqlMethods.SearchAlltel();
				request.getSession().setAttribute("UserList", lessonList);	
				request.setAttribute("msg", msg);	
				request.getRequestDispatcher("userPage.jsp").forward(request, response);
				break;
			case "AddTelByUserid":
				System.out.println("增加用户手机号");
				String userId=request.getParameter("userId");
				String telnumber=request.getParameter("telnumber");
				System.out.println(userId+"-----"+telnumber);
				String tel = SqlMethods.SearchTelCount(Integer.parseInt(userId));
				if(tel==""|tel==null||tel.length()==0){
					//没有手机号 开始更新
					SqlMethods.UpdateTelNumber(telnumber, Integer.parseInt(userId));
					msg="手机号码添加成功";
				}
				else{
					
					//有手机号 停止更新
					msg="该账号已经有手机号注册，请不要重复填写";
				}
				
			//手机号补全  如果 已经 有了 开始跳转用户界面
				System.out.println("msg"+msg);
				lessonList=SqlMethods.SearchEatUserInfoByUserid(Integer.parseInt(userId));
				request.getSession().setAttribute("eatUserid", lessonList.get(0).getId());
				request.getSession().setAttribute("openId", lessonList.get(0).getOpenid());
				request.getSession().setAttribute("eatUserName", lessonList.get(0).getName());
				
				//重定向
				
				
			//request.getRequestDispatcher("userPage.jsp").forward(request, response);
			System.out.println("开始跳转到用户主页面");
				request.getRequestDispatcher("/WEB-INF/eatUserMainPage.jsp").forward(request, response);
		   // request.getRequestDispatcher("/WEB-INF/eatUserMainPage.jsp").forward(request, response);
			break;
			
			default :
				
			break;
			}
			
			
			
			
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
	}

}
