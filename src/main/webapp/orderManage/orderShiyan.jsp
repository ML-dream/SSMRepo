<%@ page language="java" import="java.util.*,com.wl.forms.*" pageEncoding="utf-8"%>
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
 
    
    
    
    
    img 
        {
            border:none;
        }

        ul, ul li
        {
            list-style-type: none;
            margin: 0;
            padding: 0;
        }

        ul li.first
        {
            border-top: 2px solid #DFDFDF;
        }
       
        ul p
        {
            float: left;
            margin: 10px;
            width: 30%;  //这个 表示input所在的div的大小
            
        }

        ul h3
        {
            float: left;
            font-size: 15px;
            font-weight: bold;
            margin: 10px 0 0 0;
            width: 35%;   /* 这个是控制左边相距编边界的距离 */
            margin-left:1%;
            color:#FF0001;
        }

        ul li
        {       	 
            padding: 0.1% 0;
            width:100%;
            overflow:hidden;
        }

  

        
   .test01 {
	position: absolute;
	/* height: 50%; */
	width: 38%;
	left:31%;
	top:10%;
	border-radius: 15px;
		border-style: groove; 
	   text-align:center;
}

       </style>
    
  </head>
  
  <body >
  
  
	<fieldset class="test01"  align="center" >
		<legend>
			填写基本信息
		</legend>

   		
       
       
    
    
     <div class="box" id="box3"  >
<ul>

<li>
<h3><label>客户</label></h3>
<p><input id="companyName" name="companyName" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" textName="companyName" required="true" allowInput="false"/></p>
</li>

<li>
<h3>联系人</h3>
<p><input id="connector" name="connector" class="mini-textbox" width="100%" required="true" /></p>
</li>

<li>
<h3>订单编号</h3>
<p><input id="orderId"  name="orderId" class="mini-textbox"  width="99%" required="true" readonly="readonly"/></p>
</li>
<li>
<h3>联系电话</h3>
<p><input id="connectorTel"  name="connectorTel" class="mini-textbox"  width="100%" required="true" /></p>
</li>
<li>
<h3>订单名称/描述</h3>
<p><input id="orderName"  name="orderName" class="mini-textbox"  width="100%" required="true"/></p>
</li>
<li>
<h3>加工数量</h3>
<p><input id="productNum"  name="productNum" class="mini-textbox"  width="100%" value="1" required="true"/></p>
</li>
<li>
<h3>材料</h3>
<p><input id="material"  name="material" class="mini-combobox"  url= "LoadStuff" width="100%" allowInput="true"/></p>
</li>

</ul>
</div>

	<div >
  		<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm()">保存</a>
  	</div>
       
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
/*    		
   		  function getCurrentTime(){
   		      var now=new Date();
   		      var year = now.getFullYear();
		      var month = (((now.getMonth()+1) < 10) ? "0" : "") + (now.getMonth()+1);
		      var day = ((now.getDate() < 10) ? "0" : "") + now.getDate();
   		       mini.get("orderDate").setValue(year+"-"+month+"-"+day);
   		      }
   	     getCurrentTime(); */
   		
   	     
   		function getForm(){

   	    	var companyId=mini.get("companyName").getValue();
   	    	var connector=mini.get("connector").getValue();
   	    	var connectorTel=mini.get("connectorTel").getValue();
   	    	var orderId=mini.get("orderId").getValue();
   	    	var orderName=mini.get("orderName").getValue();
   	    	var productNum=mini.get("productNum").getValue();
   	    	var material=mini.get("material").getValue();
 		  		jQuery.ajax({
      				type: "POST",
      				url: "AddShiYanOrder.action?customer="+companyId+"&connector="+connector+"&connectorTel="+connectorTel+"&orderId="+orderId+"&orderName="+orderName+"&productNum="+productNum+"&material="+material,
      				dataType: "json", 
      				cache: false,
      		
      				success: function (data) {
        				alert(data.result);
        				
        				 window.location.href = window.location.href; 
        				//mini.get(div).setVisible(true)
      				}
    			});
    		
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
 	   					    mini.get("orderId").setValue(data.companyId+"-"+nowRight+total);// mini.get("orderId").setValue("-"+data.companyId+"-"+nowRight+total);
 	   				          });      
                        }
                    }

                }
            });
        }

   		
   		load();
   		function load () {
   			var staffCode="<%=((User)request.getSession().getAttribute("user")).getStaffCode()%>";
		   			
   				jQuery.ajax({
		  				type: "POST",
		  				url: "LoadDefaultShiYanOrder.action?staffCode="+staffCode,
		  				dataType: "json", 
		  				cache: false,
		  				success: function (data) {
		  					  mini.get("companyName").setValue(data.companyId);
		  					  mini.get("companyName").setText(data.companyName);
		                     mini.get("connector").setValue(data.connector);
		                     mini.get("connectorTel").setValue(data.connectorTel);
		 		          	  var params = {'companyId':data.companyId};
		                     var url='CheckOrderIdCountServlet'; 
		                     jQuery.post(url, params, function(text){
		                     text=mini.decode(text);
		                     total=text.total+1;
		          /*            if(total>0&&total<10)
		                     total="0"+total;
		                     else if(total==100)
		                     total="01"; */
		 					   mini.get("orderId").setValue(data.companyId+"-"+nowRight+total);
		 				          });
		  				},
		  				error:function(data){
		  					alert("该订单号已经存在，请刷新界面");
		  				}
		  				
		  				
					});
                         
                }
   		
		function doShow()   
	    { 
	         document.getElementById("span1").innerHTML="<input type=file id=upload size=40  name=upload label=上传文件   class=buttonface />";   
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

