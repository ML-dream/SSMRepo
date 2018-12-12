<%@ page language="java" import="java.util.*,com.wl.forms.Tree" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
  <script language=javascript src="./dtree.js" charset="utf-8"></script>
  <script type="text/javascript" src="./buildtree.js"></script>
  <link rel="stylesheet" href="../dtree.css" type="text/css"></link>
  <meta http-equiv="content-type" content="text/html; charset=UTF-8">
  <script type="text/javascript" charset="utf-8" language="utf-8">
     
  </script>
</head>
<body onload="buildtree();">  <!--点击则创建树  -->
   <div>
      <a href="javascript: d.openAll();"> 全展</a> | 
	  <a href="javascript: d.closeAll();">全关</a> |
	  <a href="javascript: location.reload();">刷新</a>
	  <a href="/test/workplan_disp.do?flag=show&id=jsq1" >跳转</a>
	  <br>
	  <button onclick="addpart();">添加零件</button>
   </div>
<% ArrayList al=(ArrayList) request.getAttribute("tree");               
               Tree tree=(Tree)al.get(4);
            %>
            
   <div id ="dtree" class ="dtree">
   <script type="text/javascript">
   /*  
     //alert("以下是参数");
     		   
		    var tree_id = <%=tree.getTree_id() %>;
		    var tree_pid = <%=tree.getTree_pid()%>;
		    var tree_name = <%=tree.getTree_name()%>;
		    
		    //alert(tree_id);
		    //alert(tree_pid);
		    //alert(tree_name);
		    
		d = new dTree('d','','dtree');        
		    d.add('top','x','EBOM结构树',null,null,null);

            d.add('tree_id','tree_pid','tree_name','..','tree_name','list','','','','ec');
		    d.add('jsq1','top','jsq1','/test/workplan_disp.do?flag=show&id=jsq1','零件1','list','','','','ec');
		    d.draw();
    */	 
	</script>
   </div>
  </body>  
</html>
