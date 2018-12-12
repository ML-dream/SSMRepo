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
        <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>采购付款统计</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
    
  </head>
  
  <body style="height:99%;width:99%;">

  	<div class="mini-toolbar">
   <a class="mini-button" iconCls="icon-add" plain="false" onclick="add()">初始数据维护</a>
<!--   <span class="separator"></span>-->
<!--  <a class="mini-button" iconCls="icon-print" plain="false" onclick="print()">打印</a>-->
  </div>
  	<div>
  	<form id="datepicker" action="" method="post">
  	<input id="startDate" name="startDate" class="mini-datepicker" showClearButton="false" showTodayButton="fasle"
  	showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false"/>&nbsp;&nbsp;&nbsp;&nbsp;
  	&nbsp;&nbsp;&nbsp;&nbsp;
  	<input id="endDate" name="endDate" class="mini-datepicker" showClearButton="false" showTodayButton="fasle"
  	showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false"/>
  	<a class="mini-button" plain="fasle" onclick="onSearch()">查询</a>
<!--  	<input id="companyId" name="companyId" class="mini-hidden" value="${companyId }"/>-->
  	</form>
  	</div>
    <div id="grid" class="mini-datagrid" style="height:99%;width:99%;" borderStyle="border:0;" idField="id" showFilterRow="false"
    	multiSelect="true" showSummaryRow="true" allowAlternating="true" showPager="true" url="SupplierPayStatisticsServlet">
    	<div property="columns">
    		<div type="indexcolumn"></div>
    		<div field="companyId" headerAlign="center" align="center">客户编号</div>
    		<div field="companyName" headerAlign="center" align="center">客户名称</div>
    		<div field="beginPayment" headerAlign="center" align="center"><ul><li>本期初应回款</li></ul></div>
    		<div field="sale" headerAlign="center" align="center">本期销售额</div>
    		<div field="thisPayment" headerAlign="center" align="center">本期回款</div>
    		<div field="endPayment" headerAlign="center" align="center">本期末应回款</div>
    		<div name="action" headerAlign="center" align="center" renderer="onActionRenderer" cellStyle="padding:0;">初始数据维护详情</div>
    	</div>
    </div>
    
    <div id="editWindow" class="mini-window" title="Window" style="width:850px;" 
    showModal="true" allowResize="true" allowDrag="true">
    <div id="editform" class="form" >
    	<input class="mini-hidden" name="id"/>
    	<table style="text-align: center;border-collapse:collapse;" border="gray 1px solid;"  width="99%" height="99%;">
    		 <tr bgcolor=#EBEFF5>
    			<td width="12%"><label for="maintenanceDate$text">日期</label></td>
    			<td><input id="initialmaintenanceDate" name="maintenanceDate" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd HH:mm:ss" 
    				format="yyyy-MM-dd" showClearButton="false" showTodayButton="fasle" showOkButton="false" required="true" enabled="false"/></td>
    			<td width="12%"><label for="companyId$text">客户</label></td>
    			<td><input id="companyId" name="companyId" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" textName="companyName" enabled="false" required="true" 
                	allowInput="false"/></td>
    			<td width="12%"><label for="endPayment$text">初期应回款</label></td>
    			<td><input id="endPayment" name="endPayment" class="mini-textbox" width="100%" required="true" enabled="false"/></td>
    		</tr>
<!--    		<tr bgcolor=#EBEFF5>-->
<!--    		<td><label for="reason$text">修改原因</label></td>-->
<!--    		<td colspan="5"><input id="reason" name="reason" class="mini-textarea" width="100%" required="true"></td>-->
<!--    		-->
<!--    		</tr>-->
<!--    		<tr>-->
<!--      			<td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="8">-->
<!--                    <a id="update" class="mini-button" href="javascript:updateRow()">保存</a> -->
<!--                    <a id="cancle" class="mini-button" href="javascript:cancelRow()">取消</a>-->
<!--      			</td>                -->
<!--      	    </tr>-->
    	</table>
    	<input id="initialEndPayment" name="initialEndPayment" class="mini-hidden" width="100%" required="true"/>
    	</div>
    </div>
    
  
    <script type="text/javascript">
    mini.parse();
    var grid=mini.get("grid");
    grid.load({startDate:"",endDate:""}); 
    var editWindow = mini.get("editWindow");
    
    function onSearch(){
   	var startDate=mini.get("startDate").getFormValue();
   	var endDate=mini.get("endDate").getFormValue();
    grid.load({startDate:startDate,endDate:endDate});
   
   } 
    function add(){
    	window.open("PO/addSupplierPayDataMaintenance.jsp",
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
    
    
    }
    
    function onActionRenderer(e) {
            var grid = e.sender;
            var record = e.record;
            var uid = record._uid;
            var rowIndex = e.rowIndex;

            var s = '<a class="" href="javascript:ondEdit(\'' + uid + '\')">查看初始应回款</a>';
            return s;
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
    
    </script>
  
  </body>
</html>
