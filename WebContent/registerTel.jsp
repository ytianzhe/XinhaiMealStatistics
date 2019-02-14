<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="Pragma" content="no-cache">
<meta http-equiv="Cache-Control" content="no-cache">
<meta http-equiv="Expires" content="0">
<title>智能商务手机号绑定系统</title>
<link href="css/login.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery-3.3.1.min.js" charset="UTF-8"></script>
<script>
function msg(){
	 var msg = document.getElementById("msg").value;
	 if(msg==""){
		// alert("msg为空");
	 }
	 else if(msg==null){
		 alert("msg为null");
	 }
	 else{
		 alert(msg);
	 }
	
}
</script>
</head>
<body onload="msg()" style="overflow:auto">
	<div class="login_box" >
		<div class="login_l_img">
			<img src="images/login-img.png" />
		</div>
		<div class="login" >
			<div class="login_logo">
				<a href="#"><img src="images/login_logo.png" /></a>
			</div>
			<div class="login_name">
				<p>智能商务管理系统</p>
			</div>
			<form  id="userform"  action="" method="post"   >
			 	<table width="100%">
			 	<tr><td><input  type="text"   value="${userName}" placeholder="员工名字" disabled="disabled"/></td></tr>
				<tr><td><input  type="text"  name="telnumber" id="telnumber" value="" placeholder="手机号"/></td></tr>
			
				<tr><td><input type="button" id="send" onclick="SendTel()" value="绑定" /></td></tr>
				</table>
				<input  type="hidden"  name="msg" id="msg" value="${msg}" />
				<input id="userId" type="hidden" name="userId" value="${userId}"  />
				<input id="userName" type="hidden" name="userName" value="${userName}"  />		
			</form>
		</div>

	</div>
	<div style="text-align: center;">
	</div>
</body>
<script>
function SendTel(){
	var telnumber=document.getElementById("telnumber").value;
	var userName=document.getElementById("userName").value;
	//var userId=document.getElementById("userId").value;
//	alert(telnumber+"   "+name);
	if(telnumber==null||telnumber.length==0||telnumber==""){
		alert("请输入手机号");
		return;  
	}
	else{
		//校验手机号是否被注册
			$.ajax({
					type:'post',
					url:'/XinhaiMealStatistics/RepeatCheck?methods=RepeatTel&&telnumber='+telnumber,
										// data:$("#myform").serialize(), 
										//	data: 
										cache : false,
										dataType : 'json',
										success : function(data) {
											if (data.success) {
												alert("该手机号已经被注册");
												return;
											} else {
												//alert("没有");
												if(confirm("确定添加用户 ："+userName+"手机号为："+telnumber+"吗？")){  
													  document.getElementById("userform").action="/XinhaiMealStatistics/AddTel?methods=AddTelByUserid";
													  document.getElementById("userform").submit();
												//	window.location.href = "AddTel?telnumber="+telnumber+"&&eatname="+eatname; 
											        return true;  
											    }  
											}
										}
									});
		}
}

</script>

</html>


