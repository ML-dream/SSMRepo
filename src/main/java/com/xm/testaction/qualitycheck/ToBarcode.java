package com.xm.testaction.qualitycheck;

import java.sql.Date;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import com.wl.tools.Sqlhelper0;

public class ToBarcode {
	public static String toBarcode(String partsplanid){
	//	零件计划 生成条码号  
		
		String barcode = "";
		int runnum =0;
		
//		获取当前日期 
//		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
//		System.out.println(df.format(new Date(runnum, runnum, runnum)));// new Date()为获取当前系统时间
		
		Calendar c = Calendar.getInstance();//可以对每个时间域单独修改

		int year = c.get(Calendar.YEAR); 
		int month = c.get(Calendar.MONTH)+1; 
		String smonth = ""+month;			//	月份要判断
		if(month<10){
			smonth = "0"+smonth;
		}
		int date = c.get(Calendar.DATE); 
		String sdate= ""+date;
		if(date<10){
			sdate = "0"+sdate;
		}
		int hour = c.get(Calendar.HOUR);
		String shour = ""+hour;
		if(hour<10){
			shour = "0"+shour;
		}
		int min = c.get(Calendar.MINUTE);
		String smin = ""+min;
		if(min<10){
			smin = "0"+smin;
		}
		ResultSet numRs =null;
		try{
			String numsql = "select mnum from barcode";
			
			
			numRs=Sqlhelper0.executeQuery(numsql, null);
			numRs.next();
	//		System.out.println(numRs.getInt(2));
			runnum = numRs.getInt(1) +1;
			String num = Integer.toString(runnum);
	//		条形码默认长度为15，其中日期占12位
			for(int i=0,n=3-num.length();i<n;i++)
			{
				barcode += "0";
				}
			barcode = ""+year + smonth+ sdate+shour+smin +barcode+ runnum;
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
		
//		往零件计划中插入条形码 
		try {
			String  partSql = "update partsplan a set a.codeid ='"+barcode+"' where a.partsplanid ='"+partsplanid+"' ";
			
			Sqlhelper0.executeUpdate(partSql, null);
			System.out.println(partSql);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
//			Sqlhelper0.close();
		}
		
//		更新 barcode 表 
//		runnum ++;
		if(runnum >= 899){
			runnum =0;	//当序列号达到 999时，刷新序列号
		}
		try {
			String barcodesql = "update barcode set mnum = "+runnum;
			
			Sqlhelper0.executeUpdate(barcodesql, null);
			System.out.println(barcodesql +"ok");
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
//			Sqlhelper0.close();
		}
		
	return barcode;
	}
	
	public static String toWeldBarcode(){
		//	无参数 生成条码号  
			
			String barcode = "";
			int runnum =0;
			
			Calendar c = Calendar.getInstance();//可以对每个时间域单独修改

			int year = c.get(Calendar.YEAR); 
			int month = c.get(Calendar.MONTH)+1; 
			String smonth = ""+month;			//	月份要判断
			if(month<10){
				smonth = "0"+smonth;
			}
			int date = c.get(Calendar.DATE); 
			String sdate= ""+date;
			if(date<10){
				sdate = "0"+sdate;
			}
			int hour = c.get(Calendar.HOUR);
			String shour = ""+hour;
			if(hour<10){
				shour = "0"+shour;
			}
			int min = c.get(Calendar.MINUTE);
			String smin = ""+min;
			if(min<10){
				smin = "0"+smin;
			}
			ResultSet numRs =null;
			try{
				String numsql = "select mnum from barcode";
				
				
				numRs=Sqlhelper0.executeQuery(numsql, null);
				numRs.next();
		//		System.out.println(numRs.getInt(2));
				runnum = numRs.getInt(1) +1;
				String num = Integer.toString(runnum);
		//		条形码默认长度为15，其中日期占12位
				for(int i=0,n=3-num.length();i<n;i++)
				{
					barcode += "0";
					}
				barcode = ""+year + smonth+ sdate+shour+smin +barcode+ runnum;
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
			
			
//			更新 barcode 表 
			runnum ++;
			if(runnum == 999){
				runnum =0;	//当序列号达到 999时，刷新序列号
			}
			try {
				String barcodesql = "update barcode set mnum = "+runnum;
				
				Sqlhelper0.executeUpdate(barcodesql, null);
				System.out.println(barcodesql +"ok");
			} catch (Exception e) {
				// TODO: handle exception
			}finally{
			}
			
		return barcode;
		}
}

