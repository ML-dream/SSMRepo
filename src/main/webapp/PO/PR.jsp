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
    
    <title>采购收货</title>
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
  <a class="mini-button" iconCls="icon-print" plain="false"  onclick="printPr()">打印</a>
  <span class="separator"></span>
  <a class="mini-button" iconCls="icon-save" plain="false" onclick="getForm()">保存</a>
  <span class="separator"></span>
  <a class="mini-button" iconCls="icon-goto" plain="false" onclick="nextForm()">下一单</a>
  </div>
  <fieldset id="prdiv"align="center">
  <legend>采购收货单</legend>
  
  <form id="Prsheet" name="Prsheet" action="#" method="post">
  <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;" width="80%">
  <tr bgcolor=#EBEFF5>
  <td style="width:10%;"><label for="prDate$text">开单日期</label></td>
  <td style="width:20%;"><input id="prDate" name="prDate" class="mini-datepicker" required="true" dateFormat="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd" 
  showTodayButton="false" showClearButton="false" allowInput="false" width="100%"/>
  <input id="prSheetid" name="prSheetid" class="mini-textbox" required="true" enabled="false" width="100%" value="${pr_sheetid.sheetid }"></td>
  <td style="width:10%;"><label for="customerId$text">供应商</label></td>
  <td style="width:20%;"><input id="customerId" name="customerId" class="mini-buttonedit" width="100%" onbuttonclick="onSupplierButtonEdit" textName="customerName" required="true" allowInput="false"/></td>
  <td style="width:10%;"><label for="purchase$text">订货单单号</label></td>
  <td style="width:20%;"><input id="purchase" name="purchase" class="mini-combobox" width="100%" textName="" textField="text" valueField="id"
  			url=""  allowInput="true" showNullItem="true" nullItemText="请选择..." required="true" onvaluechanged="LoadGrid()"/></td>
  </tr>
  
  <tr bgcolor=#EBEFF5>
  <td><label for="purState$text">订货单状态</label></td>
  <td><input id="purState" name="purState" class="mini-combobox" width="100%" textName="" textField="text" valueField="id"
  			url="data/purchaseState.txt" allowInput="false" showNullItem="true" nullItemText="请选择..." required="true" /></td>
  <td><label for="connector$text">联系人</label></td>
  <td><input id="connector" name="connector" class="mini-textbox" required="true" width="100%"/></td>
  <td><label for="connectorTel$text">联系电话</label></td>
  <td><input id="connectorTel"  name="connectorTel" class="mini-textbox" required="true" width="100%" /></td>
  </tr>
  
   <tr bgcolor=#EBEFF5>
  <td><label for="payTerm$text">付款期限</label></td>
  <td><input id="payTerm" name="payTerm" class="mini-datepicker" required="true" dateFormat="yyyy-MM-dd HH:mm:ss" 
  format="yyyy-MM-dd" showTodayButton="false" showClearButton="false" allowInput="false" width="100%"/></td> 
   <td><label for="isBill$text">是否开具发票</label></td>
  <td><input id="isBill" name="isBill" class="mini-combobox" required="true" style="width:100%;" textField="text" valueField="id" emptyText="请选择..."
   url="data/trueOrFalse.txt" value="1" allowInput="false" showNullItem="true" nullItemText="请选择..."/></td>
  <td><label for="receipt$text">发票类型</label></td>
  <td><input id="receipt" name="receipt" class="mini-combobox" required="true" url="ReceiptServlet" style="width:100%" valueField="id" textField="text" 
   emptyText="请选择..." allowInput="false" showNullItem="true" nullItemText="请选择..."/></td>  
  
  </tr>
  <tr bgcolor=#EBEFF5>
  <td><label for="dutyParagraph$text">税务登记号</label></td>
  <td><input id="dutyParagraph" name="dutyParagraph" class="mini-textbox" width="100%" /></td>
  <td><label for="bank$text">开户银行</label></td>
  <td><input id="bank" name="bank" class="mini-textbox" width="100%"/></td>
  <td><label for="account$text">账号</label></td>
  <td><input id="account" name="account" class="mini-textbox" width="100%"/></td>
  </tr>
  <tr bgcolor=#EBEFF5>
  
	  <td><label for="examineId$text">审核人</label></td>
	  <td><input id="examineId" name="examineId"  class="mini-buttonedit" width="100%" textName="examineName" onbuttonclick="onButtonEditEmployee" allowInput="false" required="false" enabled="false"></td>
	  <td><label for="salesmanId$text">业务员</label></td>
	  <td><input id="salesmanId" name="salesmanId"  class="mini-buttonedit" width="100%" textName="salesmanName" onbuttonclick="onButtonEditEmployee" allowInput="false" required="false"  text="<%=((User)session.getAttribute("user")).getStaffName()%>" 
	  			value="<%=((User)session.getAttribute("user")).getStaffCode()%>"
	  ></td>
	  <td><label for="drawerId$text">开单人</label></td>
	  <td><input id="drawerId" name="drawerId"  class="mini-buttonedit" width="100%" textName="drawerName" onbuttonclick="onButtonEditEmployee" allowInput="false" required="false"  text="<%=((User)session.getAttribute("user")).getStaffName()%>"
	  			value="<%=((User)session.getAttribute("user")).getStaffCode()%>"
	  ></td>
  </tr>
  </table>
  
  <div id="tablediv">
   	 <div id="datagrid1" class="mini-datagrid" style="width:1300px;height:430px;"  allowCellValid="true" 
        url="LoadPurchaseItem" idField="id" allowResize="true" pageSize="20"   multiSelect="true" allowCellSelect="true" allowCellEdit="true"
    	showPager= "true" showPageInfo="true" showReloadButton = "false" showPagerButtonIcon="false"   oncellvalidation="onCellValidation"
    	   showColumnsMenu ="true"   editNextOnEnterKey= "true" 
    	allowCellWrap="true"  onshowrowdetail="" 
    >
 		<div property="columns">
 			<!-- <div type="checkcolumn" visible="true"></div> -->
            <div field="itemId" name = "itemId" width="80" headerAlign="center" allowSort="false" showColumnsMenu="true" headerStyle="height:40px;">货品编码</div>
            <div field="itemName" width="80" headerAlign="center" allowSort="false" >货品名称</div>
           
            <div field="inNum" width="60" headerAlign="center" allowSort="false"  visible="true" headerStyle="color:red;"  vtype="float;">入库数量
            	<input property="editor" class="mini-textbox" style="width:100%;height:100%;" minHeight="20" value=""/>
            </div>
            <div field="unitPrice" width="60" headerAlign="center" allowSort="false" headerStyle="color:red;"  vtype="float" >单价
            	<input property="editor" class="mini-textbox" style="width:100%;height:100%;" minHeight="20" value="0"/>
            </div>
            <div field="warehouseId" width="80" headerAlign="center" allowSort="false"  renderer="onHouseRenderer" headerStyle="color:red;"  vtype="required;" cellStyle="color:red;font-weight:900;">库房
            	 <input id="combo1" property="editor" class="mini-combobox" style="width:100%;" data="warehouse"  allowinput = "true" value=""/>
            </div>
            <div field="payMethod" width="80" headerAlign="center" allowSort="false" headerStyle="color:red;" renderer="onStateRenderer">付款方式
    		<input property="editor" class="mini-combobox" style="width:100%;height:100%;" minHeight="20" textField="text" valueField="id" 
    		value="1" url="data/payMethod.txt"/>
    		</div>
            <div field="prNum" width="60" headerAlign="center" allowSort="false" visible="true">订货数量</div>
            <div field="spec" width="100" headerAlign="center" allowSort="false">规格</div>    
            <div field="unit" width="40" headerAlign="center" allowSort="false">单位</div> 
            <div field="itemTypeName" width="60" headerAlign="center" allowSort="false" visible="true">类型
            </div>
            <div field="ussage" width="100" headerAlign="center" allowSort="false" visible="true">用途</div>
            
            <div field="price" width="60" headerAlign="center" allowSort="false"  vtype="float">货款
            	<!--<input property="editor" class="mini-textbox" style="width:100%;height:100%;" minHeight="20" value="0"/>
            --></div>
            <div field="stockId" width="80" headerAlign="center" allowSort="false" >库位
    		</div>
    		
    		<div field="poSheetid" width="40" headerAlign="center" allowSort="false"  visible="false">订货单
            </div>
        </div>
     </div>
  	<table>
     	<tr height= "30px">
     		<td width="80px"></td>
     		<td width="60px"> <a class="Update_Button" href="javascript:firstpage()">首页</a> </td>
     		<td width="60px"> <a class="Update_Button" href="javascript:uppage()">上一页</a> </td>
     		<td width="60px"> <a class="Update_Button" href="javascript:downpage()">下一页</a> </td>
     		<td width="60px"> <a class="Update_Button" href="javascript:endpage()">末页</a> </td>
     		<td width="60px"> <a class="Update_Button" href="javascript:freshGrid()">刷新</a> </td>
     		<!-- <td width="60px"> <a class="Update_Button" href="javascript:saveData()">保存</a> </td>  -->
     		<td><label>(鼠标右击表头，可选择隐藏列)</label></td>
     	</tr>
     </table>
