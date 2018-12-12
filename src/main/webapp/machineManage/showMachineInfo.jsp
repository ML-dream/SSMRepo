<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>库存信息</title>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/js.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/style.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>css/pagecard.css">
	<style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
	<script type='text/javascript' src="<%=basePath%>resources/js/tabcard.js"></script>
	<script type="text/javascript" src="<%=basePath%>resources/jquery/jquery.min.js"></script>
	<jsp:include page="/commons/miniui_header.jsp" />
  </head>
  <body>
  <!--  <div class="mini-toolbar" style="padding:2px;border:0;">
    	<a class="mini-button" iconCls="icon-save" plain="false" onclick="save()">保存</a>
		<span class="separator"></span>
    	<a class="mini-button" iconCls="icon-print" plain="false" onclick="print()">导出EXCEL文件</a>
    	<span class="separator"></span>
    	<a class="mini-button" plain="false" iconCls="icon-add" onclick="addItem()">新增</a>
    	<span class="separator"></span>
    	<a class="mini-button" plain="false" iconCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a>
    </div>  -->
    <div>
  	<input id="item_name" name="item_name" class="mini-textbox" onenter="onSearch()"/>
  	<a class="mini-button" plain="fasle" onclick="onSearch()">查询</a>
  	
  	</div>
   <%-- <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="ShowMachineNodeInfo">
        <div property="columns">
            <div type="indexcolumn"></div>
            <div name="action" width="50" headerAlign="center" align="center" renderer="onOperatePower" cellStyle="padding:0;">操作</div>
            <div field="machineId" width="100" headerAlign="center" align="center">编号</div>
            <div field="machineName" width="100" headerAlign="center" align="center">名称</div>
            <div field="spec" width="100" headerAlign="center" align="center">规格</div>
            <div field="itemTypeDesc" width="100" headerAlign="center" align="center">类型</div>
            <div field="warehouseName" width="100" headerAlign="center" align="center">库房</div>
            <div field="stockId" width="100" headerAlign="center" align="center">库位</div>
            <div field="stockNum" width="100" headerAlign="center" align="center">库存量</div>
            <div field="unitPrice" width="100" headerAlign="center" align="center">单价</div>
            <div field="unit" width="100" headerAlign="center" align="center">单位</div>
        </div>
    </div>
    <input id="machineId" name="machineId" class="mini-hidden" value="${machineId }" readonly/>
    <input id="machineName" name="machineName" class="mini-hidden" value="${machineName }" readonly/>
   
    <div id="editWindow" class="mini-window" title="添加库存信息" style="width:850px;height:200px;"
    showModal="true" allowResize="true" allowDrag="true">
    <div id="editform" class="form" >
      <input class="mini-hidden" name="id"/>
      <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;" width="99%">
      <tr>
      	<td><label for="warehouse_Id$text">库房</label></td>
      	<td><input id="warehouse_Id" name="warehouse_Id" class="mini-buttonedit" width="100%" required="true" allowInput="false" 
            textName="warehouse_Name" onbuttonclick="onButtonEditWarehouse" enabled="false"/></td>
        <td><label for="date$text">日期</label></td>
        <td><inoput id="date" name="date" class="mini-datepicker" required="true" dateFormat="yyyy-MM-dd HH:mm:ss" format="yyyy-MM-dd" 
 		 showTodayButton="false" showClearButton="false" allowInput="false"  width="100%" enabled="false"></input></td>
        <td><label for="itemId$text">编号</label></td>
        <td><input id="itemId" name="itemId" class="mini-textbox" width="100%" required="true" enabled="false"/></td>
        
      </tr>
      <tr>
      <td><label for="itemName$text">名称</label></td>
        <td><input id="itemName" name="itemName" class="mini-textbox" width="100%" required="true"/></td>
      	<td><label for="productType">类型</label></td>
      	<td><input id="productType" name="productType" class="mini-combobox"width="100%" required="true"
  			 textField="text" valueField="id" emptyText="请选择..." nullItemText="请选择..." shouwNullItem="true" allowInput="false" url="GetItemTypeServlet"/></td>
      	<td><label for="spec$text">规格</label></td>
      	<td><input id="spec" name="spec" class="mini-textbox" width="100%"></td>
      	
      	
      </tr>
      <tr>
      	<td><label for="stockNum$text">数量</label></td>
      	<td><input id="stockNum" name="stockNum" class="mini-textbox" width="100%"/></td>
      	<td><label for="unit$text">单位</label></td>
      	<td><input id="unit" name="unit" class="mini-textbox" width="100%"></td>
      	<td><label for="unitPrice$text">单价</label></td>
      	<td><input id="unitPrice" class="mini-textbox" width="100%"></td>
      </tr>
      <tr>
      <td><label for="stockId$text">库位</label></td>
      	<td colspan="5"><input id="stockId" name="stockId" class="mini-textbox" width="100%"/></td>
      </tr>
      <tr>
      	<td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="6">
      		<a class="Update_Button" href="javascript:updateRow()">保存</a> 
      		<a class="Cancel_Button" href="javascript:cancelRow()">取消</a>
      	</td>                
      </tr>
      </table>
    </div>
    </div> --%>
    
    
    
    <legend>
			机床基本信息
		</legend>
  <div id="add" style="background:#EFEFEF" >
		<form name="form1" id="form1" method="post" enctype="multipart/form-data">
			<table class="green_list_table" align="center" width="100%" border="0" style="word-break:break-all;border-collapse:collapse" bgcolor="#EFEFEF">
			<tr>
								<th>机床名称</th>
			    	<td><input id="ratedTime"  name="ratedTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			        <th width="12%">机床IP</th>
			    	<td><input id="planTime"  name="planTime" class="mini-textbox" style="background-color:blue" width="100%" style="background-color:blue" allowInput="false" vtype="float"/></td>
			    	<th width="12%">连接状态</th>
			    	<td><input id="operAidTime"  name="operAidTime" class="mini-textbox" style="background-color:blue" allowInput="false" width="100%" vtype="float"/></td>
			    </tr>
			    <tr>
			    			<th>数控系统</th>
			    	<td><input id="ratedTime1"  name="ratedTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    
			        <th width="12%">累计开机时间</th>
			    	<td><input id="planTime1"  name="planTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    	<th width="12%">最大轴数</th>
			    	<td><input id="operAidTime1"  name="operAidTime" class="mini-textbox"  width="100%" allowInput="false" vtype="float"/></td>
			    </tr>
   
			</table>
		</form>
		</div>

		
		
		
		
    
     <script type="text/javascript">
    	mini.parse();
    	
    	update();
    	
    	function update(){
	    	 
	    	 var machineId = "<%=request.getParameter("machineId")%>";
       	$.ajax({
       		type: "post",
       		url:"ShowMachineNodeInfo?machineId="+machineId,
       	
   			success:function(text){
   				
				 
		           var msg=$.parseJSON(text);
		           mini.get("ratedTime").setValue(msg.data.machineId);
		           mini.get("planTime").setValue(msg.data.machineName);
		           mini.get("operAidTime").setValue(msg.data.machineSpecs);
   			    },
   			error : function() {
   			   
				}
       	});
		}
    	
    	
    	
    	
    	
	    var grid = mini.get("grid1");
	    var machineId=mini.get("machineId").getValue();
	    var itemName="";
	    grid.load({machineId:machineId,itemName:""});
	    
	    var editWindow=mini.get("editWindow");
	    grid.on("drawcell",function (e) {
            var record = e.record,
        	column = e.column,
        	field = e.field,
        	value = e.value;
	    	if (field == "stockNum" && value <= 20) {
                e.cellStyle = "color:red;font-weight:bold;";
            }
	    	
	    	}
	    
	    
	    
	    );
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str = "<a style='margin-right:2px;' title='编辑'  href=javascript:ondEdit(\'" + e.row.itemId+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
	        //alert(e.row.staffCode);
	        return str;
	    }
	    
	    function ondEdit(itemId){
	        window.location.href = "EditStockServlet?itemId="+itemId;
		}
	
		function addItem(){
			var warehosueId=mini.get("warehouseId").getValue();
			var warehosueName=mini.get("warehouseName").getValue();
			editWindow.show();
			var form = new mini.Form("#editform");
			form.clear();
            //form.loading();
            //form.unmask();
            mini.get("warehouse_Id").setValue(warehosueId);
            mini.get("warehouse_Id").setText(warehosueName);
			getCurrentTime();
			
		}
	
		function print(){
    		var itemName=mini.get("item_name").getValue();
    		window.location="StockListExcelServlet?warehouseId="+warehouseId+"&itemName="+itemName;
    		
    	}
    
		function updateRow() {  
  			
           var form = new mini.Form("#editform");
           var data= form.getData();
		   var json = mini.encode(data);
           form.validate();
           if(form.isValid()==false){
           return;
           }else{
            $.ajax({
            	type:"post",
                url: "addWarehouseItemServlet",
                data: { submitData:json },
                dataType:"json",
                success: function (data) {
                	alert(data.result);
                	if(data.status==1){
                		window.location.href=window.location.href;
                	}
                },
                error: function (jqXHR, atus, errorThrown) {
                    alert(jqXHR.responseText);
                }
            });
            }
        }
	
	
	
	
	
		function cancelRow() {
    
           grid.reload();
           editWindow.hide();
        }
	
	    function refresh(){
			grid.reload();
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
   
    function onSearch(){
	 	itemName=mini.get("item_name").getValue();
	 	grid.load({machineId:machineId,itemName:itemName});
	 
	 }
	 
	 function getCurrentTime(){
   		      var now=new Date();
   		      var year = now.getFullYear();
		      var month = (((now.getMonth()+1) < 10) ? "0" : "") + (now.getMonth()+1);
		      var day = ((now.getDate() < 10) ? "0" : "") + now.getDate();
   		       mini.get("date").setValue(year+"-"+month+"-"+day);
   		      }
   	     
	 
    </script>
  </body>
</html>
