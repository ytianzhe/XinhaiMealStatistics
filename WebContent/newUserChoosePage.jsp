<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,user-scalable=no,minimum-scale=1.0, maximum-scale=2.0" />
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script type="text/javascript" src="js/jquery-3.3.1.min.js" charset="UTF-8"></script>
<link href="css/applystyleAlert.css" type="text/css" rel="stylesheet" />
<script src="js/popups.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


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
	 td{
	align:center;
	}
	.OP1{
	color:black;
	}
</style>
<title>手机端明日预约用户填写表单</title>
</head>
<body style="background:url(images/bg.jpg) repeat-y;">
	
 <div class="container wrap1" style="height:500px;">
    <h2 class="mg-b20 text-center">吃饭统计手机填写表单</h2>
		<div class="col-sm-8 col-md-5 center-auto pd-sm-50 pd-xs-20 main_content">
	         <p class="text-center font16">昨日统计情况</p>
		<!-- 统计table --> 
		

	<table class="table table-bordered" align="center" width="100%" id="lastStatisticalTable"  >
	
      <tbody>
        <tr>
         <td>吃饭人数</td>
         <td>${lastdayCount}人</td>
         <td>米饭数量</td>
         <td>${lastdayRiceCount}碗</td>
        </tr>
       
        <tr>
        <td>实际吃饭人数</td>
        <td>${lastdayTrueCount}人</td>
        <td>实际米饭数量</td>
         <td>${lastdayTrueRiceNumber}碗</td>
        </tr>
      </tbody>
    </table>

    
	 <!-- 统计table  end-->   
	<!-- 手机号 的验证 start-->
	<p class="text-center font16"><%=new java.sql.Timestamp(System.currentTimeMillis()).toString().substring(0,10) %></p>
	<table class="table table-bordered" align="center" width="100%" id="lastStatisticalTable"  id="UserTable">
      <tbody>
        <tr  id="TelNumberTrId">
         <td colspan="2"><input id="EatTelNumber" class="login_input" type="text" name=""   placeholder="手机号*"  style="border: 0px;"/></td>
         <td> 
          <input type="button"  class="btn btn-default" value="验证码 "  style="width:80px" onclick="VerificationCode(this)"/>
         </td>
        </tr>
 		<tr id="validationid">
 		  <td colspan="2"><input  class="login_input" type="text" name=""   placeholder="验证码*"  id="TelCode" style="border:0px;" /></td>
 		  <td ><button  class="btn btn-default"  style="width:80%"  onclick="validation(this)">验证</button></td>
 		</tr>
 		<input  type="hidden"   id="codeGen"   value="" />
      </tbody>
    </table>
	<!-- 手机号 的验证 end-->	  
 <form  action=""  method="Post" id="EatDayLogFrom">
   	 <table class="table table-bordered" align="center" width="100%" id="mytable">
      	<!-- <caption>预约吃饭统计<</caption>-->
      <tbody>
        <tr>
         	<td>吃不吃<span style="color:red">*</span></td>
          <td>吃：<input type="radio" checked="checked" name="isEat" value="1" />   不吃：<input type="radio" name="isEat" value="2" onclick="notEat()"/></td>
        </tr>
        <tr>
        <td  >米饭数量<span style="color:red">*</span></td>
        <td>
        <select name="riceNumber"  id="riceNumber" class="login_input" style="border: 0px;">
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
        <td>有要说的话<span style="color:red">*</span></td>
        <td>
        <input  class="login_input"  name="AdditionalInformation" value="" style="border: 0px;"/>
       </td>
        </tr>
      <!-- 昨日菜肴喜爱 -->
		<tr>
		 <td colspan="2">昨日菜肴喜爱</td>
		</tr>
<c:if test="${ empty  EatFoodLastList}">
<tr>
<td>暂时没有数据</td>
</tr>
</c:if>		

<c:if test="${not empty  EatFoodLastList}">
	<c:forEach items="${EatFoodLastList}" var="efll" varStatus="vs" begin="0">
	<tr>
		<td>${efll.foodName}</td>
		<td><div class="icon_h" ><img src="images/zan1.png" onclick="changeimg(this)"  id="${efll.id}" title="0">
		<input type="checkbox" name="lastlovefood" value="${efll.id}"  id="${efll.id}check"   style="display:none"/>
		</div></td>
		</tr>	
		</c:forEach>
	</c:if>
        <!-- 昨日菜肴喜爱    end-->
       
        <tr  id="newUserNameTR">
          <!--  <td>名字*</td>
           <td  align="center"><input type="text" id="newUserName" name="newUserName" class="login_input" value=""  style="width:100px;border: 0px;"  /></td>
           -->
        </tr> 
        <tr>
           <td  colspan="2" align="center" id="refertd">
          <!--  <input type="submit"  class="btn btn-default" value="提交"  style="width:100px"/>-->
           <button type="button" class="btn btn-default" style="width:100px"  onclick="refer()" >提交</button>
           </td>
        </tr>
      </tbody>
    </table>
     	<input  type="hidden"   name="userId"  value="" id="userId"/>
    </form>
               
        </div><!--row end-->
    </div><!--container end-->
	<script>
