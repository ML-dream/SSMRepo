package com.wl.tools;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.rowset.CachedRowSet;

import com.sun.rowset.CachedRowSetImpl;
import com.wl.common.DatabaseCommon;

public class Sqlhelper0 {
	private static Connection conn;
	private static PreparedStatement ps;
	private static ResultSet rs;

	public static void executeUpdate(String sql,String []parameters){
		try {
			conn = JdbcUtilsC3P0.getConnection();
			ps = conn.prepareStatement(sql);
			if(parameters!=null){
				for(int i=0;i<parameters.length;i++){
					ps.setString(i+1, parameters[i]);
				}
			}
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			Sqlhelper0.close2(conn,ps,rs);
		}
		
	}
	
	
	public static void executeUpdate_noclose(String sql,String []parameters){
		try {
			conn = JdbcUtilsC3P0.getConnection();
			ps = conn.prepareStatement(sql);
			if(parameters!=null){
				for(int i=0;i<parameters.length;i++){
					ps.setString(i+1, parameters[i]);
				}
			}
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
//			Sqlhelper0.close();
		}
		
	}
	
	//��ѯ����
	public static ResultSet executeQuery(String sql,String []parameters){
		System.out.println(sql);
		try {
			conn = JdbcUtilsC3P0.getConnection();
			ps = conn.prepareStatement(sql);
			if(parameters !=null){
				for(int i=0;i<parameters.length;i++){
					ps.setString(i+1, parameters[i]);
				}
			}
			rs = ps.executeQuery();
			
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
			
		}
		CachedRowSet crs = null;
		try {
			crs = new CachedRowSetImpl();
			crs.populate(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			Sqlhelper0.close2(conn, ps, rs);
		} 
		
		return crs;
		
	} 
	
	
	public ArrayList DBSelect(String sql) throws Exception{
		ArrayList list=null;
		
		try {
			conn = JdbcUtilsC3P0.getConnection();
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			list = (ArrayList)ResultSetToList(rs);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		}finally{
//			close();
		}
        return list;
	}
	
	private static ArrayList ResultSetToList(ResultSet   rs) throws Exception{    	
    	ResultSetMetaData md = rs.getMetaData();
    	int columnCount = md.getColumnCount();
    	ArrayList list = new ArrayList();
    	Map rowData;
    	while(rs.next()){
	    	rowData = new HashMap(columnCount);
	    	for(int i = 1; i <= columnCount; i++)   {	 	    		
	    		Object v = rs.getObject(i);	    		
	    		//ʱ�����͵�ת���������൱��Ҫ������
	    		if(v != null && (v.getClass() == Date.class || v.getClass() == java.sql.Date.class)){
	    			Timestamp ts= rs.getTimestamp(i);
	    			v = new java.util.Date(ts.getTime());
	    		}else if(v != null && v.getClass() == Clob.class){
	    			v = clob2String((Clob)v);
	    		}
	    		rowData.put(md.getColumnName(i),   v);
	    	}
	    	list.add(rowData);	    	
    	}
    	return list;
	}
	private static String clob2String(Clob clob) throws Exception {
        return (clob != null ? clob.getSubString(1, (int) clob.length()) : null);
    } 
	
	
	
	
    public static ArrayList Al_executeQuery(String sql,String []parameters){  
        try {  
        	conn = JdbcUtilsC3P0.getConnection();
			ps = conn.prepareStatement(sql);
			if(parameters !=null){
				for(int i=0;i<parameters.length;i++){
					ps.setString(i+1, parameters[i]);
				}
			} 
            rs=ps.executeQuery();  
            ArrayList al=new ArrayList();  
            ResultSetMetaData rsmd=rs.getMetaData();  
            int column=rsmd.getColumnCount();  
            while(rs.next()){  
                Object[] ob=new Object[column];  
                for(int i=0;i<ob.length;i++){  
                    ob[i]=rs.getObject(i+1);  
                }  
                al.add(ob);  
            }  
            return al;  
        } catch (Exception e) {   
            e.printStackTrace();  
            return null;  
        }finally{  
//        	Sqlhelper0.close(); 
        }  
    } 
	
	
	
	
	//�ر���Դ
	public static void close(){
	/*
		if(rs!=null){
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		
		if(ps!=null){
			try {
				ps.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			ps = null;
		}
		
		if(conn!=null){
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn=null;
		}
*/
	}
	
	public static void close2(Connection conn,Statement stmt,ResultSet rs){
		if(rs!=null){
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			rs = null;
		}
		
		if(stmt!=null){
			try {
				stmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			stmt = null;
		}
		
		if(conn!=null){
			try {
				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			conn=null;
		}
	}
	

	
}

