 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>产品进库</title>
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
  		<a class="mini-button" iconCls="icon-print" plain="false"  onclick="print()">打印入库单</a>
  		<span class="separator"></span>
  		<a class="mini-button" iconCls="icon-save" plain="false" onclick="getForm()">保存</a>
  		<span class="separator"></span>
  		<a class="mini-button" plain="false" iconCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a>
  		<span class="separator"></span>
 		<a class="mini-button" iconCls="icon-goto" plain="false" onclick="nextForm()">下一单</a>
  
  	</div>
  	<!--startprint-->
  	<fieldset id="allDetail" style="width: 100%;" align="center">
		<legend>
			入库单信息
		</legend>
		
		<form id= "checkindiv" name="checkindiv" action="#" method="post">
   		<table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="99%" >
   		<tr bgcolor="#a4c2f4">
   			<td width="10%"><label for="checkin_date$text">日期</label></td>
            <td width="23%"><input id="checkin_date"  name="checkin_date" class="mini-datepicker"  width="100%" required="true"  showTodayButton="false"  dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false" showOkButton="false" showClearButton="false" /></td>
   			<td width="10%"><label for="checksheet_id$text">单号</label></td>
            <td width="23%"><input id="checksheet_id"  name="checksheet_id" class="mini-textbox"  width="100%" required="true" enabled="false" value="${checksheet_id.sheetid}"/></td>
   			<td><label for="warehouse_id$text">库房</label></td>
			<td><input id="warehouse_id" name="warehouse_id" class="mini-buttonedit" width="100%" textName="warehouse_name" onbuttonclick="onButtonEditWarehouse" 
  			allowInput="false" required="true"/></td>	
   			 
   			
       	
        </tr>
       	<tr bgcolor="#a4c2f4">
       		<td><label for="checkin_kind$text">入库类型</label></td>
            <td><input id="checkin_kind"  name="checkin_kind" class="mini-textbox" required="true" width="100%" /></td>
