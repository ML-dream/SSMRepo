<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%
String do_type = "";
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	 
	 <link rel="StyleSheet" href="css/dtree.css" type="text/css" />
	 <link rel="StyleSheet" href="css/person.css" type="text/css" />
	
	<script language="javaScript" type="text/javascript" src="js/dtree.js"></script>
  </head>
 
 
  <body onload="buildtree();">
   <div>
   <a href="javascript: d.openAll();"> 全展</a> | 
	  <a href="javascript: d.closeAll();">全关</a> | <a href="javascript: location.reload();">刷新</a>
	  <input type = "button" onclick="buildtree();"/>
   </div>
   
   <div id = "dtree" class = "dtree" >
   <script type="text/javascript">
		d = new dTree('d','','dtree');
	function buildtree(){	
		d.add('top','x','EBOM结构树',null,null,null,'','','');
        d.add('jsq','top','jsq【部件】','..','部件','list','','','','01','lucky');
 
        d.add('xt','jsq','xt[组件]','..','组件','list','');
        d.add('z','xt','z【轴】','....','零件','','','','','02','lucky');
		d.draw();
	}	

		
	</script>
	
   </div>
    <input type = "button" onclick="mod('11-21231')"/>
   <select name = "wcid" id = "wcid">
   <option value = "1">-----</option>
   <option value = "2">11</option>
   <option value = "3">432143214321</option>
   </select>
  
   
   <script>
   function mod(data){
    var arr = new Array();
    var arr2 = new Array();
    arr = data.split("-");
 
    //document.getElementById("wcid").selectedIndex = 1;
   for(var i =0;i<document.getElementById("wcid").length;i++){
   a
    if(arr[0]==document.getElementById("wcid").options[i].text)document.getElementById("wcid").selectedIndex = i;
   
   }
   
   }
   </script>
  </body>  
</html>
