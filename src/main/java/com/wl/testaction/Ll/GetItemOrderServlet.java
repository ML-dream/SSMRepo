package com.wl.testaction.Ll;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.CheckoutId;
import com.wl.forms.Item;
import com.wl.forms.OrderItem;
import com.wl.tools.Sqlhelper;
import com.wl.tools.StringUtil;

public class GetItemOrderServlet extends HttpServlet {


	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		int pageNo=0;
	    int countPerPage=20;
	    int totalCount = 0;
	    String orderStr = "PRODUCT_ID";
	    orderStr = StringUtil.isNullOrEmpty(request.getParameter("sortField"))?orderStr:request.getParameter("sortField");
	    pageNo = Integer.parseInt(request.getParameter("pageIndex"))+1;
	    countPerPage = Integer.parseInt(request.getParameter("pageSize"));
	    String checkoutId=request.getParameter("checkoutId");
	    String orderId = request.getParameter("orderId");
	    String warehouseId=request.getParameter("warehouseId");
		System.out.println(orderId);
	    
	    String totalCountSql1 = "select count(1) from ORDER_DETAIL e " +
	    		"left join stock C on C.item_id=E.product_id  where ORDER_ID=?  and warehouse_id=? ";
//	    String totalCountSql2 = "select count(1) from  CUSTOMEASSET where ORDERID=?";
	    String[] params = {orderId,warehouseId};
//	    String [] toParam ={orderId};
		try {
			totalCount = Sqlhelper.exeQueryCountNum(totalCountSql1, params);
//			+Sqlhelper.exeQueryCountNum(totalCountSql2, params);
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		String checkoutSql="select checkout_num,price from mycheckout_detl where checksheet_id=? and item_id=?";
		
//		String sql="select product_id productId,product_name productName,purduct_num purductNum,issue_num issueNum,productType,B.spec," +
//				"alreadypaynum alreadyPayNum,B.unitprice,drawingid drawingId,t.unit_m unitm,C.stock_id stockId,C.stock_num stockNum,D.item_typedesc productTypeDesc from " +
//				"(select A.*,ROWNUM row_num from (select * from ORDER_DETAIL where ORDER_ID=? order by "+orderStr+" asc) A " +
//				"where ROWNUM<="+(countPerPage*pageNo)+" order by "+orderStr+") B " +
//				"left join ITEM t on t.item_id=B.product_id " +
//				"left join stock C on C.item_id=B.product_id " +
//				"left join itemtype D on D.item_typeid=B.productType " +
//				"where row_num>="+((pageNo-1)*countPerPage+1)+" order by stock_num desc";
		String sql="select product_id productId,product_name productName,purduct_num purductNum,issue_num issueNum,productType,B.spec,alreadypaynum alreadyPayNum,B.unitprice,drawingid drawingId,unit_m unitm,stock_id stockId,stock_num stockNum,D.item_typedesc productTypeDesc from "+ 
			"(select A.*,ROWNUM row_num from (select E.*,C.stock_num,C.stock_id,t.unit_m from ORDER_DETAIL E  "+
				 "left join ITEM t on t.item_id=E.product_id "+
				 "left join stock C on C.item_id=E.product_id where ORDER_ID=?  and warehouse_id=? "+  //
				 "order by nvl(stock_num,0) desc) A where ROWNUM<="+(countPerPage*pageNo)+" order by nvl(stock_num,0) desc) B "+
				 "left join itemtype D on D.item_typeid=B.productType where row_num>="+((pageNo-1)*countPerPage+1)+" order by nvl(stock_num,0) desc";
	    String[] params2 = {orderId,warehouseId};//
	    List<OrderItem> resultList = new ArrayList<OrderItem>();
	    
	    try {
			resultList = Sqlhelper.exeQueryList(sql, params2, OrderItem.class);

	    } catch (Exception e) {
			e.printStackTrace();
		}
	    for(int i=0,len=resultList.size();i<len;i++){
	    	OrderItem orderItem=new OrderItem();
	    	orderItem=resultList.get(i);
	    	String itemId=orderItem.getProductId();
	    	int purductNum=orderItem.getPurductNum();
	    	int alreadyPayNum=orderItem.getAlreadyPayNum();
	    	String[] checkoutparams={checkoutId,itemId};
	    	CheckoutId checkout=new CheckoutId();
	    	try{
	    		int noPayNum=purductNum-alreadyPayNum;
	    		orderItem.setNoPayNum(noPayNum);
	    		checkout=Sqlhelper.exeQueryBean(checkoutSql, checkoutparams, CheckoutId.class);
	    		double checkoutNum=checkout.getCheckoutNum();
	    		double price=checkout.getPrice();
	    		orderItem.setCheckoutNum(checkoutNum);
	    		orderItem.setPrice(price);
	    		
	    	}catch(Exception e){
	    		e.printStackTrace();
	    	}
	    	
	    }
	    
		String json = PluSoft.Utils.JSON.Encode(resultList);
		json = "{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.setCharacterEncoding("UTF-8");
		response.getWriter().append(json).flush();
		System.out.println(json);
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

		doGet(request,response);
	}

}
