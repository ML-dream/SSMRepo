<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>修改采购退货信息</title>
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
   <legend>采购退货详细信息</legend>
   <form id="redetail" name="redetail" action="#" method="post">
   <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
   <tr bgcolor=#EBEFF5>
  <td><label for="reSheetid$text">单号</label></td>
  <td><input id="reSheetid" name="reSheetid" class="mini-textbox" width="100%" required="true" enabled="false" value="${redetail.reSheetid }"/></td>
   <td><label for="itemId$text">货品编码</label></td>
   <td><input id="itemId" name="itemId" class="mini-textbox" width="100%" required="true" enabled="false" value="${redetail.itemId }"></td>
    <td><label for="itemName$text">货品名称</label></td>
   <td><input id="itemName" name="itemName" class="mini-textbox" width="100%" required="true" enabled="false" value="${redetail.itemName }"></td>

   </tr>
   <tr bgcolor=#EBEFF5>
    <td><label for="spec$text">规格</label></td>
   <td><input id="spec" name="spec" class="mini-textbox" width="100%" required="true" value="${redetail.spec }"></td>
    <td><label for="unit$text">单位</label></td>
   <td><input id="unit" name="unit" class="mini-textbox" width="100%" required="true" value="${redetail.unit }"></td>
    <td><label for="itemType$text">类型</label></td>
   <td><input id="itemType"  name="itemType" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="GetItemTypeServlet" value="L"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."  value="${redetail.itemType}"/></td>
   </tr>
   <tr bgcolor=#EBEFF5>
   <td><label for="reNum$text">数量</label><br></td>
   <td><input id="reNum" name="reNum" class="mini-textbox" width="100%" required="true" value="${redetail.reNum }"></td>
   <td><label for="unitPrice$text">单价</label></td>
   <td><input id="unitPrice" name="unitPrice" class="mini-textbox" width="100%" required="true" value="${redetail.unitPrice }"></td>
   <td><label for="price$text">货款</label></td>
   <td><input id="price" name="price" class="mini-textbox" width="100%" required="true" value="${redetail.price }"></td>
   </tr>
  
   <tr bgcolor=#EBEFF5>
   <td><label for="stockId$text">库位</label></td>
   <td><input id="stockId" name="stockId"  class="mini-textbox" width="100%"  value="${redetail.stockId }"/></td>
   <td><label for="memo$text">备注/合同号</label></td>
   <td colspan="3"><input id="memo" name="memo" class="mini-textbox" width="100%"  value="${redetail.memo }"/></td>
   </tr>
   </table>
   <input id="warehouseId" name="warehouseId" class="mini-hidden" value="${warehouseId }"/> 
   <input id="reNum1" name="reNum1" class="mini-hidden" value="${redetail.reNum }"/>
   </form>   
   </fieldset>
   
   <script type="text/javascript">
   mini.parse();
   function getForm(){
    var form=new mini.Form("redetail");
    var data=form.getData();
    var json=mini.encode(data);
    form.validate();
    if (form.isValid() == false) {
          return;
    }else{
    $.ajax({
    type:"post",
    dataType: "json",
    url:"doeditReDetailServlet",
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
