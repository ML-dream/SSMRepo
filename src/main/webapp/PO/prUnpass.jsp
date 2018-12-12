<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>采购收货审核未通过</title>
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
    multiSelect="true" idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
     showFilterRow="true" url="LoadPrUnpassSheet">
      	<div property="columns">
            <div type="indexcolumn"></div>
    		<div name="action" width="60" headerAlign="center" align="center" renderer="onOperatePower"
    		cellStyle="padding:0;">操作</div>
    		<div field="prDate" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">开单日期
    			</div>
    		<div field="prSheetid" headerAlign="center" align="center" width="130">单号
    		</div>
    		
    		<div field="customerName" headerAlign="center" align="center">供应商
    			</div>
    		<div field="connector" headerAlign="center" align="center">联系人</div>
    		<div field="connectorTel" headerAlign="center" align="center">联系电话</div>
<!--    		<div field="payTerm" headerAlign="center" align="center">交货期限</div>-->
<!--    		<div field="examineName" headerAlign="center" align="center">审核人</div>-->
    		
    		<div field="isBill" headerAlign="center" align="center" renderer="onGenderRenderer">是否开具发票
    			</div>
   
    	</div>
    </div>
    
    <script type="text/javascript">
    mini.parse();
    var grid=mini.get("grid1");
    grid.load({ sheetid: "",date:"",warehouseId:"",customerId:"",isbill:"",adminId:"" });
    
    
    function onOperatePower(e){
    var str="";
    str+="<span>";
    str+="<a style='margin-right:2px;' title='查看详情' href=javascript:ondEdit(\'"+e.row.prSheetid+"\')><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
    str+="</span>";
    return str;
    }
    
    function ondEdit(prSheetid){
    window.location="editUnpassPrServlet?prSheetid="+prSheetid;		//查看 
    
    }
    
    
    function onFilterChanged() {
            var datebox = mini.get("dateFilter");
            var sheetidbox = mini.get("sheetidFilter");
			var warehousebox=mini.get("warehouseFilter");
			var customerbox=mini.get("customerFilter");
			var billbox=mini.get("billFilter");
			
			var date=datebox.getValue();
            var sheetid = sheetidbox.getValue();
         	var warehouseId=warehousebox.getValue();
         	var customerId=customerbox.getValue();
         	var isbill=billbox.getValue();
			var adminId=mini.get("adminFilter").getValue();
            grid.load({ sheetid: sheetid,date:date,warehouseId:warehouseId,customerId:customerId,isbill:isbill,adminId:adminId });            

        }
    function refresh(){
    window.location.href=window.location.href;
    
    }
    
     var Genders = [{id: '0', text: '否'},{id: '1', text: '是'}];
    function onGenderRenderer(e){
    	for(var i=0,l=Genders.length;i<l;i++){
    		var g = Genders[i];;
    		if(g.id==e.value)return g.text;
    	
    	}
    	return "";
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
