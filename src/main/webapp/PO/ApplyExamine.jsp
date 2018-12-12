<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>采购申请审核</title>
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
  	<div id="grid1" class="mini-datagrid" style="width:100%;height:95%;" borderStyle="border:0;" multiSelect="true" 
  	idField="id" showSummaryRow="true" showFilterRow="true" allowAlternating="true" showPager="true" url="ApplyExamineServlet">
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
    		<div field="status" headerAlign="center" align="center" renderer="onGenderRenderer">状态</div>
  	
  	
  	
  		</div>
  	</div>
  
  <script>
  mini.parse();
  var grid=mini.get("grid1");
  grid.load({sheetid:"",date:"",orderId:"",companyId:"",drawerId:""});
  
  
  
  function onOperatePower(e){
  var str="";
  str+="<span>";
  str+="<a style='margin-right:2px;' title='审核' href=javascript:ondEdit(\'"+e.row.posheetid+"\')><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
  str+="</span>";
  return str;
  }
  
  function ondEdit(posheetid){
  window.location="ExamineApplyServlet?posheetid="+posheetid;
  
  
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
            var sheetidbox = mini.get("sheetidFilter");
            var datebox = mini.get("dateFilter");
            var sheetid = sheetidbox.getValue();
            var date = datebox.getValue();
            var orderId=mini.get("orderidFilter").getValue();
            var companyId=mini.get("companyIdFilter").getValue();
            var drawerId=mini.get("drawerFilter").getValue();
            grid.load({ sheetid: sheetid,date:date,orderId:orderId,companyId:companyId,drawerId:drawerId });            

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
        
     function onSupplierButtonEdit(e) {
            var btnEdit = this;
            mini.open({
                //url: bootPATH + "../demo/CommonLibs/SelectGridWindow.html",
                url: "Supplier/selectSupplierWindow.jsp",
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
                          //  mini.get("connector").setValue(data.connector);
                         //  mini.get("connectortel").setValue(data.connectorTel); 
                        //  mini.get("telephone").setValue(data.telephone);
                       
                       
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
  </script>
  
  </body>
</html>
