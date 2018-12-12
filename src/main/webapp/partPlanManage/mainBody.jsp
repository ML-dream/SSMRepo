<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/css/person.css" type=text/css rel=stylesheet>
    <title>零件计划主界面</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body style="width: 100%;height:100%;">
	<fieldset style="width: 99%;" align="center">
		<legend>
			零件计划
		</legend>   	
	   	<table class="query_form_table" width="100%" align="center"  border="0" style="word-break:break-all;">
			<tr align=center><th width="25%">订单号</th><th>产品编号</th><th>产品名称</th><th>版本号</th><th>图号</th><th>产品类型</th></tr>
			<tr><td align=center id="orderId" name="orderId">${result.orderId}</td>
    			<td align=center id="productId" name="productId">${result.productId}</td>
    			<td align=center>${result.productName}</td>
    			<td align=center id="issueNum" name="issueNum">${result.issueNum}</td>
    			<td align=center id="drawingId" name="drawingId">${result.drawingId}</td>
    			<td align=center>${result.productTypeName}</td><input id="productType" name="productType" type="hidden" value="${result.productType}"/>
        		</td>
    		</tr>
    		<tr><td></td>
    			<th>父产品号</th><th>产品数量</th><th>产品规格</th><th>订单交付日期</th><th>产品交付日期</th></tr>
    		<tr>
    			<td>
    	<!--  		<c:if test="${result.isWaiXie=='1'}">
    					<a class="mini-button" iconCls="icon-node" plain="false" onclick="doB('listPartsPlan');">制定外协计划</a>			
    				</c:if>
    	-->	
    				<c:if test="${result.isGongYi=='1'}"><!--做工艺-->
    					<c:choose>
    						<c:when test="${isDone=='1'}">
    							<a class="mini-button" iconCls="icon-node" plain="false" onclick="doB('listPartsPlan');">查看零件计划</a>
    							<a class="mini-button" iconCls="icon-add" plain="false" onclick="doB('bindRfid')">绑定RFID</a> 
    						</c:when>
    						
    						<c:otherwise>
    							<!-- <a class="mini-button" iconCls="icon-add" plain="false" onclick="doB('addPartsPlan');">预编制零件计划</a>
    							 -->
    						</c:otherwise>
    					</c:choose>
    				</c:if>
    	<!--  		<c:if test="${result.isCaiGou=='1'}">
    					<a class="mini-button" iconCls="icon-node" plain="false" onclick="doB('listPartsPlan');">制定采购计划</a>
    				</c:if>  -->	
    			</td>
		        <td align=center id="fproductId" name="fproductId">${result.fproductId}</td>  
        		<td align=center id="productNum">${result.productNum}</td>
        		<td align=center>${result.spec}</td>
        		<td align=center>${result.endTime}</td>
        		<td align=center>${result.eTime}</td>
    		</tr>
  		</table>
   </fieldset>
   
   <iframe id="subIframe" frameborder="0" name="subIframe" style="width:100%;height:75%;" onLoad="javascript:reinitIframeEND();"  ></iframe>
   
   <script type="text/javascript">
   		
   		/* function bindRfid(){
   		document.getElementById("subIframe").src="qualitycheck/ProductCheckin/bindRfid.jsp";
   		
   		} */
   		function doB(doingtype){
   			var orderId = document.getElementById("orderId").innerHTML;
		    var productId = document.getElementById("productId").innerHTML;
		    var fproductId = document.getElementById("fproductId").innerHTML;
		    var issueNum = document.getElementById("issueNum").innerHTML;
		    var drawingId = document.getElementById("drawingId").innerHTML;
		    var productNum = document.getElementById("productNum").innerHTML;
		    /* alert(orderID); */
   			
   			if(doingtype == "addPartsPlan"){
		    	document.getElementById("subIframe").src=
		    		"PartPlanBaseServlet?meth=GoPartPlanAdd&pathTo=partPlan&orderId="+orderId+"&productId="+productId+"&fproductId="+fproductId+"&issueNum="+issueNum+"&drawingId="+drawingId+"&partNum="+productNum;
		    }
		     if(doingtype == "bindRfid"){
		    	document.getElementById("subIframe").src=
		    		"PartPlanBaseServlet?meth=bindRfid&pathTo=bindRfid&orderId="+orderId+"&productId="+productId+"&FProductId="+fproductId+"&issueNum="+issueNum+"&drawingId="+drawingId;
		    		//"qualitycheck/ProductCheckin/bindRfid.jsp;"
		    		} 
		    if(doingtype == "listPartsPlan"){
		    	document.getElementById("subIframe").src=
		    		"PartPlanBaseServlet?meth=SeePartsPlan&pathTo=partPlan&orderId="+orderId+"&productId="+productId+"&FProductId="+fproductId+"&issueNum="+issueNum+"&drawingId="+drawingId; 
		    }
		    else if(doingtype == "edit"){
				    document.getElementById("item_numbt").disabled = "";
				    document.getElementById("save").disabled = "";
				    return;
		    	 }else if(doingtype == "save"){
				        if(document.getElementById("item_numbt").value==""){
					        alert("请输入物料数量！");}
					        document.forsubmit2.action = "../BomEditServlet";
					        document.forsubmit2.submit();
				         	return;
				         }else if(doingtype == "addaofo"){
						   		 document.forsubmit.action = "turn.jsp";
						   	   }else if(doingtype =="del"){
									    alert("删除后不可恢复，是否确定要删除？");
									    document.forsubmit.action = "../bomdelservlet";
		   							 }
		    document.forsubmit.submit(); 
	    }
   		
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
   	   		  		mini.confirm(data.result, "确定？",
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
   	   	
   	   	var Genders = [{ id: '1', text: '是' }, { id: '0', text: '否'}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
        
        function autoHeight() {   
			var ifm= document.getElementById("subIframe");   
			var subWeb = document.frames?document.frames["subIframe"].document:ifm.contentDocument;   
			if(ifm != null && subWeb != null) {
   				ifm.height = subWeb.body.scrollHeight;
   				ifm.width = subWeb.body.scrollWidth;
			}   
		}  
		window.onload=reinitIframeEND(); 
		
		var timer1 = window.setInterval("reinitIframe()", 1500); //定时开始  
		function reinitIframeEND(){  
			var iframe = document.getElementById("subIframe");  
			try{  
    			var bHeight = iframe.contentWindow.document.body.scrollHeight;  
    			var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;  
    			var height = Math.max(bHeight, dHeight);  
    			iframe.height = height;  
			}catch (ex){}  
			// 停止定时  
			window.clearInterval(timer1);  
  		} 
   </script>
  </body>
</html>
