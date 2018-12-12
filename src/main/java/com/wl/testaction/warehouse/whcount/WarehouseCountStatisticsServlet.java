package com.wl.testaction.warehouse.whcount;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.WarehouseCountStatistics;
import com.wl.forms.payStatistics;
import com.wl.tools.Sqlhelper;

public class WarehouseCountStatisticsServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public WarehouseCountStatisticsServlet() {
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
		String warehouseId=request.getParameter("warehouseId");
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		int totalCount=0;
		String totalCountSql="";
		String Sql="";
		
		List<payStatistics> maintenancedateList=new ArrayList<payStatistics>();
		String dateSql="select companyid,max(maintenancedate) maintenanceDate from paystatistics where maintenancedate<=to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') " +
				"group by companyid";
		
		
		
		
		
		List<WarehouseCountStatistics>resultList=new ArrayList<WarehouseCountStatistics>();
		
		
		
//		if(warehouseId.equals("")){
//			totalCountSql="select count(*) from whcountstastic";
//			Sql="select countdate,warehouseid,C.warehouse_Name,itemid,itemname,spec,begincountprice,begincountnum,unit,checkinnum," +
//					"checkoutnum,endcountnum from(select A.*,rownum row_num from(select EM.* from whcountstastic EM order by itemid asc) A " +
//					"where rownum<="+pageSize*pageNow+" order by itemid) B " +
//							"left join warehouse C on C.warehouse_id=B.warehouseid " +
//							"where row_num>="+(pageSize*(pageNow-1)+1)+"";
//		}
//		
//		
//		
//		try{
//			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
//		try{
//			resultList=Sqlhelper.exeQueryList(Sql, null,WarehouseCountStatistics.class);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
	}

}
