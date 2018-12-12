package com.wl.testaction.warehouse.orderstatistics;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.org.apache.xml.internal.serializer.ToUnknownStream;
import com.wl.forms.Customer;
import com.wl.forms.Order;
import com.wl.forms.OrderTree;
import com.wl.forms.Warehouse;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class GetCustomerTreeServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetCustomerTreeServlet() {
		super();
	}


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
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

		response.setContentType("text/html;charset=utf-8");
//		request.setCharacterEncoding("utf-8");
//		客户筛选
		String customerName = "";
		customerName = StringUtil.isNullOrEmpty(request.getParameter("customer"))?customerName:ChineseCode.toUTF8(request.getParameter("customer").trim());
		String sql="select companyid,companyname from customer  ";
		String sort = "order by companyid";	//排序
		String condtion = " where companyname like '%"+customerName+"%'";
		if(customerName !=null && !customerName.equals("")){
			sql += condtion;
		}
		sql += sort;
		List<Customer> CustomerTreeList=new ArrayList<Customer>();
		try {
			CustomerTreeList = Sqlhelper.exeQueryList(sql, null, Customer.class);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		
		StringBuffer jsonBuffer = new StringBuffer(8192);
	    jsonBuffer.append("[");
	    for (int i = 0,len=CustomerTreeList.size(); i < len; i++) {
	    	Customer customer = CustomerTreeList.get(i);
			jsonBuffer.append("{");
			jsonBuffer.append("\"id\":"+"\""+customer.getCompanyId()+"\",");
			jsonBuffer.append("\"pid\":"+"\"0000\",");
			jsonBuffer.append("\"level\":"+"\"1\",");		//1：库方层
			jsonBuffer.append("\"companyId\":"+"\""+customer.getCompanyId()+"\",");
			jsonBuffer.append("\"text\":"+"\""+customer.getCompanyName()+"\"");
			jsonBuffer.append("},");
	    }
	    
	    String orderSql="select order_id orderId,order_id orderId,customer from orders t " +
	    		"       left join customer a on a.companyid = t.customer " ;
	    if(customerName !=null && !customerName.equals("")){
	    	orderSql += condtion;
		}
	    List<Order> OrderTreeList=new ArrayList<Order>();
	    try {
	    	OrderTreeList = Sqlhelper.exeQueryList(orderSql, null, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
		for (int i = 0,len=OrderTreeList.size(); i < len; i++) {
			Order order = OrderTreeList.get(i);
			jsonBuffer.append("{");
			jsonBuffer.append("\"id\":"+"\""+order.getOrderId()+"\",");
			jsonBuffer.append("\"pid\":"+"\""+order.getCustomer()+"\",");
			jsonBuffer.append("\"level\":"+"\"2\",");
			jsonBuffer.append("\"orderId\":"+"\""+order.getOrderId()+"\",");
			jsonBuffer.append("\"text\":"+"\""+order.getOrderId()+"\"");
			jsonBuffer.append("},");
		}
		
			String jsonString  = jsonBuffer.substring(0, jsonBuffer.length()-1)+"]";
			//response.setCharacterEncoding("UTF-8");
			response.getWriter().append(jsonString).flush();
			System.out.println(jsonString);
		
	}

}
