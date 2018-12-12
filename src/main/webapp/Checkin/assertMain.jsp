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
   
    <title>客户资产入库</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
	<body style="height: 100%;">
		<!-- 
		<div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0;">
            <div size="180" maxSize="250" minSize="100" showCollapseButton="true" style="border-width:1px;">
                <ul id="leftTree" class="mini-tree" url="GetOrderTreeServlet" style="width:100%;height:100%;" 
                    showTreeIcon="true" textField="text" idField="id" resultAsTree="false"  
                    onnodeselect="onNodeSelect"  >        
                </ul>
            </div>
		</div>
		 -->

		<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
		    <div showHeader="false" region="west" width="200" maxWidth="250" minWidth="100" >
	    <!--<a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()" >刷新</a> 
          --><input class="mini-datepicker" id="searchDate" name="searchDate" width="60%" dateFormat="yyyy-mm-dd" />  
              <input type="button" value="查找/刷新" onclick="search()"/> 
		        <ul id="leftTree" class="mini-tree" url="GetOrderTreeServlet1?orderStatusFrom=<%=OrderStatus.PASS%>&orderStatusTo=<%=OrderStatus.DELIVERED%>" style="width:100%;height:100%;" 
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
			var grid = mini.get("leftTree");
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

	        	var orderId = "";//订单号
	        	if(node.orderId==null){
	        		var rootNode = tree.getAncestors(node);
	        		orderId = rootNode[0].id;
		        }
//	        	alert(rootNode[0].id);		
	        	if(node.level==1){			//订单层
	        		iframe.src = "OrderSpecServlet?orderId=" + node.id+"&productId="+node.id+"&para=assert";
		        }
	        	if(node.level==2){			//零件层
	        		//iframe.src = "OrderDetailSpecServlet?orderId="+node.orderId+"&productId="+node.id;
		        }
	        	if(node.level==3){			//物料层
	        		//iframe.src = "ItemSpecServlet?orderId="+orderId+"&itemId="+node.id;
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