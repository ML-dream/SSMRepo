 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@page import="com.wl.forms.User"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>采购订货</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">-->
	
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/js.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/pagecard.css">
	<style type="text/css">
	<!--
	table {
	    table-layout:fixed;
	    word-break: break-all;
	} 
	-->
	</style>
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  <body>
  <div class="mini-toolbar">
  <a class="mini-button" iconCls="icon-print" plain="false"  onclick="print()">打印入库单</a>
<!--  <span class="separator"></span>-->
<!--  <a class="mini-button" iconCls="icon-save" plain="false" onClick="saveData()">保存</a>-->
  <span class="separator"></span>
  <a class="mini-button" iconCls="icon-goto" plain="false" onclick="nextForm()">下一单</a>
  <span class="separator"></span>
  	物料类型：
  	<input id="applyType" class="mini-combobox" style="width:100px;" url="GetItemTypeServlet" showNullItem="true" nullItemText="全部" onvaluechanged="findApply()" />
  	(注意修改申请单状态)
  <span class="separator"></span>
  	申请单类型：
  	<input id="applyState" class="mini-combobox" style="width:100px;" url="data/applyState.txt" showNullItem="false" nullItemText="全部" onvaluechanged="findApply()" />
  </div>
   
  <div id="grid1" class="mini-datagrid" style="width:100%;height:60%;" borderStyle="border:0px;" allowCellEdit="true" allowCellSelect="true" multiSelect="true" 
        editNextOnEnterKey="true"  editNextRowCell="true" selectOnLoad="true" onselectionchanged="onSelectionChanged"  oncellendedit="saveApply()"
        idField="id" showSummaryRow="false" allowAlternating="true" showPager="true" url="showPassExamineApplyServlet">
  	<div property="columns">
  		<div type="indexcolumn"></div>
<!--  		<div name="action" headerAlign="center" align="center" width="60" renderer="onOperatePower">操作</div>-->
		<div field="applyDate" headerAlign="center" align="center" width="80" dateFormat="yyyy-MM-dd">日期</div>
  		<div field="applySheetid" headerAlign="center" align="center" width="100">申请单单号</div>
  		
    	
    	<div type="comboboxcolumn" field="isPass" headerAlign="center" align="center" width="40" renderer="onGenderRenderer" headerStyle="color:red;" cellstyle="color:red;">状态
    		<input property="editor" class="mini-combobox" style="width:100%" url="data/applyState.txt"/></div>
    	<div field="orderId" headerAlign="center" align="center" width="80">订单号</div>
    	<div field="applicantName" headerAlign="center" align="center" width="80">申请人</div>
    	<div field="deptName" headerAlign="center" align="center" width="60">使用部门</div>
<!--    	<div field="examineName" headerAlign="center" align="center" width="60">审核人</div>-->
    	  	
  	</div>
  </div>
  
  <div id="grid2" class="mini-datagrid" style="width:100%;height:40%;" borderStyle="border:0;"  selectOnLoad="true" 
     multiSelect="true" idField="id" showSummaryRow="false" allowAlternating="true" showPager="true" onselectionchanged="onPoSelection"  
    url="ShowApplyDetailServlet">
      	<div property="columns">
      	<div type="indexcolumn"></div>
