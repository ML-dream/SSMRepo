<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工序成本详情</title>
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
  
  	<form id="form1">
	   	<table>
   		<!-- 表头 -->
   			<tr style= "height:10px;"></tr>
   			<tr style= "height:20px;">
   				<td style= "width:70px;"></td>
   				<td>合同号</td>
				<td>
					<input id="orderid" name ="orderid" class="mini-textbox"  allowInput="false" value ="${orderid}" visiblity = ""/>
 				</td>
 				<td>零件图号</td>
 				<td>
					<input id="productid" name ="productid" class="mini-textbox"  allowInput="false" value ="${productid}" visiblity = ""/>
 				</td>
   			</tr>
   			<tr style= "height:15px;"></tr>
   	 	</table>
   	 </form>
   	 
	<div id="tablediv">
   	 <div id="datagrid1" class="mini-datagrid" style="width:780px;height:400px;" 
        url="LoadAllTimeC" idField="id" allowResize="true" pageSize="10"   multiSelect="true" allowCellSelect="true" allowCellEdit="true"
    	showPager= "true" showPageInfo="true" showReloadButton = "true" showPagerButtonIcon="true"  editNextOnEnterKey= "true"
    >
 		<div property="columns">
            <div type="checkcolumn" visible="false"></div>
            
            <div field="fono" width="60" headerAlign="center" allowSort="false">工序号</div>
            <div field="foname" width="60" headerAlign="center" allowSort="false">工序名称
            </div>    
            <div field="fotype" width="60" headerAlign="center" allowSort="false">工种
            </div> 
            <div field="price" width="60" headerAlign="center" allowSort="false" visible="true">单价
            </div>
            <div field="timec" width="80" headerAlign="center" allowSort="false" visible="true">奖惩后工时
            </div>
            <div field="timem" width="80" headerAlign="center" allowSort="false" visible="true">工时成本
            </div>
        </div>
     </div>
     
   </div>    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("datagrid1");
		var orderid = mini.get("orderid").getValue();
		var productid = mini.get("productid").getValue();	    
	    
	    grid.load({"orderid":orderid,"productid":productid});
	    
        
    </script>
  </body>
</html>
