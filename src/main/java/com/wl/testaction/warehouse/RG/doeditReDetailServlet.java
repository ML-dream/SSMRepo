package com.wl.testaction.warehouse.RG;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;
import com.wl.tools.Stockcl;

public class doeditReDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doeditReDetailServlet() {
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
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String reSheetid=(String) datamap.get("reSheetid");
		String itemId=(String) datamap.get("itemId");
		String itemName=(String) datamap.get("itemName");
		String spec=(String) datamap.get("spec");
		String unit=(String) datamap.get("unit");
		String itemType=(String) datamap.get("itemType");
		String reNum=(String) datamap.get("reNum");
		String reNum1=(String) datamap.get("reNum1");
		String unitprice=(String) datamap.get("unitPrice");
		double unitPrice=unitprice.equals("")?0:Double.parseDouble(unitprice);
		String price=(String) datamap.get("price");
		String stockId=(String) datamap.get("stockId");
		String memo=(String) datamap.get("memo");
		String warehouseId=(String) datamap.get("warehouseId");
		String poSql="update redetail set spec='"+spec+"',unit='"+unit+"',itemType='"+itemType+"',reNum='"+reNum+"'," +
				"unitPrice="+unitPrice+",price='"+price+"',stockid='"+stockId+"',memo='"+memo+"' where resheetid='"+reSheetid+"' and itemId='"+itemId+"'";
		try{
			Sqlhelper.executeUpdate(poSql, null);
			if(!reNum.equals(reNum1)){
			double Num=reNum.equals("")?0:Double.parseDouble(reNum);
			double Num1=reNum1.equals("")?0:Double.parseDouble(reNum1);
			Stockcl.Stockin(itemId, itemName,spec, itemType, warehouseId, stockId, Num1,unitPrice, unit);
			Stockcl.Stockout(itemId, Num);
			
			}
			String json = "{\"result\":\"操作成功!\"}";
			response.getWriter().append(json).flush();
		}catch(Exception e){
			String json = "{\"result\":\"操作失败!\"}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
	}

	}

}
