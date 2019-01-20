<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>

    <script src="resources/scripts/boot.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>resources/include/ValidatorForm.js"></script>
<script type='text/javascript' src="<%=basePath%>resources/js/all.js"></script>
<script type='text/javascript' src="<%=basePath%>resources/js/validate_add.js"></script>