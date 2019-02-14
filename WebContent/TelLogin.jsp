<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<meta name="viewport" content="width=device-width,user-scalable=no,minimum-scale=1.0, maximum-scale=2.0" />
<link href="iconfont/style.css" type="text/css" rel="stylesheet">
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<style>
	body{color:#fff; font-family:"微软雅黑"; font-size:14px;}
	.wrap1{position:absolute; top:0; right:0; bottom:0; left:0; margin:auto }/*把整个屏幕真正撑开--而且能自己实现居中*/
	.main_content{background:url(images/main_bg.png) repeat; margin-left:auto; margin-right:auto; text-align:left; float:none; border-radius:8px;}
	.form-group{position:relative;}
	.login_btn{display:block; background:#3872f6; color:#fff; font-size:15px; width:100%; line-height:50px; border-radius:3px; border:none; }
	.login_input{width:100%; border:1px solid #3872f6; border-radius:3px; line-height:40px; padding:2px 5px 2px 30px; background:none;}
	.icon_font{position:absolute; bottom:15px; left:10px; font-size:18px; color:#3872f6;}
	.font16{font-size:16px;}
	.mg-t20{margin-top:20px;}
	@media (min-width:200px){.pd-xs-20{padding:20px;}}
	@media (min-width:768px){.pd-sm-50{padding:50px;}}
	#grad {
	  background: -webkit-linear-gradient(#4990c1, #52a3d2, #6186a3); /* Safari 5.1 - 6.0 */
	  background: -o-linear-gradient(#4990c1, #52a3d2, #6186a3); /* Opera 11.1 - 12.0 */
	  background: -moz-linear-gradient(#4990c1, #52a3d2, #6186a3); /* Firefox 3.6 - 15 */
	  background: linear-gradient(#4990c1, #52a3d2, #6186a3); /* 标准的语法 */
	}
</style>
<title>手机端明日预约登入</title>
</head>
<body style="background:url(images/bg.jpg) no-repeat;">
	
 <div class="container wrap1" style="height:450px;">
            <h2 class="mg-b20 text-center">吃饭统计手机登入接口</h2>
            <div class="col-sm-8 col-md-5 center-auto pd-sm-50 pd-xs-20 main_content">
                <p class="text-center font16">用户登录</p>
                <form id="TelLoginForm" action=""   Method="Post">
                    <div class="form-group mg-t20">
                        <i class="icon-user icon_font"></i>
                        
                        <input type="text"  class="login_input" name="telNumber" id="telNumber" value=""   placeholder="请输入手机号"  />
                    </div>
                    
                    <div class="checkbox mg-b25">
                    	<input type="hidden" name="methods" value="EatLoginByTel"/>
                    </div>
                    <button  class="login_btn"   onclick="EatLoginTel()">登 录</button>
               </form>
        </div><!--row end-->
    </div><!--container end-->
	


	
	<script>
function EatLoginTel(){
	var telNumber =document.getElementById("telNumber").value;	
	if(telNumber==null||telNumber.length==0||telNumber==""){
		alert("请输入手机号");
		return;
	}
	else{
	//	var a ="^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$"; 
		var pattern = /^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/;
		if( telNumber.length!=11||!telNumber.match(pattern) ){
			alert("请输入正确的钉钉手机号码");
			}else{
		document.getElementById("TelLoginForm").action="/XinhaiMealStatistics/EatLoginTel";
		document.getElementById("TelLoginForm").submit();	
			}
		}
}




</script>



</body>
</html>
