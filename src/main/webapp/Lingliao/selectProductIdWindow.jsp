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
		</div>
        <div id="grid1" class="mini-datagrid" style="width:100%;height:100%;" idField="id" allowResize="true"
            borderStyle="border-left:0;border-right:0;" onrowdblclick="onRowDblClick" showColumnsMenu ="true"
        >
            <div property="columns">
                <div type="indexcolumn" ></div>
                <div field="productId" width="100" headerAlign="center">图号</div>
	            <div field="productName" width="100" headerAlign="center">产品名称 </div>
	            <div field="issueNum" width="100" headerAlign="center">版本号</div>
            	<div field="spec" width="100" headerAlign="center">规格</div>
            	
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
    //动态设置URL
    //grid.setUrl("../data/AjaxService.jsp?method=SearchEmployees");
    var Request = new Object(); 
        Request = GetRequest(); 
    var orderId=Request['orderId'];
    
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
    
    grid.setUrl("selectProductIdServlet");
    //也可以动态设置列 grid.setColumns([]);

    grid.load({orderId:orderId});
	
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
