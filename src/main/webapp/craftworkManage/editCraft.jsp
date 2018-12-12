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
   
    <title>工艺调整主界面</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
	<body style="height: 100%;">
		<div id="win1" class="mini-window" title="操作" style="width:550px;height:150px;" expanded="true" 
	    showMaxButton="true" showCollapseButton="true" showShadow="true" showCloseButton="true" collapseOnTitleClick="true"
	    showToolbar="true" showFooter="true" showModal="false" allowResize="true" allowDrag="true"
	    >
	
	  	<form id="form1">
		   	<table>
	   		<!-- 表头 -->
	   			<tr style= "height:20px;">
	   				<td style= "width:20px;"></td>
	 				<td>
	  					<input value="查找" type="button" style= "width:90px;" onclick="findProduct()" />
	 				</td>
	   			</tr>
	   	 	</table>
	   	 </form>
	   	 <table style="text-align: left;border-collapse:collapse;" border="gray 1px solid;"  width="100%" >
	   		<tr>
	   			<td>按订单时间</td>
				<td><label>开始</label></td>
	            <td >
					<input id="bdate" name ="bdate" class="mini-datepicker" value="" dateFormat="yyyy-MM-dd" allowInput="false"/></td>
				<td><label>结束</label></td>
	           <td><input id="edate" name ="edate" class="mini-datepicker" value="" dateFormat="yyyy-MM-dd" allowInput="false"/></td>
	  		</tr>
	   		<tr>
	   			<td>其他方式</td>
	   			<td><label>零件名称</label></td>
	            <td>
	            	<input id="productName2"  name="productName2" class="mini-textbox" width="" allowInput="true" width="100%"
		    	/></td>
	            </td>
	   			<td><label>图号</label></td>
	            <td><input id="productId" name="ProductId" class="mini-textbox" width="100%"  allowInput="true" onvaluechanged="findProduct"/></td>
	   		</tr>
	   			
	   	</table>
	  </div>
	  
		<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
		    <div showHeader="false" region="west" width="250" maxWidth="250" minWidth="100" >
		    	<input id="productName" name="productName" class="mini-textbox" width="200px"  allowInput="true" onvaluechanged="search()" emptyText="输入产品名称"/>
		    	<a class="mini-button" iconCls="icon-reload" plain="false" onclick="search()" >刷新</a>
		    	<a class="mini-button" iconCls="icon-search" plain="false" onclick="toSearch()" >查找</a>
		        <ul id="leftTree" class="mini-tree" style="width:100%;height:95%;" expandOnLoad="false" autoLoad="false"
		        	url="GetEditCraftTree?orderStatusFrom=<%=OrderStatus.PASS%>&orderStatusTo=<%=OrderStatus.DELIVERED%>" 
                    showTreeIcon="true" textField="text" idField="id" resultAsTree="false"  
                    onnodeselect="onNodeSelect"  onnodeclick="onNodeSelect">        
                </ul>
		    </div>
		    <div title="center" region="center" bodyStyle="overflow:hidden;">
		        <iframe id="mainframe" frameborder="0" name="main" style="width:100%;height:100%;" border="0"></iframe>
		    </div>
		</div>
		<script >
			mini.parse();
	        var iframe = document.getElementById("mainframe");
			
			function findProduct(){
				var tree = mini.get("leftTree");
				
				var bday = mini.get("bdate").getFormValue();
	    		var eday = mini.get("edate").getFormValue();
				var productName = mini.get("productName2").getValue();
				var productId = mini.get("productId").getValue();
				
				tree.load({bday:bday,eday:eday,productName:productName,productId:productId,para:1});
				tree.expandAll();
			}
			function search(){
				var productName = mini.get("productName").getValue();
				var tree = mini.get("leftTree");
				tree.load({productName:productName,para:1});
				tree.expandAll();
			}
			function showAtPos() {
		        var win = mini.get("win1");
		
		        var x = "right";
		        var y = "top";
		
		        win.showAtPos(x, y);
		    }
	        function toSearch(){
	        	var temp = mini.get("productName").getValue();
	        	mini.get("productName2").setValue(temp);
	        	showAtPos();
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
	        		// alert("请选择相应零部件!");
		        }
	        	if(node.level==2){			//零件层
		        	// iframe.src=
			    		// "BaseServlet?meth=GetFoHeader&pathTo=foHeader&orderId="+node.orderId+"&productId="+node.id+"&FProductId="+node.pid+"&isHere=1";
	        		
	        		iframe.src = "ToEditCraftDetail?orderId="+node.orderId+"&productId="+node.id+"&FProductId="+node.pid+"&isLeaf="+isLeaf+"&issueNum="+node.issueNum+"&drawingId="+node.drawingId;
		        }
	            
	            if (isLeaf) {
	            	//iframe.src = node.url;
	            	//iframe.src = "OrderDetailSpecServlet?orderId="+node.orderId+"&productId="+node.id;
	            }   
	        }
			
		</script>
	</body>
</html>
