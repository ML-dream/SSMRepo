<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>客户信息</title>
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
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="OrderListServlet">
        <div property="columns">
            <div type="indexcolumn"></div>
            <!-- 
            <div name="action" width="40" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
             -->
            <div name="action" width="90" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
            <div field="orderId" width="110" headerAlign="center">订单编号
            </div>
            <div field="companyName" width="100" headerAlign="center">客户名称
            </div>
            <div field="connector" width="50" headerAlign="center">联系人
            </div>
            <div field="connectorTel" width="60" headerAlign="center">联系人电话
            </div>
            <div field="deptName" width="50" headerAlign="center">使用部门
            </div>
            <div field="orderDate" width="80" headerAlign="center" dateFormat="yyyy 年 MM 月 dd 日">订单日期
            </div>
            <div field="endTime" width="80" headerAlign="center"  dateFormat="yyyy 年 MM 月 dd 日">交付日期
            </div>
            <div field="orderStatus" width="60" headerAlign="center" renderer="onGenderRenderer">订单状态
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    
	    function onOperatePower(e) {
	        var str = "";
//	        str += "<span>";
//	        str += "<a style='margin-right:2px;' title='编辑订单' href=javascript:ondEdit(\'" + e.row.orderId+"\',\'"+e.row.connector + "\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
//	        str += "</span>";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='查看订单' href=javascript:ondSee(\'" + e.row.orderId+"\',\'"+e.row.connector + "\') ><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
	        str += "</span>";
//	        str += "<span>";
//	        str += "<a style='margin-right:2px;' title='增加详情' href=javascript:ondAdd(\'" + e.row.orderId+"\',\'"+e.row.connector + "\') ><span class='mini-button-text mini-button-icon icon-add'>&nbsp;</span></a>";
//	        str += "</span>";
//	        str += "<span>";
//	        str += "<a style='margin-right:2px;' title='订单报价' href=javascript:onQuotation(\'" + e.row.orderId+"\',\'"+e.row.connector + "\') ><span class='mini-button-text mini-button-icon icon-find'>&nbsp;</span></a>";
//	        str += "</span>";
//	        str += "<span>";
//	        str += "<a style='margin-right:2px;' title='订单详情' href=javascript:onAllDetail(\'" + e.row.orderId+"\',\'"+e.row.connector + "\') ><span class='mini-button-text mini-button-icon icon-lock'>&nbsp;</span></a>";
//	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
	    
	    function ondEdit(orderId,connector){
	        //window.open("EditOrderDetailServlet?orderId=" + orderId,
	        //        "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
	    	window.location="OrderSpecServlet?orderId=" + orderId+"&connector="+connector;
		}

	    function ondSee(orderId,connector){
	    
	        //window.open("EditOrderDetailServlet?orderId=" + orderId,
	        //        "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
	    	//window.location="ShowOrderAllServlet?orderId=" + orderId+"&connector="+connector;
	    	window.location="GoOrderStatisticsServlet?orderId="+orderId;
		}

	    function ondAdd(orderId,connector){
	        //window.location="orderManage/AddOrderDetail.jsp?orderId=" + orderId+"&connector="+connector;
	    	window.location="orderManage/AddOrderDetailMain.jsp?orderId=" + orderId+"&connector="+connector;
		}

		function onQuotation(orderId,connector){
			window.location="orderManage/QuotationMainIndex.jsp?orderId=" + orderId+"&connector="+connector;
		}
		
		function onAllDetail(orderId,connector){
			window.location="<%=basePath%>GoOrderAllDetailServlet?orderId=" + orderId+"&connector="+connector;
		}
		
	    //var Genders = [{ id: 'M', text: '男' }, { id: 'W', text: '女'}];
	    var Genders = [{id: "1", text: "新建"},{id: "2", text: "备料"},{id: "3", text: "代加工"},{id: "4", text: "加工中"},{id: "5", text: "完成"}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }

        
    </script>
  </body>
</html>
