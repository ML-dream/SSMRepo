<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>客户信息</title>
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
  
  <body>
  	<div class="mini-toolbar">
  		<a class="mini-button" iconCls="icon-save" plain="false"  onclick="getForm()">保存</a>
	    <span class="separator"></span>
  		<a class="mini-button" plain="false" iconCls="icon-undo" onclick="javascript:window.history.back(-1);">返回</a>
  		<span class="separator"></span>
  		<a class="mini-button" iconCls="icon-add" plain="false"  onclick="addsheetDetail()">增加入库信息</a>
	    <span class="separator"></span>
  	</div>
  	<!--startprint-->
  	<fieldset id="allDetail" style="width: 100%;" align="center">
		<legend>
			入库单信息
		</legend>
		<form name="checkindiv" id="checkindiv" action="doEditCheckinServlet" method="post" enctype="multipart/form-data">
   		<table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="99%" >
   		<tr>
   		<td width="10%"><label for="checkin_date$text">日期</label></td>
        <td width="20%"><input id="checkin_date"  name="checkin_date" class="mini-datepicker"  width="100%" required="true"  dateFormat="yyyy-MM-dd  HH:mm:ss" 
        allowInput="false" format="yyyy-MM-dd" showTime="false" showOkButton="false" showClearButton="false" value="${checkin.checkinDate}" enabled="false"/></td>
   		<td width="10%"><label for="checksheet_id$text">单号</label></td>
        <td width="25%"><input id="checksheet_id"  name="checksheet_id" class="mini-textbox"  width="100%" required="true" enabled="false" value="${checkin.checksheetId}"/></td>
   		<td><label for="warehouse_id$text">库房</label></td>
		<td><input id="warehouse_id" name="warehouse_id" class="mini-buttonedit" width="100%" textName="warehouse_name" onbuttonclick="onButtonEditWarehouse" 
  		allowInput="false" required="true"  value="${checkin.warehouseId }" text="${checkin.warehouseName }"/></td>	
   		
        </tr>
       	<tr>
       	<td><label for="checkin_kind$text">入库类型</label></td>
        <td><input id="checkin_kind"  name="checkin_kind" class="mini-textbox"  width="100%" required="true" value="${checkin.checkinKind}"/></td>
   		<td width="10%"><label for="company_id$text">供应商</label></td>
        <td style="width:23%;"><input id="company_id" name="company_id" class="mini-buttonedit" width="100%" onbuttonclick="onCustomerButtonEdit" textName="company_name" 
        allowInput="false" value="${checkin.companyId }" text="${checkin.companyName}"/>
   		</td>
   		<td><label for="deptid$text">部门</label></td>
        <td><input id="deptid" name="deptid" class="mini-buttonedit" width="100%" textName="deptname" onbuttonclick="onButtonEditDept" 
  		allowInput="false" value="${checkin.deptId}" text="${checkin.deptName }"/></td>
   		
        </tr>
        <tr>   
        <td><label for="delivery$text">交货人</label></td>
         <td><input id="delivery"  name="delivery"  class="mini-buttonedit" width="100%" textName="delivery_name" onbuttonclick="onButtonEditEmployee" 
         allowInput="false" required="true"  value="${checkin.delivery }" text="${checkin.deliveryName }"/>  
         </td>
       	 <td><label for="examine$text">审核人</label></td>
         <td><input id="examine"  name="examine"  class="mini-buttonedit" width="100%" textName="examine_name" onbuttonclick="onButtonEditEmployee" 
         allowInput="false" required="true"  value="${checkin.examine }" text="${checkin.examineName }"/> </td>
         <td><label for="admin$text">仓库管理员</label></td>
         <td><input id="admin"  name="admin"  class="mini-buttonedit" width="100%" textName="admin_name" onbuttonclick="onButtonEditEmployee" 
         allowInput="false" required="true"  value="${checkin.admin }" text="${checkin.adminName }"/>  
         </td>
   		</tr>
   	 </table>
   	</form>
   
  	<div id="grid1" class="mini-datagrid" style="width:99%;height:93%;"
    	 borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true"
		url="seeDetailServlet?checksheet_id=${checkin.checksheetId}">
        <div property="columns">
            <div type="indexcolumn" width="10"></div>
          <div name="action" width="20" headerAlign="center" align="center" renderer="onOperatePower"
                 cellStyle="padding:0;">操作
            </div> 
           <div field="checkindetlId" width="30" headerAlign="center" align="center">序列号</div>
            <div field="itemId"  width="40" headerAlign="center" align="center">物料编号</div>
            <div field="itemName" width="40" headerAlign="center" align="center">物料名称</div>
            <div field="orderId" width="40" headerAlign="center" align="center">订单号</div>
            <div field="issueNum" width="40" headerAlign="center" align="center">版本号</div>
            <div field="itemType" width="40" headerAlign="center" align="center">类型</div>
            <div field="spec" width="40" headerAlign="center" align="center">规格</div>
            <div field="unit" width="30" headerAlign="center" align="center">单位</div>
            <div field="checkinNum" width="40" headerAlign="center" align="center">数量</div>
            <div field="unitprice" width="40" headerAlign="center" align="center">单价</div>
            <div field="price" width="40" headerAlign="center" align="center">金额</div>
           <!--  <div field="warehouse_id" width="40" headerAlign="center" align="center">库房</div> -->
            <div field="stockId" width="60" headerAlign="center" align="center">库位</div>
            
           
            <div field="lot" width="30" headerAlign="center" align="center">批次</div>
            <div field="qualityId" width="40" headerAlign="center" align="center">质编号</div>
             <div field="memo" width="40" headerAlign="center" align="center">备注</div>
            <!--
            <div field="SBTime" width="100" headerAlign="center" dateFormat="yyyy 年 MM 月 dd 日">实际开始时间</div>
            <div field="SETime" width="100" headerAlign="center" dateFormat="yyyy 年 MM 月 dd 日">实际结束时间</div>
            <div field="orderId" width="100" headerAlign="center">订单编号</div>
            <div field="companyName" width="100" headerAlign="center">客户名称</div>
            <div field="fproductId" width="100" headerAlign="center">父产品号</div>
            <div field="madePlace" width="100" headerAlign="center">制造单位</div>
            <div field="deptName" width="100" headerAlign="center">使用部门</div>
            <div field="unitPrice" width="100" headerAlign="center">单价</div>
            <div field="isDuiZhangHan" width="100" headerAlign="center">有无对账函</div>
            <div field="isLaiLiao" width="100" headerAlign="center">是否来料</div>
            <div field="isJiaoHuo" width="100" headerAlign="center">是否交货</div>
            -->
        </div>
    </div>
    
    </fieldset>
    <!--endprint-->
    <script type="text/javascript">
    	mini.parse();
	    var grid = mini.get("grid1");
	    grid.load();
	    
	    function getForm(){
 		  
 		  	var form = new mini.Form("#checkindiv");
 		  	var data=form.getData();
 		  	data.checkin_date=mini.get("checkin_date").getFormValue();
 		  	var json=mini.encode(data);
   			form.validate();
            if (form.isValid() == false) {
            	return;
            }else{
 		  		$.ajax({
      				type: "POST",
      				url: "doEditCheckinServlet",   				
      				//cache: false,
      				//enctype: 'multipart/form-data',
      				dataType: "json",
      				data: { submitData: json },
      				//processData: false,
    				//contentType: false,
      				success: function (data) {
        				alert(data.result);
		                 window.location.href = window.location.href;	
		                    
      				}
    			});
    		}
   		}
	    
	    
	    function onOperatePower(e) {
	        var str = "";
	        str += "<span>";
	        str = "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" + e.row.checkindetlId+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
	        return str;
	    }
	    
	    function ondEdit(checkindetl_id){
	    var checksheet_id=mini.get("checksheet_id").getValue();
	    var warehouse_id=mini.get("warehouse_id").getValue();
	        window.location="EditDetailSheetServlet?checksheet_id=" +checksheet_id+"&checkindetl_id="+checkindetl_id+"&warehouse_id="+warehouse_id;
		}
		
		 function addsheetDetail(){
			var checksheet_id = mini.get("checksheet_id").value;
			var warehouse_id=mini.get("warehouse_id").value;
			window.location = "AddSheetDetailServlet?checksheet_id="+checksheet_id+"&warehouse_id="+warehouse_id;
			//window.location = "Checkin/AddSheetDetail.jsp?checksheet_id="+checksheet_id+"&warehouse_id="+warehouse_id;
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
   
   function onCustomerButtonEdit(e) {
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
                            mini.get("connector").setValue(data.connector);
                            mini.get("connectortel").setValue(data.connectorTel); 
                        }
                    }

                }
            });
        }
	    //var Genders = [{ id: 'M', text: '男' }, { id: 'W', text: '女'}];
	    var Genders = [{id: "1", text: "新建"},{id: "2", text: "备料"},{id: "3", text: "代加工"},{id: "4", text: "加工中"},{id: "5", text: "完成"}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
        }
        /*
        function download(file){
        	var params = eval("("+mini.encode(data)+")");
        	var url = 'AddOrderServlet';
   			jQuery.post(url, params, function(data){
   	   		 	alert(data.result);
   	   		 	window.location.href=window.location.href;
   	   		 	},'json');
        }
        */
        
        function preview() 
		{ 
			var bdhtml=window.document.body.innerHTML;//获取当前页的html代码 
			var startStr="<!--startprint-->";//设置打印开始区域 
			var endStr="<!--endprint-->";//设置打印结束区域 
			var printHtml=bdhtml.substring(bdhtml.indexOf(startStr)+startStr.length,bdhtml.indexOf(endStr));//从标记里获取需要打印的页面 

			window.document.body.innerHTML=printHtml;//需要打印的页面 
			window.print(); 
			window.document.body.innerHTML=bdhtml;//还原界面 
		} 
		
		
		function print(){
			$("#allDetail").printArea(); 
		}


        
    </script>
    <script>
