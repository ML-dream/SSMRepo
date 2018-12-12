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

public class showCompanyPayServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public showCompanyPayServlet() {
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
		request.setCharacterEncoding("utf-8");
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		String companyId=request.getParameter("companyId");
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		String totalCountSql="";
		String paySql="";
		String thispaidSql="";
		String hasPaid="";
		if(startDate.equals("")&&endDate.endsWith("")){
			totalCountSql="select count(*) from customerpayment where customerid='"+companyId+"'";
			paySql="select paydate,thispaid from(select A.*,rownum row_num from(select EM.* from customerpayment EM where " +
					"customerid='"+companyId+"' order by paydate) A where rownum<="+(pageSize*pageNow)+" order by paydate)B where " +
					"row_num >="+(pageSize*(pageNow-1)+1)+" order by paydate";
			thispaidSql="select sum(thispaid) from customerpayment where customerid='"+companyId+"'";
		}else{
			totalCountSql="select count(*) from customerpayment where customerid='"+companyId+"' and paydate between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') " +
					"and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss')";
			paySql="select paydate,thispaid from(select A.*,rownum row_num from(select EM.* from customerpayment EM where " +
					"customerid='"+companyId+"' and paydate between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss') order by paydate) A where rownum<="+(pageSize*pageNow)+" order by paydate)B where " +
					"row_num >="+(pageSize*(pageNow-1)+1)+" order by paydate";
			thispaidSql="select sum(thispaid) from customerpayment where customerid='"+companyId+"' and paydate between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss')";
		}
		
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		List<CustomerPayment> resultList=new ArrayList<CustomerPayment>();
		try{
			resultList=Sqlhelper.exeQueryList(paySql, null, CustomerPayment.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		try{
			hasPaid=Sqlhelper.exeQueryString(thispaidSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
//		double haspaid=hasPaid==null?0:Integer.parseInt(hasPaid);
		double haspaid=hasPaid==null?0:Double.parseDouble(hasPaid);
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"haspaid\":"+haspaid+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
		System.out.println(json);
	}

}
