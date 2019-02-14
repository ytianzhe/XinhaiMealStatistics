<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="css/bootstrap.css" rel="stylesheet" media="screen">
<link href="iconfont/style.css" type="text/css" rel="stylesheet">
<title>Insert title here</title>
</head>

<style>
.wrap1{position:absolute; top:0; right:0; bottom:0; left:0; margin:auto }/*把整个屏幕真正撑开--而且能自己实现居中*/
.d2{
padding-top: 20px;
 padding-right: 30%;
 padding-bottom: 2ex;
 padding-left: 20%;
}


.t1{
font-weight:bold;

}


</style>

<body>


<div class="container wrap1" >
<c:if test="${not empty StatisticalLessonList}">
		
	<c:forEach items="${StatisticalLessonList}" var="sl" varStatus="vs" begin="0">
	<div class="container wrap1">
	<table   class="table table-bordered" align="center">
	<tr>
	<td  colspan="2" class="t1"> 
明日情况
</td>
</tr>
<tr>
<td>吃饭人数：${sl.todayCount} 人</td>
<td>米饭数量： ${sl.todayRiceNum} 碗</td>
</tr>



<tr>
<td colspan="2" class="t1">累计情况 </td>
</tr>
<tr>
<td>累计费用：${countAll}元</td>
<td>本月费用：${countM}元</td>
</tr>

<tr>
<td  colspan="2" class="t1"> 
今天人数
</td>
</tr>
<tr>
<td>吃饭人数： ${sl.lastdayCount} 人</td>
<td>米饭数量：${sl.lastdayRiceNum} 碗</td>
</tr>
<tr>
<td>实际吃饭人数： ${lastdayTrueCount}人</td>
<td>实际米饭数量： ${lastdayTrueRiceNumber}</td>
</tr>
<tr>
<td  colspan="2" class="t1"> 
本周每日的平均人数
</td>
</tr>
<tr>
<td>吃饭人数：${sl.lastWeekAveCount} 人</td>
<td>米饭数量：${sl.thisWeekAveRiceNumber}碗</td>
</tr>
<tr>
<td>实际吃饭人数： ${thisWeekTrueCount}人</td>
<td>实际米饭数量： ${thisWeeTrueRiceNumber}碗</td>
</tr>
<tr>
<td  colspan="2" class="t1"> 
上周每日平均人数
</td>
</tr>
<tr>
<td>吃饭人数：${sl.lastWeekAveCount} 人</td>
<td>米饭数量：${sl.lastWeekAveRiceNumbe}碗</td>
</tr>
<tr>
<td>实际吃饭人数： ${lastWeekTrueCount} 人</td>
<td>实际米饭数量： ${lastWeekTrueRiceNumber}碗</td>
</tr>
<tr>
<td  colspan="2" class="t1"> 
本月每日平均人数
</td>
</tr>
<tr>
<td>吃饭人数：${sl.thisMonthAveCount}人</td>
<td>米饭数量：${sl.thisMonthAveRiceNumber}碗</td>
</tr>
<tr>
<td>实际吃饭人数： ${thisMonthTrueCount} 人</td>
<td>实际米饭数量： ${thisMonthTrueRiceNumber}碗</td>
</tr>

</table>


</div>

</c:forEach>
</c:if>

 <c:if test="${ empty StatisticalLessonList}">
<div class="d2">
<table   class="table table-bordered" align="center">
<tr>
<td  colspan="2" class="t1"> 
明日情况
</td>
</tr>
<tr>
<td>吃饭人数： 人</td>
<td>米饭数量： 碗</td>
</tr>



<tr>
<td colspan="2" class="t1">累计情况 </td>
</tr>
<tr>
<td>累计费用：元</td>
<td>本月费用： 元</td>
</tr>

<tr>
<td  colspan="2" class="t1"> 
今天人数
</td>
</tr>
<tr>
<td>吃饭人数： 人</td>
<td>米饭数量：17碗</td>
</tr>
<tr>
<td>实际吃饭人数： 人</td>
<td>实际米饭数量：17碗</td>
</tr>
<tr>
<td  colspan="2" class="t1"> 
本周每日的平均人数
</td>
</tr>
<tr>
<td>吃饭人数： 人</td>
<td>米饭数量：17碗</td>
</tr>
<tr>
<td>实际吃饭人数： 人</td>
<td>实际米饭数量：17碗</td>
</tr>
<tr>
<td  colspan="2" class="t1"> 
上周每日平均人数
</td>
</tr>
<tr>
<td>吃饭人数： 人</td>
<td>米饭数量：17碗</td>
</tr>
<tr>
<td>实际吃饭人数： 人</td>
<td>实际米饭数量：17碗</td>
</tr>
<tr>
<td  colspan="2" class="t1"> 
本月每日平均人数
</td>
</tr>
<tr>
<td>吃饭人数： 人</td>
<td>米饭数量：17碗</td>
</tr>
<tr>
<td>实际吃饭人数： 人</td>
<td>实际米饭数量：17碗</td>
</tr>

</table>


</div>
</c:if>
</div>
<!-- 
<div>
累计情况
累计费用：元|
累计已伺候人数：| 本月已伺候人数： 人数
已经吃掉的米饭 ：   盒
</div>


<div>
昨日人数
吃饭人数： 人|米饭数量：17碗
实际吃饭人数：17人|实际米饭数量：14碗
本周每日平均人数
吃饭人数： 人|米饭数量：17碗
实际吃饭人数：17人|实际米饭数量：14碗
上周每日平均人数
吃饭人数： 人|米饭数量：17碗
实际吃饭人数：17人|实际米饭数量：14碗
本月每日平均人数
吃饭人数： 人|米饭数量：17碗
实际吃饭人数：17人|实际米饭数量：14碗
</div>


<div>
3个百分比图表
吃饭逃跑率
小二伺候的人数占比
4点前提交率
</div>
 -->



</body>
</html>