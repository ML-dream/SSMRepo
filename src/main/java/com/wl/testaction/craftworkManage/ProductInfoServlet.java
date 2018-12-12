package com.wl.testaction.craftworkManage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.CustomerItem;
import com.wl.forms.Order;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class ProductInfoServlet extends HttpServlet {

	private static final long serialVersionUID = 7081281252936483650L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String orderSql = "select orderid,customeId,itemId,itemname," +
			    	"itemspec,starttime,endtime," +
			    	"person,memo,itemType,itemNum," +
			    	"itemPrice,isCheckIn,unit,customeName,B.staff_name " +
			    	"from customeasset A " +
			    	"left join employee_info B on B.staff_code=A.person " +
			    	"where orderid='"+orderId+"'";
		
	    System.out.println("orderSql=="+orderSql);
	    
	    ResultSet rs =null;
		try{
			rs = Sqlhelper.executeQuery(orderSql, null);
			
			rs.next();
			CustomerItem customerItem = new CustomerItem();
			customerItem.setOrderId(rs.getString(1));
			customerItem.setCustomeId(rs.getString(2));
			customerItem.setItemId(rs.getString(3));
			customerItem.setItemname(rs.getString(4));
			
			customerItem.setItemspec(rs.getString(5));
			customerItem.setStarttime(rs.getString(6));
			customerItem.setEndtime(rs.getString(7));
			
			customerItem.setPerson(rs.getString(8));
			customerItem.setMemo(rs.getString(9));
			customerItem.setItemType(rs.getString(10));
			customerItem.setItemNum(rs.getInt(11));
			
			customerItem.setItemPrice(rs.getInt(12));
			customerItem.setIsCheckIn(rs.getString(13));
			customerItem.setUnit(rs.getString(14));
			customerItem.setCustomeName(rs.getString(15));
			customerItem.setPersonName(rs.getString(16));
			
			request.setAttribute("result", customerItem);
			
		}catch(Exception e){
		}  finally{
			try {
				if(rs!=null){
					rs.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	    request.getRequestDispatcher("orderManage/CustomerItemSpec.jsp").forward(request, response);
	}


}
