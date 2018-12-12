package com.wl.testaction.warehouse.pay;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.PoPay;
import com.wl.forms.Poplan;
import com.wl.tools.Sqlhelper;

public class ShowPayServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowPayServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doPost(request,response);
	}

 
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		int pageNow=0;
		int pageSize=20;
		int totalCount=0;
		pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		pageSize=Integer.parseInt(request.getParameter("pageSize"));
		String sheetid=request.getParameter("sheetid");
		String date=request.getParameter("date");
		String customerId=request.getParameter("customerId");
		String isbill=request.getParameter("isbill");
		String examineId=request.getParameter("examineId");
		String salesmanId=request.getParameter("salesmanId");
		String drawerId=request.getParameter("drawerId");
		String paySql="";
		String totalCountSql="";
	
			totalCountSql="select count(*) from popay where paysheetid like '"+sheetid+"%' and to_char(paydate,'yyyy-mm-dd,hh24:mi:ss') " +
					"like '"+date+"%' and customerid like '"+customerId+"%' and isbill like '"+isbill+"%' and examineid like '"+examineId+"%' " +
					"and salesmanid like '"+salesmanId+"%' and drawerid like '"+drawerId+"%'";
			paySql="select PAYDATE,PAYSHEETID,CUSTOMERID,C.COMPANYNAME customerName,B.CONNECTOR,B.CONNECTORTEL, " +
				"PAYTYPE,PAYABLE,PREPAID,PAYMENT,PAYMETHOD,D.METHOD method,B.ACCOUNT,EXAMINEID,SALESMANID,DRAWERID,ISBILL," +
				"E.STAFF_NAME examineName,F.STAFF_NAME salesmanName,G.STAFF_NAME drawerName from (select A.*,rownum row_num from " +
				"(select EM.* from popay EM where paysheetid like '"+sheetid+"%' and to_char(paydate,'yyyy-mm-dd,hh24:mi:ss') " +
				"like '"+date+"%' and customerid like '"+customerId+"%' and isbill like '"+isbill+"%' and examineid like '"+examineId+"%' " +
				"and salesmanid like '"+salesmanId+"%' and drawerid like '"+drawerId+"%' order by paysheetid ) A where rownum<="+(pageSize*pageNow)+" order by paysheetid) B " +
				"left join supplier C on C.companyid=B.customerid " +
				"left join paymethod D on D.id=B.paymethod " +
				"left join employee_info E on E.staff_code=B.examineid " +
				"left join employee_info F on F.staff_code=B.salesmanid " +
				"left join employee_info G on G.staff_code=B.drawerid " +
				"where row_num >="+(pageSize*(pageNow-1)+1)+"";
		
		List<PoPay> resultList=new ArrayList<PoPay>();
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
			resultList=Sqlhelper.exeQueryList(paySql, null, PoPay.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		 String json = PluSoft.Utils.JSON.Encode(resultList);
		 json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		 //response.setCharacterEncoding("UTF-8");
		 response.getWriter().append(json).flush();
		 System.out.println(json);
	}

}
