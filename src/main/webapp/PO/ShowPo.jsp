<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>采购订货记录</title>
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
    <div id="grid1" class="mini-datagrid" style="height:95%;" borderStyle="border:0;" 
    multiSelect="true" idField="id" showSummaryRow="true" allowAlternating="true" showFilterRow="true" showPager="true"
     url="ShowPoServlet">
      	<div property="columns">
            <div type="indexcolumn"></div>
    		<div name="action" width="60" headerAlign="center" align="center" renderer="onOperatePower"
    		cellStyle="padding:0;">操作</div>
    		<div field="postartdate" headerAlign="center"  align="center" dateFormat="yyyy-MM-dd">开单日期
    			<input id="dateFilter" name="dateFilter" property="filter" class="mini-textbox" style="width:100%;"onvaluechanged="onFilterChanged"/></div>
    		<div field="posheetid" headerAlign="center" align="center">单号
    			<input id="sheetidFilter" property="filter" class="mini-textbox" style="width:100%;"onvaluechanged="onFilterChanged"/></div>
    		
<!--    		<div field="orderId" headerAlign="center"  align="center">订单号-->
<!--    			<input id="orderFilter" name="orderFilter" property="filter" class="mini-buttonedit" textName="orderId" allowInput="false" -->
<!--    			onbuttonclick="onOrderButtonEdit" style="width:100%;" onvaluechanged="onFilterChanged"/></div>-->
    		<div field="enddate" headerAlign="center" dateFormat="yyyy-MM-dd"  align="center">交货日期
    		<input id="enddateFilter" name="enddateFilter" property="filter" class="mini-textbox" style="width:100%;"onvaluechanged="onFilterChanged"/></div>
    		<div field="customername" headerAlign="center"  align="center">供应商
    			<input id="customerFilter" name="customerFilter" property="filter" class="mini-buttonedit" textName="customerName" allowInput="false" 
    			onbuttonclick="onSupplierButtonEdit" style="width:100%;" onvaluechanged="onFilterChanged"/></div>
    		<div field="connector" headerAlign="center" align="center">联系人</div>
    		<div field="connectortel" headerAlign="center" align="center">联系电话</div>
    		<div field="salesmanname" headerAlign="center" align="center">业务员
    		<input id="salesmanFilter" property="filter" class="mini-buttonedit" textName="salesmanName"  style="width:100%;" onbuttonclick="onButtonEditEmployee" onvaluechanged="onFilterChanged"/></div>
    		<div field="drawername" headerAlign="center" align="center">开单人
    		<input id="drawerFilter" property="filter" class="mini-buttonedit" textName="drawerName"  style="width:100%;" onbuttonclick="onButtonEditEmployee" onvaluechanged="onFilterChanged"/></div>
    		<div field="status" headerAlign="center" align="center" renderer="onGenderRenderer">状态</div>
    </div>
    </div>
    <script type="text/javascript">
    mini.parse();
    var grid=mini.get("grid1");
    grid.load({sheetid:"",date:"",customer:"",salesmanId:"",drawerId:"",enddate:""});
    
    
    function onOperatePower(e){
    var str="";
    str+="<span>";
    str+="<a style='margin-right:2px;' title='查看详情' href=javascript:ondSee(\'"+e.row.posheetid+"\')><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
    str+="</span>";
    str+="<span>";
    str+="<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'"+e.row.posheetid+"\')><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
    str+="</span>";
    return str;
    }
    
    
    function ondSee(posheetid){
    window.location="seePoDetailServlet?po_sheetid="+posheetid;
    
    }
    
    function ondEdit(po_sheetid){
    window.location="editPoServlet?po_sheetid="+po_sheetid;
    
    }
    
    function refresh(){
    window.location.href=window.location.href;
    
    }
    
    function onFilterChanged(){
    var sheetidbox = mini.get("sheetidFilter");
    var datebox = mini.get("dateFilter");
    var customerbox = mini.get("customerFilter");
    var orderbox = mini.get("orderFilter");
    
    var sheetid=sheetidbox.getValue();
    var date=datebox.getValue();
    var customer=customerbox.getValue();
    var orderId=orderbox.getValue();
    var salesmanId=mini.get("salesmanFilter").getValue();
    var drawerId=mini.get("drawerFilter").getValue();
    var enddate=mini.get("enddateFilter").getValue();
    
    grid.load({sheetid:sheetid,date:date,customer:customer,salesmanId:salesmanId,drawerId:drawerId,enddate:enddate});
    
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
    function onOrderButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Checkout/selectOrderIdWindow.jsp",
                title: "订单号列表",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.orderId);
                            btnEdit.setText(data.orderId);
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
    var Genders = [{id: '0', text: '申请'},{id: '1', text: '待审核'},{id:'2',text:'审核通过'},{id:'3',text:'审核不通过'},{id:'4',text:'完成'}];
    function onGenderRenderer(e){
    	for(var i=0,l=Genders.length;i<l;i++){
    		var g = Genders[i];;
    		if(g.id==e.value)return g.text;
    	
    	}
    	return "";
    	
    }
    </script>
  </body>
</html>
