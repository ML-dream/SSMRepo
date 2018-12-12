<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <meta http-equiv="content-Type" content="text/html;charset=utf-8"/>
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>添加订单</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body >
  	<div class="mini-toolbar">
  		<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm()">保存</a>
  	</div>
	<fieldset style="width: 99%;" align="center">
		<legend>
			新建订单
		</legend>
   		<!--<div id= "userdiv">-->
   		<form name="userdiv" id="userdiv" action="AddOrderServlet" method="post" enctype="multipart/form-data">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
   			<td><label for="orderId$text">订单编号</label></td>
            <td style="width:25%;"><input id="orderHead"  name="orderHead" class="mini-combobox" style="width:30%;" textField="orderHead" valueField="orderHead" emptyText="请选择..."
    				url="GetOrderHeadServlet" value="NL-XS"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            	<input id="orderId"  name="orderId" class="mini-textbox"  width="66%" required="true" readonly="readonly"/>
           	</td>
            <td><label for="customer$text">客户</label></td>
            <td style="width:20%;"><input id="customer" name="customer" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" textName="companyName" required="true" allowInput="false"/>
   			<td><label for="connector$text">联系人</label></td>
            <td><input id="connector" name="connector" class="mini-textbox" width="100%" required="true" readonly="readonly"/></td>
        </tr>
       	<tr>
            <td><label for="connectorTel$text">联系人电话</label></td>
            <td><input id="connectorTel"  name="connectorTel" class="mini-textbox"  width="100%" required="true" readonly="readonly"/></td>
   			<td><label for="deptUser$text">接收部门</label></td>
            <td><input id="deptUser"  name="deptUser" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/dept.txt"   required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            </td>
       
            <td><label for="orderDate$text">订单日期</label></td>
   <!--         <td><input id="orderDate"  name="orderDate" class="mini-textbox"  width="100%" required="true"  emptyText="日期格式：2000-01-01"/></td>  -->  
            <td><input id="orderDate" name ="orderDate" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd" allowInput="true" /></td>
        </tr>
       	<tr>
            <td><label for="endTime$text">交付日期</label></td>
 <!--         <td><input id="endTime"  name="endTime" class="mini-textbox"  width="100%" required="true"  emptyText="日期格式：2000-01-01"/></td>   -->   
            <td><input id="endTime" name ="endTime" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd" allowInput="true" /></td>
            <td><label for="orderStatus$text">订单状态</label></td>
            <td><input id="orderStatus"  name="orderStatus" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/orderStatus.txt" value="1"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..." readonly="readonly"/>  
            </td>
   		</tr>
   		<tr>
   			<td>上传合同</td>
   			<td align="left"><input type="file" name="orderPaper"/></td>
   			
   			<td>上传对账函</td>
   			<td align="left"><input type="file" name="duizhanghan"/></td>
   			
   			<td>其他文件</td>
   			<td align="left"><input type="file" name="otherPaper"/></td>
   		</tr>
   		<tr height="60px;">
   			<td><label for="memo$text">附录</label></td>
	        <td colspan="5"><input id="memo" name="memo" class="mini-textarea" emptyText="请输入备注" 
	        	width="100%" height="100%"/></td>
         </tr>
   	</table>
   	</form>
   <!--</div>-->
   </fieldset>
   
   
   <script charset="UTF-8">
   		mini.parse();
   		/*
   		function getForm(){
   			var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
                var params = eval("("+mini.encode(data)+")");
                var url = 'AddOrderServlet';
   		        jQuery.post(url, params, function(data){
   	   		        alert(data.result);
   	   		  		window.location.href=window.location.href;
   	   		        },'json');
            }
   		}
   		*/
   		  function getCurrentTime(){
   		      var now=new Date();
   		      var year = now.getFullYear();
		      var month = (((now.getMonth()+1) < 10) ? "0" : "") + (now.getMonth()+1);
		      var day = ((now.getDate() < 10) ? "0" : "") + now.getDate();
   		       mini.get("orderDate").setValue(year+"-"+month+"-"+day);
   		      }
   	     getCurrentTime();
   		
   		function getForm(){
   			//document.forms[0].action = "AddOrderServlet";
 		  	//document.forms[0].submit();
 		  	var formData = new mini.Form("#userdiv");
 		  	var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
 		  		jQuery.ajax({
      				type: "POST",
      				url: "AddOrderServlet",
      				dataType: "json", 
      				cache: false,
      				enctype: 'multipart/form-data',
      				data: new FormData($('#userdiv')[0]),
      				processData: false,
    				contentType: false,
      				success: function (data) {
        				alert(data.result);
        				window.location.href = window.location.href;
      				}
    			});
    		}
   		}
		
		var now = new Date();
		var fullYear = now.getFullYear();
		var year = fullYear.toString().substring(2,4);
		var month = (((now.getMonth()+1) < 10) ? "0" : "") + (now.getMonth()+1);
		var day = ((now.getDate() < 10) ? "0" : "") + now.getDate();
//		var hour = ((now.getHours() < 10) ? "0" : "") + now.getHours();
//		var min = ((now.getMinutes() < 10) ? "0" : "") + now.getMinutes();
//		var sec = ((now.getSeconds() < 10) ? "0" : "") + now.getSeconds();
//		var millSec = (now.getMilliseconds()).toString().substring(0,1);
		var nowRight = year+month+day;
		
   		function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
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
                            mini.get("connector").setValue(data.connector);
                            mini.get("connectorTel").setValue(data.connectorTel);
    			            var params = {'companyId':data.companyId};
                            var url='CheckOrderIdCountServlet'; 
                            jQuery.post(url, params, function(text){
                            text=mini.decode(text);
                            total=text.total+1;
                            if(total>0&&total<10)
                            total="0"+total;
                            else if(total==100)
                            total="01";
 	   					    mini.get("orderId").setValue("-"+data.companyId+"-"+nowRight+total);
 	   				          });      
                        }
                    }

                }
            });
        }

		function doShow()   
	    { 
	         document.getElementById("span1").innerHTML="<input type=file id=upload size=40  name=upload label=上传文件   class=buttonface />";   
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

