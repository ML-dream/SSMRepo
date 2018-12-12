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
         url="OrderDetailListServlet?orderId=${orderId}">
        <div property="columns">
            <div type="indexcolumn"></div>
            <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
            <!-- 
            <div field="orderId" width="100" headerAlign="center">订单编号</div>
            <div field="companyName" width="100" headerAlign="center">客户名称</div>
            <div field="fproductId" width="100" headerAlign="center">父产品号</div>
             -->
            <div field="productId" width="100" headerAlign="center">产品号</div>
            <div field="productName" width="100" headerAlign="center">产品名称</div>
            <div field="drawingId" width="100" headerAlign="center">图号</div>
            <div field="issueNum" width="50" headerAlign="center">版本号</div>
            <div field="spec" width="100" headerAlign="center">产品规格</div>
            <!--
            <div field="madePlace" width="100" headerAlign="center">制造单位</div>
            -->
            <!--
            <div field="deptName" width="100" headerAlign="center">使用部门</div>
            -->
            <div field="bTime" width="80" headerAlign="center" dateFormat="yyyy-MM-dd">开始时间</div>
            <div field="eTime" width="80" headerAlign="center"  dateFormat="yyyy-MM-dd">结束时间</div>
            <!--
            <div field="SBTime" width="100" headerAlign="center" dateFormat="yyyy 年 MM 月 dd 日">实际开始时间</div>
            <div field="SETime" width="100" headerAlign="center" dateFormat="yyyy 年 MM 月 dd 日">实际结束时间</div>
             -->
            <div field="productNum" width="60" headerAlign="center">产品数量</div>
            <div field="finishNum" width="60" headerAlign="center">完成数量</div>
            <div field="productStatus" width="60" headerAlign="center">产品状态</div>
            <div field="unitPrice" width="50" headerAlign="center">单价</div>
            <!--
            <div field="unitPrice" width="100" headerAlign="center">单价</div>
            <div field="isDuiZhangHan" width="100" headerAlign="center">有无对账函</div>
            <div field="isLaiLiao" width="100" headerAlign="center">是否来料</div>
            <div field="isJiaoHuo" width="100" headerAlign="center">是否交货</div>
            -->
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    //window.setInterval(function(){grid.load()},5000);
	    //alert("dddd");
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str = "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.orderId+"\',\'"+e.row.productId + "\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
	    
	    function ondEdit(orderId,productId){
	        //window.open("EditOrderDetailServlet?orderId=" + orderId,
	        //        "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
	    	window.location="OrderDetailSpecServlet?orderId=" + orderId+"&productId="+productId;
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
