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
    <title>外协工序详情</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>
 <!-- 	<div class="mini-toolbar">
  		<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm()">保存</a>
  	</div>
  -->
 
	<fieldset style="width: 99%;" align="center">
		<legend>
			外协工序详情
		</legend>
   	<div id= "userdiv">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
   			<td><label for="orderId$text">订单号</label></td>
            <td><input id="orderId"  name="orderId" class="mini-textbox" width="100%"  required="true" value="${process.orderId}" enabled="false"/></td>
   			<td><label for="companyName$text">外协单位</label></td>
            <td><input id="companyName" name="companyName" class="mini-textbox" width="100%"  required="true" value="${process.companyName}" enabled="false"/></td>
	        <td><label for="productId$text">图号</label></td>
            <td><input id="productId" name="productId" class="mini-textbox" width="100%" required="true" value="${process.productId}" enabled="false"/>
                <input class="mini-hidden" id="issueNum" name="issueNum" value="${process.issueNum }"/>
                 <input class="mini-hidden" id="operId" name="operId" value="${process.operId}"/>
	       </td>
            
       </tr>
       <tr>
       		<td><label for="operName$text">工序名称</label></td>
            <td><input id="operName"  name="operName" class="mini-textbox" width="100%" required="true" value="${process.operName}" enabled="false"/></td>
            <td><label for="typeName$text">工种</label></td>
            <td><input id="typeName"  name="typeName" class="mini-textbox" width="100%" required="true" allowInput="false" value="${process.typeName}" enabled="false"/></td>
   			<td><label for="num$text">数量</label></td>
            <td><input id="num"  name="num" class="mini-textbox" width="100%" required="true" allowInput="false" value="${process.num}" enabled="false"/></td>
		</tr>
   		<tr>
            <td><label for="deliverTime$text">下发时间</label></td>
            <td><input id="deliverTime" name="deliverTime" class="mini-datepicker" dateFormat="yyyy-MM-dd" width="100%" value="${process.deliverTime}" enabled="false" required="true" /></td>
	        <td><label for="planEndTime$text">预计完成时间</label></td>
            <td><input id="planEndTime" name="planEndTime" class="mini-datepicker" dateFormat="yyyy-MM-dd"  width="100%" value="${process.planEndTime}" enabled="false" required="true"  /></td>
            <td><label for="unitPrice$text">单价</label></td>
          	<td><input id="unitPrice"  name="unitPrice" class="mini-textbox" width="100%" required="true" value="${process.unitPrice}"  onblur="FillTotalPrice" enabled="false" vtype="float"/>
        </tr>
   		<tr>
   		    <td><label for="totalPrice$text">总价</label></td>
	        <td><input id="totalPrice"  name="totalPrice" class="mini-textbox" width="100%" vtype="float"  value="${process.totalPrice}" enabled="false" required="true" /></td>
   		   	<td><label for="actuallyEndTime$text">实际完成时间</label></td>
            <td><input id="actuallyEndTime"  name="actuallyEndTime" class="mini-datepicker" dateFormat="yyyy-MM-dd"  width="100%" value="${process.actuallyEndTime}"  enabled="false"/></td> 
            <td><label for="quality$text">质量情况</label></td>
	        <td><input id="quality"  name="quality" class="mini-combobox" width="100%" url="data/quality.txt"  textField="text" valueField="id" allowInput="false" renderer="onQualityRenderer" value="${process.quality}"  enabled="false"/></td>	   		

   		</tr>
   		<tr>
   		    <td><label for="qualityFine$text">质量罚款</label></td>
	        <td><input id="qualityFine"  name="qualityFine" class="mini-textbox" width="100%" vtype="float" value="${process.qualityFine}"  enabled="false"/></td>
   			<td><label for="alreadyPay$text">已付款</label></td>
            <td><input id="alreadyPay"  name="alreadyPay" class="mini-textbox" width="100%" vtype="float" value="${process.alreadyPay}"  enabled="false"/></td>
            <td><label for="waitPay$text">未付款</label></td>
            <td><input id="waitPay"  name="waitPay" class="mini-textbox" width="100%" vtype="float" value="${process.waitPay}"   enabled="false"/></td>
        </tr>
   		<tr>
            <td><label for="isOpenTicket$text">是否开票</label></td>
            <td><input id="isOpenTicket"  name="isOpenTicket" class="mini-combobox" style="width:100%;" textField="text" valueField="id" 
    			url="data/trueOrFalse.txt"    allowInput="false"  renderer="onGenderRenderer" value="${process.isOpenTicket}"  enabled="false"/>  
            </td>
            <td><label for="payTime$text">付款周期</label></td>
            <td><input id="payTime" name="payTime" class="mini-datepicker" dateFormat="yyyy-MM-dd"  width="100%"   value="${process.payTime}"  enabled="false"/></td>
            <td><label for="isBusy$text">是否急件</label></td>
            <td><input id="isBusy"  name="isBusy" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/trueOrFalse.txt"    allowInput="false" showNullItem="false" renderer="onGenderRenderer" value="${process.isBusy}"  enabled="false"/>  
    		</td>
        </tr>
   		<tr height="60px;">
   			<td><label for="memo$text">附录</label></td>
	        <td colspan="5"><input id="memo"  name="memo" class="mini-textarea" emptyText="请输入备注" 
	        	width="100%" height="100%" value="${process.memo}"  enabled="false"/></td>
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
              	data.deliverTime = mini.get("deliverTime").getFormValue();
             	data.planEndTime = mini.get("planEndTime").getFormValue();
     	        data.actuallyEndTime = mini.get("actuallyEndTime").getFormValue();
            	data.payTime=mini.get("payTime").getFormValue();
                var params = eval("("+mini.encode(data)+")");
                var url = 'ProcessOutAssistStatSaveServlet';
   		        jQuery.post(url, params, function(data){
		                     alert(data.result);
		        //             window.location.href = window.location.href;
		                 },'json');
            }
   		}
   	    function callbackFun(data)
   	    {
   	        alert(data.result);
   	         //window.location.href=window.location.href;
   	      	window.location.href="employeeManage/editEmployee.jsp";
   	    }
   	    
   	    function FillTotalPrice(){
   	         var unitPrice=mini.get("unitPrice").getValue();
   	         var num=mini.get("num").getValue();
   	         var totalPrice=num*unitPrice;
   	         mini.get("totalPrice").setValue(totalPrice);
   	         }
   	    function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                url: "outAssistComManage/selectOutAssistComWindow.jsp",
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
                            btnEdit.setText(data.companyId);
                            mini.get("companyName").setValue(data.companyName);
                        }
                    }
                }
            });
        }
   	    
   	    function onButtonEditDept(e) {
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
   	   	
   	   	var Genders = [{id: "0", text: "否"},{id: "1", text: "是"}];
        function onGenderRenderer(e) {
           for (var i = 0, l = Genders.length; i < l; i++) {
              var g = Genders[i];
               if (g.id == e.value) return g.text;
           }
          return "";
       }
   	   	
   	   	
   	  var Quality=[{id:"1",text:"差"},{id:"2",text:"一般"},{id:"3",text:"良好"},{id:"4",text:"很好"}];
      function onQualityRenderer(e){
            for (var i = 0, l = Quality.length; i < l; i++) {
              var g = Quality[i];
               if (g.id == e.value) return g.text;
           }
          return "";
       }
        
   </script>
  </body>
</html>

