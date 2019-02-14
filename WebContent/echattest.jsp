<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html  lang="zh-CN">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
<style>
.echart1{
height:400px;
width:1200px;
}
</style>
</head>
<body>
<div>
<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main"  class="echart1"  style="border:1px solid #000;width:100%;">
    </div>
  
  </div>
 <!-- ECharts单文件引入 -->
  <script src="js/echarts.js"></script>
     <script type="text/javascript">
        // 路径配置
        // 使用
            	  var myChart = echarts.init(document.getElementById('main')); 
            	  myChart.setOption({
            		  
            	  
            			    xAxis: {
            			    	data: [],
            			        type: 'category',
            			        axisTick: {
            		                alignWithLabel: true
            		            }
            			    },
            			    yAxis: {
            			        type: 'value'
            			    },
            			    legend: {
                            	data:[]
                       	 },
            			    series: [{
            			        data: [],
            			        type: 'bar'
            			    }]
            			});
            	   myChart.showLoading(
            		{ text: "图表数据正在努力加载..."}	     //不写有秘制乱码文字 
            	   );    //数据加载完之前先显示一段简单的loading动画
            	      var names = [];    //类别数组（实际用来盛放X轴坐标值）
                      var nums = [];    //销量数组（实际用来盛放Y坐标值）
            		$.ajax({  
                        type:'post',  
                        async: true,            //异步请求（同步请求将会锁住浏览器，用户其他操作必须等待请求完成才可以执行）
                        url:'/EightFactory/getEchart?method=EatUserCountData',  
                        cache:false,  
                        dataType:'json',  
                        success:function(data) {
            			    	if(data.success)
            			    	{
            			    	 // var myChart = ec.init(document.getElementById('main')); 
            			    		var option2=data.option;
            			    		var namedata=data.data;
            			    		var TeamcountdatalessonList=data.TeamcountdatalessonList;
            			    		 for (var i = 0; i < TeamcountdatalessonList.length-1; i++) {
            	                          names.push(TeamcountdatalessonList[i].name);    //挨个取出类别并填入类别数组
            	                      }
            			    	
            			    		 for (var i = 0; i < TeamcountdatalessonList.length-1; i++) {
            	                          nums.push(TeamcountdatalessonList[i].count);    //挨个取出销量并填入销量数组
            	                      }
            			    		   myChart.hideLoading();    //隐藏加载动画
            			    		 //  alert(names);
            			    		    myChart.setOption({        //加载数据图表
            			    		    	  title: {
            			            		       text:"各小组本周加工日志关系图",
            			            		    }, 
            			            		    //鼠标悬浮显示
            			            	  tooltip : {
        							trigger: 'axis',
      								  axisPointer : {            // 坐标轴指示器，坐标轴触发有效
       						     type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        						}
  								  },
            			                  
            			            	 legend: {
            			                            	data:['count']
            			                        },
            			            		    
            			    		    	xAxis: {
            			    		    		data: names,
            			    		    		type: 'category',
            			    		    		  axisTick: {
            			    		                  alignWithLabel: true
            			    		              },
            			    		              axisLabel:{
            			    		                  show:true,
            			    		                  interval: 0,
            			    		                  rotate:45,
            			    		                 // margin: 8
            			    		                //横坐标的内容过长无法显示 
            			    		              },
            			    		              

            			                        
            	            			    },
            	            			    yAxis: {
            	            			        type: 'value'
            	            			    },
            	            			    grid:{
            	                                x:25,
            	                                y:45,
            	                                x2:5,
            	                                y2:20,
            	                                height:300,
            	                                //width:600,
            	                                borderWidth:1,
            	                                left: '3%',
            	                                right: '4%',
            	                                bottom: '3%',
            	                                containLabel: true
            	                            },
            	            			    
            	            			    series: [
            	            			    	{
            	            			    		
            	            			    	
            	            			    	data: nums,
            	            			        type: 'bar',
            	            			        barWidth : 10,//柱图宽度
            	            			        itemStyle:{
            	                                    normal:{
            	                                        color:'#df3434'
            	                                    }
            	                                }
            	            			    },
            	            			    {
            	            					name:'销量1',
            	            					type:'line', //折线图
            	            					symbolSize:8,
            	            					symbol: 'square', //点的样式为正方形
            	            					itemStyle : {  /*设置折线颜色*/
            	            						normal : {
            	            							color:'#E26A6A'
            	            						}
            	            					},
            	            					data:nums
            	            				},

            	            			    	
            	            			    	
            	            			    	]
   
            		                      });
            			    		   // window.onresize = myChart.resize;
            			    		//myChart.setOption(option2); 
            					   }
            			    		else
            			    		{
            			    			  //请求失败时执行该函数
            			                  alert("图表请求数据失败!");
            			                  myChart.hideLoading();
            			    		}
            			    }
                    });    
            	 //  myChart.setOption(option); 
    </script>
</body>
</html>