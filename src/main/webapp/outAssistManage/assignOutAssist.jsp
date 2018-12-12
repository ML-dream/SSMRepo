<%@ page language="java"  import="java.util.*,com.wl.common.OrderStatus,com.wl.common.ProductStatus"  pageEncoding="utf-8"%>
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
		<script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>订单交付树结构</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
	<body style="height: 100%;">
		<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
		    <div showHeader="false" region="west" width="180" maxWidth="250" minWidth="100" >
		    <input class="mini-textbox" id="productName" name="productName" width="160px" emptyText="请输入产品名称" onenter="search()"/>
		    <input class="mini-comboBox"  id="isWaiXie" name="isWaiXie" width="160px" url="data/trueOrFalse.txt" emptyText="是否做过外协" onValuechanged="search()" />
		        <a class="mini-button" iconCls="icon-search" plain="false" onclick="search()"  >查询</a>
		    	<a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()" >刷新</a>
		        <ul id="leftTree" class="mini-tree" url="GetOrderTreeServlet2?orderStatusFrom=<%=OrderStatus.PASS%>&orderStatusTo=<%=OrderStatus.DOING%>"   style="width:100%;height:95%;" 
                    showTreeIcon="true" textField="text" idField="id" resultAsTree="false"  
                    onnodeselect="onNodeSelect"  ondrawnode="onDrawNode"  contextMenu="#treeMenu" 
                    showCheckBox="true"  showFolderCheckBox="true"
        			onbeforenodecheck="onBeforeNodeCheck" allowSelect="true" enableHotTrack="false"
                    onnodeclick="onNodeSelect" checkRecursive="true" expandOnLoad="false"  >        
                </ul>
		    </div>

		    <div title="center" region="center" bodyStyle="overflow:hidden;">
		        <iframe id="mainframe" frameborder="0" name="main" style="width:100%;height:100%;"  border="0"></iframe>
		    </div>   
		</div>
		<div>  
	    	<ul id="treeMenu" class="mini-contextmenu" onbeforeopen="onBeforeOpen">     
	    		<li iconCls="icon-node" onclick="outAssistCom">指定外协单位</li>
	    		<li iconCls="icon-node" onclick="outAssistMenu">生成外协单</li>

			</ul>
	    </div>
        <input class="mini-hidden" id="companyId" name="companyId"/>
	    <input class="mini-hidden" id="companyName" name="companyName"/>
		<script>
			mini.parse();
	        var iframe = document.getElementById("mainframe");
	        var tree = mini.get("leftTree");
            tree.load();
	
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
	        		alert("请选择产品");
		        }
	        	if(node.level==2){			//零件层
	        		iframe.src = "GoProcessOutAssistServlet?orderId="+node.orderId+"&productId="+node.productId+"&issueNum="+node.issueNum;
		        }
		        if(node.level==3){
		           iframe.src="ProcessOutAssistStatServlet?orderId="+node.orderId+"&productId="+node.productId+"&issueNum="+node.issueNum+"&operId="+node.operId;
		        }
		            
	        }
	        
