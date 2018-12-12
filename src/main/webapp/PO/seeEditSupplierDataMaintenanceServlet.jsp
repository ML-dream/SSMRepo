<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>初始回款信息维护</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body style="height:95%;">
  	<div class="mini-toolbar">
	<a class="mini-button" plain="false" iconCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a>
	</div>
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;" borderStyle="border:0;" 
    multiSelect="true" idField="id" showSummaryRow="true" allowAlternating="true" showFilterRow="true" showPager="true"
     url="seeEditSupplierDataMaintenanceServlet">
      	<div property="columns">
            <div type="indexcolumn"></div>
            <div field="companyId" align="center" headerAlign="center">客户编号</div>
            <div field="companyName" align="center" headerAlign="center">客户名称</div>
            <div field="editEndPayment" align="center" headerAlign="center">初始应回款</div>
            <div field="initialEndPayment" align="center" headerAlign="center">历史初始值</div>
            <div field="editPersonName" align="center" headerAlign="center">修改人员</div>
            <div field="editTime" align="center" headerAlign="center" dateFormat="yyyy-MM-dd">修改时间</div>
            <div field="reason" align="center" headerAlign="center">修改原因</div>
        </div>
    </div> 
    
    <script>
    mini.parse();
    var grid=mini.get("grid1");
    grid.load();
    
    </script>
  </body>
</html>
