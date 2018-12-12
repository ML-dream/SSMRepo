<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>工艺重做</title>
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
    <div id="grid1" class="mini-treegrid" idField="productId" parentField="fproductId"  treeColumn="productId" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="false"   showSummaryRow="false" allowAlternating="true" showPager="true" pageSize="50" showLoading="false"
         url="CraftModifyListServlet">
        <div property="columns">
            <div type="indexcolumn"></div>
            <!-- 
            <div name="action" width="40" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
             -->
            <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
            <div field="orderId" width="90" headerAlign="center">订单号
            </div>
            <div field="fproductId" width="70" headerAlign="center">父产品号
            </div>
            <div field="productId" name="productId" width="70" headerAlign="center">图号
            </div>
            <div field="productName" name="productId" width="70" headerAlign="center">名称
            </div>
            <div field="issueNum" width="50" headerAlign="center">版本号
            </div>
            <div field="drawingId" width="50" headerAlign="center">产品号
            </div>
            <div field="rejectNum" width="50" headerAlign="center">不合格数量
            </div>
            <div field="state" width="60" headerAlign="center" renderer="onGenderRenderer">处理状态
            </div>
            <div field="barcode" width="50" headerAlign="center" visible="true">条码号 </div>
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='操作' href=javascript:ondEdit(\'" + e.row.orderId+"\',\'"+e.row.productId + "\',\'"+e.row.issueNum +"\',\'"+e.row.drawingId+"\',\'"+e.row.fproductId+"\',\'"+e.row.barcode +"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";                              
	        //alert(e.row.staffCode);
	        return str;
	    }
	    
	    function ondEdit(orderId,productId,issueNum,drawingId,fproductId,barcode){
	        //window.open("EditOrderDetailServlet?orderId=" + orderId,
	        //        "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
	    	window.location="BaseServlet?meth=GoCraftMainBody1&pathTo=mainBody1&orderId="+orderId+"&productId="+productId+"&issueNum="+issueNum+"&drawingId="+drawingId+"&FProductId="+fproductId+"&barcode="+barcode;
		}

    </script>
  </body>
</html>
