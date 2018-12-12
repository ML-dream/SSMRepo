package com.wl.testaction.warehouse.PR;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Poplan;
import com.wl.forms.pr;
import com.wl.tools.Sqlhelper;

public class ShowPrServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowPrServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		int pageNo=0;
	    int countPerPage=20;
	    int totalCount = 0;
	    String sheetid=request.getParameter("sheetid");
	    String date=request.getParameter("date");
	    //String warehouseId=request.getParameter("warehouseId");
	    String customerId=request.getParameter("customerId");
	    String isbill=request.getParameter("isbill");
	    //String adminId=request.getParameter("adminId");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    String totalCountSql = "";
	    String psSql="";

		totalCountSql="select count(*) from pr where prsheetid like '"+sheetid+"%' and to_char(prdate ,'yyyy-mm-dd,hh24:mi:ss') like '"+date+"%' " +
//				"and warehouseid like '"+warehouseId+"%' and customerid like '"+customerId+"%' and isbill like '"+isbill+"%' " +
				"and customerid like '"+customerId+"%' and isbill like '"+isbill+"%' " ;
//						"and adminid like '"+adminId+"%'";
		psSql="select PRDATE,PRSHEETID,WAREHOUSEID,W.WAREHOUSE_NAME warehouseName,CUSTOMERID,C.COMPANYNAME customerName," +
					"B.CONNECTOR,B.CONNECTORTEL,B.PAYTERM,B.DUTYPARAGRAPH,B.BANK,B.ACCOUNT,EXAMINEID,ADMINID,SALESMANID,DRAWERID," +
					"D.STAFF_NAME examineName,E.STAFF_NAME adminName,F.STAFF_NAME salesmanName,G.STAFF_NAME drawerName,ISBILL,RECEIPT " +
					"from (select A.*,rownum row_num from (select EM.* from pr EM where prsheetid like '"+sheetid+"%' and to_char(prdate ,'yyyy-mm-dd,hh24:mi:ss') like '"+date+"%' " +
//				"and warehouseid like '"+warehouseId+"%' and customerid like '"+customerId+"%' and isbill like '"+isbill+"%' " +
					"and customerid like '"+customerId+"%' and isbill like '"+isbill+"%' " +
						" order by PRDATE desc,PRSHEETID desc) A where rownum<="+(pageNo*countPerPage)+" order by PRDATE desc,PRSHEETID desc) B " +
					"left join warehouse W on W.warehouse_id=B.warehouseid " +
					"left join supplier C on C.companyid=B.customerid " +
					"left join employee_info D on D.staff_code=B.examineid " +
					"left join employee_info E on E.staff_code=B.adminid " +
					"left join employee_info F on F.staff_code=B.salesmanid " +
					"left join employee_info G on G.staff_code=B.drawerid " +
					"where row_num>="+(countPerPage*(pageNo-1)+1)+" " +
							"order by PRDATE desc,PRSHEETID desc ";
		
		List<pr> resultList=new ArrayList<pr>();
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
			resultList=Sqlhelper.exeQueryList(psSql, null, pr.class);
		}catch (Exception e) {
			e.printStackTrace();
		}
		 String json = PluSoft.Utils.JSON.Encode(resultList);
		 json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		 //response.setCharacterEncoding("UTF-8");
		 response.getWriter().append(json).flush();
		 System.out.println(json);
		
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request,response);
		
	}

}
