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

import com.xinhai.org.entity.Eat_user;
import com.xinhai.org.sql.SqlMethods;
import com.xinhai.org.until.AppMethods;

/**
 * Servlet implementation class EatLogin
 */
@WebServlet("/EatLogin")
public class EatLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EatLogin() {
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
		request.setCharacterEncoding("utf-8");
		String methods=request.getParameter("methods");
		String openid=request.getParameter("openid");
		System.out.println("methods:"+methods);
		System.out.println("openid:"+openid);
		String msg="";
		int openidStatus=0;
		int userId=0;
		switch(methods){
		case "EatLoginByTel":
			System.out.println("用户通过手机来登入");
			String telNumberByTelInterface=request.getParameter("telNumber");
			System.out.println(telNumberByTelInterface);
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
			break;
		case "EatloginTel":
			System.out.println("methods:"+methods);
			request.getRequestDispatcher("TelLogin.jsp").forward(request, response);
		break;
		case "Eatlogin":
			
			
			System.out.println("与系统开始链接，查询是否有该openid:"+openid);
			int count=SqlMethods.SearchOpenid(openid);
			System.out.println("count:"+count);
			switch(count){
			case 1:
				System.out.println("有这个openid  判断状态是否为正常   正常1通过  2锁定 禁止登入  0删除没有这个人 让他重新注册 ");
				//有这个openid  判断状态是否为正常   正常1通过  2锁定 禁止登入  0删除没有这个人 让他重新注册 
				List<Eat_user> lessonList= new ArrayList<Eat_user>();
				lessonList=SqlMethods.SearchEatUserInfoByOpenid(openid);
				openidStatus=lessonList.get(0).getStatus();
				System.out.println("openidStatus:"+openidStatus);
				if(openidStatus==1){
				//正常状态 进行 用户菜单	
					System.out.println("用户初步验证完毕,开始验证手机号是否完整 ");
					//如果有手机号就跳转 没有手机号就要求注册
					String telNumber=lessonList.get(0).getMobile();
					if(telNumber==null||telNumber==""||telNumber.length()==0){
						//没有手机号要求注册
						//跳转的时候 附带userid  这样方便插入手机号
						System.out.println("跳转时候的userid:"+lessonList.get(0).getId());
						request.setAttribute("userId", lessonList.get(0).getId());
						request.setAttribute("userName", lessonList.get(0).getName());
						request.setAttribute("msg", "检测到您没有  绑定手机号 请添加手机号");
						request.getRequestDispatcher("registerTel.jsp").forward(request, response);
					}
					else{
						//有手机号正常登入
					request.getSession().setAttribute("eatUserid", lessonList.get(0).getId());
					request.getSession().setAttribute("openId", openid);
					request.getSession().setAttribute("eatUserName", lessonList.get(0).getName());
					request.getRequestDispatcher("/WEB-INF/eatUserMainPage.jsp").forward(request, response);
					}
				}								 
				else{
					//账号状态 不正常 回退至二维码页面
					request.setAttribute("msg", "该钉钉账号状态异常");
					request.getRequestDispatcher("http://wcphp172.xinhaimobile.cn/ding_login/dingScanInit.php?key=test&val=http://localhost:8080/XinhaiMealStatistics/EatLogin?methods=Eatlogin").forward(request, response);	
				}
			break;
			case 0:
				//没有这个openid 跳转至注册界面 
				//不跳转直接进行 注册 然后跳转
				
				
				//没有这个openid查询一下这个人的名字
				String EatUserName=AppMethods.getName(openid);
				int UserNameCount=SqlMethods.SearchUserNameCountByUserName(EatUserName);
				if(UserNameCount==1){
					//有这个人的名字 他已经注册过了直接登入拉取信息
					
					userId=SqlMethods.SearchUserUserIdCountByUserName(EatUserName);
					lessonList=SqlMethods.SearchEatUserInfoByUserid(userId);
					request.getSession().setAttribute("eatUserid", lessonList.get(0).getId());
					request.getSession().setAttribute("openId", lessonList.get(0).getOpenid());
					request.getSession().setAttribute("eatUserName", lessonList.get(0).getName());
					request.getRequestDispatcher("/WEB-INF/eatUserMainPage.jsp").forward(request, response);
				}
				
				else{
				
				
				System.out.println("不跳转直接进行注册 然后进行跳转");
				System.out.println("openid:"+openid);
				String eatUserName=AppMethods.getName(openid);
				System.out.println("eatUserName:"+eatUserName);
				//注册的方法隔离不写这里
				//开始注册
				System.out.println("没有openid记录的人开始注册");
				AppMethods.insertInfo(openid, eatUserName);
				System.out.println("注册完毕  开始跳转");
				List<Eat_user> lessonListEu= new ArrayList<Eat_user>();
				lessonListEu=SqlMethods.SearchEatUserInfoByOpenid(openid);
				String telNumber=lessonListEu.get(0).getMobile();
				System.out.println("telNumber:"+telNumber);
				if(telNumber==null||telNumber==""||telNumber.length()==0){
					//没有手机号要求注册
					//跳转的时候 附带userid  这样方便插入手机号
					
					List<Eat_user> lessonList1= new ArrayList<Eat_user>();
					lessonList1=SqlMethods.SearchEatUserInfoByOpenid(openid);
					System.out.println("跳转时候的userid:"+lessonList1.get(0).getId());
					request.setAttribute("userId", lessonList1.get(0).getId());
					request.setAttribute("userName", lessonList1.get(0).getName());
					request.setAttribute("msg", "检测到您没有  绑定手机号 请添加手机号");
					request.getRequestDispatcher("registerTel.jsp").forward(request, response);
				}
				}
			break;
			default:
				//其他   异常数量 停止登陆
				System.out.println("异常");
			break;
			}
			//Eatlogin结束
		}
	} catch (Exception e) {
		// TODO: handle exception
	}
	}
}
