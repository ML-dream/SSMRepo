<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<jsp:useBean id="aobeandao" scope="page" class="cfmes.bom.dao.AoBeanDao"/>
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
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>该Ao下的刀、量、夹、原材料、辅料添加、修改操作</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <link href="../../css/person.css" type=text/css rel=stylesheet>
    <script type="text/javascript" src="../../js/Ao_Select.js"></script>

    <script type="text/javascript">
     function toservlet(){   
     if (document.getElementById("oper_id")=="" ){alert("请先编制AO！");return ;}
        document.all.form_cmtyf.dotype.value ="addcmtyf";
		document.all.form_cmtyf.action = "../../AocmtyfSvlt";
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
    if(document.getElementById("aono2").value==""){alert("请先选择已编制的ao，或先编制ao！");return;}
    if(document.getElementById("ao_ver").value==""){alert("请先选择ao版本！");return;}
    document.form_delcmtyf.action="../../AodelcmtyfSvlt";
    document.form_delcmtyf.submit();
    }
    </script>
  </head>
  
  <body>
  <div>
    <form name=form_cmtyf action="" method=post>
    <TABLE class="green_list_table" align="center" width="100%" border="1" style="word-break:break-all;border-collapse:collapse" bgcolor="#dafec5">
    <TR>
     <tr>
      <th>Ao选择</th><td ><select id="aono" name="aono" style="width:130px;">
	          <% ArrayList list = aobeandao.getAo_no(product_type,product_id,issue_num,item_id);
	            Dept dept= new Dept();
	             if(list.size()!=0 && list!=null){ 
	                for(int i = 0;i<list.size();i++){
	                    dept = (Dept)list.get(i);%>
	                <option value="<%=dept.getDept_id()%>" selected><%=dept.getDept_id()%>【<%=dept.getDept_name()%>】</option>
	                <%} 
	             }else{%>
	                <option value="">---暂无数据---</option>
	             <%}%> </select></td> </tr>
   <tr><th>使用刀具</th><td ><select id="aocut" name="aocut" style="width:130px;">
	          <% ArrayList list2 = aobeandao.getCMT("C");
	             if(list2.size()!=0 && list2!=null){ 
	                for(int i = 0;i<list2.size();i++){
	                  Dept dept2 = (Dept)list2.get(i);%>
	                <option value="<%=dept2.getDept_id()%>" selected><%=dept2.getDept_name()%></option>

	                <%} %>
	                 <option value="" >不使用刀具</option>
	                <% }else{%>
	                <option value="">---暂无数据---</option>
	             <%}%> </select></td> 
	<th>使用刀具数量</th><td><input type="text" name="cut_num" size="20" maxlength="9"></td></tr>
	<tr>
	<th>使用量具</th><td ><select id="aomeasure" name="aomeasure" style="width:130px;">
	          <% ArrayList list3 = aobeandao.getCMT("M");
	             if(list3.size()!=0 && list3!=null){ 
	                for(int i = 0;i<list3.size();i++){
	                   Dept dept3 = (Dept)list3.get(i);%>
	                <option value="<%=dept3.getDept_id()%>" selected><%=dept3.getDept_name()%></option>
	                <%} %>
	                 <option value="" >不使用量具</option>
	             <% }else{%>
	                <option value="">---暂无数据---</option>
	             <%}%> </select></td>
	 <th>使用量具数量</th><td><input type="text" name="measure_num" size="20" maxlength="9"></td></tr>            
    <tr><th>使用夹具</th><td ><select id="aotool" name="aotool" style="width:130px;">
	          <% ArrayList list4 = aobeandao.getCMT("T");
	             if(list4.size()!=0 && list4!=null){ 
	                for(int i = 0;i<list4.size();i++){
	                   Dept dept4 = (Dept)list4.get(i);%>
	                <option value="<%=dept4.getDept_id()%>" selected><%=dept4.getDept_name()%></option>
	                <%}%>
	                  <option value="" >不使用夹具</option>
	             <% }else{%>
	                <option value="">---暂无数据---</option>
	             <%}%> </select></td>
	    <th>使用夹具数量</th><td><input type="text" name="tool_num" size="20" maxlength="9"></td></tr>         
	<tr><th>原材料</th><td ><select id="aomaterial" name="aomaterial" style="width:130px;">
	          <% ArrayList list6 = aobeandao.getCMT("Y");
	             if(list6.size()!=0 && list6!=null){ 
	                for(int i = 0;i<list6.size();i++){
	                   Dept dept5 = (Dept)list6.get(i);%>
	                <option value="<%=dept5.getDept_id()%>" selected><%=dept5.getDept_name()%></option>
	                <%}%>
	                  <option value="" >此Ao无原材料</option>
	             <% }else{%>
	                <option value="">---暂无数据---</option>
	             <%}%> </select></td>
	     <th>使用原材料数量</th><td><input type="text" name="material_num" size="20" maxlength="9"></td></tr>        
	<tr><th>辅料</th><td ><select id="aoaccessory" name="aoaccessory" style="width:130px;">
	          <% ArrayList list7 = aobeandao.getCMT("F");
	             if(list7.size()!=0 && list7!=null){ 
	                for(int i = 0;i<list7.size();i++){
	                   Dept dept6 = (Dept)list7.get(i);%>
	                <option value="<%=dept6.getDept_id()%>" selected><%=dept6.getDept_name()%></option>
	                <%}%>
	                  <option value="" >此Ao无辅料</option>
	             <% }else{%>
	                <option value="">---暂无数据---</option>
	             <%}%> </select></td>
	<th>使用辅料数量</th><td><input type="text" name="accessory_num" size="20" maxlength="9"></td></tr>             
	<tr><td><input type="button"  value="保 存" onclick="toservlet();" style="cursor:hand;">
	     <input type="button"  value="清 空" title="清空文本框" onclick="clearOk();" style="cursor:hand;">
	     <input type="hidden" name="dotype" value=""></td>
	     <input type="hidden" name="aover" value="<%=dept.getDept_name()%>">
	</tr>
