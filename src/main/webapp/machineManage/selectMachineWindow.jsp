<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
     <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/resources/scripts/boot.js"></script>
    <meta http-equiv="content-Type" content="text/html;charset=utf-8"/>
		<script type="text/javascript" src="<%=path %>/resources/scripts/jquery.min.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
		
		<script src="<%=path %>/resource/timePlanJs/bootstrap.min.js?v=3.3.6"></script>
		<script src="<%=path %>/resource/timePlanJs/layer.js"></script>
		<script src="<%=path %>/resource/timePlanJs/template.js"></script>
		<script src="<%=path %>/resource/timePlanJs/moment-with-locales.js"></script>
		<script src="<%=path %>/resource/timePlanJs/laypage.js"></script>
		<script src="<%=path %>/resource/timePlanJs/jquery.qrcode.min.js"></script>
		
		<!--jquery validate begin-->
		<script src="<%=path %>/resource/timePlanJs/jquery.validate.min.js"></script>
		<script src="<%=path %>/resource/timePlanJs/messages_zh.min.js"></script>
		<script src="<%=path %>/resource/timePlanJs/jquery.validate.extend.js"></script>
		<!--jquery validate end-->
		
		<script src="<%=path %>/resource/timePlanJs/utils.js"></script>
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
   		
   		

        <div id="grid1" class="mini-datagrid" style="width:150%;height:150%;" idField="id" allowResize="true"
            borderStyle="border-left:0;border-right:0;" onrowdblclick="onRowDblClick"
        >
            <div property="columns" height='150px' >
                <div type="indexcolumn" ></div>
                <div field="machineId" width="100" headerAlign="center">设备编号</div>
	            <div field="machineName" width="70" headerAlign="center">设备名称 </div>
	           <!--  <div field="machineSpec" width="100" headerAlign="center">设备规格</div>
	            <div field="status" width="100" headerAlign="center">状态</div>
	            <div field="power" width="100" headerAlign="center">功率</div> -->
            	<!-- <div field="machtype" width="100" headerAlign="center">设备类别</div>  
            	<div field="machineModel" width="70" headerAlign="center">设备型号</div>  -->   
            	<!-- <div field="workRange" width="70" headerAlign="center">加工范围</div>    -->       
            </div>
        </div>
    
    </div>                
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" style="width:60px;" onclick="onOk()">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="mini-button" style="width:60px;" onclick="onCancel()">取消</a>
    </div>

</body>
</html>

<script type="text/javascript">
    mini.parse();
    var grid = mini.get("grid1");
    //动态设置URL
    //grid.setUrl("../data/AjaxService.jsp?method=SearchEmployees");
//    grid.setUrl("GetMachineServlet");
    grid.setUrl("MachineListServlet");
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
    
    
    
   <%--  grid.on("drawcell", function (e) {
        var record = e.record,
            column = e.column,
            field = e.field,
            value = e.value;
        
        //将性别文本替换成图片
        if (column.field == "machineId") {
            if (e.value == "C0000049") {
            	//e.cellHtml="<div></div>"
            	//e.cellStyle = "background-image:url(Notes_Large.png) no-repeat";
            	<img src='<%=path %>/machinePicture/testjpg01.jpg' width='100' height='100'  alt='' />
                e.cellHtml ="<img src='<%=path %>/machinePicture/testjpg01.jpg' width='50px' height='50px'  alt='' />"
            } 
            else {
            	
            	/* str += "<span>";
    	        str += "<a style='margin-right:2px;' title='预约设备' href=javascript:ondEdit(\'" + e.row.orderId+"\',\'"+e.row.connector + "\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
    	        str += "</span>"; */
            	e.cellHtml ="<img src='<%=path %>/machinePicture/testjpg01.jpg' width='50px' height='50px'  alt='' />"

            }
        }

       /*  //格式化日期
        if (field == "birthday") {
            if (mini.isDate(value)) e.cellHtml = mini.formatDate(value, "yyyy-MM-dd");

        }

        //给年龄，增加"岁"字符串
        if (field == "age") {
            e.cellHtml = value + "岁";
        }

        //给帐号列，增加背景色
        if (field == "loginname") {
            e.cellStyle = "background:#ecedef";
        }

        //超过1万工资，红色标识
        if (field == "salary" && value >= 10000) {
            e.cellStyle = "color:red;font-weight:bold;";
        }

        //显示学历
        if (field == "educational") {
            for (var i = 0, l = Educationals.length; i < l; i++) {
                var edu = Educationals[i];
                if (edu.id == value) {
                    e.cellHtml = edu.name;
                    break;
                }
            }
        } */

      /*   //action列，超连接操作按钮
        if (column.name == "action") {
            e.cellStyle = "text-align:center";
            e.cellHtml = '<a href="javascript:edit(\'' + record.id + '\')">Edit</a>&nbsp; '
                        + '<a href="javascript:del(\'' + record.id + '\')">Delete</a>'
        } */

      

        //设置行样式
        if (record.gender == 1) {
            e.rowCls = "myrow";
        }
    });

     --%>
    
    
    
    
    
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