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
	
	s_issue_num = document.getElementById("issue_num");
	// s_product_id =document.getElementById("product_id");
	s_item_id = document.getElementById("item_id");
	s_oper_id = document.getElementById("oper_id");
	s_workerid = document.getElementById("workerid");
	s_machineid = document.getElementById("machineid");

	initSelect(oElement);
	orderid = document.getElementById("orderid").value;
	// equipment_drawid = document.getElementById("equipment_drawid").value;
	issue_num = document.getElementById("issue_num").value;
	// product_id = document.getElementById("product_id").value;
	item_id = document.getElementById("item_id").value;
	oper_id = document.getElementById("oper_id").value;
	
	start_time = document.getElementById("start_time").value;
	end_time = document.getElementById("end_time").value;
	
	dispatch_starttime = document.getElementById("dispatch_starttime").value;
	dispatch_endtime = document.getElementById("dispatch_endtime").value;

	/*
	 * if(elementID=="equipment_drawid"){ initSelect(s_issue_num);
	 * initSelect(s_product_id); initSelect(s_item_id); initSelect(s_oper_id);
	 * initSelect(s_machineid); s_issue_num.options[0].innerHTML="--------";
	 * s_product_id.options[0].innerHTML="--------";
	 * s_item_id.options[0].innerHTML="--------";
	 * s_oper_id.options[0].innerHTML="--------";
	 * s_machineid.options[0].innerHTML="--------"; }
	 */
	
	
	if (elementID == "orderid") {
		
		// initSelect(s_product_id);
		/*initSelect(orderid);
		initSelect(s_item_id);
		initSelect(s_oper_id);
		initSelect(s_machineid);
		 * */
		
		// s_product_id.options[0].innerHTML="--------";
		//orderid.options[0].innerHTML = "--------";
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
		// s_product_id.options[0].innerHTML="--------";
		s_item_id.options[0].innerHTML = "--------";
		s_oper_id.options[0].innerHTML = "--------";
		s_workerid.options[0].innerHTML = "--------";
		s_machineid.options[0].innerHTML = "--------";
	}
	/*
	 * if(elementID=="product_id"){ initSelect(s_item_id);
	 * initSelect(s_oper_id); initSelect(s_machineid);
	 * s_item_id.options[0].innerHTML="--------";
	 * s_oper_id.options[0].innerHTML="--------";
	 * s_machineid.options[0].innerHTML="--------";
	 *  }
	 */
	if (elementID == "item_id") {
		initSelect(s_oper_id);
		initSelect(s_machineid);
		s_oper_id.options[0].innerHTML = "--------";
		s_workerid.options[0].innerHTML = "--------";
		s_machineid.options[0].innerHTML = "--------";
	}
	if (elementID == "oper_id") {
		initSelect(s_workerid);
		s_workerid.options[0].innerHTML = "--------";
		s_machineid.options[0].innerHTML = "--------";
	}
	if (elementID == "workerid") {
		initSelect(s_machineid);
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
		/*
		 * 当准备状态改变时，需要为readyState属性指定事件处理函数，该处理函数有两种传递参数的方法：
		 * xmlhttp.onreadystatechange=
		 * function(){HandleStateChange(param1,param2...)}; 或者
		 * xmlhttp.onreadystatechange=new
		 * Function("HandleStateChange(param1,param2...)");
		 */
		//alert("ssss");

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
		/*
		 * if(oElement==document.getElementById("equipment_drawid")){ var names =
		 * xmlData.getElementsByTagName("Name_equipment_drawid"); var ids =
		 * xmlData.getElementsByTagName("ID_equipment_drawid"); for(var i = 0; i <
		 * names.length; i++) { var op=new
		 * Option(names[i].firstChild.nodeValue);
		 * //为列表/菜单添加选项时，object.options.add方法比object.appendChild方法更适用。
		 * oElement.options.add(op); op.value=ids[i].firstChild.nodeValue; } }
		 */
		if (oElement == document.getElementById("orderid")) {
			
			var names = xmlData.getElementsByTagName("order_id");
			var ids = xmlData.getElementsByTagName("order_id");
			for ( var i = 0; i < names.length; i++) {
				var op = new Option(names[i].firstChild.nodeValue);
				// 为列表/菜单添加选项时，object.options.add方法比object.appendChild方法更适用。
				oElement.options.add(op);
				op.value = ids[i].firstChild.nodeValue;
			}
		}
		
		
		
		
		if (oElement == document.getElementById("issue_num")) {
			var names = xmlData.getElementsByTagName("Name_issue_num");
			var ids = xmlData.getElementsByTagName("ID_issue_num");
			for ( var i = 0; i < names.length; i++) {
				var op = new Option(names[i].firstChild.nodeValue);
				// 为列表/菜单添加选项时，object.options.add方法比object.appendChild方法更适用。
				oElement.options.add(op);
				op.value = ids[i].firstChild.nodeValue;
			}
		}
		/*
		 * if(oElement==document.getElementById("product_id")){ var names =
		 * xmlData.getElementsByTagName("Name_product_id"); var ids =
		 * xmlData.getElementsByTagName("ID_product_id"); for(var i = 0; i <
		 * names.length; i++) { var op=new
		 * Option(names[i].firstChild.nodeValue);
		 * //为列表/菜单添加选项时，object.options.add方法比object.appendChild方法更适用。
		 * oElement.options.add(op); op.value=ids[i].firstChild.nodeValue; } }
		 */
		if (oElement == document.getElementById("item_id")) {
			var names = xmlData.getElementsByTagName("Name_item_id");
			var ids = xmlData.getElementsByTagName("ID_item_id");
			for ( var i = 0; i < names.length; i++) {
				var op = new Option(names[i].firstChild.nodeValue);
				// 为列表/菜单添加选项时，object.options.add方法比object.appendChild方法更适用。
				oElement.options.add(op);
				op.value = ids[i].firstChild.nodeValue;
			}
		}
		if (oElement == document.getElementById("oper_id")) {
			var names = xmlData.getElementsByTagName("Name_oper_id");
			var ids = xmlData.getElementsByTagName("ID_oper_id");
			for ( var i = 0; i < names.length; i++) {
				var op = new Option(names[i].firstChild.nodeValue);
				// 为列表/菜单添加选项时，object.options.add方法比object.appendChild方法更适用。
				oElement.options.add(op);
				op.value = ids[i].firstChild.nodeValue;
			}
		}
		
		
		
		
		if (oElement == document.getElementById("workerid")) {

			var names = xmlData.getElementsByTagName("Name_workerid");
			var ids = xmlData.getElementsByTagName("ID_workerid");
			for ( var i = 0; i < names.length; i++) {
				var op = new Option(names[i].firstChild.nodeValue);
				// 为列表/菜单添加选项时，object.options.add方法比object.appendChild方法更适用。
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

				// alert("进入循环了已经！！！");
				// alert(al_orederid[i].firstChild.nodeValue);
				// alert(al_issue_num[i].firstChild.nodeValue);
				// alert(al_item_id[i].firstChild.nodeValue);
				// alert(al_machine_id[i].firstChild.nodeValue);
				// alert(al_start_time[i].firstChild.nodeValue);
				// alert(al_end_time[i].firstChild.nodeValue);
			}
			while (table1.hasChildNodes()) {
				table1.removeChild(table1.firstChild);
			}
			$(html).prependTo(table1);
			// table.appendChild(html);
			// table.innerHTML+=html;
			// document.getElementById("showdispatched").appendChild(html);
			// document.body.innerText+=html;

			// var ids = xmlData.getElementsByTagName("ID_machineid");
			// for(var i = 0; i < names.length; i++) {
			// //var op=new Option(names[i].firstChild.nodeValue);
			// //为列表/菜单添加选项时，object.options.add方法比object.appendChild方法更适用。
			// alert(names[i].firstChild.nodeValue);
			// oElement.options.add(op);
			// op.value=ids[i].firstChild.nodeValue;
			// }
		}

	} else {
		oElement.options[0].innerHTML = "暂无数据";
		/*
		 * if(oElement==document.getElementById("equipment_drawid")){
		 * s_issue_num.options[0].innerHTML="暂无数据"; }
		 */
		if (oElement == document.getElementById("issue_num")) {
			s_product_id.options[0].innerHTML = "暂无数据";
		}
		/*
		 * if(oElement==document.getElementById("product_id")){
		 * s_item_id.options[0].innerHTML="暂无数据"; }
		 */
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
	// +"&equipment_drawid="+equipment_drawid
			+ "&issue_num=" + issue_num + "&item_id=" + item_id;
			// +"&product_id="+product_id
//			+ "&oper_id=" + oper_id;
	/*
	 * 当准备状态改变时，需要为readyState属性指定事件处理函数，该处理函数有两种传递参数的方法：
	 * xmlhttp.onreadystatechange=
	 * function(){HandleStateChange(param1,param2...)}; 或者
	 * xmlhttp.onreadystatechange=new
	 * Function("HandleStateChange(param1,param2...)");
	 */

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

