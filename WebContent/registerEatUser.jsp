<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册界面</title>
<link href="css/bootstrap.css" rel="stylesheet" media="screen">
<link href="css/login.css" rel="stylesheet" type="text/css" />

</head>
<body>
	<div class="login_box">
		<div class="login_l_img">
			<img src="images/login-img.png" />
		</div>
		<div class="login" style="height: 400px;">
			<div class="login_logo">
				<a href="#"><img src="images/login_logo.png" /></a>
			</div>
			<div class="login_name">
				<p>商务智能组注册系统</p>
			</div>
			<form action="EatUserRegister" method="post" class="form-horizontal"    >
				<div class="form-group">
					<!--  	<label for="inputEmail3" class="col-sm-2 control-label">Email</label>-->
					<div class="col-sm-10">
						<input type="text" class="form-control" id="telNumber"
							name="telNumber" placeholder="手机号">
					</div>
				</div>
				<div class="form-group">
					<!--	<label for="inputPassword3" class="col-sm-2 control-label">Password</label>-->
					<div class="col-sm-10">
						<input type="text" class="form-control" id="eatUserName"
							name="eatUserName" placeholder="名字">
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" class="btn btn-primary" id="submit">提交</button>
						<input type="hidden" value="${Openid}" name="openid"/>
					</div>
				</div>
					
			</form>
		</div>

	</div>
	<div style="text-align: center;"></div>








</body>
</html>














