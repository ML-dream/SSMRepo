package com.xm.testaction.qualitycheck.sum;

import com.wl.tools.Sqlhelper0;

public class OutAsistCost {
//	public static void outAsistCost(CostConfirmBean bean){
////		外协成本统计
//		double outasist = 0;
//		String orderid = bean.getOrderid();
//		String productid = bean.getDraid();
//		
////		待续
//		String s = CostCaculate.douToStr(outasist);
//		bean.setOutasist(s);
//	}
	public static void outAsistView(String bdate,String edate){
//		外协成本统计 视图
		String sql = "create or replace view outAsistView " +
		"as " +
//		待续
//		" select a.order_id,b.productid,sum(b.sumprice) outasist from orders a " +
		"select a.order_id,b.productid,sum(nvl(b.unitprice,0)*nvl(c.num,0)) outasist from orders a "+
//		"left join  outasisttemp b on b.orderid = a.order_id  " +
		"left join outassiststat b on b.orderid = a.order_id " +
		"left join processesplan c on c.orderid = b.orderid and c.productid = b.productid and c.issuenum = b.issuenum "+
		" where a.order_date between  to_date('"+bdate+"','yyyy-mm-dd hh24:mi:ss') " +
		" and to_date('"+edate+"','yyyy-mm-dd hh24:mi:ss')"+
		" group by a.order_id,b.productid";
	
	try {
		System.out.println(sql);
		Sqlhelper0.executeUpdate(sql, null);
		System.out.println("ok " +sql);
	} catch (Exception e) {
		// TODO: handle exception
	}
	}
}
