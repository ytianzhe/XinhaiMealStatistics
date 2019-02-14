package com.xinhai.org.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.xinhai.org.until.AppMethods;
import com.xinhai.org.until.DemoTest3;

/**
 * Servlet implementation class sendDingDing
 */
@WebServlet("/SendDingDing")
public class SendDingDing extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SendDingDing() {
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
			String methods=request.getParameter("methods");
			switch(methods){
			
			case "sendtest":
				String token2=request.getParameter("token");
				ChatbotSend.SenderweimaTelthree(token2);
				break;
			
			case "send":
				String token=request.getParameter("token");
				ChatbotSend.SenderweimaTelTWO(token);
				break;
				
			case "sendRice":
				AppMethods.SendRice();
				break;
				
			case "StatisticalScheduling":
				new DemoTest3().run();
				break;	
				
			
			default:
				break;
			}
			
			
		}catch(Exception e){e.printStackTrace();}
	}

}
