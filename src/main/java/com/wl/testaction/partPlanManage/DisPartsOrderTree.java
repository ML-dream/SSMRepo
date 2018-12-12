package com.wl.testaction.partPlanManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.common.ProductStatus;
import com.wl.forms.OrderTree;
import com.wl.tools.Sqlhelper;

public class DisPartsOrderTree extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public DisPartsOrderTree() {
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

	//  报废件，零件计划树加载
		System.out.println(this.getClass().getName());
		 HttpSession session = request.getSession();
			String orderStatusFrom = ProductStatus.FOPASS +"";	//工艺通过
			String orderStatusTo = "";
			String orderSql;
			String itemSql;
		    String orderSql1 = "select distinct t.order_id id,t.order_id name from todiscard t where t.state <"+orderStatusFrom+" "+
		            "order by order_id";      
		    List<OrderTree> orderTreeList = new ArrayList<OrderTree>();

		    	orderSql=orderSql1; 
		 
		    System.out.println(orderSql);
		    try {
		    	orderTreeList = Sqlhelper.exeQueryList(orderSql, null, OrderTree.class);
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		    
		    StringBuffer jsonBuffer = new StringBuffer(8192);
		    jsonBuffer.append("[");
		    
		    for (int i = 0,len=orderTreeList.size(); i < len; i++) {
		    	OrderTree tree = orderTreeList.get(i);
				jsonBuffer.append("{");
				jsonBuffer.append("\"id\":"+"\""+tree.getId()+"\",");
				jsonBuffer.append("\"pid\":"+"\"0000\",");
				jsonBuffer.append("\"level\":"+"\"1\",");		//1：订单层；2：零件层；3：物料层
				jsonBuffer.append("\"orderId\":"+"\""+tree.getId()+"\",");
				jsonBuffer.append("\"text\":"+"\""+tree.getName()+"\"");
				jsonBuffer.append("},");
			}
		    
		    String itemSql2 = "select distinct t.product_id id, product_name name,fproduct_id pid,cengci,quotationTotal ProductPrice," +
		    		"t.issue_num issueNum,t.order_Id orderId,t.state productStatus,t.barcode from todiscard t " +
		    		"left join order_detail a on a.order_id =t.order_id and a.product_id= t.product_id and a.issue_num = t.issue_num" +
		    		" where t.state <"+orderStatusFrom+" " +
					"order by id";

		
		   
		    	itemSql=itemSql2;
//		    String[] params2 = {orderStatusTo,orderStatusFrom};
		    
		    try {
				orderTreeList = Sqlhelper.exeQueryList(itemSql, null, OrderTree.class);
			} catch (Exception e) {
				e.printStackTrace();
			}
		    
			for (int i = 0,len=orderTreeList.size(); i < len; i++) {
				OrderTree tree = orderTreeList.get(i);
				jsonBuffer.append("{");
				jsonBuffer.append("\"id\":"+"\""+tree.getId()+"\",");
				jsonBuffer.append("\"pid\":"+"\""+tree.getPid()+"\",");
				jsonBuffer.append("\"level\":"+"\""+tree.getCengci()+"\",");
				jsonBuffer.append("\"issueNum\":"+"\""+tree.getIssueNum()+"\",");
				jsonBuffer.append("\"drawingId\":"+"\""+tree.getDrawingId()+"\",");
				jsonBuffer.append("\"orderId\":"+"\""+tree.getOrderId()+"\",");
				jsonBuffer.append("\"isWaiXie\":"+"\""+tree.getIsWaiXie()+"\",");
				jsonBuffer.append("\"isCaiGou\":"+"\""+tree.getIsCaiGou()+"\",");
				jsonBuffer.append("\"isGongYi\":"+"\""+tree.getIsGongYi()+"\",");
				jsonBuffer.append("\"productStatus\":"+"\""+tree.getProductStatus()+"\",");
				jsonBuffer.append("\"barcode\":"+"\""+tree.getBarcode()+"\",");
				jsonBuffer.append("\"text\":"+"\""+tree.getName()+"\"");
				jsonBuffer.append("},");
			}
			
			String jsonString  = jsonBuffer.substring(0, jsonBuffer.length()-1)+"]";
			response.setCharacterEncoding("UTF-8");
			response.getWriter().append(jsonString).flush();
			System.out.println(jsonString);
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
