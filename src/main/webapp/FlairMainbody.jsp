<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<html>
<head>
    <base href="<%=basePath%>"/>
    <title>主框架</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <script src="<%=basePath%>scripts/boot.js" type="text/javascript"></script>

    <!-- 系统菜单图标 -->

    <style type="text/css">
        html, html body {
            padding: 0;
            border: 0;
            margin: 0;
            width: 100%;
            height: 100%;
            overflow: hidden;
            font-family: "微软雅黑", "宋体";
            font-size: 12px;
            line-height: 22px;
        }

        .header {
            background: url(<%=basePath%>demo/header.gif) repeat-x 0 -1px;
        }
    </style>

</head>

<body>
<!--Layout-->
<div id="layout1" class="mini-layout" style="width:100%;height:100%;">
    <div class="header" region="north" height="50" showSplit="false" showHeader="false">
        <div style="position:absolute;top:20px;right:12px;">
            <font style="color: blue">${requestScope.name}</font>
            &nbsp;您好，欢迎登录本系统！
            &nbsp;平台：
            <input id="platId" name="platId" class="mini-combobox"
                   url="" showNullItem="false" value="${platId}"
                   textField="text" valueField="id" onvaluechanged="changePlatInfo"/>
            <a class="mini-button" iconCls="icon-tip" onclick="modifPerson">个人信息</a>
            <a class="mini-button" iconCls="icon-signOut" href="login!logout.action?&platId=${platId}"
               >退出</a>
        </div>
    </div>
    <div title="south" region="south" showSplit="false" showHeader="false" height="25">
        <div style="line-height:22px;text-align:center;cursor:default">
            Copyright@检验检疫实验室资源管理系统版权所有
        </div>
    </div>
    <div title="center" region="center" bodyStyle="overflow:hidden;">
        <!--Splitter-->
        <div class="mini-splitter" style="width:100%;height:100%;" borderStyle="border:0;">
            <div size="206" maxSize="230" minSize="150" showCollapseButton="true">
                <!-- 最好用nodeclick事件：onnodeclick -->
                <!-- 这里是加载左侧树结构的代码，从action中传回固定格式的json字符串即可 -->
                <!-- 拼接好的字符串为
                
                [{ id: "100010021000", text: "实验室资质证书", pid: "10001002"},
				 { id: "1000100210001001", text: "待审核", pid: "100010021000", iconCls: "menuIcon-defaultMenu", url: "labmanager/labDescQualityAction!queryAllDshList.action?menuId=402898f6504b7e0701504b7f75f70002&platId=402898984ffecbc4014ffecbc41f0000"},
				 { id: "1000100210001002", text: "全部", pid: "100010021000", iconCls: "menuIcon-defaultMenu", url: "labmanager/labDescQualityAction!queryAllQbList.action?menuId=402898f6504b7e0701504b80262d0003&platId=402898984ffecbc4014ffecbc41f0000"},
				 { id: "1000100210001000", text: "即将到期", pid: "100010021000", iconCls: "menuIcon-defaultMenu", url: "labmanager/labDescQualityAction!queryAllJjdqList.action?menuId=402898f6504b7e0701504b7f39ab0001&platId=402898984ffecbc4014ffecbc41f0000"}
				]
				
				拼接的java代码如下：
				
				menus.append("[");
		        if (null != allSysMenus && !allSysMenus.isEmpty()) {
		            for (int i = 0; i < allSysMenus.size(); i++) {
		                HashMap<String, ?> menu = allSysMenus.get(i);
		                //机构管理加载tree
		                if (NAMETEXT.equals(menu.get("NAME"))) {
		                    String orgId = (String) this.getSession().getAttribute(MyProperties.WEB_LOGINUSER_ORG_ID);
		                    if (StringUtils.isBlank(orgId))
		                        return null;
		                    List<SysOrgInfoBean> sysOrgInfoBeanList = sysOrgInfoService.queryWithOrg(orgId, null);
		                    if (sysOrgInfoBeanList != null && sysOrgInfoBeanList.size() > 0) {
		                        SysOrgInfoBean sysOrgInfoBean = new SysOrgInfoBean();
		                        sysOrgInfoBean.setId(menu.get("PNUM").toString());
		                        sysOrgInfoBean.setText(menu.get("NAME").toString());
		                        sysOrgInfoBeanList.add(sysOrgInfoBean);
		                        sysOrgInfoBeanList.get(0).setPid(menu.get("PNUM").toString());
		                        menus.setLength(0);
		                        menus.append(PluSoft.Utils.JSON.Encode(sysOrgInfoBeanList));
		                        return this.ajax(menus.toString(), "text/json");
		                    }
		                }
						String is_leaf = (String) menu.get("LEAF");
		                menus.append("{ ")
		                        .append("id: \"").append(menu.get("NUM")).append("\", ")
		                        .append("text: \"").append(menu.get("NAME")).append("\"");
		                Integer menu_level = (Integer) menu.get("LEVE");
		                if (null != menu_level && menu_level.intValue() > 1) {
		                    menus.append(", ").append("pid: \"").append(menu.get("PNUM")).append("\"");
		                }
		                if ("1".equals(is_leaf)) {
		                    // 菜单图标
		                    String menu_icon = (String) menu.get("ICON");
		                    if (org.apache.commons.lang.StringUtils.isEmpty(menu_icon)) {
		                        menu_icon = Constants.MENU_ICON_DEFAULT;
		                    }
		
		                    menus.append(", iconCls: \"").append(menu_icon).append("\", ");
		
		                    /************工作流菜单中用到菜单主键START**************/
		                    String menuUrl = "";
		                    if (null != menu.get("URL") && !"".equals(menu.get("URL"))) {
		                        menuUrl = menu.get("URL").toString();
		                        if (-1 != menuUrl.indexOf("?")) {
		                            menuUrl += "&menuId=" + menu.get("MENU_ID");
		                            menuUrl += "&platId=" + platId;
		                        } else {
		                            menuUrl += "?menuId=" + menu.get("MENU_ID");
		                            menuUrl += "&platId=" + platId;
		                        }
		                    }
		                    menus.append("url: \"").append(menuUrl).append("\"");
		                    /************工作流菜单中用到菜单主键END**************/
		
		                    //menus.append("url: \"").append(menu.get("MENU_URL")).append("\"");
		                }
		
		                if (i == allSysMenus.size() - 1) {
		                    menus.append("}");
		                } else {
		                    menus.append("},");
		                }
		            }
		        }
		
		        menus.append("]");
		                
                 -->
                <div class="mini-outlooktree" url="<%=basePath %>demo/data/TreeService.jsp?method=LoadTreeprocess"
                     onnodeclick="onNodeSelect"
                     idField="id" parentField="pid" textField="text">
                </div>

            </div>
            <div showCollapseButton="false">
                <!--Tabs-->
                <div id="mainTabs" class="mini-tabs bg-toolbar" activeIndex="0" style="width:100%;height:100%;"
                     bodyStyle="border:0;background:white;"
                        >
                    <div name="indexTab" title="首页" url="" showCloseButton="false" refreshOnClick="true">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

    mini.parse();

    var tab_id = "tab$1";

    var tabs = mini.get("mainTabs");

    function showTab(node) {
        var text = node.text;
        var tabs = mini.get("mainTabs");
        var id = "tab$" + node.id;
        var tab = tabs.getTab(id);//通过node.id获取该标签
        if (!tab) {       								    //若标签不存在
            tab = {};
            //tab._nodeid = node.id;
            tab.name = id;									//标签名
            tab.title = text;								//标签标题
            tab.url = node.url;								//这里的url是要自己拼接的，或者直接保存在数据库中，直接取出来即可
            tab.showCloseButton = true;						//显示关闭按钮
            tab.refreshOnClick = false;						//点击标签页不再刷新

            tabs.addTab(tab);								//渲染标签
        }
        else {												//else  中代码也可以不写
            tab.title = text;
            tab.url = node.url;
            tab.showCloseButton = true;
            tab.refreshOnClick = false;						//再次加载点击标签页不再刷新

            tabs.reloadTab(tab);
            tabs.updateTab(tab);                            //这里是判断标签是否已经存在，如果存在则刷新此标签
        }
        tabs.activeTab(tab);								//激活标签页
    }

    // 可以是tree选中事件，最好是单击事件(☆★★★☆)
    function onNodeSelect(e) {								//点击左侧的树结构后处理的动作
        var node = e.node;
        //机构管理
        if ("null" != node['class'] && "" != node['class'] && null != node['class']) {
            node.url = "labmanager/cenDescAction!selMainPage.action?org_id=" + node.id + "&menuId=${sys_menu_id}";
            node.text = node.org_ab;						//上面这个是新标签页的url地址，自己拼接
        }
        if ("null" == node.url || "" == node.url) {
            mini.alert("“" + node.text + "” 不是有效菜单！");
            return false;
        }
        showTab(node);										//显示标签
    }

    function modifPerson() {								//这里是个人信息查看和修改语句
        mini.open({
            url: "sysmanager/personInfo.action",			//点击个人信息按钮后，弹出一个mini弹窗，显示个人信息，弹窗的大小是宽880，高580
            title: "个人信息", width: 880, height: 580,
            showMaxButton: false,
            onload: function () {
                //var iframe = this.getIFrameEl();
                //var data = { action: "new"};
                //iframe.contentWindow.SetData(data);
            },
            ondestroy: function (action) {
                // grid.reload();
            }
        });
    }

    function changePlatInfo() {								//切换平台信息
        var platId = mini.get("platId");
        var platId_v = platId.getValue();					//下面是切换到的平台的url，这个自己拼接
        window.location.href = "<%=basePath%>sysmag/BSysUtil_platIndex.action?platId=" + platId_v;
    }

    //子页及子页面的子页面挂上标签板 的回调方法
    //text：标题；
    //code:标签的唯一ID，可以通过自由组编生成，确保唯一，若两个列表的数据中某条记录id一样，都生成标签页会重复，可编入其他字段区别
    //url:访问链接
    function doAdd(text, code, url) {
        var tabs = mini.get("mainTabs");

        var id = "tab$" + code;
        var tab = tabs.getTab(id);
        if (!tab) {
            tab = {};
            //tab._nodeid = node.id;
            tab.name = id;
            tab.title = text;
            tab.showCloseButton = true;
            tab.refreshOnClick = false;//加载点击标签页不再刷新
            //这里拼接了url，实际项目，应该从后台直接获得完整的url地址
            tab.url = url;
            tabs.addTab(tab);
        }
        tabs.activeTab(tab);
    }
    function closeTab(code) {
        var tabs = mini.get("mainTabs");
        var id = "tab$" + code;
        var tab = tabs.getTab(id);
        if (tab) {
            tabs.removeTab(tab)
        }
    }

    function jumpToTab(code) {
        var tabs = mini.get("mainTabs");
        var id = "tab$" + code;
        var tab = tabs.getTab(id);
        if (tab) {
            tabs.reloadTab(tab);
            tabs.updateTab(tab);
            tabs.activeTab(tab);
        }
    }
</script>
</body>
</html>