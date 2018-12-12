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
      	            <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>销售报表明细表</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
    
  </head>
  <body style="height:99%">
  <div class="mini-toolbar">
  <a class="mini-button" iconCls="icon-print" plain="false" onclick="print()">导出EXCEL文件</a>
  </div>
  
  <div>
  	<form id="datepicker" action="" method="post">
  	
  	日期：<input id="startDate" name="startDate" class="mini-datepicker" showTodayButton="false" showClearButton="true"
  	showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false"/>&nbsp;&nbsp;&nbsp;&nbsp;
  	&nbsp;&nbsp;&nbsp;&nbsp;
  	<input id="endDate" name="endDate" class="mini-datepicker" showTodayButton="fasle" showClearButton="true"
  	showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false"/>
	客户：<input id="companyId" name="companyId" class="mini-buttonedit"  onbuttonclick="onButtonEdit" textName="companyName"  
	allowInput="false" showClose="true" oncloseclick="onCompanyCloseClick('companyId')"/>
	<a class="mini-button" plain="fasle" onclick="onSearch()">查询</a>
	<a class="mini-button" iconCls="icon-node" plain="false" onclick="ondSee(1)">提交标记</a>
	<a class="mini-button" iconCls="icon-remove" plain="false" onclick="ondSee(0)">清除标记</a>
  	</form>
  	</div>
  
  	<div id="grid" class="mini-datagrid" style="width:100%;height:99%;" borderStyle="border:0;" allowUnselect="true"
  	multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true" showColumnsMenu="true"
         url="SalesStatementServlet">
        <div property="columns">
            <div type="indexcolumn"></div>
<!--            <div name="action" width="80" headerAlign="center" align="center" renderer="onOperatePower"-->
<!--                 cellStyle="padding:0;">操作-->
<!--            </div>-->
            <div type="checkcolumn" width="40"></div>
            
            <div field="checkoutDate" headerAlign="center" dateFormat="yyyy-MM-dd" align="center">日期
            </div>
            <div field="checksheetId" width="150" headerAlign="center" align="center">出库单号
            </div> 
            <div field="orderId" width="150" headerAlign="center" align="center">订单号
            </div>
            <div field="itemId" headerAlign="center" align="center">货品编号
            </div>
            <div field="itemName" headerAlign="center" align="center">货品名称
            </div>
            <div field="companyname" width="200" headerAlign="center" align="center">收货单位
            </div> 
            <div field="isreceipted" width="60" headerAlign="center" align="center" renderer="onGenderRenderer">开具发票
            </div>
            <div field="connector" width="60" headerAlign="center" align="center">联系人
            </div>
            <div field="warehouseName" width="60" headerAlign="center" align="center">库房
            </div>
            <div field="deliveryName" width="60" headerAlign="center" align="center">送货人
            </div>
           
            <div field="spec" width="60" headerAlign="center" align="center" visible="false">规格
            </div>
            <div field="checkoutNum" width="60" headerAlign="center" align="center">数量
            </div>
            <div field="unit" width="60" headerAlign="center" align="center">单位
            </div>
            <div field="unitPrice" width="60" headerAlign="center" align="center">单价
            </div>
            <div field="price" width="60" headerAlign="center" align="center">销售货款
            </div>
            <div field="tax" width="60" headerAlign="center" align="center">销项税额
            </div>
            <div field="totalPrice" width="60" headerAlign="center" align="center">价税合计
            </div>
        </div>
    </div>

    <script type="text/javascript"><!--
    mini.parse();
    var grid=mini.get("grid");
    grid.load({startDate:"",endDate:"",companyId:""});
    
    grid.on("beforeload", function (e) {
	       var data = grid.getSelecteds();
	       if (data.length > 0) {
	        mini.alert("请先提交");
	          e.cancel = true;
	          //return;
	       }
	   });
	   var Genders = [{id:"0",text:"否"},{id:"1",text:"是"}];
    function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
    
    function ondSee(para){
      	var data = grid.getSelecteds ( );
      	if(data==null || data.length==0){
      		alert ("无选中记录");
      		return;
      	}
      	var gridjson = mini.encode(data);
	 	//alert(gridjson);
	 	
		$.ajax({
	  		type:"post",
	  		url:"RemarkReceipt?para="+para,
	 	    data:{gridjson:gridjson},
	 	    dataType:"json",
	 		success: function(data){
	  			//alert(data.result);
	  			grid.clearSelect();
	  			grid.reload();
	  		}
	  	});
      	
      }
    
    
    
    function onSearch(){
    var startDate=mini.get("startDate").getFormValue();
    var endDate=mini.get("endDate").getFormValue();
    var companyId=mini.get("companyId").getValue();
    grid.load({startDate:startDate,endDate:endDate,companyId:companyId});
    
    }
    
    function onCompanyCloseClick(companyId){
    	mini.get(companyId).setValue("");
    	mini.get(companyId).setText("");
    	var startDate=mini.get("startDate").getFormValue();
    	var endDate=mini.get("endDate").getFormValue();
    	var companyId=mini.get("companyId").getValue();
    	grid.load({startDate:startDate,endDate:endDate,companyId:companyId});
    }
    
    function print(){
    	var startDate=mini.get("startDate").getFormValue();
    	var endDate=mini.get("endDate").getFormValue();
    	var companyId=mini.get("companyId").getValue();
    	window.location="SalesStatementExcelServlet?startDate="+startDate+"&endDate="+endDate+"&companyId="+companyId;
    
    }
    
    function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "orderManage/selectCustomerWindow.jsp",
                title: "选择列表",
                width: 1050,
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
                            //mini.get("connector").setValue(data.connector);
                            //mini.get("connectortel").setValue(data.connectorTel); 
                        }
                    }

                }
            });
        }
    
    
   </script>
  </body>
</html>