<div id="win1" class="mini-window" title="已收货货品" style="width:700px;height:400px;" collapseOnTitleClick="true"
    showMaxButton="true" showCollapseButton="true" showShadow="true" showCloseButton="false" expanded="false"
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true" 
    >
    <div id="winGrid" class="mini-datagrid" style="width:100%;height:95%;" allowResize="true" onload= ""
        url="SeePrItem?key=${pr_sheetid.sheetid }"  idField="id" multiSelect="false" pagesize="20" >
        <div property="columns">
 			<!-- <div type="checkcolumn" visible="true"></div> -->
            <div field="itemId" name = "itemId" width="80" headerAlign="center" allowSort="false" showColumnsMenu="true" headerStyle="height:40px;">货品编码</div>
            <div field="itemName" width="80" headerAlign="center" allowSort="false" >货品名称</div>
           
            <div field="inNum" width="60" headerAlign="center" allowSort="false"  visible="true" headerStyle="color:red;"  vtype="float;">入库数量
            </div>
            <div field="unitPrice" width="60" headerAlign="center" allowSort="false" headerStyle="color:red;"  vtype="float" >单价
            </div>
            <div field="warehouseId" width="80" headerAlign="center" allowSort="false"  renderer="onHouseRenderer" headerStyle="color:red;"  vtype="required;"
            	cellStyle="color:red;font-weight:900;">库房
            </div>
            
            <div field="spec" width="70" headerAlign="center" allowSort="false">规格</div>    
            <div field="unit" width="40" headerAlign="center" allowSort="false" visible="false">单位</div> 
            <div field="itemTypeName" width="60" headerAlign="center" allowSort="false" visible="true">类型
            </div>
            <div field="price" width="60" headerAlign="center" allowSort="false"  vtype="float">货款
            </div>
            <div field="poSheetid" width="70" headerAlign="center" allowSort="false"  >订货单
            </div>
        </div>   
     </div>
