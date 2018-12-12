package com.xm.testaction.qualitycheck.sum;

import java.util.HashMap;
import java.util.Map;

import com.mysql.jdbc.ResultSet;
import com.wl.common.OrderStatus;
import com.wl.common.ProductStatus;
import com.wl.tools.Sqlhelper;
import com.xm.testaction.qualitycheck.BarcodeLength;

public class UpToProcessPlan {
//	public static void main (String []args){
//		String barcode = "2016122103460782";
//		int fo = 5;
//		upToProcessPlan(barcode, fo);
//	}
	public static void upToProcessPlan(String barcode,int fo){
//		上报当前工序，每次都重新统计数据，即使质检这边修改数据也不影响
//		是否报废件，报废件还要上报自己的工序计划
		String sqla = "select a.order_id,a.drawingid,a.issuenum,a.input_num from po_router a where a.barcode = '"+barcode+"'";	//查订单、零件、版本以及投入量 
		String orderid = "";
		String drawingid = "";
		String issuenum = "";
		String input = "0" ;
		Map< String, String> mapa = new HashMap<String, String>();
		try {
			System.out.println(sqla);
			mapa=Sqlhelper.executeQueryMap(sqla, null);
			System.out.println(mapa);
			orderid = mapa.get("ORDER_ID");
			drawingid = mapa.get("DRAWINGID");
			issuenum = mapa.get("ISSUENUM");
			input = mapa.get("INPUT_NUM");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		是否报废件
		disUpToProcessPlan(barcode, fo);
//		根批次上报
		String sqlb = "select sum(b.accept_num) accept,sum(b.reject_num) reject from po_routing2 b where b.fo_no= "+fo+" and b.barcode in " +
				"(select a.barcode from po_router a where a.order_id =? and a.drawingid = ? and a.issuenum=?)";
		String[] params1 = {orderid,drawingid,issuenum};
		Map< String, String> mapb = new HashMap<String, String>();	//该零件该工序的所有流程卡的合格数
		try {
			System.out.println(sqlb);
			mapb =Sqlhelper.executeQueryMap(sqlb, params1);
			System.out.println(mapb);
		} catch (Exception e) {
			// TODO: handle exception
		}
//		上报
		int status = ProductStatus.DOING;
		String accepts = mapb.get("ACCEPT");
		String reject = mapb.get("REJECT");
		String operstatus = "0";
		if(accepts==null|| accepts.equals("") ){
			accepts="0";
		}
		int accept = Integer.parseInt(accepts);
		if(input==null|| input.equals("") ){
			input="0";
		}
		int inputnum = Integer.parseInt(input);
		if(accept >= inputnum){
			status = ProductStatus.DONE;
			operstatus = "2";	//完成	
		}else{
			operstatus = "1";	//正在加工
		}
		String sqlc ="update processesplan c set c.passnum =? ,c.operstatus = ?,c.failurenum= ? where c.orderid = ? and c.productid =? and c.issuenum= ? and c.operid='"+fo+"' and isdiscard='0'";
		String []params2 = {accepts,operstatus,reject,orderid,drawingid,issuenum};
		try {
			System.out.println(sqlc);
			Sqlhelper.executeUpdate(sqlc, params2);
			System.out.println("ok "+sqlc);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		把零件与订单状态置为开工
		try {
			String order = OrderStatus.DOING + "";
			String product = ProductStatus.DOING + "";
			String sqld = "begin update order_detail t set t.product_status='"+product+"' where t.order_id = '"+orderid+"' and t.product_id ='"+drawingid+"' and t.issue_num='"+issuenum+"'; "+
					"update orders m set m.order_status='"+order+"' where m.order_id = '"+orderid+"';  end;";
			Sqlhelper.executeUpdate(sqld, null);
			System.out.println("ok  "+sqld);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		

	}
/**
 * 最后一道工序的处理
 * @param barcode
 * @param fo
 */
	public static void lastFoUp(String barcode,int lastfo){
//		只考虑修改根条码号的状态
//		如果最后一道工序完工，把最后一道工序的合格数上报到其他工序
		String sqla = "select t.passnum,t.num,t.orderid,t.productid,t.issuenum from PROCESSESPLAN t " +
				"  left join po_router a on a.order_id= t.orderid and a.drawingid = t.productid and a.issuenum = t.issuenum and t.isdiscard='0' " +
				" where a.barcode = '"+barcode+"' and t.operid ='"+lastfo+"'";
		Map<String, String> mapa = new HashMap<String, String>();
		try {
			mapa = Sqlhelper.executeQueryMap(sqla, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		String spass = mapa.get("PASSNUM");
		int pass= Integer.parseInt(spass);
		int num = Integer.parseInt(mapa.get("NUM"));
		if(pass >= num){
			String orderId = mapa.get("ORDERID");
			String productId = mapa.get("PRODUCTID");
			String issuenum = mapa.get("ISSUENUM");
			
			String sqlb = "  update processesplan t set t.passnum ='"+pass+"',t.operstatus='2' where t.orderid = '"+orderId+"' and" +
					" t.productid='"+productId+"' and t.issuenum='"+issuenum+"' and t.isdiscard='0'";
			try {
				Sqlhelper.executeUpdate(sqlb, null);
				System.out.println("ok  "+sqlb);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
	}
	public static void disUpToProcessPlan(String barcode,int fo){
		
		boolean isson = BarcodeLength.barcodeLength(barcode);
		if(isson){
			boolean isdiscard =BarcodeLength.isDiscard(barcode);
			if(isdiscard){
				
				String sqla = "select a.order_id,a.drawingid,a.issuenum,a.input_num from po_router a where a.barcode = '"+barcode+"'";	//查订单、零件、版本以及投入量 
				String orderid = "";
				String drawingid = "";
				String issuenum = "";
				String input = "0" ;
				Map< String, String> mapa = new HashMap<String, String>();
				try {
					System.out.println(sqla);
					mapa=Sqlhelper.executeQueryMap(sqla, null);
					System.out.println(mapa);
					orderid = mapa.get("ORDER_ID");
					drawingid = mapa.get("DRAWINGID");
					issuenum = mapa.get("ISSUENUM");
					input = mapa.get("INPUT_NUM");
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				String dissql ="select  sum(nvl(t.accept_num,0)) accept,sum(nvl(t.reject_num,0)) rej " +
				" from po_routing2 t where  t.fo_no='"+fo+"' and t.barcode in( " +
					"select t.barcode from attach_info t " +
						"start with t.barcode= '"+barcode+"' " +
						"connect by prior t.barcode = t.fbarcode)";
				Map< String, String> dismap = new HashMap<String, String>();	//该零件该工序的所有流程卡的合格数
				try {
					System.out.println(dissql);
					dismap =Sqlhelper.executeQueryMap(dissql, null);
					System.out.println(dismap);
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				int status = ProductStatus.DOING;
				String accepts = dismap.get("ACCEPT");
				String reject = dismap.get("REJECT");
				String operstatus = "0";
				if(accepts==null|| accepts.equals("") ){
					accepts="0";
				}
				int accept = Integer.parseInt(accepts);
				if(input==null|| input.equals("") ){
					input="0";
				}
				int inputnum = Integer.parseInt(input);
				if(accept >= inputnum){
					status = ProductStatus.DONE;
					operstatus = "2";	//完工	
				}else{
					operstatus = "1";	//正在加工
				}
				String sqlc ="update processesplan c set c.passnum =? ,c.operstatus = ?,c.failurenum= ? where c.orderid = ? and c.productid =? and c.issuenum= ? and c.operid='"+fo+"' and isdiscard=?";
				String []params2 = {accepts,operstatus,reject,orderid,drawingid,issuenum,barcode};
				try {
					System.out.println(sqlc);
					Sqlhelper.executeUpdate(sqlc, params2);
					System.out.println("ok "+sqlc);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
	}
}
