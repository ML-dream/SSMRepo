<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

<script src="<%=basePath%>resources/scripts/miniui/miniui.js" type="text/javascript"></script>
<link href="<%=basePath%>resources/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>resources/scripts/miniui/themes/icons.css" rel="stylesheet" type="text/css" />
<link href="<%=basePath%>resources/scripts/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<%=basePath%>resources/include/ValidatorForm.js"></script>
<script type='text/javascript' src="<%=basePath%>resources/js/all.js"></script>
<script type='text/javascript' src="<%=basePath%>resources/js/validate_add.js"></script>