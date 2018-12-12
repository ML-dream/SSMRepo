package com.wl.tools;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class Sqlhelper {
	
//	private static  Logger log = Logger.getLogger(Sqlhelper.class);
	
	private static Connection conn;
	private static PreparedStatement ps;
	private static ResultSet rs;
	public static int executeUpdate(String sql,String []params) throws SQLException{
		System.out.println(sql);
//		if(log.isDebugEnabled()){
//			log.debug(sql);
//		}
		
		PrintArray.print(params);
		int countNum = 0;
		try {
			conn = JdbcUtilsC3P0.getConnection();
			ps = conn.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					ps.setString(i+1, params[i]);
				}
			}
			countNum = ps.executeUpdate();
			
		} finally{
			Sqlhelper.close(conn, ps, rs);
		}
		return countNum;
	}
	
//	@Deprecated
	public static void executeUpdate_noclose(String sql,String []params) throws SQLException{
		System.out.println(sql);
		PrintArray.print(params);
		try {
			conn = JdbcUtilsC3P0.getConnection();
			ps = conn.prepareStatement(sql);
			if(params!=null){
				for(int i=0;i<params.length;i++){
					ps.setString(i+1, params[i]);
				}
			}
			ps.executeUpdate();
		} finally{
			Sqlhelper.close(conn, ps, null);
		}
		
	}
	


	@Deprecated

	public static ResultSet executeQuery(String sql,String []params)throws SQLException{
		System.out.println(sql);
		PrintArray.print(params);
		try {
			conn = JdbcUtilsC3P0.getConnection();
			ps = conn.prepareStatement(sql);
			if(params !=null){
				for(int i=0;i<params.length;i++){
					ps.setString(i+1, params[i]);
				}
			}
			rs = ps.executeQuery();
			
		}finally{
			//Sqlhelper.close(conn, ps, rs); 
		}
		return rs;
	} 
	
	
	/**
	 * 返回map集，避免rs不能关闭的问题
	 * @author Flair
	 * @date	2016-07-25
	 * */
	public static Map<String,String> executeQueryMap(String sql,String []params) throws SQLException{
		System.out.println(sql);
		PrintArray.print(params);
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			conn = JdbcUtilsC3P0.getConnection();
			ps = conn.prepareStatement(sql);
			if(params !=null){
				for(int i=0;i<params.length;i++){
					ps.setString(i+1, params[i]);
				}
			}
			rs = ps.executeQuery();
			rs.next();
			resultMap = getRsMap(rs);
			return resultMap;
		}finally{
			close(conn, ps, rs);
		}
		
		
	}
	
	private static Map<String, String> getRsMap(ResultSet rs) throws SQLException{
		Map<String, String> hm = new HashMap<String, String>();  
	    ResultSetMetaData rsmd = rs.getMetaData();  
        int count = rsmd.getColumnCount();  
        for (int i = 1; i <= count; i++) {  
            String key = rsmd.getColumnLabel(i);  
            String value = rs.getString(i);  
            hm.put(key, value);  
        }  
        return hm;  
	}
	
	@Deprecated
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
			close(conn, ps, rs);
		}
        return list;
	}
	
	/**
	 * 返回查询后的arrayList集合
	 * */
	public static ArrayList exeQueryList(String sql,String []params) throws Exception{
		System.out.println(sql);
		PrintArray.print(params);
		ArrayList list=null;
		try {
			conn = JdbcUtilsC3P0.getConnection();
			ps = conn.prepareStatement(sql);
			if(params !=null){
				for(int i=0;i<params.length;i++){
					ps.setString(i+1, params[i]);
				}
			}
			rs = ps.executeQuery();
			list = (ArrayList)ResultSetToList(rs);
		}finally{
			close(conn, ps, rs);
		}
		return list;
	}
	
	public static String exeQueryString(String sql,String []params) throws Exception{
		System.out.println(sql);
		PrintArray.print(params);
		String result = "";
		try {
			conn = JdbcUtilsC3P0.getConnection();
			ps = conn.prepareStatement(sql);
			if(params !=null){
				for(int i=0;i<params.length;i++){
					ps.setString(i+1, params[i]);
				}
			}
			rs = ps.executeQuery();
			rs.next();
			result = rs.getString(1);
		}finally{
			close(conn, ps, rs);
		}
		return result;
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
	@Deprecated
    public static ArrayList Al_executeQuery(String sql,String []parameters) throws SQLException{  
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
        } finally{  
        	Sqlhelper.close(conn, ps, rs); 
        }  
    } 
	
	/**
	 * 返回包含javaBean的List集合
	 * @throws SecurityException 
	 * @throws NoSuchMethodException 
	 * @throws InvocationTargetException 
	 * @throws IllegalArgumentException 
	 * @throws IllegalAccessException 
	 * @throws InstantiationException 
	 * */
    public  static <T> List<T> exeQueryList(String sql,String []params,Class<T> clazz ) 
    throws Exception{   
    	System.out.println(sql);
		PrintArray.print(params);
    	 try {  
    		conn = JdbcUtilsC3P0.getConnection();
 			ps = conn.prepareStatement(sql);
 			if(params !=null){
 				for(int i=0;i<params.length;i++){
 					ps.setString(i+1, params[i]);
 				}
 			} 
 			rs=ps.executeQuery();
  
 			ArrayList<T> al=new ArrayList<T>();  
 			ResultSetMetaData rsmd=rs.getMetaData();  
 			int column=rsmd.getColumnCount();  
            Method[] methods = clazz.getDeclaredMethods();
            
 			while(rs.next()){  
 				Map<String, Object> dataFromSourceMap = new HashMap<String, Object>();
 				for (int j = 1; j <= column; j++) {
 	  				String name = rsmd.getColumnName(j);
 	  				int scale = rsmd.getScale(j);
 	  				String columnTypeName = rsmd.getColumnTypeName(j);
 	  				Object value=null;
 	  				if ("VARCHAR2".equals(columnTypeName)||"VARCHAR".equals(columnTypeName)||"CHAR".equals(columnTypeName)) {			//String
 	  					value = rs.getString(name);
 	  				}
 	  				if ("DATE".equals(columnTypeName)) {											//Date
 	  					value = rs.getString(name);
 	  				}
 	  				if (scale<=0&&"NUMBER".equals(columnTypeName)) {								//int
 	  					value = rs.getInt(name);
 	  				}
 	  				if (scale>0&&"NUMBER".equals(columnTypeName)) {							//double
 	  					value = rs.getDouble(name);
 	  				}
 	  	 			name = name.replaceAll("_", "").toLowerCase();
 	  	 			dataFromSourceMap.put(name,value);
 	 			}
 				T object = clazz.newInstance();
 				for (int i = 0; i < methods.length; i++) {
 	            	String methodName =methods[i].getName();
 					if (methodName.startsWith("set")) {
 						String fieldName = methodName.substring(3,4).toLowerCase()+methodName.substring(4);
 						Object value = dataFromSourceMap.get(fieldName.toLowerCase());
 						if (null!=value) {		//从数据库中取出的值包含
							Method method = null;
							method = getMethod(value, methodName, clazz);
							method.setAccessible(true);
							method.invoke(object, dataFromSourceMap.get(fieldName.toLowerCase()));
						}
 					}
 				}
 				al.add(object);
             } 
             return al;  
         }finally{  
         	Sqlhelper.close(conn, ps, rs); 
         }  
	}
    
    public static <T> T exeQueryBean(String sql,String []params,Class<T> clazz) 
    throws Exception{
    	System.out.println(sql);
		PrintArray.print(params);
    	T bean = clazz.newInstance();
    	try {  
    		conn = JdbcUtilsC3P0.getConnection();
 			ps = conn.prepareStatement(sql);
 			if(params !=null){
 				for(int i=0;i<params.length;i++){
 					ps.setString(i+1, params[i]);
 				}
 			} 
 			rs=ps.executeQuery();
 			ResultSetMetaData rsmd=rs.getMetaData();  
 			int column=rsmd.getColumnCount();  
            Method[] methods = clazz.getDeclaredMethods();
            
 			rs.next();
			Map<String, Object> dataFromSourceMap = new HashMap<String, Object>();
			for (int j = 1; j <= column; j++) {
  				String name = rsmd.getColumnName(j);
  				int scale = rsmd.getScale(j);
  				String columnTypeName = rsmd.getColumnTypeName(j);
  				Object value=null;
  				if ("VARCHAR2".equals(columnTypeName)||"VARCHAR".equals(columnTypeName)||"NVARCHAR2".equals(columnTypeName)||"CHAR".equals(columnTypeName)||"LONG".equals(columnTypeName)) {			//String
  					value = rs.getString(name);
  				}
  				if ("DATE".equals(columnTypeName)) {											//Date
  					value = rs.getString(name);
  				}
  				if (scale<=0&&"NUMBER".equals(columnTypeName)) {								//int
  					value = rs.getInt(name);
  				}
  				if (scale>0&&"NUMBER".equals(columnTypeName)) {							//double
  					value = rs.getDouble(name);
  				}
  	 			name = name.replaceAll("_", "").toLowerCase();
  	 			dataFromSourceMap.put(name,value);
 			}
			
			for (int i = 0; i < methods.length; i++) {
            	String methodName =methods[i].getName();
				if (methodName.startsWith("set")) {
					String fieldName = methodName.substring(3,4).toLowerCase()+methodName.substring(4);
					Object value = dataFromSourceMap.get(fieldName.toLowerCase());
					if (null!=value) {		//从数据库中取出的值包含
						Method method = null;
						method = getMethod(value, methodName, clazz);
						method.setAccessible(true);
						method.invoke(bean, dataFromSourceMap.get(fieldName.toLowerCase()));
					}
				}
			} 
         }finally{  
         	Sqlhelper.close(conn, ps, rs); 
         }  
    	return bean;
    }
	
    public static int exeQueryCountNum(String sql,String []params) throws SQLException{
    	System.out.println(sql);
		PrintArray.print(params);
		int countNum =0;
    	try {  
    		conn = JdbcUtilsC3P0.getConnection();
 			ps = conn.prepareStatement(sql);
 			if(params !=null){
 				for(int i=0;i<params.length;i++){
 					ps.setString(i+1, params[i]);
 				}
 			} 
 			rs=ps.executeQuery();
 			rs.next();
 			countNum = rs.getInt(1);
         } finally{  
         	Sqlhelper.close(conn, ps, rs); 
         }  
         return countNum;
    }
    
	public static void close(Connection conn,Statement stmt,ResultSet rs){
//		if(rs!=null){
//			try {
//				rs.close();
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			rs = null;
//		}
		
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
	

	/* 
     * 校验name和type是否合法*/  
    private boolean check(String name, String type) {  
        if("".equals(name) || name == null || name.trim().length() ==0){  
            return false;  
        }  
        if("".equals(type) || type == null || type.trim().length() ==0){  
            return false;  
        }  
        return true;  
    }  
    
  //将名称首字符大写  
    private String GetHeaderUp(String name) {  
        name = name.trim();  
        if(name.length() > 1){  
            name = name.substring(0, 1).toUpperCase()+name.substring(1);  
        }else {  
            name = name.toUpperCase();  
        }  
        return name;  
    } 
    
    /* 
     * 获取字段的get方法字符串*/  
    private String getSetMethodString(String name, String type) {  
        if(!check(name,type)) {  
            System.out.println("类中有属性或者类型为空");  
            return null;  
        };  
        return "set"+GetHeaderUp(name);  
    } 
    
    /* 
     * 获取get方法字符串*/  
    private String getGetMethodString(String name, String type) {  
        if(!check(name,type)) {  
            System.out.println("类中有属性或者类型为空");  
            return null;  
        };  
        return "get"+GetHeaderUp(name);
    } 
    
    private static String getSimpleTypeName(String fullName){
    	return fullName.substring(fullName.lastIndexOf(".")+1);
    }
    
    private static Object getDataValue(String columnTypeName,String name, int scale , ResultSet rs) throws SQLException{
    	Object value=null;
    	if ("VARCHAR2".equals(columnTypeName)||"VARCHAR".equals(columnTypeName)) {			//String
				value = rs.getString(name);
		}
		if ("DATE".equals(columnTypeName)) {											//Date
			value = rs.getDate(name);
		}
		if (scale<0&&"NUMBER".equals(columnTypeName)) {								//int
			value = rs.getInt(name);
		}
		if (scale>0&&"NUMBER".equals(columnTypeName)) {							//double
			value = rs.getDouble(name);
		}
		return value;
    }
    
    private static <T> Method getMethod(Object value,String methodName,Class<T> clazz) throws NoSuchMethodException, SecurityException{
    	Method method = null;
		String simpleType = getSimpleTypeName(value.getClass().toString());
		if ("String".equals(simpleType)) {
			method = clazz.getDeclaredMethod(methodName,value.getClass());
		}
		if ("Date".equals(simpleType)) {
			method = clazz.getDeclaredMethod(methodName,String.class);
		}
		if ("Double".equals(simpleType)) {
			method = clazz.getDeclaredMethod(methodName,double.class);
		}
		if ("Integer".equals(simpleType)) {
			method = clazz.getDeclaredMethod(methodName,int.class);
		}
		method.setAccessible(true);
		return method;
    }    
}


