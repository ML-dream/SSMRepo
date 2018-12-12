<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.wl.forms.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>编制装配计划</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">

	<link rel="stylesheet" href="../css/person.css" type="text/css"></link>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="scripts/jquery-1.8.0.js"></script>
	<script type="text/javascript" src="../js/wlcore.js"></script>
	<script type="text/javascript" src="../js/wlcalendar.js"></script>
	
	
	
	<!-- 
	<link rel="icon" href="<%=basePath%>rili/favicon.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="<%=basePath%>rili/favicon.ico" type="image/x-icon"/>
	 -->
	<link href="<%=basePath%>rili/index.css" type="text/css" rel="stylesheet"/>
	<link href="<%=basePath%>rili/prettify/prettify.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=basePath%>rili/prettify/prettify.js"></script>
	<script type="text/javascript" src="<%=basePath%>rili/lhgcore.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>rili/lhgcalendar.min.js"></script>
	<script type="text/javascript">
		J(function(){
			
			J('#START_TIME').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
			J('#END_TIME').calendar({ format:'yyyy-MM-dd HH:mm:ss' });	
		});
	</script>
  </head>
  
  <script>
  function showdate(times){
	   
	   /*显示当前日期*/

	   var date = new Date();
	   var datemonth = date.getMonth()+1; 
	   // alert(datemonth.toString().length);
	   if(datemonth.toString().length==1){
	       datemonth="0"+datemonth;
	   }
	   
	   var datestring = date.getFullYear()+"-"+datemonth+"-"+date.getDate()+" "+
	   		date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
	   
	   if(times=="PLAN_TIME"){
		   /*计划开始时间和结束时间都默认是今天*/
		   document.getElementById("PLAN_TIME").value =datestring;
		   document.getElementById("START_TIME").value =datestring;
		   document.getElementById("END_TIME").value   =datestring;
	   }
  }
</script>
  <body>
	<form name = "AoPlanform" action = "/work/AoPlanServlet">
      <table align="center" class = "data_list_table">
       	<tr>
       	  <th>计划编号：</th><td><input type="text" id = "PLAN_ID" name="PLAN_ID" maxlength="63" size="30" onfocus="showdate('PLAN_TIME');"></input></td>
       	  <th>计划时间：</th><td><input type="text" id = "PLAN_TIME" name="PLAN_TIME" maxlength="63" readonly="readonly" size="30"></input></td>
       	  <th>计划人：   </th><td><input type="text" id = "PLAN_PERSON" name="PLAN_PERSON" maxlength="63" size="30" value="<%=((User)session.getAttribute("user")).getStaffName()%>"></input></td></tr>  
       	<tr>
       	  <th>订单号：   </th><td><input type="text" id = "ORDER_ID" name = "ORDER_ID" value="" size="30" ></input></td>
       	  <th>产品号：   </th><td><input type="text" id = "PRODUCT_ID" name = "PRODUCT_ID" value="" size="30" ></input></td>
       	  <th>产品数量：</th><td><input type="text" id = "NUM" name = "NUM" size="30" ></input></td></tr>
        <tr>
       	  <th>计划开始时间：</th><td><input type="text" id = "START_TIME" name = "START_TIME" size="30"  onclick="J.calendar.get({time:true});" ></input></td>
       	  <th>计划结束时间：</th><td><input type="text" id = "END_TIME" name = "END_TIME" size="30"  onclick="J.calendar.get({time:true});"></input></td>
       	  <th>质编号：          </th><td><input type="text" id = "QUALITY_ID" name = "QUALITY_ID" size="30" ></input></td></tr>       	
       	<tr>
       		<%if(request.getAttribute("addOk")!=null){%>
	  		<span style="color: red;"><%=request.getAttribute("addOk") %></span>
		  	<%
		  	} %>
       	  <td><input type="submit" value="提交计划"  />
       	      <input type = "reset" value = "取消计划"/></td><td><br></td></tr>
      		
       </table>
     </form>
  </body>
</html>














