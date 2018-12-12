<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>不合格品审理单</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
	<body style="height: 95%;">
		<div id="tabs1" class="mini-tabs" activeIndex="0" style="width:100%;height:100%;" plain="false"
		    buttons="#tabsButtons" refreshOnClick="true">
		    <div title="待处理审理单"  iconCls="icon-node" showCloseButton="false" url="qualitycheck/waitForJudge.jsp">
		    </div>
		    <div title="所有审理单" iconCls="icon-node" showCloseButton="false" url="qualitycheck/allStateJudge.jsp">
		    </div>
		    <div title="审理单" iconCls="icon-node" showCloseButton="false" url="qualitycheck/stateJudge.jsp">
		    </div>
		    <div title="返工审理表" iconCls="icon-node" showCloseButton="false" url="qualitycheck/stateJudge2.jsp">
		    </div>
		</div>
		<script>
			mini.parse();
		</script>
	</body>
</html>
