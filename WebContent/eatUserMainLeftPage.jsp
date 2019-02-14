<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/bootstrap.css">
<title>Insert title here</title>
</head>
<body>
<div>
<form  id="eatlogform" action=""  method="Post" target="eatUserMainright">
<table>
<tr><td><input  type="hidden"  name="openId" id="openId" value="${openId}"/></td></tr>

<tr>
<td><button class="btn btn-default" type="button" onclick="javascript:UserSelectionInterface()" >用户选择界面</button></td>
</tr>
<tr>
<td><button class="btn btn-default" type="button" onclick="javascript:UsersViewStatisticsPage()" >用户查看统计页面</button></td>
</tr>
</table>
</form>

</div>
<script>
function UserSelectionInterface(){
	var openId=document.getElementById("openId").value;
	   document.getElementById("eatlogform").action="/XinhaiMealStatistics/SearchData?methods=UserSelectionInterfaceData&&openId="+openId;
	   document.getElementById("eatlogform").submit();	  
}


function UsersViewStatisticsPage(){
	var openId=document.getElementById("openId").value;
	document.getElementById("eatlogform").action="/XinhaiMealStatistics/SearchData?methods=UsersViewStatisticsPage&&openId="+openId;
	document.getElementById("eatlogform").submit();	
	
}


</script>
</body>
</html>