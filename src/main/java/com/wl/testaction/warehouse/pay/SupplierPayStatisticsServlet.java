package com.wl.testaction.warehouse.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.payStatistics;
import com.wl.tools.Sqlhelper;

public class SupplierPayStatisticsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SupplierPayStatisticsServlet() {
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
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		
		String viewSql="";
		String dateSql="";
		List<payStatistics> maintenancedateList=new ArrayList<payStatistics>();
		
		
		
		if(startDate.equals("")&&endDate.equals("")){
			viewSql="create or replace view supPaystaServletView as select companyid,min(maintenancedate) maintenanceDate from supplierpaystatistics group by companyid";

			dateSql="select companyid,maintenancedate from (select A.*,rownum rn from (select EM.* from supPaystaServletView EM order by companyid asc) A " +
					"where rownum <="+pageSize*pageNow+" order by companyid asc) B where rn>="+(pageSize*(pageNow-1)+1)+"";
			
			SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			endDate=df.format(new Date());
		
		}else{
			viewSql="create or replace view supPaystaServletView as select companyid,max(maintenancedate) maintenanceDate from supplierpaystatistics " +
			"where maintenancedate<=to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') group by companyid";
			dateSql="select companyid,maintenancedate from (select A.*,rownum rn from (select EM.* from supPaystaServletView EM order by companyid asc) A " +
					"where rownum <="+pageSize*pageNow+" order by companyid asc) B where rn>="+(pageSize*(pageNow-1)+1)+"";
		
		}
		
		try {
			Sqlhelper.executeUpdate(viewSql, null);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		String totalCountSql="select count(*) from supPaystaServletView";
		
		try {
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		
		try{
			maintenancedateList=Sqlhelper.exeQueryList(dateSql, null, payStatistics.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		List<payStatistics> resultList=new ArrayList <payStatistics>();
		for(int i=0,l=maintenancedateList.size();i<l;i++){
			payStatistics paystatisitcs=new payStatistics();
			payStatistics supplierpay=new payStatistics();
			paystatisitcs=maintenancedateList.get(i);
			String maintenanceDate=paystatisitcs.getMaintenanceDate();
			String companyId=paystatisitcs.getCompanyId();
			
			String Sql="select A.companyid companyId,endpayment beginPayment,cast(B.sale as number(16,4)) sale,cast(C.thispaid as number(16,4)) thisPayment,cast(C.qualitykk as number(16,4)) qualitykk,D.companyname companyName from supplierpaystatistics A "  
				+"left join (select customerid,sum(price) sale from poView where customerid='"+companyId+"' and prdate between "
	        +"to_date('"+maintenanceDate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss') group by customerid) B "
	        +"on B.customerid=A.companyid "
	        +"left join (select companyid,sum(thispaid) thispaid,sum(qualitykk) qualitykk from supplierpayment where companyid='"+companyId+"' and paydate between "
	        +"to_date('"+maintenanceDate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss') group by companyid) C "
	        +"on C.companyid=A.companyid " +
	        		"left join supplier D on D.companyid=A.companyid " +
	        		"where A.companyid='"+companyId+"' and maintenancedate=to_date('"+maintenanceDate+"','yyyy-mm-dd,hh24:mi:ss')";
			try{
				supplierpay=Sqlhelper.exeQueryBean(Sql, null, payStatistics.class);
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println(supplierpay);
			resultList.add(supplierpay);
			
		}
		
		for(int i=0,l=resultList.size();i<l;i++){
			payStatistics customerPayment=new payStatistics();
			customerPayment=resultList.get(i);
			String companyId=customerPayment.getCompanyId();
			double beginPayment=customerPayment.getBeginPayment();
			double sale=customerPayment.getSale();
			double thisPayment=customerPayment.getThisPayment();
			double qualitykk=customerPayment.getQualitykk();
			double endPayment=beginPayment+sale-thisPayment-qualitykk;
			customerPayment.setEndPayment(endPayment);
			String sql="insert into supplierpaystatistics (maintenancedate,companyid,beginpayment,purchase,thispayment,endpayment) values(to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss'),'"+companyId+"'," +
					"'"+beginPayment+"','"+sale+"','"+thisPayment+"','"+endPayment+"')";
			try{
				Sqlhelper.executeUpdate(sql, null);
			}catch(Exception e){
				e.printStackTrace();
			}
			
		}
		
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
		System.out.println(json);
	}

}
