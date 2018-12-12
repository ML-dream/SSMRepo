<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

   	<script src="<%=basePath%>resources/jquery/jquery.min.js" type="text/javascript"></script>
   	<script src="<%=basePath%>resources/jquery/jquery.tmpl.min.js" type="text/javascript"></script>
   	<script src="<%=basePath%>resources/jquery/jquery.cookie.js" type="text/javascript"></script><!-- cookie插件 -->