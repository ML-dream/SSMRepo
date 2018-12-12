<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>采购订货详细信息</title>
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
  		<a class="mini-button" iconCls="icon-print" plain="false"  onclick="print()">打印订单</a>
  		<span class="separator"></span>
  		<a class="mini-button" plain="false" iconCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a>
  	</div>
      <div>
     	 <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
  		 <tr  bgcolor=#EBEFF5>
  		 <td ><label for="postart_date$text">开单日期</label></td>
  		 <td><input id="postart_date" name="postart_date" class="mini-datepicker" required="true" dateFormat="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd" 
  		 showTodayButton="false" showClearButton="false" allowInput="false" width="100%" enabled="false" value="${poplan.postartdate }"/></td>
  		 <td><label for="po_sheetid$text">单号</label></td>
 		 <td><input id="po_sheetid" name="po_sheetid" class="mini-textbox" required="true" width="100%" enabled="false" value="${poplan.posheetid }"></td>
<!--  		 <td><label for="order_id$text">订单号</label></td>-->
<!--  		<td><input id="order_id"  name="order_id" class="mini-buttonedit" width="100%" onbuttonclick="onOrderButtonEdit" -->
<!--            textName="orderId" required="true" allowInput="false" enabled="false" value="${poplan.orderId }" text="${poplan.orderId }"/></td>-->
  		 <td><label for="customerid$text">供应商</label></td>
 		 <td><input id="customerid" name="customerid" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" textName="customername" 
 		 required="true" allowInput="false" enabled="false" value="${poplan.customerid }" text="${poplan.customername }"/></td>
 		 </tr>
 		 <tr  bgcolor=#EBEFF5>
 		 
  		 <td><label for="connector$text">联系人</label></td>
  		 <td><input id="connector" name="connector" class="mini-textbox" required="true" width="100%" enabled="false" value="${poplan.connector }"/></td>
 		 <td><label for="connectortel$text">联系电话</label></td>
 		  <td><input id="connectortel"  name="connectortel" class="mini-textbox" required="true" width="100%" enabled="false" value="${poplan.connectortel }"/></td>
 		<td><label for="salesman_id$text">业务员</label></td>
  		<td><input id="salesman_id" name="salesman_id"  class="mini-buttonedit" width="100%" textName="salesman" 
  		onbuttonclick="onButtonEditEmployee" allowInput="false" required="true" enabled="false" value="${poplan.salesmanid }" text="${poplan.salesmanname }"/></td>
 		</tr>
 		<tr  bgcolor=#EBEFF5>
 		
  		<td><label for="drawer_id$text">开单人</label></td>
  		<td><input id="drawer_id" name="drawer_id"  class="mini-buttonedit" width="100%" textName="drawer" 
 		onbuttonclick="onButtonEditEmployee" allowInput="false" required="true" enabled="false" value="${poplan.drawerid }" text="${poplan.drawername }"/></td>
  		<td><label for="end_date$text">交货日期</label></td>
  		<td><input id="end_date" name="end_date" class="mini-datepicker" required="true" width="100%" dateFormat="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd" 
  		showTodayButton="false" showClearButton="false" allowInput="false" enabled="false" value="${poplan.enddate }"></td>
  		</tr>
 		</table>
      </div>
     <div id="grid1" class="mini-datagrid" style="width:100%;height:75%;" borderStyle="border:0;" multiSelect="true"
     idField="id" showSummaryRow="true" allowAlternating="true" showPager="true" url="ShowPoDetailServlet?po_sheetid=${poplan.posheetid }">
     	<div property="columns">
            <div type="indexcolumn"></div>
            <div field="itemId" headerAlign="center" align="center">货品编码</div>
            <div field="itemName" headerAlign="center" align="center">货品名称</div>
            <div field="spec" headerAlign="center" align="center">规格</div>
            <div field="unit" headerAlign="center" align="center">单位</div>
            <div field="itemType" headerAlign="center" align="center">类型</div>
            <div field="usage" headerAlign="center" align="center">用途</div>
            <div field="poNum" headerAlign="center" align="center">数量</div>
            <div field="unitPrice" headerAlign="center" align="center">单价</div>
            <div field="price" headerAlign="center" align="center">货款</div>
    		<div field="orderId" headerAlign="center" align="center">合同号</div>
   		 </div>
    </div>
    
    <script type="text/javascript">
    mini.parse();
    var grid=mini.get("grid1");
    grid.load();
     function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "orderManage/selectCustomerWindow.jsp",
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
                            mini.get("connector").setValue(data.connector);
                            mini.get("connectortel").setValue(data.connectorTel); 
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
        
        function preview() 
		{ 
			var bdhtml=window.document.body.innerHTML;//获取当前页的html代码 
			var startStr="<!--startprint-->";//设置打印开始区域 
			var endStr="<!--endprint-->";//设置打印结束区域 
			var printHtml=bdhtml.substring(bdhtml.indexOf(startStr)+startStr.length,bdhtml.indexOf(endStr));//从标记里获取需要打印的页面 

			window.document.body.innerHTML=printHtml;//需要打印的页面 
			window.print(); 
			window.document.body.innerHTML=bdhtml;//还原界面 
		} 
		
		
		function print(){
			$("#allDetail").printArea(); 
		}


        
    </script>
    <script>
(function ($) {
    var printAreaCount = 0;
    $.fn.printArea = function () {
        var ele = $(this);
        var idPrefix = "printArea_";
        removePrintArea(idPrefix + printAreaCount);
        printAreaCount++;
        var iframeId = idPrefix + printAreaCount;
        var iframeStyle = 'position:absolute;width:0px;height:0px;left:-500px;top:-500px;';
        iframe = document.createElement('IFRAME');
        $(iframe).attr({
            style: iframeStyle,
            id: iframeId
        });
        document.body.appendChild(iframe);
        var doc = iframe.contentWindow.document;
        $(document).find("link").filter(function () {
            return $(this).attr("rel").toLowerCase() == "stylesheet";
        }).each(
                function () {
                    doc.write('<link type="text/css" rel="stylesheet" href="'
                            + $(this).attr("href") + '" >');
                });
        doc.write('<div class="' + $(ele).attr("class") + '">' + $(ele).html()
                + '</div>');
        doc.close();
        var frameWindow = iframe.contentWindow;
        frameWindow.close();
        frameWindow.focus();
        frameWindow.print();
    }
    var removePrintArea = function (id) {
        $("iframe#" + id).remove();
    };
})(jQuery);
</script>
<script>
		function print2(){
			var bodyHTML=window.document.body.innerHTML;
			//window.document.body.innerHTML=$('#allDetail').html();  
			window.document.body.innerHTML=document.getElementById("allDetail").innerHTML;
			window.print();  
			window.document.body.innerHTML=bodyHTML; 
		}
</script>
	

  </body>
</html>
