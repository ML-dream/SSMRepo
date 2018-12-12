<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>外协完成情况</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
   <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script> 
	<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
	<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
	
    <script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
    <style type="text/css">
    body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
    }
    </style>
  </head>
  
  <body>
    <div class="mini-fit">

       <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" idField="id" allowResize="true"
            borderStyle="border-left:0;border-right:0;" url="OutAssistCompleteServlet?menuId=${menuId}" >
            <div property="columns">
                <div type="indexcolumn" ></div>
                <div field="orderId" width="100" headerAlign="center">订单号</div>
	            <div field="productId" width="100" headerAlign="center">图号 </div>
	            <div field="operId" width="100" headerAlign="center">工序号</div>
            	<div field="operName" width="100" headerAlign="center">工序名称</div>   
	            <div field="num" width="100" headerAlign="center">加工数量</div>
	            <div field="passNum" width="100" headerAlign="center">完成数量</div>        
            </div>
        </div>
    
    </div>                
</body>
</html>

<script type="text/javascript">
    mini.parse();
    var grid = mini.get("grid1");
    grid.load();

    function GetData() {
        var row = grid.getSelected();
        return row;
    }
    function search() {
        var key = mini.get("key").getValue();
        grid.load({ key: key });
    }
    function onKeyEnter(e) {
        search();
    }
    function onRowDblClick(e) {
        onOk();
    }
    //////////////////////////////////
    function CloseWindow(action) {
        if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
        else window.close();
    }

    function onOk() {
        CloseWindow("ok");
    }
    function onCancel() {
        CloseWindow("cancel");
    }


</script>