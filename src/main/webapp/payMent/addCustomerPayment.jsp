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
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>客户回款初始数据维护</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body style="height:99%;">
  
  	 <div class="mini-toolbar">
  		<a  id="getForm" class="mini-button" iconCls="icon-save" plain="false" onclick="getForm(0)">保存</a>

  	</div>
  	<div>
  	<fieldset id="addPayment" style="width: 75%;" align="center">
  	<legend>初始数据维护</legend>
  	<form id="addCustomerPayment" method="post" action=""> 	
    <table style="text-align: center;border-collapse:collapse;" border="gray 1px solid;" width="99%">
    <tr>
    <td width="10%"><label for="addDate$text">日期</label></td>
    <td width="15%"><input id="addDate" name="addDate" class="mini-datepicker" width="100%" dateFormat="yyyy-MM-dd HH:mm:ss" 
    format="yyyy-MM-dd" showClearButton="false" showTodayButton="fasle" showOkButton="false" required="true"/></td>
    <td width="10%"><label for="companyId$text">客户</label></td>
    <td width="15%"><input id="companyId" name="companyId" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" textName="companyName" required="true" 
                allowInput="false"/></td>
    <td width="10%"><label for="initialPayment$text">初始应回款</label></td>
    <td width="15%"><input id="initialPayment" name="initialPayment" class="mini-textbox" width="100%" required="true"/></td>
    </tr>
    </table>
    </form>
    </fieldset>
    </div>
    <br/>
    <div id="grid" class="mini-datagrid" style="height:80%;width:100%;" borderStyle="border:0;" 
    multiSelect="true" idField="id" showSummaryRow="true" allowAlternating="true" showPager="true"
     url="ShowaddCustomerPaymentServlet">
      	<div property="columns">
            <div type="indexcolumn"></div>
    		<div field="addDate" align="center" headerAlign="center" dateFormat="yyyy-MM-dd">日期</div>
    		<div field="customerId" align="center" headerAlign="center">客户编号</div>
    		<div field="companyName" align="center" headerAlign="center">客户名称</div>
    		<div field="connector" align="center" headerAlign="center">联系人</div>
    		<div field="initialPayment" align="center" headerAlign="center">初始应回款</div>
    	</div>
    </div>
    <script type="text/javascript">
    mini.parse();
    var grid=mini.get("grid");
    grid.load();
    function getForm(){
    	var addForm=new mini.Form("addCustomerPayment");
    	addForm.validate();
            if (addForm.isValid() == false) {
            	return;
            }else{
            	var data=addForm.getData();
            	data.addDate=mini.get("addDate").getFormValue();
            	var json=mini.encode(data);
            	$.ajax({
  					type:"post",
  					url:"addCustomerPaymentServlet",		
  					data:{submitData:json},
  					dataType:"json",
  					success: function(data){
  						alert(data.result);
  						if(data.status==1){
  							window.location.href=window.location.href;
  						}
  					}
  				});
            
            }
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
   		      mini.get("addDate").setValue(year+"-"+month+"-"+day);
   		      }
   	     getCurrentTime();
    </script>
  </body>
</html>
