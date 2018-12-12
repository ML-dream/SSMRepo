<%@ page language="java" import="java.util.*,com.wl.common.OrderStatus,com.wl.common.ProductStatus" pageEncoding="utf-8"%>
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
   
    <title>工艺主界面</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  <!-- url="craftworkManage/treeTest.txt"   -->
	<body style="height: 100%;">
		<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
		    <div showHeader="false" region="west" width="200" maxWidth="250" minWidth="100" >
		    <input id="productName" name="productName" class="mini-textbox" width="200px"  allowInput="true" onvaluechanged="search()" emptyText="输入产品名称"/>
		    	<a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()" >刷新</a>
		        <ul id="leftTree" class="mini-tree" style="width:100%;height:95%;" expandOnLoad="true" autoLoad="true"
                    showTreeIcon="true" textField="text" idField="id" resultAsTree="false"  
                    url="GetCraftTreeServlet?orderStatusFrom=<%=OrderStatus.PASS%>&orderStatusTo=<%=OrderStatus.DELIVERING%>&productStatusTo=<%=ProductStatus.ADDED %>&productStatusTo=<%=ProductStatus.ADDED %>"
                    onnodeselect="onNodeSelect"  onnodeclick="onNodeSelect">        
                </ul>
		    </div>
		    <div title="center" region="center" bodyStyle="overflow:hidden;">
		        <iframe id="mainframe" frameborder="0" name="main" style="width:100%;height:100%;" border="0"></iframe>
		    </div>
		</div>
		<script>
			mini.parse();
	        var iframe = document.getElementById("mainframe");
			
			function search(){
				var productName = mini.get("productName").getValue();
				var tree = mini.get("leftTree");
				tree.load({productName:productName,para:1});
				tree.expandAll();
			}
			
	        function onNodeSelect(e) {
	            var tree = mini.get("leftTree");
	        	var node = e.node;
	        	
	        	var orderId = "";//订单号
	        	var productId = "";
	        	var isLeaf = e.isLeaf?1:0;
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
	        		
	        		iframe.src = "BaseServlet?meth=GoCraftMainBody&pathTo=mainBody&orderId="+node.orderId+"&productId="+node.id+"&FProductId="+node.pid+"&isLeaf="+isLeaf+"&issueNum="+node.issueNum+"&drawingId="+node.drawingId;
	        		//mini.alert("请选择具体订单项！");
		        }
	            
	            if (isLeaf) {
	            	//iframe.src = node.url;
	            	//iframe.src = "OrderDetailSpecServlet?orderId="+node.orderId+"&productId="+node.id;
	            }   
	        }
			
	        function refresh(){
				var tree = mini.get("leftTree");
				alert(tree.getParentField());
				tree.load();
		    }
		</script>
	</body>
</html>
