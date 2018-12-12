<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>入库审核</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script> 
<!-- 	<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>        -->
	<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
	
    <script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
    <style type="text/css">
    body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
    }
    </style>
  </head>
  
  <body>
  	<div>
  	<input id="startDate" name="startDate" class="mini-datepicker" showClearButton="false" showTodayButton="fasle"
  	showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false"/>&nbsp;&nbsp;&nbsp;&nbsp;
  	&nbsp;&nbsp;&nbsp;&nbsp;
  	<input id="endDate" name="endDate" class="mini-datepicker" showClearButton="false" showTodayButton="fasle"
  	showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false"/>
  	<a class="mini-button" plain="fasle" onclick="onSearch()">查询</a>
  	<a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()" >刷新</a>
  	</div>
     <div id="grid1" class="mini-datagrid" style="width:100%;height:98%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="examineProductCheckinServlet">
        <div property="columns">
            <div type="indexcolumn"></div>
    		<div name="action"  headerAlign="center" align="center" width="60" renderer="onOperatePower">操作</div>
    		<div field="checkindate" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">入库日期</div>
    		<div field="checksheetId" headerAlign="center" align="center">入库单号</div>
    		<div field="orderId" headerAlign="center" align="center">订单号</div>
    		<div field="productId" headerAlign="center" align="center">图号</div>
    		<div field="productName" headerAlign="center" align="center">产品名称</div>
    		<div field="drawingId" headerAlign="center" align="center">产品编号</div>
    		<div field="spec" headerAlign="center" align="center">规格</div>
    		<div field="createpersonName" headerAlign="center" align="center">质检员</div>
			<div field="status" headerAlign="center" align="center" renderer="onGrenderRenderer">状态</div>    		
    	</div>    
    </div>
   
    <script>
    mini.parse();
    var grid=mini.get("grid1");
    grid.load({startDate:"",endDate:""});
    
    
    function onOperatePower(e){
    	var str="";
    	str+="<span>";
    	str+="<a style='margin-right:2px;' title='审核' href=javascript:ondExamine(\'"+e.row.checksheetId+"\')><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
   		str+="</span>";
   		return str;
    }
    
    function ondExamine(checksheetId){
    	window.location="examineDetailCheckinServlet?checksheetId="+checksheetId;
    
    
    }
    
    function onSearch(){
      	var startDate=mini.get("startDate").getFormValue();
      	var endDate=mini.get("endDate").getFormValue();
      	grid.load({startDate:startDate,endDate:endDate});
      }
      
     var Genders = [{id: '0', text: '待审核'},{id: '1', text: '审核通过'},{id:'2',text:'审核不通过'}];
    function onGrenderRenderer(e){
    	for(var i=0,l=Genders.length;i<l;i++){
    		var g = Genders[i];;
    		if(g.id==e.value)return g.text;
    	
    	}
    	return "";
    }
    </script>
    </body>
</html>
