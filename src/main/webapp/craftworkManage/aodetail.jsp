<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
	<head>
		<base href="<%=basePath%>">
		<!-- miniui.js -->
		<script type="text/javascript"src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript"src="<%=path %>/scripts/miniui/miniui.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css"rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css"rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/css/person.css" type=text/css rel=stylesheet>
		<title>AO信息</title>
		<style type="text/css">
			* {
				margin: 0;
				padding: 0;
			}
		</style>
	</head>
		<body leftMargin=0 topMargin=0>
			<table class="data_list_table" align="center" width="100%" border="1"
				cellspacing="2" cellpadding="0"
				style="word-break: break-all; border-collapse: collapse">
				<tr height="22">
					<td align="left">
						<strong><font size="4"></font>
						</strong>
						<input type="button" id="but_add" value="" onclick="showdiv();" style="cursor: hand;">
						<input type="button" id="cmtyf" value="查看/添加cmtyf" onclick="docmtyf()" style="cursor: hand;">
					</td>
				</TR>
			</TABLE>
			<div id="add" name="add" background:#dafec5">
				<form id="form1" name="form1" method="post">
					<TABLE class="green_list_table" align="center" width="100%" border="1" cellspacing="2" cellpadding="0"
						style="word-break: break-all; border-collapse: collapse" bgcolor="#ffffff">
						<tr>
							<th width=100>装配号：</th>
							<td><input id="aoId"  name="aoId" class="mini-textbox" width="100%" required="true" value="${aoId}" readonly/></td>
							<th width=100>装配工艺编号：</th>
							<td><input id="aoNo"  name="aoNo" class="mini-textbox"  width="100%" required="true" value="${result.aoNo}"/></td>
							<th>装配名称：</th>
							<td><input id="aoName"  name="aoName" class="mini-textbox"  width="100%" required="true" value="${result.aoName}"/></td>
							</tr>
						<tr>
							<th>工艺版本号：</th>
							<td><input id="aoVer"  name="aoVer" class="mini-textbox"  width="100%" required="true" value="${result.aoVer}"/></td>
							<th width=100>工位号 ：</th>
							<td><input id="workplaceCode"  name="workplaceCode" class="mini-textbox"  width="100%" required="true" value="${result.workplaceCode}"/></td>
							<th>工位名称：</th>
							<td><input id="workplaceName"  name="workplaceName" class="mini-textbox"  width="100%"  required="true" value="${result.workplaceName}"/></td>
						</tr>
						<tr>
							<th>装配时间：</th>
							<td><input id="aoTime"  name="aoTime" class="mini-textbox"  width="100%" required="true" value="${result.aoTime}"/></td>
							<th>装配图号：</th>
							<td><input id="partNo"  name="partNo" class="mini-textbox"  width="100%" required="true" value="${result.partNo}"/></td>
						</tr>
						<tr height="60px;">
	   						<td><label for="aoContent$text">装配内容</label></td>
	        				<td colspan="5"><input id="aoContent" name="aoContent" class="mini-textarea" emptyText="请输入装配内容" value="${result.aoContent}"
	        					width="100%" height="100%"/></td>
	       				 </tr>
						<tr>
							<td align=right><a class="mini-button" iconCls="icon-save" plain="false"  onclick="toservlet()">保存</a></td>
							<!--<td><input type="button" value="清 空" title="清空文本框" onclick="clearOk();" style="cursor: hand;"></td>-->
						</tr>
						<input id="productId" name="productId" class="mini-hidden" value="${productId}"/>
						<input id="issueNum" name="issueNum" class="mini-hidden" value="${issueNum}"/>
						<input id="orderId" name="orderId" class="mini-hidden" value="${orderId}"/>
					</table>
				</form>
			</div>
			
			<div id="grid1" class="mini-datagrid" style="width:100%;height:300px;"
		         	borderStyle="border:0;" multiSelect="true" idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
		         	url="BaseServlet?meth=AOList&productId=${productId}&issueNum=${issueNum}&aoId=${aoId}">
		        <div property="columns">
		            <div type="indexcolumn"></div>
		            <div name="action" width="40" headerAlign="center" align="center" renderer="onOperatePower"cellStyle="padding:0;">操作</div>
		            <div field="aoNo" width="60" headerAlign="center">AO编号</div>
		            <div field="aoName" width="100" headerAlign="center">AO名称</div>
		            <div field="workplaceCode" width="60" headerAlign="center">工位号</div>
		            <div field="workplaceName" width="100" headerAlign="center">工位名称</div>
		            <div field="aoVer" width="50" headerAlign="center">AO版本</div>
		            <div field="partNo" width="100" headerAlign="center">装配图</div>
		            <div field="aoTime" width="60" headerAlign="center">AO时间</div>
		            <div field="aoContent" width="160" headerAlign="center">装配内容</div>
		        </div>
		    </div>
    
			<hr color=green>

			<!-- 这里对应一个物料就一个AO文件 -->
			<form name="forsubmit2">
				<input type="hidden" name="ao_no" value="">
				<input type="hidden" name="aover" value="">
				<input type="hidden" name="dotype" value="">
			</form>
		</BODY>
