<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Insert title here</title>
<style>
.b1{
display:inline;
border:1px solid red;
}

.bbb1,.bbb2,.bbb3,.bbb4{ display:inline;border:1px solid red;
} 
#t1{
cursor:pointer;
}
</style>
</head>
<body>
Meal Page
<div>
<form id="mealform" action="" method="Post" target="backstagemainright">
<input  id="mealname" name="mealname"  value=""  placeholder="菜名" />
<br/>
<input id="mealtype" name="mealtype"  value=""  placeholder="类型"/>
<input type="hidden" id="userid" name="userid"  value="${userid}"/>
<button type="button" id="send" onclick="SendMeal()">添加</button>
</form>
</div>
<div>
<c:if test="${not empty MealList}">
	<form  action="LastMenu" method="Post" target="backstagemainright">
		<table>
		<tr>
		<td>菜名</td>
		<td>类型</td>
		<td>是否昨日</td>
		
		</tr>
		<c:forEach items="${MealList}" var="m" varStatus="vs" begin="0">
		<tr>
		<td>${m.foodName}</td>
		<td>${m.foodtypeName}</td>
		
		<td>
		<c:if test="${m.isLast==1}">
		<input type="checkbox" name="lastfood" value="${m.id}" checked="checked"/>
		</c:if>
		<c:if test="${m.isLast!=1}">
		<input type="checkbox" name="lastfood" value="${m.id}" />
		</c:if>
		</td>
		
		</tr>	
		</c:forEach>
		</table>
		<input type="submit" value="提交昨天的菜" class="btn btn-default"/>
	</form>	
</c:if>
</div>

<script>



function SendMeal(){
	var mealname=document.getElementById("mealname").value;
	var mealtype=document.getElementById("mealtype").value;
//	alert(telnumber+"   "+name);
	if(mealname==null||mealname.length==0||mealname==""){
		alert("请输入菜名");
		return;  
	}
	else{
		if(mealtype==null||mealtype.length==0||mealtype==""){
			alert("请输入类型");
			return;
		}
		else{
			
			if(confirm("确定添加菜品：菜名为："+mealname+" 类型为：   "+mealtype+"么？")){  
				
				  document.getElementById("mealform").action="/XinhaiMealStatistics/AddMeal";
				  document.getElementById("mealform").submit();
			//	window.location.href = "AddTel?telnumber="+telnumber+"&&eatname="+eatname; 
		        return true;  
		    }  
			
			
			
		}
	}
	
}

</script>

</body>



</html>