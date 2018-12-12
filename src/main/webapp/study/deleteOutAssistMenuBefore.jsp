<%@ page language="java"  import="java.util.*,com.wl.common.OrderStatus,com.wl.common.ProductStatus"  pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>删除四月一号之前的外协单</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
	<body style="height: 100%;">
		<a class="mini-button" iconCls="icon-remove" plain="false" onclick="remove()">删除</a>
	
		<script>
		mini.parse();
	       
        function remove(){   
    		  var url='DeleteOutAssistMenuBeforeServlet';
    		  var params=null;
    		  jQuery.post(url, params, function(data){
   			     alert(data.result);},'json')
          }
		</script>
	</body>
</html>