//	        function outAssistList(){
//	          var tree = mini.get("leftTree");
//	          var node =tree.getSelectedNode();
//	          iframe.src="GoOutAssistListServlet?orderId="+node.id;
//	        }
	        
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
        	
        	function onBeforeNodeCheck(e) {
            	var tree = e.sender;
            	var node = e.node;
            	if (tree.hasChildren(node)) {
                	//e.cancel = true;
            	}
        	}
        	
        	function getCheckedNodesJson(nodes){
        		return JSON.stringify(nodes);
        	}
        	
        function outAssistCom(e){
            mini.open({
                url: "outAssistManage/selectOutAssistWindow.jsp",
                title: "选择列表",
                width: 650,
                height: 380,
                ondestroy: function (action) {
                    if (action == "ok") {
                        var iframe = this.getIFrameEl();
                        var data = iframe.contentWindow.GetData();
                        data = mini.clone(data);    //必须
                        if (data) {
                              	mini.get("companyId").setValue(data.companyId);
                            	mini.get("companyName").setValue(data.companyName);
  //                          	SaveOutAssistCom();
                        }
                    }

                }
            });
            
        }
        
 /*       function removeFromOutAssistMenu(){   
                var tree = mini.get("leftTree");
    	        var nodes = new Array();
    			nodes = getCheckedNodes(tree);   			
    			var params = getCheckedNodesJson(nodes);
    			var data = [];
    			var j=0;
    			for(var i=0,len=nodes.length;i<len;i++){
    			 var node = nodes[i];
    			   if(node.level!=3){
    			      continue;}
    			   else if(node.isMenu==0){
    			    alert(node.text+"尚未生成外协单");
    			    return; }
    			   else{
    				var orderId = node.orderId;
    				var productId = node.productId;
    				var issueNum = node.issueNum;
    				var operId = node.operId;
    				var subdata = "{'orderId':'"+orderId+"','productId':'"+productId+"','issueNum':'"+issueNum+"','operId':'"+operId+"','waiXieCom':'"+companyId+"'}";
    				data[j] = subdata;
    				j++;
    				}
    			}
              data = JSON.stringify(data);
    	      params = {'data':data};
    		  var url='RemoveFromOutAssistMenuServlet';
    		  jQuery.post(url, params, function(data){
   			     alert(data.result);},'json')
        }
*/
        
        
        
        function SaveOutAssistCom(){
         var companyId=mini.get("companyId").getValue();
         var tree = mini.get("leftTree");
    			var nodes = new Array();
    			nodes = getCheckedNodes(tree);   			
    			var params = getCheckedNodesJson(nodes);
    			var data = [];
    			var j=0;
    			for(var i=0,len=nodes.length;i<len;i++){
    			 var node = nodes[i];
    			   if(node.level!=3){
    			      continue;}
    			   else{
    				var orderId = node.orderId;
    				var productId = node.productId;
    				var issueNum = node.issueNum;
    				var operId = node.operId;
    				var subdata = "{'orderId':'"+orderId+"','productId':'"+productId+"','issueNum':'"+issueNum+"','operId':'"+operId+"','waiXieCom':'"+companyId+"'}";
    				data[j] = subdata;
    				j++;
    				}
    			}
    			data = JSON.stringify(data);
    			params = {'data':data};
    			var url='SaveSelectedOutAssistComServlet';
    			jQuery.post(url, params, function(data){
   			     alert(data.result);},'json')
    			
        }
        
        	function outAssistMenu(){
        	var companyName=mini.get("companyName").getValue();
        	var companyId=mini.get("companyId").getValue();
        	if(companyName==""||companyId=="")
        	{  alert("尚未指定外协商");
        	   return;}
        		var tree = mini.get("leftTree");
    			var nodes = new Array();
    			nodes = getCheckedNodes(tree);   			
    			var params = getCheckedNodesJson(nodes);
    			var data = [];
    			var j=0;
    			for(var i=0,len=nodes.length;i<len;i++){
    			 var node = nodes[i];
    			   if(node.level!=3){
    			      continue;}
    			   else if(node.isMenu==1){
    			    alert(node.text+"已生成外协单");
    			    return; }
    			   else{
    				var orderId = node.orderId;
    				var productId = node.productId;
    				var issueNum = node.issueNum;
    				var operId = node.operId;
    				var subdata = "{'orderId':'"+orderId+"','productId':'"+productId+"','issueNum':'"+issueNum+"','operId':'"+operId+"','waiXieCom':'"+companyId+"'}";
    				data[j] = subdata;
    				j++;
    				}
    			}
    			data = JSON.stringify(data);
    			params = {'data':data,'companyId':companyId};
    	        var url = 'GoAllOutAssistListServlet';
   			    jQuery.post(url, params, function(data){
   	                alert(data.result);
   	                refresh(); 
   	             },'json');  
   	                 				
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
    	    }

	       function refresh(){
				var tree = mini.get("leftTree");
				tree.load();
		    }
		    
		    function search(){
		    var tree=mini.get("leftTree");
		    var productName=mini.get("productName").getValue();
		    var isWaiXie=mini.get("isWaiXie").getValue();
		    var key={"productName":productName,"isWaiXie":isWaiXie};
		    tree.load(key);
		    tree.expandAll();
		    }
		    
		    function onDrawNode(e){
	        	var tree = e.sender;
            	var node = e.node;
                if('1'==node.level){
          		e.showCheckBox = false;			//此处设置订单不显示复选框
             	}
            	
            	/////////////////已经做过计划的显示为红色

            	if('1'==node.isMenu){
            		e.nodeHtml = '<span style="color:red;" >'+node.text+'</span>';
            	}
	        }
		</script>
	</body>
</html>
