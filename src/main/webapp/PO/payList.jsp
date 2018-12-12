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
   
    <title>付款信息</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body style="height:99%">
  <form>
  	付款期限：<input id="startDate" name="startDate" class="mini-datepicker" showClearButton="false" showTodayButton="fasle"
  	showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false"/>&nbsp;&nbsp;&nbsp;&nbsp;
  	&nbsp;&nbsp;&nbsp;&nbsp;
  	<input id="endDate" name="endDate" class="mini-datepicker" showClearButton="false" showTodayButton="fasle"
  	showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false"/>
  	<a class="mini-button" plain="fasle" onclick="onSelect()">查询</a>
  	</form>
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="payListServlet" ondrawsummarycell="onDrawSummaryCell" onshowrowdetail="onShowRowDetail">
        <div property="columns"> 
         <div type="expandcolumn"></div>
         <div field="prSheetid" headerAlign="center" align="center">收货单号</div>
    	 <div field="customerName" headerAlign="center" align="center">客户</div>
         <div field="payTerm" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">付款期限</div>
         <div field="price" headerAlign="center" align="center">金额</div>
        
    	</div>
    </div>
    
    <div id="detailGrid_Form" style="display:none;">
    	<div id="detailgrid" class="mini-datagrid" style="width:100%;height:250px;" borderStyle="border:0;" multiSelect="true" 
    	 idField="id" allowAlternating="true" showPager="true" url="ShowSupplierPayDetailServlet">
    		<div property="columns">
  		<div type="indexcolumn">序号</div>
    		<div field="itemId" headerAlign="center" align="center">货品编号</div>
    		<div field="itemName" headerAlign="center" align="center">货品名称</div>
    		<div field="spec" headerAlign="center" align="center">规格</div>
    		<div field="unit" headerAlign="center" align="center">单位</div>
    		<div field="prNum" headerAlign="center" align="center">数量</div>
    		<div field="unitPrice" headerAlign="center" align="center">单价</div>
    		<div field="price" headerAlign="center" align="center">总价</div>
    	</div>
    	</div>
    	</div>
    	
    <script type="text/javascript">
      mini.parse();
      var grid=mini.get("grid1");
      var detailgrid=mini.get("detailgrid");
      var detailGrid_Form = document.getElementById("detailGrid_Form");
      grid.load({startDate:"",endDate:""});
      var detailGrid_Form = document.getElementById("detailGrid_Form");
      
      function onDrawSummaryCell(e) {
            var result = e.result;
            var grid = e.sender;
            if (e.field == "price") {                
                var s = "<b style='color:red;'>";
                s +=  	"总额："+ result.totalPrice + "<br/>"
                		+ "</b>";
                e.cellHtml = s;
            }
            }
    var Genders = [{id: '1', text: '一星期'},{id: '2', text: '一个月'},{id:'3',text:'两个月'},{id:'4',text:'三个月'}];
    function onGrenderRenderer(e){
    	for(var i=0,l=Genders.length;i<l;i++){
    		var g = Genders[i];;
    		if(g.id==e.value)return g.text;
    	
    	}
    	return "";
    }
    
     function onSelect(){
      	var startDate=mini.get("startDate").getFormValue();
      	var endDate=mini.get("endDate").getFormValue();
      	grid.load({startDate:startDate,endDate:endDate});
      }
      
      
      function onShowRowDetail(e){
      	var grid = e.sender;
        var row = e.record;
        var td = grid.getRowDetailCellEl(row);
        //var detailGrid_Form=mini.get("detailGrid_Form");
        
        td.appendChild(detailGrid_Form);
        detailGrid_Form.style.display = "block";
        detailgrid.load({prSheetid:row.prSheetid});
      
      }
      </script> 
  </body>
</html>
