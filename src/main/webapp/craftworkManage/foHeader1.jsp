<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
	<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
	<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
	<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
	<link href="<%=path %>/css/person.css" type=text/css rel=stylesheet>
   
    <title>工序</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  <body leftMargin=0 topMargin=0 style="width:100%;height:90%;">
	<form id="form1" name="form1" action="" method=post>
		<fieldset style="width: 99%;" align="center">
		<legend>
		工艺
		</legend>   	
	   	<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%">
	   		<tr>
	   			    
             	<td><label for="foId$text">工艺号</label></td>
                <td><input id="foId" name="foId" class="mini-textbox" width="100%"  value="${result.foId}"   readonly  /></td>
   				<td><label for="orderId$text">订单号</label></td>
            	<td><input id="orderId"  name="orderId" class="mini-textbox"  width="100%" required="true" value="${result.orderId}" readonly/></td>
   				<td><label for="productId$text">产品编号</label></td>
            	<td><input id="productId"  name="productId" class="mini-textbox"  width="100%" required="true" value="${result.productId}" readonly/></td>
   			</tr>
	   		<tr>
	   			<td><label for="productName$text">产品名称</label></td>
            	<td><input id="productName"  name="productName" class="mini-textbox"  width="100%" required="true" value="${result.productName}" readonly/></td>
	   			<td><label for="issueNum$text">版本号</label></td>
            	<td><input id="issueNum"  name="issueNum" class="mini-textbox"  width="100%" required="true" value="${result.issueNum}" readonly/></td>
   				<td><label for="drawingId$text">图号</label></td>
            	<td><input id="drawingId"  name="drawingId" class="mini-textbox"  width="100%" required="true" value="${result.drawingId}" readonly/></td>
   			</tr>
	   		<tr>
	   			<td><label for="fproductId$text">父产品号</label></td>
            	<td><input id="fproductId"  name="fproductId" class="mini-textbox"  width="100%" required="true" value="${result.fproductId}" readonly/></td>
   				<td><label for="productNum$text">产品数量</label></td>
            	<td><input id="productNum"  name="productNum" class="mini-textbox"  width="100%" required="true" value="${result.productNum}" readonly/></td>
   				<td><label for="spec$text">产品规格</label></td>
            	<td><input id="spec"  name="spec" class="mini-textbox"  width="100%" required="true" value="${result.spec}" readonly/></td>
	   		</tr>
	   		<tr>
	   			<td><label for="productTypeName$text">产品类型</label></td>
            	<td><input id="productTypeName" name="productTypeName" class="mini-textbox"  width="100%" required="true" value="${result.productTypeName}" readonly/>
  		          <input id="productType" name="productType" class="mini-hidden" value="${result.productType}"/> 
            	</td>
	   			<td><label for="supplyStatus$text">供应状态</label></td>
            	<td><input id="supplyStatus"  name="supplyStatus" class="mini-textbox"  width="100%" value="${result.supplyStatus}" /></td>
   				<td><label for="techNeed$text">技术要求</label></td>
            	<td><input id="techNeed"  name="techNeed" class="mini-textbox"  width="100%" value="${result.techNeed}"/></td>
	   		</tr>
	   		<tr>
	   			<td><label for="matirial$text">材料</label></td>
            	<td><input id="matirial"  name="matirial" class="mini-textbox"  width="100%" value="${result.matirial}"/></td>
   				<td><label for="techSpec$text">技术规格</label></td>
            	<td><input id="techSpec"  name="techSpec" class="mini-textbox"  width="100%" value="${result.spec}"/></td>
   				<td><label for="roughSize$text">毛坯尺寸</label></td>
            	<td><input id="roughSize"  name="roughSize" class="mini-textbox" width="100%" value="${result.roughSize}"/></td>
	   			
	   		</tr>
	   		<tr height="60px;">
   			<td><label for="memo$text">附录</label></td>
	        <td colspan="5"><input id="memo" name="memo" class="mini-textarea" emptyText="请输入备注" value="${result.memo}"
	        	width="100%" height="100%"/></td>
         </tr>
  		</table>
  		<div class="mini-toolbar">
  			<c:choose>
    			<c:when test="${isHere=='1'}">
    				<a class="mini-button" id="startAlter" iconCls="icon-unlock" plain="false" onclick="startAlter()">修改信息</a>
    				<a class="mini-button" id="doAlter" iconCls="icon-save" plain="false" onclick="doAlter()" style="display:none">进行修改</a>
  					<a class="mini-button" iconCls="icon-add" plain="false" onclick="Gofodetail()">添加工序详情</a>
  				</c:when>
    			<c:otherwise>
    				<a class="mini-button" iconCls="icon-ok" plain="false"  onclick="getForm()">添加制造工艺</a>
  				</c:otherwise>
    		</c:choose>
  		</div>
   	</fieldset>
	</form>	
