<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>库存盘点记录详情</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">-->
	
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
  		<a class="mini-button" iconCls="icon-print" plain="false"  onclick="print()">打印</a>
  		<span class="separator"></span>
  		<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm()">保存</a>
  		<span class="separator"></span>
  		<a class="mini-button" plain="false" iconCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a>
  	</div>
  <form id="Countsheet" name="Countsheet" action="#" method="post">
  <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
  <tr bgcolor="#D9E7F8">
  <td><label for="CountSheetId$text">单号</label></td>
  <td><input id="CountSheetId" name="CountSheetId" class="mini-textbox" width="100%" required="true" enabled="false" value="${ whcount.countSheetid}"></td>
  <td><label for="CountDate$text">日期</label></td>
  <td><input id="CountDate" name="CountDate" class="mini-datepicker" width="100%" required="true" value="${whcount.countDate}"></td>
  <td><label for="warehouseId$text">库房</label></td>
  <td><input id="warehouseId" name="warehouseId" class="mini-buttonedit" width="100%" textName="warehouseName" onbuttonclick="onButtonEditWarehouse" 
  allowInput="false" required="true" value="${whcount.warehouseId }" text="${whcount.warehouseName }"/></td>
  </tr>
   <tr bgcolor="#D9E7F8">
  <td><label for="operatorId$text">操作人</label></td>
  <td><input id="operatorId" name="operatorId"  class="mini-buttonedit" width="100%" textName="salesmanName" onbuttonclick="onButtonEditEmployee" 
  allowInput="false" required="true" value="${whcount.operatorId }" text="${whcount.operatorName }"></td>
  <td><label for="deptid$text">部门</label></td>
  <td><input id="deptid" name="deptid" class="mini-buttonedit" width="100%" required="true" textName="deptname" onbuttonclick="onButtonEditDept" 
  allowInput="false" value="${whcount.deptId }" text="${whcount.deptName }"/></td>
  <td><label for="empId$text">经办人</label></td>
  <td><input id="empId" name="empId"  class="mini-buttonedit" width="100%" textName="empName" onbuttonclick="onButtonEditEmployee" 
  allowInput="false" required="true" value="${whcount.empId }" text="${whcount.empName }"></td>
  </tr>
  </table>
   </form>
   
  <div id="grid1" class="mini-datagrid" style="width:100%;height:75%;" borderStyle="border:0;" multiSelect="true"
     idField="id" showSummaryRow="true" allowAlternating="true" showPager="true" onrowdblclick="editRow()" url="WhCountServlet?countSheetid=${ whcount.countSheetid }">
     	<div property="columns">
            <div type="indexcolumn"></div>
             <div name="action"  headerAlign="center" align="center" renderer="onOperatePower">操作</div>
            <div field="itemId" headerAlign="center" align="center">货品编码</div>
            <div field="itemName" headerAlign="center" align="center">货品名称</div>
            <div field="spec" headerAlign="center" align="center">规格</div>
            <div field="unit" headerAlign="center" align="center">单位</div>
            <div field="stockId" headerAlign="center" align="center">库位</div>
            <div field="stockNum" headerAlign="center" align="center">库存数量</div>
            <div field="countNum" headerAlign="center" align="center">盘点数量</div>
            <div field="profitLossNum" headerAlign="center" align="center">盈亏数量</div>
            <div field="unitPrice" headerAlign="center" align="center">单价</div>
            <div field="profitLoss" headerAlign="center" align="center">盈亏金额</div>
    		<div field="memo" headerAlign="center" align="center">备注</div>
   		 </div>
    </div>
    
    <div id="editWindow" class="mini-window" title="添加盘点信息" style="width:850px;height:200px;"
    showModal="true" allowResize="true" allowDrag="true"
    	>
    <div id="editform" class="form" >
      <input class="mini-hidden" name="id"/>
        <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;" width="99%">
            <tr bgcolor="#B6E3BF">
                <td><label for="itemId$text">物料编号</label></td>
                <td><input id="itemId" name="itemId" class="mini-buttonedit" textName="itemId" required="true" width="100%" onbuttonclick="onCountItemButton"/></td>
                <td><label for="itemName$text">物料名称</label></td>
                <td><input id="itemName" name="itemName" class="mini-textbox" required="true" width="100%"/></td>
				<td><label for="spec$text">规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格</label></td>
                <td><input id="spec" name="spec" class="mini-textbox" width="100%" required="true"/></td>
            </tr>
            <tr bgcolor="#B6E3BF">
               
                <td><label for="unit$text">单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位</label></td>
                <td><input id="unit" name="unit" class="mini-textbox" width="100%" required="true"/></td>
                <td><label for="stockId$text">库&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位</label></td>
                <td><input id="stockId" name="stockId" class="mini-textbox" width="100%" required="true"/></td>
                <td><label for="stockNum$text">库存数量</label></td>
            	<td><input id="stockNum" name="stockNum" class="mini-textbox" width="100%" required="true"/></td>
            </tr>
            <tr bgcolor="#B6E3BF">
            	<td><label for="countNum$text">盘点数量</label></td>
            	<td><input id="countNum" name="countNum" class="mini-textbox" width="100%" required="true"/></td>
            	<td><label for="profitLossNum$text">盈亏数量</label></td>
            	<td><input id="profitLossNum" name="profitLossNum" class="mini-textbox" width="100%" required="true"/></td>
            	<td><label for="unitPrice$text">单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;价</label></td>
            	<td><input id="unitPrice" name="unitPrice" class="mini-textbox" width="100%" required="true"/></td>
            </tr>
            <tr bgcolor="#B6E3BF">
            	
            	<td><label for="profitLoss$text">盈亏金额</label></td>
            	<td><input id="profitLoss" name="profitLoss" class="mini-textbox" width="100%" required="true"/></td>
            	<td><label for="memo$text">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</label></td>
            	<td colspan="3"><input id="memo" name="memo" class="mini-textbox" width="100%"/></td>
            </tr>
            <tr>
                <td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="6">
                    <a class="Update_Button" href="javascript:updateRow()">保存</a> 
                    <a class="Cancel_Button" href="javascript:cancelRow()">取消</a>
                </td>                
            </tr>
            
        </table>
         <input id="flag" name="flag" class="mini-hidden"/>
         <input id="SitemId" name="SitemId" class="mini-hidden"/>
   	</div>
