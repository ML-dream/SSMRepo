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
   
    <title>报价树结构</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
	<body style="height: 100%;">
		<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
		    <div showHeader="false" region="west" width="180" maxWidth="250" minWidth="100" >
		    	<a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()" >刷新</a>
		        <ul id="leftTree" class="mini-tree" url="GetOrderTreeServlet?orderStatusFrom=<%=OrderStatus.NEWORDER%>&orderStatusTo=<%=OrderStatus.PASS%>" style="width:100%;height:100%;" 
                    showTreeIcon="true" textField="text" idField="id" resultAsTree="false"  
                    onnodeselect="onNodeSelect"  >        
                </ul>
		    </div>
		    <div title="center" region="center" bodyStyle="overflow:hidden;">
		        <iframe id="mainframe" frameborder="0" name="main" style="width:100%;height:100%;" border="0"></iframe>
		    </div>
		</div>
		<script>
			mini.parse();
	        var iframe = document.getElementById("mainframe");
	
	        function onNodeSelect(e) {
	            var tree = mini.get("leftTree");
	        	var node = e.node;
	        	
	        	var orderId = "";		//订单号
	        	var productId = "";
	        	var fproductId = "";
	        	if(node.orderId==null){
	        		var rootNode = tree.getAncestors(node);
	        		orderId = rootNode[0].id;
	        		productId = node.id;
	        		fproductId = rootNode[rootNode.length-1].id;
		        }	
	        	if(node.level==1){			//订单层
	        		//mini.alert("请选择产品或者零部件！！");
	        		iframe.src = "GoQuotationTotalServlet?orderId="+node.id;
	        		//iframe.src = "GoQuotationAllDetailServlet?orderId="+node.id;
		        }
	        	if(node.level==2){			//零件层
	        		var rootNode = tree.getAncestors(node);
	        		fproductId = rootNode[rootNode.length-1].id;
	        		iframe.src = "GoQuotationProductServlet?orderId="+node.orderId+"&productId="+node.id+"&fproductId="+fproductId+"&name="+node.text;
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
