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
  	<div class="mini-toolbar">
	    <a class="mini-button" plain="false" iconCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a>
	    <span class="separator"></span>
        <a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()" >刷新</a>
  	</div>
    <div id="grid1" class="mini-datagrid" style="width:100%;height:92%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="CustomerItemDetailListServlet?orderId=${orderId}">
        <div property="columns">
            <div type="indexcolumn"></div>
            <!-- 
            <div name="action" width="40" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
             -->
            <div name="action" width="40" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
            <!--<div field="orderId" width="110" headerAlign="center">订单编号</div>-->
            <div field="customeName" width="100" headerAlign="center">客户名称</div>
            <div field="connector" width="60" headerAlign="center">联系人</div>
            <div field="itemId" width="100" headerAlign="center">资产编号</div>
            <div field="itemname" width="100" headerAlign="center">资产名称</div>
            <div field="itemTypeName" width="60" headerAlign="center">资产类型</div>
            <div field="itemNum" width="60" headerAlign="center">数量</div>
            <div field="itemPrice" width="60" headerAlign="center">价格</div>
            <div field="unit" width="60" headerAlign="center">单位</div>
            <div field="person" width="60" headerAlign="center">经手人</div>
            <div field="starttime" width="80" headerAlign="center" dateFormat="yyyy-MM-dd">借入日期</div>
            <div field="endtime" width="80" headerAlign="center"  dateFormat="yyyy-MM-dd">计划归还日期</div>
            <div field="isCheckIn" width="60" headerAlign="center" renderer="onGenderRenderer">是否入库</div>
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='客户资产详情' href=javascript:ondSee(\'" + e.row.orderId+"\',\'" + e.row.itemId+"\') ><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
	        str += "</span>";
	        return str;
	    }
	    
	    function refresh(){
			grid.reload();
		}
		
	    function ondSee(orderId,itemId){
	        window.location="GoCustomerItemSpecServlet?orderId="+orderId+"&itemId="+itemId;
		}
		
	    //var Genders = [{ id: 'M', text: '男' }, { id: 'W', text: '女'}];
	    var Genders = [{id: "0", text: "否"},{id: "1", text: "是"}];
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
