var xmlData="";
var arr;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}

function EcEbomSearch(){
	var test = document.getElementById("product_type");
	var product_type =document.getElementById("product_type").value;
	var product_id   =document.getElementById("product_id").value;
	var issue_num    =document.getElementById("issue_num").value;
	var lot          =document.getElementById("lot").value;
	
	 createXMLHttpRequest();
     var url = "EcEbom_Search?product_type="+product_type+"&product_id="+product_id+"&issue_num="+issue_num+"&lot="+lot;
     
     xmlHttp.onreadystatechange = function(){onStateChanged()};
     xmlHttp.open("POST", url, true);
     xmlHttp.send(null);
}

function onStateChanged() {
    if(xmlHttp.readyState == 4) {
        if(xmlHttp.status == 200) {
            showSelected(xmlHttp.responseXML);
        }
    }
}

function showSelected(xmlData){
	if(xmlData==null){return;}
	var oElement = document.getElementById("ecebomtable");
	var ecebomdatas = xmlData.getElementsByTagName("ECEBOMDATA");
	
	for(var j=oElement.rows.length-1;j>0;j--){//删除最后记录行
		oElement.deleteRow(j);
	}
	for(var i=0;i<ecebomdatas.length;i++){
		var itemid  =ecebomdatas[i].childNodes[0].firstChild.nodeValue;
		var fitemid =ecebomdatas[i].childNodes[1].firstChild.nodeValue;
		var fid     =ecebomdatas[i].childNodes[2].firstChild.nodeValue;
		var id      =ecebomdatas[i].childNodes[3].firstChild.nodeValue;
		var levelid =ecebomdatas[i].childNodes[4].firstChild.nodeValue;
		var ectype  =ecebomdatas[i].childNodes[5].firstChild.nodeValue;
		var eccon   =ecebomdatas[i].childNodes[6].firstChild.nodeValue;
		var ectime  =ecebomdatas[i].childNodes[7].firstChild.nodeValue;
		
		 arr  = new Array();
		for(var m=0;m<13;m++){
				arr[m]=ecebomdatas[i].childNodes[m].firstChild.nodeValue;
				
		}
		
		var newRow = oElement.insertRow();//插入行
		
		var newCelldo      = newRow.insertCell(); //得到空白单元格
		var newCellitemid  = newRow.insertCell();
		var newCellfitemid = newRow.insertCell();
		var newCellfid     = newRow.insertCell();
		var newCellid      = newRow.insertCell();
		var newCelllevelid = newRow.insertCell();
		var newCellectype  = newRow.insertCell();
		var newCelleccon   = newRow.insertCell();
		var newCellectime  = newRow.insertCell();
		
		newCelldo.innerHTML     = "<td align='center'><FONT color='#338800'><A onclick='del(arr);' style='cursor:hand'>[删除]</A></FONT></td>";
		newCellitemid.innerHTML = itemid;
		newCellfitemid.innerHTML = fitemid;
		newCellfid.innerHTML = fid;
		newCellid.innerHTML = id;
		newCelllevelid.innerHTML = levelid;
		newCellectype.innerHTML = ectype;
		newCelleccon.innerHTML = eccon;
		newCellectime.innerHTML = ectime;
	
	/*if(i==ecebomdatas.length-1){
		var newRowrecord  = oElement.insertRow();
		var newCellrecord = newRowrecord.insertCell();
	    newCellrecord.colspan = 8;
		newCellrecord.innerHTML = "<td align=center colspan=8 >共有记录数：   当前  页"+
		"<a onclick='firsrpg();' style='cursor:hand'>第一页</a><a onclick='lastpg();' style='cursor:hand'>上一页 </a>"+
		"<a onclick='nextpg();' style='cursor:hand'>下一页</a>"+
		"<a onclick='finalpg();' style='cursor:hand'>最后页 </a>"+
		"直接<input type=image src='img/hand.gif' name='gotof' onclick='return chkdata();'>"+
		"<input type=text size=5 name=bm class=formcolor>页</TD>"
		}*/
	}
}







