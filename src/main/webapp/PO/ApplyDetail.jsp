<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>采购申请详细信息</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
  
  <body style="height:99%;">
   <div class="mini-toolbar">
  		<a class="mini-button" iconCls="icon-print" plain="false"  onclick="print()">打印</a>
	    <span class="separator"></span>
  		<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm()">保存</a>
	    <span class="separator"></span>
  		<a class="mini-button" plain="false" iconCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a>
  		<span class="separator"></span>
  		
  </div>
  <form id="applysheet" name="applysheet" action="#" method="post">
  <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;"  width="99%">
  <tr bgcolor=#EBEFF5>
  <td><label for="applySheetid$text">单号</label></td>
  <td><input id="applySheetid" name="applySheetid" class="mini-textbox" required="true" enabled="false"  width="100%" value="${apply.applySheetid }"></td>
  <td ><label for="applyDate$text">开单日期</label></td>
  <td><input id="applyDate" name="applyDate" class="mini-datepicker" required="true" dateFormat="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd" 
  showTodayButton="false" showClearButton="false" allowInput="false" width="100%" value="${apply.applyDate }"/></td>
   <td><label for="applicantId$text">申请人</label></td>
  <td><input id="applicantId" name="applicantId"  class="mini-buttonedit" width="100%" textName="applicantName" onbuttonclick="onButtonEditEmployee" 
  allowInput="false" required="true" value="${apply.applicantId }" text="${apply.applicantName }"></td>
  
  </tr>
  <tr bgcolor=#EBEFF5>
 
  <td><label for="deptId$text">使用部门</label></td>
  <td><input id="deptId" name="deptId" class="mini-buttonedit" width="100%" textName="deptname" onbuttonclick="onButtonEditDept" 
  		allowInput="false" required="true" value="${apply.deptId }" text="${apply.deptName }"></td>
 
 <td><label for="warehouse_id$text">库&nbsp;&nbsp;&nbsp;&nbsp;房</label></td>
 <td><input id="warehouse_id" name="warehouse_id" class="mini-buttonedit" width="100%"  textName="warehouse_name" onbuttonclick="onButtonEditWarehouse" 
  				allowInput="false" required="true" value="${apply.warehouseId }" text="${apply.warehouseName }"/></td>
 <td><label for="order_id$text">订单号</label></td>
 <td><input id="order_id"  name="order_id" class="mini-buttonedit" width="100%" onbuttonclick="onOrderButtonEdit"
     textName="orderId" required="false"allowInput="false" value="${apply.orderId }" text="${apply.orderId }"/></td>
 
 </tr>
  </table>
  <input id="isPass" name="isPass" class="mini-hidden" value="${apply.isPass }" readonly>
  </form>
  
	
  <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;" borderStyle="border:0;" 
    multiSelect="true" idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
   url="ShowApplyDetailServlet?applySheetid=${apply.applySheetid }">
      	<div property="columns">
            <div type="indexcolumn"></div>
    		<div name="action" width="60" headerAlign="center" align="center" renderer="onOperatePower"
    		cellStyle="padding:0;">操作</div>
    		<div field="itemId" headerAlign="center" align="center" width="100">货品编号</div>
    		<div field="itemName" headerAlign="center" align="center" width="100">货品名称</div>
    		<div field="spec" headerAlign="center" align="center" width="100">规格</div>
    		<div field="unit" headerAlign="center" align="center" width="100">单位</div>
    		<div field="itemTypeDesc" headerAlign="center" align="center" width="100">类型</div>
    		<div field="usage" headerAlign="center" align="center" width="100">用途</div>
    		<div field="poNum" headerAlign="center" align="center" width="100">数量</div>
    		<div field="memo" headerAlign="center" align="center" width="100">备注</div>
    
    	</div>
	</div>

 <div id="editWindow" class="mini-window" title="修改采购申请信息" style="width:850px;height:200px;"
    showModal="true" allowResize="true" allowDrag="true">
    <div id="editform" class="form" >
      <input class="mini-hidden" name="id"/>
      <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;" width="99%">
            <tr bgcolor="#B6E3BF">
                <td><label for="itemId$text">物料编号</label></td>
                <td><input id="itemId" name="itemId" class="mini-buttonedit" textName="itemId" required="true" enabled="false" width="100%" onbuttonclick="onButtonEdit"/></td>
                <td><label for="itemName$text">物料名称</label></td>
                <td><input id="itemName" name="itemName" class="mini-textbox" required="true" width="100%"/></td>
				<td><label for="spec$text">规&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;格</label></td>
                <td><input id="spec" name="spec" class="mini-textbox" width="100%" required="true"/></td>
            </tr>
            <tr bgcolor="#B6E3BF">
               
               
                <td><label for="itemType$text">类&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;型</label></td>
                <td><input id="itemType" name="itemType" class="mini-combobox" textField="text" valueField="id" 
                width="100%" required="true" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="true" url="GetItemTypeServlet"/></td>
                <td><label for="usage$text">用&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;途</label></td>
            	<td><input id="usage" name="usage" class="mini-textbox" width="100%" required="true"/></td>
            	<td><label for="poNum$text">数&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;量</label></td>
                <td><input id="poNum" name="poNum" class="mini-textbox" width="100%" required="true"/></td>
            </tr>
            <tr bgcolor="#B6E3BF">
               
                
                 <td><label for="unit$text">单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;位</label></td>
                <td><input id="unit" name="unit" class="mini-textbox" width="100%" /></td>
                <td><label for="memo$text">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注</label></td>
                <td colspan="3"><input id="memo" name="memo" class="mini-textbox" width="100%"/></td>
                
            </tr>
             <tr>
                <td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="6">
                    <a class="Update_Button" href="javascript:updateRow()">保存</a> 
                    <a class="Cancel_Button" href="javascript:cancelRow()">取消</a>
                </td>                
            </tr>
      </table>
       <input id="SitemId" name="SitemId" class="mini-hidden" required="true"/>
     </div>
 </div>
    <script type="text/javascript">
    mini.parse();
    var grid=mini.get("grid1");
    grid.load();
    var editWindow=mini.get("editWindow");
    
    function getForm(){
    	var form1=new mini.Form("applysheet");
    	var data=form1.getData();
    	data.applyDate=mini.get("applyDate").getFormValue();
    	var json= mini.encode(data);
    	form1.validate();
    	if(form1.isValid()==false){
    		return;
    	}else{
    		$.ajax({
    			type:"post",
    			url:"doEditApplyHeaderServlet",
    			data:{submitData:json},
    			dataType:"json",
    			success:function(data){
    			alert(data.result);
    			}
   			});

    	}
    
    }
    
    function onOperatePower(e){
    var str="";
    str+="<span>";
    str+="<a title='编辑' href=javascript:ondEdit(\'"+e.row.itemId+"\')><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>"; 
    str+="</span>";
    str+="<span>";
    str+="<a title='删除' href=javascript:ondDelete(\'"+e.row.itemId+"\')><span class='mini-button-text mini-button-icon icon-remove'>&nbsp;</span></a>"; 
    str+="</span>";
    return str; 
    
    }
    function ondEdit(itemId){
    	var isPass=mini.get("isPass").getValue();
    	if(isPass==1){
    		alert("已审核通过，不能修改！");
    	}else{
    	
   			var applySheetid=mini.get("applySheetid").getValue();
   			var grid= mini.get("grid1");
			var editWindow = mini.get("editWindow");
        	editWindow.show();
        	var form = new mini.Form("#editform");
        	form.clear();
        	form.loading();
        	$.ajax({
             	url: "editApplyDetailServlet?applySheetid="+applySheetid+"&itemId="+itemId,
             	success: function (text) {
             	form.unmask();
             	var data = mini.decode(text);
             	form.setData(data);
             	mini.get("itemId").setText(data.itemId);
             	mini.get("SitemId").setValue(data.itemId);
   //          mini.get("itemType").setText(data.itemTypeDesc);   
            	},
            	error: function () {
           			alert("表单加载错误");
            		form.unmask();
               		}
            	});
            
            }
   		 }
    
    
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
	    
	    }
    
    function ondDelete(itemId){
    	var applySheetid=mini.get("applySheetid").getValue();
    	$.ajax({
    	type:"post",
  		url:"deletePoApplyDetailServlet?applySheetid="+applySheetid+"&itemId="+itemId, 
  		success:function(data){
  			 alert(data.result);
  			 window.location.href=window.location.href;
  		} 	
    	});
    
    }
    
    function updateRow(){
    var applySheetid=mini.get("applySheetid").getValue();
    var form=new mini.Form("editform");
    var data=form.getData();
    var json=mini.encode(data);
    form.validate();
    if(form.isValid()==false){
    	return;
    
    }else{
    	$.ajax({
    	type:"post",
    	url:"doeditApplyServlet?applySheetid="+applySheetid,
    	data:{submitData:json},
    	dataType:"json",
    	success: function(data){
    	 grid.reload();
         form.clear();
		 editWindow.hide();
         alert(data.result);
    	}
    	
    	});
    }
  }
  
  
    function cancelRow() {
    
           grid.reload();
           editWindow.hide();
        }
    
    
     function onOrderButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Checkout/selectOrderIdWindow.jsp",
                title: "订单号列表",
                width: 650,
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
                            btnEdit.setValue(data.itemid);
                            btnEdit.setText(data.itemid);
                       		mini.get("itemName").setValue(data.itemname);
                       		mini.get("itemType").setValue(data.itemtypeid);
                       		mini.get("spec").setValue(data.itemspecification);
                       		mini.get("unit").setValue(data.unitm); 
                       		}
                       	}
                    }
               });
           }
    </script>
  </body>
</html>
