<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page
	import="com.sun.xml.internal.ws.message.stream.PayloadStreamReaderMessage"%>
<%@page import="JSOM.TransTask"%>
<%@page import="JSOM.JackSonTrans"%>
<%@page import="JSOM.DB"%>

<%@page import="java.io.PrintWriter"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<%@ page import="java.io.File"%>
<%@ page import="java.io.FileOutputStream"%>
<%@ page import="java.io.*"%>
<%@ page import="java.io.Writer"%>
<%@ page import="java.io.PrintWriter"%>
<% 
		//out.print(JackSonTrans.back());
		/* response.getWriter() Returns a PrintWriter object that can send character text to the client.*/
		BufferedReader reader = request.getReader();
			
			StringBuffer buffer = new StringBuffer();
			String string;
			while ((string = reader.readLine()) != null) {
				buffer.append(string);
			}
			//System.out.println(buffer.toString());
			System.out.println("正在执行保存操作！！！！");
		String id= buffer.toString();
		response.getWriter().append(JackSonTrans.JsonBack(DB.getFandTList()));
			//response.getOutputStream().write(("haha").getBytes());	
		%>
