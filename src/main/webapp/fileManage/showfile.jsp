<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>显示所有文件</title>
  </head>
  <body>
   请选择您要下载的文件：<br/>
    <c:forEach items="${fileList}" var="fileName">
     <a href="DownLoadServlet?filename=${fileName}">${fileName}</a><br/>
    </c:forEach>
  </body>
</html>
