<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>js定时跳转页面的方法</title> 
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
	   
  <body> 
	<script> 
		var t=5;//设定跳转的时间 
		setInterval("refer()",1000); //启动1秒定时 
		function refer(){ 
		if(t==0){ 
		   location="/test/GT/demo/wlJsp.jsp?id=1"; //#设定跳转的链接地址 
		} 
		document.getElementById('show').innerHTML=""+t+"秒后跳转到甘特图"; // 显示倒计时 
		t--; // 计数器递减 
		//本文转自： 
		} 
	</script> 
	<h2>恭喜！！！添加成功~~</h2>
	<span id="show"></span> 

  </body>
</html>
