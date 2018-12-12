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
      <div>
  	名称  <input id="companyname" name="companyname" class="mini-textbox" onenter="Search()"/>
  	<a class="mini-button" plain="fasle" onclick="Search()">查询</a>
  	
  	</div>
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="ShowCustomerInfoServlet">
        <div property="columns">
            <div type="indexcolumn"></div>
            
            <div name="action" width="40" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div>
            <div field="companyId" width="100" headerAlign="center">客户编号
            </div>
            <div field="companyName" width="100" headerAlign="center">客户名称
            </div>
            <div field="typeName" width="100" headerAlign="center">企业类型
            </div>
            <div field="address" width="100" headerAlign="center">详细地址
            </div>
            <div field="connector" width="100" headerAlign="center">联系人
            </div>
            <div field="connectorTel" width="100" headerAlign="center">联系人电话
            </div>
        </div>
    </div> 
    
    <script type="text/javascript">
    	 mini.parse();
	    var grid = mini.get("grid1");
	    grid.load({"customname":""}); //这是默认触发的语句
	    
	    //window.setInterval(function(){grid.load()},5000);
	    //alert("dddd");
		function Search(){
    	 var s=mini.get("companyname").getValue();
     	  /*  var s=document.getElementById("username").getValue; */
	 		alert(s);
	 		grid.load({companyname:s});
	 }
        
  $("#username").bind("keydown", function (e) {
            if (e.keyCode == 13) {
                Search();
            }
        });
        
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str = "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.companyId+"\',\'"+e.row.connector + "\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
	    
	    function ondEdit(companyId,connector){
	        window.location.href ="EditCustomerDetailServlet?companyId=" + companyId+"&connector="+connector;
			
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
