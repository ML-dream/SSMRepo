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
   
    <title>添加员工</title>
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
		添加员工
		</legend>
   	<div id= "userdiv">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
   			<td><label for="staffCode$text"  >员工编号※</label></td>
            <td><input id="staffCode"  name="staffCode" class="mini-textbox" width="100%"  required="true"/></td>
   			<td><label for="staffName$text">员工姓名※</label></td>
            <td><input id="staffName"  name="staffName" class="mini-textbox"  width="100%" required="true" onblur="search"/></td>
   			<td><label for="sectionCode$text">所属部门※</label></td>
   			<td><input id="sectionCode" name="sectionCode" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" required="true"/></td>
   			<!--
            <td><input id="sectionCode"  name="sectionCode" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/dept.txt" value="cj"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            </td>
            -->
            <td><label for="mobilePhone$text">手机号※</label></td>
	        <td><input id="mobilePhone"  name="mobilePhone" class="mini-textbox" width="100%" onvalidation="isMobile" required="true" /></td>
       </tr>
       <tr>
          	<td><label >性别</label></td>
          	<td><div id="gender" name="gender" class="mini-radiobuttonlist" 
              value="M" repeatItems="2" repeatDirection="" repeatLayout="table" url="data/sexs.txt" textField="text" valueField="id" ></div>
          	</td> 
            <td><label for="birthday$text">出生日期 </label></td>
          	<td><input id="birthday" name ="birthday" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd" allowInput="true" /></td>
   			<td><label for="educationLevel$text">学历水平</label></td>
            <td><input id="educationLevel"  name="educationLevel" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/educationLevel.txt" value="BK"   allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            </td>
            <td><label for="email$text">邮箱</label></td>
            <td><input id="email"  name="email" class="mini-textbox"  width="100%" vtype="email" /></td>
