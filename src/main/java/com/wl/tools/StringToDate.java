package com.wl.tools;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class StringToDate {
	public static long FromChina24ToMills(String date){
		
		long mill=0;
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.CHINESE);
			mill = format.parse(date).getTime();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return mill;
	}

}
