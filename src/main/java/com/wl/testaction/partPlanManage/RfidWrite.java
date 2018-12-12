package com.wl.testaction.partPlanManage;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;


import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.wl.tools.Sqlhelper0;



public class RfidWrite extends HttpServlet {

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String partsplanid = request.getParameter("partsPlanId");
		System.out.println(partsplanid);
		String barcode1 ="";
		String sql = "select a.codeid from partsplan a where a.partsplanid='"+partsplanid+"'";
		ResultSet rs = null;
		try {
			
			rs= Sqlhelper0.executeQuery(sql, null);
			rs.next();
			barcode1 = rs.getString(1);
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
//			Sqlhelper0.close();
			if(rs != null){
		    	try {
				rs.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
			}
			}
		String barcode=barcode1+"1";
		System.out.println(barcode);
		
	/*	request.setAttribute("barcode", barcode);
		request.getRequestDispatcher("qualitycheck/printBarcode.jsp").forward(request, response);
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(barcode).flush();
		*/
		
		Socket ss = new Socket("192.168.43.77",8898);
		System.out.println("启动。。。。");
		
		OutputStream out = ss.getOutputStream();
		out.write(barcode.getBytes());
		System.out.println("已发送 .....");
		
		InputStream in = ss.getInputStream();
		
		byte[] buf = new byte[1024];
		int len = in.read(buf);
		String text =new String(buf,0,buf.length);
		System.out.println(text);
		PrintWriter out2=response.getWriter();
	    String text1= text.substring(0, 5);
		System.out.println(text1);
/*		String a="{\"text\":"+text1+"}";*/
		out2.write(text1);
		
		
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
	}

}