<!--               <td><label for="IDCard$text">身份证号</label></td>
	        <td><input id="IDCard"  name="IDCard" class="mini-textbox" width="100%"  onvalidation="onIDCardsValidation" onblur="birthdayConfirm"/></td> -->
            

   		</tr>
   		
   	<!-- 	<tr>
            <td><label for="joinTime$text">入职时间*</label></td>
	
	      <td><input id="joinTime"  name="joinTime" class="mini-textbox" width="100%"  emptyText="日期格式：2000-01-01"  vtype="date:yyyy-MM-dd" required="true" /></td>  
	        <td><input id="joinTime" name ="joinTime" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd" allowInput="true"/></td>
	        <td><label for="position$text">职位</label></td>
            <td><input id="position"  name="position" class="mini-textbox"  width="100%"  /></td>
	        <td><label for="workTime$text">工作年限</label></td>
            <td><input id="workTime"  name="workTime" class="mini-textbox"  vtype="range:0,99" width="100%" /></td>
            <td><label for="fee$text">工资/月</label></td>
            <td><input id="fee"  name="fee" class="mini-textbox" width="100%"  emptyText="请输入工资"  vtype="float"/></td>
   		</tr> -->
   		<tr>            

   		   	<!-- <td><label for="workType$text">工种</label></td>
            <td>
            	<input id="workType"  name="workType" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="LoadWorkType" value=""  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/> 
            </td>
   			<td><label for="technicalGrade$text">技术等级</label></td>
            <td><input id="technicalGrade"  name="technicalGrade" class="mini-textbox" width="100%"  /></td> -->
   			<td><label for="officePhne$text">办公室电话</label></td>
            <td><input id="officePhne"  name="officePhne" class="mini-textbox" width="100%"  emptyText="格式：025-88888888 或 88888888"/></td>
   		   	<td><label for="address$text">地址</label></td>
            <td><input id="address"  name="address" class="mini-textbox" width="100%"  /></td>
            <td><label for="QQ$text">QQ</label></td>
            <td><input id="QQ"  name="QQ" class="mini-textbox" width="100%"    /></td>
   			
   		</tr>
   		
   		<!-- <tr>

   		   	<td><label for="homePhone$text">家庭电话</label></td>
            <td><input id="homePhone"  name="homePhone" class="mini-textbox" width="100%"  emptyText="格式：025-88888888 或 88888888"/></td>
            <td><label for="speciality$text">特长爱好</label></td>
            <td><input id="speciality"  name="speciality" class="mini-textbox"  width="100%"/></td>
   		    <td><label for="schoolFrom$text">毕业院校</label></td>
            <td><input id="schoolFrom"  name="schoolFrom" class="mini-textbox"  width="100%" /></td>
   			 <td><label for="RFIDCode$text">RFID号码</label></td>
            <td><input id="RFIDCode"  name="RFIDCode" class="mini-textbox" width="100%"  /></td> 
            
            <td style="display: none;"><label for="companyId$text">客户编号*</label></td>
            <td style="display: none;"><input id="companyId"  name="companyId" class="mini-textbox" width="100%"  required="true" allowInput="false"/></td>
            
        </tr> -->
        
        <tr>
            
   			<!-- <td><label for="leaveTime$text">离职时间</label></td> -->
  <!--        <td><input id="leaveTime"  name="leaveTime" class="mini-textbox" width="100%"  emptyText="日期格式：2000-01-01"  vtype="date:yyyy-MM-dd"/></td>  -->    
          <!--   <td><input id="leaveTime" name ="leaveTime" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd" allowInput="true"/></td> -->
            
           
   			<!-- <td><label for="companyName$text">客户名称*</label></td>
            <td><input id="companyName"  name="companyName" class="mini-textbox"  width="100%" required="true" onblur="search"/></td>
   		</tr> -->
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
            	/* data.leaveTime=mini.get("leaveTime").getFormValue();
            	data.joinTime=mini.get("joinTime").getFormValue(); */
            	data.birthday=mini.get("birthday").getFormValue();
                var params = eval("("+mini.encode(data)+")");
                var url = 'AddEmployeeServlet';
   		       /*
   		        jQuery.post(url, params, function(data){
   	   		       // mini.alert(data);
   	   		        var s = confirm(data + ",是否刷新页面？");
   	   		        if(s == true){
   	   		        	window.location.href="employeeManage/addEmployee.jsp";
   	   		        }
   	   		        },'json');
   	   		 */
	   	   		 $.ajax({
					type:"post",
					url: url,
					data:params,
					cache: false,
					success: function (text){
						var t = confirm(text +",是否刷新数据 ？");
						if(t==true){
							window.location.href="employeeManage/addEmployee.jsp";
						}
					},
					error: function (text){
						alert ("保存失败 ");
					}
				}); 
            }
   		}
   	    function callbackFun(data)
   	    {
   	        alert(data.message);
   	         //window.location.href=window.location.href;
   	      	window.location.href="employeeManage/editEmployee.jsp";
   	    }
   	    
   	    function birthdayConfirm()
   	    { 
   	    var IDCard=mini.get("IDCard").getValue();
   	     var year=IDCard.substring(6,10);
   	     var month=IDCard.substring(10,12);
   	     var day=IDCard.substring(12,14);
   	     mini.get("birthday").setValue(year+"-"+month+"-"+day);
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

   	    
   	    
   	 function search(){
	        var form = new mini.Form("#userdiv");
         var data = form.getData();
   	    data.companyName =mini.get("staffName").getValue();
         var params = eval("("+mini.encode(data)+")");
         var url = 'CheckCustomerNameServlet';
	        jQuery.post(url, params, function(data){
	        if((data.total)>=1)
	        alert("该客户已存在");
	        else
	        {
	         var totalcount=data.totalcount+1;
	         if(totalcount>0&&totalcount<10)
	         totalcount="00"+totalcount;
	         else if(totalcount>=10&&totalcount<100)
	         totalcount="0"+totalcount;
	         mini.get("companyId").setValue(totalcount);
	         }
   		        },'json');
   		   
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
   		   if (e.value.length !=11) {
                    e.errorText = "必须输入11位号码 ";
                    e.isValid = false;
                }
   		    if (e.isValid) {
   		    	// var pattern = /^(13[0-9]{9})|(14[0-9]){9}|(18[0-9]){9}|(15[0-9][0-9]{8})|(17[0-9]{9})$/;
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
   				var isMobile = /^((\+?86)|(\(\+86\)))?(13[012356789][0-9]{8}|15[012356789][0-9]{8}|18[02356789][0-9]{8}|147[0-9]{8}|1349[0-9]{7})$/;
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
