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
  		<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm('Y')">保存</a>
  		<span class="separator"></span>
	    <a class="mini-button" plain="false" iconCls="icon-undo" onclick="back()">返回</a>
	    <span class="separator"></span>
	    
	</div>
	<fieldset style="width: 99%;" align="center">
		<legend>
			订单信息
		</legend>
   	<div id= "userdiv">
   		<table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   			<tr>
	   			<td><label for="machineId$text">设备编号</label></td>
	            <td><input id="machineId"  name="machineId" class="mini-textbox" width="100%" required="true" value="${machineDiscard.machineId}" enabled="false"/></td>
	   			<td><label for="deptId$text">部门编号</label></td>
	   			<td><input id="deptId" name="deptId" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" allowInput="false" required="true"  
	   					value="${machineDiscard.deptId}" text="${machineDiscard.deptName}"/></td>
	   			<td><label for="discardDate$text">报废日期</label></td>
	   			<td><input id="discardDate"  name="errorDate" class="mini-datepicker" width="100%" required="true" dateFormat="yyyy-MM-dd" 
	   					allowInput="false" format="yyyy-MM-dd" showTime="false" showOkButton="false" showClearButton="false" value="${machineDiscard.discardDate}"/></td>
		   
	       </tr>
	       <tr>
	       		<td><label for="discardTo$text">废品去向</label></td>
	            <td><input id="discardTo"  name="discardTo" class="mini-textbox" width="100%" required="true" value="${machineDiscard.discardTo}"/></td>
	            <td><label for="discardMoney$text">废品处理收支</label></td>
		        <td><input id="discardMoney"  name="discardMoney" class="mini-textbox" width="100%" required="true" value="${machineDiscard.discardMoney}"  vtype="range:0,9999999"/></td>
	   			<td><label for="principal$text">报废负责人</label></td>
	            <td><input id="principal" name="principal" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEditEmployee" allowInput="false" required="true" text="${machineDiscard.staffName }" value="${machineDiscard.principal}" /></td>

			</tr>
	   		<tr height="60px;">
	   			<td><label for="memo$text">附录</label></td>
		        <td colspan="5"><input id="memo"  name="memo" class="mini-textarea" emptyText="请输入备注" 
		        	width="100%" height="100%" value="${machineDiscard.memo}"/></td>
	         </tr>
   	</table>
   </div>
   </fieldset>
   <script>
   		mini.parse();
   		function getForm(isTogether){
   			var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
            	data.discardDate = mini.get("discardDate").getFormValue();
                var params = eval("("+mini.encode(data)+")");
                var url = 'EditMachineDiscardSpecServlet';
   		        jQuery.post(url, params, callbackFun, 'json');
            }
   		}
   		
   		function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                url: "deptManage/selectDeptTreeWindow.jsp",
                title: "选择部门",
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
                            //mini.get("connector").setValue(data.connector);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                        }
                    }
                }
            });
        }
        
        function onButtonEditEmployee(e) {
            var btnEdit = this;
            mini.open({
                url: "employeeManage/selectEmployeeWindow.jsp",
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
                            btnEdit.setValue(data.staffCode);
                            btnEdit.setText(data.staffName);
                            //mini.get("connector").setValue(data.connector);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                        }
                    }
                }
            });
        }

   		function back(){
			window.history.back(-1);
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
