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
 * Servlet implementation class AddResultDay
 */
@WebServlet("/AddResultDay")
public class AddResultDay extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddResultDay() {
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
		try{
			request.setCharacterEncoding("utf-8");
			String msg=null;
			String ActualNumbersToday =request.getParameter("ActualNumbersToday");
			String NumberOfRemainingRice =request.getParameter("NumberOfRemainingRice");
			System.out.println(ActualNumbersToday+"    "+NumberOfRemainingRice);
			int count=SqlMethods.SearchEatResultDay();
		//	System.out.println("count: "+count);
			switch(count){
			case 1:
				msg="今天的数据已经插入过了";
				break;
			case 0:
				//执行插入操作
				Timestamp now = new Timestamp(System.currentTimeMillis());
				SqlMethods.insertResultDay(now, Integer.parseInt(ActualNumbersToday), Integer.parseInt(ActualNumbersToday)-Integer.parseInt(NumberOfRemainingRice));
				
				//回跳到后台控制面板然后刷新数据Resultday
				msg="插入成功";
				break;
				default:
					msg="数据异常，请联系管理员";
					break;
			}	
			
			System.out.println(msg);
			
			//查找明日的统计结果  一个是吃饭人数  一个数米饭数量
			//查找表eat_day_log里的  本日所有数量 进行技术  累加riceNumber字段  
			int isEatTomorrow=SqlMethods.SearchEat_day_logTomorrowStatisticsCount();
			double riceNumberTomorrow=SqlMethods.SearchEat_day_logTomorrowStatisticsRiceNumberSum();
			
			
			request.setAttribute("msg", msg);
			request.setAttribute("isEatTomorrow", isEatTomorrow);
			request.setAttribute("riceNumberTomorrow", riceNumberTomorrow);
			request.getRequestDispatcher("EatPage.jsp").forward(request, response);
			
			
			
	}catch (Exception e) {
		e.printStackTrace();
	}
		// TODO: handle exception
	}

}
