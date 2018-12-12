<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <!-- miniui.js -->
    <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>回款信息</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body  style="height:99%">
  <div class="mini-toolbar">
<!--  <a class="mini-button" iconCls="icon-print" plain="false"  onclick="print()">打印</a>-->
<!--  <span class="separator"></span>-->
  </div>
   <form id="datepicker" action="" method="post">
  	<input id="startDate" name="startDate" class="mini-datepicker" showClearButton="false" showTodayButton="fasle"
  	showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false"/>&nbsp;&nbsp;&nbsp;&nbsp;
  	&nbsp;&nbsp;&nbsp;&nbsp;<input id="endDate" name="endDate" class="mini-datepicker" showClearButton="false" showTodayButton="fasle"
  	showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false"/>
  	<a class="mini-button" plain="fasle" onclick="onSelect()">查询</a>
  	<input id="companyId" name="companyId" class="mini-hidden" value="${companyId }"/>
  	</form> 
  	
  	<div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="showCompanyPayServlet?companyId=${companyId }" ondrawsummarycell="onDrawSummaryCell">
        <div property="columns"> 
            <div type="indexcolumn">序号</div>
            <div field="payDate" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">回款日期</div>
            <div field="thisPaid"  headerAlign="center" align="center">回款金额</div>
        </div>
    </div>  
    
    
    <script>
     mini.parse();
      var grid=mini.get("grid1");
      grid.load({startDate:"",endDate:""});
      var editWindow=mini.get("editWindow");
      
      
     function onDrawSummaryCell(e) {
            var result = e.result;
            var grid = e.sender;
            if (e.field == "thisPaid") {                
                var s = "<b style='color:red;'>"
                s +=  	"已回款："+ result.haspaid + "<br/>"
                		+"</b>";
                e.cellHtml = s;
            }
      } 
      
      function onSelect(){
      	var startDate=mini.get("startDate").getFormValue();
      	var endDate=mini.get("endDate").getFormValue();
      	grid.load({startDate:startDate,endDate:endDate});
      } 
    </script>
  </body>
</html>
