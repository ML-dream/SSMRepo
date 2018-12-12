package com.xm.testaction.qualitycheck;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.wl.tools.Sqlhelper0;

public class SearchFbarcode {
	public static String searchFbarcode (String barcode){
		String fbarcode = "";
//			从attach_info表中找根barcode
			String fbarcodesql ="select gbarcode from attach_info where barcode='"+barcode+"'";
			ResultSet fbarcoderst = null;
			try {
				    		
				fbarcoderst = Sqlhelper0.executeQuery(fbarcodesql, null);
				fbarcoderst.next();
				    fbarcode = fbarcoderst.getString(1);
				}catch (Exception e) {
						// TODO: handle exception
					}finally{
//						//    Sqlhelper0.close();
						if(fbarcoderst != null){
					    	try {
					    		fbarcoderst.close();
						} catch (Exception e2) {
						// TODO: handle exception
							}
					}
				}
		return fbarcode;
	}
}
