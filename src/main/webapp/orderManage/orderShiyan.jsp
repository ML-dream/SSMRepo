<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <meta http-equiv="content-Type" content="text/html;charset=utf-8"/>
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
  
  <body >
  	<div class="mini-toolbar">
  		<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm()">保存</a>
  	</div>
	<fieldset style="width: 99%;" align="center">
		<legend>
			填写基本信息
		</legend>
   		<!--<div id= "userdiv">-->
   		<form name="userdiv" id="userdiv" action="AddOrderServlet" method="post" enctype="multipart/form-data">
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
   		<tr>
   			<td><label for="orderId$text">订单编号</label></td>
            <td style="width:25%;">
            <input id="orderHead"  name="orderHead" class="mini-combobox" style="width:30%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/OrderKinds.txt"   required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            <input id="orderId"  name="orderId" class="mini-textbox"  width="66%" required="true" readonly="readonly"/>
           	</td>
            <td><label for="customer$text">客    户</label></td>
            <td style="width:20%;"><input id="customer" name="customer" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" textName="companyName" required="true" allowInput="false"/>
   			<td><label for="connector$text">联系人</label></td>
            <td><input id="connector" name="connector" class="mini-textbox" width="100%" required="true" /></td>
        </tr>
       	<tr>
            <td><label for="connectorTel$text">联系电话</label></td>
            <td><input id="connectorTel"  name="connectorTel" class="mini-textbox"  width="100%" required="true" readonly="readonly"/></td>
   			<td><label for="deptUser$text">接收部门</label></td>
            <td><input id="deptUser"  name="deptUser" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/dept.txt"   required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            </td>
       
            <td><label for="orderDate$text">计划开始/结束日期</label></td>
            <td ><input id="orderDate" name ="orderDate" class="mini-datepicker" width="47%" dateFormat="yyyy-MM-dd" allowInput="true" />
            至
            <input id="endTime" name ="endTime" class="mini-datepicker" width="47%" dateFormat="yyyy-MM-dd" ondrawdate="onDrawDate" allowInput="true" /></td>
        </tr>
       	<tr>
            <!-- <td><label for="endTime$text">计划完成日期</label></td>
            <td><input id="endTime" name ="endTime" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd" ondrawdate="onDrawDate" allowInput="true" /></td> -->
            <td style="display:none" ><label for="orderStatus$text">状态信息</label></td>
            <td style="display:none" ><input id="orderStatus"  name="orderStatus" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/orderStatus.txt" value="5"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..." readonly="readonly"/>  
            </td>
           
   		</tr>
   		<!--一下为订单详细的信息！！！。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。分割线。。。。。。。。  -->
   		<tr>
   			<td><label for="productName$text">零件名称</label></td>
            <td><input id="productName"  name="productName" class="mini-textbox"  width="100%" required="true"/></td>
             <td><label for="productNum$text">零件数量</label></td>
            <td><input id="productNum"  name="productNum" class="mini-textbox"  width="100%" required="true"/></td>
              <td><label for="material$text">材料</label></td>
            <td><input id="material"  name="material" class="mini-combobox"  url= "LoadStuff" width="100%" allowInput="true"/></td>
       </tr>
       <tr style="display:none" >         
          	<td><label for="iswaixie$text">是否外协</label></td>
            <td><input id="iswaixie"  name="iswaixie" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/trueOrFalse.txt"  allowInput="false" showNullItem="true" nullItemText="请选择..." value="0"/>  
            </td>
            <td><label for="itemTypeId$text">零件种类</label></td>
            <td><input id="itemTypeId"  name="itemTypeId" class="mini-combobox" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
    			url="GetItemTypeServlet" value="L"  allowInput="false" showNullItem="true" nullItemText="请选择..."/>  
            </td> 
            <td><label for="drawingId$text">零件号</label></td>
        	<td><input id="drawingId"  name="drawingId" class="mini-textbox"  width="100%"  /></td>
       </tr>
       <!-- ..........................以下为设备的预约信息。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。。 -->
       <tr style="display:none" >
      <!-- <div class="mini.window" > -->
            <td><label for="equipCode$text">设备预约</label></td>
	    	<td><input id="equipCode" name="equipCode" class="mini-buttonedit" width="66%" onbuttonclick="onButtonEditMachine" required="false" allowInput="false"/>
       	
  			<a class="mini-button" iconCls="icon-search" width="30%" plain="false"  onclick="search()">保存并预约设备</a>
  		</td>
  		<!-- </div> -->
       		<!-- <td><label for="machineTime01$text">预约时间一</label></td>
            <td style="width:25%;">
            <input id="machineTime01" name ="machineTime01" class="mini-datepicker" width="66%" dateFormat="yyyy-MM-dd" allowInput="true" showTodayButton="true" required="true" onvaluechanged="onChange()" ondrawdate="onDrawDate" />
			<input id="machineTime0101"  name="machineTime0101" class="mini-combobox" style="width:30%;" textField="text" valueField="id" emptyText="请选择..."
    			url="data/machineTime.txt"   required="true" allowInput="false" onvaluechanged="onChange()" showNullItem="true" nullItemText="请选择..."/> 
       	    </td>
       	    <td><label for="machineTime02$text">预约时间二</label></td>
            <td style="width:25%;">
            <input id="machineTime02" name ="mahineTime02" class="mini-datepicker" width="66%" dateFormat="yyyy-MM-dd" allowInput="true" showTodayButton="true"  required="true" ondrawdate="onDrawDate"/>
          	<input id="machineTime0202"  name="machineTime0202" class="mini-combobox" style="width:30%;" textField="machineTime0202" valueField="machineTime0202" emptyText="请选择..."
    				url="GetOrderHeadServlet" value="NL-XS"  required="true" allowInput="false" showNullItem="true" nullItemText="请选择..."/>   
           </td> -->
         
         
         
          <!--   <td><label for="machineTime03$text">预约时间二</label></td>
            <td><input id="machineTime03" name ="mahineTime03" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd" allowInput="true" showTodayButton="true"   ondrawdate="onDrawDate"/></td> -->
       </tr>

      
     
   		
   		<input type="hidden" id="machineName" name = "machineName" value=""/>
   	</table>
   	</form>
   <!--</div>-->
   </fieldset>
   
   
   <script charset="UTF-8">
   		mini.parse();
   		var machineName;
   		function onDrawDate(e) {
   		   var date = e.date;
   		   var d = new Date();
   		   if (date.getTime() < (d.getTime()-86400000)) //getIME得到的是毫秒数，所以减去这么大的数字
   		   {
   		     e.allowSelect = false;
   		   }
   		 }
   		
   		
   		
   		
   		 function onChange() {
   			var x= mini.get("machineTime01").getValue();
   			var equipCode= mini.get("equipCode").getValue();
   			var x = new Date(x);  
   			var year = x.getFullYear();
   			var month=x.getMonth()+1;
 			var date = x.getDate();  			
 			var machineTime0101= mini.get("machineTime0101").getValue();
 			
   			/* x=x.getFullYear() + '-' + month+ '-' + x.getDate()
   			 */
   			
   			jQuery.ajax({
   				type: "POST",
   				url: "checkMachineTime?year="+year+"&month="+month+"&date="+date+"&machineTime0101="+machineTime0101+"&equipCode="+equipCode,
   				dataType: "json", 
   				cache: false,
   				enctype: 'multipart/form-data',
   		
   				processData: false,
   				contentType: false,
   				success: function (data) {
   					//mini.get("foNo").setValue(data);
   					var result=data.result;
   					if(result==0){
   						
   					}else{
   					alert("时间冲突,请重新选择时间!");	
   					}
   					
   					
   				}
   			});
   			
   		} 
   		
   			/* $(document).ready(function(){
   			  $("#machineTime01").blur(function(){
   			    alert("sihihi");
   			  }); */
   			
   		

   		function search(e) {
            var equipCode = $("input[name='equipCode']").val();
            var btnEdit=this;
            if(equipCode=="") {
                alert("请先选择设备…………");
            }else{
            	getForm();
            	
            	var orderId= mini.get("orderHead").getValue()+mini.get("orderId").getValue();
            	 
            	mini.open({
                    url: "machineManage/machineTimeGT.jsp",
                    title:machineName+"(订单"+orderId+"保存成功，请预定设备时间…………)",
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
                            window.location.href = window.location.href;
                        }
                    }
                });
            }
            
        }
   		
   		
   		
   		
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
                            //mini.get("connector").setValue(data.connector);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                            $("#machineName").attr("value",machineName);
                        }
                    }
                }
            });
        }
   		
   		  function getCurrentTime(){
   		      var now=new Date();
   		      var year = now.getFullYear();
		      var month = (((now.getMonth()+1) < 10) ? "0" : "") + (now.getMonth()+1);
		      var day = ((now.getDate() < 10) ? "0" : "") + now.getDate();
   		       mini.get("orderDate").setValue(year+"-"+month+"-"+day);
   		      }
   	     getCurrentTime();
   		
   		function getForm(){
   			//document.forms[0].action = "AddOrderServlet";
 		  	//document.forms[0].submit();
 		  	var formData = new mini.Form("#userdiv");
 		  	
 		  	var form = new mini.Form("#userdiv");
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
 		  		jQuery.ajax({
      				type: "POST",
      				url: "AddShiyanOrderServlet",
      				dataType: "json", 
      				cache: false,
      				enctype: 'multipart/form-data',
      				data: new FormData($('#userdiv')[0]),
      				processData: false,
    				contentType: false,
      				success: function (data) {
        				alert(data.result);
        				 window.location.href = window.location.href; 
        				//mini.get(div).setVisible(true)
      				}
    			});
    		}
   		}
		
		var now = new Date();
		var fullYear = now.getFullYear();
		var year = fullYear.toString().substring(2,4);
		var month = (((now.getMonth()+1) < 10) ? "0" : "") + (now.getMonth()+1);
		var day = ((now.getDate() < 10) ? "0" : "") + now.getDate();
//		var hour = ((now.getHours() < 10) ? "0" : "") + now.getHours();
//		var min = ((now.getMinutes() < 10) ? "0" : "") + now.getMinutes();
//		var sec = ((now.getSeconds() < 10) ? "0" : "") + now.getSeconds();
//		var millSec = (now.getMilliseconds()).toString().substring(0,1);
		var nowRight = year+month+day;
		
   		function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
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
                            mini.get("connector").setValue(data.connector);
                            mini.get("connectorTel").setValue(data.connectorTel);
    			            var params = {'companyId':data.companyId};
                            var url='CheckOrderIdCountServlet'; 
                            jQuery.post(url, params, function(text){
                            text=mini.decode(text);
                            total=text.total+1;
                            if(total>0&&total<10)
                            total="0"+total;
                            else if(total==100)
                            total="01";
 	   					    mini.get("orderId").setValue("-"+data.companyId+"-"+nowRight+total);
 	   				          });      
                        }
                    }

                }
            });
        }

		function doShow()   
	    { 
	         document.getElementById("span1").innerHTML="<input type=file id=upload size=40  name=upload label=上传文件   class=buttonface />";   
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

