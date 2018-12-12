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
   
    <title>订单管理</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
	<body style="height: 100%;">
		<div class="mini-fit">
			<div id="tabs1" class="mini-tabs" activeIndex="0" style="width:100%;height:100%;" plain="false"
			    buttons="#tabsButtons">
			    <div title="新增订单" iconCls="icon-add" showCloseButton="false" url="orderManage/AddOrder.jsp">
			    </div>
			    <div title="订单信息"  iconCls="icon-node" showCloseButton="false" url="orderManage/OrderList.jsp">
			    </div>
			    
			</div>
		</div>
		
		<script>
			mini.parse();
		</script>
	</body>
</html>
