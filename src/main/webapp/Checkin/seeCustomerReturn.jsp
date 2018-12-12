<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
    <title>客户退货入库详情</title>
    
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <div class="mini-toolbar">
  <a class="mini-button" iconCls="icon-undo" plain="false" onclick="javascript:window.history.back(-1);">返回</a>
  </div>
    <div id="grid" class="mini-datagrid" style="width:100%;height:99%;" borderStyle="border:0;" multiSelect="true"
    	 idField="id" allowAlternating="true" showPager="true" url="seeCustomerReturnServlet?sheetId=${sheetId }">
    	 <div property="columns">
    	 	<div type="indexcolumn"></div>
    	 	<div field="action" headerAlign="center" align="center" renderer="on"></div>
    	 	<div field="itemId" headerAlign="center" align="center">编号</div>
    	 	<div field="itemName" headerAlign="center" align="center">名称</div>
    	 	<div field="issueNum" headerAlign="center" align="center">版本号</div>
    	 	<div field="spec" headerAlign="center" align="center">规格</div>
    	 	
    	 	<div field="unit" headerAlign="center" align="center">单位</div>
    	 	<div field="returnNum" headerAlign="center" align="center">退货数量</div>
    	 	<div field="unitPrice" headerAlign="center" align="center">单价</div>
    	 	<div field="price" headerAlign="center" align="center">总价</div>
    	 	<div field="qkprice" headerAlign="center" align="center">质量扣款</div>
    	 	<div field="reason" headerAlign="center" align="center">退货原因</div>
    	 </div>
    </div>
    
    
    <script>
    mini.parse();
    var grid=mini.get("grid");
    grid.load();
    
    
    </script>
  </body>
</html>
