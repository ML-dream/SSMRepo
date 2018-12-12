<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客户订单信息</title>
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
  					<input value="查看成本详情" type="button" style= "width:90px;" onclick="" />
  					<input value="查看零件库存" type="button" style= "width:90px;" onclick="seeWare()" />
 				</td>
   			</tr>
   			<tr style= "height:15px;"></tr>
   	 	</table>
   	 </form>
   	 <div id= "userdiv">
   	 	<table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr width="100%">
   			<td width="20%"><label for="orderId$text">订单编号*</label></td>
            <td width="35%" ><input id="orderId"  name="orderId" class="mini-textbox" width="100%"   value="${order.orderId}" enabled="false"/></td>
   			<td width="20%"><label for="orderDate$text">接收日期*</label></td>
            <td width="35%"><input id="orderDate"  name="orderDate" class="mini-textbox"  width="100%"   value="${order.orderDate} " enabled="false"/></td>
   		</tr>
   		<tr>
   			<td><label for="createPerson$text">接收人员*</label></td>
            <td><input id="createPerson"  name=""createPerson"" class="mini-textbox"  width="100%"   value="${order.createPerson} " enabled="false"/> 
            </td>
            <td ><label for="endTime$text">交付日期*</label></td>
            <td><input id="endTime"  name="endTime" class="mini-textbox"  width="100%"   value="${order.endTime} " enabled="false"/></td>
            <input id="customer"  name="customer" class="mini-hidden" width="100%"   value="${param.customer}" enabled="false"/>
   			<input id="bdate"  name="bdate" class="mini-hidden" width="100%"   value="${param.bdate}" enabled="false"/>
   			<input id="edate"  name="edate" class="mini-hidden" width="100%"   value="${param.edate}" enabled="false"/>
   			
   			<input id="row"  name="row" class="mini-hidden" width="100%"   value="${param.rowIndex}" enabled="false"/>
   			<input id="page"  name="page" class="mini-hidden" width="100%"   value="${param.page}" enabled="false"/>
   		</tr>
   			
   	</table>
  </div> 	
 </div>
    <div id="grid1" class="mini-datagrid" style="width:90%;height:85%;" allowResize="true"
         borderStyle="border:2;" multiSelect="false"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true" allowDrag="true"
        ondrawsummarycell="onDrawSummaryCell"  url="LoadCustomerProducts?orderId=${order.orderId}">
        <div property="columns">
            <div type="indexcolumn"></div>
            <div type="checkcolumn"></div>
            <div name="action" width="90" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
            <div field="productid" width="110" headerAlign="center">图号
            </div>
            <div field="productName" width="110" headerAlign="center">产品名称
            </div>
            <div field="productNum" width="80" headerAlign="center">产品数量
            </div>
            <div field="manuc" width="80" headerAlign="center">制造成本
            </div>
            <div field="allc" width="70" headerAlign="center">总成本
            </div>
            <div field="qualityLoss" width="80" headerAlign="center">质量损失
            </div>
            <div field="productprice" width="70" headerAlign="center" >产品总价
            </div>
            <div field="profit" width="70" headerAlign="center">利润
            </div>
            <div field="profitRate" width="60" headerAlign="center">利润率
            </div>
            <div field="productStatus" width="60" headerAlign="center" >产品状态
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    
	    function onDrawSummaryCell(e) {
            var result = e.result;
            var grid = e.sender;
            //服务端汇总计算
            if (e.field == "productid") {                
                var s = "<b style='color:red;'>"
                s +=  	"合计：<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "manuc") {                
                var s = "<b style='color:red;'>"
                s +=  	" "+ result.Smanuc + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "allc") {                
                var s = "<b style='color:red;'>"
                s +=  	" "+ result.Sallc + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "qualityLoss") {                
                var s = "<b style='color:red;'>"
                s +=  	" "+ result.SqualityLoss + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            
            if (e.field == "productprice") {                
                var s = "<b style='color:red;'>"
                s +=  	" "+ result.Sorderprice + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            if (e.field == "profit") {                
                var s = "<b style='color:red;'>"
                s +=  	" "+ result.Sprofit + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
        }
        
	    function seeWare(){
	    	var orderId = mini.get("orderId").getValue();
	    	window.location="GetOrderListServlet?orderId="+orderId;
	    }
	    function back(){
	    	var bdate = mini.get("bdate").getFormValue();
	    	var edate = mini.get("edate").getFormValue();
	    	var customer = mini.get("customer").getValue();
	    	
	    	var page = mini.get("page").getValue();
	    	var row = mini.get("row").getValue();
	    	
			window.location="BackCustomerOrders?bdate="+bdate+"&edate="+edate+"&customer="+customer+"&page="+page+"&row="+row;
	    }
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='工序详情' href=javascript:onAllDetail(\'" + e.row.productid+"\',\'"+e.rowIndex+"\') ><span class='mini-button-text mini-button-icon icon-lock'>&nbsp;</span></a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
	    
		function onAllDetail(productId,rowIndex){
			var orderId = mini.get("orderId").getValue();
			
			var page = grid.getPageIndex();
			window.location="ToCustomerFos?orderId="+ orderId+"&productId="+productId+"&rowIndex="+rowIndex+"&page="+page;
		}
		
        var Genders=[{id: "1", text: "新建"},{id: "2", text: "待审核"},{id: "3", text: "提交上级审核"},{id: "4", text: "审核不通过"},{id: "5", text: "审核通过"},	
	                {id: "6", text: "备料"},{id: "7", text: "代加工"},{id: "8", text: "加工中"},{id: "9", text: "完成"},{id: "10", text: "交付中"},{id: "11", text: "交付完成"}]
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }

        var dept = [{id:"jj",text:"机加事业部"},{id:"yy",text:"液压装备事业部"}];
        function onDeptRenderer(e) {
            for (var i = 0, l = dept.length; i < l; i++) {
                var g = dept[i];
                if (g.id == e.value) return g.text;
            }
            return "";
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
