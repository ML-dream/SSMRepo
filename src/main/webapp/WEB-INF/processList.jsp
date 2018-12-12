<%@ page language="java" import="java.util.*,com.wl.forms.Processes" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>工序列表</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<script type="text/javascript" src="./js/wlcore.js"></script>
	<script type="text/javascript" src="./js/wlcalendar.js"></script>
	<script type="text/javascript" src="./hidden_show.js"></script>
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
    <table border="solid 1px grey">
      <tr>
        <td>工序ID号</td><td>父ID号</td><td>工序名</td><td>开工时间</td><td>完工</td>
        <td>准备时间</td><td>计划时间</td><td>计划人数</td><td>工种</td><td>图号</td>
      </tr>
      <% ArrayList al=(ArrayList)request.getAttribute("processesList");
         int i=0;
         for(i=0;i<al.size();i++){
             Processes processes = new Processes();
             processes = (Processes)al.get(i);
      %>
       <tr>
        <td><%=processes.getProcess_id()%></td>
        <td><%=processes.getProcess_pid()%></td>
        <td><%=processes.getProcess_name()%></td>
        <td><%=processes.getStarttime()%></td>
        <td><%=processes.getEndtime()%></td>
        <td><%=processes.getPreparetime()%></td>
        <td><%=processes.getPlantime()%></td>
        <td><%=processes.getPeoplenm()%></td>
        <td><%=processes.getJobtype()%></td>
        <td><%=processes.getPicnm()%></td>
      </tr> 
      <br/>
      <
      <%
         }
      %>

    </table>  
    
    <br/>
    <table border="solid 1px grey" id="add_processes_table" style="display: none">
       <tr>
          <td>工序ID号</td><td>父ID号</td><td>工序名</td><td>开工时间</td><td>完工</td>
          <td>准备时间</td><td>计划时间</td><td>计划人数</td><td>工种</td><td>图号</td>
       </tr>
       <tr bgcolor="grey">
          <form action="/test/processes_disp.do?flag=add" id="add_processes" method="post" >
	          <td><input type="text" name="process_id" size="10"/></td>
	          <td><input type="text" name="process_pid" size="10"/></td>
	          <td><input type="text" name="process_name" size="10"/></td>
	          <td><input type="text" name="starttime" size="10" onclick="J.calendar.get({time:true});"/></td>
	          <td><input type="text" name="endtime" size="10" onclick="J.calendar.get({time:true});"/></td>
	          <td><input type="text" name="preparetime" size="10" onclick="J.calendar.get({time:true});"/></td>
	          <td><input type="text" name="plantime" size="10" onclick="J.calendar.get({time:true});"/></td>
	          <td><input type="text" name="peoplenm" size="10"/></td>
	          <td><input type="text" name="jobtype" size="10"/></td>
	          <td><input type="text" name="picnm" size="10"/></td>
	          <td><input type="submit" value="保存"></td>
	          <td><button onclick="hidden_processes_table()">取消添加</button></td>
	          
          </form>
       </tr>
    </table>
    <button onclick="show_processes_table()" id="add_processes">添加零件</button>
  </body>
</html>
