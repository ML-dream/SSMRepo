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
	<body style="height: 100%;">
		<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
		    <div showHeader="false" region="west" width="180" maxWidth="250" minWidth="100" >
		    	<a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()" >刷新</a>
		    	
		        <ul id="leftTree" class="mini-tree" style="width:100%;height:95%;"
		        	url="GetOrderTreeServlet1?orderStatusFrom=<%=OrderStatus.PASS%>&orderStatusTo=<%=OrderStatus.DELIVERING%>&para=partplan" 
                    showTreeIcon="true" textField="text" idField="id" resultAsTree="false"  
                    
                   showCheckBox="true" checkRecursive="false" showFolderCheckBox="flase"
        			onbeforenodecheck="onBeforeNodeCheck" allowSelect="true" enableHotTrack="true"
                    
                    contextMenu="#treeMenu" expandOnLoad="true" onnodeclick="onNodeSelect" 
                    ondrawnode="onDrawNode">        
                </ul>
                
		    </div>
		    <div title="center" bodyStyle="overflow:hidden;">
		        <iframe id="mainDetail" frameborder="0" name="mainDetail" style="width:100%;height:100%;" border="0"></iframe>
		    </div>
		<!--    这个div的时间选择根本就没有用 ，在后面有mini.open  又打开的另外的网页 -->
		<div id="timeSelector" name="timeSelector" style="display:none;">
	    		<input id="hour" name="hour" class="mini-spinner" minValue="0" maxValue="100" value="2"/>小时
   				<input id="minite" name="minite" class="mini-spinner" minValue="0" maxValue="59" value="0"/>分钟
   				<div class="mini-toolbar" style="text-align:center;padding-top:8px;padding-bottom:8px;" 
        			borderStyle="border-left:0;border-bottom:0;border-right:0;">
        			<a class="mini-button" style="width:60px;" onclick="onOk()">确定</a>
        			<span style="display:inline-block;width:25px;"></span>
        			<a class="mini-button" style="width:60px;" onclick="onCancel()">取消</a>  
    			</div>
	    	</div>
		</div>
		<div>
	    	<ul id="treeMenu" class="mini-contextmenu" onbeforeopen="onBeforeOpen">     
	    		<li iconCls="icon-node" onclick="listDetail">查看详情</li>
	    		<li iconCls="icon-filter" onclick="getTime">设置工序间隔时间</li>
   				<li iconCls="icon-add" onclick="doPartsPlan">批量制定 零件计划</li>
   				<li iconCls="icon-node" onclick="seePartsPlan">查看零件计划</li>
   				
   				
   				<li class="separator"></li>
   				
   				<!--
   				<li>
					<span iconCls="icon-add">新增节点</span>
					<ul>
	    				<li onclick="onAddBefore">插入节点前</li>                
           				<li onclick="onAddAfter">插入节点后</li>	
						<li onclick="onAddNode">插入子节点</li>	             
					</ul>
				</li>
				<li name="edit" iconCls="icon-edit" onclick="onEditNode">编辑节点</li>
				<li name="remove" iconCls="icon-remove" onclick="onRemoveNode">删除节点</li>   
				-->     
			</ul>
	    </div>
	    <input class="mini-hidden" id="hour" name="hour">
        <input class="mini-hidden" id="hour" name="hour">
	    <input class="mini-hidden" id="minite" name="minite">
		<script>
			mini.parse();
	        
	
	        function onNodeSelect(e) {
	        	var iframe = document.getElementById("mainDetail");
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
	        		iframe.src = "PartPlanBaseServlet?meth=GoMainBody&pathTo=mainBody&orderId="+node.orderId+"&productId="+node.id+"&FProductId="+node.pid+"&issueNum="+node.issueNum+"&drawingId="+node.drawingId;
	        		//mini.alert("请选择具体订单项！");
		        }
	            
	            if (isLeaf) {
	            	//iframe.src = node.url;
	            	//iframe.src = "OrderDetailSpecServlet?orderId="+node.orderId+"&productId="+node.id;
	            }   
	        }

	        function refresh(){
				var tree = mini.get("leftTree");
				tree.load();
		    }
		    
		    function onBeforeNodeCheck(e) {
            	var tree = e.sender;
            	var node = e.node;
            	if (tree.hasChildren(node)) {
                	//e.cancel = true;
            	}
        	}
        	
        	function getTime(e){
        		var hour = mini.get("hour");
        		var minite = mini.get("minite");
    			mini.open({
                	url: "<%=path %>/partPlanManage/dateTimeSelectedPage.jsp",
                	showMaxButton: false,
                	title: "选择时间间隔小界面",
                	width: 350,
                	height: 350,
                	ondestroy: function (action) {                    
                    	if (action == "ok") {
                        	var iframe = this.getIFrameEl();/* 不明白为什么getdata怎么来的 另外 if什么鬼啊*/
                        	var data = iframe.contentWindow. GetData(); 
                        	data = mini.clone(data);
                        	if (data) {
                            	hour.setValue(data.hour);
                            	minite.setValue(data.minite);
                        	}
                    	}
                	}
            	});
        	}
        	
        	
        	function listDetail(e){
        		var iframe = document.getElementById("mainDetail");
        		var tree = mini.get("leftTree");
    			var nodes = new Array();
    			nodes = getCheckedNodes(tree);
    			
    			var params = getCheckedNodesJson(nodes);
    			var data = [];
    			var j=0;
    			for(var i=0,len=nodes.length;i<len;i++){
    				var node = nodes[i];
    				var orderId = node.orderId;
    				var productId = node.id;
    				var issueNum = node.issueNum;
    				var productName = node.text;
    				var drawingId = node.drawingId;
    				var subdata = "{'orderId':'"+orderId+"','productId':'"+productId+"','issueNum':'"+issueNum+"','productName':'"+productName+"','drawingId':'"+drawingId+"'}";
    				data[j] = subdata;
    				j++;
    			}
    			
    			data = JSON.stringify(data);
    			params = {'data':data};
    			var url = 'PartPlanBaseServlet?meth=GoSeePlanDetail&pathTo=';
    			jQuery.post(url, params, function(data){
 	   					iframe.src = '<%=path %>/partPlanManage/SeePlanDetail.jsp';
 	   				});    			
        	}
        	
        	
        	function doPartsPlan(e){
        		var iframe = document.getElementById("mainDetail");
        		var tree = mini.get("leftTree");
    			var nodes = new Array();
    			nodes = getCheckedNodes(tree);
    			
    			var hour = mini.get("hour").getValue();
        		var minite = mini.get("minite").getValue();
        		
        		if(null==hour){
        			hour = 1;
        		}
        		if(minite==hour){
        			minite = 0;
        		}
    			
    			if(nodes.length>0){
    				for(var i=0;i<nodes.length;i++){
    					if(nodes[i].productStatus>=<%=ProductStatus.PARTPLANING%>){
    						alert(nodes[i].text+"   已经做过计划了，请重新选择!");
    						return;
    					}
    					if('0'==nodes[i].isGongYi){
    						alert(nodes[i].text+"   没有做工艺，请确认工艺!");
    						return;
            			}
            			if('1'==nodes[i].isCaiGou){
            				alert(nodes[i].text+"   是采购件，请重新选择!");
    						return;
            			}
//            			if('1'==nodes[i].isWaiXie){
//            				alert(nodes[i].text+"   是外协件，请重新选择!");
//    						return;
//            			}
        			}
    			}
    			
    			var params = getCheckedNodesJson(nodes);
    			var data = [];
    			var j=0;
    			for(var i=0,len=nodes.length;i<len;i++){
    				var node = nodes[i];
    				var orderId = node.orderId;
    				var productId = node.id;
    				var issueNum = node.issueNum;
    				var productName = node.text;
    				var drawingId = node.drawingId;
    				var sortie = hour+","+minite;
    				var subdata = "{'orderId':'"+orderId+"','productId':'"+productId+"','issueNum':'"+issueNum+"','productName':'"+productName+"','sortie':'"+sortie+"','drawingId':'"+drawingId+"'}";
    				data[j] = subdata;
    				//alert(data[j]);
    				j++;
    			}
    			
    			//params = {'data':encodeURIComponent(params)};
    			data = JSON.stringify(data);
    			params = {'data':data};
    			
    			var url = 'PartPlanBaseServlet?meth=AddpartsPlan&pathTo=';
    			jQuery.post(url, params, function(data){
 	   				tree.load();
 	   				/*
 	   				mini.confirm(data.result, "确定？",
                	function (action){            
                    	if (action == "ok") {
                    	//	iframe.src = window.location.href;
                    		//alert("操作成功 ");	
                    		tree.reload();
                    	}
                	});
                	*/
 	   			},'json');
        	}
        	
        	
        	function seePartsPlan(){
        		var iframe = document.getElementById("mainDetail");
        		var tree = mini.get("leftTree");
    			var nodes = new Array();
    			nodes = getCheckedNodes(tree);
    			
    			if(nodes.length>0){
    				for(var i=0;i<nodes.length;i++){
    					if(nodes[i].productStatus<<%=ProductStatus.PARTPLANING%>){
    						alert(nodes[i].text+"   还没有制定计划，请重新选择!");
    						return;
    					}
        			}
    			}   
  			
    			var params = getCheckedNodesJson(nodes);
    			var data = [];
    			var j=0;
    			for(var i=0,len=nodes.length;i<len;i++){
    				var node = nodes[i];
    				var orderId = node.orderId;
    				var productId = node.id;
    				var issueNum = node.issueNum;
    				var productName = node.text;
    				var drawingId = node.drawingId;
    				var subdata = "{'orderId':'"+orderId+"','productId':'"+productId+"','issueNum':'"+issueNum+"','productName':'"+productName+"','drawingId':'"+drawingId+"'}";
    				data[j] = subdata;
    				j++;
    			}
    			
    			data = JSON.stringify(data);
    			params = {'data':data};
    			
    			var pageTO = '';
    			if(nodes.length==1){
    				pageTO = 'partProGT.jsp';
    			}else{
    				pageTO = 'partGT.jsp';
    			}
    			
    			var url = 'PartPlanBaseServlet?meth=GoPartsPlanGandT&pathTo=';
    			jQuery.post(url, params, function(data){
    				iframe.src = '<%=path %>/partPlanManage/'+pageTO;
 	   			},'json');
        	}
        	
        	
        	function getCheckedNodes(tree){
        		var nodesWL = tree.getList(); 
    			var checkedNodes = new Array();
    			var j=0;
    			for(var i=0;i<nodesWL.length;i++){
    				var node = nodesWL[i];
    				if(node.checked){
    					checkedNodes[j]=node;
    					j++;
    				}
    			}
    			return checkedNodes;
        	}
        	
        	function getCheckedNodesJson(nodes){
        		return JSON.stringify(nodes);
        	}
        	
        	function onBeforeOpen(e) {
        		var menu = e.sender;
    			var tree = mini.get("leftTree");
    			var nodes = getCheckedNodes(tree);
    			
        		
        		///////////没有选择节点，则不弹出右键框
    			if (!nodes.length>0) {
        			e.cancel = true;
        			return;
    			}
    			
    			
        		/*
        		var menu = e.sender;
    			var tree = mini.get("leftTree");
    			
    			var nodes = getCheckedNodes(tree);
    			
        		var nodes = tree.getCheckedNodes(false);
        		for(var i=0;i<nodes.length;i++){
        			alert(nodes.length);
        		}
        		
        		var menu = e.sender;
    			var tree = mini.get("leftTree");
    			
    			var nodes = tree.getCheckedNodes(false);
    			if (!nodes) {
        			e.cancel = true;
        			return;
    			}
    			
    			if (nodes) {
        			e.cancel = true;
        			//阻止浏览器默认右键菜单
        			e.htmlEvent.preventDefault();
        			return;
    			}
    			
    			var editItem = mini.getbyName("edit", menu);
    			var removeItem = mini.getbyName("remove", menu);
    			editItem.show();
    			removeItem.enable();

    			if (node.id == "forms") {
        			editItem.hide();
    			}
    			if (node.id == "lists") {
        			removeItem.disable();
    			}
    			*/
        		/*
    			var menu = e.sender;
    			var tree = mini.get("leftTree");

    			var node = tree.getSelectedNode();
    			if (!node) {
        			e.cancel = true;
        			return;
    			}
    			if (node && node.text == "Base") {
        			e.cancel = true;
        			//阻止浏览器默认右键菜单
        			e.htmlEvent.preventDefault();
        			return;
    			}

    			////////////////////////////////
    			var editItem = mini.getbyName("edit", menu);
    			var removeItem = mini.getbyName("remove", menu);
    			editItem.show();
    			removeItem.enable();

    			if (node.id == "forms") {
        			editItem.hide();
    			}
    			if (node.id == "lists") {
        			removeItem.disable();
    			}
    			*/
			}
			
			function onAddBefore(e) {
	            var tree = mini.get("tree1");
	            var node = tree.getSelectedNode();
	
	            var newNode = {};
	            tree.addNode(newNode, "before", node);
	        }
	        function onAddAfter(e) {
	            var tree = mini.get("tree1");
	            var node = tree.getSelectedNode();
	
	            var newNode = {};
	            tree.addNode(newNode, "after", node);
	        }
	        function onAddNode(e) {
	            var tree = mini.get("tree1");
	            var node = tree.getSelectedNode();
	
	            var newNode = {};
	            tree.addNode(newNode, "add", node);
	        }
	        function onEditNode(e) {
	            var tree = mini.get("tree1");
	            var node = tree.getSelectedNode();
	            
	            tree.beginEdit(node);            
	        }
	        function onRemoveNode(e) {
	            var tree = mini.get("tree1");
	            var node = tree.getSelectedNode();
	
	            if (node) {
	                if (confirm("确定删除选中节点?")) {
	                    tree.removeNode(node);
	                }
	            }
	        }
	        function onMoveNode(e) {
	            var tree = mini.get("tree1");
	            var node = tree.getSelectedNode();
	
	            alert("moveNode");
	        }
	        
	        function onDrawNode(e){
	        	var tree = e.sender;
            	var node = e.node;
            	
            	////////////订单层不显示复选框
            	if('1'==node.level){
            		e.showCheckBox = false;			//此处设置订单不显示复选框
            	}
            	
            	/////////////////已经做过计划的显示为红色
//            	if(node.productStatus>=<%=ProductStatus.PARTPLANING%>){
//            		e.nodeHtml = '<span style="color:red;" >'+node.text+'</span>';
            		//e.nodeStyle = "font-style:italic;";
            		//e.nodeCls = "blueColor";
//   	}
            	if('0'==node.isGongYi){
            		e.nodeHtml = '<span style="color:blue;" >'+node.text+'</span>';
            	}
            	if('1'==node.isCaiGou){
            		e.nodeHtml = '<span style="color:yellow;" >'+node.text+'</span>';
            	}
            	if('1'==node.isWaiXie){
            		e.nodeHtml = '<span style="color:green;" >'+node.text+'</span>';
            	}
            	 if(node.productStatus>=<%=ProductStatus.PARTPLANING%>){
            		e.nodeHtml = '<span style="color:red;" >'+node.text+'</span>';
	             }
	        }
	        
	        
		</script>
	</body>
</html>
