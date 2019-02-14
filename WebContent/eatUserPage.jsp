<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width,initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
<link href="css/bootstrap.css" rel="stylesheet" media="screen">

<link href="iconfont/style.css" type="text/css" rel="stylesheet">
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
<style>
.d1{
padding-top: 10px;
 padding-right: 30%;
 padding-bottom: 2ex;
 padding-left: 20%;
}




</style>
<body  >
 <!--  这里是用户选择界面-->

   
<c:if test="${empty edllessonList}">
<div  class="d1">
<div class="bs-example" data-example-id="simple-table">
<form  action="AddEatDayLog"  method="Post">
    <table class="table table-bordered" align="center">
      	<caption>预约吃饭统计</caption>
     	 <thead>
       	 <tr>
          <th></th>
          <th colspan="3" >明日吃饭统计</th>
          
        </tr>
      </thead>
      <tbody>
        <tr>
          <th scope="row"></th>
          <td>吃：<input type="radio" checked="checked" name="isEat" value="1" /></td>
          <td>不吃：<input type="radio" name="isEat" value="2" onclick="notEat()"/></td>
          <td></td>
        </tr>
        <tr>
          <th scope="row"></th>
          <td colspan="2"  >米饭数量
          <select name="riceNumber"  id="riceNumber">
		<option value="0"  >0</option>
		<option value="0.5" >0.5</option>
		<option value="1"  selected="selected">1</option>
		<option value="2" >2</option>
		<option value="3">3</option>
		</select> 盒</td>
          <td></td>
        </tr>
        <tr>
          <th scope="row"></th>
           <td colspan="2" align="center"><input type="submit"  class="btn btn-default" value="提交" /></td>
         
          <td>
           <input type="hidden"   name="userId"  value="${eatUserid}" >
          </td>
        </tr>
      </tbody>
    </table>
    </form>
  </div>
</div>
</c:if>
<c:if test="${not empty edllessonList}">

<div class="d1">
<c:forEach items="${edllessonList}" var="edl" varStatus="vs" begin="0">
	
 	<table class="table table-bordered" align="center">
      	<caption>预约吃饭统计</caption>
     	 <thead>
       	 <tr>
          <th></th>
          <th colspan="2" >明日吃饭统计</th>
        </tr>
      </thead>
      <tbody>
      	 <tr>
          <th scope="row"></th>
          <td>姓名 : <c:out value="${edl.eatName}" escapeXml="false" /></td>
        </tr>
         <tr>
          <th scope="row"></th>
          <td>添加日期 : <c:out value="${edl.firstAddTime}" escapeXml="false" /></td>
        </tr>
        
      
        <tr>
          <th scope="row"></th>
          <td>明日是否吃饭 : <c:out value="${edl.dictionaryName}" escapeXml="false" /></td>
         
        </tr>
        <tr>
          <th scope="row"></th>
          <td colspan="2"  >米饭数量: <c:out value="${edl.riceNumber}" escapeXml="false" /> 碗
         </td>
        </tr>
        
         <tr>
          <th scope="row"></th>
          <td colspan="2"  >附加信息: <c:out value="${edl.information}" escapeXml="false" /> 
         </td>
        </tr>
        
      
 <c:if test="${not empty LastLoveFoodLessonList}">
 	<tr>
  	<th scope="row"></th>
  	<td colspan="2"  >昨日喜爱的菜</td>
  	</tr>
<c:forEach items="${LastLoveFoodLessonList}" var="llfll" varStatus="vs" begin="0">
  <tr>
  <th scope="row"></th>
  <td colspan="2"  ><c:out value="${llfll.foodName}" escapeXml="false" /> 
  </tr>
</c:forEach>
</c:if>        
       
       
      </tbody>
  	  </table>
</c:forEach>
		<a href="/XinhaiMealStatistics/SearchData?methods=UsersViewStatisticsPage&&openId=111">查看统计列表</a>



</div>
</c:if>

<input  type="hidden"  name="msg" id="msg" value="${msg}" />	


<script>
function notEat(){
	//alert("不吃");
	 document.getElementById('riceNumber').value="0";	
}
</script>
</body>
</html>
<!-- edllessonList -->

<!-- 明日吃饭统计
<form  action="AddEatDayLog"  method="Post">
吃：
<input type="radio" checked="checked" name="isEat" value="1" />
<br />
不吃：
<input type="radio" name="isEat" value="2" />
<select name="riceNumber">
<option value="0"  selected="selected">0</option>
<option value="0.5" >0.5</option>
<option value="1" >1</option>
<option value="2" >2</option>
<option value="3">3</option>
</select>
<input type="hidden"   name="userId"  value="${eatUserid}" >
<input type="submit"  value="提交" />
</form> -->