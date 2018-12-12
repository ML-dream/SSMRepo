<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>采购申请</title>
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
  <a class="mini-button" iconCls="icon-print" plain="false"  onclick="print()">打印申请单</a>
  <span class="separator"></span>
  <a class="mini-button" iconCls="icon-save" plain="false" onclick="getForm()">提交申请</a>
  <span class="separator"></span>
  <a class="mini-button" iconCls="icon-goto" plain="false" onclick="nextForm()">下一单</a>
  </div>
 
  
  
  <div style="width:99%; margin:0 auto; border:2px solid #6D6D6D;">
  <br/>
  <h1>采购申请单</h1><br/>
  <form id="applysheet" name="applysheet" action="PoServlet" method="post">
  <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
  <tr bgcolor=#EBEFF5>
  <td ><label for="applyDate$text">开单日期</label></td>
  <td><input id="applyDate" name="applyDate" class="mini-datepicker" required="true" dateFormat="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd" 
  showTodayButton="false" showClearButton="false" allowInput="false" width="100%"/></td>
  <td><label for="applySheetid$text">单号</label></td>
  <td><input id="applySheetid" name="applySheetid" class="mini-textbox" required="true" enabled="false"  width="100%" value="${applysheetid.sheetid }"></td>
   <td><label for="applicantId$text">申请人</label></td>
  <td><input id="applicantId" name="applicantId" class="mini-buttonedit" width="100%" textName="applicantName" enabled="false" onbuttonclick="onButtonEditEmployee" 
  allowInput="false" required="true" value="${StaffCode }" text="${StaffName }"></td>
  
  </tr>
  <tr bgcolor=#EBEFF5>
  <td><label for="deptId$text">使用部门</label></td>
  <td><input id="deptId" name="deptId" class="mini-buttonedit" width="100%" textName="deptname" onbuttonclick="onButtonEditDept" 
  		allowInput="false" required="true"/></td>
  <td><label for="order_id$text">订单号</label></td>
  <td><input id="order_id"  name="order_id" class="mini-buttonedit" width="100%" onbuttonclick="onOrderButtonEdit"
          textName="orderId" required="false" allowInput="false"/></td>
 </tr>
  </table>

  

  <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
  		<tr>
 		<!-- <td>序号</td> --><td>货品编码</td><td>货品名称</td><td>规格</td><td>单位</td><td>类型</td><td>用途</td><td>数量</td><!-- <td>单价</td><td>货款</td> <td>库房</td><td>订单号</td><td>批次</td>--><td>备注/合同号</td>
   		</tr>
   		<tr><td><input id="item_id1"  name="item_id1" class="mini-buttonedit" onbuttonclick="onButtonEdit" textname="itemid_name1" allowinput="true"  width="100%" /></td><td><input id="item_name1"  name="item_name1" class="mini-textbox" width="100%" /></td><td><input id="spec1"  name="spec1" class="mini-textbox"  width="100%" /></td><td><input id="unit1"  name="unit1" class="mini-textbox"  width="100%" /></td><td><input id="kind1"  name="kind1" class="mini-combobox" textField="text" valueField="id" width="100%" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="true" url="GetItemTypeServlet"/></td><td><input id="usage1"  name="usage1" class="mini-textbox"  width="100%" /></td><td><input id="po_num1"  name="po_num1" class="mini-textbox"  width="100%" /></td><!--<td><input id="unitprice1"  name="unitprice1" class="mini-textbox"  width="100%" /></td><td><input id="price1"  name="price1" class="mini-textbox"  width="100%" /></td><td><input id="warehouse_id1"  name="warehouse_id1" class="mini-textbox"  width="100%" /></td> --><!-- <td><input id="order_id1"  name="order_id1" class="mini-textbox"  width="100%" /></td><td><input id="lot1"  name="lot1" class="mini-textbox"  width="100%" /></td> --><td><input id="memo1"  name="memo1" class="mini-textbox"  width="100%" /></td></tr>
   		<tr><td><input id="item_id2"  name="item_id2" class="mini-buttonedit" onbuttonclick="onButtonEdit" textname="itemid_name1" allowinput="true" width="100%" /></td><td><input id="item_name2"  name="item_name2" class="mini-textbox" width="100%"/></td><td><input id="spec2"  name="spec2" class="mini-textbox"  width="100%" /></td><td><input id="unit2"  name="unit2" class="mini-textbox"  width="100%" /></td><td><input id="kind2"  name="kind2" class="mini-combobox" textField="text" valueField="id" width="100%" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="true" url="GetItemTypeServlet"/></td><td><input id="usage2"  name="usage2" class="mini-textbox"  width="100%" /></td><td><input id="po_num2"  name="po_num2" class="mini-textbox"  width="100%" /></td><!--<td><input id="unitprice2"  name="unitprice2" class="mini-textbox"  width="100%" /></td><td><input id="price2"  name="price2" class="mini-textbox"  width="100%" /></td><td><input id="warehouse_id2"  name="warehouse_id2" class="mini-textbox"  width="100%" /></td> --><!-- <td><input id="order_id2"  name="order_id2" class="mini-textbox"  width="100%" /></td><td><input id="lot2"  name="lot2" class="mini-textbox"  width="100%" /></td> --><td><input id="memo2"  name="memo2" class="mini-textbox"  width="100%" /></td></tr>
  		<tr><td><input id="item_id3"  name="item_id3" class="mini-buttonedit" onbuttonclick="onButtonEdit" textname="itemid_name1" allowinput="true"  width="100%" /></td><td><input id="item_name3"  name="item_name3" class="mini-textbox" width="100%"/></td><td><input id="spec3"  name="spec3" class="mini-textbox"  width="100%" /></td><td><input id="unit3"  name="unit3" class="mini-textbox"  width="100%" /></td><td><input id="kind3"  name="kind3" class="mini-combobox" textField="text" valueField="id" width="100%" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="true" url="GetItemTypeServlet"/></td><td><input id="usage3"  name="usage3" class="mini-textbox"  width="100%" /></td><td><input id="po_num3"  name="po_num3" class="mini-textbox"  width="100%" /></td><!--<td><input id="unitprice3"  name="unitprice3" class="mini-textbox"  width="100%" /></td><td><input id="price3"  name="price3" class="mini-textbox"  width="100%" /></td><td><input id="warehouse_id3"  name="warehouse_id3" class="mini-textbox"  width="100%" /></td> --><!-- <td><input id="order_id3"  name="order_id3" class="mini-textbox"  width="100%" /></td><td><input id="lot3"  name="lot3" class="mini-textbox"  width="100%" /></td> --><td><input id="memo3"  name="memo3" class="mini-textbox"  width="100%" /></td></tr>
  		<tr><td><input id="item_id4"  name="item_id4" class="mini-buttonedit" onbuttonclick="onButtonEdit" textname="itemid_name1" allowinput="true" width="100%" /></td><td><input id="item_name4"  name="item_name4" class="mini-textbox" width="100%"/></td><td><input id="spec4"  name="spec4" class="mini-textbox"  width="100%" /></td><td><input id="unit4"  name="unit4" class="mini-textbox"  width="100%" /></td><td><input id="kind4"  name="kind4" class="mini-combobox" textField="text" valueField="id" width="100%" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="true" url="GetItemTypeServlet"/></td><td><input id="usage4"  name="usage4" class="mini-textbox"  width="100%" /></td><td><input id="po_num4"  name="po_num4" class="mini-textbox"  width="100%" /></td><!--<td><input id="unitprice4"  name="unitprice4" class="mini-textbox"  width="100%" /></td><td><input id="price4"  name="price4" class="mini-textbox"  width="100%" /></td><td><input id="warehouse_id4"  name="warehouse_id4" class="mini-textbox"  width="100%" /></td> --><!-- <td><input id="order_id4"  name="order_id4" class="mini-textbox"  width="100%" /></td><td><input id="lot4"  name="lot4" class="mini-textbox"  width="100%" /></td> --><td><input id="memo4"  name="memo4" class="mini-textbox"  width="100%" /></td></tr>
  		<tr><td><input id="item_id5"  name="item_id5" class="mini-buttonedit" onbuttonclick="onButtonEdit" textname="itemid_name1" allowinput="true" width="100%" /></td><td><input id="item_name5"  name="item_name5" class="mini-textbox" width="100%"/></td><td><input id="spec5"  name="spec5" class="mini-textbox"  width="100%" /></td><td><input id="unit5"  name="unit5" class="mini-textbox"  width="100%" /></td><td><input id="kind5"  name="kind5" class="mini-combobox" textField="text" valueField="id" width="100%" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="true" url="GetItemTypeServlet"/></td><td><input id="usage5"  name="usage5" class="mini-textbox"  width="100%" /></td><td><input id="po_num5"  name="po_num5" class="mini-textbox"  width="100%" /></td><!--<td><input id="unitprice5"  name="unitprice5" class="mini-textbox"  width="100%" /></td><td><input id="price5"  name="price5" class="mini-textbox"  width="100%" /></td><td><input id="warehouse_id5"  name="warehouse_id5" class="mini-textbox"  width="100%" /></td> --><!-- <td><input id="order_id5"  name="order_id5" class="mini-textbox"  width="100%" /></td><td><input id="lot5"  name="lot5" class="mini-textbox"  width="100%" /></td> --><td><input id="memo5"  name="memo5" class="mini-textbox"  width="100%" /></td></tr>
  		<tr><td><input id="item_id6"  name="item_id6" class="mini-buttonedit" onbuttonclick="onButtonEdit" textname="itemid_name1" allowinput="true" width="100%" /></td><td><input id="item_name6"  name="item_name6" class="mini-textbox" width="100%"/></td><td><input id="spec6"  name="spec6" class="mini-textbox"  width="100%" /></td><td><input id="unit6"  name="unit6" class="mini-textbox"  width="100%" /></td><td><input id="kind6"  name="kind6" class="mini-combobox" textField="text" valueField="id" width="100%" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="true" url="GetItemTypeServlet"/></td><td><input id="usage6"  name="usage6" class="mini-textbox"  width="100%" /></td><td><input id="po_num6"  name="po_num6" class="mini-textbox"  width="100%" /></td><!--<td><input id="unitprice6"  name="unitprice6" class="mini-textbox"  width="100%" /></td><td><input id="price6"  name="price6" class="mini-textbox"  width="100%" /></td><td><input id="warehouse_id6"  name="warehouse_id6" class="mini-textbox"  width="100%" /></td> --><!-- <td><input id="order_id6"  name="order_id6" class="mini-textbox"  width="100%" /></td><td><input id="lot6"  name="lot6" class="mini-textbox"  width="100%" /></td> --><td><input id="memo6"  name="memo6" class="mini-textbox"  width="100%" /></td></tr>
  		<tr><td><input id="item_id7"  name="item_id7" class="mini-buttonedit" onbuttonclick="onButtonEdit" textname="itemid_name1" allowinput="true" width="100%" /></td><td><input id="item_name7"  name="item_name7" class="mini-textbox" width="100%" /></td><td><input id="spec7"  name="spec7" class="mini-textbox"  width="100%" /></td><td><input id="unit7"  name="unit7" class="mini-textbox"  width="100%" /></td><td><input id="kind7"  name="kind7" class="mini-combobox" textField="text" valueField="id" width="100%" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="true" url="GetItemTypeServlet"/></td><td><input id="usage7"  name="usage7" class="mini-textbox"  width="100%" /></td><td><input id="po_num7"  name="po_num7" class="mini-textbox"  width="100%" /></td><!--<td><input id="unitprice7"  name="unitprice7" class="mini-textbox"  width="100%" /></td><td><input id="price7"  name="price7" class="mini-textbox"  width="100%" /></td><td><input id="warehouse_id6"  name="warehouse_id6" class="mini-textbox"  width="100%" /></td> --><!-- <td><input id="order_id7"  name="order_id7" class="mini-textbox"  width="100%" /></td><td><input id="lot7"  name="lot7" class="mini-textbox"  width="100%" /></td> --><td><input id="memo7"  name="memo7" class="mini-textbox"  width="100%" /></td></tr>
  		<tr><td><input id="item_id8"  name="item_id8" class="mini-buttonedit" onbuttonclick="onButtonEdit" textname="itemid_name1" allowinput="true" width="100%" /></td><td><input id="item_name8"  name="item_name8" class="mini-textbox" width="100%" /></td><td><input id="spec8"  name="spec8" class="mini-textbox"  width="100%" /></td><td><input id="unit8"  name="unit8" class="mini-textbox"  width="100%" /></td><td><input id="kind8"  name="kind8" class="mini-combobox" textField="text" valueField="id" width="100%" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="true" url="GetItemTypeServlet"/></td><td><input id="usage8"  name="usage8" class="mini-textbox"  width="100%" /></td><td><input id="po_num8"  name="po_num8" class="mini-textbox"  width="100%" /></td><!--<td><input id="unitprice8"  name="unitprice8" class="mini-textbox"  width="100%" /></td><td><input id="price8"  name="price8" class="mini-textbox"  width="100%" /></td><td><input id="warehouse_id6"  name="warehouse_id6" class="mini-textbox"  width="100%" /></td> --><!-- <td><input id="order_id8"  name="order_id8" class="mini-textbox"  width="100%" /></td><td><input id="lot8"  name="lot8" class="mini-textbox"  width="100%" /></td> --><td><input id="memo8"  name="memo8" class="mini-textbox"  width="100%" /></td></tr>
  		<tr><td><input id="item_id9"  name="item_id9" class="mini-buttonedit" onbuttonclick="onButtonEdit" textname="itemid_name1" allowinput="true" width="100%" /></td><td><input id="item_name9"  name="item_name9" class="mini-textbox" width="100%" /></td><td><input id="spec9"  name="spec9" class="mini-textbox"  width="100%" /></td><td><input id="unit9"  name="unit9" class="mini-textbox"  width="100%" /></td><td><input id="kind9"  name="kind9" class="mini-combobox" textField="text" valueField="id" width="100%" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="true" url="GetItemTypeServlet"/></td><td><input id="usage9"  name="usage9" class="mini-textbox"  width="100%" /></td><td><input id="po_num9"  name="po_num9" class="mini-textbox"  width="100%" /></td><!--<td><input id="unitprice9"  name="unitprice9" class="mini-textbox"  width="100%" /></td><td><input id="price9"  name="price9" class="mini-textbox"  width="100%" /></td><td><input id="warehouse_id6"  name="warehouse_id6" class="mini-textbox"  width="100%" /></td> --><!-- <td><input id="order_id7"  name="order_id7" class="mini-textbox"  width="100%" /></td><td><input id="lot7"  name="lot7" class="mini-textbox"  width="100%" /></td> --><td><input id="memo9"  name="memo9" class="mini-textbox"  width="100%" /></td></tr>
  		<tr><td><input id="item_id10"  name="item_id10" class="mini-buttonedit" onbuttonclick="onButtonEdit" textname="itemid_name1" allowinput="true"  width="100%" /></td><td><input id="item_name10"  name="item_name10" class="mini-textbox"  width="100%" /></td><td><input id="spec10"  name="spec10" class="mini-textbox"  width="100%" /></td><td><input id="unit10"  name="unit10" class="mini-textbox"  width="100%" /></td><td><input id="kind10"  name="kind10" class="mini-combobox" textField="text" valueField="id" width="100%" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="true" url="GetItemTypeServlet"/></td><td><input id="usage10"  name="usage10" class="mini-textbox"  width="100%" /></td><td><input id="po_num10"  name="po_num10" class="mini-textbox"  width="100%" /></td><!--<td><input id="unitprice10"  name="unitprice10" class="mini-textbox"  width="100%" /></td><td><input id="price10"  name="price10" class="mini-textbox"  width="100%" /></td><td><input id="warehouse_id6"  name="warehouse_id6" class="mini-textbox"  width="100%" /></td> --><!-- <td><input id="order_id8"  name="order_id8" class="mini-textbox"  width="100%" /></td><td><input id="lot8"  name="lot8" class="mini-textbox"  width="100%" /></td> --><td><input id="memo10"  name="memo10" class="mini-textbox"  width="100%" /></td></tr>
  		
  		</table>
  <input id="id" name="id" class="mini-hidden" value="${applysheetid.id }"/>
  <input id="seq" name="seq" class="mini-hidden" value="${applysheetid.seq }"/>
  <input id="isPass" name="isPass" class="mini-hidden" value="3"/>
  </form>
 
  
