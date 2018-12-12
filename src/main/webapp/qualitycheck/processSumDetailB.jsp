<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>执行进度</title>
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
  
  	<form id="form1">
	   	<table>
   		<!-- 表头 -->
   			<tr>
				<td><label>合同号</label></td>
	            <td >
   					<input id="orderId"  name="orderId" class="mini-textbox" width="240px" enabled="false" allowInput="false" value="${order.orderId}"/></td>
   				<td><label>零件图号</label></td>
	            <td><input id="productId" name="productId" class="mini-textbox" enabled="false" allowInput="false" value="${order.productId}"/></td>
   				<td><label>零件名称</label></td>
	            <td><input id="productName" name="productName" class="mini-textbox" enabled="false" allowInput="false" value="${order.productName}"/></td>
	            <td><input id="issueNum" name="issueNum" class="mini-hidden" enabled="false" allowInput="false" value="${order.issueNum}"/></td>
   				<td><label>零件数量</label></td>
	            <td><input id="ProductNum" name="ProductNum" class="mini-textbox" enabled="false" allowInput="false" value="${order.productNum}"/></td>
   			</tr>
   	 	</table>
   	 </form>
   	 
	<div id="tablediv">
   	 <div id="datagrid1" class="mini-datagrid" style="width:900px;height:480px;" allowResize="true"
        url="ProductSumDetail"  idField="id" multiSelect="true" pagesize="20" onselect="rowselect()" onrowclick="rowselect()">
        <div property="columns">
                
            <!--零件条形码 ，不可见 -->    
           <!--  <div type="indexcolumn"></div><div type="checkcolumn" ></div>   --> 
           
            <div field="fono" width="40" headerAlign="center" allowSort="false">工序号</div>    
            <div field="foopname" width="60" headerAlign="center" allowSort="false">工序名称</div> 
            
            <div field="partnum" width="60" headerAlign="center">计划数量</div>
        	<div field="acceptnum" width="40" headerAlign="center" allowSort="false">合格数</div>
   			<div field="rejectnum" width="40" headerAlign="center" allowSort="false">不合格数</div>
    		<div field="stateName" width="40" headerAlign="center" allowSort="false" >状态</div>
    		<div field="remark" width="80" headerAlign="center" allowSort="false" >备注</div>
        </div>
     </div>
   </div>    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("datagrid1");
	    start();
	    function start(){
	    	var productId = mini.get("productId").getValue();
	    	var issueNum = mini.get("issueNum").getValue();
	    	var orderId = mini.get("orderId").getValue();
	    	grid.load({productId:productId,issueNum:issueNum,orderId:orderId});
	    }
			
    </script>
  </body>
</html>
