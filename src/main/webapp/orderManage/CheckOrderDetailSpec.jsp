<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
   
    <title>产品详细信息</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>
  <div class="mini-toolbar">
	    <a class="mini-button" plain="false" iconCls="icon-undo" onclick="back()">返回</a>
	    <span class="separator"></span>
	</div>
  <fieldset style="width: 100%;" align="center">
		<legend>
			产品信息
		</legend>
   	<div id= "userdiv">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
   			<td><label for="orderId$text">订单编号</label></td>
            <td><input id="orderId"  name="orderId" class="mini-textbox"  width="100%" required="true" value="${order.orderId}"  enabled="false"/></td>
            <td><label for="productId$text">图号</label></td>
            <!-- 
            <td><input id="productId" name="productId" class="mini-buttonedit" width="100%" 
            	onbuttonclick="onButtonEdit" textName="companyName" required="true" value="${order.customer}" text="${order.companyName}"/>
   			 -->
   			<td><input id="productId"  name="productId" class="mini-textbox"  width="100%" required="true" value="${order.productId}" enabled="false"/></td>
            <td><label for="productName$text">产品名称</label></td>
            <td><input id="productName"  name="productName" class="mini-textbox"  width="100%" required="true" value="${order.productName}"enabled="false"/></td>
       </tr>
       <tr>
   			<td><label for="FProductId$text">父产品号</label></td>
            <td><input id="FProductId"  name="FProductId" class="mini-textbox"  width="100%" required="true" value="${order.fproductId}" enabled="false"/></td>
            <td><label for="spec$text">产品规格</label></td>
            <td><input id="spec"  name="spec" class="mini-textbox"  width="100%" required="true" value="${order.spec}" enabled="false"/></td>
   			<td><label for="issueNum$text">版本号</label></td>
            <td><input id="issueNum"  name="issueNum" class="mini-textbox"  width="100%" required="true" value="${order.issueNum}" enabled="false"/></td>
       </tr>
       <tr>    
   			<td><label for="productNum$text">产品数量</label></td>
   			<!-- 
            <td><input id="productNum"  name="productNum" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/dept.txt" required="true" allowInput="false" showNullItem="true" nullItemText="请选择..." value="${order.deptUser}" enabled="false"/>  
            </td>
             -->
       		<td><input id="productNum"  name="productNum" class="mini-textbox"  width="100%" required="true" value="${order.productNum}" enabled="false"/></td>
            <td><label for="BTime$text">开始时间</label></td>
            <td><input id="BTime"  name="BTime" class="mini-textbox"  width="100%" required="true"  emptyText="日期格式：2000-01-01" value="${order.bTime}" enabled="false"/></td>
            <td><label for="ETime$text">结束时间</label></td>
            <td><input id="ETime"  name="ETime" class="mini-textbox"  width="100%" required="true"  emptyText="日期格式：2000-01-01" value="${order.eTime}" enabled="false"/></td>
       </tr>
       <tr>
       		<td><label for="deptNo$text">部门号</label></td>
            <td><input id="deptNo"  name="deptNo" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/dept.txt" required="true" allowInput="false" showNullItem="true" nullItemText="请选择..." value="${order.deptId}" enabled="false"/>  
            </td>
     <!--      <td><label for="SBTime$text">实际开始时间</label></td>
            <!-- 
            <td><input id="SBTime"  name="SBTime" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/orderStatus.txt"   required="true" allowInput="false" showNullItem="true" nullItemText="请选择..." value="${order.orderStatus}" enabled="false"/>  
            </td>
             -->
      <!--     <td><input id="SBTime"  name="SBTime" class="mini-textbox"  width="100%" required="true"  emptyText="日期格式：2000-01-01" value="${order.sbTime}" enabled="false"/></td>
            <td><label for="SETime$text">实际结束时间</label></td>
            <td><input id="SETime"  name="SETime" class="mini-textbox"  width="100%" required="true"  emptyText="日期格式：2000-01-01" value="${order.seTime}" enabled="false"/></td>
       -->
       </tr>
    <!--    <tr> 
            <td><label for="productStatus$text">产品状态</label></td>
            <td><input id="productStatus"  name="productStatus" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/productStatus.txt" required="true" allowInput="false" showNullItem="true" nullItemText="请选择..." value="${order.productStatus}" enabled="false"/>  
            </td>  --> 
            <!-- 
            <td><label for="finishNum$text">完成数量</label></td>
            <td><input id="finishNum"  name="finishNum" class="mini-textbox"  width="100%" required="true" value="${order.orderDate}" enabled="false"/></td>
   			 -->
    <!--    </tr>  -->   
   	   <tr height="60px;">
   			<td><label for="memo$text">附录</label></td>
	        <td colspan="5"><input id="memo"  name="memo" class="mini-textarea" emptyText="请输入备注"  width="100%" height="100%" value="${order.memo}" enabled="false"/></td>
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
            	//data.leave=leave;
                var params = eval("("+mini.encode(data)+")");
                var url = 'EditOrderDetailSpecServlet';
   		        jQuery.post(url, params, callbackFun, 'json');
            }
   		}

   		function back(){
			window.history.back(-1);
		}
		
   		function addItem(){
			var orderId = mini.get("orderId").getValue();
			var productId = mini.get("productId").getValue();
			//window.location="itemManage/AddItem.jsp?orderId="+orderId+"&productId="+productId;
			window.location = "orderManage/AddOrderDetail.jsp?orderId="+orderId+"&productId="+productId;
   	   	}
   	   	
   	    function callbackFun(data)
   	    {
   	         alert(data.result);
   	         //window.close();
   	      	 //window.opener.location.href = window.opener.location.href;
   	      	 //window.location="orderManage/showOrder.jsp";
   	      	 window.location.href = window.location.href;
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
   </script>
  </body>
</html>
