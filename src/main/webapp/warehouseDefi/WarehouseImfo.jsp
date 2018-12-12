<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>库房信息</title>
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
   <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"borderStyle="border:0;" 
   multiSelect="true" idField="id" showSummaryRow="true" allowAlternating="true" 
   showPager="true" url="WarehouseServlet">
   <div property="columns">
		<div type="indexcolumn"></div>   
		 <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower" cellStyle="padding:0;">操作</div>
            <div field="warehouseid" width="100" headerAlign="center">库房编号</div>
            <div field="warehousename" width="100" headerAlign="center">库房名称</div>
            <div field="shelfnum" width="100" headerAlign="center">货架数量</div> 
            <div field="shelfstorey" width="100" headerAlign="center">层/货架</div>
            <div field="shelfcolumn" width="100" headerAlign="center">列/货架</div> 
            <div field="whaddr" width="100" headerAlign="center">地址</div>
            <div field="whphone" width="100" headerAlign="center">联系电话</div>
            <div field="empName" width="100" headerAlign="center">管理员</div>
            
   </div>
   </div>
   <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    
	      function onOperatePower(e) {
	        var str = "";
	      //  str += "<span>";
	       // str += "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.checkin_id+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	      //  str += "</span>";
	     
	    	str += "<span>";
	        str += "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.warehouseid+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }

	  function ondEdit(warehouseid){
	  		window.location.href="editWarehouseServlet?warehouseid="+warehouseid;

	 	}
        
    </script> 
   
   
  </body>
</html>
