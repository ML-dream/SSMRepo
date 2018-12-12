<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>出库审核</title>
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
  
  
  <body style="width:99%;">
   <div class="mini-toolbar">
  		<a class="mini-button" iconCls="icon-ok" plain="false"  onclick="getForm(1)">审核通过</a>
  		<span class="separator"></span>
  		<a class="mini-button" iconCls="icon-remove" plain="false"  onclick="getForm(2)">审核不通过</a>
  		<span class="separator"></span>
  		<a class="mini-button" iconCls="icon-print" plain="false"  onclick="printCheckout()">打印出库单</a>
  		<span class="separator"></span>
  		<a class="mini-button" plain="false" iconCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a>
  	</div>
  	<!--startprint-->
  	<fieldset id="allDetail" style="width: 99%;" align="center">
		<legend>
			出库单详细信息
		</legend>
		<div id="checkoutdiv">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%" >
   		<tr bgcolor=#EBEFF5>
   			<td width="13%"><label for="checkout_date$text">日期</label></td>
            <td><input id="checkout_date"  name="checkout_date" class="mini-datepicker"  width="100%" required="true" enabled="false" dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" 
            showTime="false" showOkButton="false" showClearButton="false" value="${checkout.checkoutDate}"/></td>
            <td width="13%"><label for="checksheet_id$text">单号</label></td>
            <td><input id="checksheet_id" name="checksheet_id" class="mini-textbox" width="100%" required="true" enabled="false" value="${checkout.checksheetId}" /></td>
<!--   			<td width="13%"><label for="order_id$text">订单号</label></td>-->
<!--            <td><input id="order_id"  name="order_id" class="mini-buttonedit" width="100%" onbuttonclick="onOrderButtonEdit" value="${checkout.orderId }" text="${checkout.orderId }"-->
<!--            textName="orderId" required="true" enabled="false" allowInput="false"/></td>-->
            
   		    <td><label for="companyname$text">收货单位</label></td>
            <td><input id="companyId" name="companyId" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" textName="companyName" required="true" allowInput="false" 
            value="${checkout.companyid }" text="${checkout.companyname}" enabled="false"/></td>
        </tr>
       	<tr bgcolor=#EBEFF5>
       		<td><label for="connector$text">收货人</label></td>
            <td><input id="connector"  name="connector" class="mini-textbox" required="true" width="100%" enabled="false" value="${checkout.connector }"/></td>
            <td><label for="connectortel$text">联系电话</label></td>
            <td><input id="connectortel"  name="connectortel" class="mini-textbox" required="true" width="100%" enabled="false" value="${checkout.connectortel }"/></td>
  			<td><label for="warehouse_id$text">库房</label></td>  
       		<td><input id="warehouse_id" name="warehouse_id" class="mini-buttonedit" width="100%" enabled="false" textName="warehouse_name" onbuttonclick="onButtonEditWarehouse"
  			allowInput="false" required="true" value="${checkout.warehouseId}" text="${checkout.warehouseName }"/></td>  
  		</tr>
  		
  		<tr bgcolor=#EBEFF5>	
  			<td><label for="delivery$text">送货员</label></td>
       		<td><input id="delivery" name="delivery"  class="mini-buttonedit" width="100%" textName="deliveryName" onbuttonclick="onButtonEditEmployee" allowInput="false" 
        	required="true" enabled="false" value="${checkout.delivery }" text="${checkout.deliveryName }"></td>
  			<td><label for="orderStatus$text">订单状态</label></td>
   			<td><input id="orderStatus" name="orderStatus"  class="mini-combobox" width="100%" textField="text" enabled="false" valueField="id" allowInput="false" required="true" url="data/orderStatus.txt"
   		    value="${checkout.orderStatus }" text="${checkout.orderStatusDesc }"></td>
       		<td><label for="checkoutType$text">出库类型</label></td>
 			<td><input id="checkoutType" name="checkoutType"  class="mini-combobox" width="100%" enabled="false" textField="text" valueField="id" allowInput="false" required="true" url="data/CheckoutType.txt" 
 			value="${checkout.checkoutType }" text="${checkout.checkoutTypeDesc }"></td>
        </tr>
        <tr bgcolor=#EBEFF5>
   		<td><label for="opinion$text">审核意见</label></td>
  		<td colspan="5"><input id="opinion" name="opinion" class="mini-textarea" width="100%" height="70px" emptyText="请输入意见" value="${checkout.opinion }"></td>
  		</tr>
   		</table>
   		</div>
   		<div id="grid1" class="mini-datagrid" style="width:99%;height:95%;"
    	 borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
		url="seeDetailCheckoutServlet?checksheetId=${checkout.checksheetId}">
        <div property="columns">
            <div type="indexcolumn"></div>

            <div field="itemId" headerAlign="center" align="center" width="80">图号 </div>
            <div field="itemName" headerAlign="center" align="center" width="80">产品名称</div>
