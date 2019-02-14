<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
		<link rel="stylesheet" href="css/bootstrap.css">
		<link rel="stylesheet" href="css/index.css">	<!-- 修改自Bootstrap官方Demon，你可以按自己的喜好制定CSS样式 -->
		<link rel="stylesheet" href="css/font-change.css">


		
</head>

<body>
<div style="min-height:40px">
<nav class="navbar navbar-inverse navbar-fixed-top" >
      		<div class="container">
        		<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" >
		            	<span class="sr-only">Toggle navigation</span>
		            	<span class="icon-bar"></span>
		            	<span class="icon-bar"></span>
		            	<span class="icon-bar"></span>
					</button>
	          		<a class="navbar-brand" >新海商务智能组管理系统 </a>
        		</div>
        		
				<div id="navbar" class="navbar-collapse collapse">
					<ul class="nav navbar-nav navbar-right">
					<li><a href="###" ><i class="fa fa-users"></i> 欢迎您:${eatUserName}</a></li>
					
					<li><a href="###" ><i class="fa fa-calendar"></i><%=new java.sql.Timestamp(System.currentTimeMillis()).toString().substring(0,10) %>  </a></li>	
				 	<li><a href="Logout" onclick="return queren()" target=_top></i>登出 </a></li>	 
				
					<!-- 			            
						<li><a href="###" onclick="showAtRight('userList.jsp')"><i class="fa fa-users"></i> 用户列表</a></li>	
						<li><a href="###" onclick="showAtRight('productList.jsp')"><i class="fa fa-list-alt"></i> 产品列表</a></li>
						<li><a href="###" onclick="showAtRight('recordList.jsp')" ><i class="fa fa-list"></i> 订单列表</a></li>	
					 -->
					</ul>
          			
        		</div>
      		</div>
    	</nav>
    	</div>
    		<script>
	function queren(){
		
		 if(confirm("确认退出吗？")){  
		        return true;  
		    }  
		    return false;  
		
	}
	
	</script>
</body>
</html>