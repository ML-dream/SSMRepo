package com.xm.testaction.qualitycheck.sum;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.wl.tools.Sqlhelper0;

public class SumByEmpClose {
	public static void sumByEmpClose(){
		String sql = "truncate table empsumtem";
		String sqlb = "truncate table empsumtem2";
		String sqla = "select count(*) from empsumtem a " ;
		String sqlba =  "select count(*) from empsumtem2 b " ;
		try {
			Sqlhelper0.executeUpdate(sql, null);
			System.out.println("ok  "+sql);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			ResultSet rsa = null;
			try {
				
				rsa= Sqlhelper0.executeQuery(sqla, null);
				int con = 0;
				rsa.next();
				con =rsa.getInt(1);
				if(con != 0){
					try {
						Sqlhelper0.executeUpdate(sql, null);
						System.out.println("ok  "+sql);
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}finally{
				if(rsa != null){
					try {
						rsa.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		
		try {
			Sqlhelper0.executeUpdate(sqlb, null);
			System.out.println("ok  "+sqlb);
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			ResultSet rsa = null;
			try {
				
				rsa= Sqlhelper0.executeQuery(sqlba, null);
				int con = 0;
				rsa.next();
				con =rsa.getInt(1);
				if(con != 0){
					try {
						Sqlhelper0.executeUpdate(sqlb, null);
						System.out.println("ok  "+sqlb);
					} catch (Exception e2) {
						// TODO: handle exception
					}
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}finally{
				if(rsa != null){
					try {
						rsa.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
	}
}
