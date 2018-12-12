package com.wl.testaction.warehouse.salesstatement;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Checkout;
import com.wl.forms.taxRate;
import com.wl.tools.Sqlhelper;

public class SalesStatementStatisticsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SalesStatementStatisticsServlet() {
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
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		
		
		
		String rateSql="select valueaddedrate,rate from taxrate";
		taxRate taxRateBean=new taxRate();
		try{
			taxRateBean=Sqlhelper.exeQueryBean(rateSql, null, taxRate.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		double valueAddedRate=taxRateBean.getValueAddedRate();
		double rate=taxRateBean.getRate();
		
		
		String viewSql="create or replace view salesstatementview as select A.companyid,sum(price) totalprice," +
						"sum(price)/"+rate+" price,sum(price)/"+rate+"*"+valueAddedRate+" tax from mycheckout A "+
						"left join mycheckout_detl C on C.checksheet_id=A.checksheet_id " +
						"where checkout_date between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') " +
						"and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss') " +
						"group by A.companyid";
		try{
			Sqlhelper.executeUpdate(viewSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String CheckoutSql="select B.companyid,C.companyname,cast(price as number(12,2)) price,cast(tax as number(12,2)) tax,cast(totalprice as number(12,2)) totalprice from(select A.*,rownum row_num from" +
				"(select EM.* from salesstatementview EM order by companyid) A where rownum<="+pageSize*pageNow+" order by companyid) B " +
						"left join customer C on C.companyid=B.companyid " +
						"where row_num >="+(pageSize*(pageNow-1)+1)+"";
		
		List<Checkout> resultList=new ArrayList<Checkout>();
		try{
			resultList=Sqlhelper.exeQueryList(CheckoutSql, null, Checkout.class);
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
		totalCount=resultList.size();
		
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
		System.out.println(json);
		
	}

}
