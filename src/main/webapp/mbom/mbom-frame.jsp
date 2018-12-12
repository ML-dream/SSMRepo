<%@ page language="java" contentType="text/html; charset=utf-8"
	errorPage="error.jsp"%>
<jsp:useBean id="ds" scope="page" class="cfmes.util.DealString" />
<HTML>
	<HEAD>
		<TITLE></TITLE>
		<%
			request.setCharacterEncoding("utf-8");
			response.setHeader("Charset", "utf-8");

			String flight_type = ds.toURL(ds.toString(ds.toGBK(request
					.getParameter("product_type"))));
			String product_id = ds.toGBK(request.getParameter("product_id"));
			//String issue_num = ds.toGBK(request.getParameter("issue_num"));

			String lot = ds.toGBK(request.getParameter("lot"));
			String sortie5 = ds.toGBK(request.getParameter("sortie"));

			String issue = "1";
			String issue_num = "1";
			String sortie = "1";

			if (!sortie5.equals("")) {
				String[] num = new String[4];
				num = sortie5.split("---");
				issue = num[3]; //对应issue_deploy中的issue_pos
				issue_num = ds.toURL(num[2]); //对应issue_deploy中的issue_num
				sortie = ds.toURL(num[0] + "-" + num[1]);
			} else {
				flight_type = "1";
				product_id = "1";
				lot = "1";
			}
		%>
		<META http-equiv=Content-Type content="text/html; charset=utf-8">
		<LINK href="" type=text/css rel=stylesheet>
		<SCRIPT type=text/javascript>
     function getUrlByCatalogId(product_id,issue_num,node_id,memo1,memo2,memo3,memo4) {
     return "domtree.jsp?product_id="+product_id+"&issue_num="+issue_num+"&node_id="+node_id+"&memo1="+memo1+"&memo2="+memo2+"&memo3="+memo3+"&memo4="+memo4;
     }
 
</SCRIPT>
		<META content="MSHTML 6.00.2900.3527" name=GENERATOR>
	</HEAD>

	<FRAMESET border=0 frameSpacing='10' border=0 cols=150,*>
		<FRAME name=mtree frameborder='0' height="100%" marginHeight="0"
			MARGINWIDTH="10" scrolling=yes target="framedomtree"
			src="mtree.jsp?product_id=+<%=product_id%>+&issue_num=+<%=issue_num%>+&issue=+<%=issue%>+&lot=+<%=lot%>+&sortie=+<%=sortie%>+">
		<FRAME name=framedomtree frameborder='0' src="domtree.jsp"
			marginHeight="0" height="100%" width="100%">
		<NOFRAMES>
			<body>
				&nbsp;

			</body>
		</NOFRAMES>
	</FRAMESET>
</HTML>
