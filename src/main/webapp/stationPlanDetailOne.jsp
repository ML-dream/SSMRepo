<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.wl.forms.StationPlan"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>My JSP 'stationPlanDetailOne.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="css/person1.css" type="text/css"></link>

  </head>
  
  <body>
  	<% ArrayList<StationPlan> al =(ArrayList<StationPlan>)request.getAttribute("stationplan");
	     for(int i=0;i<al.size();i++){
	        StationPlan stationPlan = new StationPlan();
            stationPlan = (StationPlan)al.get(i);
            System.out.println("进入stationPlanDetailOne.jsp的显示界面了已经！！！！！");
	%>
    <table class="query_form_table" align="center">
      <tr><th>订单号</th><td><%=stationPlan.getOrderid() %></td><th>产品号</th><td><%=stationPlan.getProductid() %></td><th>工位顺序</th><td><%=stationPlan.getAono() %></td></tr>
      <tr><th>数量</th><td><%=stationPlan.getNum() %></td><th>开始时间</th><td><%=stationPlan.getStarttime() %></td><th>结束时间</th><td><%=stationPlan.getEndtime() %></td></tr>
      <tr><th>装配地点编号</th><td><%=stationPlan.getPlaceid() %></td><th>装配地点名称</th><td><%=stationPlan.getPlacename() %></td><th>计划人</th><td><%=stationPlan.getPlanperson() %></td></tr>
      <tr><th>计划时间</th><td><%=stationPlan.getPlantime() %></td><th>质编号</th><td><%=stationPlan.getQualityid() %></td><th>条码号</th><td><%=stationPlan.getBarcode() %></td></tr>
    </table>
    <br/>
    <%
	   }
	%>
  </body>
</html>
