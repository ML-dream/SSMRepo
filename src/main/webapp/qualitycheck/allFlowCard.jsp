<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html >
<html>
<head>
    <base href="<%=basePath%>">
    <!-- miniui.js -->
      <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<script type="text/javascript" src="<%=path %>/o_js/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/o_js/miniui/miniui.js"></script>
		<link href="<%=path %>/o_js/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/o_js/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>质检记录</title>
    <style type="text/css">
    	table#size_mark {
			font-family: verdana,arial,sans-serif;
			font-size:11px;
			color:#333333;
			border-width: 1px;
			border-color: #666666;
			border-collapse: collapse;}
		table#size_mark td {
			border-width: 1px;
			padding: 0px;
			border-style: solid;
			border-color: #666666;
			background-color: #ffffff;
		}
    </style>
</head>
<body>
	
<div id="win1" class="mini-window" title="所有记录" style="width:600px;height:450px;" collapseOnTitleClick="true"
    showMaxButton="true" showCollapseButton="true" showShadow="true" showCloseButton="false"
    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
    >
    <table >
   		<tr>
   			<td>选择时间</td>
          <td><input id="day" class="mini-datepicker" value="" allowinput="false" onvaluechanged="onValueChanged" format="yyyy-MM-dd"/>
          </td>
          <td>零件名称</td>
          <td> <input id="productNameF"  name="productNameF" class="mini-textbox" required="false" 
	                onenter="onValueChanged"   style= "width:100%;"/>
          </td>
   		</tr>
   	</table>
    <div id="winGrid" class="mini-datagrid" style="width:100%;height:90%;" allowResize="true" onload= ""
        url="LoadDayBarcode"  idField="id" multiSelect="false" pagesize="20" onselect="setBarcode()" onrowclick="setBarcode()">
        <div property="columns">
           <div type="indexcolumn"></div>
            <div field="barcode" name="barcode" width="40" headerAlign="center" allowSort="">条码号</div>    
            <div field="orderId" width="60" headerAlign="center" allowSort="false">订单号</div> 
            <div field="companyName" width="60" headerAlign="center" allowSort="false">客户</div>  
            <div field="productId" width="60" headerAlign="center" allowSort="false">产品号</div> 
            <div field="productName" width="40" headerAlign="center" allowSort="false">产品名称</div>  
         </div>   
     </div>
