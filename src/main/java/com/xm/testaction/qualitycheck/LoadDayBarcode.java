package com.xm.testaction.qualitycheck;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Order;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class LoadDayBarcode extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public LoadDayBarcode() {
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
//		加载历史条形码
		System.out.println(this.getClass().getName());
		
		int pageIndex = 0;
		int pageSize = 0;
		pageIndex= Integer.parseInt(request.getParameter("pageIndex"));
		pageSize= Integer.parseInt(request.getParameter("pageSize"));
		
		int min=pageIndex*pageSize;
		int max = (pageIndex+1)*pageSize;
		
		String day = StringUtil.isNullOrEmpty(request.getParameter("day"))?"":request.getParameter("day");
		String productName = ChineseCode.toUTF8(StringUtil.isNullOrEmpty(request.getParameter("productName"))?"":request.getParameter("productName").trim());
		String inCondition = "";
		String condition = "";
		if(day.isEmpty()){
			
		}else{
			inCondition = "where a.checkdate like  to_date('"+day+"','yyyy-MM-ddHH:mi:ss')";
			
		}
		if(!productName.isEmpty()){
			condition = " and product_name like '%"+productName+"%' ";
		}
		
		String sqla = "select * from ("+
			"select t.barcode,t.order_id orderId,t.companyname companyName,t.drawingid productId,t.product_name productName,rownum rn from PO_ROUTER t " +
				"where t.barcode in(" +
				"select distinct barcode from (select a.barcode,nvl(a.confirm_num,0) confirm from po_routing2 a "+inCondition+" ) where confirm > 0) " +condition+
						")where "+min+ "<rn and rn <=" +max ;
		List<Order> lista = new ArrayList<Order>();
		try {
			lista = Sqlhelper.exeQueryList(sqla, null, Order.class);
		} catch (Exception e) {
			// TODO: handle exception
		}
		int total = 0;
		String sqlb = "select count(*) from (select t.barcode from PO_ROUTER t " +
				"where t.barcode in(" +
				"select distinct barcode from (select a.barcode,nvl(a.confirm_num,0) confirm from po_routing2 a "+inCondition+" ) where confirm > 0) " +condition+")";
		try {
			total = Sqlhelper.exeQueryCountNum(sqlb, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String json = PluSoft.Utils.JSON.Encode(lista);
		json = "{\"total\":"+total+",\"data\":"+json+"}";
		response.setCharacterEncoding("utf-8");
		response.getWriter().append(json).flush();
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
