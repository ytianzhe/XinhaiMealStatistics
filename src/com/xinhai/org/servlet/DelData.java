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
import com.xinhai.org.entity.Eat_user;
import com.xinhai.org.sql.SqlMethods;

/**
 * Servlet implementation class DelTel
 */
@WebServlet("/DelData")
public class DelData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DelData() {
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
			switch(methods){
				case "deltel":
				String msg=null;
				String userid =request.getParameter("userid");
//				String name =request.getParameter("name");
				System.out.println("userid:"+userid);
				SqlMethods.UpdateEat_userStatus(Integer.parseInt(userid),"0");
				msg="删除成功";
				List<Eat_user> lessonList=new ArrayList<Eat_user>();
				lessonList=SqlMethods.SearchAlltel();
				request.getSession().setAttribute("UserList", lessonList);	
				request.setAttribute("msg", msg);
				request.getRequestDispatcher("userPage.jsp").forward(request, response);	
				break;
				
				case "delTodayLogData":
					String userId=request.getParameter("userId");
					SqlMethods.UpdateEat_day_logStatusById(Integer.parseInt(userId));
					msg="删除完成 请填写OA";
					List<Eat_day_log> edllessonList= new ArrayList<Eat_day_log>();
					edllessonList=SqlMethods.SelectEat_day_logbyUserId(Integer.parseInt(userId));
					request.setAttribute("msg",msg);
					request.setAttribute("eatUserid", userId);
					request.setAttribute("edllessonList", edllessonList);
					request.getRequestDispatcher("eatUserPage.jsp").forward(request, response);	
				break;
				default:
					
					break;
			}
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
		e.printStackTrace();
		}
	}

}
