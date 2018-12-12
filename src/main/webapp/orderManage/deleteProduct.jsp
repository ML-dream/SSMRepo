<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>删除产品</title>
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
  	<div class="mini-toolbar">
  		<a class="mini-button" iconCls="icon-remove" plain="false"  onclick="deleteProduct()">删除产品</a>
  		<a class="mini-button" iconCls="icon-undo" plain="false"  onclick="javascript:window.history.back(-1);">返回</a>
  	</div>
  	<!--startprint-->
  	<fieldset id="allDetail" style="width: 100%;" align="center">
		<legend>
			订单信息
		</legend>
		<div id= "userdiv">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%" >
   		<tr >
   			<td width="10%"><label for="orderId$text">订单编号</label></td>
            <td width="20%"><input id="orderId"  name="orderId" class="mini-textbox"  width="100%" required="true" value="${order.orderId}" readonly/></td>
            <td width="10%"><label for="customer$text">客户</label></td>
            <td width="20%"><input id="customer" name="customer" class="mini-buttonedit" width="100%" enabled="false"
            	onbuttonclick="onButtonEdit" textName="companyName" required="true" value="${order.customer}" text="${order.companyName}"/>
   			<td width="10%"><label for="connector$text">联系人</label></td>
            <td width="30%"><input id="connector"  name="connector" class="mini-textbox"  width="100%" required="true" enabled="false" value="${order.connector}"/></td>
        </tr>
       	<tr>
            <td><label for="connectorTel$text">联系人电话</label></td>
            <td><input id="connectorTel"  name="connectorTel" class="mini-textbox"  width="100%" required="true" enabled="false" value="${order.connectorTel}"/></td>
   			
   			<td><label for="deptUser$text">使用部门</label></td>
            <td><input id="deptUser"  name="deptUser" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..." enabled="false"
    			url="data/dept.txt" required="true" allowInput="false" showNullItem="true" nullItemText="请选择..." value="${order.deptUser}"/>  
            </td>
            <td><label for="orderDate$text">订单日期</label></td>
            <td><input id="orderDate"  name="orderDate" class="mini-textbox"  width="100%" required="true"  emptyText="日期格式：2000-01-01" value="${order.orderDate}" enabled="false"/></td>
        </tr>
       	<tr>
       		<td><label for="endTime$text">交付日期</label></td>
            <td><input id="endTime"  name="endTime" class="mini-textbox"  width="100%" required="true"  emptyText="日期格式：2000-01-01" value="${order.endTime}" enabled="false"/></td>
            <td><label for="orderStatus$text">订单状态</label></td>
            <td><input id="orderStatus"  name="orderStatus" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..." enabled="false"
    			url="data/orderStatus.txt"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..." value="${order.orderStatus}"/>  
            </td>
            <!--<td><label for="download$text">文件</label></td>
            <td style="text-align: center;">
            	<a class="mini-button" iconCls="icon-download" plain="false" href="DownLoadOrderFileServlet?filename=${order.orderPaper}">合同下载</a>
            	<a class="mini-button" iconCls="icon-download" plain="false"  onclick="download('2')">对账函下载</a>
            	<a class="mini-button" iconCls="icon-download" plain="false"  onclick="download('3')">其他文件下载</a>
            </td>
   		--></tr>
   </table>
   	
  	<div id="grid1" class="mini-datagrid" style="width:99%;height:93%;"
    	url="OrderAllDetailListServlet?orderId=${order.orderId}" 
    	allowResize="true" expandOnLoad="true"  allowUnselect="true"
	>
        <div property="columns">
            <div type="checkcolumn" ></div>
            <div field="productId" width="60" headerAlign="center">产品号</div>
            <div field="productName" name="productName" width="60" headerAlign="center">产品名称</div>
            <div field="drawingId" width="60" headerAlign="center">图号</div>
            <div field="issueNum" width="20" headerAlign="center">版本号</div>
            <div field="spec" width="50" headerAlign="center">产品规格</div>
            <div field="bTime" width="40" headerAlign="center" dateFormat="yyyy-MM-dd">开始时间</div>
            <div field="eTime" width="40" headerAlign="center"  dateFormat="yyyy-MM-dd">结束时间</div>
            <div field="productNum" width="30" headerAlign="center">产品数量</div>
            <div field="finishNum" width="30" headerAlign="center">完成数量</div>
            <div field="productStatus" width="30" headerAlign="center">产品状态</div>
            <div field="unitPrice" width="30" headerAlign="center">单价</div>
            <div field="quotationTotal" width="30" headerAlign="center">报价</div>
            
        </div>
    </div>
    
    </fieldset>
    <!--endprint-->
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    
	    
	    var Genders = [{id: "1", text: "新建"},{id: "2", text: "备料"},{id: "3", text: "代加工"},{id: "4", text: "加工中"},{id: "5", text: "完成"}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
       function deleteProduct(){
       		var productId = "";
			var row = grid.getSelected();
			if(row){
				productId = row.productId;
			}else{
				alert("请选择具体零件");
				return;
			}
			var temp = confirm ("删除零件"+productId+",不可撤销,是否继续?");
			if(temp){
				var orderId = mini.get("orderId").getValue();
				$.ajax({
		       		type: "post",
		       		url:"DeleteProductId?productId="+productId+"&orderId="+orderId,
		       		cache: false,
		   			async:false,
		   			//data:{"data" : json},
		   			success:function(text){
		   				var data = mini.decode(text);
		   				alert(data.result);
		   				grid.reload();
		   			}
		   		})
			}else{
				return;
			}
       }
    </script>
  </body>
</html>
