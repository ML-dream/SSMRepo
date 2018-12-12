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
    <title>添加工时单价</title>
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
			添加工时单价
		</legend>
   	<div id= "userdiv">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
   			<td><label for="craftId$text">工艺编号</label></td>
            <td><input id="craftId"  name="craftId" class="mini-textbox" width="100%"  required="true"/></td>
   			<td><label for="craftName$text">工艺名称</label></td>
            <td><input id="craftName"  name="craftName" class="mini-textbox"  width="100%" required="true" /></td>
   		</tr>
   		<tr>
   			<td><label for="price$text">单价</label></td>
            <!-- 
            <td><input id="price"  name="price" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/companyType.txt" value="SY"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            </td>
             -->
            <td><input id="price"  name="price" class="mini-textbox"  width="100%" required="true" vtype="float" /></td>
            <td><label for="unit$text">单位</label></td>
            <td><input id="unit"  name="unit" class="mini-textbox"  width="100%" required="true" /></td>
       </tr>
   	</table>
   </div>
    </fieldset>
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
                var url = 'AddPriceManHourServlet';
   		        jQuery.post(url, params, function(data){
   	   		        //mini.alert(data.result);
   	   		        //window.location.href="priceManHourManage/showpriceManHour.jsp";
   	   		  		//window.location.href = window.location.href;
   	   		  		var result = data.result;
   		        	mini.confirm(result, "确定？",
   		                 function (action){            
   		                     if (action == "ok") {
   		     		            //var params = $("#form1").serialize() // form对应的后台bean需要序列化
   		     		            //document.forms[0].action = "alliance/Alliance_addNotice.action?allianceId=${allianceId}";
   		     		            //editor.sync();
   		     		            //document.forms[0].submit();
   		     		            //mini.loading("正在保存记录，请稍候...... ", "提交数据... ");
   		                    	window.location.href = window.location.href;	
   		                     }
   		                 }
   		             );
   	   		        },'json');
            }
   		}

		/*
   		mini.confirm(alertMsg, "确定？",
            function (action){            
                if (action == "ok") {
		            var params = $("#form1").serialize() // form对应的后台bean需要序列化
		            document.forms[0].action = "alliance/Alliance_addNotice.action?allianceId=${allianceId}";
		            editor.sync();
		            document.forms[0].submit();
		            mini.loading("正在保存记录，请稍候...... ", "提交数据... ");	
                }
            }
        );
   		*/
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
