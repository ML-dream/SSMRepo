<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
	<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
	<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
	<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>工序</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  <body leftMargin=0 topMargin=0 style="width:100%;height:90%;">
	<form id="form1" name="form2" action="" method=post>
		<table class="data_list_table" align="center" width="100%" border="1" cellspacing="2" cellpadding="0" style="word-break:break-all;border-collapse:collapse" >
			<tr height="22">
		 		<td align="left">
		 			<strong><font size="4">FO:工序信息</font></strong>
		 			<input type="button" id="but_add" value=""  onclick="showdiv();" style="cursor:hand;">
		 			<!-- <input type="button" id="but_add2" value="添加/查看CMTYF" onclick="showaddcmtyf();" style="cursor:hand;">  -->
		 		</td>
		  	</tr>
		</table>
		
		<div id="add" style="background:#dafec5" >
			<div id="add" style="background:#dafec5" >
		<form name="form1" id="form1" method="post" enctype="multipart/form-data">
			<table class="green_list_table" align="center" width="100%" border="1" style="word-break:break-all;border-collapse:collapse" bgcolor="#dafec5">
			<tr>
				<tr><th width=100>工序编号*</th> 
					<td><input id="foNo"  name="foNo" class="mini-textbox"  width="100%" required="true" vtype="float"/></td>
			    	<th rowspan="4" width=100>工序内容
			    		<input type="file" accept="image/*" id="foOpcontent" name="foOpcontent" style="width:90%" onchange="previewImage(this)"/>
			    	</th>
			    	<td rowspan="4" colspan="3" id="picTD">
			    	<!--<input id="foOpcontent"  name="foOpcontent" class="mini-textarea" emptyText="请输入工序内容" width="100%" height="100%"/>-->
			    		<div id="preview"><img id="imghead" src="" width="300" height="100"></div>
			    	</td>
			   	<tr><th>工序名称*</th>
			   		<td><input id="foOpName"  name="foOpName" class="mini-textbox"  width="100%" required="true" /></td>
			   	</tr>
			   	<tr><th>工种*</th>
			        <td><input id="workBranchId"  name="workBranchId" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    					url="GetWorkBranchServlet"  allowInput="true" showNullItem="true" nullItemText="请选择..." required="true"/>  
            		</td>
            	</tr>
			    <tr><th>工序总时间(min)*</th>
			    	<td><input id="operTime"  name="operTime" class="mini-textbox"  width="100%" required="true" vtype="float"/></td>
			    </tr>
			    <tr><th>额定工时(min)*</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox"  width="100%" required="true" vtype="float"/></td>
			    
			        <th width="12%">计划工时(min)</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox"  width="100%" vtype="float"/></td>
			    	<th width="12%">辅助工时(min)</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox"  width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    	<th width="12%">检验工时(min)</th>
			    	<td><input id="inspTime"  name="inspTime" class="mini-textbox"  width="100%" vtype="float"/></td>
			    	<th>是否外协</th>
			        <td><input id="isCo"  name="isCo" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    					url="data/trueOrFalse.txt"  allowInput="false" showNullItem="true" nullItemText="请选择..." value="${order.isWaiXie}"/>  
            		</td>
            		<th>是否设检</th>
			        <td><input id="isInsp"  name="isInsp" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    					url="data/trueOrFalse.txt" value="1"  allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            		</td>
                 </tr> 
			     <tr>
			     	<th>设备编号</th>
			    	<td><input id="equipCode" name="equipCode" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEditMachine" allowInput="false"/></td>
			    	<th>制造工段</th>
			    	<td><select id="WCID" name="WCID" style="width:130px;"><option>---请输入---</option>
				    		<option value="1" selected>---暂无数据---</option>
				    	</select>
				   	</td> 
					<th>PDM工段</th>
					<td><input id="equipType"  name="equipType" class="mini-textbox"  width="100%"/></td>
    			</tr>                    
			    <tr> 
			    	<th>关键工序</th>
			        <td><input id="isKey"  name="isKey" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    					url="data/trueOrFalse.txt" value="0"  allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            		</td>
			    	<th>是否军检</th>
			        <td><input id="isArmInsp"  name="isArmInsp" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    					url="data/trueOrFalse.txt" value="0" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            		</td>
                    <th>持证上岗</th>
			        <td><input id="isCerTop"  name="isCerTop" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    					url="data/trueOrFalse.txt" value="1" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            		</td>
            	</tr>
            	<tr>
            		<th>上传工艺文件</th>
			        <td align="left"><input type="file" name="craftPaper"/></td>
					<td><a class="mini-button" iconCls="icon-save" plain="false"  onclick="toservlet();">保存</a>
						<a class="mini-button" iconCls="icon-undo" plain="false"  onclick="javascript:window.history.back(-1);">返回</a>
					</td>
					<input type="hidden" id="productType" name = "productType" value="${productType}"/>
					<input type = "hidden" name = "productId" value="${productId}"/>
					<input type = "hidden" name = "issueNum" value="${issueNum}"/>
					<input type = "hidden" name = "orderId" value="${orderId}"/>
					<input type = "hidden" name = "foId" value="${foId}"/>
					<input type = "hidden" name = "drawingId" value="${drawingId}"/>
					
				</tr>
			</table>
		</form>
		</div>
		</div>
	</form>
	
	<div id="grid1" class="mini-datagrid" style="width:100%;height:100%;"
         borderStyle="border:0;" multiSelect="true" idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="BaseServlet?meth=CraftworkList1&productId=${productId}&issueNum=${issueNum}">
        <div property="columns">
            <div type="indexcolumn"></div>
            <div name="action" width="40" headerAlign="center" align="center" renderer="onOperatePower"cellStyle="padding:0;">操作</div>
            <div field="foNo" width="80" headerAlign="center">工序编号</div>
            <div field="foOpName" width="100" headerAlign="center">工序名称</div>
            <div field="operTime" width="50" headerAlign="center">工序时间</div>
            <div field="ratedTime" width="50" headerAlign="center">额定工时</div>
            <div field="planTime" width="50" headerAlign="center">计划工时</div>
            <div field="operAidTime" width="50" headerAlign="center">辅助工时</div>
            <div field="inspTime" width="50" headerAlign="center">检验工时</div>
            <div field="foOpcontent" width="170" headerAlign="center">工序内容</div>
            <div field="isCo" width="50" headerAlign="center" renderer="onGenderRenderer">是否外协</div>
            <div field="isKey" width="50" headerAlign="center" renderer="onGenderRenderer">关键工序</div>
        </div>
    </div>
	
