<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客户信息</title>
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
  
  	<div class="mini-toolbar" style="padding:2px;border:0;">
		<a class="mini-button" iconCls="icon-find" plain="false" onclick="pass('5')">审核通过</a>
		<span class="separator"></span>
		<a class="mini-button" iconCls="icon-find" plain="false" onclick="pass('4')">审核不通过</a>
		<span class="separator"></span>
    	<a class="mini-button" iconCls="icon-ok" plain="false" onclick="pass('3')">提交上级审核</a>
    	<span class="separator"></span>
    	<a class="mini-button" iconCls="icon-undo" plain="false" onclick="javascript:window.history.back(-1);">返回</a>
    </div>
  	<fieldset style="width: 100%;" align="center">
		<legend>
			订单信息
		</legend>
		<div id= "userdiv">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr >
   			<td width="10%"><label for="orderId$text">订单编号</label></td>
            <td width="20%"><input id="orderId"  name="orderId" class="mini-textbox"  width="100%" required="true" value="${order.orderId}" enabled="false"/></td>
            <td width="10%"><label for="customer$text">客户</label></td>
            <td width="20%"><input id="customer" name="customer" class="mini-buttonedit" width="100%" enabled="false"
            	onbuttonclick="onButtonEdit" textName="companyName" required="true" value="${order.customer}" text="${order.companyName}"/>
   			<td width="15%"><label for="deptUser$text">接收部门</label></td>
            <td width="25%"><input id="deptUser"  name="deptUser" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..." enabled="false"
    			url="data/dept.txt" required="true" allowInput="false" showNullItem="true" nullItemText="请选择..." value="${order.deptUser}"/>  
            </td>
   		<!-- 	<td width="10%"><label for="connector$text">联系人</label></td>
            <td width="30%"><input id="connector"  name="connector" class="mini-textbox"  width="100%" required="true" enabled="false" value="${order.connector}"/></td>  -->
        </tr>
       	<tr>
       	    <td width="10%"><label for="connector$text">联系人</label></td>
            <td width="30%"><input id="connector"  name="connector" class="mini-textbox"  width="100%" required="true" enabled="false" value="${order.connector}"/></td>
            <td><label for="connectorTel$text">联系人电话</label></td>
            <td><input id="connectorTel"  name="connectorTel" class="mini-textbox"  width="100%" required="true" enabled="false" value="${order.connectorTel}"/></td>
   			
   	<!-- 	<td><label for="deptUser$text">接收部门</label></td>
            <td><input id="deptUser"  name="deptUser" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..." enabled="false"
    			url="data/dept.txt" required="true" allowInput="false" showNullItem="true" nullItemText="请选择..." value="${order.deptUser}"/>     
            </td>   -->	
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
            <td>下载合同</td>
   			<td align="left"><a href="DownLoadOrderFileServlet?filename=${order.orderPaper}">${order.orderPaper}</a><br/></td>
 <!--            <td><label for="download$text">文件</label></td>  --> 
            <!--
            <td style="text-align: center;">
            	<a class="mini-button" iconCls="icon-download" plain="false"  onclick="download('1')">订单文件下载</a>
            	<a class="mini-button" iconCls="icon-download" plain="false"  onclick="download('2')">对账函下载</a>
            </td>
            -->
   		</tr>
   		<tr>
   <!-- 		<td>下载合同</td>
   			<td align="left"><a href="DownLoadOrderFileServlet?filename=${order.orderPaper}">${order.orderPaper}</a><br/></td>   -->	
   			
   			<td>下载对账函</td>
   			<td align="left"><a href="DownLoadOrderFileServlet?filename=${order.duizhanghan}">${order.duizhanghan}</a></td>
   			
   			<td>下载其他文件</td>
   			<td align="left"><a href="DownLoadOrderFileServlet?filename=${order.otherPaper}">${order.otherPaper}</a></td>
   		</tr>
   		<tr height="60px;">
   			<td><label for="confirmedAdvice$text">已有审核意见</label></td>
	        <td colspan="5"><input id="confirmedAdvice" name="confirmedAdvice" class="mini-textarea" width="100%" height="100%" value="${order.confirmAdvice}" readonly/></td>
       </tr>
   		<tr height="60px;">
   			<td><label for="confirmAdvice$text">审核意见</label></td>
	        <td colspan="5"><input id="confirmAdvice" name="confirmAdvice" class="mini-textarea" emptyText="请输入审核意见" width="100%" height="100%"/></td>
       </tr>
   	</table>
   	
  	<div id="grid1" class="mini-treegrid" style="width:100%;height:93%;"
    	url="OrderAllDetailListServlet?orderId=${order.orderId}" showTreeIcon="true" 
    	treeColumn="productName" idField="productId" parentField="fproductId" resultAsTree="false"  
    	allowResize="true" expandOnLoad="true"
	>
        <div property="columns">
            <div type="indexcolumn"></div>
            <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
            <!-- 
            <div field="orderId" width="100" headerAlign="center">订单编号</div>
            <div field="companyName" width="100" headerAlign="center">客户名称</div>
            <div field="fproductId" width="100" headerAlign="center">父产品号</div>
             -->
            <div field="productId" width="100" headerAlign="center">产品号</div>
            <div field="productName" name="productName" width="100" headerAlign="center">产品名称</div>
            <div field="drawingId" width="100" headerAlign="center">图号</div>
            <div field="issueNum" width="50" headerAlign="center">版本号</div>
            <div field="spec" width="100" headerAlign="center">产品规格</div>
            <!--
            <div field="madePlace" width="100" headerAlign="center">制造单位</div>
            -->
            <!--
            <div field="deptName" width="100" headerAlign="center">使用部门</div>
            -->
            <div field="bTime" width="80" headerAlign="center" dateFormat="yyyy-MM-dd">开始时间</div>
            <div field="eTime" width="80" headerAlign="center"  dateFormat="yyyy-MM-dd">结束时间</div>
            <!--
            <div field="SBTime" width="100" headerAlign="center" dateFormat="yyyy 年 MM 月 dd 日">实际开始时间</div>
            <div field="SETime" width="100" headerAlign="center" dateFormat="yyyy 年 MM 月 dd 日">实际结束时间</div>
             -->
            <div field="productNum" width="60" headerAlign="center">产品数量</div>
            <div field="finishNum" width="60" headerAlign="center">完成数量</div>
 <!--       <div field="productStatus" width="60" headerAlign="center">产品状态</div>  -->     
            <div field="unitPrice" width="50" headerAlign="center">单价</div>
            <!--
            <div field="unitPrice" width="100" headerAlign="center">单价</div>
            <div field="isDuiZhangHan" width="100" headerAlign="center">有无对账函</div>
            <div field="isLaiLiao" width="100" headerAlign="center">是否来料</div>
            <div field="isJiaoHuo" width="100" headerAlign="center">是否交货</div>
            -->
        </div>
    </div>
    </fieldset>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str = "<a style='margin-right:2px;' title='查看' href=javascript:ondEdit(\'" + e.row.orderId+"\',\'"+e.row.productId + "\') ><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
	    
	    function ondEdit(orderId,productId){
	        //window.open("EditOrderDetailServlet?orderId=" + orderId,
	        //        "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
	    	window.location="OrderConfirmDetail?orderId="+orderId+"&productId="+productId;
		}
	    //var Genders = [{ id: 'M', text: '男' }, { id: 'W', text: '女'}];
	    var Genders = [{id: "1", text: "新建"},{id: "2", text: "备料"},{id: "3", text: "代加工"},{id: "4", text: "加工中"},{id: "5", text: "完成"}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
        
        
        function pass(orderStatus){
        	var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
            	//data.leave=leave;
            	data.orderStatus=orderStatus;
                var params = eval("("+mini.encode(data)+")");
                var url = 'OrderConfirmServlet';
   		        jQuery.post(url, params, callbackFun, 'json');
            }
		}
		
		function callbackFun(data)
   	    {
   	         alert(data.result);
   	      	 window.location.href = window.location.href;
   	    }

        
    </script>
  </body>
</html>
