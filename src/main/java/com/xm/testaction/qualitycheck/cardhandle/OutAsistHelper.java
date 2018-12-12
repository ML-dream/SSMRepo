package com.xm.testaction.qualitycheck.cardhandle;

import java.util.HashMap;
import java.util.Map;

import com.wl.tools.Sqlhelper;
import com.xm.testaction.qualitycheck.BarcodeLength;
import com.xm.testaction.qualitycheck.SearchFbarcode;

/**
 * 审理单查找外协商
 * @author work
 *
 */
public class OutAsistHelper {
	public static void outAsistHelper(String barcode,RejectJudgeBean bean,int fo_no){
//		查找是否外协，以及外协厂商
		boolean isson = false;
		isson = BarcodeLength.barcodeLength(barcode);
		if(isson){
			boolean isdis = BarcodeLength.isDiscard(barcode);	//是否报废件，报废件用自己的
			if(isdis== false){	//返修件
				barcode = SearchFbarcode.searchFbarcode(barcode);
			}
		}
		String sqlb = "select t.isco,t.waixiecom com,b.companyname name from PROCESSESPLAN t" +
				" left join partsplan a on a.orderid = t.orderid and a.productid= t.productid and a.issuenum = t.issuenum" +
				" left join outassistcom b on b.companyid = t.waixiecom " +
				" where a.codeid = ? and to_number(t.operid) = ?";
		String [] parama = {barcode,fo_no+""};
		
		Map<String, String> mapa = new HashMap<String, String> ();
		try {
			mapa = Sqlhelper.executeQueryMap(sqlb, parama);
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println(mapa);
		String isco = mapa.get("ISCO");
		if(isco!=null && isco.equals("1")){
			bean.setProsource("outer");
			bean.setDuty_man(mapa.get("COM"));	//外协公司id
			bean.setDuty_mann(mapa.get("NAME"));	//外协公司名称
		}else{
			bean.setProsource("self");
		}
	}
}
