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
  
  	<div>
  	名称  <input id="itemName" name="itemName" class="mini-textbox" onenter="onSearch()"/>
  	<a class="mini-button" plain="fasle" onclick="onSearch()">查询</a>
  	
  	</div>
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="StockServlet">
        <div property="columns">
            <div type="indexcolumn"></div>
            <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower" cellStyle="padding:0;">操作</div>
            <div field="itemId" width="100" headerAlign="center" align="center">编号</div>
            <div field="itemName" width="100" headerAlign="center" align="center">名称</div>
            <div field="spec" width="100" headerAlign="center" align="center">规格</div>"
            <div field="itemTypeDesc" width="100" headerAlign="center" align="center">类型</div>
            <div field="warehouseName" width="100" headerAlign="center" align="center">库房</div>
            <div field="stockId" width="100" headerAlign="center" align="center">库位</div>
            <div field="stockNum" width="100" headerAlign="center" align="center">库存量</div>
            <div field="unitPrice" width="100" headerAlign="center" align="center">单价</div>
            <div field="unit" width="100" headerAlign="center" align="center">单位</div>
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load({itemName:""});
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
	    
	    function ondEdit(itemId){
	        window.location.href = "EditStockServlet?itemId="+itemId;
		}

	 function onSearch(){
	 	var itemName=mini.get("itemName").getValue();
	 	grid.load({itemName:itemName});
	 
	 }
        
    </script>
  </body>
</html>
