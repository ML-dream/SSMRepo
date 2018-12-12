package com.wl.testaction.orderManage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Order;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class GoOrderConfirmServlet extends HttpServlet {

	private static final long serialVersionUID = 2709459227537128702L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doPost(request,response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		System.out.println(this.getClass().getName());
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String	orderSql = 
		    	"select ORDER_ID orderId,DEPT_USER deptUser,ORDER_DATE orderDate,ENDTIME endTime,CUSTOMER,ORDER_STATUS orderStatus,B.companyName,A.connector,A.connectortel,confirmAdvice,orderPaper,duizhanghan,otherPaper " +
		    	"from orders A left join customer B on B.companyId=A.customer " +
		    	"where ORDER_ID=?";
		String[] params = {orderId};
		Order order = new Order();
		try {
			order = Sqlhelper.exeQueryBean(orderSql, params, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.setAttribute("order", order);
		
//	    System.out.println("orderSql=="+orderSql);
//	    
//	    ResultSet OrderRs =null;
//		try{
//			OrderRs = Sqlhelper.executeQuery(orderSql, null);
//			
//			OrderRs.next();
//			Order order = new Order();
//			order.setOrderId(OrderRs.getString(1));
//			order.setDeptUser(OrderRs.getString(2));
//			order.setOrderDate(OrderRs.getString(3));
//			order.setEndTime(OrderRs.getString(4));
//			order.setCustomer(OrderRs.getString(5));
//			order.setOrderStatus(OrderRs.getString(6));
//			order.setCompanyName(OrderRs.getString(7));
//			order.setConnector(OrderRs.getString(8));
//			order.setConnectorTel(OrderRs.getString(9));
//			order.setConfirmAdvice(OrderRs.getString(10));
//			
//			order.setOrderPaper(OrderRs.getString(11));
//			order.setDuizhanghan(OrderRs.getString(12));
//			order.setOtherPaper(OrderRs.getString(13));
//			
//			request.setAttribute("order", order);
//			
//		}catch(Exception e){
//		}  finally{
//			try {
//				if(OrderRs!=null){
//					OrderRs.close();
//				}
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
	    request.getRequestDispatcher("orderManage/OrderConfirm.jsp").forward(request, response);
	}


}
