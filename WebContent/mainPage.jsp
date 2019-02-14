<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>


</head>
<frameset rows="15%,*"   >
	<frame  noresize="noresize"    src="top.jsp"   name="employeetop" frameborder="0">
	<frameset cols="15%,*" >
		<frame   noresize="noresize" src="left.jsp"  name="employeeleft" id="employeeleft"  frameborder="0" >
		<frame   noresize="noresize" src="backstagePage.jsp" name="employeeright"  frameborder="0">
	</frameset>
</frameset>
<body>
<input type="hidden"   name="rows"  value="${rows}" />

</body>
</html>