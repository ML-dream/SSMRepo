package com.xm.testaction.qualitycheck.sum;

import com.wl.tools.Sqlhelper;

public class AddNewStuff {
	/**
	 * 判断是否新材料
	 * @param stuff
	 */
//	public static void main (String [] args){
//		addNewStuff("58559");
//	}
	public static void addNewStuff(String stuff){
		String sqla = "declare " +
				"total number; " +
				"begin " +
				"select count(1) into total from STUFFPRICE where stuffid ='"+stuff+"';" +
				"if total = 0 then " +
				"insert into  STUFFPRICE  (STUFFID, STUFFNAME, PRICE, DENSITY) values ('"+stuff+"','"+stuff+"',0,0);"+
				"end if;" +
				"end;";
		try {
			System.out.println(sqla);
			Sqlhelper.executeUpdate(sqla, null);
			System.out.println("ok  "+sqla);
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
