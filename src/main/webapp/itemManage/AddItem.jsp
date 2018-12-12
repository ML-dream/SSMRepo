<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
   
    <title>添加物料</title>
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
  	</div>
	<fieldset style="width: 100%;" align="center">
		<legend>
			添加物料
		</legend>
   		<div id= "userdiv">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
   			<td><label for="itemId$text">物料编号</label></td>
            <td><input id="orderId" name="orderId" class="mini-textbox" visible="false"/>
            	<input id="itemId"  name="itemId" class="mini-textbox"  width="100%" required="true" /></td>
            <td><label for="itemTypeId$text">物料种类</label></td>
            <td><input id="itemTypeId"  name="itemTypeId" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="GetItemTypeServlet" value="L"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            </td>
            <td><label for="itemName$text">物料名称</label></td>
            <td><input id="itemName"  name="itemName" class="mini-textbox"  width="100%" required="true" /></td>
        </tr>
       	<tr>
            <td><label for="itemDrawing$text">图号</label></td>
            <td><input id="itemDrawing"  name="itemDrawing" class="mini-textbox"  width="100%" required="true" /></td>
            <td><label for="FItemId$text">父物料</label></td>
            <td><input id="FItemId" name="FItemId" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" textName="companyName" required="true" allowInput="false" value="0" text="无"/></td>
            <!-- 
            <td><input id="FItemId"  name="FItemId" class="mini-textbox"  width="100%" required="true" /></td>
             -->
            <td><label for="itemSpecification$text">物料规格</label></td>
            <td><input id="itemSpecification"  name="itemSpecification" class="mini-textbox"  width="100%" required="true" /></td>
        </tr>
        <tr>    
            <td><label for="purchaseUnite$text">采购单位</label></td>
            <td><input id="purchaseUnite"  name="purchaseUnite" class="mini-textbox"  width="100%" required="true" /></td>
            <td><label for="itemPrice$text">物料价格</label></td>
            <td><input id="itemPrice"  name="itemPrice" class="mini-textbox"  width="100%" required="true" vtype="range:0,99999999"/></td>
            <td><label for="priceUnit$text">价格单位</label></td>
            <td><input id="priceUnit"  name="priceUnit" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/priceUnit.txt" value="YUAN"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            </td>
        </tr>
       	<tr>  
       		<!-- 
       		<td><label for="itemStatus$text">物料状态</label></td>
            <td><input id="itemStatus"  name="itemStatus" class="mini-textbox"  width="100%" required="true" /></td>
             -->
            <td><label for="lotSize$text">批量大小</label></td>
            <td><input id="lotSize"  name="lotSize" class="mini-textbox"  width="100%" required="true" /></td> 
       		<td><label for="materialMark$text">材料标识</label></td>
            <td><input id="materialMark"  name="materialMark" class="mini-textbox"  width="100%" /></td> 
       		<td><label for="itemAttri$text">物料属性</label></td>
            <td><input id="itemAttri"  name="itemAttri" class="mini-textbox"  width="100%"/></td>  
        </tr>
       	<tr>
            <td><label for="leadTime$text">提前期</label></td>
            <td><input id="leadTime"  name="leadTime" class="mini-textbox"  width="100%" /></td>
            <td><label for="safeStock$text">安全库存</label></td>
            <td><input id="safeStock"  name="safeStock" class="mini-textbox"  width="100%"/></td>
            <td><label for="stockLow$text">最低库存</label></td>
            <td><input id="stockLow"  name="stockLow" class="mini-textbox"  width="100%"/></td>
        </tr>
       	<tr>
            <td><label for="stockHigh$text">最高库存</label></td>
            <td><input id="stockHigh"  name="stockHigh" class="mini-textbox"  width="100%"/></td>
            <td><label for="itemWeight$text">物料重量</label></td>
            <td><input id="itemWeight"  name="itemWeight" class="mini-textbox"  width="100%"/></td>
            <td><label for="weightUnit$text">重量单位</label></td>
            <td><input id="weightUnit"  name="weightUnit" class="mini-textbox"  width="100%"/></td>
        </tr>
       	
       	<tr>   
       		
            <!-- 
            <td><label for="customer$text">客户</label></td>
            <td><input id="customer" name="customer" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" textName="companyName" required="true" allowInput="false"/>
   			<td><label for="connector$text">联系人</label></td>
            <td><input id="connector"  name="connector" class="mini-textbox"  width="100%" required="true"/></td>
             -->
        </tr>
        <tr height="60px;">
   			<td><label for="memo$text">附录</label></td>
	        <td colspan="5"><input id="memo"  name="memo" class="mini-textarea" emptyText="请输入备注" 
	        	width="100%" height="100%"/></td>
         </tr>
        <!-- 
       	<tr>
            <td><label for="connectorTel$text">联系人电话</label></td>
            <td><input id="connectorTel"  name="connectorTel" class="mini-textbox"  width="100%" required="true"/></td>
   			<td><label for="deptUser$text">使用部门</label></td>
            <td><input id="deptUser"  name="deptUser" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/dept.txt" value="cj"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            </td>
            <td><label for="orderDate$text">订单日期</label></td>
            <td><input id="orderDate"  name="orderDate" class="mini-textbox"  width="100%" required="true"  emptyText="日期格式：2000-01-01"/></td>
        </tr>
       	<tr>
            <td><label for="endTime$text">交付日期</label></td>
            <td><input id="endTime"  name="endTime" class="mini-textbox"  width="100%" required="true"  emptyText="日期格式：2000-01-01"/></td>
            <td><label for="orderStatus$text">订单状态</label></td>
            <td><input id="orderStatus"  name="orderStatus" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/orderStatus.txt" value="1"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            </td>
   		</tr>
   		<tr height="60px;">
   			<td><label for="memo$text">附录</label></td>
	        <td colspan="5"><input id="memo"  name="memo" class="mini-textarea" emptyText="请输入备注" 
	        	width="100%" height="100%"/></td>
         </tr>
          -->
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
                var url = 'AddItemServlet';
   		        jQuery.post(url, params, function(data){
   	   		        alert(data.result);
   	   		  		window.location.href=window.location.href;
   	   		        },'json');
            }
   		}

   		function back(){
			window.history.back(-1);
		}
		function refresh(){
			window.location.href=window.location.href;
		}
   		function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "itemManage/selectItemWindow.jsp",
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
                            btnEdit.setValue(data.itemid);
                            btnEdit.setText(data.itemname);
                            //mini.get("FItemId").setValue(data.itemid);
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
        mini.get("orderId").value = Request['orderId'];
        mini.get("FItemId").value = Request['productId'];
        mini.get("FItemId").text = Request['productId'];
   </script>
  </body>
</html>
