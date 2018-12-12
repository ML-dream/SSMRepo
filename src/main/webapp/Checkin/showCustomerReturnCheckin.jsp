<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
        <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
    <title>客户退货入库记录</title>
    
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    	<div id="grid" class="mini-datagrid" style="width:100%;height:99%;" borderStyle="border:0;" multiSelect="true"
    	 idField="id" allowAlternating="true" showPager="true" url="ShowCustomerReturnServlet">
    	 	<div property="columns">
    	 	<div type="indexcolumn"></div>
    	 	<div field="action" headerAlign="center" align="center" renderer="onOperatePower">操作</div>
    	 	<div field="sheetId" headerAlign="center" align="center">单号</div>
    	 	<div field="returnDate" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">日期</div>
    	 	<div field="orderId" headerAlign="center" align="center">订单号</div>
    	 	<div field="companyName" headerAlign="center" align="center">客户</div>
    	 	<div field="connector" headerAlign="center" align="center">联系人</div>
    	 	<div field="connectorTel" headerAlign="center" align="center">联系电话</div>
    		</div>
    	</div>
    	
    	
    	<script>
    	mini.parse();
    	var grid=mini.get("grid");
    	grid.load();
    	
    	
    	function onOperatePower(e){
    		var str="";
    		str+="<span>";
    		str+="<a style='margin-right:2px;' title='添加详情' href=javascript:ondAdd(\'"+e.row.sheetId+"\',\'"+e.row.orderId+"\')><span class='mini-button-text mini-button-icon icon-add'>&nbsp;</span></a>";
    		str+="</span>";
    		str+="<span>";
    		str+="<a style='margin-right:2px;' title='查看详情' href=javascript:ondSee(\'"+e.row.sheetId+"\')><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
    		str+="</span>";
    		str+="<span>";
    		str+="<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'"+e.row.sheetId+"\')><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
    		str+="</span>";
    		return str;
    	
    	}
    	
    	function ondAdd(sheetId,orderId){
    		
    		window.location="GoAddCustomerReturnServlet?sheetId="+sheetId+"&orderId="+orderId;
    	
    	}
    	
    	function ondSee(sheetId){
    		window.location="GoSeeCustomerReturnServlet?sheetId="+sheetId;
    	}
    	
    	function ondEdit(sheetId){
    		window.location="editCustomerReturnServlet?sheetId="+sheetId;
    	}
    	</script>
  </body>
</html>
