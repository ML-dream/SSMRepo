<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>收货记录</title>
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
	<a class="mini-button" plain="false" iconCls="icon-reload" onclick="refresh()">刷新</a>
	</div>
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;" borderStyle="border:0;" 
    multiSelect="true" idField="id" showSummaryRow="true" allowAlternating="true" showPager="true" showFilterRow="true"
     url="ShowReServlet">
      	<div property="columns">
            <div type="indexcolumn"></div>
    		<div name="action" width="60" headerAlign="center" align="center" renderer="onOperatePower"
    		cellStyle="padding:0;">操作</div>
    		<div field="reDate" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">开单日期
    			<input id="dateFilter" property="filter" class="mini-textbox" style="width:100%;"onvaluechanged="onFilterChanged"/></div>
    		<div field="reSheetid" headerAlign="center" align="center" width="130">单号
    			<input id="sheetidFilter" property="filter" class="mini-textbox" style="width:100%;"onvaluechanged="onFilterChanged"/></div>
    		<div field="warehouseName" headerAlign="center" align="center">库房
    		<input id="warehouseFilter" name="warehouseFilter" property="filter" class="mini-buttonedit" textName="warehouseName" allowInput="false"
    			 onbuttonclick="onButtonEditWarehouse" style="width:100%;" onvaluechanged="onFilterChanged"/></div>
    		<div field="customerName" headerAlign="center" align="center">供应商
    		<input id="customerFilter" name="customerFilter" property="filter" class="mini-buttonedit" onbuttonclick="onSupplierButtonEdit" textName="customerName"
    			 allowInput="false" style="width:100%;" onvaluechanged="onFilterChanged"/></div>
    		<div field="connector" headerAlign="center" align="center">联系人</div>
    		<div field="connectorTel" headerAlign="center" align="center">联系电话</div>
<!--    		<div field="reType" headerAlign="center" align="center">退款类型</div>-->
<!--    		<div field="account" headerAlign="center" align="center">收款账号</div>-->
<!--    		<div field="payMethod" headerAlign="center" align="center">收款方式</div>-->
    		<div field="examineName" headerAlign="center" align="center">审核人<input id="examineFilter" property="filter" class="mini-buttonedit" 
    		textName="examineName"  style="width:100%;" onbuttonclick="onButtonEditEmployee" onvaluechanged="onFilterChanged"/></div>
    		<div field="adminName" headerAlign="center" align="center">仓管员<input id="adminFilter" property="filter" class="mini-buttonedit" 
    		textName="adminName"  style="width:100%;" onbuttonclick="onButtonEditEmployee" onvaluechanged="onFilterChanged"/></div>
    		<div field="salesmanName" headerAlign="center" align="center">业务员<input id="salesmanFilter" property="filter" class="mini-buttonedit" 
    		textName="salesmanName"  style="width:100%;" onbuttonclick="onButtonEditEmployee" onvaluechanged="onFilterChanged"/></div>
    		<div field="drawerName" headerAlign="center" align="center">开单人<input id="drawerFilter" property="filter" class="mini-buttonedit" 
    		textName="drawerName"  style="width:100%;" onbuttonclick="onButtonEditEmployee" onvaluechanged="onFilterChanged"/></div>
    
    	</div>
    </div>
    
    <script type="text/javascript">
    mini.parse();
    var grid=mini.get("grid1");
    grid.load({ sheetid:"",date:"",warehouseId:"",customerId:"",isbill:"",examineId:"",adminId:"",salesmanId:"",drawerId:"" });
    
    
    function onOperatePower(e){
    var str="";
    str+="<span>";
    str+="<a style='margin-right:2px;' title='查看详情' href=javascript:ondSee(\'"+e.row.reSheetid+"\')><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
    str+="</span>";
    str+="<span>";
    str+="<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'"+e.row.reSheetid+"\')><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
    str+="</span>";
    return str;
    }
    
    
    function ondSee(reSheetid){
    window.location="seeReDetailServlet?reSheetid="+reSheetid;
    
    }
    
    function ondEdit(reSheetid){
    window.location="editReServlet?reSheetid="+reSheetid;
    
    }
    
    function refresh(){
    window.location.href=window.location.href;
    
    }
    
     function onFilterChanged() {
            var date = mini.get("dateFilter").getValue();
            var sheetid = mini.get("sheetidFilter").getValue();
			var warehouseId=mini.get("warehouseFilter").getValue();
			var customerId=mini.get("customerFilter").getValue();
			var examineId=mini.get("examineFilter").getValue();
			var adminId=mini.get("adminFilter").getValue();
			var salesmanId=mini.get("salesmanFilter").getValue();
			var drawerId=mini.get("drawerFilter").getValue();
            grid.load({ sheetid: sheetid,date:date,warehouseId:warehouseId,customerId:customerId,
            examineId:examineId,adminId:adminId,salesmanId:salesmanId,drawerId:drawerId });            

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
                         	onFilterChanged();
                        }
                    }
                }
            });
   
   }
   
    
     function onSupplierButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Supplier/selectSupplierWindow.jsp",
                title: "选择列表",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.companyId);
                            btnEdit.setText(data.companyName);
                            onFilterChanged();
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
                            onFilterChanged();
                        }
                    }
                }
            });
        }   
    </script>
  </body>
</html>
