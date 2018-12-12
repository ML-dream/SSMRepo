<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>采购统计</title>
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
  <a class="mini-button" iconCls="icon-print" plain="false" onclick="print()">导出EXCEL文件</a>
  </div>
    <div id="grid" class="mini-datagrid" style="height:99%;width:99%;" borderStyle="border:0;" idField="id" showFilterRow="true"
    	multiSelect="true" showSummaryRow="true" allowAlternating="true" showPager="true" showColumnsMenu="true" url="PoStatisticsServlet">
    	<div property="columns">
    		<div type="indexcolumn"></div>
<!--    		<div field="posheetid" headerAlign="center" align="center">单号-->
<!--    			<input id="sheetidFilter" property="filter" class="mini-textbox" style="width:100%;"onvaluechanged="onFilterChanged"/></div>-->
    		<div field="poStartDate" headerAlign="center"  align="center" dateFormat="yyyy-MM-dd">日期
    			<input id="dateFilter" name="dateFilter" property="filter" class="mini-textbox" style="width:100%;"onvaluechanged="onFilterChanged"/></div>
    		<div field="orderId" headerAlign="center"  align="center">订单号
    		<input id="orderFilter" name="orderFilter" property="filter" class="mini-buttonedit" textName="orderName" allowInput="false" 
    			onbuttonclick="onOrderButtonEdit" style="width:100%;" onvaluechanged="onFilterChanged" showClose="true" onCloseClick="onCloseClick('orderFilter')"/></div>
    		<div field="customerName" headerAlign="center"  align="center">供应商
    			<input id="customerFilter" name="customerFilter" property="filter" class="mini-buttonedit" textName="customerName" allowInput="false" 
    			onbuttonclick="onSupplierButtonEdit" style="width:100%;" onvaluechanged="onFilterChanged" showClose="true" onCloseClick="onCloseClick('customerFilter')"/></div>
    		<div field="connector" headerAlign="center" align="center">联系人</div>
    		<div field="connectorTel" headerAlign="center" align="center" visible="false">联系电话</div>
    		<div field="itemId" headerAlign="center" align="center">物品编号</div>
    		<div field="itemName" headerAlign="center" align="center">物品名称</div>
    		<div field="spec" headerAlign="center" align="center">规格</div>
    		<div field="itemTypeDesc" headerAlign="center" align="center">类型</div>
    		<div field="usage" headerAlign="center" align="center">产品用途</div>
    		<div field="poNum" headerAlign="center" align="center">数量(个)/重量(kg)</div>
    		<div field="unitPrice" headerAlign="center" align="center">单价</div>
    		<div field="price" headerAlign="center" align="center">合计</div>
    		<div field="hasPaid" headerAlign="center" align="center" visible="false">已付款</div>
    		<div field="nopay" headerAlign="center" align="center" visible="false">未付款</div>
    		<div field="isBill" headerAlign="center" align="center" renderer="onGenderRenderer" visible="false">是否开票
    		<input id="billFilter" property="filter" class="mini-combobox" textField="text" valueField="id" emptyText="请选择..." style="width:100%;"
    			 url="data/trueOrFalse.txt" allowInput="false" showNullItem="true" nullItemText="请选择..." onvaluechanged="onFilterChanged"/></div>
    		<div field="payTerm" headerAlign="center" align="center" renderer="onPaytermRenderer" visible="false">付款周期</div>
    	</div>
    </div>

  
  <script type="text/javascript">
  	mini.parse();
  	var grid=mini.get("grid");
  	grid.load({ orderId:"",date:"",customerId:"",isbill:"" });
  
  
  	function onCloseClick(keyWord){
  		mini.get(keyWord).setValue("");
  		mini.get(keyWord).setText("");
  		
  		var date = mini.get("dateFilter").getValue();
        var orderId = mini.get("orderFilter").getValue();
		var customerId=mini.get("customerFilter").getValue();
		var isbill=mini.get("billFilter").getValue();
  		
  		grid.load({ orderId:orderId,date:date,customerId:customerId,isbill:isbill });s
  	
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
        
         function onFilterChanged() {
            var datebox = mini.get("dateFilter");
            var sheetidbox = mini.get("orderFilter");
			var customerbox=mini.get("customerFilter");
			var billbox=mini.get("billFilter");
			
			var date=datebox.getValue();
            var orderId = sheetidbox.getValue();
         	var customerId=customerbox.getValue();
         	var isbill=billbox.getValue();
			
            grid.load({ orderId:orderId,date:date,customerId:customerId,isbill:isbill });            

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
        
        function print(){
    	var poStartDate=mini.get("dateFilter").getValue();
    	var orderId=mini.get("orderFilter").getValue();
    	var companyId=mini.get("customerFilter").getValue();
    	window.location="StatisticsExcelServlet?poStartDate="+poStartDate+"&orderId="+orderId+"&companyId="+companyId;
    
    } 
        
    var Genders = [{id: '0', text: '否'},{id: '1', text: '是'}];
    function onGenderRenderer(e){
    	for(var i=0,l=Genders.length;i<l;i++){
    		var g = Genders[i];;
    		if(g.id==e.value)return g.text;
    	
    	}
    	return "";
    }
    
    var Payterms = [{id:'1', text:'一星期'},{id:'2', text:'一个月'},{id:'3', text:'两个月'},{id:'4', text:'三个月'}];
	function onPaytermRenderer(e){
		for(var j=0,len=Payterms.length;j<len;j++){
			var P=Payterms[j];
			if(P.id==e.value) return P.text;
	
			}
			return "";
		}
	
	
	</script>
</body>
</html>
