package com.wl.testaction.orderManage;


import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.Order;
import com.wl.tools.ChineseCode;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;
/**
 * 合同审核查看产品详情
 * @author flair
 *
 */
public class OrderDetailSpecServlet extends HttpServlet {
	private static final long serialVersionUID = 5960822724073807776L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
	    System.out.println(this.getClass().getName());
	    String orderId = request.getParameter("orderId");
	    String productId = ChineseCode.toUTF8(request.getParameter("productId").trim());
	    String isModify=StringUtil.isNullOrEmpty(request.getParameter("isModify"))?"":request.getParameter("isModify");
	    String para = "";
	    try {
	    	para =StringUtil.isNullOrEmpty(request.getParameter("para"))?para:request.getParameter("para");
		} catch (Exception e) {
			// TODO: handle exception
		}
		String	orderSql = 
			"select B.ORDER_ID orderId,PRODUCT_ID productId,ISSUE_NUM issueNum,B.LOT,SORTIE," +
    		"PURDUCT_NUM productNum,B_TIME bTime,E_TIME eTime,SB_TIME sbTime,SE_TIME seTime," +
    		"DEPT_ID deptId,PRODUCT_STATUS productStatus,FPRODUCT_ID fproductId," +
    		"MOVENUM,UP_LOT upLot,FINISHNUM,MEMO,PLANCHANNO," +
    		"PRODUCT_NAME productName,SPEC,MADEPLACE,drawingId,unitprice," +
    		"planProfit,customerkoukuan,shijixiaoshouer,yijinghuikuan,yukuan," +
    		"isduizhanghan,islailiao,isjiaohuo,D.customer,D.connector,C.companyName,E.deptname," +
    		"B.paper,B.otherPaper,B.iswaiXie,B.material,B.productType " +
	    	"from  order_detail B " +
	    	"left join orders D on D.order_id=B.order_id " +
	    	"left join customer C on D.CUSTOMER=C.COMPANYID " +
	    	"left join dept E on E.DEPTID=D.DEPT_USER " +
		    "where B.ORDER_ID=? and B.product_Id=? ";
		String[] params = {orderId,productId};
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
//			order.setProductId(OrderRs.getString(2));
//			order.setIssueNum(OrderRs.getString(3));
//			order.setLot(OrderRs.getString(4));
//			order.setSortie(OrderRs.getString(5));
//			order.setProductNum(OrderRs.getInt(6));
//			order.setbTime(OrderRs.getString(7));
//			order.seteTime(OrderRs.getString(8));
//			order.setSbTime(OrderRs.getString(9));
//			order.setSeTime(OrderRs.getString(10));
//			order.setDeptId(OrderRs.getString(11));
//			order.setProductStatus(OrderRs.getString(12));
//			order.setFproductId(OrderRs.getString(13));
//			order.setMoveNum(OrderRs.getString(14));
//			order.setUpLot(OrderRs.getString(15));
//			order.setFinishNum(OrderRs.getString(16));
//			order.setMemo(OrderRs.getString(17));
//			order.setPlanChanNo(OrderRs.getString(18));
//			order.setProductName(OrderRs.getString(19));
//			order.setSpec(OrderRs.getString(20));
//			order.setMadePlace(OrderRs.getString(21));
//			order.setDrawingId(OrderRs.getString(22));
//			order.setUnitPrice(OrderRs.getDouble(23));
//			order.setPlanProfit(OrderRs.getDouble(24));
//			order.setCustomerKouKuan(OrderRs.getDouble(25));
//			order.setShiJiXiaoShouer(OrderRs.getDouble(26));
//			order.setYiJingHuiKuan(OrderRs.getDouble(27));
//			order.setYuKuan(OrderRs.getDouble(28));
//			order.setIsDuiZhangDan(OrderRs.getString(29));
//			order.setIsLaiLiao(OrderRs.getString(30));
//			order.setIsJiaoHuo(OrderRs.getString(31));
//			order.setCustomer(OrderRs.getString(32));
//			order.setConnector(OrderRs.getString(33));
//			order.setCompanyName(OrderRs.getString(34));
//			order.setDeptName(OrderRs.getString(35));
//			
//			order.setPaper(OrderRs.getString(36));
//			order.setDetailOtherPaper(OrderRs.getString(37));
//			
//			request.setAttribute("order", order);
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
		String result =null;
		if(isModify.equals("1")){
			result="orderManage/ModifyOrderDetailSpec.jsp";
		}
		else{
			result= "orderManage/EditOrderDetailSpec.jsp";
		}
		if(para.equals("seeAll")){
			result= "orderManage/productForConfirm.jsp";
		}
		request.getRequestDispatcher(result).forward(request, response);
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













