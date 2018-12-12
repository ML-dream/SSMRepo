package com.wl.testaction.warehouse.whcount;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class addWarehouseCountServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public addWarehouseCountServlet() {
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
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String maintenanceDate=(String) datamap.get("maintenanceDate");
		String warehouseId=(String) datamap.get("warehouseId");
		String itemId=(String) datamap.get("itemId");
		String itemName=(String) datamap.get("itemName");
		String spec=(String)datamap.get("spec");
		double beginCountPrice=StringUtil.isNullOrEmpty((String) datamap.get("beginCountPrice"))?0:Double.parseDouble((String) datamap.get("beginCountPrice"));
		double beginCountNum=StringUtil.isNullOrEmpty((String) datamap.get("beginCountNum"))?0:Double.parseDouble((String) datamap.get("beginCountNum"));
		String unit=(String) datamap.get("unit");
		double checkinNum=0;
		double checkoutNum =0;
		double endCountNum=beginCountNum;
		
		String warehouseCountSql="insert into whcountstastic (maintenancedate,warehouseid,itemid,itemName," +
				"spec,begincountprice,begincountnum,unit,checkinnum,checkoutnum,endcountnum) values " +
				"(to_date('"+maintenanceDate+"','yyyy-mm-dd,hh24:mi:ss'),'"+warehouseId+"','"+itemId+"','"+itemName+"','"+spec+"','"+beginCountPrice+"','"+beginCountNum+"'," +
						"'"+unit+"','"+checkinNum+"','"+checkoutNum+"','"+endCountNum+"')";
		try{
			Sqlhelper.executeUpdate(warehouseCountSql, null);
			String json="{\"result\":\"操作成功！\",\"status\":1}";
			response.getWriter().append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"操作失败！\",\"status\":0}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
		
		
		
	}

}
