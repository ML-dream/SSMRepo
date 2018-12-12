package com.wl.testaction.warehouse.supplier;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class addNewSupplierServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public addNewSupplierServlet() {
		super();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
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

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String companyId=df.format(new Date());
		companyId=companyId.replace(" ","");
		companyId=companyId.replace("-", "");
		companyId=companyId.replace(":", "");
		request.setAttribute("companyId", companyId);
		request.getRequestDispatcher("/Supplier/addNewSupplier.jsp").forward(request, response);
	}


	private String replace(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}

}
