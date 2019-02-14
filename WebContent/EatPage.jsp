<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<title>Insert title here</title>
<script>
window.onload=function loadmsg(){
	var msg=document.getElementById("msg").value;
	if(msg!=null&&msg.length>0){
		alert(msg);}
//	alert("EmployeeName: "+EmployeeName+" mouldname: "+mouldname+" drawingno: "+drawingno);
//	

}
</script>


</head>

<body>
	<c:if test="${not empty TodayEatResultDayList}">
	<c:forEach items="${TodayEatResultDayList}" var="tedl" varStatus="vs" begin="0">
	今日实际人数 ${tedl.trueEatNumber} 人
	剩余  ${tedl.trueRiceNumber} 盒米饭
	</c:forEach>
	</c:if>
	<c:if test="${empty TodayEatResultDayList}">
	<div>
	<form id="Eatform" action="" method="Post" target="backstagemainmid">
	<input  id="ActualNumbersToday" name="ActualNumbersToday"  value=""  placeholder="今日实际人数" />
	<br/>
	<input id="NumberOfRemainingRice" name="NumberOfRemainingRice"  value=""  placeholder="剩余多少盒米饭"/>
	<button type="button" id="Save" onclick="SaveResult()">保存</button>
	<input id="msg" type="hidden"  value="${msg}"  />
	</form>
	</div>
	</c:if>
<div style="width:100%;text-align:center">
	<table>
	<tr >
	<td colspan="2">明日情况</td>
	</tr>
	<tr>
	<td colspan="2">吃饭人数： ${isEatTomorrow}人|米饭数量：${riceNumberTomorrow}人</td>
	</tr>
	<tr>
	<td>预计最高费用：600元<td>
	</tr>
	</table>
</div>
<!-- 今天吃饭的人数的列表 -->
<div>
今天报名人员
<table>
	
<c:if test="${not empty TodayIsEatList}">
	<tr>
	<td>名字</td>
	<td>删除请填写OA</td>
	</tr>
	<c:forEach items="${TodayIsEatList}" var="til" varStatus="vs" begin="0">
	<tr>
	
		
		<td><input class="btn btn-default" style="border-style:none;" type="text"  value="${til.userName}" /></td>
		<td><button class="btn btn-default" type="button" id="${til.id}"  value="${til.userName}"  onclick="delToday(this)">删除</button></td>
		</tr>	
		</c:forEach>
		
</c:if>
</table>
</div>
<script>
function delToday(obj){
	var id=obj.id;
	var name=obj.value;
	if(confirm("确定要删除："+name+"今天的吃饭预订么 ?")){  
		//  document.getElementById("userform").action="/XinhaiMealStatistics/DelTel";
		//  document.getElementById("userform").submit();
		window.location.href = "DelData?methods=delTodayLogData&&userId="+id; 
        
	//	window.location.href="www.baidu.com";
		return true;  
    }  	
}



function SaveResult(){
	var ActualNumbersToday=document.getElementById("ActualNumbersToday").value;
	var NumberOfRemainingRice=document.getElementById("NumberOfRemainingRice").value;
//	alert(telnumber+"   "+name);
	if(ActualNumbersToday==null||ActualNumbersToday.length==0||ActualNumbersToday==""){
		alert("请输入今日实际人数");
		return;  
	}
	else{
		if(NumberOfRemainingRice==null||NumberOfRemainingRice.length==0||NumberOfRemainingRice==""){
			alert("请输入剩余多少米饭");
			return;
		}
		else{
			
			if(confirm("确定添加用户：今日实际人数为："+ActualNumbersToday+"人, 剩余米饭数量为 ： "+NumberOfRemainingRice)){  
				
				  document.getElementById("Eatform").action="/XinhaiMealStatistics/AddResultDay";
				  document.getElementById("Eatform").submit();
			//	window.location.href = "AddTel?telnumber="+telnumber+"&&eatname="+eatname; 
		        return true;  
		    }  
			
			
			
		}
	}
	
}
</script>
</body>
</html>
