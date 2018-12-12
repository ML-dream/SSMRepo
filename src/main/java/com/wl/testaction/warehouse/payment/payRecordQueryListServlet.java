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

public class payRecordQueryListServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public payRecordQueryListServlet() {
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
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		String companyId=request.getParameter("companyId");
		String totalCountSql="";
		String customerPaySql="";
		String totalPriceSql="";
		
		if(companyId.isEmpty()){
			totalCountSql="select count(*) from customerpayment";
			customerPaySql="select customerid,C.companyname,C.connector,paydate,thispaid from" +
			"(select A.*,rownum row_num from(select EM.* from customerpayment EM order by paydate desc) A " +
			"where rownum<="+pageSize*pageNow+" order by paydate desc) B " +
					"left join customer C on C.companyid=B.customerid " +
					"where row_num>="+(pageSize*(pageNow-1)+1)+" order by paydate desc";
			totalPriceSql="select sum(thispaid) totalPrice from customerpayment";
			
		}else{
			totalCountSql="select count(*) from customerpayment where customerid='"+companyId+"'";
			customerPaySql="select customerid,C.companyname,C.connector,paydate,thispaid from" +
			"(select A.*,rownum row_num from(select EM.* from customerpayment EM where customerid='"+companyId+"' order by paydate desc) A " +
			"where rownum<="+pageSize*pageNow+" order by paydate desc) B " +
					"left join customer C on C.companyid=B.customerid " +
					"where row_num>="+(pageSize*(pageNow-1)+1)+" order by paydate desc";
			totalPriceSql="select sum(thispaid) totalPrice from customerpayment where customerid='"+companyId+"'";
		}
		
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();	
		}
		List<CustomerPayment> resultList=new ArrayList<CustomerPayment>();
		try {
			resultList=Sqlhelper.exeQueryList(customerPaySql, null, CustomerPayment.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		String totalPrice="";
		try {
			totalPrice=Sqlhelper.exeQueryString(totalPriceSql, null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"data\":"+json+",\"totalPrice\":"+totalPrice+"}";
		response.getWriter().append(json).flush();
		System.out.println(json);
		
	}

}
