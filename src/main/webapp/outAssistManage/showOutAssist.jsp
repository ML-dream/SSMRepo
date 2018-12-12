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
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/js.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/pagecard.css">
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
         url="OutAssistListServlet">
        <div property="columns">
            <div type="checkcolumn"></div>
            <div name="action" width="100" headerAlign="center" align="center" renderer="onOperatePower" cellStyle="padding:0;">操作</div>
            <div field="orderId" width="100" headerAlign="center">外协单号</div>
            <div field="companyId" width="100" headerAlign="center">外协单位编号</div>
            <div field="companyName" width="100" headerAlign="center">外协单位名称</div>
            <div field="principalName" width="100" headerAlign="center">联络人</div>
            <div field="connectorName" width="100" headerAlign="center">联系人</div>
            <div field="connectorTel" width="100" headerAlign="center">联系人电话</div>
            <div field="startDate" width="100" headerAlign="center" dateFormat="yyyy-MM-dd">下单时间</div>
            <div field="planEndDate" width="100" headerAlign="center" dateFormat="yyyy-MM-dd">计划收货时间</div>
            <div field="isBusy" width="60" headerAlign="center" renderer="onGenderRenderer">是否加急件</div>
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.orderId+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='添加外协任务' href=javascript:onAdd(\'" + e.row.orderId+"\') ><span class='mini-button-text mini-button-icon icon-add'>&nbsp;</span></a>";
	        str += "</span>";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='查看外协任务' href=javascript:onSee(\'" + e.row.orderId+"\') ><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
	        str += "</span>";
	        //str += "<span>";
	        //str += "<a style='margin-right:2px;' title='外协完成' href=javascript:onOk(\'" + e.row.orderId+"\') ><span class='mini-button-text mini-button-icon icon-ok'>&nbsp;</span></a>";
	        //str += "</span>";
	        return str;
	    }
	    
	    function ondEdit(orderId){
	        window.location.href = "OutAssistSpecServlet?orderId="+orderId;
		}
		
		function onAdd(orderId){
	        window.location.href = "outAssistManage/addOutAssistSpec.jsp?orderId="+orderId;
		}
		
		function onSee(orderId){
	        window.location.href = "GoOutAssistDetailListServlet?orderId="+orderId;
		}
		
		function onOk(orderId){
	        window.location.href = "GoOutAssistDetailListServlet?orderId="+orderId;
		}

	    var Genders = [{ id: '0', text: '否' }, { id: '1', text: '是'}];
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
