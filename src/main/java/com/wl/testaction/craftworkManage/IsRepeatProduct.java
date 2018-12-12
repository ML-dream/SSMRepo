package com.wl.testaction.craftworkManage;

import com.wl.tools.Sqlhelper;

public class IsRepeatProduct {
/**
 * 
 */
	public static String searchFoId (String orderId,String productId,String issueNum){
		String foid= null;
		String sqla ="select distinct t.foid from FOHEADER t where t.productid = '"+productId+"' and t.issuenum = '"+issueNum+"'"; // 这里查找是否重复零件，不应带订单号
		
		try {
			System.out.print(sqla);
			foid= Sqlhelper.exeQueryString(sqla, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return foid;
	}
	public static String searchIsFo (String orderId,String productId,String issueNum){
//		判断是否做过工艺头
		String foid= null;
		String sqla ="select distinct t.foid from FOHEADER t where t.orderid = '"+orderId+"' and t.productid = '"+productId+"' and t.issuenum = '"+issueNum+"'";
		
		try {
			foid= Sqlhelper.exeQueryString(sqla, null);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return foid;
	}
}
