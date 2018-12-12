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
   
    <title>客户资产入库</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
    
  </head>
  
  <body style="height:99%;">
   <div id="tabs1" class="mini-tabs" activeIndex="0" style="width:100%;height:100%;" plain="false"
		    buttons="#tabsButtons" refreshOnClick="true">
		    <div title="添加客户资产" iconCls="icon-add" showCloseButton="false" url="Checkin/assertMain.jsp"></div>
		    <div title="客户资产入库" iconCls="icon-add" showCloseButton="false" url="Checkin/CustomerCheckin.jsp"></div>
		    <div title="客户资产入库记录"  iconCls="icon-node" showCloseButton="false" url="Checkin/showCustomerCheckin.jsp"></div>
		   
		</div>
		<script>
			mini.parse();
		</script>
  </body>
</html>
