<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>My JSP 'alreadyhere.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <% String id = request.getParameter("id"); %>
    <script> 
        function GetRequest() { 
			var url = location.search; //获取url中"?"符后的字串 
			var theRequest = new Object(); 
			if (url.indexOf("?") != -1) { 
			var str = url.substr(1); 
			strs = str.split("&"); 
			for(var i = 0; i < strs.length; i ++) { 
			theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
			} 
			} 
			return theRequest; 
		} 
        
        var Request = new Object(); 
		Request = GetRequest(); 
		id = Request['id'];
		//alert("id=="+id);
    
		var t=3;//设定跳转的时间 
		setInterval("refer()",1000); //启动1秒定时 
		function refer(){ 
		if(t==0){ 
		   location="<%=basePath%>GT/demo/wlJsp.jsp?id="+id+""; //#设定跳转的链接地址 
		   //alert(location);
		} 
		document.getElementById('show').innerHTML="<span  style='color: red;font-weight: bolder;'>"+t+"</span>秒后跳转到此零件工序计划甘特图"; // 显示倒计时 
		t--; // 计数器递减 
		//本文转自： 
		} 

	</script> 
    <h1 align="center" style="color: red;font-weight: bolder;">此零件已经做过工序计划了！！！</h1>
    <br/><br/>
    <div id="show" align="center"></div>
  </body>
</html>
