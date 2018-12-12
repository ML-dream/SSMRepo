<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <title>派工总界面</title>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<link href="demo.css" rel="stylesheet" type="text/css" />


		<script src="<%=basePath%>scripts/boot.js" type="text/javascript"></script>
		<!-- 
		<link href="<%=basePath%>scripts/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css"/>

		 -->
		<style type="text/css">
		body {
			margin: 0;
			padding: 0;
			border: 0;
			width: 100%;
			height: 100%;
			overflow: hidden;
		}
		
		.header {
			background: url(../header.gif) repeat-x 0 -1px;
		}
		
		.Note {
			background: url(Notes_Large.png) no-repeat;
			width: 32px;
			height: 32px;
		}
		
		.Reports {
			background: url(Reports_Large.png) no-repeat;
			width: 32px;
			height: 32px;
		}
		</style>

  </head>
  
  <body style="background-color: #EBEFF5;">

		<div id="layout1" class="mini-layout" style="width: 100%; height: 100%;">
		
			<div title="south" region="south" showSplit="false"
				showHeader="false" height="30">
				<div style="line-height: 28px; text-align: center; cursor: default">
					Copyright © 南京航空航天大学版权所有
				</div>
			</div>
			
			
			<div title="center" region="center" bodyStyle="overflow:hidden;" style="border:0;">
        <!--Splitter-->
        <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0;">
            <div size="180" maxSize="250" minSize="100" showCollapseButton="true" style="border-width:1px;">
            
                <!--Tree-->
                <ul id="leftTree" class="mini-tree" style="width:100%;height:100%;" 
                    showTreeIcon="true" textField="id" idField="id" resultAsTree="false" 
                    url="demo/data/TreeService.jsp?method=LoadTreePaigong"
                    allowSelect="true" enableHotTrack="false" expandOnLoad="false"  onnodeclick="onNodeSelect"
                >        
                </ul>
                
            </div>
            <div showCollapseButton="false" style="border:0px;" >
                <!--Tabs-->
                <div id="mainTabs" class="mini-tabs bg-toolbar" activeIndex="0" style="width:100%;height:100%;"      
                    onactivechanged="onTabsActiveChanged"
                >        
                    <div title="首页" url="<%=basePath %>dispatch/dispatchFirst.jsp" >        
                    </div>
                </div>
            </div>        
        </div>
    </div>
		</div>

		</div>

		<script type="text/javascript">
      //  
        mini.parse();
        var tree = mini.get("leftTree"); 

    	
        function onItemClick(e) {
		    var item = e.sender;
		    alert(item.getText());     
		}
		
		window.onload = function () {
		    $("#region1").bind("contextmenu", function (e) {
		        var menu = mini.get("leftTree");
		        menu.showAtPos(e.pageX, e.pageY);
		        return false;
		    });
		}
			
	
        function callback(data){
            mini.parse();
            var tree = mini.get("leftTree");            
            var dataObj=eval("("+data+")");
            //alert(typeof(dataObj)); 
            tree.loadList(dataObj,"id","pid");
        }

        function showTab(node) {
			var tabs = mini.get("mainTabs");
	
			var id = "tab$" + node.id;
			var tab = tabs.getTab(id);
			if(node.pid == "-1"){				//根节点
				if (!tab) {
					tab = {};
					tab._nodeid = node.id;
					tab.name = id;
					//tab.title = node.text;
					tab.title = node.pid + node.id;
					tab.showCloseButton = true;
					tab.url = "<%=basePath%>?flag=gen&orderId="+ node.id;
					tabs.addTab(tab);
				}
			}else{
				if (!tab) {						//叶子节点
					tab = {};
					parentNode = tree.getParentNode(node);
					tab._nodeid = node.id;
					tab.name = id;
					//tab.title = node.text;
					tab.title = node.pid + node.id;
					tab.showCloseButton = true;
					tab.url = "<%=basePath%>GoDispatchServlet?flag=ye&orderId="+ node.ORDERID+"&itemId="+node.pid+"&productId="+parentNode.PRODUCT_ID+"&operId="+node.id;
					
					
					tabs.addTab(tab);
				}
			}
			
			
			tabs.activeTab(tab);
		}
	
		function onNodeSelect(e) {
			var node = e.node;
			showTab(node);
			
		}

        function onClick(e) {
            var text = this.getText();
            alert(text);
        }
        function onQuickClick(e) {
            tree.expandPath("datagrid");
            tree.selectNode("datagrid");
        }

        function onTabsActiveChanged(e) {
            var tabs = e.sender;
            var tab = tabs.getActiveTab();
            if (tab && tab._nodeid) {

                var node = tree.getNode(tab._nodeid);
                if (node && !tree.isSelectedNode(node)) {
                    tree.selectNode(node);
                }
            }
        }
        
        
		
    </script>
	</body>
</html>
