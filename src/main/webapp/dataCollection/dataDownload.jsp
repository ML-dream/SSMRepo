<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <base href="<%=basePath%>">
    <meta http-equiv="Content-Type" content="text/html;charset=UTF-8">
    <!-- miniui.js -->
      <script type="text/javascript" src="<%=path %>/scripts/boot.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/jquery.min.js"></script>
		<script type="text/javascript" src="<%=path %>/scripts/miniui/miniui.js"></script>
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>数据下载</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>

</head>
<body>
<table style="">
            <tr>
            	<td style="width:80px;">机床一</td>
                <td style="width:60px;">
                	 <a class="mini-button" iconCls="icon-reload" plain="false" onclick="download()">Download</a>
                </td>
            </tr>
            <tr>
               <td>机床二 </td>
                <td>
                	 <a class="mini-button" iconCls="icon-reload" plain="false" onclick="download()">Download</a>
                </td>
            </tr>
            <tr>
            	<td>机床三</td>
            	<td>
            		 <a class="mini-button" iconCls="icon-reload" plain="false" onclick="download()">Download</a>
            	</td>
            </tr>
        </table>
</body>
<script type="text/javascript">
	
	function download(){
		$.ajax({
    		type: "post",
    		url:"dataDownload",
			success:function(text){
				
			    alert("生成txt文件成功，点击确定开始下载……"); 
			    /* var msg=$.parseJSON(text); */
		        /* mini.get("parm1").setValue(msg.data.id);
		        mini.get("parm2").setValue(msg.data.xaxisfeedspeed);
		        mini.get("parm3").setValue(msg.data.xaxiscoordinates); */
		        window.open("file/02.docx");
	          
			    },
			error : function() {
			     alert("下载失败，请重新尝试………"); 
			}
    	});
		
	}
















</script>
</html>