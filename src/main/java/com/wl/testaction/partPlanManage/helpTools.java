package com.wl.testaction.partPlanManage;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.common.OrderStatus;
import com.wl.common.ProductStatus;
import com.wl.tools.ChineseCode;
import com.wl.tools.StringUtil;

public class helpTools  extends HttpServlet {
/**
 * 零件计划调整树订单加载，返回sql语句
 * @return
 */
	public static String seePartPlanOrders(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String sql = "";
		String bdate = StringUtil.isNullOrEmpty(request.getParameter("bdate"))?"":request.getParameter("bdate");
		String edate = StringUtil.isNullOrEmpty(request.getParameter("edate"))?"":request.getParameter("edate");
		
		String productName = StringUtil.isNullOrEmpty(request.getParameter("productName"))?"":ChineseCode.toUTF8(request.getParameter("productName").trim());
		String productId = StringUtil.isNullOrEmpty(request.getParameter("productId"))?"":ChineseCode.toUTF8(request.getParameter("productId").trim());
		
		int stateFrom = OrderStatus.PASS;	//订单状态
		int stateTo = OrderStatus.DELIVERED;
		
		int proStateFrom = ProductStatus.FODOING;
		int proStateTo = ProductStatus.DELEVERED;
		
//		如果输入了图号或产品名称，则直接从orderdetail 表找数据
		if(!productName.equals("")){
			sql = "select distinct t.order_id id,t.order_id name,t.b_time from order_detail t where t.product_name like '%"+productName+"%'" +
					" and t.product_status <="+proStateTo+" and t.product_status>= "+proStateFrom;
			return sql;
		}
		if(!productId.equals("")){
			sql = "select distinct t.order_id id,t.order_id name,t.b_time from order_detail t where t.product_id like '%"+productName+"%'" +
			" and t.product_status <="+proStateTo+" and t.product_status>= "+proStateFrom;
			return sql;
		}
		if(!bdate.equals("")||!edate.equals("")){
			sql="select  order_id id,order_id name,order_date from orders " +
	    		"where order_date between to_date('"+bdate+"','yyyy-MM-dd HH:mi:ss') and to_date('"+edate+"','yyyy-MM-dd HH:mi:ss') and order_status<="+stateTo+" and order_status>= "+stateFrom+
	            "order by order_date desc";
			return sql;
		}
		return sql;
	}
	
	/**
	 * 零件计划调整树零件加载，返回sql语句
	 */
	public static String seePartPlanProducts(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		String sql = "";
		
		String bdate = StringUtil.isNullOrEmpty(request.getParameter("bdate"))?"":request.getParameter("bdate");
		String edate = StringUtil.isNullOrEmpty(request.getParameter("edate"))?"":request.getParameter("edate");
		
		String productName = StringUtil.isNullOrEmpty(request.getParameter("productName"))?"":ChineseCode.toUTF8(request.getParameter("productName").trim());
		String productId = StringUtil.isNullOrEmpty(request.getParameter("productId"))?"":ChineseCode.toUTF8(request.getParameter("productId").trim());
		
		int stateFrom = OrderStatus.PASS;	//订单状态
		int stateTo = OrderStatus.DELIVERED;
		
		int proStateFrom = ProductStatus.FODOING;
		int proStateTo = ProductStatus.DELEVERED;
		
//		如果输入了图号或产品名称，则直接从orderdetail 表找数据
		if(!productName.equals("")){
			sql =  "select distinct product_id id, product_name name,fproduct_id pid,cengci,quotationTotal ProductPrice," +
				"issue_num issueNum,drawingId,order_Id orderId,product_status productStatus,isWaiXie,isCaiGou,isGongYi  "+
				"from order_detail A "+
				"where a.product_name like '%"+productName+"%'" +
				" and a.product_status <="+proStateTo+" and a.product_status>= "+proStateFrom;
			return sql;
		}
		if(!productId.equals("")){
			sql =  "select distinct product_id id, product_name name,fproduct_id pid,cengci,quotationTotal ProductPrice," +
			"issue_num issueNum,drawingId,order_Id orderId,product_status productStatus,isWaiXie,isCaiGou,isGongYi  "+
			"from order_detail A "+
			"where a.product_id like '%"+productId+"%'" +
			" and a.product_status <="+proStateTo+" and a.product_status>= "+proStateFrom;
			return sql;
		}
		if(!bdate.equals("")||!edate.equals("")){
			sql="select distinct product_id id, product_name name,fproduct_id pid,cengci,quotationTotal ProductPrice," +
			"issue_num issueNum,drawingId,order_Id orderId,product_status productStatus,isWaiXie,isCaiGou,isGongYi  "+
			"from order_detail A "+
			"start with order_id in (select  order_id id from orders where order_date between to_date('"+bdate+"','yyyy-MM-dd HH:mi:ss') and to_date('"+edate+"','yyyy-MM-dd HH:mi:ss') and order_status<="+stateTo+" and order_status>= "+stateFrom+")"+
			"connect by prior A.product_id=A.fproduct_id " +
			"order by product_id";
			return sql;
		}
		
		return sql;
	}
}
