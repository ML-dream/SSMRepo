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

public class payMentListServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public payMentListServlet() {
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
		int pageNow=Integer.parseInt(request.getParameter("pageIndex"))+1;
		int pageSize=Integer.parseInt(request.getParameter("pageSize"));
		int totalCount=0;
//		String totalPrice="";
//		String totalCountSql="select count(*) from mycheckout_detl";
		
		String totalCountSql="select count(*) from sumPriceView";
		try{
			totalCount=Sqlhelper.exeQueryCountNum(totalCountSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		String viewSql="create or replace view sumpriceview as "+
						"select companyid,cast(sum(B.price) as number(12,2)) totalPrice from mycheckout A "+
						"left join mycheckout_detl B on B.checksheet_id=A.checksheet_id "+
						"group by companyid";
		try{
			Sqlhelper.executeUpdate(viewSql, null);
		}catch(Exception e){
			e.printStackTrace();
		}
		
//		String checkoutSql="select B.checksheet_id,checkout_date,B.companyid,C.order_id orderId,C.item_id itemId,item_name itemName,issue_num issueNum," +
//				"drawingid drawingId,checkout_num checkoutNum,unitprice,price,D.companyname price from (select A.*,rownum row_num from (select EM.* from mycheckout EM " +
//				"order by checksheet_id ) A where rownum<="+(pageSize*pageNow)+" order by checksheet_id) B " +
//				"left join mycheckout_detl C on C.checksheet_id=B.checksheet_id " +
//				"left join customer D on D.companyid=B.companyid " +
//				"where row_num >="+(pageSize*(pageNow-1)+1)+" order by checksheet_id " +
//				"group by B.checksheet_id,checkout_date,B.companyid,C.order_id,C.item_id,item_name,issue_num,drawingid,checkout_num,unitprice,price,D.companyname";
		 String Sql="select A.companyid,cast(totalPrice as number(12,2)) totalPrice,cast(B.totalPaid as number(18,2)) totalPaid,cast((totalPrice-B.totalPaid) as number(12,2)) arrears,C.companyName,C.connector from " +
		 		"(select t.*,rownum row_num from(select EM.* from sumPriceView EM order by companyid asc) t where rownum<="+pageSize*pageNow+" order by companyid asc) A " +
		 		"left join customerPaymentView B on B.customerid=A.companyid " +
		 		"left join customer C on C.companyid=A.companyid " +
		 		"where row_num>="+(pageSize*(pageNow-1)+1)+"";
		List<CheckoutDetl> resultList=new ArrayList<CheckoutDetl>();
		try{
			resultList=Sqlhelper.exeQueryList(Sql, null, CheckoutDetl.class);
		}catch(Exception e){
			e.printStackTrace();
		}
//		for(int i=0,l=resultList.size();i<l;i++){
//			CheckoutDetl checkout=new CheckoutDetl();
//			checkout=resultList.get(i);
//			double totalPrice=checkout.getTotalPrice();
//			double totalPaid=checkout.getTotalPaid();
//			double arrears=totalPrice-totalPaid;
//			checkout.setArrears(arrears);			
//		}
//		String totalPriceSql="select sum(M.price) from (select B.checksheet_id,checkout_date,C.order_id orderId,C.item_id itemId,item_name itemName,issue_num issueNum," +
//				"drawingid drawingId,checkout_num checkoutNum,unitprice,price from mycheckout B " +
//				"left join mycheckout_detl C on C.checksheet_id=B.checksheet_id order by checksheet_id ) M";
//		try{
//			totalPrice=Sqlhelper.exeQueryString(totalPriceSql, null);
//		}catch(Exception e){
//			e.printStackTrace();
//		}
		

		
		String json=PluSoft.Utils.JSON.Encode(resultList);
		json="{\"total\":"+totalCount+",\"data\":"+json+"}";
		response.getWriter().append(json).flush();
		System.out.println(json);
		
	}

}
