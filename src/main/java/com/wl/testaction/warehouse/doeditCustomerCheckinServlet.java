package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class doeditCustomerCheckinServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doeditCustomerCheckinServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	@SuppressWarnings("unchecked")
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String data=request.getParameter("submitData");
		HashMap<String,String> datamap=(HashMap<String,String>) Test.JSON.Decode(data);
		String checkSheetid=datamap.get("chechSheetid");
		String checkinDate=datamap.get("checkinDate");
		String warehouseId=datamap.get("warehouseId");
		String stockId=datamap.get("stockId");
		String lot=datamap.get("lot");
		String qualityId=datamap.get("qualityId");
		String memo=datamap.get("memo");
		String deliveryId=datamap.get("deliveryId");
		String examineId=datamap.get("examineId");
		String whstaffId=datamap.get("whstaffId");
		
		HttpSession session=request.getSession();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String changeTime=df.format(new Date());
		
		String Sql="update customercheckin set checkindate=to_date('"+checkinDate+"','yyyy-MM-dd,hh24:mi:ss')," +
				"warehouseid='"+warehouseId+"',stockId='"+stockId+"',lot='"+lot+"',qualityId='"+qualityId+"'," +
				"memo='"+memo+"',deliveryId='"+deliveryId+"',examineId='"+examineId+"',whstaffId='"+whstaffId+"' where " +
				"checksheetid='"+checkSheetid+"'";
		try{
			Sqlhelper.executeUpdate(Sql, null);
			String json="{\"result\":\"操作成功！\"}";
			out.append(json).flush();
		}catch(Exception e){
			String json="{\"result\":\"操作失败！\"}";
			out.append(json).flush();
			e.printStackTrace();
		}
		
	}

}
