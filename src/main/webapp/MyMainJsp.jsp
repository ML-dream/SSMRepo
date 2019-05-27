<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  <title>主页面</title>
    <base href="<%=basePath%>">
    <meta http-equiv="co ntent-type" content="text/html; charset=UTF-8"/>
    <script type="text/javascript" src="resources/scripts/boot.js"></script>
 	<link href="resources/res/third-party/scrollbar/jquery.mCustomScrollbar.css" rel="stylesheet" type="text/css" />
    <script src="resources/res/third-party/scrollbar/jquery.mCustomScrollbar.concat.min.js" type="text/javascript"></script>
    <link href="resources/frame1/res/menu/menu.css" rel="stylesheet" type="text/css" />
    <script src="resources/frame1/res/menu/menu.js" type="text/javascript"></script>
    <script src="resources/frame1/res/menutip.js" type="text/javascript"></script>
    <link href="resources/frame1/res/tabs.css" rel="stylesheet" type="text/css" />
    <link href="resources/frame1/res/frame.css" rel="stylesheet" type="text/css" />
    <link href="resources/frame1/res/index.css" rel="stylesheet" type="text/css" />


  </head>
  <body>
    
<div class="navbar">
    <div class="navbar-header">
        <div class="navbar-brand navbar-brand-compact">M</div>
    </div>
    
    <h1 style="margin: 0; padding: 10px; cursor: default; font-family: 'Trebuchet MS', Arial, sans-serif;text-align: center;overflow: hidden;">
					实&nbsp;验&nbsp;室&nbsp;智&nbsp;能&nbsp;制&nbsp;造&nbsp;MES&nbsp;系&nbsp;统
				</h1>
  
    <ul class="nav navbar-nav navbar-right">
        <!-- <li ><a href="#"><i class="fa fa-paper-plane"></i> 代办事项</a></li> -->
        <li ><a href="EditSysUsersServlet?para=0"><i class="fa fa-pencil-square-o"></i> 修改密码</a></li>
        <li class="dropdown">
            <a class="dropdown-toggle userinfo">
                <img class="user-img" src="resources/frame1/res/images/user.jpg" />个人资料<i class="fa fa-angle-down"></i>
            </a>
            <ul class="dropdown-menu pull-right">
                <li ><a href="sysUserManage/personalInfoManagement.jsp" ><i class="fa fa-eye"></i> 用户信息</a></li>
                <li ><a href="ToLogOut" ><i class="fa fa-user"></i> 退出登录</a></li>
            </ul>
        </li>
    </ul>
</div>

<div class="container">
    
    <div class="sidebar">
        <div class="sidebar-toggle"><i class = "fa fa-fw fa-dedent" ></i></div>
        <div id="mainMenu"></div>
    </div>

    <div class="main">
        <div id="mainTabs" class="mini-tabs main-tabs" activeIndex="0" style="height:100%;" plain="false"
             buttons="#tabsButtons" arrowPosition="side" >
            <div name="index" iconCls="fa-android" title="系统首页"  url="">
            
            
            </div>
        <!--     <div title="south" region="south" showSplit="false" showHeader="false" height="30">
				<div style="line-height: 28px; text-align: center; cursor: default">
					Copyright © 南京航空航天大学版权所有
				</div>
			</div> -->
        </div>
        <!-- <div id="tabsButtons">
            <a class="tabsBtn"><i class="fa fa-home"></i></a>
            <a class="tabsBtn"><i class="fa fa-refresh"></i></a>
            <a class="tabsBtn"><i class="fa fa-remove"></i></a>
            <a class="tabsBtn"><i class="fa fa-arrows-alt"></i></a>
        </div>    -->
    </div>
   
</div>

	

</body>

