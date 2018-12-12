<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>装配管理页面</title>

	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link href="./css/person1.css" type=text/css rel=stylesheet>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="./scripts/jquery.min.js" charset="utf-8" language="utf-8"></script>
	<script type="text/javascript" charset="utf-8" language="utf-8">
		function editAO(number){
		 	if(number==1){
		 		document.getElementById("addAO").style.display="none";
				document.getElementById("showAOInfo").style.display="";
				document.getElementById("showAOChange").style.display="none";
		 	}
		 	if(number==2){
		 		document.getElementById("addAO").style.display="none";
				document.getElementById("showAOInfo").style.display="none";
				document.getElementById("showAOChange").style.display="";
		 	}
		 	createXMLHttpRequest();
			var url = "EditAOInfoServlet";
			xmlHttp.open("POST", url, true);
			xmlHttp.onreadystatechange = function() {
				onStateChange(number)
			};
			xmlHttp.send(null);
		 }
		 
		 function createXMLHttpRequest() {
			if (window.XMLHttpRequest) {
				xmlHttp = new XMLHttpRequest();
			} else if (window.ActiveXObject) {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
		}
		
		function onStateChange(number) {
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					showSelect(xmlHttp.responseXML,number);
				}
			}
		}
		
		
		function fenye(now,will,length,number){
		
			createXMLHttpRequest();
			var url = "EditAOInfoServlet?now="+now+"&will="+will+"&length="+length;
			xmlHttp.open("POST", url, true);
			xmlHttp.onreadystatechange = function() {
				onStateChange(number);
			};
			xmlHttp.send(null);
		}
		
		function changeLength(value,number){
			createXMLHttpRequest();
			var url = "EditAOInfoServlet?pageLength="+value;
			xmlHttp.open("POST", url, true);
			xmlHttp.onreadystatechange = function() {
				onStateChange(number);
			};
			xmlHttp.send(null);
		}
		
		function updateAODetail(value){
			//document.getElementById("showAOChange").style.display="none";
			
			var PRODUCTIDS = document.getElementsByName("PRODUCTIDChange");
			var AO_NOS = document.getElementsByName("AO_NOChange");
			var ISSUE_NUMS = document.getElementsByName("ISSUE_NUMChange");
			var AO_ORDERS = document.getElementsByName("AO_ORDERChange");
			var ITEMIDS = document.getElementsByName("ITEMIDChange");
			var AOVERS = document.getElementsByName("AOVERChange");
			var PARENTAO_NOS = document.getElementsByName("PARENTAO_NOChange");
			var AO_TIMES = document.getElementsByName("AO_TIMEChange");
			var AONAMES = document.getElementsByName("AONAMEChange");
			var WORKPLACECODES = document.getElementsByName("WORKPLACECODEChange");
			var WORKPLACENAMES = document.getElementsByName("WORKPLACENAMEChange");
			var AO_CONTENTS = document.getElementsByName("AO_CONTENTChange");

			var PRODUCTID = PRODUCTIDS[value].value;
			var AO_NO = AO_NOS[value].value;
			var ISSUE_NUM = ISSUE_NUMS[value].value;
			var AO_ORDER = AO_ORDERS[value].value;
			var ITEMID = ITEMIDS[value].value;
			var AOVER = AOVERS[value].value;
			var PARENTAO_NO = PARENTAO_NOS[value].value;
			var AO_TIME = AO_TIMES[value].value;
			var AONAME = AONAMES[value].value;
			var WORKPLACECODE = WORKPLACECODES[value].value;
			var WORKPLACENAME = WORKPLACENAMES[value].value;
			var AO_CONTENT = AO_CONTENTS[value].value;
			
			
			var table1 = document.getElementById("showAOChange");
					while (table1.hasChildNodes()) {
						table1.removeChild(table1.firstChild);
					}
			
			var html = "";

			
			html += "<tr><th>产品编号</th><td><input type=\"text\" name=\"PRODUCTIDChange\" value='"+
				PRODUCTID+"'  readonly style='color: red;' "+
				"onkeyup=\"(this.v=function(){this.value=this.value.replace(/[^0-9]+.?[^0-9]+/,'');}).call(this)\"/></td>";
			html += "<th>装配号</th><td><input type=\"text\" name=\"AO_NOChange\" value='"+
				AO_NO+"' /></td>";
			html += "<th rowspan='4'>装配事项</th><td rowspan='4' colspan='4' padding='0'><textarea id=\"AO_CONTENTChange\" name=\"AO_CONTENTChange\" maxlength=\"4000\" rows=\"8\" cols=\"50\" ></textarea></td></tr>";	
				/* document.showAODetail.oper_content.value = arr[1];*/
			/*<input type=\"textarea\" name=\"AO_CONTENTChange\" value='"+
				AO_CONTENT+"' />*/
			html += "<tr><th>版本号</th><td><input type=\"text\" name=\"ISSUE_NUMChange\" value='"+
				ISSUE_NUM+"' /></td>";
			html += "<th>装配顺序</th><td><input type=\"text\" name=\"AO_ORDERChange\" value='"+
				AO_ORDER+"' /></td></tr>";
			html += "<tr><th>物料号</th><td><input type=\"text\" name=\"ITEMIDChange\" value='"+
				ITEMID+"' /></td>";
			html += "<th>装配版本</th><td><input type=\"text\" name=\"AOVERChange\" value='"+
				AOVER+"'/></td></tr>";
			html += "<tr><th>父装配号</th><td><input type=\"text\" name=\"PARENTAO_NOChange\" value='"+
				PARENTAO_NO+"' /></td>";
			html += "<th>装配耗时</th><td><input type=\"text\" name=\"AO_TIMEChange\" value='"+
				AO_TIME+"'/></td></tr>";
			html += "<tr><th>装配名称</th><td><input type=\"text\" name=\"AONAMEChange\" value='"+
				AONAME+"' /></td>";
			html += "<th>装配地点编号</th><td><input type=\"text\" name=\"WORKPLACECODEChange\" value='"+
				WORKPLACECODE+"'/></td>";
			html += "<th>装配地点名称</th><td><input type=\"text\" name=\"WORKPLACENAMEChange\" value='"+
				WORKPLACENAME+"' /></td>";
			html += "<td><button name='0' onclick=\"updateAO(this.name);\">修改装配工艺</button></td></tr>";
		
			var table3 = document.getElementById("showAODetail");
			
			while (table3.hasChildNodes()) {
				table3.removeChild(table3.firstChild);
			}
			document.getElementById("showAOChange").style.display="none";
			$(html).prependTo(table3);
			var content = document.getElementById("AO_CONTENTChange");
			content.value = AO_CONTENT;
		}
		
		function showSelect(xmlData,number) {
			if (xmlData.documentElement.hasChildNodes()) {
				var AOInfo = xmlData.getElementsByTagName("AOInfo");
				var PRODUCTID = xmlData.getElementsByTagName("PRODUCTID");
				var AO_NO = xmlData.getElementsByTagName("AO_NO");
				var ISSUE_NUM = xmlData.getElementsByTagName("ISSUE_NUM");
				var AO_ORDER = xmlData.getElementsByTagName("AO_ORDER");
				var ITEMID = xmlData.getElementsByTagName("ITEMID");
				var AOVER = xmlData.getElementsByTagName("AOVER");
				var PARENTAO_NO = xmlData.getElementsByTagName("PARENTAO_NO");
				var AO_TIME = xmlData.getElementsByTagName("AO_TIME");
				var AONAME = xmlData.getElementsByTagName("AONAME");
				var WORKPLACECODE = xmlData.getElementsByTagName("WORKPLACECODE");
				var WORKPLACENAME = xmlData.getElementsByTagName("WORKPLACENAME");AO_CONTENT
				var AO_CONTENT = xmlData.getElementsByTagName("AO_CONTENT");

				var html = "<tr><th>编辑</th><th>产品编号</th><th>装配号</th><th>版本号</th><th>装配顺序</th>"+
				"<th>物料号</th><th>装配版本</th><th>父装配号</th><th>装配耗时</th><th>装配名称</th><th>装配地点编号</th><th>装配地点名称</th><th>装配事项</th></tr>";
				if(number==1){                    //删除用户
					for ( var i = 0; i < AOInfo.length; i++) {
						html += "<tr id=\""+i+"\"><td>";
						html +="<select onchange=\"deleteAO('"
							+PRODUCTID[i].firstChild.nodeValue+"','"
							+AO_NO[i].firstChild.nodeValue+"','"
							+AO_ORDER[i].firstChild.nodeValue+"',this.value);\"><option value='-1' selected='selected'>请选择</option><option value='"+i+"'>删除</option></select></td>";
						html += "<td>" + PRODUCTID[i].firstChild.nodeValue+ "</td>";
						html += "<td>" + AO_NO[i].firstChild.nodeValue+ "</td>";
						html += "<td>" + ISSUE_NUM[i].firstChild.nodeValue+ "</td>";
						html += "<td>" + AO_ORDER[i].firstChild.nodeValue+ "</td>";
						html += "<td>" + ITEMID[i].firstChild.nodeValue+ "</td>";
						html += "<td>" + AOVER[i].firstChild.nodeValue+ "</td>";
						html += "<td>" + PARENTAO_NO[i].firstChild.nodeValue+ "</td>";
						html += "<td>" + AO_TIME[i].firstChild.nodeValue+ "</td>";
						html += "<td>" + AONAME[i].firstChild.nodeValue+ "</td>";
						html += "<td>" + WORKPLACECODE[i].firstChild.nodeValue + "</td>";
						html += "<td>" + WORKPLACENAME[i].firstChild.nodeValue + "</td>";
						html += "<td>" + AO_CONTENT[i].firstChild.nodeValue + "</td></tr>";
					}
					
					html += "<tr><td colspan='5'></td>"+
						"<td  colspan='4'><a id='first_delete' onclick=\"fenye(1,-4,4,1)\";>|<<</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "+
						"<a id='previous_delete' onclick=\"fenye(1,-1,4,1)\";>|\<</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<a id='next_delete' onclick=\"fenye(1,-2,4,1)\";>\>|</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<a id='last_delete' onclick=\"fenye(1,-3,4,1)\";>>>|</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<select onchange='changeLength(this.value,1);'>"+
						"<option value='5' selected='selected'>-请选择-</option>"+
						"<option value='5'>5</option><option value='10'>10</option>"+
						"<option value='15'>15</option><option value='20'>20</option></select></td>"+
						"<td colspan='3'></td></tr>";
						
					var table1 = document.getElementById("showAOInfo");
					while (table1.hasChildNodes()) {
						table1.removeChild(table1.firstChild);
					}
					$(html).prependTo(table1);
				}
				
				if(number==2){                    //修改用户
					for ( var i = 0; i < AOInfo.length; i++) {
						html += "<tr>";
						html += "<td><button name='"+i+"' onclick=\"updateAO(this.name);\">修改</button>"+
						"<button name='"+i+"' onclick=\"updateAODetail(this.name);\">详情修改</button></td>";
						html += "<td><input type=\"text\" name=\"PRODUCTIDChange\" value='"+
							PRODUCTID[i].firstChild.nodeValue+"' size='18' readonly style='color: red;' "+
							"onkeyup=\"(this.v=function(){this.value=this.value.replace(/[^0-9]+.?[^0-9]+/,'');}).call(this)\"/></td>";
						html += "<td><input type=\"text\" name=\"AO_NOChange\" value='"+
							AO_NO[i].firstChild.nodeValue+"' size='18'/></td>";
						html += "<td><input type=\"text\" name=\"ISSUE_NUMChange\" value='"+
							ISSUE_NUM[i].firstChild.nodeValue+"' size='10'/></td>";
						html += "<td><input type=\"text\" name=\"AO_ORDERChange\" value='"+
							AO_ORDER[i].firstChild.nodeValue+"' size='10'/></td>";
						html += "<td><input type=\"text\" name=\"ITEMIDChange\" value='"+
							ITEMID[i].firstChild.nodeValue+"' size='18'/></td>";
						html += "<td><input type=\"text\" name=\"AOVERChange\" value='"+
							AOVER[i].firstChild.nodeValue+"' size='18'/></td>";
						html += "<td><input type=\"text\" name=\"PARENTAO_NOChange\" value='"+
							PARENTAO_NO[i].firstChild.nodeValue+"' size='18'/></td>";
						html += "<td><input type=\"text\" name=\"AO_TIMEChange\" value='"+
							AO_TIME[i].firstChild.nodeValue+"' size='10'/></td>";
						html += "<td><input type=\"text\" name=\"AONAMEChange\" value='"+
							AONAME[i].firstChild.nodeValue+"' size='18'/></td>";
						html += "<td><input type=\"text\" name=\"WORKPLACECODEChange\" value='"+
							WORKPLACECODE[i].firstChild.nodeValue+"' size='18'/></td>";
						html += "<td><input type=\"text\" name=\"WORKPLACENAMEChange\" value='"+
							WORKPLACENAME[i].firstChild.nodeValue+"' size='2S0'/></td>";
						html += "<td><input type=\"text\" name=\"AO_CONTENTChange\" value='"+
							AO_CONTENT[i].firstChild.nodeValue+"' size='2S0'/></td></tr>";
					}
					
					html += "<tr><td colspan='5'></td>"+
						"<td  colspan='4'><a id='first_delete' onclick=\"fenye(1,-4,4,2)\";>|<<</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "+
						"<a id='previous_delete' onclick=\"fenye(1,-1,4,2)\";>|\<</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<a id='next_delete' onclick=\"fenye(1,-2,4,2)\";>\>|</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<a id='last_delete' onclick=\"fenye(1,-3,4,2)\";>>>|</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<select onchange='changeLength(this.value,2);'>"+
						"<option value='5' selected='selected'>-请选择-</option>"+
						"<option value='5'>5</option><option value='10'>10</option>"+
						"<option value='15'>15</option><option value='20'>20</option></select></td>"+
						"<td colspan='3'></td></tr>";

					var table1 = document.getElementById("showAOChange");
					while (table1.hasChildNodes()) {
						table1.removeChild(table1.firstChild);
					}
					$(html).prependTo(table1);

				}
				jQuery(document).ready(function(){  
		        jQuery('.query_form_table tr').mouseover(function(){    //如果鼠标移到class为query_form_table的表格的tr上时，执行函数  
		        jQuery(this).addClass("over");}).mouseout(function(){   //给这行添加class值为over，并且当鼠标一出该行时执行函数  
		        $(this).removeClass("over");})                          //移除该行的class  
		        jQuery('.query_form_table tr:even').addClass("alt");    //给class为query_form_table的表格的偶数行添加class值为alt    
		    	jQuery('tr:even').addClass('alt');
      		});		
			}
		}

		function initSelect(oElement) {
			while (oElement.options.length > 0) {
				oElement.remove(oElement.options.length - 1);
			}
			var op = new Option("数据加载中...");
			oElement.options.add(op);
			op.value = "";
		}
		
		function updateAO(value){
		
			var PRODUCTIDS = document.getElementsByName("PRODUCTIDChange");
			var AO_NOS = document.getElementsByName("AO_NOChange");
			var ISSUE_NUMS = document.getElementsByName("ISSUE_NUMChange");
			var AO_ORDERS = document.getElementsByName("AO_ORDERChange");
			var ITEMIDS = document.getElementsByName("ITEMIDChange");
			var AOVERS = document.getElementsByName("AOVERChange");
			var PARENTAO_NOS = document.getElementsByName("PARENTAO_NOChange");
			var AO_TIMES = document.getElementsByName("AO_TIMEChange");
			var AONAMES = document.getElementsByName("AONAMEChange");
			var WORKPLACECODES = document.getElementsByName("WORKPLACECODEChange");
			var WORKPLACENAMES = document.getElementsByName("WORKPLACENAMEChange");
			var AO_CONTENTS = document.getElementsByName("AO_CONTENTChange");

			var PRODUCTID = PRODUCTIDS[value].value;
			var AO_NO = AO_NOS[value].value;
			var ISSUE_NUM = ISSUE_NUMS[value].value;
			var AO_ORDER = AO_ORDERS[value].value;
			var ITEMID = ITEMIDS[value].value;
			var AOVER = AOVERS[value].value;
			var PARENTAO_NO = PARENTAO_NOS[value].value;
			var AO_TIME = AO_TIMES[value].value;
			var AONAME = AONAMES[value].value;
			var WORKPLACECODE = WORKPLACECODES[value].value;
			var WORKPLACENAME = WORKPLACENAMES[value].value;
			var AO_CONTENT = AO_CONTENTS[value].value;
			
			createXMLHttpRequest();
			var url =  "UpdateAO?PRODUCTID=" + encodeURI(PRODUCTID) 
			+ "&AO_NO=" + encodeURI(AO_NO)
			+ "&ISSUE_NUM=" + encodeURI(ISSUE_NUM)
			+ "&AO_ORDER=" + encodeURI(AO_ORDER)
			+ "&ITEMID=" + encodeURI(ITEMID)
			+ "&AOVER=" + encodeURI(AOVER)
			+ "&PARENTAO_NO=" + encodeURI(PARENTAO_NO)
			+ "&AO_TIME=" + encodeURI(AO_TIME)
			+ "&AONAME=" + encodeURI(AONAME)
			+ "&WORKPLACECODE=" + encodeURI(WORKPLACECODE)
			+ "&WORKPLACENAME=" + encodeURI(WORKPLACENAME)
			+ "&AO_CONTENT=" + encodeURI(AO_CONTENT);
		
			xmlHttp.open("POST", url, true);
			xmlHttp.onreadystatechange = function() {
				onupdate()
			};
			xmlHttp.send(null);
		}
		function onupdate(){
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					showUpdate(xmlHttp.responseXML);
				}
			}
		}
		
		function showUpdate(xmlData) {
			var isDeleted = xmlData.getElementsByTagName("ok");
			if (isDeleted[0].firstChild.nodeValue==1) {
				alert("修改成功！！！！");   //删除成功以后还要做显示的功能
		                                 //删除不成功要找到原因所在
				//editUserInfo();
			}
			if (isDeleted[0].firstChild.nodeValue==0) {
				alert("修改失败！！！！");
				editMachine(2);   
			}
		}

		function deleteAO(PRODUCTID,AO_NO,AO_ORDER,optionvalue){
			if (optionvalue!='-1') {                 //1 表示删除操作，还可以在此添加其他的操作！！！！
				createXMLHttpRequest();
				//此处有中文乱码问题，有待解决！！！！
				var url = "DeleteAOInfo?PRODUCTID="+encodeURI(PRODUCTID)+"&AO_NO="+encodeURI(AO_NO)+"&AO_ORDER="+encodeURI(AO_ORDER);
				xmlHttp.open("POST", url, true);
				xmlHttp.onreadystatechange = function() {
					ondeleted(optionvalue)
				};
				xmlHttp.send(null);
			}
		}
		function ondeleted(optionvalue){
			if (xmlHttp.readyState == 4) {
				if (xmlHttp.status == 200) {
					showDeleted(xmlHttp.responseXML,optionvalue);
				}
			}
		}
		function showDeleted(xmlData,optionvalue) {
			var isDeleted = xmlData.getElementsByTagName("isDeleted");
			if (isDeleted[0].firstChild.nodeValue==1) {
				alert("删除成功！！！！");   //删除成功以后还要做显示的功能
		                                 //删除不成功要找到原因所在
				editMachine(1);
			}
			if (isDeleted[0].firstChild.nodeValue==0) {
				alert("删除失败！！！！");   
			}
		}
		
		function showAddAO(){
			document.getElementById("addAO").style.display="";
			document.getElementById("showAOInfo").style.display="none";
			document.getElementById("showAOChange").style.display="none";			
		}
		
		function checkinput(inputid){
			var doc = document.getElementById(inputid);
			var length =  doc.value.length;
			if(inputid=="machineID"){
				
				if(length<2){
					alert("机床编号长度小于2");
				}else{
					if(length>32){
						alert("机床编号长度大于32");
					}
				}	
			}
			if(inputid=="machineName"){
				if(length>32){
					alert("机床名称长度大于32");
				}
			}
			if(inputid=="machinePower"){
				var patrn=/^\d+(\d|(\.[1-9]{1,6}))$/; 
				if (!patrn.exec(doc.value)) alert("您输入的不是小数！"); 
			}
		}
	</script>

  </head>
  
  <body>
    <h1 align="center" style="cursor: pointer" onmouseover="this.style.color='#0000ff'" 
    	onmouseout="this.style.color='#000000'" onclick="showAddAO()">编制装配工艺</h1>
    <form id="addAO" action="AddAOServlet">
      <table  class="query_form_table" align="center">

   		<tr><th>产品编号：</th>
   		    <td><input type="text" id = "PRODUCTID" name="PRODUCTID" maxlength="63" size="47"></input>
   		<span class="red_star">* 请输入2-32位字符</span></td></tr>
   		
   		<tr><th>装配号：</th>
   		    <td><input type="text" id = "AO_NO" name="AO_NO" maxlength="63" size="47" 
   		    	onblur="checkinput('AO_NO')"></input>
   		<span class="red_star">* 请输入1-32个字符</span></td></tr> 
   		
   		<tr><th>版本号：</th>
   		    <td><input type="text" id = "ISSUE_NUM" name="ISSUE_NUM" maxlength="63" size="47"></input>
   		<span class="red_star">* 请输入1-32个字符</span></td></tr> 
   		
   		<tr><th>装配顺序号：</th>
   		    <td><input type="text" id = "AO_ORDER" name="AO_ORDER" maxlength="63" size="47" 
   		    	onblur="checkinput('AO_ORDER')"></input>
   		<span class="red_star">* 请输入8位内数字</span></td></tr> 
   		
   		<tr><th>物料编号：</th>
   		    <td><input type="text" id = "ITEMID" name="ITEMID" maxlength="63" size="47"></input>
   		<span class="red_star">* 请输入1-32个字符</span></td></tr> 
   			
   		<tr><th>装配版本：</th>
   		    <td><input type="text" id = "AOVER" name="AOVER" maxlength="63" size="47"></input>
   		<span class="red_star">* 请输入1-20个字符</span></td></tr> 
   		
   		<tr><th>父装配号：</th>
   		    <td><input type="text" id = "PARENTAO_NO" name="PARENTAO_NO" maxlength="63" size="47"></input>
   		<span class="red_star">* 请输入1-32个字符</span></td></tr> 
   		
   		<tr><th>装配时间：</th>
   		    <td><input type="text" id = "AO_TIME" name="AO_TIME" maxlength="63" size="47" 
   		    	onblur="checkinput('AO_TIME')"></input>
   		<span class="red_star">* 请输入相应小数</span></td></tr> 
   		
   		<tr><th>装配名称：</th>
   		    <td><input type="text" id = "AONAME" name="AONAME" maxlength="63" size="47"></input>
   		<span class="red_star">* 请输入1-32个字符</span></td></tr> 
   		
   		<tr><th>装配地点编号：</th>
   		    <td><input type="text" id = "WORKPLACECODE" name="WORKPLACECODE" maxlength="63" size="47"></input>
   		<span class="red_star">* 请输入1-32个字符</span></td></tr> 
   		
   		<tr><th>装配地点名称：</th>
   		    <td><input type="text" id = "WORKPLACENAME" name="WORKPLACENAME" maxlength="63" size="47"></input>
   		<span class="red_star">* 请输入1-32个字符</span></td></tr> 
   		
   		<tr><th>装配事项：</th>
   		    <td><input type="text" id = "AO_CONTENT" name="AO_CONTENT" maxlength="63" size="47"></input>
   		<span class="red_star">* 请输入装配注意事项</span></td></tr> 

   		<tr><td><input type = "submit" value = "添加装配信息"></td>
			<%if(request.getAttribute("addOk")!=null){%>
	  		<span style="color: red;"><%=request.getAttribute("addOk") %></span>
		  	<%
		  	} %>
   			<td><input type = "reset" value = "取消添加"></td></tr>
       </table>
     </form>
        

	<h1 align="center" onclick="editAO(1)" style="cursor: pointer" 
		onmouseover="this.style.color='#0000ff'" onmouseout="this.style.color='#000000'">删除装配工艺</h1>
      <table id="showAOInfo" class="query_form_table" align="center" width="100%"></table>
       
    <h1 align="center" onclick="editAO(2)" style="cursor: pointer" 
    	onmouseover="this.style.color='#0000ff'" onmouseout="this.style.color='#000000'">修改装配工艺</h1>
      <table id="showAOChange" class="query_form_table" align="center" width="100%"></table>
      <table id="showAODetail" name="showAODetail" class="query_form_table" align="center" width="75%"></table>

  </body>
</html>

