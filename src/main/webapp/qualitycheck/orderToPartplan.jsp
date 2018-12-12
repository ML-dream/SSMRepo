<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html >
<html>
<head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/o_js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/o_js/miniui/miniui.js"></script>
		<link href="<%=path %>/o_js/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/o_js/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>订单下的零件计划</title>
    <style type="text/css">
    </style>
</head>
<body>
	<div id="win1" class="mini-window" title="操作" style="width:450px;height:280px;" collapseOnTitleClick="true"
	    showMaxButton="true" showCollapseButton="true" showShadow="true" showCloseButton="true" expanded="true"
	    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
	    >
	    
		<fieldset style="width:400px;height:200px;" align="center"> 
			<legend>
				超期原因
			</legend>
			<table >
	   		<tr>
	   			<td>原因列表:</td>
	   			<td width="80%"><div id="cbl1" class="mini-checkboxlist" repeatItems="3" repeatLayout="table"
			        textField="text" valueField="id" value="" 
			        url="data/whyExceed.txt" >
			    </div>
	   			</td>
	   		</tr>
	   		<tr height="100px">
	   			<td>原因:</td>
	   			<td><textarea id="excReason" class="mini-textarea" emptyText="" width="100%" height="100%"></textarea></td>
	   		</tr>
	   		
	   		<tr>
	   			<td><input value="提交超期原因" type="button" onclick="upExceed()" style="width:100%;height:20px;"/></td>
	   		</tr>
	   	</table>
		</fieldset> 
	</div>
	</br>
	<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="80%" >
   		<tr >
   			<td width="10%"><label for="orderId$text">订单编号</label></td>
            <td width="20%"><input id="orderId"  name="orderId" class="mini-textbox"  width="100%" required="true" value="${order.orderId}" enabled="false"/></td>
            <td width="10%"><label for="customer$text">客户</label></td>
            <td width="20%"><input id="customer" name="customer" class="mini-buttonedit" width="100%" enabled="false"
            	onbuttonclick="onButtonEdit" textName="companyName" required="true" value="${order.customer}" text="${order.companyName}"/>
   			<td width="10%"><label for="deptUser$text">接收部门</label></td>
            <td width="30%"><input id="deptUser"  name="deptUser" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..." enabled="false"
    			url="data/dept.txt" required="true" allowInput="false" showNullItem="true" nullItemText="请选择..." value="${order.deptUser}"/>  
            </td>
        </tr>
       	<tr>
       	    <td width="10%"><label for="connector$text">联系人</label></td>
            <td width="30%"><input id="connector"  name="connector" class="mini-textbox"  width="100%" required="true" enabled="false" value="${order.connector}"/></td>
            <td><label for="connectorTel$text">联系人电话</label></td>
            <td><input id="connectorTel"  name="connectorTel" class="mini-textbox"  width="100%" required="true" enabled="false" value="${order.connectorTel}"/></td>
   			
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
        </tr>
       	<!--<tr>
            <td><label for="endTime$text">超期原因:</label></td>
            <td><textarea id="whyOver" class="mini-textarea" emptyText="" width="100%" ></textarea></td>
            <td><input value="添加超期原因" type="button" onclick="whyExceed()" style="width:100%"/></td>
        </tr>
   	--></table>
	

	  <div id="grid1" class="mini-treegrid" style="width:80%;height:500px"
    	url="ProcessSumOrder?orderId=${order.orderId}" showTreeIcon="true" 
    	treeColumn="productName" idField="productId" parentField="fproductId" resultAsTree="false"  onrowclick="rowselect()"
    	allowResize="true" expandOnLoad="true" showPager= "true" showPageInfo="true" showReloadButton = "true" showPagerButtonIcon="true"
	>
        <div property="columns">
            <div type="indexcolumn"></div>
            <div field="productId" width="100" headerAlign="center">图号</div>
            <div field="productName" name="productName" width="100" headerAlign="center">产品名称</div>
            <div field="issueNum" width="50" headerAlign="center">版本号</div>
            <div field="productNum" width="60" headerAlign="center">产品数量</div>
            <div field="finishNum" width="60" headerAlign="center">完成数量</div>
            <div field="productStatusName" width="60" headerAlign="center">产品状态</div>
        </div>
    </div>
	   			
</body>
<script type="text/javascript">
	mini.parse();
	var grid = new mini.get("grid1");
	grid.load();
	
	function upExceed(){
		var already= mini.get("excReason").getValue();
		var others= mini.get("cbl1").getValue();
		var para = others+","+already;
		var orderId= mini.get("orderId").getValue();
		
		$.ajax({
       		type: "post",
       		url:"UpExceed",
       		cache: false,
   			async:false,
   			data:{"orderId" : orderId, "para":para},
   			success:function(text){
   				var data = mini.decode(text);
   				alert (data.result);
   			},
   			error : function() {
				alert("加载失败");
			}
       	});
		//alert(others+","+already);
		// hideWindow(); 
	}
	function whyExceed(){
	//点击添加超期原因
		showAtPos();
		var para= mini.get("whyOver").getValue();
		mini.get("excReason").setValue(para);
	}

	function showAtPos() {
        var win = mini.get("win1");
		//var win2 = mini.get("win2");
        var x = "right";
        var y = "top";

        win.showAtPos(x, y);
		//win2.showAtPos("center", y);
    }
	//showAtPos();
	function hideWindow() {
        var win = mini.get("win1");
        win.hide();
    }
	hideWindow();
	function rowselect(){
		var row = grid.getSelected();
		row.orderId  = mini.get("orderId").getValue();
		var partsplanid = "";
		var url = "qualitycheck/processSumDetail.jsp";
		if(row){
			partsplanid = row.partsplanid;
			/*
			 window.open("EditPriceManHourServlet?craftId=" + craftId,
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
			*/
			mini.open({
			    url: "qualitycheck/processSumDetail.jsp",
			    title: "进度详情", 
			    width: 920, height: 650,
			    onload: function () {
			    	var iframe = this.getIFrameEl();
               		iframe.contentWindow.setData(row);
			    },
			    ondestroy: function (action) {
			    }
			});
		}
	}
</script>
</html>