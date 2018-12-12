package com.wl.testaction.warehouse.payment;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wl.forms.payStatistics;
import com.wl.tools.Sqlhelper;

public class saveCustomerPayServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public saveCustomerPayServlet() {
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
		String data=request.getParameter("submitData");
		HashMap datamap=(HashMap) Test.JSON.Decode(data);
		String companyId=(String) datamap.get("companyId");
		String payDate=(String) datamap.get("payDate");
		String thispaid=(String) datamap.get("thispaid");
//		int year=0;
//		int month=0;
//		int date=0;
//		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改
//		year = c.get(Calendar.YEAR); 
//		month = c.get(Calendar.MONTH);//当前月份+1，如当前为9月，则month为8
//		date = c.get(Calendar.DATE); 
//		String bdate= "";
//		String edate= "";
//		bdate= "" +year +"-"+month+"-"+1;
//		edate= "" +year +"-"+month+"-"+31;
//		String time= "" +year+"-"+(month-1);
//		String Month= "" +year+"-"+month+"-"+1;
//		String totalThisPaid="";
//		String totalPrice="";
//		String shouldPay="";
//		
//		double totalthispaid=0;
//		double totalprice=0;
//		double shouldpay=0;
		
		String Sql="insert into customerpayment (customerid,paydate,thispaid) values('"+companyId+"'," +
		"to_date('"+payDate+"','yyyy-MM-dd,hh24:mi:ss'),'"+thispaid+"')";
		
//		if(date==1){
//			String paySql="select sum(thispaid) from customerpayment A where customerid='"+companyId+"' and paydate " +
//				"between to_date('"+bdate+"','yyyy-mm-dd,hh24:mi:ss') and to_date('"+edate+"','yyyy-mm')";
//			String payStatisticsSql="select shouldpay from paystatistics where companyid='"+companyId+"' and month='"+time+"'";
//			String saleSql="select sum(M.price) from (select B.checksheet_id,checkout_date,C.order_id orderId,C.item_id itemId,item_name itemName,issue_num issueNum," +
//			"drawingid drawingId,checkout_num checkoutNum,unitprice,price from mycheckout B " +
//			"left join mycheckout_detl C on C.checksheet_id=B.checksheet_id where companyid='"+companyId+"'  and checkout_date between to_date('"+bdate+"','yyyy-mm-dd,hh24:mi:ss') " +
//			"and to_date('"+edate+"','yyyy-mm-dd,hh24:mi:ss')  order by checksheet_id ) M";
//		
//		
//			try{
//				totalThisPaid=Sqlhelper.exeQueryString(paySql, null);
//				totalthispaid=totalThisPaid.equals("")?0:Double.parseDouble(totalThisPaid);
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		
//			try{
//				totalPrice=Sqlhelper.exeQueryString(saleSql, null);
//				totalprice=totalPrice.equals("")?0:Double.parseDouble(totalPrice);
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//		
//			try{
//				shouldPay=Sqlhelper.exeQueryString(payStatisticsSql, null);
//				shouldpay=shouldPay.equals("")?0:Double.parseDouble(shouldPay);
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//			String selectSql="select month from paystatistics";
//			List<payStatistics> resultList=new ArrayList<payStatistics>();
//			boolean b=false;
//			String totalsql="insert into paystatistics(companyid,sale,lastmonth,thismonth,shouldpay,month) values(" +
//			"'"+companyId+"','"+totalprice+"','"+shouldpay+"','"+totalthispaid+"','"+(totalprice+shouldpay-totalthispaid)+"',to_date('"+Month+"','yyyy-mm'))";
//			
//			try{
//				resultList=Sqlhelper.exeQueryList(selectSql, null, payStatistics.class);
//			}catch(Exception e){
//				e.printStackTrace();
//			}
//			for(int i=0,l=resultList.size();i<l;i++){
//				payStatistics paystatistics=new payStatistics();
//				paystatistics=resultList.get(i);
//				if(Month.equals(paystatistics.getMonth())){
//					b=true;
//				}	
//			}
//			if(b==false){
//				try{
//					Sqlhelper.executeUpdate(totalsql, null);
//				}catch(Exception e){
//					e.printStackTrace();
//				}
//			}
//		
//		}	
//		
		try{
			Sqlhelper.executeUpdate(Sql, null);
			String json = "{\"result\":\"操作成功!\"}";
			response.getWriter().append(json).flush();
		}catch(Exception e){
			String json = "{\"result\":\"操作失败!\"}";
			response.getWriter().append(json).flush();
			e.printStackTrace();
		}
		
	}

}
