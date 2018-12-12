<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>产品出库</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">-->
	
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
    <style>
	#showbox {
		width: 150px;
		min-height: 50px;
		font: 100 14px/1 "微软雅黑";
		border: 1px solid #3c8dbc;
		display: none;
		position: absolute;
		top: 0;
		left: 0;
		background-color: #fff;
		padding: 5px;
	}
</style>
    
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  
  <body style="width:100%;">
    <div class="mini-toolbar">
  		<a class="mini-button" iconCls="icon-print" plain="false"  onclick="printCheckout()">打印出库单</a>
		<span class="separator"></span>
 		<a class="mini-button" iconCls="icon-goto" plain="false" onclick="nextForm()">下一单</a>
 		<span class="separator"></span>
 		<a class="mini-button" id="oneKey" iconCls="icon-goto" plain="false" onclick="oneKeyOut()">批量出库</a>
		<span class="separator"></span>
 		<a class="mini-button" id="fresh" iconCls="icon-reload" plain="false" onclick="orderRefrsh()">订单状态更新</a>
  	</div>
  	<!--startprint-->
  	<fieldset id="allDetail" style="width: 100%;" align="center">
		<legend>
			出库单信息
		</legend>
		
		<form id= "checkoutdiv" name="checkoutdiv" action="AddCheckoutServlet" method="post">
   		<table id="checkout" style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%" >
   		<tbody>
   		<tr bgcolor=#EBEFF5>
   			<td><label for="checkout_date$text">日期</label></td>
            <td><input id="checkout_date"  name="checkout_date" class="mini-datepicker"  width="100%" required="true"  dateFormat="yyyy-MM-dd  HH:mm:ss"  showTodayButton="false" allowInput="false" format="yyyy-MM-dd" 
            showTime="false" showOkButton="false" showClearButton="false" /></td>
            <td><label for="checksheet_id$text">单号</label></td>
            <td><input id="checksheet_id" name="checksheet_id" class="mini-textbox" width="100%" enabled="false" required="true" value="${checksheet_id.sheetid}" onhover="showBox"/></td>
   			<td><label for="companyId$text">收货单位</label></td>
       		<td><input id="companyId" name="companyId" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" textName="companyName" required="true" allowInput="false"/></td>
   			
   			
        	
        </tr>
       	<tr bgcolor=#EBEFF5>
       		
       		<td><label for="connector$text">联系人</label></td>
            <td><input id="connector"  name="connector" class="mini-textbox" required="true" width="100%" /></td>
            <td><label for="connectortel$text">联系电话</label></td>
            <td><input id="connectortel"  name="connectortel" class="mini-textbox" required="true" width="100%" /></td>
            <td><label for="order_id$text">订单号</label></td>
            <td><input id="order_id"  name="order_id" class="mini-buttonedit" width="100%" onbuttonclick="onOrderButtonEdit" 
            textName="orderId" required="true" allowInput="false"/></td>
  			
        	
