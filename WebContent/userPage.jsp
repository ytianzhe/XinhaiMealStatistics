<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>   
 
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<title>Insert title here</title>
<script>
window.onload=function loadmsg(){
	var msg=document.getElementById("usermsg").value;
	if(msg!=null&&msg.length>0){
		alert(msg);}
//	alert("EmployeeName: "+EmployeeName+" mouldname: "+mouldname+" drawingno: "+drawingno);
//	

}

</script>
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
这里是后台展示界面  
<input id="usermsg" type="hidden"  value="${msg}"  />
<!--
<div>
<form id="userform" action="" method="Post" target="backstagemainleft">
<input  id="telnumber" name="telnumber"  value=""  placeholder="手机号" />
<br/>
<input id="eatname" name="eatname"  value=""  placeholder="名字"/>

<button type="button" id="send" onclick="SendTel()">添加</button>
</form>
</div>
-->
<div style="text-align:center">
<c:if test="${not empty UserList}">
		<table style="align:center">
		<c:forEach items="${UserList}" var="u" varStatus="vs" begin="0">
		<tr>
		<td><button class="btn btn-default" style="border-style:none;" type="button" value="${u.mobile}"  onclick="showTel(this)">${u.name}</button></td>
		<td><button class="btn btn-default" type="button" id="${u.id}" value="${u.name}"  onclick="del(this)">删除</button></td>
		</tr>	
		</c:forEach>
		</table>
</c:if>
</div>

<script>
function showTel(obj){
	alert("该用户手机号为： "+obj.value);	
}

function del(obj){
	var id=obj.id;
	var name=obj.value;
	if(confirm("确定要删除："+name+"么 ?")){  
		//  document.getElementById("userform").action="/XinhaiMealStatistics/DelTel";
		//  document.getElementById("userform").submit();
		window.location.href = "DelData?methods=deltel&&userid="+id; 
        return true;  
    }  	
}
function SendTel(){
	var telnumber=document.getElementById("telnumber").value;
	var eatname=document.getElementById("eatname").value;
//	alert(telnumber+"   "+name);
	if(telnumber==null||telnumber.length==0||telnumber==""){
		alert("请输入手机号");
		return;  
	}
	else{
		if(eatname==null||eatname.length==0||eatname==""){
			alert("请输入名字");
			return;
		}
		else{
			if(confirm("确定添加用户：手机号为："+telnumber+" 名字为   "+eatname)){  
				
				  document.getElementById("userform").action="/XinhaiMealStatistics/AddTel?methods=AddtelByTelNumberandName";
				  document.getElementById("userform").submit();
			//	window.location.href = "AddTel?telnumber="+telnumber+"&&eatname="+eatname; 
		        return true;  
		    }  
			
			
			
		}
	}
	
}

</script>

</body>



</html>