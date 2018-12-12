 <%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>采购申请</title>
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
<!--    <table>-->
<!--    <tr style="height:10px;"></tr>-->
<!--    <tr style="height:20px;">-->
<!--    <td>订单号：</td>-->
<!--    <td><input id="orderId" name="orderId"  class="mini-buttonedit" width="100%" onbuttonclick="onOrderButtonEdit"-->
<!--          textName="orderId" required="false" allowInput="false"/></td>-->
<!--    <td><input value="查询" type="button" style= "width:70px;" onclick="search()" /></td>-->
<!--    </tr>-->
<!--    <tr style= "height:15px;"></tr>-->
<!--    </table>-->
	<div class="mini-toolbar">
	<a class="mini-button" iconCls="" plain="false"  onclick="seePoApply()">查看</a>
	<span class="separator"></span>
	<a class="mini-button" iconCls="" plain="false"  onclick="addPoApplyDetail('0')">采购</a>
	<span class="separator"></span>
	<a class="mini-button" iconCls="" plain="false"  onclick="printItemSheet()">打印物料单</a>
	</div>
	<h1>采购申请单</h1><br/>
	<br/>
    <form id="applysheet" name="applysheet" action="PoServlet" method="post">
    <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
        <tr bgcolor=#EBEFF5>
  			<td width="10%"><label for="applyDate$text">开单日期</label></td>
  			<td><input id="applyDate" name="applyDate" class="mini-datepicker" required="true" dateFormat="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd" 
  			showTodayButton="false" showClearButton="false" allowInput="false" width="100%"/></td>
  			<td width="10%"><label for="applySheetid$text">单号</label></td>
  			<td><input id="applySheetid" name="applySheetid" class="mini-textbox" required="true" enabled="false"  width="100%" value="${applysheetid.sheetid }"></td>
  			<td width="10%"><label for="applicantId$text">申请人</label></td>
  			<td><input id="applicantId" name="applicantId" class="mini-buttonedit" width="100%" textName="applicantName" enabled="false" onbuttonclick="onButtonEditEmployee" 
  			allowInput="false" required="true" value="${StaffCode }" text="${StaffName }"></td>
  
  		</tr>
  		<tr bgcolor=#EBEFF5>
  			<td><label for="deptId$text">使用部门</label></td>
  			<td><input id="deptId" name="deptId" class="mini-buttonedit" width="100%" textName="deptname" onbuttonclick="onButtonEditDept" 
  			allowInput="false" required="true" showClose="true" oncloseclick="onDeptCloseClick('deptId')"/></td>
  			
  			<td><label for="warehouse_id$text">库&nbsp;&nbsp;&nbsp;&nbsp;房</label></td>
   			<td><input id="warehouse_id" name="warehouse_id" class="mini-buttonedit" width="100%"  textName="warehouse_name" onbuttonclick="onButtonEditWarehouse" 
  				allowInput="false" required="true" value="" text=""  showClose="true" oncloseclick="onWarehouseCloseClick('warehouse_id')"/></td>
  			
  			<td><label for="order_id$text">订单号</label></td>
  			<td><input id="order_id" name="order_id" class="mini-buttonedit" width="100%" onbuttonclick="onOrderButtonEdit"
          	textName="orderId" required="false" allowInput="false" showClose="true" oncloseclick="onCloseClick('order_id')"
          	/></td>
 		</tr>
  	</table>
  	<input id="id" name="id" class="mini-hidden" value="${applysheetid.id }"/>
  	<input id="seq" name="seq" class="mini-hidden" value="${applysheetid.seq }"/>
  	 <input id="isPass" name="isPass" class="mini-hidden" value="3"/>
	</form>
    	<table>
    	<tr>
    	<td>
    	<div id="grid1" class="mini-datagrid" style="width:550px;height:320px;" borderStyle="border:1" multiSelect="false" 
    	idField="id" showSummaryRow="true" showFilterRow="false" allowAlternating="true" showPager="true" url="ShowProductMatirialServlet"
    	 onselectionchanged="onSelectionChanged" selectOnLoad="true">
    	<div property="columns">
    		<div type="checkcolumn"></div>
    	<div type="indexcolumn">序号</div>
<!--    <div field="productId" headerAlign="center" align="center">产品号</div>-->
    	<div field="productId" headerAlign="center" align="center">图号</div>
    	<div field="productName" headerAlign="center" align="center">产品名称</div>
