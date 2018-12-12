<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>入库记录</title>
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
  <div>
  	<input id="startDate" name="startDate" class="mini-datepicker" showClearButton="false" showTodayButton="fasle"
  	showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false"/>&nbsp;&nbsp;&nbsp;&nbsp;
  	&nbsp;&nbsp;&nbsp;&nbsp;
  	<input id="endDate" name="endDate" class="mini-datepicker" showClearButton="false" showTodayButton="fasle"
  	showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false"/>
  	<a class="mini-button" plain="fasle" onclick="onSearch()">查询</a>
  	<a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()" >刷新</a>
  	</div>
    <div id="grid1" class="mini-datagrid" style="width:100%;height:98%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="showExamineProductCheckinServlet">
        <div property="columns">
            <div type="indexcolumn"></div>
    		<div name="action"  headerAlign="center" align="center" width="60" renderer="onOperatePower">操作</div>
    		<div field="checkindate" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">入库日期</div>
    		<div field="checksheetId" headerAlign="center" align="center">入库单号</div>
    		<div field="orderId" headerAlign="center" align="center">订单号</div>
    		<div field="productId" headerAlign="center" align="center">图号</div>
    		<div field="productName" headerAlign="center" align="center">产品名称</div>
    		<div field="drawingId" headerAlign="center" align="center">产品编号</div>
<!--    		<div field="spec" headerAlign="center" align="center">规格</div>-->
<!--    		<div field="createpersonName" headerAlign="center" align="center">质检员</div>-->
<!--    		<div field="deliveryName" headerAlign="center" align="center">送货员</div>-->
<!--    		<div field="whstaffName" headerAlign="center" align="center">仓管员</div>-->
    		<div field="warehouseName" headerAlign="center" align="center">库房</div>
			<div field="status" headerAlign="center" align="center" renderer="onGrenderRenderer">状态</div>    		
    	</div>    
    </div>
    
    <div id="editForm1" style="display:none;padding:5px;position:relative;">
    	
    	<input class="mini-hidden" name="id"/>
  		<table style="text-align: center;border-collapse:collapse;" border="gray 1px solid;"  width="95%" height="80px;">
  			
  			<tr bgcolor=#EBEFF5>
  			<td><label>入库单号</label></td>
  			<td><input id="checksheetId" name="checksheetId" class="mini-textbox" required="true" enabled="false" width="100%"/></td>
  			<td><label>入库日期</label></td>
  			<td><input id="checkindate" name="checkindate" class="mini-datepicker" required="true" enabled="false" width="100%"
  			dateFormat="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd" showTodayButton="false" showOKButton="false" showClearButton="false" allowInput="false"></td>
  			<td><label>订单号</label></td>
  			<td><input id="orderId" name="orderId" class="mini-textbox" required="true" enabled="false" width="100%"/></td>
  			</tr>
  			<tr bgcolor=#EBEFF5>
  			<td><label>图&nbsp;&nbsp;&nbsp;&nbsp;号</label></td>
  			<td><input id="productId" name="productId" class="mini-textbox" required="true" enabled="false" width="100%"/></td>
  			<td><label>产品名称</label></td>
  			<td><input id="productName" name="productName" class="mini-textbox" required="true" enabled="false" width="100%"/></td>
  			<td><label>产品编号</label></td>
  			<td><input id="drawingId" name="drawingId" class="mini-textbox" required="true" enabled="false" width="100%"/></td>
  			</tr>
  			<tr bgcolor=#EBEFF5>
  			<td><label>版&nbsp;&nbsp;本&nbsp;&nbsp;号</label></td>
  			<td><input id="issueNum" name="issueNum" class="mini-textbox" required="true" enabled="false" width="100%"/></td>
			<td><label>规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格</label></td>
  			<td><input id="spec" name="spec" class="mini-textbox" required="true" enabled="false" width="100%"/></td>
  			<td><label>类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型</label></td>
  			<td><input id="productType" name="productType" class="mini-combobox" required="true" enabled="false" width="100%"
  			 textField="text" valueField="id" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="false" url="GetItemTypeServlet"/></td>		
  					
  			</tr>
  			<tr bgcolor=#EBEFF5>	
  			<td><label>单&nbsp;&nbsp;&nbsp;&nbsp;价</label></td>
  			<td><input id="unitPrice" name="unitPrice" class="mini-textbox" required="true" enabled="false" width="100%"/></td>
  			<td><label>数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量</label></td>
  			<td><input id="checkinNum" name="checkinNum" class="mini-textbox" required="true" enabled="false" width="100%"/></td>
  			<td><label>单&nbsp;&nbsp;&nbsp;&nbsp;位</label></td>
  			<td><input id="unit" name="unit" class="mini-textbox" required="true" enabled="false" width="100%"/></td>				
  			</tr>
  			<tr bgcolor=#EBEFF5>
  			<td><label>质检员</label></td>
            <td><input id="createpersonId" name="createpersonId" class="mini-buttonedit" width="100%" enabled="false" required="true"allowInput="false" 
            textName="createpersonName" onbuttonclick="onButtonEditEmployee"/></td>
  			<td><label>批&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;次</label></td>
  			<td><input id="lot" name="lot" class="mini-textbox" width="100%" enabled="false"/></td>	
  			<td><label>质编号</label></td>
  			<td><input id="qualityId" name="qualityId" class="mini-textbox" enabled="false" width="100%"/></td>	
  			</tr>
  			<tr bgcolor=#EBEFF5>
  			<td><label>送货人</label></td>
            <td><input id="deliveryId" name="deliveryId" class="mini-buttonedit" width="100%" required="true"allowInput="false" 
            textName="deliveryName" enabled="false" onbuttonclick="onButtonEditEmployee"/></td>  
            <td><label>仓管员</label></td>
            <td><input id="whstaffId" name="whstaffId" class="mini-buttonedit" width="100%" required="true"allowInput="false" 
            textName="whstaffName" enabled="false" onbuttonclick="onButtonEditEmployee"/></td>     
  			<td><label>库房</label></td>
            <td><input id="warehouseId" name="warehouseId" class="mini-buttonedit" width="100%" required="true" allowInput="false" 
            textName="warehouseName" enabled="false" onbuttonclick="onButtonEditWarehouse" /></td>
  			</tr>
  			<tr bgcolor=#EBEFF5>
  			<td><label>库位</label></td>
            <td colspan="5"><input id="stockId" name="stockId" class="mini-textbox" width="100%" enabled="false"/></td> 
  			</tr>
   			<tr bgcolor=#EBEFF5 height="60px;">
   			<td><label for="memo$text">附&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</label></td>
	        <td colspan="5"><input id="memo"  name="memo" class="mini-textarea" emptyText="请输入备注" width="100%" enabled="false" height="100%"/></td>
         	</tr>
			
			<tr>
            <td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="6">
