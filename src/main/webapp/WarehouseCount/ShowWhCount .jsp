<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>库存盘点记录</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>
   <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;" borderStyle="border:0;" 
    multiSelect="true" idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
     url="ShowWhCountServlet">
      	<div property="columns">
            <div type="indexcolumn"></div>
    		<div name="action" width="60" headerAlign="center" align="center" renderer="onOperatePower"
    		cellStyle="padding:0;">操作</div>
    		<div field="countSheetid" headerAlign="center" width="100">单号</div>
    		<div field="countDate" headerAlign="center" width="80" dateFormat="yyyy-MM-dd">开单日期</div>
    		<div field="warehouseName" headerAlign="center" width="80">库房</div>
    		<div field="operatorName" headerAlign="center" width="80">操作人</div>
    		<div field="deptName" headerAlign="center" width="80">部门</div>
    		<div field="empName" headerAlign="center" width="60">经办人</div>
    		
    
    	</div>
    </div>
    
     <script type="text/javascript">
    mini.parse();
    var grid=mini.get("grid1");
    grid.load();
    
    
    function onOperatePower(e){
    var str="";
    str+="<span>";
    str+="<a style='margin-right:2px;' title='查看详情' href=javascript:ondSee(\'"+e.row.countSheetid+"\')><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
    str+="</span>";
    str+="<span>";
    str+="<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'"+e.row.countSheetid+"\')><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
    str+="</span>";
    return str;
    }
    
    
    function ondSee(countSheetid){
    window.location="seeWhCountDetailServlet?countSheetid="+countSheetid;
    
    }
    
    function ondEdit(countSheetid){
    window.location="editWhCountServlet?countSheetid="+countSheetid;
    
    }
    
    </script>
  </body>
</html>
