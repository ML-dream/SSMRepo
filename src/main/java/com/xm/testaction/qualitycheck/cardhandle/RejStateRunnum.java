package com.xm.testaction.qualitycheck.cardhandle;

import java.sql.ResultSet;
import java.util.Calendar;

import com.wl.tools.Sqlhelper0;

public class RejStateRunnum {
	public static String rejStateRunnum(){
//		审理单流水号
		String result = "";
		int runnum =0;
		
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改

		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH)+1; 
		String smonth = ""+month;			//	月份要判断
		if(month<10){
			smonth = "0"+smonth;
		}
		int date = c.get(Calendar.DATE); 
		String sdate = "" + date;
		if(date<10){
			sdate = "0"+sdate;
		}
		ResultSet numRs =null;
		try{
			String numsql = "select hnum,mnum from runnum";
			
			
			numRs=Sqlhelper0.executeQuery(numsql, null);
			numRs.next();
	//		System.out.println(numRs.getInt(2));
			runnum = numRs.getInt(2) +1;
			String num = Integer.toString(runnum);
			String hnum = numRs.getString(1);
	//		流水号默认长度为12，其中日期占8 位
			for(int i=0,n=3-num.length();i<n;i++)
			{
				result += "0";
				}
			result = ""+hnum+year + smonth+ sdate +result+ runnum;
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(numRs != null){
		    	try {
		    		numRs.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
			}
		}
//		更新 runnum 表 
		runnum ++;
		if(runnum == 999){
			runnum =0;	//当序列号达到 9999时，刷新序列号
		}
		try {
			String barcodesql = "update runnum set mnum ="+runnum;
			
			Sqlhelper0.executeUpdate(barcodesql, null);
			System.out.println(barcodesql +"ok");
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return result;
	}
	public static String fixRunnum (){
		String result = "";
		int runnum =0;
		
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改

		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH)+1; 
		String smonth = ""+month;			//	月份要判断
		if(month<10){
			smonth = "0"+smonth;
		}
		int date = c.get(Calendar.DATE); 
		String sdate = "" + date;
		if(date<10){
			sdate = "0"+sdate;
		}
		ResultSet numRs =null;
		try{
			String numsql = "select first,mnum from FIXCARDRUNNUM";
			
			
			numRs=Sqlhelper0.executeQuery(numsql, null);
			numRs.next();
	//		System.out.println(numRs.getInt(2));
			runnum = numRs.getInt(2) +1;
			String num = Integer.toString(runnum);
			String hnum = numRs.getString(1);
	//		流水号默认长度为12，其中日期占8 位
			for(int i=0,n=3-num.length();i<n;i++)
			{
				result += "0";
				}
			result = ""+hnum+year + smonth+ sdate +result+ runnum;
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(numRs != null){
		    	try {
		    		numRs.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
			}
		}
//		更新 runnum 表 
		runnum ++;
		if(runnum == 999){
			runnum =0;	//当序列号达到 9999时，刷新序列号
		}
		try {
			String barcodesql = "update FIXCARDRUNNUM set mnum ="+runnum;
			
			Sqlhelper0.executeUpdate(barcodesql, null);
			System.out.println(barcodesql +"ok");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
	
	public static String disRunnum (){
		String result = "";
		
		int runnum =0;
		
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改

		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH)+1; 
		String smonth = ""+month;			//	月份要判断
		if(month<10){
			smonth = "0"+smonth;
		}
		int date = c.get(Calendar.DATE); 
		String sdate = "" + date;
		if(date<10){
			sdate = "0"+sdate;
		}
		ResultSet numRs =null;
		try{
			String numsql = "select hnum,mnum from DISCARDRUNNUM";
			
			
			numRs=Sqlhelper0.executeQuery(numsql, null);
			numRs.next();
	//		System.out.println(numRs.getInt(2));
			runnum = numRs.getInt(2) +1;
			String num = Integer.toString(runnum);
			String hnum = numRs.getString(1);
	//		流水号默认长度为12，其中日期占8 位
			for(int i=0,n=3-num.length();i<n;i++)
			{
				result += "0";
				}
			result = ""+hnum+year + smonth+ sdate +result+ runnum;
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(numRs != null){
		    	try {
		    		numRs.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
			}
		}
//		更新 runnum 表 
		runnum ++;
		if(runnum == 999){
			runnum =0;	//当序列号达到 9999时，刷新序列号
		}
		try {
			String barcodesql = "update DISCARDRUNNUM set mnum ="+runnum;
			
			Sqlhelper0.executeUpdate(barcodesql, null);
			System.out.println(barcodesql +"ok");
		} catch (Exception e) {
			// TODO: handle exception
		}
		return result;
	}
}
