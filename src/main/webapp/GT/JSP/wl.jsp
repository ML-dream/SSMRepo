<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="java.io.*"%>
  
  <%
			BufferedReader reader = request.getReader();
			StringBuffer buffer = new StringBuffer();
			String string;
			while ((string = reader.readLine()) != null) {
				buffer.append(string);
			}
			System.out.println("输入的是"+buffer.toString());
		    response.getWriter().append(buffer.toString());
		//DB.saveFandTList(JackSonTrans.ListBack(buffer.toString()));
   %>	
