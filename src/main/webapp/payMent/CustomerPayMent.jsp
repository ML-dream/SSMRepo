<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<c:choose>
       	<c:when test="${param.para=='see'}">
 		</c:when>
 		<c:otherwise>
		  <a class="mini-button" iconCls="icon-add" plain="false" onclick="addPay()">新增回款</a>
		  <span class="separator"></span>
		  <a class="mini-button" iconCls="icon-node" plain="false" onclick="paySheet()">回款记录</a>
		</c:otherwise>
	</c:choose>
<!--   <span class="separator"></span>-->
<!--  <input value="返回" type="button" style= "width:70px;" onclick="javascript:window.history.back(-1);" />-->
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
         url="GetCompanyPayServlet?companyId=${companyId }" ondrawsummarycell="onDrawSummaryCell">
        <div property="columns"> 
            <div type="indexcolumn">序号</div>
         <div field="checkoutDate" headerAlign="center" align="center" dateFormat="yyyy-MM-dd">出库日期</div>
         <div field="orderId" headerAlign="center" align="center">订单号</div>
    	 <div field="itemId" headerAlign="center" align="center">图号</div>
         <div field="itemName" headerAlign="center" align="center">名称</div>
         <div field="issueNum" headerAlign="center" align="center">版本号</div>
         <div field="drawingId" headerAlign="center" align="center">产品号</div>
         <div field="checkoutNum" headerAlign="center" align="center">已发货数量</div>
         <div field="unitprice" headerAlign="center" align="center">单价</div>
         <div field="price" headerAlign="center" align="center">总价</div> 
    	</div>
    </div>
    <div id="editWindow" class="mini-window" title="添加回款信息" style="width:850px;height:200px;"
    showModal="true" allowResize="true" allowDrag="true">
    	<div id="editform" class="form" >
        <input class="mini-hidden" name="id"/>
         <table style="text-align: right;border-collapse:collapse;" border="gray 1px solid;" width="99%">
            <tr bgcolor="#B6E3BF">
             <td><label for="companyId$text">客户</label></td>
                <td><input id="companyId" name="companyId" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" textName="companyName" required="true" 
                allowInput="false" value="${companyId }" text="${companyName }"/></td>
                <td><label for="payDate$text">日期</label></td>
                <td><input id="payDate" name="payDate" class="mini-datepicker" width="100%" showClearButton="false" showTodayButton="fasle"
  				showOkButton="false" dateFormat="yyyy-MM-dd  HH:mm:ss" required="true" allowInput="false" format="yyyy-MM-dd" showTime="false"/></td>
				 <td><label for="thispaid$text">本次回款</label></td>
                <td><input id="thispaid" name="thispaid" class="mini-textbox" width="100%" required="true"/></td>
            </tr>
             <tr>
                <td style="text-align:right;padding-top:5px;padding-right:20px;" colspan="6">
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
      grid.load({startDate:"",endDate:""});
      var editWindow=mini.get("editWindow");
          
      function onDrawSummaryCell(e) {
            var result = e.result;
            var grid = e.sender;
            if (e.field == "price") {                
                var s = "<b style='color:red;'>"
                s +=  	"应回款："+ result.totalPrice + "<br/>"
                		+ "退货总金额:"+result.backPrice+"<br/>"
                		+ "已回款:"+result.haspaid+"<br/>"
                		+"未回款:"+result.nopay+"<br/>"
                		+"</b>";
                e.cellHtml = s;
            }
      }
            
      function addPay(){
      	 editWindow.show(); 
      	  getCurrentTime();
      	  
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
       	  	url:"saveCustomerPayServlet",
       	  	dataType:"json",
       	  	data:{submitData:json},
       	  	success:function(data){
       	  	alert(data.result);
       	  	window.location.href=window.location.href;
       	  	
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
      	window.open("seeCustomerPayServlet?companyId="+companyId,
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
      
      function getCurrentTime(){
   		      var now=new Date();
   		      var year = now.getFullYear();
		      var month = (((now.getMonth()+1) < 10) ? "0" : "") + (now.getMonth()+1);
		      var day = ((now.getDate() < 10) ? "0" : "") + now.getDate();
   		       mini.get("payDate").setValue(year+"-"+month+"-"+day);
   		      }
   	    
      
      </script> 
  </body>
</html>
