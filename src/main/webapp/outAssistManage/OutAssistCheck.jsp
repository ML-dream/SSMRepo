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
		<a class="mini-button" iconCls="icon-find" plain="false" onclick="pass('3')">审核通过</a>
		<span class="separator"></span>
		<a class="mini-button" iconCls="icon-find" plain="false" onclick="pass('2')">审核不通过</a>	
    </div>
  	<fieldset style="width: 100%;" align="center">
		<legend>
			外协信息
		</legend>
		<div id= "userdiv">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr >
   			<td width="10%"><label for="menuId$text">外协单号</label></td>
            <td width="25%"><input id="menuId"  name="menuId" class="mini-textbox"  width="100%"  value="${outassistmenu.menuId}" enabled="false"/></td>
            <td width="10%"><label for="companyName$text">外协单位编号</label></td>
            <td width="25%"><input id="companyName" name="comapnyname" class="mini-textbox"  width="100%"  value="${outassistmenu.waiXieCom}" enabled="false"/>
   			<td width="10%"><label for="deliverTime$text">外协单位名称</label></td>
            <td width="20%"><input id="deliverTime"  name="deliverTime" class="mini-textbox"  width="100%"  value="${outassistmenu.companyName}" enabled="false" />  
            </td>
        </tr>
        <tr>
       	    <td><label for="connector$text">联系人</label></td>
            <td><input id="connector"  name="connector" class="mini-textbox"  width="100%"  enabled="false" value="${outassistmenu.connector}"/></td>
            <td><label for="connectorTel$text">联系人电话</label></td>
            <td><input id="connectorTel"  name="connectorTel" class="mini-textbox"  width="100%"  enabled="false" value="${outassistmenu.connectorTel}"/></td>
            <td><label for="address$text">地址</label></td>
            <td><input id="address"  name="address" class="mini-textbox"  width="100%"  value="${outassistmenu.address}" enabled="false"/></td>
        </tr>
       	<tr>
       		<td><label for="deliverTime$text">下达日期</label></td>
            <td><input id="deliverTime"  name="deliverTime" class="mini-textbox"  width="100%"   value="${outassistmenu.deliverTime}" enabled="false"/></td>
            <td><label for="totalNum$text">外协工序数量</label></td>
            <td><input id=totalNum"  name="totalNum" class="mini-textbox"  width="100%"   value="${outassistmenu.totalNum}" enabled="false"/></td>
            <td><label for="outAssistStatus$text">订单状态</label></td>
            <td><input id="outAssistStatus"  name="outAssistStatus" class="mini-combobox" style="width:100%;" textField="text" valueField="id"  enabled="false"
    			url="data/OutAssistStatus.txt"  required="true"  showNullItem="true"  value="${outassistmenu.outAssistStatus}"/>
    	    </td>
    	</tr> 
   		<tr height="60px;">
   			<td><label for="checkedAdvice$text">已有审核意见</label></td>
	        <td colspan="5"><input id="checkedAdvice" name="checkedAdvice" class="mini-textarea" width="100%" height="100%" value="${outassistmenu.checkedAdvice}" readonly/></td>
       </tr>
   		<tr height="60px;">
   			<td><label for="checkAdvice$text">审核意见</label></td>
	        <td colspan="5"><input id="checkAdvice" name="checkAdvice" class="mini-textarea" emptyText="请输入审核意见" width="100%" height="100%"/></td>
       </tr>
   	</table>
   
        <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="OutAssistMenuServlet?menuId=${outassistmenu.menuId}" allowCellSelect="true" allowCellEdit="true">
        <div property="columns">
            <div type="indexcolumn"></div> 
            <div field="orderId" width="120"  headerAlign="center">订单号 </div> 
            <div field="productId" width="80"  headerAlign="center">图号 </div>
            <div field="companyName" width="100" headerAlign="center">单位名称</div>
            <div field="productName" width="60" headerAlign="center">零件名称</div>
            <div field="operName" width="50" headerAlign="center">加工工序</div>
            <div field="num" width="30" headerAlign="center">数量</div>
            <div field="planStartTime" width="80" dateFormat="yyyy-MM-dd" headerAlign="center">发货日</div>
            <div field="planEndTime" width="80" dateFormat="yyyy-MM-dd"  headerAlign="center">交货日</div>
            <div field="unitPrice" width="40" headerAlign="center">单价</div>
            <div field="totalPrice" width="40" headerAlign="center">总价</div>
            <div field="memo" width="60"  headerAlign="center" >备注</div>
            <div field="productPrice" width="60"  headerAlign="center" >产品价格</div>
        </div>
    </div>
    </fieldset>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    var orderId=<%=request.getParameter("orderId")%>
	    grid.load({orderId:orderId});
	    
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
	    	window.location="OrderDetailSpecServlet?orderId=" + orderId+"&productId="+productId;
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
        
        
        function pass(outAssistStatus){
        	var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
            	//data.leave=leave;
            	data.outAssistStatus=outAssistStatus;
                var params = eval("("+mini.encode(data)+")");
                var url = 'OutAssistCheckServlet';
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
