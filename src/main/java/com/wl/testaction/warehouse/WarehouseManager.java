package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import PluSoft.Utils.JSON;

import com.wl.tools.Sqlhelper;

public class WarehouseManager extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WarehouseManager() {
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
		String sqla= "select t.staff_code id,t.staff_name text from employee_info t where t.work_type='store'";
		BeanWarehouseManager bean = new BeanWarehouseManager();
		try {
			bean = Sqlhelper.exeQueryBean(sqla, null, BeanWarehouseManager.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = JSON.Encode(bean);
		System.out.println(json);
		response.setCharacterEncoding("utf-8");
		response.getWriter().append("["+json+"]").flush();
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
