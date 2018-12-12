package com.xm.testaction.qualitycheck.cardhandle;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.wl.tools.Sqlhelper0;

public class RejectStateHelper {
	public static int rejectStaticHelper(String barcode,int fo_no){
		int result = 0;		//表示 剩余不合格数
//		从reject_info表找 总不合格数,以及当前审理单中的不合格数
		String sqla = "select nvl(b.reject_num,0) snum,sum(nvl(a.rejectnum,0)) anum from reject_state a " +
				" left join reject_info b on b.barcode = a.barcode and b.fo_no = a.fo_no " +
				" where a.barcode = '"+barcode+"' and a.fo_no = '"+fo_no+"' " +
						"group by b.reject_num";
		ResultSet rsa = null;
		try {
			System.out.println(sqla);
			rsa = Sqlhelper0.executeQuery(sqla, null);
			System.out.println("ok  "+sqla);
			rsa.next();
			int snum = rsa.getInt(1);	//该工序总不合格数
			int anum = rsa.getInt(2);	//已审理的不合格数
			if(anum < snum){
				result = snum -anum;
			}else{
				result = 0;
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(rsa !=null){
				try {
					rsa.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		System.out.println(result);
		return result;
	}
}