<!--            <div field="issueNum"  headerAlign="center" align="center" width="40">版本号</div>-->
<!--            <div field="drawingId"  headerAlign="center" align="center" width="40">产品号</div>-->
            <div field="itemTypeDesc" headerAlign="center" align="center" width="40">类型</div>
            <div field="spec" headerAlign="center" align="center" width="40">规格</div>
<!--            <div field="stockNum"  headerAlign="center" align="center">库存数量</div>-->
            <div field="purductNum"  headerAlign="center" align="center" width="60">订单总数</div>
            <div field="alreadyPayNum"  headerAlign="center" align="center" width="60">已交付数量</div>
            <div field="noPayNum"  headerAlign="center" align="center" width="60" visible="false">未交付数量</div>
            <div field="stockNum"  headerAlign="center" align="center" width="60" headerStyle="color:red;">库存数量</div>
            
            <div field="checkoutNum" headerAlign="center" align="center" width="70">本次出库数量</div>
            
            <div field="unitprice"  headerAlign="center" align="center" width="40">单价</div>
            <div field="price" headerAlign="center" align="center" width="40">本次出库金额 </div>
<!--            <div field="stockId" headerAlign="center" align="center" width="40">库位</div>-->
            <div field="memo" headerAlign="center" align="center" width="60">备注 </div>
         
        </div>
    </div>
    </fieldset>
<!--    <div align="left">-->
<!--  	<a href="Checkout/Checkout.jsp"><font size="4">成品出库申请</font></a>-->
<!--  	<img src="img/right.gif" height="15" width="40"/>-->
<!--  	<a href="Checkout/examineCheckout.jsp"><font size="4">成品出库审核</font></a>-->
<!--  	<img src="img/right.gif" height="15" width="40"/>-->
<!--  	<a href="Checkout/showCheckout.jsp"><font size="4">返回修改</font></a>-->
<!--  	</div>-->
     <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    
	    function printCheckout(){
	    	var checksheetId = mini.get("checksheet_id").getValue();
	    	window.open("ToPrintCheckout?checkSheetid="+checksheetId,
	    	          "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
	    }
	    function getForm(status){
	    	var form=new mini.Form("checkoutdiv");
	    	form.validate();
	    	if(form.isValid()==false){
	    		return;
	    	}else{
	    		var data=form.getData();
	    		data.checkout_date=mini.get("checkout_date").getFormValue();
	    		var json=mini.encode(data);
	    		$.ajax({
	    			type:"post",
	    			url:"doExamineCheckoutServlet?status="+status,
	    			data:{submitData:json},
	    			dataType:"json",
	    			success:function(data){
	    			
	    			alert(data.result);
	    			if(data.status==1){
	    				window.location.href=window.location.href;
	    			}
	    			
	    			}
	    		
	    		});
	    	
	    	}
	    
	    
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
                            mini.get("companyname").setValue(data.companyName);
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
