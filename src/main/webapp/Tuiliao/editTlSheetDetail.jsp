<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>修改退料记录</title>
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
  <a id="getForm" class="mini-button" iconCls="icon-save" plain="false" onclick="getForm()">保存</a> 
  <span class="separator"></span>
  <a class="mini-button" iconCls="icon-undo" plain="false" onclick="javascript:window.history.back(-1);">返回</a>
   </div>
   <fieldset id="allDetail" style="width: 100%;" align="center">
		<legend>
			退料信息
		</legend>
    <div id="editform" class="form" >
   		<table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
   		<tr bgcolor=#EBEFF5>
   		<td><label for="itemId$text">物料号</label></td>
   		<td><input id="itemId" name="itemId" class="mini-buttonedit" width="100%" textName="itemId" required="true" 
   		onbuttonclick="onButtonEdit" allowInput="false" enabled="false" value="${tlsheetdetail.itemId }" text="${tlsheetdetail.itemId }"/></td>
   		<td><label for="itemName$text">物料名称</label></td>
   		<td><input id="itemName" name="itemName" enabled="false" class="mini-textbox" width="100%" required="true" value="${tlsheetdetail.itemName }"/></td>
   		<td><label for="orderId$text">订单号</label></td>
   		<td><input id="orderId" name="orderId" enabled="false" class="mini-textbox" width="100%" required="true" value="${tlsheetdetail.orderId }"/></td>

   		</tr>
   		<tr bgcolor=#EBEFF5>
   		<td><label for="issueNum$text">版本号</label></td>
   		<td><input id="issueNum" name="issueNum" enabled="false" class="mini-textbox" width="100%" required="true" value="${tlsheetdetail.issueNum }"/></td>
   		 <td><label for="itemType$text">类型</label></td>
   		<td><input id="itemType" name="itemType" class="mini-textbox" width="100%" required="true" enabled="false" value="${tlsheetdetail.itemType }"/></td>
    	<td><label for="spec$text">规格</label></td>
   		<td><input id="spec" name="spec" class="mini-textbox" width="100%" required="true" enabled="false" value="${tlsheetdetail.spec }"/></td>
    	
   		</tr>
   		 <tr bgcolor=#EBEFF5>
   		 <td><label for="unit$text">单位</label></td>
   		<td><input id="unit" name="unit" class="mini-textbox" width="100%" required="true" enabled="false" value="${tlsheetdetail.unit }"/></td>
   		<td><label for="rmNum$text">退料数量</label></td>
   		<td><input id="rmNum" name="rmNum" class="mini-textbox" width="100%" required="true" value="${tlsheetdetail.rmNum }"/></td>
    	<td><label for="unitPrice$text">单价</label></td>
   		<td><input id="unitPrice" name="unitPrice" class="mini-textbox" width="100%" required="true" value="${tlsheetdetail.unitPrice }"/></td>
   		</tr>
   		<tr bgcolor=#EBEFF5>
   		<td><label for="price$text">金额</label></td>
   		<td><input id="price" name="price" class="mini-textbox" width="100%" required="true" value="${tlsheetdetail.price }"/></td>
    	<td><label for="stockId$text">库位</label></td>
   		<td><input id="stockId" name="stockId" class="mini-textbox" width="100%" required="false" value="${tlsheetdetail.stockId }"/></td>
    	<td><label for="memo$text">备注</label></td>
   		<td><input id="memo" name="memo" class="mini-textbox" width="100%"  required="false" value="${tlsheetdetail.memo }"/></td>
   		</tr>
   		            
   		</table>
   		<input id="rmSheetid" name="rmSheetid" class="mini-hidden" width="100%" required="true" enabled="false" value="${tlsheetdetail.rmSheetid}"/>
   		<input id="rmNum1" name="rmNum1" class="mini-hidden" width="100%" required="true" enabled="false" value="${tlsheetdetail.rmNum }"/>
		<input id="warehouse_id" name="warehouse_id" class="mini-hidden" width="100%" required="true" enabled="false" value="${warehouse_id }"/>
		</div>
	</fieldset>

	<script>
	mini.parse();
		function getForm(){
 		 	var form = new mini.Form("#editform");
           	var data = form.getData();
            var json = mini.encode(data); 
            form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	mini.get("getForm").disable();
            	$.ajax({
            		url: "doeditTlSheetDetailServlet",
            		type:"post",      
                	data: {submitData: json },
               		dataType: "json",
                	success: function (data) {
                   		alert(data.result);	
		           		window.location.href = window.location.href;	
                	}
               
				});
			}
 		}
	
	</script>
  </body>
</html>
