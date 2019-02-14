package com.xinhai.org.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinhai.org.entity.Eat_day_log;
import com.xinhai.org.entity.Eat_food;
import com.xinhai.org.entity.Eat_user;
import com.xinhai.org.sql.SqlMethods;
import com.xinhai.org.until.AppMethods;

/**
 * Servlet implementation class AddEatDayLog
 */
@WebServlet("/AddEatDayLog")
public class AddEatDayLog extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddEatDayLog() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	//	response.getWriter().append("Served at: ").append(request.getContextPath());
	doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
	try {
		request.setCharacterEncoding("utf-8");
		String userId=request.getParameter("userId");
		String isEat=request.getParameter("isEat");
		String riceNumber=request.getParameter("riceNumber");
		String AdditionalInformation=request.getParameter("AdditionalInformation");
		if(request.getParameter("newUserName")!=null){
			String newUserName=request.getParameter("newUserName");
			SqlMethods.UpdateEat_userName(Integer.parseInt(userId), newUserName);
		}
		String msg="";
		//插入之前检查是否今天有没有写入记录  主要是防止用户重复提交
		int count =SqlMethods.SearchCount(Integer.parseInt(userId));
		if(count==0){
		AppMethods.inserteatDayLog(Integer.parseInt(userId),Integer.parseInt(isEat),Double.parseDouble(riceNumber),AdditionalInformation);
		
		if(request.getParameterValues("lastlovefood")!=null){
			String[] lastlovefood=request.getParameterValues("lastlovefood");
//			for(int i=0;i<lastlovefood.length;i++){
//				System.out.println("lovefoodId:"+lastlovefood[i]);
//			}
			SqlMethods.insertEatUserLoveFood(lastlovefood, userId);
		}
		
		
		msg="提交完成";
		//提交完成 返回提交界面 查看提交结果
		}
		else{
		msg="今日的记录已经提交了 请不要重复提交";	
		}
		System.out.println(msg);
		List<Eat_day_log> edllessonList= new ArrayList<Eat_day_log>();
		edllessonList=SqlMethods.SelectEat_day_logbyUserId(Integer.parseInt(userId));
		request.setAttribute("msg",msg);
		request.setAttribute("eatUserid", userId);
		request.setAttribute("edllessonList", edllessonList);
				
		//获取昨日喜爱的食物List
		List<Eat_food> LastLoveFoodLessonList= new ArrayList<Eat_food>();
		LastLoveFoodLessonList=SqlMethods.SearchEat_userLastLoveFoodByUserId(userId);
		request.setAttribute("LastLoveFoodLessonList", LastLoveFoodLessonList);

		request.getRequestDispatcher("eatUserPage.jsp").forward(request, response);	
		
		
	} catch (Exception e) {
		// TODO: handle exception
	}
	
	}

}
