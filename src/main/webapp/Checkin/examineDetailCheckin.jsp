<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="com.wl.forms.User"%>
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
    </style>
    
  </head>
  <body>
  <div class="mini-toolbar" >
  <a id="getpass" class="mini-button" plain="false" iconcls="icon-find" onclick="getPass(1)">审核通过</a>
  <span class="sepaator"></span>
  <a class="mini-button" plain="false" iconcls="icon-find" onclick="getPass(2)">审核不通过</a>
  <span class="sepaator"></span>
  <a class="mini-button" plain="false" iconcls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a>
  </div>
    <form id="checkinForm">
    	
  		<table style="text-align: center;border-collapse:collapse;" border="gray 1px solid;"  width="95%">
  			
  			<tr bgcolor=#EBEFF5>
  			<td><label>入库单号</label></td>
  			<td><input id="checksheetId" name="checksheetId" class="mini-textbox" required="true" enabled="false" width="100%" value="${productcheckin.checksheetId }"/></td>
  			<td><label>入库日期</label></td>
  			<td><input id="checkindate" name="checkindate" class="mini-datepicker" required="true" enabled="false" width="100%"dateFormat="yyyy-MM-dd HH:mm:ss" 
  			format="yyyy-MM-dd" showTodayButton="false" showOKButton="false" showClearButton="false" allowInput="false" value="${productcheckin.checkindate }"></td>
  			<td><label>订单号</label></td>
  			<td><input id="orderId" name="orderId" class="mini-textbox" required="true" width="100%" enabled="false" value="${productcheckin.orderId }"/></td>
  			</tr>
  			<tr bgcolor=#EBEFF5>
  			<td><label>图&nbsp;&nbsp;&nbsp;&nbsp;号</label></td>
  			<td><input id="productId" name="productId" class="mini-textbox" required="true" width="100%" enabled="false" value="${productcheckin.productId }"/></td>
  			<td><label>产品名称</label></td>
  			<td><input id="productName" name="productName" class="mini-textbox" required="true" width="100%" enabled="false" value="${productcheckin.productName }"/></td>
  			<td><label>产品编号</label></td>
  			<td><input id="drawingId" name="drawingId" class="mini-textbox" required="true" width="100%" enabled="false" value="${productcheckin.drawingId }"/></td>
  			</tr>
  			<tr bgcolor=#EBEFF5>
  			<td><label>版&nbsp;&nbsp;本&nbsp;&nbsp;号</label></td>
  			<td><input id="issueNum" name="issueNum" class="mini-textbox" required="true" width="100%" enabled="false" value="${productcheckin.issueNum }"/></td>
			<td><label>规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格</label></td>
  			<td><input id="spec" name="spec" class="mini-textbox" required="true" width="100%" enabled="false" value="${productcheckin.spec }"/></td>
  			<td><label>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型</label></td>
  			<td><input id="productType" name="productType" class="mini-combobox" required="true" width="100%"
  			 textField="text" valueField="id" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" enabled="false" allowInput="false" url="GetItemTypeServlet"
  			 value="${productcheckin.productType }" text="${productcheckin.productTypeDesc }"/></td>		
  			
  			</tr>
  			<tr bgcolor=#EBEFF5>	
  			<td><label>单&nbsp;&nbsp;&nbsp;&nbsp;价</label></td>
  			<td><input id="unitPrice" name="unitPrice" class="mini-textbox" required="true" enabled="false" width="100%" value="${productcheckin.unitPrice }"/></td>		
  			<td><label>数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量</label></td>			
  			<td><input id="checkinNum" name="checkinNum" class="mini-textbox" required="true" enabled="false" width="100%" value="${productcheckin.checkinNum }"/></td>			
  			<td><label>单&nbsp;&nbsp;&nbsp;&nbsp;位</label></td>
  			<td><input id="unit" name="unit" class="mini-textbox" required="true" width="100%" enabled="false" value="${productcheckin.unit }"/></td>	
  			</tr>
  			<tr bgcolor=#EBEFF5>
  			 <td><label>质检员</label></td>
            <td><input id="createpersonId" name="createpersonId" class="mini-buttonedit" width="100%" enabled="false" required="true"allowInput="false" 
            textName="createpersonName" onbuttonclick="onButtonEditEmployee" value="${productcheckin.createPerson }" text="${productcheckin.createpersonName }"/></td>
  			<td><label>批&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次</label></td>
  			<td><input id="lot" name="lot" class="mini-textbox" width="100%" enabled="false" value="${productcheckin.lot }"/></td>	
  			<td><label>质编号</label></td>
  			<td><input id="qualityId" name="qualityId" class="mini-textbox" enabled="false" width="100%" value="${productcheckin.qualityId }"/></td>	
  			</tr>
  			<tr bgcolor=#EBEFF5>
            <td><label>送货人</label></td>
            <td><input id="deliveryId" name="deliveryId" class="mini-buttonedit" width="100%" required="true"allowInput="false" 
            textName="deliveryName" onbuttonclick="onButtonEditEmployee" value="${productcheckin.deliveryId }" text="${productcheckin.deliveryName }"/></td>  
            <td><label>仓管员</label></td>
            <td><input id="whstaffId" name="whstaffId" class="mini-buttonedit" width="100%" required="true"allowInput="false" 
            textName="whstaffName" onbuttonclick="onButtonEditEmployee" text="<%=((User)session.getAttribute("user")).getStaffName()%>" value="<%=((User)session.getAttribute("user")).getStaffCode()%>"/></td>     
        	<td><label>库房</label></td>
            <td><input id="warehouseId" name="warehouseId" class="mini-buttonedit" width="100%" required="true" allowInput="false" 
            textName="warehouseName" onbuttonclick="onButtonEditWarehouse" value="p01" text="成品"/></td>  
        	</tr>
        	<tr bgcolor=#EBEFF5>
  			<td><label>库位</label></td>
            <td colspan="5"><input id="shelfNum" name="shelfNum" class="mini-textbox" width="100%" value="${productcheckin.stockId }"/>架</td> 
        	</tr>
   			<tr bgcolor=#EBEFF5 height="60px;">
   			<td><label for="memo$text">附&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</label></td>
	        <td colspan="5"><input id="memo"  name="memo" class="mini-textarea" emptyText="请输入备注" width="100%" height="100%" value="${productcheckin.memo}"/></td>
         	</tr>
  		</table>
    </form>
