package com.xm.testaction.qualitycheck.sum;

import java.sql.ResultSet;

import com.wl.common.OrderStatus;
import com.wl.common.ProductStatus;
import com.wl.tools.Sqlhelper;
import com.wl.tools.Sqlhelper0;

public class UpToOrders {
//  完工上报  订单 
	public static void upToOrders(String partsplanid){
		int orderstatus = OrderStatus.DOING ;	//订单状态，8 为加工中 
		String orderid = "";
		
//		查找订单号
		String sqla ="select a.orderid from partsplan a where a.partsplanid ='"+partsplanid+"'";
		ResultSet rsa = null;
		try {
			
			rsa = Sqlhelper0.executeQuery(sqla, null);
			rsa.next();
			orderid = rsa.getString(1);
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
//		查找订单下所有产品，以及产品状态    存在零件状态 小于 70（done） 的，则判定为 未完成。因为外协也需要质检质检入库
		String sqlb ="select a.product_id,a.product_status from order_detail a where a.order_id ='"+orderid+"'";
		ResultSet rsb = null;
		try {
			
			rsb = Sqlhelper0.executeQuery(sqlb, null);
			orderstatus =OrderStatus.DONE;
			
			while(rsb.next()){
				int status = rsb.getInt(2);		// 当前状态
				if (status < ProductStatus.DONE){	//如果存在不是“完工”状态的零件
					orderstatus =OrderStatus.DOING;
//					if(status == 5){
//						orderstatus = 10;	//如果有零件开始交付，则订单状态修改为开始交付
//						break;
//					}
					break;
				}else if(status >ProductStatus.DONE){
					orderstatus = OrderStatus.DELIVERING;
					break;
				}
			}
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
//		查找订单的实际完工时间
		String timeSql = "select to_char(max(t.se_time),'yyyy-MM-dd') from order_detail t where t.order_id= '"+orderid+"'";
		
		String rendTime = "2000-01-01";
		try {
			rendTime = Sqlhelper.exeQueryString(timeSql, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
//		上报到 orders
		String sqlc = "update orders a set a.order_status ='"+orderstatus+"',a.rendtime=to_date('"+rendTime+"','yyyy-MM-dd HH:mi:ss') where a.order_id ='"+orderid+"'";
		try {
			Sqlhelper0.executeUpdate(sqlc, null);
			System.out.println("ok "+sqlc);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
//			Sqlhelper0.close();
		}
		
	}
}
