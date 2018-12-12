package com.wl.testaction.warehouse.RG;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class doeditReServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doeditReServlet() {
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
		String reDate=(String) datamap.get("reDate");
		String reSheetid=(String) datamap.get("reSheetid");
		String warehouseId=(String) datamap.get("warehouseId");
		String customerId=(String) datamap.get("customerId");
		String connector=(String) datamap.get("connector");
		String connectorTel=(String) datamap.get("connectorTel");
		String telephone=(String) datamap.get("telephone");
		String reType=(String) datamap.get("reType");
		String account=(String) datamap.get("account");
		String payMethod=(String) datamap.get("payMethod");
		String receipt=(String) datamap.get("receipt");
		String examineId=(String) datamap.get("examineId");
		String adminId=(String) datamap.get("adminId");
		String salesmanId=(String) datamap.get("salesmanId");
		String drawerId=(String) datamap.get("drawerId");
		String prSql="update returngood set warehouseId='"+warehouseId+"',customerId='"+customerId+"'," +
		"connector='"+connector+"',connectorTel='"+connectorTel+"',telephone='"+telephone+"',reType='"+reType+"'," +
		"account='"+account+"',paymethod='"+payMethod+"',examineId='"+examineId+"',adminId='"+adminId+"'," +
		"salesmanId='"+salesmanId+"',drawerId='"+drawerId+"',receipt='"+receipt+"' " +
		"where resheetid='"+reSheetid+"'";
		try{
			Sqlhelper.executeUpdate(prSql, null);
			String json="{\"result\":\"操作成功！\"}";
			response.getWriter().append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"操作失败！\"}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}

}
