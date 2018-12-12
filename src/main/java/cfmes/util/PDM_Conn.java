package cfmes.util;
import java.io.PrintWriter;
import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
public class PDM_Conn {
	private Connection conn=null;
    private DataSource source=null;
//    public Statement stmt = null;
    private PrintWriter out = null;
//    public PDM_Conn() {
//	    openConn();
//    }
//	打开数据库连接
	  private void openConn() {
	    try {
	      if (conn == null) {
	    	  Context ic=new InitialContext();
			  source=(DataSource)ic.lookup("java:comp/env/jdbc/winddb");
			  conn =source.getConnection();
	      }
	    }
	    catch (SQLException ex) {
	    	out.print("数据库winddb连接失败!");out.flush();out.close();
	      ex.printStackTrace();
	    }
	    catch (NamingException ex) {
	    	out.print("数据库winddb连接失败!");out.flush();out.close();
	      ex.printStackTrace();
	    }
	    catch (Exception ex) {
	    	out.print("数据库winddb连接失败!");out.flush();out.close();
	      ex.printStackTrace();
	    }
	  }
//关闭数据库连接
	  public void closeConn() {
		  try {
			  
		        if (conn != null && !conn.isClosed()) {
		            conn.close();
		            conn = null;
		        }
		    }
		    catch (SQLException ex) {
		    	out.print("数据连接池winddb关闭失败！");out.flush();out.close();
		      ex.printStackTrace();
		    }
	  }
	public ResultSet executeQuery(String sql) {
		ResultSet rs = null;
	    openConn();
		try {
			Statement stmt = conn.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			rs = stmt.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println("PDM_Conn.executeQuery:" + e.getMessage());
		}
		return rs;
	}
}