</HTML>
<script type="text/javascript">
	mini.parse();
	var grid = mini.get("grid1");
    grid.load();
    
    function onOperatePower(e) {
		var str = "";
		str += "<span>";
		str += "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.aoId+"\',\'" + e.row.aoNo+"\',\'" + e.row.aoVer+"\',\'" + e.row.workplaceCode+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;&nbsp;</span></a>";
		str += "</span>";
		str += "<span>";
		str += "<a style='margin-right:2px;' title='刀量夹具' href=javascript:onCMTYF(\'" + e.row.aoId+"\',\'" + e.row.aoNo+"\',\'" + e.row.aoVer+"\',\'" + e.row.workplaceCode+"\') ><span class='mini-button-text mini-button-icon icon-cut'>&nbsp;&nbsp;</span></a>";
		str += "</span>";
		return str;
	};
	
	function ondEdit(aoId,aoNo,aoVer,workplaceCode){
		var orderId = document.getElementById("orderId").value;
	    window.parent.document.getElementById("subIframe").src="BaseServlet?meth=AodetailData&pathTo=aodetailData&aoId="+aoId+"&aoNo="+aoNo+"&orderId="+orderId;
	}
	
	function onCMTYF(aoId,aoNo,aoVer,workplaceCode){
		var orderId = document.getElementById("orderId").value;
	    window.location.href="BaseServlet?meth=GoAocmtyf&pathTo=aocmtyf&aoId="+aoId+"&aoNo="+aoNo+"&orderId="+orderId;	    
	}
		
	function refresh(){
		grid.reload();
	}
	
	function toservlet(){
   			var form = new mini.Form("#form1");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
                var params = eval("("+mini.encode(data)+")");
                var url = 'BaseServlet?meth=addAoDetail';
   		        jQuery.post(url, params, function(data){
   	   		  		mini.confirm(data.result, "确定？",
		                 function (action){            
		                     if (action == "ok") {
		                    	window.location.href = window.location.href;	
		                     }
		                 }
		             );
   	   		        },'json');
            }
   		}

	function toservlet2() {
		if (document.all.workplace.value == "") {
			alert("请输入工位号！");
			document.all.workplace.focus();
			return;
		}
		document.all.form1.action = "../../../down2";
		document.all.form1.submit();
	}

	function showdiv() {
		if (document.all.but_add.value == '添加AO') {
			document.all.add.style.display = "block";
			document.all.but_add.title = "取消这次添加操作";
			document.all.but_add.value = "取消添加";
		} else if (document.all.but_add.value == '取消添加') {
			document.all.add.style.display = "none";
			document.all.but_add.value = "添加AO";
		} else if (document.all.but_add.value == '查看AO') {
			document.all.but_add.disabled = "disabled";
		}

	}

	function add2Ao() {
		document.all.add.style.display = "block";
		document.all.but_add.title = "取消这次添加操作";
		document.all.but_add.value = "取消添加";

	}

	function docmtyf() {
		window.location.href = "Aocmtyf.jsp";
	}
	function hiddendiv() {
		document.all.add.style.display = "none";
		document.all.but_add.style.display = "block";
		document.all.txt_but_add.value = "block";
		document.all.txt_div.value = "none";
	}
	function clearOk() {
		document.all.ao_no.value = "";
		document.all.aoname.value = "";
		document.all.aover.value = "";
		document.all.ao_time.value = "";
		document.all.workplacecode.value = "";
		document.all.workplacename.value = "";
		document.all.partno.value = "";
	}

	function modAo(ao_no, aoname, aover, ao_time, code, name, partno) {

		document.all.add.style.display = "block";
		document.all.but_add.title = "取消这次修改操作";
		document.all.but_add.value = "取消修改";
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
	function delAo(ao_no, aover) {
		if (!confirm("这本AO的工序信息也会被删除，删除后不可恢复,是否真的要删除？")) {
		} else {
			document.forsubmit2.ao_no.value = ao_no;
			document.forsubmit2.aover.value = aover;
			document.forsubmit2.dotype.value = "del";
			document.forsubmit2.action = "../../AoSvlt";
			document.forsubmit2.submit();
		}
	}

	function del() {
		var delid = "";
		var num = "";
		if (num == 1) {
			if (document.all.checkboxid.checked == true)
				delid = "'" + document.all.checkboxid.value + "'";
		}
		if (num >= 2) {
			var numid = document.all.checkboxid.length;
			for ( var i = 0; i < numid; i++) {
				if (document.all.checkboxid[i].checked == true) {
					if (delid == "") {
						delid = "'" + document.all.checkboxid[i].value + "'";
					} else {
						delid = delid + ",'" + document.all.checkboxid[i].value
								+ "'";
					}
				}
			}
		}
		if (delid == "") {
			alert("请选择要删除的记录！");
			return false;
		}
		if (!confirm("是否真的要删除？")) {
			return false;
		}
		document.all.txt_edit.value = "4";
		document.all.txt_ao_no.value = delid;
		document.all.form1.action = "../../../AoSvlt";
		document.all.form1.submit();
	}

	//-->
</script>
