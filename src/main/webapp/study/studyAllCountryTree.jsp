<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>studyTree</title>
    <script type="text/javascript"src="<%=path %>/scripts/jquery.min.js"></script>
	<script type="text/javascript"src="<%=path %>/scripts/miniui/miniui.js"></script>
	<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
	<link href="<%=path %>/scripts/miniui/themes/default/miniui.css"rel="stylesheet" type="txt/css"></link>
	<link href="<%=path %>/scripts/miniui/themes/icons.css"rel="stylesheet" type="txt/css"></link>


  </head>
  
  <body>
    <h1>LazyTree 懒加载树形</h1>      
    
    <input type="button" value="刷新节点" onclick="refreshNode()"/>
	<ul id="tree1" class="mini-tree" url="BaseServlet?meth=getLazyTree" style="width:300px;height:600px;padding:5px;" 
    	showTreeIcon="true" textField="NAME" onbeforeload="onBeforeTreeLoad" 
    	idField="ID" parentField="PID" resultAsTree="false" 
       	 >        
	</ul>

    
  <script type="text/javascript">
    mini.parse();
        
    function onBeforeTreeLoad(e) {
        var tree = e.sender;    //树控件
        var node = e.node;      //当前节点
        var params = e.params;  //参数对象
        
        //可以传递自定义的属性
        params.myField = "123"; //后台：request对象获取"myField"
        
    }

    function refreshNode() {
    	alert("dddd");
        var tree = mini.get("tree1");
        var node = tree.getSelectedNode();
        if (node) {
            tree.loadNode(node);
        }
    }

    </script>

    <div class="description">
        <h3>Description</h3>
        <p>
        </p>
    </div>
</body>
</html>
