<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>统计工序详情</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    
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
		});
	</script>
  </head>
  
  <body>
    <div class="page_title">生产过程数据 </div>     
  	<fieldset>
		<legend>详细工序信息</legend>
       	<table border="2" bordercolor="#B0E0E6" height="45%;" width="65%;" align="center" style="text-align: center;">   
        	<tr>
				<td>零件号</td>
				<td><%=request.getAttribute("itemId")%></td>
			</tr>        
			<tr>	
		        <td>工序号</td>
				<td><%=request.getAttribute("processId")%></td>				
				<td >加工者</td>
				<td><%=request.getAttribute("worker")%></td>				
			</tr>
			<tr>
				<td>设备号</td>
				<td><%=request.getAttribute("wcid")%></td>
				<td>设备名称</td>
				<td><%=request.getAttribute("machinename")%></td>
			</tr>
			<tr>
				<td>开工时间</td>
				<td><%=request.getAttribute("start_time")%></td>
				<td>完工时间</td>
				<td><%=request.getAttribute("end_time")%></td>
			</tr>
			<tr>
				<td>计划数量</td>
				<td><%=request.getAttribute("num")%></td>
				<td>完成数量</td>
				<td><%=request.getAttribute("finishnum")%></td>
			</tr>
			<tr>
				<td>合格数量</td>
				<td><%=request.getAttribute("pass_num")%></td>
				<td>失败数量</td>
				<td><%=request.getAttribute("failure_num")%></td>
			</tr> 		
       </table>
    </fieldset> 
    <br/>
    <fieldset>
		<legend>下道工序加工设备状态查询</legend>
		<table id="shuaxin" border="2" bordercolor="#B0E0E6">      
			<tr  align="center" valign="middle" bgcolor="#6EC2FD">
				<th width="210">下道工序号</th>
				<th width="245">加工设备号	</th>
				<th width="245">加工设备名称	</th>
				<th width="275">设备状态</th>
			</tr> 
			<tr  align="center" valign="middle"  >
				<td ><%=request.getAttribute("next_proId")%> </td>
				<td id="info" onclick="showMenu(this.innerText);" style="background:#E0FFFF;cursor:hand;"> <%=request.getAttribute("next_machinId")%></td>
				<td ><%=request.getAttribute("next_machName")%></td>
				<td ><%=request.getAttribute("next_status")%></td>
			</tr>    			 		
       	</table>
	</fieldset>  
   	<div class="page_title">
		<p>点击加工设备号，查看设备的甘特图</p>
    </div> 
    
    <fieldset>
		<legend>工序统计信息</legend>
	    <div id="container" style="min-width: 300px; height: 300px; max-width: 450px; margin: 0 auto;float: left;"></div>
		<div id="container2" style="min-width: 300px; height: 300px; max-width: 450px; margin: 0 auto"></div>
    </fieldset>
  </body>
</html>
