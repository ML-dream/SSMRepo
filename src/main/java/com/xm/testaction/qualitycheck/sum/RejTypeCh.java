package com.xm.testaction.qualitycheck.sum;

public class RejTypeCh {
	public static String rejTypeCh (String rejtype){
		String s = "返工";
		if(rejtype.equals("toDiscard")){
			s = "报废";
		}else if(rejtype.equals("toSale")){
			s= "超差使用";
		}
		return s;
	}
}
