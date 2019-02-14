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

import com.xinhai.org.entity.Eat_food;
import com.xinhai.org.entity.Eat_user;
import com.xinhai.org.sql.SqlMethods;

/**
 * Servlet implementation class AddMeal
 */
@WebServlet("/AddMeal")
public class AddMeal extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddMeal() {
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
			String msg=null;
			String mealname=request.getParameter("mealname");
			String mealtype=request.getParameter("mealtype");
			String userid=request.getParameter("userid");
			String lastfood=request.getParameter("lastfood");
			int mealtypeid=0;
			System.out.println("mealname: "+mealname+"mealtype:"+mealtype+"userid:"+userid+"lastfood:"+lastfood);
			
			
			int count=SqlMethods.SearchEatFood(mealname);
			System.out.println("count: "+count);
			switch(count){
			case 1:
				msg="该菜名已经注册";
				break;
			case 0:
				//改菜没有录入 开始录入  查询菜的类型是否有录入
				Timestamp now = new Timestamp(System.currentTimeMillis());
				int typecount=SqlMethods.SearchEatFoodType(mealtype);
				switch(typecount){
					case 1://有该类型停止插入 并且查询该类型对应的id 等待菜名注册
						mealtypeid=SqlMethods.SearchEatFoodTypeId(mealtype);
						SqlMethods.insertEat_food(now, mealname, mealtypeid,Integer.parseInt(userid));
						msg="菜名注册成功，该菜品类型已经注册过";
					break;
					case 0:
					//没有改类型 进行注册该类型
						SqlMethods.inserteat_food_type(now, mealtype);
						mealtypeid=SqlMethods.SearchEatFoodTypeId(mealtype);
						SqlMethods.insertEat_food(now, mealname, mealtypeid,Integer.parseInt(userid));
						msg="菜名注册成功，该菜品类型没有注册过";
					break;
					default:
						//其他  数据异常暂停注册
						msg="注册失败，菜名数据异常";
					break;
				}
				//执行插入操作
				
			//	SqlMethods.insertEat_food(now, mealname, mealtype,Integer.parseInt(userid));
				//SqlMethods.insertEat_User(now,eatName,telNumber);
				//回跳到后台控制面板然后刷新数据eatName
				break;
				default:
				msg="数据异常，请联系管理员";
				break;
			}
			List<Eat_food> lessonList=new ArrayList<Eat_food>();
			
			lessonList=SqlMethods.SearchEatfood();
			request.getSession().setAttribute("MealList", lessonList);	
			request.setAttribute("msg", msg);	
			request.getRequestDispatcher("MealPage.jsp").forward(request, response);
			
			
		}catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	
	
	}

}