</div>
    <script>
    mini.parse();
    var grid=mini.get("grid1");
    grid.load();
   var editWindow=mini.get("editWindow");
   
    function getForm(){
    var Cform=new mini.Form("Countsheet");
     var data=Cform.getData();
     data.CountDate=mini.get("CountDate").getFormValue();
     Cform.validate();
     var json=mini.encode(data);
     if(Cform.isValid()==false){
     return;
     }else{
           $.ajax({
           		type:"post",
           		url:"editCountServlet",
           		data:{submitData:json},
           		dataType:"json",
           		success: function(data){
           		alert(data.result);
           		
           		}
           
           });
         }
     }
     
    
    function onOperatePower(e){
    var str="";
    str+="<span>";
    str+="<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'"+e.row.itemId+"\')><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
    str+="</span>";
    return str;
    }  
    
    
    function ondEdit(itemId) {
		
			var editWindow = mini.get("editWindow");
                editWindow.show();
                var form = new mini.Form("#editform");
                var CountSheetId=mini.get("CountSheetId").getValue();
                form.clear();
                form.loading();
                $.ajax({
                    url: "editCountDetailServlet?CountSheetId="+CountSheetId+"&itemId="+itemId,
                    success: function (text) {
                        form.unmask();
                        var data = mini.decode(text);
                        form.setData(data);
                        mini.get("itemId").setText(data.itemId);
                        mini.get("SitemId").setValue(data.itemId);
                    },
                    error: function () {
                       alert("表单加载错误");
                       form.unmask();
                    }
                });

           }
        
        function updateRow() {  
  			var countSheetid=mini.get("CountSheetId").getValue();  
            var form = new mini.Form("#editform");
            var data= form.getData();
			var json = mini.encode(data);
            form.validate();          
           if(form.isValid()==false){
           return;
           
           }else{
            $.ajax({
            	type:"post",
                url: "CountDetailServlet?countSheetid="+countSheetid,
                data: { submitData:json },
                dataType:"json",
                success: function (text) {
                    grid.reload();
                     form.clear();
					
                     editWindow.hide();
                     alert(text.result);
                   
                },
                error: function (jqXHR, atus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
            }
        }
    
   
  
     function cancelRow() {
    
           grid.reload();
           editWindow.hide();
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
        
      function onButtonEditDept(e){
   		var btnEdit = this;
            mini.open({
                url: "deptManage/selectDeptTreeWindow.jsp",
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
                            btnEdit.setValue(data.deptId);
                            btnEdit.setText(data.deptName);
                            //mini.get("connector").setValue(data.connector);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                        }
                    }
                }
            });
   
   
   }  
   function onCountItemButton(e){
   var btnEdit = this;
   var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                url: "WarehouseCount/selectCountItemWindow.jsp?warehouseId="+warehouseId,
                title: "选择上级部门",
                width: 800,
                height: 350,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                           mini.get("itemName").setValue(data.itemName);
                            mini.get("spec").setValue(data.spec);
                             mini.get("unit").setValue(data.unit);
                              mini.get("stockId").setValue(data.stockId);
                               mini.get("stockNum").setValue(data.stockNum);
                                mini.get("unitPrice").setValue(data.unitPrice);
                        }
                    }
                }
            });
   
   
   }
    </script>
     
  </body>
</html>
