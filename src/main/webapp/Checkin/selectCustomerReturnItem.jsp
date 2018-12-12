<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>选择物料</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    
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

        <div id="grid" class="mini-datagrid" style="width:100%;height:100%;" idField="id" allowResize="true"
            borderStyle="border-left:0;border-right:0;" showpager="true" onrowdblclick="onRowDblClick">
            <div property="columns">
                <div type="indexcolumn" ></div>
       			<div field="productId" headerAlign="center" align="center">编号</div>
       			<div field="productName" headerAlign="center" align="center">名称</div>
       			<div field="issueNum" headerAlign="center" align="center">版本号</div>
       			<div field="spec" headerAlign="center" align="center">规格</div>
<!--       			<div field="unit" headerAlign="center" align="center">单位</div>-->
       			<div field="unitPrice" headerAlign="center" align="center">单价</div>
            </div>
        </div>
    
    </div>                
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" style="width:60px;" onclick="onOk()">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="mini-button" style="width:60px;" onclick="onCancel()">取消</a>
    </div>
    
    
    <script type="text/javascript">
    mini.parse();
    var grid = mini.get("grid");
	
	var Request = new Object(); 
        Request = GetRequest(); 
    var orderId=Request['orderId'];
	
	function GetRequest() { 
        var url = location.search; //获取url中"?"符后的字串 
        var theRequest = new Object(); 
        if (url.indexOf("?") != -1) { 
        	var str = url.substr(1); 
        	strs = str.split("&"); 
        	for(var i = 0; i < strs.length; i ++) { 
        		theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
        	} 
        } 
        return theRequest; 
    } 
    //alert(Request['type']);
	
    //动态设置URL
    //grid.setUrl("../data/AjaxService.jsp?method=SearchEmployees");
    grid.setUrl("GetCustomerReturnItemServlet?orderId="+orderId);
    //也可以动态设置列 grid.setColumns([]);

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
  </body>
</html>
