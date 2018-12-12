<%@ page language="java" import="java.util.*,com.wl.forms.Partplan" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>My JSP 'add_part_processes.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<script type="text/javascript" src="./js/wlcore.js"></script>
	<script type="text/javascript" src="./js/wlcalendar.js"></script>  
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action="#">
		  <input type="radio" name="part" value="part1" >零件1</input>
		  <input type="radio" name="part" value="part2" >零件2</input>
		</form>
		<select id="QueryParts" style="display: none;">
		  <option value="part1" selected="selected">零件1</option>
		  <option value="part2" >零件2</option>
		  <option value="part3" >零件3</option>
		  <option value="part4" >零件4</option>
		  <option value="part5" >零件5</option>
		</select>
		
		<table>
		  <thead>零件计划列表</thead>
		  <tr>
		
	  <% ArrayList al=(ArrayList)request.getAttribute("partplan");
         int i=0;
         for(i=0;i<al.size();i++){
             Partplan partplan = new Partplan();
             partplan = (Partplan)al.get(i);
      %>
            <td><a href="#"><%=partplan.getPartname() %>[<%=partplan.getProductID() %>] </a></td>

      <%
         }
      %>
            </tr>
		</table>
		<br/><br/>

		<table id="addpartplan" width="900px;" onclick="addpart();" style="display: none;">
		 <form>
		  <thead>添加零件计划</thead>
		  <tr style="background-color: #0000ee;"><td>零件号</td><td>物料号</td><td>零件数量</td><td>开工时间</td><td>结束时间</td><td>零件状态</td><td>完成量</td></tr>
		  <tr><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td></tr>
		  <tr style="background-color: #0000ee;"><td>次品数量</td><td>合格数量</td><td>入库数量</td><td>出库数量</td><td>入库时间</td><td>是否取消</td><td></td></tr>
		  <tr><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td></tr>
		  <tr><td><input type="submit" value="添加零件"/></td><td><input type="reset" value="重置"/></td></tr>
		 </form>
		</table>
		
		<table id="addprocessplan" width="700px;" onclick="addprocess();" style="display: none;">
		  <thead>添加工序计划</thead>
		  <tr style="background-color: #0000ee;"><td>工序号</td><td>产品号</td><td>物料号</td><td>开工时间</td><td>完工时间</td></tr>
		  <tr><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td></tr>
		  <tr style="background-color: #0000ee;"><td>总数量</td><td>完成数量</td><td>次品数量</td><td>工序状态</td><td>是否撤销</td></tr>
		  <tr><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td><td><input type="text"/></td></tr>
		</table>
		
		
		
  </body>
</html>
