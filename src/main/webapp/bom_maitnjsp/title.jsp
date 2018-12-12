<%@ page language="java" contentType="text/html; charset=utf-8" errorPage="error.jsp"%>
<jsp:useBean id="titles" scope="page" class="Bom.Titles"/>
<jsp:useBean id="ds" scope="page" class="cfmes.util.DealString"/>
<% request.setCharacterEncoding("utf-8"); response.setHeader("Charset","utf-8");  
    String flight_type = ds.toString(ds.toGBK(request.getParameter("flight_type")));
    String product_id  = ds.toString(ds.toGBK(request.getParameter("product_id")));
    String lot         = ds.toString(ds.toGBK(request.getParameter("lot")));
    String sortie      = ds.toString(ds.toGBK(request.getParameter("sortie")));
    String issue_num   = ds.toString(ds.toGBK(request.getParameter("issue_num")));
    String item_id     = ds.toString(ds.toGBK(request.getParameter("item_id")));
    //System.out.println("title-product_id: "+product_id);
    //System.out.println("title-item_id: "+item_id);
%>
<html>
<head>
</head>
<link href="../css/person.css" type=text/css rel=stylesheet>
<body>
  <table class="query_form_table" width="100%" align="center"  border="0" style="word-break:break-all;">
<tr align=center><th>产品类型</th><th>产品名称</th><th>批次</th><th>编号</th><th>版本</th><th>物料</th><th>物料类型</th></tr>
<tr><td align=center><%=flight_type%></td>
    <td align=center><%=titles.getProduct_name(product_id)%></td>
    <td align=center><%=lot%></td>
    <td align=center><%=sortie%></td>
    <td align=center><%=issue_num%></td>
    <td align=center><%=item_id %></td>
<%--    <td align=center><%=titles.getItem_name(item_id)%></td>--%>
    <td align=center><%=titles.getItem_type(item_id)%></td>
    </tr>
  </table>
  <hr color=yellow>
  </body>
</html>