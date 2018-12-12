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
   
    <title>员工详细信息</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>
   <div class="mini-toolbar">
   <a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm('N')">保存</a>
    <span class="separator"></span>
    <a  class="mini-button" iconCls="icon-remove" plain="false"  onclick="getForm('Y')">离职</a>
    <span class="separator"></span>
    <a  class="mini-button" iconCls="icon-undo" plain="false"  onclick="javascript:window.history.back(-1);">返回</a>
    </div>
 <fieldset style="width: 100%;" align="center">
	    <legend>
		修改员工信息
		</legend>
   	<div id= "userdiv">
   		<table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
   			<td><label for="staffCode$text">员工编号*</label></td>
            <td><input id="staffCode"  name="staffCode" class="mini-textbox" width="100%"  required="true" value="${employee.staffCode}" enabled="false"/></td>
   			<td><label for="staffName$text">员工姓名*</label></td>
            <td><input id="staffName"  name="staffName" class="mini-textbox"  width="100%" required="true"  value="${employee.staffName} " enabled="false"/></td>
   			<td><label for="sectionCode$text">所属部门*</label></td>
   		<!-- <td><input id="sectionCode" name="sectionCode" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" required="true" value="${dept.deptName}" text="${dept.deptName}"/></td> 
   			<td><input id="sectionCode" name="sectionCode" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" required="true" value="${employee.sectionCode}" /></td>  -->
   			
        <!--  <td><input id="sectionCode"  name="sectionCode" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/dept.txt" required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."   value="${employee.sectionCode}"/>  
            </td>    -->   
            <td><input id="sectionCode" name="sectionCode" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" required="true"  value="${employee.sectionCode}" text="${employee.sectionName}"/></td>
            
            <td><label for="birthday$text">出生日期*</label></td>
    <!--      	<td><input id="birthday" name="birthday" class="mini-textbox" width="100%"   emptyText="日期格式：2000-01-01"  vtype="date:yyyy-MM-dd" required="true" value="${employee.birthday}" enabled="false"/></td> -->  
            <td><input id="birthday" name ="birthday" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd" allowInput="true" required="true" value="${employee.birthday}"/></td>
       </tr>
       <tr>
          	<td><label >性别* </label></td>
          	<td><div id="gender" name="gender" class="mini-radiobuttonlist" 
              repeatItems="2" repeatDirection="" repeatLayout="table" url="data/sexs.txt" textField="text" valueField="id"  value="${employee.gender}" enabled="false"></div>
          	</td> 
			<td><label for="IDCard$text">身份证号*</label></td>
	        <td><input id="IDCard"  name="IDCard" class="mini-textbox" width="100%"  onvalidation="onIDCardsValidation" required="true"  value="${employee.IDCard}" enabled="false"/></td>
   			<td><label for="educationLevel$text">学历水平*</label></td>
            <td><input id="educationLevel"  name="educationLevel" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/educationLevel.txt" required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."  value="${employee.educationLevel}"/>  
    		<td><label for="mobilePhone$text">手机号*</label></td>
	        <td><input id="mobilePhone"  name="mobilePhone" class="mini-textbox" width="100%" onvalidation="isMobile" required="true"  value="${employee.mobilePhone}"/></td>

   		</tr>
   		<tr>
   		   <td><label for="joinTime$text">入职时间*</label></td>
	<!--     <td><input id="joinTime"  name="joinTime" class="mini-textbox" width="100%"  emptyText="日期格式：2000-01-01"  vtype="date:yyyy-MM-dd" required="true"  value="${employee.joinTime}"/></td>  -->     
	        <td><input id="joinTime" name ="joinTime" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd" allowInput="true"  value="${employee.joinTime}"/></td>
	        <td><label for="position$text">职位</label></td>
            <td><input id="position"  name="position" class="mini-textbox"  width="100%"  value="${employee.position}"/></td>
             <td><label for="workTime$text">工作年限</label></td>
            <td><input id="workTime"  name="workTime" class="mini-textbox"  vtype="range:0,99" width="100%" value="${employee.workTime}"/></td>
   			<td><label for="fee$text">工资/月</label></td>
            <td><input id="fee"  name="fee" class="mini-textbox" width="100%"    emptyText="请输入工资"  vtype="float" value="${employee.fee}"/></td>	
   		</tr>
   		<tr>
   		    <td><label for="workType$text">工种</label></td>
            <td><input id="workType"  name="workType" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="LoadWorkType" value="${employee.workType}"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/> 
            </td>
   			<td><label for="technicalGrade$text">技术等级</label></td>
            <td><input id="technicalGrade"  name="technicalGrade" class="mini-textbox" width="100%"    value="${employee.technicalGrade}"/></td>
   			<td><label for="officePhne$text">办公室电话</label></td>
            <td><input id="officePhne"  name="officePhne" class="mini-textbox" width="100%" value="${employee.officePhne}"/></td>
            <td><label for="address$text">地址</label></td>
            <td><input id="address"  name="address" class="mini-textbox" width="100%"    value="${employee.address}"/></td>
   			
   		</tr>
   		<tr>
   		    <td><label for="homePhone$text">家庭电话</label></td>
            <td><input id="homePhone"  name="homePhone" class="mini-textbox" width="100%" value="${employee.homePhone}"/></td>
            <td><label for="speciality$text">特长爱好</label></td>
            <td><input id="speciality"  name="speciality" class="mini-textbox"  width="100%" value="${employee.speciality}"/></td>
            </td><td><label for="schoolFrom$text">毕业院校</label></td>
            <td><input id="schoolFrom"  name="schoolFrom" class="mini-textbox"  width="100%"   value="${employee.schoolFrom}"/></td>
   			<td><label for="RFIDCode$text">RFID号码</label></td>
            <td><input id="RFIDCode"  name="RFIDCode" class="mini-textbox" width="100%"  value="${employee.RFIDCode}"/></td>
        </tr>
        
        <tr>
   			<td><label for="QQ$text">QQ</label></td>
            <td><input id="QQ"  name="QQ" class="mini-textbox" width="100%"     value="${employee.QQ}"/></td>
   			<td><label for="email$text">邮箱</label></td>
            <td><input id="email"  name="email" class="mini-textbox"  width="100%" vtype="email"   value="${employee.email}"/></td>
   			<td><label for="leaveTime$text">离职时间</label></td>
     <!--   <td><input id="leaveTime"  name="leaveTime" class="mini-textbox" width="100%"  emptyText="日期格式：2000-01-01" value="${employee.leaveTime}"/></td> -->       
            <td><input id="leaveTime" name ="leaveTime" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd" allowInput="true" value="${employee.leaveTime}"/></td>
   		</tr>
   	</table>
   </div>
   </fieldset>
   <script>
   		mini.parse();
   		function getForm(leave){
   			var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
            	data.sectionCode=mini.get("sectionCode").getValue();
            	data.workType=mini.get("workType").getValue();
            	data.sectionCode = mini.get("sectionCode").getValue();
            	data.leave=leave;
            	data.leaveTime=mini.get("leaveTime").getFormValue();
            	data.joinTime=mini.get("joinTime").getFormValue();
            	data.birthday=mini.get("birthday").getFormValue();
                var params = eval("("+mini.encode(data)+")");
                var url = 'DoEditEmployeeDetailServlet';
   		        jQuery.post(url, params, callbackFun, 'json');
                //jQuery.post(url, params, callbackFun);
            }
   		}
   	    function callbackFun(data)
   	    {
   	         alert(data.result);
   	         var form = new mini.Form("#userdiv");
   	         form.clear();
   	        // window.close();
   	      	 //window.location.href = window.location.href;
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
                            //mini.get("deptLevel").setValue(data.deptLevel+1);
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
   </script>
  </body>
</html>
