package com.wl.tools;

public class Null_change {
	public static String nulltoString(Object obj){
		System.out.println("obj.toString())=="+obj);
		if(obj.toString()==null||obj.toString().trim().length()==0){
			System.out.println("传入为空！！！！");
			return "";
		}
		else{
			return obj.toString();
		}
	}

}
