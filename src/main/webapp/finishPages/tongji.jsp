<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>统计界面</title>
    <link href="<%=basePath %>css/person.css" type=text/css rel=stylesheet>
	<link rel="stylesheet" href="<%=basePath %>css/demo.css" type="text/css"></link>
	
	<script type="text/javascript" src="<%=basePath %>js/Process_Select.js"></script>
	<script type="text/javascript" src="<%=basePath %>js/jquery-1.8.2.min.js"></script>
	<script src="<%=basePath %>js/highcharts.js"></script>
	<script src="<%=basePath %>js/exporting.js"></script>
	<script type="text/javascript">


		Highcharts.setOptions({  
			colors: ['#058DC7', '#50B432', '#ED561B','#DDDF00', '#24CBE5', '#64E572', '#FF9655', '#FFF263','#FF0000', '#6AF9C4']  
		}); 
		$(function () {
		    $('#container').highcharts({			    
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false,
		            type: 'pie'
		        },
		        title: {
		            text: '工序合格率统计'
		        },
		        tooltip: {
		            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		        },
		        credits:{
		            enabled:false // 禁用版权信息
		        },
		        exporting: {
		            enabled:false //禁用print&download 图标
				},
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
		                    style: {
		                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                    }
		                }
		            }
		        },
		        series: [{
		            name: "Brands",
		            colorByPoint: true,
		            data: [
		                ['合格',   <%=request.getAttribute("pass_rate")%>],
		                ['不合格', 100.0-<%=request.getAttribute("pass_rate")%> ],
			        ]
			    }]
			 });
		     $('#container2').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false,
		            type: 'pie'
		        },
		        title: {
		            text: '工序完成率统计'
		        },
		        tooltip: {
		            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		        },
		        credits:{
		            enabled:false // 禁用版权信息
		        },
		        exporting: {
		            enabled:false
				},
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
		                    style: {
		                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                    }
		                }
		            }
		        },
		        series: [{
		            name: "Brands",
		            colorByPoint: true,
		            data: [
		                ['完成',   <%=request.getAttribute("finish_rate")%>],
		                ['未完成',  100.0-<%=request.getAttribute("finish_rate")%>],
		            ]
		        }]
		    });
		    $('#container3').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false,
		            type: 'pie'
		        },
		        title: {
		            text: '零件合格率统计'
		        },
		        tooltip: {
		            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		        },
		        credits:{
		            enabled:false // 禁用版权信息
		        },
		        exporting: {
		            enabled:false
				},
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
		                    style: {
		                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                    }
		                }
		            }
		        },
		        series: [{
		            name: "Brands",
		            colorByPoint: true,
		            data: [
		                ['合格',   <%=request.getAttribute("part_pass_rate")%>],
		                ['不合格',  100.0-<%=request.getAttribute("part_pass_rate")%>],
		            ]
		        }]
		    });
		    $('#container4').highcharts({
		        chart: {
		            plotBackgroundColor: null,
		            plotBorderWidth: null,
		            plotShadow: false,
		            type: 'pie'
		        },
		        title: {
		            text: '零件完成率统计'
		        },
		        tooltip: {
		            pointFormat: '{series.name}: <b>{point.percentage:.1f}%</b>'
		        },
		        credits:{
		            enabled:false // 禁用版权信息
		        },
		        exporting: {
		            enabled:false
				},
		        plotOptions: {
		            pie: {
		                allowPointSelect: true,
		                cursor: 'pointer',
		                dataLabels: {
		                    enabled: true,
		                    format: '<b>{point.name}</b>: {point.percentage:.1f} %',
		                    style: {
		                        color: (Highcharts.theme && Highcharts.theme.contrastTextColor) || 'black'
		                    }
		                }
		            }
		        },
		        series: [{
		            name: "Brands",
		            colorByPoint: true,
		            data: [
		                ['完成',   <%=request.getAttribute("part_finish_rate")%>],
		                ['未完成',  100.0-<%=request.getAttribute("part_finish_rate")%>],
		            ]
		        }]
		    });
		});
	</script>
  </head>
  
  	<script type="text/javascript">
	   function checkform(){
		  if (document.form.item_id.value==""){alert("请选择零件号 ！");return ;}
		  if (document.form.product_id.value==""){alert("请选择批次 ！");return ;}
		  if (document.form.oper_id.value=="" ){alert("请选择工序号 ！");return ;}           
		  document.form.submit();
	   }
  	</script>
  
  	<body>
	    <fieldset> 
	    	<legend>工序可视化统计</legend>
	      	<div id="container3" style="min-width: 400px; height: 400px; max-width: 450px; margin: 0 auto"></div>
	      	<div id="container4" style="min-width: 400px; height: 400px; max-width: 450px; margin: 0 auto"></div>
	      	<!-- 
		  	<div id="container" style="min-width: 400px; height: 400px; max-width: 450px; margin: 0 auto"></div>
	      	<div id="container2" style="min-width: 400px; height: 400px; max-width: 450px; margin: 0 auto"></div>
	      	 -->
	    </fieldset>
	</body>
</html>
