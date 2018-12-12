package com.xm.testaction.qualitycheck.sum;

public class AidCostConfirm {
	public static String isSup(String islailiao){
		String issup="Y";
		if(islailiao!= null && islailiao.equals("N")){
			issup = "N";
		}
		return issup;
	}
}
