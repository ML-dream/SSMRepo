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
   
    <title>订单详细信息</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body onload="getCurrentTime()">
  	<div class="mini-toolbar">
   		<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm()">保存</a>
   		<!-- 
	    <a class="mini-button" iconCls="icon-add">增加</a>
	    <a class="mini-button" iconCls="icon-edit">修改</a>
	    <a class="mini-button" iconCls="icon-remove">删除</a>
	     -->
	    <span class="separator"></span>
	    <a class="mini-button" plain="false" iconCls="icon-undo" onclick="back()">返回</a>
	    <!-- 
	    <span class="separator"></span>
	    <a class="mini-button" plain="false" iconCls="icon-add" onclick="addItem()">添加子物料</a>
	    <a class="mini-button" plain="true">修改</a>
	    <a class="mini-button" plain="true">删除</a>
	     -->
	    <span class="separator"></span>
	    <!-- 
	    <input class="mini-textbox" />   
	    <a class="mini-button" plain="true">查询</a>
	     -->
	</div>
  	<fieldset style="width: 100%;" align="center">
		<legend>
			订单信息
		</legend>
   		<form name="userdiv" id="userdiv" action="AddOrderDetailServlet" method="post" enctype="multipart/form-data">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
   			<td><label for="orderId$text">订单编号*</label></td>
            <td><input id="orderId"  name="orderId" class="mini-textbox"  width="100%" required="true" readonly="readOnly"/></td>
            
            <td><label for="productId$text">图号*</label></td>
            <td><input id="productId"  name="productId" class="mini-combobox" style="width:100%;" data="" url= "LoadCusProductId?customerId=${param.customerId}" allowinput = "true" onblur="search"/>
            	<input id="customerId"  name="customerId" class="mini-hidden" value="${param.customerId}"  />
            </td>
   			<td><label for="productName$text">产品名称*</label></td>
            <td><input id="productName"  name="productName" class="mini-textbox"  width="100%" required="true"/></td>
       </tr>
       <tr>
       		<td><label for="FProductId$text">父产品号*</label></td>
            <td><input id="FProductId"  name="FProductId" class="mini-textbox"  width="100%" required="true" readonly="readOnly"/></td>
            	<td><label for="issueNum$text">版本号*</label></td>
        	<td><input id="issueNum"  name="issueNum" class="mini-textbox"  width="100%" value="1"/></td>  
            <td><label for="productNum$text">产品数量*</label></td>
            <td><input id="productNum"  name="productNum" class="mini-textbox"  width="100%" required="true"/></td>

       </tr>
       <tr> 	
            <td><label for="unitPrice$text">单价*</label></td>
        	<td><input id="unitPrice"  name="unitPrice" class="mini-textbox"  width="100%" required="true"/></td>
        	<td><label for="islailiao$text">是否来料</label></td>
            <td><input id="islailiao"  name="islailiao" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/islailiao.txt" required="true" allowInput="false" showNullItem="true" nullItemText="请选择..." value="0"/>  
            </td>
            <td><label for="material$text">材料</label></td>
            <td><input id="material"  name="material" class="mini-combobox"  url= "LoadStuff" width="100%" allowInput="true"/></td> 
       </tr>
       <tr>         
          	<td><label for="iswaixie$text">是否外协</label></td>
            <td><input id="iswaixie"  name="iswaixie" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/trueOrFalse.txt"  allowInput="false" showNullItem="true" nullItemText="请选择..." value="0"/>  
            </td>
            <td><label for="itemTypeId$text">产品种类</label></td>
            <td><input id="itemTypeId"  name="itemTypeId" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="GetItemTypeServlet" value="L"  allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            </td> 
            <td><label for="drawingId$text">产品号</label></td>
        	<td><input id="drawingId"  name="drawingId" class="mini-textbox"  width="100%"  /></td>
       </tr>
       <tr>    	
        	<td><label for="spec$text">产品规格</label></td>
            <td><input id="spec"  name="spec" class="mini-textbox"  width="100%" value= "1"/></td>
        	<td><label for="lot$text">批次</label></td>
        	<td><input id="lot"  name="lot" class="mini-textbox"  width="100%" value="1"/></td>
       
 <!--      <tr> 
       		<td><label for="productStatus$text">产品状态</label></td>

            <td><input id="productStatus"  name="productStatus" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/productStatus.txt" required="true" allowInput="false" showNullItem="true" nullItemText="请选择..." value="0"/>  
            </td>
       		<td><label for="SBTime$text">实际开始时间</label></td>
            <td><input id="SBTime"  name="SBTime" class="mini-textbox"  width="100%" required="true"  emptyText="日期格式：2000-01-01"/></td>
       
            <td><label for="SETime$text">实际结束时间</label></td>
            <td><input id="SETime"  name="SETime" class="mini-textbox"  width="100%" required="true"  emptyText="日期格式：2000-01-01"/></td>
 </tr>   --> 
      
           <td><label for="deptNo$text">部门号</label></td>
            <td><input id="deptNo"  name="deptNo" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/dept.txt"  allowInput="false" showNullItem="true" nullItemText="请选择..." value="cj"/>  
            </td>
       </tr>
       <tr>   
            <td><label for="BTime$text">开始时间</label></td>
      <!--  <td><input id="BTime"  name="BTime" class="mini-textbox"  width="100%" required="true"  emptyText="日期格式：2000-01-01"/></td>  --> 
            <td><input id="BTime" name ="BTime" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd" allowInput="true"   emptyText="日期格式：2000-01-01" /></td>
            <td><label for="ETime$text">结束时间</label></td>
     <!--   <td><input id="ETime"  name="ETime" class="mini-textbox"  width="100%" required="true"  emptyText="日期格式：2000-01-01"/></td>    -->      
            <td><input id="ETime" name ="ETime" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd" allowInput="true"  emptyText="日期格式：2000-01-01" required="true"/></td>
            <!-- 
            <td><label for="finishNum$text">完成数量</label></td>
            <td><input id="finishNum"  name="finishNum" class="mini-textbox"  width="100%" required="true"/></td>
   			 -->

      	   <td>上传图纸</td>
   			<td align="left"><input type="file" name="paper"/></td>
       </tr>
       <tr> 
      		<td>上传其他文件</td>
   			<td align="left"><input type="file" name="otherPaper"/></td>
      </tr>
   		<tr height="60px;">
   			<td><label for="memo$text">附录</label></td>
	        <td colspan="5"><input id="memo"  name="memo" class="mini-textarea" emptyText="请输入备注" 
	        	width="100%" height="100%"/></td>
	        
         </tr>
   	</table>
  	</form>
   </fieldset>
   
   <script>
   		mini.parse();
   		/*
   		function getForm(){
   			var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	var data = form.getData();
            	//data.leave=leave;
                var params = eval("("+mini.encode(data)+")");
                var url = 'AddOrderDetailServlet';
   		        jQuery.post(url, params, callbackFun, 'json');
            }
   		}
   		*/
   		
   		 function getCurrentTime(){
   		      var now=new Date();
   		      var year = now.getFullYear();
		      var month = (((now.getMonth()+1) < 10) ? "0" : "") + (now.getMonth()+1);
		      var day = ((now.getDate() < 10) ? "0" : "") + now.getDate();
 //           document.getElementById('BTime').innerHTML = "year+"-"+month+"-"+day"; 
   		       mini.get("BTime").setValue(year+"-"+month+"-"+day);
   		      }
   		     
   		         
   		
   		function search(){
   		        var form = new mini.Form("#userdiv");
                var data = form.getData();
//            	data.productId =mini.get("productId").getValue();
                var params = eval("("+mini.encode(data)+")");
                var url = 'DrawingIdSearchServlet';
   		        jQuery.post(url, params, function(data){
   		        data = mini.decode(data);
                mini.get("productName").setValue(data[0].productName);
                mini.get("drawingId").setValue(data[0].drawingId);
                mini.get("issueNum").setValue(data[0].issueNum);
                mini.get("spec").setValue(data[0].spec);
                mini.get("unitPrice").setValue(data[0].unitPrice);
   	   		        },'json');
            }
            
  //         function issueNumSearch(){
  // 		        var form = new mini.Form("#userdiv");
  //              var data = form.getData();
   //         	data.productId =mini.get("productId").getValue();
  //              var params = eval("("+mini.encode(data)+")");
  //              var url = 'GetIssueNumServlet';
   //		        jQuery.post(url, params, function(){},'json');
 //           }
   		
   		function getForm(){
   			var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
            	jQuery.ajax({
      				type: "POST",
      				url: "AddOrderDetailServlet",
      				dataType: "json", 
      				cache: false,
      				enctype: 'multipart/form-data',
      				data: new FormData($('#userdiv')[0]),
      				processData: false,
    				contentType: false,
      				success: function (data) {
        				//alert(data.result);
        				var s = confirm (data.result +"是否刷新页面?");
        				if(s ==true){
        					window.location.href = window.location.href;
        				}
      				}
    			});
            }
        }

		function back(){
			window.history.back(-1);
		}

   		function addItem(){
			var orderId = mini.get("orderId").getValue();
			window.location="itemManage/AddItem.jsp?orderId="+orderId;
   	   	}
	   		
   	    function callbackFun(data)
   	    {
   	         alert(data.result);
   	         var orderId = mini.get("orderId").value;
   	         //window.close();
   	      	 //window.opener.location.href = window.opener.location.href;
   	      	 window.location="GoOrderDetailListServlet?orderId="+orderId;
   	    }

   	 function onButtonEdit(e) {
         var btnEdit = this;
         mini.open({
             url: "orderManage/selectCustomerWindow.jsp",
             title: "选择列表",
             width: 650,
             height: 480,
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
        mini.get("FProductId").value = Request['productId'];
   </script>
  </body>
</html>