<!--      		<div field="applySheetid" headerAlign="center" align="center" width="100">单号</div>-->
    		<div field="itemId" headerAlign="center" align="center" width="100">货品编号</div>
    		<div field="itemName" headerAlign="center" align="center" width="100">货品名称</div>
    		<div field="spec" headerAlign="center" align="center" width="100">规格</div>
    		<div field="unit" headerAlign="center" align="center" width="100">单位</div>
    		<div field="itemTypeDesc" headerAlign="center" align="center" width="100">类型</div>
    		<div field="usage" headerAlign="center" align="center" width="100">用途</div>
    		<div field="poNum" headerAlign="center" align="center" width="100">申请采购数量</div>
    		<div field="alreadyNum" headerAlign="center" align="center" width="100">已采购数量</div>
    		<div field="memo" headerAlign="center" align="center" width="100">备注</div>
    
    	</div>
	</div>
  
  
   <fieldset align="center">
    <legend>采购信息</legend>
    	<div id="editForm1">
        <input class="mini-hidden" name="id"/>
   		<table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
 		<tr bgcolor=#EBEFF5>
 		<td ><label for="postart_date$text">开单日期</label></td>
  		<td><input id="postart_date" name="postart_date" class="mini-datepicker" required="true" dateFormat="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd" 
 		 showTodayButton="false" showClearButton="false" allowInput="false"  width="100%" value="${createTime }"/></td>
  		<td><label for="po_sheetid$text">单号</label></td>
  		<td><input id="po_sheetid" name="po_sheetid" class="mini-textbox" required="true" enabled="false"  width="100%" value="${po_sheetid.sheetid }"></td>
  		<td><label for="customerid$text">供应商</label></td>
  		<td><input id="customerid" name="customerid" class="mini-buttonedit" width="100%" onbuttonclick="onSupplierButtonEdit" textName="customername" allowInput="false" required="true"/></td>
  		
 
  		</tr>
  		<tr bgcolor=#EBEFF5>
  		
  		<td><label for="connector$text">联系人</label></td>
  		<td><input id="connector" name="connector" class="mini-textbox" width="100%"/></td>
  		<td><label for="connectortel$text">联系电话</label></td>
  		<td><input id="connectortel"  name="connectortel" class="mini-textbox" width="100%" /></td>
  		<td><label for="salesmanId$text">业务员</label></td>
  		<td><input id="salesmanId"  name="salesmanId" class="mini-buttonedit" textName="salesmanName" required="false" width="100%" text="<%=((User)session.getAttribute("user")).getStaffName()%>"
  			value="<%=((User)session.getAttribute("user")).getStaffCode()%>" allowInput="false" onbuttonclick="onButtonEditEmployee"/></td>
   		</tr>
   		<tr bgcolor=#EBEFF5>
   		
  		<td><label for="drawerId$text">开单人</label></td>
  		<td><input id="drawerId"  name="drawerId" class="mini-buttonedit" textName="drawerName" required="false" width="100%"  text="<%=((User)session.getAttribute("user")).getStaffName()%>"
  			value="<%=((User)session.getAttribute("user")).getStaffCode()%>" allowInput="false" onbuttonclick="onButtonEditEmployee"/></td>
   		<td><label for="end_date$text">交货日期</label></td>
   		<td><input id="end_date" name="end_date" class="mini-datepicker" required="true" width="100%" dateFormat="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd" 
   		showTodayButton="false" showClearButton="false" allowInput="false"></td>
  		</tr>
  		</table>
  		<input id="seq" name="seq" class="mini-hidden" enabled="false" value="${po_sheetid.seq }"/>
  		<input id="id" name="id" class="mini-hidden" enabled="false"value="${po_sheetid.id }"/>
  		<input id="count" name="count" class="mini-hidden" value="0">
  		</div>
  		
  		<div id="editForm2">
   		<table  style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
   		
		<tr bgcolor=#EBEFF5>
  		<td><label>货品编号</label></td>
  		<td><input id="itemId" name="itemId" class="mini-textbox" width="100%"/></td>
  		<td><label>货品名称</label></td>
  		<td><input id="itemName" name="itemName" class="mini-textbox" width="100%"/></td>
  		<td><label>规格</label></td>
  		<td><input id="spec" name="spec" class="mini-textbox" width="100%"/></td>
  		
  		</tr>
   		<tr bgcolor=#EBEFF5>
   		<td><label>单位</label></td>
  		<td><input id="unit" name="unit" class="mini-textbox" width="100%"/></td>
   		<td><label>类型</label></td>
   		<td><input id="itemType" name="itemType" class="mini-combobox" textField="text" valueField="id" width="100%" emptyText="请选择..." 
   		nullItemText="请选择..." shouwNullItem="true" allowInput="true" url="GetItemTypeServlet"/></td>
   		<td><label>用途</label></td>
   		<td><input id="usage" name="usage" class="mini-textbox" width="100%"/></td>
   		</tr>
   		<tr bgcolor=#EBEFF5>
   		<td><label>数量</label></td>
   		<td><input id="poNum" name="poNum" class="mini-textbox" width="100%"/></td>
   		<td><label>单价</label></td>
   		<td><input id="unitPrice" name="unitPrice" class="mini-textbox" width="100%" required="true"/></td>
   		<td><label>总价</label></td>
   		<td><input id="price" name="price" class="mini-textbox" width="100%"/></td>
   		</tr>
   		<tr bgcolor=#EBEFF5>
   		<td><label for="order_id$text">订单号</label></td>
  		<td colspan="5"><input id="order_id"  name="order_id" class="mini-textbox"  width="100%" /></td>
<!--   		<td><label>备注</label></td>-->
<!--   		<td colspan="5"><input id="memo" name="memo" class="mini-textbox" width="100%" emptyText="请输入备注"/></td>-->
   		
   		</tr>
   		<tr><td colspan="6" style="text-align:right;padding-top:5px;padding-right:20px;">
   		<a class="mini-button" href="javascript:ondSee()">查看</a>
   		<a class="mini-button" href="javascript:update(1)">保存</a>
   		<a class="mini-button" href="javascript:cancel()">取消</a>
   		
   		</td></tr>
   		</table>
   		</div>
   		<input id="applysheetid" name="applysheetid" class="mini-hidden" enabled="false" width="200"/>
   </fieldset>


  	