</div>

	<div id= "userdiv" style="margin:0 auto;">
   		<h1>零件条形码号</h1>
   		<form id ="form0" >
   		<table >
	   		<!-- 验证等属性在 textbox 中 -->
	   		<tr>
	   			<td>
	                <label >零件条形码</label>
	            </td>
	            <td style = "width:135px;">
	                <input id="piece_barcode"  name="piece_barcode" class="mini-textbox" required="true" 
	                	value="" onenter="loadgrid()"   vtype="rangeLength:15,18" style= "width:100%;"/>
	                <input id= "" name= "" class="mini-textbox" style="display:none"/>
	                
	            </td>
	            <td> <input value="确定" type="button" onclick="loadgrid()" style="width:50px;"/></td>
	            <td><input value="打印当前流程卡" type="button" onclick="printCard()" /></td>
	   		</tr>
	   	</table>
	   	</form>
	   	<form id="form1">
	   	<table>
   		<!-- 表头 -->
   			<tr>
				<td><label>订单号</label></td>
	            <td>
   					<input id="Order_id"  name="order_id" class="mini-textbox" width="240px" enabled="false" allowInput="false"/></td>
   				<td><label>客户名称</label></td>
	            <td>
   					<input id="CompanyName"  name="companyname" class="mini-textbox"  enabled="false" allowInput="false"/></td>
   				<td><label>工件状态</label></td>
	            <td>
   					<input id="Grade"  name="grade" class="mini-textbox"  value="正常" enabled="false" allowInput="false"/></td>
   			</tr>
   			<tr>
   				<td><label>零件图号</label></td>
	            <td><input id="DrawingId" name="drawingid" class="mini-textbox"  width="240px" enabled="false" allowInput="false"/></td>
   				<td><label>零件名称</label></td>
	            <td><input id="Product_Name" name="product_name" class="mini-textbox" enabled="false" allowInput="false"/></td>
	            <td><label>投入量</label></td>
	            <td><input id="Input_Num" name="input_num" class="mini-textbox" enabled="false" allowInput="false"/></td>
   			</tr>
   	 	</table>
   	 </form>
   	 
   <div id="tablediv">
   	 <div id="datagrid1" class="mini-datagrid" style="width:980px;height:480px;" allowResize="true" onload= ""
        url="ProcessFlowCard"  idField="id" multiSelect="false" pagesize="20" onselect="" onrowclick="">
        <div property="columns">
                
            <!--零件条形码 ，不可见 -->    
           <!--  <div type="indexcolumn"></div><div type="checkcolumn" ></div>   --> 
           
            <div field="fo_no" name="foNo" width="40" headerAlign="center" allowSort="true">工序号</div>    
            <div field="fo_opname" width="60" headerAlign="center" allowSort="false">工序名称</div> 
            <div field="rated_time" width="60" headerAlign="center" allowSort="false">工时定额</div>   
            
            <div header="任务" headerAlign="center">
                <div property="columns">
                    <div field="num" width="60" headerAlign="center">计划数量</div>
                    <div field="workername" width="60" headerAlign="center">加工者</div>
                    <div field="workerid" width="60" headerAlign="center" visible="false">工号</div>  
                    <div field="checkdate" width="80" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="false">日期</div>
                    <div field="machine" width="60" headerAlign="center">设备</div>
                     <div field="machineid" width="60" headerAlign="center"  visible="false">设备id</div>
                    <div field="item" width="60" headerAlign="center">刀具</div>
                </div>
            </div>  
             
             <div header="检验" headerAlign="center">
                <div property="columns">
                	<div field="usedtime" width="60" headerAlign="center" allowSort="false">实做工时</div>
                	<div field="accept_num" width="40" headerAlign="center" allowSort="false">合格数
           			 </div>
           			 <div field="reject_num" width="60" headerAlign="center" allowSort="false">不合格数
           			 </div>
            		 <div field="checker" width="60" headerAlign="center" allowSort="false">检验者</div>
                </div>
            </div> 
            
            <!-- 需配合datagrid的allowCellEdit等属性一起用 -->
            <div field="confirm_num" width="80" headerAlign="center" allowSort="false">送检数量
            </div>    
            <!-- 备注栏自动换行？ -->
            <div field="remark" width="120" headerAlign="center" allowSort="false">备注
            	<!-- <input class="mini-textbox" value="" style="width:100%;"  />  -->
            </div>        
        </div>
     </div>
   </div>
   
</body>
	<script type="text/javascript">
		mini.parse();
		//mini.get("piece_barcode").focus();
		var winGrid =new mini.get("winGrid");
		
	function onValueChanged(){
		var day = mini.get("day").getFormValue();
		var productName= mini.get("productNameF").getValue();
		winGrid.load({day:day,productName:productName});
	}
	function setBarcode(){
		var row = winGrid.getSelected();
		mini.get("piece_barcode").setValue(row.barcode);
		loadgrid();
	}
	function showAtPos() {
        var win = mini.get("win1");

        var x = "right";
        var y = "top";

        win.showAtPos(x, y);

    }
	showAtPos();
	function loadgrid(){
		// 	mini.get("piece_barcode").blur();
			var grid = new mini.get("datagrid1");
			var headerForm = new mini.Form("#form1");
			var form0 = new mini.Form("form0");
    	 	form0.validate();
        	 if (form0.isValid() == false) {return;} 
        	 
			var mark =0;
         	var barcode = mini.get("piece_barcode").getValue();
         	
         	checked = 0;
         	
         	
        //	加载表头 
        	$.ajax({
        		type: "post",
        		url:"LoadCardHeader?barcode="+barcode,
        		cache: false,
    			async:false,
    			success:function(text){
    				var data = mini.decode(text);
    				mark = data.status;		// 根据工件状态判断查询方式 
    				//9-16
					headerForm.setData(data,true);
					grid.load({key:barcode,mark:mark});	
					//11-1
    			},
    			error : function() {
					//alert("加载失败, 错误码：" + jqXHR.responseText);
				}
        	});
		}
     function printCard(){
     	//editWindow.show('right','middle');	//由于页面初始化时，给form赋过值，这里form是有初始值的 
       //	var editform= new mini.Form('#editform')
       //	editform.loading();
     	var barcode = mini.get("piece_barcode").getValue();
   		window.open("FlowCardPrint?barcode=" + barcode,
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
     }
	</script>
</html>