</BODY>
<script>
	mini.parse();
        
    function onOperatePower(e) {
		var str = "";
		str += "<span>";
		str += "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.foNo+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;&nbsp;</span></a>";
		str += "</span>";
		str += "<span>";
		str += "<a style='margin-right:2px;' title='刀量夹具' href=javascript:onCMTYF(\'" + e.row.productId+"\',\'" + e.row.issueNum+"\',\'" + e.row.foNo+"\') ><span class='mini-button-text mini-button-icon icon-cut'>&nbsp;&nbsp;</span></a>";
		str += "</span>";
		return str;
	};
	
	
	    
	function ondEdit(foNo){
 		var productType = window.parent.document.getElementById("productType").value;
		var productId = window.parent.document.getElementById("productId").innerHTML;
		var issueNum = window.parent.document.getElementById("issueNum").innerHTML;
	    window.parent.document.getElementById("subIframe").src="BaseServlet?meth=FodetailData&pathTo=fodetailData&productType="+productType+"&productId="+productId+"&issueNum="+issueNum+"&foNo="+foNo;
	}
	
	function onCMTYF(productId,issueNum,foNo){
	     window.location.href="BaseServlet?meth=Gofocmtyf&pathTo=focmtyf&productId="+productId+"&issueNum="+issueNum+"&foNo="+foNo;	    
	}
		
	function refresh(){
		grid.reload();
	}
	
	function Gofodetail(){
		var orderId = mini.get("orderId").value;
		var foId = mini.get("foId").value;
		var drawingId = mini.get("drawingId").value;
		window.location.href="BaseServlet?meth=Gofodetail&pathTo=fodetail&productType=${result.productType}&productId=${result.productId}&issueNum=${result.issueNum}&orderId="+orderId+"&foId="+foId+"&drawingId="+drawingId;
	}
	function getForm(){
 		var form = new mini.Form("#form1");
 		form.validate();
        if (form.isValid() == false) {
          	return;
        }else{
          	var data = form.getData();
            var params = eval("("+mini.encode(data)+")");
            var url = 'BaseServlet?meth=AddFoHeader1&pathTo=';
 		    jQuery.post(url, params, function(data){
 	   			mini.confirm(data.result, "确定？",
                function (action){            
                    if (action == "ok") {
                    	window.location.href = window.location.href;	
                    }
                });
 	   		},'json');
		}
   	}
   	
   	function doAlter(){
 		var form = new mini.Form("#form1");
 		form.validate();
        if (form.isValid() == false) {
          	return;
        }else{
          	var data = form.getData();
            var params = eval("("+mini.encode(data)+")");
            var url = 'BaseServlet?meth=updateFoHeader&pathTo=';
 		    jQuery.post(url, params, function(data){
 	   			mini.confirm(data.result, "确定？",
                function (action){            
                    if (action == "ok") {
                    	window.location.href = window.location.href;	
                    }
                });
 	   		},'json');
		}
   	}
   	
   	function getFormOld(){
  		var form = new mini.Form("#userdiv");
		form.validate();
        if (form.isValid() == false) {
         	return;
        }else{
  			jQuery.ajax({
   				type: "POST",
   				url: "AddOrderServlet",
   				dataType: "json", 
   				cache: false,
   				enctype: 'multipart/form-data',
   				data: new FormData($('#userdiv')[0]),
   				processData: false,
 				contentType: false,
   				success: function (data) {
     				alert(data.result);
     				window.location.href = window.location.href;
   				}
 			});
 		}
	}
   		
	function onButtonEditMachine(e) {
           var btnEdit = this;
           mini.open({
               url: "machineManage/selectMachineWindow.jsp",
               title: "选择上级部门",
               width: 650,
               height: 380,
               ondestroy: function (action) {
                   //if (action == "close") return false;
                   if (action == "ok") {
                       var iframe = this.getIFrameEl();
                       var data = iframe.contentWindow.GetData();
                       data = mini.clone(data);    //必须
                       if (data) {
                           btnEdit.setValue(data.machineId);
                           btnEdit.setText(data.machineName);
                           //mini.get("connector").setValue(data.connector);
                           //mini.get("connectorTel").setValue(data.connectorTel);
                       }
                   }
               }
           });
       }
       
       var Genders = [{ id: '1', text: '是' }, { id: '0', text: '否'}];
       function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
       }
       
       function GetRequest() { 
        	var url = location.search; //获取url中"?"符后的字串 
        	var theRequest = new Object(); 
        	if (url.indexOf("?") != -1) { 
        		var str = url.substr(1); 
        		strs = str.split("&"); 
        		for(var i = 0; i < strs.length; i ++) { 
        			theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
        		} 
        	} 
        	return theRequest; 
        }

        var Request = new Object(); 
        Request = GetRequest();
        //alert(Request['productId']);
        //mini.get("productType").value = Request['machineId'];
       
           //    function onlyRead(){
    //    	if("1"==${isHere}){
    //    		var inputs=document.getElementsByTagName("input");
    //			for(var i=0;i<inputs.length;i++){
    				//inputs[i].setAttribute("readOnly","readOnly");
    				//alert(inputs[i].name);
    //				var t = mini.get(inputs[i].name);
    //				if(t!=null){
    //					t.disable();
    //				}
   // 			}
   //     	}
   // 		mini.get("foId").allowInput=false;
    		//alert("dd");
	//	}
    // onlyRead();
     

		
		function startAlter(){
			var inputs=document.getElementsByTagName("input");
    		for(var i=0;i<inputs.length;i++){
    			var t = mini.get(inputs[i].name);
    			if(t!=null){
    				t.enable();
    			}
    		}
    		document.getElementById("doAlter").style.display="";
    		document.getElementById("startAlter").style.display="none";
		}
</script>
</html>