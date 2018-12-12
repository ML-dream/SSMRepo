var xmlHttp;
var orderid = "";
var equipment_drawid = "";
var issue_num = "";
var product_id = "";
var item_id = "";
var oper_id = "";
var machineid = "";

function createXMLHttpRequest() {
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
}

function addSelect(type, sid, elementID) {

	oElement = document.getElementById(elementID);
	firstchar = document.getElementById(type);
	s_item_id = document.getElementById("item_id");
	s_oper_id = document.getElementById("oper_id");
	s_machineid = document.getElementById("machineid");
	s_worker = document.getElementById("worker");
	initSelect(oElement);
	orderid = document.getElementById("orderid").value;
	item_id = document.getElementById("item_id").value;
	oper_id = document.getElementById("oper_id").value;
	worker = document.getElementById("worker").value;

	if (elementID == "item_id") {
		initSelect(s_oper_id);
		initSelect(s_machineid);
		initSelect(s_worker);
		s_oper_id.options[0].innerHTML = "--------";
		s_machineid.options[0].innerHTML = "--------";
		s_worker.options[0].innerHTML = "--------";
	}
	if (elementID == "oper_id") {
		initSelect(s_machineid);
		initSelect(s_worker);
		s_machineid.options[0].innerHTML = "--------";
		s_worker.options[0].innerHTML = "--------";
	}
	
	if (elementID == "worker") {
		initSelect(s_machineid);
		s_machineid.options[0].innerHTML = "--------";
	}
	if (sid == "") {
		oElement.options[0].innerHTML = "--------";
	} else {
		createXMLHttpRequest();
		var url = "AoDispatch_select?type=" + type + "&orderid=" + orderid
				+ "&issue_num=" + issue_num + "&item_id=" + item_id
				+ "&oper_id=" + oper_id+ "&worker=" + worker;
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
		
		if (oElement == document.getElementById("worker")) {
			alert("dddd");
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
			var html = "<tr><th>编辑</th><th>订单号</th><th>批号</th><th>物料号</th><th>工序号</th><th>机床编号</th><th>开始时间</th><th>结束时间</th></tr>";
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
				html += "<td>" + al_end_time[i].firstChild.nodeValue+ "</td></tr>";

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
		item_id = document.getElementById("item_id").value;
		oper_id = document.getElementById("oper_id").value;
		createXMLHttpRequest();
		var url = "AoDispatchFinish?type=" + encodeURI(type) + "&machineid="
				+ encodeURI(machineid) + "&orderid=" + orderid
				+  "&item_id=" + item_id
				+  "&worker=" + worker
				+ "&oper_id=" + oper_id;
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
			alert("恭喜！装配派工成功！！！");
			showdata('machineid');
		}
		if (isfinish[0].firstChild.nodeValue==1) {
			alert("此道工位已经派过工了！！您可选择修改~~");
			showdata('machineid');
		}
		if (timeout[0].firstChild.nodeValue==1) {
			alert("场地指定时间已经有任务了，请检查计划是否合理！！");
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

function deletedispatched(orderid,itemid,operid,optionvalue){
	if (optionvalue==1) {                 //1 表示删除操作，还可以在此添加其他的操作！！！！
		createXMLHttpRequest();
		var url = "aoDispatch_Delete?orderid=" + orderid 
			+ "&itemid=" + itemid 
			+ "&operid=" + operid;
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
	var url = "AoDispatch_select?type=machineid&orderid=" + orderid
			+ "&item_id=" + item_id;
	
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
		var al_oper_id = xmlData.getElementsByTagName("al_oper_id");
		var al_item_id = xmlData.getElementsByTagName("al_item_id");
		var al_machine_id = xmlData.getElementsByTagName("al_machine_id");
		var al_start_time = xmlData.getElementsByTagName("al_start_time");
		var al_end_time = xmlData.getElementsByTagName("al_end_time");
		var al_dispatch_person = xmlData.getElementsByTagName("al_dispatch_person");
		var al_dispatch_time = xmlData.getElementsByTagName("al_dispatch_time");
		var al_worker =  xmlData.getElementsByTagName("al_worker");
		var html = "<tr><th>编辑</th><th>订单号</th><th>产品号</th><th>装配顺序号</th><th>工作地点</th><th>开始时间</th><th>结束时间</th><th>计划人</th><th>计划时间</th><th>员工</th></tr>";
		var table1 = document.getElementById("showdispatched");
		for ( var i = 0; i < names.length; i++) {
			html += "<tr id=\""+i+"\"><td>";
			html +="<select onchange=\"deletedispatched('"
				+al_orederid[i].firstChild.nodeValue+"','"
				+al_item_id[i].firstChild.nodeValue+"','"
				+al_oper_id[i].firstChild.nodeValue+"',this.value);\"><option value='0' selected='selected'>请选择</option><option value='1'>删除</option></select></td>";
			html += "<td>" + al_orederid[i].firstChild.nodeValue+ "</td>"
			html += "<td>" + al_item_id[i].firstChild.nodeValue + "</td>";
			html += "<td>" + al_oper_id[i].firstChild.nodeValue + "</td>";
			html += "<td>" + al_machine_id[i].firstChild.nodeValue+ "</td>";
			html += "<td>" + al_start_time[i].firstChild.nodeValue+ "</td>";
			html += "<td>" + al_end_time[i].firstChild.nodeValue+ "</td>";
			html += "<td>" + al_dispatch_person[i].firstChild.nodeValue+ "</td>";
			html += "<td>" + al_dispatch_time[i].firstChild.nodeValue+ "</td>";
			html += "<td>" + al_worker[i].firstChild.nodeValue+ "</td></tr>";
		}
		while (table1.hasChildNodes()) {
			table1.removeChild(table1.firstChild);
		}
		$(html).prependTo(table1);
	}
}