<!--        <td><label for="operator$text">审核人</label></td>-->
<!--  		<td><input id="operator" name="operator"  class="mini-buttonedit" width="100%" textName="operatorName" enabled="false" onbuttonclick="onButtonEditEmployee" allowInput="false" required="true"></td>-->
 			
 		</tr>
 			<tr bgcolor=#EBEFF5>
 			
 			<td><label for="warehouse_id$text">库&nbsp;&nbsp;&nbsp;&nbsp;房</label></td>
   			<td><input id="warehouse_id" name="warehouse_id" class="mini-buttonedit" width="100%"  textName="warehouse_name" onbuttonclick="onButtonEditWarehouse" 
  				allowInput="false" required="true" value="p01" text="成品库"/></td>
 			<td><label for="delivery$text">销货员</label></td>
  			<td><input id="delivery" name="delivery"  class="mini-buttonedit" width="100%" textName="deliveryName" onbuttonclick="onButtonEditEmployee" allowInput="false" 
  			required="true" value="${deliveryId }" text="${deliveryName }"></td>
 			<td><label for="orderStatus$text">订单状态</label></td>
   			<td><input id="orderStatus" name="orderStatus"  class="mini-combobox" width="100%" allowInput="false" required="true" url="data/orderPayStatus.txt" value="10"></td>
  			
 			</tr>
 			<tr  bgcolor=#EBEFF5>
 			<td><label for="checkoutType$text">出库类型</label></td>
 			<td><input id="checkoutType" name="checkoutType"  class="mini-combobox" width="100%" allowInput="false" required="true" url="data/CheckoutType.txt" value="0"></td>
 			
 			</tr>
 		</tbody>
   		</table>
   		<div id="showbox"></div>
   		<input id="id" name="id" class="mini-hidden" required="true" value="${checksheet_id.id}"/>
        <input id="seq" name="seq" class="mini-hidden" required="true" value="${checksheet_id.seq}"/>	
   	 </form>
   		 <div id="grid1" class="mini-datagrid" style="width:99%;height:99%;" 
        url="GetItemOrderServlet" idField="id" allowResize="true" allowCellEdit="true" showColumnsMenu ="true" pageSize="20"
        allowCellSelect="true" multiSelect="true" editNextOnEnterKey="true"  editNextRowCell="true" showPager="true">
        <div property="columns">
        	<div type="checkcolumn"></div>
            <div type="indexcolumn"></div>
            <div name="action" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;" width="40">操作</div>
            <div field="drawingId" headerAlign="center" align="center" width="80">图号 </div>
            <div field="productName" headerAlign="center" align="center" width="80">产品名称</div>
            <div field="issueNum"  headerAlign="center" align="center" width="40">版本号</div>
<!--            <div field="drawingId"  headerAlign="center" align="center" width="80" validate="false">产品号</div>-->
<!--            <div field="productTypeDesc" headerAlign="center" align="center" width="40">类型</div>-->
            <div field="spec" headerAlign="center" align="center" width="40">规格</div>
            <div field="unitm"  headerAlign="center" align="center" width="40">单位</div>
            <div field="purductNum"  headerAlign="center" align="center" width="60">订单总数</div>
            <div field="alreadyPayNum"  headerAlign="center" align="center" width="60">已交付数量</div>
            <div field="noPayNum"  headerAlign="center" align="center" width="60" visible="false">未交付数量</div>
            <div field="stockNum"  headerAlign="center" align="center" width="60" headerStyle="color:red;">库存数量</div>
            <div field="checkoutNum" headerAlign="center" align="center" width="70">本次出库数量</div>
            <div field="unitPrice"  headerAlign="center" align="center" width="40">单价</div>
            <div field="price" headerAlign="center" align="center" width="80">本次出库金额 </div>
