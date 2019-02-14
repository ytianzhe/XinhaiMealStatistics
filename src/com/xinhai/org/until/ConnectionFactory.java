package com.xinhai.org.until;

import java.sql.Connection;
import java.sql.DriverManager;


public class ConnectionFactory {
	/**
	 * 连接数据库zhongkonghe
	 * @return
	 */
	public static Connection makeConnection(){
		 try {
			 Connection conn = null;
		        //加载数据库驱动类
			 	//Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		        Class.forName("com.mysql.jdbc.Driver").newInstance();
		        //数据库连接URL
		        /// String url = "jdbc:sqlserver://192.168.0.5;DatabaseName=XHA8";
		        //  String  url = "jdbc:mysql://10.168.20.196:3306/xh_ytz?useUnicode=true&characterEncoding=utf-8&useSSL=false"; 
		        //	   String  url = "jdbc:mysql://localhost:3306/xh_ytz?useUnicode=true&characterEncoding=utf-8&useSSL=false"; 
		        ///	   String  url = "jdbc:mysql://10.168.20.196:3306/xh_ytz?useUnicode=true&characterEncoding=utf-8&useSSL=false"; 
		        //	  String  url = "jdbc:mysql://10.168.20.186:3306/xh_ytz?useUnicode=true&characterEncoding=utf-8&useSSL=false"; 
		        //  String user="root";
		        // String password="1234";
		        //	     String username = "xh_ytz";
		        //	     String password = "xinhai0574";
		        //	    //   String  url = "jdbc:mysql://192.168.0.100:3306/eight_factory?useUnicode=true&characterEncoding=utf-8&useSSL=false"; 
		        //数据库用户名
		        //   String user = "eight_factory";
		        //数据库密码
		        // String password = "xinhai0574";
		        //根据数据库参数取得一个数据库连接
		        
		        
		       //外网的数据库链接池 
		     String  url =   "jdbc:mysql://10.66.117.211:3306/ai_xinhai_wx?useUnicode=true&characterEncoding=utf8&characterSetResults=utf8"; 
		     String username = "ai_xinhai";
		     String password = "xinhai0574";  
		     
		     
		      //  String  url = "jdbc:mysql://10.168.20.196:3306/xh_ytz?useUnicode=true&characterEncoding=utf-8&useSSL=false"; 
		     //	     String username = "xh_ytz";
		     //	     String password = "xinhai0574";
		     conn = DriverManager.getConnection(url, username, password);
		     return conn;
		} catch (Exception e) {
			// TODO: handle exception
			return null;
		}
	}
	
	public static void main(String[] arg){
		Connection makeConnection = makeConnection();
		System.err.println(makeConnection);
			
		}

}
