<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>采购申请审核 </title>
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
  <div class="mini-toolbar" >
  <a class="mini-button" plain="false" iconCls="icon-find" onclick="getPass(2)">审核通过</a>
  <span class="sepaator"></span>
  <a class="mini-button" plain="false" iconCls="icon-find" onclick="getPass(3)">审核不通过</a>
  <span class="sepaator"></span>
  <a class="mini-button" plain="false" iconCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a>
  </div>
   <form id="examinepo" name="editpo" action="doEditPoServlet" method="post">
  
     	 <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
  		 <tr bgcolor=#EBEFF5>
  		 <td ><label for="postart_date$text">开单日期</label></td>
  		 <td><input id="postart_date" name="postart_date" class="mini-datepicker" required="true" dateFormat="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd" 
  		 showTodayButton="false" showClearButton="false" allowInput="false" width="100%"  value="${poplan.postartdate }" enabled="false"/></td>
  		 <td><label for="po_sheetid$text">单号</label></td>
 		 <td><input id="po_sheetid" name="po_sheetid" class="mini-textbox" required="true" width="100%" enabled="false" value="${poplan.posheetid }" enabled="false"/></td>
<!--  		 <td><label for="order_id$text">订单号</label></td>-->
<!--  		 <td><input id="order_id"  name="order_id" class="mini-buttonedit" width="100%" onbuttonclick="onOrderButtonEdit" -->
<!--            textName="orderId" required="true" allowInput="false" value="${poplan.orderId }" text="${poplan.orderId }" enabled="false"/></td>-->
<!--  		-->
		 <td><label for="customerid$text">供应商</label></td>
 		 <td><input id="customerid" name="customerid" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" textName="customername" 
 		 required="true" allowInput="false"  value="${poplan.customerid }" text="${poplan.customername }" enabled="false"/></td>
 		 </tr>
 		 <tr bgcolor=#EBEFF5>
 		
  		 <td><label for="connector$text">联系人</label></td>
  		 <td><input id="connector" name="connector" class="mini-textbox" required="true" width="100%"  value="${poplan.connector }" enabled="false"/></td>
 		 <td><label for="connectortel$text">联系电话</label></td>
 		 <td><input id="connectortel"  name="connectortel" class="mini-textbox" required="true" width="100%"  value="${poplan.connectortel }" enabled="false"/></td>
  		 <td><label for="salesman_id$text">业务员</label></td>
  		<td><input id="salesman_id" name="salesman_id"  class="mini-buttonedit" width="100%" textName="salesman" 
  		onbuttonclick="onButtonEditEmployee" allowInput="false" required="true" value="${poplan.salesmanid }" text="${poplan.salesmanname }" enabled="false"/></td>
  		</tr>
  		<tr bgcolor=#EBEFF5>
  		
  		<td><label for="drawer_id$text">开单人</label></td>
  		<td><input id="drawer_id" name="drawer_id"  class="mini-buttonedit" width="100%" textName="drawer" 
  		onbuttonclick="onButtonEditEmployee" allowInput="false" required="true"  value="${poplan.drawerid }" text="${poplan.drawername }" enabled="false"/></td>
  		<td><label for="end_date$text">交货日期</label></td>
  		<td><input id="end_date" name="end_date" class="mini-datepicker" required="true" width="100%" dateFormat="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd" 
  		showTodayButton="false" showClearButton="false" allowInput="false"  value="${poplan.enddate }" enabled="false"/></td>
  		</tr>
 	<tr bgcolor=#EBEFF5>
   	<td><label for="opinion$text">已有审核意见</label></td>
  	<td colspan="5"><input id="confirmedAdvice" name="confirmedAdvice" class="mini-textarea" width="100%" enabled="false" height="70px" emptyText="请输入备注" value="${poplan.opinion }"></td>
  	</tr>	
 	<tr bgcolor=#EBEFF5>
   	<td><label for="opinion$text">审核意见</label></td>
  	<td colspan="5"><input id="opinion" name="opinion" class="mini-textarea" width="100%" height="70px" emptyText="请输入备注"></td>
  	</tr>
  	</table>
  </form>
  
  <div id="grid1" class="mini-datagrid" style="width:100%;height:75%;" borderStyle="border:0;" multiSelect="true" ondrawsummarycell="onDrawSummaryCell"
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
	
<!--  <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;" borderStyle="border:0;" -->
<!--    multiSelect="true" idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"-->
<!--   url="ShowApplyDetailServlet?applySheetid=${apply.applySheetid }">-->
<!--      	<div property="columns">-->
<!--      	<div type="indexcolumn"></div>-->
<!--    		<div field="itemId" headerAlign="center" align="center" width="100">货品编号</div>-->
<!--    		<div field="itemName" headerAlign="center" align="center" width="100">货品名称</div>-->
<!--    		<div field="spec" headerAlign="center" align="center" width="100">规格</div>-->
<!--    		<div field="unit" headerAlign="center" align="center" width="100">单位</div>-->
<!--    		<div field="itemTypeDesc" headerAlign="center" align="center" width="100">类型</div>-->
<!--    		<div field="usage" headerAlign="center" align="center" width="100">用途</div>-->
<!--    		<div field="poNum" headerAlign="center" align="center" width="100">数量</div>-->
<!--    		<div field="memo" headerAlign="center" align="center" width="100">备注</div>-->
<!--    -->
<!--    	</div>-->
<!--	</div>-->
	
	<script type="text/javascript">
	mini.parse();
	var grid=mini.get("grid1");
	grid.load();
	
	function onDrawSummaryCell(e) {
            var result = e.result;
            var grid = e.sender;
            //服务端汇总计算
            if (e.field == "price") {                
                var s = "<b style='color:red;'>"
                s +=  	"总价："+ result.sumPrice + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
        }
	
	function getPass(ispass){
		var form=new mini.Form("examinepo");
		form.validate();
		if(form.isValid()==false){
			return;
		}else{
			var data=form.getData();
			var json=mini.encode(data);
			$.ajax({
			type:"post",
			url:"doExamineApplyServlet?isPass="+ispass,
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
	</script>
  </body>
</html>
