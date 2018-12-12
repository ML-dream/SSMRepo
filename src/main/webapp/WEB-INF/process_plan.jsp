<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.wl.forms.ProcessPlan"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>My JSP 'process_plan.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
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
			J('#endtime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
			J('#receivetime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
			J('#guaqitime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });	
		});
	</script>
	

  </head>
  
  <body>    
      <a href="/test/processplan.do?flag=Queryprocessplan">查询工序计划</a>
	  <form action="/test/processplan.do?flag=processplan" method="post">
	    <table>
	      <thead>添加工序计划</thead>
	      <tr><td>orderID</td><td><input type="text" name="orderID"></td>
	          <td>产品ID</td><td><input type="text" name="productId"></td>
	          <td>issue_id</td><td><input type="text" name="issueid"></td></tr>
	      <tr><td>计划ID</td><td><input type="text" name="planid"></td>
	          <td>物料ID</td><td><input type="text" name="itemid"></td>
	          <td>工序ID</td><td><input type="text" name="processid"></td></tr>
	      <tr><td>工序计划ID</td><td><input type="text" name="processplanid"></td>
	          <td>quality_id</td><td><input type="text" name="qualityid"></td>
	          <td>bar_code</td><td><input type="text" name="barcode"></td></tr>
	      <tr><td>level_id</td><td><input type="text" name="levelid"></td>
	          <td>父物料ID</td><td><input type="text" name="fatheritemid"></td>
	          <td>数量</td><td><input type="text" name="num"></td></tr>
	      <tr><td>开工时间</td><td><input type="text" id="starttime" name="starttime"  onclick="J.calendar.get({time:true});"></td>
	          <td>完工时间</td><td><input type="text" id="endtime"  name="endtime" onclick="J.calendar.get({time:true});"></td>
	          <td>工序状态</td><td><input type="text" name="processstate"></td></tr>
	      <tr><td>是否撤销</td><td><input type="text" name="idcancle"></td>
	          <td>完成数量</td><td><input type="text" name="finishnum"></td>
	          <td>depatch_pro</td><td><input type="text" name="depatchpro"></td></tr>
	      <tr><td>pass_num</td><td><input type="text" name="passnum"></td>
	          <td>废品数量</td><td><input type="text" name="failurenum"></td>
	          <td>is_co</td><td><input type="text" name="isco"></td></tr>
	      <tr><td>部件ID</td><td><input type="text" name="deptid"></td>
	          <td>工作中心</td><td><input type="text" name="workcore"></td>
	          <td>receive_item</td><td><input type="text"  id="receivetime" name="receivetime" onclick="J.calendar.get({time:true});"></td></tr>
	      <tr><td>is_receive</td><td><input type="text" name="isreceive"></td>
	          <td>report_status</td><td><input type="text" name="reportstatus"></td>
	          <td>jigstatus</td><td><input type="text" name="jigstatus"></td></tr>
	      <tr><td>rowstatus</td><td><input type="text" name="rowstatus"></td>
	          <td>standardstatus</td><td><input type="text" name="standardstatus"></td>
	          <td>shelflife</td><td><input type="text" name="shelflife"></td></tr>
	      <tr><td>A工序计划</td><td><input type="text" name="processplana"></td>
	          <td>ao_no_use</td><td><input type="text" name="aonouse"></td>
	          <td>B工序计划</td><td><input type="text" name="processplanb"></td></tr>
	      <tr><td>挂起时间</td><td><input type="text" id="guaqitime"  name="guaqitime" onclick="J.calendar.get({time:true});"></td>
	          <td>是否挂起</td><td><input type="text" name="isguaqi"></td>
	    </table>
	    <input type="submit" value="添加工序计划" style="width: 150px;height: 30px;">
	    <input type="reset" value="重       置"  style="width: 150px;height: 30px;"> 
	  </form>
  </body>
</html>
