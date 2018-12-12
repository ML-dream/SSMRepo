<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>采购收货详细信息</title>
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
  		 <td ><lable for="prDate$text">开单日期</lable></td>
  		 <td><input id="prDate" name="prDate" class="mini-datepicker" required="true" dateFormat="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd" 
  		 showTodayButton="false" showClearButton="false" allowInput="false" width="100%" enabled="false" value="${prsheet.prDate }"/></td>
  		 <td><lable for="prSheetid$text">单号</lable></td>
 		 <td><input id="prSheetid" name="prSheetid" class="mini-textbox" required="true" width="100%" enabled="false" value="${prsheet.prSheetid }"></td>
  		 <td><label for="warehouseId">库房</label></td>
  		 <td><input id="warehouseId" name="warehouseId" class="mini-buttonedit" width="100%"  textName="warehouseName" onbuttonclick="onButtonEditWarehouse" 
  		allowInput="false" required="true" enabled="false" value="${prsheet.warehouseId}" text="${prsheet.warehouseName }"/></td>
  		</tr> 
  		<tr bgcolor=#EBEFF5>
 	  <td><label for="customerId$text">供应商</label></td>
  	  <td><input id="customerId" name="customerId" class="mini-buttonedit" width="100%" onbuttonclick="onSupplierButtonEdit" textName="customerName" 
  	  required="true" allowInput="false" enabled="false" value="${prsheet.customerId }" text="${prsheet.customerName }"/></td>
  	  <td><label for="connector$text">联系人</label></td>
  	  <td><input id="connector" name="connector" class="mini-textbox"  enabled="false" required="true" width="100%" value="${prsheet.connector}"/></td>
 	  <td><label for="connectorTel$text">联系电话</label></td>
  	  <td><input id="connectorTel"  name="connectorTel" class="mini-textbox" required="true" enabled="false" width="100%" value="${prsheet.connectorTel}"/></td>
 	 </tr>
  	<tr bgcolor=#EBEFF5>
  	<td><label for="payTerm$text">付款期限</label></td>
  <td><input id="payTerm" name="payTerm" class="mini-combobox" required="true" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
  url="data/payTerm.txt" allowInput="false" showNullItem="true" nullItemText="请选择..." enabled="false" value="${prsheet.payTerm}"/></td> 
   <td><label for="isBill$text">是否开具发票</label></td>
  <td><input id="isBill" name="isBill" class="mini-combobox" required="true" style="width:100%;" enabled="false" textField="text" valueField="id" emptyText="请选择..."
   url="data/trueOrFalse.txt" value="0" allowInput="false" showNullItem="true" nullItemText="请选择..." value="${prsheet.isBill} }"/></td>
   <td><label for="receipt$text">发票类型</label></td>
  <td><input id="receipt" name="receipt" class="mini-combobox" required="true" url="ReceiptServlet" style="width:100%" valueField="id" textField="text" 
   emptyText="请选择..." allowInput="false" showNullItem="true" nullItemText="请选择..." enabled="false" value="${prsheet.receipt }"/></td>  
    </tr>
  	<tr bgcolor=#EBEFF5>
  	<td><label for="dutyParagraph$text">税务登记号</label></td>
    <td><input id="dutyParagraph" name="dutyParagraph" class="mini-textbox" width="100%" enabled="false" value="${prsheet.dutyParagraph}"/></td>
  	<td><label for="bank$text">开户银行</label></td>
 	 <td><input id="bank" name="bank" class="mini-textbox" width="100%" enabled="false" value="${prsheet.bank }"/></td>
  	<td><label for="account$text">账号</label></td>
  	<td><input id="account" name="account" class="mini-textbox" width="100%" enabled="false" value="${prsheet.account }"/></td>
  	</tr>
  </table>
  </div>
     <div id="grid1" class="mini-datagrid" style="width:100%;height:75%;" borderStyle="border:0;" multiSelect="true"
     idField="id" showSummaryRow="true" allowAlternating="true" showPager="true" url="seePrServlet?prSheetid=${prsheet.prSheetid }">
     	<div property="columns">
            <div type="indexcolumn"></div>
            <div field="poSheetid" headerAlign="center" align="center">引用单号</div>
            <div field="itemId" headerAlign="center" align="center">货品编码</div>
            <div field="itemName" headerAlign="center" align="center">货品名称</div>
            <div field="spec" headerAlign="center" align="center">规格</div>
            <div field="unit" headerAlign="center" align="center">单位</div>
            <div field="itemTypeName" headerAlign="center" align="center">类型</div>
            <div field="ussage" headerAlign="center" align="center">用途</div>
            <div field="prNum" headerAlign="center" align="center">数量</div>
            <div field="unitPrice" headerAlign="center" align="center">单价</div>
            <div field="price" headerAlign="center" align="center">货款</div>
            <div field="taxrate" headerAlign="center" align="center">税率</div>
            <div field="tax" headerAlign="center" align="center">税款</div>
            <div field="stockId" headerAlign="center" align="center">库位</div>
    		<div field="memo" headerAlign="center" align="center">备注/合同号</div>
   		 </div>
    </div>
    
  <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
  <tr bgcolor=#EBEFF5>
  
  <td><label for="examineId$text">审核人</label></td>
  <td><input id="examineId" name="examineId"  class="mini-buttonedit" width="100%" textName="examineName" onbuttonclick="onButtonEditEmployee" 
  allowInput="false" required="true" enabled="false" value="${prsheet.examineId }" text="${prsheet.examineName }"/></td>
  <td><label for="adminId$text">仓管员</label></td>
  <td><input id="adminId" name="adminId"  class="mini-buttonedit" width="100%" textName="adminName" onbuttonclick="onButtonEditEmployee" 
  allowInput="false" required="true" enabled="false" value="${prsheet.adminId }" text="${prsheet.adminName }"/></td>
  <td><label for="salesmanId$text">业务员</label></td>
  <td><input id="salesmanId" name="salesmanId"  class="mini-buttonedit" width="100%" textName="salesmanName" onbuttonclick="onButtonEditEmployee" 
  allowInput="false" required="true" enabled="false" value="${prsheet.salesmanId }" text="${prsheet.salesmanName }"></td>
  <td><label for="drawerId$text">开单人</label></td>
  <td><input id="drawerId" name="drawerId"  class="mini-buttonedit" width="100%" textName="drawerName" onbuttonclick="onButtonEditEmployee" 
  allowInput="false" required="true" enabled="false" value="${prsheet.drawerId }" text="${prsheet.drawerName }"></td>
  </tr>
  </table>
  
  <script>
   mini.parse();
    var grid=mini.get("grid1");
    grid.load();
  
  
  
  </script>
  </body>
</html>
