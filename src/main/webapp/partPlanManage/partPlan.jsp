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
   
    <title>零件计划</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  <body leftMargin=0 topMargin=0 style="width:100%;height:90%;">
	<form id="form1" name="form1" action="" method=post>
		<fieldset style="width: 99%;" align="center">
			<legend>
				添加零件计划
			</legend>  
		<div id="add" style="background:#dafec5" >
			<table class="green_list_table" align="center" width="100%" border="1" style="word-break:break-all;border-collapse:collapse" bgcolor="#dafec5">
			<tr>
				<tr><th width=100>计划编号</th> 
					<td><input id="partsPlanId" name="partsPlanId" class="mini-textbox" width="100%" required="true" value="${result.partsPlanId}"/></td>
					<!--
					<th>计划时间</th>
			   		<td><input id="planTime" name="planTime" class="mini-textbox" width="100%" required="true" readonly /></td>
			   		<th>计划人</th>
			    	<td><input id="planPerson" name="planPerson" class="mini-textbox" width="100%" required="true" readonly/></td>
			    	<th>零件名称</th>
			    	<td><input id="productName" name="productName" class="mini-textbox" required="true" width="100%" readonly value="${productName}"/></td>
			    	<th width="12%">零件父物料号</th>
			    	<td><input id="fitemId" name="fitemId" class="mini-textbox" required="true" width="100%" readonly value="${fproductId}"/></td>
			    	-->
			    	<th>零件号</th>
			    	<td><input id="productId" name="productId" class="mini-textbox" required="true" width="100%" readonly value="${result.productId}"/></td>
			    	
			    
			    <tr>
			    	<th width="12%">订单号</th>
			    	<td><input id="orderId" name="orderId" class="mini-textbox" required="true" width="100%" readonly value="${result.orderId}"/></td>
			    	
			    	<th width="12%">零件数量</th>
			    	<td><input id="partNum" name="partNum" class="mini-textbox" required="true" width="100%" vtype="int" value="${result.productNum}"/></td>
			    </tr>
			    <tr>
			    	<th>计划开始时间</th>
			    	<td>
			    		<input id="planBTime"  name="planBTime" class="mini-datepicker" width="100%" allowInput="false" value="${result.planBTime}"
	   						dateFormat="yyyy-MM-dd" format="yyyy-MM-dd" showTime="false" showOkButton="false" showClearButton="false"/>
				   	</td> 
					<th>计划结束时间</th>
					<td><input id="planETime"  name="planETime" class="mini-datepicker" width="100%" allowInput="false" value="${result.planETime}"
	   						dateFormat="yyyy-MM-dd" format="yyyy-MM-dd" showTime="false" showOkButton="false" showClearButton="false"/>
					<!--
			    	<th>质编号</th>
			    	<td><input id="qualityId" name="qualityId" class="mini-textbox" width="100%" /></td>
			    	-->
   				</tr>      
            	<tr><input class="mini-hidden" id="productTypeId" name = "productTypeId" value="${result.productTypeId}"/>
					<input class = "mini-hidden"  name = "productId" value="${result.productId}"/>
					<input class = "mini-hidden" id="issueNum" name = "issueNum" value="${result.issueNum}"/>
					<input class = "mini-hidden" name = "orderId" value="${result.orderId}"/>
					<input class = "mini-hidden" id="drawingId" name = "drawingId" value="${result.drawingId}"/>
				</tr>
			</table>
			<div class="mini-toolbar">
    			<c:if test="${isDone=='1'}">
    				<a class="mini-button" id="startAlter" iconCls="icon-unlock" plain="false" onclick="startAlter()">修改信息</a>
    				<a class="mini-button" id="doAlter" iconCls="icon-save" plain="false" onclick="doAlter();" style="display:none">进行修改</a>
  <!--    				<c:if test="${proPlanDone=='1'}">
    					<a class="mini-button" iconCls="icon-node" plain="false" onclick="listProPlan();" style="margin-left:40px;">查看工序计划</a>
    				</c:if>
  --><!--此处的生成工序计划没有什么用，因此我将其删除掉了  -->
    				<c:if test="${proPlanDone!='1'}">
    					<a class="mini-hidden" iconCls="icon-date" plain="false" onclick="addProPlan();" style="margin-left:40px;">生成工序计划</a><!--此处我隐藏一起来，因为没有用处，只是和生成零件计划一样的额  -->
    				</c:if>
    			</c:if>
    			<c:if test="${isDone!='1'}">
    				<a class="mini-button" iconCls="icon-ok" plain="false" onclick="doPartPlan();">生成零件计划</a>
    			</c:if>
    			<a class="mini-button" iconCls="icon-date" plain="false" onclick="allPartsGandT();" style="margin-left:40px;">查看零件甘特图</a>
    			<!-- 9-5 xiexiaoming -->
    			<a class="mini-button" iconCls="icon-date" plain="false" onclick="showbarcode();" style="margin-left:40px;">查看条形码</a>
			</div>
		</div>
		</fieldset>
	</form>
