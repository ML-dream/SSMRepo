package com.wl.testaction.warehouse.productcheckin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;
import com.wl.tools.Stockcl;
import com.wl.tools.StockinItem;

public class doexamineDetailCheckinServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doexamineDetailCheckinServlet() {
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
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String status=request.getParameter("status");
		String checksheetId=(String) datamap.get("checksheetId");
		String productId=(String) datamap.get("productId");
		String productName=(String) datamap.get("productName");
		String spec=(String) datamap.get("spec");
		String unit=(String) datamap.get("unit");
		String unitprice=(String) datamap.get("unitPrice");
		String checkinnum=(String) datamap.get("checkinNum");
		String productType=(String) datamap.get("productType");
		String warehouseId=(String) datamap.get("warehouseId");
		String stockId=(String) datamap.get("stockId");
		String deliveryId=(String) datamap.get("deliveryId");
		String whstaffId=(String) datamap.get("whstaffId");
		String lot=(String) datamap.get("lot");
		String qualityId=(String) datamap.get("qualityId");
		String memo=(String) datamap.get("memo");
		double checkinNum=Double.parseDouble(checkinnum);
		double unitPrice=Double.parseDouble(unitprice);
		String sql="update productcheckin set warehouseid='"+warehouseId+"',stockid='"+stockId+"',deliveryid='"+deliveryId+"',status='"+status+"'," +
				"whstaffid='"+whstaffId+"',lot='"+lot+"',qualityid='"+qualityId+"',memo='"+memo+"' where checksheetid='"+checksheetId +"'";
		try{
			Sqlhelper.executeUpdate(sql, null);
			if(status.equals("1")){
				
				Stockcl.Stockin(productId,productName,spec,productType,warehouseId,stockId,checkinNum,unitPrice,unit);
				
			}
			StockinItem.ItemStockin(productId,productName,productType,spec,unitPrice,memo);
			String json="{\"result\":\"操作成功！\",\"status\":1}";
			out.append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"操作失败！\",\"status\":0";
			out.append(json).flush();
			e.printStackTrace();
		}
	}

}
