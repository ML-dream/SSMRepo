package com.xm.testaction.qualitycheck;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.wl.tools.Sqlhelper0;

public class StateJudge {
//查找非正常工件的状态
	public static int searchState(String barcode){
		int grade = 0;	//status
		String sql= "select status from attach_info where barcode='"+barcode+"'";
		
		ResultSet stateRst = null;
		try{
			stateRst = Sqlhelper0.executeQuery(sql, null);
			stateRst.next();
			grade = stateRst.getInt(1);
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			//    Sqlhelper0.close();
			if(stateRst != null){
		    	try {
		    		stateRst.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}
		return grade;
	}
//	判断工件状态
	public static String stateJudge(int grade){
		String state ="正常";
		
		switch (grade) {
		case 1:
			state = "返工";
			break;
		case 2:
			state = "多批次";
			break;
		case 3:
			state = "报废重新下单";
		default:
			break;
		}
		return state;
	}
}
