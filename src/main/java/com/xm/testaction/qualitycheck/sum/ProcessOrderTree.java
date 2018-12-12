package com.xm.testaction.qualitycheck.sum;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.common.OrderStatus;
import com.wl.forms.OrderTree;
import com.wl.forms.ProductStatus;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class ProcessOrderTree extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public ProcessOrderTree() {
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
//  生产进度查询 订单树加载
		System.out.println(this.getClass().getName());
//		String orderStatusFrom = StringUtil.isNullOrEmpty(request.getParameter("orderStatusFrom"))?OrderStatus.NEWORDER+"":request.getParameter("orderStatusFrom");
//		String orderStatusTo = StringUtil.isNullOrEmpty(request.getParameter("orderStatusTo"))?OrderStatus.DELIVERED+"":request.getParameter("orderStatusTo");
		String orderStatusTo= OrderStatus.DELIVERING+"";
		String orderStatusFrom= OrderStatus.PASS+"";
		String orderSql;
		String itemSql;
		String productName= "";
		String para = "1";	//参数1 表示 搜索，加载数据，0不直接加载数据
		String isCheckIn = "0";	//参数0 表示所有，参数1表示未入库
		isCheckIn =StringUtil.isNullOrEmpty(request.getParameter("isCheckIn"))?isCheckIn:request.getParameter("isCheckIn").trim();
		int productStatusFrom = com.wl.common.ProductStatus.FOSUBMIT;
		para = StringUtil.isNullOrEmpty(request.getParameter("para"))?para:request.getParameter("para").trim();
		if(para.equals("0")){
			return;
		}
		productName = StringUtil.isNullOrEmpty(request.getParameter("productName"))?productName:ChineseCode.toUTF8(request.getParameter("productName").trim());
		String[] params = {orderStatusTo,orderStatusFrom};
		
	    String orderSql1 = "select  order_id id,order_id name,order_date from orders " +
	    		"where order_status<=to_number(?) and order_status>=to_number(?) "+
	            "order by order_date desc";      
	    
	    if(productName != null && !productName.equals("") ){
	    	orderSql1 = "select id,id name from ( " +
	    			"select distinct t.order_id id from order_detail t " +
	    			"where t.product_status <= to_number(?) and t.product_status >= to_number(?) and t.product_name like '%"+productName+"%' )";
	    	orderStatusFrom = com.wl.common.ProductStatus.PARTPLANING +"";
	    	orderStatusTo = com.wl.common.ProductStatus.DELEVERED +"";
	    }
	    if(isCheckIn.equals("1")){
//	    	如果是查看未入库的零件
	    	if(productName != null && !productName.equals("")){
	    		orderSql1 = "select distinct pi.order_id id,pi.order_id name  from productNoIn pi where pi.noCheckin >0 and  pi.product_status >= "+productStatusFrom+" and pi.product_name like '%"+productName+"%' ";
	    	}else{
	    		orderSql1 = "select distinct pi.order_id id,pi.order_id name from productNoIn pi where pi.noCheckin >0 and  pi.product_status >= "+productStatusFrom;
	    	}
	    	params = null;
	    }
	    
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
	    
	    
//	    String itemSql1 = "select distinct product_id id, product_name name,fproduct_id pid,cengci,quotationTotal ProductPrice," +
//			"issue_num issueNum,drawingId,order_Id orderId,product_status productStatus,isWaiXie,isCaiGou,isGongYi  "+
//			"from order_detail A where createPerson=? "+
//			"start with order_id in (select  order_id id from orders) "+
//			"connect by prior A.product_id=A.fproduct_id " +
//			"order by product_id";
	    
	    String[] params2 = {orderStatusTo,orderStatusFrom};
	    String itemSql2 = "select distinct product_id id, product_name name,fproduct_id pid,cengci," +
				"issue_num issueNum,drawingId,order_Id orderId,product_status productStatus,isWaiXie,isCaiGou,isGongYi,purduct_num productNum  "+	//
				"from order_detail A "+
				"start with order_id in (select  order_id id from orders where order_status<=to_number(?) and order_status>=to_number(?))"+
				"connect by prior A.product_id=A.fproduct_id " +
				"order by product_id";

	    if(productName != null && !productName.equals("") ){
	    	itemSql2 = "select distinct product_id id, product_name name,fproduct_id pid,cengci," +
				"issue_num issueNum,drawingId,order_Id orderId,product_status productStatus,isWaiXie,isCaiGou,isGongYi,purduct_num productNum  "+	//
				"from order_detail A "+
				"where a.product_status <= to_number(?) and a.product_status >= to_number(?) and a.product_name like '%"+productName+"%' ";
	    }
	    if(isCheckIn.equals("1")){
//	    	如果是查看未入库的零件
	    	if(productName != null && !productName.equals("")){
	    		itemSql2 = "select distinct a.product_id id, a.product_name name,fproduct_id pid,cengci," +
					"issue_num issueNum,drawingId,a.order_Id orderId,a.product_status productStatus,isWaiXie,isCaiGou,isGongYi,purduct_num productNum  "+	//
					"from order_detail A " +
					"left join productNoIn pi on a.order_id= pi.order_id and a.product_id=pi.product_id "+
					"where  a.product_status >= "+productStatusFrom+" and a.product_name like '%"+productName+"%' and pi.noCheckin >0 ";
	    	}else{
	    		itemSql2 = "select distinct a.product_id id, a.product_name name,fproduct_id pid,cengci," +
					"issue_num issueNum,drawingId,a.order_Id orderId,a.product_status productStatus,isWaiXie,isCaiGou,isGongYi,purduct_num productNum  "+	//
					"from order_detail A " +
					"left join productNoIn pi on a.order_id= pi.order_id and a.product_id=pi.product_id "+
					"where  a.product_status >= "+productStatusFrom+" and  pi.noCheckin >0 ";	    	}
	    	params2=null;
	    	
	    }
	    	itemSql=itemSql2;
	   
	    
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
			jsonBuffer.append("\"ProductNum\":"+"\""+tree.getProductNum()+"\",");
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
