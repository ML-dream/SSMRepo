<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:useBean id="fobeandao" scope="page" class="cfmes.bom.dao.FoBeanDao"/>
<jsp:directive.page import="cfmes.bom.entity.Dept"/>
<jsp:useBean id="ds" scope="page" class="cfmes.util.DealString"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%//从共享数据中取得数据
    String item_id      = (String )session.getAttribute("item_id");
	String product_type  = (String)session.getAttribute("flight_type");
    String product_id   = (String)session.getAttribute("product_id");
    String issue_num    = (String)session.getAttribute("issue_num");
    String fo_no = ds.toString((String)request.getParameter("fo_no"));
    String fover = ds.toString((String)request.getParameter("fover"));
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>该工序下的刀、量、夹、原材料、辅料添加、修改操作</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link href="../../css/person.css" type=text/css rel=stylesheet>
    <script type="text/javascript" src="../../js/Fo_Select.js"></script>

    <script type="text/javascript">
     function toservlet(){   
     if (document.all.oper_id.value=="" ){alert("请先编制此fo下的工序！");return ;}
        document.all.form_cmtyf.dotype.value ="addcmtyf";
		document.all.form_cmtyf.action = "../../FocmtyfSvlt";
		document.all.form_cmtyf.submit();
    }
    
     function clearOk(){
          document.all.oper_id.value = "";
		  document.all.focut.value = "";
		  document.all.cut_num.value= "";
		  document.all.fomeasure.value = "";
		  document.all.measure_num.value = "";
		  document.all.fotool.value = "";
		  document.all.tool_num.value = "";
		  document.all.fomaterial.value = "";
		  document.all.material_num.value = "";
		  document.all.foaccessory.value = "";
		  document.all.accessory_num.value = "";
    }
     
    function delcmtyf(){
    if(document.getElementById("oper_id2").value==""){alert("请先选择已编制的fo工序，或先编制工序！");return;}
    document.form_delcmtyf.action="../../FodelcmtyfSvlt";
    document.form_delcmtyf.submit();
    }
    </script>

  </head>
  
  <body>
    <form name=form_cmtyf action="" method=post>
    <TABLE class="green_list_table" align="center" width="100%" border="1" style="word-break:break-all;border-collapse:collapse" bgcolor="#dafec5">
    <TR>
     <tr>
      <th>工序选择</th><td ><select id="oper_id" name="oper_id" style="width:130px;">
	          <% ArrayList list = fobeandao.getFooper(product_type,product_id,issue_num,item_id,fo_no,fover);
	             if(list.size()!=0 && list!=null){ 
	                for(int i = 0;i<list.size();i++){
	                   Dept dept = (Dept)list.get(i);%>
	                <option value="<%=dept.getDept_id()%>" selected><%=dept.getDept_name()%></option>
	                <%} 
	             }else{%>
	                <option value="">---暂无数据---</option>
	             <%}%> </select></td> </tr>
   <tr><th>使用刀具</th><td ><select id="focut" name="focut" style="width:130px;">
	          <% ArrayList list2 = fobeandao.getCMT("C");
	             if(list2.size()!=0 && list2!=null){ 
	                for(int i = 0;i<list2.size();i++){
	                   Dept dept = (Dept)list2.get(i);%>
	                <option value="<%=dept.getDept_id()%>" selected><%=dept.getDept_name()%></option>

	                <%} %>
	                 <option value="" >不使用刀具</option>
	                <% }else{%>
	                <option value="">---暂无数据---</option>
	             <%}%> </select></td> 
	<th>使用刀具数量</th><td><input type="text" name="cut_num" size="20" maxlength="9"></td></tr>
	<tr>
	<th>使用量具</th><td ><select id="fomeasure" name="fomeasure" style="width:130px;">
	          <% ArrayList list3 = fobeandao.getCMT("M");
	             if(list3.size()!=0 && list3!=null){ 
	                for(int i = 0;i<list3.size();i++){
	                   Dept dept = (Dept)list3.get(i);%>
	                <option value="<%=dept.getDept_id()%>" selected><%=dept.getDept_name()%></option>
	                <%} %>
	                 <option value="" >不使用量具</option>
	             <% }else{%>
	                <option value="">---暂无数据---</option>
	             <%}%> </select></td>
	 <th>使用量具数量</th><td><input type="text" name="measure_num" size="20" maxlength="9"></td></tr>            
    <tr><th>使用夹具</th><td ><select id="fotool" name="fotool" style="width:130px;">
	          <% ArrayList list4 = fobeandao.getCMT("T");
	             if(list4.size()!=0 && list4!=null){ 
	                for(int i = 0;i<list4.size();i++){
	                   Dept dept = (Dept)list4.get(i);%>
	                <option value="<%=dept.getDept_id()%>" selected><%=dept.getDept_name()%></option>
	                <%}%>
	                  <option value="" >不使用夹具</option>
	             <% }else{%>
	                <option value="">---暂无数据---</option>
	             <%}%> </select></td>
	    <th>使用夹具数量</th><td><input type="text" name="tool_num" size="20" maxlength="9"></td></tr>         
	<tr><th>原材料</th><td ><select id="fomaterial" name="fomaterial" style="width:130px;">
	          <% ArrayList list6 = fobeandao.getCMT("Y");
	             if(list6.size()!=0 && list6!=null){ 
	                for(int i = 0;i<list6.size();i++){
	                   Dept dept = (Dept)list6.get(i);%>
	                <option value="<%=dept.getDept_id()%>" selected><%=dept.getDept_name()%></option>
	                <%}%>
	                  <option value="" >此工序无原材料</option>
	             <% }else{%>
	                <option value="">---暂无数据---</option>
	             <%}%> </select></td>
	     <th>使用原材料数量</th><td><input type="text" name="material_num" size="20" maxlength="9"></td></tr>        
	<tr><th>辅料</th><td ><select id="foaccessory" name="foaccessory" style="width:130px;">
	          <% ArrayList list7 = fobeandao.getCMT("F");
	             if(list7.size()!=0 && list7!=null){ 
	                for(int i = 0;i<list7.size();i++){
	                   Dept dept = (Dept)list7.get(i);%>
	                <option value="<%=dept.getDept_id()%>" selected><%=dept.getDept_name()%></option>
	                <%}%>
	                  <option value="" >此工序无辅料</option>
	             <% }else{%>
	                <option value="">---暂无数据---</option>
	             <%}%> </select></td>
	<th>使用辅料数量</th><td><input type="text" name="accessory_num" size="20" maxlength="9"></td></tr>             
	<tr><td><input type="button"  value="保 存" onclick="toservlet();" style="cursor:hand;">
	     <input type="button"  value="清 空" title="清空文本框" onclick="clearOk();" style="cursor:hand;">
	     <input type="hidden" name="dotype" value="">
	     <input type="hidden" name="fo_no" value="<%=fo_no %>">
	     <input type="hidden" name="fover" value="<%=fover%>"></td>
	</tr>
