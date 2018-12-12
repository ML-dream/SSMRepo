var xmlHttp;
var item_id="";
var product_id="";
var oper_id="";

function createXMLHttpRequest() {
	if (window.XMLHttpRequest) {
        xmlHttp = new XMLHttpRequest();
    }
	else if (window.ActiveXObject) {
        xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
    }     
}
    
function addSelect(type,sid,elementID) {
    //System.out.println(type+" "+sid+" "+elementID);
	//alert(type+" "+sid+" "+elementID);
    //不用var声明变量，并将其最先执行，该变量就具有了全局性
    oElement = document.getElementById(elementID);   
    s_oper_id =document.getElementById("oper_id");
    
    initSelect(oElement);
    item_id = document.getElementById("item_id").value;
    product_id  = document.getElementById("product_id").value;
    oper_id         = document.getElementById("oper_id").value;

    if(elementID=="product_id"){
       initSelect(s_oper_id);
        s_oper_id.options[0].innerHTML="--------";
    }
    if(sid==""){
        oElement.options[0].innerHTML="--------";
    }else{
        createXMLHttpRequest();
        var url = "../Process_Select?type="+type+"&item_id="+item_id+"&product_id="+product_id+"&oper_id="+oper_id;
        /*当准备状态改变时，需要为readyState属性指定事件处理函数，该处理函数有两种传递参数的方法：
        xmlhttp.onreadystatechange= function(){HandleStateChange(param1,param2...)}; 或者  
        xmlhttp.onreadystatechange=new Function("HandleStateChange(param1,param2...)"); 
        */
        
        xmlHttp.open("POST", url, true);
        xmlHttp.onreadystatechange = function(){onStateChange()};
        xmlHttp.send(null);
    }
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
           oElement.options[0].innerHTML="--请选择--";

           if(oElement==document.getElementById("product_id")){
            var names = xmlData.getElementsByTagName("Name_product_id");
            var ids = xmlData.getElementsByTagName("ID_product_id");
            for(var i = 0; i < names.length; i++) {
            var op=new Option(names[i].firstChild.nodeValue);  
            //为列表/菜单添加选项时，object.options.add方法比object.appendChild方法更适用。   
            oElement.options.add(op);
	        op.value=ids[i].firstChild.nodeValue;
            }
           }
           if(oElement==document.getElementById("oper_id")){
             var names = xmlData.getElementsByTagName("Name_oper_id");
             var ids = xmlData.getElementsByTagName("ID_oper_id");
              for(var i = 0; i < names.length; i++) {
              var op=new Option(names[i].firstChild.nodeValue);  
            //为列表/菜单添加选项时，object.options.add方法比object.appendChild方法更适用。   
             oElement.options.add(op);
	         op.value=ids[i].firstChild.nodeValue;
            }
           }
        }else{
          oElement.options[0].innerHTML="暂无数据";

          if(oElement==document.getElementById("product_id")){
           s_oper_id.options[0].innerHTML="暂无数据";
          }
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
        var url = "../Process_Select?type="+encodeURI(type)+"&sid="+encodeURI(sid);
        xmlHttp.open("GET", url, true);
        xmlHttp.send(null);   
    }
}
