<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<jsp:useBean id="ds" scope="page" class="cfmes.util.DealString"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

 <%
    String flight_type = ds.toURL(ds.toString(ds.toGBK(request.getParameter("product_type"))));//tree_session.getFlight_type();
    String product_id  = ds.toURL(ds.toString(ds.toGBK(request.getParameter("product_id"))));//(String)tree_session.getProduct_id();
    String issue_num     = ds.toURL(ds.toString(ds.toGBK(request.getParameter("issue_num"))));
    String lot         = ds.toURL(ds.toString(ds.toGBK(request.getParameter("lot"))));
   
 %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>显示界面</title>
    
    
    <!-- Bom变更量          页面下面的Bom变更量 -->
    
    
    
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
    <div class="page_title">BOM变更量 </div>
     <table class="query_form_table" id = "ecebomtable" border="1" align="center"  cellspacing="0" width="100%" style="word-break:break-all;" >
    <tr><th>操作</th><th>父物料号</th><th>物料编号</th><th>FID</th><th>ID</th><th>LEVEL_ID</th><th>变更类型</th><th>变更内容</th><th>操作时间</th>
    </tr>
    
    <%String fitem_id="",item_id="",fid ="",id="",level_id="",ec_type="",ec_con="",ec_time=""; 
    ArrayList ecbomlist = new ArrayList();
     
    %>
    <tr>
        <td align="center"><FONT color="#338800"><A onclick="doB('del'); "style="cursor:hand;">[删除]</A></FONT></td>
        <td><%=fitem_id %></td><td><%=item_id %></td><td><%=fid %></td><td><%=id %></td><td><%=level_id %></td><td><%=ec_type%></td>
        <td><%=ec_con %></td><td><%=ec_time%></td>
    </tr>
        
        
        
    <tr height=25>
		<td align=center colspan=18 >
		共有记录数:当前  页
		<a onclick="firsrpg();" style="cursor:hand">第一页</a>
		<a onclick="lastpg();" style="cursor:hand">上一页 </a>
		<a onclick="nextpg();" style="cursor:hand">下一页</a>
		<a onclick="finalpg();" style="cursor:hand">最后页 </a>
		直接<input type=image src="img/hand.gif" name="gotof" onclick="return chkdata();"/>
		<input type=text size=5 name=bm class=formcolor/>页</TD>
	</tr>
    </table> 
  </body>
</html>
