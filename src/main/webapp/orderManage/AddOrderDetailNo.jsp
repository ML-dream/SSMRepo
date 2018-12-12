<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   	<title>添加订单详情</title>
   	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>
	<h1>添加订单详情</h1>
   	<div id= "userdiv">
   		<table style="text-align: right;border-collapse:collapse;width: 100%;" border="gray 1px solid;">
   		<tr>
   			<td><label for="orderId$text">订单编号</label></td>
            <td><input id="orderId"  name="orderId" class="mini-textbox" width="100%" required="true" /></td>
   			<td><label for="productId$text">产品号</label></td>
            <td><input id="productId"  name="productId" class="mini-textbox" width="100%"  required="true" /></td>
   			<td><label for="lot$text">LOT</label></td>
            <td><input id="lot"  name="lot" class="mini-textbox"  width="100%" required="true" /></td>
       		<td><label for="issueNum$text">ISSUE_NUM</label></td>
            <td><input id="issueNum"  name="issueNum" class="mini-textbox" width="100%"  required="true" /></td>
   	   </tr>
       <tr>
          	<td><label for="sortIe$text">SORTIE</label></td>
            <td><input id="sortIe"  name="sortIe" class="mini-textbox"  width="100%" required="true" /></td>
   			<td><label for="productNum$text">产品数量</label></td>
            <td><input id="productNum"  name="productNum" class="mini-textbox"  width="100%" required="true" /></td>
   			<td><label for="BTime$text">开始时间</label></td>
            <td><input id="BTime"  name="BTime" class="mini-textbox"  width="100%" required="true" /></td>
   			<td><label for="ETime$text">结束时间</label></td>
            <td><input id="ETime"  name="ETime" class="mini-textbox"  width="100%" required="true" /></td>
   		</tr>
   		
   		<tr>
          	<td><label for="SBTime$text">SB_TIME</label></td>
            <td><input id="SBTime"  name="SBTime" class="mini-textbox" width="100%"  required="true" /></td>
   			<td><label for="SETime$text">SE_TIME</label></td>
            <td><input id="SETime"  name="SETime" class="mini-textbox" width="100%"  required="true" /></td>
   			<td><label for="FlightType$text">FLIGHT_TYPE</label></td>
            <td><input id="FlightType"  name="FlightType" class="mini-textbox" width="100%"  required="true" /></td>
   			<td><label for="isArmy$text">IS_ARMY</label></td>
            <td><input id="isArmy"  name="isArmy" class="mini-textbox" width="100%"  required="true" /></td>
   		</tr>
   		
   		<tr>
          	<td><label for="deptId$text">DEPT_ID</label></td>
            <td><input id="deptId"  name="deptId" class="mini-textbox"  width="100%" required="true" /></td>
   			<td><label for="isUnite$text">IS_UNITE</label></td>
            <td><input id="isUnite"  name="isUnite" class="mini-textbox"  width="100%" required="true" /></td>
   			<td><label for="isLot$text">IS_LOT</label></td>
            <td><input id="isLot"  name="isLot" class="mini-textbox"  width="100%" required="true" /></td>
   			<td><label for="productStatus$text">产品状态</label></td>
            <td><input id="productStatus"  name="productStatus" class="mini-textbox" width="100%"  required="true" /></td>
   		</tr>
   		
   		<tr>
          	<td><label for="fproductId$text">FPRODUCT_ID</label></td>
            <td><input id="fproductId"  name="fproductId" class="mini-textbox" width="100%"  required="true" /></td>
   			<td><label for="moveNum$text">MOVENUM</label></td>
            <td><input id="moveNum"  name="moveNum" class="mini-textbox" width="100%"  required="true" /></td>
   			<td><label for="upLot$text">UP_LOT</label></td>
            <td><input id="upLot"  name="upLot" class="mini-textbox"  width="100%" required="true" /></td>
   			<td><label for="upSortIe$text">UP_SORTIE</label></td>
            <td><input id="upSortIe"  name="upSortIe" class="mini-textbox"  width="100%" required="true" /></td>
   		</tr>
   		
   		<tr>
          	<td><label for="finishNum$text">完成数量</label></td>
            <td><input id="finishNum"  name="finishNum" class="mini-textbox"  width="100%" required="true" /></td>
   			<td><label for="endSortIe$text">END_SORTIE</label></td>
            <td><input id="endSortIe"  name="endSortIe" class="mini-textbox"  width="100%" required="true" /></td>
   			<td><label for="planChanNo$text">PLANCHANNO</label></td>
            <td><input id="planChanNo"  name="planChanNo" class="mini-textbox"  width="100%" required="true" /></td>
   			<td><label for="productName$text">PRODUCT_NAME</label></td>
            <td><input id="productName"  name="productName" class="mini-textbox"  width="100%" required="true" /></td>
   		</tr>
   		
   		<tr>
        	<td><label for="spec$text">SPEC</label></td>
            <td><input id="spec"  name="spec" class="mini-textbox" width="100%"  required="true" /></td>
   		</tr>
   		
   		<tr height="60px;">
   			<td><label for="memo$text">附录</label></td>
	        <td colspan="7"><input id="memo"  name="memo" class="mini-textarea" width="100%"  emptyText="请输入备注" 
	        	required="true" width="100%" height="100%"/></td>
   			
         </tr>
   		<tr>
          	<td><input value="保存" type="button" onclick="getForm()" /></td>
   		</tr>
   	</table>
   </div>
   
   <script>
   		mini.parse();
   		function getForm(){
   			var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) return;
            var data = form.getData();
            var s = mini.encode(data);
            alert(s);
   		}
   </script>
  </body>
</html>
