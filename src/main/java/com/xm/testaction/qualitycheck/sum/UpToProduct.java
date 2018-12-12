package com.xm.testaction.qualitycheck.sum;

import java.sql.ResultSet;

import com.wl.common.ProductStatus;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Sqlhelper0;

public class UpToProduct {
//  上报到产品   order_detail  9-10
	public static void upToProduct (String partsplanid){
//		查询
		String productid ="";	//产品号
		String issuenum = "";	//版本号
		String orderid = "";	//订单号
		int passnum =0;		//完成数量
		int productnum =0;		//产品数量
		int productstatus = ProductStatus.DOING;	//产品状态  ,加工中 
		
//		零件计划 对应 产品号、版本号
		String sqla ="select a.productid,a.issuenum,a.orderid from partsplan a where a.partsplanid = '"+partsplanid+"'";
		ResultSet rsa =null;
		try {
			
			rsa = Sqlhelper0.executeQuery(sqla, null);
			rsa.next();
			productid = rsa.getString(1);
			issuenum = rsa.getString(2);
			orderid = rsa.getString(3);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(rsa != null){
		    	try {
				rsa.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		
//		产品下所有零件计划的通过数量
		String sqlb ="select sum(b.passnum) from partsplan b where b.partsplanid in " +
					"(select a.partsplanid from partsplan a where a.productid = '"+productid+"' and a.issuenum ='"+issuenum+"' and orderid='"+orderid+"')";
		ResultSet rsb = null;
		try {
			
			rsb = Sqlhelper0.executeQuery(sqlb, null);
			rsb.next();
			passnum = rsb.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(rsb != null){
		    	try {
				rsb.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		
//		查找 产品订单数量
		String sqlc = "select a.purduct_num from order_detail a where a.product_id ='"+productid+"' and a.issue_num ='"+issuenum+"' and order_id='"+orderid+"'";
		ResultSet rsc = null;
		try {
			
			rsc = Sqlhelper0.executeQuery(sqlc, null);
			rsc.next();
			productnum = rsc.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(rsc != null){
		    	try {
				rsc.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
//		判断产品状态
		if(passnum >= productnum){
			productstatus = ProductStatus.DONE;	//加工完成
//			上报到订单
			try {
				UpToOrders.upToOrders(partsplanid);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
//		查找零件的实际完工时间
		String timeSql = "select to_char(max(t.checkdate),'yyyy-MM-dd') from po_routing2 t" +
				"       left join po_router a on a.barcode= t.barcode" +
				" where a.order_id='"+orderid+"' and a.drawingid='"+productid+"' and a.issuenum='"+issuenum+"'";
		
		String rendTime = "2000-01-01";
		try {
			rendTime = Sqlhelper.exeQueryString(timeSql, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		上报 至 order_detail
		String sqld ="update order_detail a set a.finishnum ="+passnum+" ,a.product_status = "+productstatus+",a.se_time=to_date('"+rendTime+"','yyyy-MM-dd HH:mi:ss') where a.order_id='"+orderid+"' and a.product_id ='"+productid+"' and a.issue_num ='"+issuenum+"'";
		try {
			Sqlhelper0.executeUpdate(sqld, null);
			System.out.println("ok "+sqld);
		} catch (Exception e) {
			// TODO: handle exception
		}
	
	}
}
