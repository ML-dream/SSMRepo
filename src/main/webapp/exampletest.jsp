<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>My JSP 'exampletest.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
 
<style>
#showbox {
width: 150px;
min-height: 50px;
font: 100 14px/1 "微软雅黑";
border: 1px solid #3c8dbc;
display: none;
position: absolute;
top: 0;
left: 0;
background-color: #fff;
padding: 5px;
}
</style>
 
  </head>
  
  <body>
<table border="1">
 <tr>
  <td title="鼠标停留显示内容1">文字内容1</td>
 </tr>
</table>

<!-- 可以自己声明一个Div, 用来显示内容, 根据对鼠标位置的侦听, 动态改变Div 的位置 -->
<table border="1">
 <tr>
  <td onmouseover="overShow()" onmouseout="outHide()">文字内容2</td>
 </tr>
</table>
<div id="showDiv" style="position: absolute; background-color: white; border: 1px solid black;"></div>
<script>
 function overShow() {
  var showDiv = document.getElementById('showDiv');
  showDiv.style.left = event.clientX;
  showDiv.style.top = event.clientY;
  showDiv.style.display = 'block';
  showDiv.innerHTML = '鼠标停留显示内容2';
 }
 
 function outHide() {
  var showDiv = document.getElementById('showDiv');
  showDiv.style.display = 'none';
  showDiv.innerHTML = '';
 }
</script>
  </body>
</html>
