<%@ page language="java" import="java.util.*,com.wl.forms.Employee" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>客户详细信息</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>
  	<div class="mini-toolbar">
  		<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm()">保存</a>
  		<span class="separator"></span>
  		<a class="mini-button" plain="false" iconCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a>
  	</div>
	<fieldset style="width: 50%;" align="center">
		<legend>
			修改密码
		</legend>
   	<div id= "userdiv">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
   			<td><label for="userId$text">用户号(登陆账户)</label></td>
            <td><input id="userId"  name="userId" class="mini-textbox" width="100%" required="true" value="${result.userId}" enabled="false" /></td>
   			<td><label for="staffCode$text">员工号</label></td>
            <td><input id="staffCode" name="staffCode" class="mini-buttonedit" width="100%" value="${result.staffCode}" text="${result.staffCode}"
            		onbuttonclick="onButtonEditEmployee" required="true" allowInput="false" enabled="false" />
        </tr>
       	<tr>
   			<td><label for="userName$text">员工姓名</label></td>
            <td><input id="userName" name="userName" class="mini-textbox" width="100%" required="true" enabled="false" value="${result.userName}"/></td>
       		<td><label for="password$text">密码</label></td>
            <td><input id="password" name="password" class="mini-password" width="100%"/></td>
       	</tr>
       	<tr>
       		<td><label for="newpassword$text">新密码</label></td>
            <td><input id="newpassword" name="newpassword" class="mini-password" width="100%"/></td>
       		<td><label for="Confirmpassword$text">密码确认</label></td>
            <td><input id="Confirmpassword" name="Confirmpassword" class="mini-password" width="100%"/></td>
        </tr>
   	</table>
   </div>
   </fieldSet>
   <script>
   		mini.parse();
   		function getForm(){
   			var newpassword = mini.get("newpassword").value;
   			var Confirmpassword = mini.get("Confirmpassword").value;
   			if(newpassword==Confirmpassword){
   			}else{
   				mini.alert("密码不匹配，请确认新密码！");
   				return;
   			}
   			var form = new mini.Form("#userdiv");//?????
   			form.validate();                    //???
   			
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
                var params = eval("("+mini.encode(data)+")");
                var url = 'DoEditSysUsersServlet';
   		        var url = 'DoEditSysUsersServlet';		//参数1 表示重置密码
   		        $.post(url, params, function(data){
   		        	alert(data.result);
   	   		  		window.location.href = "ToLogOut";
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