</BODY>
<script>
	mini.parse();
   	//var grid = mini.get("grid1");
    //grid.load();
        
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
	
	//function refresh(){
	//	grid.reload();
	//}

	/*
	function doPartPlan(){
 		var form = new mini.Form("#form1");
 		form.validate();
        if (form.isValid() == false) {
          	return;
        }else{
          	var data = form.getData();
          	data.planBTime = mini.get("planBTime").getFormValue();
          	data.planETime = mini.get("planETime").getFormValue();
            var params = eval("("+mini.encode(data)+")");
            var url = 'PartPlanBaseServlet?meth=AddpartPlan&pathTo=';
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
   	*/
   	
   	
   	function doPartPlan(){
   		var form = new mini.Form("#form1");
 		form.validate();
        if (form.isValid() == false) {
          	return;
        }else{
          	var data = form.getData();
          	data.planBTime = mini.get("planBTime").getFormValue();
          	data.planETime = mini.get("planETime").getFormValue();
            var params = eval("("+mini.encode(data)+")");
            var url = 'PartPlanBaseServlet?meth=AddpartPlan&pathTo=';
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

   	function addProPlan(){
   		var form = new mini.Form("#form1");
 		form.validate();
        if (form.isValid() == false) {
          	return;
        }else{
          	var data = form.getData();
          	data.planBTime = mini.get("planBTime").getFormValue();
          	data.planETime = mini.get("planETime").getFormValue();
            var params = eval("("+mini.encode(data)+")");
            var url = 'PartPlanBaseServlet?meth=addProPlan&pathTo=';
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
   	
   	function listProPlan(){
   		var form = new mini.Form("#form1");
 		form.validate();
        if (form.isValid() == false) {
          	return;
        }else{
          	var data = form.getData();
          	data.planBTime = mini.get("planBTime").getFormValue();
          	data.planETime = mini.get("planETime").getFormValue();
            var params = eval("("+mini.encode(data)+")");
            var url = 'PartPlanBaseServlet?meth=GoProPlan&pathTo=partProGT';
            window.location.href = "PartPlanBaseServlet?meth=GoProPlan&pathTo=partProGT&orderId="+data.orderId+"&productId="+data.productId+"&issueNum="+data.issueNum+"&drawingId="+data.drawingId;
 		    //jQuery.post(url, params);
		}
   	}
   	
   	function allPartsGandT(){
   	    var orderId=mini.get("orderId").getValue();
   	    var productId=mini.get("productId").getValue();
   	    var drawingId=mini.get("drawingId").getValue();
   	    var issueNum=mini.get("issueNum").getValue();
   	    var data =["{'orderId':'"+orderId+"','productId':'"+productId+"','issueNum':'"+issueNum+"','drawingId':'"+drawingId+"'}"];
   	    data = JSON.stringify(data);
    	params = {'data':data}; 			
    	var url = 'PartPlanBaseServlet?meth=GoPartGT&pathTo=partProGT';
    			jQuery.post(url, params, function(data){
     },'json');
    			 window.location.href = 'partPlanManage/partProGT.jsp';
 //   	window.location.href = "PartPlanBaseServlet?meth=GoPartGT&pathTo=partProGT";   
   	}
       
       	function preview(file)  {  
 			var prevDiv = document.getElementById('picTD');  
 			var picTD = document.getElementById('picTD');
            var height = 0;
            var width = 0;
            if(null==picTD.offsetHeight||undefined==picTD.offsetHeight||''==picTD.offsetHeight){
            	height = 100;
                width = 350;
            }else{
                height = picTD.offsetHeight;
                width = picTD.clientWidth;
            }
 			if (file.files && file.files[0]){  
 				var reader = new FileReader();  
 				reader.onload = function(evt){ 
 					prevDiv.innerHTML = '<img src="' + evt.target.result + '" height="' + height+ '" width="' +width+ '"/>';  
				}    
 				reader.readAsDataURL(file.files[0]);  
			}else {  
 				prevDiv.innerHTML = '<div class="img" style="filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src=\'' + file.value + '\'"></div>';  
 			} 
		}  
       
       	function doAlter(){
 			var form = new mini.Form("#form1");
 			form.validate();
        	if (form.isValid() == false) {
          		return;
        	}else{
          		var data = form.getData();
          		data.planBTime = mini.get("planBTime").getFormValue();
          		data.planETime = mini.get("planETime").getFormValue();
           		var params = eval("("+mini.encode(data)+")");
            	var url = 'PartPlanBaseServlet?meth=UpdatepartPlan&pathTo=';
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
        
        
        
        function onlyRead(){
        	if("1"==${isDone}){
        		var inputs=document.getElementsByTagName("input");
    			for(var i=0;i<inputs.length;i++){
    				var t = mini.get(inputs[i].name);
    				if(t!=null){
    					t.disable();
    				}
    			}
    			mini.get("partsPlanId").allowInput=false;
        	}
		}
		onlyRead();
		
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
		//9-5 9-18 xiexiaoming 
		function showbarcode(){
			var partsplanid = mini.get("partsPlanId").getValue();
			window.open("PartsplanBarcode?partsplanid=" + partsplanid,
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
			/*
			$.ajax({
        	type: "post",
            url: "PartsplanBarcode?partsplanid=" + partsplanid,
            cache: false,
            success: function (text) {
            	window.confirm(text);
            }
       });
       */
		}
</script>
</html>