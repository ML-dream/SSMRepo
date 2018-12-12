package com.wl.testaction.orderManage;


import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.User;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

import cfmes.util.sql_data;

public class EditOrderCheckServlet extends HttpServlet {

	private static final long serialVersionUID = -6636466172419300443L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		
		response.setCharacterEncoding("UTF-8");
		
		HttpSession session = request.getSession();
	    String createPerson = ((User)session.getAttribute("user")).getStaffCode();
	    String changePerson = ((User)session.getAttribute("user")).getStaffCode();
	    
	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	    String createTime = df.format(new Date());
	    String changeTime = df.format(new Date());
	    
		
		//ChineseCode.toUTF8(requestValueMap.get("customer").trim());

		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
	    String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
	    String FProductId = ChineseCode.toUTF8(request.getParameter("FProductId").trim());
	    String issueNum = ChineseCode.toUTF8(request.getParameter("issueNum").trim());
	    
	    String isCaiGou = ChineseCode.toUTF8(request.getParameter("isCaiGou").trim());
	    String isWaiXie = ChineseCode.toUTF8(request.getParameter("isWaiXie").trim());
	    String isGongYi = ChineseCode.toUTF8(request.getParameter("isGongYi").trim());
	    
	    String memo = ChineseCode.toUTF8(request.getParameter("memo"));
		
//		String updateOrderSql="update order_detail set " +
//				"isCaiGou='"+isCaiGou+"',isWaiXie='"+isWaiXie+"',isGongYi='"+isGongYi+"'," +
//				"memo='"+memo+"',changeTime=to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),changePerson='"+changePerson+"' " +
//				"where ORDER_ID='"+orderId+"' and PRODUCT_ID='"+productId+"' and FPRODUCT_ID='"+FProductId+"' and issue_num='"+issueNum+"'";
	    String updateOrderSql="update order_detail set " +
			"isCaiGou=?,isWaiXie=?,isGongYi=?," +
			"memo=?,changeTime=to_date(?,'yyyy-mm-dd,hh24:mi:ss'),changePerson=? " +
			"where ORDER_ID=? and PRODUCT_ID=? and FPRODUCT_ID=? and issue_num=? ";
	    
	    String updateItemSql = "update item set isCaiGou=?,isWaiXie=?,isGongYi=? " +
	    		"where item_id=? ";

	    String[] params = {isCaiGou,isWaiXie,isGongYi,memo,changeTime,changePerson,orderId,productId,FProductId,issueNum};
	    String[] itemParams = {isCaiGou,isWaiXie,isGongYi,productId};
		System.out.println("updateOrderSql="+updateOrderSql);
		try {
			int count = Sqlhelper.executeUpdate(updateOrderSql, params);
			int itemCount = Sqlhelper.executeUpdate(updateItemSql, itemParams);
			String json = "{\"result\":\"操作成功!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
		} catch (Exception e) {
			String json = "{\"result\":\"操作失败!\"}";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
		
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













