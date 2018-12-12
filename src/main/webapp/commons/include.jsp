<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!-- ysl --->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
%>
<!-- 
<link rel="icon" href="<%=basePath%>favicon.ico" type="image/x-icon"/>
<link rel="shortcut icon" href="<%=basePath%>favicon.ico" type="image/x-icon"/>
 -->
<style type="text/css">
    body {
        width: 100%;
        height: 100%;
        border: 0;
        margin: 0;
        padding: 0;
        overflow: visible;
        background-color: #FFFFFF;
    }
</style>
<link rel="stylesheet" type="text/css" href="<%=basePath%>css/js.css"/>
<link href="<%=basePath%>resources/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>resources/scripts/miniui/themes/icons.css" rel="stylesheet" type="text/css"/>
<link href="<%=basePath%>resources/scripts/miniui/themes/blue/skin.css" rel="stylesheet" type="text/css"/>
<!-- 换肤 -->
<script src="<%=basePath%>resources/jquery/jquery.min.js" type="text/javascript"></script>
<script src="<%=basePath%>resources/jquery/jquery.cookie.js" type="text/javascript"></script>
<!-- cookie插件 -->
<script src="<%=basePath%>resources/scripts/miniui/miniui.js" type="text/javascript"></script>
<script src="<%=basePath%>resources/js/cssSkin.js" type="text/javascript"></script>
<script src="<%=basePath%>resources/js/jsrule.js" type="text/javascript"></script>
<script src="<%=basePath%>resources/js/jquery-barcode.js" type="text/javascript"></script>
<script type="text/javascript" src="<%=basePath%>resources/js/setGrey.js"></script>

<script type="text/javascript">

    $(document).ready(function () {
        var selectSkinVal = $.cookie("selectSkinVal");

        if (selectSkinVal == null) {
            selectSkinVal = "blue";	// 默认蓝色
        }

        // 设置fastui皮肤
        var url_ui = "<%=basePath%>resources/fastui/themes/" + selectSkinVal + "/skin.css";
        AddCSSLink("fastuiSkin", url_ui);

        // 设置mini皮肤
        var url_mini = "<%=basePath%>resources/scripts/miniui/themes/" + selectSkinVal + "/skin.css";
        AddCSSLink("miniSkin", url_mini);

        // 设置ExtJs皮肤
        var url_ext = "<%=basePath%>resources/ext3/resources/css/xtheme-" + selectSkinVal + ".css";
        AddCSSLink("extJsSkin", url_ext);

    });	// end ready

    function onDoUpload(relate_id, relate_type, isMore) {
        <%-----------弹窗居中参数----------%>
        var iWidth = 600; //弹出窗口的宽度;
        var iHeight = 450; //弹出窗口的高度;
        var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
        var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
        <%-----------弹窗居中参数----------%>

        window.open("<%=basePath%>commons/uploadAttachs.jsp?relate_id=" + relate_id + "&relate_type=" + relate_type + "&isMore=" + isMore, "newwindow", "height=" + iHeight + ", width=" + iWidth + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no,menubar=no,scrollbars");
    }


    function viewAttachs(relate_id, relate_type) {
        <%-----------弹窗居中参数----------%>
        var iWidth = 600; //弹出窗口的宽度;
        var iHeight = 450; //弹出窗口的高度;
        var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
        var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
        <%-----------弹窗居中参数----------%>

        // 是否显示删除按钮，true
        var showDelButton = 'true';
        window.open("<%=basePath%>pasystem/BAttachment_list.action?relate_id=" + relate_id + "&relate_type=" + relate_type + "&showDelButton=" + showDelButton, "newwindow", "height=" + iHeight + ", width=" + iWidth + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no,menubar=no,scrollbars");
    }

    function viewAttachsView(relate_id, relate_type) {
        <%-----------弹窗居中参数----------%>
        var iWidth = 600; //弹出窗口的宽度;
        var iHeight = 450; //弹出窗口的高度;
        var iTop = (window.screen.availHeight - 30 - iHeight) / 2; //获得窗口的垂直位置;
        var iLeft = (window.screen.availWidth - 10 - iWidth) / 2; //获得窗口的水平位置;
        <%-----------弹窗居中参数----------%>


        // 是否显示删除按钮，true
        var showDelButton = 'false';
        window.open("<%=basePath%>pasystem/BAttachment_list.action?relate_id=" + relate_id + "&relate_type=" + relate_type + "&showDelButton=" + showDelButton, "newwindow", "height=" + iHeight + ", width=" + iWidth + ",top=" + iTop + ",left=" + iLeft + ",toolbar=no,menubar=no,scrollbars");
    }


</script>
