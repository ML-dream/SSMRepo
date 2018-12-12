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
   
    <title>采购收货明细表</title>
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
	付款方式：<input id="payMethod" name="payMethod" class="mini-combobox" textField="text" valueField="id" emptyText="全部"
    			url="data/payMethod.txt" allowInput="false" showNullItem="true" nullItemText="全部" value="2"/>
	
	<a class="mini-button" plain="fasle" onclick="onSearch()">查询</a>
	
  	</form>
  	</div>
  
  	<div id="grid" class="mini-datagrid" style="width:100%;height:99%;" borderStyle="border:0;"
  	multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true" showColumnsMenu="true"
         url="PrStatisticsServlet">
        <div property="columns">
            <div type="indexcolumn"></div>
<!--            <div name="action" width="80" headerAlign="center" align="center" renderer="onOperatePower"-->
<!--                 cellStyle="padding:0;">操作-->
<!--            </div>-->
            
            <div field="prDate" headerAlign="center" dateFormat="yyyy-MM-dd" align="center">日期
            </div>
            <div field="prSheetid" width="150" headerAlign="center" align="center">收货单号
            </div> 
             <div field="customerName" width="200" headerAlign="center" align="center">送货单位
            </div> 
            <div field="connector" width="60" headerAlign="center" align="center">联系人
            </div>
            <div field="payMethod" width="60" headerAlign="center" align="center" renderer="onStateRenderer">付款方式
            </div>
            <div field="itemId" headerAlign="center" width="60" align="center">货品编号
            </div>
            <div field="itemName" headerAlign="center" width="60" align="center">货品名称
            </div>
            <div field="spec" width="60" headerAlign="center" align="center">规格
            </div>
            <div field="inNum" width="60" headerAlign="center" align="center">收货数量
            </div>
            <div field="unitPrice" width="60" headerAlign="center" align="center">单价
            </div>
            <div field="price" width="60" headerAlign="center" align="center">总价
            </div>
            <div field="unit" width="60" headerAlign="center" align="center">单位
            </div>
            <div field="warehouseName" width="60" headerAlign="center" align="center" visible="false">库房
            </div>
            <div field="itemTypeName" width="60" headerAlign="center" allowSort="false" visible="false">类型
            </div>
            <div field="ussage" width="100" headerAlign="center" allowSort="false" visible="false">用途</div>
            <div field="stockId" width="80" headerAlign="center" allowSort="false" visible="false">库位
    		</div>
        </div>
    </div>

    <script type="text/javascript">
    mini.parse();
    var grid=mini.get("grid");
    var startDate=mini.get("startDate").getFormValue();
    var endDate=mini.get("endDate").getFormValue();
    var companyId=mini.get("companyId").getValue();
    var payMethod=mini.get("payMethod").getValue();
    grid.load({startDate:startDate,endDate:endDate,companyId:companyId,payMethod:payMethod});
    
    function onSearch(){
    var startDate=mini.get("startDate").getFormValue();
    var endDate=mini.get("endDate").getFormValue();
    var companyId=mini.get("companyId").getValue();
    var payMethod=mini.get("payMethod").getValue();
    grid.load({startDate:startDate,endDate:endDate,companyId:companyId,payMethod:payMethod});
    
    }
    
    function onCompanyCloseClick(companyId){
    	mini.get(companyId).setValue("");
    	mini.get(companyId).setText("");
    	var startDate=mini.get("startDate").getFormValue();
    	var endDate=mini.get("endDate").getFormValue();
    	var companyId=mini.get("companyId").getValue();
    	var payMethod=mini.get("payMethod").getValue();
    	grid.load({startDate:startDate,endDate:endDate,companyId:companyId,payMethod:payMethod});
    }
    
    function print(){
    	var startDate=mini.get("startDate").getFormValue();
    	var endDate=mini.get("endDate").getFormValue();
    	var companyId=mini.get("companyId").getValue();
    	var payMethod=mini.get("payMethod").getValue();
    	window.location="PrStatisticsExcelServlet?startDate="+startDate+"&endDate="+endDate+"&companyId="+companyId+"&payMethod="+payMethod;
    
    }
    
    function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Supplier/selectSupplierWindow.jsp",
                title: "选择列表",
                width: 750,
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
                       		
                        }
                    }

                }
            });
        }
        
    	var prstate=[{id:"1",text:"现金"},{id:"2" ,text:"月结"}];
   	     
   	    function onStateRenderer(e) {
            for (var i = 0, l = prstate.length; i < l; i++) {
                var g = prstate[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
    
    </script>
  </body>
</html>
