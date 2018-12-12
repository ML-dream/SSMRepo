package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class AddSheetDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public AddSheetDetailServlet() {
		super();
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

		doPost(request,response);
	
	}


	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String warehouse_id=request.getParameter("warehouse_id");
		String checksheet_id=request.getParameter("checksheet_id");
		String sql="select count(*) from mycheckin_detl where checksheet_id='"+checksheet_id+"'";
		int totalCount=0;
		try{
			totalCount=Sqlhelper.exeQueryCountNum(sql, null);
			totalCount=totalCount+1;
			request.setAttribute("checkindetl_id", totalCount);
		}catch(Exception e){
			e.printStackTrace();
		}
		request.setAttribute("checksheet_id", checksheet_id);
		request.setAttribute("warehouse_id", warehouse_id);
		request.getRequestDispatcher("/Checkin/AddSheetDetail.jsp").forward(request, response);
	}

}
