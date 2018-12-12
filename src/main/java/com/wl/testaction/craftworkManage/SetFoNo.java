package com.wl.testaction.craftworkManage;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class SetFoNo extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SetFoNo() {
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
//		String orderid = request.getParameter("orderid");
		String productid = request.getParameter("productid");
		String issuenum = request.getParameter("issuenum");
		String [] para = {productid,issuenum};
		String sqla = "select max(fono) from (select to_number(a.fo_no) fono from fo_detail a where a.product_id=? and a.issue_num=? and isInUse='1' )" ;
		int temp = 5; //最大的工序号
		try {
			temp = Sqlhelper.exeQueryCountNum(sqla, para);
		} catch (Exception e) {
			// TODO: handle exception
		}
//		工序号自动加5
		temp += 5;
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(""+temp).flush();
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
