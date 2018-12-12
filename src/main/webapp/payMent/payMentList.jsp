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
  
  <body style="height:99%">
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="payMentListServlet">
        <div property="columns"> 
            <div type="indexcolumn">序号</div>
             <div field="companyName" headerAlign="center" align="center">客户</div>
             <div field="connector" headerAlign="center" align="center">联系人</div>
              <div field="totalPrice" headerAlign="center" align="center">应回款</div>
               <div field="totalPaid" headerAlign="center" align="center">已回款</div>
                <div field="arrears" headerAlign="center" align="center">剩余欠款</div>
<!--         <div field="orderId" headerAlign="center" align="center">订单号</div>-->
<!--    	 <div field="itemId" headerAlign="center" align="center">图号</div>-->
<!--         <div field="itemName" headerAlign="center" align="center">名称</div>-->
<!--         <div field="issueNum" headerAlign="center" align="center">版本号</div>-->
<!--         <div field="drawingId" headerAlign="center" align="center">产品号</div>-->
<!--         <div field="checkoutNum" headerAlign="center" align="center">已发货数量</div>-->
<!--         <div field="unitprice" headerAlign="center" align="center">单价</div>-->
<!--         <div field="price" headerAlign="center" align="center">总价</div>-->
         
    
    
    
    	</div>
    </div>
    <script type="text/javascript">
      mini.parse();
      var grid=mini.get("grid1");
      grid.load();
      
      
      function onDrawSummaryCell(e) {
            var result = e.result;
            var grid = e.sender;
            if (e.field == "price") {                
                var s = "<b style='color:red;'>"
                s +=  	"总报价："+ result.totalPrice + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            }
      
      </script> 
  </body>
</html>
