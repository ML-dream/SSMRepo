package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.StockInfo;
import com.wl.tools.ExportExcelUtil;
import com.wl.tools.Sqlhelper;

public class StockListExcelServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public StockListExcelServlet() {
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
	    String warehouseId=request.getParameter("warehouseId");
	    String itemName=request.getParameter("itemName");
	    String totalCountSql="";
	    String sql="";
	    if(itemName.equals("")){
	    	totalCountSql ="select count(*) from stock where warehouse_id='"+warehouseId+"'";
		    sql= "select ITEM_ID,ITEM_NAME,B.WAREHOUSE_ID,STOCK_ID,STOCK_NUM,UNIT,ITEM_TYPE,SPEC,UNITPRICE,C.WAREHOUSE_NAME warehouseName,D.ITEM_TYPEDESC itemTypeDesc from " +
	 		"(select A.*,ROWNUM row_num from (select EM.* from stock EM where EM.warehouse_id='"+warehouseId+"' order by nvl(stock_num,0) asc,item_id asc) A " +
	 		"order by nvl(stock_num,0) asc,item_id asc) B " +
			"left join itemtype D on D.item_typeid=B.item_type " +
			"left join warehouse C on C.warehouse_id=B.warehouse_id " +
			"order by nvl(stock_num,0) asc,item_id asc";
	    }else{
	    	totalCountSql ="select count(*) from stock where warehouse_id='"+warehouseId+"' and item_name like '%"+itemName+"%'";
		    sql= "select ITEM_ID,ITEM_NAME,B.WAREHOUSE_ID,STOCK_ID,STOCK_NUM,UNIT,ITEM_TYPE,SPEC,UNITPRICE,C.WAREHOUSE_NAME warehouseName,D.ITEM_TYPEDESC itemTypeDesc from " +
	 		"(select A.*,ROWNUM row_num from (select EM.* from stock EM where EM.warehouse_id='"+warehouseId+"' and item_name like '%"+itemName+"%' order by nvl(stock_num,0) asc,item_id asc) " +
	 		"A order by nvl(stock_num,0) asc,item_id asc) B " +
			"left join itemtype D on D.item_typeid=B.item_type " +
			"left join warehouse C on C.warehouse_id=B.warehouse_id " +
			"order by nvl(stock_num,0) asc,item_id asc";
	    }
	    
		 List<StockInfo> resultList = new ArrayList<StockInfo>();
		 try{
	     resultList=Sqlhelper.exeQueryList(sql, null, StockInfo.class);
	    
	  	}catch(Exception e){
	  	e.printStackTrace();
	  	}
	  
	  	LinkedHashMap<String, String> liebiaoxiang = new LinkedHashMap<String, String>();
		liebiaoxiang.put("itemId", "编号");
		liebiaoxiang.put("itemName", "名称");
		liebiaoxiang.put("spec", "规格");
		liebiaoxiang.put("itemTypeDesc", "类型");
		liebiaoxiang.put("warehouseName", "库房");
		liebiaoxiang.put("stockId", "库位");
		liebiaoxiang.put("stockNum", "库存量");
		liebiaoxiang.put("unitPrice", "单价");
		liebiaoxiang.put("unit", "单位");
	
		List<Integer> columnWidth = new ArrayList<Integer>();
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
		columnWidth.add(5500);
	

		
		
		
		ExportExcelUtil.exportExcel(request, response, liebiaoxiang, columnWidth, resultList);
	  	
	}

}
