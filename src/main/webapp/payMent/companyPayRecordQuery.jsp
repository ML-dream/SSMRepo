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
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>客户回款记录查询</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body style="height:100%;">
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="payRecordQueryListServlet?companyId=${companyId }" ondrawsummarycell="onDrawSummaryCell">
        <div property="columns"> 
            <div type="indexcolumn">序号</div>
            <div field="payDate" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">回款日期</div>
            <div field="companyName" headerAlign="center" align="center">客户</div>
            <div field="connector" headerAlign="center" align="center">联系人</div>
            <div field="thisPaid" headerAlign="center" align="center">回款金额</div>

    	</div>
    </div>
    
    <script type="text/javascript">
    mini.parse();
    var grid=mini.get("grid1");
    grid.load();
    
    function onDrawSummaryCell(e) {
            var result = e.result;
            var grid = e.sender;
            if (e.field == "thisPaid") {                
                var s = "<b style='color:red;'>"
                s +=  	"已回款："+ result.totalPrice + "<br/>";
                e.cellHtml = s;
            }
      }
    </script>
  </body>
</html>
