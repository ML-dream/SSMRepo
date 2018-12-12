<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    <!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>初始付款信息维护</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body style="height:99%;">
  	<div class="mini-toolbar">
  	<a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()" >刷新</a>
  	<span class="separator"></span>
  	<a class="mini-button" iconCls="icon-save" plain="false" onclick="getForm()" >保存</a>
  	<span class="separator"></span>
  	<a class="mini-button" iconCls="icon-save" plain="false" onclick="editReport()" >修改记录</a>
  	</div>
    <form id="datamaintenance" method="post">
    <table>
    <tr>
    <td><label for="maintenanceDate$text">日期</label></td>
    <td><input id="maintenanceDate" name="maintenanceDate" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd HH:mm:ss" 
    format="yyyy-MM-dd" showClearButton="false" showTodayButton="fasle" showOkButton="false" required="true"/></td>
    <td><label for="companyId$text">供应商</label></td>
    <td><input id="companyId" name="companyId" class="mini-buttonedit" width="100%" onbuttonclick="onSupplierButtonEdit" textName="companyName" required="true" 
                allowInput="false"/></td>
    <td><label for="endPayment$text">本期末应付款</label></td>
    <td><input id="endPayment" name="endPayment" class="mini-textbox" width="100%" required="true"/></td>
    </tr>
    </table>
    </form>
    
    <br/>
    <div id="grid" class="mini-datagrid" style="width:100%;height:85%;"
         borderStyle="border:0;" multiSelect="true" idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="showAddSupplierDataMaintenanceServlet" >
    	<div property="columns">
            <div type="indexcolumn"></div>
            <div name="action" width="60" headerAlign="center" align="center" renderer="onOperatePower"
    		cellStyle="padding:0;">操作</div>
    		<div field="companyId" align="center" headerAlign="center">供应商编号</div>
    		<div field="companyName" align="center" headerAlign="center">供应商名称</div>
    		<div field="maintenanceDate" align="center" headerAlign="center" dateFormat="yyyy-MM-dd">初始维护日期</div>
    		<div field="endPayment" align="center" headerAlign="center">初始维护金额</div>
    	</div>
    </div>
    
    <div id="editWindow" class="mini-window" title="Window" style="width:850px;" showModal="true" allowResize="true" allowDrag="true">
    <div id="editform" class="form" >
    	<input class="mini-hidden" name="id"/>
    	<table style="text-align: center;border-collapse:collapse;" border="gray 1px solid;"  width="99%" height="99%;">
    		 <tr bgcolor=#EBEFF5>
    			<td width="12%"><label for="maintenanceDate$text">日期</label></td>
    			<td><input id="initialmaintenanceDate" name="maintenanceDate" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd HH:mm:ss" 
    				format="yyyy-MM-dd" showClearButton="false" showTodayButton="fasle" showOkButton="false" required="true" enabled="false"/></td>
    			<td width="12%"><label for="companyId$text">供应商</label></td>
    			<td><input id="companyId" name="companyId" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" textName="companyName" enabled="false" required="true" 
                	allowInput="false"/></td>
    			<td width="12%"><label for="endPayment$text">初期应回款</label></td>
    			<td><input id="endPayment" name="endPayment" class="mini-textbox" width="100%" required="true"/></td>
    		</tr>
    		<tr bgcolor=#EBEFF5>
    		<td><label for="reason$text">修改原因</label></td>
    		<td colspan="5"><input id="reason" name="reason" class="mini-textarea" width="100%" required="true"></td>
    		
    		</tr>
    		<tr>
      			<td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="8">
                    <a id="update" class="mini-button" href="javascript:updateRow()">保存</a> 
                    <a id="cancle" class="mini-button" href="javascript:cancelRow()">取消</a>
      			</td>                
      	    </tr>
    	</table>
    	<input id="initialEndPayment" name="initialEndPayment" class="mini-hidden" width="100%" required="true"/>
    	</div>
    </div>
    
    <script type="text/javascript">
    mini.parse();
    var grid=mini.get("grid");
    grid.load();
     var editWindow = mini.get("editWindow");
     
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
    			url:"addSupplierPayDataMaintenanceServlet",
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
    
    
    function onOperatePower(e){
    var str="";
    var grid = e.sender;
    var record = e.record;
    var uid = record._uid;
    
    str+="<span>";
    str+="<a style='margin-right:2px;' title='修改' href=javascript:ondEdit(\'"+uid+"\')><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
    str+="</span>";
    return str;
   
    }
    
     function ondEdit(row_uid){
    	var row=grid.getRowByUID(row_uid);
    	if(row){
    		
    		editWindow.show();
    		var form = new mini.Form("editform");
            form.clear();
     
            $.ajax({
			     url: "getAddSupplierDataMaintenanceFormServlet?companyId="+row.companyId,
                 success: function (data) {
                        var formData = mini.decode(data);
                        form.setData(formData);
                        mini.get("initialEndPayment").setValue(formData.endPayment);
                        
                    }
            });
    		
    	}
    	
    }
    
    function updateRow(){
    	var editform=new mini.Form("editform");
    	editform.validate();
    	if(editform.isValid()==false){
    		return;
    	}else{
    		var data=editform.getData();
    		data.initialmaintenanceDate=mini.get("initialmaintenanceDate").getFormValue();
    		var initialEndPayment=mini.get("initialEndPayment").getValue();
    		var json=mini.encode(data);
    		$.ajax({
    			type:"post",
    			url:"editSupplierDataMaintenanceServlet?initialEndPayment="+initialEndPayment,
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
     function cancelRow() {
        editWindow.hide();
    }
    
    function editReport(){
    	 window.location="PO/seeEditSupplierDataMaintenanceServlet.jsp";
    }
    
    function refresh(){
			window.location.href=window.location.href;
		}
    
  function onSupplierButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Supplier/selectSupplierWindow.jsp",
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
                          
                         //  mini.get("telephone").setValue(data.telephone);
                       
                       
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
