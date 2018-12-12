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
import javax.servlet.http.HttpSession;


import com.wl.common.OrderStatus;
import com.wl.common.ProductStatus;
import com.wl.forms.OrderTree;
import com.wl.forms.Tree;
import com.wl.forms.User;
import com.wl.testaction.partPlanManage.helpTools;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class GetOrderTreeServlet1 extends HttpServlet {

	private static final long serialVersionUID = 6140646624086385805L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {

		
	    HttpSession session = request.getSession();
		String orderStatusFrom = StringUtil.isNullOrEmpty(request.getParameter("orderStatusFrom"))?OrderStatus.NEWORDER+"":request.getParameter("orderStatusFrom");
		String orderStatusTo = StringUtil.isNullOrEmpty(request.getParameter("orderStatusTo"))?OrderStatus.DELIVERED+"":request.getParameter("orderStatusTo");
//		如果参数是1 表示，不加载数据
		String noLoad = StringUtil.isNullOrEmpty(request.getParameter("noLoad"))?"":request.getParameter("noLoad");
		if(!noLoad.isEmpty()&&noLoad.equals("1")){
			return;
		}
		//		xiem 判断是否是 零件计划请求操作  para = partplan,如果是零件计划调整 seePartPlan
		String para ="";
		try {
			para = StringUtil.isNullOrEmpty(request.getParameter("para"))?para:request.getParameter("para").trim();
		} catch (Exception e) {
			// TODO: handle exception
		} 
		
		String orderSql;
		String itemSql;
	    String orderSql1 = "select  order_id id,order_id name,order_date from orders " +
	    		"where order_status<=to_number(?) and order_status>=to_number(?) "+
	            "order by order_date desc";      
	    String[] params = {orderStatusTo,orderStatusFrom};
	    List<OrderTree> orderTreeList = new ArrayList<OrderTree>();
	    if(para.equals("seePartPlan")){
	    	orderSql1=helpTools.seePartPlanOrders(request, response);
	    	params =null;
	    }
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
	    String itemSql2 = "select distinct product_id id, product_name name,fproduct_id pid,cengci,quotationTotal ProductPrice," +
				"issue_num issueNum,drawingId,order_Id orderId,product_status productStatus,isWaiXie,isCaiGou,isGongYi  "+
				"from order_detail A "+
				"start with order_id in (select  order_id id from orders where order_status<=to_number(?) and order_status>=to_number(?))"+
				"connect by prior A.product_id=A.fproduct_id " +
				"order by product_id";
//		如果是零件计划请求，则再加一层零件状态筛选
	    if(para.equals("partplan")){
	    	int productFrom = ProductStatus.FODOING;
	    	int productTo = ProductStatus.DOING;
	    	itemSql2 = "select * from( "+itemSql2 +" ) where productstatus >="+productFrom+" and productstatus <="+productTo+"" +
	    			"  order by id";
	    }
	   
	    	
	    String[] params2 = {orderStatusTo,orderStatusFrom};
	    
	    if(para.equals("seePartPlan")){
	    	itemSql2=helpTools.seePartPlanProducts(request, response);
	    	params2 =null;
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
			jsonBuffer.append("\"ProductPrice\":"+"\""+tree.getProductPrice()+"\",");
			jsonBuffer.append("\"text\":"+"\""+tree.getName()+"\"");
			jsonBuffer.append("},");
		}
		
		String jsonString  = jsonBuffer.substring(0, jsonBuffer.length()-1)+"]";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(jsonString).flush();
		System.out.println(jsonString);
	    
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}


