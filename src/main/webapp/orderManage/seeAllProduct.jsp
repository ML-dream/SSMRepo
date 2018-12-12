<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>订单下产品</title>
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
  		<a class="mini-button" iconCls="icon-undo" plain="false"  onclick="back()">返回</a>
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
            <td><label for="download$text">文件</label></td>
            <td style="text-align: center;">
            	<a class="mini-button" iconCls="icon-download" plain="false" href="DownLoadOrderFileServlet?filename=${order.orderPaper}">合同下载</a>
            	<a class="mini-button" iconCls="icon-download" plain="false"  onclick="download('2')">对账函下载</a>
            	<a class="mini-button" iconCls="icon-download" plain="false"  onclick="download('3')">其他文件下载</a>
            </td>
            <input id="eday" name="eday" class="mini-hidden" value="${order.eday}"/>
            <input id="bday" name="bday" class="mini-hidden" value="${order.bday}"/>
            <input id="creater" name="creater" class="mini-hidden" value="${order.creater}"/>
            <input id="rowIndex" name="rowIndex" class="mini-hidden" value="${order.rowIndex}"/>
            <input id="page" name="page" class="mini-hidden" value="${order.page}"/>
            
             <input id="Fcustomer" name="Fcustomer" class="mini-hidden" value="${order.fcustomer}"/>
              <input id="status" name="status" class="mini-hidden" value="${order.status}"/>
              <input id="orderMode" name="orderMode" class="mini-hidden" value="${param.orderMode}"/>
   		</tr>
   </table>
   	
  	<div id="grid1" class="mini-datagrid" style="width:99%;height:85%;"  showSummaryRow="true" ondrawsummarycell="onDrawSummaryCell"
    	url="OrderAllDetailListServlet?orderId=${order.orderId}&para=seeAll" showTreeIcon="true" idField="id"
    	treeColumn="productName" idField="productId" parentField="fproductId" resultAsTree="false"  dataField="data"
    	allowResize="true" expandOnLoad="true"
	>
        <div property="columns">
            <div type="indexcolumn" width="10"></div>
            <div name="action" width="20" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
            <div field="productId" width="60" headerAlign="center">产品号</div>
            <div field="productName" name="productName" width="60" headerAlign="center">产品名称</div>
            <div field="drawingId" width="60" headerAlign="center">图号</div>
            <div field="issueNum" width="20" headerAlign="center">版本号</div>
            <div field="spec" width="30" headerAlign="center">产品规格</div>
            <div field="bTime" width="40" headerAlign="center" dateFormat="yyyy-MM-dd">开始时间</div>
            <div field="eTime" width="40" headerAlign="center"  dateFormat="yyyy-MM-dd">结束时间</div>
            <div field="productNum" width="30" headerAlign="center">产品数量</div>
            <div field="finishNum" width="30" headerAlign="center">完成数量</div>
            <div field="productStatus" width="30" headerAlign="center" visible="true" renderer="onGenderRenderer">产品状态</div>
            <div field="unitPrice" width="50" headerAlign="center">单价</div>
            <!--<div field="quotationTotal" width="30" headerAlign="center">报价</div>
            
        --></div>
    </div>
    
    </fieldset>
    <!--endprint-->
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    
	    
	    function back(){
	    	var bday = mini.get("bday").getFormValue();
	    	var eday = mini.get("eday").getFormValue();
	    	
	    	var creater = mini.get("creater").getValue();
	    	var status = mini.get("status").getValue();
	    	var customer = mini.get("Fcustomer").getValue();
	    	var orderMode =  mini.get("orderMode").getValue();
	    	
	    	var rowIndex = mini.get("rowIndex").getValue();
	    	var page = mini.get("page").getValue();
	    	
	    	window.location="orderManage/backAllOrder.jsp?bday="+bday+"&eday="+eday+"&creater="+creater+"&status="+status+"&customer="+customer+"&rowIndex="+rowIndex+"&page="+page+"&orderMode="+orderMode;
	    }
	    function onDrawSummaryCell(e){
	    	var result = e.result;
            var grid = e.sender;
	    	if (e.field == "unitPrice") {                
                var s = "<span style='color:red;'>"
                s +=    "订单总价:" + result.sum +"</span>";
                e.cellHtml = s;
            }
	    	
	    }
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str = "<a style='margin-right:2px;' title='查看' href=javascript:ondEdit(\'" + e.row.orderId+"\',\'"+e.row.productId + "\') ><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
	        str += "</span>";
	        return str;
	    }
	    
	    function ondEdit(orderId,productId){
	        window.location="OrderDetailSpecServlet?orderId=" + orderId+"&productId="+productId+"&para=seeAll";
	        //ShowOrderDetailSpecServlet
		}
	    //var Genders = [{ id: 'M', text: '男' }, { id: 'W', text: '女'}];
	    var Genders = [{id: "0", text: "新建"},{id: "60", text: "正在加工"},{id: "70", text: "加工完成"},	{id: "80", text: "正在交付"},	{id: "90", text: "交付完成"}	];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
        
    </script>
  </body>
</html>
