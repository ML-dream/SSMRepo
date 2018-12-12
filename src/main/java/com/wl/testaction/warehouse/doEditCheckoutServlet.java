package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class doEditCheckoutServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	
	}

	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String Status=(String) datamap.get("status");
		int status=Integer.parseInt(Status);
		if(status==1){
			String json="{\"result\":\"已审核通过，不能修改！\"}";
			out.append(json).flush();
		}else{
			String checkout_date=(String) datamap.get("checkout_date");
			String checksheet_id=(String) datamap.get("checksheet_id");
			String order_id=(String) datamap.get("order_id");
			String companyId=(String) datamap.get("companyId");
//			String companyName=(String) datamap.get("companyName");
			String warehouse_id=(String) datamap.get("warehouse_id");
			String connector=(String) datamap.get("connector");
			String connectortel=(String) datamap.get("connectortel");
			String delivery=(String) datamap.get("delivery");
			String orderStatus=(String) datamap.get("orderStatus");
			String checkoutType=(String) datamap.get("checkoutType");
			String warehouseId=(String) datamap.get("warehouse_id");
			String sql="update mycheckout set status=0,checkout_date=to_date('"+checkout_date+"','yyyy-mm-dd,hh24:mi:ss'),warehouse_id='"+warehouse_id+"',connector='"+connector+"'," +
				"connectortel='"+connectortel+"',companyid='"+companyId+"',delivery='"+delivery+"',order_id='"+order_id+"',checkouttype='"+checkoutType+"' where checksheet_id='"+checksheet_id+"'";
			String orderSql="update orders set order_status='"+orderStatus+"' where order_id='"+order_id+"'";
			
			try{
				Sqlhelper.executeUpdate(sql, null);
				Sqlhelper.executeUpdate(orderSql, null);
				String json = "{\"result\":\"操作成功!\"}";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
			}catch(Exception e){
				String json = "{\"result\":\"操作失败!\"}";
				response.setCharacterEncoding("UTF-8");
				response.getWriter().append(json).flush();
				e.printStackTrace();
			}
		}
	}

}
