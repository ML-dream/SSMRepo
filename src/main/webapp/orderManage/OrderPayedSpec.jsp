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
    <!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>订单详细信息</title>
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
	    <a class="mini-button" plain="false" iconCls="icon-reload" onclick="back()">刷新</a>
	</div>
   	
   	<fieldset style="width: 50%;" align="center">
		<legend>
			订单交付
		</legend>
		<div id= "userdiv">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
	   		<tr>
	   			<td><label for="orderId$text">订单编号</label></td>
	            <td><input id="orderId"  name="orderId" class="mini-textbox"  width="100%" required="true" /></td>
	        </tr>
	       	<tr>
	            <td><label for="productId$text">产品号</label></td>
	            <td><input id="productId"  name="productId" class="mini-textbox"  width="100%" required="true" /></td>
	        </tr>
	       	<tr>
				<td><label for="fproductId$text">父产品号</label></td>
	            <td><input id="fproductId"  name="fproductId" class="mini-textbox"  width="100%" required="true" /></td>
	   		</tr>
	       	<tr>
	       		<td><label for="payedTime$text">交付时间</label></td>
	            <td><input id="payedTime"  name="payedTime" class="mini-textbox"  width="100%" required="true"  emptyText="日期格式：2000-01-01" /></td>
	        </tr>
	       	<tr>
	            <td><label for="payedNum$text">交付数量</label></td>
	            <td><input id="payedNum"  name="payedNum" class="mini-textbox"  width="100%" required="true" /></td>
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
                var url = 'OrderPayedSpecServlet';
   		        jQuery.post(url, params, callbackFun, 'json');
            }
   		}

   		function back(){
			window.history.back(-1);
		}

		function download(data){
			if(data==1){
				alert("订单文件下载！！");
			}
			if(data=='2'){
				alert("对账函下载！！");
			}
			
		}

   		function CustomerDetail(){
			var customer = mini.get("customer").getValue();
			var connector = mini.get("connector").getValue();
			window.location="EditCustomerDetailServlet?companyId="+customer+"&connector="+connector;
   	   	}
	   		
   	    function callbackFun(data)
   	    {
   	         alert(data.result);
   	      	 //window.opener.location.href = window.opener.location.href;
   	      	 window.history.back(-1);
   	    }

   	 	function onButtonEdit(e) {
         	var btnEdit = this;
         	mini.open({
             	url: "orderManage/selectCustomerWindow.jsp",
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
                         	btnEdit.setText(data.companyName);
                     	}
                 	}
             	}
         	});
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

   		var Genders = [{ id: 'M', text: '男' }, { id: 'W', text: '女'}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }

        function addOrderDetail(){
			var orderId = mini.get("orderId").value;
			window.location = "orderManage/AddOrderDetail.jsp?orderId="+orderId+"&productId="+orderId;
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
        mini.get("productId").value = Request['productId'];
        mini.get("fproductId").value = Request['fproductId'];
   </script>
  </body>
</html>
