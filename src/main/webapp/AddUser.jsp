<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@page import="java.util.*" %>
<%@page import="com.wl.forms.User"%>
<jsp:useBean id="ds" scope="page" class="cfmes.util.DealString"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>新增系统用户</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link href="./css/person1.css" type=text/css rel=stylesheet>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="./scripts/jquery.min.js" charset="utf-8" language="utf-8"></script>

  </head>
  
  <script>
  function toservlet(){
    if((document.getElementById("adduser_id")).value == ""){alert("请输入用户代码!");return;}
    if((document.getElementById("addstaff_code")).value == ""){alert("请输入员工代码!");return;}
    if((document.getElementById("addrole_id")).value == ""){alert("请输入角色代码!");return;}
    if((document.getElementById("addrole_name")).value == ""){alert("请输入角色名称!");return;}
    if((document.getElementById("addpassword")).value == ""){alert("请输入用户密码!");return;}
 }
 
 function getAuthority(value){
 	document.getElementById("authority").value = value;
 }
 
 function editUserInfo(number){
 
 	if(number==1){
 		document.getElementById("changePass").style.display="none";
		document.getElementById("adduser").style.display="none";
		document.getElementById("showUserInfo").style.display="";
		document.getElementById("showUserInfoChange").style.display="none";
 	}
 	if(number==2){
 		document.getElementById("changePass").style.display="none";
		document.getElementById("adduser").style.display="none";
		document.getElementById("showUserInfo").style.display="none";
		document.getElementById("showUserInfoChange").style.display="";
 	}
 
 
 
 	createXMLHttpRequest();
	var url = "EditUserInfoServlet";
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

function getChangedAuthority(value,textid){
	alert(value+"  "+textid);
	var text = document.getElementById(textid);
	text.value = value;
}


function fenye(now,will,length,number){
	createXMLHttpRequest();
	var url = "EditUserInfoServlet?now="+now+"&will="+will+"&length="+length;
	xmlHttp.open("POST", url, true);
	xmlHttp.onreadystatechange = function() {
		onStateChange(number);
	};
	xmlHttp.send(null);
}

function changeLength(value,number){
	createXMLHttpRequest();
	var url = "EditUserInfoServlet?pageLength="+value;
	xmlHttp.open("POST", url, true);
	xmlHttp.onreadystatechange = function() {
		onStateChange(number);
	};
	xmlHttp.send(null);
}

function showSelect(xmlData,number) {

	if (xmlData.documentElement.hasChildNodes()) {

			var names = xmlData.getElementsByTagName("userInfo");
			var staffCode = xmlData.getElementsByTagName("staffcode");
			var userName = xmlData.getElementsByTagName("userName");
			var password = xmlData.getElementsByTagName("password");
			var authority = xmlData.getElementsByTagName("authority");

			var html = "<tr><th>编辑</th><th>员工编号</th><th>员工姓名</th><th>用户密码</th><th>用户权限</th></tr>";
			if(number==1){                    //删除用户
				for ( var i = 0; i < staffCode.length; i++) {
					html += "<tr id=\""+i+"\"><td>";
					html +="<select onchange=\"deleteUser('"
						+staffCode[i].firstChild.nodeValue+"','"
						+userName[i].firstChild.nodeValue+"','"
						+password[i].firstChild.nodeValue+"','"
						+authority[i].firstChild.nodeValue+"',this.value);\"><option value='-1' selected='selected'>请选择</option><option value='"+i+"'>删除</option></select></td>";
					html += "<td>" + staffCode[i].firstChild.nodeValue+ "</td>"
					html += "<td>" + userName[i].firstChild.nodeValue + "</td>";
					html += "<td>" + password[i].firstChild.nodeValue + "</td>";
					html += "<td>" + authority[i].firstChild.nodeValue + "</td></tr>";
				}
			
				html += "<tr><td colspan='4'></td>"+
						"<td  colspan='8'><a id='first_delete' onclick=\"fenye(1,-4,4,1)\";>|<<</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "+
						"<a id='previous_delete' onclick=\"fenye(1,-1,4,1)\";>|\<</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<a id='next_delete' onclick=\"fenye(1,-2,4,1)\";>\>|</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<a id='last_delete' onclick=\"fenye(1,-3,4,1)\";>>>|</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<select onchange='changeLength(this.value,1);'><option value='5' selected='selected'>-请选择-</option><option value='5'>5</option><option value='10'>10</option><option value='15'>15</option><option value='20'>20</option></select></td><td colspan='4'></td></tr>";
						
			
				var table1 = document.getElementById("showUserInfo");
				while (table1.hasChildNodes()) {
					table1.removeChild(table1.firstChild);
				}
				$(html).prependTo(table1);
			}
			if(number==2){                    //修改用户
				for ( var i = 0; i < staffCode.length; i++) {

					html += "<tr>";
					html += "<td><button name='"+i+"' onclick=\"updateuser(this.name);\">修改</button></td>";
					html += "<td><input type=\"text\" name=\"staffCodeChange\" value='"+
						staffCode[i].firstChild.nodeValue+"' readonly style='color: red;'/></td>";
					html += "<td><input type=\"text\" name=\"userNameChange\" value='"+
						userName[i].firstChild.nodeValue+"'/></td>";
					html += "<td><input type=\"text\" name=\"passwordChange\" value='"+
						password[i].firstChild.nodeValue+"'/></td>";
					html += "<td><input id='authorityChange"+i+"' type=\"text\" name=\"authorityChange\" value='"+
						authority[i].firstChild.nodeValue+"'/><select id=\"authority_selected\" name=\"authoriy_selected\" onchange=\"getChangedAuthority(this.value,'authorityChange"+i+"')\"><option value=\"-1\" selected=\"selected\">--选择权限--</option><option value=\"99999999\">管理员（最高权限）</option><option value=\"00000000\"S>普通用户（无权限）</option></select></td></tr>";
				}

				html += "<tr><td colspan='4'></td>"+
						"<td  colspan='8'><a id='first_delete' onclick=\"fenye(1,-4,4,2)\";>|<<</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "+
						"<a id='previous_delete' onclick=\"fenye(1,-1,4,2)\";>|\<</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<a id='next_delete' onclick=\"fenye(1,-2,4,2)\";>\>|</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<a id='last_delete' onclick=\"fenye(1,-3,4,2)\";>>>|</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<select onchange='changeLength(this.value,2);'><option value='5' selected='selected'>-请选择-</option><option value='5'>5</option><option value='10'>10</option><option value='15'>15</option><option value='20'>20</option></select></td><td colspan='4'></td></tr>";
						

				var table1 = document.getElementById("showUserInfoChange");
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

function updateuser(value){

	var staffCodes = document.getElementsByName("staffcodeChange");
	var userNames = document.getElementsByName("userNameChange");
	var passwords = document.getElementsByName("passwordChange");
	var authoritys = document.getElementsByName("authorityChange");
	
	var staffCode = staffCodes[value].value;
	var userName = userNames[value].value;
	var password = passwords[value].value;
	var authority = authoritys[value].value;

	//encodeURI(encodeURI(userName))
	createXMLHttpRequest();
	var url = "UpdateUser?staffCode=" + staffCode 
		+ "&userName=" + encodeURI(encodeURI(userName))
		+ "&password=" + password 
		+ "&authority=" + authority;

	
	xmlHttp.open("POST", url, true);
	//xmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charset=utf-8"); 
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
		editUserInfo(2);   
	}
}



function deleteUser(staffCode,userName,password,authority,optionvalue){
	if (optionvalue!='-1') {                 //1 表示删除操作，还可以在此添加其他的操作！！！！
		createXMLHttpRequest();
		var url = "DeleteUserInfo?staffCode=" + staffCode 
			+ "&userName=" + encodeURI(encodeURI(userName))
			+ "&password=" + password 
			+ "&authority=" + authority;

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
		editUserInfo(1);
	}
	if (isDeleted[0].firstChild.nodeValue==0) {
		alert("删除失败！！！！");   
	}
}

function showChangePass(){
	document.getElementById("changePass").style.display="";
	document.getElementById("adduser").style.display="none";
	document.getElementById("showUserInfo").style.display="none";
	document.getElementById("showUserInfoChange").style.display="none";
	
}

function showAddUser(){
	document.getElementById("changePass").style.display="none";
	document.getElementById("adduser").style.display="";
	document.getElementById("showUserInfo").style.display="none";
	document.getElementById("showUserInfoChange").style.display="none";
}
</script>
  <body>
  
  
  	<h1 align="center" style="cursor: pointer" onmouseover="this.style.color='#0000ff'" onmouseout="this.style.color='#000000'" onclick="showChangePass()">修改密码</h1>
  	
  	<form id="changePass" action="ChangePasswordServlet" method="post" style="display: none;">
  	  <table class="query_form_table" align="center" width="300px;">
  	    <tr><th>员工号码</th><td><input type="text" name="staffCode" size="32" width="32" readonly style="font-weight: bolder;" value=<%=((User)session.getAttribute("user")).getStaffCode() %>></input></td></tr>
  	    <tr><th>原始密码</th><td><input type="text" name="oldPassword" size="32" width="32"/></td></tr>
  	    <tr><th>输入新密码</th><td><input type="text" name="newPassword" size="32" width="32"/></td></tr>
  	    <tr><th>确认新密码</th><td><input type="text" name="newPassword2" size="32" width="32"/></td></tr>
  	    <tr><td><input type="submit" value="确认修改" name="确认修改"/></td>
	  	    <%if(request.getAttribute("changeOk")!=null){%>
	  		<span style="color: red;"><%=request.getAttribute("changeOk") %></span>
		  	<%
		  	} %>
  	    	<td><input type="reset" value="重新输入" name="重新输入"/></td></tr>
  	  </table>
  	</form>

    <h1 align="center" style="cursor: pointer" onmouseover="this.style.color='#0000ff'" onmouseout="this.style.color='#000000'" onclick="showAddUser()">新增系统用户 </h1>
    <form id="adduser" name = "forsubmit2" action="AddUserServlet" style="display: none;">
     <table  class="query_form_table" align="center">
   		<tr><th>员工编号：</th>
   		    <td><input type="text" id = "staffCode" name="staffCode" maxlength="63" size="47"></input>
   		<span class="red_star">* 请输入1-32个字符</span></td></tr>
   		
   		<tr><th>员工姓名：</th>
   		    <td><input type="text" id = "userName" name="userName" maxlength="63" size="47"></input>
   		<span class="red_star">* 请输入员工证实姓名</span></td></tr> 

   		<tr><th>用户密码：</th>
   		    <td><input type="text" id = "password" name="password" maxlength="63" size="47"></input>
   		<span class="red_star">* 请输入2-20个字符</span></td></tr>
   		
   		<tr><th>用户权限：</th>
   		  <td><input id="authority" name="authority" type="hidden"  maxlength="32" size="32"/>
  		    <select id="authority_select" name="authoriy_select" onchange="getAuthority(this.value)">
  		      <option value="-1" selected="selected">--选择权限--</option>
  		    	<option value="99999999">管理员（最高权限）</option>
  		    	<option value="00000000">普通用户（无权限）</option>
   		    </select>
   		  </td>
   		</tr>
   		<tr><td><input type = "submit" value = "新增用户"></td>
   			<td><input type = "reset" value = "取消"></td></tr>
       </table>
     </form>
        

	<h1 align="center" onclick="editUserInfo(1);" style="cursor: pointer" onmouseover="this.style.color='#0000ff'" onmouseout="this.style.color='#000000'">删除系统用户信息</h1>
      <table id="showUserInfo" class="query_form_table" align="center" width="100%"></table>
       
    <h1 align="center" onclick="editUserInfo(2);" style="cursor: pointer" onmouseover="this.style.color='#0000ff'" onmouseout="this.style.color='#000000'">修改系统用户信息</h1>
      <table id="showUserInfoChange" class="query_form_table" align="center" width="100%"></table>

  </body>
</html>






















