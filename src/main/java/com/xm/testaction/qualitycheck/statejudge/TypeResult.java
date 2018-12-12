package com.xm.testaction.qualitycheck.statejudge;

public class TypeResult {
/*
 * 工种比较
 */
	public static boolean isQualityCheck(String current){
//		如果是工种是质检，返回true
		boolean result = false;
		if(current.equals("check")){
			result = true;
		}
		return result;
	}
}
