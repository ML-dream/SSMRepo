<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>修改退料记录</title>
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
  <a class="mini-button" iconCls="icon-print" plain="false" onclick="print()">打印订单</a> 
  <span class="separator"></span>
  <a class="mini-button" iconCls="icon-save" plain="false" onclick="getForm()">保存</a> 
  <span class="separator"></span>
  <a class="mini-button" iconCls="icon-undo" plain="false" onclick="javascript:window.history.back(-1);">返回</a>
  </div>
  <fieldset id="allDetail" style="width: 100%;" align="center">
		<legend>
			退料信息
		</legend>
	<form id="tlsheet" name="tlsheet" action="#" method="post">
	<div>
	<table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="99%"  >
   <tr bgcolor=#EBEFF5>
   <td><label for="rmDate$text">日&nbsp;&nbsp;&nbsp;&nbsp;期</label></td>
   <td><input id="rmDate" name="rmDate" class="mini-datepicker" width="100%" required="true" enabled="false" value="${tlsheet.rmDate }" ></td>
   <td><label for="rmSheetid">单&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;号</label></td>
   <td><input id="rmSheetid" name="rmSheetid" class="mini-textbox" width="100%" required="true" enabled="false" value="${tlsheet.rmSheetid }"></td>
   <td><label for="warehouse_id">库&nbsp;&nbsp;&nbsp;&nbsp;房</label></td>
   <td><input id="warehouse_id" name="warehouse_id" class="mini-buttonedit" width="100%" textName="warehouse_name" onbuttonclick="onButtonEditWarehouse" 
  allowInput="false" required="true"  value="${tlsheet.warehouseId }" text="${tlsheet.warehouseName }"/></td>
   </tr> 
   </table> 
   </div>
   
    <div id="grid1" class="mini-datagrid" style="width:100%;height:95%;"
         borderStyle="border:0;" multiSelect="true"  idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
         url="showTlDetailServlet?rmSheetid=${tlsheet.rmSheetid }"  >
        <div property="columns">
            <div type="indexcolumn"></div>
            <div name="action" width="60" headerAlign="center" align="center" renderer="onActionRenderer" cellStyle="padding:0;">操作
            </div>
            <div field="itemId" width="60" headerAlign="center" align="center">物料号
            </div>
          <div field="itemName" width="80" headerAlign="center" align="center">物料名称
          </div>  
            <div field="orderId" width="80" headerAlign="center" align="center">订单号
            </div>
            <div field="issueNum" width="60" headerAlign="center" align="center">版本号
            </div>
            <div field="itemType" width="60" headerAlign="center" align="center">类型
            </div>
              <div field="spec" width="60" headerAlign="center" align="center">规格
            </div>
            <div field="unit" width="60" headerAlign="center" align="center">单位
            </div>
            <div field="rmNum" width="60" headerAlign="center" align="center">退料数量
            </div>
            <div field="unitPrice" width="60" headerAlign="center" align="center">单价
            </div>
            <div field="price" width="60" headerAlign="center" align="center">金额
            </div>
            <div field="stockId" width="90" headerAlign="center" align="center">库位
            </div>
            <div field="memo" width="60" headerAlign="center" align="center">备注
            </div>    
        </div>
    </div>
    <div>
  <table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;" width="99%">
   <tr bgcolor=#EBEFF5>
   <td><label for="emp_id$text">领料人</label></td>
  <td><input id="emp_id" name="emp_id"  class="mini-buttonedit" width="100%" textName="emp" onbuttonclick="onButtonEditEmployee" 
  allowInput="false" required="true"  value="${tlsheet.empId }" text="${tlsheet.empName }"></td>
   <td><label for="dept$text">所属部门</label></td>
   <td><input id="dept" name="dept" class="mini-textbox" width="100%" required="true"  value="${tlsheet.dept }"></td>
   <td><label for="operator_id$text">操作员</label></td>
   <td><input id="operator_id" name="operator_id" class="mini-buttonedit" width="100%" textName="operator" onbuttonclick="onButtonEditEmployee"
   allowInput="false" required="true"  value="${tlsheet.operatorId }" text="${tlsheet.operatorName }"></td>
   </tr> 
   </table>
   </div>
   </form>
    </fieldset> 
    <script type="text/javascript">
    mini.parse();
    var grid=mini.get("grid1");
	grid.load();
    
    function getForm(){
    var form = new mini.Form("#tlsheet");
           	var data = form.getData();
           	data.rmDate=mini.get("rmDate").getFormValue();
            var json = mini.encode(data); 
            $.ajax({
            	url: "doeditTlSheetServlet",
            	type:"post",      
                data: {submitData: json },
               	dataType: "json",
                success: function (data) {
                   alert(data.result);	
		           window.location.href = window.location.href;	
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
                            mini.get("dept").setValue(data.sectionName);
                            //mini.get("connector").setValue(data.connector);
                            //mini.get("connectorTel").setValue(data.connectorTel);
                        }
                    }
                }
            });
        }
   function onActionRenderer(e){
		var str="";
			str += "<span>";
	        str += "<a style='margin-right:2px;' title='编辑' href=javascript:ondEdit(\'" +e.row.itemId+"\') ><span class='mini-button-text mini-button-icon icon-edit'>&nbsp;</span></a>";
	        str += "</span>";
     	return str;
   }
   
    function ondEdit(itemId) {
            var rmSheetid=mini.get("rmSheetid").getValue();
            var warehouse_id=mini.get("warehouse_id").getValue();
             window.location="editTlSheetForm?rmSheetid="+rmSheetid+"&itemId="+itemId+"&warehouse_id="+warehouse_id; 
  
        }
   
 	
 	
 	 function cancelRow() {
            grid.reload();
            editWindow.hide();
        }
 	
            
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
