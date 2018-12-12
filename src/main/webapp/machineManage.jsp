<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
	<script type="text/javascript" src="./scripts/jquery.min.js" charset="utf-8" language="utf-8"></script>
	<script type="text/javascript" charset="utf-8" language="utf-8">
		function editMachine(number){
		 	if(number==1){
		 		document.getElementById("addMachine").style.display="none";
				document.getElementById("showMachineInfo").style.display="";
				document.getElementById("showMachineChange").style.display="none";
		 	}
		 	if(number==2){
		 		document.getElementById("addMachine").style.display="none";
				document.getElementById("showMachineInfo").style.display="none";
				document.getElementById("showMachineChange").style.display="";
		 	}
		 	createXMLHttpRequest();
			var url = "EditMachineInfoServlet";
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
			var url = "EditMachineInfoServlet?now="+now+"&will="+will+"&length="+length;
			xmlHttp.open("POST", url, true);
			xmlHttp.onreadystatechange = function() {
				onStateChange(number);
			};
			xmlHttp.send(null);
		}
		
		function changeLength(value,number){
			createXMLHttpRequest();
			var url = "EditMachineInfoServlet?pageLength="+value;
			xmlHttp.open("POST", url, true);
			xmlHttp.onreadystatechange = function() {
				onStateChange(number);
			};
			xmlHttp.send(null);
		}
		
		
		function showSelect(xmlData,number) {
			if (xmlData.documentElement.hasChildNodes()) {
				var MachineInfo = xmlData.getElementsByTagName("MachineInfo");
				var machineid = xmlData.getElementsByTagName("machineid");
				var machinename = xmlData.getElementsByTagName("machinename");
				var machinepower = xmlData.getElementsByTagName("machinepower");
				var machineworker = xmlData.getElementsByTagName("machineworker");

				var html = "<tr><th>编辑</th><th>设备编号</th><th>设备名称</th><th>设备功率</th><th>设备负责人</th></tr>";
				if(number==1){                    //删除用户
					for ( var i = 0; i < machineid.length; i++) {
						html += "<tr id=\""+i+"\"><td>";
						html +="<select onchange=\"deleteUser('"
							+machineid[i].firstChild.nodeValue+"','"
							+machinename[i].firstChild.nodeValue+"',this.value);\"><option value='-1' selected='selected'>请选择</option><option value='"+i+"'>删除</option></select></td>";
						html += "<td>" + machineid[i].firstChild.nodeValue+ "</td>";
						html += "<td>" + machinename[i].firstChild.nodeValue + "</td>";
						html += "<td>" + machinepower[i].firstChild.nodeValue + "</td>";
						html += "<td>" + machineworker[i].firstChild.nodeValue + "</td></tr>";
					}
					
					html += "<tr><td colspan='4'></td>"+
						"<td  colspan='8'><a id='first_delete' onclick=\"fenye(1,-4,4,1)\";>|<<</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "+
						"<a id='previous_delete' onclick=\"fenye(1,-1,4,1)\";>|\<</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<a id='next_delete' onclick=\"fenye(1,-2,4,1)\";>\>|</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<a id='last_delete' onclick=\"fenye(1,-3,4,1)\";>>>|</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<select onchange='changeLength(this.value,1);'><option value='5' selected='selected'>-请选择-</option><option value='5'>5</option><option value='10'>10</option><option value='15'>15</option><option value='20'>20</option></select></td><td colspan='4'></td></tr>";
						
					
					var table1 = document.getElementById("showMachineInfo");
					while (table1.hasChildNodes()) {
						table1.removeChild(table1.firstChild);
					}
					$(html).prependTo(table1);
				}
				
				if(number==2){                    //修改用户
					for ( var i = 0; i < machineid.length; i++) {
						html += "<tr>";
						html += "<td><button name='"+i+"' onclick=\"updateMachine(this.name);\">修改</button></td>";
						html += "<td><input type=\"text\" name=\"machineidChange\" value='"+
							machineid[i].firstChild.nodeValue+"' readonly style='color: red;' onkeyup=\"(this.v=function(){this.value=this.value.replace(/[^0-9]+.?[^0-9]+/,'');}).call(this)\"/></td>";
						html += "<td><input type=\"text\" name=\"machinenameChange\" value='"+
							machinename[i].firstChild.nodeValue+"'/></td>";
						html += "<td><input type=\"text\" name=\"machinepowerChange\" value='"+
							machinepower[i].firstChild.nodeValue+"' onkeyup=\"(this.v=function(){this.value=this.value.replace(/[^0-9.]+/,'');}).call(this)\"/></td>";
						html += "<td><input type=\"text\" id='machineworkerChange"+i+"' name=\"machineworkerChange\" value='"+
							machineworker[i].firstChild.nodeValue+"'/><select id='machineworker"+i+"' name='machineworker' style='width:80px;' onchange=\"changeworker(this.value,'machineworkerChange"+i+"');\"><option value=''>----</option></select></td></tr>";
					}
					
					html += "<tr><td colspan='4'></td>"+
						"<td  colspan='8'><a id='first_delete' onclick=\"fenye(1,-4,4,2)\";>|<<</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; "+
						"<a id='previous_delete' onclick=\"fenye(1,-1,4,2)\";>|\<</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<a id='next_delete' onclick=\"fenye(1,-2,4,2)\";>\>|</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<a id='last_delete' onclick=\"fenye(1,-3,4,2)\";>>>|</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;"+
						"<select onchange='changeLength(this.value,2);'><option value='5' selected='selected'>-请选择-</option><option value='5'>5</option><option value='10'>10</option><option value='15'>15</option><option value='20'>20</option></select></td><td colspan='4'></td></tr>";
				
					
					var table1 = document.getElementById("showMachineChange");
					while (table1.hasChildNodes()) {
						table1.removeChild(table1.firstChild);
					}
					$(html).prependTo(table1);
					
					//alert();
					var names = xmlData.getElementsByTagName("machineworkers");
					var ids = xmlData.getElementsByTagName("machineworkerid");
					for ( var i = 0; i < machineid.length; i++) {
						oElement = document.getElementById("machineworker"+i);					
						oElement.options[0].innerHTML = "--------";
						for ( var j = 0; j < names.length; j++) {
							var op = new Option(names[j].firstChild.nodeValue);
							oElement.options.add(op);
							op.value = ids[j].firstChild.nodeValue;
						}
					}
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
		
		function changeworker(value,textid){
			var text = document.getElementById(textid);
			text.value = value;
		}
		
		function initSelect(oElement) {
			while (oElement.options.length > 0) {
				oElement.remove(oElement.options.length - 1);
			}
			var op = new Option("数据加载中...");
			oElement.options.add(op);
			op.value = "";
		}
		
		function updateMachine(value){
		
			var machineids = document.getElementsByName("machineidChange");
			var machinenames = document.getElementsByName("machinenameChange");
			var machinepowers = document.getElementsByName("machinepowerChange");
			var machineworkers = document.getElementsByName("machineworkerChange");

			var machineid = machineids[value].value;
			var machinename = machinenames[value].value;
			var machinepower = machinepowers[value].value;
			var machineworker = machineworkers[value].value;
			
			createXMLHttpRequest();
			var url =  "UpdateMachine?machineid=" + machineid + "&machinename=" + encodeURI(encodeURI(machinename))
			+ "&machinepower=" + machinepower+ "&machineworker=" + machineworker;
		
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

		function deleteUser(machineid,machinename,optionvalue){
			if (optionvalue!='-1') {                 //1 表示删除操作，还可以在此添加其他的操作！！！！
				createXMLHttpRequest();
				//此处有中文乱码问题，有待解决！！！！
				var url = "DeleteMachineInfo?machineid=" + machineid + "&machinename=" +encodeURI(machinename);
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
		
		function showAddMachine(){
			document.getElementById("addMachine").style.display="";
			document.getElementById("showMachineInfo").style.display="none";
			document.getElementById("showMachineChange").style.display="none";			
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
				//var patrn=/^(([0-9]+\\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
				//var patrn =/[^0-9]+/;
				//if(patrn.exec(doc.value)){
					//alert("输入的功率不是小数！");
				//}
				var patrn=/^\d+(\d|(\.[1-9]{1,6}))$/; 
				if (!patrn.exec(doc.value)) alert("您输入的不是小数！"); 
			}
		}
	</script>

  </head>
  
  <body>

    <h1 align="center" style="cursor: pointer" onmouseover="this.style.color='#0000ff'" onmouseout="this.style.color='#000000'" onclick="showAddMachine()">新加机床</h1>
    <form id="addMachine" action="AddMachineServlet">
      <table  class="query_form_table" align="center">
   		<tr><th>机床编号：</th>
   		    <td><input type="text" id = "machineID" name="machineID" maxlength="63" size="47" 
   		      onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9]+/,'');}).call(this)" 
   		       onblur="checkinput('machineID');"></input>
   		<span class="red_star">* 请输入2-32位数字</span></td></tr>
   		
   		<tr><th>机床名称：</th>
   		    <td><input type="text" id = "machineName" name="machineName" maxlength="63" size="47" onblur="checkinput('machineName')"></input>
   		<span class="red_star">* 请输入1-32个字符</span></td></tr> 
   		<tr><th>机床型号</th>
   			<td><input type="text" id="machinespec" name="machinespec" maxlength="63" size="47"/>
   			<span class="red_star">* 请输入设备型号</span></td>
   		</tr>
   		<tr><th>机床功率：</th>
   		    <td><input type="text" id = "machinePower" name="machinePower" maxlength="63" size="47" onblur="checkinput('machinePower')"
   		    onkeyup="(this.v=function(){this.value=this.value.replace(/[^0-9]+.?[^0-9]+/,'');}).call(this)"></input>
   		<span class="red_star">* 单位：d千瓦</span></td></tr> 
   		<tr><td><input type = "submit" value = "添加机床"></td>
			<%if(request.getAttribute("addOk")!=null){%>
	  		<span style="color: red;"><%=request.getAttribute("addOk") %></span>
		  	<%
		  	} %>
   			<td><input type = "reset" value = "取消添加"></td></tr>
       </table>
     </form>
        

	<h1 align="center" onclick="editMachine(1)" style="cursor: pointer" onmouseover="this.style.color='#0000ff'" onmouseout="this.style.color='#000000'">删除机床</h1>
      <table id="showMachineInfo" class="query_form_table" align="center" width="100%"></table>
       
    <h1 align="center" onclick="editMachine(2)" style="cursor: pointer" onmouseover="this.style.color='#0000ff'" onmouseout="this.style.color='#000000'">修改机床信息</h1>
      <table id="showMachineChange" class="query_form_table" align="center" width="100%"></table>

  </body>
</html>
