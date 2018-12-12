<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>进入首页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
  
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <a href="/test/intomain.do">进入首页面</a>
    <br><br>
    <a href="/test/GT/demo/LpfMyHtml.html">进入甘特图界面p151任务</a>
    <br/><br/>
    <a href="/test/toprocessplan.do?flag=process">进入工序计划</a>
    <br/><br/>
    <a href="/test/toitemList.do?flag=QueryitemList">进入物料管理</a>
    <br/><br/>
    <a href="/test/GT/demo/wlJsp.jsp?id=1">进入物料管理</a>
  </body>
</html>
