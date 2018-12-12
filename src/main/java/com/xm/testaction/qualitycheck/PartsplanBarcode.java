package com.xm.testaction.qualitycheck;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper0;

public class PartsplanBarcode extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public PartsplanBarcode() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

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

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String partsplanid = request.getParameter("partsplanid");
		String barcode ="";
		String sql = "select a.codeid from partsplan a where a.partsplanid='"+partsplanid+"'";
		ResultSet rs = null;
		try {
			
			rs= Sqlhelper0.executeQuery(sql, null);
			rs.next();
			barcode = rs.getString(1);
			
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
		request.setAttribute("barcode", barcode);
		request.getRequestDispatcher("qualitycheck/printBarcode.jsp").forward(request, response);
//		response.setCharacterEncoding("utf-8");
//		response.getWriter().append(barcode).flush();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
