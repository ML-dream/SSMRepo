<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <!-- miniui.js -->
	    <script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
	<%-- 	<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script> --%>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<%-- <link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link> --%>
    <title>注册用户</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>
  	<div class="mini-toolbar">
  		<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm()">保存</a>
  	</div>
	<fieldset style="width: 50%;" align="center">
		<legend>
			注册用户
		</legend>
   	<div id= "userdiv">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
   			<td><label for="userId$text">用户号</label></td>
            <td><input id="userId"  name="userId" class="mini-textbox" width="100%"  required="true"/></td>
   			<td><label for="staffCode$text">员工号</label></td>
            <td><input id="staffCode" name="staffCode" class="mini-buttonedit" width="100%" 
            		onbuttonclick="onButtonEditEmployee" required="true" allowInput="false"/>
        </tr>
       	<tr>
   			<td><label for="userName$text">员工名称</label></td>
            <td><input id="userName" name="userName" class="mini-textbox" width="100%" required="true" enabled="false"/></td>
       		<td><label for="password$text">密码</label></td>
            <td><input id="password" name="password" class="mini-textbox" width="100%" required="true"/></td>
       	</tr>
       	<tr>
            <td><label for="authority$text">权限</label></td>
            <td><input id="authority"  name="authority" class="mini-combobox" style="width:100%;" textField="authorityName" valueField="authorityId" emptyText="请选择..."
    			url="GetAuthorityServlet" value="0"  required="true" allowInput="false" showNullItem="true" enabled="false" nullItemText="请选择..."/>  
            </td>
	        
		</tr>
   	</table>
   </div>
   </fieldSet>
   <script>
   		mini.parse();
   		function getForm(){
   			var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
                var params = eval("("+mini.encode(data)+")");
                var url = 'AddSysUsersServlet';
   		        jQuery.post(url, params, function(data){
   	   		  		mini.confirm(data.result, "确定？",
		                 function (action){            
		                     if (action == "ok") {
		                    	window.location.href = window.location.href;	
		                     }
		                 }
		             );
   	   		        },'json');
            }
   		}
   		
   		function onButtonEditEmployee(e) {
            var btnEdit = this;
            mini.open({
                url: "employeeManage/selectEmployeeWindow.jsp",
                title: "选择员工",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.staffCode);
                            btnEdit.setText(data.staffCode);
                            mini.get("userName").setValue(data.staffName);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                        }
                    }
                }
            });
        }
        
   	    function callbackFun(data)
   	    {
   	        alert(data.result);
   	         //window.location.href=window.location.href;
   	      	window.location.href="employeeManage/editEmployee.jsp";
   	    }

   		function onIDCardsValidation(e) {
            if (e.isValid) {
                var pattern = /\d*/;
                if (e.value.length < 15 || e.value.length > 18 || pattern.test(e.value) == false) {
                    e.errorText = "必须输入15~18位数字";
                    e.isValid = false;
                }
            }
        }

   	//验证QQ号码5-11位
   		function isQQ(e) {
   			if (e.isValid) {
                var pattern = /^\s*[.0-9]{5,11}\s*$/;
                if (!pattern.test(e.value)) {
                    e.errorText = "必须输入正确QQ号";
                    e.isValid = false;
                }
            }
   		}

   		//校验手机号码
   		function isMobile(e) {
   		    //var patrn = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
   		    if (e.isValid) {
   		    	var pattern = /^(13[0-9]{9})|(14[0-9])|(18[0-9])|(15[0-9][0-9]{8})$/;
                if (!pattern.exec(e.value)) {
                    e.errorText = "必须输入正确手机号码";
                    e.isValid = false;
                }
            }
   		}

   		function isTelephone(e){
   			if (e.isValid) {
   				var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
   				var isMobile = /^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/
                if (!isPhone.exec(e.value)&&!isMobile.exec(e.value)) {
                    e.errorText = "必须输入正确电话号码";
                    e.isValid = false;
                }
            }
   			
   	   	}

   		function isOnlyTelephone(e){
   			if (e.isValid) {
   				var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
   				if (!isPhone.exec(e.value)) {
                    e.errorText = "必须输入正确电话号码";
                    e.isValid = false;
                }
            }
   			
   	   	}
   </script>
  </body>
</html>
