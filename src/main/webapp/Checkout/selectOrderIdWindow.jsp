<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>选择订单号</title>
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
   <div class="mini-fit">
		<div>
		<table>
		<tr>
		<td>客戶</td>
		<td><input id="customer" name="customer" class="mini-buttonedit" width="100%" onbuttonclick="onButtonEdit" textName="companyName" allowInput="false" 
		 value="${param.companyId }" onenter="search()" showclose="true" oncloseclick="onCloseClick ('customer')"/></td>
<!--		 text="${param.companyName }"-->
		<td>订单号</td>
		<td><input id="orderId" name="orderId" class="mini-textbox" width="100%" onenter="search()"/></td>
		<td ><label for="orderStatus$text">查询模式</label></td>
   		<td width="100px"><input id="orderMode" name="orderMode"  class="mini-combobox" width="100%" allowInput="false" required="true" 
   			url="data/orderPayMode.txt" value="1" onValuechanged="search()"></td>
		<td><input value="查询" type="button" style= "width:70px;" onclick="search()"/></td>
		</tr>
		</table>
		</div>
        <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" idField="id" allowResize="true"
            borderStyle="border-left:0;border-right:0;" onrowdblclick="onRowDblClick" showColumnsMenu ="true"
        >
            <div property="columns">
                <div type="indexcolumn" ></div>
                <!-- 
                <div field="loginname" width="120" headerAlign="center" allowSort="true">员工帐号</div>    
                <div field="name" width="100%" headerAlign="center" allowSort="true">姓名</div>                                            
                <div field="createtime" width="100" headerAlign="center" dateFormat="yyyy-MM-dd" allowSort="true">创建日期</div>    
                 -->
                <div field="orderId" width="100" headerAlign="center">订单号</div>
	            <div field="companyName" width="100" headerAlign="center">客户名称 </div>
	            <div field="connector" width="100" headerAlign="center">联系人</div>
            	<div field="connectorTel" width="100" headerAlign="center">联系人电话</div>
            	<div field="orderDate" width="100" headerAlign="center" dateFormat="yyyy 年 MM 月 dd 日">订单日期 </div>
            	<div field="endTime" width="100" headerAlign="center"  dateFormat="yyyy 年 MM 月 dd 日">交付日期 </div>
            	<div field="orderStatus" width="60" headerAlign="center" renderer="onGenderRenderer">订单状态</div>           
            </div>
        </div>
    
    </div>                
    <div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" borderStyle="border:0;">
        <a class="mini-button" style="width:60px;" onclick="onOk()">确定</a>
        <span style="display:inline-block;width:25px;"></span>
        <a class="mini-button" style="width:60px;" onclick="onCancel()">取消</a>
    </div>
    
    <script type="text/javascript">
    mini.parse();
    var grid = mini.get("grid1");
	var orderId=mini.get("orderId").getValue();
	var customer=mini.get("customer").getValue();
    //动态设置URL
    //grid.setUrl("../data/AjaxService.jsp?method=SearchEmployees");
    var Request = new Object(); 
        Request = GetRequest(); 
    var companyId=Request['companyId'];
    var companyName=Request['companyName'];
    var orderMode = mini.get("orderMode").getValue();
    
    function GetRequest() { 
        var url = location.search; //获取url中"?"符后的字串 
        var theRequest = new Object(); 
        if (url.indexOf("?") != -1) { 
        	var str = url.substr(1); 
        	strs = str.split("&"); 
        	for(var i = 0; i < strs.length; i ++) { 
        		theRequest[strs[i].split("=")[0]]=unescape(strs[i].split("=")[1]); 
        	} 
        } 
        return theRequest; 
    }
    
    grid.setUrl("OrderListServlet");
    //也可以动态设置列 grid.setColumns([]);

    grid.load({orderId:orderId,customer:customer,orderMode:orderMode});
	
	function search(){
		orderId=mini.get("orderId").getValue();
		customer=mini.get("customer").getValue();
		orderMode = mini.get("orderMode").getValue();
		//alert(customer);
		grid.load({orderId:orderId,customer:customer,orderMode:orderMode});
	}
	
	
	function onCloseClick (para){
		mini.get(para).setValue("");
		mini.get(para).setText("");
	
	}
	
    function GetData() {
        var row = grid.getSelected();
        return row;
    }
    /*function search() {
        var key = mini.get("key").getValue();
        grid.load({ key: key });
    }*/
    function onKeyEnter(e) {
        search();
    }
    function onRowDblClick(e) {
        onOk();
    }
    //////////////////////////////////
    function CloseWindow(action) {
        if (window.CloseOwnerWindow) return window.CloseOwnerWindow(action);
        else window.close();
    }

    function onOk() {
        CloseWindow("ok");
    }
    function onCancel() {
        CloseWindow("cancel");
    }
	 var Genders = [{id: "1", text: "新建"},{id: "2", text: "备料"},{id: "3", text: "代加工"},{id: "4", text: "加工中"},{id: "5", text: "完成"}];
        function onGenderRenderer(e) {
            for (var i = 0, l = Genders.length; i < l; i++) {
                var g = Genders[i];
                if (g.id == e.value) return g.text;
            }
            return "";
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

	
	
	 

</script>
  </body>
</html>
