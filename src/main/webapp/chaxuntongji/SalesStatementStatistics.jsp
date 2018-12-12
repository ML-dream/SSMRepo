<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <!-- miniui.js -->
              	            <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>    
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>销售报表汇总表</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
    
  </head>
  
  <body style="height:99%;"> 
  	<div>
  	<form id="datepicker" action="" method="post">
           起始日期：<input id="startDate" name="startDate" class="mini-datepicker" showTodayButton="false" showClearButton="true"
  	showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false"/>&nbsp;&nbsp;&nbsp;&nbsp;
  	&nbsp;&nbsp;&nbsp;&nbsp;
  	终止日期：<input id="endDate" name="endDate" class="mini-datepicker" showTodayButton="fasle" showClearButton="true"
  	showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false"/>
  	<a class="mini-button" plain="fasle" onclick="onSearch()">查询</a>
  	</form>
  	</div>
  	
  	<div id="grid" class="mini-datagrid" style="width:100%;height:99%;" borderStyle="border:0;" multiSelect="true"  
  	idField="id" showSummaryRow="true" allowAlternating="true" showPager="true" showColumnsMenu="true" 
  	url="SalesStatementStatisticsServlet">
  		<div property="columns">
  		<div type="indexcolumn"></div>
  		<div field="companyid" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">客户编号</div>
  		<div field="companyname" headerAlign="center" align="center">客户名称</div>
  		<div field="price" headerAlign="center" align="center">销售货款</div>
  		<div field="tax" headerAlign="center" align="center">销项税额</div>
  		<div field="totalPrice" headerAlign="center" align="center">价税合计</div>
  		</div>
  	
  	</div>
  	<script type="text/javascript">
  	mini.parse();
  	var grid=mini.get("grid");
  	function onSearch(){
  		var startDate=mini.get("startDate").getFormValue();
  		var endDate=mini.get("endDate").getFormValue();
		grid.load({startDate:startDate,endDate:endDate});  	
  	}
  	</script>
  </body>
</html>
