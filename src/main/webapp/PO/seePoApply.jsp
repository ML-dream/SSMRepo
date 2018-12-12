 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
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

<!--	<div class="mini-toolbar">-->
<!--	<a class="mini-button" iconCls="" plain="false"  onclick="seePoApply()">查看</a>-->
<!--	-->
<!--	</div>-->
	<h1>采购申请单</h1><br/>
	<br/>
    <form id="applysheet" name="applysheet" action="PoServlet" method="post">
    <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
        <tr bgcolor=#EBEFF5>
  			<td width="10%"><label for="applyDate$text">开单日期</label></td>
  			<td><input id="applyDate" name="applyDate" class="mini-datepicker" required="true" enabled="false" dateFormat="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd" 
  			showTodayButton="false" showClearButton="false" allowInput="false" width="100%" value="${apply.applyDate }"/></td>
  			<td width="10%"><label for="applySheetid$text">单号</label></td>
  			<td><input id="applySheetid" name="applySheetid" class="mini-textbox" required="true" enabled="false"  width="100%" value="${apply.applySheetid }"></td>
  			<td width="10%"><label for="applicantId$text">申请人</label></td>
  			<td><input id="applicantId" name="applicantId" class="mini-buttonedit" width="100%" textName="applicantName" enabled="false" onbuttonclick="onButtonEditEmployee" 
  			allowInput="false" required="true" value="${apply.applicantId }" text="${apply.applicantName }"></td>
  
  		</tr>
  		<tr bgcolor=#EBEFF5>
  			<td><label for="deptId$text">使用部门</label></td>
  			<td><input id="deptId" name="deptId" class="mini-buttonedit" width="100%" textName="deptname" onbuttonclick="onButtonEditDept" 
  			allowInput="false" required="true" enabled="false" value="${apply.deptId }" text="${apply.deptName }"/></td>
  			<td><label for="order_id$text">订单号</label></td>
  			<td><input id="order_id"  name="order_id" class="mini-buttonedit" width="100%" onbuttonclick="onOrderButtonEdit"
          	textName="orderId" required="false" enabled="false" allowInput="false" value="${apply.orderId }" text="${apply.orderId }"/></td>
 		</tr>
  	</table>
	</form>
	
	<div id="grid" class="mini-datagrid" style="height:99%;width:99%" borderStyle="border:0;" idField="id" multiSelect="true" 
	allowAlternating="true" showPager="true" url="ShowApplyDetailServlet?applySheetid=${apply.applySheetid }">
		<div property="columns">
            <div type="indexcolumn"></div>
<!--    		<div name="action" width="60" headerAlign="center" align="center" renderer="onOperatePower"-->
<!--    		cellStyle="padding:0;">操作</div>-->
    		<div field="itemId" headerAlign="center" align="center" width="100">货品编号</div>
    		<div field="itemName" headerAlign="center" align="center" width="100">货品名称</div>
    		<div field="spec" headerAlign="center" align="center" width="100">规格</div>
    		<div field="unit" headerAlign="center" align="center" width="100">单位</div>
    		<div field="itemTypeDesc" headerAlign="center" align="center" width="100">类型</div>
    		<div field="usage" headerAlign="center" align="center" width="100">用途</div>
    		<div field="poNum" headerAlign="center" align="center" width="100">数量</div>
    		<div field="memo" headerAlign="center" align="center" width="100">备注</div>
    
    	</div>
	</div>
	
	<script>
	mini.parse();
	var grid=mini.get("grid");
	grid.load();
	
	
	</script>
  </body>
</html>