</BODY>
<script>
	mini.parse();
   	var grid = mini.get("grid1");
    grid.load();
    
    setFoNo();
    function setFoNo(){
    	var orderid= '${orderId}';
    	var productid = '${productId}';
    	var issuenum ='${issueNum}';
/*
    	var data = {
    		orderid: '${orderId}',
    		productid : '${productId}',
    		issuenum :'${issueNum}'
    	}
    	*/
    //	 var params = eval("("+mini.encode(data)+")");
    	jQuery.ajax({
			type: "POST",
			url: "SetFoNo?productid="+productid+"&issuenum="+issuenum,
			dataType: "json", 
			cache: false,
			enctype: 'multipart/form-data',
		//	data: params,
			processData: false,
			contentType: false,
			success: function (data) {
				mini.get("foNo").setValue(data);
			}
		});
    }
    
    
    function onOperatePower(e) {
		var str = "";
		str += "<span>";
		str += "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.foNo+"\',\'" + e.row.foId+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;&nbsp;</span></a>";
		str += "</span>";
		str += "<span>";
		str += "<a style='margin-right:2px;' title='刀量夹具' href=javascript:onCMTYF(\'" + e.row.productId+"\',\'" + e.row.issueNum+"\',\'" + e.row.foNo+"\',\'" + e.row.foId+"\') ><span class='mini-button-text mini-button-icon icon-cut'>&nbsp;&nbsp;</span></a>";
		str += "</span>";
		return str;
	};
	    
	function ondEdit(foNo,foId){
 		var productType = window.parent.document.getElementById("productType").value;
		var productId = window.parent.document.getElementById("productId").innerHTML;
		var issueNum = window.parent.document.getElementById("issueNum").innerHTML;
	    window.parent.document.getElementById("subIframe").src="BaseServlet?meth=FodetailData&pathTo=adjustFoData&productType="+productType+"&productId="+productId+"&issueNum="+issueNum+"&foNo="+foNo+"&foId="+foId;
	}
	
	function onCMTYF(productId,issueNum,foNo,foId){
	     window.location.href="BaseServlet?meth=Gofocmtyf&pathTo=focmtyf&productId="+productId+"&issueNum="+issueNum+"&foNo="+foNo+"&foId="+foId;	    
	}
		
	function refresh(){
		grid.reload();
	}
	/*
	function toservlet(){
 		var form = new mini.Form("#form1");
 		form.validate();
        if (form.isValid() == false) {
          	return;
        }else{
          	var data = form.getData();
          	data.productType = '${productType}';
          	data.productId = '${productId}';
          	data.issueNum = '${issueNum}';
            var params = eval("("+mini.encode(data)+")");
            var url = 'BaseServlet?meth=Addcraftwork&pathTo=';
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
   	
   	function toservlet(){
 		  	var form1 = new mini.Form("#form1");
   			form1.validate();
            if (form1.isValid() == false) {
            	return;
            }else{
 		  		jQuery.ajax({
      				type: "POST",
      				url: "BaseServlet?meth=Addcraftwork1&pathTo=",
      				dataType: "json", 
      				cache: false,
      				enctype: 'multipart/form-data',
      				data: new FormData($('#form1')[0]),
      				processData: false,
    				contentType: false,
      				success: function (data) {
        				//alert(data.result);
        				mini.confirm(data.result, "刷新页面 ？",
		                 function (action){            
		                     if (action == "ok") {
		                    	window.location.href = window.location.href;	
		                     }
		                 }
		             );
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
       
       /*
       document.getElementById('foOpcontent').onchange = function(evt) {  
   			// 如果浏览器不支持FileReader，则不处理  
    		if (!window.FileReader) {
    			alert(evt.target.value);
    			var value = evt.target.result;
    			var image = document.getElementById("foOpcontentPic");
       			image.src=value;
    		} 
    		var files = evt.target.files; 
    		for (var i = 0, f; f = files[i]; i++) {  
        		if (!f.type.match('image.*')) {  
            		continue;  
        		}  
        		var reader = new FileReader();  
        		reader.onload = (function(theFile) {  
            		return function(e) {  
               			 // img 元素  
                		var pic = document.getElementById('foOpcontentPic');
                		var picTD = document.getElementById('picTD');
                		pic.src = e.target.result;
                		if(null==picTD.offsetHeight||undefined==picTD.offsetHeight||''==picTD.offsetHeight){
                			pic.height = 100;
                			pic.width = 350;
                		}else{
                			pic.height = picTD.offsetHeight;
                			pic.width = picTD.clientWidth;
                		}
                		
            		}; 
        		})(f);  
       			reader.readAsDataURL(f);  
    		}  
		} 
		*/
       
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
</script>
	<script type="text/javascript"> 
		function previewImage(file) { 
			var MAXWIDTH = 100; 
			var MAXHEIGHT = 100; 
			var div = document.getElementById('preview'); 
			if (file.files && file.files[0]) { 
				/*
				div.innerHTML = '<img id=imghead>'; 
				var img = document.getElementById('imghead'); 
				img.onload = function(){ 
					var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight); 
					var picTD = document.getElementById('picTD');
					var height = 0;
					var width = 0;
					if(null==picTD.offsetHeight||undefined==picTD.offsetHeight||''==picTD.offsetHeight){
						height = 100;
						width = 350;
					}else{
						//height = picTD.offsetHeight;
						//width = picTD.clientWidth;
						height = 100;
						width = 350;
					}
					img.width = width; 
					img.height = height; 
					img.style.marginLeft = rect.left+'px'; 
					img.style.marginTop = rect.top+'px'; 
				} 
				var reader = new FileReader(); 
				reader.onload = function(evt){img.src = evt.target.result;} 
				reader.readAsDataURL(file.files[0]); 
			} 
				*/
				var prevDiv = document.getElementById('preview'); 
				var picTD = document.getElementById('preview');
				var height = 0;
				var width = 0;
				if(null==picTD.offsetHeight||undefined==picTD.offsetHeight||''==picTD.offsetHeight){
					height = 100;
					width = 350;
				}else{
					height = picTD.offsetHeight;
					width = picTD.clientWidth;
				}
				var reader = new FileReader();  
				reader.onload = function(evt){ 
					prevDiv.innerHTML = '<img src="' + evt.target.result + '" height="' + height+ '" width="' +width+ '"/>';  
				}    
				reader.readAsDataURL(file.files[0]);  
			}else{ 
				var sFilter='filter:progid:DXImageTransform.Microsoft.AlphaImageLoader(sizingMethod=scale,src="'; 
				file.select(); 
				file.blur();
				var src = document.selection.createRange().text; 
				div.innerHTML = '<img id=imghead>'; 
				var img = document.getElementById('imghead'); 
				//img.filters.item('DXImageTransform.Microsoft.AlphaImageLoader').src = src; 
				var rect = clacImgZoomParam(MAXWIDTH, MAXHEIGHT, img.offsetWidth, img.offsetHeight); 
				status =('rect:'+rect.top+','+rect.left+','+rect.width+','+rect.height); 
				div.innerHTML = "<div id=divhead style='width:300px;height:100px;"+sFilter+src+"\"'></div>"; 
			} 
		} 
		
		function clacImgZoomParam( maxWidth, maxHeight, width, height ){ 
			var param = {top:0, left:0, width:width, height:height}; 
			if( width>maxWidth || height>maxHeight ){ 
				rateWidth = width / maxWidth; 
				rateHeight = height / maxHeight; 

				if( rateWidth > rateHeight ){ 
					param.width = maxWidth; 
					param.height = Math.round(height / rateWidth); 
				}else { 
					param.width = Math.round(width / rateHeight); 
					param.height = maxHeight; 
				} 
			} 
			param.left = Math.round((maxWidth - param.width) / 2); 
			param.top = Math.round((maxHeight - param.height) / 2); 
			return param; 
		} 
	</script> 
</html>