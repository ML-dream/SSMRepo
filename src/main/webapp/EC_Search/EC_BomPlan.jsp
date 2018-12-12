<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>Bom和Plan变更量获取与查询</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
<FRAMESET border=0 frameSpacing='10' border=0 rows=150,*>
<FRAME  border='0' height="100%" marginHeight="0" MARGINWIDTH="10"  target="list" src="EC_Search/EC_Boma.jsp">
<FRAME name=list frameborder='0'  onscroll="alert('dd')" src="EC_Search/EC_Bomb.jsp" marginHeight="0" height="100%" width="100%">
</html>
