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
  		<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm('1')">保存</a>
  		<span class="separator"></span>
	    <a class="mini-button" plain="false" iconCls="icon-undo" onclick="back()">返回</a>
	    <span class="separator"></span>
   		<a class="mini-button" iconCls="icon-remove" plain="false"  onclick="getForm('0')" style="float: right;">取消合作</a>
	    <span class="separator"></span>
	</div>
  	<fieldset style="width: 99%;" align="center">
		<legend>
			外协单位信息
		</legend>
   	<div id= "userdiv">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
   			<td><label for="companyId$text">加工单位编号</label></td>
            <td><input id="companyId"  name="companyId" class="mini-textbox" width="100%"  required="true" value="${result.companyId}" enabled="false"/></td>
   			<td><label for="companyName$text">加工单位名称</label></td>
            <td><input id="companyName"  name="companyName" class="mini-textbox"  width="100%" required="true"  value="${result.companyName}" enabled="false"/></td>
   			<td><label for="type$text">企业类型</label></td>
            <td><input id="type"  name="type" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="GetCompanyTypeServlet" required="true" allowInput="false" showNullItem="true" nullItemText="请选择..." value="${result.type}"/>  
            </td>
       </tr>
       
   		<tr>
   			<td><label for="connector$text">联系人</label></td>
            <td><input id="connector"  name="connector" class="mini-textbox" width="100%" required="true" value="${result.connector}"/></td>
            <td><label for="connectorTel$text">联系人电话</label></td>
	        <td><input id="connectorTel" name="connectorTel" class="mini-textbox" width="100%" onvalidation="" required="true" value="${result.connectorTel}"/></td>
   			<td><label for="address$text">详细地址</label></td>
            <td><input id="address"  name="address" class="mini-textbox"  width="100%" required="true"  value="${result.address}"/></td>
   			
	    </tr>
   		<tr>
   			<td><label for="employeeNum$text">职工人数</label></td>
	        <td><input id="employeeNum"  name="employeeNum" class="mini-textbox" width="100%"  vtype="int" required="false"  value="${result.employeeNum}"/></td>
   			<td><label for="header$text">法人代表</label></td>
            <td><input id="header"  name="header" class="mini-textbox" width="100%"  required="false" value="${result.header}" /></td>
   			<td><label for="postCode$text">邮编</label></td>
            <td><input id="postCode"  name="postCode" class="mini-textbox"  width="100%" vtype="" required="false"  value="${result.postCode}"/></td>
   			
        </tr>
        <tr>
        	<td><label for="business$text">主营业务</label></td>
            <td><input id="business"  name="business" class="mini-textbox" width="100%"  required="false"  value="${result.business}"/></td>
       		<td><label for="telephone$text">传真或电话</label></td>
            <td><input id="telephone"  name="telephone" class="mini-textbox" onvalidation="" width="100%" required="false"  value="${result.telephone}"/></td>
   			<td><label for="foundingTime$text">成立时间</label></td>
   			<td><input id="foundingTime"  name="foundingTime" class="mini-datepicker" width="100%" required="false" allowInput="false"  value="${result.foundingTime}"
	   				dateFormat="yyyy-MM-dd  HH:mm:ss" format="yyyy-MM-dd" showTime="false" showOkButton="false" showClearButton="false"/>
	   		</td>
	   </tr>
   	   <tr>     
	        <td><label for="connectorEmail$text">联系人邮箱</label></td>
	        <td><input id="connectorEmail"  name="connectorEmail" class="mini-textbox" width="100%" vtype="email" value="${result.connectorEmail}"/></td>
   			<td><label for="connectorQQ$text">联系人QQ</label></td>
	        <td><input id="connectorQQ"  name="connectorQQ" class="mini-textbox" width="100%" value="${result.connectorQQ}"/></td>
   			<td><label for="webAddress$text">公司网址地址</label></td>
            <td><input id="webAddress"  name="webAddress" class="mini-textbox"  width="100%"  value="${result.webAddress}"/></td>
       	</tr>
   		<tr>
            <td><label for="bank$text">开户行</label></td>
            <td><input id="bank"  name="bank" class="mini-textbox"  width="100%"  value="${result.bank}"/></td>
            <td><label for="account$text">银行账号</label></td>
            <td><input id="account"  name="account" class="mini-textbox"  width="100%" value="${result.account}" /></td>
            <td><label for="dutyParagraph$text">税号</label></td>
            <td><input id="dutyParagraph"  name="dutyParagraph" class="mini-textbox"  width="100%"  value="${result.dutyParagraph}"/></td>
        </tr>
   		<tr>
            <td><label for="founding$text">注册资金(万元)</label></td>
            <td><input id="founding"  name="founding" class="mini-textbox"  width="100%" vtype="range:0,100000000" value="${result.founding}"/></td>
            <td><label for="passRate$text">合格率(%)</label></td>
            <td><input id="passRate"  name="passRate" class="mini-textbox"   width="75%" readonly value="${result.passRate}"/>
            	<a id= "passRateB" name = "passRateB" class="mini-button" iconCls="icon-update" plain="false"  onclick="passRate()" width="20%">更新</a>
            </td>
   		</tr>
   		<tr>
   			<td><label for="connector2$text">备用联系人2 </label></td>
          	<td><input id="connector2"  name="connector2" class="mini-textbox" width="100%" value="${result.connector2}"/></td>
   			<td><label for="connector3$text">备用联系人3 </label></td>
	        <td><input id="connector3"  name="connector3" class="mini-textbox" width="100%" value="${result.connector3}"/></td>
   			<td><label for="connector4$text">备用联系人4 </label></td>
            <td><input id="connector4"  name="connector4" class="mini-textbox" width="100%" value="${result.connector4}"/></td>
   		</tr>
   		<tr>
            <td><label for="connector2Tel$text">联系人2电话</label></td>
	        <td><input id="connector2Tel"  name="connector2Tel" class="mini-textbox" width="100%"  required="false" emptyText="025-88888888或13612345678" value="${result.connector2Tel}"/></td>
	         <td><label for="connector3Tel$text">联系人3电话</label></td>
	        <td><input id="connector3Tel"  name="connector3Tel" class="mini-textbox" width="100%"  required="false" emptyText="025-88888888或13612345678" value="${result.connector3Tel}"/></td>
	         <td><label for="connector4Tel$text">联系人4电话</label></td>
	        <td><input id="connector4Tel"  name="connector4Tel" class="mini-textbox" width="100%"  required="false" emptyText="025-88888888或13612345678" value="${result.connector4Tel}"/></td>
		</tr>
   		<tr height="60px;">
   			<td><label for="memo$text">附录</label></td>
	        <td colspan="5"><input id="memo" name="memo" class="mini-textarea" emptyText="请输入备注" value="${result.memo}"
	        	width="100%" height="100%"/></td>
        </tr>
   	</table>
   </div>
   </fieldSet>
   <script>
   		mini.parse();
   		passRate();
   		function getForm(isTogether){
   			var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
            	data.isTogether=isTogether;
            	data.foundingTime = mini.get("foundingTime").getFormValue();
                var params = eval("("+mini.encode(data)+")");
                var url = 'EditOutAssistComSpecServlet';
   		        jQuery.post(url, params, callbackFun, 'json');
            }
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
        function passRate(){
   	   		var comId = mini.get(companyId).getValue();
	   	   		$.ajax({
				type: "post",
				url: "PassRate?comId="+comId,
				dataType: "json",
				async:false,
				cache:false,
				success: function (text){
					mini.get("passRate").setValue(text);
				},
				error: function(){
				}
			});
   	   	}
   </script>
  </body>
</html>
