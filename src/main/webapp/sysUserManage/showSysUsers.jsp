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
    	*{margin: 0;padding: 0;}
    </style>

	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
	
	<%String flag =request.getParameter("flag");
	String message = "cunwufffffffff";
	if (flag!=null )
	{
	message="你的格式错误！";
	} 
	 %>
	
	
  </head>
  
  <body>

          <!--  <span>用户姓名：</span><input type="text" id="username" name="username" />
           <input type="button" value="查找" onclick="search()"/> 
  -->
  <div>
  	名称  <input id="username" name="username" class="mini-textbox" onenter="Search()"/><%-- <span><%=message%></span> --%>
  	<a class="mini-button" plain="fasle" onclick="Search()">查询</a>
  	
  	</div>
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="ShowSysUsersServlet"> 
        <div property="columns">
            <div type="indexcolumn"></div>
            <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower"  cellStyle="padding:0;">操作</div>
            <div field="userId" width="100" headerAlign="center" align="center" >用户名</div>
            <div field="staffCode" width="100" headerAlign="center" align="center">员工号</div>
            <div field="staffName" width="100" headerAlign="center" align="center">员工姓名</div>
         <!--    <div field="authority" width="100" headerAlign="center" align="center">authority</div>
            <div field="authorityName" width="100" headerAlign="center"  align="center"  visible="true">权限</div> -->
        </div>
    </div>  
    <script type="text/javascript">
    	mini.parse();
	     var grid = mini.get("grid1"); 
	   grid.load({username:""});
     
	   /*  function search() {
        var username = document.getElementById("username").value;
        key={"username":username };
        grid.load(key);
        } */
       
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str = "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.userId+"\') ><span class='icon-edit' style='width:30px;height:20px;display:inline-block'></span></a>";
	        str += "</span>";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='删除' href=javascript:ondelete(\'" + e.row.userId+"\',\'"+e.row.staffCode+"\') ><span class='mini-button-text mini-button-icon icon-remove'>&nbsp;</span></a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
	    
	    function ondEdit(userId){
	        window.location.href = "EditSysUsersServlet?userId="+userId;
		}
		
		function ondelete(userId,staffCode){	//参数1 表示删除用户
			mini.confirm("删除当前用户", "确定？",
                function (action){            
                  if (action == "ok") {
					 var url = "EditSysUsersServlet?para=1&userId="+userId+"&staffCode="+staffCode;
					 var params =null;
		   		        jQuery.post(url, params, function(data){
		   		        	alert(data.result);
		   	   		  		grid.reload();
		   	   		        },'json');
		   	   		 }
                }
            );
		}
	    var Genders = [{ id: 'M', text: '男' }, { id: 'W', text: '女'}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
         
         function Search(){
	 	var username=mini.get("username").getValue();
	 	grid.load({username:username});
	
	 }
        
 $("#username").bind("keydown", function (e) {
            if (e.keyCode == 13) {
                search();
            }
        });
        
    </script>
  </body>
</html>
