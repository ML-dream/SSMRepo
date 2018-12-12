package com.wl.testaction.warehouse.payment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.CheckoutDetl;
import com.wl.tools.Sqlhelper;

public class GetCompanyPayServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public GetCompanyPayServlet() {
		super();
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

		doPost(request,response);
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

		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
		
		
		
		String companyId=request.getParameter("companyId");
		String startDate=request.getParameter("startDate");
		String endDate=request.getParameter("endDate");
		double totalprice=0;
		double haspaid=0;
		double nopay=0;
		double initialPayment=0;
		double backPrice=0;
		String totalCountSql="";
		String checkoutSql="";
		String totalPriceSql="";
		String paySql="";
		
		String initialPaymentSql="select * from INITIALCUSTOMERPAYMENT where companyid='"+companyId+"'";
		
		String returnSql="select A.customer,cast(sum(backprice) as number(20,5)) backPrice from orders A " +
							"left join order_detail B on B.order_id=A.order_id " +
							"where customer='"+companyId+"' group by A.customer";
		
		if(startDate.equals("")||endDate.equals("")){
			totalCountSql="select count(*) from mycheckout B " +
				"left join mycheckout_detl C on C.checksheet_id=B.checksheet_id " +
				"where companyid='"+companyId+"' and status='1'";
			
			checkoutSql="select B.checksheet_id,checkout_date,order_id orderId,item_id itemId,item_name itemName,issue_num issueNum," +
				"drawingid drawingId,checkout_num checkoutNum,unitprice,price from (select A.*,rownum row_num from (select Em.Checksheet_Id sheetId,em.checkout_date,c.* from mycheckout EM " +
				" left join mycheckout_detl C on C.checksheet_id=em.checksheet_id " +
				"where companyid='"+companyId+"' and status='1' order by sheetId desc,item_id desc ) A where rownum<="+(pageSize*pageNow)+" order by sheetId desc,item_id desc) B " +
				" where row_num >="+(pageSize*(pageNow-1)+1)+" order by checksheet_id";
	
			totalPriceSql="select cast(sum(M.price) as number(12,2)) totalPrice from (select B.checksheet_id,checkout_date,C.order_id orderId,C.item_id itemId,item_name itemName,issue_num issueNum," +
				"drawingid drawingId,checkout_num checkoutNum,unitprice,price from mycheckout B " +
				"left join mycheckout_detl C on C.checksheet_id=B.checksheet_id where companyid='"+companyId+"' and status='1' order by checksheet_id ) M";
			
			paySql="select cast(sum(thispaid) as number(12,2)) haspaid from customerpayment where customerid='"+companyId+"'";
			
		}else{
			totalCountSql="select count(*) from mycheckout B " +
				"left join mycheckout_detl C on C.checksheet_id=B.checksheet_id " +
				"where companyid='"+companyId+"'and status='1' and checkout_date between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss')";
			
			checkoutSql="select B.checksheet_id,checkout_date,order_id orderId,item_id itemId,item_name itemName,issue_num issueNum," +
				"drawingid drawingId,checkout_num checkoutNum,unitprice,price from (select A.*,rownum row_num from (select Em.Checksheet_Id sheetId,em.checkout_date,c.* from mycheckout EM " +
				" left join mycheckout_detl C on C.checksheet_id=em.checksheet_id " +
				"where companyid='"+companyId+"' and status='1' and checkout_date between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss')  order by sheetId desc,item_id desc  ) A where rownum<="+(pageSize*pageNow)+" order by checksheet_id) B " +
				" where row_num >="+(pageSize*(pageNow-1)+1)+" order by sheetId desc,item_id desc";
			
			totalPriceSql="select cast(sum(M.price) as number(12,2)) totalPrice from (select B.checksheet_id,checkout_date,C.order_id orderId,C.item_id itemId,item_name itemName,issue_num issueNum," +
				"drawingid drawingId,checkout_num checkoutNum,unitprice,price from mycheckout B " +
				"left join mycheckout_detl C on C.checksheet_id=B.checksheet_id " +
				"where companyid='"+companyId+"' and status='1' and checkout_date between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss')  order by checksheet_id ) M";
			
			paySql="select cast(sum(thispaid) as number(12,2)) haspaid from customerpayment where customerid='"+companyId+"' and checkout_date between to_date('"+startDate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+endDate+"','yyyy-mm-dd,hh24:mi:ss')";
			
		}
//		 and mycheckout_date between '"+startDate+"' and '"+endDate+"'
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		List<CheckoutDetl> resultList=new ArrayList<CheckoutDetl>();
		try{
			resultList=Sqlhelper.exeQueryList(checkoutSql, null, CheckoutDetl.class);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		CheckoutDetl totalPriceBean=new CheckoutDetl();
		CheckoutDetl haspaidBean=new CheckoutDetl();
		CheckoutDetl initialPaymentBean=new CheckoutDetl();
		CheckoutDetl backPriceBean=new CheckoutDetl();
		
//		if(!(resultList.size()==0)){
			try{
				totalPriceBean=Sqlhelper.exeQueryBean(totalPriceSql, null, CheckoutDetl.class);
			}catch(Exception e){
				e.printStackTrace();
			}
			totalprice=totalPriceBean.getTotalPrice();
//			totalprice=totalPrice.equals("")?0:Integer.parseInt(totalPrice);
//			totalprice=totalPrice.isEmpty()?0:Double.parseDouble(totalPrice);
//			CheckoutDetl checkout=new CheckoutDetl();
			try{
				haspaidBean=Sqlhelper.exeQueryBean(paySql, null, CheckoutDetl.class);
			}catch(Exception e){
				e.printStackTrace();
			}
			haspaid=haspaidBean.getHaspaid();
			try {
				initialPaymentBean=Sqlhelper.exeQueryBean(initialPaymentSql, null, CheckoutDetl.class);
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			initialPayment=initialPaymentBean.getInitialPayment();
			
			try {
				backPriceBean=Sqlhelper.exeQueryBean(returnSql, null, CheckoutDetl.class);
				
			} catch (Exception e) {
				e.printStackTrace();
				// TODO: handle exception
			}
			backPrice=backPriceBean.getBackPrice();
			totalprice=totalprice+initialPayment-backPrice;
//			haspaid=hasPaid==null?0:Integer.parseInt(hasPaid);
			nopay=totalprice-haspaid;
			
//		}	
			
		
		
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"totalPrice\":"+totalprice+",\"haspaid\":"+haspaid+",\"nopay\":"+nopay+",\"backPrice\":"+backPrice+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
		System.out.println(json);
		
		
		
	}

}
