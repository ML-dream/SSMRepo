<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/css/person.css" type=text/css rel=stylesheet>
    <title>选择时间间隔小界面</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  	<input id="hour" name="hour" class="mini-spinner" minValue="0" maxValue="100" value="0"/>小时
   	<input id="minite" name="minite" class="mini-spinner" minValue="0" maxValue="59" value="0"/>分钟
   	<div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" 
        borderStyle="border-left:0;border-bottom:0;border-right:0;">
        <a class="mini-button" style="width:60px;" onclick="onOk()">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="mini-button" style="width:60px;" onclick="onCancel()">取消</a>
    </div>
   	
  <body>
   
   <script type="text/javascript">
		mini.parse();
		
		function GetData() {
        	var hour = mini.get("hour").getValue();
        	var minite = mini.get("minite").getValue();
        	var time = {hour:2,
        				minite:0};
        	if(null!=hour){time.hour=hour;}
        	if(null!=minite){time.minite=minite;}
        	return time;
    	}
    	
    	function onOk() {
        	CloseWindow("ok");        
   		}
   		
   		function onCancel() {
        	CloseWindow("cancel");
    	}
    	
    	function CloseWindow(action) {
        	if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
        	else window.close();
    	}
    	
   		
    	
		
   </script>
  </body>
</html>
