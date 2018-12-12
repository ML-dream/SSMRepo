//package com.wl.websocketDNC;
//
//import java.sql.*;
//import java.util.Properties;
//import oracle.jdbc.*;
//import oracle.jdbc.dcn.*;
//public class OracleDCNSimple {
//
//	/**
//	 * @param args
//	 */
//	public static void main(String[] args) {
//
//		OracleDCNSimple demo = new OracleDCNSimple();
//		try {
//			demo.run();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//	
//	
//	/**
//	 *  jdbc.driver=oracle.jdbc.driver.OracleDriver
//		jdbc.url=jdbc:oracle:thin:@192.168.2.68:1521:orcl
//		jdbc.user=ELEV_USER
//		jdbc.password=elev1234
//	 * 
//	 * */
//	
//	static final String USERNAME = "scott";
//	static final String PASSWORD = "wanglu";
//	static String URL = "jdbc:oracle:thin:@192.168.11.125:1521:orclwl";
//	
//	void run () throws SQLException {
//		OracleConnection coon = connect();
//		Properties prop = new Properties();
//		//prop.setProperty(OracleConnection.NTF_LOCAL_HOST, "14.14.13.12");
//		prop.setProperty(OracleConnection.DCN_NOTIFY_ROWIDS, "true");
//		prop.setProperty(OracleConnection.DCN_IGNORE_DELETEOP, "false");
//		prop.setProperty(OracleConnection.NTF_TIMEOUT, "3600");
//		DatabaseChangeRegistration dcr = coon.registerDatabaseChangeNotification(prop);
//		
//		try {
//			dcr.addListener(new DatabaseChangeListener() {
//				
//				public void onDatabaseChangeNotification(DatabaseChangeEvent e) {
//					// TODO Auto-generated method stub
//					DatabaseChangeEvent.EventType eType = e.getEventType();
//					System.out.println("receive "+ eType + " event, RegId= "+ e.getRegId());
//				}
//			});
//			coon.setAutoCommit(true);			
//			
//			String sql = "update TEST_WL_USER_DICT set name= ? where id = '1'";
//			
//			Statement stmt = coon.createStatement();
//			((OracleStatement)stmt).setDatabaseChangeRegistration(dcr);
//			ResultSet rs = stmt.executeQuery("select 1 from TEST_WL_USER_DICT where 1=2");
//			for (String tableName : dcr.getTables()) {
//				System.out.println("register on ===="+tableName);
//			}
//		
//			
//			//ResultSet rs = upPstmt.executeQuery(sqlTest);
//			//rs.next();
//			//System.out.println(rs.getString(1));
//
//			rs.close();
//			stmt.close();
//			System.out.println("dddddddddd");
//		} catch (SQLException e) {
//			// TODO: handle exception
//			if (coon != null)
//				coon.unregisterDatabaseChangeNotification(dcr);
//			throw e;
//		} finally{
//			try {
//				// Note that we close the connection!
//				coon.close();
//			} catch (Exception innerex) {
//				innerex.printStackTrace();
//			}
//		}
//	}
//	
//	
//	OracleConnection connect () throws SQLException{
//		OracleDriver driver = new OracleDriver();
//		Properties prop = new Properties();
//		prop.setProperty("user", OracleDCNSimple.USERNAME);
//		prop.setProperty("password", OracleDCNSimple.PASSWORD);
//		Connection coon = driver.connect(OracleDCNSimple.URL, prop);
//		OracleConnection ocoon = (OracleConnection)coon.unwrap(OracleConnection.class);
//		return ocoon;
//	}
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//	
//
//}
