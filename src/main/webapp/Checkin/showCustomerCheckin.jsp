<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客户资产入库记录</title>
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
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;" borderStyle="border:0;" multiSelect="true"
    idField="id" showSummeryRow="true" allowAlternating="true" showPager="true" url="showCustomerCheckinServlet">
    	<div property="columns">
    		<div type="indexcolumn"></div>
    			<div name="action" headerAlign="center" align="center" width="60" renderer="onOperatePower">操作</div>
    			<div field="checkinDate" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">入库日期</div>
    			<div field="checkSheetid" headerAlign="center" align="center">入库单号</div>
    			<div field="orderId" headerAlign="center" align="center">订单号</div>
    			<div field="customerName" headerAlign="center" align="center">客户名称</div>
    			<div field="deliveryName" headerAlign="center" align="center" width="70">交货人</div>
    			<div field="examineName" headerAlign="center" align="center" width="70">审核人</div>
    			<div field="whstaffName" headerAlign="center" align="center" width="70">仓库管理员</div> 
   		 </div>
    </div>
    
    <div id="editForm" style="display:none;padding:5px;position:relative;">
    	
    	<input class="mini-hidden" name="id"/>
			<table style="text-align: center;border-collapse:collapse;" border="gray 1px solid;"  width="95%" height="80px;">
				<tr bgcolor=#EBEFF5>
				<td><label for="checkinDate$text">日期</label></td>
   				<td><input id="checkinDate" name="checkinDate" class="mini-datepicker" required="true" width="100%" 
   				dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false" showOkButton="false" showClearButton="false"/></td>
   				<td><label for="checkSsheetid$text">单号</label></td>
       			<td><input id="checkSheetid"  name="checkSheetid" class="mini-textbox"  width="100%" required="true" enabled="false"/></td>
        		<td><label for="orderId$text">订单号</label></td>
        		<td><input id="orderId"  name="orderId" class="mini-textbox"  width="100%" required="true" enabled="false"/></td>  	
        		</tr>
       			<tr bgcolor=#EBEFF5>
       			<td><label for="customerId$text">客户编号</label></td>
        		<td><input id="customerId"  name="customerId" class="mini-textbox"  width="100%" required="true" enabled="false"/></td>
       			<td><label for="companyName$text">客户名称</label></td>
           		<td><input id="companyName"  name="companyName" class="mini-textbox"  width="100%" required="true" enabled="false"/></td>
            	<td><label for="itemId$text">资产标号</label></td>
            	<td><input id="itemId"  name="itemId" class="mini-textbox"  width="100%" required="true" enabled="false"/></td>
        		</tr>
       			<tr bgcolor=#EBEFF5>
           
            	<td><label for="itemName$text">资产名称</label></td>
            	<td><input id="itemName"  name="itemName" class="mini-textbox"  width="100%" required="true" enabled="false"/></td>
            	<td><label for="itemTypeId$text">资产类型</label></td>
            	<td><input id="itemTypeId"  name="itemTypeId" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="GetItemTypeServlet" required="true" enabled="false" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            	</td>
             	<td><label for="spec$text">资产规格</label></td>
            	<td><input id="spec"  name="spec" class="mini-textbox"  width="100%" required="true" enabled="false"/></td>
        		</tr>
         		<tr bgcolor=#EBEFF5>    
            	<td><label for="checkinNum$text">资产数量</label></td>
            	<td><input id="checkinNum"  name="checkinNum" class="mini-textbox" width="100%" vtype="int" required="true" enabled="false"/></td> 
            	<td><label for="price$text">资产价格</label></td>
            	<td><input id="price"  name="price" class="mini-textbox"  width="100%" vtype="float" enabled="false" required="true"/></td>  
           		<td><label for="warehouseId$text">库房</label></td>
				<td><input id="warehouseId" name="warehouseId" class="mini-buttonedit" width="100%" textName="warehouseName" onbuttonclick="onButtonEditWarehouse" 
  				allowInput="false" required="true"/></td>	
            
        		</tr>
        		<tr bgcolor=#EBEFF5>
        		<td><label for="stockId$text">库位</label></td>
        		<td><input id="stockId"  name="stockId" class="mini-textbox" width="100%"></td>
        		<td><label for="lot$text">批次</label></td>
				<td><input id="lot" name="lot" class="mini-textbox" width="100%"></td>
       			<td><label for="qualityId$text">质编号</label></td>
       			<td><input id="qualityId" name="qualityId" class="mini-textbox" width="100%"></td>
        
        		</tr>
        		<tr bgcolor=#EBEFF5>
       	 		<td><label for="deliveryId$text">交货人</label></td>
        		<td><input id="deliveryId"  name="deliveryId"  class="mini-buttonedit" width="100%" textName="deliveryName" onbuttonclick="onButtonEditEmployee" 
         		allowInput="false" required="true"/>  
         		</td>
       	 		<td><label for="examineId$text">审核人</label></td>
         		<td><input id="examineId"  name="examineId"  class="mini-buttonedit" width="100%" textName="examineName" onbuttonclick="onButtonEditEmployee" 
         		allowInput="false" required="true"/> </td>
         		<td><label for="whstaffId$text">仓库管理员</label></td>
         		<td><input id="whstaffId"  name="whstaffId"  class="mini-buttonedit" width="100%" textName="whstaffName" onbuttonclick="onButtonEditEmployee" 
         		allowInput="false" required="true"/>  
         		</td>
   		 		</tr>
		
       			<tr bgcolor=#EBEFF5>
			
       			<td><label for="memo$text">附录</label></td>
	    		<td colspan="5"><input id="memo"  name="memo" class="mini-textarea" emptyText="请输入备注"
	        	width="100%" height="80"/></td>
	    		</tr>
				<tr>
            	<td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="6">
                <a class="mini-button" plain="false" onclick="updateRow()">修改保存</a> 
                <a class="mini-button" plain="false" onclick="cancelRow()">取消</a>
           		</td>                
        		</tr>
			</table>   
    </div>
    <script>
    	mini.parse();
    	var grid=mini.get("grid1");
    	grid.load();
    
    	function onOperatePower(e){
    	var grid = e.sender;
        var record = e.record;
        var uid = record._uid;
        var rowIndex = e.rowIndex;
     	var str = "";
	    str += "<span>";
	    str += "<a style='margin-right:2px;' title='详情' href=javascript:ondSee(\'"+uid+"\') ><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
	    str += "</span>";
	    return str;
    	
    	}
    	
    	function ondSee(uid){
    	
    		var row = grid.getRowByUID(uid);
            if (row) {
                //显示行详细
                grid.hideAllRowDetail();
                grid.showRowDetail(row);

                //将editForm元素，加入行详细单元格内
                var td = grid.getRowDetailCellEl(row);
                td.appendChild(editForm);
                editForm.style.display = "";

                //表单加载员工信息
               
                var form = new mini.Form("editForm");
                    $.ajax({
                        url: "showCustomerCheckinFormServlet?checkSheetid="+row.checkSheetid,
                        success: function (text) {
                            var data = mini.decode(text);
                            form.setData(data);                            

                            form.unmask();
                        }
                    }); 
                     
                grid.doLayout();
            }
    	
    	}
    
    function cancelRow(){
    	grid.reload();
    }
    
    function updateRow(){
    	var form=new mini.Form("editForm");
   	 		var data=form.getData();
   	 		data.checkinDate=mini.get("checkinDate").getFormValue();
   	 		var json=mini.encode(data);
   	 		form.validate();
   	 		if(form.isValid()==false){
   	 			return;
   	 		}else{
   	 			$.ajax({
   	 				type:"post",
   	 				url:"doeditCustomerCheckinServlet",
   	 				data:{submitData:json},
   	 				dataType:"json",
   	 				success: function(data){
   	 					alert(data.result);
   	 					window.location.href=window.location.href;
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
    
    </script>
  </body>
</html>
