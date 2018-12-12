<%@ page language="java" contentType="text/html; charset=utf-8" import="java.util.*" pageEncoding="utf-8"%>
<jsp:useBean id="dispatchBean" scope="page" class="com.wl.forms.DispatchBean"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>派工页面</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<link rel="stylesheet" href="./css/person1.css" type="text/css"></link>
	<script type="text/javascript" src="./scripts/jquery.min.js" charset="utf-8" language="utf-8"></script>
    <script type="text/javascript" src="./js/Dispatch_Select.js" charset="utf-8" language="utf-8"></script> 
    <script type="text/javascript" src="./js/wlcore.js"></script>
	<script type="text/javascript" src="./js/wlcalendar.js"></script>
	
	
	<!-- 
	<link rel="icon" href="<%=basePath%>rili/favicon.ico" type="image/x-icon"/>
	<link rel="shortcut icon" href="<%=basePath%>rili/favicon.ico" type="image/x-icon"/>
	 -->
	<link href="<%=basePath%>rili/index.css" type="text/css" rel="stylesheet"/>
	<link href="<%=basePath%>rili/prettify/prettify.css" type="text/css" rel="stylesheet"/>
	<script type="text/javascript" src="<%=basePath%>rili/prettify/prettify.js"></script>
	<script type="text/javascript" src="<%=basePath%>rili/lhgcore.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>rili/lhgcalendar.min.js"></script>
	<script type="text/javascript">
		J(function(){
			J('#start_time').calendar({ format:'yyyy-MM-dd' });
			J('#end_time').calendar({ format:'yyyy-MM-dd' });
			
			J('#dispatch_starttime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });
			J('#dispatch_endtime').calendar({ format:'yyyy-MM-dd HH:mm:ss' });	
		});
	</script>
  </head>
 
  <body style="width: 960px;margin: 0 auto;">
      <div class="page_title" style="float: left; border-right-width: 50px;" >任务选择</div>
      <div><a href="GT/demo/wlmachinegandt.html">&nbsp;设备甘特图</a></div>
    <form name="dispatchForm" class="zipcode" action="DispatchFinish" method=post>
    <table class="query_form_table" align="center" width="85%" >
         
         <tr>
         	<th>订单号：</th>
		    <td>
		    	<input type="text" id="orderid" name="orderid" style="width:300px;" readonly="readonly" value="<%=request.getAttribute("orderId") %>"/>
			</td>
          
            <th>零件号：</th>
		    <td>
		    	<input type="text" id="item_id" name="item_id" style="width:300px;" readonly="readonly" value="<%=request.getAttribute("itemId") %>"/>
		    </td>
		 </tr>
         <tr>
            <th>工序号：</th>
		    <td>
		    	<input type="text" id="oper_id" name="oper_id" style="width:300px;" dreadonly="readonly" value="<%=request.getAttribute("operId") %>"/>
		    </td>
		    <th>选择员工：</th>
		    <td >
		    	<select id="workerid" name="workerid" style="width:300px;">
		    	
		    	<% 	ArrayList<Map<String,String>> worker = (ArrayList<Map<String,String>>)request.getAttribute("worker");
		    		if(worker != null){
		    	%>
		    			<option value="">--请选择--</option>
		    	<%
		    			for(int i=0;i<worker.size();i++){
		    				
		    	%>
		    				<option value="<%=worker.get(i).get("staff_code") %>"><%=worker.get(i).get("staff_name") %>[<%=worker.get(i).get("staff_code") %>_<%=worker.get(i).get("dept_id") %>]</option>
		    	<%			
		    			}
		    			
		    		}else{
		    	%>
		    			<option value="">--暂无数据--</option>	
		    	<%
		    		}
		    	%>
            	</select>
            </td>
         </tr>  
         <tr>
         	<th>开始时间</th>
         	<td>
         	    <input type="text" id="dispatch_starttime" name="dispatch_starttime" style="width:300px;"
         	    onclick="J.calendar.get({time:true});"  value="<%=request.getAttribute("startTime") %>"/>
         	</td>
         	
         	<th>选择机床：</th>
		    <td >
		    	<select id="machineid" name="machineid" style="width:300px;">
            		<% 	ArrayList<Map<String,String>> machine = (ArrayList<Map<String,String>>)request.getAttribute("machine");
		    		if(machine != null){
		    	%>
		    			<option value="">--请选择--</option>
		    	<%
		    			for(int i=0;i<machine.size();i++){
		    				
		    	%>
		    				<option value="<%=machine.get(i).get("machineid") %>"><%=machine.get(i).get("machinename") %>[<%=machine.get(i).get("machineid") %>_<%=machine.get(i).get("machinespec") %>]</option>
		    	<%			
		    			}
		    			
		    		}else{
		    	%>
		    			<option value="">--暂无数据--</option>	
		    	<%
		    		}
		    	%>
            	</select>
            </td>
         	
         </tr>
         
    	 <tr>
    		<th>结束时间</th>
         	<td>
         	    <input type="text" id="dispatch_endtime" name="dispatch_endtime" style="width:300px;"
         	    onclick="J.calendar.get({time:true});"  value="<%=request.getAttribute("endTime") %>"/>
         	    <input type="text" id="type" name="type" value="machineid" style="display: none;"/>
         	</td>
        	<th>
        		<input type="submit" value="派工" name="派工" style="width: 55px;" >
        	</th>
        	
          </tr>        
        </table>   
    </form>
    <table id="showdispatched" class="query_form_table" align="center" width="100%">
    </table>
  </body>
  
  <script type="text/javascript">
	function getWorker(){
		$.ajax({   
		    url:"<%=basePath%>Dispatch_Select",
		    //async: false,
			type: "post",
			//dataType: "json", //返回的数据类型  
			success:callback //请求成功处理函数  
	  	}); 
	}

  	function callback(data){
		alert(data);
  	}











  
  var xmlHttp;
  var orderid = "";
  var equipment_drawid = "";
  var issue_num = "";
  var product_id = "";
  var item_id = "";
  var oper_id = "";
  var machineid = "";
  var start_time="";
  var ent_time="";
  var dispatch_starttime = "";
  var dispatch_endtime = "";

  function createXMLHttpRequest() {
  	if (window.XMLHttpRequest) {
  		xmlHttp = new XMLHttpRequest();
  	} else if (window.ActiveXObject) {
  		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
  	}
  }

  function addSelect(type, sid, elementID) {

  	// 不用var声明变量，并将其最先执行，该变量就具有了全局性
  	oElement = document.getElementById(elementID);
  	firstchar = document.getElementById(type);
  	
  	
  	if (elementID == "orderid") {
  		s_item_id.options[0].innerHTML = "--------";
  		s_oper_id.options[0].innerHTML = "--------";
  		s_machineid.options[0].innerHTML = "--------";
  		s_workerid.options[0].innerHTML = "--------";
  	}
  	
  	
  	
  	if (elementID == "issue_num") {
  		// initSelect(s_product_id);
  		initSelect(s_item_id);
  		initSelect(s_oper_id);
  		initSelect(s_machineid);
  		s_item_id.options[0].innerHTML = "--------";
  		s_oper_id.options[0].innerHTML = "--------";
  		s_workerid.options[0].innerHTML = "--------";
  		s_machineid.options[0].innerHTML = "--------";
  	}

  	if (elementID == "item_id") {
  		initSelect(s_oper_id);
  		initSelect(s_machineid);
  		s_oper_id.options[0].innerHTML = "--------";
  		s_workerid.options[0].innerHTML = "--------";
  		s_machineid.options[0].innerHTML = "--------";
  	}
  
  	if (sid == "") {
  		oElement.options[0].innerHTML = "--------";
  	} else {
  		createXMLHttpRequest();
  		var url ="Dispatch_Select?type=" + type + "&orderid=" + orderid
  		// +"&equipment_drawid="+equipment_drawid
  				+"&issue_num=" + issue_num + "&item_id=" + item_id
  				// +"&product_id="+product_id
  				+"&oper_id=" + oper_id
  				+"&start_time="+start_time
  				+"&end_time="+end_time;
  		
  		xmlHttp.open("POST", url, true);
  		xmlHttp.onreadystatechange = function() {
  			onStateChange()
  		};
  		xmlHttp.send(null);
  	}
  }

  function onStateChange() {
  	if (xmlHttp.readyState == 4) {
  		if (xmlHttp.status == 200) {
  			showSelect(xmlHttp.responseXML);
  		}
  	}
  }


  function showSelect(xmlData) {

  	if (xmlData.documentElement.hasChildNodes()) {
  		oElement.options[0].innerHTML = "--请选择--";
  		
  		if (oElement == document.getElementById("orderid")) {
  			
  			var names = xmlData.getElementsByTagName("order_id");
  			var ids = xmlData.getElementsByTagName("order_id");
  			for ( var i = 0; i < names.length; i++) {
  				var op = new Option(names[i].firstChild.nodeValue);
  				oElement.options.add(op);
  				op.value = ids[i].firstChild.nodeValue;
  			}
  		}
  		
  		
  		
  		
  		if (oElement == document.getElementById("issue_num")) {
  			var names = xmlData.getElementsByTagName("Name_issue_num");
  			var ids = xmlData.getElementsByTagName("ID_issue_num");
  			for ( var i = 0; i < names.length; i++) {
  				var op = new Option(names[i].firstChild.nodeValue);
  				oElement.options.add(op);
  				op.value = ids[i].firstChild.nodeValue;
  			}
  		}
  		if (oElement == document.getElementById("item_id")) {
  			var names = xmlData.getElementsByTagName("Name_item_id");
  			var ids = xmlData.getElementsByTagName("ID_item_id");
  			for ( var i = 0; i < names.length; i++) {
  				var op = new Option(names[i].firstChild.nodeValue);
  				oElement.options.add(op);
  				op.value = ids[i].firstChild.nodeValue;
  			}
  		}
  		if (oElement == document.getElementById("oper_id")) {
  			var names = xmlData.getElementsByTagName("Name_oper_id");
  			var ids = xmlData.getElementsByTagName("ID_oper_id");
  			for ( var i = 0; i < names.length; i++) {
  				var op = new Option(names[i].firstChild.nodeValue);
  				oElement.options.add(op);
  				op.value = ids[i].firstChild.nodeValue;
  			}
  		}
  		
  		if (oElement == document.getElementById("workerid")) {

  			var names = xmlData.getElementsByTagName("Name_workerid");
  			var ids = xmlData.getElementsByTagName("ID_workerid");
  			for ( var i = 0; i < names.length; i++) {
  				var op = new Option(names[i].firstChild.nodeValue);
  				oElement.options.add(op);
  				op.value = ids[i].firstChild.nodeValue;
  				
  			}
  		}
  		
  		if (oElement == document.getElementById("machineid")) {
  			var names = xmlData.getElementsByTagName("Name_machineid");
  			var ids = xmlData.getElementsByTagName("ID_machineid");
  			for ( var i = 0; i < names.length; i++) {
  				var op = new Option(names[i].firstChild.nodeValue);
  				// 为列表/菜单添加选项时，object.options.add方法比object.appendChild方法更适用。
  				oElement.options.add(op);
  				op.value = ids[i].firstChild.nodeValue;
  			}
  		}

  		if (firstchar == document.getElementById("item_id")
  				|| firstchar == document.getElementById("machineid")) {
  			var names = xmlData.getElementsByTagName("al_lists");
  			var al_orederid = xmlData.getElementsByTagName("al_orderid");
  			var al_issue_num = xmlData.getElementsByTagName("al_issue_num");
  			var al_oper_id = xmlData.getElementsByTagName("al_oper_id");
  			var al_item_id = xmlData.getElementsByTagName("al_item_id");
  			var al_machine_id = xmlData.getElementsByTagName("al_machine_id");
  			var al_start_time = xmlData.getElementsByTagName("al_start_time");
  			var al_end_time = xmlData.getElementsByTagName("al_end_time");
  			var al_worker = xmlData.getElementsByTagName("al_worker");
  			var html = "<tr><th>编辑</th><th>订单号</th><th>批号</th><th>物料号</th><th>工序号</th><th>机床编号</th><th>开始时间</th><th>结束时间</th><th>员工</th></tr>";
  			var table1 = document.getElementById("showdispatched");
  			for ( var i = 0; i < names.length; i++) {

  				html += "<tr id=\""+i+"\"><td>";
  				html +="<select onchange=\"deletedispatched('"
  					+al_orederid[i].firstChild.nodeValue+"','"
  					+al_issue_num[i].firstChild.nodeValue+"','"
  					+al_item_id[i].firstChild.nodeValue+"','"
  					+al_oper_id[i].firstChild.nodeValue+"',this.value);\"><option value='0' selected='selected'>请选择</option><option value='1'>删除</option></select></td>";
  				html += "<td>" + al_orederid[i].firstChild.nodeValue+ "</td>"
  				html += "<td>" + al_issue_num[i].firstChild.nodeValue + "</td>";
  				html += "<td>" + al_item_id[i].firstChild.nodeValue + "</td>";
  				html += "<td>" + al_oper_id[i].firstChild.nodeValue + "</td>";
  				html += "<td>" + al_machine_id[i].firstChild.nodeValue+ "</td>";
  				html += "<td>" + al_start_time[i].firstChild.nodeValue+ "</td>";
  				html += "<td>" + al_end_time[i].firstChild.nodeValue+ "</td>";
  				html += "<td>" + al_worker[i].firstChild.nodeValue+ "</td></tr>";

  			}
  			while (table1.hasChildNodes()) {
  				table1.removeChild(table1.firstChild);
  			}
  			$(html).prependTo(table1);
  		}

  	} else {
  		oElement.options[0].innerHTML = "暂无数据";
  		
  		if (oElement == document.getElementById("item_id")) {
  			s_oper_id.options[0].innerHTML = "暂无数据";
  		}
  		if (oElement == document.getElementById("oper_id")) {
  			s_machineid.options[0].innerHTML = "暂无数据";
  		}
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
  function dispatch(type, machineid) {
  	if (machineid != "") {
  		orderid = document.getElementById("orderid").value;
  		// equipment_drawid = document.getElementById("equipment_drawid").value;
  		issue_num = document.getElementById("issue_num").value;
  		// product_id = document.getElementById("product_id").value;
  		item_id = document.getElementById("item_id").value;
  		oper_id = document.getElementById("oper_id").value;
  		
  		dispatch_starttime = document.getElementById("dispatch_starttime").value;
  		dispatch_endtime = document.getElementById("dispatch_endtime").value;
  		workerid = document.getElementById("workerid").value;
  		
  		createXMLHttpRequest();
  		var url = "DispatchFinish?type=" + encodeURI(type) + "&machineid="
  				+ encodeURI(machineid) + "&orderid=" + orderid
  				// +"&equipment_drawid="+equipment_drawid
  				+ "&issue_num=" + issue_num + "&item_id=" + item_id
  				// +"&product_id="+product_id
  				+ "&oper_id=" + oper_id
  				+ "&dispatch_starttime=" + dispatch_starttime
  				+ "&dispatch_endtime=" + dispatch_endtime
  				+ "&workerid=" + workerid;
  		// +"&starttime="+dispatch_starttime
  		// +"&endtime="+dispatch_endtime;

  		xmlHttp.open("GET", url, true);
  		xmlHttp.onreadystatechange = function() {onFinish()};
  		xmlHttp.send(null);
  	}
  }

  function onFinish() {
  	if (xmlHttp.readyState == 4) {
  		if (xmlHttp.status == 200) {
  			showFinish(xmlHttp.responseXML);
  		}
  	}
  }

  function showFinish(xmlData) {
  	if (xmlData.documentElement.hasChildNodes()) {
  		var finish = xmlData.getElementsByTagName("finish");
  		var isfinish = xmlData.getElementsByTagName("isfinish");
  		var timeout = xmlData.getElementsByTagName("timeout");
  		if (finish[0].firstChild.nodeValue==1) {
  			alert("恭喜！派工成功！！！");
  			showdata('machineid');
  		}
  		if (isfinish[0].firstChild.nodeValue==1) {
  			alert("此道工序已经派过工了！！您可选择修改~~");
  		}
  		if (timeout[0].firstChild.nodeValue==1) {
  			alert("机床指定时间已经有任务了，请检查计划是否合理！！");
  		}
  	} else {
  	}
  }

  function showDeleted(xmlData) {
  	var isDeleted = xmlData.getElementsByTagName("isDeleted");
  	if (isDeleted[0].firstChild.nodeValue==1) {
  		alert("删除成功！！！！");   //删除成功以后还要做显示的功能
                                   //删除不成功要找到原因所在
  		showdata('machineid');
  	}
  	if (isDeleted[0].firstChild.nodeValue==0) {
  		alert("删除失败！！！！");   
  	}
  }

  function deletedispatched(orderid,issuenum,itemid,operid,optionvalue){
  	if (optionvalue==1) {                 //1 表示删除操作，还可以在此添加其他的操作！！！！
  		createXMLHttpRequest();
  		var url = "Dispatch_Delete?orderid=" + orderid 
  			+ "&issuenum=" + issuenum
  			+ "&itemid=" + itemid 
  			+ "&operid=" + operid;
  		/*
  		 * 当准备状态改变时，需要为readyState属性指定事件处理函数，该处理函数有两种传递参数的方法：
  		 * xmlhttp.onreadystatechange=
  		 * function(){HandleStateChange(param1,param2...)}; 或者
  		 * xmlhttp.onreadystatechange=new
  		 * Function("HandleStateChange(param1,param2...)");
  		 */
  		xmlHttp.open("POST", url, true);
  		xmlHttp.onreadystatechange = function() {
  			ondeleted()
  		};
  		xmlHttp.send(null);
  	}
  }

  function ondeleted(){
  	if (xmlHttp.readyState == 4) {
  		if (xmlHttp.status == 200) {
  			showDeleted(xmlHttp.responseXML);
  		}
  	}
  }

  function showdata(type){
  	createXMLHttpRequest();
  	var url = "Dispatch_Select?type=machineid&orderid=" + orderid
  			+ "&issue_num=" + issue_num + "&item_id=" + item_id;

  	xmlHttp.open("POST", url, true);
  	xmlHttp.onreadystatechange = function() {
  		onStateChange_show()
  	};
  	xmlHttp.send(null);
  }

  function onStateChange_show() {
  	if (xmlHttp.readyState == 4) {
  		if (xmlHttp.status == 200) {
  			showSelect_show(xmlHttp.responseXML);
  		}
  	}
  }

  function showSelect_show(xmlData) {
  	if (xmlData.documentElement.hasChildNodes()) {
  			var names = xmlData.getElementsByTagName("al_lists");
  			var al_orederid = xmlData.getElementsByTagName("al_orderid");
  			var al_issue_num = xmlData.getElementsByTagName("al_issue_num");
  			var al_oper_id = xmlData.getElementsByTagName("al_oper_id");
  			var al_item_id = xmlData.getElementsByTagName("al_item_id");
  			var al_machine_id = xmlData.getElementsByTagName("al_machine_id");
  			var al_start_time = xmlData.getElementsByTagName("al_start_time");
  			var al_end_time = xmlData.getElementsByTagName("al_end_time");
  			var al_worker = xmlData.getElementsByTagName("al_worker");
  			var html = "<tr><th>编辑</th><th>订单号</th><th>批号</th><th>物料号</th><th>工序号</th><th>机床编号</th><th>开始时间</th><th>结束时间</th><th>员工</th></tr>";
  			var table1 = document.getElementById("showdispatched");
  			for ( var i = 0; i < names.length; i++) {
  				html += "<tr id=\""+i+"\"><td>";
  				html +="<select onchange=\"deletedispatched('"
  					+al_orederid[i].firstChild.nodeValue+"','"
  					+al_issue_num[i].firstChild.nodeValue+"','"
  					+al_item_id[i].firstChild.nodeValue+"','"
  					+al_oper_id[i].firstChild.nodeValue+"',this.value);\"><option value='0' selected='selected'>请选择</option><option value='1'>删除</option></select></td>";
  				html += "<td>" + al_orederid[i].firstChild.nodeValue+ "</td>"
  				html += "<td>" + al_issue_num[i].firstChild.nodeValue + "</td>";
  				html += "<td>" + al_item_id[i].firstChild.nodeValue + "</td>";
  				html += "<td>" + al_oper_id[i].firstChild.nodeValue + "</td>";
  				html += "<td>" + al_machine_id[i].firstChild.nodeValue+ "</td>";
  				html += "<td>" + al_start_time[i].firstChild.nodeValue+ "</td>";
  				html += "<td>" + al_end_time[i].firstChild.nodeValue+ "</td>";
  				html += "<td>" + al_worker[i].firstChild.nodeValue+ "</td></tr>";
  			}
  			while (table1.hasChildNodes()) {
  				table1.removeChild(table1.firstChild);
  			}
  			$(html).prependTo(table1);
  		}
  }

  </script>
</html>


