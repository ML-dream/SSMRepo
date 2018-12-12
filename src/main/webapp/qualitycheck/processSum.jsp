<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
   
    <title>订单交付树结构</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
	<body style="height: 100%;">
		<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
		    <div showHeader="false" region="west" width="250" maxWidth="250" minWidth="100" >
		    	<input id="productName"  name="productName" class="mini-textbox" width="200px" allowInput="true" 
		    		onvaluechanged="search()" emptyText="输入产品名称"/>
		    	<input id="isCheckIn" class="mini-combobox" style="" textField="text" valueField="id" 
   						 url="data/isCheckIn.txt" value="" showNullItem="false"  allowInput="false" onvaluechanged="search()"/>
		    	
		    	<a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()" >刷新</a>
		        <ul id="leftTree" class="mini-tree" url="ProcessOrderTree" style="width:100%;height:95%;" autoLoad="false"
                    showTreeIcon="true" textField="text" idField="id" resultAsTree="false"  expandOnLoad="false"
                    onnodeselect="onNodeSelect"  >        
                </ul>
		    </div>
		    <div title="center" region="center" bodyStyle="overflow:hidden;">
		        <iframe id="mainframe" frameborder="0" name="main" style="width:100%;height:100%;" src="qualitycheck/ordersTempo.jsp" border="0"></iframe>
		    </div>
		</div>
		<script>
			mini.parse();
	        var iframe = document.getElementById("mainframe");
			
			function search(){
				var productName = mini.get("productName").getValue();
				var isCheckIn = mini.get("isCheckIn").getValue();
				var tree = mini.get("leftTree");
				//tree.setUrl("ProcessOrderTree?para=1")
				tree.load({productName:productName,isCheckIn:isCheckIn});
				tree.expandAll();
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
	        		
		        }else{
		        	orderId = node.orderId;
		        }
		        
	        	if(node.level==1){			//订单层
	        		iframe.src = "ProductSum?orderId="+node.id;
		        }
	        	if(node.level==2){			//零件层
	        		iframe.src = "ProductSumSon?orderId="+orderId+"&productId="+node.id+"&issueNum="+node.issueNum+"&productName="+node.text+"&productNum="+node.ProductNum;
	        		//mini.alert("请选择具体订单项！");
		        }
	        	if(node.level==3){			//物料层
	        		iframe.src = "ProductSumSon?orderId="+orderId+"&productId="+node.id+"&issueNum="+node.issueNum+"&productName="+node.text+"&productNum="+node.ProductNum;
		       		//var rootNode = tree.getAncestors(node);
	        		//orderId = rootNode[0].id;
		       		//iframe.src = "ProductSum?productId="+node.id+"&orderId="+orderId;
		       		
		        }
	            var isLeaf = e.isLeaf;
	            if (isLeaf) {
	            	//iframe.src = node.url;
	            	//iframe.src = "OrderDetailSpecServlet?orderId="+node.orderId+"&productId="+node.id;
	            }
		            
	        }

	        function refresh(){
				var tree = mini.get("leftTree");
				search();
				//tree.load();
		    }
		</script>
	</body>
</html>
