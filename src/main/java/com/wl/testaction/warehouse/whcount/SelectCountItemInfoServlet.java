package com.wl.testaction.warehouse.whcount;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.StockInfo;
import com.wl.tools.Sqlhelper;

public class SelectCountItemInfoServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public SelectCountItemInfoServlet() {
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
	    String warehouseId=request.getParameter("warehouseId");
	    String itemName=request.getParameter("itemName");

	    String totalCountSql = "select count(*) from stock where warehouse_id='"+warehouseId+"' and item_name like '%"+itemName+"%'";
	    String	Sql="select B.ITEM_ID itemId,B.ITEM_NAME itemName,B.SPEC spec,B.UNIT unit,STOCK_ID stockId,STOCK_NUM stockNum,T.ITEM_PRICE unitPrice from " +
			"(select A.*,rownum from (select EM.* from stock EM where warehouse_id='"+warehouseId+"' and item_name like '%"+itemName+"%' order by item_id) A where rownum<='"+(countPerPage*pageNo)+"' order by item_id) B " +
			"left join item T on T.item_id=B.item_id " +
			"where rownum>='"+(countPerPage*(pageNo-1))+"'";
	    
		
		    try {
		    	totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, null);
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		List<StockInfo> resultList=new ArrayList<StockInfo>();
		try{
			resultList=Sqlhelper.exeQueryList(Sql, null, StockInfo.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		out.append(json).flush();
		System.out.println(json);
	}

}
