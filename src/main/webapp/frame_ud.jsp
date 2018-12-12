<%@ page language="java"  contentType="text/html; charset=utf-8" errorPage="error.jsp"%>
<jsp:useBean id="ds" scope="page" class="cfmes.util.DealString"/>
<% request.setCharacterEncoding("utf-8"); response.setHeader("Charset","utf-8");  %>
<%      
    String flight_type = ds.toString(ds.toGBK(request.getParameter("flight_type")));
    String product_id  = ds.toString(ds.toGBK(request.getParameter("product_id")));
    String lot         = ds.toString(ds.toGBK(request.getParameter("lot")));
    String sortie      = ds.toString(ds.toGBK(request.getParameter("sortie")));
    String issue_num   = ds.toString(ds.toGBK(request.getParameter("issue_num")));
    String issue       = ds.toString(ds.toGBK(request.getParameter("issue")));
    String item_id     = ds.toString(ds.toGBK(request.getParameter("item_id")));
    String father_item_id = ds.toString(ds.toGBK(request.getParameter("father_item_id")));
	String id          = ds.toString(ds.toGBK(request.getParameter("id")));
	String fid         = ds.toString(ds.toGBK(request.getParameter("fid")));
	String level_id    = ds.toString(ds.toGBK(request.getParameter("level_id")));
	String issue_100   = ds.toString(ds.toGBK(request.getParameter("issue_100")));
    //System.out.println("frame-ud-product_id: "+product_id);
    //System.out.println("frame-ud-item_id: "+item_id);
	String aofo = ds.toString(ds.toGBK(request.getParameter("aofo")));
	            flight_type = ds.toURL(flight_type);
				product_id = ds.toURL(product_id);
				issue_num = ds.toURL(issue_num);
				issue = ds.toURL(issue);
				item_id = ds.toURL(item_id);
				father_item_id = ds.toURL(father_item_id);
				id = ds.toURL(id);
				fid = ds.toURL(fid);
				level_id = ds.toURL(level_id);
				issue_100 = ds.toURL(issue_100);
				lot = ds.toURL(lot);
				sortie = ds.toURL(sortie);
%>

<%
   //使用session对象来在各个不同的jsp中传递共享数据
  session.setAttribute("flight_type",flight_type);
  session.setAttribute("product_id",product_id);
  session.setAttribute("lot",lot);
  session.setAttribute("sortie",sortie);
  session.setAttribute("issue_num",issue_num);
  session.setAttribute("item_id",item_id);
  session.setAttribute("father_item_id",father_item_id);
  session.setAttribute("id",id);
  session.setAttribute("fid",fid);
  session.setAttribute("level_id",level_id);
 
 %>
<frameset rows="40,80, *" border="0" >
	<frame name="title"  src="bom_maitnjsp/title.jsp?flight_type=+<%=flight_type%>+&product_id=+<%=product_id%>+&lot=+<%=lot%>+&sortie=+<%=sortie%>+&issue_num=+<%=issue_num%>+&item_id=+<%=item_id%>+" frameborder="0" scrolling="no" noresize marginheight="0"></frame>
	<frame name= "subtitle" src="bom_maitnjsp/subtitle.jsp" frameborder="0" scrolling="no" noresize marginheight="0"></frame>
	<frame name="work" src="bom_maitnjsp/blank.jsp" frameborder="0" scrolling="yes" noresize marginheight="0"> </frame>
</frameset>
<html>
  <head>
    <title></title>
  </head>
 <body>
 </body>
 </html>