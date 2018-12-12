package job;

import java.sql.ResultSet;

import com.wl.tools.Sqlhelper0;
import com.xm.testaction.qualitycheck.ToSum;

public class FixToSum {
// 9-11 返修产品   数据统计到统计表 
	public static void fixToSum(String runnum,int num,int fo_no,String sumbarcode){
		int lastfo = 0;
		String opinion = "tofix";
//		是否是最后一道工序
		String sqla ="select a.lastfo from reject_info a where a.barcode='"+sumbarcode+"' and a.fo_no ="+fo_no;
		ResultSet rsa = null;
		try {
			
			rsa = Sqlhelper0.executeQuery(sqla, null);
			rsa.next();
			lastfo = rsa.getInt(1);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(rsa != null){
		    	try {
				rsa.close();
			} catch (Exception e2) {
			// TODO: handle exception
				}
		}
		}

//		if (lastfo == 1){		//		最后一道工序
//			ToSum.fstateToSum(opinion, runnum, num, fo_no, sumbarcode);
//		}else{		//		如果不是最后一道工序
		try {
			ToSum.stateToSum(opinion, runnum, num, fo_no, sumbarcode);
		} catch (Exception e) {
			// TODO: handle exception
		}	
		
//		}

	}
}
