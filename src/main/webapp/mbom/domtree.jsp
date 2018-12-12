<%@ page language="java"  contentType="text/html; charset=utf-8" errorPage="error.jsp"%>
<jsp:useBean id="ds" scope="page" class="cfmes.util.DealString"/>
<HTML><HEAD><TITLE></TITLE>
<%    
        String product_id = ds.toGBK(request.getParameter("product_id"));
		String issue_num = ds.toGBK(request.getParameter("issue_num"));
		String node_id = ds.toGBK(request.getParameter("node_id"));
		String memo1 = ds.toGBK(request.getParameter("memo1"));
		String memo2 = ds.toGBK(request.getParameter("memo2"));
		String memo3 = ds.toGBK(request.getParameter("memo3"));
		String memo4 = ds.toGBK(request.getParameter("memo4"));
		 
 %>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<LINK href="" type=text/css rel=stylesheet>

<META content="MSHTML 6.00.2900.3527" name=GENERATOR></HEAD>
<FRAMESET border=0 frameSpacing='10' border=0 rows=80,*>
<FRAME name=nodetitle frameborder='0' height="100%" marginHeight="0" MARGINWIDTH="10" scrolling=yes target="donode" src="../partplan/nodetitle.jsp?product_id=+<%=product_id %>+&issue_num=+<%=issue_num %>+&node_id=+<%=node_id%>+&memo1=+<%=memo1%>+&memo2=+<%=memo2%>+&memo3=+<%=memo3%>+&memo4=+<%=memo4%>">
<FRAME name=donode frameborder='0'  src="../partplan/donode.jsp" marginHeight="0" height="100%" width="100%"><NOFRAMES>
  <body>&nbsp;
</body>
</NOFRAMES></FRAMESET></HTML>
