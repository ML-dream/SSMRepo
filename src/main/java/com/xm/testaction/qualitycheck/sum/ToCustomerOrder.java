package com.xm.testaction.qualitycheck.sum;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Customer;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class ToCustomerOrder extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ToCustomerOrder() {
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
		
		String companyId = request.getParameter("customer");
//	    String connector = ChineseCode.toUTF8(request.getParameter("connector"));
	
		String	CustomerSql = 
		    	"select COMPANYID,COMPANYNAME,FOUNDEINGTIME,EMPLLOYEENUM,TYPE,ADDRESS,POSTCODE,TELEPHONE," +
		    	"WEBADDRESS,HEADER,BUSINESS,ADVISE,connector,connectorTel,connector2,connector2Tel,connector3,connector3Tel,connector4,connector4Tel " +
		    	"from customer where companyId=?";
		
		String[] params = {companyId};
		Customer customer = new Customer();
		try {
			customer = Sqlhelper.exeQueryBean(CustomerSql, params, Customer.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("customer", customer);
		
		request.getRequestDispatcher("orderStatistics/CustomerOrders.jsp").forward(request, response);
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
