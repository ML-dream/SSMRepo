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
	s_item_id = document.getElementById("item_id");
	s_oper_id = document.getElementById("oper_id");
	s_machineid = document.getElementById("machineid");

	initSelect(oElement);
	orderid = document.getElementById("orderid").value;
	issue_num = document.getElementById("issue_num").value;
	item_id = document.getElementById("item_id").value;
	oper_id = document.getElementById("oper_id").value;

	if (elementID == "item_id") {
		initSelect(s_oper_id);
		initSelect(s_machineid);
		s_oper_id.options[0].innerHTML = "--------";
		s_machineid.options[0].innerHTML = "--------";
	}
	if (elementID == "oper_id") {
		initSelect(s_machineid);
		s_machineid.options[0].innerHTML = "--------";
	}
	if (sid == "") {
		oElement.options[0].innerHTML = "--------";
	} else {
		createXMLHttpRequest();
		var url = "Dispatch_Select?type=" + type + "&orderid=" + orderid
				+ "&issue_num=" + issue_num + "&item_id=" + item_id
				+ "&oper_id=" + oper_id;
		/*
		 * 当准备状态改变时，需要为readyState属性指定事件处理函数，该处理函数有两种传递参数的方法：
		 * xmlhttp.onreadystatechange=
		 * function(){HandleStateChange(param1,param2...)}; 或者
		 * xmlhttp.onreadystatechange=new
		 * Function("HandleStateChange(param1,param2...)");
		 */
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
				// 为列表/菜单添加选项时，object.options.add方法比object.appendChild方法更适用。
				oElement.options.add(op);
				op.value = ids[i].firstChild.nodeValue;
			}
		}
	} else {
		oElement.options[0].innerHTML = "暂无数据";
		if (oElement == document.getElementById("issue_num")) {
			s_product_id.options[0].innerHTML = "暂无数据";
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


