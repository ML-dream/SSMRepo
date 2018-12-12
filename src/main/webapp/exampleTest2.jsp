<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>产品出库</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">-->
	
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
    <style>
	#showbox {
		width: 150px;
		min-height: 50px;
		font: 100 14px/1 "微软雅黑";
		border: 1px solid #3c8dbc;
		display: none;
		position: absolute;
		top: 0;
		left: 0;
		background-color: #fff;
		padding: 5px;
	}
</style>
    
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  
  <body>
    <table id="checkout" style="text-align: right;border-collapse:collapse;" border="gray 1px solid;">
   		<tbody>
   		<tr bgcolor=#EBEFF5>
            <td><label for="checksheet_id$text">单号</label></td>
            <td><input id="checksheet_id" name="checksheet_id" type="text" width="20"  required="true" value="鼠标悬停测试使用界面鼠标悬停测试使用界面鼠标悬停测试使用界面"/></td>
   			
        	<td><label for="connector$text">联系人</label></td>
            <td><input id="connector"  name="connector" class="text" required="true" width="100%" /></td>
        </tr>
       	
 		</tbody>
   		</table>
   		<div id="showbox"></div>
   		<script>
   		$(function() {
		
		function showBox(){
			var timer = null;
			$("#checkout > tbody td").on("mouseover", function (e) {
			clearTimeout(timer);
			var clientX = e.clientX;
			var clientY = e.clientY;
			var txt = $(this).text();
			timer = setTimeout(function () {
				console.log(clientX, clientY);
				$("#showbox").css("left", clientX).css("top", clientY);
			
				if (txt == "") {
					$("#showbox").hide();
				} else {
					$("#showbox").show();
					$("#showbox").innerHtml(txt);
				}
				}, 1000);
			});
		$("#showbox").on("mouseout",function(){
			clearTimeout(timer);
			$(box).hide();
			});
		}
   		
		//showBox("#checkout > tbody td","#showbox");
		}
 		);
		
   		</script>
  </body>
</html>
