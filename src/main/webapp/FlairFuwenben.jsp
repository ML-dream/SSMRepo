<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>"><script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
    
    <title>My JSP 'FlairFuwenben.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  	<!-- 这三个头是ueditor文本编辑器引入的！！！！！ -->
    <script type="text/javascript" src="<%=basePath %>resources/ueditor/editor_config.js"></script>
    <script type="text/javascript" src="<%=basePath %>resources/ueditor/editor_all_min.js"></script>
    <link href="<%=basePath %>resources/ueditor/themes/default/ueditor.css" rel="stylesheet" type="text/css"/>
  <body>
  	<!-- 这是FCKeditor富文本编辑器,这个文本编辑器不需要在头中引入任何js头！！！！！ -->
    <div>
	    <input type="hidden" id="labDocBean.doc_text_c" name="labDocBean.doc_text_c" value="nihao">
	    <input type="hidden" id="labDocBean.doc_text_c___Config" value="SkinPath=/JsCiqLab/FCKeditor/editor/skins/silver/">
	    <iframe id="labDocBean.doc_text_c___Frame" width="600" height="380" frameborder="no" scrolling="no" 
	    	src="/JsCiqLab/FCKeditor/editor/fckeditor.html?InstanceName=labDocBean.doc_text_c&Toolbar=Basic" >
	    </iframe>
    </div>
    <!-- 以上是FCKeditor富文本编辑器 -->
    
    <!-- 以下是ueditor富文本编辑器 -->
    <div>
	    <script type="text/plain" id="content1" style="width:85%;">
   	 	</script>
    </div>
    <!-- 以上是ueditor富文本编辑器 -->
  </body>
  <script type="text/javascript">
  		/***********以下是ueditor富文本编辑器的核心代码**********/
  		var editor = new baidu.editor.ui.Editor({
	        textarea : 'cmsCont.content', //  textarea 的名称，后台就是接这个变量。
	        minFrameHeight: 270,
	        imageUrl: 'http://localhost:8080/JsCiqLab/resources/ueditor/jsp/imageUp.jsp',
	        fileUrl: 'http://localhost:8080/JsCiqLab/resources/ueditor/jsp/fileUp.jsp',
	        wordImageUrl: 'http://localhost:8080/JsCiqLab/resources/ueditor/jsp/imageUp.jsp',
	        imageManagerUrl: 'http://localhost:8080/JsCiqLab/resources/ueditor/jsp/imageManager.jsp',
	        UEDITOR_HOME_URL: 'http://localhost:8080/JsCiqLab/resources/ueditor/'
	    });
	    editor.render("content1");
	    /***********以上是ueditor富文本编辑器的核心代码**********/
  </script>
</html>
