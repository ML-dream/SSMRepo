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
    <title>新增设备</title>
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
			新增设备
		</legend>
	   	<div id= "userdiv">
	   		<table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
	   		<tr>
	   			<td><label for="machineId$text">设备编号</label></td>
	            <td><input id="machineId"  name="machineId" class="mini-textbox" width="100%"  required="true"/></td>
	   			<td><label for="machineName$text">设备名称</label></td>
	            <td><input id="machineName"  name="machineName" class="mini-textbox"  width="100%" required="true" /></td>
	            <td><label for="machType$text">设备类别</label></td>
	   			<td><input id="machType"  name="machType" class="mini-combobox" style="width:100%;" textField="typeName" valueField="machineType" emptyText="请选择..."
    				url="GetMachTypeServlet" value="CC"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            	</td>
	   			
	   			<!-- 
	            <td><input id="machineSpec"  name="machineSpec" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
	    			url="data/companyType.txt" value="SY"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
	            </td>
	             -->
	       </tr>
	       <tr style="display:none">
	       		<td style="display:none"><label for="hourPercent$text">工时百分比</label></td>
		   		<td style="display:none"><input id="hourPercent" name="hourPercent" class="mini-textbox" width="100%"   /></td>
		   		<td style="display:none"><label for="countPercent$text">计件百分比</label></td>
		        <td style="display:none"><input id="countPercent" name="countPercent" class="mini-textbox" width="100%" /></td>
	   			<td><label for="outDate$text">出厂日期</label></td>
	   			<!-- vtype="date:yyyy-MM-dd" -->
	            <td><input id="outDate"  name="outDate" class="mini-textbox" width="100%"  emptyText="日期格式：2000-01-01"   required="false" /></td>
			</tr>
	   		<tr>
	   			<td><label for="outDate$text">出厂日期</label></td>
	   			<!-- vtype="date:yyyy-MM-dd" -->
	            <td><input id="outDate"  name="outDate" class="mini-textbox" width="100%"  emptyText="日期格式：2000-01-01"   required="false" /></td>
	   			<td style="display:none"><label for="machNum$text">设备数量</label></td>
	          	<td style="display:none"><input id="machNum" name="machNum" class="mini-textbox" width="100%" vtype="range:0,9999" required="false"/></td>
	   			<td><label for="workRange$text">加工范围</label></td>
		        <td><input id="workRange"  name="workRange" class="mini-textbox" width="100%" required="false" /></td>
	   			<td><label for="machineSpec$text">设备规格</label></td>
	   			<td><input id="machineSpec"  name="machineSpec" class="mini-textbox"  width="100%" required="false" /></td>
	            <!--<td><input id="machType"  name="machType" class="mini-textbox" width="100%" required="true" /></td>-->
	   		</tr>
	   		<tr>
	   			<td><label for="machModel$text">设备型号</label></td>
	          	<td><input id="machModel" name="machModel" class="mini-textbox" width="100%" required="false"/></td>
	   			<td><label for="machStandard$text">设备品牌</label></td>
		        <td><input id="machStandard"  name="machStandard" class="mini-textbox" width="100%" required="false" /></td>
	   			<td><label for="machManufacture$text">生产厂商</label></td>
	            <td><input id="machManufacture"  name="machManufacture" class="mini-textbox" width="100%"  required="false" /></td>
	   		</tr>
	   		<tr>
	   			<td><label for="usedYears$text">使用年限</label></td>
	          	<td><input id="usedYears" name="usedYears" class="mini-textbox" width="100%" vtype="range:0,999" required="false"/></td>
	   			<td><label for="machPrice$text">设备价格</label></td>
		        <td><input id="machPrice"  name="machPrice" class="mini-textbox" width="100%"  vtype="range:0,4999999" required="false" /></td>
	   			<td><label for="machOldRate$text">年折旧率</label></td>
	            <td><input id="machOldRate"  name="machOldRate" class="mini-textbox" width="100%" vtype="float"  required="false" /></td>
	   		</tr>
	   		<tr>
	   			<td><label for="isKeyMach$text">是否关键设备</label></td>
	   			<td><input id="isKeyMach"  name="isKeyMach" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    				url="data/trueOrFalse.txt" value="1"  required="false" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            	</td>
	            <!--<td><input id="isKeyMach"  name="isKeyMach" class="mini-textbox"  width="100%" required="true" /></td>-->
	   			<td><label for="buyDate$text">购买日期</label></td>
		        <td><input id="buyDate"  name="buyDate" class="mini-textbox" width="100%" emptyText="日期格式：2000-01-01"  vtype="date:yyyy-MM-dd" required="false" /></td>
	   			<td><label for="status$text">设备状态</label></td>
	   			<td><input id="status"  name="status" class="mini-combobox" style="width:100%;" textField="remark" valueField="machineStatus" emptyText="请选择..."
    				url="GetMachStatusServlet" value="0"  required="false" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            	</td>
		        <!--<td><input id="status"  name="status" class="mini-textbox" width="100%" required="true" /></td>-->
	   		</tr>
	   		<tr>
	   			<td><label for="power$text">功率</label></td>
	            <td><input id="power"  name="power" class="mini-textbox" width="100%" vtype="float" required="false" /></td>
	   			<td><label for="deptId$text">部门号</label></td>
	   			<td><input id="deptId" name="deptId" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" allowInput="false" required="false"/></td>
	          	<!--<td><input id="deptId" name="deptId" class="mini-textbox" width="100%" /></td>-->
	   			<td><label for="runDate$text">使用日期</label></td>
	            <td><input id="runDate"  name="runDate" class="mini-textbox" width="100%" emptyText="日期格式：2000-01-01"  vtype="date:yyyy-MM-dd"/></td>
	   		</tr>
	   		<tr>
	   			<td><label for="worker$text">操作员工</label></td>
	   			<td><input id="worker" name="worker" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEditEmployee" allowInput="false" required="false"/></td>
	            <!--<td><input id="worker"  name="worker" class="mini-textbox"  width="100%"/></td>-->
	   			<td><label for="madeDate$text">制造日期</label></td>
	          	<td><input id="madeDate" name="madeDate" class="mini-textbox" width="100%"   emptyText="日期格式：2000-01-01"/></td>
	   			<td><label for="checkDate$text">检查时间</label></td>
	            <td><input id="checkDate"  name="checkDate" class="mini-textbox" emptyText="日期格式：2000-01-01" width="100%"/></td>
	        </tr>
	        <tr>
		   		<td><label for="place$text">放置地点</label></td>
	            <td><input id="place"  name="place" class="mini-textbox" width="100%" required="false"/></td>
	            <td><label for="outCode$text">出厂编号</label></td>
		        <td><input id="outCode"  name="outCode" class="mini-textbox" width="100%" required="false" /></td>
		    </tr>
	   		<tr height="60px;">
	   			<td><label for="memo$text">附录</label></td>
		        <td colspan="5"><input id="memo"  name="memo" class="mini-textarea" emptyText="请输入备注" 
		        	width="100%" height="100%"/></td>
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
                var url = 'AddMachineServ';
   		        jQuery.post(url, params, function(data){
   	   		  		mini.confirm(data.result, "是否刷新页面 ？",
		                 function (action){            
		                     if (action == "ok") {
		                    	window.location.href = window.location.href;	
		                     }
		                 }
		             );
   	   		        },'json');
            }
   		}
   		
   	    function callbackFun(data){
   	      	window.location.href="employeeManage/editEmployee.jsp";
   	    }
   	    
   	    function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                url: "deptManage/selectDeptTreeWindow.jsp",
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
                            btnEdit.setValue(data.deptId);
                            btnEdit.setText(data.deptName);
                            //mini.get("connector").setValue(data.connector);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                        }
                    }
                }
            });
        }
        
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
   </script>
  </body>
</html>
