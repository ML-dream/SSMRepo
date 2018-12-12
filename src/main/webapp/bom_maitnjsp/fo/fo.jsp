<%@ page language="java" contentType="text/html; charset=utf-8"
	errorPage="../error.jsp"%>
<%@page import="java.util.*,java.text.*,java.util.Vector"%>
<jsp:directive.page import="cfmes.bom.entity.Dept" />
<jsp:useBean id="fobean" scope="page" class="cfmes.bean.FoBean" />
<jsp:useBean id="fobeandao" scope="page" class="cfmes.bom.dao.FoBeanDao" />
<jsp:useBean id="ds" scope="page" class="cfmes.util.DealString" />
<jsp:useBean id="titles" scope="page" class="Bom.Titles" />
<%
	request.setCharacterEncoding("utf-8");
	response.setHeader("Charset", "utf-8");
%>
<%
	try {
		String but_add = "";
		String div = "none";
		String item_id = (String) session.getAttribute("item_id");
		String flight_type = (String) session.getAttribute("flight_type");
		String product_id = (String) session.getAttribute("product_id");
		String issue_num = (String) session.getAttribute("issue_num");
		String aofo = (String) session.getAttribute("aofo");
		//div属性
		if (aofo.equals("FO")) {
			but_add = "查看FO";
		} else {
			but_add = "添加FO";
		}
%>
<HTML>
	<head>
		<title>FO信息</title>
		<link href="../../css/person.css" type=text/css rel=stylesheet>
	</head>
	<BODY leftMargin=0 topMargin=0>

		<TABLE class="data_list_table" align="center" width="100%" border="1"
			cellspacing="2" cellpadding="0"
			style="word-break: break-all; border-collapse: collapse">
			<TR height="22">
				<td align="left">
					<strong><font size="4">FO:<%=item_id%></font>
					</strong>
					<input type="button" id="but_add" value="<%=but_add%>"
						onclick="showdiv();" style="cursor: hand;">
				</td>
			</TR>
		</TABLE>
		<div id="add" style="display:<%=div%>;background:#dafec5">
			<form name="form1" action="fo.jsp" method=post>
				<TABLE class="green_list_table" align="center" width="100%"
					STYLE="table-layout: fixed" border="1" cellspacing="2"
					cellpadding="0"
					style="word-break:break-all;border-collapse:collapse"
					bgcolor="#ffffff">
					<tr>
						<th>
							FO编号：
						</th>
						<td>
							<input type=text name="fo_no" value="" size="40" maxlength="192">
							<span class="red_star">*</span>
						</td>
						<th>
							FO版本：
						</th>
						<td>
							<input type=text name="fover" value="" size="40" maxlength="30">
							<span class="red_star">*</span>
						</td>
						<th>
							制造部门:
						</th>
						<td>
							<select id="dept_id" name="dept_id" style="width: 130px;">
								<%
									ArrayList list = fobeandao.getDept(2, "");
										if (list.size() != 0 && list != null) {
											for (int i = 0; i < list.size(); i++) {
												Dept dept = (Dept) list.get(i);
								%>
								<option value="<%=dept.getDept_id()%>" selected><%=dept.getDept_name()%></option>
								<%
									}
										} else {
								%>
								<option value="">
									---暂无数据---
								</option>
								<%
									}
								%>
							</select>
						</td>
						<td align=right>
							<input type="button" value="保 存" onclick="toservlet();"
								style="cursor: hand;">
							<input type="hidden" name="dotype" value="add">
							<input type="button" value="清 空" title="清空文本框"
								onclick="clearOk();" style="cursor: hand;">
						</td>
					</TR>

				</TABLE>
			</form>
		</div>
		<table class="data_list_table" width="100%" align="center"
			STYLE="table-layout: fixed" border="0" style="word-break:break-all;">
			<TR height="22">

				<Th align=center width=80>
					<font color="#000000"><B>操作</B>
					</font>
				</th>
				<Th align=center width=200>
					<font color="#000000"><B>FO编号</B>
					</font>
				</th>
				<Th align=center width=150>
					<font color="#000000"><B>物料名称</B>
					</font>
				</th>
				<Th align=center width=200>
					<font color="#000000"><B>FO版本</B>
					</font>
				</th>
				<Th align=center width=80>
					<font color="#000000"><B>制造部门</B>
					</font>
				</th>

			</TR>
			<%
				fobean.setFlight_type(flight_type);
					fobean.setIssue_num(issue_num);
					fobean.setItem_id(item_id);
					fobean.setProduct_id(product_id);
					Vector vect = null;//= (Vector)issuebean.getData();
					String bgcolor = "";
					//strPaixu:0,1按fo版本排序;
					vect = (Vector) fobean.getData("FO_VER", false, "", "", true);

					Hashtable ht = (Hashtable) vect.get(0);
					String sql = (String) ht.get("sql");

					String bm = (String) request.getParameter("bm");
					if (bm == null || bm.equals("")) {
						bm = "1";
					}

					int cur = Integer.parseInt(bm);

					vect = (Vector) fobean.getOnePage(sql, cur, 20);
					int recsum = Integer.parseInt((String) vect.get(0));//总记录数
					int sum = Integer.parseInt((String) vect.get(1));//总页数
					int j = vect.size();
					for (int i = 2; i < j; i++) {
						if ((i % 2) != 0) {
							bgcolor = "#FFFFFF";
						} else {
							bgcolor = "#E7F2FF";
						}

						Hashtable hash = (Hashtable) vect.get(i);

						String fo_no1 = ds.toString((String) hash.get("FO_NO"));
						String fover1 = ds.toString((String) hash.get("FO_VER"));
						String dept_id1 = ds.toString((String) hash.get("DEPT_ID"));
						String dept_name1 = ds.toString((String) hash
								.get("DEPT_NAME"));
			%>
			<TR bgcolor="<%=bgcolor%>" align=center>

				<td align="center">
					<FONT color="#338800"> <A
						onclick="modFo('<%=fo_no1%>','<%=fover1%>','<%=dept_id1%>'); "
						style="cursor: hand;"><img title="编辑"
								src="../../img/bt_edit.gif" class="op_button" />
					</A> <A onclick="delFo('<%=fo_no1%>','<%=fover1%>'); "
						style="cursor: hand;"><img title="删除"
								src="../../img/bt_del.gif" class="op_button" />
					</A> <A
						onclick="detailFo('<%=fo_no1%>','<%=fover1%>','<%=dept_id1%>'); "
						style="cursor: hand;"><img title="工艺路线明细"
								src="../../img/bt_detail.gif" class="op_button" />
					</A> </FONT>
				</td>
				<td align=center><%=fo_no1%></td>
				<td align=center><%=titles.getItem_name(item_id)%></td>
				<td align=center><%=fover1%></td>
				<td align=center><%=dept_name1%></td>
			</TR>
			<%
				}
					int t = -1;
					int s = -1;
					//if(j>=4){
			%>

			<tr height=25>
				<TD align=center colspan=6>
					共有记录数：<%=recsum%>&nbsp;&nbsp;&nbsp;&nbsp;
					<%
						if (vect.size() > 1) {
					%>
					当前<%=cur%>/<%=sum%>页&nbsp&nbsp&nbsp
					<%
						if (cur > 1) {
					%>
					<a onclick="firsrpg();" style="cursor: hand">第一页&nbsp&nbsp&nbsp
					</a>
					<%
						}
					%>
					<%
						if (cur > 1) {t = cur - 1;
					%>
					<a onclick="lastpg();" style="cursor: hand">上一页&nbsp&nbsp&nbsp
					</a>
					<%
						}
					%>
					<%
						if (cur < sum) {s = cur + 1;
					%>
					<a onclick="nextpg();" style="cursor: hand">下一页&nbsp&nbsp&nbsp</a>
					<%
						}
					%>&nbsp&nbsp
					<%
						if (cur < sum) {
					%>
					<a onclick="finalpg();" style="cursor: hand">最后页&nbsp&nbsp&nbsp
					</a>
					<%
						}
					%>
					直接
					<input type=image src="../../img/hand.gif" name="gotof"
						onclick="return chkdata();">
					<input type=text size=5 name=bm class=formcolor>
					页
				</TD>
				<%
					}
				%>
			</tr>
		</TABLE>

	</BODY>
</HTML>
<script type="text/javascript">
<!--
    function detailFo(fo_no,fover,dept_id){
        document.form1.fo_no.value = fo_no;
        document.form1.fover.value = fover;
        document.form1.dept_id.value = dept_id;
        document.all.form1.action = "fo_oper.jsp";
		document.all.form1.submit();  
    }
    function toservlet(){   
     if (document.all.fo_no.value=="" ){alert("请输入FO编号！");document.all.fo_no.focus();return ;}
     if (document.all.fover.value=="" ){alert("请输入FO版本！");document.all.fover.focus();return ;}	
	 if (document.all.dept_id.value=="" ) {alert("请选择制造部门！");document.all.dept_id.focus();return ;}
		document.all.form1.action = "../../FoSvlt";
		document.all.form1.submit();
    }
    
    function toservlet2(){   
     if (document.all.workplace.value=="" ){alert("请输入工位号！");document.all.workplace.focus();return ;}
		document.all.form1.action = "../../../down3";
		document.all.form1.submit();
    }
    
   function showdiv(){
     if(document.all.but_add.value =='添加FO'){
          document.all.add.style.display= "block";
		  document.all.but_add.title= "取消这次添加操作";
          document.all.but_add.value="取消添加";
     }else if(document.all.but_add.value=='取消添加'){
          document.all.add.style.display= "none";
		  document.all.but_add.value="添加FO";
     }else if(document.all.but_add.value=='查看FO'){
     document.all.but_add.disabled="disabled";}
	}
	
	function hiddendiv(){
          document.all.add.style.display= "none";
		  document.all.but_add.style.display= "block";
		  document.all.txt_but_add.value = "block";
		  document.all.txt_div.value = "none";
	}
    function clearOk(){   
      document.all.fo_no.value = "";
	  document.all.fover.value = "";
	  document.all.dept_id.value = "";
    }
    function refresh(){
		document.all.txt_paixu.value = "";
		document.all.txt_srch.value = "";
		document.all.txt_search.value = "";
		document.all.chk_search.value = "";
		document.all.form1.action = "fo.jsp";
		document.all.form1.submit();
	}
    function firsrpg(){
		//document.all.txt_edit.value = "";
		document.all.form1.action = "fo.jsp?bm=1";
		document.all.form1.submit();
	}
	function lastpg(){
		//document.all.txt_edit.value = "";
		document.all.form1.action = "fo.jsp?bm="+<%=t%>;
		document.all.form1.submit();
	}
	function nextpg(){
		//document.all.txt_edit.value = "";
		document.all.form1.action = "fo.jsp?bm="+<%=s%>;
		document.all.form1.submit();
	}
	function finalpg(){
		//document.all.txt_edit.value = "";
		document.all.form1.action = "fo.jsp?bm="+<%=sum%>;
		document.all.form1.submit();
	}
	function onchgss(selobj){
		var choice = selobj.selectedIndex;
	    document.all.txt_srch.value = selobj.options[choice].value;
		selobj.options[choice].selected;
	}
	function onpaixuFo(){
		if(document.all.txt_paixu.value !="0" && document.all.txt_paixu.value !="1")
			document.all.txt_paixu.value ="0";
		if(document.all.txt_paixu.value =="1"){
			document.all.txt_paixu.value ="0";
			imgname = "../../img/arrowup.gif";
		}else if(document.all.txt_paixu.value =="0"){
			document.all.txt_paixu.value ="1";
			imgname = "../../img/arrowdown.gif";
		}
		document.all.form1.action = "fo.jsp?imgname0="+imgname;
		document.all.form1.submit();
	}
	function srch(){
		onchgss(document.all.sel_search);
		//document.all.txt_edit.value = "";
		if(document.all.txt_search.value == ""){
			alert("请输入搜索内容！");
			return false;
		}
		if(document.all.txt_srch.value == ""){
			alert("请选择查询字段！");
			return false;
		}
		if(document.all.chk_search.value=="ON" && document.all.txt_search.value == ""){
			alert("请输入搜索内容！");
			return false;
		}
		document.all.form1.action = "fo.jsp";
		document.all.form1.submit(); 
	}

	function oncheck(myname){
		if(myname.checked)
			myname.value="ON";
		else
			myname.value="";
	}
	function modFo(fo_no,fover,dept_id){
		
		document.all.add.style.display= "block";
		document.all.but_add.title= "取消这次修改操作";
        document.all.but_add.value= "取消修改";
	
		document.all.fo_no.value = fo_no;
		document.all.fover.value = fover;
		document.all.dept_id.value = dept_id;
	}
	
	function delFo(fo_no,fover){
		if(!confirm("本FO的工序信息也会被删除，删除后不可恢复,是否真的要删除？")){
		}else{
			  document.all.dotype.value = "del";
			  document.all.fo_no.value = fo_no;
			  document.all.fover.value = fover;
			  document.all.form1.action = "../../FoSvlt";
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
		document.all.txt_fo_no.value = delid;
		document.all.form1.action = "../../../FoSvlt";
		document.all.form1.submit();
	}
	function clearlxr(){
		if(!confirm("是否真的要删除所有记录？")){
			return false;
		}
		document.all.txt_edit.value = "5";
		document.all.form1.action = "../../../FoSvlt";
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
		return true;
	}
//-->
</script>
<%
	} catch (Exception e) {
		System.out.println("fo 出错：" + e);
	}
%>