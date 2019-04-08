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
		<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
    <title>添加客户</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>
  <div class="mini-toolbar">
  		<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm()">保存</a>
  </div>	
	<fieldset style="width: 100%;" align="center">
		<legend>
			添加客户
		</legend>
   	<div id= "userdiv">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
   			<td><label for="companyId$text">客户编号※</label></td>
            <td><input id="companyId"  name="companyId" class="mini-textbox" emptyText="不必填写，此项自动生成！" width="100%"  required="true" allowInput="false"/></td>
   			<td><label for="companyName$text">客户名称※</label></td>
            <td><input id="companyName"  name="companyName" class="mini-textbox"  width="100%" required="true" onblur="search"/></td>
   			<td><label for="type$text">企业类型※</label></td>
            <td><input id="type"  name="type" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="GetCompanyTypeServlet" value="CC"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            </td>
       </tr>
       <tr>
       		<td><label for="connector$text">联系人※</label></td>
            <td><input id="connector"  name="connector" class="mini-textbox" width="100%" required="true"/></td>
            <td><label for="connectorTel$text">联系电话※</label></td>
	        <td><input id="connectorTel"  name="connectorTel" class="mini-textbox" width="100%" onvalidation="isTelephone" required="true" emptyText="025-88888888或13612345678" /></td>
	        <td><label for="address$text">详细地址</label></td>
            <td><input id="address"  name="address" class="mini-textbox"  width="100%"  /></td>
		</tr>
   		<tr>
   		   	<td><label for="header$text">法人代表</label></td>
            <td><input id="header"  name="header" class="mini-textbox" width="100%"   /></td>
            <td><label for="telephone$text">传真或电话</label></td>
            <td><input id="telephone"  name="telephone" class="mini-textbox"  width="100%"  /></td>
   			<td><label for="postCode$text">邮编</label></td>
            <td><input id="postCode"  name="postCode" class="mini-textbox"  width="100%" vtype="range:100000,999999" /></td>
        </tr>
   		<tr>
   			<td><label for="foundingTime$text">成立时间</label></td>
          	<td><input id="foundingTime" name="foundingTime" class="mini-textbox" width="100%"   emptyText="日期格式：2000-01-01"  vtype="date:yyyy-MM-dd" /></td>
   			<td><label for="employeeNum$text">职工人数</label></td>
	        <td><input id="employeeNum"  name="employeeNum" class="mini-textbox" width="100%"  vtype="range:1,999999"  /></td>
   			<td><label for="business$text">主营业务</label></td>
            <td><input id="business"  name="business" class="mini-textbox" width="100%"   /></td>
   			
   		</tr>
   		<tr>
   			<td><label for="webAddress$text">网络地址</label></td>
            <td><input id="webAddress"  name="webAddress" class="mini-textbox"  width="100%" /></td>
   			<td><label for="advise$text">客户建议</label></td>
            <td colspan="3"><input id="advise"  name="advise" class="mini-textbox" width="100%"/></td>
   		</tr>
   		<!-- <tr>
   			<td><label for="connector2$text">备用联系人2 </label></td>
          	<td><input id="connector2"  name="connector2" class="mini-textbox" width="100%" /></td>
   			<td><label for="connector3$text">备用联系人3 </label></td>
	        <td><input id="connector3"  name="connector3" class="mini-textbox" width="100%" /></td>
   			<td><label for="connector4$text">备用联系人4 </label></td>
            <td><input id="connector4"  name="connector4" class="mini-textbox" width="100%" /></td>
   		</tr>
   		<tr>
            <td><label for="connector2Tel$text">联系人2电话</label></td>
	        <td><input id="connector2Tel"  name="connector2Tel" class="mini-textbox" width="100%"  required="false" emptyText="025-88888888或13612345678" /></td>
	         <td><label for="connector3Tel$text">联系人3电话</label></td>
	        <td><input id="connector3Tel"  name="connector3Tel" class="mini-textbox" width="100%"  required="false" emptyText="025-88888888或13612345678" /></td>
	         <td><label for="connector4Tel$text">联系人4电话</label></td>
	        <td><input id="connector4Tel"  name="connector4Tel" class="mini-textbox" width="100%"  required="false" emptyText="025-88888888或13612345678" /></td>
		</tr> -->
   	</table>
   </fieldset>	
   </div>
   
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
                var url = 'AddCustomerServlet';
   		        jQuery.post(url, params, function(data){
   	   		        //mini.alert(data);
   	   		        //window.location.href="customerManage/showCustomer.jsp";
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
   	    function callbackFun(data)
   	    {
   	        alert(data.result);
   	         //window.location.href=window.location.href;
   	      	window.location.href="employeeManage/editEmployee.jsp";
   	    }
        

        var now = new Date();
		var hour = ((now.getHours() < 10) ? "0" : "") + now.getHours();
		var min = ((now.getMinutes() < 10) ? "0" : "") + now.getMinutes()
		var millSec = (now.getMilliseconds()).toString().substring(0,1);
		var nowRight =hour+min+sec;


       function search(){
   		        var form = new mini.Form("#userdiv");
                var data = form.getData();
          	    data.companyName =mini.get("companyName").getValue();
                var params = eval("("+mini.encode(data)+")");
                var url = 'CheckCustomerNameServlet';
   		        jQuery.post(url, params, function(data){
   		        if((data.total)>=1)
   		        alert("该客户已存在");
   		        else
   		        {
   		         var totalcount=data.totalcount+1;
   		         if(totalcount>0&&totalcount<10)
   		         totalcount="00"+totalcount;
   		         else if(totalcount>=10&&totalcount<100)
   		         totalcount="0"+totalcount;
   		         mini.get("companyId").setValue(totalcount);
   		         }
   	   		        },'json');
   	   		   
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
