<%@ page language="java" import="java.util.*" pageEncoding="utf-8" contentType="text/html; charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>My JSP 'machineManage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link href="./css/person1.css" type=text/css rel=stylesheet>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	
	<script type="text/javascript" src="./js/wlcore.js"></script>
	<script type="text/javascript" src="./js/wlcalendar.js"></script>
	 
	 <!-- 
	<style type="text/css">

	tr {background-color:expression((this.sectionRowIndex%2==0)?"#E1F1F1":"#F0F0F0")}
	table { background-color:#000000; cursor:hand; width:100%; }
	td {
		onmouseover: expression(onmouseover=function (){this.style.borderColor ='blue';this.style.color='red';this.style.backgroundColor ='yellow'}); 
		onmouseout: expression(onmouseout=function (){this.style.borderColor='';this.style.color='';this.style.backgroundColor =''});
		background-color:#ffffff;
	}
	
	</style>
	  -->

	<script type="text/javascript" src="scripts/jquery-1.8.0.js"></script>
	<script type="text/javascript" charset="utf-8" language="utf-8">

	function editEmployee(number){
	
	 	if(number==1){
	 		document.getElementById("addEmployee").style.display="none";
			document.getElementById("showEmployeeInfo").style.display="";
			document.getElementById("showEmployeeChange").style.display="none";	
	 	}
	 	if(number==2){
	 		document.getElementById("addEmployee").style.display="none";
			document.getElementById("showEmployeeInfo").style.display="none";
			document.getElementById("showEmployeeChange").style.display="";	
	 	}
	 	createXMLHttpRequest();
		var url = "EditEmployeeInfoServlet";
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
		var url = "EditEmployeeInfoServlet?now="+now+"&will="+will+"&length="+length;
		xmlHttp.open("POST", url, true);
		xmlHttp.onreadystatechange = function() {
			onStateChange(number);
		};
		xmlHttp.send(null);
	}
	
	function changeLength(value,number){
		createXMLHttpRequest();
		var url = "EditEmployeeInfoServlet?pageLength="+value;
		xmlHttp.open("POST", url, true);
		xmlHttp.onreadystatechange = function() {
			onStateChange(number);
		};
		xmlHttp.send(null);
	}
	
	function showSelect(xmlData,number) {
		if (xmlData.documentElement.hasChildNodes()) {
			var EmployeeInfo = xmlData.getElementsByTagName("EmployeeInfo");
			
			var EmployeeID = xmlData.getElementsByTagName("EmployeeID");
			var EmployeeName = xmlData.getElementsByTagName("EmployeeName");
			var deptid = xmlData.getElementsByTagName("deptid");
			var gender = xmlData.getElementsByTagName("gender");
			var schoolFrom = xmlData.getElementsByTagName("schoolFrom");
			var ADDRESS = xmlData.getElementsByTagName("ADDRESS");
			var techGrade = xmlData.getElementsByTagName("techGrade");
			
			var BIRTHDAY = xmlData.getElementsByTagName("BIRTHDAY");
			var EDUCATION_LEVEL = xmlData.getElementsByTagName("EDUCATION_LEVEL");
			var SPECIALITY = xmlData.getElementsByTagName("SPECIALITY");
			var WORK_TYPE = xmlData.getElementsByTagName("WORK_TYPE");
			var OFFICE_PHNE = xmlData.getElementsByTagName("OFFICE_PHNE");
			var MOBILE_PHONE = xmlData.getElementsByTagName("MOBILE_PHONE");
			var HOME_PHONE = xmlData.getElementsByTagName("HOME_PHONE");
			var POSITION = xmlData.getElementsByTagName("POSITION");
			var WORKTIME = xmlData.getElementsByTagName("WORKTIME");
			var QQ = xmlData.getElementsByTagName("QQ");
			var EMAIL = xmlData.getElementsByTagName("EMAIL");
			var RFID_CODE = xmlData.getElementsByTagName("RFID_CODE");

			if(number==1){                    //删除用户
			
				var html = "<tr><th>编辑</th><th>员工编号</th><th>员工名称</th><th>部门号</th><th>性别</th><th>毕业院校</th><th>地址</th><th>技术等级</th></tr>";
				for ( var i = 0; i < EmployeeID.length; i++) {
					html += "<tr id=\""+i+"\"><td>";
					html +="<select onchange=\"deleteEmployee('"
						+EmployeeID[i].firstChild.nodeValue+"','"
						+EmployeeName[i].firstChild.nodeValue+"',this.value);\"><option value='-1' selected='selected'>请选择</option><option value='"+i+"'>删除</option></select></td>";
					html += "<td>" + EmployeeID[i].firstChild.nodeValue+ "</td>";
					html += "<td>" + EmployeeName[i].firstChild.nodeValue + "</td>";
					html += "<td>" + deptid[i].firstChild.nodeValue + "</td>";
					html += "<td>" + gender[i].firstChild.nodeValue + "</td>";
					html += "<td>" + schoolFrom[i].firstChild.nodeValue + "</td>";
					html += "<td>" + ADDRESS[i].firstChild.nodeValue + "</td>";
					html += "<td>" + techGrade[i].firstChild.nodeValue + "</td></tr>";
				}
				html += "<tr><td colspan='3'></td>"+
				"<td  colspan='2'><a id='first_delete' onclick=\"fenye(1,-4,4,1)\";>|<<</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "+
				"<a id='previous_delete' onclick=\"fenye(1,-1,4,1)\";>|\<</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
				"<a id='next_delete' onclick=\"fenye(1,-2,4,1)\";>\>|</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
				"<a id='last_delete' onclick=\"fenye(1,-3,4,1)\";>>>|</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
				"<select onchange='changeLength(this.value);'><option value='5' selected='selected'>-请选择-</option><option value='5'>5</option><option value='10'>10</option><option value='15'>15</option><option value='20'>20</option></select></td><td colspan='3'></td></tr>";
				var table1 = document.getElementById("showEmployeeInfo");
				while (table1.hasChildNodes()) {
					table1.removeChild(table1.firstChild);
				}
				$(html).prependTo(table1);
			}
			if(number==2){                    //修改用户
				var html = "<tr><th>编辑</th><th>员工编号</th><th>员工名称</th><th>部门号</th>"+
				"<th>性别</th><th>毕业院校</th><th>地址</th><th>技术等级</th><th>生日</th>"+
				"<th>教育程度</th><th>特长爱好</th><th>工种</th><th>办公电话</th>"+
				"<th>手机号码</th><th>家庭电话</th><th>职位</th><th>工作年限</th><th>QQ</th>"+
				"<th>邮箱</th><th>RFID号码</th></tr>";
				for ( var i = 0; i < EmployeeID.length; i++) {
					html += "<tr>";
					html += "<td><button name='"+i+"' onclick=\"updateEmployee(this.name);\">修改</button></td>";
					html += "<td><input type=\"text\" name=\"EmployeeIDChange\" value='"+
						EmployeeID[i].firstChild.nodeValue+"' readonly style='color: red;'size='10' /></td>";
					html += "<td><input type=\"text\" name=\"EmployeeNameChange\" value='"+
						EmployeeName[i].firstChild.nodeValue+"' size='8'/></td>";
					html += "<td><input type=\"text\" name=\"deptidChange\" value='"+
						deptid[i].firstChild.nodeValue+"' size='6'/></td>";
					html += "<td><input type=\"text\" name=\"genderChange\" value='"+
						gender[i].firstChild.nodeValue+"'  readonly style='color: green;' size='4'/></td>";
					html += "<td><input type=\"text\" name=\"schoolFromChange\" value='"+
						schoolFrom[i].firstChild.nodeValue+"' size='12'/></td>";
					html += "<td><input type=\"text\" name=\"ADDRESSChange\" value='"+
						ADDRESS[i].firstChild.nodeValue+"' size='20'/></td>";
					html += "<td><input type=\"text\" name=\"techGradeChange\" value='"+
						techGrade[i].firstChild.nodeValue+"' size='7'/></td>";
					html += "<td><input type=\"text\" name=\"BIRTHDAYChange\" value='"+
						BIRTHDAY[i].firstChild.nodeValue+"' readonly style='color: green;' size='9'/></td>";
					html += "<td><input type=\"text\" name=\"EDUCATION_LEVELChange\" value='"+
						EDUCATION_LEVEL[i].firstChild.nodeValue+"' size='8'/></td>";
					html += "<td><input type=\"text\" name=\"SPECIALITYChange\" value='"+
						SPECIALITY[i].firstChild.nodeValue+"' size='14'/></td>";
					html += "<td><input type=\"text\" name=\"WORK_TYPEChange\" value='"+
						WORK_TYPE[i].firstChild.nodeValue+"' size='6'/></td>";
					html += "<td><input id='OFFICE_PHNEChange"+i+"' type=\"text\" name=\"OFFICE_PHNEChange\" value='"+
						OFFICE_PHNE[i].firstChild.nodeValue+"' size='14' onkeyup=\"(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)\" onblur=\"checkinputUser('OFFICE_PHNEChange"+i+"');\"/></td>";
					html += "<td><input id='MOBILE_PHONEChange"+i+"' type=\"text\" name=\"MOBILE_PHONEChange\" value='"+
						MOBILE_PHONE[i].firstChild.nodeValue+"' size='14'  onkeyup=\"(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)\" onblur=\"checkinputUser('MOBILE_PHONEChange"+i+"');\"/></td>";
					html += "<td><input id='HOME_PHONEChange"+i+"' type=\"text\" name=\"HOME_PHONEChange\" value='"+
						HOME_PHONE[i].firstChild.nodeValue+"' size='14' onkeyup=\"(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)\" onblur=\"checkinputUser('HOME_PHONEChange"+i+"');\"/></td>";
					html += "<td><input type=\"text\" name=\"POSITIONChange\" value='"+
						POSITION[i].firstChild.nodeValue+"' size='10'/></td>";
					html += "<td><input id='WORKTIMEChange"+i+"' type=\"text\" name=\"WORKTIMEChange\" value='"+
						WORKTIME[i].firstChild.nodeValue+"' size='7' onkeyup=\"(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)\" onblur=\"checkinputUser('WORKTIMEChange"+i+"');\"/></td>";
					html += "<td><input id='QQChange"+i+"' type=\"text\" name=\"QQChange\" value='"+
						QQ[i].firstChild.nodeValue+"' size='10'  onblur=\"checkinputUser('QQChange"+i+"');\" Sonkeyup=\"(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)\"/></td>";
					html += "<td><input id='EMAILChange"+i+"' type=\"text\" name=\"EMAILChange\" value='"+
						EMAIL[i].firstChild.nodeValue+"' size='14'  onblur=\"checkinputUser('EMAILChange"+i+"');\"/></td>";
					html += "<td><input type=\"text\" name=\"RFID_CODEChange\" value='"+
						RFID_CODE[i].firstChild.nodeValue+"' size='10'/></td></tr>";
				}
				
				html += "<tr><td colspan='4'></td>"+
				"<td  colspan='8'><a id='first_delete' onclick=\"fenye(1,-4,4,2)\";>|<<</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "+
				"<a id='previous_delete' onclick=\"fenye(1,-1,4,2)\";>|\<</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
				"<a id='next_delete' onclick=\"fenye(1,-2,4,2)\";>\>|</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
				"<a id='last_delete' onclick=\"fenye(1,-3,4,2)\";>>>|</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
				"<select onchange='changeLength(this.value,2);'><option value='5' selected='selected'>-请选择-</option><option value='5'>5</option><option value='10'>10</option><option value='15'>15</option><option value='20'>20</option></select></td><td colspan='4'></td></tr>";
				
				
				var table1 = document.getElementById("showEmployeeChange");
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
	
	function updateEmployee(value){
	
		var EmployeeIDChange = document.getElementsByName("EmployeeIDChange");
		var EmployeeNameChange = document.getElementsByName("EmployeeNameChange");
		var deptidChange = document.getElementsByName("deptidChange");
		var genderChange = document.getElementsByName("genderChange");
		var schoolFromChange = document.getElementsByName("schoolFromChange");
		var ADDRESSChange = document.getElementsByName("ADDRESSChange");
		var techGradeChange = document.getElementsByName("techGradeChange");
		var BIRTHDAYChange = document.getElementsByName("BIRTHDAYChange");
		var EDUCATION_LEVELChange = document.getElementsByName("EDUCATION_LEVELChange");
		var SPECIALITYChange = document.getElementsByName("SPECIALITYChange");
		var WORK_TYPEChange = document.getElementsByName("WORK_TYPEChange");
		var OFFICE_PHNEChange = document.getElementsByName("OFFICE_PHNEChange");
		var MOBILE_PHONEChange = document.getElementsByName("MOBILE_PHONEChange");
		var HOME_PHONEChange = document.getElementsByName("HOME_PHONEChange");
		var POSITIONChange = document.getElementsByName("POSITIONChange");
		var WORKTIMEChange = document.getElementsByName("WORKTIMEChange");
		var QQChange = document.getElementsByName("QQChange");
		var EMAILChange = document.getElementsByName("EMAILChange");
		var RFID_CODEChange = document.getElementsByName("RFID_CODEChange");

		var EmployeeID = EmployeeIDChange[value].value;
		var EmployeeName = EmployeeNameChange[value].value;
		var deptid = deptidChange[value].value;
		var gender = genderChange[value].value;
		var schoolFrom = schoolFromChange[value].value;
		var ADDRESS = ADDRESSChange[value].value;
		var techGrade = techGradeChange[value].value;
		var BIRTHDAY = BIRTHDAYChange[value].value;
		var EDUCATION_LEVEL = EDUCATION_LEVELChange[value].value;
		var SPECIALITY = SPECIALITYChange[value].value;
		var WORK_TYPE = WORK_TYPEChange[value].value;
		var OFFICE_PHNE = OFFICE_PHNEChange[value].value;
		var MOBILE_PHONE = MOBILE_PHONEChange[value].value;
		var HOME_PHONE = HOME_PHONEChange[value].value;
		var POSITION = POSITIONChange[value].value;
		var WORKTIME = WORKTIMEChange[value].value;
		var QQ = QQChange[value].value;
		var EMAIL = EMAILChange[value].value;
		var RFID_CODE = RFID_CODEChange[value].value;
		
		
		createXMLHttpRequest();
		var url =  "UpdateEmployee?EmployeeID=" + EmployeeID 
		+ "&EmployeeName="+encodeURI(encodeURI(EmployeeName))
		+ "&deptid=" +encodeURI(encodeURI(deptid))
		+ "&gender="+encodeURI(encodeURI(gender))
		+ "&schoolFrom=" +encodeURI(encodeURI(schoolFrom))
		+ "&ADDRESS=" +encodeURI(encodeURI(ADDRESS))
		+ "&techGrade=" +encodeURI(encodeURI(techGrade))
		+ "&BIRTHDAY=" +BIRTHDAY
		+ "&EDUCATION_LEVEL=" +  encodeURI(encodeURI(EDUCATION_LEVEL))
		+ "&SPECIALITY=" +  encodeURI(encodeURI(SPECIALITY))
		+ "&WORK_TYPE=" +  encodeURI(encodeURI(WORK_TYPE))
		+ "&OFFICE_PHNE=" + OFFICE_PHNE
		+ "&MOBILE_PHONE=" + MOBILE_PHONE
		+ "&HOME_PHONE=" + HOME_PHONE
		+ "&POSITION=" +  encodeURI(encodeURI(POSITION))
		+ "&WORKTIME=" + WORKTIME
		+ "&QQ=" + QQ
		+ "&EMAIL=" + EMAIL
		+ "&RFID_CODE=" + encodeURI(encodeURI(RFID_CODE));
		
		//var url = encodeURIComponent(encodeURIComponent(url1)));	
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
			editEmployee(2);   
		}
	}

	function deleteEmployee(EmployeeID,EmployeeName,optionvalue){
		if (optionvalue!='-1') {                 //1 表示删除操作，还可以在此添加其他的操作！！！！
			createXMLHttpRequest();
			//此处有中文乱码问题，有待解决！！！！
			var url = "DeleteEmployeeInfo?EmployeeID=" + EmployeeID + "&EmployeeName=" +encodeURI(EmployeeName);
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
			editEmployee(1);
		}
		if (isDeleted[0].firstChild.nodeValue==0) {
			alert("删除失败！！！！");   
		}
	}
	
	function showAddEmployee(){
		document.getElementById("addEmployee").style.display="";
		document.getElementById("showEmployeeInfo").style.display="none";
		document.getElementById("showEmployeeChange").style.display="none";			
	}
	
	function checkinputUser(textid){
		var doc = document.getElementById(textid);
		if(textid=="email"){
			var patrn = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
			if(!patrn.exec(doc.value)){
				alert("您所输入的邮箱格式不正确！！");
			}
		}
		if(textid=="QQ"){
			if(doc.value.length>10 || doc.value.length<7){
				alert("您所输入的QQ账号有误！！");
			}
		}
		if(textid=="workTime"){
			if(doc.value.length>2){
				alert("您所输入的年数太大！！");
			}
		}
		if(textid=="mobilePhone"){
			var patrn = /^1\d{10}$/;
			if(!patrn.exec(doc.value)){
				alert("请输入11位手机号码！！");
			}
		}
		if(textid=="homePhone"){
			var patrn = /^0\d{2,3}-?\d{7,8}$/;
			if(!patrn.exec(doc.value)){
				alert("请输入正确手机号码！！");
			}
		}
		if(textid=="officephone"){
			var patrn = /^0\d{2,3}-?\d{7,8}$/;
			if(!patrn.exec(doc.value)){
				alert("请输入正确手机号码！！");
			}
		}
	}
	</script>

  </head>
  
  <body>

    <h1 align="center" style="cursor: pointer" onmouseover="this.style.color='#0000ff'" onmouseout="this.style.color='#000000'" onclick="showAddEmployee()">添加新员工</h1>
    <form id="addEmployee" action="AddEmployeeServlet">
      <table id="addUser"  class="query_form_table" align="center">
   		<tr><th>员工编号：</th>
   		
   		    <td><input type="text" id = "EmployeeID" name="EmployeeID" maxlength="63" size="47"></input></td>
   		    <th>员工名称：</th>
   		    <td><input type="text" id = "EmployeeName" name="EmployeeName" maxlength="63" size="47"></input></td><th>所属部门：</th>
   		    <td><input type="text" id = "deptID" name="deptID" maxlength="63" size="47"></input></td></tr> 
   		<tr><th>性别：</th>
   		    <td><input type="radio" value='男' id = "gender" name="gender" maxlength="63" size="20" checked="Schecked"/>男
   		    	<input type="radio" value='女' id = "gender" name="gender" maxlength="63" size="20"/>女
   		    	</td>
   		    <th>出生日期：</th>
   		    <td><input type="text" id = "birthday" name="birthday" maxlength="63" size="47"  onclick="J.calendar.get({time:false});"></input></td><th>学历水平：</th>
   		    <td><input type="text" id = "educationLevel" name="educationLevel" maxlength="63" size="47"></input></td></tr>   		
   		<tr><th>毕业院校：</th>
   		    <td><input type="text" id = "schoolFrom" name="schoolFrom" maxlength="63" size="47"></input></td><th>特长爱好：</th>
   		    <td><input type="text" id = "speciality" name="speciality" maxlength="63" size="47"></input></td><th>工种：</th>
   		    <td><input type="text" id = "workType" name="workType" maxlength="63" size="47"></input></td></tr> 
   		<tr><th>技术等级：</th>
   		    <td><input type="text" id = "technicalGrade" name="technicalGrade" maxlength="63" size="47"></input></td><th>地址：</th>
   		    <td><input type="text" id = "address" name="address" maxlength="63" size="47"></input></td><th>办公室电话：</th>
   		    <td><input type="text" id = "officephone" name="officephone" maxlength="63" size="47" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)" onblur="checkinputUser('officephone');"></input></td></tr> 
   		<tr><th>手机号码：</th>
   		    <td><input type="text" id = "mobilePhone" name="mobilePhone" maxlength="63" size="47" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)" onblur="checkinputUser('mobilePhone');"></input></td><th>家庭电话：</th>
   		    <td><input type="text" id = "homePhone" name="homePhone" maxlength="63" size="47" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)" onblur="checkinputUser('homePhone');"></input></td><th>职位：</th>
   		    <td><input type="text" id = "position" name="position" maxlength="63" size="47"></input></td></tr> 
   		<tr><th>工作年限：</th>
   		    <td><input type="text" id = "workTime" name="workTime" maxlength="63" size="47" onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)" onblur="checkinputUser('workTime');"></input></td><th>RFID号码：</th>
   		    <td><input type="text" id = "rfidCode" name="rfidCode" maxlength="63" size="47"></input></td><th>QQ：</th>
   		    <td><input type="text" id = "QQ" name="QQ" maxlength="63" size="47" onblur="checkinputUser('QQ');"
   		      onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)" ></input></td></tr> 
   		<tr><th>邮箱：</th>
   		    <td><input type="text" id = "email" name="email" maxlength="63" size="47" onblur="checkinputUser('email');"/></td>
   		    <td><input type = "submit" value = "添加员工"></td>
			<%if(request.getAttribute("addOk")!=null){%>
	  		<span style="color: red;"><%=request.getAttribute("addOk") %></span>
		  	<%
		  	} %>
   			<td><input type = "reset" value = "取消添加"></td></tr>
       </table>
     </form>
        

	<h1 align="center" onclick="editEmployee(1)" style="cursor: pointer" onmouseover="this.style.color='#0000ff'" onmouseout="this.style.color='#000000'">删除员工信息</h1>
      <table id="showEmployeeInfo" class="query_form_table" align="center" width="100%"></table>
       
    <h1 align="center" onclick="editEmployee(2)" style="cursor: pointer" onmouseover="this.style.color='#0000ff'" onmouseout="this.style.color='#000000'">修改员工信息</h1>
      <table id="showEmployeeChange" class="query_form_table" align="center" width="100%"></table>

  </body>
  
</html>
