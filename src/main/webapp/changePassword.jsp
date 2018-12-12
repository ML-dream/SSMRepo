<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>My JSP 'changePassword.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	

  <link rel="stylesheet" href="css/person1.css" type="text/css"></link>
  </head>
  
  <body>
  	<form action="ChangePasswordServlet" method="post">
  	  <table class="query_form_table" align="center" width="300px;">
  	    <tr><th>员工号码</th><td><input type="text" name="staffCode" size="32" width="32"/></td></tr>
  	    <tr><th>原始密码</th><td><input type="text" name="oldPassword" size="32" width="32"/></td></tr>
  	    <tr><th>输入新密码</th><td><input type="text" name="newPassword" size="32" width="32"/></td></tr>
  	    <tr><th>确认新密码</th><td><input type="text" name="newPassword2" size="32" width="32"/></td></tr>
  	    <tr><td><input type="submit" value="确认修改" name="确认修改"/></td>
  	    	<td><input type="reset" value="重新输入" name="重新输入"/></td></tr>
  	  </table>
  	</form>
    
  </body>
</html>
