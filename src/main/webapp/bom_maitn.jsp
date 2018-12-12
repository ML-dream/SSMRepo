<%@ page language="java"  contentType="text/html; charset=utf-8" errorPage="error.jsp"%>
<jsp:useBean id="ds" scope="page" class="cfmes.util.DealString"/>
<HTML><HEAD><TITLE></TITLE>
<% request.setCharacterEncoding("utf-8"); response.setHeader("Charset","utf-8");  %>
<jsp:useBean id="capp" class="cfmes.bom.entity.Capp" scope="request" />

<%
    String flight_type = ds.toURL(ds.toString(ds.toGBK(request.getParameter("product_type"))));//tree_session.getFlight_type();
    String product_id  = ds.toURL(ds.toString(ds.toGBK(request.getParameter("product_id"))));//(String)tree_session.getProduct_id();
    String lot         = ds.toURL(ds.toString(ds.toGBK(request.getParameter("lot"))));
    String sortie5      = ds.toURL(ds.toString(ds.toGBK(request.getParameter("sortie"))));
    
    String issue="1";
    String issue_num="1";
    String sortie="1"; 
    //sortie5======1601---1601---160108---1601
    if(!sortie5.equals("")){
    String[] num = new String[4];
    num = sortie5.split("---");
    System.out.println("sortie5======"+sortie5);
    issue       = num[3]; 			//对应issue_deploy中的issue_pos==>memo
    issue_num  = ds.toURL(num[2]); 	//对应issue_deploy中的issue_num==>issue_num
    sortie     = ds.toURL(num[0]+"-"+num[1]); 
    }else{
    flight_type="1";
    product_id="1";
    lot="1";
    }
%>

<META http-equiv=Content-Type content="text/html; charset=utf-8">
<LINK href="" type=text/css rel=stylesheet>
<SCRIPT type=text/javascript>
     function getUrlByCatalogId(flight_type,product_id,lot,sortie,issue_num,issue,item_id,father_item_id,id,fid,level_id,issue_100,aofo) {
     return "frame_ud.jsp?flight_type="+flight_type+"&product_id="+product_id+"&lot="+lot+"&sortie="+sortie+"&issue_num="+issue_num+"&issue="+issue+"&item_id="+item_id+"&father_item_id="+father_item_id+"&id="+id+"&fid="+fid+"&level_id="+level_id+"&issue_100="+issue_100+"&aofo="+aofo;
     }
</SCRIPT>
<META content="MSHTML 6.00.2900.3527" name=GENERATOR></HEAD>

<FRAMESET border=0 frameSpacing='10' border=0 cols=150,*>
<FRAME name=dtree frameborder='0' height="100%" marginHeight="0" MARGINWIDTH="10"  target="list" src="tree.jsp?product_id=+<%=product_id%>+&flight_type=+<%=flight_type%>+&issue_num=+<%=issue_num%>+&lot=+<%=lot%>+&sortie=+<%=sortie%>+&issue=+<%=issue%>+" >
<FRAME name=list frameborder='0'  onscroll="alert('dd')" src="frame_ud.jsp" marginHeight="0" height="100%" width="100%">
 
</FRAMESET></HTML>
