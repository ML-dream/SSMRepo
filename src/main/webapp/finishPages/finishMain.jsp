<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    <title>完工总界面</title>
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
			<!-- 
			<div class="header" region="north" height="60" showSplit="false" showHeader="false" style="background-color: #D9E7F8">
				<h1 style="margin: 0; padding: 10px; cursor: default; font-family: 'Trebuchet MS', Arial, sans-serif;" align="center">
					南京航空航天大学
				</h1>
				
				<div style="position:absolute;top:18px;right:10px;">
		            <a class="mini-button mini-button-iconTop" iconCls="icon-add" onclick="onQuickClick" plain="true">快捷</a>    
		            <a class="mini-button mini-button-iconTop" iconCls="icon-edit" onclick="onClick"  plain="true" >首页</a>        
		            <a class="mini-button mini-button-iconTop" iconCls="icon-date" onclick="onClick"  plain="true" >消息</a>        
		            <a class="mini-button mini-button-iconTop" iconCls="icon-edit" onclick="onClick"  plain="true" >设置</a>        
		            <a class="mini-button mini-button-iconTop" iconCls="icon-close" onclick="onClick"  plain="true" >关闭</a>        
        		</div>
        		 
			</div>
			-->
			<div title="south" region="south" showSplit="false"
				showHeader="false" height="30">
				<div style="line-height: 28px; text-align: center; cursor: default">
					Copyright © 南京航空航天大学版权所有
				</div>
			</div>
			<div showHeader="false" region="west" width="180" maxWidth="250"
				minWidth="100">
				<!--OutlookMenu-->
				<div id="leftTree" class="mini-tree" url="<%=basePath%>demo/data/TreeService.jsp?method=LoadTreeFinish"
					onnodeclick="onNodeSelect" idField="id" parentField="pid" textField="id" borderStyle="border:0"
					showTreeIcon="true" resultAsTree="false" expandOnLoad="false"
					allowSelect="true">
				</div>
			</div>
			
			
			<div showCollapseButton="false" style="border: 0;">
			<!--Tabs-->
			<div id="mainTabs" class="mini-tabs" activeIndex="0"
				style="width: 100%; height: 100%;" plain="true"
				onactivechanged="onTabsActiveChanged">
				<div title="首页" url="<%=basePath %>finishPages/tongjiMain.jsp">
				</div>
			</div>
		</div>

		</div>

		<script type="text/javascript">
			mini.parse();
		
			var tree = mini.get("leftTree");
		
			function onItemSelect(e) {
	            var item = e.item;
	            var isLeaf = e.isLeaf;
				showTab(item);
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
						tab.url = "<%=basePath%>TongjiServlet?itemId="+ node.id + "&processId=" + node.id;
						
						tabs.addTab(tab);
					}
				}else{
					if (!tab) {
						tab = {};
						tab._nodeid = node.id;
						tab.name = id;
						//tab.title = node.text;
						tab.title = node.pid + node.id;
						tab.showCloseButton = true;
						tab.url = "<%=basePath%>TongjiDetailServlet?itemId="+ node.pid + "&processId=" + node.id;
						
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
