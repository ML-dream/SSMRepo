package com.xm.testaction.qualitycheck;

import java.sql.ResultSet;

import com.wl.tools.Sqlhelper0;

// ＞ 15 返回true
public class BarcodeLength {
	public static Boolean barcodeLength(String barcode){
		int length = 0;
		length= barcode.length();
		boolean cresult = (length > 15 ? true:false);
		return cresult;
	}
	
//	判断是否是报废件
	public static boolean isDiscard(String barcode){
		boolean isDiscard = false;
		int status = 0;
		String sql = "select status from attach_info where barcode ='"+barcode+"'";
		ResultSet rs = null;
		try {
			
			rs = Sqlhelper0.executeQuery(sql, null);
			rs.next();
			status = rs.getInt(1);
			if(status ==3){
				isDiscard = true;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			//    Sqlhelper0.close();
			if(rs != null){
		    	try {
				rs.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		return isDiscard;
	}
}
