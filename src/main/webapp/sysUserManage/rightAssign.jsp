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
   
    <title>权限配置</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
	<body style="height: 100%;">
		<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
		    <div showHeader="false" region="west" width="230" maxWidth="250" minWidth="100" >
		    	</br>
		    	<a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()" >刷新</a>
		    	
		        <ul id="leftTree" class="mini-tree" style="width:100%;height:95%;"
		        	url="LoadAllMenu" 
                    showTreeIcon="true" textField="text" idField="id" resultAsTree="false"  
                    
                    showCheckBox="true" checkRecursive="false" showFolderCheckBox="false"
        			onbeforenodecheck="onBeforeNodeCheck" allowSelect="true" enableHotTrack="false"
                    
                    contextMenu="#treeMenu" expandOnLoad="false" onnodeclick="onNodeSelect" 
                    ondrawnode="onDrawNode">        
                </ul>
		    </div>
		    
		     <div showHeader="false" region="center" width="180" maxWidth="250" minWidth="100" >
		     	<form id = "form1">
			     	<table id= "table1">
			     		<tr>
				     		<td><label>被授予者</label></td>
		 					<td>
				 				<input id="emp" name="emp" class="mini-buttonedit" width="" 
				            		onbuttonclick="onButtonEdit" textName="emp" required="true" value="" text="" allowInput="false"/>
				 			</td>
				 			<td>
				 				<a id="empbutton" class="mini-button" iconCls="" onclick="empSearch()">查询</a>
				 			</td>
				 			
				<!--  			<td><label>授予者</label></td>
				 			<td>
				 				<input id="cankao" name="cankao" class="mini-buttonedit" width="" 
				            		onbuttonclick="onButtonEdit" textName="emp" required="false" value="" text="" allowInput="false"/>
				 			</td> -->
				 			<!-- <td>
				 				<a id="copyRight" class="mini-button" iconCls="" onclick="copyRight()">权限参考</a>
				 			</td> -->
			 			</tr>
			     	</table>
		     	</form>
		    	<a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()" >刷新</a>
		    	
		        <ul id="leftTree2" class="mini-tree" style="width:100%;height:95%;"
		        	url="" 
                    showTreeIcon="true" textField="text" idField="id" resultAsTree="false"  
                    
                    showCheckBox="true" checkRecursive="false" showFolderCheckBox="false"
        			onbeforenodecheck="onBeforeNodeCheck" allowSelect="true" enableHotTrack="false"
                    
                    contextMenu="#treeMenu2" expandOnLoad="true" onnodeclick="onNodeSelect2" 
                    ondrawnode="onDrawNode">        
                </ul>
		    </div>
		</div>
		<div>
	    	<ul id="treeMenu" class="mini-contextmenu" onbeforeopen="onBeforeOpen">     
	    		<li iconCls="icon-node" onclick="grant()">赋予权限</li>
			</ul>n
	    </div>
	    <div>
	    	<ul id="treeMenu2" class="mini-contextmenu" onbeforeopen="onBeforeOpen2">     
	    		<li iconCls="icon-node" onclick="deright()">删除权限</li>
			</ul>
	    </div>
		
		<script>
			mini.parse();
	        
			function copyRight(){
				var form = new mini.Form("form1");
		    	form.validate();
		        if (form.isValid() == false) {return;}
		         
	        	var staffcode = mini.get("emp").getValue();	//被授予人
	        	var tree = mini.get("leftTree");
	        	
	        	var cankao = mini.get("cankao").getValue();	//授予人
	        	//参数1 表示 权限复制
	        	var params = null;
	        	var url = 'GrantRights?para=1&staffcode='+staffcode+"&cankao="+cankao;
    			$.ajax({
		    		type: "post",
		    		url: url,
		    		cache: false,
		    		data: params,
		    		success: function (text){
						alert(text);
						refresh();
						empSearch();
		    		}
		    	})
			}
	        function onNodeSelect(e) {
	            var tree = mini.get("leftTree");
	        	var node = e.node;
	        	
	        	var isLeaf = e.isLeaf?1:0;
	        	if(node.level==1){			
	        		//iframe.src = "GoOrderPayedListServlet?orderId="+node.id;
	        		tree.expandNode ( node );
		        }
	        	if(node.level==2){	
	        		tree.checkNode ( node );		
		        }
	            /*
	            if (isLeaf) {
	            	tree.checkNode ( node );
	            } 
	            */  
	        }
	        function onNodeSelect2(e){
	        	var tree = mini.get("leftTree2");
	        	var node = e.node;
	        	
	        	var isLeaf = e.isLeaf?1:0;
	        	if(node.level==1){			
	        		tree.expandNode ( node );
		        }
	        	if(node.level==2){	
	        		tree.checkNode ( node );		
		        }
	            /*
	            if (isLeaf) {
	            	tree.checkNode ( node );
	            } 
	            */ 
	        }
	        function empSearch(){
	        //查找用户权限 
	        
	        	var form = new mini.Form("form1");
		    	form.validate();
		        if (form.isValid() == false) {return;}
		         
	        	var staffcode = mini.get("emp").getValue();
	        	var tree2 = mini.get("leftTree2");
                tree2.load("LoadPerMenu?staffcode="+staffcode);
	        }
	        function grant(){
	        //赋予权限 
	        	var form = new mini.Form("form1");
		    	form.validate();
		        if (form.isValid() == false) {return;}
		         
	        	var staffcode = mini.get("emp").getValue();
	        	var tree = mini.get("leftTree");
    			var nodes = new Array();
    			nodes = getCheckedNodes(tree);
    			var params = getCheckedNodesJson(nodes);
    			var data = [];
    			var j=0;
    			for(var i=0,len=nodes.length;i<len;i++){
    				var node = nodes[i];
    				var pageid = node.id;
    				var subdata = "{'staffcode':'"+staffcode+"','pageid':'"+pageid+"'}";
    				data[j] = subdata;
    				//alert(data[j]);
    				j++;
    			}
    			
    			//params = {'data':encodeURIComponent(params)};
    			data = JSON.stringify(data);
    			params = {'data':data};
    			
    			var url = 'GrantRights';
    			$.ajax({
		    		type: "post",
		    		url: url,
		    		cache: false,
		    		data: params,
		    		success: function (text){
						alert(text);
						refresh();
						empSearch();
		    		}
		    	})
	        }
	        function deright(){
	        	var form = new mini.Form("form1");
		    	form.validate();
		        if (form.isValid() == false) {return;}
		         
	        	var staffcode = mini.get("emp").getValue();
	        	var tree = mini.get("leftTree2");
    			var nodes = new Array();
    			nodes = getCheckedNodes(tree);
    			var params = getCheckedNodesJson(nodes);
    			var data = [];
    			var j=0;
    			for(var i=0,len=nodes.length;i<len;i++){
    				var node = nodes[i];
    				var pageid = node.id;
    				var subdata = "{'staffcode':'"+staffcode+"','pageid':'"+pageid+"'}";
    				data[j] = subdata;
    				//alert(data[j]);
    				j++;
    			}
    			
    			//params = {'data':encodeURIComponent(params)};
    			data = JSON.stringify(data);
    			params = {'data':data};
    			
    			var url = 'DeleteRights';
    			$.ajax({
		    		type: "post",
		    		url: url,
		    		cache: false,
		    		data: params,
		    		success: function (text){
						alert(text);
						empSearch();
		    		}
		    	})
	        }
			function onButtonEdit(e) {
		         var btnEdit = this;
		         mini.open({
		             url: "qualitycheck/selectEmployeeWindow.jsp",
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
		                         btnEdit.setValue(data.staff_code);
		                         btnEdit.setText(data.staff_name);
		                     }
		                 }
		             }
		         });
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
			function onBeforeOpen2(e) {
        		var menu = e.sender;
    			var tree = mini.get("leftTree2");
    			var nodes = getCheckedNodes(tree);
    			
        		
        		///////////没有选择节点，则不弹出右键框
    			if (!nodes.length>0) {
        			e.cancel = true;
        			return;
    			}
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
            	if(node.productStatus>=<%=ProductStatus.PARTPLANING%>){
            		e.nodeHtml = '<span style="color:red;" >'+node.text+'</span>';
            		//e.nodeStyle = "font-style:italic;";
            		//e.nodeCls = "blueColor";
            	}
            	if('0'==node.isGongYi){
            		e.nodeHtml = '<span style="color:blue;" >'+node.text+'</span>';
            	}
            	if('1'==node.isCaiGou){
            		e.nodeHtml = '<span style="color:yellow;" >'+node.text+'</span>';
            	}
            	if('1'==node.isWaiXie){
            		e.nodeHtml = '<span style="color:green;" >'+node.text+'</span>';
            	}
	        }
	        
	        
	        
		</script>
	</body>
</html>
