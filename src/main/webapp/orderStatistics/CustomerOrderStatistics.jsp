<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客户订单记录</title>
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
  	<!--</br>
  		<a class="mini-button" iconCls="icon-undo" plain="false" onclick="javascript:window.history.back(-1);">返回</a>
  	</br>
    -->
     <div id="grid1" class="mini-datagrid" style="height:85%;"  showColumnsMenu ="false" 
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="false" allowAlternating="true" showPager="true"
           url="showOrderStatisticsServlet?orderId=${orderId }">
      	<div property="columns">
            <div type="indexcolumn">序号</div>
<!--         <div field="orderId" headerAlign="center" align="center">订单号</div>-->
         <div field="productId" headerAlign="center" align="center">图号</div>
         <div field="productName" headerAlign="center" align="center">名称</div>
         <div field="issueNum" headerAlign="center" align="center">版本号</div>
         <div field="drawingId" headerAlign="center" align="center">产品号</div>
         <div field="productNum" headerAlign="center" align="center">订单数量</div>
         <div field="checkinNum" headerAlign="center" align="center">已入库数量</div>
         <div field="alreadyPayNum" headerAlign="center" align="center">已发货数量</div>
         <div field="makingNum" headerAlign="center" align="center">在制品数量</div>
         </div>
      </div>
      <script type="text/javascript">
      mini.parse();
      var grid=mini.get("grid1");
       grid.load();
       grid.unmask ( );
      </script> 
  </body>
</html>
