<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改采购订货信息</title>
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
  		<!-- <a class="mini-button" iconCls="icon-print" plain="false" onclick="print()">打印订单</a> 
  		<span class="separator"></span>  -->
  		<a class="mini-button" iconCls="icon-save" plani="false" onclick="getForm()">保存</a> 
  		<span class="separator"></span> 
  		<a class="mini-button" plain="false" icoCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a> 
  	</div>
  
   <fieldset id="allDetail" style="width: 100%;" align="center">
   <legend>采购订货详细信息</legend>
   <form id="detailpo" name="detailpo" action="#" method="post">
   <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
   <tr>
  <td><label for="po_sheetid$text">单号</label></td>
  <td><input id="po_sheetid" name="po_sheetid" class="mini-textbox" width="100%" required="true" enabled="false" value="${podetail.poSheetid }"/></td>
   <td><label for="item_id$text">货品编码</label></td>
   <td><input id="item_id" name="item_id" class="mini-textbox" width="100%" required="true" enabled="false"  value="${podetail.itemId }"></td>
    <td><label for="item_name$text">货品名称</label></td>
   <td><input id="item_name" name="item_name" class="mini-textbox" width="100%" required="true" enabled="false" value="${podetail.itemName }"></td>

   </tr>
   <tr>
    <td><label for="spec$text">规格</label></td>
   <td><input id="spec" name="spec" class="mini-textbox" width="100%"  value="${podetail.spec }"></td>
    <td><label for="unit$text">单位</label></td>
   <td><input id="unit" name="unit" class="mini-textbox" width="100%"  value="${podetail.unit }"></td>
   <td><label for="kind$text">类型</label></td>
   <td><input id="kind" name="kind" class="mini-combobox" textField="text" valueField="id" width="100%" emptyText="请选择..." 
   		nullItemText="请选择..." shouwNullItem="true" allowInput="true" url="GetItemTypeServlet" value="${podetail.kind }"/></td>
   </tr>
   <tr>
    <td><label for="usage$text">用途</label></td>
   <td><input id="usage" name="usage" class="mini-textbox" width="100%"  value="${podetail.usage }"></td>
    <td><label for="po_num$text">数量</label></td>
   <td><input id="po_num" name="po_num" class="mini-textbox" width="100%"  value="${podetail.poNum }"></td>
    <td><label for="unitprice$text">单价</label></td>
   <td><input id="unitprice" name="unitprice" class="mini-textbox" width="100%"  value="${podetail.unitPrice }"></td>

   </tr>
   <tr>
    <td><label for="price$text">货款</label></td>
   <td><input id="price" name="price" class="mini-textbox" width="100%"  value="${podetail.price }"></td>
    <td><label for="orderid$text">合同号</label></td>
   <td><input id="orderid" name="orderid" class="mini-textbox" width="100%"  value="${podetail.orderId }"></td>
   </tr>
   </table>
   </form>   
   </fieldset>
   
   <script type="text/javascript">
   mini.parse();
   function getForm(){
    var form=new mini.Form("detailpo");
    var data=form.getData();
    var json=mini.encode(data);
    form.validate();
    if (form.isValid() == false) {
          return;
    }else{
    $.ajax({
    type:"post",
    dataType: "json",
    url:"doDetailPoServlet",
    data:{submitData:json},
    success: function(data){
    	alert(data.result);
    	window.location.href=window.location.href;

    }
 
    });
   }
   
   }
   
   
   
   
   </script>
  </body>
</html>
