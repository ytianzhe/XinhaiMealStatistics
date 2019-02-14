<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html >
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<input onclick="Ctime()" value="提交" type="button"  id="Codebtn"/>
<script>
var wait = 60;
var Codebtn=document.getElementById("Codebtn")
function Ctime(Codebtn) {
    if(wait == 0) {
    	Codebtn.removeAttribute("disabled");
    	Codebtn.value = "获取验证码";
        wait = 60;
    } else {
    	Codebtn.setAttribute("disabled", true);
    	Codebtn.value = "重新发送(" + wait + ")";
        wait--;
        setTimeout(function() {
                time(Codebtn) },
            1000)
    }	

}
</script>
</body>
</html>