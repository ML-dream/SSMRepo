<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>客户退货入库</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
    
  </head>
  
  <body style="height:95%;">
  	 <div class="mini-toolbar">
  	 	<a class="mini-button" iconCls="icon-print" plain="false"  onclick="print()">打印</a>
  	 	<span class="separator"></span>
  	 	<a class="mini-button" iconCls="icon-save" plain="false" onclick="getForm()">保存</a>
  	 	<span class="separator"></span>
 	 	<a class="mini-button" iconCls="icon-goto" plain="false" onclick="nextForm()">下一单</a>
  	</div>
    <div>
    	<form id="CustomerReturn" name="CustomerReturn" action="#" method="post">
    	<table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="99%" >
   		<tr bgcolor=#EBEFF5>
   		<td width="13%"><label for="sheetId$text">单号</label></td>
    	<td><input id="sheetId" name="sheetId" class="mini-textbox" width="100%" required="true" enabled="false" value="${CustomerReturnSheetid.sheetid}"/></td>
    	
    	<td width="13%"><label for="date$text">日期</label></td>
    	<td><input id="date" name="date"  class="mini-datepicker"  width="100%" required="true"  showTodayButton="false"  dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false" showOkButton="false" showClearButton="false"></td>
    	
		<td width="13%"><label for="orderId$text">订单号</label></td>    	
    	<td><input id="orderId" name="orderId" class="mini-buttonedit" width="100%" onbuttonclick="onOrderButtonEdit"
    	textName="orderId" required="true" allowInput="false"/></td>
    	</tr>
    	<tr bgcolor=#EBEFF5>
    	<td><label for="companyId$text">客户</label></td>
    	<td><input id="companyId" name="companyId" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" textName="companyName" allowinput="false"/></td>
    	<td><label for="connector$text">联系人</label></td>
    	<td><input id="connector" name="connector" class="mini-textbox" width="100%"/></td>
    	<td><label for="connectorTel$text">联系电话</label></td>
    	<td><input id="connectorTel" name="connectorTel" class="mini-textbox" width="100%"/></td>
    	</tr>
    	</table>
    	<input id="seq" name="seq" class="mini-hidden" value="${CustomerReturnSheetid.seq }"/>
    	<input id="id" name="id" class="mini-hidden" value="${CustomerReturnSheetid.id }"/>
    	</form>
<!--    	<table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="99%" >-->
<!--   		-->
<!--   		<tr>-->
<!-- 		<td>货品编码</td><td>货品名称</td><td>规格</td><td>版本号</td><td>单位</td><td>数量</td><td>单价</td><td>总价</td><td>退货原因</td>-->
<!--   		</tr>-->
<!--   		<tr>-->
<!--    	<td><input id="itemId1" name="itemId1" class="mini-buttonedit" onbuttonclick="" textname="itemName1" allowinput="false" width="100%"/></td>-->
<!--    	<td><input id="itemName1" name="itemName1" class="mini-textbox" width="100%"/></td>-->
<!--    	<td><input id="spec1" name="spec1" class="mini-textbox" width="100%"/></td>-->
<!--    	<td><input id="issueNum1" name="issueNum1" class="mini-textbox" width="100%"/></td>-->
<!--    	<td><input id="unit1" name="unit1" class="mini-textbox" width="100%"/></td>-->
<!--    	<td><input id="returnNum1" name="returnNum1" class="mini-textbox" width="100%"/></td>-->
<!--    	<td><input id="unitPrice1" name="unitPrice1" class="mini-textbox" width="100%"/></td>-->
<!--    	<td><input id="price1" name="price1" class="mini-textbox" width="100%"/></td>-->
<!--    	<td><input id="reason1" name="reason1" class="mini-textbox" width="100%"/></td>-->
<!--    	</tr>-->
<!--    	-->
<!--    	</table>-->
    	
    </div>
    
    <script>
    mini.parse();
    var form=new mini.Form();
    function getForm(){
    var form=new mini.Form("CustomerReturn");
   
    form.validate();
    if (form.isValid() == false) {
          return;
    }else{
    	var data=form.getData();
    	data.date=mini.get("date").getFormValue();
    	var json=mini.encode(data);
    	$.ajax({
  		type:"post",
  		url:"CustomerReturnCheckinServlet",
 	    data:{submitData:json},
 	    dataType:"json",
 		success: function(data){
  		alert(data.result);
  			if(data.status==1){
  				window.location.href=window.location.href;
  			}
  		}
  	});
    
    }
    }
    
    function nextForm(){
    	window.location.href=window.location.href;
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
                            mini.get("companyId").setValue(data.customer);
                            mini.get("companyId").setText(data.companyName);
                            mini.get("connector").setValue(data.connector);
                            mini.get("connectorTel").setValue(data.connectorTel); 
                          /*var orderId=mini.get("order_id").getValue();
                           var checkoutId=mini.get("checksheet_id").getValue();
                			var grid=mini.get("grid1");
   	 	 					grid.load({orderId:orderId,checkoutId:checkoutId});*/
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
    </script>
  </body>
</html>
