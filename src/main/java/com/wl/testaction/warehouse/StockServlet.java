package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.StockInfo;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class StockServlet extends HttpServlet {

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

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		int pageNo=0;
	    int countPerPage=20;
	    int totalCount = 0;
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    String itemName=request.getParameter("itemName");
	    String totalCountSql="";
	    String sql="";
	    if(itemName.equals("")){
	    	totalCountSql = "select count(*) from stock";
		    sql="select ITEM_ID,ITEM_NAME,B.WAREHOUSE_ID,STOCK_ID,STOCK_NUM,UNIT,ITEM_TYPE,SPEC,UNITPRICE,C.WAREHOUSE_NAME warehouseName," +
	 		"D.ITEM_TYPEDESC itemTypeDesc from (select A.*,rownum row_num from (select EM.* from stock EM order by nvl(stock_num,0) asc,item_id asc) A where rownum<="+(pageNo*countPerPage)+" " +
	 				"order by nvl(stock_num,0) asc,item_id asc) B left join warehouse C on C.warehouse_id=B.warehouse_id " +
	 				"left join itemtype D on D.item_typeid=B.item_type " +
	 				"where row_num>="+(countPerPage*(pageNo-1)+1)+" order by nvl(stock_num,0) asc,item_id asc";
	    }else{
	    	totalCountSql = "select count(*) from stock where item_name like '%"+itemName+"%'";
		    sql="select ITEM_ID,ITEM_NAME,B.WAREHOUSE_ID,STOCK_ID,STOCK_NUM,UNIT,ITEM_TYPE,SPEC,UNITPRICE,C.WAREHOUSE_NAME warehouseName," +
	 		"D.ITEM_TYPEDESC itemTypeDesc from (select A.*,rownum row_num from (select EM.* from stock EM where item_name like '%"+itemName+"%' order by nvl(stock_num,0) asc,item_id asc) A where rownum<="+(pageNo*countPerPage)+" " +
	 				"order by nvl(stock_num,0) asc, item_id asc ) B left join warehouse C on C.warehouse_id=B.warehouse_id " +
	 				"left join itemtype D on D.item_typeid=B.item_type " +
	 				"where row_num>="+(countPerPage*(pageNo-1)+1)+" order by  nvl(stock_num,0) asc, item_id asc";
	    }
	    
	    try {
		    totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		 
		 List<StockInfo> resultList = new ArrayList<StockInfo>();
		 try{
	     resultList=Sqlhelper.exeQueryList(sql, null, StockInfo.class);
	    
	  	}catch(Exception e){
	  	e.printStackTrace();
	  	}
	  	String json = PluSoft.Utils.JSON.Encode(resultList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	   
	    
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

		this.doGet(request, response);
	}

}
