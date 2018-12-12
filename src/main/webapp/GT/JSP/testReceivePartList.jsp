<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page
	import="com.sun.xml.internal.ws.message.stream.PayloadStreamReaderMessage"%>
<%@page import="JSOM.JackSonTrans"%>
<%@page import="JSOM.DB"%>
<%@page import="org.omg.PortableInterceptor.SYSTEM_EXCEPTION"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
		<%@ page import="java.io.File"%>
		<%@ page import="java.io.FileOutputStream"%>
		<%@ page import="java.io.*"%>
	
	<%-- //File file=new File("d:\\test1.txt");
			File file = new File(
					"c:\\apache-tomcat-6.0.39\\webapps\\javawebZX\\GT\\data\\lpfdata.txt");
			FileWriter fw = new FileWriter(file);
			BufferedReader reader = request.getReader();
			StringBuffer buffer = new StringBuffer();
			String string;
			while ((string = reader.readLine()) != null) {
				buffer.append(string);
			}
			BufferedWriter bw = new BufferedWriter(fw);
			bw.write(buffer.toString());
			bw.close();
			fw.close();
			reader.close();
		response.getWriter().append(buffer.toString());
	
		--%>  
			<%//需要把本工程里的所有引用的外部jar包，放到tomcat的lib下面，才能使用相应的函数 如jackson
			BufferedReader reader = request.getReader();
			
			StringBuffer buffer = new StringBuffer();
			String string;
			while ((string = reader.readLine()) != null) {
				buffer.append(string);
			}
			//System.out.println(buffer.toString());
			System.out.println("正在执行保存操作！！！！");
		    response.getWriter().append(buffer.toString());
		   // System.out.println(buffer.toString());
		  // JackSonTrans.PrintFandTList(JackSonTrans.ListBack(buffer.toString()));
		DB.saveFandTList(JackSonTrans.ListBack(buffer.toString()));
		%>	


