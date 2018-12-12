<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>出库审核</title>
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
	    font-size:100%;
	} 
	-->
	</style>
	
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
	
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    	
     .mini-grid-cell-inner, .mini-grid-headerCell-inner {
		    font-size: 14pt;
		    font-family: Tahoma, Verdana, 宋体;
		    line-height: 18px;
		    padding: 0px;
		    padding-top: 2px;
		    padding-bottom: 2px;
		    width: 100%;
		    position: relative;
		    overflow: hidden;
		    white-space: normal;
		    word-break: break-all;
		}
    </style>
  </head>
  
  <body>
  	<fieldset id="allDetail" style="width: 810px;" align="center">
  	 <legend>物料单</legend>
   <table style="text-align: right;border-collapse:collapse;"  width="99%" >
   		<tr  height="35px">
   			<td width="10%" height= "25"><label for="companyName$text">收货单位：</label></td>
            <td width="50%" height= "34px"><input id="companyName" name="companyName" class="mini-textbox" width="100%" height= "100%" readonly borderStyle="background:transparent;border:0"
             	 inputStyle="font-size:17px;" value="${order.companyName}" /></td>
         <td width="10%"><label for="orderId$text">单号：</label></td>
         <td width="30%"><input id="orderId" name="orderId" class="mini-textbox" width="100%" readonly value="${orderId}" borderStyle="background:transparent;border:0"/></td>
   		</table>
  		<div id="grid" class="mini-datagrid" style="width:99%;height:590px;" borderStyle="border:1" multiSelect="true" 
    	idField="id" showSummaryRow="true" showFilterRow="false" allowAlternating="true" showPager="true" url="ShowProductMatirialServlet?orderId=${orderId }"
    	>
    	<div property="columns">
    	<div type="indexcolumn">序号</div>
<!--    <div field="productId" headerAlign="center" align="center">产品号</div>-->
    	<div field="productId" headerAlign="center" align="center">图号</div>
    	<div field="productName" headerAlign="center" align="center">产品名称</div>
<!--    	<div field="issueNum" headerAlign="center" align="center">版本号</div>-->
<!--		<div field="spec" headerAlign="center" align="center">规格</div>-->
		<div field="productNum" headerAlign="center" align="center">产品数量</div>
		<div field="matirial" headerAlign="center" align="center">材料</div>
		<div field="roughSize" headerAlign="center" align="center">毛坯尺寸</div>    
   		</div>
    	</div>
    </fieldset>
    <script>
   		mini.parse();
	    var grid = mini.get("grid");
	    grid.load();
    
    </script>
  </body>
</html>
