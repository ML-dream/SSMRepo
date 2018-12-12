<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

   	<!-- ExtJs -->
    <link rel="stylesheet" type="text/css" href="<%=basePath%>resources/ext3/resources/css/ext-all.css" /> 
    <script type="text/javascript" src="<%=basePath%>resources/ext3/adapter/ext/ext-base.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/ext3/ext-all.js"></script>
    <script type="text/javascript" src="<%=basePath%>resources/ext3/ext-lang-zh_CN.js"></script>
