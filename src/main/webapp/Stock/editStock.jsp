<%@ page language="java" import="java.util.*,com.wl.forms.Employee" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>库存详细信息</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>
    <div class="mini-toolbar">
  		<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm()">保存</a>
  		<span class="separator"></span>
  		<a class="mini-button" plain="false" iconCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a>
	    
  	</div>
  	
  	<fieldset style="width: 50%;" align="center">
		<legend>
			库存管理
		</legend>
   	<form id= "stockdiv">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr bgcolor=#EBEFF5>
   			<td><label for="itemId$text">编号</label></td>
            <td><input id="itemId"  name="itemId" class="mini-textbox" width="100%" required="true" value="${result.itemId}" enabled="false" /></td>
   			<td><label for="itemName$text">名称</label></td>
            <td><input id="itemName" name="itemName" class="mini-textbox" width="100%" value="${result.itemName}" required="true" allowInput="false" enabled="false" />
        </tr>
       	<tr bgcolor=#EBEFF5>
       		<td><label for="spec$text">规格</label></td>
       		<td><input id="spec" name="spec" class="mini-textbox" width="100%" value="${result.spec}" required="true" allowInput="false" enabled="false" /></td>
       		<td><label for="itemType$text">类型</label></td>
            <td><input id="itemType" name="itemType" class="mini-textbox" width="100%" value="${result.itemType}" required="true" allowInput="false" enabled="false" /></td>
   			
       	</tr>	
       	<tr bgcolor=#EBEFF5>
       		<td><label for="warehouseId$text">库房</label></td>
            <td><input id="warehouseId" name="warehouseId" class="mini-textbox" width="100%" required="true" enabled="false" value="${result.warehouseId}"/></td>
       		<td><label for="stockId$text">库位</label></td>
            <td><input id="stockId" name="stockId" class="mini-textbox" width="100%" required="true" enabled="true" value="${result.stockId}"/></td>
       		
       		
        </tr>
        <tr bgcolor=#EBEFF5>
        <td><label for="stockNum$text">库存量</label></td>
        <td><input id="stockNum" name="stockNum" class="mini-textbox" width="100%" required="true" enabled="true" value="${result.stockNum}"/></td>
        <td><label for="unit$text">单位</label></td>
        <td><input id="unit" name="unit" class="mini-textbox" width="100%" required="true" enabled="true" value="${result.unit}"/></td>
        </tr>
   	</table>
   </form>
   </fieldSet>
  	<script>
   		mini.parse();
   		function getForm(){
   			var form = new mini.Form("#stockdiv");
   		        var data = form.getData();
                var json =mini.encode(data);
                $.ajax({
    				url: "DoeditStockServlet",
    				type: "post",
    				dataType:"json",
   					data: { submitData: json },
    				success: function (data) {
	       				alert(data.result);
	       				window.location.href=window.location.href;
    						},
    				error: function (){}
						});
   		}
  	
  	</script>
  </body>
</html>
