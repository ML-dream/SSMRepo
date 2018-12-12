<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import="com.wl.forms.User"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
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
  
  <body>
  	<div class="mini-toolbar">
  		<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm()">保存</a>
  		<span class="separator"></span>
	    <a class="mini-button" plain="false" iconCls="icon-undo" onclick="back()">返回</a>
	    <span class="separator"></span>
        <a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()" >刷新</a>
        <span class="separator"></span>
        <a class="mini-button" iconCls="icon-add" plain="false" onclick="changeSpec()" >修改资产规格</a>
  	</div>
	<fieldset style="width: 99%;" align="center">
		<legend>
			添加客户资产
		</legend>
   		<div id= "userdiv">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%" >
   		<tr>
   			<td><label for="orderId$text">订单号</label></td>
            <td><input id="orderId"  name="orderId" class="mini-textbox"  width="100%" required="true" enabled="false"/></td>
            <td><label for="customeId$text">客户编号</label></td>
            <td><input id="customeId"  name="customeId" class="mini-textbox"  width="100%" required="true" enabled="false"/></td>
            <td><label for="customeName$text">客户名称</label></td>
            <td><input id="customeName"  name="customeName" class="mini-textbox"  width="100%" required="true" enabled="false"/></td>
        </tr>
       	<tr>
            <td><label for="itemId$text">资产标号</label></td>
            <td>
            	<input id="itemId"  name="itemId" class="mini-textbox"  allowinput="true"  required="true" width="100%"   enabled="false"/>
            </td>
            <td><label for="itemname$text">资产名称</label></td>
            <td><input id="itemname"  name="itemname" class="mini-buttonedit"  width="100%" required="true"  onbuttonclick="onButtonEdit" /></td>
            <td><label for="itemType$text">资产类型</label></td>
            <td><input id="itemType"  name="itemType" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="GetItemTypeServlet" value="Y"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            </td>
        </tr>
        <tr>    
            <td><label for="itemspec$text">资产规格</label></td>
            <td><input id="itemspec"  name="itemspec" class="mini-textbox"  width="100%" required="true" enabled="true"/></td>
            <td><label for="itemNum$text">资产数量</label></td>
            <td><input id="itemNum"  name="itemNum" class="mini-textbox" width="100%" vtype="int" required="true"/></td>
            <td><label for="itemUnit$text">单位</label></td>
      		<td><input id="itemUnit" name="unit" class="mini-textbox" width="100%"/></td>
             
        </tr>
       	<tr>
       		<td><label for="starttime$text">借入日期</label></td>
            <td><input id="starttime"  name="starttime" class="mini-datepicker" width="100%" required="true" 
	   					dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false" showOkButton="false" showClearButton="false"/></td>
       	    <td><label for="itemPrice$text">资产价格</label></td>
            <td><input id="itemPrice"  name="itemPrice" class="mini-textbox"  width="100%" vtype="float" /></td>
       		<td><label for="unit$text">价格单位</label></td>
            <td><input id="unit"  name="unit" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="GetPriceUnitServlet" value="YUAN"   allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            </td> 
		    
		</tr>
       	<tr>
       		<td><label for="endtime$text">计划归还日期</label></td>
            <td><input id="endtime"  name="endtime" class="mini-datepicker" width="100%" 
	   					dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false" showOkButton="false" showClearButton="false"/></td>
       		<td><label for="person$text">经手人</label></td>
            <td><input id="person" name="person" class="mini-buttonedit" width="100%"  value="<%=((User)session.getAttribute("user")).getStaffName()%>" text="<%=((User)session.getAttribute("user")).getStaffCode()%>"
            onbuttonclick="onButtonEditEmployee" allowInput="false" required="true"/></td>
	        <!--
            <td><label for="orderDetailId$text">订单明细号</label></td>
            <td><input id="orderDetailId"  name="orderDetailId" class="mini-textbox"  width="100%" required="true" /></td> 
            -->
            <td><label for="isCheckIn$text">是否入库</label></td>
            <!--<td><input id="isCheckIn"  name="isCheckIn" class="mini-textbox" width="100%"/></td>-->
            <td><input id="isCheckIn"  name="isCheckIn" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/trueOrFalse.txt" value="1"   allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            </td>
            
        </tr>
        <tr height="60px;">
   			<td><label for="memo$text">附录</label></td>
	        <td colspan="5"><input id="memo"  name="memo" class="mini-textarea" emptyText="请输入备注" 
	        	width="100%" height="100%"/></td>
         </tr>
   	</table>
   	<input id="checkinstatus" name="checkinstatus" class="mini-hidden" value="0"/>
   </div>
   </fieldset>
   <script>
   		mini.parse();
   		
   		function changeSpec(){
   			mini.get("itemId").setValue("");
   			mini.get("itemspec").enable();
   		}
   		
   		function getCurrentTime(){
   		      var now=new Date();
   		      var year = now.getFullYear();
		      var month = (((now.getMonth()+1) < 10) ? "0" : "") + (now.getMonth()+1);
		      var day = ((now.getDate() < 10) ? "0" : "") + now.getDate();
   		       mini.get("starttime").setValue(year+"-"+month+"-"+day);
   		      }
   	     getCurrentTime();
   	     
   		function search(){
   		       	var itemId =mini.get("itemId").getValue();
   		       	//alert(itemId);
   		        if(itemId != null){
   		        	var params = {'itemId':itemId};
	                var url = 'CheckItemExistServlet';
	   		        jQuery.post(url, params, function(data){
	   		        if((data.total)>=1){
	   		        	alert("此编号已存在，请重新编号");
	   		        	return;
	   		        }
	   	   		        },'json');
   		        }
                
   	   		   
           }
   		function onButtonEdit(e) {
            var btnEdit = this;
            var id=btnEdit.id;
            mini.open({
               
                url: "Lingliao/selectItemWindow.jsp",
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
                            btnEdit.setValue(data.itemName);
                            btnEdit.setText(data.itemName);
                            mini.get("itemId").setValue(data.itemId);  //
                            mini.get("itemspec").setValue(data.spec);
                            mini.get("itemspec").disable();
                        }
                     }
                }
            })    
        }
        
   		function getForm(){
   			var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
            	data.itemId= mini.get("itemId").getValue();
            	data.starttime =mini.get("starttime").getFormValue();
            	data.endtime =mini.get("endtime").getFormValue();
                var params = eval("("+mini.encode(data)+")");
                var url = 'AddCustomerItemServlet';
   		        jQuery.post(url, params, function(data){
   	   		        mini.confirm(data.result, "是否刷新页面？",
		                 function (action){            
		                     if (action == "ok") {
		                    	window.location.href = window.location.href;	
		                     }
		                 }
		             );
   	   		        },'json');
            }
   		}

   		function back(){
			window.history.back(-1);
		}
		function refresh(){
			window.location.href=window.location.href;
		}
   		
        
        function fillPerson(){
                var params =null;
                var url = 'FillPersonServlet';
   		        jQuery.post(url, params, function(data){
   		          var text=mini.decode(data);
   	   		       mini.get("person").setValue(text.staffCode);
   	   		  	   mini.get("person").setText(text.staffName);
   	   		        },'json'); 
        }
        fillPerson();
        
        
        function onButtonEditEmployee(e) {
            var btnEdit = this;
            mini.open({
                url: "employeeManage/selectEmployeeWindow.jsp",
                title: "选择上级部门",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.staffCode);
                            btnEdit.setText(data.staffName);
                            //mini.get("connector").setValue(data.connector);
                            //mini.get("connectorTel").setValue(data.connectorTel);
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
        //alert(Request['customerName']);
        mini.get("orderId").value = Request['orderId'];
        mini.get("customeId").value = Request['customerId'];
        mini.get("customeName").value = decodeURI(Request['customerName']);
   </script>
  </body>
</html>
