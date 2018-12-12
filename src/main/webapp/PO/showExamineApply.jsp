<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>采购申请审核记录</title>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
   <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
	<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
	<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
	
    <script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
	<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
    <style type="text/css">
    body{
        margin:0;padding:0;border:0;width:100%;height:100%;overflow:hidden;
    }
    </style>
  </head>
  
  <body>
  	<div class="mini-toolbar">
	<a class="mini-button" plain="false" iconCls="icon-reload" onclick="refresh()">刷新</a>
	</div>
	<div id="grid1" class="mini-datagrid" style="width:100%;height:95%;" borderStyle="border:0;" multiSelect="true" 
  	idField="id" showSummaryRow="true" showFilterRow="true" allowAlternating="true" showPager="true" url="ShowExamineApplyServlet">
  		<div property="columns">
  			<div type="indexcolumn"></div>
  			<div name="action" width="40" headerAlign="center" align="center" renderer="onOperatePower"
    		cellStyle="padding:0;">操作</div>
    		<div field="postartdate" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">日期
    			<input id="dateFilter" property="filter" class="mini-datepicker" style="width:100%;" dateFormat="yyyy-MM-dd" 
    			format="yyyy-MM-dd" showTodayButton="false" showClearButton="false" allowInput="false"  width="100%" onvaluechanged="onFilterChanged"/></div>
    		<div field="posheetid" headerAlign="center" align="center" >单号
    			<input id="sheetidFilter" property="filter" class="mini-textbox" style="width:100%;"onvaluechanged="onFilterChanged"/></div>
    		
<!--    		<div field="orderId" headerAlign="center" align="center">订单号-->
<!--    			<input id="orderidFilter" property="filter" class="mini-buttonedit" width="100%" textName="orderId" onbuttonclick="onOrderButtonEdit" onvaluechanged="onFilterChanged"/></div>-->
    		<div field="customername" headerAlign="center" align="center">供应商
    		<input id="companyIdFilter" property="filter" class="mini-buttonedit" width="100%" textName="companyId" onbuttonclick="onSupplierButtonEdit" onvaluechanged="onFilterChanged"/></div>
    		<div field="drawername" headerAlign="center" align="center">开单人
    		<input id="drawerFilter" property="filter" class="mini-buttonedit" width="100%" textName="companyId" onbuttonclick="onButtonEditEmployee" onvaluechanged="onFilterChanged"/></div>
    		<div field="examinename" headerAlign="center" align="center" width="60">审核人
    		<input id="drawerFilter" property="filter" class="mini-buttonedit" textName="examineName"  style="width:100%;" onbuttonclick="onButtonEditEmployee" onvaluechanged="onFilterChanged"/></div>
    		
    		<div field="status" headerAlign="center" align="center" renderer="onGenderRenderer">状态
    		<input id="statusFilter" name="statusFilter" property="filter" class="mini-combobox" textField="text" valueField="id" 
    		url="data/examineapply.txt" showNullItem="true" nullItemText="请选择..." emptyText="请选择..." allowInput="false" style="width:100%;" onvaluechanged="onFilterChanged"/></div>
  		</div>
  	</div>
	
	
<!--    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;" borderStyle="border:0;" -->
<!--    multiSelect="true" idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"-->
<!--     showFilterRow="true" url="ShowExamineApplyServlet">-->
<!--      	<div property="columns">-->
<!--            <div type="indexcolumn"></div>-->
<!--    		<div name="action" width="60" headerAlign="center" align="center" renderer="onOperatePower"-->
<!--    		cellStyle="padding:0;">操作</div>-->
<!--    		<div field="applySheetid" headerAlign="center" align="center" width="100">单号-->
<!--    			<input id="sheetidFilter" property="filter" class="mini-textbox" style="width:100%;"onvaluechanged="onFilterChanged"/></div>-->
<!--    		<div field="applyDate" headerAlign="center" align="center" width="80" dateFormat="yyyy-MM-dd">日期-->
<!--    			<input id="dateFilter" property="filter" class="mini-textbox" style="width:100%;"onvaluechanged="onFilterChanged"/></div>-->
<!--    			<div field="orderId" headerAlign="center" align="center">订单号-->
<!--    			<input id="orderidFilter" property="filter" class="mini-buttonedit" width="100%" textName="orderId" onbuttonclick="onOrderButtonEdit" onvaluechanged="onFilterChanged"/></div>-->
<!--    		<div field="applicantName" headerAlign="center" align="center" width="80">申请人-->
<!--    		<input id="applicantFilter" property="filter" class="mini-buttonedit" textName="applicantName"  style="width:100%;" onbuttonclick="onButtonEditEmployee" onvaluechanged="onFilterChanged"/></div>-->
<!--    		<div field="deptName" headerAlign="center" align="center" width="60">使用部门</div>-->
<!--    		<div field="examineName" headerAlign="center" align="center" width="60">审核人-->
<!--    		<input id="examineFilter" property="filter" class="mini-buttonedit" textName="examineName"  style="width:100%;" onbuttonclick="onButtonEditEmployee" onvaluechanged="onFilterChanged"/></div>-->
<!--    		<div field="isPass" headerAlign="center" align="center" width="60" renderer="onGenderRenderer">状态-->
<!--    			<input id="ispassFilter" name="ispassFilter" property="filter" class="mini-combobox" textField="text" valueField="id" url="data/examineapply.txt" showNullItem="true"-->
<!--    			nullItemText="请选择..." emptyText="请选择..." allowInput="false" style="width:100%;" onvaluechanged="onFilterChanged"/></div>-->
<!--    		-->
<!--    -->
<!--    	</div>-->
<!--    </div>-->
    
     <script type="text/javascript">
    mini.parse();
    var grid=mini.get("grid1");
   grid.load({ sheetid:"",date:"",orderId:"",companyId:"",drawerId:"",examineId:"",status:"" });
    
    
    function onOperatePower(e){
    var str="";
    str+="<span>";
    str+="<a style='margin-right:2px;' title='采购申请详情' href=javascript:ondSee(\'"+e.row.posheetid+"\')><span class='mini-button-text mini-button-icon icon-node'>&nbsp;</span></a>";
    str+="</span>";
    return str;
    }
    
    
    function ondSee(posheetid){
    window.location="showExamineApplyDetailServlet?posheetid="+posheetid;
    
    }
    
    var Genders = [{id: '0', text: '申请'},{id: '1', text: '待审核'},{id:'2',text:'审核通过'},{id:'3',text:'审核不通过'},{id:'4',text:'完成'}];
    function onGenderRenderer(e){
    	for(var i=0,l=Genders.length;i<l;i++){
    		var g = Genders[i];;
    		if(g.id==e.value)return g.text;
    	
    	}
    	return "";
    }
    
    
    function onFilterChanged() {
            var date=mini.get("dateFilter").getValue();
			var sheetid=mini.get("sheetidFilter").getValue();
			var orderId=mini.get("orderidFilter").getValue();
			var companyId=mini.get("companyIdFilte").getValue();
			var drawerId=mini.get("drawerFilter").getValue();
			var examineId=mini.get("drawerFilter").getValue();
			var status=mini.get("statusFilter").getValue();
			
            grid.load({ sheetid: sheetid,date:date,orderId:orderId,companyId:companyId,drawerId:drawerId,examineId:examineId,status:status });            

        }
     function refresh(){
    window.location.href=window.location.href;
    
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
                            onFilterChanged();
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
                            onFilterChanged();
                        }
                    }
                }
            });
        }     
    
    </script>
  </body>
</html>
