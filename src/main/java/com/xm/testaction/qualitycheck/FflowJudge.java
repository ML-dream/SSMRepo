package com.xm.testaction.qualitycheck;

import java.sql.ResultSet;

import com.wl.tools.Sqlhelper0;

public class FflowJudge {
// 流程卡统计时，主卡，判断是否有未处理的子卡数据
	public static void fflowJudge(String gbarcode,int fo_no,int afo_no){
//		查询辅助表，当前主卡是否有 多少未处理子卡,主卡就是 gbarcode 
		
		String sqla = "select d.barcode from aidsum d where d.gbarcode = '"+gbarcode+"' and d.fo_no ="+fo_no;
		ResultSet rsa = null;
		String barcode = "";
		String sqlb = "";
		try {
			rsa = Sqlhelper0.executeQuery(sqla, null);
			while(rsa.next()){
				barcode = rsa.getString(1);
				ToSum.flowToSum(barcode, afo_no);
				
				sqlb += "update aidsum d set d.ishandle =1 where d.gbarcode='"+gbarcode+"' and d.barcode = '"+barcode+"' and d.fo_no = "+fo_no+";";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(rsa != null){
				try {
					rsa.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		sqlb = "begin " +sqlb +"end;";
		try {
			Sqlhelper0.executeUpdate(sqlb, null);
			System.out.println("ok  "+ sqlb);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
	}
	/*
	public static void fstateJudge(String gbarcode,int fo_no,int afo_no){
//		流程卡 父卡处理子卡数据
		String sqla = "select d.barcode from aidsum d where d.gbarcode = '"+gbarcode+"' and d.fo_no ="+fo_no;
		ResultSet rsa = null;
		String barcode = "";
		String sqlb = "";
		try {
			rsa = Sqlhelper0.executeQuery(sqla, null);
			while(rsa.next()){
				barcode = rsa.getString(1);
				try {
				} catch (Exception e) {
					// TODO: handle exception
				}
				sqlb += "update aidsum d set d.ishandle =1 where d.gbarcode='"+gbarcode+"' and d.barcode = '"+barcode+"' and d.fo_no = "+fo_no+";";
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if(rsa != null){
				try {
					rsa.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		sqlb = "begin " +sqlb +"end;";
		try {
			Sqlhelper0.executeUpdate(sqlb, null);
			System.out.println("ok  "+ sqlb);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	*/
}
