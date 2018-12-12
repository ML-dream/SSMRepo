<%@ page language="java" import="java.util.*,com.wl.forms.Employee" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <!-- miniui.js -->
      	            <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>待处理通知</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>
  	<div class="mini-toolbar" style="padding:2px;border-top:0;border-left:0;border-right:0;"> 
	            <a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()">刷新</a>
	            <!--
	            <a class="mini-button" iconCls="icon-remove" plain="false" onclick="getForm('0')">删除</a>     
	            <span class="separator"></span>             
	            <a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm('1')">保存</a>
	            <span class="separator"></span>             
	            <a class="mini-button" iconCls="icon-undo" plain="false" onclick="javascript:window.history.back(-1);">返回</a>  
	            -->                 
	        </div>
	        <div id="grid1" class="mini-datagrid" style="width:100%;height:320px;"
				         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
				         url="NoticeListServlet?isReaded=0" onrowdblclick="rowdblclick">
				        <div property="columns">
				            <div type="indexcolumn"></div>
				            <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower"
                 				cellStyle="padding:0;">操作
            				</div>    
				            <div field="contentTitle" width="30%" headerAlign="center">通知标题</div>
				            <div field="sender" width="20%" headerAlign="center">发送人</div>
				            <div field="receiver" width="20%" headerAlign="center">接收人</div>
				            <div field="sendTime" width="20%" headerAlign="center" dateFormat="yyyy-MM-dd HH:mm:ss">发送时间</div>
				            <div field="grade" width="10%" headerAlign="center">通知等级</div>
				            <div field="isReaded" width="10%" headerAlign="center" renderer="onGenderRenderer">通知状态</div>
				        </div>
				    </div>
   <script>
   		mini.parse();
   		var grid = mini.get("grid1");
	    grid.load();
	    
	    function refresh(){
			window.location.href=window.location.href;
		}
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str += "<a style='margin-right:2px;' title='读取' href=javascript:ondEdit(\'" + e.row.id + "\',\'" + e.row.isReaded + "\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
	        return str;
	    }
	    
	    function ondEdit(id,isReaded){
	        window.location.href="NoticeDetailServlet?id=" + id+"&isReaded="+isReaded;
		}
   		
   		function getForm(flag){
			var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
            	data.isAlive=flag;
                var params = eval("("+mini.encode(data)+")");
                var url = 'EditDeptSpecServlet';
   		        jQuery.post(url, params, callbackFun, 'json');
            }
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

   		function isTelephone(){
   			if (e.isValid) {
   				var isPhone = /^([0-9]{3,4}-)?[0-9]{7,8}$/;
   				var isMobile = /^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/
                if (!isPhone.exec(e.value)&&!isMobile.exec(e.value)) {
                    e.errorText = "必须输入正确电话号码";
                    e.isValid = false;
                }
            }
   			
   	   	}

   		var Genders = [{ id: '0', text: '未读' }, { id: 'W', text: '已读'}];
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
