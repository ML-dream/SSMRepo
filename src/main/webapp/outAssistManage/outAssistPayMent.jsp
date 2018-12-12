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
   
    <title>外协付款</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body style="height:99%">
  <div class="mini-toolbar">
  <a class="mini-button" iconCls="icon-add" plain="false" onclick="addPay()">新增</a>
  <span class="separator"></span>
  <a class="mini-button" iconCls="icon-edit" plain="false" onclick="primary()">初期数据维护</a>
  <span class="separator"></span>
  <a class="mini-button" iconCls="icon-node" plain="false" onclick="paySheet()">付款记录</a>
  </div>
  	<form id="datepicker" action="" method="post">
  	<input id="startDate" name="startDate" class="mini-datepicker" showClearButton="false" showTodayButton="fasle"
  	showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false"/>&nbsp;&nbsp;&nbsp;&nbsp;
  	&nbsp;&nbsp;&nbsp;&nbsp;
  	<input id="endDate" name="endDate" class="mini-datepicker" showClearButton="false" showTodayButton="fasle"
  	showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" allowInput="false" format="yyyy-MM-dd" showTime="false"/>
  	<a class="mini-button" plain="fasle" onclick="onSelect()">查询</a>
  	<input id="companyId" name="companyId" class="mini-hidden" value="${companyId }"/>
  	</form>
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="SelectedOutAssistPayListServlet?companyId=${companyId}" ondrawsummarycell="onDrawSummaryCell" onshowrowdetail="onShowRowDetail">
        <div property="columns"> 
         <div type="indexcolumn">序号</div>
         <div type="expandcolumn"></div>
         <div field="menuId" headerAlign="center" align="center">外协单号</div>
    	 <div field="companyName" headerAlign="center" align="center">外协单位</div>
         <div field="deliverTime" headerAlign="center" align="center">外协时间</div>
         <div field="payTime" headerAlign="center" align="center">付款时间</div>
         <div field="theoryTotalPrice" headerAlign="center" align="center">理论报价</div>
         <div field="totalPrice" headerAlign="center" align="center">实际报价</div>
         <div field="qualityFine" headerAlign="center" align="center">质量罚款</div>
       </div>
    </div>
    
    <div id="detailGrid_Form" style="display:none;">
    	<div id="detailgrid" class="mini-datagrid" style="width:100%;height:250px;" borderStyle="border:0;" multiSelect="true" 
    	 idField="id" allowAlternating="true" showPager="true" url="OutAssistMenuSpecServlet">
    		<div property="columns">
            <div type="indexcolumn"></div>
            <div field="orderId" width="120"  headerAlign="center">订单号 </div> 
            <div field="productId" width="80"  headerAlign="center">图号</div>
            <div field="companyName" width="100" headerAlign="center">单位名称</div>
            <div field="productName" width="60" headerAlign="center">零件名称</div>
            <div field="operName" width="50" headerAlign="center">加工工序</div>
            <div field="unitPrice" width="40" headerAlign="center">单价  </div>
            <div field="num" width="30" headerAlign="center">计划数量</div>
            <div field="passNum" width="30" headerAlign="center">实际数量</div>
            <div field="theoryTotalPrice" width="40" headerAlign="center">计划总价</div>
            <div field="totalPrice" width="40" headerAlign="center">实际总价</div>
            <div field="qualityFine" width="30"  headerAlign="center">质量罚款 </div>
        </div>
    	</div>
    	</div>
    <div id="editWindow" class="mini-window" title="添加付款信息" style="width:100%;height:200px;"
    showModal="true" allowResize="true" allowDrag="true">
    	<div id="editform" class="form" >
        <input class="mini-hidden" name="id"/>
         <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;" width="100%">
            <tr bgcolor="#B6E3BF">
             <td><label for="companyId$text">客户</label></td>
                <td><input id="companyId" name="companyId" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" textName="companyName" required="true" 
                allowInput="false" value="${companyId }" text="${companyName }" enable="false" readonly="readonly"/></td>
                <td><label for="payDate$text">日期</label></td>
                <td><input id="payDate" name="payDate" class="mini-datepicker" width="100%" showClearButton="false" showTodayButton="fasle"
  				showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" required="true" allowInput="false" format="yyyy-MM-dd" showTime="false" enable="false" readonly="readonly"/></td>
				<td><label for="thispaid$text">本次付款</label></td>
                <td><input id="thispaid" name="thispaid" class="mini-textbox" width="100%" required="true"/></td>
                <td><label for="qualitykk$text">质量扣款</label></td>
                <td><input id="qualitykk" name="qualitykk" class="mini-textbox" width="100%" required="true"/></td>
             </tr>
             <tr>
                <td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="8">
                    <a class="Update_Button" href="javascript:updateRow()">保存</a> 
                    <a class="Cancel_Button" href="javascript:cancelRow()">取消</a>
                </td>                
            </tr>
          </table> 
    	</div>
    </div>    
    <script type="text/javascript">
      mini.parse();
      var grid=mini.get("grid1");
      var detailgrid=mini.get("detailgrid");
      var editWindow=mini.get("editWindow");
      grid.load({startDate:"",endDate:""});
      
      function updateRow() {  
       	  var editform = new mini.Form("#editform");
       	  editform.validate();
       	  if(editform.isValid()==false){
       	  	return;
       	  }else{
       	  	var editdata=editform.getData();
       	  	editdata.payDate=mini.get("payDate").getFormValue();
       	  	var json=mini.encode(editdata);
       	  	$.ajax({
       	  	type:"post",
       	  	url:"SaveOutAssistPayServlet",
       	  	dataType:"json",
       	  	data:{submitData:json},
       	  	success:function(data){
       	  	alert(data.result);
       	  	window.location.href=window.location.href;
       	  	}
       	  	})
       	  
       	  }
       
       }
       
       
       
   		  function getCurrentTime(){
   		      var now=new Date();
   		      var year = now.getFullYear();
		      var month = (((now.getMonth()+1) < 10) ? "0" : "") + (now.getMonth()+1);
		      var day = ((now.getDate() < 10) ? "0" : "") + now.getDate();
   		       mini.get("payDate").setValue(year+"-"+month+"-"+day);
   		      }
   	     getCurrentTime();
       
       
       
       function primary(){
       var companyId=mini.get("companyId").getValue();
       window.location="GoPrimaryDataMaintainServlet?companyId="+companyId;
       
       }
       
       
       
        function paySheet(){
      	var companyId=mini.get("companyId").getValue();
      	window.open("GoOutAssistPayListServlet?companyId="+companyId,
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
      
      }
       
        function onButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "orderManage/selectCustomerWindow.jsp",
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
                          
                        }
                    }

                }
            });
        }
       
       
       
     function onDrawSummaryCell(e) {
            var result = e.result;
            var grid = e.sender;
            if (e.field == "qualityFine") {                
                var s = "<b style='color:red;'>"
                s +=  	"应付款："+ result.totalPrice + "<br/>"
                		+"质量扣款:"+result.qualityFine+"<br/>"
                		+"已付款："+result.alreadyPay+"<br/>"
                		+"尚需付款："+result.shouldPay+"<br/>"
                		+"</b>";
                e.cellHtml = s;
            }
      }
      
     function onSelect(){
      	var startDate=mini.get("startDate").getFormValue();
      	var endDate=mini.get("endDate").getFormValue();
      	grid.load({startDate:startDate,endDate:endDate});
      } 
      
      function addPay(){
      	 editWindow.show();  
      }
      
       function cancelRow() {
    
           grid.reload();
           editWindow.hide();
        }
       
      function onShowRowDetail(e){
      	var grid = e.sender;
        var row = e.record;
        var td = grid.getRowDetailCellEl(row);
        td.appendChild(detailGrid_Form);
        detailGrid_Form.style.display = "block";
        detailgrid.load({menuId:row.menuId});
      }
      
      </script> 
  </body>
</html>