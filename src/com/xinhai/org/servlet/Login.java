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
import com.xinhai.org.entity.Eat_result_day;
import com.xinhai.org.entity.Eat_user;
import com.xinhai.org.sql.SqlMethods;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
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
			//System.out.println("\u6768\u5929\u54f2");
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		System.out.println(username+"----------"+password);
		int count=SqlMethods.SearchUsername(username, password);
		if(count==1){
			List<Eat_user> lessonList=new ArrayList<Eat_user>();
			lessonList=SqlMethods.SearchAlltel();
			int userid=0;
			userid=SqlMethods.SearchUserid(username);
			request.getSession().setAttribute("userid", userid);
			request.getSession().setAttribute("UserList", lessonList);	
			System.out.println(1);
			List<Eat_food> MealList=new ArrayList<Eat_food>();
			MealList=SqlMethods.SearchEatfood();
			request.getSession().setAttribute("MealList", MealList);	
		//	System.out.println(2);
			int isEatTomorrow=SqlMethods.SearchEat_day_logTomorrowStatisticsCount();
			double riceNumberTomorrow=SqlMethods.SearchEat_day_logTomorrowStatisticsRiceNumberSum();
			request.getSession().setAttribute("isEatTomorrow", isEatTomorrow);
			request.getSession().setAttribute("riceNumberTomorrow", riceNumberTomorrow);
			//查询今天的吃饭人的名单列表
			List<Eat_day_log> TodayIsEatList=new ArrayList<Eat_day_log>();
			System.out.println(3);
			TodayIsEatList=SqlMethods.SearchEat_day_logTodayAllUserName();
			System.out.println(4);
			request.getSession().setAttribute("TodayIsEatList", TodayIsEatList);
			
			
			
			//查询今天是否有进行吃饭统计的等级  有的话就拉取
			List<Eat_result_day> TodayEatResultDayList=new ArrayList<Eat_result_day>();
			if(SqlMethods.SearchEat_result_dayToday()!=null){
				TodayEatResultDayList=SqlMethods.SearchEat_result_dayToday();
			}
			if(TodayEatResultDayList!=null){
			request.getSession().setAttribute("TodayEatResultDayList", TodayEatResultDayList);	
			}
			request.getRequestDispatcher("mainPage.jsp").forward(request, response);	
		}
		else {
			String msg=null;
			msg="请输入正确的账号密码";
			request.setAttribute("msg", msg);
			request.getRequestDispatcher("login2.jsp").forward(request, response);
		}
		
		
		
		
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	
	
	}

}
