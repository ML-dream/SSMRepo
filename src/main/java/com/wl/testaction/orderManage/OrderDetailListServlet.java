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

import com.wl.tools.ChineseCode;
import com.wl.tools.StringUtil;
import com.wl.forms.Order;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;
public class OrderDetailListServlet extends HttpServlet {

	private static final long serialVersionUID = 6736837469650044615L;
	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		int pageNo=0;
	    int countPerPage=10;
	    int totalCount = 0;
	    String orderStr = "PRODUCT_ID";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    String orderId = ChineseCode.toUTF8(request.getParameter("orderId").trim());
	    
	    HttpSession session = request.getSession();
		String userId = ((User)session.getAttribute("user")).getUserId();
	    String staffcode = ((User)session.getAttribute("user")).getStaffCode();
	    String totalCountSql = "select count(*) from order_detail where order_id=? and createPerson=? ";
	    String[] params1 = {orderId,staffcode};
	    try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql, params1);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
	    String OrderSql= "select B.ORDER_ID orderId,PRODUCT_ID productId,ISSUE_NUM issueNum,B.LOT,SORTIE," +
	    		"PURDUCT_NUM productNum,B_TIME bTime,E_TIME eTime,SB_TIME sbTime,SE_TIME seTime," +
	    		"DEPT_ID deptId,PRODUCT_STATUS productStatus,FPRODUCT_ID fproductId," +
	    		"MOVENUM moveNum,UP_LOT upLot,FINISHNUM,MEMO,PLANCHANNO," +
	    		"PRODUCT_NAME productName,SPEC,MADEPLACE,drawingId,unitprice," +
	    		"planProfit,customerkoukuan,shijixiaoshouer,yijinghuikuan,yukuan," +
	    		"isduizhanghan,islailiao,isjiaohuo,D.customer,D.connector," +
	    		"C.companyName,E.deptname,B.quotationtotal,B.paper " +
	    	"from (select A.*,ROWNUM row_num from (select EM.* from order_detail EM where EM.order_id=? and cengci='2' and createPerson=? order by "+orderStr+" asc) A where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
	    	"left join orders D on D.order_id=B.order_id " +
	    	"left join customer C on D.CUSTOMER=C.COMPANYID " +
	    	"left join dept E on E.DEPTID=D.DEPT_USER " +
	    	"where row_num>="+((pageNo-1)*countPerPage+1)+" order by "+orderStr;
	    
	    String[] params2 = {orderId,staffcode};
	    List<Order> orderList = new ArrayList<Order>();
	    
	    try {
			orderList = Sqlhelper.exeQueryList(OrderSql, params2, Order.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    
	    String json = PluSoft.Utils.JSON.Encode(orderList);
//		json = "{\"orderId\":"+orderId+",\"total\":"+totalCount+",\"data\":"+json+"}";
	    json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
	    
	    
	    
//	    System.out.println("OrderSql=="+OrderSql);
//	    ResultSet OrderRs =null;
//		try{
//			OrderRs = Sqlhelper.executeQuery(OrderSql, null);
//			List<Order> orderList = new ArrayList<Order>();
//			try {
//				while (OrderRs.next()) {
//					Order order = new Order();
//					order.setOrderId(OrderRs.getString(1));
//					order.setProductId(OrderRs.getString(2));
//					order.setIssueNum(OrderRs.getString(3));
//					order.setLot(OrderRs.getString(4));
//					order.setSortie(OrderRs.getString(5));
//					
//					order.setProductNum(OrderRs.getInt(6));
//					order.setbTime(OrderRs.getString(7));
//					order.seteTime(OrderRs.getString(8));
//					order.setSbTime(OrderRs.getString(9));
//					order.setSeTime(OrderRs.getString(10));
//					
//					order.setDeptId(OrderRs.getString(11));
//					order.setProductStatus(OrderRs.getString(12));
//					order.setFproductId(OrderRs.getString(13));
//					
//					order.setMoveNum(OrderRs.getString(14));
//					order.setUpLot(OrderRs.getString(15));
//					order.setFinishNum(OrderRs.getString(16));
//					order.setMemo(OrderRs.getString(17));
//					order.setPlanChanNo(OrderRs.getString(18));
//					
//					order.setProductName(OrderRs.getString(19));
//					order.setSpec(OrderRs.getString(20));
//					order.setMadePlace(OrderRs.getString(21));
//					order.setDrawingId(OrderRs.getString(22));
//					order.setUnitPrice(OrderRs.getDouble(23));
//					
//					order.setPlanProfit(OrderRs.getDouble(24));
//					order.setCustomerKouKuan(OrderRs.getDouble(25));
//					order.setShiJiXiaoShouer(OrderRs.getDouble(26));
//					order.setYiJingHuiKuan(OrderRs.getDouble(27));
//					order.setYuKuan(OrderRs.getDouble(28));
//					order.setIsDuiZhangDan(OrderRs.getString(29));
//					order.setIsLaiLiao(OrderRs.getString(30));
//					order.setIsJiaoHuo(OrderRs.getString(31));
//					order.setCustomer(OrderRs.getString(32));
//					order.setConnector(OrderRs.getString(33));
//					order.setCompanyName(OrderRs.getString(34));
//					order.setDeptName(OrderRs.getString(35));
//					order.setIssueNum(OrderRs.getString(36));
//					order.setQuotationTotal(OrderRs.getDouble(37));
//					order.setPaper(OrderRs.getString("paper"));
//					System.out.println(OrderRs.getString("paper"));
//					orderList.add(order);
//				}
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			String json = PluSoft.Utils.JSON.Encode(orderList);
//			json = "{\"orderId\":"+orderId+",\"total\":"+totalCount+",\"data\":"+json+"}";
//			response.setCharacterEncoding("UTF-8");
//			response.getWriter().append(json).flush();
//			System.out.println(json);
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
	}
	public void doPost(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		doGet(request,response);
	}
}













