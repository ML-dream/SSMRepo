<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>库存盘点记录详情</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">-->
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/js.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/pagecard.css">
	<style type="text/css">
	<!--
	table {
	    table-layout:fixed;
	    word-break: break-all;
	} 
	-->
	</style>
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  
  <body>
  <div class="mini-toolbar">
  		<a class="mini-button" iconCls="icon-print" plain="false"  onclick="print()">打印订单</a>
  		<span class="separator"></span>
  		<a class="mini-button" plain="false" iconCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a>
  	</div>
  <form id="Countsheet" name="Countsheet" action="#" method="post">
  <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
  <tr bgcolor="#D9E7F8">
  <td><label for="CountSheetId$text">单号</label></td>
  <td><input id="CountSheetId" name="CountSheetId" class="mini-textbox" width="100%" required="true" enabled="false" value="${ whcount.countSheetid}"></td>
  <td><label for="CountDate$text">日期</label></td>
  <td><input id="CountDate" name="CountDate" class="mini-datepicker" width="100%" required="true" enabled="false" value="${whcount.countDate}"></td>
  <td><label for="warehouseId$text">库房</label></td>
  <td><input id="warehouseId" name="warehouseId" class="mini-buttonedit" width="100%" textName="warehouseName" enabled="false" onbuttonclick="onButtonEditWarehouse" 
  allowInput="false" required="true" value="${whcount.warehouseId }" text="${whcount.warehouseName }"/></td>
  </tr>
   <tr bgcolor="#D9E7F8">
  <td><label for="operatorId$text">操作人</label></td>
  <td><input id="operatorId" name="operatorId"  class="mini-buttonedit" width="100%" textName="salesmanName" enabled="false" onbuttonclick="onButtonEditEmployee" 
  allowInput="false" required="true" value="${whcount.operatorId }" text="${whcount.operatorName }"></td>
  <td><label for="deptid$text">部门</label></td>
  <td><input id="deptid" name="deptid" class="mini-buttonedit" width="100%" required="true" textName="deptname" enabled="false" onbuttonclick="onButtonEditDept" 
  allowInput="false" value="${whcount.deptId }" text="${whcount.deptName }"/></td>
  <td><label for="empId$text">经办人</label></td>
  <td><input id="empId" name="empId"  class="mini-buttonedit" width="100%" textName="empName" enabled="false" onbuttonclick="onButtonEditEmployee" 
  allowInput="false" required="true" value="${whcount.empId }" text="${whcount.empName }"></td>
  </tr>
  </table>
   </form>
   
  <div id="grid1" class="mini-datagrid" style="width:100%;height:75%;" borderStyle="border:0;" multiSelect="true"
     idField="id" showSummaryRow="true" allowAlternating="true" showPager="true" onrowdblclick="editRow()" url="WhCountServlet?countSheetid=${ whcount.countSheetid }">
     	<div property="columns">
            <div type="indexcolumn"></div>
            <div field="itemId" headerAlign="center" align="center">货品编码</div>
            <div field="itemName" headerAlign="center" align="center">货品名称</div>
            <div field="spec" headerAlign="center" align="center">规格</div>
            <div field="unit" headerAlign="center" align="center">单位</div>
            <div field="stockId" headerAlign="center" align="center">库位</div>
            <div field="stockNum" headerAlign="center" align="center">库存数量</div>
            <div field="countNum" headerAlign="center" align="center">盘点数量</div>
            <div field="profitLossNum" headerAlign="center" align="center">盈亏数量</div>
            <div field="unitPrice" headerAlign="center" align="center">单价</div>
            <div field="profitLoss" headerAlign="center" align="center">盈亏金额</div>
    		<div field="memo" headerAlign="center" align="center">备注</div>
   		 </div>
    </div>
    
    <script>
    mini.parse();
    var grid=mini.get("grid1");
    grid.load();
    
    

    
    
    </script>
     
  </body>
</html>
