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
    <!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>部门信息</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>
  	<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;"> 
	            <a class="mini-button" iconCls="icon-remove" plain="false" onclick="getForm('0')">删除</a>     
	            <span class="separator"></span>             
	            <a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm('1')">保存</a>
	            <span class="separator"></span>             
	            <a class="mini-button" iconCls="icon-undo" plain="false" onclick="javascript:window.history.back(-1);">返回</a>                   
	        </div>
	<fieldset style="width: 99%;" align="center">
		<legend>部门详细信息</legend>
	
   	<div id= "userdiv">
   	<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
   			<td><label for="deptId$text">部门编号</label></td>
            <td><input id="deptId"  name="deptId" class="mini-textbox"  width="100%" required="true" emptyText="10个以内字符" value="${dept.deptId}" enabled="false"/></td>
   			<td><label for="deptName$text">部门名称</label></td>
            <td><input id="deptName"  name="deptName" class="mini-textbox"  width="100%" required="true" emptyText="20个以内汉字" value="${dept.deptName}"/></td>
   			<td><label for="FDeptId$text">上级部门ID</label></td>
   			<td><input id="FDeptId" name="FDeptId" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit"required="false" value="${dept.FDeptId}" text="${dept.deptName}"/></td>
   		</tr>
        <tr>
        	<td><label for="deptLevel$text">部门层次</label></td>
            <td><input id="deptLevel"  name="deptLevel" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/deptLevel.txt" value="2"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..." value="${dept.deptLevel}" enabled="false"/>  
            </td>
            <td><label for="headStaffId$text">部门领导</label></td>
            <td><input id="headStaffId"  name="headStaffId" class="mini-textbox"  width="100%"  value="${dept.headStaffId}"/></td>
   			<td><label for="isKey$text">是否关键部门</label></td>
            <td><input id="isKey"  name="isKey" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/trueOrFalse.txt" value="1"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..." value="${dept.isKey}"/>  
            </td>
        </tr>
   		
   		<tr height="60px;">
   			<td><label for="memo$text">附录</label></td>
	        <td colspan="5"><input id="memo"  name="memo" class="mini-textarea" emptyText="请输入备注" 
	        	width="100%" height="100%" value="${dept.memo}"/></td>
        </tr>
   	</table>
   </div>
   </fieldset>
   <script>
   		mini.parse();
   		
   		function getForm(flag){
			var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
            	data.isAlive=flag;
                var params = eval("("+mini.encode(data)+")");
                var url = 'EditDeptSpecServlet';
   		        jQuery.post(url, params, callbackFun, 'json');
            }
   		}
   		
   		function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                url: "deptManage/selectDeptTreeWindow.jsp",
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
                            btnEdit.setValue(data.deptId);
                            btnEdit.setText(data.deptName);
                            mini.get("deptLevel").setValue(data.deptLevel+1);
                            //mini.get("connector").setValue(data.connector);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                        }
                    }
                }
            });
        }
   		
   	    function callbackFun(data)
   	    {
   	         alert(data.result);
   	      	 window.location.href = window.location.href;
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

   		function isTelephone(){
   			if (e.isValid) {
   				var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
   				var isMobile = /^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/
                if (!isPhone.exec(e.value)&&!isMobile.exec(e.value)) {
                    e.errorText = "必须输入正确电话号码";
                    e.isValid = false;
                }
            }
   			
   	   	}

   		var Genders = [{ id: 'M', text: '男' }, { id: 'W', text: '女'}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
   </script>
  </body>
</html>
