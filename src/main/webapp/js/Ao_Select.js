var xmlHttp;
var flight_type="";
var product_id="";
var lot="";
var sortie="";
var y=1;

function createXMLHttpRequest() {
    if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    } 
    else if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
}
    
function select(aoxid,type) {
	/**刷新后面的输入框的值**/
	initSelect(document.getElementById("ao_cut"));
	initSelect(document.getElementById("ao_measure"));
	initSelect(document.getElementById("ao_tool"));
	initSelect(document.getElementById("ao_material"));
	initSelect(document.getElementById("ao_accessory"));
        createXMLHttpRequest();
        if(type==1){
        	initSelect(document.getElementById("ao_ver"));
        	y=type;
        	var aoxid2;
        var url = "../../Ao_Select?aoxid1="+aoxid+"&aoxid2="+aoxid2+"&type="+type;}
        else{
        	y=type;
        	var aoxid2 = document.getElementById("aono2").value;
            var url = "../../Ao_Select?aoxid2="+aoxid+"&aoxid1="+aoxid2+"&type="+type;}
        xmlHttp.onreadystatechange = function(){onStateChange()};
        xmlHttp.open("POST", url, true);
        xmlHttp.send(null);
    }

    
function onStateChange() {
    if(xmlHttp.readyState == 4) {
        if(xmlHttp.status == 200) {
            showSelect(xmlHttp.responseXML);
        }
    }
}

function showSelect(xmlData) {
        if(xmlData.documentElement.hasChildNodes()){
//自己写的代码             	
           var x = xmlData.documentElement.childNodes;
           if(y==1){
           oElement =  document.getElementById("ao_ver");
           oElement.options[0].innerHTML="--请选择--";
//查看传送来的xml文件
           var xtype=x[0];
           var names2 = xtype.getElementsByTagName("Name_id2");
           var ids2 = xtype.getElementsByTagName("ID2");
           for(var i = 0; i < names2.length; i++) {
           var op=new Option(names2[i].firstChild.nodeValue);     
           oElement.options.add(op);
	        op.value=ids2[i].firstChild.nodeValue;
           }
           }else{
        	   for(var j=1;j<6;j++){
                   if(j==1){type="cut";}else if(j==2){type="measure";}else if(j==3){type="tool";}else if(j==4){type="material";}
                   else if(j==5){type="accessory";}
        	   oElement =  document.getElementById("ao_"+type);
               oElement.options[0].innerHTML="--请选择--"; 
           var xtype = x[j-1];
            var names = xtype.getElementsByTagName("Name_id");
            var ids = xtype.getElementsByTagName("ID");
            for(var i = 0; i < names.length; i++) {
            var op=new Option(names[i].firstChild.nodeValue);     
            oElement.options.add(op);
	        op.value=ids[i].firstChild.nodeValue;
            }
           }
           }
        }else{
          oElement.options[0].innerHTML="暂无数据";
}
}

function initSelect(oElement) {
    while(oElement.options.length > 0) {
        oElement.remove(oElement.options.length-1);
    }
    var op=new Option("数据加载中...");        
    oElement.options.add(op);
    op.value="";
}

function saveissue(type,sid) {
    if(sid!=""){
        //ddz.innerHTML="数据加载中...";
        createXMLHttpRequest();
        var url = "bom_select?type="+encodeURI(type)+"&sid="+encodeURI(sid);
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);   
    }
}