(function ($) {
    var printAreaCount = 0;
    $.fn.printArea = function () {
        var ele = $(this);
        var idPrefix = "printArea_";
        removePrintArea(idPrefix + printAreaCount);
        printAreaCount++;
        var iframeId = idPrefix + printAreaCount;
        var iframeStyle = 'position:absolute;width:0px;height:0px;left:-500px;top:-500px;';
        iframe = document.createElement('IFRAME');
        $(iframe).attr({
            style: iframeStyle,
            id: iframeId
        });
        document.body.appendChild(iframe);
        var doc = iframe.contentWindow.document;
        $(document).find("link").filter(function () {
            return $(this).attr("rel").toLowerCase() == "stylesheet";
        }).each(
                function () {
                    doc.write('<link type="text/css" rel="stylesheet" href="'
                            + $(this).attr("href") + '" >');
                });
        doc.write('<div class="' + $(ele).attr("class") + '">' + $(ele).html()
                + '</div>');
        doc.close();
        var frameWindow = iframe.contentWindow;
        frameWindow.close();
        frameWindow.focus();
        frameWindow.print();
    }
    var removePrintArea = function (id) {
        $("iframe#" + id).remove();
    };
})(jQuery);
</script>

	<script>
		function print2(){
			var bodyHTML=window.document.body.innerHTML;
			//window.document.body.innerHTML=$('#allDetail').html();  
			window.document.body.innerHTML=document.getElementById("allDetail").innerHTML;
			window.print();  
			window.document.body.innerHTML=bodyHTML; 
		}
	</script>
  </body>
</html>
