<%@ page language="java" import="java.util.*,com.wl.common.OrderStatus" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <!-- miniui.js -->
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>零件计划主界面</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
	<body style="height: 100%;">
		<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
		    <div showHeader="false" region="west" width="200" maxWidth="250" minWidth="100" >
		    	<input id="foType" class="mini-combobox" style="" textField="text" valueField="id" 
   						 url="data/fochecktype.txt" value="0" showNullItem="false"  allowInput="false" onvaluechanged="changeFotype()"/>
		    	<a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()" >刷新</a>
		        <ul id="leftTree" class="mini-tree" style="width:100%;height:95%;"
		        	url="NorFoOrderTree" 
                    showTreeIcon="true" textField="text" idField="id" resultAsTree="false"  
                    onnodeselect="onNodeSelect"  onnodeclick= "onNodeSelect">        
                </ul>
		    </div>
		    <div title="center" region="center" bodyStyle="overflow:hidden;">
		        <iframe id="mainframe" frameborder="0" name="main" style="width:100%;height:100%;" border="0"></iframe>
		    </div>
		</div>
		<script>
			mini.parse();
	        var iframe = document.getElementById("mainframe");
			//var fotype = [{id:"0",text:"正常件"},{id:"1",text:"报废件"}];
			function changeFotype(){
				var type = mini.get("foType").getValue();  //判断正常件还是 报废件
				var url = "NorFoOrderTree";
				if(type ==1){
					url = "DisFoOrderTree";
				}
				var tree = mini.get("leftTree");
				tree.setUrl(url);
				tree.load();
			}
	        function onNodeSelect(e) {
	            var tree = mini.get("leftTree");
	        	var node = e.node;
	        	
	        	var orderId = "";//订单号
	        	var productId = "";
	        	if(node.orderId==null){
	        		var rootNode = tree.getAncestors(node);
	        		orderId = rootNode[0].id;
	        		if(2<=rootNode.length){
	        			productId = rootNode[1].id;
	        		}
		        }	
	        	if(node.level==1){			//订单层
	        		//iframe.src = "GoOrderPayedListServlet?orderId="+node.id;
	        		alert("请选择相应零部件!");
		        }
	        	if(node.level==2){			//零件层
	        		var type = mini.get("foType").getValue();  //判断正常件还是 报废件
	        		
	        		iframe.src = "BaseServlet?meth=ProductInfoServlet&pathTo=foAlldetail&orderId="+node.orderId+"&productId="+node.id+"&FProductId="+node.pid+"&issueNum="+node.issueNum+"&foType="+type+"&barcode="+node.barcode;
	        		//mini.alert("请选择具体订单项！");
		        }
	            var isLeaf = e.isLeaf;
	            if (isLeaf) {
	            	//iframe.src = node.url;
	            	//iframe.src = "OrderDetailSpecServlet?orderId="+node.orderId+"&productId="+node.id;
	            }   
	        }

	        function refresh(){
				var tree = mini.get("leftTree");
				tree.load();
		    }
		</script>
	</body>
</html>
