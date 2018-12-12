package com.wl.testaction.warehouse.PR;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.Sqlhelper;

public class doeditPrServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public doeditPrServlet() {
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
		String prDate=(String) datamap.get("prDate");
		String prSheetid=(String) datamap.get("prSheetid");
		String warehouseId=(String) datamap.get("warehouseId");
		String customerId=(String) datamap.get("customerId");
		String connector=(String) datamap.get("connector");
		String connectorTel=(String) datamap.get("connectorTel");
		String isBill=(String) datamap.get("isBill");
		String payTerm=(String) datamap.get("payTerm");
		String dutyParagraph=(String) datamap.get("dutyParagraph");
		String bank=(String) datamap.get("bank");
		String account=(String) datamap.get("account");
		String examineId=(String) datamap.get("examineId");
		String adminId=(String) datamap.get("adminIdId");
		String salesmanId=(String) datamap.get("salesmanId");
		String drawerId=(String) datamap.get("drawerId");
		String receipt=(String) datamap.get("receipt");
		
		HttpSession session=request.getSession();
		String changePerson=((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String changeTime=df.format(new Date());
		
		String prSql="update pr set warehouseId='"+warehouseId+"',customerId='"+customerId+"'," +
				"connector='"+connector+"',connectorTel='"+connectorTel+"',isbill='"+isBill+"',payTerm='"+payTerm+"'," +
				"dutyParagraph='"+dutyParagraph+"',bank='"+bank+"',account='"+account+"',examineId='"+examineId+"',adminId='"+adminId+"'," +
				"salesmanId='"+salesmanId+"',drawerId='"+drawerId+"',receipt='"+receipt+"',changeperson='"+changePerson+"',changetime=to_date('"+changeTime+"','yyyy-MM-dd,hh24:mi:ss') " +
				"where prsheetid='"+prSheetid+"'";
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