<!--            <div field="stockId"  headerAlign="center" align="center" width="40">库位</div>-->
<!--            <div field="qualityId" headerAlign="center" align="center">质编号 </div>-->
            <div field="memo" headerAlign="center" align="center" width="60">备注 </div>
   		</div>
   		</div>

   	<div id="editForm1" style="display:none;padding:5px;position:relative;">
    <input class="mini-hidden" name="id"/>
     <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%" >
       <tr bgcolor=#EBEFF5>
    <td><label for="productId$text">图号</label></td>
    <td><input id="productId" name="productId" class="mini-textbox" width="100%" required="true" enabled="false"/></td>
   	<td><label for="productName$text">产品名称</label></td>
    <td><input id="productName" name="productName" class="mini-textbox" width="100%" required="true" enabled="false"/></td>
  	<td><label for="issueNum$text">版本号</label></td>
  	<td><input id="issueNum" name="issueNum" class="mini-textbox" width="100%" required="true" enabled="false"/></td>
    </tr>
    <tr bgcolor=#EBEFF5>
     <td><label for="drawingId$text">产品号</label></td>
    <td><input id="drawingId" name="drawingId" class="mini-textbox" width="100%" required="true" enabled="false"/></td>
    <td><label for="productType$text">类&nbsp;&nbsp;&nbsp;&nbsp;型</label></td> 
  	<td><input id="productType" name="productType" class="mini-combobox" required="true" width="100%" enabled="false"
  			 textField="text" valueField="id" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="false" url="GetItemTypeServlet"/></td>
    <td><label for="spec$text">规格</label></td>
    <td><input id="spec" name="spec" class="mini-textbox" width="100%" required="true" enabled="false"/></td>
    
    </tr>
    <tr bgcolor=#EBEFF5>
     <td><label for="stockNum$text">库存数量</label></td>
     <td><input id="stockNum" name="stockNum" class="mini-textbox" width="100%" required="true" enabled="false"/></td>
     <td><label for="purductNum$text">订单总数</label></td>
    <td><input id="purductNum" name="purductNum" class="mini-textbox" width="100%" required="true" enabled="false"/></td>
    <td><label for="alreadyPayNum$text">已交付数量</label></td>
    <td><input id="alreadyPayNum" name="alreadyPayNum" class="mini-textbox" width="100%" required="true" enabled="false"/></td>
  
    </tr>
    <tr bgcolor=#EBEFF5>
     <td><label for="noPayNum$text">未交付数量</label></td>
    <td><input id="noPayNumPayNum" name="noPayNum" class="mini-textbox" width="100%" required="true" enabled="false"/></td>
    <td><label for="unitPrice$text">单价</label></td>
    <td><input id="unitPrice" name="unitPrice" class="mini-textbox" width="100%"  required="true" enabled="false"/></td>
    <td><label for="checkoutNum$text">本次交付数量</label></td>
    <td><input id="checkoutNum" name="checkoutNum" class="mini-textbox" width="100%" required="true"/></td>
   
   
   
   
    </tr>
   <tr bgcolor=#EBEFF5>
    <td><label for="price$text">金额</label></td>
    <td><input id="price" name="price" class="mini-textbox" width="100%" /></td>
    <td><label for="stockId$text">库位</label></td>
    <td><input id="stockId" name="stockId" class="mini-textbox" width="100%"/></td>
    <td><label for="memo$text">备注</label></td>
    <td><input id="memo" name="memo" class="mini-textbox" width="100%" required="false"/></td>

    </tr>
     <tr>
            <td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="6">
                <a id="update" class="mini-button" plain="false" onclick="updateRow(0,0)">保存</a> 
                <a class="mini-button" plain="false" onclick="cancelRow()">取消</a>
            </td>                
        </tr>
    
    </table>
    </div>
   

   	 
   	 <div align="left">
   	 销货单位：南京纳联数控技术有限公司<br/>
   	 公司地址：南京市江宁区东山街道中前社区前马场118-1号&nbsp;&nbsp;&nbsp;&nbsp;电话/传真：025-84272402
   	 </div>
