<%@ page language="java" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>简单注销</title>
</head>
<body>
<%@ page import = "java.util.*" %>
<%
//取出当前session的username并在olUserList中删除
String username = (String)session.getAttribute("username");
List olUserList = (List)application.getAttribute("olUserList");
olUserList.remove(username);
application.setAttribute("olUserList", olUserList);
//销毁会话
session.invalidate();
%>
<form action="">
<input type = "submit" value = "返回" />
</form>
<form action="login.jsp">
<input type = "submit" value = "登陆" />
</form>
<h1>注销成功</h1>
</body>
</html>