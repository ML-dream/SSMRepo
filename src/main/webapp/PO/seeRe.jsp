<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>采购退货详细信息</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
  	 <div>
     	 <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
  		 <tr bgcolor=#EBEFF5>
  		 <td ><label for="reDate$text">开单日期</label></td>
  		 <td><input id="reDate" name="reDate" class="mini-datepicker" required="true" dateFormat="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd" 
  		 showTodayButton="false" showClearButton="false" allowInput="false" width="100%" enabled="false" value="${returngood.reDate }"/></td>
  		 <td><label for="reSheetid$text">单号</label></td>
 		 <td><input id="reSheetid" name="reSheetid" class="mini-textbox" required="true" width="100%" enabled="false" value="${returngood.reSheetid }"></td>
  		 <td><label for="warehouseId">库房</label></td>
  		 <td><input id="warehouseId" name="warehouseId" class="mini-buttonedit" width="100%"  textName="warehouseName" onbuttonclick="onButtonEditWarehouse" 
  		allowInput="false" required="true" enabled="false" value="${returngood.warehouseId}" text="${returngood.warehouseName }"/></td>
  		</tr> 
  		<tr bgcolor=#EBEFF5>
 	  <td><label for="customerId$text">供应商</label></td>
  	  <td><input id="customerId" name="customerId" class="mini-buttonedit" width="100%" onbuttonclick="onSupplierButtonEdit" textName="customerName" 
  	  required="true" allowInput="false" enabled="false" value="${returngood.customerId }" text="${returngood.customerName }"/></td>
  	  <td><label for="connector$text">联系人</label></td>
  	  <td><input id="connector" name="connector" class="mini-textbox"  enabled="false" required="true" width="100%" value="${returngood.connector}"/></td>
 	  <td><label for="connectorTel$text">联系电话</label></td>
  	  <td><input id="connectorTel"  name="connectorTel" class="mini-textbox" required="true" enabled="false" width="100%" value="${returngood.connectorTel}"/></td>
 	 </tr>
  	<tr bgcolor=#EBEFF5>
    <td><label for="telephone$text">传真号</label></td>
    <td><input id="telephone" name="telephone" class="mini-textbox" required="true" width="100%" enabled="false" value="${returngood.telephone}"/></td>
    <td><label for="payTerm$text">退货类型</label></td>
    <td><input id="payTerm" name="payTerm" class="mini-textbox" required="true" width="100%" enabled="false" value="${returngood.reType}"/></td>   
    <td><label for="account$text">收款账号</label></td>
  	<td><input id="account" name="account" class="mini-textbox" width="100%" enabled="false" value="${returngood.account }"/></td>
    </tr>
  	<tr bgcolor=#EBEFF5>
  	<td><label for="payMethod$text">收款方式</label></td>
    <td><input id="payMethod" name="payMethod" class="mini-combobox" style="width:100%" textField="text" valueField="id" emptyText="请选择..."
    url="GetpayMethodServlet" value="XZ"  required="true" allowInput="true" enabled="false" showNullItem="true" nullItemText="请选择..." value="${returngood.payMethod}"/></td>
  	<td><label for="receipt$text">发票类型</label></td>
    <td><input id="receipt" name="receipt" class="mini-combobox" required="true" url="ReceiptServlet" style="width:100%" valueField="id" textField="text" 
   emptyText="请选择..." allowInput="false" showNullItem="true" enabled="false" nullItemText="请选择..." value="${returngood.receipt }"/></td> 
  	</tr>
  </table>
  </div>
     <div id="grid1" class="mini-datagrid" style="width:100%;height:75%;" borderStyle="border:0;" multiSelect="true"
     idField="id" showSummaryRow="true" allowAlternating="true" showPager="true" url="seeReServlet?reSheetid=${returngood.reSheetid }">
     	<div property="columns">
            <div type="indexcolumn"></div>
            <div field="itemId" headerAlign="center" align="center">货品编码</div>
            <div field="itemName" headerAlign="center" align="center">货品名称</div>
            <div field="spec" headerAlign="center" align="center">规格</div>
            <div field="unit" headerAlign="center" align="center">单位</div>
            <div field="itemTypeDesc" headerAlign="center" align="center">类型</div>
            <div field="reNum" headerAlign="center" align="center">数量</div>
            <div field="unitPrice" headerAlign="center" align="center">单价</div>
            <div field="price" headerAlign="center" align="center">货款</div>
            <div field="stockId" headerAlign="center" align="center">库位</div>
    		<div field="memo" headerAlign="center" align="center">备注/合同号</div>
   		 </div>
    </div>
    
  <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
  <tr bgcolor=#EBEFF5>
  
  <td><label for="examineId$text">审核人</label></td>
  <td><input id="examineId" name="examineId"  class="mini-buttonedit" width="100%" textName="examineName" onbuttonclick="onButtonEditEmployee" 
  allowInput="false" required="true" enabled="false" value="${returngood.examineId }" text="${returngood.examineName }"/></td>
  <td><label for="adminId$text">仓管员</label></td>
  <td><input id="adminId" name="adminId"  class="mini-buttonedit" width="100%" textName="adminName" onbuttonclick="onButtonEditEmployee" 
  allowInput="false" required="true" enabled="false" value="${returngood.adminId }" text="${returngood.adminName }"/></td>
  <td><label for="salesmanId$text">业务员</label></td>
  <td><input id="salesmanId" name="salesmanId"  class="mini-buttonedit" width="100%" textName="salesmanName" onbuttonclick="onButtonEditEmployee" 
  allowInput="false" required="true" enabled="false" value="${returngood.salesmanId }" text="${returngood.salesmanName }"></td>
  <td><label for="drawerId$text">开单人</label></td>
  <td><input id="drawerId" name="drawerId"  class="mini-buttonedit" width="100%" textName="drawerName" onbuttonclick="onButtonEditEmployee" 
  allowInput="false" required="true" enabled="false" value="${returngood.drawerId }" text="${returngood.drawerName }"></td>
  </tr>
  </table>
  
  <script>
   mini.parse();
    var grid=mini.get("grid1");
    grid.load();
  
  
  
  </script>
  </body>
</html>