<script>


    function activeTab(item) {
        var tabs = mini.get("mainTabs");
        tabs.on("activechanged",function(e){
            tabs.reloadTab(e.tab);
        })
        var tab = tabs.getTab(item.id);
        if (!tab) {
            tab = { name: item.id, title: item.text, url: item.url, iconCls: item.iconCls, showCloseButton: true };
            tab = tabs.addTab(tab);
        }
        tabs.activeTab(tab);
        
    }

    $(function () {

        //menu
        var menu = new Menu("#mainMenu", {
            itemclick: function (item) {
                if (!item.children) {
                    activeTab(item);
                }
            }
        });
      /*   var tabs = mini.get("mainTabs");
        tabs.on("activechanged",function(e){
            tabs.reloadTab(e.tab);
        }) */

      /*   var tabs = mini.get("mainTabs");
        tabs.on("activechanged",function(e){
            tabs.reloadTab(e.tab);
        }) */
        
        $(".sidebar").mCustomScrollbar({ autoHideScrollbar: true });

        new MenuTip(menu);

        $.ajax({
            url: "LoadMainMenu",
            success: function (text) {
            	var data = mini.decode(text);
                if(data.result=="当前登陆用户信息失效，请重新登陆!"){
                	alert("当前登陆用户信息失效，请重新登陆!");
                	window.location.href="login.jsp";
                }else{
            	
                menu.loadData(data);
                }
            }
        })

        //toggle
        $("#toggle, .sidebar-toggle").click(function () {
            $('body').toggleClass('compact');
            mini.layout();
        });

        //dropdown
        $(".dropdown-toggle").click(function (event) {
            $(this).parent().addClass("open");
            return false;
        });

        $(document).click(function (event) {
            $(".dropdown").removeClass("open");
        });
    });
   

    function onClick1(){
    	window.location.href="sysUserManage/personalInfoManagement.jsp";
    }
    function onClick2(){
    	window.location.href="logOut.jsp";
    }
    function onClick3(){
    	window.location.href="EditSysUsersServlet?para=0";
    }

</script>






  <!-- <body style="background-color: #EBEFF5;">

		<div id="layout1" class="mini-layout" style="width: 100%; height: 100%;">
			<div class="header" region="north"  showSplit="false" showHeader="false" style="background-color: #D9E7F8">
				
				<h1 style="margin: 0; padding: 10px; cursor: default; font-family: 'Trebuchet MS', Arial, sans-serif;text-align: center;overflow: hidden;">
					实&nbsp;验&nbsp;室&nbsp;智&nbsp;能&nbsp;制&nbsp;造&nbsp;MES&nbsp;系&nbsp;统
				</h1>
				
				<div style="position:absolute;top:10px;right:10px;">   
		            <a class="mini-button mini-button-iconTop" iconCls="icon-node" onclick="toMainIndex()"  plain="true" >首页</a> 
		            <a class="mini-button mini-button-iconTop" iconCls="icon-edit"  href="EditSysUsersServlet?para=0" plain="true" >修改密码</a>          
		            <a class="mini-button mini-button-iconTop" iconCls="icon-remove" onclick="" href="ToLogOut" plain="true" >注销</a>onlick 没什么用
        		</div>
        		
        		<ul id="menu1" class="mini-menubar" style="width:80%;height=30;margin-left:1%;" borderStyle="border:0"
		            url="LoadMainMenu" onitemclick="onItemClick" imgPath="imgs/"
		            textField="text" idField="id" parentField="pid"  
		        >
		    	</ul>                 总热言之  该处和下面的lefttree都是事实现 的是 分别展开相应的选项
        		
			</div>
			
			
			<div title="south" region="south" showSplit="false"
				showHeader="false" height="30">
				<div style="line-height: 28px; text-align: center; cursor: default">
					Copyright © 南京航空航天大学版权所有
				</div>
			</div>
			
			<div showHeader="false" region="west" width="180" maxWidth="250"  showHeader="true" title="目录"
				minWidth="100">
				OutlookMenu
				<div id="leftTree" class="mini-outlookmenu" url="LoadMainMenu"
					onitemclick="onItemSelect" idField="id" parentField="pid" style="height:550" imgPath="imgs/"
					textField="text" borderStyle="border:0">
				</div>
			</div>
			
			下面这个是页面中间的那个页面
			<div showCollapseButton="false" style="border: 0;">
			Tabs
			<div id="mainTabs" class="mini-tabs main-tabs" activeIndex="0"
				style="width: 100%; height: 100%;" plain="true"
				onactivechanged="onTabsActiveChanged" buttons="#tabsButtons" arrowPosition="side">
				<div title="首页" url="waitDoPage.jsp">
				</div>
			</div>
		</div>

		</div> -->

		

		<!-- <script type="text/javascript">
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
		</script> -->

</html>