<!--   	<div align="left">-->
<!--  	<a href="Checkout/Checkout.jsp"><font size="4">成品出库申请</font></a>-->
<!--  	<img src="img/right.gif" height="15" width="40"/>-->
<!--  	<a href="Checkout/examineCheckout.jsp"><font size="4">成品出库审核</font></a>-->
<!--  	<img src="img/right.gif" height="15" width="40"/>-->
<!--  	<a href="Checkout/showCheckout.jsp"><font size="4">返回修改</font></a>-->
<!--  	</div>-->
   	 </fieldset>
   	  
   	 <script type="text/javascript">
   	 	mini.parse(); 
   	 	var editForm = document.getElementById("editForm1");        
        var grid=mini.get("grid1");
        
        function helpOneKey(){
        	var griddata = grid.getSelecteds ( );
	   		var gridjson = mini.encode(griddata);
		 	//alert(gridjson);
		 	var checksheet_id=mini.get("checksheet_id").getValue();
          	var orderId=mini.get("order_id").getValue(); 
          	var checkoutType=mini.get("checkoutType").getValue();
          	
			$.ajax({
		  		type:"post",
		  		async:"false",
		  		url:"OneKeyOut?checksheet_id="+checksheet_id+"&orderId="+orderId+"&checkoutType="+checkoutType,
		 	    data:{gridjson:gridjson},
		 		success: function(data){
		  			grid.reload();
		  			 mini.get("oneKey").enable();
		  			 var text = mini.decode(data);
		  			 alert(text.result);
		  		}
	  		});
        }
        function oneKeyOut(){
        	var status = 0;
        	var Cform=new mini.Form("checkoutdiv");
            var Cdata=Cform.getData();
             var orderId=mini.get("order_id").getValue();
             var checkoutId=mini.get("checksheet_id").getValue();
            Cdata.checkout_date=mini.get("checkout_date").getFormValue();
            
            var Cjson=mini.encode(Cdata);
            //grid.loading("保存中，请稍后......");
          	Cform.validate();
   		  if(Cform.isValid()==false){
                return;
            }
            mini.get("oneKey").disable();
            $.ajax({
   				type:"post",
   				async:"false",
      			url: "AddCheckoutServlet?status="+status,
       			data: {submitData: Cjson },
       			dataType:"json",
   				success: function(text){
					helpOneKey();
				}
			});
        }
        
        function orderRefrsh(){
        	$.ajax({
		  		type:"post",
		  		url:"refreshOrderStatusServlet",
		 	    //data:{gridjson:gridjson},
		 	    dataType:"json",
		 		success: function(data){
		 		
		  			 alert(data.result);
		  		}
	  		});
        
        
        
        }
        
        
         function printCheckout(){
	    	var checksheetId = mini.get("checksheet_id").getValue();
	    	window.open("ToPrintCheckout?checkSheetid="+checksheetId,
	    	          "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
	    }
        grid.on("drawcell",function (e) {
            var record = e.record,
        	column = e.column,
        	field = e.field,
        	value = e.value;
	    	if (field == "stockNum" && value >0) {
                e.cellStyle = "color:red;font-weight:bold;";
            }
	    	
	    	}
	    
	    
	    
	    );
    
    function nextForm(){
    window.location.href=window.location.href;
    }
    
      function onOperatePower(e){
        	var grid = e.sender;
            var record = e.record;
            var uid = record._uid;
            var rowIndex = e.rowIndex;
     		var str = "";
	        str += "<span>";
	        str = "<a style='margin-right:2px;' title='出库' href=javascript:ondEdit(\'"+uid+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
	        return str;
      }
      
      
      
      
     function ondEdit(uid){
       		var row = grid.getRowByUID(uid);
       		var orderId=mini.get("order_id").getValue();
       		var warehouseId=mini.get("warehouse_id").getValue();
            if (row) {
                //显示行详细
                grid.hideAllRowDetail();
                grid.showRowDetail(row);

                //将editForm元素，加入行详细单元格内
                var td = grid.getRowDetailCellEl(row);
                td.appendChild(editForm);
                editForm.style.display = "";

                //表单加载员工信息
               
                var form = new mini.Form("editForm1");
                    $.ajax({
                        url: "CheckoutFormServlet?orderId="+orderId+"&productId="+row.productId+"&warehouseId="+warehouseId,
                        success: function (text) {
                            var data = mini.decode(text);
                            form.setData(data);                             
							// xiem 本次出库数量默认为库存数量
							mini.get("checkoutNum").setValue(data.stockNum);
                            form.unmask();
                        }
                    }); 
                     
                grid.doLayout();
                
        	
            }
      
      }
      
      function updateRow(status,para) {
            var form = new mini.Form("editForm1");
            var Cform=new mini.Form("checkoutdiv");
            var Cdata=Cform.getData();
             var orderId=mini.get("order_id").getValue();
             var checkoutId=mini.get("checksheet_id").getValue();
            Cdata.checkout_date=mini.get("checkout_date").getFormValue();
            
            var Cjson=mini.encode(Cdata);
            //grid.loading("保存中，请稍后......");
          Cform.validate();
          form.validate();
   		  if(form.isValid()==false||Cform.isValid()==false){
                return;
                
                }else{
                mini.get("update").disable();
                var stockNum=parseFloat(mini.get("stockNum").getValue());
                var checkoutNum=parseFloat(mini.get("checkoutNum").getValue());
                var alreadyPayNum=parseFloat(mini.get("alreadyPayNum").getValue());
                var purductNum=parseFloat(mini.get("purductNum").getValue());
                if(checkoutNum>stockNum){
                	alert("出库数量不能大于库存数量，请修改！");
                	mini.get("update").enable();
                }else if((checkoutNum+alreadyPayNum)>purductNum){
                	mini.confirm("出库数量不能大于订单数量，是否出库？","确定",
                	function (action){
                		if(action=="ok"){
                		//alert("你选择了是");
                		$.ajax({
            				type:"post",
               				url: "AddCheckoutServlet?status="+status,
                			data: {submitData: Cjson },
                			dataType:"json",
            				success: function(text){
            					if(para==3){
            					//3 表示是一键出库
            						//oneKeyOut();
            					}else{
            						save(form);
            					  }
     	   						}
     						});
     					}else{
     					alert("请重新填写出库数量！");
     					mini.get("update").enable();
     					}
   					});
                }else{
                	$.ajax({
           				type:"post",
              				url: "AddCheckoutServlet?status="+status,
               			data: {submitData: Cjson },
               			dataType:"json",
           				success: function(text){
           					if(para==3){
           						//oneKeyOut();
           					}else{
           						save(form);
           					  }
    	   						}
    						});
                }	
  
			}
		}
		
      
       function save(form){
          var data = form.getData();
          var json=mini.encode(data);
       	  var checksheet_id=mini.get("checksheet_id").getValue();
          var orderId=mini.get("order_id").getValue(); 
          var checkoutType=mini.get("checkoutType").getValue();
          
          var page=grid.getPageIndex();
          //var size=grid.getPageSize();
          $.ajax({
            		type:"post",
                	url: "saveCheckoutDetailServlet?checksheet_id="+checksheet_id+"&orderId="+orderId+"&checkoutType="+checkoutType,
                	data: {submitData: json },
                	dataType:"json",
               		success: function (data) { 
						alert(data.result);	
						var warehouseId=mini.get("warehouse_id").getValue();
						grid.load({orderId:orderId,checkoutId:checksheet_id,pageIndex:page,warehouseId:warehouseId});
						//grid.gotoPage(page,size);
						mini.get("update").enable();
               		 }
               		 	
           	 	}); 
      }
      
       function cancelRow() {
            grid.reload();
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
                            mini.get("connector").setValue(data.connector);
                            mini.get("connectortel").setValue(data.connectorTel); 
                        }
                    }

                }
            });
        }
      

   	  function onOrderButtonEdit(e) {
            var btnEdit = this;
            var companyId=mini.get("companyId").getValue();
            var companyName=mini.get("companyId").getText();
            var warehouseId=mini.get("warehouse_id").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Checkout/selectOrderIdWindow.jsp?companyId="+companyId+"&companyName="+companyName,
                title: "订单号列表",
                width: 1050,
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
                            //mini.get("companyId").setValue(data.customer);
                            //mini.get("companyId").setText(data.companyName);
                            
                           var orderId=mini.get("order_id").getValue();
                           var checkoutId=mini.get("checksheet_id").getValue();
                           // 订单状态 复位  禁用客户选择
                           mini.get("orderStatus").setValue("10");
                           mini.get("companyId").disable();
                			//var grid=mini.get("grid1");
   	 	 					grid.load({orderId:orderId,checkoutId:checkoutId,warehouseId:warehouseId});
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

	  function getCurrentTime(){
   		      var now=new Date();
   		      var year = now.getFullYear();
		      var month = (((now.getMonth()+1) < 10) ? "0" : "") + (now.getMonth()+1);
		      var day = ((now.getDate() < 10) ? "0" : "") + now.getDate();
   		       mini.get("checkout_date").setValue(year+"-"+month+"-"+day);
   		      }
   	     getCurrentTime();
 
   	 </script>
  </body>
</html>
