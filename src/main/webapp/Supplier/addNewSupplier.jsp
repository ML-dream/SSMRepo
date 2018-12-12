<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>新增供应商</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/js.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/pagecard.css">
	<style type="text/css">
	<!--
	table {
	    table-layout:fixed;
	    word-break: break-all;
	} 
	-->
	</style>
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  <body>
     <div class="mini-toolbar">
 	 	<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm()">保存</a>
 		<span class="separator"></span>
 	 	<a class="mini-button" iconCls="icon-undo" plain="false" onclick="javascript:window.history.back(-1);">返回</a>
 	</div>
 	
  <fieldset id="supplier" style="width: 100%;" align="center">
  <legend>新增供应商信息</legend>
  <form id="addsupplier" name="addsupplier" action="#" method="post">
  <table  style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
  	<tr  bgcolor=#EBEFF5>
   			<td><label for="companyId$text">客户编号</label></td>
            <td><input id="companyId"  name="companyId" class="mini-textbox" width="100%" required="true" enabled="false" value="${companyId }"/></td>
   			<td><label for="companyName$text">客户名称</label></td>
            <td><input id="companyName"  name="companyName" class="mini-textbox"  width="100%" required="true" /></td>
   			<td><label for="type$text">企业类型</label></td>
            <td><input id="type"  name="type" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="GetCompanyTypeServlet" required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            </td>
       </tr>
       <tr  bgcolor=#EBEFF5>
       		<td><label for="connector$text">联系人</label></td>
            <td><input id="connector"  name="connector" class="mini-textbox" width="100%" required="true"/></td>
            <td><label for="connectorTel$text">联系电话</label></td>
	        <td><input id="connectorTel"  name="connectorTel" class="mini-textbox" width="100%" onvalidation="isTelephone" required="true" /></td>
   			<td><label for="header$text">法人代表</label></td>
            <td><input id="header"  name="header" class="mini-textbox" width="100%"  required="true" /></td>
		</tr>
   		<tr bgcolor=#EBEFF5>
   			<td><label for="address$text">详细地址</label></td>
            <td><input id="address"  name="address" class="mini-textbox"  width="100%" required="true" /></td>
            <td><label for="rate$text">税率(小数)</label></td>
            <td><input id="rate"  name="rate" class="mini-textbox"  width="100%" required="true" /></td>
            <td><label for="telephone$text">传真或电话</label></td>
            <td><input id="telephone"  name="telephone" class="mini-textbox" required="true" onvalidation="isOnlyTelephone" width="100%"/></td>
   			
   		
        </tr>
   		<tr bgcolor=#EBEFF5>
   			<td><label for="postCode$text">邮编</label></td>
            <td><input id="postCode"  name="postCode" class="mini-textbox"  width="100%" vtype="range:100000,999999"/></td>
   			<td><label for="foundingTime$text">成立时间</label></td>
          	<td><input id="foundingTime" name="foundingTime" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd HH:mm:ss" 
          	format="yyyy-MM-dd" showTodayButton="false" showClearButton="false" allowInput="false"/></td>
   			<td><label for="employeeNum$text">职工人数</label></td>
	        <td><input id="employeeNum"  name="employeeNum" class="mini-textbox" width="100%"  vtype="range:1,999999"/></td>
   			
   			
   		</tr>
   		<tr bgcolor=#EBEFF5>
   			<td><label for="business$text">主营业务</label></td>
            <td><input id="business"  name="business" class="mini-textbox" width="100%"/></td>
   			<td><label for="dutyParagraph">税务登记号</label></td>
   			<td><input id="dutyParagraph" name="dutyParagraph" class="mini-textbox" width="100%" /></td>
   			<td><label for="bank$text">开户银行</label></td>
   			<td><input id="bank" name="bank" class="mini-textbox" width="100%"/></td>
   			
   		</tr>
   		<tr bgcolor=#EBEFF5>
   			<td><label for="account$text">账号</label></td>
   			<td><input id="account" name="account" class="mini-textbox" width="100%"/></td>
   			<td><label for="webAddress$text">网络地址</label></td>
            <td><input id="webAddress"  name="webAddress" class="mini-textbox"  width="100%" /></td>
   			<td><label for="advise$text">供应商建议</label></td>
            <td><input id="advise"  name="advise" class="mini-textbox" width="100%"/></td>
   			
   		</tr>
  </table>
  </form>
  </fieldset>
  
  <script type="text/javascript">
  mini.parse();
  function getForm(){
  var form=new mini.Form("addsupplier");
  form.validate();
  if(form.isValid()==false){
  	return;
  }else{
  
  	var data=form.getData();
  	data.foundingTime=mini.get("foundingTime").getFormValue();
  	var json=mini.encode(data);
  	$.ajax({
 		type:"post",
 		url:"addSupplierServlet",
 		data:{submitData:json},
 		dataType:"json",
 		success: function(data){
 			alert(data.result);
 			
  			if(data.status==1){
  				window.location.href=window.location.href;
  			}
 		}
  	});
  }
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
