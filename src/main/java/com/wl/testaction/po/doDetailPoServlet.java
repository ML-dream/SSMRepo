package com.wl.testaction.po;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class doDetailPoServlet extends HttpServlet {


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
		String po_sheetid=(String) datamap.get("po_sheetid");
		String item_id=(String) datamap.get("item_id");
		String item_name=(String) datamap.get("item_name");
		String spec=(String) datamap.get("spec");
		String unit=(String) datamap.get("unit");
		String kind=(String) datamap.get("kind");
		String usage=(String) datamap.get("usage");
		String po_num=(String) datamap.get("po_num");
		String unitprice=(String) datamap.get("unitprice");
		String price=(String) datamap.get("price");
		String memo=(String) datamap.get("memo");
		String poSql="update poplan_detl set spec='"+spec+"',unit='"+unit+"',po_num='"+po_num+"'," +
				"unitprice='"+unitprice+"',price='"+price+"',memo='"+memo+"',kind='"+kind+"',usage='"+usage+"' " +
						"where po_sheetid='"+po_sheetid+"' and item_id='"+item_id+"'";
		String statusSql="update po_plan set status='1' where po_sheetid='"+po_sheetid+"'";
		try{
			Sqlhelper.executeUpdate(poSql, null);
			Sqlhelper.executeUpdate(statusSql, null);
			String json = "{\"result\":\"操作成功!\"}";
			response.getWriter().append(json).flush();
		}catch(Exception e){
			String json = "{\"result\":\"操作失败!\"}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
		
			
		}
		
	}

}
