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
   
    <title>客户管理</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
	<body style="height: 95%;">
		<div id="tabs1" class="mini-tabs" activeIndex="1" style="width:100%;height:100%;" plain="false"
		    buttons="#tabsButtons" refreshOnClick="true">
		    <div title="新增加工单位" iconCls="icon-add" showCloseButton="false" url="outAssistComManage/addOutAssistCom.jsp"></div>
		    <div title="加工单位信息"  iconCls="icon-node" showCloseButton="false" url="outAssistComManage/showOutAssistCom.jsp"></div>
		</div>
		<script>
			mini.parse();
		</script>
	</body>
</html>
