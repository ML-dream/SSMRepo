<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld" %>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*,com.wl.forms.Parts" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <title>My JSP 'workplan.jsp' starting page</title>  
    <script type="text/javascript" src="./hidden_show.js"></script>  
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<script type="text/javascript" src="./js/wlcore.js"></script>
	<script type="text/javascript" src="./js/wlcalendar.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	
	
	<link rel="icon" href="<%=basePath%>rili/favicon.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="<%=basePath%>rili/favicon.ico" type="image/x-icon"/>
	<link href="<%=basePath%>rili/index.css" type="text/css" rel="stylesheet"/>
	<link href="<%=basePath%>rili/prettify/prettify.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=basePath%>rili/prettify/prettify.js"></script>
	<script type="text/javascript" src="<%=basePath%>rili/lhgcore.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>rili/lhgcalendar.min.js"></script>
	<script type="text/javascript">
		J(function(){
			
			J('#starttime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
			J('#endtime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
			J('#preparetime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
			J('#plantime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });	
		});
	</script>
	
	
	
  </head> 
  <body>
    <% Parts parts = (Parts)request.getAttribute("parts");
      %> 
    <p>零件ID: ${param.id}</p>  <p>零件名: <%=parts.getPart_pid()%></p>
    <table border="solid 1px grey" id="parts_table">
      <tr>
        <td>零件ID号</td><td>零件名</td><td>开工时间</td><td>完工</td>
        <td>准备时间</td><td>计划时间</td><td>计划人数</td><td>图号</td>
      </tr>
      
        
      <tr>
        <td><%=parts.getPart_id()%></td>
        <td><%=parts.getPart_pid()%></td>
        <td><%=parts.getStarttime()%></td>
        <td><%=parts.getEndtime()%></td>
        <td><%=parts.getPreparetime()%></td>
        <td><%=parts.getPlantime()%></td>
        <td><%=parts.getPeoplenm()%></td>
        <td><%=parts.getPicnm()%></td>
      </tr> 
      
     
    </table>
    <br/>
    <table border="solid 1px grey" id="add_parts_table" style="display: none">
      <tr>
        <td>零件ID号</td><td>零件名</td><td>开工时间</td><td>完工</td>
        <td>准备时间</td><td>计划时间</td><td>计划人数</td><td>图号</td>
      </tr>
       <tr bgcolor="grey">
        <form action="/test/workplan_disp.do?flag=add" method="post" >
          <td><input type="text" name="part_id" size="10"/></td>
          <td><input type="text" name="part_pid" size="10"/></td>
          <td><input type="text" id="starttime" name="starttime" size="10" onclick="J.calendar.get({time:true});"/></td>
          <td><input type="text" id="endtime"  name="endtime" size="10"  onclick="J.calendar.get({time:true});"/></td>
          <td><input type="text" id="preparetime"  name="preparetime" size="10" onclick="J.calendar.get({time:true});"/></td>
          <td><input type="text"  id="plantime" name="plantime" size="10" onclick="J.calendar.get({time:true});"/></td>
          <td><input type="text" name="peoplenm" size="10"/></td>
          <td><input type="text" name="picnm" size="10"/></td>
          <td><input type="submit" value="保存"></td>
          <button onclick="hidden_table()">取消添加</button>
        </form>
      </tr>
    </table>
    
    <button onclick="show_table()" id="add">添加零件</button>
    <a href="/test/workplan_disp.do?flag=add">ceshi</a>
    
  </body>
</html>
