<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>部门信息</title>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/js.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/pagecard.css">
	
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  
  <body>

    <div class="mini-splitter" style="width:100%;height:100%;">
	    <div size="240" showCollapseButton="true">
	    	<!--
	        <div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;">                
	            <span style="padding-left:5px;">名称：</span>
	            <input class="mini-textbox" width="120"/>
	            <a class="mini-button" iconCls="icon-search" plain="true">查找</a>                  
	        </div>
	        -->
	        <div class="mini-fit">
	            <ul id="tree1" class="mini-tree" url="GetDeptTreeServlet" style="width:100%;"
	                showTreeIcon="true" textField="deptName" idField="deptId" parentField="FDeptId" resultAsTree="false"
	            >        
	            </ul>
	        </div>
	    </div>
	    <div showCollapseButton="true">
	        <div class="mini-fit" >
	            <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" 
	                borderStyle="border:0;"
	                url="DeptListServlet"
	                showFilterRow="true" allowCellSelect="true" allowCellEdit="true"                
	            >
	            
	                <div property="columns">     
	                	<div type="indexcolumn"></div>
            			<div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower"
                 			cellStyle="padding:0;">操作
            			</div>       
	                    <div field="deptId" width="120" headerAlign="center">部门编号</div>      
	                    <div field="deptName" width="120" headerAlign="center">部门名称  </div>                
	                    <div field="FDeptId" width="100" align="center" headerAlign="center">上级部门</div>            
	                    <div field="headStaffId" width="100" headerAlign="center">部门领导</div>
	                    <div field="deptLevel" width="100" headerAlign="center" >部门级别</div>                                    
	                    <div field="isKey" width="100" headerAlign="center">是否关键部门</div>            
	                </div>
	            </div>  
	        </div>
	    </div>        
	</div>
    
    <script type="text/javascript">
    	 mini.parse();

        var tree = mini.get("tree1");
        var grid = mini.get("grid1");
        grid.load();

		function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.deptId + "\') ><span class='icon-edit' style='width:30px;height:20px;display:inline-block'></span></a>";
	        str += "</span>";
	        return str;
	    }
		
        tree.on("nodeselect", function (e) {
        
            if (e.isLeaf) {
                //grid.setData([]);
                //grid.setTotalCount(0);
            } else {
            	grid.load({ deptId: e.node.deptId });
                
            }
        });
        //////////////////////////////////////////////
        var Genders = [{ id: 1, text: '男' }, { id: 2, text: '女'}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
        function onNameFilterChanged(e) {
            var textbox = e.sender;
            var key = textbox.getValue();
            
            var node = tree.getSelectedNode();
            if (node) {
                grid.load({ dept_id: node.id, key: key });
            }
        }
        function addRow() {            
            var node = tree.getSelectedNode();
            if (node) {
                var newRow = { name: "New Row" };
                newRow.dept_id = node.id;
                grid.addRow(newRow, 0);
            }
        }
        function removeRow() {
            var rows = grid.getSelecteds();
            if (rows.length > 0) {
                grid.removeRows(rows, true);
            }
        }
        function saveData() {
            var data = grid.getChanges();
            var json = mini.encode(data);
            grid.loading("保存中，请稍后......");
            $.ajax({
                url: "../data/AjaxService.aspx?method=SaveEmployees",
                data: { data: json },
                type: "post",
                success: function (text) {
                    grid.reload();
                },
                error: function (jqXHR, textStatus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
        }
        
        function ondEdit(deptId){
	        window.location.href="DeptSpecServlet?deptId=" + deptId;
		}

        
    </script>
  </body>
</html>
