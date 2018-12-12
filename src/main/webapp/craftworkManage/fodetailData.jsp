<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
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
		<table class="data_list_table" align="center" width="100%" border="1" cellspacing="2" cellpadding="0" style="word-break:break-all;border-collapse:collapse" >
			<tr height="22">
		 		<td align="left"><strong><font size="4">FO:工序信息</font></strong></td>
		  	</tr>
		</table>
		  
		<div id="add" style="background:#dafec5" >
		<form name="form1" id="form1" method="post" enctype="multipart/form-data">
			<table class="green_list_table" align="center" width="100%" border="1" style="word-break:break-all;border-collapse:collapse" bgcolor="#dafec5">
			<tr>
				<tr><th width=100>工序编号*</th> 
					<td><input id="foNo"  name="foNo" class="mini-textbox"  width="100%" required="true" readonly value="${result.foNo}"/></td>
			    	<th rowspan="4" width=100>工序内容
			    		<input type="file" accept="image/*" id="foOpcontent" name="foOpcontent" style="width:90%" onchange="previewImage(this)"/>
			    	</th>
			    	<td rowspan="4" colspan="3">
			    		<img src="${result.foOpcontent}" height="100" width="400">
			    		<!--<input id="oper_content"  name="oper_content" class="mini-textarea" emptyText="请输入工序内容" width="100%" height="100%"  value="${result.foOpcontent}"/>-->
			    	</td>
			   	<tr><th>工序名称*</th>
			   		<td><input id="foOpName"  name="foOpName" class="mini-textbox"  width="100%" required="true"  value="${result.foOpName}"/></td>
			   	</tr>
			   	<tr>
			   		<th>工种*</th>
			        <td><input id="workBranchId"  name="workBranchId" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..." 
    					url="GetWorkBranchServlet" value="${result.workBranchId}" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            		</td>
			   	</tr>
			    <tr><th>工序总时间*</th>
			    	<td><input id="operTime"  name="operTime" class="mini-textbox"  width="100%" required="true" vtype="float" value="${result.operTime}"/></td>
			    </tr>
			    <tr><th>额定工时*</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox"  width="100%" vtype="float" value="${result.ratedTime}"/></td>
			    	<th width="12%">计划工时</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox"  width="100%" vtype="float" value="${result.planTime}"/></td>
			    	<th width="12%">辅助工时</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox"  width="100%" vtype="float" value="${result.operAidTime}"/></td>
			    </tr>
			    <tr>
			    	<th width="12%">检验工时</th>
			    	<td><input id="inspTime"  name="inspTime" class="mini-textbox"  width="100%" vtype="float" value="${result.inspTime}"/></td>
			    	<th>是否外协</th>
			        <td><input id="isCo"  name="isCo" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..." value="${result.isCo}"
    					url="data/trueOrFalse.txt" value="0" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            		</td>
            		<th>是否设检</th>
			        <td><input id="isInsp"  name="isInsp" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..." value="${result.isInsp}"
    					url="data/trueOrFalse.txt" value="0"  allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            		</td>
			    </tr>
			    <tr>
			    	<th>设备编号</th>
			    	<td><input id="equipCode" name="equipCode" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEditMachine" allowInput="false" 
			    			value="${result.equipCode}" text="${result.equipCode}" /></td>
			    	<th>制造工段</th>
			    	<td><select id="WCID" name="WCID" style="width:130px;"><option>---请输入---</option>
				    		<option value="1" selected>---暂无数据---</option>
				    	</select>
				   	</td> 
					<th>PDM工段</th>
					<td><input id="equipType"  name="equipType" class="mini-textbox"  width="100%" value="${result.equipType}"/></td>
   				</tr>                    
			    <tr>
			    	<th>关键工序</th>
			        <td><input id="isKey"  name="isKey" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..." value="${result.isKey}"
    					url="data/trueOrFalse.txt" value="0"  allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            		</td>
			    	
			    	<th>是否军检</th>
			        <td><input id="isArmInsp"  name="isArmInsp" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..." value="${result.isArmInsp}"
    					url="data/trueOrFalse.txt" value="0" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            		</td>
			    	<th>持证上岗</th>
			        <td><input id="isCerTop"  name="isCerTop" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..." value="${result.isCerTop}"
    					url="data/trueOrFalse.txt" value="1" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            		</td>
            	</tr>
            	<tr>
            		<th>上传工艺文件</th>
			        <td align="left"><input type="file" name="craftPaper"/></td>
            		<td>工艺文件下载</td>
   					<th><a href="DownLoadOrderFileServlet?filename=${result.craftPaper}">${result.craftPaper}</a><br/></th>
   			
					<td><a class="mini-button" iconCls="icon-save" plain="false"  onclick="toservlet('1');">保存修改</a>
						
					</td>
					<td><a class="mini-button" iconCls="icon-remove" plain="false"  onclick="toservlet('0');">删除此道工序</a>
						<a class="mini-button" plain="false" iconCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a>
					</td>
					<input type="hidden" id="productType" name = "productType" value="${result.productType}"/>
					<input type = "hidden" name = "productId" value="${result.productId}"/>
					<input type = "hidden" name = "issueNum" value="${result.issueNum}"/>
					<input type = "hidden" name = "orderId" value="${result.orderId}"/>
					<input type = "hidden" name = "foId" value="${result.foId}"/>
					<input type = "hidden" id="isInUse" name="isInUse" value=""/>
				</tr>
			</table>
			</form>
		</div>
	
</BODY>
<script>
	mini.parse();
	/*
	function toservlet(isInUse){
 		var form = new mini.Form("#form1");
 		form.validate();
        if (form.isValid() == false) {
          	return;
        }else{
          	var data = form.getData();
          	data.productType = Request['productType'];
          	data.productId = Request['productId'];
          	data.issueNum = Request['issueNum'];
          	data.isInUse = isInUse;
            var params = eval("("+mini.encode(data)+")");
            var url = 'BaseServlet?meth=Updatacraftwork&pathTo=';
 		    jQuery.post(url, params, function(data){
 	   			mini.confirm(data.result, "确定？",
                function (action){            
                    if (action == "ok") {
                    	window.location.href = "BaseServlet?meth=Gofodetail&pathTo=fodetail&productType="+Request['productType']+"&productId="+Request['productId']+"&issueNum="+Request['issueNum'];	
                    }
                });
 	   		},'json');
		}
   	}
   	*/
   	
   	function toservlet(isInUse){
   			//mini.get("isInUse").setValue(isInUse);
 		  	var form1 = new mini.Form("#form1");
   			form1.validate();
            if (form1.isValid() == false) {
            	return;
            }else{
 		  		jQuery.ajax({
      				type: "POST",
      				url: "BaseServlet?meth=Updatacraftwork&isInUse="+isInUse,
      				dataType: "json", 
      				cache: false,
      				enctype: 'multipart/form-data',
      				data: new FormData($('#form1')[0]),
      				processData: false,
    				contentType: false,
      				success: function (data) {
      					var  o = mini.decode(data);
        				alert(o.result);
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
       // mini.get("productType").value = Request['machineId'];
</script>
</html>