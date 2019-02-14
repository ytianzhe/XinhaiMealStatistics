package com.xinhai.org.servlet;
import java.io.IOException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.xinhai.org.sql.SqlMethods;
import com.xinhai.org.until.AppMethods;
/**
 * Servlet implementation class EatLoginTel
 */
@WebServlet("/EatLoginTel")
public class EatLoginTel extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EatLoginTel() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			request.setCharacterEncoding("utf-8");
			System.out.println("进入跳转");
			String methods=request.getParameter("methods");
			System.out.println("methods:"+methods);
			String msg="";
			int userId=0;
			switch(methods){
			case "EatLoginByTel":
				System.out.println("用户通过手机来登入");
				String telNumberByTelInterface=request.getParameter("telNumber");
				//System.out.println(telNumberByTelInterface);
				if(telNumberByTelInterface==null||telNumberByTelInterface==""||telNumberByTelInterface.length()==0){
					msg="手机不能为空";
					request.setAttribute("msg",msg);	
					request.getRequestDispatcher("TelLogin.jsp").forward(request, response);
				   }
				else{
				int TelCount =SqlMethods.SearchEatUserTel(telNumberByTelInterface);
				
				switch(TelCount){
					case 1:
					//已经有这个手机号了 直接进入 跳转
					//开始跳转
					//去个人主页  拉取信息的sql
					//获取UserId
					userId=SqlMethods.SearchUserIdByTelNumber(telNumberByTelInterface);
					request.setAttribute("userId", userId);	
					request.getRequestDispatcher("TelEatLog.jsp").forward(request, response);
					break;
				case 0:
					//没有这个手机号  进行注册 注册的名字采用独立表的方式进行
					//拉取名字的sql  然后注册 
					String UserName=SqlMethods.SearchEatUserNameByTelNumber(telNumberByTelInterface);
					if(UserName==""||UserName==null||UserName.length()==0){
						//没有这个名字
						UserName="新海新员工";
						}
					//获取一个uuid给他
					String uuid=AppMethods.Createuuid();
					Timestamp now = new Timestamp(System.currentTimeMillis());
					SqlMethods.insertEatUserInfoByTelNumber(uuid, now, telNumberByTelInterface, UserName);
					//注册完毕后跳转
					//获取userId
					userId=SqlMethods.SearchUserIdByTelNumber(telNumberByTelInterface);
					//开始跳转到手机页面
					request.setAttribute("userId",userId);	
					request.getRequestDispatcher("TelEatLog.jsp").forward(request, response);
					break;
					default:
				    break;
					}
				}
				break;
				 case "EatloginTel":
				//System.out.println("methods:"+methods);
					 System.out.println("获取到的methods是 EatloginTel");
				request.getRequestDispatcher("TelLogin.jsp").forward(request, response);
				break;
		
				}
			
		}catch (Exception e){
			e.printStackTrace();
		}
	}
}


