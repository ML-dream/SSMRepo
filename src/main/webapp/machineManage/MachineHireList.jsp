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
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="MachineHireListServlet">
        <div property="columns">
            <div type="checkcolumn"></div>
            <!-- 
            <div name="action" width="40" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
             -->
            <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
            <div field="machineId" width="100" headerAlign="center">设备编号</div>
            <div field="deptName" width="100" headerAlign="center">部门号</div>
            <div field="hireStatus" width="100" headerAlign="center" renderer="onGenderRenderer">租赁状态</div>
            <div field="outDate" width="100"  dateFormat="yyyy-MM-dd" headerAlign="center">租出时间</div>
            <div field="inDate" width="100" dateFormat="yyyy-MM-dd" headerAlign="center">租入时间</div>
            <div field="backDate" width="100" dateFormat="yyyy-MM-dd" headerAlign="center">归还时间</div>
            <div field="hireMoney" width="100" headerAlign="center">租金</div>
            <div field="hireNum" width="100" headerAlign="center">租金数量</div>
            <div field="staffName" width="100" headerAlign="center">负责人</div>
        </div>
    </div>
    
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str = "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.machineId+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
	        return str;
	    }
	    
	    function ondEdit(machineId){
	        //window.open("EditCustomerDetailServlet?machineId=" + machineId+"&connector="+connector,
	        //        "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
			window.location.href = "MachineHireSpecServlet?machineId=" + machineId;
		}

	    var Genders = [{ id: '1', text: '租入' }, { id: '0', text: '租出'}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            /*
            var row = e.row;
            if("1"==e.value){		//租入
            	row.outDate='';
            }else{					//租出
            	row.inDate='';
            }
            grid.updateRow(row,row);
            */
            return "";
        }

        
    </script>
  </body>
</html>