<!--    	<div field="issueNum" headerAlign="center" align="center">版本号</div>-->
<!--		<div field="spec" headerAlign="center" align="center">规格</div>-->
		<div field="productNum" headerAlign="center" align="center">产品数量</div>
		<div field="matirial" headerAlign="center" align="center">材料</div>
		<div field="roughSize" headerAlign="center" align="center">毛坯尺寸</div>    
   		</div>
    	</div>
   		</td>
   		<td width="20"></td>
   		<td>
   		<div id="grid2" class="mini-datagrid" style="width:500px;height:320px;" borderStyle="border:1" multiSelect="true" 
    	idField="id" showSummaryRow="true" showFilterRow="false" allowAlternating="true" showPager="true" 
    	url="SelectProductMatirialServlet" >
    	<div property="columns">
    	<div type="indexcolumn">序号</div>
    	<div name="action" width="30" headerAlign="center" align="center" renderer="onOperatePower"
    		cellStyle="padding:0;">操作</div>
    	<div field="itemId" headerAlign="center" align="center">物料编号</div>
    	<div field="itemName" headerAlign="center" align="center">物料名称</div>
    	<div field="spec" headerAlign="center" align="center">规格</div>
    	<div field="stockNum" headerAlign="center" align="center">库存余量</div>
    	</div>
    </div>
    </td>
    </tr>
   </table>
   
   <input id="count" name="count" class="mini-hidden" value=0>
   
   <div id="editWindow" class="mini-window" title="原料采购" style="width:850px;height:200px;"
    showModal="true" allowResize="true" allowDrag="true">
    <div id="editform" class="form" >
      <input class="mini-hidden" name="id"/>
      <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;" width="99%">
      <tr bgcolor="#D9E7F8">
      <td width="8%"><label for="itemId$text">原料编号</label></td>
      <td><input id="itemId" name="itemId" class="mini-textbox" width="100%" required="true" enabled="false"/></td>
      <td width="8%"><label for="itemName$text">原料名称</label></td>
      <td><input id="itemName" name="itemName" class="mini-buttonedit" textName="applicantName" width="100%" required="true"
       onbuttonclick="onButtonEdit" allowInput="true" showClose="true" oncloseclick="onItemCloseClick('itemName')"/></td>
      <td width="8%"><label for="spec$text">规格</label></td>
      <td><input id="spec" name="spec" class="mini-textbox" width="100%" required="true"/></td>
      <td width="8%"><label for="itemType$text">类型</label></td>
      <td><input id="itemType" name="itemType" class="mini-combobox" textField="text" required="true" valueField="id" width="100%"
       emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="true" url="GetItemTypeServlet"/></td>
      </tr>
      <tr bgcolor="#D9E7F8">
      <td><label for="poNum$text">采购数量</label></td>
      <td><input id="poNum" name="poNum" class="mini-textbox" required="true" width="100%"/></td>
      <td><label for="unit$text">单位</label></td>
      <td><input id="unit" name="unit" class="mini-textbox" width="100%"/></td>
      <td><label for="usage$text">用途</label></td>
      <td><input id="usage" name="usage" class="mini-textbox" width="100%"/></td>
      <td><label for="memo$text">备注</label></td>
      <td><input id="memo" name="memo" class="mini-textbox" width="100%"/></td>
      </tr>
      
      <tr>
      <td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="8">
                    <a id="update" class="mini-button" href="javascript:updateRow()">保存</a> 
                    <a id="cancle" class="mini-button" href="javascript:cancelRow()">取消</a>
      </td>                
      </tr>
   		
      </table>
      <input id="addApply" name="addApply" class="mini-hidden"/>
      </div>
      </div>
   
   
    <script type="text/javascript">
    mini.parse();
    var grid1=mini.get("grid1");
    var grid2=mini.get("grid2");
 	var editWindow=mini.get("editWindow");
 	
 	
 	function printItemSheet(){
 		var orderId=mini.get("order_id").getValue();
 		window.open("PrintItemServlet?orderId="+orderId,
 			"editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes")
 	}
 	
 	
 	
    function onSelectionChanged(e) {
        	//var grid2=mini.get("grid2");
            var grid = e.sender;
            var record = grid.getSelected();
            if (record) {
                grid2.load({ matirial:record.matirial});
            }
        }
   
   function seePoApply(){
   	var applySheetid=mini.get("applySheetid").getValue();
   	window.open("PrintItemServlet?applySheetid="+applySheetid,
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
   	
   
   }
   
   function onOperatePower(e){
   		var grid = e.sender;
		var record = e.record;
        var uid = record._uid;
    	var str="";
    	str+="<span>";
    	str+="<a style='margin-right:2px;' title='采购' href=javascript:ondPurchase(\'"+uid+"\')><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
    	str+="</span>";
    	return str;
    }
    
   
   function ondPurchase(uid){
   		var row = grid2.getRowByUID(uid);
   		if (row) {

        	editWindow.show();
            var form = new mini.Form("editform");
            form.clear();
            $.ajax({
            		
                    url: "getApplyFormServlet?itemId="+row.itemId,
                    success: function (data) {
                        var applydata = mini.decode(data);
                        form.setData(applydata);
                        mini.get("itemName").setText(applydata.itemName);
                        
                        
                    }
            });
            
        }
        
   	}	
   	
   	function addPoApplyDetail(addApply){
   	//表头信息不完善时，不弹窗
   		var applyform=new mini.Form("applysheet");
		applyform.validate();
		if(applyform.isValid()==false){
			alert("请先完善表头信息");
		  	return;
		 }
   		
   		editWindow.show();
   		var form = new mini.Form("editform");
   		form.clear();
   		mini.get("addApply").setValue(addApply);
   		var row = null;
   		row=grid1.getSelecteds ( );
   		{	if(row[0]!=null){
   				var temp = row[0];
	   			mini.get("itemName").setValue(row[0].matirial);
	   			mini.get("itemName").setText(row[0].matirial);
	   			mini.get("spec").setValue(row[0].roughSize);
	   			mini.get("usage").setValue(row[0].productName);
   			}
   		}
   	}
   	
   	
   	function updateRow() {
   		mini.get("update").disable();
   		var applyform=new mini.Form("applysheet");
  		var editform=new mini.Form("editform");
  		var applySheetid=mini.get("applySheetid").getValue();
  		var warehouseId=mini.get("warehouse_id").getValue();
  		applyform.validate();
  		editform.validate();
  		if(applyform.isValid()==false||editform.isValid()==false){
  			return;
  			
  		}else{
  			var count=mini.get("count").getValue();
  			var editData=editform.getData();
  			var editjson=mini.encode(editData);
  			
  			if(count==0){
  				var applyData=applyform.getData();
  				applyData.applyDate=mini.get("applyDate").getFormValue();
  				var json=mini.encode(applyData);
  				$.ajax({
  					type:"post",
  					url:"PoApplyServlet",
  					data:{submitData:json},
  					dataType:"json",
  					success:function(data){
  						if(data.status==0){
  							alert(data.result);
  						}
  					//	alert(data.count);
  						mini.get("count").setValue(data.count);
  						mini.get("order_id").setEnabled(false);
  					}
  			
  			
  				});
  			
  			}
  			$.ajax({
  					type:"post",
  					url:"PoApplyDetailServlet?applySheetid="+applySheetid+"&warehouseId="+warehouseId,
  					data:{submitData:editjson},
  					dataType:"json",
  					success:function(data){
  						alert(data.result);
  						if(data.status==1){
  						
  							editWindow.hide();
  						}
  					}
  			});
  			
  			mini.get("update").disable();
  		}
   	
   	}
   	
   	function cancelRow() {
        editWindow.hide();
    }
   	
   	oncloseclick="onCloseClick('customer')"
   	
   function onCloseClick (para){
	    	mini.get(para).setValue("");
	    	mini.get(para).setText("");
	    	grid1.load({orderId:"yyyyyy"});
	    	grid2.load({ matirial:"####"});
	    }
   
   function onDeptCloseClick (para){
	    	mini.get(para).setValue("");
	    	mini.get(para).setText("");
	    }
	    
	function onWarehouseCloseClick (para){
	    	mini.get(para).setValue("");
	    	mini.get(para).setText("");
	    
	    }
	
	function onItemCloseClick (para){
	    	mini.get(para).setValue("");
	    	mini.get(para).setText("");
	    	mini.get("itemId").setValue("");
	    	mini.get("spec").setValue("");
	    	mini.get("spec").enable();
	    }
   
   
   function onOrderButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Checkout/selectOrderIdWindow.jsp",
                title: "订单号列表",
                width: 850,
                height: 380,
                ondestroy: function (action) {
                    //if (action == "close") return false;
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                            btnEdit.setValue(data.orderId);
                            btnEdit.setText(data.orderId);
                            var orderId=mini.get("order_id").getValue();
   							grid1.load({orderId:orderId});
                        }
                    }

                }
                
            });
      	 
        } 
        
        function onButtonEditDept(e){
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
            var id=btnEdit.id;
            mini.open({
                
                url: "Lingliao/selectItemWindow.jsp",
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
                            btnEdit.setValue(data.itemName);
                            btnEdit.setText(data.itemName);
                            mini.get("itemId").setValue(data.itemId);
                            mini.get("spec").setValue(data.spec);
                            btnEdit.allowInput=false;
                            mini.get("spec").disable();
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
   		       mini.get("applyDate").setValue(year+"-"+month+"-"+day);
   		      }
   	     getCurrentTime();
    </script>
  </body>
</html>
