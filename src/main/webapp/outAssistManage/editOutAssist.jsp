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
	    <a class="mini-button" plain="false" iconCls="icon-undo" onclick="back()">返回</a>
	    <span class="separator"></span>
	    
	</div>
	<fieldset style="width: 99%;" align="center">
		<legend>
			新建外协订单
		</legend>
   	<div id= "userdiv">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
   			<td><label for="orderId$text">外协单号</label></td>
            <td><input id="orderId"  name="orderId" class="mini-textbox" width="100%" required="true" enabled="false" value="${result.orderId}"/></td>
   			<td><label for="deptId$text">外协部门</label></td>
            <td><input id="deptId" name="deptId" class="mini-buttonedit" width="100%"  value="${result.deptId}" text="${result.deptName}"
            		onbuttonclick="onButtonEditDept" allowInput="false" required="true"/></td>
	        <td><label for="companyId$text">公司编号</label></td>
            <td><input id="companyId" name="companyId" class="mini-buttonedit" width="100%"  value="${result.companyId}" text="${result.companyId}"
            		onbuttonclick="onButtonEdit" required="true" allowInput="false"/>
       </tr>
       <tr>
       		<td><label for="companyName$text">公司名称</label></td>
            <td><input id="companyName"  name="companyName" class="mini-textbox" width="100%" required="true" enabled="false" value="${result.companyName}"/></td>
            <td><label for="principal$text">联络人</label></td>
            <td><input id="principal" name="principal" class="mini-buttonedit" width="100%"  value="${result.principal}" text="${result.principalName}"
            		onbuttonclick="onButtonEditEmployee" required="true" allowInput="false"/>
	        <td><label for="connectorName$text">联系人名称</label></td>
            <td><input id="connectorName" name="connectorName" class="mini-textbox" width="100%"  required="true"  value="${result.connectorName}"/></td>
		</tr>
   		<tr>
   			<td><label for="connectorTel$text">联系人电话</label></td>
            <td><input id="connectorTel"  name="connectorTel" class="mini-textbox" width="100%" required="true" onvalidation="isTelephone" value="${result.connectorTel}"/></td>
   			<td><label for="startDate$text">下单时间</label></td>
            <td><input id="startDate"  name="startDate" class="mini-datepicker" width="100%" required="true" allowInput="false"  value="${result.startDate}"
	   				dateFormat="yyyy-MM-dd  HH:mm:ss" format="yyyy-MM-dd" showTime="false" showOkButton="false" showClearButton="false"/>
	   		</td>
   			<td><label for="planEndDate$text">计划收货时间</label></td>
            <td><input id="planEndDate"  name="planEndDate" class="mini-datepicker" width="100%" required="true" allowInput="false"  value="${result.planEndDate}"
	   				dateFormat="yyyy-MM-dd  HH:mm:ss" format="yyyy-MM-dd" showTime="false" showOkButton="false" showClearButton="false"/>
	   		</td>
        </tr>
   		<tr>
   			<td><label for="trueEndDate$text">实际收货时间</label></td>
          	<td><input id="trueEndDate"  name="trueEndDate" class="mini-datepicker" width="100%" allowInput="false"  value="${result.trueEndDate}"
	   				dateFormat="yyyy-MM-dd  HH:mm:ss" format="yyyy-MM-dd" showTime="false" showOkButton="false" showClearButton="false"/>
	   		</td>
	   		<td><label for="fine$text">罚款</label></td>
	        <td><input id="fine"  name="fine" class="mini-textbox" width="100%"  vtype="float" value="${result.fine}"/></td>
   			<td><label for="shouldPay$text">应付款</label></td>
            <td><input id="shouldPay"  name="shouldPay" class="mini-textbox" width="100%" vtype="float" value="${result.shouldPay}"/></td>
   		</tr>
   		<tr>
   			<td><label for="alreadyPay$text">已付款</label></td>
            <td><input id="alreadyPay"  name="alreadyPay" class="mini-textbox" vtype="float" width="100%"  value="${result.alreadyPay}"/></td>
            <td><label for="notPay$text">未付款</label></td>
            <td><input id="notPay"  name="notPay" class="mini-textbox" vtype="float" width="100%"  value="${result.notPay}"/></td>
            <td><label for="isBusy$text">是否急件</label></td>
            <td><input id="isBusy"  name="isBusy" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/trueOrFalse.txt" value="0"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..." value="${result.isBusy}"/>  
            </td>
        </tr>
   		<tr height="60px;">
   			<td><label for="memo$text">附录</label></td>
	        <td colspan="5"><input id="memo" name="memo" class="mini-textarea" emptyText="请输入备注"  value="${result.memo}"
	        	width="100%" height="100%"/></td>
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
            	data.startDate = mini.get("startDate").getFormValue();
            	data.planEndDate = mini.get("planEndDate").getFormValue();
            	data.trueEndDate = mini.get("trueEndDate").getFormValue();
                var params = eval("("+mini.encode(data)+")");
                var url = 'EditOutAssistSpecServlet';
   		        jQuery.post(url, params, callbackFun, 'json');
            }
   		}
   		
   		
   	    function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                url: "outAssistComManage/selectOutAssistComWindow.jsp",
                title: "选择列表",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.companyId);
                            btnEdit.setText(data.companyId);
                            mini.get("companyName").setValue(data.companyName);
                        }
                    }
                }
            });
        }
   	    
   	    function onButtonEditDept(e) {
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
