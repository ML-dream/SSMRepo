<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>  
    <title>My JSP 'part.jsp' starting page</title>   
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<script type="text/javascript" src="./hidden_show.js"></script>
	<script type="text/javascript" src="./js/wlcore.js"></script>
	<script type="text/javascript" src="./js/wlcalendar.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	
	<link rel="icon" href="<%=basePath%>rili/favicon.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="<%=basePath%>rili/favicon.ico" type="image/x-icon"/>
	<link href="<%=basePath%>rili/index.css" type="text/css" rel="stylesheet"/>
	<link href="<%=basePath%>rili/prettify/prettify.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=basePath%>rili/prettify/prettify.js"></script>
	<script type="text/javascript" src="<%=basePath%>rili/lhgcore.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>rili/lhgcalendar.min.js"></script>
	<script type="text/javascript">
		J(function(){
			
			J('#starttime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
			J('#finishtime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
			J('#starttime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
			J('#finishtime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });	
		});
	</script>
	
  </head>  
  <body>
        <form action="#" method="post">
	    <table border="gray solid 1px" >
	      <thead>零件计划</thead>
	      <tr>
	        <td>零件ID号</td><td>零件名</td><td>开工时间</td><td>完工</td><td>准备时间</td>
	        <td>计划时间</td><td>计划人数</td><td>图号</td>
	      </tr>
	      <tr>
	        <td><input type="text" value="零件ID号" /></td><!-- 有空的时候把字体做成有透明度的灰体 -->
	        <td><input type="text" value="零件名" /></td>
	        <td><input type="text" value="开工时间"  onclick="J.calendar.get({time:true});"/></td>
	        <td><input type="text" value="完工"  onclick="J.calendar.get({time:true});"/></td>
	        <td><input type="text" value="准备时间" onclick="J.calendar.get({time:true});" /></td>
	        <td><input type="text" value="计划时间"  onclick="J.calendar.get({time:true});"/></td>
	        <td><input type="text" value="计划人数" /></td>
	        <td><input type="text" value="图号" /></td>
	      </tr>
	    </table>
	    <input type="submit" value="增加零件" />
    </form>
  </body>
</html>
