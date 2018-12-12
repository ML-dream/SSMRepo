package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.Sqlhelper;

public class doEditCheckinServlet extends HttpServlet {


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
		String checkin_date=(String) datamap.get("checkin_date");
		String checksheet_id=(String) datamap.get("checksheet_id");
		String company_id=(String) datamap.get("company_id");
		String company_name=(String) datamap.get("company_name");
		String deptid=(String) datamap.get("deptid");
		String deptname=(String) datamap.get("deptname");
		String checkin_kind=(String) datamap.get("checkin_kind");
		String warehouse_id=(String) datamap.get("warehouse_id");
		String warehouse_name=(String) datamap.get("warehouse_name");
		String examine=(String) datamap.get("examine");
		String examine_name=(String) datamap.get("examine_name");
		String admin=(String) datamap.get("admin");
		String admin_name=(String) datamap.get("admin_name");
		String delivery=(String) datamap.get("delivery");
		String delivery_name=(String) datamap.get("delivery_name");
		String sql="update mycheckin set checkin_date=to_date('"+checkin_date+"','yyyy-mm-dd,hh24:mi:ss'),checksheet_id='"+checksheet_id+"',company_id='"+company_id+"',checkin_kind='"+checkin_kind+"'," +
				"deptid='"+deptid+"',warehouse_id='"+warehouse_id+"',examine='"+examine+"',admin='"+admin+"',delivery='"+delivery+"' where checksheet_id='"+checksheet_id+"'";
	
		try{
		Sqlhelper.executeUpdate(sql, null);
		String json = "{\"result\":\"操作成功!\"}";
		response.getWriter().append(json).flush();
		}catch(Exception e){
			String json = "{\"result\":\"操作失败!\"}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
	}

}
