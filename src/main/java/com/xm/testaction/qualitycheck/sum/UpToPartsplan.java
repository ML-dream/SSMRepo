package com.xm.testaction.qualitycheck.sum;

import java.util.HashMap;
import java.util.Map;

import com.wl.common.ProductStatus;
import com.wl.tools.Sqlhelper;
import com.xm.testaction.qualitycheck.BarcodeLength;

public class UpToPartsplan {
//	public static void main (String []args){
//		String barcode ="201612210346080";
//		upToPartsplan(barcode);
//	}
//	上报零件计划
	public static void upToPartsplan(String barcode){
//		流程卡类型，返工、多批次都只上报主卡，报废上报自己计划与主卡
//		查找最后一道工序
		String sqla = "select a.fo_no from po_routing2 a where a.barcode = '"+barcode+"' order by a.fo_no desc";
		String fo = null;
		try {
			fo = Sqlhelper.exeQueryString(sqla, null);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
//		是否子卡，子卡类型
		boolean isson = BarcodeLength.barcodeLength(barcode);
//		是否报废件
		boolean isdiscard = false;
		if(isson){
			isdiscard = BarcodeLength.isDiscard(barcode);
		}
		
		if(!isson){
			upMainParts(barcode, fo);	//主卡
		}else if(isdiscard){
			upDisParts(barcode, fo);
		}else {
			upSonParts(barcode,fo);
		}
		
//		查找零件计划下最后一道工序的所有数据
	}
	
	public static void upMainParts(String barcode,String fo){
		int accept = 0;	// 合格数量
		int reject = 0;
		int finish = 0;	//完成数量
		
		int partnum = 0;	// 零件计划计划数量
//		int passnum = 0;	//零件计划合格数量
//		int partfinish = 0;
		int partstatus =ProductStatus.DOING;	// 计划中零件状态
		
		String partsplanid = "";	//零件计划号,可能查不出来  
		
		String sqla = "select  sum(nvl(t.accept_num,0)) accept,sum(nvl(t.reject_num,0)) rej,sum(nvl(t.confirm_num,0)) con" +
				" from po_routing2 t where  t.fo_no='"+fo+"' and t.barcode in" +
				" (select a.barcode from attach_info a where a.gbarcode = '"+barcode+"' " +
				" union select '"+barcode+"' from dual )";
		Map<String, String> mapa =new HashMap<String, String>();
		try {
			mapa = Sqlhelper.executeQueryMap(sqla, null);
			accept = Integer.parseInt(mapa.get("ACCEPT"));
			reject = Integer.parseInt(mapa.get("REJ"));
			finish = Integer.parseInt(mapa.get("CON"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		零件计划数量
		
		String partsql = "select a.partsplanid partId,a.partnum,a.codeid from partsplan a where a.codeid = '"+barcode+"'";
		Map<String, String> mapb = new HashMap<String, String>();
		try {
			mapb = Sqlhelper.executeQueryMap(partsql, null);
			partsplanid = mapb.get("PARTID");
			partnum = Integer.parseInt(mapb.get("PARTNUM"));
		} catch (Exception e) {
			// TODO: handle exception
		}
//		判断状态
		partstatus = statusHand(accept, partnum, partsplanid);
//		更新状态
		String mupdatesql ="update partsplan a set a.passnum ='"+accept+"',a.finishnum='"+finish+"',a.failurenum='"+reject+"',a.partstatus='"+partstatus+"' where a.codeid='"+barcode+"'";
		try {
			Sqlhelper.executeUpdate(mupdatesql, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
//		如果做完了，上报零件
		if(accept >= partnum){
			try {
				UpToProduct.upToProduct(partsplanid);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}
	public static void upDisParts(String barcode,String fo){
		
		int accept = 0;	// 合格数量
		int reject = 0;
		int finish = 0;	//完成数量
		
		int partnum = 0;	// 零件计划计划数量
		int passnum = 0;	//零件计划合格数量
		int partfinish = 0;
		int partstatus =ProductStatus.DOING;	// 计划中零件状态
		
		String partsplanid = "";	//零件计划号,可能查不出来  
		
		String sqla ="select  sum(nvl(t.accept_num,0)) accept,sum(nvl(t.reject_num,0)) rej,sum(nvl(t.confirm_num,0)) con" +
				" from po_routing2 t where  t.fo_no='"+fo+"' and t.barcode in( " +
					"select t.barcode from attach_info t " +
						"start with t.barcode= '"+barcode+"' " +
						"connect by prior t.barcode = t.fbarcode)";
		
		Map<String, String> mapa =new HashMap<String, String>();
		try {
			mapa = Sqlhelper.executeQueryMap(sqla, null);
			accept = Integer.parseInt(mapa.get("ACCEPT"));
			reject = Integer.parseInt(mapa.get("REJ"));
			finish = Integer.parseInt(mapa.get("CON"));
		} catch (Exception e) {
			// TODO: handle exception
		}
		
//		零件计划数量
		
		String partsql = "select a.partsplanid partId,a.partnum,a.codeid from partsplan a where a.codeid = '"+barcode+"'";
		Map<String, String> mapb = new HashMap<String, String>();
		try {
			mapb = Sqlhelper.executeQueryMap(partsql, null);
			partsplanid = mapb.get("PARTID");
			partnum = Integer.parseInt(mapb.get("PARTNUM"));
		} catch (Exception e) {
			// TODO: handle exception
		}
//		判断状态
		partstatus = statusHand(passnum, partnum, partsplanid);
//		更新状态
		String mupdatesql ="update partsplan a set a.passnum ='"+passnum+"',a.finishnum='"+finish+"',a.failurenum='"+reject+"',a.partstatus='"+partstatus+"' where a.codeid='"+barcode+"'";
		try {
			Sqlhelper.executeUpdate(mupdatesql, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
//		如果做完了，上报零件
		if(passnum >= partnum){
			try {
				UpToProduct.upToProduct(partsplanid);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
//		报废件还要上报根条码号
		String gbarcode ="";
		String sqlc ="select t.gbarcode from attach_info t where t.barcode ='"+barcode+"'";
		try {
			gbarcode =Sqlhelper.exeQueryString(sqlc, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			upMainParts(gbarcode, fo);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static void upSonParts (String barcode,String fo){
//		返工、多批次
//		根条码号
		String gbarcode ="";
		String sqlc ="select t.gbarcode from attach_info t where t.barcode ='"+barcode+"'";
		try {
			gbarcode =Sqlhelper.exeQueryString(sqlc, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		try {
			upMainParts(gbarcode, fo);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	public static int statusHand(int passnum,int partnum,String partsplanid){
//		这里的passnum  为完成数量，不是通过数量
		int status=0;
		if(passnum >= partnum){
			status =ProductStatus.DONE;		//如果 大于计划数量，则 修改状态

		}else if(passnum > 0 ){
			status = ProductStatus.DOING;
		}
		return status;
	}
	
}
