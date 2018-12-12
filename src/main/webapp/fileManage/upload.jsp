<%@ page language="java" import="java.util.*"  contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <title>上传的文件显示</title>
  </head>
  
  <body>
  	<FORM METHOD="POST" ENCTYPE="multipart/form-data" ACTION="SmartUpladServlet" acceptcharset="UTF-8" >
   		<INPUT TYPE="text" name="userName"><br>
   		<INPUT TYPE="file" NAME="myFile"><br>
   		<INPUT TYPE="file" NAME="myFile2"><br>
   		<INPUT TYPE="file" NAME="myFile3"><br>
   		<INPUT TYPE="SUBMIT" value="上传">
  	</FORM>
  </body>
</html>