<!--                <a class="mini-button" plain="false" onclick="updateRow()">修改保存</a> -->
                <a class="mini-button" plain="false" onclick="cancelRow()">取消</a>
            </td>                
        </tr>
  		</table>
  	</div>
   
    <script>
    mini.parse();
    var grid=mini.get("grid1");
     var editForm = document.getElementById("editForm1"); 
    grid.load({startDate:"",endDate:""});
    
    
    function onOperatePower(e){
    	var grid = e.sender;
        var record = e.record;
        var uid = record._uid;
        var rowIndex = e.rowIndex;
    	var str="";
    	str+="<span>";
    	str+="<a style='margin-right:2px;' title='详情' href=javascript:ondEdit(\'"+uid+"\')><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
   		str+="</span>";
   		return str;
    }
    
    function ondEdit(uid){
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
               
                var form = new mini.Form("editForm1");
                    $.ajax({
                        url: "showProductCheckinFormServlet?checksheetId="+row.checksheetId,
                        success: function (text) {
                            var data = mini.decode(text);
                            form.setData(data);                            

                            form.unmask();
                        }
                    }); 
                     
                grid.doLayout();
            }
    }
    
    
    function updateRow(){
   	 		var form=new mini.Form("editForm1");
   	 		var data=form.getData();
   	 		data.checkindate=mini.get("checkindate").getFormValue();
   	 		var json=mini.encode(data);
   	 		form.validate();
   	 		if(form.isValid()==false){
   	 			return;
   	 		}else{
   	 			$.ajax({
   	 				type:"post",
   	 				url:"doshowExamineProductCheckinServlet",
   	 				data:{submitData:json},
   	 				dataType:"json",
   	 				success: function(data){
   	 					alert(data.result);
   	 					window.location.href=window.location.href;
   	 				}
   	 			
   	 			});
   	 		}
   	 	}
    
    
    
     function cancelRow() {
            grid.reload();
     }
    
     var Genders = [{id: '0', text: '待审核'},{id: '1', text: '审核通过'},{id:'2',text:'审核不通过'}];
    function onGrenderRenderer(e){
    	for(var i=0,l=Genders.length;i<l;i++){
    		var g = Genders[i];;
    		if(g.id==e.value)return g.text;
    	
    	}
    	return "";
    }
    
    function onSearch(){
      	var startDate=mini.get("startDate").getFormValue();
      	var endDate=mini.get("endDate").getFormValue();
      	grid.load({startDate:startDate,endDate:endDate});
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
