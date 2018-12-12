package com.wl.testaction.craftworkManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Order;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;

public class ToEditCraftDetail extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ToEditCraftDetail() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
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
//		工艺调整，树跳转
		System.out.println(this.getClass().getName());
		String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
		String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
		String FProductId = ChineseCode.toUTF8(request.getParameter("FProductId").trim());
		
		String orderSql = "select distinct ORDER_ID orderId,A.PRODUCT_ID productId,A.ISSUE_NUM issueNum,LOT lot, purduct_num productNum,PRODUCT_STATUS productStatus,FPRODUCT_ID fproductId,PRODUCT_NAME productName,a.SPEC spec,a.drawingId," +
    	     "B.ITEM_TYPEID itemTypeId,C.ITEM_TYPEDESC itemTypeName,D.CONFIRMADVICE confirmedAdvice,to_char(a.e_time,'yyyy-MM-dd') eTime,e.matirial  " +
    	     "from order_detail A " +
    	     "left join item B on B.ITEM_ID=A.PRODUCT_ID "+
//    	     "left join item B on B.ITEM_ID=A.PRODUCT_ID and B.FITEM_ID=A.FPRODUCT_ID " +
    	     "left join itemtype C on C.item_typeid=B.item_typeid  " +
             "left join fo_detail D on D.product_id=A.product_id and D.issue_num=A.issue_num " +
             "left join foheader e on e.orderid=e.orderid and e.productid=A.product_id and e.issuenum=A.issue_num "+
    	     "where ORDER_ID=? and A.PRODUCT_ID=? and FPRODUCT_ID=? ";	// and D.isinuse='1'
		System.out.println(orderSql);
		String [] params = {orderId,productId,FProductId};
		Order order = new Order();
		try {
			order = Sqlhelper.exeQueryBean(orderSql, params, Order.class);
			request.setAttribute("result", order);
		}  catch (SQLException e) {
			e.printStackTrace();
		}  catch (Exception e1) {
			e1.printStackTrace();
		}
			request.getRequestDispatcher("craftworkManage/adjustCraft.jsp").forward(request, response);
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
