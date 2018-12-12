package com.xm.testaction.qualitycheck.sum;

import com.wl.tools.Sqlhelper;

public class RejectView {
	/**
	 * 返修数视图
	 */
	public static void fixView(){
		String sqla = "create or replace view fofix " +
				"       as " +
				"       select d.barcode,d.fo_no,sum(nvl(d.rejectnum,0)) fixnum from fixdetail f " +
				"       left join reject_state d on d.runnum = f.staterunnum" +
				"     group by d.barcode,d.fo_no ";
		try {
			System.out.println(sqla);
			Sqlhelper.executeUpdate(sqla, null);
			System.out.println("ok   " +sqla);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 报废数视图
	 */
	public static void disView(){
		String sqla = "create or replace view fodis" +
				"       as " +
				"       select d.barcode,d.fo_no,sum(nvl(d.rejectnum,0)) disnum from disdetail g " +
				"       left join reject_state d on d.runnum = g.staterunnum" +
				"     group by d.barcode,d.fo_no";
		try {
			System.out.println(sqla);
			Sqlhelper.executeUpdate(sqla, null);
			System.out.println("ok   " +sqla);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 每个条码，工序质量损失视图
	 */
	public static void foLoss(){
		String sqla = "create or replace view foLoss " +
				"       as " +
				"   select t.barcode,t.fo_no,sum(nvl(a.timeloss,0)) qualityLoss from po_routing2 t " +
				"           left join reject_state  a on a.barcode= t.barcode and a.fo_no=t.fo_no    " +
				"     group by t.barcode,t.fo_no";
		
		
		try {
			System.out.println(sqla);
			Sqlhelper.executeUpdate(sqla, null);
			System.out.println("ok   " +sqla);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 零件-工序质量损失
	 */
	public static void proFoLoss(){
		String sqla = "create or replace view profoLoss " +
				"       as" +
				"   select t.order_id,t.drawingid,t.issuenum,a.fo_no,sum(nvl(a.qualityLoss,0)) qualityLoss from po_router t " +
				"           left join foLoss a on a.barcode = t.barcode" +
				"     group by t.order_id,t.drawingid,t.issuenum,a.fo_no";
		
		
		try {
			System.out.println(sqla);
			Sqlhelper.executeUpdate(sqla, null);
			System.out.println("ok   " +sqla);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	/**
	 * 零件质量损失
	 */
	public static void productLoss(){
		String sqla = "create or replace view productLoss " +
				"       as" +
				"   select t.order_id orderId,t.drawingid productId,t.issuenum,sum(t.qualityLoss)  qualityLoss from profoLoss t " +
				"   group by t.order_id,t.drawingid,t.issuenum";
		
		
		try {
			System.out.println(sqla);
			Sqlhelper.executeUpdate(sqla, null);
			System.out.println("ok   " +sqla);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	public static void orderLoss(){
		String sqla = "create or replace view orderLoss " +
				"       as" +
				"   select t.order_id orderId,sum(t.qualityLoss)  qualityLoss from profoLoss t " +
				"   group by t.order_id ";
		
		
		try {
			System.out.println(sqla);
			Sqlhelper.executeUpdate(sqla, null);
			System.out.println("ok   " +sqla);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
