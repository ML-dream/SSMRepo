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
   
    <title>回款信息</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body style="height:99%">
   <div class="mini-toolbar">
<!--  <a class="mini-button" iconCls="icon-print" plain="false"  onclick="print()">打印</a>-->
<!--  <span class="separator"></span>-->
  <a class="mini-button" iconCls="icon-add" plain="false" onclick="addPay()">新增付款</a>
  <span class="separator"></span>
  <a class="mini-button" iconCls="icon-node" plain="false" onclick="paySheet()">付款记录</a>
  <span class="separator"></span>
  <a class="mini-button" iconCls="icon-node" plain="false" onclick="ondSee()">提交标记</a>
  <span class="separator"></span>
  <a class="mini-button" iconCls="icon-node" plain="false" onclick="seeRemarked()">查看已对账</a>
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
     <div id="grid1" class="mini-datagrid" style="width:100%;height:90%;"  onshowrowdetail="onShowRowDetail"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="SupplierPayServlet?companyId=${companyId }" ondrawsummarycell="onDrawSummaryCell">
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
    	   allowUnselect="true" idField="id" allowAlternating="true" showPager="true" url="ShowSupplierPayDetailServlet?para=1">
    		<div property="columns">
    			
  				<div type="indexcolumn"></div>
<!--  				<DIV NAME="ACTION" WIDTH="40" HEADERALIGN="CENTER" ALIGN="CENTER" RENDERER="ONOPERATEPOWER"-->
<!--	                 CELLSTYLE="PADDING:0;">-->
<!--	            </DIV>-->
  				<div type="checkcolumn" width="40"></div>
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
    <div id="editWindow" class="mini-window" title="添加回款信息" style="width:100%;height:200px;"
    showModal="true" allowResize="true" allowDrag="true">
    	<div id="editform" class="form" >
        <input class="mini-hidden" name="id"/>
         <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;" width="100%">
            <tr bgcolor="#B6E3BF">
             <td><label for="companyId$text">客户</label></td>
                <td><input id="companyId" name="companyId" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" textName="companyName" required="true" 
                allowInput="false" value="${companyId }" text="${companyName }"/></td>
                <td><label for="payDate$text">日期</label></td>
                <td><input id="payDate" name="payDate" class="mini-datepicker" width="100%" showClearButton="false" showTodayButton="fasle"
  				showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" required="true" allowInput="false" format="yyyy-MM-dd" showTime="false"/></td>
				<td><label for="thispaid$text">本次付款</label></td>
                <td><input id="thispaid" name="thispaid" class="mini-textbox" width="100%" required="true"/></td>
                
            </tr>
            <tr bgcolor="#B6E3BF">
            <td><label for="qualitykk$text">质量扣款</label></td>
                <td><input id="qualitykk" name="qualitykk" class="mini-textbox" width="100%" required="true"/></td>
                <td><label for="tax$text">税款</label></td>
                <td><input id="tax" name="tax" class="mini-textbox" width="100%"/></td>
            	<td><label for="memo$text">备注</label></td>
                <td><input id="memo" name="memo" class="mini-textbox" width="100%"/></td>
            </tr>
             <tr>
                <td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="10">
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
      grid.load({startDate:"",endDate:""});
      var editWindow=mini.get("editWindow");
      var detailGrid_Form = document.getElementById("detailGrid_Form");
	    
	   detailgrid.on("beforeload", function (e) {
	       var data = detailgrid.getSelecteds();
	       if (data.length > 0) {
	        mini.alert("请先提交");
	          e.cancel = true;
	          //return;
	       }
	   });
	    
      function seeRemarked(){
      	detailgrid.setUrl("ShowSupplierPayDetailServlet?para=1&lookChecked=1");		//para=1 表示从当前页面传递过去
      	grid.load({startDate:"",endDate:"",para:0});
      	
      }    
      function ondSee(){
      	var data = detailgrid.getSelecteds ( );
      	if(data==null || data.length==0){
      		alert ("无选中记录");
      		return;
      	}
      	var gridjson = mini.encode(data);
	 	//alert(gridjson);
	 	
		$.ajax({
	  		type:"post",
	  		url:"RemarkPurchase",
	 	    data:{gridjson:gridjson},
	 	    dataType:"json",
	 		success: function(data){
	  			//alert(data.result);
	  			detailgrid.clearSelect();
	  			detailgrid.reload();
	  		}
	  	});
      	
      }
        
      function onDrawSummaryCell(e) {
            var result = e.result;
            var grid = e.sender;
            if (e.field == "price") {                
                var s = "<b style='color:red;'>"
                s +=  	"应付款："+ result.totalPrice + "<br/>"
                		+ "已付款:"+result.haspaid+"<br/>"
                		+"质量扣款:"+result.qualitykk+"<br/>"
                		+"未付款:"+result.nopay+"<br/>"
                		+"</b>";
                e.cellHtml = s;
            }
      }
            
      function addPay(){
      	 editWindow.show();  
      }
      
       function cancelRow() {
    
           grid.reload();
           editWindow.hide();
        }
        
        
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
       	  	url:"saveSupplierPayServlet",
       	  	dataType:"json",
       	  	data:{submitData:json},
       	  	success:function(data){
       	  	alert(data.result);
       	  		if(data.status==1){
       	  	
       	  			window.location.href=window.location.href;
       	  		}
       	  	}
       	  	})
       	  
       	  }
       
       }

        
      function onSelect(){
      	var startDate=mini.get("startDate").getFormValue();
      	var endDate=mini.get("endDate").getFormValue();
      	grid.load({startDate:startDate,endDate:endDate});
      }
      
      function paySheet(){
      	var companyId=mini.get("companyId").getValue();
      	window.open("seeSupplierPayServlet?companyId="+companyId,
	                "editwindow","top=50,left=100,width=950px,height=400px,status=no,toolbar=no,menubar=no,location=no,resizable=no,scrollbars=yes");
      
      }
      
      function onShowRowDetail(e){
      	var grid = e.sender;
        var row = e.record;
        var td = grid.getRowDetailCellEl(row);
        td.appendChild(detailGrid_Form);
        detailGrid_Form.style.display = "block";
        detailgrid.load({prSheetid:row.prSheetid});
      
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
        var Genders = [{id: '1', text: '一星期'},{id: '2', text: '一个月'},{id:'3',text:'两个月'},{id:'4',text:'三个月'}];
    function onGrenderRenderer(e){
    	for(var i=0,l=Genders.length;i<l;i++){
    		var g = Genders[i];;
    		if(g.id==e.value)return g.text;
    	
    	}
    	return "";
    }
    
     function getCurrentTime(){
   		      var now=new Date();
   		      var year = now.getFullYear();
		      var month = (((now.getMonth()+1) < 10) ? "0" : "") + (now.getMonth()+1);
		      var day = ((now.getDate() < 10) ? "0" : "") + now.getDate();
   		       mini.get("payDate").setValue(year+"-"+month+"-"+day);
   		      }
   	     getCurrentTime();
      </script> 
  </body>
</html>
