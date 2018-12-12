package com.wl.testaction.warehouse.PR;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.pr;
import com.wl.tools.Sqlhelper;

public class LoadPrUnpassSheet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadPrUnpassSheet() {
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
//    采购收货审核不通过加载  
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		
		int totalCount = 0;
		String totalCountSql= "";
		String psSql = "";
		
		psSql="select distinct PRDATE,PRSHEETID,WAREHOUSEID,W.WAREHOUSE_NAME warehouseName,CUSTOMERID,C.COMPANYNAME customerName," +
				"B.CONNECTOR,B.CONNECTORTEL,B.PAYTERM,B.DUTYPARAGRAPH,B.BANK,B.ACCOUNT,EXAMINEID,ADMINID,SALESMANID,DRAWERID," +
				"D.STAFF_NAME examineName,E.STAFF_NAME adminName,F.STAFF_NAME salesmanName,G.STAFF_NAME drawerName,ISBILL,RECEIPT " +
				"from ((select a.*,rownum rn from prdetail t left join pr a on a.prsheetid = t.prsheetid where t.status='0' order by PRDATE desc)B " +
				" left join warehouse W on W.warehouse_id=B.warehouseid " +
					"left join supplier C on C.companyid=B.customerid " +
					"left join employee_info D on D.staff_code=B.examineid " +
					"left join employee_info E on E.staff_code=B.adminid " +
					"left join employee_info F on F.staff_code=B.salesmanid " +
					"left join employee_info G on G.staff_code=B.drawerid ) " +
					"where rn>"+min+" and rn <="+max+" " +
					"order by PRDATE desc " ;
		
		totalCountSql = "select count(*) from (" +
				"select distinct a.prsheetid from prdetail t left join pr a on a.prsheetid = t.prsheetid where t.status='0' )";
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

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
