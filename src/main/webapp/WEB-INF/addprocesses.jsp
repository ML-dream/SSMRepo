<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>My JSP 'addprocesses.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<script type="text/javascript" src="./js/wlcore.js"></script>
	<script type="text/javascript" src="./js/wlcalendar.js"></script>
	<script type="text/javascript" src="./hidden_show.js"></script>   
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
		});
	</script>

  </head>
  
  <body>   
    <table id="addprocess">
      <form action="#" method="post">
        <thead>添加工序计划</thead>
        <tr><td>工序号</td><td>产品号</td><td>物料号</td><td>开工时间</td><td>完工时间</td>
          <td>工序状态</td><td>是否撤销</td><td>数量</td><td>完成量</td><td>次品数量</td></tr>
        <tr><td><input type="text" name="processid"></td>
           <td><input type="text" name="productid"></td>
           <td><input type="text" name="itemid"></td>
           <td><input type="text" name="starttime" onclick="J.calendar.get({time:true});"></td>
           <td><input type="text" name="finishtime" onclick="J.calendar.get({time:true});"></td>
           <td><input type="text" name="processstatus"></td>
           <td><input type="text" name="iscancle"></td>
           <td><input type="text" name="num"></td>
           <td><input type="text" name="finishnum"></td>
           <td><input type="text" name="failurenum"></td>
        </tr>
        <tr><td><input type="submit" value="保存"></td>
            <td><input type="reset" value="重置"></td></tr>
      </form>
    </table> 
    <input type="button" value="添加工序" id="addpro_button">   
  </body>
</html>
