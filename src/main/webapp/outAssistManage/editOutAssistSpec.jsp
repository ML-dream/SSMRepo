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
	    <span class="separator"></span>
  	</div>
	<fieldset style="width: 99%;" align="center">
		<legend>
			添加外协产品
		</legend>
   	<div id= "userdiv">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
   			<td><label for="orderId$text">外协单号</label></td>
            <td><input id="orderId"  name="orderId" class="mini-textbox" width="100%" enabled="false" required="true" value="${result.orderId}"/></td>
   			<td><label for="itemId$text">零部件号</label></td>
            <td><input id="itemId"  name="itemId" class="mini-textbox"  width="100%" required="true" value="${result.itemId}"/></td>
   			<td><label for="itemName$text">零部件名称</label></td>
   			<td><input id="itemName"  name="itemName" class="mini-textbox" width="100%" required="true" value="${result.itemName}"/></td>
       </tr>
       <tr>
       		<td><label for="drawingId$text">图号</label></td>
            <td><input id="drawingId"  name="drawingId" class="mini-textbox" width="100%" required="true" value="${result.drawingId}"/></td>
            <td><label for="num$text">数量</label></td>
	        <td><input id="num"  name="num" class="mini-textbox" width="100%" vtype="int" required="true" value="${result.num}"/></td>
   			<td><label for="numUnit$text">单位</label></td>
            <td><input id="numUnit" name="numUnit" class="mini-textbox" width="100%" vtype="maxLength:16" required="true" value="${result.numUnit}"/></td>
		</tr>
   		<tr>
   			<td><label for="startDate$text">下单时间</label></td>
   			<td><input id="startDate" name="startDate" class="mini-datepicker" width="100%" allowInput="false" value="${result.detailstartDate}"
	   				dateFormat="yyyy-MM-dd  HH:mm:ss" format="yyyy-MM-dd" showTime="false" showOkButton="false" showClearButton="false" required="true"/>
	   		</td>
	   		<td><label for="planEndDate$text">计划结束时间</label></td>
   			<td><input id="planEndDate"  name="planEndDate" class="mini-datepicker" width="100%" allowInput="false" value="${result.detailplanEndDate}"
	   				dateFormat="yyyy-MM-dd  HH:mm:ss" format="yyyy-MM-dd" showTime="false" showOkButton="false" showClearButton="false" required="true"/>
	   		</td>
   			<td><label for="endDate$text">收货时间</label></td>
   			<td><input id="endDate"  name="endDate" class="mini-datepicker" width="100%" allowInput="false" value="${result.detailendDate}"
	   				dateFormat="yyyy-MM-dd  HH:mm:ss" format="yyyy-MM-dd" showTime="false" showOkButton="false" showClearButton="false"/>
	   		</td>
        </tr>
   		<tr>
   			<td><label for="unitPrice$text">单价</label></td>
            <td><input id="unitPrice"  name="unitPrice" class="mini-textbox" width="100%" vtype="float" required="true"  value="${result.detailunitPrice}"/></td>
   			<td><label for="totalPrice$text">价格</label></td>
            <td><input id="totalPrice"  name="totalPrice" class="mini-textbox" width="100%" vtype="float" required="true"  value="${result.detailtotalPrice}"/></td>
   		</tr>
   		<tr height="60px;">
   			<td><label for="detail$text">加工内容</label></td>
            <td colspan="5"><input id="detail" name="detail" class="mini-textarea" emptyText="请输入加工内容" width="100%" height="100%" value="${result.detaildetail}"/></td>
   		</tr>
   		<tr height="60px;">
   			<td><label for="memo$text">附录</label></td>
	        <td colspan="5"><input id="memo" name="memo" class="mini-textarea" emptyText="请输入备注" width="100%" height="100%" value="${result.detailmemo}"/></td>
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
            	data.endDate = mini.get("endDate").getFormValue();
                var params = eval("("+mini.encode(data)+")");
                var url = 'EditOutAssistDetailServlet';
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
   	    function callbackFun(data)
   	    {
   	        alert(data.result);
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
   	   	
   	   	function GetRequest() { 
        	var url = location.search; //获取url中"?"符后的字串 
        	var theRequest = new Object(); 
        	if (url.indexOf("?") != -1) { 
        		var str = url.substr(1); 
        		strs = str.split("&"); 
        		for(var i = 0; i < strs.length; i ++) { 
        			theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
        		} 
        	} 
        	return theRequest; 
        } 

        var Request = new Object(); 
        Request = GetRequest();
        mini.get("orderId").value = Request['orderId'];
   </script>
  </body>
</html>
