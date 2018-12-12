<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

   	<!-- fastui -->
    <link href="<%=basePath%>resources/fastui/themes/default/fastui.css" rel="stylesheet" type="text/css" /> 
    <link href="<%=basePath%>resources/fastui/themes/icons.css" rel="stylesheet" type="text/css" />
    <link href="<%=basePath%>resources/fastui/themes/blue/skin.css" rel="stylesheet" type="text/css" />

    <script src="<%=basePath%>resources/fastui/fastui.min.js" type="text/javascript"></script>

    <script type="text/javascript">
	    // 查看代码。
		function onViewCodes(event) {
			fastui.open({
				url: "<%=basePath%>standard/viewCodes.action",
				title: "查看代码", 
				bodyStyle: "padding:0px;",
				width: 900, height: 560,
				allowDrag: true, allowResize: true, showCloseButton: true, showMaxButton: true, showModal: false, showShadow: true,
				ondestroy: function (action) {
				}
			});
		}
	</script>