<%@ page errorPage="error.jsp" language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<jsp:useBean id="ds" scope="page" class="cfmes.util.DealString"/>
<html>
  <head>
  
  </head>
  <body background="#dafec5">
<%
    String aofo = (String)session.getAttribute("aofo");
    if(aofo.equals("AO")){
      out.println("<script>window.location.href='ao/ao.jsp;'</script>"); 
    }else if(aofo.equals("FO")){    
      out.println("<script>window.location.href='fo/fo.jsp;'</script>");  
    }else{ 
%>
  <table>
    <tr ><h2 >该物料还未添加工艺路线，请选择其工艺路线类型：</h2></tr>
    <tr>
    <td style="font-size:20px;" valign="bottom"><a href="ao/ao.jsp"><font color="#ff0000">AO(装配工艺路线)</font></a></td>
    <td>&nbsp;&nbsp;</td>
    <td>&nbsp;&nbsp;</td>
    <td style="font-size:20px;" valign="bottom"><a href="fo/fo.jsp"><font color="#008080">FO(制造工艺路线)</font></a></td>
    </tr>
    </table>
<%}%>
  </body>
</html>