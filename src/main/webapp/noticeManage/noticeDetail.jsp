<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
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
  	
  		<a class="mini-button" iconCls="icon-print" plain="false"  onclick="print()">打印</a>
		<fieldset style="width: 100%;" align="center" >
			<legend>通知详情</legend>
		  		<div id= "userdiv">
		  			<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="95%" >
		  				<tr>
		  					<td width="15%"><label for="receivers$text">发件人:</label></td>
		  					<td  colspan="2"><input id="receivers" name="receivers" class="mini-textarea" width="100%" height="100%" readonly value="${result.sender}"/></td>
						</tr>
		  				<tr>
		  					<td><label for="noticeTitle$text">通知标题:</label></td>
		  					<td><input id="noticeTitle" name="noticeTitle" class="mini-textbox"  width="100%" readonly  value="${result.contentTitle}"/></td>
		  				</tr>
		  				<tr>
				   				<td><label for="grade$text">通知等级:</label></td>
				   				<td><input id="grade"  name="grade" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..." 
    									url="data/noticeGradeStatus.txt" value="${result.grade}" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            					</td>
				   			</tr>
		  				<tr  height="200px;">
		  					<td><label for="content$text">通知内容:</label></td>
		  					<td  colspan="15"><input id="content" name="content" class="mini-textarea" width="100%" height="100%" readonly value="${result.content}"/></td>
		  				</tr>
		  				<tr>
		  					<td colspan="2">
		  						<div class="mini-toolbar" align="center">
					  				<a class="mini-button" iconCls="icon-undo" plain="false"  onclick="back()">关闭</a>
						  		</div>
		   					</td>
		   				</tr>
		   			</table>
		   	</div>
		   	<input id="id" name="id" class="mini-hidden" value="${result.id }"/>
		</fieldset>
	
	
   <script>
   		mini.parse();
   		var grid = mini.get("grid1");
	    grid.load();
	    
	    function back(){
			window.history.back(-1);
		}
	    
	    function getForm(){
   			var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
                var params = eval("("+mini.encode(data)+")");
                var url = 'SendNoticeServlet';
   		        jQuery.post(url, params, function(data){
   	   		        alert(data.result);
   	   		  		window.location.href=window.location.href;
   	   		        },'json');
            }
   		}
	    
	    function print(){
	    	 var id = mini.get("id").getValue();
	    	window.open("ToPrintNotice?id="+id, 
	    	          "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
	    
	    
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
