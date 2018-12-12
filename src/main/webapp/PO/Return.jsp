<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>采购退货</title>
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
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  
  <body>
  <div class="mini-toolbar">
  <a class="mini-button" iconCls="icon-print" plain="false"  onclick="print()">打印</a>
  <span class="separator"></span>
  <a class="mini-button" iconCls="icon-save" plain="false" onclick="getForm()">保存</a>
  <span class="separator"></span>
 <a class="mini-button" iconCls="icon-goto" plain="false" onclick="nextForm()">下一单</a>
  </div>
  <fieldset id="rediv" style="width: 100%;" align="center">
  <legend>采购退货单</legend>
  
  <form id="Resheet" name="Resheet" action="#" method="post">
  <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
  <tr bgcolor=#EBEFF5>
  <td ><label for="reDate$text">开单日期</label></td>
  <td><input id="reDate" name="reDate" class="mini-datepicker" required="true" dateFormat="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd" 
  showTodayButton="false" showClearButton="false" allowInput="false" width="100%"/></td>
  <td><label for="reSheetid$text">单号</label></td>
  <td><input id="reSheetid" name="reSheetid" class="mini-textbox" required="true" enabled="false" width="100%" value="${resheetid.sheetid }"></td>
  <td><label for="warehouseId$text">库房</label></td>
  <td><input id="warehouseId" name="warehouseId" class="mini-buttonedit" width="100%" textName="warehouseName" onbuttonclick="onButtonEditWarehouse" 
  allowInput="false" required="true"/></td>
  </tr>
  <tr bgcolor=#EBEFF5>
  <td><label for="customerId$text">供应商</label></td>
  <td><input id="customerId" name="customerId" class="mini-buttonedit" width="100%" onbuttonclick="onSupplierButtonEdit" textName="customerName" required="true" allowInput="false"/></td>
  <td><label for="connector$text">联系人</label></td>
  <td><input id="connector" name="connector" class="mini-textbox" required="true" width="100%"/></td>
  <td><label for="connectorTel$text">联系电话</label></td>
  <td><input id="connectorTel"  name="connectorTel" class="mini-textbox" required="true" width="100%" /></td>
   
  </tr>
   <tr bgcolor=#EBEFF5>
  <td><label for="telephone$text">传真号</label></td>
  <td><input id="telephone" name="telephone" class="mini-textbox" required="true" width="100%"/></td>
  <td><label for="reType$text">退款类型</label></td>
  <td><input id="reType" name="reType" class="mini-textbox" width="100%"/></td>   
  <td><label for="account$text">收款账号</label></td>
  <td><input id="account" name="account" class="mini-textbox" width="100%"/></td>
  </tr>
  <tr bgcolor=#EBEFF5>
  <td><label for="payMethod$text">收款方式</label></td>
  <td><input id="payMethod" name="payMethod" class="mini-combobox" style="width:100%" textField="text" valueField="id" emptyText="请选择..."
    url="GetpayMethodServlet" value="XZ" allowInput="true" showNullItem="true" nullItemText="请选择..."/></td>
  <td><label for="receipt$text">发票类型</label></td>
  <td><input id="receipt" name="receipt" class="mini-combobox" url="ReceiptServlet" style="width:100%" valueField="id" textField="text" 
   emptyText="请选择..." allowInput="false" value="O" showNullItem="true" nullItemText="请选择..."/></td> 
  </tr>
 
  </table>
  <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
  		<tr>
 		<!-- <td>序号</td> --><td>货品编码</td><td>货品名称</td><td>规格</td><td>单位</td><td>类型</td><td>数量</td><td>单价</td><td>货款</td><td>库位</td><td>备注</td>
   		</tr>
   		<tr height="20"><!-- <td><input id="seq1"  name="seq1" class="mini-textbox" value="1" width="100%"  /></td> --><td><input id="itemId1"  name="itemId1" class="mini-buttonedit" onbuttonclick="onButtonEdit1" textname="itemid_name1" allowinput="false" width="100%"/></td><td><input id="itemName1"  name="itemName1" class="mini-textbox"  width="100%" /></td><td><input id="spec1"  name="spec1" class="mini-textbox"  width="100%" /></td><td><input id="unit1"  name="unit1" class="mini-textbox"  width="100%" /></td><td><input id="kind1"  name="kind1" class="mini-combobox"  width="100%" textField="text" valueField="id" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="false" url="GetItemTypeServlet"/></td><td><input id="reNum1"  name="reNum1" class="mini-textbox"  width="100%" /></td><td><input id="unitPrice1"  name="unitPrice1" class="mini-textbox"  width="100%" /></td><td><input id="price1"  name="price1" class="mini-textbox"  width="100%" /></td><!-- <td><input id="taxrate1"  name="taxrate1" class="mini-textbox"  width="100%" /></td> <td><input id="tax1"  name="tax1" class="mini-textbox" width="100%" /></td>--><td><input id="stockId1"  name="stockId1" class="mini-textbox"  width="100%" /></td><td><input id="memo1"  name="memo1" class="mini-textbox"  width="100%" /></td></tr>
   		<tr height="20"><!-- <td><input id="seq2"  name="seq2" class="mini-textbox" value="2"  width="100%" /></td> --><td><input id="itemId2"  name="itemId2" class="mini-buttonedit" onbuttonclick="onButtonEdit2" textname="itemid_name2" allowinput="false" width="100%"/></td><td><input id="itemName2"  name="itemName2" class="mini-textbox"  width="100%" /></td><td><input id="spec2"  name="spec2" class="mini-textbox"  width="100%" /></td><td><input id="unit2"  name="unit2" class="mini-textbox"  width="100%" /></td><td><input id="kind2"  name="kind2" class="mini-combobox"  width="100%" textField="text" valueField="id" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="false" url="GetItemTypeServlet"/></td><td><input id="reNum2"  name="reNum2" class="mini-textbox"  width="100%" /></td><td><input id="unitPrice2"  name="unitPrice2" class="mini-textbox"  width="100%" /></td><td><input id="price2"  name="price2" class="mini-textbox"  width="100%" /></td><!-- <td><input id="taxrate2"  name="taxrate2" class="mini-textbox"  width="100%" /></td><td><input id="tax2"  name="tax2" class="mini-textbox"  width="100%" /></td>--><td><input id="stockId2"  name="stockId2" class="mini-textbox"  width="100%" /></td><td><input id="memo2"  name="memo2" class="mini-textbox"  width="100%" /></td></tr>
  		<tr height="20"><!-- <td><input id="seq3"  name="seq3" class="mini-textbox" value="3"  width="100%" /></td> --><td><input id="itemId3"  name="itemId3" class="mini-buttonedit" onbuttonclick="onButtonEdit3" textname="itemid_name3" allowinput="false" width="100%"/></td><td><input id="itemName3"  name="itemName3" class="mini-textbox"  width="100%" /></td><td><input id="spec3"  name="spec3" class="mini-textbox"  width="100%" /></td><td><input id="unit3"  name="unit3" class="mini-textbox"  width="100%" /></td><td><input id="kind3"  name="kind3" class="mini-combobox"  width="100%" textField="text" valueField="id" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="false" url="GetItemTypeServlet"/></td><td><input id="reNum3"  name="reNum3" class="mini-textbox"  width="100%" /></td><td><input id="unitPrice3"  name="unitPrice3" class="mini-textbox"  width="100%" /></td><td><input id="price3"  name="price3" class="mini-textbox"  width="100%" /></td><!-- <td><input id="taxrate3"  name="taxrate3" class="mini-textbox"  width="100%" /></td><td><input id="tax3"  name="tax3" class="mini-textbox"  width="100%" /></td>--><td><input id="stockId3"  name="stockId3" class="mini-textbox"  width="100%" /></td><td><input id="memo3"  name="memo3" class="mini-textbox"  width="100%" /></td></tr>
  		<tr height="20"><!-- <td><input id="seq4"  name="seq4" class="mini-textbox" value="4"  width="100%" /></td> --><td><input id="itemId4"  name="itemId4" class="mini-buttonedit" onbuttonclick="onButtonEdit4" textname="itemid_name4" allowinput="false" width="100%"/></td><td><input id="itemName4"  name="itemName4" class="mini-textbox"  width="100%" /></td><td><input id="spec4"  name="spec4" class="mini-textbox"  width="100%" /></td><td><input id="unit4"  name="unit4" class="mini-textbox"  width="100%" /></td><td><input id="kind4"  name="kind4" class="mini-combobox"  width="100%" textField="text" valueField="id" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="false" url="GetItemTypeServlet"/></td><td><input id="reNum4"  name="reNum4" class="mini-textbox"  width="100%" /></td><td><input id="unitPrice4"  name="unitPrice4" class="mini-textbox"  width="100%" /></td><td><input id="price4"  name="price4" class="mini-textbox"  width="100%" /></td><!-- <td><input id="taxrate4"  name="taxrate4" class="mini-textbox"  width="100%" /></td><td><input id="tax4"  name="tax4" class="mini-textbox"  width="100%" /></td>--><td><input id="stockId4"  name="stockId4" class="mini-textbox"  width="100%" /></td><td><input id="memo4"  name="memo4" class="mini-textbox"  width="100%" /></td></tr>
  		<tr height="20"><!-- <td><input id="seq5"  name="seq5" class="mini-textbox" value="5"  width="100%" /></td> --><td><input id="itemId5"  name="itemId5" class="mini-buttonedit" onbuttonclick="onButtonEdit5" textname="itemid_name5" allowinput="false" width="100%"/></td><td><input id="itemName5"  name="itemName5" class="mini-textbox"  width="100%" /></td><td><input id="spec5"  name="spec5" class="mini-textbox"  width="100%" /></td><td><input id="unit5"  name="unit5" class="mini-textbox"  width="100%" /></td><td><input id="kind5"  name="kind5" class="mini-combobox"  width="100%" textField="text" valueField="id" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="false" url="GetItemTypeServlet"/></td><td><input id="reNum5"  name="reNum5" class="mini-textbox"  width="100%" /></td><td><input id="unitPrice5"  name="unitPrice5" class="mini-textbox"  width="100%" /></td><td><input id="price5"  name="price5" class="mini-textbox"  width="100%" /></td><!-- <td><input id="taxrate5"  name="taxrate5" class="mini-textbox"  width="100%" /></td><td><input id="tax5"  name="tax5" class="mini-textbox"  width="100%" /></td>--><td><input id="stockId5"  name="stockId5" class="mini-textbox"  width="100%" /></td><td><input id="memo5"  name="memo5" class="mini-textbox"  width="100%" /></td></tr>
  		<tr height="20"><!-- <td><input id="seq6"  name="seq6" class="mini-textbox" value="6"  width="100%" /></td> --><td><input id="itemId6"  name="itemId6" class="mini-buttonedit" onbuttonclick="onButtonEdit6" textname="itemid_name6" allowinput="false" width="100%"/></td><td><input id="itemName6"  name="itemName6" class="mini-textbox"  width="100%" /></td><td><input id="spec6"  name="spec6" class="mini-textbox"  width="100%" /></td><td><input id="unit6"  name="unit6" class="mini-textbox"  width="100%" /></td><td><input id="kind6"  name="kind6" class="mini-combobox"  width="100%" textField="text" valueField="id" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="false" url="GetItemTypeServlet"/></td><td><input id="reNum6"  name="reNum6" class="mini-textbox"  width="100%" /></td><td><input id="unitPrice6"  name="unitPrice6" class="mini-textbox"  width="100%" /></td><td><input id="price6"  name="price6" class="mini-textbox"  width="100%" /></td><!-- <td><input id="taxrate6"  name="taxrate6" class="mini-textbox"  width="100%" /></td><td><input id="tax6"  name="tax6" class="mini-textbox"  width="100%" /></td>--><td><input id="stockId6"  name="stockId6" class="mini-textbox"  width="100%" /></td><td><input id="memo6"  name="memo6" class="mini-textbox"  width="100%" /></td></tr>
  		<tr height="20"><!-- <td><input id="seq7"  name="seq7" class="mini-textbox" value="7"  width="100%" /></td> --><td><input id="itemId7"  name="itemId7" class="mini-buttonedit" onbuttonclick="onButtonEdit7" textname="itemid_name7" allowinput="false" width="100%"/></td><td><input id="itemName7"  name="itemName7" class="mini-textbox"  width="100%" /></td><td><input id="spec7"  name="spec7" class="mini-textbox"  width="100%" /></td><td><input id="unit7"  name="unit7" class="mini-textbox"  width="100%" /></td><td><input id="kind7"  name="kind7" class="mini-combobox"  width="100%" textField="text" valueField="id" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="false" url="GetItemTypeServlet"/></td><td><input id="reNum7"  name="reNum7" class="mini-textbox"  width="100%" /></td><td><input id="unitPrice7"  name="unitPrice7" class="mini-textbox"  width="100%" /></td><td><input id="price7"  name="price7" class="mini-textbox"  width="100%" /></td><!-- <td><input id="taxrate7"  name="taxrate7" class="mini-textbox"  width="100%" /></td><td><input id="tax7"  name="tax7" class="mini-textbox"  width="100%" /></td>--><td><input id="stockId7"  name="stockId7" class="mini-textbox"  width="100%" /></td><td><input id="memo7"  name="memo7" class="mini-textbox"  width="100%" /></td></tr>
  		<tr height="20"><!-- <td><input id="seq8"  name="seq8" class="mini-textbox" value="8"  width="100%" /></td> --><td><input id="itemId8"  name="itemId8" class="mini-buttonedit" onbuttonclick="onButtonEdit8" textname="itemid_name8" allowinput="false" width="100%"/></td><td><input id="itemName8"  name="itemName8" class="mini-textbox"  width="100%" /></td><td><input id="spec8"  name="spec8" class="mini-textbox"  width="100%" /></td><td><input id="unit8"  name="unit8" class="mini-textbox"  width="100%" /></td><td><input id="kind8"  name="kind8" class="mini-combobox"  width="100%" textField="text" valueField="id" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="false" url="GetItemTypeServlet"/></td><td><input id="reNum8"  name="reNum8" class="mini-textbox"  width="100%" /></td><td><input id="unitPrice8"  name="unitPrice8" class="mini-textbox"  width="100%" /></td><td><input id="price8"  name="price8" class="mini-textbox"  width="100%" /></td><!-- <td><input id="taxrate8"  name="taxrate8" class="mini-textbox"  width="100%" /></td><td><input id="tax8"  name="tax8" class="mini-textbox"  width="100%" /></td>--><td><input id="stockId8"  name="stockId8" class="mini-textbox"  width="100%" /></td><td><input id="memo8"  name="memo8" class="mini-textbox"  width="100%" /></td></tr>
  		<tr height="20"><!-- <td><input id="seq9"  name="seq9" class="mini-textbox" value="9"  width="100%" /></td> --><td><input id="itemId9"  name="itemId9" class="mini-buttonedit" onbuttonclick="onButtonEdit9" textname="itemid_name9" allowinput="false" width="100%"/></td><td><input id="itemName9"  name="itemName9" class="mini-textbox"  width="100%" /></td><td><input id="spec9"  name="spec9" class="mini-textbox"  width="100%" /></td><td><input id="unit9"  name="unit9" class="mini-textbox"  width="100%" /></td><td><input id="kind9"  name="kind9" class="mini-combobox"  width="100%" textField="text" valueField="id" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="false" url="GetItemTypeServlet"/></td><td><input id="reNum9"  name="reNum9" class="mini-textbox"  width="100%" /></td><td><input id="unitPrice9"  name="unitPrice9" class="mini-textbox"  width="100%" /></td><td><input id="price9"  name="price9" class="mini-textbox"  width="100%" /></td><!-- <td><input id="taxrate9"  name="taxrate9" class="mini-textbox"  width="100%" /></td><td><input id="tax9"  name="tax9" class="mini-textbox"  width="100%" /></td>--><td><input id="stockId9"  name="stockId9" class="mini-textbox"  width="100%" /></td><td><input id="memo9"  name="memo9" class="mini-textbox"  width="100%" /></td></tr>
  		<tr height="20"><!-- <td><input id="seq10"  name="seq10" class="mini-textbox" value="10"  width="100%" /></td> --><td><input id="itemId10"  name="itemId10"class="mini-buttonedit" onbuttonclick="onButtonEdit10" textname="itemid_name10" allowinput="false" width="100%"/></td><td><input id="itemName10"  name="itemName10" class="mini-textbox"  width="100%" /></td><td><input id="spec10"  name="spec10" class="mini-textbox"  width="100%" /></td><td><input id="unit10"  name="unit10" class="mini-textbox"  width="100%" /></td><td><input id="kind10"  name="kind10" class="mini-combobox"  width="100%" textField="text" valueField="id" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="false" url="GetItemTypeServlet"/></td><td><input id="reNum10"  name="reNum10" class="mini-textbox"  width="100%" /></td><td><input id="unitPrice10"  name="unitPrice10" class="mini-textbox"  width="100%" /></td><td><input id="price10"  name="price10" class="mini-textbox"  width="100%" /></td><!-- <td><input id="taxrate10"  name="taxrate10" class="mini-textbox"  width="100%" /></td><td><input id="tax10"  name="tax10" class="mini-textbox"  width="100%" /></td>--><td><input id="stockId10"  name="stockId10" class="mini-textbox"  width="100%" /></td><td><input id="memo10"  name="memo10" class="mini-textbox" width="100%" /></td></tr>
  		</table>
  		
  <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
  <tr bgcolor=#EBEFF5>
  
  <td><label for="examineId$text">审核人</label></td>
  <td><input id="examineId" name="examineId"  class="mini-buttonedit" width="100%" textName="examineName" onbuttonclick="onButtonEditEmployee" allowInput="false" required="true"></td>
  <td><label for="adminId$text">仓管员</label><br></td>
  <td><input id="adminId" name="adminId"  class="mini-buttonedit" width="100%" textName="adminName" onbuttonclick="onButtonEditEmployee" allowInput="false" required="true"></td>
  <td><label for="salesmanId$text">业务员</label></td>
  <td><input id="salesmanId" name="salesmanId"  class="mini-buttonedit" width="100%" textName="salesmanName" onbuttonclick="onButtonEditEmployee" allowInput="false" required="true"></td>
  <td><label for="drawerId$text">开单人</label></td>
  <td><input id="drawerId" name="drawerId"  class="mini-buttonedit" width="100%" textName="drawerName" onbuttonclick="onButtonEditEmployee" allowInput="false" required="true"></td>
  </tr>
  </table>
  <input id="id" name="id" class="mini-hidden" value="${resheetid.id }"/>
  <input id="seq" name="seq" class="mini-hidden" value="${resheetid.seq }"/>
  </form>
  
  
  </fieldset>
  
  
  
  
  <script type="text/javascript">
  	mini.parse();
  	function getForm(){
  	var form=new mini.Form("Resheet");
  	var data=form.getData();
  	data.reDate=mini.get("reDate").getFormValue();
 	 var json=mini.encode(data);
  	form.validate();
    if (form.isValid() == false) {
          return;
   }else{
  	$.ajax({
  		type:"post",
  		url:"ReServlet",
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
                            mini.get("connector").setValue(data.connector);
                            mini.get("connectorTel").setValue(data.connectorTel); 
                            mini.get("telephone").setValue(data.telephone);
                       		//mini.get("dutyParagraph").setValue(data.dutyParagraph);
                       		//mini.get("bank").setValue(data.bank);
                       		mini.get("account").setValue(data.account);
                       
                       
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
   
   //物料号
    function onButtonEdit1(e) {
            var btnEdit = this;
            var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
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
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       	mini.get("itemName1").setValue(data.itemName);
                       	mini.get("kind1").setValue(data.itemType);
                       	mini.get("spec1").setValue(data.spec);
                       	mini.get("unit1").setValue(data.unit);
                       	mini.get("stockId1").setValue(data.stockId);
                       	mini.get("unitPrice1").setValue(data.unitPrice);
                        }
                    }

                }
            });
        }
    function onButtonEdit2(e) {
            var btnEdit = this;
            var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
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
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       	mini.get("itemName2").setValue(data.itemName);
                       	mini.get("kind2").setValue(data.itemType);
                       	mini.get("spec2").setValue(data.spec);
                       	mini.get("unit2").setValue(data.unit);
                       	mini.get("stockId2").setValue(data.stockId);
                       	mini.get("unitPrice2").setValue(data.unitPrice);
                        }
                    }

                }
            });
        }
         function onButtonEdit3(e) {
            var btnEdit = this;
            var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
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
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       	mini.get("itemName3").setValue(data.itemName);
                       	mini.get("kind3").setValue(data.itemType);
                       	mini.get("spec3").setValue(data.spec);
                       	mini.get("unit3").setValue(data.unit);
                       	mini.get("stockId3").setValue(data.stockId);
                       	mini.get("unitPrice3").setValue(data.unitPrice);
                        }
                    }

                }
            });
        }
         function onButtonEdit4(e) {
            var btnEdit = this;
            var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
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
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       	mini.get("itemName4").setValue(data.itemName);
                       	mini.get("kind4").setValue(data.itemType);
                       	mini.get("spec4").setValue(data.spec);
                       	mini.get("unit4").setValue(data.unit);
                       	mini.get("stockId4").setValue(data.stockId);
                       	mini.get("unitPrice4").setValue(data.unitPrice);
                        }
                    }

                }
            });
        }
         function onButtonEdit5(e) {
            var btnEdit = this;
            var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
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
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       	mini.get("itemName5").setValue(data.itemName);
                       	mini.get("kind5").setValue(data.itemType);
                       	mini.get("spec5").setValue(data.spec);
                       	mini.get("unit5").setValue(data.unit);
                       	mini.get("stockId5").setValue(data.stockId);
                       	mini.get("unitPrice5").setValue(data.unitPrice);
                        }
                    }

                }
            });
        }
         function onButtonEdit6(e) {
            var btnEdit = this;
            var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
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
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       	mini.get("itemName6").setValue(data.itemName);
                       	mini.get("kind6").setValue(data.itemType);
                       	mini.get("spec6").setValue(data.spec);
                       	mini.get("unit6").setValue(data.unit);
                       	mini.get("stockId6").setValue(data.stockId);
                       	mini.get("unitPrice6").setValue(data.unitPrice);
                        }
                    }

                }
            });
        }
         function onButtonEdit7(e) {
           var btnEdit = this;
            var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
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
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       	mini.get("itemName7").setValue(data.itemName);
                       	mini.get("kind7").setValue(data.itemType);
                       	mini.get("spec7").setValue(data.spec);
                       	mini.get("unit7").setValue(data.unit);
                       	mini.get("stockId7").setValue(data.stockId);
                       	mini.get("unitPrice7").setValue(data.unitPrice);
                        }
                    }

                }
            });
        }
         function onButtonEdit8(e) {
            var btnEdit = this;
            var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
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
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       	mini.get("itemName8").setValue(data.itemName);
                       	mini.get("kind8").setValue(data.itemType);
                       	mini.get("spec8").setValue(data.spec);
                       	mini.get("unit8").setValue(data.unit);
                       	mini.get("stockId8").setValue(data.stockId);
                       	mini.get("unitPrice8").setValue(data.unitPrice);
                        }
                    }

                }
            });
        }
         function onButtonEdit9(e) {
            var btnEdit = this;
            var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
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
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       	mini.get("itemName9").setValue(data.itemName);
                       	mini.get("kind9").setValue(data.itemType);
                       	mini.get("spec9").setValue(data.spec);
                       	mini.get("unit9").setValue(data.unit);
                       	mini.get("stockId9").setValue(data.stockId);
                       	mini.get("unitPrice9").setValue(data.unitPrice);
                        }
                    }

                }
            });
        }
        
        function onButtonEdit10(e) {
         var btnEdit = this;
            var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
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
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                       	mini.get("itemName10").setValue(data.itemName);
                       	mini.get("kind10").setValue(data.itemType);
                       	mini.get("spec10").setValue(data.spec);
                       	mini.get("unit10").setValue(data.unit);
                       	mini.get("stockId10").setValue(data.stockId);
                       	mini.get("unitPrice10").setValue(data.unitPrice);
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
   		       mini.get("reDate").setValue(year+"-"+month+"-"+day);
   		      }
   	     getCurrentTime();
        
 </script>
  </body>
</html>
