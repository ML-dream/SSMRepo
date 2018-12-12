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
         url="OutAssistDetailListServlet?orderId=${orderId}">
        <div property="columns">
            <div type="indexcolumn"></div>
            <div name="action" width="80" headerAlign="center" align="center" renderer="onOperatePower"ncellStyle="padding:0;">操作</div>
            <div field="orderId" width="100" headerAlign="center">外协单号</div>
            <div field="itemId" width="100" headerAlign="center">零部件号</div>
            <div field="itemName" width="100" headerAlign="center">零部件名称</div>
            <div field="drawingId" width="100" headerAlign="center">图号</div>
            <div field="num" width="50" headerAlign="center">数量</div>
            
            <div field="detailunitPrice" width="50" headerAlign="center">单价</div>
            <div field="numUnit" width="50" headerAlign="center">单位</div>
            <div field="detailstartDate" width="80" headerAlign="center" dateFormat="yyyy-MM-dd">下单时间</div>
            <div field="detailplanEndDate" width="80" headerAlign="center" dateFormat="yyyy-MM-dd">计划结束时间</div>
            <div field="detailendDate" width="80" headerAlign="center" dateFormat="yyyy-MM-dd">收货时间</div>
            <div field="detailtotalPrice" width="50" headerAlign="center">价格</div>
            <div field="detaildetail" width="200" headerAlign="center">加工内容</div>
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
	        str += "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.orderId+"\',\'"+e.row.itemId + "\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='接收外协产品' href=javascript:onOk(\'" + e.row.orderId+"\',\'" + e.row.itemId+"\',\'" + e.row.itemName+"\',\'" + e.row.drawingId+"\') ><span class='mini-button-text mini-button-icon icon-ok'>&nbsp;</span></a>";
	        str += "</span>";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='查看接收外协产品' href=javascript:onSee(\'" + e.row.orderId+"\',\'" + e.row.itemId+"\',\'" + e.row.drawingId+"\') ><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
	        str += "</span>";
	        return str;
	    }
	    
	    function ondEdit(orderId,itemId){
	        window.location.href = "OutAssistDetailServlet?orderId="+orderId+"&itemId="+itemId;
		}
		
		function onOk(orderId,itemId,itemName,drawingId){
	        window.location.href = "outAssistManage/addOutAssistGet.jsp?orderId="+orderId+"&itemId="+itemId+"&itemName="+encodeURI(encodeURI(itemName))+"&drawingId="+drawingId;
		}
		
		function onSee(orderId,itemId,drawingId){
	        window.location.href = "GoOutAssistGetListServlet?orderId="+orderId+"&itemId="+itemId+"&drawingId="+drawingId;
		}

	    var Genders = [{ id: 'M', text: '男' }, { id: 'W', text: '女'}];
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
