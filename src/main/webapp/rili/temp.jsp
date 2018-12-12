<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
<head>
		<base href="<%=basePath%>">
    <!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/o_js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/o_js/miniui/miniui.js"></script>
		<link href="<%=path %>/o_js/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/o_js/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
		<title>listtree</title>
		
		<style type="text/css">
		</style>
</head>
<body>
	<input type="button" onclick = "test()"/>
	<div id="datagrid1" class="mini-datagrid" style="width:1200px;height:480px;" allowResize="true"
        url="data/listTree.txt"  idField="id" multiSelect="true" pagesize="20" onselect="" onrowclick=""  onshowrowdetail="onShowRowDetail">
        <div property="columns">
                
            <div type="checkcolumn" visible="true"></div>
             <div type="indexcolumn" ></div>
            
            <div field="id" width="65" headerAlign="center" allowSort="false">id</div>    
            <div field="text" width="65" headerAlign="center" allowSort="false">text</div> 
            <div field="iconCls" width="65" headerAlign="center" allowSort="false">iconCls</div>
            <div field="pid" width="65" headerAlign="center" allowSort="false">pid</div>
            <div field="url" width="65" headerAlign="center" allowSort="false">url</div>
             <div field="level" width="65" headerAlign="center" allowSort="false">level</div>
		</div>
     </div>
</body>
<script type="text/javascript">
	mini.parse();
	var grid = mini.get("datagrid1");
	function test(){
		grid.selectAll();
			var data = grid.getSelecteds();
    		 var json = mini.encode(data);
    		//alert (json);
    		grid.deselectAll();
    		$.ajax({
				type:"post",
				url: "SaveListTree",
				data:{"data" : json},
				cache: false,
				success: function (text){
					var t = confirm(text +",是否刷新数据 ？");
					if(t==true){
						grid.reload();
					}
				},
				error: function (text){
					alert ("保存失败 ");
				}
			}); 
	}
</script>
</html>