<!--           <td width="10%"><label for="company_id$text">供应商</label></td>-->
<!--           <td width="30%"><input id="company_id"  name="company_id" class="mini-buttonedit"  style="width:100%;" textName="company_name" onbuttonclick="onSupplierButtonEdit" /></td>-->
   		   <td><label for="deptid$text">部门</label></td>
           <td><input id="deptid" name="deptid" class="mini-buttonedit" width="100%" textName="deptname" onbuttonclick="onButtonEditDept" 
  		allowInput="false" /></td>
   			
        </tr>
	</table>
   		<table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
   		<tr height="25">
   		<td width="5%">序列号</td><td>货品编码</td><td>货品名称</td><td>订单号</td><td>版本号</td><td>图号</td><td>规格</td><td>单位</td><td>数量</td><td>单价</td><td>金额</td><td>库位</td><td>批次</td><td>质编号</td><td>备注</td>
   		</tr>
   		<tr height="20"><td><input id="checkindetl_id1"  name="checkindetl_id1" class="mini-textbox"  width="100%" value="1"/></td><td><input id="item_id1"  name="item_id1"  class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit1" textName="itemid_name1"  allowInput="true"/></td><td><input id="item_name1"  name="item_name1" class="mini-textbox"  width="100%" /></td><td><input id="order_id1"  name="order_id1" class="mini-textbox"  width="100%" /></td><td><input width="100%" id="issue_num1" name="issue_num1" class="mini-textbox"></td><td><input id="drawingId1"  name="drawingId1" class="mini-textbox"  width="100%" /></td><td><input id="spec1"  name="spec1" class="mini-textbox"  width="100%" /></td><td><input id="unit1"  name="unit1" class="mini-textbox"  width="100%" /></td><td><input id="checkin_num1"  name="checkin_num1" class="mini-textbox"  width="100%" /></td><td><input id="unitprice1"  name="unitprice1" class="mini-textbox"  width="100%" /></td><td><input id="price1"  name="price1" class="mini-textbox"  width="100%" /></td><!--<td><input id="warehouse_id1"  name="warehouse_id1" class="mini-textbox"  width="100%" /></td>--><td><input id="stock_id1"  name="stock_id1" class="mini-textbox"  width="100%" /></td><td><input id="lot1"  name="lot1" class="mini-textbox"  width="100%" /></td><td><input id="quality_id1"  name="quality_id1" class="mini-textbox"  width="100%" /></td><td><input id="memo1"  name="memo1" class="mini-textbox"  width="100%" /></td></tr>
   		<tr height="20"><td><input id="checkindetl_id2"  name="checkindetl_id2" class="mini-textbox"  width="100%" value="2"/></td><td><input id="item_id2"  name="item_id2"  class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit2" textName="itemid_name2"  allowInput="true"/></td><td><input id="item_name2"  name="item_name2" class="mini-textbox"  width="100%" /></td><td><input id="order_id2"  name="order_id2" class="mini-textbox"  width="100%" /></td><td><input width="100%" id="issue_num2" name="issue_num2" class="mini-textbox"></td><td><input id="drawingId2"  name="drawingId2" class="mini-textbox"  width="100%" /></td><td><input id="spec2"  name="spec2" class="mini-textbox"  width="100%" /></td><td><input id="unit2"  name="unit2" class="mini-textbox"  width="100%" /></td><td><input id="checkin_num2"  name="checkin_num2" class="mini-textbox"  width="100%" /></td><td><input id="unitprice2"  name="unitprice2" class="mini-textbox"  width="100%" /></td><td><input id="price2"  name="price2" class="mini-textbox"  width="100%" /></td><!--<td><input id="warehouse_id2"  name="warehouse_id2" class="mini-textbox"  width="100%" /></td>--><td><input id="stock_id2"  name="stock_id2" class="mini-textbox"  width="100%" /></td><td><input id="lot2"  name="lot2" class="mini-textbox"  width="100%" /></td><td><input id="quality_id2"  name="quality_id2" class="mini-textbox"  width="100%" /></td><td><input id="memo2"  name="memo2" class="mini-textbox"  width="100%" /></td></tr>
  		<tr height="20"><td><input id="checkindetl_id3"  name="checkindetl_id3" class="mini-textbox"  width="100%" value="3"/></td><td><input id="item_id3"  name="item_id3"  class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit3" textName="itemid_name3"  allowInput="true"/></td><td><input id="item_name3"  name="item_name3" class="mini-textbox"  width="100%" /></td><td><input id="order_id3"  name="order_id3" class="mini-textbox"  width="100%" /></td><td><input width="100%" id="issue_num3" name="issue_num3" class="mini-textbox"></td><td><input id="drawingId3"  name="drawingId3" class="mini-textbox"  width="100%" /></td><td><input id="spec3"  name="spec3" class="mini-textbox"  width="100%" /></td><td><input id="unit3"  name="unit3" class="mini-textbox"  width="100%" /></td><td><input id="checkin_num3"  name="checkin_num3" class="mini-textbox"  width="100%" /></td><td><input id="unitprice3"  name="unitprice3" class="mini-textbox"  width="100%" /></td><td><input id="price3"  name="price3" class="mini-textbox"  width="100%" /></td><!--<td><input id="warehouse_id3"  name="warehouse_id3" class="mini-textbox"  width="100%" /></td>--><td><input id="stock_id3"  name="stock_id3" class="mini-textbox"  width="100%" /></td><td><input id="lot3"  name="lot3" class="mini-textbox"  width="100%" /></td><td><input id="quality_id3"  name="quality_id3" class="mini-textbox"  width="100%" /></td><td><input id="memo3"  name="memo3" class="mini-textbox"  width="100%" /></td></tr>
  		<tr height="20"><td><input id="checkindetl_id4"  name="checkindetl_id4" class="mini-textbox"  width="100%" value="4"/></td><td><input id="item_id4"  name="item_id4"  class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit4" textName="itemid_name4"  allowInput="true"/></td><td><input id="item_name4"  name="item_name4" class="mini-textbox"  width="100%" /></td><td><input id="order_id4"  name="order_id4" class="mini-textbox"  width="100%" /></td><td><input width="100%" id="issue_num4" name="issue_num4" class="mini-textbox"></td><td><input id="drawingId4"  name="drawingId4" class="mini-textbox"  width="100%" /></td><td><input id="spec4"  name="spec4" class="mini-textbox"  width="100%" /></td><td><input id="unit4"  name="unit4" class="mini-textbox"  width="100%" /></td><td><input id="checkin_num4"  name="checkin_num4" class="mini-textbox"  width="100%" /></td><td><input id="unitprice4"  name="unitprice4" class="mini-textbox"  width="100%" /></td><td><input id="price4"  name="price4" class="mini-textbox"  width="100%" /></td><!--<td><input id="warehouse_id4"  name="warehouse_id4" class="mini-textbox"  width="100%" /></td>--><td><input id="stock_id4"  name="stock_id4" class="mini-textbox"  width="100%" /></td><td><input id="lot4"  name="lot4" class="mini-textbox"  width="100%" /></td><td><input id="quality_id4"  name="quality_id4" class="mini-textbox"  width="100%" /></td><td><input id="memo4"  name="memo4" class="mini-textbox"  width="100%" /></td></tr>
  		<tr height="20"><td><input id="checkindetl_id5"  name="checkindetl_id5" class="mini-textbox"  width="100%" value="5"/></td><td><input id="item_id5"  name="item_id5"  class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit5" textName="itemid_name5"  allowInput="true"/></td><td><input id="item_name5"  name="item_name5" class="mini-textbox"  width="100%" /></td><td><input id="order_id5"  name="order_id5" class="mini-textbox"  width="100%" /></td><td><input width="100%" id="issue_num5" name="issue_num5" class="mini-textbox"></td><td><input id="drawingId5"  name="drawingId5" class="mini-textbox"  width="100%" /></td><td><input id="spec5"  name="spec5" class="mini-textbox"  width="100%" /></td><td><input id="unit5"  name="unit5" class="mini-textbox"  width="100%" /></td><td><input id="checkin_num5"  name="checkin_num5" class="mini-textbox"  width="100%" /></td><td><input id="unitprice5"  name="unitprice5" class="mini-textbox"  width="100%" /></td><td><input id="price5"  name="price5" class="mini-textbox"  width="100%" /></td><!--<td><input id="warehouse_id5"  name="warehouse_id5" class="mini-textbox"  width="100%" /></td>--><td><input id="stock_id5"  name="stock_id5" class="mini-textbox"  width="100%" /></td><td><input id="lot5"  name="lot5" class="mini-textbox"  width="100%" /></td><td><input id="quality_id5"  name="quality_id5" class="mini-textbox"  width="100%" /></td><td><input id="memo5"  name="memo5" class="mini-textbox"  width="100%" /></td></tr>
  		<tr height="20"><td><input id="checkindetl_id6"  name="checkindetl_id6" class="mini-textbox"  width="100%" value="6"/></td><td><input id="item_id6"  name="item_id6"  class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit6" textName="itemid_name6"  allowInput="true"/></td><td><input id="item_name6"  name="item_name6" class="mini-textbox"  width="100%" /></td><td><input id="order_id6"  name="order_id6" class="mini-textbox"  width="100%" /></td><td><input width="100%" id="issue_num6" name="issue_num6" class="mini-textbox"></td><td><input id="drawingId6"  name="drawingId6" class="mini-textbox"  width="100%" /></td><td><input id="spec6"  name="spec6" class="mini-textbox"  width="100%" /></td><td><input id="unit6"  name="unit6" class="mini-textbox"  width="100%" /></td><td><input id="checkin_num6"  name="checkin_num6" class="mini-textbox"  width="100%" /></td><td><input id="unitprice6"  name="unitprice6" class="mini-textbox"  width="100%" /></td><td><input id="price6"  name="price6" class="mini-textbox"  width="100%" /></td><!--<td><input id="warehouse_id6"  name="warehouse_id6" class="mini-textbox"  width="100%" /></td>--><td><input id="stock_id6"  name="stock_id6" class="mini-textbox"  width="100%" /></td><td><input id="lot6"  name="lot6" class="mini-textbox"  width="100%" /></td><td><input id="quality_id6"  name="quality_id6" class="mini-textbox"  width="100%" /></td><td><input id="memo6"  name="memo6" class="mini-textbox"  width="100%" /></td></tr>
  		<tr height="20"><td><input id="checkindetl_id4"  name="checkindetl_id4" class="mini-textbox"  width="100%" value="7"/></td><td><input id="item_id4"  name="item_id7"  class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit7" textName="itemid_name7"  allowInput="true"/></td><td><input id="item_name7"  name="item_name7" class="mini-textbox"  width="100%" /></td><td><input id="order_id7"  name="order_id7" class="mini-textbox"  width="100%" /></td><td><input width="100%" id="issue_num7" name="issue_num7" class="mini-textbox"></td><td><input id="drawingId7"  name="drawingId7" class="mini-textbox"  width="100%" /></td><td><input id="spec7"  name="spec7" class="mini-textbox"  width="100%" /></td><td><input id="unit7"  name="unit7" class="mini-textbox"  width="100%" /></td><td><input id="checkin_num7"  name="checkin_num7" class="mini-textbox"  width="100%" /></td><td><input id="unitprice7"  name="unitprice7" class="mini-textbox"  width="100%" /></td><td><input id="price7"  name="price7" class="mini-textbox"  width="100%" /></td><!--<td><input id="warehouse_id4"  name="warehouse_id4" class="mini-textbox"  width="100%" /></td>--><td><input id="stock_id7"  name="stock_id7" class="mini-textbox"  width="100%" /></td><td><input id="lot7"  name="lot7" class="mini-textbox"  width="100%" /></td><td><input id="quality_id7"  name="quality_id7" class="mini-textbox"  width="100%" /></td><td><input id="memo7"  name="memo7" class="mini-textbox"  width="100%" /></td></tr>
  		<tr height="20"><td><input id="checkindetl_id5"  name="checkindetl_id5" class="mini-textbox"  width="100%" value="8"/></td><td><input id="item_id5"  name="item_id8"  class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit8" textName="itemid_name8"  allowInput="true"/></td><td><input id="item_name8"  name="item_name8" class="mini-textbox"  width="100%" /></td><td><input id="order_id8"  name="order_id8" class="mini-textbox"  width="100%" /></td><td><input width="100%" id="issue_num8" name="issue_num8" class="mini-textbox"></td><td><input id="drawingId8"  name="drawingId8" class="mini-textbox"  width="100%" /></td><td><input id="spec8"  name="spec8" class="mini-textbox"  width="100%" /></td><td><input id="unit8"  name="unit8" class="mini-textbox"  width="100%" /></td><td><input id="checkin_num8"  name="checkin_num8" class="mini-textbox"  width="100%" /></td><td><input id="unitprice8"  name="unitprice8" class="mini-textbox"  width="100%" /></td><td><input id="price8"  name="price8" class="mini-textbox"  width="100%" /></td><!--<td><input id="warehouse_id5"  name="warehouse_id5" class="mini-textbox"  width="100%" /></td>--><td><input id="stock_id8"  name="stock_id8" class="mini-textbox"  width="100%" /></td><td><input id="lot8"  name="lot8" class="mini-textbox"  width="100%" /></td><td><input id="quality_id8"  name="quality_id8" class="mini-textbox"  width="100%" /></td><td><input id="memo8"  name="memo8" class="mini-textbox"  width="100%" /></td></tr>
  		<tr height="20"><td><input id="checkindetl_id6"  name="checkindetl_id6" class="mini-textbox"  width="100%" value="9"/></td><td><input id="item_id6"  name="item_id9"  class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit9" textName="itemid_name9"  allowInput="true"/></td><td><input id="item_name9"  name="item_name9" class="mini-textbox"  width="100%" /></td><td><input id="order_id9"  name="order_id9" class="mini-textbox"  width="100%" /></td><td><input width="100%" id="issue_num9" name="issue_num9" class="mini-textbox"></td><td><input id="drawingId9"  name="drawingId9" class="mini-textbox"  width="100%" /></td><td><input id="spec9"  name="spec9" class="mini-textbox"  width="100%" /></td><td><input id="unit9"  name="unit9" class="mini-textbox"  width="100%" /></td><td><input id="checkin_num9"  name="checkin_num8" class="mini-textbox"  width="100%" /></td><td><input id="unitprice9"  name="unitprice9" class="mini-textbox"  width="100%" /></td><td><input id="price9"  name="price9" class="mini-textbox"  width="100%" /></td><!--<td><input id="warehouse_id6"  name="warehouse_id6" class="mini-textbox"  width="100%" /></td>--><td><input id="stock_id9"  name="stock_id9" class="mini-textbox"  width="100%" /></td><td><input id="lot9"  name="lot9" class="mini-textbox"  width="100%" /></td><td><input id="quality_id9"  name="quality_id9" class="mini-textbox"  width="100%" /></td><td><input id="memo9"  name="memo9" class="mini-textbox"  width="100%" /></td></tr>
  		<tr height="20"><td><input id="checkindetl_id6"  name="checkindetl_id6" class="mini-textbox"  width="100%" value="10"/></td><td><input id="item_id6"  name="item_id10"  class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit10" textName="itemid_name10"  allowInput="true"/></td><td><input id="item_name10"  name="item_name10" class="mini-textbox"  width="100%" /></td><td><input id="order_id10"  name="order_id10" class="mini-textbox"  width="100%" /></td><td><input width="100%" id="issue_num10" name="issue_num10" class="mini-textbox"></td><td><input id="drawingId10"  name="drawingId10" class="mini-textbox"  width="100%" /></td><td><input id="spec10"  name="spec10" class="mini-textbox"  width="100%" /></td><td><input id="unit10"  name="unit10" class="mini-textbox"  width="100%" /></td><td><input id="checkin_num10"  name="checkin_num10" class="mini-textbox"  width="100%" /></td><td><input id="unitprice10"  name="unitprice10" class="mini-textbox"  width="100%" /></td><td><input id="price10"  name="price10" class="mini-textbox"  width="100%" /></td><!--<td><input id="warehouse_id6"  name="warehouse_id6" class="mini-textbox"  width="100%" /></td>--><td><input id="stock_id10"  name="stock_id10" class="mini-textbox"  width="100%" /></td><td><input id="lot10"  name="lot10" class="mini-textbox"  width="100%" /></td><td><input id="quality_id10"  name="quality_id10" class="mini-textbox"  width="100%" /></td><td><input id="memo10"  name="memo10" class="mini-textbox"  width="100%" /></td></tr>

  		</table>
  		<input id="id" name="id" class="mini-hidden" required="true" value="${checksheet_id.id}"/>
        <input id="seq" name="seq" class="mini-hidden" required="true" value="${checksheet_id.seq}"/>	
   	 
   	 <table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="99%" >
   		 <tr  bgcolor="#a4c2f4">
       	 <td><label for="delivery$text">交货人</label></td>
         <td><input id="delivery"  name="delivery"  class="mini-buttonedit" width="100%" textName="delivery_name" onbuttonclick="onButtonEditEmployee" 
         allowInput="false" required="true"/>  
         </td>
       	 <td><label for="examine$text">审核人</label></td>
         <td><input id="examine"  name="examine"  class="mini-buttonedit" width="100%" textName="examine_name" onbuttonclick="onButtonEditEmployee" 
         allowInput="false" required="true"/> </td>
         <td><label for="admin$text">仓库管理员</label></td>
         <td><input id="admin"  name="admin"  class="mini-buttonedit" width="100%" textName="admin_name" onbuttonclick="onButtonEditEmployee" 
         allowInput="false" required="true"/>  
         </td>
   		 </tr>
   	 
   	 
   	 
   	 </table>
   	 
   	 
   	 
   	 </form>
   	 </fieldset>
   	 <script type="text/javascript">
   	 	mini.parse(); 
	    function getForm(){
 		  
 		  	var form = new mini.Form("#checkindiv");
 		  	var data=form.getData();
 		  	data.checkin_date=mini.get("checkin_date").getFormValue();
 		  	var json=mini.encode(data);
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
 		  		$.ajax({
      				type: "POST",
      				url: "AddCheckinServlet", 
      				dataType: "json",  				
      				//cache: false,
      				//enctype: 'multipart/form-data',
      				data: { submitData: json },
      				//processData: false,
    				//contentType: false,
      				success: function (data) {
        				alert(data.result);
        				window.location.href = window.location.href;
      				}
      				
    			});
    		}
   		}
   		
   	function nextForm(){
    window.location.href=window.location.href;
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
   
   
   function onButtonEditDept(e){
   		var btnEdit = this;
            mini.open({
                url: "deptManage/selectDeptTreeWindow.jsp",
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
                            btnEdit.setValue(data.deptId);
                            btnEdit.setText(data.deptName);
                            //mini.get("connector").setValue(data.connector);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                        }
                    }
                }
            });
   
   
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
                           
                          
                       
                       
                        }
                    }

                }
            });
        }
   
   	 function onButtonEdit1(e) {
             var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectItemWindow.jsp",
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
                            btnEdit.setValue(data.itemid);
                            btnEdit.setText(data.itemid);
                            //mini.get("item_id1").setText(data.itemid);
                            //mini.get("FItemId").setValue(data.itemid);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                       	mini.get("item_name1").setValue(data.itemname);
                       	mini.get("drawingId1").setValue(data.itemdrawing);
                        mini.get("order_id1").setValue(data.orderid);
                        mini.get("issue_num1").setValue(data.issuenum);
                       	mini.get("spec1").setValue(data.itemspecification);
                       	mini.get("unit1").setValue(data.unitm);
                       	mini.get("unitprice1").setValue(data.itemprice);
                      
                        }
                    }

                }
            });
        }
        
        function onButtonEdit2(e) {
            var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectItemWindow.jsp",
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
                            btnEdit.setValue(data.itemid);
                            btnEdit.setText(data.itemid);
                            //mini.get("item_id1").setText(data.itemid);
                            //mini.get("FItemId").setValue(data.itemid);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                       	mini.get("item_name2").setValue(data.itemname);
                       	mini.get("drawingId2").setValue(data.itemdrawing);
                       	mini.get("order_id2").setValue(data.orderid);
                        mini.get("issue_num2").setValue(data.issuenum);
                       	mini.get("spec2").setValue(data.itemspecification);
                        mini.get("unit2").setValue(data.unitm);
                       	mini.get("unitprice2").setValue(data.itemprice);
                       
                        }
                    }

                }
            });
        }
   	 
   	 function onButtonEdit3(e) {
            var btnEdit = this;
             mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectItemWindow.jsp",
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
                            btnEdit.setValue(data.itemid);
                            btnEdit.setText(data.itemid);
                            //mini.get("item_id1").setText(data.itemid);
                            //mini.get("FItemId").setValue(data.itemid);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                       	mini.get("item_name3").setValue(data.itemname);
                       	mini.get("drawingId3").setValue(data.itemdrawing);
                        mini.get("order_id3").setValue(data.orderid);
                        mini.get("issue_num3").setValue(data.issuenum);
                       	mini.get("spec3").setValue(data.itemspecification);
                       	mini.get("unit3").setValue(data.unitm);
                       	mini.get("unitprice3").setValue(data.itemprice);
                      
                        }
                    }

                }
            });
        }
   	 
   	 
   	 function onButtonEdit4(e) {
            var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectItemWindow.jsp",
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
                            btnEdit.setValue(data.itemid);
                            btnEdit.setText(data.itemid);
                            //mini.get("item_id1").setText(data.itemid);
                            //mini.get("FItemId").setValue(data.itemid);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                       	mini.get("item_name4").setValue(data.itemname);
                       	mini.get("drawingId4").setValue(data.itemdrawing);
                        mini.get("order_id4").setValue(data.orderid);
                        mini.get("issue_num4").setValue(data.issuenum);
                       	mini.get("spec4").setValue(data.itemspecification);
                       	mini.get("unit4").setValue(data.unitm);
                       	mini.get("unitprice4").setValue(data.itemprice);
                      
                        }
                    }

                }
            });
        }
   	 
   	 function onButtonEdit5(e) {
            var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectItemWindow.jsp",
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
                            btnEdit.setValue(data.itemid);
                            btnEdit.setText(data.itemid);
                            //mini.get("item_id1").setText(data.itemid);
                            //mini.get("FItemId").setValue(data.itemid);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                       	mini.get("item_name5").setValue(data.itemname);
                       	mini.get("drawingId5").setValue(data.itemdrawing);
                        mini.get("order_id5").setValue(data.orderid);
                        mini.get("issue_num5").setValue(data.issuenum);
                       	mini.get("spec5").setValue(data.itemspecification);
                       	mini.get("unit5").setValue(data.unitm);
                       	mini.get("unitprice5").setValue(data.itemprice);
                      
                        }
                    }

                }
            });
        }
   	 
   	 
   	 function onButtonEdit6(e) {
            var btnEdit = this;
             mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectItemWindow.jsp",
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
                            btnEdit.setValue(data.itemid);
                            btnEdit.setText(data.itemid);
                            //mini.get("item_id1").setText(data.itemid);
                            //mini.get("FItemId").setValue(data.itemid);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                       	mini.get("item_name6").setValue(data.itemname);
                       	mini.get("drawingId6").setValue(data.itemdrawing);
                        mini.get("order_id6").setValue(data.orderid);
                        mini.get("issue_num6").setValue(data.issuenum);
                       	mini.get("spec6").setValue(data.itemspecification);
                       	mini.get("unit6").setValue(data.unitm);
                       	mini.get("unitprice6").setValue(data.itemprice);
                      
                        }
                    }

                }
            });
        }
   	 
   	 function onButtonEdit7(e) {
            var btnEdit = this;
             mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectItemWindow.jsp",
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
                            btnEdit.setValue(data.itemid);
                            btnEdit.setText(data.itemid);
                            //mini.get("item_id1").setText(data.itemid);
                            //mini.get("FItemId").setValue(data.itemid);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                       	mini.get("item_name7").setValue(data.itemname);
                       	mini.get("drawingId7").setValue(data.itemdrawing);
                        mini.get("order_id7").setValue(data.orderid);
                        mini.get("issue_num7").setValue(data.issuenum);
                       	mini.get("spec7").setValue(data.itemspecification);
                       	mini.get("unit7").setValue(data.unitm);
                       	mini.get("unitprice7").setValue(data.itemprice);
                      
                        }
                    }

                }
            });
        }
        
        function onButtonEdit8(e) {
            var btnEdit = this;
             mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectItemWindow.jsp",
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
                            btnEdit.setValue(data.itemid);
                            btnEdit.setText(data.itemid);
                            //mini.get("item_id1").setText(data.itemid);
                            //mini.get("FItemId").setValue(data.itemid);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                       	mini.get("item_name8").setValue(data.itemname);
                       	mini.get("drawingId8").setValue(data.itemdrawing);
                        mini.get("order_id8").setValue(data.orderid);
                        mini.get("issue_num8").setValue(data.issuenum);
                       	mini.get("spec8").setValue(data.itemspecification);
                       	mini.get("unit8").setValue(data.unitm);
                       	mini.get("unitprice8").setValue(data.itemprice);
                      
                        }
                    }

                }
            });
        }
        
        function onButtonEdit9(e) {
            var btnEdit = this;
             mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectItemWindow.jsp",
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
                            btnEdit.setValue(data.itemid);
                            btnEdit.setText(data.itemid);
                            //mini.get("item_id1").setText(data.itemid);
                            //mini.get("FItemId").setValue(data.itemid);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                       	mini.get("item_name9").setValue(data.itemname);
                       	mini.get("drawingId9").setValue(data.itemdrawing);
                        mini.get("order_id9").setValue(data.orderid);
                        mini.get("issue_num9").setValue(data.issuenum);
                       	mini.get("spec9").setValue(data.itemspecification);
                       	mini.get("unit9").setValue(data.unitm);
                       	mini.get("unitprice9").setValue(data.itemprice);
                      
                        }
                    }

                }
            });
        }
        
        function onButtonEdit10(e) {
            var btnEdit = this;
             mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectItemWindow.jsp",
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
                            btnEdit.setValue(data.itemid);
                            btnEdit.setText(data.itemid);
                            //mini.get("item_id1").setText(data.itemid);
                            //mini.get("FItemId").setValue(data.itemid);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                       	mini.get("item_name10").setValue(data.itemname);
                       	mini.get("drawingId10").setValue(data.itemdrawing);
                        mini.get("order_id10").setValue(data.orderid);
                        mini.get("issue_num10").setValue(data.issuenum);
                       	mini.get("spec10").setValue(data.itemspecification);
                       	mini.get("unit10").setValue(data.unitm);
                       	mini.get("unitprice10").setValue(data.itemprice);
                      
                        }
                    }

                }
            });
        }
   	 
   	 
   	  
        
        
         
   		function onIDCardsValidation(e) {
            if (e.isValid) {
                var pattern = /\d*/;
                if (e.value.length < 15 || e.value.length > 18 || pattern.test(e.value) == false) {
                    e.errorText = "必须输入15~18位数字";
                    e.isValid = false;
                }
            }
        }

   		//验证QQ号码5-11位
   		function isQQ(e) {
   			if (e.isValid) {
                var pattern = /^\s*[.0-9]{5,11}\s*$/;
                if (!pattern.test(e.value)) {
                    e.errorText = "必须输入正确QQ号";
                    e.isValid = false;
                }
            }
   		}

   		//校验手机号码
   		function isMobile(e) {
   		    //var patrn = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
   		    if (e.isValid) {
   		    	var pattern = /^(13[0-9]{9})|(14[0-9])|(18[0-9])|(15[0-9][0-9]{8})$/;
                if (!pattern.exec(e.value)) {
                    e.errorText = "必须输入正确手机号码";
                    e.isValid = false;
                }
            }
   		}

   		function isTelephone(e){
   			if (e.isValid) {
   				var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
   				var isMobile = /^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/
                if (!isPhone.exec(e.value)&&!isMobile.exec(e.value)) {
                    e.errorText = "必须输入正确电话号码";
                    e.isValid = false;
                }
            }
   			
   	   	}

   		function isOnlyTelephone(e){
   			if (e.isValid) {
   				var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
   				if (!isPhone.exec(e.value)) {
                    e.errorText = "必须输入正确电话号码";
                    e.isValid = false;
                }
            }
   			
   	   	}
   	 </script>
  </body>
</html>