<!--    <input id="ispass" name="ispass" class="mini-textbox" value="0">-->
<!--    <div align="left">-->
<!--  	<a href="qualitycheck/ProductCheckin/ProductCheckin.jsp"><font size="4">成品入库申请</font></a>-->
<!--  	<img src="img/right.gif" height="15" width="40"/>-->
<!--  	<a href="Checkin/examineProductCheckin.jsp"><font size="4">成品入库审核</font></a>-->
<!--  	<img src="img/right.gif" height="15" width="40"/>-->
<!--  	<a href="qualitycheck/ProductCheckin/showProductCheckin.jsp"><font size="4">返回修改</font></a>-->
<!--  	</div>-->
    <script><!--
    mini.parse();
    
	
    function getPass(status){
    	
    	var form=new mini.Form("checkinForm");
    	var data=form.getData();
    	data.checkindate=mini.get("checkindate").getFormValue();
    	var json=mini.encode(data);
    	form.validate();
    	if(form.isValid()==false){
    		return;
    	}else{
    		mini.get("getpass").disable();
    		$.ajax({
    			type:"post",
    			url:"doexamineDetailCheckinServlet?status="+status,
    			data:{submitData:json},
    			dataType:"json",
    			success:function(data){
    				alert(data.result);
    				//if(data.status==1){
    					
    				//	window.location.href=window.location.href;
    
    				//}
    				//mini.get("ispass").setValue(data.ispass);
    				
    			}
    		
    		
    		});
    	
    	}
    
    }
    
    
     function onButtonEditEmployee(e) {
            var btnEdit = this;
            mini.open({
                url: "employeeManage/selectEmployeeWindow.jsp",
                title: "选择上级部门",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.staffCode);
                            btnEdit.setText(data.staffName);
                            //mini.get("connector").setValue(data.connector);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                        }
                    }
                }
            });
        }
    
    function onButtonEditWarehouse(e){
   	var btnEdit = this;
            mini.open({
                url: "warehouseDefi/selectWarehouseWindow.jsp",
                title: "选择库房",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.warehouseid);
                            btnEdit.setText(data.warehousename);
                         
                        }
                    }
                }
            });
   
   }
    --></script>
  </body>
</html>
