<%@ page language="java" contentType="text/html; charset=utf-8" errorPage="../error.jsp"%>
<%@page import="java.util.*,java.text.*,java.util.Vector"%>
<jsp:useBean id="aobean" scope="page" class="cfmes.bean.AoBean"/>
<jsp:useBean id="ds" scope="page" class="cfmes.util.DealString"/>
<% request.setCharacterEncoding("utf-8");response.setHeader("Charset","utf-8");%>
<%
	try{  
	String but_add = "";
	String div =  "none";
	String item_id      = (String )session.getAttribute("item_id");
	String flight_type  = (String)session.getAttribute("flight_type");
    String product_id   = (String)session.getAttribute("product_id");
    String issue_num    = (String)session.getAttribute("issue_num");
    String aofo         = (String)session.getAttribute("aofo");
    //div属性
    if(aofo.equals("AO")){but_add = "查看AO";}else{but_add = "添加AO";}
	if(div==null||div.equals(""))div = "none";
%>

<HTML>
<head><title>AO信息</title>
<link href="../../css/person.css" type=text/css rel=stylesheet>
</head>
<BODY leftMargin=0 topMargin=0 >
<TABLE class="data_list_table" align="center" width="100%" border="1" cellspacing="2" cellpadding="0" style="word-break:break-all;border-collapse:collapse" >
 <TR  height="22">
 <td align="left"><strong><font size="4"><%=item_id %></font></strong>
<input type="button" id="but_add" value="<%=but_add%>"  onclick="showdiv();" style="cursor:hand;">
<input type="button" id="cmtyf" value="查看/添加cmtyf" onclick="docmtyf()" style="cursor:hand;"></td>
  </TR>
  </TABLE>
  <div id="add" style="display:<%=div%>;background:#dafec5" >
  	<form name = "form1">
	  <TABLE class="green_list_table" align="center" width="980" border="1" cellspacing="2" 
	     cellpadding="0" style="word-break:break-all;border-collapse:collapse" bgcolor="#ffffff">
   	    <tr><th width=100>AO 编号：</th>
   	    	<td width=100>
   	    	  <input type=text  name="ao_no"  size="40" maxlength="192" >
   	    	  <span class="red_star">*</span></td>
            <th width=100>工位号 ：</th>
            <td>
              <input type=text name="workplacecode"  size="40" maxlength="60">
              <span class="red_star">*</span></td></tr>
        <tr><th>AO 名称：</th>
        	<td>
        	  <input type=text  name="aoname"    size="40" maxlength="192" ></td>
	        <th>工位名称：</th>
	        <td>
	          <input type=text name="workplacename"  size="40" maxlength="192" ></td></tr>
	    <tr><th>AO 版本：</th>
	    	<td>
	    	  <input type=text name="aover"  size="40" maxlength="30" >
	    	  <span class="red_star">*</span></td>
	        <th>装配图号：</th>
	        <td>
	          <input type=text name="partno"  size="40" maxlength="192" ></td></tr>
	    <tr><th>AO 时间：</th>
	    	<td>
	    	  <input type=text name="ao_time"  size="40" maxlength="4" t_value="" o_value="" ></td>
	        <td align=right><input type="button"  value="保 存" onclick="toservlet();" style="cursor:hand;">
	        <td><input type="button"  value="清 空" title="清空文本框" onclick="clearOk();" style="cursor:hand;">
	            <input type = "hidden" name = "dotype" value = ""></td></tr>
      </TABLE>
    </form>
  </div>
<hr color=green>

<table class="data_list_table" width="100%" align="center" STYLE="table-layout:fixed" border="0" style="word-break:break-all;">
  <TR  height="22">

    <Th align=center width=80 ><font color="#000000"><B>操作</B></font></th>
	<Th align=center width=80 ><font color="#000000"><B>Ao编号</B></font></th>
    <Th align=center width=200 ><font color="#000000"><B>工位号</B></font></th>
    <Th align=center width=150 ><font color="#000000"><B>Ao名称</B></font></th>
    <Th align=center width=200 ><font color="#000000" ><B>工位名称</B></font></th>
    <Th align=center width=80 ><font color="#000000"><B>Ao版本</B></font></th>
    <Th align=center width=80 ><font color="#000000"><B>装配图号</B></font></th>
    <Th align=center width=80 ><font color="#000000"><B>Ao时间</B></font></th>
    
  </TR>
<%  
	Vector vect = null;//= (Vector)issuebean.getData();
	String bgcolor="";    
        //strPaixu:0,1按fo版本排序;
			vect = (Vector)aobean.getData("AO_VER",false,"","",true,flight_type,product_id,issue_num,item_id);
	   
    Hashtable ht = (Hashtable)vect.get(0);
	String sql = (String)ht.get("sql");

	String bm = (String)request.getParameter("bm");
	if(bm==null || bm.equals("")){bm = "1";}

	int cur = Integer.parseInt(bm);

	vect = (Vector)aobean.getOnePage(sql,cur,20);
	int recsum = Integer.parseInt((String)vect.get(0));//总记录数
	int sum    = Integer.parseInt((String)vect.get(1));//总页数
	int j=vect.size();
	for(int i=2;i<j;i++)
	{
		if ((i%2)!=0) { 
			bgcolor="#FFFFFF";
		}else {
			bgcolor="#E7F2FF";
		}

		Hashtable hash = (Hashtable)vect.get(i);

	    String aono = ds.toString((String)hash.get("AO_NO"));
		String workplacecode = ds.toString((String)hash.get("WORKPLACECODE"));
		String aoname = ds.toString((String)hash.get("AO_NAME"));
		String workplacename = ds.toString((String)hash.get("WORKPLACENAME"));
		String aover = ds.toString((String)hash.get("AO_VER"));
		String partno = ds.toString((String)hash.get("PART_NO"));
		String aotime = ds.toString((String)hash.get("AO_TIME"));
%>
<TR  bgcolor="<%=bgcolor%>" align=center>

  <td align="center"><FONT color="#338800">
  <A onclick="add2Ao(); "style="cursor:hand;"><img title="编辑" src="../../img/bt_edit2.gif" class="op_button" /></A>
  <A onclick="delAo('<%=aono%>','<%=aover%>'); "style="cursor:hand;"><img title="删除" src="../../img/bt_del.gif" class="op_button" /></A>
 </FONT></td>
  <td align=center><%=aono%></td>
  <td align=center><%=workplacecode%></td>
  <td align=center><%=aoname%></td>
  <td align=center><%=workplacename%></td>
  <td align=center><%=aover%></td>
  <td align=center><%=partno%></td>
  <td align=center><%=aotime%></td>
  </TR>
<%}
int t =-1;
int s = -1;
//if(j>=4){%>	

<tr height=25>
		<TD align=center colspan=6 >
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

<!-- 这里对应一个物料就一个AO文件 -->
<form name = "forsubmit2">
  <input type = "hidden" name = "ao_no" value = "">
  <input type = "hidden" name = "aover" value = "">
  <input type = "hidden" name = "dotype" value = "">
</form>
</BODY>
</HTML>
<script type="text/javascript">
    function toservlet(){   
	     if (document.all.ao_no.value=="" ){
	     	alert("请输入AO编号！");document.all.ao_no.focus();return ;
	     }
	     if (document.all.aover.value=="" ){
	     	alert("请输入AO版本！");document.all.aover.focus();return ;
	     }	
		 if (document.all.workplacecode.value=="" ) {
		 	alert("请输入工位号！");
		 	document.all.workplacecode.focus();
		 	return ;
		 }
		    document.form1.dotype.value = "save";
			document.all.form1.action = "../../AoSvlt";
			document.all.form1.submit();
    }
    
     function toservlet2(){   
     if (document.all.workplace.value=="" ){alert("请输入工位号！");document.all.workplace.focus();return ;}
		document.all.form1.action = "../../../down2";
		document.all.form1.submit();
    }
    
    function showdiv(){
     if(document.all.but_add.value =='添加AO'){
          document.all.add.style.display= "block";
		  document.all.but_add.title= "取消这次添加操作";
          document.all.but_add.value="取消添加";
     }else if(document.all.but_add.value=='取消添加'){
          document.all.add.style.display= "none";
		  document.all.but_add.value="添加AO";
     }else if(document.all.but_add.value=='查看AO'){
     document.all.but_add.disabled="disabled";}
          
	}
	
	function add2Ao(){
	      document.all.add.style.display= "block";
		  document.all.but_add.title= "取消这次添加操作";
          document.all.but_add.value="取消添加";
	 
	}
	
	function docmtyf(){
	     window.location.href="Aocmtyf.jsp";
	}
	function hiddendiv(){
          document.all.add.style.display= "none";
		  document.all.but_add.style.display= "block";
		  document.all.txt_but_add.value = "block";
		  document.all.txt_div.value = "none";
	}
    function clearOk(){   
      document.all.ao_no.value = "";
	  document.all.aoname.value = "";
	  document.all.aover.value = "";
	  document.all.ao_time.value = "";
	  document.all.workplacecode.value = "";
	  document.all.workplacename.value = "";
	  document.all.partno.value = "";
    }
    
	function modAo(ao_no,aoname,aover,ao_time,code,name,partno){
		
		document.all.add.style.display= "block";
		document.all.but_add.title= "取消这次修改操作";
        document.all.but_add.value= "取消修改";
        document.all.txt_but_add.value = "取消修改";
        document.all.txt_div.value = "block";
		
		document.all.txt_ao_no.value = ao_no;
		document.all.txt_aover.value = aover;
		document.all.txt_edit.value = "2";
		document.all.ao_no.value = ao_no;
		document.all.aoname.value = aoname;
		document.all.aover.value = aover;
		document.all.ao_time.value = ao_time;
		document.all.workplacecode.value = code;
		document.all.workplacename.value = name;
		document.all.partno.value = partno;
		document.all.form1.action = "ao.jsp";
		document.all.form1.submit();
	}
	function delAo(ao_no,aover){
		if(!confirm("这本AO的工序信息也会被删除，删除后不可恢复,是否真的要删除？")){
		}else{
			  document.forsubmit2.ao_no.value = ao_no;
			  document.forsubmit2.aover.value = aover;
			  document.forsubmit2.dotype.value = "del";
			  document.forsubmit2.action = "../../AoSvlt";
			  document.forsubmit2.submit();
		}
	}
	
	function del()
	{
		var delid = "";
		var num = "";
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
		document.all.txt_ao_no.value = delid;
		document.all.form1.action = "../../../AoSvlt";
		document.all.form1.submit();
	}
	
//-->
</script>
<%}catch(Exception e){
    System.out.println("ao.jsp 出错："+e);
}%>