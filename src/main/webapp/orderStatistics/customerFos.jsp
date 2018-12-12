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
  </br>
  </br>
  <div id="win1" class="mini-window" title="操作" style="width:600px;height:200px;" expanded="false"
    showMaxButton="true" showCollapseButton="true" showShadow="true" showCloseButton="false" collapseOnTitleClick="true"
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >

  	<form id="form1">
	   	<table>
   		<!-- 表头 -->
   			<tr style= "height:10px;"></tr>
   			<tr style= "height:20px;">
   				<td style= "width:20px;"></td>
 				<td>
  					<input value="返回" type="button" style= "width:70px;" onclick="back()" />
  					<input value="查看工序详情" type="button" style= "width:90px;" onclick="seeFoDetail()" />
 				</td> 
   			</tr>
   			<tr style= "height:15px;"></tr>
   	 	</table>
   	 </form>
   	 <table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
			<td><label>合同号</label></td>
            <td >
				<input id="orderId"  name="orderId" class="mini-textbox" width="100%" enabled="false" allowInput="false" value="${order.orderId} "/></td>
			<td><label>零件图号</label></td>
           <td><input id="productId" name="productId" class="mini-textbox" width="100%" enabled="false" allowInput="false" value="${order.productId} "/></td>
  		</tr>
   		<tr>
   			<td><label>零件名称</label></td>
            <td><input id="ProductName" name="productName" class="mini-textbox" width="100%" enabled="false" allowInput="false" value="${order.productName} "/></td>
   			<td ><label for="endTime$text">交付日期*</label></td>
            <td><input id="endTime"  name="endTime" class="mini-textbox"  width="100%"   value="${order.eTime}" enabled="false"/></td>
   		</tr>
   			<input id="rowIndex"  name="rowIndex" class="mini-hidden" width="100%"   value="${param.rowIndex}" enabled="false"/>
   			<input id="page"  name="page" class="mini-hidden" width="100%"   value="${param.page}" enabled="false"/>
   	</table>
  </div> 	
   	 
	<div id="tablediv">
   	 <div id="datagrid1" class="mini-datagrid" style="width:950px;height:480px;" allowResize="true"
        url="ProductSumDetail"  idField="id" multiSelect="true" pagesize="20" onselect="rowselect()" onrowclick="rowselect()">
        <div property="columns">
                
            <!--零件条形码 ，不可见 -->    
           <!--  <div type="indexcolumn"></div><div type="checkcolumn" ></div>   --> 
           
            <div field="fono" width="40" headerAlign="center" allowSort="false">工序号</div>    
            <div field="foopname" width="60" headerAlign="center" allowSort="false">工序名称</div> 
            <div field="qualityLoss" width="50" headerAlign="center">质量损失
            </div>
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
	    search();
	    
	    function back(){
	    	var orderId = mini.get("orderId").getValue();
	    	
	    	var page = mini.get("page").getValue();
	    	var rowIndex = mini.get("rowIndex").getValue();
	    	
			window.location="ToCustomerProducts?orderId="+ orderId+"&rowIndex="+rowIndex+"&page="+page+"&para=back";
	    }
	    function search (){
	    	var orderId = mini.get("orderId").getValue();
	    	var productId = mini.get("productId").getValue();
       		 grid.load({productId:productId,orderId:orderId,issueNum:"1"});
        }
        function seeFoDetail(){
        	var orderId = mini.get("orderId").getValue();
	    	var productId = mini.get("productId").getValue();
	    	
        	window.open("orderStatistics/seeFoDetail.jsp?orderId="+orderId+"&productId="+productId,
                      "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
        	
        }
        function showAtPos() {
	        var win = mini.get("win1");
	
	        var x = "right";
	        var y = "top";
	
	        win.showAtPos(x, y);
	    }
	showAtPos();	
    </script>
  </body>
</html>
