<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <title>上传的文件显示</title>
  </head>
  
  <body>
    
    用户名：	${requestScope.usename } <br/>
    文件：		${requestScope.file1 }<br/>
    		${requestScope.file2 }<br/>
    <!-- 把上传的图片显示出来 -->
    		<img alt="go" src="upload/<%=(String)request.getAttribute("file1")%> " />
    
  </body>
</html>
