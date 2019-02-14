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
	table{
	table-layout:fixed;
	
	}
	table#mytable td{

	}
	.OP1{
	color:black;
	}
</style>
<title>手机端明日预约用户填写表单</title>
</head>
<body style="background:url(images/bg.jpg) no-repeat;">
	
 <div class="container wrap1" style="height:500px;">
            <h2 class="mg-b20 text-center">吃饭统计手机填写表单</h2>
<div class="col-sm-8 col-md-5 center-auto pd-sm-50 pd-xs-20 main_content">
	           <p class="text-center font16">预约吃饭统计</p>
	           
 <form  action="AddEatDayLog"  method="Post">
   	 <table class="table table-bordered" align="center" width="100%" id="mytable">
      	<!-- <caption>预约吃饭统计<</caption>-->
      <tbody>
        <tr>
         	<td>吃不吃</td>
          <td>吃：<input type="radio" checked="checked" name="isEat" value="1" />   不吃：<input type="radio" name="isEat" value="2" onclick="notEat()"/></td>
        </tr>
        <tr>
        <td  >米饭数量</td>
        <td>
        <select name="riceNumber"  id="riceNumber" class="login_input">
		<option value="0"  class="OP1"  >0</option>
		<option value="0.5" class="OP1">0.5</option>
		<option value="1"  selected="selected" class="OP1">1</option>
		<option value="2" class="OP1">1.5</option>
		<option value="2" class="OP1">2</option>
		<option value="3" class="OP1" >3</option>
		</select> 
		</td>
          
        </tr>
        <tr>
        <td>有要说的话</td>
        <td>
        <input  class="login_input"  name="AdditionalInformation" value=""/>
       </td>
        </tr>
        <tr>
          
           <td colspan="2" align="center">
           <input type="submit"  class="btn btn-default" value="提交"  style="width:100px"/></td>
          
        </tr>
       
          
         
      </tbody>
    </table>
     	<input type="hidden"   name="userId"  value="${userId}" />
    </form>
               
        </div><!--row end-->
    </div><!--container end-->
	


	
	<script>
	function notEat(){
		//alert("不吃");
		 document.getElementById('riceNumber').value="0";	
	}


	</script>




</body>
</html>
