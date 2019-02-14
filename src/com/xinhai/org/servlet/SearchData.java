package com.xinhai.org.servlet;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.xinhai.org.entity.Eat_day_log;
import com.xinhai.org.entity.Eat_day_log_Statistical;
import com.xinhai.org.entity.Eat_user;
import com.xinhai.org.sql.SqlMethods;
import com.xinhai.org.until.AppMethods;

/**
 * Servlet implementation class SearchData
 */
@WebServlet("/SearchData")
public class SearchData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchData() {
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
		//	doGet(request, response);
		try {
			String methods=request.getParameter("methods");
			String openId=request.getParameter("openId");
			switch(methods){
			case "UserSelectionInterfaceData":
				
				List<Eat_user> lessonList= new ArrayList<Eat_user>();
				lessonList=SqlMethods.SearchEatUserInfoByOpenid(openId);
				int eatUserid=lessonList.get(0).getId();
				
				List<Eat_day_log> edllessonList= new ArrayList<Eat_day_log>();
				edllessonList=SqlMethods.SelectEat_day_logbyOpenid(openId);
				
				request.setAttribute("eatUserid", eatUserid);
				request.setAttribute("edllessonList", edllessonList);
				request.getRequestDispatcher("eatUserPage.jsp").forward(request, response);	
				
		    break;
			case "UsersViewStatisticsPage":
				
				int  tomorrowMealNumber=SqlMethods.SearchEat_day_logTomorrowStatisticsCount();
				double tomorrowRiceNumber=SqlMethods.SearchEat_day_logTomorrowStatisticsRiceNumberSum();
				
				List<Eat_day_log_Statistical> StatisticalLessonList = new ArrayList<Eat_day_log_Statistical>();
				StatisticalLessonList=SqlMethods.SearchEat_result_dayStatistical();
				//本月累计的费用
				//System.out.println("TodayCount:"+StatisticalLessonList.get(0).getTodayCount());
				String monthConditions="where   DATE_FORMAT( edl.firstAddTime, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' )";
				int countM =SqlMethods.SearchEatDayLogbyConditions(monthConditions);
			//	System.out.println(countM*15); 
				String all="";
				//累计的费用
				int countAll =SqlMethods.SearchEatDayLogbyConditions(all);
			//	System.out.println(countAll*15);	
				
				
				int todayTrueCount = SqlMethods.SearchEat_Result_DayTrueStatistical("today");
				
				//int lastdayTrueCount = SqlMethods.SearchEat_Result_DayTrueStatistical("lastday");
				Timestamp now = new Timestamp(System.currentTimeMillis());
				int lastdayTrueCount =SqlMethods.SearchEat_Result_DayTrueStatisticalNeW(now);
				int thisWeekTrueCount = SqlMethods.SearchEat_Result_DayTrueStatistical("thisWeek");
				
				int lastWeekTrueCount = SqlMethods.SearchEat_Result_DayTrueStatistical("lastWeek");
				
				int thisMonthTrueCount = SqlMethods.SearchEat_Result_DayTrueStatistical("thisMonth");
				
				double todayTrueRiceNumber=SqlMethods.SearchEat_Result_DayTrueStatisticalRiceNum("today");
				double lastdayTrueRiceNumber=SqlMethods.SearchEat_Result_DayTrueStatisticalRiceNum("lastday");
				double thisWeeTrueRiceNumber=SqlMethods.SearchEat_Result_DayTrueStatisticalRiceNum("thisWeek");
				double lastWeekTrueRiceNumber=SqlMethods.SearchEat_Result_DayTrueStatisticalRiceNum("lastWeek");
				double thisMonthTrueRiceNumber=SqlMethods.SearchEat_Result_DayTrueStatisticalRiceNum("thisMonth");
				
				
				
				
				
				
				
				request.setAttribute("StatisticalLessonList", StatisticalLessonList);
				
				
				
				//返回实际的数据人数和米饭消耗量
				
				request.setAttribute("todayTrueCount", todayTrueCount);
				request.setAttribute("lastdayTrueCount", lastdayTrueCount);
				request.setAttribute("thisWeekTrueCount", thisWeekTrueCount);
				request.setAttribute("lastWeekTrueCount", lastWeekTrueCount);
				request.setAttribute("thisMonthTrueCount", thisMonthTrueCount);
				request.setAttribute("todayTrueRiceNumber", todayTrueRiceNumber);
				request.setAttribute("lastdayTrueRiceNumber", lastdayTrueRiceNumber);
				request.setAttribute("thisWeeTrueRiceNumber", thisWeeTrueRiceNumber);
				request.setAttribute("lastWeekTrueRiceNumber", lastWeekTrueRiceNumber);
				request.setAttribute("thisMonthTrueRiceNumber", thisMonthTrueRiceNumber);
				
				request.setAttribute("tomorrowMealNumber", tomorrowMealNumber);
				request.setAttribute("tomorrowRiceNumber", tomorrowRiceNumber);
				
				
				request.setAttribute("countM", countM*15);
				request.setAttribute("countAll", countAll*15);
				request.getRequestDispatcher("usersViewStatisticsPage.jsp").forward(request, response);	
				break;
		    //通过手机号获取用户的信息
			case "SearchUserInformationByTelNumber":
				String EatTelNumber=request.getParameter("EatTelNumber");
				System.out.println("EatTelNumber:"+EatTelNumber);
				//判断手机号是否存在，不在就返回false
				int count=SqlMethods.SearchEatUserTel(EatTelNumber);
				Map<String, Object> map = new LinkedHashMap<String, Object>();
				if(count==1){
					//用户状态正常
					 map.put("msg", "成功");
					 map.put("success", true);
					
					 List<Eat_user> UserlessonList = new ArrayList<Eat_user>();
					 UserlessonList=SqlMethods.SearchEat_userInformationByTelNumber(EatTelNumber);
					 map.put("eatUserId", UserlessonList.get(0).getId());
					 map.put("eatName", UserlessonList.get(0).getName());
					 map.put("eatTelNumber",EatTelNumber);	
				}
				else if(count==0){
					int emploueeuserCount=SqlMethods.SearchCountByTelNumber(EatTelNumber);
					if(emploueeuserCount==1)
					{
						
						String UserName=SqlMethods.SearchEatUserNameByTelNumber(EatTelNumber);
						System.out.println("这次从外表中拉取到的名字 "+UserName);
						//获取一个uuid给他
						String uuid=AppMethods.Createuuid();
						 now = new Timestamp(System.currentTimeMillis());
						SqlMethods.insertEatUserInfoByTelNumber(uuid, now, EatTelNumber, UserName);
						//注册完毕后跳转
						
						
						//拉取这个外表中用户的信息   然后进行注册
						//获取用户的手机号和名字   并且随机赋予一个openid  
						//注册
						 map.put("msg", "成功");
						 map.put("success", true);
						 List<Eat_user> UserlessonList = new ArrayList<Eat_user>();
						 UserlessonList=SqlMethods.SearchEat_userInformationByTelNumber(EatTelNumber);
						 map.put("eatUserId", UserlessonList.get(0).getId());
						 map.put("eatName", UserlessonList.get(0).getName());
						 map.put("eatTelNumber",EatTelNumber);
					}
					else if((emploueeuserCount==0)){
						//外表里面也没有这个员工
						//获取这个员工的手机号和名字 对其进行注册  并且随机赋予一个openid  
						//注册
						String newUserName=request.getParameter("newUserName");
						if(newUserName==null||newUserName==""||newUserName.length()==0){
							newUserName="新海新员工";
						}
						String uuid=AppMethods.Createuuid();
						String eatName="新海新员工";
						now = new Timestamp(System.currentTimeMillis());
						SqlMethods.insertEatUserInfoByTelNumber(uuid, now, EatTelNumber, newUserName);
						
						 map.put("msg", "成功");
						 map.put("success", true);
						 List<Eat_user> UserlessonList = new ArrayList<Eat_user>();
						 UserlessonList=SqlMethods.SearchEat_userInformationByTelNumber(EatTelNumber);
						 map.put("eatUserId", UserlessonList.get(0).getId());
						 map.put("eatName", eatName);
						 map.put("eatTelNumber",EatTelNumber);
						 map.put("newUserName",eatName);
						 
						
					}
					else{
						 map.put("msg", "外表的账号信息异常");
						 map.put("false", true);
					}
					
				}
				
				
				else{
					 map.put("msg", "用户的账号存在异常");
					 map.put("false", true);
				}
				 String json=JSON.toJSONString(map);  
				 response.setContentType("text/javascript;charset=utf-8");
				 response.setCharacterEncoding("utf-8");
				 response.getWriter().print(json);		
				
				break;
			
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}

}
