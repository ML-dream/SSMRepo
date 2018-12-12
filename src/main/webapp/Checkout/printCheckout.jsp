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
	    font-size:100%;
	} 
	-->
	</style>
	
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
	
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    	
     .mini-grid-cell-inner, .mini-grid-headerCell-inner {
		    font-size: 14pt;
		    font-family: Tahoma, Verdana, 宋体;
		    line-height: 18px;
		    padding: 0px;
		    padding-top: 2px;
		    padding-bottom: 2px;
		    width: 100%;
		    position: relative;
		    overflow: hidden;
		    white-space: normal;
		    word-break: break-all;
		}
    </style>
  </head>
  
  
  <body style="width:99%;">
  	<fieldset id="allDetail" style="width: 810px;" align="center">
		<legend>
			送（销）货单
		</legend><!--
	<div id="checkoutdiv"  class="fontManage" >
   		--><table style="text-align: right;border-collapse:collapse;"  width="99%" >
   		<tr  height="35px">
   			<td width="10%" height= "25"><label for="companyname$text">收货单位：</label></td>
            <td width="50%" height= "34px"><input id="companyId" name="companyId" class="mini-textbox" width="100%" height= "100%" readonly borderStyle="background:transparent;border:0"
             	 inputStyle="font-size:17px;" value="${checkout.companyname}" /></td>
        </tr>
        <tr height="25px">
   			<td width="10%"><label for="checkout_date$text">日期：</label></td>
            <td width="18%"><input id="checkout_date"  name="checkout_date" class="mini-textbox"  width="100%" readonly  borderStyle="background:transparent;border:0"
            showTime="false" showOkButton="false" showClearButton="false" value="${checkout.checkoutDate}"/></td>
            <td width="10%"><label for="checksheet_id$text">单号：</label></td>
            <td width="20%"><input id="checksheet_id" name="checksheet_id" class="mini-textbox" width="100%" readonly value="${checkout.checksheetId}" borderStyle="background:transparent;border:0"/></td>
   		    
        </tr>
   		</table>

   	<div id="grid1" class="mini-datagrid" style="width:800px;height:380px;" allowResizeColumn="true" 
    	  multiSelect="true"  idField="id" showSummaryRow="false" allowAlternating="true" showPager="true"
		url="seeDetailCheckoutServlet?checksheetId=${checkout.checksheetId}&warehouseId=${checkout.warehouseId}">
        <div property="columns">
            <div type="indexcolumn"></div>
			<div field="itemName" headerAlign="center" align="center" cellStyle="height:30px;"  >产品名称</div>
            <div field="drawingId" headerAlign="center" align="center"  >图号 </div>
            <div field="checkoutNum" headerAlign="center" align="center"  width="10%">数量</div>
            <div field="unitm"  headerAlign="center" align="center"  width="10%">单位</div>
            <div field="memo" headerAlign="center" align="center" width="10%">备注 </div>
        </div>
    </div>
    <div>
    <table style="text-align: right;"   width="99%" >
       	<tr height="30px">
       		<td width="10%"><label >销货单位：</label></td>
            <td width="25%" style="text-align: left;" >南京纳联数控技术有限公司</td>
       		<td width="6%"><label for="connector$text">收货人:</label></td>
            <td></td>
            <td><label for="delivery$text">送货员:</label></td>
       		<td width="20%"><input id="delivery" name="delivery"  class="mini-textbox" width="100%"  readonly value="${checkout.deliveryName }" borderStyle="background:transparent;border:0" inputStyle="font-size:17px;"/></td>
        	<td width="6%"><label for="warehouse_id$text">库房:</label></td>  
       		<td><input id="warehouse_id" name="warehouse_id" class="mini-textbox" width="100%" readonly value="" borderStyle="background:transparent;border:0"/></td>  
 
        </tr>
       
   		</table>
   		</div><!--
      </div>
    --></fieldset>
 <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    grid.setSizeList([5,8,10,20,50,100]);
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
