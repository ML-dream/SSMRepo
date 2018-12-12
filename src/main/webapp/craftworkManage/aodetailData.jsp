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
   
    <title>装配工序</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  <body leftMargin=0 topMargin=0 style="width:100%;height:90%;">
		<table class="data_list_table" align="center" width="100%" border="1" cellspacing="2" cellpadding="0" style="word-break:break-all;border-collapse:collapse" >
			<tr height="22">
		 		<td align="left"><strong><font size="4">FO:装配工艺信息</font></strong></td>
		  	</tr>
		</table>
		  
		<div id="add" name="add" background:#dafec5">
				<form id="form1" name="form1" method="post">
					<TABLE class="green_list_table" align="center" width="100%" border="1" cellspacing="2" cellpadding="0"
						style="word-break: break-all; border-collapse: collapse" bgcolor="#ffffff">
						<tr>
							<th width=100>装配号：</th>
							<td><input id="aoId"  name="aoId" class="mini-textbox" width="100%" required="true" value="${result.aoId}" readonly/></td>
							<th width=100>装配工艺编号：</th>
							<td><input id="aoNo"  name="aoNo" class="mini-textbox"  width="100%" required="true" value="${result.aoNo}" readonly/></td>
							<th>装配名称：</th>
							<td><input id="aoName"  name="aoName" class="mini-textbox"  width="100%" required="true" value="${result.aoName}"/></td>
							</tr>
						<tr>
							<th>工艺版本号：</th>
							<td><input id="aoVer"  name="aoVer" class="mini-textbox"  width="100%" required="true" value="${result.aoVer}"/></td>
							<th width=100>工位号 ：</th>
							<td><input id="workplaceCode"  name="workplaceCode" class="mini-textbox"  width="100%" required="true" value="${result.workplaceCode}"/></td>
							<th>工位名称：</th>
							<td><input id="workplaceName"  name="workplaceName" class="mini-textbox"  width="100%"  required="true" value="${result.workplaceName}"/></td>
						</tr>
						<tr>
							<th>装配时间：</th>
							<td><input id="aoTime"  name="aoTime" class="mini-textbox"  width="100%" required="true" value="${result.aoTime}"/></td>
							<th>装配图号：</th>
							<td><input id="partNo"  name="partNo" class="mini-textbox"  width="100%" required="true" value="${result.partNo}"/></td>
						</tr>
						<tr height="60px;">
	   						<td><label for="aoContent$text">装配内容</label></td>
	        				<td colspan="5"><input id="aoContent" name="aoContent" class="mini-textarea" emptyText="请输入装配内容" value="${result.aoContent}"
	        					width="100%" height="100%"/></td>
	       				 </tr>
						<tr>
							<td align=right><a class="mini-button" iconCls="icon-save" plain="false"  onclick="toservlet()">保存</a></td>
							<!--<td><input type="button" value="清 空" title="清空文本框" onclick="clearOk();" style="cursor: hand;"></td>-->
						</tr>
						<input id="productId" name="productId" class="mini-hidden" value="${result.productId}"/>
						<input id="issueNum" name="issueNum" class="mini-hidden" value="${result.issueNum}"/>
						<input id="orderId" name="orderId" class="mini-hidden" value="${orderId}"/>
					</table>
				</form>
			</div>
	
</BODY>
<script>
	mini.parse();
	
	function toservlet(isInUse){
 		var form = new mini.Form("#form1");
 		form.validate();
        if (form.isValid() == false) {
          	return;
        }else{
          	var data = form.getData();
            var params = eval("("+mini.encode(data)+")");
            var url = 'BaseServlet?meth=UpdateAodetail&pathTo=';
 		    jQuery.post(url, params, function(data){
 	   			mini.confirm(data.result, "确定？",
                function (action){            
                    if (action == "ok") {
                    	window.location.href = window.location.href;
                    	//"BaseServlet?meth=Gofodetail&pathTo=fodetail&productType="+Request['productType']+"&productId="+Request['productId']+"&issueNum="+Request['issueNum'];	
                    }
                });
 	   		},'json');
		}
   	}
   	
   	/*
   	function toservlet(isInUse){
   			//mini.get("isInUse").setValue(isInUse);
 		  	var form1 = new mini.Form("#form1");
   			form1.validate();
            if (form1.isValid() == false) {
            	return;
            }else{
 		  		jQuery.ajax({
      				type: "POST",
      				url: "BaseServlet?meth=Updatacraftwork&pathTo=",
      				dataType: "json", 
      				cache: false,
      				enctype: 'multipart/form-data',
      				data: new FormData($('#form1')[0]),
      				processData: false,
    				contentType: false,
      				success: function (data) {
        				alert(data.result);
        				window.location.href = window.location.href;
      				}
    			});
    		}
   		}
   	*/
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
        mini.get("productType").value = Request['machineId'];
</script>
</html>