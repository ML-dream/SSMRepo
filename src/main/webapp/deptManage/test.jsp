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
   
    <title>订单树结构</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
	<body style="height: 100%;">
		
	<!-- 	<div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0;">
            <div size="180" maxSize="250" minSize="100" showCollapseButton="true" style="border-width:1px;">
                <ul id="leftTree" class="mini-tree" url="GetOrderTreeServlet" style="width:100%;height:100%;" 
                    showTreeIcon="true" textField="text" idField="id" resultAsTree="false"  
                    onnodeselect="onNodeSelect"  >        
                </ul>
            </div>
		</div>
		 -->
        <input class="mini-hidden" id="isModify" name="isModify" value="是否违反微服务"/>
		<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
		    <div showHeader="false" region="west" width="200" maxWidth="250" minWidth="100" >
	    
		        <ul id="leftTree" class="mini-tree" url="GetOrderTreeServlet?orderStatusFrom=<%=OrderStatus.NEWORDER%>&orderStatusTo=<%=OrderStatus.PASS%>" style="width:100%;height:100%;" 
                    showTreeIcon="true" textField="text" idField="id" resultAsTree="false"  
                    onnodeselect="onNodeSelect"  >        
                </ul>
		    </div>
		    <div title="center" region="center" bodyStyle="overflow:hidden;">
		        <iframe id="mainframe" frameborder="0" name="main" style="width:100%;height:100%;" border="0"></iframe>
		    </div>
		</div>
		
		<!-- 
		<div class="mini-fit">
			<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
			    <div showHeader="false" region="west" width="180" maxWidth="250" minWidth="100" >
			        <div id="leftTree" class="mini-outlookmenu" url="GetOrderTreeServlet" onitemselect="onItemSelect"
			            idField="id" parentField="pid" textField="text" borderStyle="border:0"  >
			        </div>
			    </div>
			    <div title="center" region="center" bodyStyle="overflow:hidden;">
			        <iframe id="mainframe" frameborder="0" name="main" style="width:100%;height:100%;" border="0"></iframe>
			    </div>
			</div>
		</div>
		 -->
		
		<script>
			mini.parse();
			var isModify=mini.get("isModify").getValue();
			var grid = mini.get("leftTree");
			if(isModify=="1"){
			    grid.setUrl("ModifyOrderTreeServlet");
                grid.load();
            }
            else
	         grid.load();
	        //init iframe src
	        var iframe = document.getElementById("mainframe");
	        //iframe.src = "orderManage/AddOrderDetail.jsp"
	        
	
	    function search() {
        var searchDate = mini.get("searchDate").getFormValue();
        key={"searchDate":searchDate};
        grid.load(key);
        }
        $("#searchDate").bind("keydown", function (e) {
            if (e.keyCode == 13) {
                search();
            }
        });
	
	        function onNodeSelect(e) {
	            //var item = e.item;
	            //iframe.src = item.url;
	            var tree = mini.get("leftTree");
	        	var node = e.node;
	        	var isModify=mini.get("isModify").getValue();

	        	var orderId = "";//订单号
	        	if(node.orderId==null){
	        		var rootNode = tree.getAncestors(node);
	        		orderId = rootNode[0].id;
		        }
	        	if(node.level==1){			//订单层
	        		iframe.src = "OrderSpecServlet?orderId=" + node.id+"&productId="+node.id+"&isModify"+isModify;
		        }
	        	if(node.level==2){			//零件层
	        		iframe.src = "OrderDetailSpecServlet?orderId="+node.orderId+"&productId="+node.id+"&isModify="+isModify;
		        }
	        	if(node.level==3){			//物料层
	        		iframe.src = "ItemSpecServlet?orderId="+orderId+"&itemId="+node.id;
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