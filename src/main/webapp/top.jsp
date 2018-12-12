<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<%@ page language="java" import="java.util.*,java.util.ArrayList" %>
<%@ page language="java" import="cfmes.bom.entity.Menu" %>
<jsp:useBean id="menubean" scope="page" class="cfmes.bean.MenuBean"/>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>顶部图片和菜单</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/mb5uv4.css">
	<script type="text/javascript" src="js/index.js"></script>
	<link rel="stylesheet" type="text/css">
		
	</link>

  </head>

  <body>
    <div>
    	<table width = 100% border=0 cellpadding=0 height ="40px;" >
    		<tr width=100% heigtht="50px;"><td width=100% heigtht="40px;"><img src="img/fucai3.png" width="1360px;" height="80px;" alt="" /></td></tr>
    	</table>
    </div>
    
    <div id=menu >
    <dl class=topmenu style="background-color: #B0E0E6;">
    <% 
    ArrayList menulist =new ArrayList();
    menulist = menubean.GetMenu();
    if(menulist.size()==0){
   %>
<DT class=menu_first><A class=selected id=nav1 onmouseover=javascript:change(this)><SPAN>首页</SPAN></A></DT>
<DD>
  <UL id=sub1>
    <LI>没有可操作的菜单^_^</LI>
  </UL>
</DD>
<%}else{
    for(int i=0;i<menulist.size();i++){
       Menu menu = (Menu)menulist.get(i);
%>  
    <dt <%if(i==0){ %>class=menu_first<%} %>><A <%if(i==0){ %>class = selected<%} %>  id=nav<%=(i+1) %> onclick=javascript:doClick(this)><span><%=menu.getMenu_name() %></span></A>
    </dt>
    <dd>
    <ul <%if(i>0){ %>class=undis<%} %> id=sub<%=(i+1) %> >
    <%ArrayList childmenulist =new ArrayList();
      childmenulist = menubean.GetChildMenu(menu.getMenu_id());
      for(int j=0;j<childmenulist.size();j++){
      Menu childmenu = (Menu)childmenulist.get(j);
     %>
    <li><a href="<%=childmenu.getMenu_id() %>" target = "content"><%=childmenu.getMenu_name() %></a>
    </li>
    <%} %>
    </ul>
    </dd>
    <%}} %>
    </dl>
    </div>
  </body>
</html>
