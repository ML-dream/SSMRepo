package com.wl.testaction.warehouse.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.payStatistics;
import com.wl.tools.Sqlhelper;

public class showAddSupplierDataMaintenanceServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public showAddSupplierDataMaintenanceServlet() {
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
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		
		
		
		String viewSql="create or replace view supPaystaServletView as select companyid,min(maintenancedate) maintenanceDate from supplierpaystatistics group by companyid";

		try {
			Sqlhelper.executeUpdate(viewSql, null);
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		List<payStatistics> maintenancedateList=new ArrayList<payStatistics>();
		
		String dateSql="select companyid,maintenanceDate from (select A.*,rownum rn from (select EM.* from supPaystaServletView EM order by companyid asc) A " +
		"where rownum<="+pageSize*pageNow+" order by companyid) B where rn >="+(pageSize*(pageNow-1)+1)+"";

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
		
		List<payStatistics> resultList=new ArrayList<payStatistics>();
		
		for(int i=0,l=maintenancedateList.size();i<l;i++){
			
			payStatistics paystatisitcs=new payStatistics();
			payStatistics customerpay=new payStatistics();
			paystatisitcs=maintenancedateList.get(i);
			String maintenanceDate=paystatisitcs.getMaintenanceDate();
			String companyId=paystatisitcs.getCompanyId();
			
			String Sql="select maintenancedate,A.companyid,endpayment,B.companyname from supplierpaystatistics A " +
					"left join supplier B on B.companyid=A.companyid " +
					"where A.companyid='"+companyId+"' and maintenancedate=to_date('"+maintenanceDate+"','yyyy-mm-dd,hh24:mi:ss')";
					
			
			try{
				customerpay=Sqlhelper.exeQueryBean(Sql, null, payStatistics.class);
			}catch(Exception e){
				e.printStackTrace();
			}
			System.out.println(customerpay);
			resultList.add(customerpay);
			
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
		System.out.println(json);
	}

}
