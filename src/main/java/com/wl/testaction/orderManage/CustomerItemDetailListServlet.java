package com.wl.testaction.orderManage;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.tools.ChineseCode;
import com.wl.forms.CustomerItem;
import com.wl.tools.Sqlhelper;

public class CustomerItemDetailListServlet extends HttpServlet {

	private static final long serialVersionUID = 2723106413008313260L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		String orderId = ChineseCode.toUTF8(request.getParameter("orderId"));
		System.out.println(orderId);
		String sql = "select A.orderid,A.customeId,A.itemId,A.itemname," +
			"itemspec,A.starttime,A.endtime," +
			"person,orderDetailId,itemType,itemNum," +
			"itemPrice,isCheckIn,D.text unit,customeName,B.connector,C.item_typeDesc itemTypeName " +
			"from customeasset A  " +
			"left join orders B on B.order_id=A.orderId "+
			"left join itemtype C on C.item_typeId=A.itemType "+
			"left join priceunit D on D.id=A.unit "+
			"where orderId=? ";
	
		String[] params = {orderId};
		List<CustomerItem> orderList = new ArrayList<CustomerItem>();
		try {
			orderList = Sqlhelper.exeQueryList(sql, params,CustomerItem.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		String json = PluSoft.Utils.JSON.Encode(orderList);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
		
		
//	    System.out.println("sql=="+sql);
	    
//	    ResultSet rs =null;
//		try{
//			rs = Sqlhelper.executeQuery(sql, null);
//			List<CustomerItem> orderList = new ArrayList<CustomerItem>();
//			try {
//				while (rs.next()) {
//					CustomerItem customerItem = new CustomerItem();
//					customerItem.setOrderId(rs.getString(1));
//					customerItem.setCustomeId(rs.getString(2));
//					customerItem.setItemId(rs.getString(3));
//					customerItem.setItemname(rs.getString(4));
//					
//					customerItem.setItemspec(rs.getString(5));
//					customerItem.setStarttime(rs.getString(6));
//					customerItem.setEndtime(rs.getString(7));
//					
//					customerItem.setPerson(rs.getString(8));
//					customerItem.setOrderDetailId(rs.getString(9));
//					customerItem.setItemType(rs.getString(10));
//					customerItem.setItemNum(rs.getInt(11));
//					
//					customerItem.setItemPrice(rs.getDouble(12));
//					customerItem.setIsCheckIn(rs.getString(13));
//					customerItem.setUnit(rs.getString(14));
//					customerItem.setCustomeName(rs.getString(15));
//					customerItem.setConnector(rs.getString(16));
//					customerItem.setItemTypeName(rs.getString(17));
//					
//					orderList.add(customerItem);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String json = PluSoft.Utils.JSON.Encode(orderList);
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().append(json).flush();
//			System.out.println(json);
//		}catch(Exception e){
//		}  finally{
//			try {
//				if(rs!=null){
//					rs.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