<script type="text/javascript">
  	mini.parse();
  	var grid=mini.get("grid1");
  	var grid1=mini.get("grid2");
  	grid.load();
	// grid1.load();
	
	function saveApply(){
	
		var data = grid.getChanges();
  		 var json = mini.encode(data);
  		//alert (json);
  		$.ajax({
			type:"post",
			url: "UpdateApplyState",
			data:{"data" : json},
			cache: false,
			success: function (text){
			},
			error: function (text){
				//alert ("保存失败 ");
			}
	});
	}
	
	function findApply(){
		var applyType = mini.get("applyType").getValue();
		var applyState = mini.get("applyState").getValue();
		
		grid.load({applyType:applyType , applyState:applyState});
		//grid1.load();
	}
	
  	  function onSelectionChanged(e) {
        
            var grid2 = e.sender;
            var record = grid2.getSelected();
            var applySheetid=record.applySheetid;
           
            if (applySheetid) {
             	mini.get("applysheetid").setValue(applySheetid);
                grid1.load({ applySheetid:applySheetid });
                
            }
            
        }
  	
  
   function nextForm(){
    window.location.href=window.location.href;
    }
  
  function onPoSelection(e){
 	 var grid3 = e.sender;
     var record = grid3.getSelected();
     var form=new mini.Form("editForm2");
     var applySheetid=mini.get("applysheetid").getValue();
     $.ajax({
     	type:"post",
     	url:"getPoFormServlet?applySheetid="+applySheetid+"&itemId="+record.itemId,
     	dataType:"json",
     	success:function(text){
     		var data=mini.decode(text);
     		form.setData(data);
    		mini.get("order_id").setValue(data.orderId);
     		
     	}
     });
     
  }
  
  	function update(status){
  		var form1=new mini.Form("editForm1");
  		var form2=new mini.Form("editForm2");
  		form1.validate();
  		form2.validate();
  		if(form1.isValid()==false||form2.isValid()==false){
  			return;
  			
  		}else{
  			var data=form2.getData();
  			var posheetid=mini.get("po_sheetid").getValue();
  			var json=mini.encode(data);
  			var applySheetid=mini.get("applysheetid").getValue(); 
  			$.ajax({
  				type:"post",
  				url:"PoDetailServlet?posheetid="+posheetid+"&applySheetid="+applySheetid+"&status="+status,
  				data:{submitData:json},
  				dataType:"json",
  				success:function(data){
  					PoServlet(form1,status);
  					alert(data.result);
  					grid1.reload();
  					mini.get("customerid").disable();
  				}
  			});
  		}
  
  
  	}
  
  	function PoServlet(form,status){
  		var count=mini.get("count").getValue();
  		if(count==0){
  			var data=form.getData();
  			data.postart_date=mini.get("postart_date").getFormValue();
  			data.end_date=mini.get("end_date").getFormValue();
  			
  			data.salesmanId=mini.get("salesmanId").getValue();
  			data.drawerId=mini.get("drawerId").getValue();
  			data.customerid =mini.get("customerid").getValue();
  			
  			var json=mini.encode(data);
  			
  			$.ajax({
  				type:"post",
  				url:"PoServlet?status="+status+"&count="+count,
  				data:{submitData:json},
  				dataType:"json",
  				success:function(data){
  					mini.get("count").setValue(data.count);
  				}
  			});
  		}
  	
  	
  	}
  	
  	function saveData(){
  		
        if(grid.isChanged()){	//false表明没有修改
        	getDataGrid();
        }else{
        	mini.alert("数据没有修改！");
        	return;
        }
  	}
  	
  	 function getDataGrid(){
            var data="{";
            var gridData = grid.getData();
            var len = parseInt(gridData.length);
     
			data+="\"data\":\"";
			for(i=0;i<len;i++){
			
				data+=gridData[i].applySheetid+"#"+gridData[i].isPass+",";
				
			}
			data = data.substring(0,data.length-1);
			data+="\"}";
			
			params = JSON.parse(data);
			url = "PoStatusServlet";
			jQuery.post(url, params, function(data){
   	   			alert(data.result);
   	   			window.location.href=window.location.href;
   	   		},'json');
        }
  	
  	
   function cancel(){
   	window.location.href=window.location.href;
   }
   
  
	function ondSee(){
			var poSheetid = mini.get("po_sheetid").getValue();
			window.open("seePoDetail?po_sheetid=" + poSheetid,
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
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
                            mini.get("connector").setValue(data.connector);
                            mini.get("connectortel").setValue(data.connectorTel); 
                         //  mini.get("telephone").setValue(data.telephone);
                       
                       
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
        
      
      
      var Genders = [{id: "3", text: "订货中"},{id: "5", text: "订货完成"}];
    function onGenderRenderer(e){
    	for(var i=0,l=Genders.length;i<l;i++){
    		var g = Genders[i];;
    		if(g.id==e.value)return g.text;
    	
    	}
    	return "";
    }
  </script>
  </body>
</html>
