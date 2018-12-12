package com.xm.testaction.qualitycheck.cardhandle;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.wl.tools.Sqlhelper0;

public class RejectJudge {
//	判断是否出现不合格品
	public static String rejectJudge(int reject_num){
		String result = "success";
//		不合格数规定为整数，只要不为零
		if (reject_num != 0){
			result = "rejectOccured";
		}
		return result;
	}
//	判断 修改前 是否有不合格品
	public static boolean numSearch(String barcode){
		boolean result = false;
		ResultSet numrst = null;
		String sql = "select reject_num from edit_control where barcode='"+barcode+"'";
		try{
			numrst = Sqlhelper0.executeQuery(sql, null);
			numrst.next();
			int reject_num = numrst.getInt(1);
			if(reject_num >0){
				result = true;
			}
		}catch (Exception e) {
			// TODO: handle exception
		}finally{
			if(numrst != null){
				try {
					numrst.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		return result;
	}
	
//	更新不合格信息
	public static void updateRejectInfo(String workername,int accept_num,int reject_num,String checker,int confirm_num,String barcode,int fo_no){
		String sql = "update reject_info set workername='"+workername+"',accept_num="
						+accept_num+",reject_num="+reject_num+",confirm_num="+confirm_num+",checker='"+checker+"' where " +
						"barcode='"+barcode+"' and fo_no="+fo_no+"";
		try {
			Sqlhelper0.executeUpdate(sql, null);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
//	更新编辑权限表的不合格数量，否则会一直提醒
	public static void updateEditControl (String barcode ,String fo,int reject){
		String sqla = "update edit_control t set t.reject_num= '"+reject+"' where t.barcode='"+barcode+"' and t.fo_no='"+fo+"'";
		try {
			Sqlhelper0.executeUpdate(sqla, null);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
	
//	插入 不合格信息
	public static void insertRejectInfo(String barcode,String fo_opname,double rated_time,int fnum,String workername,int accept_num,int reject_num,String checker,int fo_no,int confirm_num){
		
		String sql = "insert into reject_info (barcode,fo_opname,rated_time,fnum,workername,accept_num,reject_num,checker,confirm_num,fo_no) " +
						"values ('"+barcode+"','"+fo_opname+"',"+rated_time+","+fnum+",'"+workername
							+"',"+accept_num+","+reject_num+",'"+checker+"',"+confirm_num +","+fo_no+")";
		System.out.println(sql);
		try {
			Sqlhelper0.executeUpdate(sql, null);
		}catch (Exception e) {
			// TODO: handle exception
		}
	}
}
