<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<jsp:useBean id="ds" scope="page" class="cfmes.util.DealString"/>
<jsp:useBean id="partplandao" scope="page" class="cfmes.bom.dao.PartPlanDao"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%  
        String dotype = "";
        String product_id = ds.toGBK(request.getParameter("product_id"));
		String issue_num = ds.toGBK(request.getParameter("issue_num"));
		String node_id = ds.toGBK(request.getParameter("node_id"));
		String memo1 = ds.toGBK(request.getParameter("memo1"));
		String memo2 = ds.toGBK(request.getParameter("memo2"));
		String memo3 = ds.toGBK(request.getParameter("memo3"));
		String memo4 = ds.toGBK(request.getParameter("memo4"));
		
		/*查看该节点，若非零件则不可进行零件编制，若是零件，显示该零件计划状态
		  ，并显示相应的操作按钮！*/
		String statusresult=""; //状态描述
		String statusdo="";//针对此状态的操作类型
		boolean ispart=false;//是否为零件
		String da="button";
		String partstatus="";//状态代号
		/*查看是否为零件*/
		ispart = partplandao.IsPart(node_id);
		/*查看零件计划状态*/
		partstatus = partplandao.PartPlanStatus(product_id,issue_num,node_id);
		if(partstatus==null||"".equals(partstatus)){partstatus="0";}
		/*查看描述和操作*/
		statusresult=partplandao.StatusResult(partstatus);
		statusdo=partplandao.StatusDo(partstatus);
		
		String show1="";//显示是否为零件和零件状态
		String show2 = "";//显示应该的操作类型
		if(!ispart){
		show1="非零件";
	    show2 = "无零件计划";
	    da = "hidden";
		}else{
	    show1=statusresult;
	    show2=statusdo;
	    dotype=partstatus;
	    da="button";
		}
 %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base target="donode">
    
    <title>mtree处理界面</title>
    
    
    
    <!-- 编制零件计划      mtree处理界面 -->
    
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="../css/person.css" type=text/css rel=stylesheet>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  <script>
  function donode(type){
    document.forsubmit3.dotype.value = type;
    document.forsubmit3.action = "donode.jsp";
    document.forsubmit3.submit();
  }
  function doaoplan(){
    document.forsubmit3.action = "aoplan.jsp";
    document.forsubmit3.submit();
  }
  </script>
  </head>
  
  <body>
    mtree处理界面 <br>
   <table class="query_form_table" border="1" align="center"  cellspacing="0" width="100%" style="word-break:break-all;" >
    <tr><th>操作</th><th>产品号</th><th>版本号</th><th>ID号</th><th>物料号</th><th>ao/fo号</th><th>ao/fo版本</th><th>fo工序号</th></tr>
    <tr>
        <td>
                   状态：<%=show1 %>
        <%if("非零件".equals(show1)){ %>
        	<input type="button" value="编制零件计划" onclick = "donode();"/>
        <%} %>
        <input  name="dopart" id="dopart" type="<%=da %>" value = "<%=show2 %>" onclick = "donode('<%=dotype %>');" style="cursor:hand;">
        </td>
        <td><%=product_id %></td><td><%=issue_num %></td><td><%=node_id %></td>
        <td><%=memo4%></td><td><%=memo1 %></td><td><%=memo2 %></td><td><%=memo3 %></td>
        </tr>  
    </table>
     <form name = "forsubmit3">
    <input type = "hidden" name = "dotype" value = "<%=dotype%>">
    <input type = "hidden" name = "product_id" value = "<%=product_id%>">
    <input type = "hidden" name = "issue_num" value = "<%=issue_num%>">
    <input type = "hidden" name = "node_id" value = "<%=node_id%>">
    </form> 
    <hr color=yellow>  
    
  </body>
</html>
