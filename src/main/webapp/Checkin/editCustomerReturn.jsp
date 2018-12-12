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
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>客户退货入库</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
    
  </head>
  
  <body style="height:99%;">
     <div class="mini-toolbar">
  	 	<a class="mini-button" iconCls="icon-print" plain="false"  onclick="print()">打印</a>
  	 	<span class="separator"></span>
  	 	<a class="mini-button" iconCls="icon-save" plain="false" onclick="getForm()">保存</a>
  	 	<span class="separator"></span>
  	 	<a class="mini-button" iconCls="icon-undo" plain="false" onclick="javascript:window.history.back(-1);">返回</a>
<!--  	 	<span class="separator"></span>-->
<!-- 	 	<a class="mini-button" iconCls="icon-goto" plain="false" onclick="nextForm()">下一单</a>-->
  	</div>
    <div>
    	<form id="CustomerReturn" name="CustomerReturn" action="#" method="post">
    	<table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="99%" >
   		<tr bgcolor=#EBEFF5>
   		<td width="13%"><label for="sheetId$text">单号</label></td>
    	<td><input id="sheetId" name="sheetId" class="mini-textbox" width="100%" required="true" enabled="false" value="${customerreturn.sheetId}"/></td>
    	
    	<td width="13%"><label for="date$text">日期</label></td>
    	<td><input id="date" name="date"  class="mini-datepicker"  width="100%" required="true"  showTodayButton="false"  dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" 
    	showTime="false" showOkButton="false" showClearButton="false" value="${customerreturn.returnDate }"></td>
    	
		<td width="13%"><label for="orderId$text">订单号</label></td>    	
    	<td><input id="orderId" name="orderId" class="mini-buttonedit" width="100%" onbuttonclick="onOrderButtonEdit"
    	textName="orderId" required="true" allowInput="false" value="${customerreturn.orderId }" text="${customerreturn.orderId }"/></td>
    	</tr>
    	<tr bgcolor=#EBEFF5>
    	<td><label for="companyId$text">客户</label></td>
    	<td><input id="companyId" name="companyId" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" 
    	textName="companyName" allowinput="false" value="${customerreturn.companyId }" text="${customerreturn.companyName }"/></td>
    	<td><label for="connector$text">联系人</label></td>
    	<td><input id="connector" name="connector" class="mini-textbox" width="100%" value="${customerreturn.connector }"/></td>
    	<td><label for="connectorTel$text">联系电话</label></td>
    	<td><input id="connectorTel" name="connectorTel" class="mini-textbox" width="100%" value="${customerreturn.connectorTel }"/></td>
    	</tr>
    	</table>
<!--    	<input id="seq" name="seq" class="mini-hidden" value="${CustomerReturnSheetid.seq }"/>-->
<!--    	<input id="id" name="id" class="mini-hidden" value="${CustomerReturnSheetid.id }"/>-->
    	</form>
    	 </div>
    	<div id="grid" class="mini-datagrid" style="width:100%;height:99%;" borderStyle="border:0;" multiSelect="true"
    	 idField="id" allowAlternating="true" showPager="true" url="seeCustomerReturnServlet?sheetId=${customerreturn.sheetId}">
    	 <div property="columns">
    	 	<div type="indexcolumn"></div>
    	 	<div name="action" width="60" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作</div>
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
    
    function getForm(){
    	var form=new mini.Form("CustomerReturn");
  		form.validate();
  		if(form.isValid()==false){
  			return;
  		}else{
  			var data=form.getData();
  			data.date=mini.get("date").getFormValue();
  			var json=mini.encode(data);
  			$.ajax({
  				type:"Post",
  				url:"doEditCustomerReturnServlet",
  				data:{submitData:json},
  				dataType: "json",
  				success:function(data){
  					alert(data.result);
  					if(data.status==1){
  						window.location.href=window.location.href;
  					}
  				}
  			
  			});
  		
  		
  		}
    }
    
    function onOperatePower(e){
    	 var str = "";
	     str += "<span>";
	     str += "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.itemId+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	     str += "</span>";
	     return str;
    
    }
    
    function ondEdit(itemId){
    var sheetId=mini.get("sheetId").getValue();
    window.location="editDetailCustomerReturnSerclet?sheetId="+sheetId+"&itemId="+itemId;
    
    }
    </script>
  </body>
</html>