function notEat(){
		 document.getElementById('riceNumber').value="0";	
}
function VerificationCode(btn){
	//alert("11111");
	
	var EatTelNumber=document.getElementById("EatTelNumber").value;
	if(EatTelNumber==null||EatTelNumber==""||EatTelNumber.length==0){
		alert('请填写手机号');
		return;}
		else{
			if(EatTelNumber.length!=11){
				alert("请填写11位的手机号");
				return;
			}
			else{
				
				var pattern = /^((1[3,5,8][0-9])|(14[5,7])|(17[0,6,7,8])|(19[7]))\d{8}$/;
				if( EatTelNumber.length!=11||!EatTelNumber.match(pattern) ){
					alert("请输入正确的手机号码");
					return;
					}
				else
				{
					//alert("正确的手机号码,通过开始申请验证码");
					Ctime(btn);
					$.ajax({
						type : 'post',
						url : '/XinhaiMealStatistics/VerificationCode?method=EatStatisticsVerificationCode&&EatTelNumber='+EatTelNumber,
						// data:$("#myform").serialize(), 
						//	data: /XinhaiMealStatistics/src/com/xinhai/org/servlet/VerificationCode.java
						cache : false,
						dataType : 'json',
						success : function(data) {
							if (data.success) {
								var msg=data.msg;
    				    		var codeGen=data.codeGen;
    				    		document.getElementById("codeGen").value=codeGen;
    				    		alert(msg);
							} else {
								var msg=data.msg;
    				    		
    				    		alert(msg);
							}
						}
					});
					
					
				}
			}
		}	
	}
	
var wait = 60;
function Ctime(btn){
    if(wait == 0) {
        btn.removeAttribute("disabled");
        btn.value = "验证码";
        wait = 60;
    } else {
        btn.setAttribute("disabled", true);
        btn.value = "(" + wait + ")";
        wait--;
        setTimeout(function(){Ctime(btn)},1000);
    }	
}
function validation(obj){
	
	var codeGen=document.getElementById("codeGen").value;
	var TelCode=document.getElementById("TelCode").value;
	var EatTelNumber=document.getElementById("EatTelNumber").value;
	if(TelCode==""||TelCode==null||TelCode.length==0){
		alert("请填写验证码");
	}
	else{
		if(codeGen==TelCode){
			//alert("验证码正确，通过 开始拉取用户的数据");
			$.ajax({
				type : 'post',
				url : '/XinhaiMealStatistics/SearchData?methods=SearchUserInformationByTelNumber&&openId=xxx&&EatTelNumber='+EatTelNumber,
				// data:$("#myform").serialize(), 
				//	data: /XinhaiMealStatistics/src/com/xinhai/org/servlet/VerificationCode.java
				cache : false,
				dataType : 'json',
				success : function(data) {
					if (data.success) {
						var msg=data.msg;
			    		var userName=data.eatName;
			    		var userId=data.eatUserId;
			    		var eatTelNumber=data.eatTelNumber;
			    		var newUserName=data.newUserName;
			    		//打印用户信息
			    		//alert("msg:"+msg+" userName:"+userName+" userId:"+userId+" eatTelNumber: "+eatTelNumber);
			    		//$("#validationid").empty;
			    		console.log($("#validationid"));
			    		$("#validationid").empty();
			    		$("#TelNumberTrId").empty();
			    		$("#validationid").append('<td >'+"用户名称"+'</td><td id="userName">'+userName+'</td>');
			    		$("#validationid").after('<tr><td>'+"手机号码"+'</td><td>'+eatTelNumber+'</td></tr>');
			    		$("#validationid").after('<tr><td>'+"用户ID"+'</td><td>'+userId+'</td><tr>');
			    		
			    		
			    		
			    		if(userName=="新海新员工"){
			    		$("#newUserNameTR").append('<td>名字<span style="color:red">*</span></td>');
			    		$("#newUserNameTR").append('<td  align="center"><input type="text" id="newUserName" name="newUserName" class="login_input" value=""  style="width:100px;border: 0px;"/></td>');
			    		}
			    		
			    		$("#userId").val(userId);
					} else {
						var msg=data.msg;
			    		alert(msg);
					}
				}
			});	
			
		}
		else{
			alert("验证码错误");
		}
		
	}	
}

function refer(){

	var userId=document.getElementById("userId").value;
//	alert("userId:"+userId+"-----userId length"+userId.length);
	
	var userNameObject =document.getElementById("userName");
	if(userNameObject!=null){
	var userName=document.getElementById("userName").innerHTML;
	}
	
	
	//alert("userName"+userName);
	//return;//测试不提交
	  //alert("userId:"+userId);
	if(userId==""||userId==null||userId.length==0){
			alert("请先进行手机验证");
			return;
	}
	else{

		var newUserName=null;
		//alert("userName:"+userName);
		if(userName=="新海新员工"){
		var object =document.getElementById("newUserName");
		alert(object);
		if(object!=null){
			newUserName=document.getElementById("newUserName").value;
		}
		
		
		
		if(newUserName==null||newUserName==""||newUserName.length==0){
			alert("由于未在本系统中查询到你的资料 所以请先填写你的名字后提交");
			return;
			}
		else{
			  document.getElementById("EatDayLogFrom").action="/XinhaiMealStatistics/AddEatDayLog";
			  document.getElementById("EatDayLogFrom").submit();
			}
		
		}
		else{
		  document.getElementById("EatDayLogFrom").action="/XinhaiMealStatistics/AddEatDayLog";
		  document.getElementById("EatDayLogFrom").submit();
		}
	}
	
}

function changeimg(obj){
	//alert(obj.id);
	//var src=obj.src;
	var checkid=obj.id+"check";
	if(obj.title==0){
	document.getElementById(obj.id).src = "images/zan2.png";
	
	document.getElementById(obj.id).title = "1";
	//选中对应的checkedID
//	alert("即将选中的checkId："+checkid);
	//$(checkid).prop("checked",true);
	document.getElementById(checkid).checked=true;
	}
	else{
		document.getElementById(obj.id).src = "images/zan1.png";
		document.getElementById(obj.id).title = "0";
	//	alert("即将选中的checkId："+checkid);
		//$(checkid).prop("checked",false);
		document.getElementById(checkid).checked=false;
	}
	
}





	</script>
</body>
</html>
