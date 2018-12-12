<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客户资产入库</title>
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
<!--      <div class="mini-toolbar">-->
<!--  		<a class="mini-button" iconCls="icon-print" plain="false"  onclick="print()">打印入库单</a>-->
<!--  		<span class="separator"></span>-->
<!--  		 <a class="mini-button" iconCls="icon-save" plain="false" onclick="getForm()">保存</a>-->
<!--  		<span class="separator"></span>-->
<!--  		<a class="mini-button" plain="false" iconCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a>-->
<!--  	</div>-->
  	<div>
   <table>
  	<tr>
  	<td><label for="orderid$text">订单号</label></td>
  	<td><input id="orderid" name="orderid" class="mini-textbox" width="100%" reqiured="true" autofocus="autofocus" onchange="asset_search()" onenter="asset_search()"/></td>
  	<td><input type="button" value="查询" onclick="asset_search()"/></td>
  	<td>&nbsp;&nbsp;&nbsp;&nbsp;</td>
  	<td><label for="customerId$text">客户</label></td>
  	<td><input id="customerId" name="customerId" class="mini-buttonedit" textName="customerName" width="100%" reqiured="true" 
  	 autofocus="autofocus" allowInput="false" onchange="asset_search()" onclick="onCustomerButtonEdit"/></td>
  	<td><input type="button" value="查询" onclick="asset_search()"/></td>
  	</tr>
  	</table>
  	</div>
  	<div id="grid1" class="mini-datagrid" style="width:100%;height:90%;"  showColumnsMenu ="true"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="SearchAssetServlet">
  		 <div property="columns">
            <div type="indexcolumn"></div>
           
            <div name="action" width="40" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
            <div field="orderId" width="100" headerAlign="center">订单编号</div>
            <div field="customeId" width="100" headerAlign="center" visible="false">客户编号</div>
            <div field="customeName" width="100" headerAlign="center">客户名称</div>
            <div field="connector" width="60" headerAlign="center">联系人</div>
            <div field="mainId" width="100" headerAlign="center" visible="false">资产编号</div>
            <div field="itemId" width="100" headerAlign="center">资产编号</div>
            <div field="itemname" width="100" headerAlign="center">资产名称</div>
            <div field="itemTypeName" width="60" headerAlign="center">资产类型</div>
            <div field="itemNum" width="60" headerAlign="center">数量</div>
            <div field="itemPrice" width="60" headerAlign="center" >价格</div>
            <div field="unit" width="60" headerAlign="center">价格单位</div>
            <div field="personName" width="60" headerAlign="center">经手人</div>
            <div field="starttime" width="80" headerAlign="center" dateFormat="yyyy-MM-dd">借入日期</div>
            <div field="endtime" width="80" headerAlign="center"  dateFormat="yyyy-MM-dd"  visible="false">计划归还日期</div>
            <div field="isCheckIn" width="60" headerAlign="center" renderer="onGenderRenderer" visible="false">是否入库</div>
        </div>
  	</div>
  	
  	
  	</div>
  	<script>
  	mini.parse();
  	showAll();
  	function showAll(){
  		var grid =mini.get("grid1");
  		grid.load({"para":"all"});
  	}
	
 	function asset_search(){
 		var grid =mini.get("grid1");
 		//var form=new mini.Form("customercheckin");
  		var id=mini.get("orderid").getValue();
  		var customerId=mini.get("customerId").getValue();
  		grid.load({orderid:id,customerId:customerId});
  	}
  	 
  	   function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='客户资产入库' href=javascript:Checkin(\'" + e.row.orderId+"\',\'"+e.row.mainId+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
	        return str;
	    }
	    
	    function refresh(){
			grid.reload();
		}
		
	    function Checkin(orderId,mainId){
	    	// var id=mini.get("orderid").getValue();
  			// var customerId=mini.get("customerId").getValue();
	       // window.location="SeeCustomerItemSpecServlet?orderId="+orderId+"&mainId="+mainId+"&id="+id+"&customer="+customerId;
	       window.location="SeeCustomerItemSpecServlet?orderId="+orderId+"&mainId="+mainId;
		}
		
		
       	function getCheckedRowsJson(rows){
       		return JSON.stringify(rows);
       	}
       	
       	var Genders = [{id: "0", text: "否"},{id: "1", text: "是"}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
       	
       	function onCustomerButtonEdit(e) {
         var btnEdit = this;
         mini.open({
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