<!--  <form id="examine" name="examine" >-->
<!--  <div>-->
<!--  <label>审核意见</label> <br/>-->
<!--  <textarea id="opinion" name="opinion" class="mini-textarea" width="100%" height="100px" emptyText="请输入..." enabled="false"></textarea>-->
<!--  <span class="sign"><label>审核人</label>-->
<!--  <input id="examineId" name="examineId" class="mini-buttonedit" width="100px" allowinput="false" -->
<!--  textName="examineName" onbuttonclick="onButtonEditEmployee" enabled="false"/></span>-->
<!--  <a id="examinebutton" class="mini-button" iconCls="" onclick="submit">确定保存</a>-->
<!--  </div>-->
<!--  </form>-->
 
  </div>

  
  
  
  
<script type="text/javascript">
  	mini.parse();
  	//var win=mini.get("win1");
  	var grid=mini.get("grid");
  	var orderId="";
  	grid.load({orderId:orderId});
  	
  	function getForm(){
  	var form=new mini.Form("applysheet");
  	var data=form.getData();
  	data.applyDate=mini.get("applyDate").getFormValue();
 	 var json=mini.encode(data);
  	form.validate();
    if (form.isValid() == false) {
          return;
   }else{
  		$.ajax({
  		type:"post",
  		url:"ApplyServlet",
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
       
   function search(){
   var orderId=mini.get("orderId").getValue();
   grid.load({orderId:orderId});
   //var win = mini.get("win1");
	//win.setExpanded(false);
  
       
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
                           
                        }
                    }

                }
                
            });
      	 
        } 
      
     function onButtonEdit(e) {
            var btnEdit = this;
            var id=btnEdit.id;
            mini.open({
               
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
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemId);
                            if(id=="item_id1"){
                       		mini.get("item_name1").setValue(data.itemName);
                       		mini.get("kind1").setValue(data.itemType);
                       		mini.get("spec1").setValue(data.spec);
                       		mini.get("unit1").setValue(data.unit); 
                       		
                       		}else if(id=="item_id2"){
                       		mini.get("item_name2").setValue(data.itemName);
                       		mini.get("kind2").setValue(data.itemType);
                       		mini.get("spec2").setValue(data.spec);
                       		mini.get("unit2").setValue(data.unit); 
                       		
                       		}else if(id=="item_id3"){
                       		mini.get("item_name3").setValue(data.itemName);
                       		mini.get("kind3").setValue(data.itemType);
                       		mini.get("spec3").setValue(data.spec);
                       		mini.get("unit3").setValue(data.unit);
                       		
                       		}else if(id=="item_id4"){
                       		mini.get("item_name4").setValue(data.itemName);
                       		mini.get("kind4").setValue(data.itemType);
                       		mini.get("spec4").setValue(data.spec);
                       		mini.get("unit4").setValue(data.unit);
                       		
                       		}else if(id=="item_id5"){
                       		mini.get("item_name5").setValue(data.itemName);
                       		mini.get("kind5").setValue(data.itemType);
                       		mini.get("spec5").setValue(data.spec);
                       		mini.get("unit5").setValue(data.unit);
                       		
                       		}else if(id=="item_id6"){
                       		mini.get("item_name6").setValue(data.itemName);
                       		mini.get("kind6").setValue(data.itemType);
                       		mini.get("spec6").setValue(data.spec);
                       		mini.get("unit6").setValue(data.unit);
                       		
                       		}else if(id=="item_id7"){
                       		mini.get("item_name7").setValue(data.itemName);
                       		mini.get("kind7").setValue(data.itemType);
                       		mini.get("spec7").setValue(data.spec);
                       		mini.get("unit7").setValue(data.unit);
                       		
                       		
                       		}else if(id=="item_id8"){
                       		mini.get("item_name8").setValue(data.itemName);
                       		mini.get("kind8").setValue(data.itemType);
                       		mini.get("spec8").setValue(data.spec);
                       		mini.get("unit8").setValue(data.unit);
                       		
                       		}else if(id=="item_id9"){
                       		mini.get("item_name9").setValue(data.itemName);
                       		mini.get("kind9").setValue(data.itemType);
                       		mini.get("spec9").setValue(data.spec);
                       		mini.get("unit9").setValue(data.unit);
                       		
                       		}else if(id=="item_id10"){
                       		mini.get("item_name10").setValue(data.itemName);
                       		mini.get("kind10").setValue(data.itemType);
                       		mini.get("spec10").setValue(data.spec);
                       		mini.get("unit10").setValue(data.unit);
                       		
                       		}
                        }
                    }

                }
            });
        }
        
        function showAtPos() {
        var win = mini.get("win1");

        var x = "right";
        var y = "top";

        win.showAtPos(x, y);
    }
	showAtPos();
	
	 function getCurrentTime(){
   		      var now=new Date();
   		      var year = now.getFullYear();
		      var month = (((now.getMonth()+1) < 10) ? "0" : "") + (now.getMonth()+1);
		      var day = ((now.getDate() < 10) ? "0" : "") + now.getDate();
   		       mini.get("applyDate").setValue(year+"-"+month+"-"+day);
   		      }
   	     getCurrentTime();
  </script>
  </body>
</html>