</div>  		

  <input id="id" name="id" class="mini-hidden" value="${pr_sheetid.id }"/>
  <input id="seq" name="seq" class="mini-hidden" value="${pr_sheetid.seq }"/>
  </form>
  
  
  </fieldset>
  
  
  
  
  <script type="text/javascript">
  	mini.parse();
  	var warehouse=null;
  	
  	var winGrid  = mini.get("winGrid");
  	
  	
  	function printPr(){
  		var prSheetid= mini.get("prSheetid").getValue();
  		window.open("WarePrDetailServlet?prSheetid="+prSheetid+"&para=print");
  	}
  	
  	//winGrid.load();
  	function onbuttonclick(e){
  	}
  	function showAtPos() {
        var win = mini.get("win1");

        var x = "right";
        var y = "bottom";

        win.showAtPos(x, y);

    }
	showAtPos();
  	
  	function onCellValidation(e) {
            if (e.field == "warehouseId") {
                if (e.value == 0) {
                    e.isValid = false;
                    e.errorText = "不能为空";
                }
            }
        }
  	
  	function onHouseRenderer(e) {
            for (var i = 0, l = warehouse.length; i < l; i++) {
                var g = warehouse[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
  	
  	function LoadGrid(){
  		var purId = mini.get("purchase").getValue();
  		var grid  = mini.get("datagrid1");
  		grid.load({key: purId});
  		 $.ajax({
        	type: "post",
            url: "ShowWarehouse",
            cache: false,
            success: function (text) {
                var o = mini.decode(text);
                warehouse = o;
            }
	       });
  	}
  	
  	function uppage(){
			helpSave();
			var size = grid.getPageSize();
			var npage = grid.getPageIndex();	//获取当前页码 
			var page = npage;
			if(npage ==0){
			}else{
				page = npage -1;
				grid.gotoPage ( page, size );
			}
			issave =0;
		}
		function downpage(){
			helpSave();
			var size = grid.getPageSize();
			var npage = grid.getPageIndex();	//获取当前页码 
			
			var total = grid.getTotalCount();	//数据总数 
			var tpage = Math.ceil(total/size);	//总页数 
			
			var page = npage;		//跳转页码 
			if(npage == tpage-1){
			}else{
				page = npage +1;
				grid.gotoPage ( page, size );
			}
			issave =0;
		}
		function firstpage(){
			helpSave();
			var size = grid.getPageSize();
			grid.gotoPage ( 0, size );
			issave =0;
		}
		function endpage(){
			helpSave();
			var size = grid.getPageSize();
			var npage = grid.getPageIndex();	//获取当前页码 
			
			var total = grid.getTotalCount();	//数据总数 
			var tpage = Math.ceil(total/size);	//总页数 
			
			grid.gotoPage ( tpage-1, size );
			issave =0;
		}
		function celorder(){
			helpSave();
			grid.clearSort ( );		
		}
		
		function saveData(){
			// var data = grid.getChanges();
			var grid  = mini.get("datagrid1");
			grid.selectAll();
			var data = grid.getSelecteds();
    		 var json = mini.encode(data);
    		//alert (json);
    		grid.deselectAll();
		}
		function helpSave(){
			var data = grid.getChanges();
			var json = mini.encode(data);
			if(json != "[]" && issave ==0){
				var r = confirm("当前页数据已发生修改，是否保存?");
				if(r == true){
					saveData();
					issave = 1;
				}
			}
		}
		
	function freshGrid(){
		var grid  = mini.get("datagrid1");
		grid.reload();
	}
  	function getForm(){
	  	var form=new mini.Form("Prsheet");
	  	var data=form.getData();
	  	data.prDate=mini.get("prDate").getFormValue();
	  	data.payTerm=mini.get("payTerm").getFormValue();
	 	 var json=mini.encode(data);
	 	 
	 	var grid  = mini.get("datagrid1");
	 	grid.validate ( );
	 	var tepara = grid.isValid ( );
	 	if(!grid.isValid ( )){
	 		return;
	 	}
	 	
		grid.selectAll();
		var griddata = grid.getSelecteds();
   		var gridjson = mini.encode(griddata);
   		grid.deselectAll();
   		//alert(gridjson);
	  	form.validate();
	    if (form.isValid() == false) {
	          return;
	   }else{
	  	$.ajax({
	  		type:"post",
	  		url:"PrServlet",
	 	    data:{submitData:json, gridjson:gridjson},
	 	    dataType:"json",
	 		success: function(data){
	  			alert(data.result);
	  			winGrid.load();
	  			mini.get("customerId").disable(); 
	  		}
	  	});
	  	}
		
	  }
  
    function nextForm(){
    	//   加载新的收货单号
    	 $.ajax({
        	type: "post",
            url: "LoadNewPrsheetId",
            cache: false,
            success: function (text) {
                var o = mini.decode(text);
                mini.get("id").setValue(o.id);
                mini.get("seq").setValue(o.seq);
                mini.get("prSheetid").setValue(o.sheetid);
                mini.get("customerId").enable();
                
                mini.get("purState").setValue("");
                winGrid.setUrl("SeePrItem?key="+o.sheetid);
                winGrid.load();
                alert("请重新填写收货信息");
            }
	       });
    } 
  
   function onSupplierButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Supplier/selectSupplierWindow.jsp",
                title: "选择列表",
                width: 750,
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
 //                           mini.get("telephone").setValue(data.telephone);
                       		mini.get("dutyParagraph").setValue(data.dutyParagraph);
                       		mini.get("bank").setValue(data.bank);
                       		mini.get("account").setValue(data.account);
                       		
                       		//加载对应供货商下的订货单  
                       		 $.ajax({
					        	type: "post",
					            url: "FindPurchaseId?companyId=" + data.companyId,
					            cache: false,
					            success: function (text) {
					                var o = mini.decode(text);
					                mini.get("purchase").setData(o);
					            }
						       });
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
   function getCurrentTime(){
   		      var now=new Date();
   		      var year = now.getFullYear();
		      var month = (((now.getMonth()+1) < 10) ? "0" : "") + (now.getMonth()+1);
		      var day = ((now.getDate() < 10) ? "0" : "") + now.getDate();
   		       mini.get("prDate").setValue(year+"-"+month+"-"+day);
   		      }
   	     getCurrentTime();
   	     
   	     var prstate=[{id:"1",text:"现金"},{id:"2" ,text:"月结"}];
   	     
   	     function onStateRenderer(e) {
            for (var i = 0, l = prstate.length; i < l; i++) {
                var g = prstate[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
   	     
 </script>
  </body>
</html>
