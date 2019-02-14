package com.xinhai.org.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinhai.org.entity.Eat_day_log_Statistical;
import com.xinhai.org.entity.Eat_food;
import com.xinhai.org.sql.SqlMethods;

/**
 * Servlet implementation class NewLoginServlet
 */
@WebServlet("/NewLoginServlet")
public class NewLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NewLoginServlet() {
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
		try {
			List<Eat_day_log_Statistical> StatisticalLessonList = new ArrayList<Eat_day_log_Statistical>();
			StatisticalLessonList=SqlMethods.SearchEat_result_dayStatistical();
			int lastdayCount=StatisticalLessonList.get(0).getLastdayCount();
			double lastdayRiceCount= StatisticalLessonList.get(0).getLastdayRiceNum();
			int lastdayTrueCount =SqlMethods.lastEatTrueCountSearchEat_result_day();
			double lastdayTrueRiceNumber=SqlMethods.SearchEat_Result_DayTrueStatisticalRiceNum("lastday");
			//返回实际的数据人数和米饭消耗量
			request.setAttribute("lastdayTrueCount", lastdayTrueCount);
			request.setAttribute("lastdayTrueRiceNumber", lastdayTrueRiceNumber);
			request.setAttribute("lastdayCount", lastdayCount);
			request.setAttribute("lastdayRiceCount", lastdayRiceCount);
			//获取昨日菜肴
			List<Eat_food> EatFoodLastList = new ArrayList<Eat_food>();
			EatFoodLastList=SqlMethods.SearchEatfoodisLast();
			request.setAttribute("EatFoodLastList", EatFoodLastList);
			request.getRequestDispatcher("newUserChoosePage.jsp").forward(request, response);	
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	
		
		
		
	}

}
