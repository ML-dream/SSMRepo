package cfmes.util;

import java.io.PrintWriter;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.wl.common.DatabaseCommon;
import com.wl.tools.Sqlhelper;

public class sql_data {  //�����login.java�����ˡ���fy

	 private DataSource source=null; //ͬ��--��
	 private PrintWriter out = null;  //java.io���µ���--��
 
	 private static Connection conn;
	 private static PreparedStatement ps;
	 private static ResultSet rs;
	 private static String driver;
	 private static String url;
	 private static String username;
	 private static String password;
 
	 public Connection getConn(){
	        return conn;
	 }

		static{
			try {
				driver  = DatabaseCommon.driver;
				url = DatabaseCommon.url;
				username = DatabaseCommon.username;
				password = DatabaseCommon.password;
				
				Class.forName(driver);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		/*
		public static Map get(){

			Map<String,String> map = new HashMap<String, String>();
			try {
				map.put("driver", "oracle.jdbc.driver.OracleDriver");
				map.put("url", "jdbc:oracle:thin:@127.0.0.1:1521:orclwl");
				map.put("username", "work");
				map.put("password", "work");	
			} catch (Exception e) {
				e.printStackTrace();
			}
			return map;
		}
		 */
//	����ݿ�����
//	  private void openConn() {
//	    try {
//	      if (conn == null) {
//	    	  Context ic=new InitialContext();  //����һ����ʼ������--��
//			  source=(DataSource)ic.lookup("java:comp/env/jdbc/cfmes");//lookup����ָ���Ķ���--��
//			  conn =source.getConnection();
//	      }
//	    }catch (SQLException ex) {
//	    	out.println("<script>alert('��ݿ�cfmes����ʧ��!')</script>");
//	    	out.print("��ݿ�cfmes����ʧ��!");out.flush();out.close();
//	      ex.printStackTrace();
//	    }catch (NamingException ex) {
//	    	out.print("��ݿ�cfmes����ʧ��!");out.flush();out.close();
//	      ex.printStackTrace();
//	    }catch (Exception ex) {
//	    	out.print("��ݿ�cfmes����ʧ��!");out.flush();out.close();
//	    	out.println("<script>alert('��ݿ�cfmes����ʧ��!')</script>");
//	    }
//	  }
//�ر���ݿ�����
	  public void closeConn() {
	    try {//System.out.println("conn+"+conn);
	    	if (conn != null) {
	    		
	            conn.close();
	            conn = null;
	            //System.out.println("conn+close+"+conn );
	        }
	    }
	    catch (SQLException ex) {
	    	out.print("������ӳ�cfmes�ر�ʧ�ܣ�");out.flush();out.close();
	      ex.printStackTrace();
	    }
	  }

//	public Connection getConnection() {
//		try{
//			if (conn == null) {
//		    	  Context ic=new InitialContext();
//				  source=(DataSource)ic.lookup("java:comp/env/jdbc/cfmes");
//				  conn =source.getConnection();
//		    }
//		}catch(Exception e){
//			e.printStackTrace();
//		}
//		return conn;
//	}
	
	
	public ResultSet executeQuery(String sql) {
		ResultSet rs = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
			ps = conn.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			rs = ps.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
		}
		return rs;
		

	}
	public int executeUpdate(String sql) {
		int result = 0;
	
		try {
			conn = DriverManager.getConnection(url, username, password);
			ps = conn.prepareStatement(sql);
			result= ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			Sqlhelper.close(conn, ps, null);
		}
			
		return result;
	}
	
	public int exeUpdateThrowExcep(String sql)throws SQLException {
		int result = 0;
	
		try {
			conn = DriverManager.getConnection(url, username, password);
			ps = conn.prepareStatement(sql);
			result= ps.executeUpdate();
		} finally{
			Sqlhelper.close(conn, ps, null);
		}
			
		return result;
	}

}
