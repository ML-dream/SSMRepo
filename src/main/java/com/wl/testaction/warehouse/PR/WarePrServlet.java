package com.wl.testaction.warehouse.PR;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.forms.pr;
import com.wl.tools.Sqlhelper;

public class WarePrServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WarePrServlet() {
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
//		库房 采购收货单
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
		System.out.println(this.getClass().getName());
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		HttpSession session=request.getSession();
		String staff=((User)session.getAttribute("user")).getStaffCode(); //仓库只能看到各自的数据
		
		
		request.setCharacterEncoding("utf-8");
	    int totalCount = 0;
	    
	    int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		
	    String sheetid=request.getParameter("sheetid");
	    String date=request.getParameter("date");
	    String warehouseId=request.getParameter("warehouseId");
	    String customerId=request.getParameter("customerId");
	    String isbill=request.getParameter("isbill");
	    
	    String totalCountSql = "";
	    String psSql="";

		totalCountSql="select count(*) from(select distinct b.PRSHEETID from(" +
				"(select distinct EM.PRSHEETID from pr EM where prsheetid like '%' and to_char(prdate ,'yyyy-mm-dd,hh24:mi:ss') like '%' and customerid like '%' and isbill like '%'  " +
				"order by prsheetid) B " +
				" left join prdetail d on d.prsheetid= b.prsheetid" +
				" left join warehouse W on W.warehouse_id=d.warehouseid )"+
				" where w.emp_id='"+staff+"' and d.status = '3')";
//		psSql="select distinct PRDATE,b.PRSHEETID,CUSTOMERID,C.COMPANYNAME customerName," +		//d.warehouseid,W.WAREHOUSE_NAME warehouseName,
//				"B.CONNECTOR,B.CONNECTORTEL,B.PAYTERM,B.DUTYPARAGRAPH,B.BANK,B.ACCOUNT,EXAMINEID,SALESMANID,DRAWERID,G.STAFF_NAME drawerName,ISBILL,RECEIPT " +
//				"from((select EM.*,rownum rn from pr EM where prsheetid like '%' and to_char(prdate ,'yyyy-mm-dd,hh24:mi:ss') like '%' and customerid like '%' and isbill like '%'  order by prsheetid desc) B " +
//				" left join prdetail d on d.prsheetid= b.prsheetid" +
//				" left join warehouse W on W.warehouse_id=d.warehouseid" +
//				" left join supplier C on C.companyid=B.customerid " +
//				" left join employee_info G on G.staff_code=B.drawerid )" +
//				"where w.emp_id='"+staff+"' and d.status = '3' and rn>"+min+" and rn <="+max+"  order by prsheetid desc";
		psSql = "select distinct rn,PRDATE,b.PRSHEETID,CUSTOMERID,C.COMPANYNAME customerName,B.CONNECTOR,B.CONNECTORTEL,B.PAYTERM,B.DUTYPARAGRAPH,B.BANK," +
				"B.ACCOUNT,EXAMINEID,SALESMANID,DRAWERID,G.STAFF_NAME drawerName,ISBILL,RECEIPT from(" +
				"(select ea.*,rownum rn from(select distinct EM.* from pr EM  " +
				" left join prdetail d on d.prsheetid= em.prsheetid " +
				" left join warehouse W on W.warehouse_id=d.warehouseid" +
				" where  em.prsheetid like '%' and to_char(prdate ,'yyyy-mm-dd,hh24:mi:ss') like '%' and customerid like '%' and isbill like '%'" +
				" and w.emp_id='"+staff+"' and d.status = '3'" +
				" order by em.prsheetid desc)ea) B  left join supplier C on C.companyid=B.customerid  left join employee_info G on G.staff_code=B.drawerid )" +
				" where rn>"+min+" and rn <="+max+"  order by prsheetid desc";
		
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
