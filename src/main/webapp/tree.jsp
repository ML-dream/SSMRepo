<%@ page language="java"  contentType="text/html; charset=utf-8" errorPage="../error.jsp"%>
<%@ page import="java.util.List" import="Bom.*" %>
<jsp:useBean id="ds" scope="page" class="cfmes.util.DealString"/>
<jsp:useBean id="bombeandao" scope="page" class="cfmes.bom.dao.BomBeanDao"/>
<%@page import="cfmes.bom.entity.AddTree"%>
<%request.setCharacterEncoding("utf-8"); 
  response.setHeader("Charset","utf-8"); %>
<%      String flight_type = ds.toString(ds.toGBK(request.getParameter("flight_type")));//tree_session.getFlight_type();
        String product_id  = ds.toString(ds.toGBK(request.getParameter("product_id")));//(String)tree_session.getProduct_id();
        String lot         = ds.toURL(ds.toString(ds.toGBK(request.getParameter("lot"))));
        String sortie      = ds.toURL(ds.toString(ds.toGBK(request.getParameter("sortie"))));
        String issue       = ds.toString(ds.toGBK(request.getParameter("issue")));//(String)tree_session.getIssue();
        String issue_num   = ds.toString(ds.toGBK(request.getParameter("issue_num")));//(String)tree_session.getIssue_num();
        Bom_TreeDAO treedao = new Bom_TreeDAO();
		
		List list=treedao.getCapp_List(flight_type,product_id,issue,issue_num);		//flight_type==1
	    
	    flight_type = ds.toURL(flight_type);
        product_id = ds.toURL(product_id);
        issue_num = ds.toURL(issue_num);
        Bom_TreeNode bom;
        int num2;
        int num;
        if(list.size()!=0&&list!=null){ 
		    bom=(Bom_TreeNode)list.get(list.size()-1);   //为了取得num，因为num是最后一个列入的——fy 
		    num2 = 0;
		    num = bom.getNum(); 
	    }
       System.out.println("hello");
		%>
<html>
<head>
	<title>基础数据      Bom维护        树结构</title>
	
	<!-- 基础数据      Bom维护        树结构 -->
	
	<link href="css/person.css" type=text/css rel=stylesheet>
	<link rel="StyleSheet" href="css/dtree.css" type="text/css" />
	<script type="text/javascript" src="js/dtree.js"></script>
	<script>
		function tombomsvlt(){
		  	window.location.href="mbomsvlt?product_id="+"<%=product_id%>"+"&issue_num="+"<%=issue_num%>"+"&issue="+"<%=issue%>"+"&lot="+"<%=lot%>"+"&sortie="+"<%=sortie%>";
		}
		function ToFshBomSvlt(){
		  	window.location.href="FshBomSvlt?flight_type="+"<%= flight_type%>"+"&product_id="+"<%=product_id%>"+"&issue_num="+"<%=issue_num%>"
		  	+"&lot="+"<%=lot%>";
		}
	</script>
</head>
<body bgcolor=#FFFFFF onresize="return true;" leftMargin=1 topMargin=1 onload="buildtree();">
<form name="form3" class="zipcode" action="" method=post>
<input type="button" name="bt_mbom" title="后台构造MBOm" value="构造mbom" onclick="tombomsvlt()">

<div class="dtree"><div align="center"> 
	</div><p align="left"><FONT size="2" ><a href="javascript: d.openAll();">&nbsp;&nbsp;&nbsp; 全展</a> | 
	  <a href="javascript: d.closeAll();">全关</a> | <a href="javascript: location.reload();" style="color: red; font-size: 16px;" >刷新</a> </FONT>
	  </p>  
</div>

<div id='dtree' class="dtree">	
	  <script type="text/javascript">
		d = new dTree('d','','dtree');
		function buildtree(){	
		d.add('top','x','EBOM结构树',null,null,null);
	<%		
       if(list.size()!=0&&list!=null){
	       for(int i=0;i<list.size()-1;i++){
				bom=(Bom_TreeNode)list.get(i);
	            String Father_item_id = ds.toURL(bom.getFather_item_id());
	            String Item_id = ds.toURL(bom.getItem_id());
	%>
			    d.add('<%=bom.getTree_id()%>','<%=bom.getFather_tree_id()%>'
			    ,'<%=bom.getItem_id()%><%if(!bom.getItem_name().equals(bom.getItem_id()))
			    {%>【<%=bom.getItem_name()%>】<%}%><%if(!bom.getAofo().equals("")){%>【<%=bom.getAofo()%>】<%}%>'
			    ,parent.getUrlByCatalogId('<%=flight_type%>','<%=product_id%>','<%=lot%>','<%=sortie%>'
			    ,'<%=issue_num%>','<%=issue%>','<%=Item_id%>','<%=Father_item_id%>','<%=bom.getId()%>'
			    ,'<%=bom.getFid()%>','<%=bom.getLevel_id()%>','<%=bom.getIssue_num()%>','<%=bom.getAofo()%>')
			    ,'<%=bom.getItem_name()%>','list');
	<%  	}%>
    <%  }%>	
		d.draw();
	}	
	</script>
</div>
</form>
<body bgcolor="l&gt;
" onresize="dy&gt;
</html>
" leftMargin="
" topMargin="
" onload="dy&gt;
</html>
"></body>
</html>
