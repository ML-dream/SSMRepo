package com.xm.testaction.qualitycheck.sum;

import java.sql.ResultSet;
import java.text.DecimalFormat;

import com.wl.tools.Sqlhelper0;

public class TimeLossSum {
// 工时损耗统计
	public static double fixTime(String barcode,int fo_no){
//		返修工时损耗统计
		double timeloss=0.00;
//		9-22   返修工时跟数量有关
		String  sql = "select a.usedtime,a.reject_num,a.confirm_num from po_routing2 a where a.barcode ='"+barcode+"' and a.fo_no="+fo_no;
		ResultSet rs = null;
		try {
			
			rs = Sqlhelper0.executeQuery(sql, null);
			rs.next();
			double time = rs.getDouble(1);
			double reject = rs.getDouble(2);
			double num = rs.getDouble(3);
			timeloss = (reject/num)*time;	//工时损耗 =  不合格的比例 * 总工时
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			Sqlhelper0.close();
			if(rs != null){
		    	try {
				rs.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
//		DecimalFormat df = new DecimalFormat( "0.00");
//		timeloss = Double.parseDouble(df.format(timeloss));
		return timeloss;
	}
	public static double discardTime(String barcode,int fo_no){
//		返修工时损耗统计
		double timeloss=0.00;
		String  sql = "select a.usedtime from po_routing2 a where a.barcode ='"+barcode+"' and a.fo_no<"+fo_no;
		ResultSet rs = null;
		try {
			
			rs = Sqlhelper0.executeQuery(sql, null);
			while(rs.next()){
				double tem = rs.getDouble(1);
				timeloss += tem;
			};
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			Sqlhelper0.close();
			if(rs != null){
		    	try {
				rs.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
			
		}
		
		String  sqlb = "select a.reject_num,a.confirm_num from po_routing2 a where a.barcode ='"+barcode+"' and a.fo_no="+fo_no;
		ResultSet rsb = null;
		try {
			
			rsb = Sqlhelper0.executeQuery(sqlb, null);
			rsb.next();
			double reject = rsb.getDouble(2);
			double num = rsb.getDouble(3);
			timeloss = (reject/num)*timeloss;	//工时损耗 =  不合格的比例 * 总工时
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			Sqlhelper0.close();
			if(rsb != null){
		    	try {
				rsb.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		return timeloss;
	}
}
