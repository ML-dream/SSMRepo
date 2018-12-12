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
import com.wl.forms.OrderTree;
import com.wl.forms.Tree;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class GetOrderTreeServlet extends HttpServlet {

	private static final long serialVersionUID = 6140646624086385805L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    HttpSession session = request.getSession();
	    String userId = ((User)session.getAttribute("user")).getUserId();
	    String staffCode =  ((User)session.getAttribute("user")).getStaffCode();
		String orderStatusFrom = StringUtil.isNullOrEmpty(request.getParameter("orderStatusFrom"))?OrderStatus.NEWORDER+"":request.getParameter("orderStatusFrom");
		String orderStatusTo = StringUtil.isNullOrEmpty(request.getParameter("orderStatusTo"))?OrderStatus.DELIVERED+"":request.getParameter("orderStatusTo");
		String orderSql;
		String itemSql;
		String searchDate=null;
		searchDate=request.getParameter("searchDate");
//		按订单日期排序
	    String orderSql1 = "select  order_id id,order_id name,order_date from orders " +
	    		"where createPerson=? and order_status<=to_number(?) and order_status>=to_number(?) "+
	            "order by order_date desc";      
	    String orderSql2="select  order_id id,order_id name from orders " +
	    		"where createPerson=? and order_status<=to_number(?) and order_status>=to_number(?) "+
	            "and to_char(createTime,'yyyy-mm-dd') between '"+searchDate+"' and to_char(sysdate,'yyyy-mm-dd') "+
	    		"order by order_id";
	    String[] params = {staffCode,orderStatusTo,orderStatusFrom};
	    List<OrderTree> orderTreeList = new ArrayList<OrderTree>();
	    if(searchDate==null||searchDate=="")
	    {
	    	orderSql=orderSql1; }
	    	
	    else{ 
	    	orderSql=orderSql2;}
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
	    
	    String itemSql1 = "select distinct product_id id, product_name name,fproduct_id pid,cengci,quotationTotal ProductPrice," +
			"issue_num issueNum,drawingId,order_Id orderId,product_status productStatus,isWaiXie,isCaiGou,isGongYi  "+
			"from order_detail A where createPerson=? "+
			"start with order_id in (select  order_id id from orders where order_status<=to_number(?) and order_status>=to_number(?) ) "+
			"connect by prior A.product_id=A.fproduct_id " +
			"order by product_id";
	    String itemSql2 = "select distinct product_id id, product_name name,fproduct_id pid,cengci,quotationTotal ProductPrice," +
				"issue_num issueNum,drawingId,order_Id orderId,product_status productStatus,isWaiXie,isCaiGou,isGongYi  "+
				"from order_detail A where createPerson=? "+
				"start with order_id in (select  order_id id from orders where order_status<=to_number(?) and order_status>=to_number(?) and to_char(createTime,'yyyy-mm-dd') between '"+searchDate+"' and to_char(sysdate,'yyyy-mm-dd')) "+
				"connect by prior A.product_id=A.fproduct_id " +
				"order by product_id";
	    if(searchDate==null||searchDate=="")
	    {
	    	itemSql=itemSql1; }
	    	
	    else{ 
	    	itemSql=itemSql2;}
	    String[] params2 = {staffCode,orderStatusTo,orderStatusFrom};
	    
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













