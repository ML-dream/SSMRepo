package com.wl.testaction.warehouse.payment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.wl.forms.CustomerPayment;
import com.wl.tools.Sqlhelper;

public class ShowaddCustomerPaymentServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowaddCustomerPaymentServlet() {
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
		request.setCharacterEncoding("utf-8");
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int totalCount=0;
		
		String totalCountSql="select count(1) from initialcustomerpayment";
		
		try {
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		String paymentSql="select adddate,B.companyid customerId,initialpayment,companyname,connector from " +
				"(select A.*,rownum rn from (select t.* from initialcustomerpayment t order by adddate desc ) A " +
				"where rownum<="+pageSize*pageNow+" order by adddate desc) B " +
						"left join customer C on C.companyid=B.companyid " +
						"where rn>="+(pageSize*(pageNow-1)+1)+"";
		
		List<CustomerPayment> resultList=new ArrayList<CustomerPayment>();
		
		try {
			resultList=Sqlhelper.exeQueryList(paymentSql, null, CustomerPayment.class);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		System.out.println(json);
		response.getWriter().append(json).flush();
		
		
	}

}
