<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>员工信息</title>
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
  
     <input id="key" class="mini-textbox" emptyText="请输入员工姓名关键字" style="width:250px;" onenter="onKeyEnter"/>   
                        <a class="mini-button" onclick="onSearch()">查询</a>
  
  	<div>
  <!-- 	在职状态：<input id="leave" name="leave" class="mini-combobox" showNullItem="true" url="data/leave.txt"/>
  	&nbsp;&nbsp;<a class="mini-button" iconCls="" onclick="onSearch()">筛选</a> -->
  	
  	               <!--   用户姓名：
  	          <input type="text" id="username" name="username" />
             <input type="button" value="查找" onclick="search()"/>
  	</div> -->
  	 
  
    <div id="grid1" class="mini-datagrid" style="width:100%;height:93%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="EditEmployeeInfoServlet">
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
	    grid.load();
	    
	   /*  function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str = "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.staffCode + "\') ><span class='icon-edit' style='width:30px;height:20px;display:inline-block'></span></a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    } */
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='修改信息' href=javascript:onEdit(\'" + e.row.staffCode+"\') ><span class='icon-edit' style='width:30px;height:20px;display:inline-block'></span></a>";

	        str += "<a style='margin-right:2px;' title='删除信息' href=javascript:onRemove(\'" + e.row.staffCode+"\') ><span class='icon-remove' style='width:30px;height:20px;display:inline-block'></span></a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
	    
	    function onEdit(staffCode){
//	        window.open("EditEmployeeDetailServlet?staffCode=" + staffCode,
//	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
	         window.location.href="EditEmployeeDetailServlet?staffCode="+staffCode;
		}
	    
	    
	    function onRemove(staffCode){
	    	$.ajax({
                type:"POST",
                url:"showEmployeeDelete.action",
                data: {staffCode:staffCode},
                dataType: "json",
                success:function(data){
              	  alert(data.data);
              	  grid.load();
                } ,
                error:function(data){
                 alert(data.data);
                } 
            });
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
