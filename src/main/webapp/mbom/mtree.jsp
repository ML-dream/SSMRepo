<%@ page language="java" import="java.util.*" contentType="text/html; charset=utf-8"%>
<%@ page import="Bom.Mbom_TreeDao" import = "Bom.Bom_TreeNode" %>
<jsp:useBean id="ds" scope="page" class="cfmes.util.DealString"/>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<% 
        
        String product_id = ds.toGBK(request.getParameter("product_id"));
		String issue_num = ds.toGBK(request.getParameter("issue_num"));
		String issue = ds.toGBK(request.getParameter("issue"));
		String lot = ds.toGBK(request.getParameter("lot"));
		String sortie = ds.toGBK(request.getParameter("sortie"));
		
        Mbom_TreeDao treedao = new Mbom_TreeDao();
		Bom_TreeNode bom;
		List list=treedao.getCapp_List(product_id,issue,issue_num);
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>生成MBOM树处理界面</title>
    
    
    
    <!--计划管理      编制零件计划       MBom结构树左侧的 -->
    
    
   
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link href="../css/person.css" type=text/css rel=stylesheet>
	<link rel="StyleSheet" href="../css/dtree.css" type="text/css" />
	<script type="text/javascript" src="../js/dtree.js"></script>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body bgcolor=#FFFFFF onresize="return true;" leftMargin=1 topMargin=1 onload="buildtree();">
   <div class="dtree" align="center"> 
	<p align="left"><FONT size="2" ><a href="javascript: d.openAll();"> &nbsp;&nbsp; 全展</a>  
	  <a href="javascript: d.closeAll();">全关</a> | <a href="javascript: location.reload();" style="color: red;">刷新</a> </FONT>
	  </p>  
   </div>  
   <div id='dtree' class="dtree">	
	  <script type="text/javascript">
		d = new dTree('d','../','dtree');
	function buildtree(){	
		d.add('top','x','MBOM结构树',null,null,null);
<%		  
       if(list.size()!=0&&list!=null){
       for(int i=0;i<list.size()-1;i++){
			bom=(Bom_TreeNode)list.get(i);
            String Father_item_id = ds.toURL(bom.getFather_item_id());
            String Item_id = ds.toURL(bom.getItem_id());
            
		%>
		    d.add('<%=bom.getTree_id()%>','<%=bom.getFather_tree_id()%>','<%=bom.getItem_id()%><%if(!bom.getItem_name().equals(bom.getItem_id())){%>【<%=bom.getItem_name()%>】<%}%>',parent.getUrlByCatalogId('<%=product_id%>','<%=bom.getIssue_num()%>','<%=Item_id%>','<%=bom.GetMemo1()%>','<%=bom.GetMemo2()%>','<%=bom.GetMemo3()%>','<%=bom.GetMemo4()%>'),'<%=bom.getItem_name()%>','framedomtree','','','','<%=bom.getEc_id()%>','<%=bom.getEc_name()%>');
		<%  }%>
        <%  }%>	
		d.draw();
	}		
	</script>
</div>
  </body>
</html>
