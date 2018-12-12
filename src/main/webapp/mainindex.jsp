<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>主页面</title>
   	<meta http-equiv="co ntent-type" content="text/html; charset=UTF-8" />
	<link href="css/demo.css" rel="stylesheet" type="text/css" />
	<script src="scripts/boot.js" type="text/javascript"></script>
	<link href="scripts/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css"/>

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
			<div class="header" region="north"  showSplit="false" showHeader="false" style="background-color: #D9E7F8">
				
				<h1 style="margin: 0; padding: 10px; cursor: default; font-family: 'Trebuchet MS', Arial, sans-serif;text-align: center;overflow: hidden;">
					L&nbsp;A&nbsp;B&nbsp;_&nbsp;M&nbsp;E&nbsp;S&nbsp;系&nbsp;统
				</h1>
				
				<div style="position:absolute;top:10px;right:10px;">   
		            <a class="mini-button mini-button-iconTop" iconCls="icon-node" onclick="toMainIndex()"  plain="true" >首页</a> 
		            <a class="mini-button mini-button-iconTop" iconCls="icon-edit"  href="EditSysUsersServlet?para=0" plain="true" >修改密码</a>          
		            <a class="mini-button mini-button-iconTop" iconCls="icon-remove" onclick="" href="ToLogOut" plain="true" >注销</a><!-- onlick 没什么用 -->
                    <a class="mini-button mini-button-iconTop" iconCls="icon-remove" onclick="ToStep" href="" plain="true" >跳转</a>       
        		</div>
        		
        		<ul id="menu1" class="mini-menubar" style="width:80%;height=30;margin-left:1%;" borderStyle="border:0"
		            url="LoadMainMenu" onitemclick="onItemClick" imgPath="imgs/"
		            textField="text" idField="id" parentField="pid"  
		        >
		    	</ul>                <!--  总热言之  该处和下面的lefttree都是事实现 的是 分别展开相应的选项 -->
        		
			</div>
			
			<div title="south" region="south" showSplit="false"
				showHeader="false" height="30">
				<div style="line-height: 28px; text-align: center; cursor: default">
					Copyright © 南京航空航天大学版权所有
				</div>
			</div>
			
			<div showHeader="false" region="west" width="180" maxWidth="250"  showHeader="true" title="目录"
				minWidth="100">
				<!--OutlookMenu-->
				<div id="leftTree" class="mini-outlookmenu" url="LoadMainMenu"
					onitemclick="onItemSelect" idField="id" parentField="pid" imgPath="imgs/"
					textField="text" borderStyle="border:0">
				</div>
			</div>
			
			<!-- 下面这个是页面中间的那个页面 -->
			<div showCollapseButton="false" style="border: 0;">
			<!--Tabs-->
			<div id="mainTabs" class="mini-tabs" activeIndex="0"
				style="width: 100%; height: 100%;" plain="true"
				onactivechanged="onTabsActiveChanged">
				<div title="首页" url="waitDoPage.jsp">
				</div>
			</div>
		</div>

		</div>

		

		<script type="text/javascript">
			mini.parse();//获得html的控件对象
			var tree = mini.get("leftTree");//得到lefttree的对象
			
			function onItemSelect(e) {
	            var item = e.item;
	            var isLeaf = e.isLeaf;
				showTab(item);
	        }
			
			function showTab(node) {
				var tabs = mini.get("mainTabs");
		
				var id = "tab$" + node.id;
				var tab = tabs.getTab(id);
				if (!tab) {
					tab = {};
					tab._nodeid = node.id;
					tab.name = id;
					tab.title = node.text;
					tab.showCloseButton = true;
					//这里拼接了url，实际项目，应该从后台直接获得完整的url地址
					//tab.url = mini_JSPath + "../../docs/api/" + node.url;
					tab.url = node.url;
					tabs.addTab(tab);
				}else {												//else  中代码也可以不写
		            tab.title = node.text;
		            tab.url = node.url;
		            tab.showCloseButton = true;
		            tab.refreshOnClick = false;						//再次加载点击标签页不再刷新
		            tabs.reloadTab(tab);
		            tabs.updateTab(tab);                            //这里是判断标签是否已经存在，如果存在则刷新此标签
		        }
				tabs.activeTab(tab);
			} 
		
			function onNodeSelect(e) {
				var node = e.node;
				var isLeaf = e.isLeaf;
		
				if (isLeaf) {
					showTab(node);
				}
			}

			function onItemClick(e){
				var item = e.item;//获得当前项
		        var isLeaf = e.isLeaf;//判断是否是子叶点

		        if (isLeaf) {
		            showTab(item);
		        } 
			}
		
			function onClick(e) {
				var text = this.getText();
				alert(text);
			}
			function onQuickClick(e) {
				tree.expandPath("datagrid");
				tree.selectNode("datagrid");
			}
		
			/* function onTabsActiveChanged(e) {
				var tabs = e.sender;
				var tab = tabs.getActiveTab();
				if (tab && tab._nodeid) {
		
					var node = tree.getNode(tab._nodeid);
					if (node && !tree.isSelectedNode(node)) {
						tree.selectNode(node);
					}
				}
			} */
			
			function toMainIndex(){
				window.location.href="mainindex.jsp";
			}
			function toLogOut(){
				window.location.href="logOut.jsp";
				/* $.ajax({
					type: "post",
	        		url:ToLogOut,
	        		cache: false,
	        		processData: false,
	    			contentType: false,
	    			success:function(text){
	    				
	    			},
	    			error : function() {
					}
				}) */
			}
			function ToStep(){
				window.location.href="http://localhost:8080/work/WebRoot/qualitycheck/costConfirm.jsp";
			}
		</script>
	</body>
</html>
