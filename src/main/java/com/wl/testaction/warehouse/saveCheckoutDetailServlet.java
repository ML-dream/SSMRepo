package com.wl.testaction.warehouse;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.wl.forms.OrderItem;
import com.wl.forms.User;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Stockcl;
import com.wl.tools.StringUtil;

public class saveCheckoutDetailServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public saveCheckoutDetailServlet() {
		super();
	}

	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String checksheet_id=request.getParameter("checksheet_id");
		String orderId=request.getParameter("orderId");
		String checkoutType=request.getParameter("checkoutType");
		String data=request.getParameter("submitData");
//		String warehouseId=request.getParameter("warehouseId");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String productId=(String) datamap.get("productId");
		String productName=(String) datamap.get("productName");
		String issueNum=(String) datamap.get("issueNum");
		String drawingId=(String) datamap.get("drawingId");
		String productType=(String) datamap.get("productType");
		String spec=(String) datamap.get("spec");
		String stockNum=(String) datamap.get("stockNum");
		String purductNum=(String) datamap.get("purductNum");
		String alreadyPayNum=(String) datamap.get("alreadyPayNum");
		String noPayNum=(String) datamap.get("noPayNum");
		String checkoutNum=(String) datamap.get("checkoutNum");
		String unitPrice=(String) datamap.get("unitPrice");
		String price=(String) datamap.get("price");
		String stockId=(String) datamap.get("stockId");
		//String qualityId=(String) datamap.get("qualityId");
		String memo=(String) datamap.get("memo");
		double totalPrice=0;
		
		
		HttpSession session = request.getSession();
		String createPerson = ((User)session.getAttribute("user")).getStaffCode();
		String changePerson = ((User)session.getAttribute("user")).getStaffCode();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String createTime = df.format(new Date());
		String changeTime = df.format(new Date());
		String orderSql="";
		
		double unitprice=Double.parseDouble(unitPrice);
		double outNum=Double.parseDouble(checkoutNum);
		double payNum=Double.parseDouble(alreadyPayNum);
		double alreadyNum=outNum+payNum;
		double productNum=0;
		
		String[] params={orderId,productId,issueNum};
		totalPrice=StringUtil.isNullOrEmpty(price)?outNum*unitprice:Double.parseDouble(price);
//		if(price.equals("")){
//			double num=Double.parseDouble(checkoutNum);
//			double unitprice=Double.parseDouble(unitPrice);
//			totalPrice=num*unitprice;
//			
//		}
		
		String checkoutSql="insert into mycheckout_detl (CHECKSHEET_ID,ITEM_ID,ITEM_NAME," +
				"SPEC,STOCKNUM,CHECKOUT_NUM,UNITPRICE,PRICE,STOCK_ID,MEMO,ISSUE_NUM,ITEM_TYPE," +
				"ALREADYPAYNUM,NOPAYNUM,PURDUCTNUM,ORDER_ID,CREATEPERSON,CREATETIME,CHANGEPERSON,CHANGETIME,DRAWINGID) values('"+checksheet_id+"','"+productId+"','"+productName+"'," +
				"'"+spec+"','"+stockNum+"',"+checkoutNum+","+unitPrice+","+totalPrice+",'"+stockId+"'," +
				"'"+memo+"','"+issueNum+"','"+productType+"',"+alreadyPayNum+","+noPayNum+","+purductNum+",'"+orderId+"'," +
				"'"+createPerson+"',to_date('"+createTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+changePerson+"',to_date('"+changeTime+"','yyyy-mm-dd,hh24:mi:ss'),'"+drawingId+"')";
		int re=0;
		try{
			re=Sqlhelper.executeUpdate(checkoutSql, null);
			String json="{\"result\":\"操作成功！\"}";
			response.getWriter().append(json).flush();
			
		}catch(Exception e){
			String json="{\"result\":\"操作失败！\"}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
		
			
		Stockcl.Stockout(productId,outNum);
		if(checkoutType.equals("0")){
			String productNumSql="select purduct_num from order_detail where order_id='"+orderId+"' and product_id='"+productId+"' and issue_num='"+issueNum+"'";
			OrderItem orderitem=new OrderItem();
			
			try{
				orderitem=Sqlhelper.exeQueryBean(productNumSql, null, OrderItem.class);
				productNum=orderitem.getPurductNum();
			}catch(Exception e){
				e.printStackTrace();
			}
			
			if(alreadyNum<productNum){
				orderSql="update ORDER_DETAIL set alreadypaynum='"+alreadyNum+"',product_status=80 where order_id=? and product_id=? and issue_num=?";
			}else{
				orderSql="update ORDER_DETAIL set alreadypaynum='"+alreadyNum+"',product_status=90 where order_id=? and product_id=? and issue_num=?";
			}
			
			try{
				Sqlhelper.executeUpdate(orderSql, params);
			}catch(Exception e){
				e.printStackTrace();
			}
		}
		
	}

}
