<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>新海</title>
    <link rel="stylesheet" href="plugins/layui/css/layui.css" media="all">

  
</head>
<title>Insert title here</title>
<body>
    <div class="layui-layout layui-layout-admin kit-layout-admin">

        <div class="layui-side layui-bg-black kit-side "  style="top:0%">
            <div class="layui-side-scroll">
              
                <!-- 左侧导航区域（可配合layui已有的垂直导航） -->
                <ul class="layui-nav layui-nav-tree" lay-filter="kitNavbar" kit-navbar>
                    <li class="layui-nav-item">
                        <a href="javascript:UserSelectionInterface()"  data-name="form" kit-loader><i class="fa fa-plug" aria-hidden="true"  target="eatUserMainright"></i><span>用户选择页面</span></a>
                    </li>
                     <li class="layui-nav-item">
                        <a href="javascript:UsersViewStatisticsPage()"  data-name="form" kit-loader><i class="fa fa-plug" aria-hidden="true"></i><span>用户查看统计页面</span></a>
                    </li>
                </ul>
            </div>
        </div>
        <form  id="eatlogform" action=""  method="Post" target="eatUserMainright">
        <input  type="hidden"  name="openId" id="openId" value="${openId}"/>
       </form>
    </div>
  
    <script src="./plugins/layui/layui.js"></script>
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
    
    
    <script>
        var message;
        layui.config({
            base: 'build/js/'
        }).use(['app', 'message'], function() {
            var app = layui.app,
                $ = layui.jquery,
                layer = layui.layer;
            //将message设置为全局以便子页面调用
            message = layui.message;
            //主入口
            app.set({
                type: 'iframe'
            }).init();
            $('#pay').on('click', function() {
                layer.open({
                    title: false,
                    type: 1,
                    content: '<img src="/build/images/pay.png" />',
                    area: ['500px', '250px'],
                    shadeClose: true
                });
            });
        });
    </script>
</body>

</html>