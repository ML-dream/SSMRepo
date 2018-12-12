<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>当日任务监测</title>
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
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  
  <body>
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="jianceServlet?flag=today&orderId=<%=request.getAttribute("orderId") %>&itemId=<%=request.getAttribute("itemId") %>">
        <div property="columns">
            <div type="checkcolumn"></div>
            <!-- 
            <div name="action" width="40" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
             -->
            <div field="dingdanhao" width="100" headerAlign="center">订单号
                
            </div>
            <div field="lingjianhao" width="100" headerAlign="center">零件号
                <input property="editor" type="checkbox" class="mini-textbox" style="width:100%;"/>
            </div>
            <div field="gongxuhao" width="100" headerAlign="center">工序号
                <input property="editor" type="checkbox" class="mini-textbox" style="width:100%;"/>
            </div>
            <div field="gongxuming" width="100" headerAlign="center">工序名称
                <input property="editor" type="checkbox" class="mini-textbox" style="width:100%;"/>
            </div>
            <div field="machine" width="100" headerAlign="center">机床
                <input property="editor" type="checkbox" class="mini-textbox" style="width:100%;"/>
            </div>
            <div field="startTime" width="100" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss">开始加工时间
            </div>
            <div field="worker" width="70" headerAlign="center">加工人员
                <input property="editor" type="checkbox" class="mini-textbox" style="width:100%;"/>
            </div>
            <div field="status" width="50" headerAlign="center">工件状态
                <input property="editor" type="checkbox" class="mini-textbox" style="width:100%;"/>
            </div>
            <div field="planNum" width="50" headerAlign="center">计划数量
                <input property="editor" type="checkbox" class="mini-textbox" style="width:100%;"/>
            </div>
            <div field="finishNum" width="50" headerAlign="center">完成数量
                <input property="editor" type="checkbox" class="mini-textbox" style="width:100%;"/>
            </div>
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    window.setInterval(function(){grid.load()},5000);
	    //alert("dddd");
    </script>
  </body>
</html>
