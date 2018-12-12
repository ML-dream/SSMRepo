package com.wl.testaction.craftworkManage;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.common.OrderStatus;
import com.wl.forms.OrderTree;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class GetEditCraftTree extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetEditCraftTree() {
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
//		工艺调整，树加载
		HttpSession session = request.getSession();
		String orderStatusFrom = StringUtil.isNullOrEmpty(request.getParameter("orderStatusFrom"))?OrderStatus.NEWORDER+"":request.getParameter("orderStatusFrom");
		String orderStatusTo = StringUtil.isNullOrEmpty(request.getParameter("orderStatusTo"))?OrderStatus.DELIVERED+"":request.getParameter("orderStatusTo");
		
		String productName = ChineseCode.toUTF8(StringUtil.isNullOrEmpty(request.getParameter("productName"))?"":request.getParameter("productName").trim());
		String para = StringUtil.isNullOrEmpty(request.getParameter("para"))?"":request.getParameter("para");	//参数1 表示带条件筛选
		
		String condition = "";	//附加查询条件
		String bday  = ChineseCode.toUTF8(StringUtil.isNullOrEmpty(request.getParameter("bday"))?"":request.getParameter("bday"));
		String eday = ChineseCode.toUTF8(StringUtil.isNullOrEmpty(request.getParameter("eday"))?"":request.getParameter("eday"));
		String productId = ChineseCode.toUTF8(StringUtil.isNullOrEmpty(request.getParameter("productId"))?"":request.getParameter("productId"));
		if(!bday.isEmpty()&&!eday.isEmpty()){
			condition += " and order_date between to_date('"+bday+"','yyyy-MM-ddHH:mi:ss') and to_date('"+eday+"','yyyy-MM-ddHH:mi:ss') ";
		}
		String proCondition = "";
		String orProCondition = "";
		if(!productId.isEmpty()){
			proCondition = " and id like '%"+productId+"%' ";
			orProCondition= " and product_id like '%"+productId+"%' ";
		}
		
		String orderSql;
		String itemSql;
	    String orderSql1 = "select  order_id id,order_id name,order_date from orders " +
	    		"where order_status<=to_number(?) and order_status>=to_number(?) "+condition+		//and order_id like '%"+orderId+"%'
	            " order by order_date desc "; 
	    
	    if(!para.isEmpty() && para.equals("1")){
//	    	如果是带参数筛选
	    	orderSql1 = "select t.order_id id,t.order_id name,t.order_status,order_date from orders t where exists (select 'X' from order_detail a where a.order_id= t.order_id and a.product_name like '%"+productName+"%' "+orProCondition+" ) " +
	    			"and order_status<=to_number(?) and order_status>=to_number(?) "+condition+" order by order_date desc ";
	    }
	    
	    String[] params = {orderStatusTo,orderStatusFrom};
	    List<OrderTree> orderTreeList = new ArrayList<OrderTree>();

	    	orderSql=orderSql1; 
	 
	    System.out.println(orderSql);
	    try {
	    	orderTreeList = Sqlhelper.exeQueryList(orderSql, params, OrderTree.class);
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
	    
	    String itemSql2 = "select distinct product_id id, product_name name,fproduct_id pid,cengci,quotationTotal ProductPrice," +
				"issue_num issueNum,drawingId,order_Id orderId,product_status productStatus,isWaiXie,isCaiGou,isGongYi  "+
				"from order_detail A "+
				"start with order_id in (select  order_id id from orders where order_status<=to_number(?) and order_status>=to_number(?) "+condition+" )"+	//and order_id like '%"+orderId+"%'
				"connect by prior A.product_id=A.fproduct_id " +
				"order by product_id ";
//				"where dr.name like '%"+productName+"%' ";
	    
	    if(!para.isEmpty() && para.equals("1")){
	    	itemSql2 = "select * from (" +
	    		"select distinct product_id id, product_name name,fproduct_id pid,cengci,quotationTotal ProductPrice," +
				"issue_num issueNum,drawingId,order_Id orderId,product_status productStatus,isWaiXie,isCaiGou,isGongYi  "+
				"from order_detail A "+
				"start with order_id in (select  order_id id from orders where order_status<=to_number(?) and order_status>=to_number(?) "+condition+" )"+	//and order_id like '%"+orderId+"%'
				"connect by prior A.product_id=A.fproduct_id " +
				"order by product_id )dr " +
				"where dr.name like '%"+productName+"%' "+proCondition;
	    }
	
	   
	    	itemSql=itemSql2;
	    String[] params2 = {orderStatusTo,orderStatusFrom};
	    
	    try {
			orderTreeList = Sqlhelper.exeQueryList(itemSql, params2, OrderTree.class);
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
			jsonBuffer.append("\"ProductPrice\":"+"\""+tree.getProductPrice()+"\",");
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
