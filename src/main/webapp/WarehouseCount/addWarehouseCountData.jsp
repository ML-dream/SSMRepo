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
   
    <title>初始回款信息维护</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body>
  	<div class="mini-toolbar">
  	<a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()" >刷新</a>
  	<span class="separator"></span>
  	<a class="mini-button" iconCls="icon-save" plain="false" onclick="getForm()" >保存</a>
  	</div>
    <form id="datamaintenance" method="post" action="">
    	<table  style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
    		<tr>
    			<td><label for="maintenanceDate$text">日期</label></td>
   	 			<td><input id="maintenanceDate" name="maintenanceDate" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd HH:mm:ss" 
    			format="yyyy-MM-dd" showClearButton="false" showTodayButton="fasle" showOkButton="false" required="true"/></td>
     			<td><label for="warehouseId">库房</label></td>
   				<td><input id="warehouseId" name="warehouseId" class="mini-buttonedit" width="100%" textName="warehouseName" onbuttonclick="onButtonEditWarehouse" 
  				allowInput="false" required="true" value="${warehouseId }" text="${warehouseName }"/></td>
    			<td><label for="itemId$text">物料</label></td>
    			<td><input id="itemId" name="itemId" class="mini-buttonedit" width="100%" required="true" textName="itemName" onbuttonclick="onButtonEdit"/></td>
    		</tr>
    		<tr>
    			<td><label for="spec$text">规格</label></td>
    			<td><input id="spec" name="spec" class="mini-textbox" width="100%"/></td>
    			<td><label for="beginCountPrice$text">初期结存金额</label></td>
    			<td><input id="beginCountPrice" name="beginCountPrice" class="mini-textbox" width="100%" required="true"/></td>
    			<td><label for="beginCountNum$text">初期结存数量</label></td>
    			<td><input id="beginCountNum" name="beginCountNum" class="mini-textbox" width="100%" required="true"/></td>
    		</tr>
    		<tr>
    			<td><label for="unit$text">单位</label></td>
    			<td><input id="unit" name="unit" class="mini-textbox" width="100%"/></td>
    			<td><label for="checkinNum$text">收入数量</label></td>
    			<td><input id="checkinNum" name="checkinNum" class="mini-textbox" width="100%" /></td>
    			<td><label for="checkoutNum$text">发出数量</label></td>
    			<td><input id="checkoutNum" name="checkoutNum" class="mini-textbox" width="100%" /></td>
    		</tr>
    	</table>
    </form>
    
    <script type="text/javascript">
    mini.parse();
    
    function getForm(){
    	var form=new mini.Form("datamaintenance");
    	form.validate();
    	if(form.isValid()==false){
    		return;
    	}else{
    		var data=form.getData();
    		data.maintenanceDate=mini.get("maintenanceDate").getFormValue();
    		var json=mini.encode(data);
    		$.ajax({
    			type:"post",
    			url:"addWarehouseCountServlet",
    			data:{submitData:json},
    			dataType:"json",
    			success:function(data){
    				alert(data.result);
        			if(data.status==1){
        					window.location.href=window.location.href;
        			}
    		
    			}
    	
    		});
    	
    	}
    }
    
    function refresh(){
			window.location.href=window.location.href;
		}
        
    function onButtonEditWarehouse(e){
   		var btnEdit = this;
            mini.open({
                url: "warehouseDefi/selectWarehouseWindow.jsp",
                title: "选择库房",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.warehouseid);
                            btnEdit.setText(data.warehousename);
                         
                        }
                    }
                }
            });
   
   }
   
   function onButtonEdit(e) {
            var btnEdit = this;
            var warehouseId=mini.get("warehouseId").getValue();
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Lingliao/selectWarehouseItemWindow.jsp?warehouseId="+warehouseId,
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
                            btnEdit.setValue(data.itemId);
                            btnEdit.setText(data.itemName);
                       		mini.get("spec").setValue(data.spec);
                       		mini.get("unit").setValue(data.unit);
                       		
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
   		       mini.get("maintenanceDate").setValue(year+"-"+month+"-"+day);
   		      }
   	     getCurrentTime();
    </script>
  </body>
</html>
