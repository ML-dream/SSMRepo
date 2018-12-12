package com.xm.testaction.qualitycheck;

import java.sql.ResultSet;

import com.wl.tools.Sqlhelper0;

public class SflowJudge {
// 统计数据，子卡，判断统计表是否有父卡的该道工序
	public static void sflowJudge(String barcode,int fo_no){
//		如果有，则不操作   参数工序是要储存数据的工序
		String gbarcode = "";
//		没有记录 ,查找根条码号
		String sqlb = "select b.gbarcode from attach_info b where b.barcode = '"+barcode+"' ";
		ResultSet rsb = null;
		try {
			rsb = Sqlhelper0.executeQuery(sqlb, null);
			rsb.next();
			gbarcode = rsb.getString(1);
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
		
		String sqla = "select count(1) from (select a.fo_no,a.accept from qualitysum a where a.barcode ='"+gbarcode+"' and a.fo_no = +"+fo_no+")";
		int con = 1;
//		若没有，则在辅助表 保存一条记录
		ResultSet rsa = null;
		try {
			rsa = Sqlhelper0.executeQuery(sqla, null);
			rsa.next();
			con = rsa.getInt(1);
			
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
		
		if(con ==0){

//			往辅助表里插入数据
			String sqlc = "insert into aidsum c (c.gbarcode,c.barcode,c.fo_no) values ('"+gbarcode+"','"+barcode+"',"+fo_no+")";
			try {
				Sqlhelper0.executeUpdate(sqlc, null);
				System.out.println("ok  "+sqlc);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
	}
	
	public static boolean sstateJudge(String type,String runnum,int num,int afo_no,String barcode){
//		子卡审理单在统计表中，没有父卡数据时的处理
		boolean result = false;
		String gbarcode = "";
//		没有记录 ,查找根条码号
		String sqlb = "select b.gbarcode from attach_info b where b.barcode = '"+barcode+"' ";
		ResultSet rsb = null;
		try {
			rsb = Sqlhelper0.executeQuery(sqlb, null);
			rsb.next();
			gbarcode = rsb.getString(1);
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
		
		String sqla = "select count(1) from (select a.fo_no from qualitysum a where a.barcode ='"+gbarcode+"' and a.fo_no = +"+afo_no+")";
		int con = 1;
//		若没有，则在辅助表 保存一条记录
		ResultSet rsa = null;
		try {
			rsa = Sqlhelper0.executeQuery(sqla, null);
			rsa.next();
			con = rsa.getInt(1);
			
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
		
		if(con ==0){
			result = true ;
//			往辅助表里插入数据
			String sqlc = "insert into aidsum c (c.gbarcode,c.barcode,c.fo_no,c.runnum,c.rejtype,c.rejnum) " +
					"values ('"+gbarcode+"','"+barcode+"',"+afo_no+",'"+runnum+"','"+type+"',"+num+")";
			try {
				Sqlhelper0.executeUpdate(sqlc, null);
				System.out.println("ok  "+sqlc);
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}
		
		return result;
	}
}
