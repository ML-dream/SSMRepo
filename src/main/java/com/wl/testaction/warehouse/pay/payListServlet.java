package com.wl.testaction.warehouse.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.CheckoutDetl;
import com.wl.forms.pr;
import com.wl.tools.Sqlhelper;

public class payListServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public payListServlet() {
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
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		int totalCount=0;
//		String totalPrice="";
		String Sql="";
		String viewSql="";
		String totalPriceSql="";
		String totalCountSql="";
		
		
		if(startDate.equals("")||endDate.equals("")){
			
			viewSql="create or replace view prview as select A.prsheetid,cast(sum(B.price) as number(12,2)) price from pr A " +
					"left join prdetail B on B.prsheetid=A.prsheetid " +
					"group by A.prsheetid order by A.prsheetid";
			
			totalCountSql="select count(*) from pr";
			
			Sql="select B.prsheetid,B.customerid,D.companyname customerName,payTerm,cast(C.price as number(12,2)) price from " +
					"(select A.*,rownum row_num from (select EM.* from pr EM order by prsheetid) A where rownum <="+pageNow*pageSize+" order by prsheetid) B " +
							"left join prview C on C.prsheetid=B.prsheetid " +
							"left join supplier D on D.companyid=B.customerid " +
							"where row_num>="+(pageSize*(pageNow-1)+1)+" order by prsheetid";
			
			totalPriceSql="select sum(price) totalprice from prdetail";
//			Sql="select B.prsheetid,B.customerid,D.companyname customerName,payTerm,cast(sum(C.price) as number(18,2)) price from " +
//				"(select A.*,rownum row_num from (select EM.* from pr EM order by prsheetid) A where rownum <="+pageNow*pageSize+") B " +
//					"left join prdetail C on C.prsheetid=B.prsheetid " +
//					"left join supplier D on D.companyid=B.customerid " +
//					"where row_num>="+(pageSize*(pageNow-1)+1)+" " +
//					"group by B.prsheetid,B.customerid,D.companyname,payTerm";
		}else{
			viewSql="create or replace view prview as select A.prsheetid,cast(sum(B.price) as number(12,2)) price from pr A " +
			"left join prdetail B on B.prsheetid=A.prsheetid " +
			"group by A.prsheetid order by A.prsheetid";
			
			totalCountSql="select count(*) from pr where payterm between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') " +
				"and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss')";
			
			Sql="select B.prsheetid,B.customerid,D.companyname customerName,payTerm,cast(C.price as number(12,2)) price from " +
			"(select A.*,rownum row_num from (select EM.* from pr EM where payterm between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') " +
				"and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss') order by prsheetid ) A where rownum <="+pageNow*pageSize+") B " +
				"left join prview C on C.prsheetid=B.prsheetid " +
				"left join supplier D on D.companyid=B.customerid " +
				"where row_num>="+(pageSize*(pageNow-1)+1)+" order by prsheetid";
			
			totalPriceSql="select to_char(sum(price),'9999999999.99') totalprice from prdetail A " +
					"left join pr B on B.prsheetid=A.prsheetid " +
					"where B.payterm between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') " +
					"and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss')";
		}
		
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		List<pr> resultList=new ArrayList<pr>();
		try{
			Sqlhelper.executeUpdate(viewSql, null);
			resultList=Sqlhelper.exeQueryList(Sql, null, pr.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String totalprice="";
		try{
			totalprice=Sqlhelper.exeQueryString(totalPriceSql, null);
		}catch(Exception e){
			
		}
		
//		double totalprice=0;
//		for(int i=0,l=resultList.size();i<l;i++){
//			pr prprice=resultList.get(i);
//			totalprice+=prprice.getPrice();
//		}
//		
//		String totalPriceSql="select sum(M.price) from (select B.prsheetid,B.customerid,B.payterm,C.price,D.companyname customerName from pr B " +
//				"left join prdetail C on C.prsheetid=B.prsheetid " +
//				"left join supplier D on D.companyid=B.customerid order by prsheetid ) M";
//		try{
//			totalPrice=Sqlhelper.exeQueryString(totalPriceSql, null);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		

		
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+",\"totalPrice\":"+totalprice+"}";
		response.getWriter().append(json).flush();
		System.out.println(json);
	
	}

}
