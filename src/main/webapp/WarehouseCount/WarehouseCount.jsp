<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>库存信息</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/js.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/pagecard.css">
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  
  <body>
  	 <div class="mini-toolbar">
   	<a class="mini-button" iconCls="icon-add" plain="false" onclick="add()">初始数据维护</a>
  	</div>
  	<div>
  	<form id="datepicker" action="" method="post">
  	起始日期：<input id="startDate" name="startDate" class="mini-datepicker" showClearButton="false" showTodayButton="fasle"
  	showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false"/>&nbsp;&nbsp;&nbsp;&nbsp;
  	&nbsp;&nbsp;&nbsp;&nbsp;
  	截止日期：<input id="endDate" name="endDate" class="mini-datepicker" showClearButton="false" showTodayButton="fasle"
  	showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false"/>
  	<a class="mini-button" plain="fasle" onclick="onSearch()">查询</a>
  	</form>
  	</div>
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="WarehouseCountStatisticsServlet?warehouseId=${warehouseId }">
        <div property="columns">
            <div type="indexcolumn"></div>
            <div field="countDate" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">盘点日期</div>
            <div field="warehouseName" width="100" headerAlign="center" align="center">库房</div>
            <div field="itemId" width="100" headerAlign="center" align="center">编号</div>
            <div field="itemName" width="100" headerAlign="center" align="center">名称</div>
            <div field="spec" width="100" headerAlign="center" align="center">规格</div>
            <div field="beginCountPrice" width="100" headerAlign="center" align="center">初期结存金额</div>
            <div field="beginCountNum" width="100" headerAlign="center" align="center">初期结存数量</div>
            <div field="unit" width="100" headerAlign="center" align="center">单位</div>
            <div field="checkinNum" width="100" headerAlign="center" align="center">收入数量</div>
            <div field="checkoutNum" width="100" headerAlign="center" align="center">发出数量</div>
            <div fielfd="endCountNum" width="100" headerAlign="center" align="center">结存数量</div>
        </div>
    </div>
    <input id="warehouseId" name="warehouseId" class="mini-textbox" value="${warehouseId }"/>
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    grid.on("drawcell",function (e) {
            var record = e.record,
        	column = e.column,
        	field = e.field,
        	value = e.value;
	    	if (field == "stockNum" && value <= 20) {
                e.cellStyle = "color:red;font-weight:bold;";
            }
	    	
	    	}
	    
	    
	    
	    );
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str = "<a style='margin-right:2px;' title='编辑'  href=javascript:ondEdit(\'" + e.row.itemId+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
	    
		function add(){
			var warehouseId=mini.get("warehouseId").getValue();
    		window.open("getAddWarehouseCountWarehouseIdServlet?warehouseId="+warehouseId,
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
	                
    	}
	/* function onSearch(){
	 	var itemName=mini.get("itemName").getValue();
	 	grid.load({itemName:itemName});
	 
	 }*/
        
    </script>
  </body>
</html>