</TABLE>
<br>
</form>
</div>
<div>
<form name="form_delcmtyf">
<table class="data_list_table" width="100%" align="center" id=theObjTable STYLE="table-layout:fixed" border="1" style="word-break:break-all;">
  <TR  height="22">
	<Th align=center width=80 ><font color="#000000"><B>操作</B></font></th>
    <Th align=center width=140 ><font color="#000000" ><B>Ao选择</B></font></th>
    <Th align=center width=140 ><font color="#000000" ><B>Ao版本选择</B></font></th>
    <Th align=center width=140 ><font color="#000000"><B>刀具选择</B></font></th>
    <Th align=center width=140 ><font color="#000000"><B>量具选择</B></font></th>
    <Th align=center width=140 ><font color="#000000"><B>夹具选择</B></font></th>
    <Th align=center width=140 ><font color="#000000" ><B>原材料</B></font></th>
    <Th align=center width=140 ><font color="#000000" ><B>辅料</B></font></th>
  </TR>
  <tr>
  <td><font><A onclick="delcmtyf(); "style="cursor:hand;"><img title="删除此共序" src="../../img/bt_del.gif" class="op_button" /></A></FONT></td>
  <td ><select id="aono2" name="aono2" style="width:130px;" onchange = "select(this.value,1);">
	          <%  ArrayList list8 = aobeandao.getAo_no2(product_type,product_id,issue_num,item_id);
	                    if(list8.size()!=0 && list8!=null){ 
	                    Dept dept7=new Dept();
	                for(int i = 0;i<list8.size();i++){
	                    dept7 = (Dept)list8.get(i);%>
	                <option value="<%=dept7.getDept_id()%>" ><%=dept7.getDept_id()%>【<%=dept7.getDept_name()%>】</option>
	                <%}%>
	                  <option value="" selected>请选择Ao</option>
	             <% }else{%>
	                <option value="">---暂无数据---</option>
	             <%}%> </select></td>
  <td><select id="ao_ver" name="ao_ver" style="width:130px;" onchange = "select(this.value,2);">
  <option value="">-----</option></select></td>             
  <td><select id="ao_cut" name="ao_cut" style="width:130px;">
  <option value="">-----</option></select></td>
  <td><select id="ao_measure" name="ao_measure" style="width:130px;">
  <option value="">-----</option></select></td>
  <td><select id="ao_tool" name="ao_tool" style="width:130px;">
  <option value="">-----</option></select></td>
  <td><select id="ao_material" name="ao_material" style="width:130px;">
  <option value="">-----</option></select></td>
  <td><select id="ao_accessory" name="ao_accessory" style="width:130px;">
  <option value="">-----</option></select></td>
  </tr>
</table>
</form>
</div>
  </body>
</html>
