package com.xm.testaction.qualitycheck.sum;

import com.wl.tools.Sqlhelper0;

public class RewardsView {
//	public static void main (String [] args){
//		String bdate ="2016-12-01";
//		String edate ="2016-12-30";
//		rewardsView(bdate, edate);
//	}
	public static void rewardsView(String bdate,String edate){
//		创建工时处理的 视图，这个视图是零件下所有条码的数据
		String sql = "create or replace view rewardstemp " +
				"as " +
				"select distinct k.order_id,k.drawingid,k.product_name,d.barcode,d.fo_no,d.fo_opname,round(d.rated_time,2) rated_time, " +	//额定工时从流程卡取，否则子卡无工时
				"d.accept_num,d.reject_num,d.usedtime,d.workerid,(d.accept_num*nvl(d.rated_time,0)) basetime, " +
				"h.staff_name workername ,a.order_date,d.remark,g.workbranch,l.typename,l.price,a.customer " +
				"from po_routing2 d " +
				"left join po_router k on k.barcode = d.barcode " +
//				" left join partsplan c on c.codeid = d.barcode " +		//子卡的数据找不到
				"left join order_detail b on  b.order_id = k.order_id and b.product_id = k.drawingid and b.issue_num = k.issuenum " +
				"left join orders a on a.order_id = b.order_id " +
				"left join fo_detail g on g.product_id = b.product_id and g.issue_num = b.issue_num and to_number(g.fo_no) = d.fo_no and g.isinuse='1' " +
				"left join employee_info h on h.staff_code = d.workerid " +
				
				"left join workbranch l on l.typeid = g.workbranch "+
				"where d.barcode in " +
					"(select w.barcode from attach_info w " +
					"left join partsplan x on x.codeid = w.gbarcode " +
					"left join order_detail y on y.order_id = x.orderid and y.product_id = x.productid " +
					"left join orders z  on z.order_id = y.order_id " +
					
					" where  z.order_date between  to_date('"+bdate+"','yyyy-mm-dd hh24:mi:ss') " +
					" and to_date('"+edate+"','yyyy-mm-dd hh24:mi:ss') " +
					"union all " +
					" select q.codeid from partsplan q " +
					" left join order_detail p on p.order_id = q.orderid and p.product_id = q.productid" +
					" left join orders o on o.order_id = p.order_id" +
					" where o.order_date between  to_date('"+bdate+"','yyyy-mm-dd hh24:mi:ss') " +
					" and to_date('"+edate+"','yyyy-mm-dd hh24:mi:ss')" +
				" ) order by order_date asc,order_id asc,drawingid asc,fo_no asc";
		try {
			System.out.println(sql);
			Sqlhelper0.executeUpdate(sql, null);
			System.out.println("ok " +sql);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
