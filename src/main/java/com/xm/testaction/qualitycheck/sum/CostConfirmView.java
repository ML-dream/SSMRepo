package com.xm.testaction.qualitycheck.sum;

import com.wl.common.OrderStatus;
import com.wl.tools.Sqlhelper0;

public class CostConfirmView {
	public static void costConfirmView(String bdate,String edate){
		String para = OrderStatus.PASS+""; //只核算审核通过的订单
		String sql = "create or replace view costconfirmview " +
			"as " +
			"select a.customer,d.companyname,a.order_id,a.order_date,a.endtime,b.product_id,b.drawingid,b.product_name" +
			",b.purduct_num,c.matirial,c.roughsize,b.islailiao,e.stuffname,e.price stuffpri,f.timec,e.density " +
			" from orders a " +
			" left join order_detail b on b.order_id = a.order_id" +
			" left join foheader c on c.orderid= b.order_id and c.productid=b.product_id and c.issuenum = b.issue_num" +
			" left join customer d on  d.companyid = a.customer" +
			" left join stuffprice e on e.stuffid = c.matirial "+
			" left join alltimecview f on f.order_id = a.order_id and f.drawingid= b.product_id "+	//产品号与 图号的问题 
			" where a.order_date between  to_date('"+bdate+"','yyyy-mm-dd hh24:mi:ss') " +
			" and to_date('"+edate+"','yyyy-mm-dd hh24:mi:ss') and order_status>=to_number("+para+")";
		
		try {
			System.out.println(sql);
			Sqlhelper0.executeUpdate(sql, null);
			System.out.println("ok " +sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
