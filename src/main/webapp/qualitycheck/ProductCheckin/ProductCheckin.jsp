<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>成品入库</title>
    
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    	<!-- Javascript goes in the document HEAD -->
</style> 
  </head>
  
  <body>
  <div class="mini-toolbar">
  		<a class="mini-button" iconCls="icon-print" plain="false"  onclick="print()">打印入库单</a>
  		<span class="separator"></span>
  		<a  id="getForm" class="mini-button" iconCls="icon-save" plain="false" onclick="getForm(0)">保存</a>
<!--  		<span class="separator"></span>-->
<!-- 		<a class="mini-button" iconCls="icon-goto" plain="false" onclick="nextForm()">下一单</a>-->
  
  	</div>
  	<div id="checkindiv">
		<h1>成品入库单</h1>  	
		
  				<label>零件条形码</label>
  				<input id="pieId"  name="pieId" class="mini-textbox" autofocus="autofocus" 
	                	 onvaluechanged = "loadgrid()"/>
	            <input value=" ok " type="button" onclick="loadgrid()"/>
		<form id="checkin">
  		<table style="text-align: center;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
  			
  			<tr bgcolor=#EBEFF5>
  			<td><label>入库单号</label></td>
  			<td><input id="checksheetId" name="checksheetId" class="mini-textbox" enabled="false" required="true" width="100%"/></td>
  			<td><label>入库日期</label></td>
  			<td><input id="checkindate" name="checkindate" class="mini-datepicker" required="true" width="100%"
  			dateFormat="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd" showTodayButton="false" showOKButton="false" showClearButton="false" allowInput="false"></td>
  			<td><label>订单号</label></td>
  			<td><input id="orderId" name="orderId" class="mini-textbox" required="true" width="100%"/></td>
  			</tr>
  			<tr bgcolor=#EBEFF5>
  			<td><label>图&nbsp;&nbsp;&nbsp;&nbsp;号</label></td>
  			<td><input id="productId" name="productId" class="mini-textbox" required="true" width="100%"/></td>
  			<td><label>产品名称</label></td>
  			<td><input id="productName" name="productName" class="mini-textbox" required="true" width="100%"/></td>
  			<td><label>产品编号</label></td>
  			<td><input id="drawingId" name="drawingId" class="mini-textbox" required="true" width="100%"/></td>
  			</tr>
  			<tr bgcolor=#EBEFF5>
  			<td><label>版&nbsp;&nbsp;本&nbsp;&nbsp;号</label></td>
  			<td><input id="issueNum" name="issueNum" class="mini-textbox" required="true" width="100%"/></td>
			<td><label>规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格</label></td>
  			<td><input id="spec" name="spec" class="mini-textbox" required="true" width="100%"/></td>
  			<td><label>类&nbsp;&nbsp;&nbsp;&nbsp;型</label></td> 
  			<td><input id="productType" name="productType" class="mini-combobox" required="true" width="100%"
  			 textField="text" valueField="id" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="false" url="GetItemTypeServlet"/></td>		
  			</tr>
  			<tr bgcolor=#EBEFF5>
  			<td><label>单&nbsp;&nbsp;&nbsp;&nbsp;价</label></td>
  			<td><input id="unitPrice" name="unitPrice" class="mini-textbox" required="true" width="100%"/></td>			
  			<td><label>数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量</label></td>
  			<td><input id="checkinnum" name="checkinnum" class="mini-textbox" required="true" width="100%"/></td>			
  			<td><label>单&nbsp;&nbsp;&nbsp;&nbsp;位</label></td>
  			<td><input id="unit" name="unit" class="mini-textbox" width="100%"/></td>			
  			</tr>
  			<tr bgcolor=#EBEFF5>
  			<td><label>批&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次</label></td>
  			<td><input id="lot" name="lot" class="mini-textbox" width="100%"/></td>	
  			<td><label>质编号</label></td>
  			<td colspan="3"><input id="qualityId" name="qualityId" class="mini-textbox"  width="100%"/></td>
  			</tr>
  			
   			<tr bgcolor=#EBEFF5 height="60px;">
   			<td><label for="memo$text">附&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</label></td>
	        <td colspan="5"><input id="memo"  name="memo" class="mini-textarea" emptyText="请输入备注" width="100%" height="100%"/></td>
         	</tr>

  		</table>
  		<input id="id" name="id" class="mini-hidden" enabled="false"/>
  		<input id="seq" name="seq" class="mini-hidden" enabled="false"/>
  	</form>
  	</div>
  	<br/>
  	<br/>
<!--  	<div>-->
<!--  	<a href="qualitycheck/ProductCheckin/ProductCheckin.jsp"><font size="4">成品入库申请</font></a>-->
<!--  	<img src="img/right.gif" height="15" width="40"/>-->
<!--  	<a href="Checkin/examineProductCheckin.jsp"><font size="4">成品入库审核</font></a>-->
<!--  	<img src="img/right.gif" height="15" width="40"/>-->
<!--  	<a href="qualitycheck/ProductCheckin/showProductCheckin.jsp"><font size="4">返回修改</font></a>-->
<!--  	</div>-->
  	
  <script type="text/javascript">
  mini.parse();
  var sheetid="";
  function getForm(status){
  	
  	var form=new mini.Form("checkin");
  	var data=form.getData();
  	data.checkindate=mini.get("checkindate").getFormValue();
  	var json=mini.encode(data);
  		form.validate();
  	if(form.isValid()==false){
  		return;
  	}else{
  		mini.get("getForm").disable();
  		$.ajax({
  			type:"post",
  			url:"ProductServlet?status="+status,		
  			data:{submitData:json},
  			dataType:"json",
  			success: function(data){
  				alert(data.result);
  				if(data.status==1){
  					window.location.href=window.location.href;
  				}
  			}
  		});
  
  	}
  }
  
  function loadgrid(){
  	var form=new mini.Form("checkin");
  	var codeId=mini.get("pieId").getValue();
  	$.ajax({
  		type:"post",
  		url:"getCheckinFormServlet?codeId="+codeId,
    	datatype:"json",
   		success: function(text){
    	var data=mini.decode(text);
    	form.setData(data);
    	sheetid=data.sheetId;
   
    		}  
  	});
  
  } 
  </script>
  
   </body>
</html>
