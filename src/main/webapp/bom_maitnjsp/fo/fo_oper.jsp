<%@ page language="java" contentType="text/html; charset=utf-8" errorPage="../error.jsp"%>
<%@page import="java.util.*,java.text.*,java.util.Vector"%>
<jsp:useBean id="operbean" scope="page" class="cfmes.bean.FoOperBean"/>
<jsp:useBean id="fobeandao" scope="page" class="cfmes.bom.dao.FoBeanDao"/>
<jsp:useBean id="ds" scope="page" class="cfmes.util.DealString"/>
<jsp:directive.page import="cfmes.bom.entity.Dept"/>
<% request.setCharacterEncoding("utf-8");response.setHeader("Charset","utf-8");%>
<% 
   
   try{  
	String item_id      = (String )session.getAttribute("item_id");
	String flight_type  = (String)session.getAttribute("flight_type");
    String product_id   = (String)session.getAttribute("product_id");
    String issue_num    = (String)session.getAttribute("issue_num");
    String aofo         = (String)session.getAttribute("aofo");
    
    String fo_no = ds.toString((String)request.getParameter("fo_no"));
    String fover = ds.toString((String)request.getParameter("fover"));
    String dept_id = ds.toString((String)request.getParameter("dept_id"));
    
    String div = "none";
    String but_add = "";
    if(fobeandao.isinFoOp2( product_id, issue_num, fo_no, fover)){but_add = "查看fo工序";}else{but_add = "添加fo工序";}
%>
<HTML>
<head>
<title>FO:<%=fo_no%>工序信息</title>
<link href="../../css/person.css" type=text/css rel=stylesheet>
</head>
<BODY leftMargin=0 topMargin=0 >
<form name=form1 action="" method=post>

<table><tr><td><br></td></tr></table>
<TABLE class="data_list_table" align="center" width="1240" border="1" cellspacing="2" cellpadding="0" style="word-break:break-all;border-collapse:collapse" >
 <TR  height="22">
 <td align="left"><strong><font size="4">FO:<%=fo_no%>工序信息</font></strong><input type="button" id="but_add" value="<%=but_add%>"  onclick="showdiv();" style="cursor:hand;">
 <input type="button" id="but_add2" value="添加/查看CMTYF"  onclick="showaddcmtyf();" style="cursor:hand;"></td>
  </TR>
  </TABLE>
  
<div id="add" style="display:<%=div%>;background:#dafec5" >
<TABLE class="green_list_table" align="center" width="100%" border="1" style="word-break:break-all;border-collapse:collapse" bgcolor="#dafec5">
<TR>
   <tr><th width=100>工序编号</th><td width=150><input type=text name="oper_id"  value="" size="20" maxlength="5" ><span>*</span></td> 
    <th rowspan="4" width=100>工序内容</th><td rowspan="4" colspan="7"><textarea name="oper_content" maxlength="4000" rows="10" cols="90"></textarea></td> </tr>
   <tr> <th>工序名称</th><td><input type=text name="oper_name" value="" size="20" maxlength="100" ></td></tr>
    <tr><th>工序时间</th><td><input type=text name="oper_time" value="" size="20" maxlength="9" ></td></tr>
    <tr><th>额定工时</th><td><input type=text name="rated_time"  value="" size="20" maxlength="9" ></td></tr>
    <tr><th>计划工时</th><td><input type=text name="plan_time" value="" size="20" maxlength="9" ></td>
    <th>辅助工时</th><td width=100><input type=text name="oper_aidtime" value="" size="20" maxlength="9" ></td>
    <th>检验工时</th><td width=100><input type=text name="insp_time" value="" size="20" maxlength="9" ></td></tr>
    <tr>
    <th>制造工段</th><td ><select id="wcid" name="wcid" style="width:130px;"><option>---请输入---</option>
	          <% ArrayList list = fobeandao.getDept(3,dept_id);
	             if(list.size()!=0 && list!=null){ 
	                for(int i = 0;i<list.size();i++){
	                   Dept dept = (Dept)list.get(i);%>
	                <option value="<%=dept.getDept_id()%>" selected><%=dept.getDept_name()%></option>
	                <%} 
	             }else{%>
	                <option value="">---暂无数据---</option>
	             <%}%> </select></td> 
	<th>PDM工段</th><td><input type=text name="equiptype" value="" size="20" maxlength="60"></td>
    <th>设备编号</th><td><input type=text name="equipcode" value="" size="20" maxlength="30"></td></tr>                    
    <tr>
    <th>关键工序</th><td ><select id="is_keyop" name="is_keyop" style="width:130px;">
                        <option value="0" >否</option>
                        <option value="1" >是</option></select></td>
    <th>是否设检</th><td ><select id="is_insp" name="is_insp" style="width:130px;">
                        <option value="0" selected>否</option>
                        <option value="1" >是</option></select></td>
    <th>是否军检</th><td ><select id="is_arminsp" name="is_arminsp" style="width:130px;">
                        <option value="0" selected>否</option>
                        <option value="1" >是</option></select></td></tr>
    <tr><th>持证上岗</th><td ><select id="is_certop" name="is_certop" style="width:130px;">
                        <option value="0" selected>否</option>
                        <option value="1" >是</option></select></td>
    <th>是否外协</th><td ><select id="is_co" name=is_co style="width:130px;">
                        <option value="0" selected>否</option>
                        <option value="1" >是</option></select></td>
	<td><input type="button"  value="保 存" onclick="toservlet();" style="cursor:hand;">
	<input type="button"  value="清 空" title="清空文本框" onclick="clearOk();" style="cursor:hand;"></td>
	<input type="hidden" name = "dotype">
	<input type = "hidden" name = "fo_no" value = "<%=fo_no%>">
	<input type = "hidden" name = "fover" value = "<%=fover%>">
	<input type = "hidden" name = "dept_id" value = "<%=dept_id%>">
	</tr>
</TABLE>

</div>
</form>

<table class="data_list_table" width="100%" align="center" id=theObjTable STYLE="table-layout:fixed" border="1" style="word-break:break-all;">
  <TR  height="22">
	
	<Th align=center width=80 ><font color="#000000"><B>操作</B></font></th>
    <Th align=center width=80 ><font color="#000000" ><B>工序编号</B></font></th>
    <Th align=center width=100 ><font color="#000000"><B>工序名称</B></font></th>
    <Th align=center width=400 ><font color="#000000"><B>工序内容</B></font></th>
    <Th align=center width=120 ><font color="#000000"><B>制造工段</B></font></th>
    <Th align=center width=80 ><font color="#000000" ><B>工序工时</B></font></th>
    <Th align=center width=80 ><font color="#000000" ><B>额定工时</B></font></th>
    <Th align=center width=80 ><font color="#000000" ><B>计划工时</B></font></th>
	<Th align=center width=80 ><font color="#000000"><B>辅助工时</B></font></th>
	<Th align=center width=80 ><font color="#000000"><B>检验工时</B></font></th>
	<Th align=center width=80 ><font color="#000000" ><B>关键工序</B></font></th>
	<Th align=center width=80 ><font color="#000000" ><B>是否设检</B></font></th>
	<Th align=center width=80 ><font color="#000000" ><B>是否军检</B></font></th>
	<Th align=center width=80 ><font color="#000000" ><B>持证上岗</B></font></th>
	<Th align=center width=80 ><font color="#000000" ><B>是否外协</B></font></th>
	<Th align=center width=80 ><font color="#000000"><B>设备编号</B></font></th>
  </TR>

<%  
    operbean.setFo_no(fo_no);
    operbean.setFover(fover);
    operbean.setFlight_type(flight_type);
    operbean.setIssue_num(issue_num);
    operbean.setItem_id(item_id);
    operbean.setProduct_id(product_id);
	Vector vect = null;
	String bgcolor="";     
  
	 vect = (Vector)operbean.getData("FO_OPERID",false,"","",true);

    Hashtable ht = (Hashtable)vect.get(0);
	String sql = (String)ht.get("sql");

	String bm = (String)request.getParameter("bm");
	if(bm==null || bm.equals("")){bm = "1";}

	int cur = Integer.parseInt(bm);

	vect = (Vector)operbean.getOnePage(sql,cur,20);
	int recsum = Integer.parseInt((String)vect.get(0));//总记录数
	int sum    = Integer.parseInt((String)vect.get(1));//总页数
	int j=vect.size();
	for(int i=2;i<j;i++){
		if ((i%2)!=0) { 
			bgcolor="#FFFFFF";
		}else {
			bgcolor="#E7F2FF";
		}

		Hashtable hash = (Hashtable)vect.get(i);

	    String opr_id1 = ds.toString((String)hash.get("FO_OPERID"));
	  	String oper_name1 = ds.toString((String)hash.get("FO_OPNAME"));
		String oper_content1 = ds.toString((String)hash.get("FO_OPCONTENT"));
		String oper_time1 = ds.toString((String)hash.get("OPER_TIME"));
		String rated_time1 = ds.toString((String)hash.get("RATED_TIME"));
		String plan_time1 = ds.toString((String)hash.get("PLAN_TIME"));
		String oper_aidtime1 = ds.toString((String)hash.get("OPER_AIDTIME"));
		String insp_time1 = ds.toString((String)hash.get("INSP_TIME"));
		String is_keyop1 = ds.toString((String)hash.get("IS_KEY"));
		String is_insp1 = ds.toString((String)hash.get("IS_INSP"));
		String is_arminsp1 = ds.toString((String)hash.get("IS_ARMINSP"));
		String is_certop1 = ds.toString((String)hash.get("IS_CERTOP"));
		String is_co1 = ds.toString((String)hash.get("IS_CO"));
		String wcid1 = ds.toString((String)hash.get("WCID"));
		String dept_name1 = ds.toString((String)hash.get("DEPT_NAME"));
		String equiptype1 = ds.toString((String)hash.get("EQUIPTYPE"));
		String equipcode1 = ds.toString((String)hash.get("EQUIPCODE"));
		
		String combine = opr_id1+"-"+oper_content1+"-"+oper_name1+"-"+oper_time1+"-"+rated_time1+"-"+plan_time1+"-"+
		                 oper_aidtime1+"-"+insp_time1+"-"+dept_name1+"-"+equiptype1+"-"+equipcode1+"-"+is_keyop1+"-"+
		                 is_insp1+"-"+is_arminsp1+"-"+is_certop1+"-"+is_co1;
		
%>
<TR  bgcolor="<%=bgcolor%>" align=center>

  <td align="center"><FONT color="#338800">
  <A onclick="modFoOper('<%=combine%>'); "style="cursor:hand;">
                 <img title="编辑此共序" src="../../img/bt_edit.gif" class="op_button" /></A>
  <A onclick="delFo('<%=opr_id1%>'); "style="cursor:hand;"><img title="删除此共序" src="../../img/bt_del.gif" class="op_button" /></A></FONT></td>
  <td align=center><%=opr_id1%></td>
  <td align=center><%=oper_name1%></td>
  <td align=center><%=oper_content1%></td>
  <td align=center><%=dept_name1%>(<%=equiptype1%>)</td>
  <td align=center><%=oper_time1%></td>
  <td align=center><%=rated_time1%></td>
  <td align=center><%=plan_time1%></td>
  <td align=center><%=oper_aidtime1%></td>
  <td align=center><%=insp_time1%></td>
  <td align=center><%if(is_keyop1.equals("1")){%>是<%}else if(is_keyop1.equals("0")){%>否<%}%></td>
  <td align=center><%if(is_insp1.equals("1")){%>是<%}else if(is_insp1.equals("0")){%>否<%}%></td>
  <td align=center><%if(is_arminsp1.equals("1")){%>是<%}else if(is_arminsp1.equals("0")){%>否<%}%></td>
  <td align=center><%if(is_certop1.equals("1")){%>是<%}else if(is_certop1.equals("0")){%>否<%}%></td>
  <td align=center><%if(is_co1.equals("1")){%>是<%}else if(is_co1.equals("0")){%>否<%}%></td>
  <td align=center><%=equipcode1%></td>
  </TR>
<%}
int t =-1;
int s = -1;
%>
<tr height=25>
		<td align=center colspan=18 >
		共有记录数：<%=recsum%>&nbsp;&nbsp;&nbsp;&nbsp;
        <%if(vect.size()>1){%>
		当前<%=cur%>/<%=sum%>页&nbsp&nbsp&nbsp
        <%if(cur>1){%>
		<a onclick="firsrpg();" style="cursor:hand">第一页&nbsp&nbsp&nbsp </a><%}%>
		<%if(cur>1){ t=cur-1;%>
		<a onclick="lastpg();" style="cursor:hand">上一页&nbsp&nbsp&nbsp </a><%}%>
		<%if(cur<sum){ s=cur+1;%>
		<a onclick="nextpg();" style="cursor:hand">下一页&nbsp&nbsp&nbsp</a><%}%>&nbsp&nbsp
		<%if(cur<sum){%>
		<a onclick="finalpg();" style="cursor:hand">最后页&nbsp&nbsp&nbsp </a><%}%>
		直接<input type=image src="../../img/hand.gif" name="gotof" onclick="return chkdata();">
		<input type=text size=5 name=bm class=formcolor>页</TD>
<%}%>
	</tr>
</TABLE>

</BODY>
</HTML>
<script type="text/javascript">

    function modFoOper(com) {
       
       document.form1. oper_id.readOnly= true;
       document.form1.oper_id.title="该项不可更改，请修改其他！";
       document.form1.dotype.value = "mod";
       document.all.add.style.display = "block";
       var arr = new Array();
       arr = com.split("-");
       
       document.form1.oper_id.value      = arr[0];
       document.form1.oper_content.value = arr[1];
       document.form1.oper_name.value    = arr[2];
       document.form1.oper_time.value    = arr[3];
       document.form1.rated_time.value   = arr[4];
       document.form1.plan_time.value    = arr[5];
       document.form1.oper_aidtime.value = arr[6];
       document.form1.insp_time.value    = arr[7];
       for(var i=0;i<document.getElementById("wcid").length;i++){
         if(arr[8]==document.getElementById("wcid").options[i].text)
         document.form1.wcid.selectedIndex = i;
         else document.form1.wcid.selectedIndex = 0;
       }
       //document.form1.wcid.selectedIndex = 1;
       document.form1.equiptype.value    = arr[9];
       document.form1.equipcode.value    = arr[10];
       if(arr[11]=="0")document.form1.is_keyop.selectedIndex = 0;
       else document.form1.is_keyop.selectedIndex = 1;
       if(arr[12]=="0")document.form1.is_insp.selectedIndex = 0;
       else document.form1.is_insp.selectedIndex = 1;
       if(arr[13]=="0")document.form1.is_arminsp.selectedIndex = 0;
       else document.form1.is_arminsp.selectedIndex = 1;
       if(arr[14]=="0")document.form1.is_certop.selectedIndex = 0;
       else document.form1.is_certop.selectedIndex = 1;
      if(arr[15]=="0")document.form1.is_co.selectedIndex = 0;
       else document.form1.is_co.selectedIndex = 1;
    }

    function nots(){
      if((event.keyCode<48)||(event.keyCode>57)){
         event.returnValue= false;
      }
    }
   
    function toservlet(){   
     if (document.all.oper_id.value=="" ){alert("请输入工序编号！");document.all.oper_id.focus();return ;}
        document.all.form1.dotype.value ="add";
		document.all.form1.action = "../../FoOperSvlt";
		document.all.form1.submit();
    }
    function showdiv(){
       if(document.all.but_add.value =="添加fo工序"){
          document.all.add.style.display= "block";
          document.all.but_add.value="取消添加";
          document.all.but_add.title= "取消这次添加操作";
		  document.all.oper_id.value = "";
		  document.all.oper_name.value = "";
		  document.all.oper_content.value = "";
		  document.all.oper_time.value = "";
		  document.all.rated_time.value = "";
		  document.all.plan_time.value = "";
		  document.all.oper_aidtime.value = "";
		  document.all.insp_time.value = "";
		  document.all.is_keyop.value = "0";
		  document.all.is_insp.value = "0";
		  document.all.is_arminsp.value = "0";
		  document.all.is_certop.value = "0";
		  document.all.is_co.value = "0";
		  document.all.wcid.value = "";
		  document.all.equiptype.value = "";
		  document.all.equipcode.value = "";
		  
       }else if(document.all.but_add.value == "查看fo工序"){
          document.all.add.style.display= "none";
		  document.all.but_add.value="添加fo工序";
       }else if(document.all.but_add.value == "取消添加"){
          window.location.href='fo_oper.jsp';
       }  
	}
	function showaddcmtyf(){
	    // window.location.href="cmtyf.jsp";
	     window.location.href="cmtyf.jsp?fo_no=+<%=fo_no%>+&fover=+<%=fover%>+";	    
	}
	function hiddendiv(){
          document.all.add.style.display= "none";
		  document.all.but_add.style.display= "block";
		  document.all.txt_but_add.value = "block";
		  document.all.txt_div.value = "none";
	}
    function clearOk(){
          document.all.oper_id.value = "";
		  document.all.oper_name.value = "";
		  document.all.oper_content.value = "";
		  document.all.oper_time.value = "";
		  document.all.rated_time.value = "";
		  document.all.plan_time.value = "";
		  document.all.oper_aidtime.value = "";
		  document.all.insp_time.value = "";
		  document.all.is_keyop.value = "0";
		  document.all.is_insp.value = "0";
		  document.all.is_arminsp.value = "0";
		  document.all.is_certop.value = "0";
		  document.all.is_co.value = "0";
		  document.all.wcid.value = "";
		  document.all.equiptype.value = "";
		  document.all.equipcode.value = "";
    }
    function refresh(){
		document.all.txt_paixu.value = "";
		document.all.txt_srch.value = "";
		document.all.txt_search.value = "";
		document.all.chk_search.value = "";
		document.all.form1.action = "fo_oper.jsp";
		document.all.form1.submit();
	}
    function firsrpg(){
		//document.all.txt_edit.value = "";
		document.all.form1.action = "fo_oper.jsp?bm=1";
		document.all.form1.submit();
	}
	function lastpg(){
		//document.all.txt_edit.value = "";
		document.all.form1.action = "fo_oper.jsp?bm="+<%=t%>;
		document.all.form1.submit();
	}
	function nextpg(){
		//document.all.txt_edit.value = "";
		document.all.form1.action = "fo_oper.jsp?bm="+<%=s%>;
		document.all.form1.submit();
	}
	function finalpg(){
		//document.all.txt_edit.value = "";
		document.all.form1.action = "fo_oper.jsp?bm="+<%=sum%>;
		document.all.form1.submit();
	}
	function onchgss(selobj){
		var choice = selobj.selectedIndex;
	    document.all.txt_srch.value = selobj.options[choice].value;
		selobj.options[choice].selected;
	}

	function oncheck(myname){
		if(myname.checked)
			myname.value="ON";
		else
			myname.value="";
	}
	
	function showModFoOp(data){
		document.all.add.style.display= "block";
        document.all.but_add.value= "取消修改";
        document.all.txt_but_add.value = "取消修改";
        document.all.but_add.title= "取消这次修改操作";
        document.all.txt_div.value = "block";
		
		document.all.txt_edit.value = "2";
		  document.all.oper_name.value = data.oper_name;
		  document.all.oper_content.value = data.oper_content;
		  document.all.oper_time.value = data.oper_time;
		  document.all.rated_time.value = data.rated_time;
		  document.all.plan_time.value = data.plan_time;
		  document.all.oper_aidtime.value = data.oper_aidtime;
		  document.all.insp_time.value = data.insp_time;
		  document.all.wcid.value = data.wcid;
		  document.all.equiptype.value = data.equiptype;
		  document.all.equipcode.value = data.equipcode;
		  document.all.is_keyop.value = data.is_keyop;
		  document.all.is_insp.value = data.is_insp;
		  document.all.is_arminsp.value = data.is_arminsp;
		  document.all.is_certop.value = data.is_certop;
		  document.all.is_co.value = data.is_co;
		document.all.form1.action = "fo_oper.jsp";
		document.all.form1.submit();
	}
	function delFo(oper_id){
		if(!confirm("删除后不可恢复,是否真的要删除？")){
		}else{
			  document.form1.oper_id.value = oper_id;
			  document.form1.dotype.value = "del";
			  document.all.form1.action = "../../FoOperSvlt";
			  document.all.form1.submit();
		}
	}
	function isDigit(str){
		var bool = true;
		for(var i=0;i<str.length;i++){
			if(!(str.charAt(i)>=0&&str.charAt(i)<=9)){
				bool = false;
				break;
			}
		}return bool;
	}
	
	function selectall(){
		var num = document.all.checkboxid.length;
		if(document.all.selall.value == "全选"){
			for(var i=0;i<num;i++){
				document.all.checkboxid[i].checked = true;	
			}
			document.all.selall.value = "取消全选";
		}else{
			for(var i=0;i<num;i++){
				document.all.checkboxid[i].checked = false;	
			}
			document.all.selall.value = "全选";
		}
	}
	function del()
	{
		var delid = "";
		var num = <%=j%>;
		if(num==1){
			if(document.all.checkboxid.checked==true)
				delid = "'"+document.all.checkboxid.value+"'";
		}
		if(num>=2){
			var numid = document.all.checkboxid.length;
			for(var i=0;i<numid;i++){
				if(document.all.checkboxid[i].checked == true){
					if(delid == ""){
						delid = "'"+document.all.checkboxid[i].value+"'";
					}else{
						delid = delid+",'"+document.all.checkboxid[i].value+"'";
					}
				}
			}
		}
		if(delid==""){
			alert("请选择要删除的记录！");
			return false;
		}
		if(!confirm("是否真的要删除？")){
			return false;
		}
		document.all.txt_edit.value = "4";
		document.all.txt_oper_id.value = delid;
		document.all.form1.action = "../../../FoOperSvlt";
		document.all.form1.submit();
	}
	function clearlxr(){
		if(!confirm("是否真的要删除所有记录？")){
			return false;
		}
		document.all.txt_edit.value = "5";
		document.all.form1.action = "../../../FoOperSvlt";
		document.all.form1.submit();
	}
	function chkdata(){   
	//document.all.txt_edit.value = "";
		if(!isDigit(document.all.bm.value)){
			alert("输入的页码不是数字!");
			document.all.bm.value="";
			document.all.bm.focus();
			return false;
		}
		if(document.all.bm.value==""){
			alert("请输入页码!");
			document.all.bm.value="";
			document.all.bm.focus();
			return false;
		}else{
			var t = document.all.bm.value;
			var cur = <%=cur%>;
			var sum = <%=sum%>;
			while(t.length>cur.length){
				alert("请跳至1和"+sum+"之间!");
				document.all.bm.value="";
				document.all.bm.focus();
				return false;
			}
			if(t==cur){return false;}
			while(t.length>sum.length){
				alert("请跳至1和"+sum+"之间!");
				document.all.bm.value="";
				document.all.bm.focus();
				return false;
			}
			if(t<1||t>sum){
				alert("请跳至1和"+sum+"之间!");
				document.all.bm.value="";
				document.all.bm.focus();
				return false;
			}
		}
		document.all.form1.action = "fo_oper.jsp";
		return true;
	}
//-->
</script>
<%}catch(Exception e){
    System.out.println("fo_oper 出错："+e);
}%>