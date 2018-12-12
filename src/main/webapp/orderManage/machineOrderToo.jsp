<%@ page language="java" import="java.util.*,com.wl.forms.Employee" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
  
  <body>
  	<div class="mini-toolbar">
  		<c:choose>
        	<c:when test="${para=='assert'}">
  			</c:when>
  			<c:otherwise>
<!-- 		   		<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm()">保存</a> -->
			    <span class="separator"></span>
			    <a class="mini-button" plain="false" iconCls="icon-undo" onclick="back()">返回</a>
			    <!-- <span class="separator"></span>
			    <a class="mini-button" iconCls="icon-node" plain="false"  onclick="seeOrderDetail()">查看订单内容</a>
			    <span class="separator"></span> -->
			  <!--   <a class="mini-button" iconCls="icon-add" plain="false"  onclick="addOrderDetail()">增加产品信息</a>  -->
			    <span class="separator"></span> 
			    <a class="mini-button" iconCls="icon-node" plain="false"  onclick="CustomerDetail()">修改客户信息</a>
			    <span class="separator"></span>
			   </c:otherwise>
		    </c:choose>
	    <!-- <a class="mini-button" iconCls="icon-addnew" plain="false"  onclick="CustomerItem()">添加客户资产</a>
	    <span class="separator"></span>
	    <a class="mini-button" iconCls="icon-node" plain="false"  onclick="CustomerItemDetail()">客户资产列表</a> -->
	    <!-- 
	    <input class="mini-textbox" />   
	    <a class="mini-button" plain="true">查询</a>
	     -->
	</div>
   	
   	<fieldset style="width: 100%;" align="center">
		<legend>
			订单信息
		</legend>
		<form name="userdiv" id="userdiv" action="EditOrderSpecServlet" method="post" enctype="multipart/form-data">
   		<table align="center" width="100%" border="0" style="word-break:break-all;border-collapse:collapse" bgcolor="#FFFFFF" >
   		<tr>
   			<td><label for="orderId$text">订单编号</label></td>
            <td><input id="orderId"   name="orderId" class="mini-textbox"  width="100%" required="true" value="${order.orderId}" readonly="readOnly"/></td>
            <td><label for="customer$text">客户</label></td>
            <td><input id="customer" name="customer" class="mini-buttonedit" readonly="readOnly" width="100%" 
            	onbuttonclick="onButtonEdit" textName="companyName" required="true" value="${order.customer}" text="${order.companyName}"/>
   				<td><label for="connector$text">联系人</label></td>
            <td><input id="connector"  name="connector" class="mini-textbox"  width="100%" required="true" readonly="readOnly"  value="${order.connector}"/></td>
        </tr>
       	<tr>
            <td><label for="connectorTel$text">联系人电话</label></td>
            <td><input id="connectorTel"  name="connectorTel" class="mini-textbox"  width="100%" required="true" readonly="readOnly" value="${order.connectorTel}"/></td>
   			
   			<td><label for="deptUser$text">使用部门</label></td>
            <td><input id="deptUser"  name="deptUser" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/dept.txt" required="true" allowInput="false" showNullItem="true" readonly="readOnly" nullItemText="请选择..." value="${order.deptUser}"/>  
            </td>
            <td><label for="orderDate$text">订单日期</label></td>
            <td><input id="orderDate"  name="orderDate" class="mini-textbox"  width="100%" required="true" readonly="readOnly"  emptyText="日期格式：2000-01-01" value="${order.orderDate}" readonly="readOnly"/></td>
        </tr>
       	<tr>
       		<td><label for="endTime$text">交付日期</label></td>
            <td><input id="endTime"  name="endTime" class="mini-textbox"  width="100%" required="true"  readonly="readOnly" emptyText="日期格式：2000-01-01" value="${order.endTime}"/></td>
            <td><label for="orderStatus$text">订单状态</label></td>
            <td><input id="orderStatus"  name="orderStatus" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/orderStatus.txt"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..." value="${order.orderStatus}" readonly="readOnly"/>  
            </td>
   		</tr>
   		
   		 <tr>

            <td><label for="equipCode$text">设备预约</label></td>
            <td>
	    	<!-- <input id="equipCode" name="equipCode" class="mini-buttonedit" width="66%" onbuttonclick="onButtonEditMachine" required="false" allowInput="false"/>
       	
  			<a class="mini-button" iconCls="icon-search" width="30%" plain="false"  onclick="search()">保存并预约设备</a> -->
  			</br> 
  			
  			<div style="text-align: center;">
  			<a class="mini-button" iconCls="icon-search" width="100%" plain="false"  onclick="search2()">确认信息并预约</a>  	</br>  	</br>
  	<div>
  		</td>
       </tr>
       <tr>
        <div><img src='<%=path %>/machinePicture/testjpg01.jpg' width='100' height='100'  alt='' /></div>
       </tr>
   		
   		
   		<%-- <tr>
   			<td>上传合同</td>
   			<td align="left"><input type="file" name="orderPaper"/></td>
   			
   			<td>上传对账函</td>
   			<td align="left"><input type="file" name="duizhanghan"/></td>
   			
   			<td>其他文件</td>
   			<td align="left"><input type="file" name="otherPaper"/></td>
   		</tr>
   		<tr>
   			<td>下载合同</td>
   			<td align="left"><a href="DownLoadOrderFileServlet?filename=${order.orderPaper}">${order.orderPaper}</a><br/></td>
   			
   			<td>下载对账函</td>
   			<td align="left"><a href="DownLoadOrderFileServlet?filename=${order.duizhanghan}">${order.duizhanghan}</a></td>
   			
   			<td>下载其他文件</td>
   			<td align="left"><a href="DownLoadOrderFileServlet?filename=${order.otherPaper}">${order.otherPaper}</a></td>
   		</tr>
   		<tr height="60px;">
   			<td><label for="memo$text">附录</label></td>
	        <td colspan="5"><input id="memo"  name="memo" class="mini-textarea" emptyText="请输入备注" width="100%" height="100%"/></td>
         </tr> --%>
   	</table>
   	</form>
	</fieldset>
   <script>
   		mini.parse();
   	 	var machineName;
   	 	var machineId;
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
                var url = 'EditOrderSpecServlet';
   		        jQuery.post(url, params, callbackFun, 'json');
            }
   		}
   		*/
   		
   	   function search2(){
   			var orderId="${order.orderId}";
   			var connector="${order.connector}";
   			
	        //window.open("EditOrderDetailServlet?orderId=" + orderId,
	        //        "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
	    	window.location="OrderSpecServlet?orderId=" +orderId +"&connector="+connector+"&isModify="+"3";
		}
   		
   		
   	 grid.on("drawcell", function (e) {
         var record = e.record,
             column = e.column,
             field = e.field,
             value = e.value;

         //将性别文本替换成图片
         if (column.field == "machineId") {
             if (e.value == "C0000049") {
                 e.cellHtml = "<img src='/imgs/addfolder.gif' width='20px' height='20px'  alt='' />"
             } 
             else {
                 e.cellHtml = "<span class='icon-boy'></span>"
             }
         }

         
     });
   		
   		
   		
   		
   		function onButtonEditMachine(e) {
            var btnEdit = this;
            mini.open({
                url: "machineManage/selectMachineWindow.jsp",
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
                            btnEdit.setValue(data.machineId);
                            btnEdit.setText(data.machineName);
                            machineName=data.machineName;
                            machineId=data.machineId;
                            //mini.get("connector").setValue(data.connector);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                            $("#machineName").attr("value",machineName);
                        }
                    }
                }
            });
        }
   		
   		function getForm(){
   			var orderId = mini.get("orderId").getValue();
   			
   			
   			/* window.location="insertMachine?orderId=" + orderId; */
   			
   			jQuery.ajax({
   				type: "POST",
   				url: "insertMachine?orderId="+orderId+"&machineId="+machineId,
   				dataType: "json", 
   				cache: false,
   				enctype: 'multipart/form-data',
   		
   				processData: false,
   				contentType: false,
   				success: function (data) {
   					//mini.get("foNo").setValue(data);
   					var result=data.result;
   					alert(result)
   					
   					
   				}
   			});
   		}
   		//
   		function search(e) {
            var equipCode = $("input[name='equipCode']").val();
            var btnEdit=this;
            if(equipCode=="") {
                alert("请先选择设备…………");
            }else{
            	var orderId= mini.get("orderId").getValue();
           		  getForm();  
            
            	mini.open({
                    url: "machineManage/machineTimeGT.jsp",
                    title:machineName+"(当前订单为"+orderId+"请预定设备时间…………)",
                    width: 1200,
                    height: 600,
                    //父窗口向子窗口传值；                    
                    onload: function () {
                        var iframe = this.getIFrameEl();
  						
                        var data = { action:equipCode,orderId:orderId};
                        iframe.contentWindow.SetData(data);
                        
                    },
                    ondestroy: function (action) {
                        if (action == "ok") {
                            var iframe = this.getIFrameEl();
                            var data = iframe.contentWindow.GetData();
                            data = mini.clone(data);    //必须
                            if (data) {
                                btnEdit.setValue(data.machineId);
                                btnEdit.setText(data.machineName);
                               
                                
                            }
                            
                        }
                    }
                });
            }
            
        }
   		
   		
   		
   		function seeOrderDetail(){
   			var orderId = mini.get("orderId").getValue();
   			window.location="GoOrderAllDetailServlet?orderId=" + orderId;
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
		function toCustomerItem(){
   			var para=Request['para'];
   			//alert(para); 
   			if(para=="assert"){
   				CustomerItem();
   			}
   		}
        var Request = new Object(); 
        Request = GetRequest();
   		toCustomerItem();
   		
   		function getForm2(){		    
 		  	var formData = new mini.Form("#userdiv");
 		  	//var temp = new FormData($('#userdiv')[0]);
 		  	var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
 		  		jQuery.ajax({
      				type: "POST",
      				url: "ModifyOrderSpecServlet",
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

   		function back(){
			window.history.back(-1);
		}

		function download(data){
			if(data==1){
				alert("订单文件下载！！");
			}
			if(data=='2'){
				alert("对账函下载！！");
			}
			
		}

   		function CustomerDetail(){
			var customer = mini.get("customer").getValue();
			var connector = mini.get("connector").getValue();
			window.location="EditCustomerDetailServlet?companyId="+customer+"&connector="+connector;
   	   	}
   	   	
   	   	function CustomerItem(){
   	   		var orderId = mini.get("orderId").getValue();
   	   		var customerId = mini.get("customer").getValue();
   	   		var customerName = mini.get("customer").getText();
   	   		var connector = mini.get("connector").getValue();
   	   		//window.location="GoAddCustomerItemServlet?orderId="+orderId;
   	   		window.location = "orderManage/AddCustomerItem.jsp?orderId="+orderId+"&customerId="+customerId+"&customerName="+encodeURI(encodeURI(customerName));
   	   	}
   	   	
   	   	function CustomerItemDetail(){
   	   		var orderId = mini.get("orderId").getValue();
   	   		var customerId = mini.get("customer").getValue();
   	   		var customerName = mini.get("customer").getText();
   	   		var connector = mini.get("connector").getValue();
   	   		//window.location="GoAddCustomerItemServlet?orderId="+orderId;
   	   		window.location = "GoCustomerItemDetailServlet?orderId="+orderId+"&customerId="+customerId+"&customerName="+encodeURI(encodeURI(customerName));
   	   	}
	   		
   	    function callbackFun(data){
   	         alert(data.result);
   	         //window.close();
   	      	 //window.opener.location.href = window.opener.location.href;
   	      	 window.location="orderManage/OrderList.jsp"
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

        function addOrderDetail(){
			var orderId = mini.get("orderId").value;
			var customerId = mini.get("customer").getValue();
			window.location = "orderManage/AddOrderDetail.jsp?orderId="+orderId+"&productId="+orderId+"&customerId="+customerId;
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
   </script>
  </body>
</html>
