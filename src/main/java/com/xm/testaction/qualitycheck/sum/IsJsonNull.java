package com.xm.testaction.qualitycheck.sum;

public class IsJsonNull {
	public static boolean isJsonNull (Object json ){
		boolean s = false;
//		System.out.println(json);
//		String temp = json.toString();
		if(json ==null ||json.toString().equals("null")){
			s =true;
		}
		return s;
	}
//	public static void main (String []args){
//		Object s = null;
//		System.out.println(s);
//		System.out.println(s.toString());
//		
//	}
}
