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
	    <span class="separator"></span>
   		<a class="mini-button" iconCls="icon-remove" plain="false"  onclick="getForm('N')" style="float: right;">取消合作</a>
	    
	</div>
	<fieldset style="width: 100%;" align="center">
		<legend>
			订单信息
		</legend>
   	<div id= "userdiv">
   		<table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
   			<td><label for="companyId$text">客户编号</label></td>
            <td><input id="companyId"  name="companyId" class="mini-textbox" width="100%"  required="true" value="${customer.companyId}" enabled="false"/></td>
   			<td><label for="companyName$text">客户姓名</label></td>
            <td><input id="companyName"  name="companyName" class="mini-textbox"  width="100%" required="true"  value="${customer.companyName} " enabled="false"/></td>
   			<td><label for="type$text">企业类型</label></td>
            <td><input id="type"  name="type" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/companyType.txt" required="true" allowInput="false" showNullItem="true" nullItemText="请选择..." value="${customer.type}"/>  
            </td>
   		</tr>
        <tr>
            <td><label for="connector$text">联系人</label></td>
            <td><input id="connector"  name="connector" class="mini-textbox" width="100%" required="true" value="${customer.connector}"/></td>
            <td><label for="connectorTel$text">联系电话</label></td>
	        <td><input id="connectorTel"  name="connectorTel" class="mini-textbox" width="100%"  onvalidation="isTelephone" required="true" value="${customer.connectorTel}"/></td>
   			<td><label for="header$text">法人代表</label></td>
            <td><input id="header"  name="header" class="mini-textbox"  width="100%"  required="true" value="${customer.header}"/></td>
   		</tr>
   		<tr>
   			<td><label for="address$text">详细地址</label></td>
            <td><input id="address"  name="address" class="mini-textbox" style="width:100%;" required="true" value="${customer.address}"/>  
        	</td><td><label for="postCode$text">邮编</label></td>
            <td><input id="postCode"  name="postCode" class="mini-textbox"  width="100%" required="false" vtype="range:100000,999999" value="${customer.postCode}"/></td>
   			<td><label for="telephone$text">传真电话</label></td>
            <td><input id="telephone"  name="telephone" class="mini-textbox" width="100%" required="false" onvalidation="isOnlyTelephone" value="${customer.telephone}"/></td>
        </tr>
   		<tr>
   			<td><label for="foundingTime$text">成立时间</label></td>
          	<td><input id="foundingTime" name="foundingTime" class="mini-textbox" width="100%"   emptyText="日期格式：2000-01-01"  vtype="date:yyyy-MM-dd" required="false" value="${customer.foundingTime}" enabled="false"/></td>
			<td><label for="employeeNum$text">职工人数</label></td>
	        <td><input id="employeeNum"  name="employeeNum" class="mini-textbox" width="100%"  vtype="range:1,999999" required="false"  value="${customer.employeeNum}"/></td>
   			<td><label for="business$text">主营业务</label></td>
            <td><input id="business"  name="business" class="mini-textbox" width="100%" required="false" value="${customer.business}"/></td>
   		</tr>
   		<tr>
   			<td><label for="webAddress$text">网站地址</label></td>
            <td><input id="webAddress"  name="webAddress" class="mini-textbox"  width="100%" value="${customer.webAddress}"/></td>
   			<td><label for="advise$text">客户建议</label></td>
            <td colspan="3"><input id="advise"  name="advise" class="mini-textbox" width="100%"  value="${customer.advise}"/></td>
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
            	data.isTogether=isTogether;
                var params = eval("("+mini.encode(data)+")");
                var url = 'DoEditCustomerDetailServlet';
   		        jQuery.post(url, params, callbackFun, 'json');
            }
   		}

   		function back(){
			window.history.back(-1);
		}
		
   	    function callbackFun(data)
   	    {
   	         alert(data.result);
   	         window.close();
   	      	 window.opener.location.href = window.opener.location.href;
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
