package com.wl.testaction.warehouse.RG;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Return;
import com.wl.forms.pr;
import com.wl.tools.Sqlhelper;

public class ShowReServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ShowReServlet() {
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
		request.setCharacterEncoding("utf-8");
		int pageNo=0;
	    int countPerPage=20;
	    int totalCount = 0;
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    String sheetid=request.getParameter("sheetid");
	    String date=request.getParameter("date");
	    String warehouseId=request.getParameter("warehouseId");
	    String customerId=request.getParameter("customerId");
	    String examineId=request.getParameter("examineId");
	    String adminId=request.getParameter("adminId");
	    String salesmanId=request.getParameter("salesmanId");
	    String drawerId=request.getParameter("drawerId");
	    String totalCountSql = "select count(*) from returngood where resheetid like '"+sheetid+"%' and to_char(redate,'yyyy-mm-dd,hh24:mi:ss') " +
	    		"like '"+date+"%' and warehouseid like '"+warehouseId+"%' and customerid like '"+customerId+"%' and examineid like '"+examineId+"%' " +
	    		"and adminid like '"+adminId+"%' and salesmanid like '"+salesmanId+"%' and drawerid like '"+drawerId+"%'";
	    try {
	    	totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String reSql="select redate,resheetid,warehouseid,customerid,B.connector,B.connectortel,B.telephone,retype,B.account,paymethod,receipt," +
				"examineid,adminid,salesmanid,drawerid,C.warehouse_name warehouseName,D.companyname customerName,E.METHOD method,F.receiptname receiptName," +
				"G.staff_name examineName,H.staff_name adminName,K.staff_name salesmanName,P.staff_name drawerName from (select A.*,rownum row_num from(select EM.* from " +
				"returngood EM where resheetid like '"+sheetid+"%' and to_char(redate,'yyyy-mm-dd,hh24:mi:ss') " +
	    		"like '"+date+"%' and warehouseid like '"+warehouseId+"%' and customerid like '"+customerId+"%' and examineid like '"+examineId+"%' " +
	    		"and adminid like '"+adminId+"%' and salesmanid like '"+salesmanId+"%' and drawerid like '"+drawerId+"%' order by resheetid ) A where rownum<="+(countPerPage*pageNo)+" " +
	    				"order by resheetid ) B left join warehouse C on C.warehouse_id=B.warehouseid " +
	    				"left join supplier D on D.companyid=B.customerid " +
	    				"left join paymethod E on E.id=B.paymethod " +
	    				"left join receipt F on F.receiptid=B.receipt " +
	    				"left join employee_info G on G.staff_code=B.examineid " +
	    				"left join employee_info H on H.staff_code=B.adminid " +
	    				"left join employee_info K on K.staff_code=B.salesmanid " +
	    				"left join employee_info P on P.staff_code=B.drawerid " +
	    				"where row_num>="+(countPerPage*(pageNo-1)+1)+"";
		List<Return> resultList=new ArrayList<Return>();
		try{
			resultList=Sqlhelper.exeQueryList(reSql, null, Return.class);
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
