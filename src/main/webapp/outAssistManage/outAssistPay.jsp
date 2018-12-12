<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
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
		<link href="<%=path %>/scripts/miniui/themes/default/miniui.css" rel="stylesheet" type="txt/css"></link>
		<link href="<%=path %>/scripts/miniui/themes/icons.css" rel="stylesheet" type="txt/css"></link>
   
    <title>外协付款</title>
    <style type="text/css">
    	*{margin: 0;padding: 0;}
    </style>
  </head>
  
  <body style="height:100%;">
    <div id="layout1" class="mini-layout" style="width:100%;height:100%;">
		    <div showHeader="false" region="west" width="180" maxWidth="250" minWidth="100" >
		    	<a class="mini-button" iconCls="icon-reload" plain="false" onclick="refresh()" >刷新</a>
		        <ul id="leftTree" class="mini-tree" url="GetOutAssistPayTreeServlet" style="width:100%;height:95%;" 
                    showTreeIcon="true" textField="text" idField="id" parentField="pid" resultAsTree="false"  
                    onnodeselect="onNodeSelect">        
                </ul>
		    </div>
            <div title="center" region="center" bodyStyle="overflow:hidden;">
		        <iframe id="mainframe" frameborder="0" name="main" style="width:100%;height:100%;" src="outAssistManage/outAssistPayList.jsp" border="0"></iframe>
		    </div>
		</div>
<script> 
			mini.parse();
			var iframe = document.getElementById("mainframe");
	
	        function onNodeSelect(e) {
	            var tree = mini.get("leftTree");
	        	var node = e.node;  	

	        	if(node.level==1){			//客户层
        		iframe.src = "GoOutAssistPayMentServlet?companyId="+node.id+"&companyName="+node.text;
		        }
	    
            
	        }

	        function refresh(){
				var tree = mini.get("leftTree");
				tree.load();
		    }
		</script>
  </body>
</html>
