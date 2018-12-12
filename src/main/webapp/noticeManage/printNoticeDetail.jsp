<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.wl.forms.User" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/js.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/pagecard.css">
	
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
    <title>发送通知</title>
    
  </head>
  
  <body>
		<fieldset style="width: 95%;" align="center" >
		
			<legend>通知详情</legend>
			
				
		  		<div id= "userdiv">
		  			<table style="text-align: right;border-collapse:collapse;" width="95%" >
		  		
		  				<tr height= "25px">
		  					<td width="15%" height= "25px"><label for="receivers$text">发件人:</label></td>
		  					<td width="35%" height= "25px"><input id="receivers" name="receivers" class="mini-textbox" width="100%"  height="100%" readonly value="${result.sender}" 
		  					borderStyle="background:transparent;border:0"/></td>
		  					<td width="15%" width="15%" height= "25px"><label for="receivers$text">收件人：</label></td>
		  					<td width="35%" height= "25px"><input id="receivers" name="receivers" class="mini-textbox" width="100%"  height="100%" readonly value="<%=((User)session.getAttribute("user")).getStaffName()%>"
		  					borderStyle="background:transparent;border:0"/></td>
		  				</tr>		
					</table>
					<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="95%" >	
		  				<tr>
		  					<td><label for="noticeTitle$text">通知标题:</label></td>
		  					<td><input id="noticeTitle" name="noticeTitle" class="mini-textbox"  width="100%" readonly  value="${result.contentTitle}" borderStyle="background:transparent;border:0"/></td>
		  				</tr>
		  				
		  			
		  				
		  				<tr>
				   				<td><label for="grade$text">通知等级:</label></td>
				   				<td><input id="grade"  name="grade" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..." 
    									url="data/noticeGradeStatus.txt" value="${result.grade}" allowInput="false" readonly showNullItem="true" nullItemText="请选择..." borderStyle="background:transparent;border:0"/>  
            					</td>
				   			</tr>
		  				<tr  height="200px;">
		  					<td><label for="content$text">通知内容:</label></td>
		  					<td  colspan="15"><input id="content" name="content" class="mini-textarea" width="100%" height="100%" readonly value="${result.content}" borderStyle="background:transparent;border:0"/></td>
		  				</tr>
		   			</table>
		   	</div>
		   	<input id="id" name="id" class="mini-hidden" value="${result.id }"/>
		</fieldset>
	
	
   <script>
   		mini.parse();
	    
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
