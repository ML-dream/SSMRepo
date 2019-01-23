<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>选择客户</title>
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
  	<!-- 
    <div class="mini-toolbar" style="text-align:center;line-height:30px;" borderStyle="border:0;">
          <label >名称：</label>
          <input id="key" class="mini-textbox" style="width:150px;" onenter="onKeyEnter"/>
          <a class="mini-button" style="width:60px;" onclick="search()">查询</a>
    </div>
     -->
    <div class="mini-fit">

        <div class="mini-fit">
		<form id="form1">
	   	<table>
   		<!-- 表头 -->
   			<tr>
	   			<td>
	   				<label>院系</label>
	   			</td>
	   			<td>
	   				<input id="workSection" class="mini-combobox" style="" textField="text" valueField="id" 
   						 url="LoadWorkSection"  showNullItem="true"  nullItemText="全部"  allowInput="false" onvaluechanged="search()" />
	   			</td>
	   		<td><label>姓名</label></td>
	   			<td>
	   				<input id="workName" class="mini-textbox" style="" textField="text" valueField="id" 
   						 value=""   allowInput="true" onvaluechanged="search()"/>
	   			</td> 
	   			<td><input value="确定" type="button" onclick="search()" /></td>
	   		</tr>
   	 	</table>
   	 </form>
        <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" idField="id" allowResize="true"
            borderStyle="border-left:0;border-right:0;" onrowdblclick="onRowDblClick"
        >
            <div property="columns">
                <div type="indexcolumn" ></div>
                <div field="staff_code" width="100" headerAlign="center">员工编号</div>
	            <div field="staff_name" width="100" headerAlign="center">员工名称 </div>
	            <div field="dept_id" width="100" headerAlign="center">所在部门</div>
	            <div field="gender" width="100" headerAlign="center" visible="false">性别</div>
            	<div field="mobile_phone" width="100" headerAlign="center">联系电话</div>           
            </div>
        </div>
    
    </div>     
    
    </div>                
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
      <!--   <a class="mini-button" style="width:90px;" onclick="onOut()">外协厂商</a> -->
        <!-- <span style="display:inline-block;width:25px;"></span> -->
        <a class="mini-button" style="width:60px;" onclick="onOk()">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="mini-button" style="width:60px;" onclick="onCancel()">取消</a>
    </div>

</body>
</html>

<script type="text/javascript">
    mini.parse();
    var grid = mini.get("grid1");
    grid.setUrl("ShowEmployeeInfoServlet");
    search();
	function search(){
    	var section = mini.get("workSection").getValue();
    	var workName = mini.get("workName").getValue(); 
    	grid.load({section:section,workName:workName});
    }
    //动态设置URL
    
    //也可以动态设置列 grid.setColumns([]);

   // grid.load();

    function GetData() {
        var row = grid.getSelected();
        return row;
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
	function onOut(){
		CloseWindow("outer");
	}

</script>
