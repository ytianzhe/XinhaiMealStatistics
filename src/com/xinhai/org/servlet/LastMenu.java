package com.xinhai.org.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinhai.org.entity.Eat_food;
import com.xinhai.org.sql.SqlMethods;

/**
 * Servlet implementation class LastMenu
 */
@WebServlet("/LastMenu")
public class LastMenu extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LastMenu() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		try{
			//清空昨日的数据
			SqlMethods.UpdateEatFoodisLastempty();
			String[] lastfood = request.getParameterValues("lastfood");
			for(int i=0;i<lastfood.length;i++){
				System.out.println("lastfood"+lastfood[i]);
			}
			SqlMethods.UpdateEatFoodisLast(lastfood);
			//更新完成 返回数据
			List<Eat_food> MealList=new ArrayList<Eat_food>();
			MealList=SqlMethods.SearchEatfood();
			request.getSession().setAttribute("MealList", MealList);	
			request.getRequestDispatcher("MealPage.jsp").forward(request, response);
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

}
