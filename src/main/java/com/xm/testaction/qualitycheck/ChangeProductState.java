package com.xm.testaction.qualitycheck;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.common.OrderStatus;
import com.wl.common.ProductStatus;
import com.wl.tools.Sqlhelper;
/**
 * 报废件，临时修改订单状态
 * @author xiem
 *
 */
public class ChangeProductState extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ChangeProductState() {
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
		
		String barcode = request.getParameter("barcode");
		String sqla = "update todiscard t set t.state=? where t.barcode= ?";
		String [] params = {ProductStatus.FOSUBMIT+"",barcode};
		try {
			Sqlhelper.executeUpdate(sqla, params);
			System.out.println("ok "+sqla);
		} catch (Exception e) {
			// TODO: handle exception
		}
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
