<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>采购付款详细信息</title>
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
  		<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm()">保存</a>
  		<span class="separator"></span>
  		<a class="mini-button" plain="false" iconCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a>
  	</div>
  <form id="paySheet" name="paySheet" action="doeditPayServlet" method="post">
  <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
  <tr bgcolor=#EBEFF5>
  <td ><label for="payDate$text">开单日期</label></td>
  <td><input id="payDate" name="payDate" class="mini-datepicker" required="true" dateFormat="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd" 
  showTodayButton="false" showClearButton="false" allowInput="false" width="100%" enabled="false" value="${popay.payDate }"/></td>
  <td><label for="paySheetid$text">单号</label></td>
  <td><input id="paySheetid" name="paySheetid" class="mini-textbox" required="true" width="100%" enabled="false" value="${popay.paySheetid }"></td>
  <td><label for="customerId$text">供应商</label></td>
  <td><input id="customerId" name="customerId" class="mini-buttonedit" width="100%" onbuttonclick="onSupplierButtonEdit" textName="customerName" 
  required="true" allowInput="false"  value="${popay.customerId }" text="${popay.customerName }"/></td>
  </tr>
  <tr bgcolor=#EBEFF5>
  <td><label for="connector$text">联系人</label></td>
  <td><input id="connector" name="connector" class="mini-textbox" required="true" width="100%" value="${popay.connector }"/></td>
  <td><label for="connectorTel$text">联系电话</label></td>
  <td><input id="connectorTel"  name="connectorTel" class="mini-textbox" required="true" width="100%" value="${popay.connectorTel }"/></td>
  <td><label for="payType$text">付款类型</label></td>
  <td><input id="payType" name="payType" class="mini-textbox" required="true" width="100%" value="${popay.payType }"/></td>
  </tr>
   <tr bgcolor=#EBEFF5>
  <td><label for="payable$text">应付金额</label></td>
  <td><input id="payable" name="payable" class="mini-textbox" required="true" width="100%" value="${popay.payable }"/></td>
  <td><label for="prepaid$text">预付金额</label></td>
  <td><input id="prepaid" name="prepaid" class="mini-textbox" required="true" width="100%" value="${popay.prepaid }"/></td>
  <td><label for="payment$text">实付金额</label></td>
  <td><input id="payment" name="payment" class="mini-textbox" required="true" width="100%" value="${popay.payment }"/></td>
 </tr>
 <tr bgcolor=#EBEFF5>
 <td><label for="isBill$text">是否开具发票</label></td>
  <td><input id="isBill" name="isBill" class="mini-combobox" required="true" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
   url="data/trueOrFalse.txt" allowInput="false" showNullItem="true" nullItemText="请选择..." value="${popay.isBill }"/></td>
  <td><label for="payMethod$text">付款方式</label></td>
  <td><input id="payMethod" name="payMethod" class="mini-combobox" style="width:100%" textField="text" valueField="id" emptyText="请选择..."
    url="GetpayMethodServlet" value="XZ"  required="true" allowInput="true" showNullItem="true" nullItemText="请选择..." value="${popay.payMethod }" text="${popay.method }"/></td>
  <td><label for="account$text">付款账号</label></td>
  <td><input id="account" name="account" required="true" class="mini-textbox" width="100%" value="${popay.account }"/></td>
  </table>
  
  <div id="grid1" class="mini-datagrid" style="width:100%;height:75%;" borderStyle="border:0;" multiSelect="true"
     idField="id" showSummaryRow="true" allowAlternating="true" showPager="true" url="ShowPayDetailServlet?paySheetid=${popay.paySheetid }">
     	<div property="columns">
            <div type="indexcolumn"></div>
            <div name="action" headerAlign="center" align="center" renderer="onOperatePower">操作</div>
            <div field="prSheetId" headerAlign="center" align="center" width="130">引用单号</div>
            <div field="prdate" headerAlign="center" align="center">单据日期</div>
            <div field="itemId" headerAlign="center" align="center">货品编号</div>
            <div field="itemName" headerAlign="center" align="center">货品名称</div>
            <div field="spec" headerAlign="center" align="center">规格</div>
            <div field="price" headerAlign="center" align="center">总金额</div>
            <div field="haspaid" headerAlign="center" align="center">已结算金额</div>
            <div field="nopay" headerAlign="center" align="center">未结算金额</div>
            <div field="thispay" headerAlign="center" align="center">本次结算金额</div>
    		<div field="memo" headerAlign="center" align="center">备注</div>
   		 </div>
    </div>
  <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
  <tr bgcolor=#EBEFF5>
  
  <td><label for="examineId$text">审核人</label></td>
  <td><input id="examineId" name="examineId"  class="mini-buttonedit" width="100%" textName="examineName" onbuttonclick="onButtonEditEmployee" 
  allowInput="false" required="true" value="${popay.examineId }" text="${popay.examineName }"></td>
  <td><label for="salesmanId$text">业务员</label></td>
  <td><input id="salesmanId" name="salesmanId"  class="mini-buttonedit" width="100%" textName="salesmanName" onbuttonclick="onButtonEditEmployee" 
  allowInput="false" required="true" value="${popay.salesmanId }" text="${popay.salesmanName }"></td>
  <td><label for="drawerId$text">开单人</label></td>
  <td><input id="drawerId" name="drawerId"  class="mini-buttonedit" width="100%" textName="drawerName" onbuttonclick="onButtonEditEmployee" 
  allowInput="false" required="true" value="${popay.drawerId }" text="${popay.drawerName }"></td>
  </tr>
  </table>
  </form>
  <script>
  mini.parse();
  var grid=mini.get("grid1");
  grid.load();
  function getForm(){
  	var form=new mini.Form("paySheet");
  	var data=form.getData();
  	data.payDate=mini.get("payDate").getFormValue();
  	var json=mini.encode(data);
  	form.validate();
  	if(form.isValid()==false){
  		return;
  	}else{
  		$.ajax({
  		type:"post",
  		url:"doeditPayServlet",
  		data:{submitData:json},
  		dataType:"json",
  		success: function(data){
  		alert(data.result);
  		window.location.href=window.location.href;
  		
  		}
  		});
  	}
  
  }
  
  
  function onOperatePower(e){
  	var str="";
    str+="<span>";
    str+="<a title='编辑' href=javascript:ondEdit(\'"+e.row.prSheetId+"\',\'"+e.row.itemId+"\')><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>"; 
    str+="</span>";
    return str;
  }
  
  function ondEdit(prSheetId,itemId){
  var paySheetid=mini.get("paySheetid").getValue();
  window.location="editPayDetailServlet?paySheetid="+paySheetid+"&prSheetId="+prSheetId+"&itemId="+itemId;
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
  </script>
</body>
</html>
