<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- ysl --->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<link rel="stylesheet" type="text/css" href="<%=basePath%>css/js.css"/>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css"/>

<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>

<script type="text/javascript" src="<%=basePath%>resources/include/ValidatorForm.js"></script>

<link rel="stylesheet" type="text/css" href="<%=basePath%>resources/ext3/resources/css/ext-all.css"/>
<script type="text/javascript" src="<%=basePath%>resources/ext3/adapter/ext/ext-base.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/ext3/ext-all.js"></script>
<script type="text/javascript" src="<%=basePath%>resources/ext3/ext-lang-zh_CN.js"></script>
