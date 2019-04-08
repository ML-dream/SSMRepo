<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
	<base href="<%=basePath%>">
	<script type="text/javascript" src="<%=path %>/resources/scripts/boot.js"></script>
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
  </head>
  
  <body>

  	<h1>设备管理员维护</h1>      

    <div style="width:100%;">
        <div class="mini-toolbar" style="border-bottom:0;padding:0px;">
            <table style="width:100%;">
                <tr>
                    <td style="width:100%;">
                        <a class="mini-button" iconCls="icon-add" onclick="add()">增加</a>
                    </td>
                    
                </tr>
            </table>           
        </div>
    </div>
	  
    <div id="grid1" class="mini-datagrid" style="width:100%;height:93%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="loadMachineManager.action">
        <div property="columns">
            <div type="indexcolumn"></div>
            <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower"cellStyle="padding:0;">操作</div>
            <div field="staffCode" width="100" headerAlign="center" align="center" >员工编号</div>
            <div field="staffName" width="100" headerAlign="center" align="center" >员工名称</div>
            <div field="sectionName" width="100" headerAlign="center" align="center" >所属部门</div>
           <!--  <div field="gender" width="100" headerAlign="center" renderer="onGenderRenderer" align="center" >性别</div> -->
            <div field="mobilePhone" width="100" headerAlign="center" align="center" >手机号码</div>
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    
	    var machineId = "<%=request.getParameter("machineId")%>";
	    grid.load({machineId:machineId});
	   
	    function add(e) {
	         var btnEdit = this;
	         mini.open({
	             url: "qualitycheck/selectEmployeeWindow.jsp",
	             title: "选择列表",
	             width: 750,
	             height: 480,
	             ondestroy: function (action) {
	                 if (action == "ok") {
	                     var iframe = this.getIFrameEl();
	                     var data = iframe.contentWindow.GetData();
	                     data = mini.clone(data);    //必须
	                     if (data) {
	                         var staffCode=data.staff_code;
	                         var staffName=data.staff_name;
	                         //用于更新相应设备管理table
	                         $.ajax({
	                             type:"POST",
	                             url:"addMachineManager.action",
	                             data: {staffCode:staffCode,staffName:staffName,machineId:machineId},
	                             dataType: "json",
	                             success:function(data){
	                           			U.msg(data.result);
	                           			grid.load({machineId:machineId});
	                             } 
	                         });
	                         
	                         
	                         
	                     }
	                 }
	             }
	         });
	     }	
	    
	    function onOperatePower(e) {
	        var str = "";

	        str += "<a style='margin-right:2px;' title='删除信息' href=javascript:onRemove(\'" + e.row.staffCode+"\') ><span class='icon-remove' style='width:30px;height:20px;display:inline-block'></span></a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
	   
	    
	    function onRemove(staffCode){
	    	$.ajax({
                type:"POST",
                url:"deleteMachineManager.action",
                data: {machineId:machineId,staffCode:staffCode},
                dataType: "json",
                success:function(data){
                	U.msg(data.data);
              	  grid.load({machineId:machineId});
                } ,
                error:function(data){
                	U.msg(data.data);
                } 
            });
	    	}
	    
	    
	    
	    
	    
	    function onEdit(staffCode){
	         window.location.href="EditEmployeeDetailServlet?staffCode="+staffCode;
		}

		function onSearch(){
			var leave=mini.get("key").getValue();
			grid.load({leave:leave});
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
