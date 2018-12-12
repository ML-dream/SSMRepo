package com.wl.testaction.warehouse.customerreturncheckin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class doeditCustomerReturnDetailSerclet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doeditCustomerReturnDetailSerclet() {
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
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String sheetId=(String) datamap.get("sheetId");
		String orderId=(String) datamap.get("orderId");
		String itemId=(String) datamap.get("itemId");
		String itemName=(String) datamap.get("itemName");
		String issueNum=(String) datamap.get("issueNum");
		String spec=(String) datamap.get("spec");
		String returnNum=(String) datamap.get("returnNum");
		String unit=(String) datamap.get("unit");
		String unitPrice=(String) datamap.get("unitPrice");
		String price=(String) datamap.get("price");
		String qkprice=(String) datamap.get("qkprice");
		String returnReason=(String) datamap.get("returnReason");
		String item_id=(String) datamap.get("item_id");
		
		String sql="update customerreturndetail set itemid='"+itemId+"',itemName='"+itemName+"',issueNum='"+issueNum+"'," +
				"spec='"+spec+"',returnnum='"+returnNum+"',unit='"+unit+"',unitPrice='"+unitPrice+"',price='"+price+"'," +
						"qkprice='"+qkprice+"',returnreason='"+returnReason+"' where sheetid='"+sheetId+"' and itemid='"+item_id+"'";
		try{
			Sqlhelper.executeUpdate(sql, null);
			String json="{\"result\":\"操作成功！\",\"status\":1}";
			response.getWriter().append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"操作成功！\",\"status\":0}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}

}
