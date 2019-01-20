<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>设备操作</title>
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
  <div>dsdsadasd</div>

    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="MachineListServlet">
        <div property="columns">
            <div type="indexcolumn"></div>
            <div name="action" width="100" headerAlign="center" align="center" renderer="onOperatePower"cellStyle="padding:0;">操作</div>
            <div field="machineId" width="80" headerAlign="center">设备编号</div>
            <div field="machineName" width="80" headerAlign="center">设备名称</div>
            <div field="machineSpec" width="80" headerAlign="center">设备规格</div>
            <div field="typeName" width="80" headerAlign="center">设备类别</div>
            <div field="machModel" width="80" headerAlign="center">设备型号</div>
            <div field="machStandard" width="80" headerAlign="center">设备品牌</div>
            <div field="machPrice" width="60" headerAlign="center">设备价格</div>
             <div field="hourPercent" width="80" headerAlign="center">工时百分比</div>
            <div field="countPercent" width="80" headerAlign="center">计件百分比</div> 
            <div field="outDate" width="100" headerAlign="center" dateFormat="yyyy-MM-dd">出厂日期</div>
            <div field="place" width="100" headerAlign="center">放置地点</div>
            <div field="isKeyMach" width="80" headerAlign="center" renderer="onGenderRenderer">是否关键设备</div>
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.machineId+"\') ><span class='icon-edit' style='width:30px;height:20px;display:inline-block'></span></a>";
	        str += "</span>";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='维修' href=javascript:onRepair(\'" + e.row.machineId+"\',\'"+e.row.machineName+"\') ><span class='icon-unlock' style='width:30px;height:20px;display:inline-block'></span></a>";
	        str += "</span>";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='报废' href=javascript:onDiscard(\'" + e.row.machineId+"\') ><span class='icon-no' style='width:30px;height:20px;display:inline-block'></span></a>";
	        str += "</span>";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='租赁' href=javascript:onHire(\'" + e.row.machineId+"\') ><span class='icon-user' style='width:30px;height:20px;display:inline-block'></span></a>";
	        str += "</span>";
	        return str;
	    }
	    
	    
	    function ondEdit(machineId){
	        window.location.href = "MachineSpecServlet?machineId="+machineId;
		}
		
		function onRepair(machineId,machineName){
	        window.location.href = "machineManage/addMachineRepair.jsp?machineId="+machineId+"&machineName="+machineName;
		}
		
		function onDiscard(machineId){
	        window.location.href = "machineManage/addMachineDiscard.jsp?machineId="+machineId;
		}
		
		function onHire(machineId){
	        window.location.href = "machineManage/addMachineHire.jsp?machineId="+machineId;
		}

	    var Genders = [{ id: '1', text: '是' }, { id: '0', text: '否'}];
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