</TABLE>
<br>

</form>
<form name="form_delcmtyf">
<table class="data_list_table" width="100%" align="center" id=theObjTable STYLE="table-layout:fixed" border="1" style="word-break:break-all;">
  <TR  height="22">
	<Th align=center width=80 ><font color="#000000"><B>操作</B></font></th>
    <Th align=center width=140 ><font color="#000000" ><B>工序选择</B></font></th>
    <Th align=center width=140 ><font color="#000000"><B>刀具选择</B></font></th>
    <Th align=center width=140 ><font color="#000000"><B>量具选择</B></font></th>
    <Th align=center width=140 ><font color="#000000"><B>夹具选择</B></font></th>
    <Th align=center width=140 ><font color="#000000" ><B>原材料</B></font></th>
    <Th align=center width=140 ><font color="#000000" ><B>辅料</B></font></th>
  </TR>
  <tr>
  <td><font><A onclick="delcmtyf(); "style="cursor:hand;"><img title="删除此共序" src="../../img/bt_del.gif" class="op_button" /></A></FONT></td>
  <td ><select id="oper_id2" name="oper_id2" style="width:130px;" onchange = "select(this.value,'<%=fo_no%>','<%=fover%>');">
	          <%  if(list.size()!=0 && list!=null){ 
	                for(int i = 0;i<list.size();i++){
	                   Dept dept = (Dept)list.get(i);%>
	                <option value="<%=dept.getDept_id()%>" ><%=dept.getDept_name()%></option>
	                <%}%>
	                  <option value="" selected>请选择工序</option>
	             <% }else{%>
	                <option value="">---暂无数据---</option>
	             <%}%> </select></td>
  <td><select id="fo_cut" name="fo_cut" style="width:130px;">
  <option value="">-----</option></select></td>
  <td><select id="fo_measure" name="fo_measure" style="width:130px;">
  <option value="">-----</option></select></td>
  <td><select id="fo_tool" name="fo_tool" style="width:130px;">
  <option value="">-----</option></select></td>
  <td><select id="fo_material" name="fo_material" style="width:130px;">
  <option value="">-----</option></select></td>
  <td><select id="fo_accessory" name="fo_accessory" style="width:130px;">
  <option value="">-----</option></select></td>
   <td></td>  <input type="hidden" name="fo_no" value="<%=fo_no %>">
	     <input type="hidden" name="fover" value="<%=fover%>"></td>
  </tr>
</table>
</form>
  </body>
</html>
