package com.xinhai.org.servlet;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.jasper.tagplugins.jstl.core.Catch;

import com.alibaba.fastjson.JSON;
import com.xinhai.org.sql.SqlMethods;

/**
 * Servlet implementation class RepeatCheck
 */
@WebServlet("/RepeatCheck")
public class RepeatCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RepeatCheck() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at:
		// ").append(request.getContextPath());
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		try {
			 Map<String, Object> map = new LinkedHashMap<String, Object>();
			String methods = request.getParameter("methods");
			System.out.println("methods:" + methods);
			switch (methods) {
			case "RepeatTel":

				String telnumber = request.getParameter("telnumber");
				System.out.println(telnumber);
				int count = SqlMethods.RepeatTel(telnumber);
				if (count == 0) {
					 map.put("false", true); 
				} else {
					 map.put("success", true); 
				}
				break;
			}
			
			 String json=JSON.toJSONString(map);  
			 System.out.println(json);//输出{"a":"aaa","b":"bbb","c":"ccc"}   
			 response.setContentType("text/javascript;charset=utf-8");
			 response.setCharacterEncoding("utf-8");
			 response.getWriter().print(json);		
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
